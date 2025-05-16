import {Component, signal, computed} from '@angular/core';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {Trail} from '../../models/trail.model'

@Component({
  selector: 'app-session-choosing',
  standalone: true,
  imports: [CommonModule, NzButtonModule],
  templateUrl: './session-choosing.component.html',
  styleUrls: ['./session-choosing.component.scss']
})
export class SessionChoosingComponent {
  private _trails = signal<Trail[]>([
    {
      id: 1,
      name: 'Aklā purva taka',
      location: 'Daudzeses pagasts, Aizkraukles novads, LV-5111',
      type: 'Gājēju taka',
      biome: 'Purvs',
      difficulty: 'Viegli',
      length: 1.9,
      image: 'assets/trails/1.jpg'
    },
    {
      id: 2,
      name: 'Akmeņu taka',
      location: 'Jaunstašuļi, Mākoņkalna pagasts, Rēzeknes novads, LV-4626',
      type: 'Gājēju taka',
      biome: 'Mežs',
      difficulty: 'Normāli',
      length: 2.3,
      image: 'assets/trails/2.jpg'
    },
    {
      id: 3,
      name: 'Amatas ģeotaka. Melturi - Kārļu zivjaudzētava',
      location: 'Drabešu pagasts, Cēsu novads. Melturi: 57.2218595, 25.2359774. Kārļu zivjaudzētava: 57.2374353, 25.2011978',
      type: 'Gājēju taka',
      biome: 'Mežs, upe',
      difficulty: 'Grūti',
      length: 3.6,
      image: 'assets/trails/3.jpg'
    },
    {
      id: 4,
      name: 'Amatas ģeotaka. Kārļu zivjaudzētava - Zvārtes iezis',
      location: 'Drabešu pagasts, Cēsu novads. Kārļu zivjaudzētava: 57.2374353, 25.2011978 Zvārtes iezis: 57.2459047, 25.1432441',
      type: 'Gājēju taka',
      biome: 'Mežs, upe',
      difficulty: 'Normāli',
      length: 9.2,
      image: 'assets/trails/4.jpg'
    },
    {
      id: 5,
      name: 'Amatas ģeotaka. Zvārtes iezis - Veclauču tilts',
      location: 'Drabešu pagasts, Cēsu novads. Zvārtes iezis: 57.2459047, 25.1432441; Veclauču tilts: 57.2617720, 25.1379117',
      type: 'Gājēju taka',
      biome: 'Upe',
      difficulty: 'Viegli',
      length: 2.4,
      image: 'assets/trails/5.jpg'
    },
    {
      id: 6,
      name: 'Andrupenes purva taka',
      location: 'Skolas iela 5, Andrupene, Andrupenes pagasts, Krāslavas novads',
      type: 'Gājēju taka',
      biome: 'Purvs',
      difficulty: 'Viegli',
      length: 0.8,
      image: 'assets/trails/6.jpg'
    },
    {
      id: 7,
      name: 'Augstrozes pilskalna taka',
      location: 'Umurgas pagasts, Limbažu novads, LV-4004',
      type: 'Gājēju taka',
      biome: 'Tīrums',
      difficulty: 'Grūti',
      length: 0.5,
      image: 'assets/trails/7.jpg'
    },
    {
      id: 8,
      name: 'Barona taka',
      location: 'Vilces pagasts, Jelgavas novads. 56.42091763152077, 23.54215030025643',
      type: 'Gājēju taka',
      biome: 'Mežs',
      difficulty: 'Viegli',
      length: 2.5,
      image: 'assets/trails/8.jpg'
    },
    {
      id: 9,
      name: 'Bernātu dabas parka taka',
      location: 'Bernāti, Nīcas pagasts, Dienvidkurzemes novads, LV-3473',
      type: 'Gājēju taka',
      biome: 'Mežs',
      difficulty: 'Grūti',
      length: 1.5,
      image: 'assets/trails/9.jpg'
    },
    {
      id: 10,
      name: 'Cenas tīreļa laipu taka',
      location: 'Babītes pagasts, Mārupes novads, LV-2107',
      type: 'Gājēju taka',
      biome: 'Purvs',
      difficulty: 'Viegli',
      length: 6.0,
      image: 'assets/trails/10.jpg'
    }
  ]);

  private _idx = signal(0);
  currentTrail = computed(() => this._trails()[this._idx()]);
  currentIndex = computed(() => this._idx());
  totalTrails = computed(() => this._trails().length);

  constructor(private router: Router) {
  }

  onLike(liked: boolean): void {
    console.log(`Trail ${this.currentTrail().id} liked?`, liked);
    this.advance();
  }

  onNext(): void {
    this.onLike(false);
  }

  private advance(): void {
    const next = this._idx() + 1;
    if (next < this.totalTrails()) {
      this._idx.set(next);
    } else {
      this.router.navigate(['/session/result']);
    }
  }
}
