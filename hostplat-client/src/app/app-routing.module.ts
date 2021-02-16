import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { ForbiddenPageComponent } from './pages/forbidden-page/forbidden-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { ChangeBasicInfoComponent } from './user/change-basic-info/change-basic-info.component';
import { ChangePasswordComponent } from './user/change-password/change-password.component';
import { RoleGuard } from './core/guards/role.guard';
import { ProjectDetailsComponent } from './projects/project-details/project-details.component';
import { ProjectsListComponent } from './projects/projects-list/projects-list.component';
import { ProjectListItemComponent } from './projects/project-list-item/project-list-item.component';
import { UpdateProjectComponent } from './projects/update-project/update-project.component';
import { AddProjectComponent } from './projects/add-project/add-project.component';
import { AccountConfirmationComponent } from './authentication/account-confirmation/account-confirmation.component';
import { AddTaskComponent } from './tasks/add-task/add-task.component';
import { TaskDetailsComponent } from './tasks/task-details/task-details.component';
import { TasksListComponent } from './tasks/tasks-list/tasks-list.component';
import { UpdateTaskComponent } from './tasks/update-task/update-task.component';
import { UpdateMilestoneComponent } from './milestones/update-milestone/update-milestone.component';
import { AddMilestoneComponent } from './milestones/add-milestone/add-milestone.component';
import { MilestoneDetailsComponent } from './milestones/milestone-details/milestone-details.component';

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'home', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'verify', component: AccountConfirmationComponent },
  {path: 'forbidden-page', component: ForbiddenPageComponent},
  {path: 'not-found-page', component: NotFoundPageComponent},
  {
    path: 'change-password',
    component: ChangePasswordComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'change-basic-info',
    component: ChangeBasicInfoComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'project-details/:id',
    component: ProjectDetailsComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'project-list',
    component: ProjectsListComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'update-project/:id',
    component: UpdateProjectComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'add-project',
    component: AddProjectComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'add-task/:id',
    component: AddTaskComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'task-details/:id',
    component: TaskDetailsComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },

  {
    path: 'update-task/:id',
    component: UpdateTaskComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'update-milestone/:id',
    component: UpdateMilestoneComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'add-milestone/:id',
    component: AddMilestoneComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'milestone-details/:id',
    component: MilestoneDetailsComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },

 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
