import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundPageComponent } from './not-found-page/not-found-page.component';
import { ForbiddenPageComponent } from './forbidden-page/forbidden-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { HomePageDialogComponent } from './home-page-dialog/home-page-dialog.component';




@NgModule({
  declarations: [NotFoundPageComponent, ForbiddenPageComponent, HomePageComponent, HomePageDialogComponent],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports: [
    NotFoundPageComponent,
    ForbiddenPageComponent,
    HomePageComponent
  ]
})
export class PagesModule { }
