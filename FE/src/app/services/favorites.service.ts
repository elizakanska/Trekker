import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_URL } from './constants/constants';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {
  private favoritesApiUrl = `${API_URL}/favorites`;

  constructor(private http: HttpClient) { }

  getFavorites(userId: number): Observable<any> {
    return this.http.get(`${this.favoritesApiUrl}/user/${userId}`);
  }

  addFavorite(userId: number, trailId: number): Observable<any> {
    return this.http.post(this.favoritesApiUrl, { userId, trailId });
  }

  removeFavorite(userId: number, trailId: number): Observable<any> {
    return this.http.delete(`${this.favoritesApiUrl}/user/${userId}/trail/${trailId}`);
  }
}
