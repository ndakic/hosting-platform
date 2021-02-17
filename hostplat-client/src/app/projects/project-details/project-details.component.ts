import { NgIf } from "@angular/common";
import { identifierModuleUrl } from "@angular/compiler";
import { Component, Inject, OnInit } from "@angular/core";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { MilestoneService } from "src/app/milestones/milestone.service";
import { Milestone } from "src/app/models/milestone.model";
import { Project } from "src/app/models/project.model";
import { Task } from "src/app/models/task.model";
import { TaskService } from "src/app/tasks/task.service";
import { ProjectService } from "../project.service";



@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.scss']
})
export class ProjectDetailsComponent implements OnInit {

  project: Project;
  role = '';
  openTasks: Task[];
  closeTasks: Task[];
  openMilestones: Milestone[];
  closeMilestones: Milestone[];


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService,
    private authService: AuthenticationService,
    private toastr: ToastrService,
    private taskService: TaskService,
    private milestoneService: MilestoneService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.projectService.getProject(Number(id)).subscribe(
        (data: Project) => {
          this.project = data;
        }
      );
    }
    this.taskService.getAllOpenByProjectId(Number(id)).subscribe(
      (data: Task[]) => {
        this.openTasks = data;
      }
    );
    this.taskService.getAllCloseByProjectId(Number(id)).subscribe(
      (data: Task[]) => {
        this.closeTasks = data;
      }
    );
    this.getCloseMilestones()
    this.getOpenMilestones()
  }

  updateProject() {
    this.router.navigate(['/update-project/' + this.project.id]);
  }

  delete() {
    this.projectService.delete(this.project.id).subscribe();
    this.router.navigate(['/project-list']);
  }

  showDetails(id: Number){
    this.router.navigate(['/task-details/' + id]);
  }

  updatePrivateProject() {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.projectService.updatePrivateProject(Number(id)).subscribe(
      (data: Project) => {
        this.project = data;
      }
    );
  }

  getCloseMilestones() {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.projectService.getCloseMilestone(Number(id)).subscribe(
      (data: Milestone[]) => {
        this.closeMilestones = data;          
      }
    );
  }

  getOpenMilestones() {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.projectService.getOpenMilestone(Number(id)).subscribe(
      (data: Milestone[]) => {
        this.openMilestones = data;          
      }
    );
  }


  update(id: number){
    this.router.navigate(['/update-milestone/' + id]);
  }

  close(id: number){
    this.milestoneService.close(id).subscribe();
  }

  deleteMilestone(id: number){
    this.milestoneService.delete(id).subscribe();
  }

  showDetailsMilestone(id: number){
    this.router.navigate(['/milestone-details/' + id]);
  }

  addTask(){
    this.router.navigate(['/add-task/' + this.project.id]);
  }
} 
