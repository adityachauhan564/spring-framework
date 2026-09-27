import { provideHttpClient, withFetch } from '@angular/common/http';
import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';

import { routes } from './app.routes';

// App-wide providers of a standalone app (the job an AppModule did before standalone components)
export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes, withComponentInputBinding()),   // route params (:id) -> component inputs
    provideHttpClient(withFetch()),                       // makes HttpClient injectable; uses fetch()
  ],
};
