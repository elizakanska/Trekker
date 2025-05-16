import {Component, signal} from '@angular/core';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NzInputModule} from 'ng-zorro-antd/input';

import {SessionService} from '../../services/session.service';
import {Session} from '../../models/session.model';

@Component({
  selector: 'app-session-start',
  standalone: true,
  imports: [CommonModule, FormsModule, NzInputModule, NzButtonComponent],
  templateUrl: './session-start.component.html',
  styleUrls: ['./session-start.component.scss']
})
export class SessionStartComponent {
  inviteCode = '';
  session = signal<Session | null>(null);

  constructor(
    private readonly sessionSvc: SessionService,
    private readonly router: Router
  ) {
  }

  create(): void {
    // @ts-ignore
    this.sessionSvc.createSession()
      .subscribe((sess: Session) => {
        this.session.set(sess);
        this.router.navigate(
          ['/session', sess.user1Id, 'filters'],
          {queryParams: {code: sess.inviteCode}}
        );
      });
  }

  join(): void {
    // @ts-ignore
    this.sessionSvc.joinSession(this.inviteCode)
      .subscribe((sess: Session) => {
        this.session.set(sess);
        this.router.navigate(
          ['/session', sess.user1Id, 'filters'],
          {queryParams: {code: sess.inviteCode}}
        );
      });
  }
}
