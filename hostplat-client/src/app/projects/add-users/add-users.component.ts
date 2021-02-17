import { SelectionModel } from "@angular/cdk/collections";
import { Component, OnInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { User } from "src/app/models/user";
import { UserProject } from "src/app/models/user-project.model";
import { ProjectService } from "../project.service";

@Component({
    selector: 'app-add-users',
    templateUrl: './add-users.component.html',
    styleUrls: ['./add-users.component.scss']
  })
  export class AddUsersComponent implements OnInit {
  
    role = '';
    usersOnProject: User[];
    rowClicked;
    displayedColumns: string[] = ['select', 'firstName', 'lastName', 'username', 'email'];
    dataSource;
    selection = new SelectionModel<User>(true, []);
    userProject: UserProject;
  
    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private projectService: ProjectService,
      private authService: AuthenticationService,
      private toastr: ToastrService,
    ) { }
  
    ngOnInit() {
        this.role = this.authService.getRole();
        const id = this.route.snapshot.paramMap.get('id');
        this.projectService.getAllNewUserForProject(Number(id)).subscribe(
            (data: User[]) => {
                this.usersOnProject = data;          
            }
        );
      
    }
  
    isAllSelected() {
        this.dataSource = new MatTableDataSource(this.usersOnProject);

        const numSelected = this.selection.selected.length;
        const numRows = this.dataSource.data.length;
        return numSelected === numRows;
      }
    
      /** Selects all rows if they are not all selected; otherwise clear selection. */
      masterToggle() {
        this.dataSource = new MatTableDataSource(this.usersOnProject);

        this.isAllSelected() ?
            this.selection.clear() :
            this.dataSource.data.forEach(row => this.selection.select(row));
      }
    
      /** The label for the checkbox on the passed row */
      checkboxLabel(row?: User): string {
        this.dataSource = new MatTableDataSource(this.usersOnProject);

        if (!row) {
          return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
        }
        return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row`;
    }

    addUser(){
        const project_id = this.route.snapshot.paramMap.get('id');
        this.userProject = new UserProject(Number(project_id), this.selection.selected);
        this.projectService.setUsersToProject(this.userProject).subscribe(
          (data: User[]) => {
              this.usersOnProject = data;
            this.router.navigate(['/project-details/' + Number(project_id)]);

          }
        );
      }
    
}
  
   
  
  