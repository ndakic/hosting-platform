import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { Milestone } from "src/app/models/milestone.model";
import { Task } from "src/app/models/task.model";
import { MilestoneService } from "../milestone.service";

@Component({
  selector: 'app-milestone-details',
  templateUrl: './milestone-details.component.html',
  styleUrls: ['./milestone-details.component.scss']
})
export class MilestoneDetailsComponent implements OnInit {

  milestone: Milestone;
  role = '';
  openTasks: Task[];
  closeTasks: Task[];
  step = 0;
  broj: number;
  open: number;
  close: number;
  displayedColumns: string[] = ['title', 'description', 'create_date'];
  displayedColumns2: string[] = ['title', 'description', 'create_date', 'end_date'];


   

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthenticationService,
    private toastr: ToastrService,
    private milestoneService: MilestoneService
  ) { }

  ngOnInit() {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.milestoneService.getOne(Number(id)).subscribe(
        (data: Milestone) => {
          this.milestone = data;
        }
      );
    }
    this.getOpenTask();
    this.open = this.openTasks.length;
    this.getCloseTask();

  }

  updateMilestone() {
    this.router.navigate(['/update-milestone/' + this.milestone.id]);
  }

  deleteMilestone() {
    this.milestoneService.delete(this.milestone.id).subscribe();
    this.router.navigate(['/project-list']);
  }

  closeMilestone(id: number){
    this.milestoneService.close(id).subscribe();
    this.router.navigate(['/project-list']);
  }

  setStep(index: number) {
    this.step = index;
  }
  
  nextStep() {
    this.step++;
  }
  
  prevStep() {
    this.step--;
  }


  getOpenTask(){
    this.broj = Number(this.route.snapshot.paramMap.get('id'));
    this.milestoneService.getOpenTaskForMilestone(Number(this.route.snapshot.paramMap.get('id'))).subscribe(
      (data: Task[]) => {
        this.openTasks = data;
      }
    );
  }

  getCloseTask(){
    this.broj = Number(this.route.snapshot.paramMap.get('id'));
    this.milestoneService.getCloseTaskForMilestone(Number(this.route.snapshot.paramMap.get('id'))).subscribe(
      (data: Task[]) => {
        this.closeTasks = data;
      }
    );
  }
    
} 