import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { API_URL } from './constants/constants';
import {AuthService} from './auth.service';
import { Observable } from 'rxjs';
import { Session } from '../models/session.model';

@Injectable({ providedIn: 'root' })
export class SessionService {
  private readonly baseUrl = `${API_URL}/sessions`;

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) {
  }

  private authHeaders(): HttpHeaders {
    const token = this.auth.getToken();
    return new HttpHeaders({Authorization: `Bearer ${token}`});
  }

  createSession(): Observable<Session> {
    const user1Id = this.auth.getCurrentUserId();
    return this.http.post<Session>(
      this.baseUrl,
      null,
      {
        params: {user1Id},
        headers: this.authHeaders()
      }
    );
  }

  joinSession(inviteCode: string): Observable<Session> {
    const user2Id = this.auth.getCurrentUserId();
    return this.http.post<Session>(
      `${this.baseUrl}/${inviteCode}/join`,
      null,
      {
        params: {user2Id},
        headers: this.authHeaders()
      }
    );
  }

  getSessionUsers(user1Id: number): Observable<{ id: number; username: string }[]> {
    return this.http.get<{ id: number; username: string }[]>(
      `${this.baseUrl}/${user1Id}/users`,
      {headers: this.authHeaders()}
    );
  }

  setFilters(
    user1Id: number,
    filters: {
      min: number;
      max: number;
      difficulties: string[];
      biomes: string[];
    }
  ): Observable<Session> {
    const params = {
      min: filters.min.toString(),
      max: filters.max.toString(),
      difficulty: filters.difficulties.join(','),
      biome: filters.biomes.join(',')
    };
    return this.http.patch<Session>(
      `${this.baseUrl}/${user1Id}/filters`,
      null,
      {
        params,
        headers: this.authHeaders()
      }
    );
  }
}
