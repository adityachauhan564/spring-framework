import { CurrencyPipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, computed, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

import { Product } from '../product';
import { ProductService } from '../product.service';

/*
 * The home page: a table of products that you can search.
 * The data lives in signals (a signal = a value that tells the screen to refresh when it changes).
 * This app is zoneless (no zone.js), so Angular redraws the page when a signal used in the template
 * changes: products.set(...) updates the table, nothing else is needed.
 * Like a cricket scoreboard that updates by itself the moment a run is scored.
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

  // computed: a value worked out from other signals, recalculated only when products() changes
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
        // status 0 = the request never got an answer: the backend is down, or the browser blocked it (CORS)
        this.error.set(e.status === 0 ? 'Cannot reach the product service on port 8080. Is it running?' : e.message);
        this.loading.set(false);
      },
    });
  }

  protected remove(product: Product): void {
    if (!confirm(`Delete "${product.name}"?`)) return;
    this.productService.delete(product.id!).subscribe(() =>
      // update(): the new value is worked out from the old one
      this.products.update((list) => list.filter((p) => p.id !== product.id)),
    );
  }
}
