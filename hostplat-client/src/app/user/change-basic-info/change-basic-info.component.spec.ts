import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeBasicInfoComponent } from './change-basic-info.component';

describe('ChangeBasicInfoComponent', () => {
  let component: ChangeBasicInfoComponent;
  let fixture: ComponentFixture<ChangeBasicInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeBasicInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeBasicInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
