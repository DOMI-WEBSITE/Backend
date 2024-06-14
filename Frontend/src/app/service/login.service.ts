import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../interfaces/login';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private backendUrl = 'http://localhost:8095/auth/login'

  constructor(private httpClient: HttpClient) { }

  login(login: Login): Observable<any> {
    //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post<any>(this.backendUrl, login).pipe(
      tap(response => {
        if (response && response.token) {
          localStorage.setItem('authToken', response.token);
          //console.log(response.token);
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem('authToken');
  }

  getToken(): string | null {
    return localStorage.getItem('authToken');
  }
}