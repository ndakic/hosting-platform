import { Component, Input, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Project } from "src/app/models/project.model";

@Component({
    selector: 'app-project-list-item',
    templateUrl: './project-list-item.component.html',
    styleUrls: ['./project-list-item.component.scss']
  })
  export class ProjectListItemComponent implements OnInit {

    @Input() project: Project;

    constructor(
      private router: Router
    ) { }

    ngOnInit() {
    }

    showDetails() {
      this.router.navigate(['/project-details/' + this.project.id]);
    }
  }