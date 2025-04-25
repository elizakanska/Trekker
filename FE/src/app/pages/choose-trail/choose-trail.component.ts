import { Component, OnInit } from '@angular/core';
import { Router }             from '@angular/router';

interface Trail {
  id:   number;
  name: string;
}

@Component({
  selector: 'app-choose-trail',
  templateUrl: './choose-trail.component.html',
  styleUrls: ['./choose-trail.component.scss']
})
export class ChooseTrailComponent implements OnInit {
  trails: Trail[] = [];
  index = 0;

  constructor(private router: Router) {}

  ngOnInit() {
    // Replace this with real data fetch
    this.trails = [
      { id: 1, name: 'Takas nosaukums 1' },
      { id: 2, name: 'Takas nosaukums 2' },
      { id: 3, name: 'Takas nosaukums 3' },
      { id: 4, name: 'Takas nosaukums 4' },
      // …etc
    ];
  }

  get pair(): Trail[] {
    // Grab two at a time, wrapping if needed
    const first  = this.trails[this.index % this.trails.length];
    const second = this.trails[(this.index + 1) % this.trails.length];
    return [first, second];
  }

  choose(trail: Trail) {
    console.log('User chose trail', trail);
    // TODO: record in session/db
    // Move to next pair
    this.index = (this.index + 2) % this.trails.length;
  }

  logout() {
    // TODO: your authService.logout();
    this.router.navigate(['/login']);
  }
}
