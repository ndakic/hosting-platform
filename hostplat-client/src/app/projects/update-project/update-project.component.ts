import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { Project } from "src/app/models/project.model";
import { ProjectService } from "../project.service";

@Component({
    selector: 'app-update-project',
    templateUrl: './update-project.component.html',
    styleUrls: ['./update-project.component.scss']
  })
  export class UpdateProjectComponent implements OnInit {
  
    project: Project;

    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private projectService: ProjectService,
      private toastr: ToastrService
    ) {
    }
  
    ngOnInit() {
      const id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.projectService.getProject(Number(id)).subscribe(
          (data: Project) => {
            this.project = data;
          }
        );
      }
    }
  

  
    update() {
      console.log("cao");
      console.log(this.project.description);
      this.projectService.update(this.project).subscribe(
        (data: any) => {
          this.project = data;
          this.toastr.success('Successful update!');
          this.router.navigate(['project-details/' + this.project.id]);
        }
      );
      }
  }