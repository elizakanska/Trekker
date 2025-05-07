import { Component } from '@angular/core';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import { AuthService } from './services/auth.service';
import {
  NzContentComponent,
  NzFooterComponent,
  NzHeaderComponent,
  NzLayoutComponent,
  NzSiderComponent
} from 'ng-zorro-antd/layout';
import {NzMenuDirective, NzMenuItemComponent} from 'ng-zorro-antd/menu';
import {NzIconDirective} from 'ng-zorro-antd/icon';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-shell',
  templateUrl: './shell.component.html',
  imports: [
    NzLayoutComponent,
    NzSiderComponent,
    NzMenuDirective,
    NzMenuItemComponent,
    RouterLink,
    NzIconDirective,
    NzHeaderComponent,
    NzButtonComponent,
    NzContentComponent,
    RouterOutlet,
    NzFooterComponent,
    NgOptimizedImage
  ],
  styleUrls: ['./shell.component.scss']
})
export class ShellComponent {
  isCollapsed = false;

  constructor(private router: Router, private authService: AuthService) {}

  logout() {
    this.authService.logout();
    this.router.navigate(['/landing']);
  }

  get isAuthenticated() {
    return this.authService.isLoggedIn();
  }
}
