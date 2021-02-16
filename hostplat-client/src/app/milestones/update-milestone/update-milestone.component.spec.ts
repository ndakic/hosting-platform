import { async, ComponentFixture, TestBed } from "@angular/core/testing";
import { UpdateMilestoneComponent } from "./update-milestone.component";

describe('UpdateMilestoneComponent', () => {
    let component: UpdateMilestoneComponent;
    let fixture: ComponentFixture<UpdateMilestoneComponent>;

    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [ UpdateMilestoneComponent ]
      })
      .compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(UpdateMilestoneComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

    it('should create', () => {
      expect(component).toBeTruthy();
    });
  }); 