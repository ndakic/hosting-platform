import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from 'src/app/core/services/authentication.service';

@Component({
  selector: 'app-account-confirmation',
  templateUrl: './account-confirmation.component.html',
  styleUrls: ['./account-confirmation.component.css']
})
export class AccountConfirmationComponent implements OnInit {

  private confirmationToken: string;

  constructor(private activatedRoute: ActivatedRoute,
              private authenticationService: AuthenticationService,
              private toastr: ToastrService,
              private router: Router) {
    this.activatedRoute.queryParams.subscribe(params => {
      this.confirmationToken = params.token;
      this.activateAccount();
    });
  }

  ngOnInit() {
  }

  private activateAccount(): void {
    this.authenticationService.activateAccount(this.confirmationToken).subscribe(data => {
      this.toastr.success('Your account has been activated.');
      this.router.navigate(['login']);
    }, error => {
      this.toastr.error('There was an error while activating your account.');
    });
  }

}
