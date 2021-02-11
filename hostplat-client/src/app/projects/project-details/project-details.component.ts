import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { Project } from "src/app/models/project.model";
import { ProjectService } from "../project.service";

@Component({
    selector: 'app-project-details',
    templateUrl: './project-details.component.html',
    styleUrls: ['./project-details.component.scss']
  })
  export class ProjectDetailsComponent implements OnInit {

    project: Project;
    role = '';

    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private projectService: ProjectService,
      private authService: AuthenticationService,
      private toastr: ToastrService

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

    }

    updateProject() {
      this.router.navigate(['/update-project/' + this.project.id]);
    }

    delete() {
      this.projectService.delete(this.project.id).subscribe(
      );
      this.router.navigate(['/project-list']);
    }
  } 