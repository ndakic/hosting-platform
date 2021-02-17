import { Component, Input, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Task } from "src/app/models/task.model";

@Component({
    selector: 'app-task-list-item',
    templateUrl: './task-list-item.component.html',
    styleUrls: ['./task-list-item.component.scss']
  })
  export class TaskListItemComponent implements OnInit {

    @Input() task: Task;

    constructor(
      private router: Router
    ) { }

    ngOnInit() {
      console.log("CAO IZ TASKA");
      console.log(this.task);
    }

    showDetails() {
        this.router.navigate(['/task-details/' + this.task.id]);
    }
  }