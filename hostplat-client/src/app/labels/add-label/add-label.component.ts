import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { Label } from "src/app/models/label.model";
import { LabelService } from "src/app/labels/label.service";

@Component({
  selector: 'app-add-label',
  templateUrl: './add-label.component.html',
  styleUrls: ['./add-label.component.scss']
})
export class AddLabelComponent implements OnInit {
  label = new Label(null, null , null, null);
  role = '';


  constructor(
    private labelService: LabelService,
    private router: Router,
    private toastr: ToastrService,
    private route: ActivatedRoute,
    private authService: AuthenticationService,
  ) { }

  ngOnInit() {
  }



  

  create() {
    const id = this.route.snapshot.paramMap.get('id');

    this.labelService.add(this.label).subscribe(
      (data: any) => {
        this.label = data;
        this.toastr.success('Successful add!');
        this.router.navigate(['/task-details/' + Number(id)]);
      }
    );

  }

}