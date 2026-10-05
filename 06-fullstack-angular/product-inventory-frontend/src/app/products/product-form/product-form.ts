import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, inject, input, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { Product } from '../product';
import { ProductService } from '../product.service';

/*
 * One form for both "new" (/products/new) and "edit" (/products/:id/edit).
 * id is a route parameter, given to us as a component input: withComponentInputBinding() in app.config.ts
 * copies :id from the URL into this input.
 *
 * Reactive forms: the form is built in code (FormBuilder), and the template only connects to it.
 * The validators copy the backend's rules (@NotBlank, @PositiveOrZero), so most mistakes are caught before
 * sending. The backend still checks everything, and its field errors are shown too (serverErrors).
 * Like a form checked once at the counter, and again in the back office.
 */
@Component({
  selector: 'app-product-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './product-form.html',
})
export class ProductForm implements OnInit {
  private readonly productService = inject(ProductService);
  private readonly router = inject(Router);

  readonly id = input<string>();          // undefined on /products/new (a new product has no id yet)

  protected readonly form = inject(FormBuilder).nonNullable.group({
    name: ['', [Validators.required, Validators.maxLength(100)]],
    price: [0, [Validators.required, Validators.min(0)]],
    quantity: [0, [Validators.required, Validators.min(0)]],
  });
  protected readonly serverErrors = signal<Record<string, string>>({});
  protected readonly saving = signal(false);

  ngOnInit(): void {
    const id = this.id();
    if (id) {
      this.productService.get(Number(id)).subscribe((product) => this.form.setValue({
        name: product.name, price: product.price, quantity: product.quantity,
      }));
    }
  }

  protected save(): void {
    if (this.form.invalid) return;
    this.saving.set(true);
    const product: Product = this.form.getRawValue();
    const id = this.id();
    const request = id ? this.productService.update(Number(id), product) : this.productService.create(product);

    request.subscribe({
      next: () => this.router.navigate(['/']),
      error: (e: HttpErrorResponse) => {
        // a 400 from the backend carries {"errors": {"field": "message"}} (sent by its ApiExceptionHandler)
        this.serverErrors.set(e.error?.errors ?? { form: 'Saving failed: ' + e.message });
        this.saving.set(false);
      },
    });
  }

  // true when the user has touched the field and a rule fails, e.g. hasError('price', 'min')
  protected hasError(field: 'name' | 'price' | 'quantity', error: string): boolean {
    const control = this.form.controls[field];
    return control.touched && control.hasError(error);
  }
}
