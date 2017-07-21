import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HighalertDetailsComponent } from './highalert-details.component';

describe('HighalertDetailsComponent', () => {
  let component: HighalertDetailsComponent;
  let fixture: ComponentFixture<HighalertDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HighalertDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HighalertDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
