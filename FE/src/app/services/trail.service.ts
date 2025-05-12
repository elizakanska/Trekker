import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { API_URL } from './constants/constants';
import { Observable } from 'rxjs';
import { Trail } from '../models/trail.model';
import {AuthService} from './auth.service';

@Injectable({ providedIn: 'root' })
export class TrailService {
  private readonly baseUrl = `${API_URL}/trails`;

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) {
  }

  private authHeaders(): HttpHeaders {
    const token = this.auth.getToken();
    return new HttpHeaders({Authorization: `Bearer ${token}`});
  }

  getAll(): Observable<Trail[]> {
    return this.http.get<Trail[]>(
      this.baseUrl,
      {headers: this.authHeaders()}
    );
  }

  getById(id: number): Observable<Trail> {
    return this.http.get<Trail>(
      `${this.baseUrl}/${id}`,
      {headers: this.authHeaders()}
    );
  }

  getTrailsByIds(ids: number[]): Observable<Trail[]> {
    return this.http.get<Trail[]>(
      this.baseUrl,
      {
        headers: this.authHeaders(),
        params: {ids: ids.join(',')}
      }
    );
  }

  create(trail: Trail): Observable<Trail> {
    return this.http.post<Trail>(
      this.baseUrl,
      trail,
      {headers: this.authHeaders()}
    );
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(
      `${this.baseUrl}/${id}`,
      {headers: this.authHeaders()}
    );
  }
}
