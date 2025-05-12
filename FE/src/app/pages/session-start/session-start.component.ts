import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {SessionService} from '../../services/session.service';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NzInputModule} from 'ng-zorro-antd/input';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-session-start',
  standalone: true,
  imports: [CommonModule, NzButtonComponent, NzInputModule, FormsModule],
  templateUrl: './session-start.component.html',
  styleUrls: ['./session-start.component.scss']
})
export class SessionStartComponent {
  inviteCode = '';

  constructor(private sessionSvc: SessionService, private router: Router) {
  }

  create() {
    this.sessionSvc.createSession().subscribe(sess => {
      const code = sess.inviteCode;
      this.router.navigate(['/session', sess.user1Id, 'filters'], {queryParams: {code}});
    });
  }

  join() {
    this.sessionSvc.joinSession(this.inviteCode).subscribe(sess => {
      const code = sess.inviteCode;
      this.router.navigate(['/session', sess.user1Id, 'filters'], {queryParams: {code}});
    });
  }
}
