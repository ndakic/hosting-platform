import { Component, OnInit } from "@angular/core";
import { MatDatepickerInputEvent } from "@angular/material/datepicker";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { Label } from "src/app/models/label.model";
import { LabelService } from "../label.service";

@Component({
  selector: 'app-update-label',
  templateUrl: './update-label.component.html',
  styleUrls: ['./update-label.component.scss']
})
export class UpdateLabelComponent implements OnInit {

  label: Label;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private labelService: LabelService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.labelService.getOne(id).subscribe(
        (data: Label) => {
          this.label = data;
        }
      );
    }
  }

 
  update() {
    const id = this.route.snapshot.paramMap.get('id');

    this.labelService.update(this.label).subscribe(
        (data: any) => {
          this.label = data;
          this.toastr.success('Successful update!');
          this.router.navigate(['project-list']);
        }
      );
    }
  }
 