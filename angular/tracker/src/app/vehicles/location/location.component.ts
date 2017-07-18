import { Component, OnInit } from '@angular/core';
import {ReadingService} from "../reading.service";
import {Reading} from "../Reading.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {

  longitude:number = 41.8719;
  latitude:number = 12.5674;
  locationReadings:Reading[] = [];
  vinNumber:string;
  zoomNumber:number=2;
  constructor(private activeRoute:ActivatedRoute,private readingService:ReadingService) {
    this.activeRoute.parent.params.subscribe(
      params=>{
        console.log(this.vinNumber);
        this.vinNumber = params['vin'];
      }
    )
  }

  ngOnInit() {
      this.readingService.getVehicleReadings(this.vinNumber,'MINUTES',30)
        .subscribe(
          data=>{
            this.locationReadings = data;
          },
          error=>console.log(error)
        );
  }

  isReadingsPresent(){
    return this.locationReadings.length !== 0;
  }

}
