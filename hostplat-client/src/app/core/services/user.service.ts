import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ChangePassword } from 'src/app/models/change-password';
import { User } from 'src/app/models/user';
import { UserEditInfo } from 'src/app/models/user-edit-info';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  changePassword(changePassword: ChangePassword): Observable<any> {
    return this.http.post(environment.apiUrlPrefix + `/api/auth/change-password`, changePassword);
  }

  getMyProfileData(): Observable<User> {
    return this.http.get(environment.apiUrlPrefix + '/api/users/my-profile');
  }

  editMyProfile(userEditData: UserEditInfo): Observable<any> {
    return this.http.put(environment.apiUrlPrefix + '/api/users/my-profile', userEditData);
  }
  
}
