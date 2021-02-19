import { Router } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { ProjectService } from 'src/app/projects/project.service';
import { HomePageDialogComponent } from '../home-page-dialog/home-page-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  private publicProjectDetails: any;

  displayedColumns: string[] = ['id', 'name', 'description', 'details'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;

  constructor(private projectService: ProjectService,
              private toastr: ToastrService,
              private router: Router,
              public dialog: MatDialog
    ) {}
  ngOnInit() {
    this.getPublicProjects();
  }
  getPublicProjects(): void {
    this.projectService.getAllPublic().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }, error => {
      this.toastr.error(error);
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onClickOpenHomePageDialog(id: number): void {
    this.projectService.getProject(id).subscribe(data => {
      this.publicProjectDetails = data;
      const dialogRef = this.dialog.open(HomePageDialogComponent, {
        width: '500px',
        data: this.publicProjectDetails
      });
      },
      error => {
        this.toastr.error(error);
        this.toastr.error('There was an error while getting the data about public project details.');
        this.router.navigate(['not-found-page']);
      }
    );
  }

}
