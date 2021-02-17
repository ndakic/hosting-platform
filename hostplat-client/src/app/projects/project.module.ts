import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AddProjectComponent } from './add-project/add-project.component';
import { ProjectsListComponent } from './projects-list/projects-list.component';
import { UpdateProjectComponent } from './update-project/update-project.component';
import { ProjectListItemComponent } from './project-list-item/project-list-item.component';
import { TasksListComponent } from '../tasks/tasks-list/tasks-list.component';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRippleModule } from '@angular/material/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from '../app-routing.module';
import { MatDialogModule } from '@angular/material/dialog';
import { AddUsersComponent } from './add-users/add-users.component';



@NgModule({
  declarations: [ProjectDetailsComponent, AddProjectComponent, ProjectsListComponent, UpdateProjectComponent,
  ProjectListItemComponent, AddUsersComponent],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule    
  ],
  exports: [
    ProjectDetailsComponent,
    AddProjectComponent,
    ProjectsListComponent,
    UpdateProjectComponent,
    ProjectListItemComponent,
    AddUsersComponent
  ]
})
export class ProjectModule { } 