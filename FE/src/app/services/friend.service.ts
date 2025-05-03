import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from './constants/constants';
import { Observable } from 'rxjs';
import { Friend } from '../models/friend.model';

@Injectable({ providedIn: 'root' })
export class FriendService {
  constructor(private http: HttpClient) {}

  getFriendships(userId: number): Observable<Friend[]> {
    return this.http.get<Friend[]>(`${API_URL}/friends/user/${userId}`);
  }

  add(friend: Friend): Observable<Friend> {
    return this.http.post<Friend>(`${API_URL}/friends`, friend);
  }

  delete(friend: Friend): Observable<void> {
    return this.http.delete<void>(`${API_URL}/friends`, { body: friend });
  }
}
