import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

import { Product } from './product';

// Where product-service-backend runs. Calling another port is a cross-origin call, so the backend's
// CorsConfig must allow http://localhost:4200 - otherwise the browser blocks the response.
export const API_URL = 'http://localhost:8080/api/products';

/*
 * All HTTP calls for products, in one place. Components never build URLs themselves.
 * HttpClient methods return an Observable: nothing is sent until someone subscribes
 * (like a newspaper that is delivered only after you subscribe), and the JSON body
 * arrives already converted into the given type.
 */
@Injectable({ providedIn: 'root' })   // one shared object for the whole app
export class ProductService {
  private readonly http = inject(HttpClient);

  list(search = ''): Observable<Product[]> {
    const params = search ? new HttpParams().set('search', search) : undefined;
    return this.http.get<Product[]>(API_URL, { params });
  }

  get(id: number): Observable<Product> {
    return this.http.get<Product>(`${API_URL}/${id}`);
  }

  create(product: Product): Observable<Product> {
    return this.http.post<Product>(API_URL, product);
  }

  update(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${API_URL}/${id}`, product);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/${id}`);
  }
}
