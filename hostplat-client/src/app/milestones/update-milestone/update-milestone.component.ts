import { Component, OnInit } from "@angular/core";
import { MatDatepickerInputEvent } from "@angular/material/datepicker";
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
  currentDate = new Date();


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private milestoneService: MilestoneService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.milestoneService.getOne(Number(id)).subscribe(
        (data: Milestone) => {
          this.milestone = data;
        }
      );
    }
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.milestone.start_date = event.value;
  }
    
  addEvent2(type: string, event: MatDatepickerInputEvent<Date>) {
    this.milestone.end_date = event.value;
  }


  update() {
    const id = this.route.snapshot.paramMap.get('id');

    if(this.milestone.start_date>this.milestone.end_date){
      this.toastr.error('Start date cannot be after end date!');
      this.router.navigate(['/update-milestone/' + Number(id)]);
    }else if(this.currentDate>this.milestone.start_date){
      this.toastr.error('Start date cannot be in the past!');
      this.router.navigate(['/update-milestone/' + Number(id)]);
    }else if(this.currentDate>this.milestone.end_date){
      this.toastr.error('End date cannot be in the past!');
      this.router.navigate(['/update-milestone/' + Number(id)]);
    }else{
      this.milestoneService.update(this.milestone).subscribe(
        (data: any) => {
          this.milestone = data;
          this.toastr.success('Successful update!');
          this.router.navigate(['project-list']);
        }
      );
    }
  }
}  