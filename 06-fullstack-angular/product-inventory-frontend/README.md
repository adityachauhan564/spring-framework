# Product Inventory (Angular frontend)

> An Angular 21 app for [`../product-service-backend`](../product-service-backend): a searchable product table with the total stock value, and a create/edit form with validation. It's built from standalone components, signals, the router, `HttpClient` and reactive forms.

**Before this:** the backend README. Start the backend first; this app calls it on port 8080.

## Why it matters
Most backends end up behind a frontend. Building one shows what the API must provide:
- predictable JSON;
- errors the user can read;
- CORS, so the browser allows the call.

It also covers the basics of a modern Angular app: routing, a service for HTTP, state in signals, and forms.

## What it teaches
- Standalone components (no `NgModule`): each lists what it uses in `imports`
- App-wide providers in `app.config.ts`: `provideRouter`, `provideHttpClient`
- **Routing:** `app.routes.ts`, `<router-outlet>`, `routerLink`, route params delivered as component `input()`s
- **A service for all HTTP calls:** `HttpClient` returns Observables
- **Signals:** `signal`, `computed` and `update`. This app is **zoneless**: signals are what trigger re-rendering
- **Built-in control flow:** `@if`, `@for` (with `track`), `@empty`
- **Reactive forms:** `FormBuilder`, validators, showing client and server errors
- **Tests with Vitest:** `HttpTestingController`, fake services with `useValue`, `vi.fn()`

## Run it
Prerequisites: Node 20+ (tested with Node 22) and npm.
```bash
npm ci                         # install the exact versions from package-lock.json
npx ng serve                   # http://localhost:4200 (start the backend first)
npx ng test --watch=false      # 12 tests, no backend needed
npx ng build                   # production build into dist/
```
With the backend running, open http://localhost:4200.
- **The list page:** the sample products, sorted by name. Out-of-stock rows are red, and the stock value is at the bottom.
- **Search:** type in the search box, and the list reloads from `GET /api/products?search=`.
- **Create Product:** leave the name empty and Save stays disabled. A negative price shows a message.
- **Edit and Delete:** work on each row.

If the backend is down you see "Cannot reach the product service on port 8080". The same message appears if CORS blocks the call: the browser hides the real response, so the app only sees status 0. Open the browser console to see which one it is.

## Read the code in this order
1. `src/main.ts` → `src/app/app.config.ts`: bootstrapping and providers
2. `src/app/app.routes.ts`, `app.ts` / `app.html`, `header/`: pages and navigation
3. `src/app/products/product.ts` and `product.service.ts`: the model and the HTTP calls
4. `src/app/products/product-list/`: signals, `computed`, `@for`, delete
5. `src/app/products/product-form/`: the reactive form, create vs edit, server errors
6. The `*.spec.ts` files next to each: `product.service.spec.ts` → `product-list.spec.ts` → `product-form.spec.ts`

## Revision notes
- **Where providers go:** a standalone app has no `AppModule`, so app-wide providers go in `app.config.ts`. `HttpClient` can only be injected after `provideHttpClient()`.
- **Observables are lazy:** `http.get(...)` sends nothing until something subscribes. The service returns the Observable, and the component subscribes and stores the result in a signal.
- **Zoneless change detection:** there is no zone.js watching every event, so Angular re-renders when a signal read by the template changes (`products.set(...)`). Plain fields changed in a callback would **not** update the page.
- **`computed`** caches its value and recalculates only when a signal it reads changes; `totalValue` depends on `products()`. **`update(fn)`** sets a signal from its previous value.
- **`@for` needs `track`:** tracking by `product.id` lets Angular keep the DOM rows of unchanged items. `@empty` renders when the list is empty.
- **Route params as inputs:** `withComponentInputBinding()` copies `:id` from `/products/:id/edit` into `id = input<string>()`. One form component then handles both new and edit.
- **Validation happens twice:** in the form (Validators) for instant feedback, and in the backend, which has the final say. Show the backend's `errors` too, because it may know rules the form doesn't.
- **Status 0** in `HttpErrorResponse` means no response reached the app: server down, network error, or CORS.
- **Component templates are fragments:** never put `<html>` or `<body>` in them, only in `src/index.html`.
- **Testing:**
  - fake the service (`{ provide: ProductService, useValue: {...} }`) to test a component without HTTP;
  - use `HttpTestingController` to test the service without a server;
  - test behaviour (rows shown, button disabled), not exact text.

## Status
✅ **Working.** 12 tests pass and the production build succeeds. The list page was rendered in headless Chrome against the running backend: 4 products and the correct stock value, loaded across origins.
