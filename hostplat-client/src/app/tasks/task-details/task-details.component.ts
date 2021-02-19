import { getMultipleValuesInSingleSelectionError, SelectionModel } from "@angular/cdk/collections";
import { Component, Inject, OnInit } from "@angular/core";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { MilestoneService } from "src/app/milestones/milestone.service";
import { Milestone } from "src/app/models/milestone.model";
import { Task } from "src/app/models/task.model";
import { User } from "src/app/models/user";
import { ProjectService } from "src/app/projects/project.service";
import { TaskService } from "../task.service";
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import { MatCheckboxChange } from "@angular/material/checkbox";
import { MatTableDataSource } from "@angular/material/table";
import { UserTask } from "src/app/models/user-task.model";
import { MilestoneTask } from "src/app/models/milestone-task.model";
import { Label} from "src/app/models/label.model";
import { LabelTask} from "src/app/models/label-task.model";


@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.scss']
})
export class TaskDetailsComponent implements OnInit {

  task: Task;
  role = '';
  milestones: Milestone[];
  milestone: Milestone;
  users: User[];
  selected = 'option2';
  step = 0;
  displayedColumns: string[] = ['title', 'start_date', 'end_date'];
  displayedColumns2: string[] = ['select', 'firstName', 'lastName', 'username', 'email'];
  displayedColumns3: string[] = ['firstName', 'lastName', 'username', 'email'];
  displayedColumns4: string[] = ['select', 'name'];
  displayedColumns5: string[] = [ 'name'];

  labels: Label[];
  labelTask: LabelTask;


  selection = new SelectionModel<Milestone>(true, []);
  rowClicked;
  milestoneUpdate = new MilestoneTask(null, null);
  usersForTask: User[];
  usersFromFront: User[];
  userTask: UserTask;
  selection2 = new SelectionModel<User>(true, []);
  dataSource;
  id_glavni: number;
  selection3 = new SelectionModel<Label>(true, []);
  labelsForTask: Label[];


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService,
    private authService: AuthenticationService,
    private toastr: ToastrService,
    private milestoneService: MilestoneService,
    public dialog: MatDialog,
    private projectService: ProjectService,
  ) { }

  ngOnInit() {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.id_glavni = Number(this.route.snapshot.paramMap.get('id'));

    this.getMilestones();
    this.getUsers();
    this.getMilestoneForTask();
    this.getUsersForTask();
    this.getLabels();
    this.getLabelsForTask();

    if (id) {
      this.taskService.getTask(Number(id)).subscribe(
        (data: Task) => {
            this.task = data;
        }
      );
    }

    console.log(this.milestones);
  }

  changeTableRowColor(idx: any) { 
    if(this.rowClicked === idx) this.rowClicked = -1;
    else this.rowClicked = idx;
  }

  getMilestones() {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.projectService.getMilestoneForProject(Number(id)).subscribe(
      (data: Milestone[]) => {
        this.milestones = data;          
      }
    );
  }

  getUsers() {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.projectService.getUsersForProject(Number(id)).subscribe(
      (data: User[]) => {
        this.users = data;          
      }
    );

  }


  updateTask() {
    this.router.navigate(['/update-task/' + this.task.id]);
  }

  closeTask() {
    this.taskService.closeTask(this.task.id).subscribe();
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

  drop(event: CdkDragDrop<Milestone[]>) {
    moveItemInArray(this.milestones, event.previousIndex, event.currentIndex);
  }

  addMilestone(){
    this.router.navigate(['/add-milestone/' + this.task.id]);
  }

  addLabel(){
    this.router.navigate(['/add-label/' + this.task.id]);
  }

  onRowClicked(row) {
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.milestoneUpdate.task_id = Number(id);
    this.milestoneUpdate.milestone = row;
    this.taskService.setMilestoneToTask(this.milestoneUpdate).subscribe(
      (data: Milestone) => {
        this.milestone = data;
        this.toastr.success('Successful update!');
      }
    );
  }

  getUsersForTask(){
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.taskService.getUsersForTask(Number(id)).subscribe(
      (data: User[]) => {
        this.usersForTask = data;
      }
    );
  }


  getMilestoneForTask(){
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.taskService.getMilestoneForTask(Number(id)).subscribe(
      (data: Milestone) => {
        this.milestone = data;
      }
    );

  }

  addUser(task_id: number){
    console.log(task_id);
    this.userTask = new UserTask(task_id, this.selection2.selected);
    this.taskService.setUsersToTask(this.userTask).subscribe(
      (data: User[]) => {
        this.usersForTask = data;
      }
    );
  }

  isAllSelected() {
    const numSelected = this.selection2.selected.length;
    const numRows = this.users.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.dataSource = new MatTableDataSource<User>(this.users);

    this.isAllSelected() ?
        this.selection2.clear() :
        this.dataSource.forEach(row => this.selection2.select(row));
  }

  checkboxLabel(row?: User): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection2.isSelected(row) ? 'deselect' : 'select'} row`;
  }

 getLabels() {

    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.projectService.getLabels().subscribe(
      (data: Label[]) => {
        this.labels = data;          
      }
    );
  }

  addLabels(task_id: number){
    console.log(this.selection3.selected);
    console.log(task_id);
    this.labelTask = new LabelTask(task_id, this.selection3.selected);
    this.taskService.setLabelsToTask(this.labelTask).subscribe(
      (data: Label[]) => {
        this.labelsForTask = data;
      }
    );
  }

  getLabelsForTask(){
    this.role = this.authService.getRole();
    const id = this.route.snapshot.paramMap.get('id');
    this.taskService.getLabelsForTask(Number(id)).subscribe(
      (data: Label[]) => {
        this.labelsForTask = data;
      }
    );
  }

isAllSelected2() {
    const numSelected = this.selection3.selected.length;
    const numRows = this.users.length;
    return numSelected === numRows;
  }

  masterToggle2() {
    this.dataSource = new MatTableDataSource<Label>(this.labels);

    this.isAllSelected2() ?
        this.selection3.clear() :
        this.dataSource.forEach(row => this.selection3.select(row));
  }

  checkboxLabel2(row?: Label): string {
    if (!row) {
      return `${this.isAllSelected2() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection3.isSelected(row) ? 'deselect' : 'select'} row`;
  }


  }
