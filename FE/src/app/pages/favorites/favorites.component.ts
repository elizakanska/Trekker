import { Component, effect, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { Favorite } from '../../models/favorite.model';
import { Trail } from '../../models/trail.model';
import { FavoriteService } from '../../services/favorite.service';
import { AuthService } from '../../services/auth.service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-favorites',
  standalone: true,
  imports: [CommonModule, NzButtonModule, NzIconModule, RouterLink],
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.scss']
})
export class FavoritesComponent {
  favorites = signal<Favorite[]>([]);
  selectedTrail = signal<Trail | null>(null);
  isSubmitting = signal(false);

  constructor(
    private favSvc: FavoriteService,
    private auth: AuthService
  ) {
    effect(() => {
      const uid = this.auth.getCurrentUserId();
      this.favSvc.getFavoritesByUser(uid).subscribe(favs => {
        this.favorites.set(favs || []);
      });
    });
  }

  showDetails(trail: Trail) {
    this.selectedTrail.set(trail);
  }

  closeTrailDetails() {
    this.selectedTrail.set(null);
  }

  removeFavorite(trailId: number) {
    this.isSubmitting.set(true);
    const uid = this.auth.getCurrentUserId();
    this.favSvc.removeFavorite(uid, trailId).subscribe(() => {
      const remaining = this.favorites().filter(f => f.trailId !== trailId);
      this.favorites.set(remaining);
      this.isSubmitting.set(false);
      this.closeTrailDetails();
    });
  }
}
