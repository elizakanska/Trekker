import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { API_URL } from './constants/constants';
import { Favorite } from '../models/favorite.model';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class FavoriteService {
  private readonly baseUrl = `${API_URL}/favorites`;

  constructor(private http: HttpClient, private auth: AuthService) {}

  getFavoritesByUser(userId: number): Observable<Favorite[]> {
    const token = this.auth.getToken();
    return this.http.get<Favorite[]>(
      `${this.baseUrl}/user/${userId}`,
      {
        headers: new HttpHeaders({
          Authorization: `Bearer ${token}`
        })
      }
    );
  }

  removeFavorite(userId: number, trailId: number): Observable<void> {
    const token = this.auth.getToken();
    return this.http.delete<void>(
      `${this.baseUrl}/user/${userId}/trail/${trailId}`,
      {
        headers: new HttpHeaders({
          Authorization: `Bearer ${token}`
        })
      }
    );
  }
}
