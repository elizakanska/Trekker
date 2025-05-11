import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {SessionService} from '../../services/session.service';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NzInputModule} from 'ng-zorro-antd/input';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-choose-trail',
  standalone: true,
  imports: [CommonModule, NzButtonComponent, NzInputModule, FormsModule],
  templateUrl: './choose-trail.component.html',
  styleUrls: ['./choose-trail.component.scss']
})
export class ChooseTrailComponent {
  inviteCode = '';

  constructor(private sessionSvc: SessionService, private router: Router) {
  }

  create() {
    this.sessionSvc.createSession().subscribe(sess => {
      this.router.navigate(['/filters', sess.user1Id, sess.user2Id]);
    });
  }

  join() {
    this.sessionSvc.joinSession(this.inviteCode).subscribe(sess => {
      this.router.navigate(['/filters', sess.user1Id, sess.user2Id]);
    });
  }
}
