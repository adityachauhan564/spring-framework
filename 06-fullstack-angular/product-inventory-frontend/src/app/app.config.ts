import { provideHttpClient, withFetch } from '@angular/common/http';
import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';

import { routes } from './app.routes';

// App-wide providers (services the whole app can use) of a standalone app.
// This is the job an AppModule did before standalone components existed.
export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes, withComponentInputBinding()),   // route params (:id) are passed into component inputs
    provideHttpClient(withFetch()),                       // makes HttpClient available to inject; it uses the browser's fetch()
  ],
};
