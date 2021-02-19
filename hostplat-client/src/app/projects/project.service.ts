import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Project } from "../models/project.model";
import { Statistics } from "../models/statistics.model";
import { UserProject } from "../models/user-project.model";

@Injectable({
    providedIn: 'root'
  })
  export class ProjectService {

    constructor(private http: HttpClient) {
    }

    getProject(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/project/` + id);
    }

    getAllForUser(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/project/allForUser/` + id);
    }

    update(project: Project):  Observable<any> {
      return this.http.put(environment.apiUrlPrefix +  `/api/project` , project);
    }

    getUser(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/users/` + id);
    }

    add(project: Project):  Observable<any> {
      return this.http.post(environment.apiUrlPrefix +  `/api/project` , project);
    }

    delete(id: number):  Observable<any> {
      return this.http.delete(environment.apiUrlPrefix +  `/api/project/` + id);
    }

    getMilestoneForProject(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/project/allMilestoneForProject/` + id);
    }

    getUsersForProject(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/project/allUserForTask/` + id);
    }
   
    updatePrivateProject(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/project/updatePrivateProject/` + id);
    }

    getOpenMilestone(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/milestone/getAllOpenForProject/` + id);
    }

    getCloseMilestone(id: number): Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/milestone/getAllCloseForProject/` + id);
    }

    getUsersOnProject(id: number):Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/project/allUserForProject/` + id);
    }

    getAllNewUserForProject(id: number):Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/api/project/getAllNewUserForProject/` + id);
    }
    
    setUsersToProject(users: UserProject):Observable<any> {
      return this.http.post(environment.apiUrlPrefix + `/api/project/setUsersToProject/`, users);
    }

    statistics(project: Statistics){
      return this.http.post(environment.apiUrlPrefix + `/api/project/statistics/`, project);
    }

    getLabels():Observable<any> {
      return this.http.get(environment.apiUrlPrefix + `/label/active`);
    }
    

  }