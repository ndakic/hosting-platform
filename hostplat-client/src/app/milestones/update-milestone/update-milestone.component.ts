import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { Milestone } from "src/app/models/milestone.model";
import { MilestoneService } from "../milestone.service";

@Component({
    selector: 'app-update-milestone',
    templateUrl: './update-milestone.component.html',
    styleUrls: ['./update-milestone.component.scss']
  })
  export class UpdateMilestoneComponent implements OnInit {

    milestone: Milestone;

    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private milestoneService: MilestoneService,
      private toastr: ToastrService
    ) {
    }

    ngOnInit() {
      const id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.milestoneService.getOne(Number(id)).subscribe(
          (data: Milestone) => {
            this.milestone = data;
          }
        );
      }
      console.log(this.milestone);
    }



    update() {
      this.milestoneService.update(this.milestone).subscribe(
        (data: any) => {
          this.milestone = data;
          this.toastr.success('Successful update!');
          this.router.navigate(['project-list']);
        }
      );
      }
  }  