// src/app/pages/welcome/welcome.component.ts
import { Component }  from '@angular/core';
import { Router }     from '@angular/router';

interface NavLink {
  label: string;
  path:  string;
  icon?: string;    // if you want to show an icon
}

interface Card {
  title: string;
  desc:  string;
  path:  string;
  css:   string;    // to pick up card-primary, etc.
}

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent {
  // Sidebar items
  navLinks: NavLink[] = [
    { label: 'Sākums',      path: '/welcome',  icon: 'home' },
    { label: 'Meklē taku',  path: '/search',   icon: 'search' },
    { label: 'Savienojies', path: '/session',  icon: 'rss' },
    { label: 'Draugi',      path: '/friends',  icon: 'smile' },
    { label: 'Mīļākās takas', path: '/favorites', icon: 'list' }
  ];

  // Cards
  cards: Card[] = [
    { title: 'Sāc izvēlēties',   desc: 'Sāciet kopīgu dabas taku izvēli!', path: '/search',    css: 'card-primary' },
    { title: 'Savienojies',      desc: 'Izveidojiet savienojumu, pirms sākt!', path: '/session',   css: 'card-accent'  },
    { title: 'Mīļākās takas',    desc: 'Atsauc atmiņā iecienītākās takas!', path: '/favorites',  css: 'card-success' },
    { title: 'Draugi',           desc: 'Pievieno draugus!', path: '/friends',     css: 'card-dark'    }
  ];

  constructor(private router: Router) {}

  /** navigate to any of your app’s routes */
  navigate(path: string) {
    this.router.navigate([path]);
  }

  /** highlight active sidebar item */
  isActive(path: string): boolean {
    // exact match; tweak options if you want prefix‐matching, etc.
    return this.router.url === path;
  }

  /** called when “Atslēgties” clicked */
  logout() {
    // TODO: call your AuthService.logout()
    // e.g. this.authService.logout();
    // then redirect to login
    this.router.navigate(['/login']);
  }
}



// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-welcome',
//   imports: [],
//   templateUrl: './welcome.component.html',
//   styleUrl: './welcome.component.scss'
// })
// export class WelcomeComponent {
//
// }
// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-welcome',
//   templateUrl: './welcome.component.html',
//   styleUrls: ['./welcome.component.scss']
// })
// export class WelcomeComponent {
//   // hook up methods for logout, navigation, etc. here
// }

