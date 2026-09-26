# 06 — Full-Stack: Angular + Spring Boot

A two-part product-inventory app: a Spring Boot REST API and an Angular frontend. They are separate builds that run side by side (API on `:8080`, UI on `:4200`). Right now the two halves are **not wired together**. The frontend is still the UI shell, and the backend has no CORS configuration.

| # | Project | Topics | Status |
| --- | --- | --- | --- |
| 1 | [product-service-backend](./product-service-backend) | Spring Boot 4, Spring Data JPA (`CrudRepository`), MySQL, `ResponseEntity` status codes | ✅ builds; needs MySQL |
| 2 | [product-inventory-frontend](./product-inventory-frontend) | Angular 21 standalone components, signals, TestBed + Vitest | 🚧 shell only; tests pass |

## Suggested study order
1. **product-service-backend**: build and exercise the API with Postman/curl first, and look at the real JSON it returns (note the `pname` naming gotcha).
2. **product-inventory-frontend**: learn the component structure, then add `HttpClient` + a `ProductService` that calls the API.
3. To connect them: add CORS on the backend, add `provideHttpClient()` in the frontend, then build the list and create pages.

## Quick revision checklist
- [ ] Which HTTP status should each CRUD operation return (200 / 201 / 204 / 404)?
- [ ] Why does the browser block `localhost:4200` → `localhost:8080` without CORS?
- [ ] How does a standalone Angular component declare what it uses (`imports`)?
- [ ] Where do app-wide providers go in a standalone app (`app.config.ts`)?
- [ ] How do Java getter names become JSON keys in Jackson?
- [ ] Why run `npm ci` rather than `npm install` on a fresh clone?
