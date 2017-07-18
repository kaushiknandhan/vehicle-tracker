import { Component, OnInit } from '@angular/core';
import {HighAlert} from "../highalert/highalert.model";
import {HighAlertService} from "./highalert.service";

@Component({
  selector: 'app-alert',
  templateUrl: './highalert.component.html',
  styleUrls: ['./highalert.component.css']
})
export class HighalertComponent implements OnInit {

  highAlert:HighAlert[]=[];
  sortType:string = 'Descending';
  constructor(private highalertService:HighAlertService) { }

  ngOnInit() {
    this.highalertService.getHighAlerts()
      .subscribe(
        data=>this.highAlert = data,
        error=>{
          console.log(error)
        });
  }

  isLengthZero():boolean{
    return this.highAlert.length === 0;
  }
  sortList():void{
    (this.sortType === 'Descending')?this.sortType = 'Ascending':this.sortType = 'Descending';
    console.log(this.sortType);
  }

}
