import {Component, OnInit, OnDestroy, signal, computed} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NzSliderModule} from 'ng-zorro-antd/slider';
import {interval, Subject, takeUntil, switchMap, startWith} from 'rxjs';

import {SessionService} from '../../services/session.service';
import {TrailService} from '../../services/trail.service';
import {Session} from '../../models/session.model';
import {Trail} from '../../models/trail.model';

@Component({
  selector: 'app-session-filters',
  standalone: true,
  imports: [CommonModule, FormsModule, NzSliderModule],
  templateUrl: './session-filters.component.html',
  styleUrls: ['./session-filters.component.scss']
})
export class SessionFiltersComponent implements OnInit, OnDestroy {
  user1Id!: number;
  inviteCode = '';
  private destroy$ = new Subject<void>();

  // all trails
  trails = signal<Trail[]>([]);

  // computed lists
  allDifficulties = computed(() =>
    Array.from(new Set(this.trails().map(t => t.difficulty)))
  );
  allBiomes = computed(() =>
    Array.from(
      new Set(
        this.trails()
          .flatMap(t => t.biome.split(',').map(b => b.trim().toLowerCase()))
      )
    ).map(b => b.charAt(0).toUpperCase() + b.slice(1))
  );

  // absolute length range from trails.length
  absRange = signal<[number, number]>([0, 0]);
  // user-selected length range
  selRange = signal<[number, number]>([0, 0]);

  // selected filters
  selectedDifficulties = signal<string[]>([]);
  selectedBiomes = signal<string[]>([]);

  // session & users
  session = signal<Session | null>(null);
  waitingUsers = signal<string[]>([]);

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private sessionService: SessionService,
    private trailService: TrailService
  ) {
  }

  ngOnInit(): void {
    this.user1Id = +this.route.snapshot.paramMap.get('user1Id')!;
    this.inviteCode = this.route.snapshot.queryParamMap.get('code') || '';

    // load all trails once
    this.trailService.getAll()
      .pipe(takeUntil(this.destroy$))
      .subscribe(trs => {
        this.trails.set(trs);
        const lengths = trs.map(t => t.length);
        const minL = Math.min(...lengths);
        const maxL = Math.max(...lengths);
        this.absRange.set([minL, maxL]);
        this.selRange.set([minL, maxL]);
        this.selectedDifficulties.set(this.allDifficulties());
        this.selectedBiomes.set(this.allBiomes());
      });

    // poll session
    interval(3000)
      .pipe(startWith(0), switchMap(() => this.sessionService.getCurrent(this.user1Id)), takeUntil(this.destroy$))
      .subscribe(s => this.session.set(s));

    // poll users
    interval(3000)
      .pipe(startWith(0), switchMap(() => this.sessionService.getUsers(this.user1Id)), takeUntil(this.destroy$))
      .subscribe(u => this.waitingUsers.set(u));
  }

  updateSelRange([min, max]: [number, number]) {
    const [absMin, absMax] = this.absRange();
    if (max > min && min >= absMin && max <= absMax) {
      this.selRange.set([+min.toFixed(1), +max.toFixed(1)]);
    }
  }

  toggleDifficulty(d: string) {
    const list = [...this.selectedDifficulties()];
    const idx = list.indexOf(d);
    if (idx > -1) list.splice(idx, 1);
    else list.push(d);
    this.selectedDifficulties.set(list);
  }

  toggleBiome(b: string) {
    const list = [...this.selectedBiomes()];
    const idx = list.indexOf(b);
    if (idx > -1) list.splice(idx, 1);
    else list.push(b);
    this.selectedBiomes.set(list);
  }

  saveFilters() {
    const [min, max] = this.selRange();
    this.sessionService
      .setFilters(
        this.user1Id,
        min,
        max,
        this.selectedDifficulties().join(','),
        this.selectedBiomes().join(',')
      )
      .pipe(takeUntil(this.destroy$))
      .subscribe();
  }

  proceed() {
    const sess = this.session();
    if (sess?.user2Id) {
      this.router.navigate(
        ['/session', this.user1Id, 'choose'],
        {queryParams: {code: this.inviteCode}}
      );
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
