import { CurrencyPipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, computed, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

import { Product } from '../product';
import { ProductService } from '../product.service';

/*
 * The home page: a searchable table of products.
 * State lives in signals. This app is zoneless (no zone.js), so Angular re-renders when a signal it
 * read in the template changes: products.set(...) updates the table, nothing else is needed.
 */
@Component({
  selector: 'app-product-list',
  imports: [CurrencyPipe, RouterLink],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductList {
  private readonly productService = inject(ProductService);

  protected readonly products = signal<Product[]>([]);
  protected readonly loading = signal(true);
  protected readonly error = signal<string | null>(null);

  // computed: derived from other signals, recalculated only when products() changes
  protected readonly totalValue = computed(() =>
    this.products().reduce((sum, p) => sum + p.price * p.quantity, 0),
  );

  constructor() {
    this.load();
  }

  protected load(search = ''): void {
    this.loading.set(true);
    this.productService.list(search).subscribe({
      next: (products) => {
        this.products.set(products);
        this.error.set(null);
        this.loading.set(false);
      },
      error: (e: HttpErrorResponse) => {
        // status 0 = the request never got an answer: backend down, or blocked by CORS
        this.error.set(e.status === 0 ? 'Cannot reach the product service on port 8080. Is it running?' : e.message);
        this.loading.set(false);
      },
    });
  }

  protected remove(product: Product): void {
    if (!confirm(`Delete "${product.name}"?`)) return;
    this.productService.delete(product.id!).subscribe(() =>
      // update(): the new value is computed from the old one
      this.products.update((list) => list.filter((p) => p.id !== product.id)),
    );
  }
}
