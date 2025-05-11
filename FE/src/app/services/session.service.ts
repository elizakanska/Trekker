import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { API_URL } from './constants/constants';
import {AuthService} from './auth.service';
import { Observable } from 'rxjs';
import { Session } from '../models/session.model';

@Injectable({ providedIn: 'root' })
export class SessionService {
  private readonly baseUrl = `${API_URL}/sessions`;

  constructor(private http: HttpClient, private auth: AuthService) {
  }

  createSession(): Observable<Session> {
    const token = this.auth.getToken();
    const user1Id = this.auth.getCurrentUserId();
    return this.http.post<Session>(
      this.baseUrl,
      null,
      {
        params: {user1Id},
        headers: new HttpHeaders({Authorization: `Bearer ${token}`})
      }
    );
  }

  joinSession(inviteCode: string): Observable<Session> {
    const token = this.auth.getToken();
    const user2Id = this.auth.getCurrentUserId();
    return this.http.post<Session>(
      `${this.baseUrl}/${inviteCode}/join`,
      null,
      {
        params: {user2Id},
        headers: new HttpHeaders({Authorization: `Bearer ${token}`})
      }
    );
  }
}
