import { Component, OnInit } from "@angular/core";
import { getMatInputUnsupportedTypeError } from "@angular/material/input";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { Project } from "src/app/models/project.model";
import { User } from "src/app/models/user.model";
import { ProjectService } from "../project.service";
import { ProjectsListComponent } from "../projects-list/projects-list.component";

@Component({
    selector: 'app-add-project',
    templateUrl: './add-project.component.html',
    styleUrls: ['./add-project.component.scss']
  })
  export class AddProjectComponent implements OnInit {
    project = new Project(null, '', '', null, [], false);
    user: User;    
    

    constructor(
      private projectService: ProjectService,
      private router: Router,
      private toastr: ToastrService
    ) { }
  
    ngOnInit() {
      this.getUser();
    }

    getUser(){
      const id = localStorage.getItem('user-id-key');
      this.projectService.getUser(Number(id)).subscribe(
        (data: any) => {
          this.user = data;
        }
      );
     
    }
  
  
    add() {
      this.project.users.push(this.user);
      
      this.projectService.add(this.project).subscribe(
        (data: any) => {
          this.toastr.success('Successful add!');
          this.project = data;
          this.router.navigate(['/project-list']);
        }
      );
    }
  
  }