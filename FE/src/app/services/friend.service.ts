import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Friend } from '../models/friend.model';
import {catchError, Observable, throwError} from 'rxjs';
import { API_URL } from './constants/constants';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class FriendService {
  private readonly baseUrl = `${API_URL}/friends`;

  constructor(private http: HttpClient, private auth: AuthService) {}

  getFriendsByUser(userId: number): Observable<Friend[]> {
    const token = this.auth.getToken();
    return this.http.get<Friend[]>(`${this.baseUrl}/user/${userId}`, {
      headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
    });
  }

  addFriend(friend: Friend): Observable<Friend> {
    const token = this.auth.getToken();
    return this.http.post<Friend>(this.baseUrl, friend, {
      headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
    }).pipe(
      catchError(error => {
        console.error('Error adding friend:', error);
        return throwError(() => new Error('Failed to add friend'));
      })
    );
  }

  deleteFriend(friend: Friend): Observable<void> {
    const token = this.auth.getToken();
    return this.http.request<void>('delete', this.baseUrl, {
      body: friend,
      headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
    });
  }
}
