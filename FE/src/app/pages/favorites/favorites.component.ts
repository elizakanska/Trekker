import { ChangeDetectionStrategy, Component, DestroyRef, signal } from '@angular/core';
import { FavoriteService } from '../../services/favorite.service';
import { TrailService } from '../../services/trail.service';
import { Trail } from '../../models/trail.model';
import { Favorite } from '../../models/favorite.model';
import { BehaviorSubject, combineLatest, finalize, switchMap, map } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  imports: [
    NzButtonModule,
    NzIconModule,
    NzTableModule,
    CommonModule,
    NzModalModule,
    NzDescriptionsModule
  ],
  selector: 'app-favorites',
  standalone: true,
  styleUrls: ['./favorites.component.scss'],
  templateUrl: './favorites.component.html'
})
export class FavoritesComponent {
  favorites = signal<Favorite[]>([]);
  trails = signal<Trail[]>([]);
  users = signal<User[]>([]);
  userId = signal<number | null>(null);
  isLoading = signal<boolean>(false);
  isSubmitting = signal<boolean>(false);
  // Add to existing signals
  showTrailDetails = signal<boolean>(false);
  selectedTrail = signal<Trail | null>(null);

  private loadTriggerSubj = new BehaviorSubject<void>(undefined);

  constructor(
    private favoriteService: FavoriteService,
    private trailService: TrailService,
    private userService: UserService,
    private message: NzMessageService,
    private destroyRef: DestroyRef
  ) {
    this.setupDataStreams();
    this.loadUserData();
  }

  private setupDataStreams(): void {
    combineLatest([
      this.loadTriggerSubj,
      this.loadTriggerSubj.pipe(map(() => this.userId()))
    ]).pipe(
      switchMap(([_, userId]) => {
        if (!userId) return [];
        this.isLoading.set(true);
        return this.favoriteService.getByUser(userId).pipe(
          switchMap(favorites => {
            this.favorites.set(favorites);
            const trailIds = favorites.map(f => f.trailId);
            return trailIds.length > 0
              ? this.trailService.getTrailsByIds(trailIds)
              : [];
          }),
          finalize(() => this.isLoading.set(false))
        );
      }),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe({
      next: trails => this.trails.set(trails),
      error: err => {
        console.error('Failed to load data', err);
        this.message.error('Failed to load favorites');
      }
    });

    this.userService.getAll().pipe(
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(users => this.users.set(users));
  }

  private loadUserData(): void {
    const userData = localStorage.getItem('user');
    if (userData) this.userId.set(JSON.parse(userData).id);
}

  addFavorite(trailId: number): void {
    if (!this.userId() || this.isSubmitting()) return;
    this.isSubmitting.set(true);
    this.isLoading.set(true);

    this.favoriteService.add({
      userId: this.userId()!,
      trailId,
      id: 0
    }).pipe(
      takeUntilDestroyed(this.destroyRef),
      finalize(() => {
        this.isSubmitting.set(false);
        this.isLoading.set(false);
      })
    ).subscribe({
      next: () => {
        this.message.success('Trail added to favorites');
        this.loadTriggerSubj.next();
      },
      error: err => this.message.error('Failed to add favorite')
    });
  }

  removeFavorite(trailId: number): void {
    if (!this.userId()) return;

    const favoriteToRemove = this.favorites().find(
      f => f.userId === this.userId() && f.trailId === trailId
    );

    if (!favoriteToRemove) {
      this.message.error('Favorite not found');
      return;
    }

    this.favoriteService.delete(favoriteToRemove).pipe(
      takeUntilDestroyed(this.destroyRef)
    ).subscribe({
      next: () => {
        this.message.success('Trail removed from favorites');
        this.loadTriggerSubj.next();
      },
      error: err => this.message.error('Failed to remove favorite')
    });
  }

  showDetails(trail: Trail): void {
    this.selectedTrail.set(trail);
    this.showTrailDetails.set(true);
  }

  closeTrailDetails(): void {
    this.showTrailDetails.set(false);
    this.selectedTrail.set(null);
  }
}
