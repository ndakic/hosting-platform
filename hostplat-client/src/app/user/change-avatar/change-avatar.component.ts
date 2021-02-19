import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/user.service';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-change-avatar',
  templateUrl: './change-avatar.component.html',
  styleUrls: ['./change-avatar.component.css']
})
export class ChangeAvatarComponent implements OnInit {

  user: User = {};
  constructor(private userService: UserService,
              private toastr: ToastrService,
              private authenticationService: AuthenticationService) {}

  ngOnInit(): void {
    this.getUserData();
  }


  private getUserData(): void {
    this.userService.getMyProfileData().subscribe(data => {
      this.user = {
        id: data.id,
        username: data.username,
        email: data.email,
        firstName: data.firstName,
        lastName: data.lastName,
        imagePath: data.imagePath
      };
    }, error => {
      this.toastr.error('There was an error while getting your profile data');
    });
  }

  isUserLoggedIn(): boolean {
    return this.authenticationService.isUserLoggedIn();
  }

  onClickChangeAvatar(event): void {
    console.log('ide slika na back')
/*     const image = event.target.files[0];
    const uploadData = new FormData();
    uploadData.append('file', image, image.name);

    this.userService.editUserProfileImage(uploadData).subscribe(data => {
      this.toastr.success('Your image has been successfully updated.');
    }, error => {
      this.toastr.error('There was an error while uploading your new profile image.');
    }); */
  }

}
