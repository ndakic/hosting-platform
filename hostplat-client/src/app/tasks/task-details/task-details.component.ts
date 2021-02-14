import { getMultipleValuesInSingleSelectionError } from "@angular/cdk/collections";
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
    selectedFoodstuffs = [];
  foodstuffs = [];
  selectFoodstuff = [];

  

    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private taskService: TaskService,
      private authService: AuthenticationService,
      private toastr: ToastrService,
      private milestoneService: MilestoneService,
      //public dialog: MatDialog
      public dialog: MatDialog,
      private projectService: ProjectService,


    ) { }

    ngOnInit() {
      this.role = this.authService.getRole();
      const id = this.route.snapshot.paramMap.get('id');

      this.getMilestones();
      this.getUsers();
      
      if (id) {
        this.taskService.getTask(Number(id)).subscribe(
          (data: Task) => {
            this.task = data;
        }
        );
      }
     


    }

    getMilestones() {
      this.role = this.authService.getRole();
      const id = this.route.snapshot.paramMap.get('id');
      this.projectService.getMilestoneForProject(Number(id)).subscribe(
        (data: Milestone[]) => {
          this.milestones = data;          
      }
      );
      console.log(this.milestones);
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
      this.taskService.closeTask(this.task.id).subscribe(
      );
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
      console.log(event.currentIndex);
    }

    addMilestone(){
      this.router.navigate(['/add-milestone']);
    }

    isFoodstuffSelected = (foodstuffID: number) => {
      return this.selectedFoodstuffs.includes(foodstuffID);
    }
  
    onFoodstuffCheckboxChange(event: MatCheckboxChange, idx: number, foodstuff: Milestone) {
      if (event.checked) {
        this.selectFoodstuff.push(foodstuff);
      }
    }

  } 

 
  