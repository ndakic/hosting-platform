import { ThrowStmt } from "@angular/compiler";
import { Label } from "./label.model";
import { User } from "./user.model";

export class Task {
    public id: number;
    public title: string;
    public description: String;
    public create_date: Date;
    public end_date: Date;
    public author_id: number;
    public assigned_users: User[];
    public project_id: number;
    public milestone_id: number;
    public labels: Label[];

    constructor(id?: number, title?: string, description?: string, create_date?: Date, 
        end_date?: Date, author_id?: number, assigned_users?: User[], project_id?: number,
        milestone_id?: number, labels?: Label[]) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.create_date = create_date;
        this.end_date = end_date;
        this.author_id = author_id;
        this.assigned_users = assigned_users;
        this.project_id = project_id;
        this.milestone_id = milestone_id;
        this.labels = labels;
    }


} 