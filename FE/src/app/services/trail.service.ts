import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from './constants/constants';
import { Observable } from 'rxjs';
import { Trail } from '../models/trail.model';

@Injectable({ providedIn: 'root' })
export class TrailService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Trail[]> {
    return this.http.get<Trail[]>(`${API_URL}/trails`);
  }

  getById(id: number): Observable<Trail> {
    return this.http.get<Trail>(`${API_URL}/trails/${id}`);
  }

  getTrailsByIds(ids: number[]): Observable<Trail[]> {
    return this.http.get<Trail[]>(`${API_URL}/trails`, {
      params: { ids: ids.join(',') }
    });
  }

  create(trail: Trail): Observable<Trail> {
    return this.http.post<Trail>(`${API_URL}/trails`, trail);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/trails/${id}`);
  }
}
