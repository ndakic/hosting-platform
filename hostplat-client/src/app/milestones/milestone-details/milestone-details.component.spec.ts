import { async, ComponentFixture, TestBed } from "@angular/core/testing";
import { MilestoneDetailsComponent } from "./milestone-details.component";

describe('MilestoneDetailsComponent', () => {
    let component: MilestoneDetailsComponent;
    let fixture: ComponentFixture<MilestoneDetailsComponent>;

    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [ MilestoneDetailsComponent ]
      })
      .compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(MilestoneDetailsComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

    it('should create', () => {
      expect(component).toBeTruthy();
    });
  }); 