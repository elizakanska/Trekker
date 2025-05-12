import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SessionService} from '../../services/session.service';
import {TrailService} from '../../services/trail.service';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-session-filters',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './session-filters.component.html',
  styleUrls: ['./session-filters.component.scss']
})
export class SessionFiltersComponent implements OnInit {
  user1Id!: number;
  user2Id: number | null = null;
  inviteCode = '';
  waitingUsers: string[] = [];

  minLength = 0;
  maxLength = 0;
  selectedMin = 0;
  selectedMax = 0;

  allDifficulties: string[] = [];
  selectedDifficulties: string[] = [];

  allBiomes: string[] = [];
  selectedBiomes: string[] = [];

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

    this.trailService.getAll().subscribe(trails => {
      const lengths = trails.map(t => t.length);
      this.minLength = Math.min(...lengths);
      this.maxLength = Math.max(...lengths);
      this.selectedMin = this.minLength;
      this.selectedMax = this.maxLength;

      this.allDifficulties = [...new Set(trails.map(t => t.difficulty))];
      this.selectedDifficulties = [...this.allDifficulties];

      const flatBiomes = trails.flatMap(t => t.biome.split(',').map(b => b.trim().toLowerCase()));
      this.allBiomes = Array.from(new Set(flatBiomes)).map(b => b.charAt(0).toUpperCase() + b.slice(1));
      this.selectedBiomes = [...this.allBiomes];
    });

    setInterval(() => {
      this.sessionService.getSessionUsers(this.user1Id).subscribe(users => {
        this.waitingUsers = users.map(u => u.username);
        const other = users.find(u => u.id !== this.user1Id);
        this.user2Id = other ? other.id : null;
      });
    }, 3000);
  }

  toggleDifficulty(d: string) {
    const i = this.selectedDifficulties.indexOf(d);
    i >= 0 ? this.selectedDifficulties.splice(i, 1) : this.selectedDifficulties.push(d);
  }

  toggleBiome(b: string) {
    const i = this.selectedBiomes.indexOf(b);
    i >= 0 ? this.selectedBiomes.splice(i, 1) : this.selectedBiomes.push(b);
  }

  saveFilters(): void {
    this.sessionService.setFilters(this.user1Id, {
      min: this.selectedMin,
      max: this.selectedMax,
      difficulties: this.selectedDifficulties,
      biomes: this.selectedBiomes
    }).subscribe();
  }

  proceed(): void {
    if (this.user2Id) {
      this.router.navigate(['/session', this.user1Id, this.user2Id, 'choosing']);
    }
  }
}
