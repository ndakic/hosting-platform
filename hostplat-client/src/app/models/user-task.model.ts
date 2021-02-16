import { User } from "./user";

export class UserTask {
    public task_id: number;
    public users: User[];
  
    constructor(task_id?: number, users?: User[]) {
        this.task_id = task_id;
        this.users = users;
    }

} 