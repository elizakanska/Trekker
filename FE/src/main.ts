import { bootstrapApplication } from '@angular/platform-browser';
import {provideHttpClient, withFetch} from '@angular/common/http';
import { provideRouter }           from '@angular/router';
import { provideNoopAnimations }   from '@angular/platform-browser/animations';
import { AppComponent }            from './app/app.component';
import { routes }                  from './app/app.routes';

bootstrapApplication(AppComponent, {
  providers: [
    provideNoopAnimations(),
    provideHttpClient(withFetch()),
    provideRouter(routes),
  ]
})
  .catch(err => console.error(err));
