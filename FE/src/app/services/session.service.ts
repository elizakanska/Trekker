import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

import {AuthService} from './auth.service';
import { API_URL } from './constants/constants';
import { Session } from '../models/session.model';
import {Trail} from '../models/trail.model';
import {SessionLike} from '../models/session-like.model';
import {SessionResult} from '../models/session-result.model';

@Injectable({ providedIn: 'root' })
export class SessionService {
  private readonly api = `${API_URL}/sessions`;

  constructor(private http: HttpClient, private auth: AuthService) {
  }

  private authHeaders(): HttpHeaders {
    return new HttpHeaders({Authorization: `Bearer ${this.auth.getToken()}`});
  }

  createSession(): Observable<Session> {
    const user1Id = this.auth.getCurrentUserId();
    return this.http.post<Session>(
      `${this.api}?user1Id=${user1Id}`,
      {},
      {headers: this.authHeaders()}
    );
  }

  joinSession(inviteCode: string): Observable<Session> {
    const user2Id = this.auth.getCurrentUserId();
    return this.http.post<Session>(
      `${this.api}/${inviteCode}/join?user2Id=${user2Id}`,
      {},
      {headers: this.authHeaders()}
    );
  }

  getCurrent(userId: number): Observable<Session> {
    return this.http.get<Session>(
      `${this.api}/current?userId=${userId}`,
      {headers: this.authHeaders()}
    );
  }

  getUsers(user1Id: number): Observable<string[]> {
    return this.http.get<string[]>(
      `${this.api}/${user1Id}/users`,
      {headers: this.authHeaders()}
    );
  }

  setFilters(
    user1Id: number,
    min: number,
    max: number,
    difficulty: string,
    biome: string
  ): Observable<Session> {
    return this.http.patch<Session>(
      `${this.api}/${user1Id}/filters`,
      null,
      {
        headers: this.authHeaders(),
        params: {min: min.toString(), max: max.toString(), difficulty, biome}
      }
    );
  }

  begin(user1Id: number): Observable<Trail[]> {
    return this.http.get<Trail[]>(
      `${this.api}/${user1Id}/trails`,
      {headers: this.authHeaders()}
    );
  }

  like(
    user1Id: number,
    trailId: number,
    liked: boolean,
    round: number
  ): Observable<SessionLike> {
    const userId = this.auth.getCurrentUserId();
    return this.http.post<SessionLike>(
      `${this.api}/${user1Id}/likes`,
      null,
      {
        headers: this.authHeaders(),
        params: {
          trailId: trailId.toString(),
          userId: userId.toString(),
          liked: liked.toString(),
          round: round.toString()
        }
      }
    );
  }

  getMutualLikes(user1Id: number): Observable<Trail[]> {
    return this.http.get<Trail[]>(
      `${this.api}/${user1Id}/mutual`,
      {headers: this.authHeaders()}
    );
  }

  rank(user1Id: number, round: number): Observable<SessionResult[]> {
    return this.http.post<SessionResult[]>(
      `${this.api}/${user1Id}/rank`,
      null,
      {
        headers: this.authHeaders(),
        params: {round: round.toString()}
      }
    );
  }

  finalize(user1Id: number): Observable<SessionResult[]> {
    return this.http.get<SessionResult[]>(
      `${this.api}/${user1Id}/final`,
      {headers: this.authHeaders()}
    );
  }
}
