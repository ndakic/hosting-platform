import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ChangePassword } from 'src/app/models/change-password';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  changePassword(changePassword: ChangePassword): Observable<any> {
    return this.http.post(environment.apiUrlPrefix + `/api/auth/change-password`, changePassword);
  }
}
