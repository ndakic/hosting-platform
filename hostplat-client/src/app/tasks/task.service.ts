import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Task } from "../models/task.model";

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


  }