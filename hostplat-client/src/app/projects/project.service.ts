import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Project } from "../models/project.model";

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

  }