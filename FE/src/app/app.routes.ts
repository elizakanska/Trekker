import { Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { SetupComponent } from './pages/setup/setup.component';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { FavoritesComponent } from './pages/favorites/favorites.component';
import { FriendsComponent } from './pages/friends/friends.component';
import { FriendFormComponent } from './pages/friend-form/friend-form.component';
import {SessionStartComponent} from './pages/session-start/session-start.component';
import {SessionFiltersComponent} from './pages/session-filters/session-filters.component';
import {SessionChoosingComponent} from './pages/session-choosing/session-choosing.component';
import {SessionResultComponent} from './pages/session-result/session-result.component';
import { LandingComponent } from './pages/landing/landing.component';
import { ShellComponent } from './shell.component';
import { AuthGuard } from './auth.guard';

export const routes: Routes = [
  { path: '', component: LandingComponent, pathMatch: 'full' },
  { path: 'landing', component: LandingComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'setup', component: SetupComponent },
  {
    path: '',
    component: ShellComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'welcome', component: WelcomeComponent },
      {path: 'session', component: SessionStartComponent},
      {path: 'session/:user1Id/filters', component: SessionFiltersComponent},
      {path: 'session/:user1Id/:user2Id/choosing', component: SessionChoosingComponent},
      {path: 'session/result', component: SessionResultComponent},
      { path: 'friends', component: FriendsComponent },
      { path: 'friend-form', component: FriendFormComponent },
      { path: 'favorites', component: FavoritesComponent },
      { path: '', redirectTo: 'welcome', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: '' }
];
