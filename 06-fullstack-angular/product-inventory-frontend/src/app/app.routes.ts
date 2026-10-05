import { Routes } from '@angular/router';

import { ProductForm } from './products/product-form/product-form';
import { ProductList } from './products/product-list/product-list';

// URL -> component. The matching component is shown where <router-outlet> is in app.html.
// Routes are checked in order, top to bottom. '**' catches everything else (any unknown URL).
export const routes: Routes = [
  { path: '', component: ProductList, title: 'Products' },
  { path: 'products/new', component: ProductForm, title: 'New product' },
  { path: 'products/:id/edit', component: ProductForm, title: 'Edit product' },
  { path: '**', redirectTo: '' },
];
