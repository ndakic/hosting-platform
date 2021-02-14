import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { MilestoneTask } from "../models/milestone-task.model";
import { Milestone } from "../models/milestone.model";

@Injectable({
    providedIn: 'root'
  })
  export class MilestoneService {

    constructor(private http: HttpClient) {
    }

    getOne(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix +  `/api/milestone/` + id);
    }

    delete(id: number):  Observable<any> {
      return this.http.delete(environment.apiUrlPrefix +  `/api/milestone/` + id);
    }
   
    close(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix +  `/api/milestone/closeMilestone/` + id);
    }

    update(milestone: Milestone):  Observable<any> {
      return this.http.put(environment.apiUrlPrefix +  `/api/milestone` , milestone);
    }

    add(milestone: Milestone):  Observable<any> {
      return this.http.post(environment.apiUrlPrefix +  `/api/milestone` , milestone);
    }

    addMilestoneToTask(milestoneTask: MilestoneTask):  Observable<any> {
      return this.http.put(environment.apiUrlPrefix +  `/api/milestone/addMilestoneToTask` , milestoneTask);
    }

    getAll():  Observable<any> {
      return this.http.get(environment.apiUrlPrefix +  `/api/milestone/getAll` );
    }

  }