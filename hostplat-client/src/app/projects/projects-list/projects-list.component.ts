import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { UserService } from "src/app/core/services/user.service";
import { Project } from "src/app/models/project.model";
import { Task } from "src/app/models/task.model";
import { User } from "src/app/models/user.model";
import { TaskService } from "src/app/tasks/task.service";
import { ProjectService } from "../project.service";

@Component({
  selector: 'app-projects-list',
  templateUrl: './projects-list.component.html',
  styleUrls: ['./projects-list.component.scss']
})
export class ProjectsListComponent implements OnInit {


  projects: Project[];
  totalElements = 0;
  size = 8;
  tasks: Task[];
  user: User;

  constructor(
    private projectService: ProjectService,
    private userService: UserService,
    private taskService: TaskService,

    ) { }

  ngOnInit() {
    this.getProjects();
  }

  getProjects() {
    const id = localStorage.getItem('user-id-key');

    this.projectService.getAllForUser(Number(id)).subscribe(
      (data: Project[]) => {
        this.projects = data;
        console.log(this.projects);
      }
    );
  }

}  