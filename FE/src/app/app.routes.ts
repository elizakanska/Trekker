// import { Routes } from '@angular/router';
//
// export const routes: Routes = [];
import { Routes } from '@angular/router';
import {SignupComponent} from './pages/signup/signup.component';
import { WelcomeComponent }  from './pages/welcome/welcome.component';
import {FavouritesComponent} from './pages/favourites/favourites.component';
import {FriendsComponent} from './pages/friends/friends.component';
import {FriendFormComponent} from './pages/friend-form/friend-form.component';
import {ChooseTrailComponent} from './pages/choose-trail/choose-trail.component';


export const routes: Routes = [
  // { path: '', pathMatch: 'full', redirectTo: '/signup' },
  { path: 'signup', component: SignupComponent},
  { path: '', pathMatch: 'full', redirectTo: '/welcome' },
  { path: 'welcome',  component: WelcomeComponent },
  // { path: '', pathMatch: 'full', redirectTo: '/favourites' },
  { path: 'favourites',  component: FavouritesComponent },
  // { path: '', pathMatch: 'full', redirectTo: '/friends' },
  { path: 'friends', component: FriendsComponent },
  // { path: '', pathMatch: 'full', redirectTo: '/friend-form' },
  { path: 'friends/new', component: FriendFormComponent },
  // { path: 'friends/:id', component: FriendDetailComponent },
  // { path: '', pathMatch: 'full', redirectTo: '/choose-trail' },
  { path: 'session',  component: ChooseTrailComponent },
];

// export const routes: Routes = [
//   { path: '',       pathMatch: 'full', redirectTo: '/welcome' },
//   { path: 'welcome',  component: WelcomeComponent },
//   // … your existing signup & other routes …
// ];
