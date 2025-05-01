import { Component }        from '@angular/core';
import { CommonModule }     from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [ CommonModule, RouterModule ],
  templateUrl: './landing.component.html',
  styleUrls:   ['./landing.component.scss']
})
export class LandingComponent {
  constructor(private router: Router) {}

  /** go to login/signup page */
  login() {
    this.router.navigate(['/signup']);
  }

  /** go to signup/join page */
  join() {
    this.router.navigate(['/signup']);
  }
}



// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-landing',
//   imports: [],
//   templateUrl: './landing.component.html',
//   styleUrl: './landing.component.scss'
// })
// export class LandingComponent {
//
// }
