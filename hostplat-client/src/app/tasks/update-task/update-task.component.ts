import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { Task } from "src/app/models/task.model";
import { TaskService } from "../task.service";

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.scss']
})
export class UpdateTaskComponent implements OnInit {

  task: Task;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
      
    if (id) {
      this.taskService.getTask(Number(id)).subscribe(
        (data: Task) => {
          this.task = data;
        }
      );
    }
  }



  update() {
    this.taskService.updateTask(this.task).subscribe(
      (data: any) => {
        this.task = data;
        this.toastr.success('Successful update!');
        this.router.navigate(['task-details/' + this.task.id]);
      }
    );
  }

}  