import { User } from "./user.model";

export class Project {
    public id: number;
    public name: string;
    public description: String;
    public create_date: Date;
    public users: User[];
    public private_project: boolean;

    constructor(id?: number, name?: string, description?: string, create_date?: Date, 
        users?: User[], private_project?: boolean) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.create_date = create_date;
        this.users = users;
        this.private_project = private_project;
    }


} 