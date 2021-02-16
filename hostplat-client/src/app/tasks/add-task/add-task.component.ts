import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { UserService } from "src/app/core/services/user.service";
import { Task } from "src/app/models/task.model";
import { User } from "src/app/models/user.model";
import { ProjectService } from "src/app/projects/project.service";
import { TaskService } from "../task.service";

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.scss']
})
export class AddTaskComponent implements OnInit {
  task = new Task(null, '', '', null, null, null, null, null, null, null);
  user: User;    
  role = '';
  users: User[];


  constructor(
    private taskService: TaskService,
    private router: Router,
    private toastr: ToastrService,
    private route: ActivatedRoute,
    private authService: AuthenticationService,
    private projectService: ProjectService
  ) { }

  ngOnInit() {
  }

    

  getUser(){
    const id = localStorage.getItem('user-id-key');
    this.projectService.getUser(Number(id)).subscribe(
      (data: any) => {
        this.user = data;
      }
    );
  }

  create() {
    this.role = this.authService.getRole();
    const project_id = this.route.snapshot.paramMap.get('id');
    const user_id = localStorage.getItem('user-id-key');
    this.task.project_id = Number(project_id);
    this.task.author_id = Number(user_id);
    this.taskService.add(this.task).subscribe(
      (data: any) => {
        this.task = data;
        this.toastr.success('Successful add!');
        this.router.navigate(['/task-details/' + data.id]);
      }
    );

  }

} 