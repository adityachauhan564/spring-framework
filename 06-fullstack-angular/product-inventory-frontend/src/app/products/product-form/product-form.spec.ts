import { HttpErrorResponse } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { Router, provideRouter } from '@angular/router';
import { of, throwError } from 'rxjs';

import { ProductService } from '../product.service';
import { ProductForm } from './product-form';

describe('ProductForm', () => {
  async function render(service: Partial<ProductService>, id?: string) {
    await TestBed.configureTestingModule({
      imports: [ProductForm],
      providers: [provideRouter([]), { provide: ProductService, useValue: service }],
    }).compileComponents();
    const fixture = TestBed.createComponent(ProductForm);
    if (id) fixture.componentRef.setInput('id', id);        // what the router does for /products/:id/edit
    await fixture.whenStable();
    return fixture;
  }

  function type(page: HTMLElement, name: string, value: string) {
    const input = page.querySelector<HTMLInputElement>(`input[formControlName="${name}"]`)!;
    input.value = value;
    input.dispatchEvent(new Event('input'));
    input.dispatchEvent(new Event('blur'));                  // the user left the field, so it is "touched": now errors may show
  }

  it('keeps Save disabled until the form is valid, then creates and goes home', async () => {
    const create = vi.fn(() => of({ id: 9, name: 'Stapler', price: 150, quantity: 8 }));
    const fixture = await render({ create });
    const navigate = vi.spyOn(TestBed.inject(Router), 'navigate').mockResolvedValue(true);
    const page = fixture.nativeElement as HTMLElement;
    const save = page.querySelector<HTMLButtonElement>('button[type="submit"]')!;

    expect(save.disabled).toBe(true);                        // name is required
    type(page, 'name', 'Stapler');
    type(page, 'price', '150');
    type(page, 'quantity', '8');
    await fixture.whenStable();
    expect(save.disabled).toBe(false);

    save.click();
    expect(create).toHaveBeenCalledWith({ name: 'Stapler', price: 150, quantity: 8 });
    expect(navigate).toHaveBeenCalledWith(['/']);
  });

  it('shows a validation message for a negative price', async () => {
    const fixture = await render({});
    const page = fixture.nativeElement as HTMLElement;

    type(page, 'price', '-5');
    await fixture.whenStable();

    expect(page.textContent).toContain("Price can't be negative");
  });

  it('in edit mode loads the product and saves with PUT', async () => {
    const update = vi.fn(() => of({ id: 4, name: 'Lamp', price: 800, quantity: 3 }));
    const fixture = await render({ get: () => of({ id: 4, name: 'Lamp', price: 900, quantity: 3 }), update }, '4');
    vi.spyOn(TestBed.inject(Router), 'navigate').mockResolvedValue(true);
    const page = fixture.nativeElement as HTMLElement;

    expect(page.querySelector('h1')?.textContent).toContain('Edit product');
    expect(page.querySelector<HTMLInputElement>('input[formControlName="name"]')!.value).toBe('Lamp');

    type(page, 'price', '800');
    page.querySelector<HTMLButtonElement>('button[type="submit"]')!.click();
    expect(update).toHaveBeenCalledWith(4, { name: 'Lamp', price: 800, quantity: 3 });
  });

  it("shows the backend's field errors from a 400", async () => {
    const error = new HttpErrorResponse({ status: 400, error: { errors: { name: 'Name is required' } } });
    const fixture = await render({ create: () => throwError(() => error) });
    const page = fixture.nativeElement as HTMLElement;

    type(page, 'name', 'X');
    await fixture.whenStable();
    page.querySelector<HTMLButtonElement>('button[type="submit"]')!.click();
    await fixture.whenStable();

    expect(page.textContent).toContain('Name is required');
  });
});
