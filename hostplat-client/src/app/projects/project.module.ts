import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AddProjectComponent } from './add-project/add-project.component';
import { ProjectsListComponent } from './projects-list/projects-list.component';
import { UpdateProjectComponent } from './update-project/update-project.component';
import { ProjectListItemComponent } from './project-list-item/project-list-item.component';



@NgModule({
  declarations: [ProjectDetailsComponent, AddProjectComponent, ProjectsListComponent, UpdateProjectComponent,
  ProjectListItemComponent],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports: [
    ProjectDetailsComponent,
  ]
})
export class ProjectModule { } 