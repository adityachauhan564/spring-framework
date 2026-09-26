# Product Inventory (Angular frontend)

> An Angular 21 standalone-components app for the product inventory bootcamp. So far it has only the app shell and a header; it is **not yet connected** to [`../product-service-backend`](../product-service-backend).

## What it teaches
- The Angular CLI project layout for a standalone app (no `NgModule`): `main.ts` → `app.config.ts` → `App`
- Building a component (`header/`) and composing it into the root template with its selector `<app-header>`
- Signals for component state (`title = signal(...)` in `app.ts`)
- Component tests with Angular `TestBed`, run by Vitest

## Run it
Prerequisites: Node 20+ (tested with Node 22) and npm.
```bash
npm ci                         # install exact versions from package-lock.json
npx ng serve                   # http://localhost:4200
npx ng test --watch=false      # 3 tests (App + Header), all pass
npx ng build                   # production build into dist/
```

## Read the code in this order
1. `src/main.ts`: bootstraps `App` with `appConfig`.
2. `src/app/app.config.ts`: providers (router, global error listeners).
3. `src/app/app.ts` and `app.html`: the root component, which renders `<app-header>`.
4. `src/app/header/header.ts` and `header.html`: the navigation markup.
5. `src/app/app.spec.ts`: how a component is tested.

## Revision notes
- A component template is a **fragment**. Never put `<!DOCTYPE>`, `<html>` or `<body>` inside it; only `src/index.html` has those.
- Standalone components list their dependencies in `imports: [...]` (here `Header`), with no `NgModule`.
- A test that checks text (`'Hello, productinventory'`) breaks when the template changes. Test for structure or behaviour instead.
- Next steps to call the backend: add `provideHttpClient()` to `app.config.ts`, create a `ProductService` that uses `HttpClient`, and enable CORS on the backend.
- `app.routes.ts` is empty, so the "Home" and "Create Product" menu items don't link anywhere yet.

## Status
🚧 This is the UI shell only. There is no routing, no HTTP service and no product pages yet.
✅ Builds, and the tests pass.
