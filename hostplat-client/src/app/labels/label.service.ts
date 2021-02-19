import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Label } from "../models/label.model";


@Injectable({
    providedIn: 'root'
  })
  export class LabelService {

    constructor(private http: HttpClient) {
    }

    update(label: Label):  Observable<any> {
      return this.http.put(environment.apiUrlPrefix +  `/label` , label);
    }

    delete(id: number):  Observable<any> {
      return this.http.delete(environment.apiUrlPrefix +  `/label/` + id);
    }

    getOne(id: string): Observable<any> {
      return this.http.get(environment.apiUrlPrefix +  `/label/` + id);
    }

     add(label: Label): Observable<any> {
      return this.http.post(environment.apiUrlPrefix +  `/label`, label);
    }

  }