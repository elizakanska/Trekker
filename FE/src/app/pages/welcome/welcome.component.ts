// src/app/pages/welcome/welcome.component.ts

import { Component } from '@angular/core';
import { Router }    from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

// 1) Define the shape of each card
interface Card {
  title:    string;
  subtitle: string;
  route:    string;
  css:      string;
}

@Component({
  selector: 'app-welcome',
  standalone: true,
  imports: [ CommonModule, RouterModule ],
  templateUrl: './welcome.component.html',
  styleUrls:   ['./welcome.component.scss']
})
export class WelcomeComponent {
  // 2) Declare and initialize your cards array
  cards: Card[] = [
    {
      title:    'Sāc izvēlēties',
      subtitle: 'Sāciet kopīgu dabas taku izvēli!',
      route:    '/session',
      css:      'card-primary'
    },
    {
      title:    'Savienojies',
      subtitle: 'Izveidojiet savienojumu pirms sākt!',
      route:    '/friend-form',
      css:      'card-accent'
    },
    {
      title:    'Mīļākās takas',
      subtitle: 'Atsauc atmiņā iecienītākās takas!',
      route:    '/favourites',
      css:      'card-success'
    },
    {
      title:    'Draugi',
      subtitle: 'Pievieno draugus!',
      route:    '/friends',
      css:      'card-dark'
    }
  ];

  constructor(private router: Router) {}

  // 3) Navigate when a card is clicked
  go(card: Card) {
    this.router.navigate([card.route]);
  }
}


// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-welcome',
//   templateUrl: './welcome.component.html',
//   styleUrl: './welcome.component.scss'
// })
// export class WelcomeComponent {
//   constructor() {}
// }
