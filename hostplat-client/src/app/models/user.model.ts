export class User {
    public id: number;
    public firstName: string;
    public lastName: string;
    public email: string;
    public username: string;

    constructor(id?: number, firstName?: string, lastName?: string, email?: string, username?: string) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.username = username;
    }

  }  