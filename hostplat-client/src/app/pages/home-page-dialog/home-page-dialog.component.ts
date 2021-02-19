import { Component, Inject} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-home-page-dialog',
  templateUrl: './home-page-dialog.component.html',
  styleUrls: ['./home-page-dialog.component.css']
})
export class HomePageDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<HomePageDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

}
