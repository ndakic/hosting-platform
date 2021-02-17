import { User } from "./user";

export class UserProject {
    public project_id: number;
    public users: User[];

    constructor(project_id?: number, users?: User[]) {
        this.project_id = project_id;
        this.users = users;
    }

}  