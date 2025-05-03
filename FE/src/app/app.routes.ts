import { Routes } from '@angular/router';
import { LandingComponent } from './pages/landing/landing.component';
import { SignupComponent }  from './pages/signup/signup.component';
import { ShellComponent }    from './shell.component';
import {WelcomeComponent} from './pages/welcome/welcome.component';
import {FriendsComponent} from './pages/friends/friends.component';
import {FavoritesComponent} from './pages/favorites/favorites.component';
import {ChooseTrailComponent} from './pages/choose-trail/choose-trail.component';
import {FriendFormComponent} from './pages/friend-form/friend-form.component';

export const routes: Routes = [
  { path: '', component: LandingComponent, pathMatch: 'full' },
  { path: 'signup', component: SignupComponent },

  {
    path: '',
    component: ShellComponent,
    children: [
      { path: 'welcome',    component: WelcomeComponent },
      // { path: 'search',     component: SearchComponent },
      { path: 'session',    component: ChooseTrailComponent },
      { path: 'friends',    component: FriendsComponent },
      { path: 'friend-form',component: FriendFormComponent },
      { path: 'favorites', component: FavoritesComponent },
      { path: '', redirectTo: 'welcome', pathMatch: 'full' }
    ]
  },

  { path: '**', redirectTo: '' }
];



// import { Routes } from '@angular/router';
// import {WelcomeComponent} from './pages/welcome/welcome.component';
// import {SignupComponent} from './pages/signup/signup.component';
// import {FriendsComponent} from './pages/friends/friends.component';
// import {FavouritesComponent} from './pages/favourites/favourites.component';
// import {ChooseTrailComponent} from './pages/choose-trail/choose-trail.component';
// import {FriendFormComponent} from './pages/friend-form/friend-form.component';
//
// export const routes: Routes = [
//   { path: '', pathMatch: 'full', redirectTo: '/landing' },
//   { path: 'signup', component: SignupComponent },
//   { path: 'welcome', component: WelcomeComponent },
//   { path: 'friends', component: FriendsComponent },
//   { path: 'session', component: ChooseTrailComponent },
//   { path: 'friend-form', component: FriendFormComponent },
//   { path: 'favourites', component: FavouritesComponent }
// ];
