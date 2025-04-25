import { Component }      from '@angular/core';
import { CommonModule }   from '@angular/common';

interface Trail {
  name:  string;
  desc:  string;
  image: string;
}

@Component({
  selector: 'app-favourites',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './favourites.component.html',
  styleUrls:   ['./favourites.component.scss']
})
export class FavouritesComponent {
  // static data
  trails: Trail[] = [
    {
      name: 'Dabas taka Nr.1',
      desc: 'Pieejamais dabas takas apraksts.',
      image: 'https://via.placeholder.com/120x120?text=Img'
    },
    {
      name: 'Dabas taka Nr.2',
      desc: 'Pieejamais dabas takas apraksts.',
      image: 'https://via.placeholder.com/120x120?text=Img'
    },
    {
      name: 'Dabas taka Nr.3',
      desc: 'Pieejamais dabas takas apraksts.',
      image: 'https://via.placeholder.com/120x120?text=Img'
    }
  ];
}



// src/app/pages/favourites/favourites.component.ts
// import { Component, OnInit }      from '@angular/core';
// import { FavouritesService, Trail } from './favourites.service';
// import { Observable }             from 'rxjs';
//
// @Component({
//   selector: 'app-favourites',
//   templateUrl: './favourites.component.html',
//   styleUrls: ['./favourites.component.scss']
// })
// export class FavouritesComponent implements OnInit {
//   favourites$: Observable<Trail[]>;   // stream of trails
//
//   constructor(private favSvc: FavouritesService) {}
//
//   ngOnInit() {
//     // subscribe only once; the | async pipe in template will keep it fresh
//     this.favourites$ = this.favSvc.getAll();
//   }
//
//   onRemove(trail: Trail) {
//     this.favSvc.remove(trail.id).subscribe(() => {
//       // re-fetch or optimistically remove from stream
//       this.favourites$ = this.favSvc.getAll();
//     });
//   }
//
//   // If you want to edit, you can open a dialog, then:
//   onUpdate(trail: Trail) {
//     this.favSvc.update(trail).subscribe(() => {
//       this.favourites$ = this.favSvc.getAll();
//     });
//   }
// }


// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-favourites',
//   imports: [],
//   templateUrl: './favourites.component.html',
//   styleUrl: './favourites.component.scss'
// })
// export class FavouritesComponent {
//
// }
