import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { API_URL, ProductService } from './product.service';

/*
 * HttpTestingController replaces the network: the test sees each request the service makes
 * (method, URL, body) and decides the response with flush(). No backend needs to run.
 */
describe('ProductService', () => {
  let service: ProductService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({ providers: [provideHttpClient(), provideHttpClientTesting()] });
    service = TestBed.inject(ProductService);
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());   // fails if a request was made that the test didn't expect

  it('lists products, with the search as a query parameter', () => {
    let names: string[] = [];
    service.list('pen').subscribe((products) => (names = products.map((p) => p.name)));

    const request = http.expectOne(`${API_URL}?search=pen`);
    expect(request.request.method).toBe('GET');
    request.flush([{ id: 1, name: 'Ball pen', price: 10, quantity: 5 }]);
    expect(names).toEqual(['Ball pen']);
  });

  it('sends the product as the body when creating and updating', () => {
    const product = { name: 'Stapler', price: 150, quantity: 8 };

    service.create(product).subscribe();
    const create = http.expectOne(API_URL);
    expect(create.request.method).toBe('POST');
    expect(create.request.body).toEqual(product);
    create.flush({ id: 5, ...product });

    service.update(5, product).subscribe();
    expect(http.expectOne(`${API_URL}/5`).request.method).toBe('PUT');
  });

  it('deletes by id', () => {
    service.delete(3).subscribe();
    const request = http.expectOne(`${API_URL}/3`);
    expect(request.request.method).toBe('DELETE');
    request.flush(null, { status: 204, statusText: 'No Content' });
  });
});
