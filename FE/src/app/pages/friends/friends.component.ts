import { Component, effect, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Friend } from '../../models/friend.model';
import { FriendService } from '../../services/friend.service';
import { AuthService } from '../../services/auth.service';
import { RouterLink } from '@angular/router';
import {NzButtonComponent} from 'ng-zorro-antd/button';

@Component({
  selector: 'app-friends',
  standalone: true,
  imports: [CommonModule, RouterLink, NzButtonComponent],
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss'],
})
export class FriendsComponent {
  friends = signal<Friend[]>([]);

  constructor(private friendSvc: FriendService, private auth: AuthService) {
    effect(() => {
      const uid = this.auth.getCurrentUserId();
      this.friendSvc.getFriendsByUser(uid).subscribe((data) => {
        this.friends.set(data || []);
      });
    });
  }
}
