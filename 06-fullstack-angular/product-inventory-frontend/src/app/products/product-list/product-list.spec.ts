import { HttpErrorResponse } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { of, throwError } from 'rxjs';

import { Product } from '../product';
import { ProductService } from '../product.service';
import { ProductList } from './product-list';

/*
 * The component with a FAKE ProductService (useValue): the test controls exactly what "the backend"
 * returns, and checks what ends up on the page.
 */
describe('ProductList', () => {
  const products: Product[] = [
    { id: 1, name: 'Ball pen', price: 10, quantity: 5 },
    { id: 2, name: 'Desk lamp', price: 900, quantity: 0 },
  ];

  async function render(service: Partial<ProductService>) {
    await TestBed.configureTestingModule({
      imports: [ProductList],
      providers: [provideRouter([]), { provide: ProductService, useValue: service }],
    }).compileComponents();
    const fixture = TestBed.createComponent(ProductList);
    await fixture.whenStable();
    return fixture;
  }

  it('shows one row per product and the total stock value', async () => {
    const fixture = await render({ list: () => of(products) });
    const page = fixture.nativeElement as HTMLElement;

    expect(page.querySelectorAll('tbody tr').length).toBe(2);
    expect(page.querySelector('tbody tr.out-of-stock')?.textContent).toContain('Desk lamp');
    expect(page.querySelector('.total')?.textContent).toContain('50.00');     // 10 x 5 + 900 x 0
  });

  it('removes a deleted product from the table', async () => {
    const remove = vi.fn(() => of(undefined));
    vi.spyOn(window, 'confirm').mockReturnValue(true);
    const fixture = await render({ list: () => of(products), delete: remove });

    (fixture.nativeElement as HTMLElement).querySelector<HTMLButtonElement>('tbody tr button')!.click();
    await fixture.whenStable();

    expect(remove).toHaveBeenCalledWith(1);
    expect((fixture.nativeElement as HTMLElement).querySelectorAll('tbody tr').length).toBe(1);
  });

  it('explains when the backend cannot be reached', async () => {
    const fixture = await render({ list: () => throwError(() => new HttpErrorResponse({ status: 0 })) });

    expect((fixture.nativeElement as HTMLElement).querySelector('.error')?.textContent).toContain('port 8080');
  });
});
