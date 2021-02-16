import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { MilestoneTask } from "../models/milestone-task.model";
import { Task } from "../models/task.model";
import { UserTask } from "../models/user-task.model";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(environment.apiUrlPrefix + `/api/task/getAll`);
  }

  getAllOpenByProjectId(id: number): Observable<any> {
    return this.http.get(environment.apiUrlPrefix +  `/api/task/getAllOpenById/` + id);
  }

  getAllCloseByProjectId(id: number): Observable<any> {
    return this.http.get(environment.apiUrlPrefix +  `/api/task/getAllCloseById/` + id);
  }

  getTask(id: number): Observable<any> {
    return this.http.get(environment.apiUrlPrefix +  `/api/task/` + id);
  }

  closeTask(id: number): Observable<any> {
    return this.http.get(environment.apiUrlPrefix +  `/api/task/closeTask/` + id);
  }

  updateTask(task: Task): Observable<any> {
    return this.http.put(environment.apiUrlPrefix +  `/api/task/`, task);
  }

  add(task: Task): Observable<any> {
    return this.http.post(environment.apiUrlPrefix +  `/api/task`, task);
  }

  getMilestoneForTask(id: number): Observable<any> {
    return this.http.get(environment.apiUrlPrefix +  `/api/task/getMilestoneForTask/` + id);
  }

  setMilestoneToTask(milestoneUpdate: MilestoneTask): Observable<any> {
    return this.http.post(environment.apiUrlPrefix +  `/api/task/setMilestoneToTask`, milestoneUpdate);
  }

  getUsersForTask(id: number): Observable<any> {
    return this.http.get(environment.apiUrlPrefix +  `/api/task/getUsersForTask/` + id);
  }

  setUsersToTask(users: UserTask): Observable<any> {
    return this.http.post(environment.apiUrlPrefix +  `/api/task/setUsersToTask`, users);
  }

  }