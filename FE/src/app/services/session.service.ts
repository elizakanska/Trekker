import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from './constants/constants';
import { Observable } from 'rxjs';
import { Session } from '../models/session.model';

@Injectable({ providedIn: 'root' })
export class SessionService {
  constructor(private http: HttpClient) {}

  getActiveSessions(userId: number): Observable<Session[]> {
    return this.http.get<Session[]>(`${API_URL}/sessions/user/${userId}`);
  }

  getByInviteCode(inviteCode: number): Observable<Session> {
    return this.http.get<Session>(`${API_URL}/sessions/invite/${inviteCode}`);
  }

  create(session: Session): Observable<Session> {
    return this.http.post<Session>(`${API_URL}/sessions`, session);
  }

  getById(user1Id: number, user2Id: number): Observable<Session> {
    return this.http.get<Session>(`${API_URL}/sessions/${user1Id}/${user2Id}`);
  }

  delete(session: Session): Observable<void> {
    return this.http.delete<void>(`${API_URL}/sessions`, { body: session });
  }
}
