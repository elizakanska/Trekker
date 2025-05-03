import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from './constants/constants';
import { Observable } from 'rxjs';
import { Favorite } from '../models/favorite.model';

@Injectable({ providedIn: 'root' })
export class FavoriteService {
  constructor(private http: HttpClient) {}

  add(favorite: Favorite): Observable<Favorite> {
    return this.http.post<Favorite>(`${API_URL}/favorites`, favorite);
  }

  getByUser(userId: number): Observable<Favorite[]> {
    return this.http.get<Favorite[]>(`${API_URL}/favorites/user/${userId}`);
  }

  delete(favorite: Favorite): Observable<void> {
    return this.http.delete<void>(`${API_URL}/favorites`, { body: favorite });
  }
}
