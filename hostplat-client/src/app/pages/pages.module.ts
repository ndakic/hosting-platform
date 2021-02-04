import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundPageComponent } from './not-found-page/not-found-page.component';
import { ForbiddenPageComponent } from './forbidden-page/forbidden-page.component';
import { HomePageComponent } from './home-page/home-page.component';



@NgModule({
  declarations: [NotFoundPageComponent, ForbiddenPageComponent, HomePageComponent],
  imports: [
    CommonModule
  ]
})
export class PagesModule { }
