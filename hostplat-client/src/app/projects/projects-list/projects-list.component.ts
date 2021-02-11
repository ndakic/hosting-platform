import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { UserService } from "src/app/core/services/user.service";
import { Project } from "src/app/models/project.model";
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

  constructor(
    private projectService: ProjectService,
    private userService: UserService,

    ) { }

  ngOnInit() {
    this.getProjects();
  }

  getProjects() {
    this.projectService.getAllForUser(1).subscribe(
      (data: Project[]) => {
        // tslint:disable-next-line: no-string-literal
        this.projects = data;
        console.log(this.projects);
      }
    );
  }

}