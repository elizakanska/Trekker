import { Component }      from '@angular/core';
import { CommonModule }   from '@angular/common';
import { Router, RouterModule } from '@angular/router';

interface TrailOption {
  title: string;
  css:   string;
  route: string;
}

@Component({
  selector: 'app-choose-trail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './choose-trail.component.html',
  styleUrls:   ['./choose-trail.component.scss']
})
export class ChooseTrailComponent {
  options: TrailOption[] = [
    {
      title: 'Takas nosaukums 1',
      css:   'option-primary',
      route: '/trail/1'
    },
    {
      title: 'Takas nosaukums 2',
      css:   'option-accent',
      route: '/trail/2'
    }
  ];

  constructor(private router: Router) {}

  select(opt: TrailOption) {
    this.router.navigate([opt.route]);
  }
}

