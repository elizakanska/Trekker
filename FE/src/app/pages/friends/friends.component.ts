import { Component, effect, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Friend } from '../../models/friend.model';
import { FriendService } from '../../services/friend.service';
import { AuthService } from '../../services/auth.service';
import { RouterLink } from '@angular/router';
import { NzButtonComponent } from 'ng-zorro-antd/button';
import { NzIconDirective } from 'ng-zorro-antd/icon';
import { NzWaveDirective } from 'ng-zorro-antd/core/wave';
import {User} from '../../models/user.model';

@Component({
  selector: 'app-friends',
  standalone: true,
  imports: [CommonModule, RouterLink, NzButtonComponent, NzIconDirective, NzWaveDirective],
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss'],
})
export class FriendsComponent {
  friends = signal<Friend[]>([]);
  selectedFriend = signal<User | null>(null);
  isDeleting = signal(false);

  constructor(private friendSvc: FriendService, private auth: AuthService) {
    effect(() => {
      const uid = this.auth.getCurrentUserId();
      this.friendSvc.getFriendsByUser(uid).subscribe(data => {
        this.friends.set(data || []);
      });
    });
  }

  showDetails(friend: User) {
    this.selectedFriend.set(friend);
  }

  closeDetails() {
    this.selectedFriend.set(null);
  }

  deleteFriend(friendId: number) {
    this.isDeleting.set(true);
    const uid = this.auth.getCurrentUserId();
    const friend = { user1Id: uid, friendId } as Friend;
    this.friendSvc.deleteFriend(friend).subscribe(() => {
      const remaining = this.friends().filter(f => f.friendId !== friendId);
      this.friends.set(remaining);
      this.isDeleting.set(false);
      this.closeDetails();
    });
  }
}
