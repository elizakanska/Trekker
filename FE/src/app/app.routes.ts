import { Routes } from '@angular/router';
import {WelcomeComponent} from './pages/welcome/welcome.component';
import {SignupComponent} from './pages/signup/signup.component';
import {FriendsComponent} from './pages/friends/friends.component';
import {FavouritesComponent} from './pages/favourites/favourites.component';
import {ChooseTrailComponent} from './pages/choose-trail/choose-trail.component';
import {FriendFormComponent} from './pages/friend-form/friend-form.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/signup' },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'friends', component: FriendsComponent },
  { path: 'session', component: ChooseTrailComponent },
  { path: 'friend-form', component: FriendFormComponent },
  { path: 'favourites', component: FavouritesComponent }
];
