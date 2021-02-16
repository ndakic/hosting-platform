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

    }

    updateMilestone() {
      this.router.navigate(['/update-milestone/' + this.milestone.id]);
    }

    deleteMilestone() {
      this.milestoneService.delete(this.milestone.id).subscribe(
      );
      this.router.navigate(['/project-list']);
    }

    closeMilestone(id: number){
      this.milestoneService.close(id).subscribe(
      );
      this.router.navigate(['/project-list']);

    }

    

    
  } 