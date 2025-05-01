import { Component }        from '@angular/core';
import {Router, RouterModule} from '@angular/router';
import { NzLayoutModule }   from 'ng-zorro-antd/layout';
import { NzMenuModule }     from 'ng-zorro-antd/menu';
import { NzIconModule }     from 'ng-zorro-antd/icon';

@Component({
  selector: 'app-shell',
  standalone: true,
  imports: [
    RouterModule,
    NzLayoutModule,
    NzMenuModule,
    NzIconModule
  ],
  templateUrl: './shell.component.html',
  styleUrls:   ['./shell.component.scss']
})
export class ShellComponent {
  isCollapsed = false;

  constructor(private router: Router) {}

  logout() {
    // TODO: clear any auth tokens you need here...
    this.router.navigate(['/']);
  }
}



// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-shell',
//   imports: [],
//   templateUrl: './shell.component.html',
//   styleUrl: './shell.component.scss'
// })
// export class ShellComponent {
//
// }
