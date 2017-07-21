import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphPlotComponent } from './graph-plot.component';

describe('GraphPlotComponent', () => {
  let component: GraphPlotComponent;
  let fixture: ComponentFixture<GraphPlotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GraphPlotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GraphPlotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
