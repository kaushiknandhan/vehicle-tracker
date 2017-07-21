import { Component, OnInit } from '@angular/core';
import {AlertService} from "./alert.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {

  lowAlertsCount:number = 0;
  mediumAlertsCount:number = 0;
  highAlertsCount:number = 0;
  selectedPriority:string = '';
  vinNumber:string ;

  constructor(private activeRoute:ActivatedRoute,private alertService:AlertService) {
    this.activeRoute.parent.params.subscribe(
      params => {
        this.vinNumber = params['vin'];
      }

    )
  }

  ngOnInit() {
    this.getAlertCount('HIGH');
    this.getAlertCount('MEDIUM');
    this.getAlertCount('Low');
  }

  setColorByPriority(type:string):void{
    this.selectedPriority = type;
  }
  getAlertCount(type:string):void{
    this.alertService.getAlertTypeCount(this.vinNumber,type)
      .subscribe(
        data=>{
          if(type === 'HIGH'){
            this.highAlertsCount = data;
          }else if(type === 'MEDIUM'){
            this.mediumAlertsCount = data;
          }else{
            this.lowAlertsCount = data;
          }
        }
      );
  }

}
