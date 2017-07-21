import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PriorityAlertComponent } from './priority-alert.component';

describe('PriorityAlertComponent', () => {
  let component: PriorityAlertComponent;
  let fixture: ComponentFixture<PriorityAlertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PriorityAlertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PriorityAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
