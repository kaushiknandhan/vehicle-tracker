import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Vehicle} from "../home/vehicle.model";
import {VehicleService} from "./vehicle.service";

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehiclesComponent implements OnInit {

  vehicleInfo:Vehicle;
  vinNumber:string;
  totalAlertCount:number;
  constructor(private activeRoute:ActivatedRoute,private vehicleService:VehicleService) {
    this.activeRoute.params.subscribe(
      params =>  {
        this.vinNumber = params['vin']
        this.getVehicleDetails();
      }
    );
  }

  getVehicleDetails(){
    this.vehicleService.getVehicleDetails(this.vinNumber)
      .subscribe(
        data=>this.vehicleInfo = data,
        error=>console.log(error)
      );
  }

  ngOnInit() {
    this.vehicleService.getVehicleAlertsCount(this.vinNumber,'All')
      .subscribe(
        data=>this.totalAlertCount= data,
        error=>console.log(error)
      );

  }

}
