import { Routes } from '@angular/router';
import { SignupComponent }     from './pages/signup/signup.component';
import { LoginComponent }      from './pages/login/login.component';
import { SetupComponent }      from './pages/setup/setup.component';
import { WelcomeComponent }    from './pages/welcome/welcome.component';
import { FavoritesComponent } from './pages/favorites/favorites.component';
import { FriendsComponent }    from './pages/friends/friends.component';
import { FriendFormComponent } from './pages/friend-form/friend-form.component';
import { ChooseTrailComponent } from './pages/choose-trail/choose-trail.component';
import {LandingComponent} from './pages/landing/landing.component';
import {ShellComponent} from './shell.component';

export const routes: Routes = [
  { path: '', component: LandingComponent, pathMatch: 'full' },
  { path: 'landing',  component: LandingComponent },
  { path: 'signup',   component: SignupComponent },
  { path: 'login',    component: LoginComponent },
  { path: 'setup',    component: SetupComponent },

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
