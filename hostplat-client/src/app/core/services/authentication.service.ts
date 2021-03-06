import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from 'src/app/models/login';
import { UserRegistrationData } from 'src/app/models/user-registration-data';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {
  }

  isUserLoggedIn(): boolean {
    return localStorage.getItem('user-id-key') != null;
  }

  login(login: Login): Observable<any> {
    return this.http.post(environment.apiUrlPrefix + `/api/auth/login`, login);
  }

  checkForAdmin(): boolean {
    return localStorage.getItem('user-role-key') === 'ROLE_ADMIN';
  }

  logout(): void {
    localStorage.removeItem('user-id-key');
    localStorage.removeItem('user-role-key');
    localStorage.removeItem('user-username-key');
    localStorage.removeItem('user-token-key');
  }

  getToken(): string {
    return localStorage.getItem('user-token-key');
  }

  getRole(): string {
    return localStorage.getItem('user-role-key');
  }

  addNewUser(userInfo: UserRegistrationData): Observable<any> {
    return this.http.post(environment.apiUrlPrefix + '/api/users/public/add-user', userInfo);
  }


  activateAccount(confirmationToken: string): Observable<any> {
    return this.http.get(`${environment.apiUrlPrefix + '/api/users/public/verify-account'}/${confirmationToken}`);
  }
}
