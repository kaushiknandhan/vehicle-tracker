import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HighalertComponent } from './highalert.component';

describe('HighalertComponent', () => {
  let component: HighalertComponent;
  let fixture: ComponentFixture<HighalertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HighalertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HighalertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
