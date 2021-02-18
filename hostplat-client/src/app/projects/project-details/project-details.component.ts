import { SelectionModel } from "@angular/cdk/collections";
import { NgIf } from "@angular/common";
import { identifierModuleUrl } from "@angular/compiler";
import { Component, Inject, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AsyncSubject, Subject } from "rxjs";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { MilestoneService } from "src/app/milestones/milestone.service";
import { Milestone } from "src/app/models/milestone.model";
import { Project } from "src/app/models/project.model";
import { StatisticsBack } from "src/app/models/statisticBack.model";
import { Statistics } from "src/app/models/statistics.model";
import { Task } from "src/app/models/task.model";
import { User } from "src/app/models/user";
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
  usersOnProject: User[];
  displayedColumns3: string[] = ['firstName', 'lastName', 'username', 'email'];
  selection2 = new SelectionModel<User>(true, []);
  dataSource;
  selected = '3 days';
  statistics: Statistics;
  back: StatisticsBack;
  open: Task[];
  closeT: Task[];
  o: number;
  c: number;

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
    this.getUsersOnProject()
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

  addUsers(){
    this.router.navigate(['/add-users/' + this.project.id]);
  }

  getUsersOnProject() {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.projectService.getUsersOnProject(Number(id)).subscribe(
      (data: User[]) => {
        this.usersOnProject = data;          
      }
    );
  }

  show(selected){
    console.log(selected);
    const id = this.route.snapshot.paramMap.get('id');
    this.statistics = new Statistics(Number(id), selected);
    this.projectService.statistics(this.statistics).subscribe(
      (data: StatisticsBack)  => {
        this.back = data;       
        console.log(data); 
        this.closeT = this.back.close;
        this.c = this.closeT.length;
        this.open = this.back.open; 
        this.o = this.open.length; 
      }
    );
  }
} 
