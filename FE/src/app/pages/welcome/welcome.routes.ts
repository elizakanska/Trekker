import { Routes } from '@angular/router';
import { WelcomeComponent } from './welcome.component';
import {SignupComponent} from '../signup/signup.component';
import {FriendsComponent} from '../friends/friends.component';
import {FavouritesComponent} from '../favourites/favourites.component';
import {SessionStartComponent} from '../session-start/session-start.component';

export const WELCOME_ROUTES: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'friends', component: FriendsComponent },
  { path: 'favourites', component: FavouritesComponent },
  {path: 'choose-trail', component: SessionStartComponent}
];
