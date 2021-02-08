import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'HOSTPLAT';
  public role: string;

  constructor() {}

  checkRole() {
    const item = localStorage.getItem('user-role-key');

    if (!item) {
      this.role = undefined;
      return;
    }
    this.role = item;
    console.log(this.role);
  }
}
