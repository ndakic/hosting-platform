import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/core/services/user.service";
import { Task } from "src/app/models/task.model";
import { TaskService } from "../task.service";

@Component({
    selector: 'app-tasks-list',
    templateUrl: './tasks-list.component.html',
    styleUrls: ['./tasks-list.component.scss']
  })
  export class TasksListComponent implements OnInit {


  tasks: Task[];
  totalElements = 0;
  size = 8;

  constructor(
    private taskService: TaskService,
    private userService: UserService,

    ) { }

  ngOnInit() {
    this.getTasks();
  }

  getTasks() {
    this.taskService.getAll().subscribe(
        (data: Task[]) => {
        this.tasks = data;
        console.log(this.tasks);
    }
    );
  }

} 