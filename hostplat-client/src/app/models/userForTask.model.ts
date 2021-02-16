import { EmailValidator } from "@angular/forms";

export class UsersForTask {
    public id?: number;
    public username?: string;
    public email?: string;
    public firstName?: string;
    public lastName?: string;
  
    constructor(id?: number, usename?: string, email?: string,
        firstName?: string, lastName?: string) {
        this.id = id;
        this.username = usename;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

} 