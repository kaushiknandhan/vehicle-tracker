import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AlertService} from "../alert.service";
import {Alert} from "../../../highalert/Alert.model";

@Component({
  selector: 'app-priority-alert',
  templateUrl: './priority-alert.component.html',
  styleUrls: ['./priority-alert.component.css']
})
export class PriorityAlertComponent implements OnInit {

  priorityType:string;
  vinNumber:string;
  priorityAlert:Alert[]=[];

  constructor(private activatedRoute:ActivatedRoute,private alertService:AlertService) {
    this.activatedRoute.pathFromRoot[this.activatedRoute.pathFromRoot.length - 3].params
      .subscribe(
        params=>{
          this.vinNumber = params['vin'];
        }
      )
    this.activatedRoute.params.subscribe(
      params=>{
        this.priorityType = params['priority'];
        this.getVehicleAlerts(this.vinNumber,this.priorityType);
      }
    );
   }

  getVehicleAlerts(vin,type){
    console.log('get vehicle alerts');
    this.alertService.getVehicleAlerts(this.vinNumber,this.priorityType)
      .subscribe(
        data=>this.priorityAlert = data,
        error=>console.log(error)
      )
  }

  ngOnInit() {


  }

}
