import {Component, signal} from '@angular/core';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {NzButtonModule} from 'ng-zorro-antd/button';

export interface Trail {
  id: number;
  name: string;
  length: number;
  image: string;
}

@Component({
  selector: 'app-session-result',
  standalone: true,
  imports: [CommonModule, NzButtonModule],
  templateUrl: './session-result.component.html',
  styleUrls: ['./session-result.component.scss']
})
export class SessionResultComponent {
  resultTrails = signal<Trail[]>([
    {id: 5, name: 'Amatas ģeotaka. Zvārtes iezis - Veclauču tilts', length: 2.4, image: 'assets/trails/5.jpg'},
    {id: 7, name: 'Augstrozes pilskalna taka', length: 0.5, image: 'assets/trails/7.jpg'},
    {id: 9, name: 'Bernātu dabas parka taka', length: 1.5, image: 'assets/trails/9.jpg'},
    {id: 10, name: 'Cenas tīreļa laipu taka', length: 6.0, image: 'assets/trails/10.jpg'},
  ]);

  // track favorites
  favorites = signal<Trail[]>([]);

  constructor(private router: Router) {
  }

  addFavorite(trail: Trail): void {
    const favs = [...this.favorites()];
    if (!favs.find(t => t.id === trail.id)) {
      favs.push(trail);
      this.favorites.set(favs);
    }
  }

  retry(): void {
    this.router.navigate(['/session/choose-dummy']);
  }
}
