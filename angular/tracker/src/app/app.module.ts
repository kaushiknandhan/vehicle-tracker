import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import {Routes, RouterModule} from "@angular/router";
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import {HomeService} from "./home/home.service";
import {HighAlertService} from "./highalert/highalert.service";
import { SortPipePipe } from './shared/sort-pipe.pipe';
import { HighalertComponent } from './highalert/highalert.component';
import { HighalertDetailsComponent } from './highalert/highalert-details/highalert-details.component';
import { AlertBoxComponent } from './shared/alert-box/alert-box.component';
import {VehiclesComponent} from "./vehicles/vehicles.component";
import {VehicleService} from "./vehicles/vehicle.service";
import { AlertComponent } from './vehicles/alert/alert.component';
import { LocationComponent } from './vehicles/location/location.component';
import { GraphComponent } from './vehicles/graph/graph.component';
import { VehicleItemComponent } from './shared/vehicle-item/vehicle-item.component';
import {AlertService} from "./vehicles/alert/alert.service";
import { PriorityAlertComponent } from './vehicles/alert/priority-alert/priority-alert.component';
import { AgmCoreModule } from '@agm/core';
import {ReadingService} from "./vehicles/reading.service";
import { GraphPlotComponent } from './vehicles/graph/graph-plot/graph-plot.component';
import {ChartsModule} from "ng2-charts/index";

const appRoutes : Routes =[
  {path:'home',component:HomeComponent},
  {path:'highalerts',component:HighalertComponent,children:[
    {path:':id',component:HighalertDetailsComponent}
  ]},
  {path:'vehicles/:vin',component:VehiclesComponent,children:[
    {path:'alert',component:AlertComponent,children:[
      {path:':priority',component:PriorityAlertComponent}
    ]},
    {path:'location',component:LocationComponent},
    {path:'graph',component:GraphComponent,children:[
      {path:':signal',component:GraphPlotComponent}
    ]}
  ]},
  {path:'',redirectTo:'/home',pathMatch:'full'}
  ];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    SortPipePipe,
    HighalertComponent,
    HighalertDetailsComponent,
    AlertBoxComponent,
    VehiclesComponent,
    AlertComponent,
    LocationComponent,
    GraphComponent,
    VehicleItemComponent,
    PriorityAlertComponent,
    GraphPlotComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes,{useHash:true}),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBN4sRHkoDk6nE-H_kHl3RE4M3eRmRx-2E'
    }),
    ChartsModule
  ],
  providers: [HomeService,HighAlertService,VehicleService,AlertService,ReadingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
