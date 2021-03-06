import { ContentObserver } from "@angular/cdk/observers";
import { THIS_EXPR } from "@angular/compiler/src/output/output_ast";
import { Component, OnInit } from "@angular/core";
import { MatDatepickerInputEvent } from "@angular/material/datepicker";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { MilestoneTask } from "src/app/models/milestone-task.model";
import { Milestone } from "src/app/models/milestone.model";
import { MilestoneModule } from "../milestone.module";
import { MilestoneService } from "../milestone.service";

@Component({
  selector: 'app-add-milestone',
  templateUrl: './add-milestone.component.html',
  styleUrls: ['./add-milestone.component.scss']
})
export class AddMilestoneComponent implements OnInit {
  milestone = new Milestone(null, '', null, null);
  role = '';
  minDate = new Date();
  events: string[] = [];
  milestoneTask: MilestoneTask;
  milestone_id: number;
  milestones: Milestone[];
  broj: number;
  currentDate = new Date();

  constructor(
    private milestoneService: MilestoneService,
    private router: Router,
    private toastr: ToastrService,
    private authService: AuthenticationService,
    private route: ActivatedRoute,

  ) { }

  ngOnInit() {
    this.getAll();


  }

  getAll(){
    this.milestoneService.getAll().subscribe(
      (data: Milestone[]) => {
        this.milestones = data;
      }
    );
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.milestone.start_date = event.value;
  }

  addEvent2(type: string, event: MatDatepickerInputEvent<Date>) {
    this.milestone.end_date = event.value;
  }

  create() {
    this.broj = Number(this.route.snapshot.paramMap.get('id'));
    const id = localStorage.getItem('user-id-key');
    this.milestone.user_id = Number(id);
    this.milestoneTask = new MilestoneTask(this.broj, this.milestone);
    if(this.milestone.start_date>this.milestone.end_date){
      this.toastr.error('Start date cannot be after end date!');
      this.router.navigate(['/add-milestone/' + this.broj]);
    }else if(this.currentDate>this.milestone.start_date){
      this.toastr.error('Start date cannot be in the past!');
      this.router.navigate(['/add-milestone/' + this.broj]);
    }else if(this.currentDate>this.milestone.end_date){
      this.toastr.error('End date cannot be in the past!');
      this.router.navigate(['/add-milestone/' + this.broj]);
    }else{
      this.milestoneService.add(this.milestoneTask).subscribe(
        (data: any) => {
          this.toastr.success('Successful add!');
          this.milestone = data;
          this.router.navigate(['/task-details/' + this.broj]);
        }
      );
    }
  }

}