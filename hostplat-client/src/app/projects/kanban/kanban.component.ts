import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { Task } from 'src/app/models/task.model';
import { TaskService } from 'src/app/tasks/task.service';
import { ProjectService } from '../project.service';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-kanban',
  templateUrl: './kanban.component.html',
  styleUrls: ['./kanban.component.css']
})
export class KanbanComponent implements OnInit {
  openTasks: Task[];
  closeTasks: Task[];
  routeSub: Subscription;

  constructor(private toastr: ToastrService,
              private router: Router,
              private route: ActivatedRoute,
              private projectService: ProjectService,
              private taskService: TaskService,) { }

  ngOnInit() {
    this.routeSub = this.route.params.subscribe( params => {
      this.getAllOpen(params.id as number);
      this.getAllClose(params.id as number);
    });
  }

  private getAllOpen(id: number): void {
    this.taskService.getAllOpenByProjectId(id).subscribe(
      (data: Task[]) => {
        this.openTasks = data;
      }
    );
  }

  private getAllClose(id: number): void {
    this.taskService.getAllCloseByProjectId(Number(id)).subscribe(
      (data: Task[]) => {
        this.closeTasks = data;
      }
    );
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
                        event.container.data,
                        event.previousIndex,
                        event.currentIndex);
    }
  }

}
