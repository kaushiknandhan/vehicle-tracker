import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HighAlertService} from "../highalert.service";
import {Alert} from "../Alert.model";

@Component({
  selector: 'app-highalert-details',
  templateUrl: './highalert-details.component.html',
  styleUrls: ['./highalert-details.component.css']
})
export class HighalertDetailsComponent implements OnInit {
  alerts: Alert[] = [];
  vinId:string ;

  constructor(private activeRoute:ActivatedRoute,private highAlertSevice:HighAlertService) { }

  ngOnInit() {
    this.activeRoute.params.subscribe(params => {
      this.vinId = params['id'];
      this.highAlertSevice.getAlertsofVehicle(params['id'],'HIGHALERTS')
        .subscribe(
          data=> this.alerts = data,
          error=>{
            console.log(error);
          }
        )
    })
  }

}
