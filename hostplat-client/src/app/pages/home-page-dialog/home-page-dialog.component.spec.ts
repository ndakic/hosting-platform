import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageDialogComponent } from './home-page-dialog.component';

describe('HomePageDialogComponent', () => {
  let component: HomePageDialogComponent;
  let fixture: ComponentFixture<HomePageDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePageDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
