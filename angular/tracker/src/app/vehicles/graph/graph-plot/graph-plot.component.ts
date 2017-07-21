import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ReadingService} from "../../reading.service";
import {Reading} from "../../Reading.model";

@Component({
  selector: 'app-graph-plot',
  templateUrl: './graph-plot.component.html',
  styleUrls: ['./graph-plot.component.css']
})
export class GraphPlotComponent implements OnInit {

  signal:string;
  vinNumer:string;
  readingsArr:Reading[] = [];
  selectedTime:string = 'DAYS';
  timesStamps:string[]=['DAYS','HOURS','MINUTES'];
  timeValue:number = 5;
  speedData:Array<any> =[
    {data:[],label: 'Speed Data'}
  ];
  engineRpmData:Array<any> =[
    {data:[],label: 'Engine RPM Data'}
  ];
  engineHpData:Array<any> =[
    {data:[],label: 'Engine Hp Data'}
  ];
  fuelVolumeData:Array<any> =[
    {data:[],label: 'Fuel Volume Data'}
  ];
  timestampArr:string[] = [];
  lineChartOptions:any = {
    responsive: true
  };
  lineChartLegend:boolean = true;
  lineChartType:string = 'line';
  barChartType:string = 'bar';
  public lineChartColors:Array<any> = [
    {
      backgroundColor: 'rgba(20,30,48,0.5)',
      borderColor: 'rgba(20,30,48,1)',
      pointBackgroundColor: 'rgb(20,30,48,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(20,30,48,1)'
    }];


  constructor(private activedRoute:ActivatedRoute,private readingService:ReadingService) {
    this.activedRoute.params.subscribe(
      params=>{
        this.signal = params['signal'];
      }
    );
    this.activedRoute.pathFromRoot[this.activedRoute.pathFromRoot.length - 3]
      .params.subscribe(
      params=>{
        this.vinNumer = params['vin'];
      }
    )
  }

  ngOnInit() {
   this.getReadingsWithTime(this.vinNumer,this.selectedTime,this.timeValue)
  }

  getReadingsWithTime(vin:string,timeStamp:string,time:number){
    this.readingService.getVehicleReadings(vin,timeStamp,time)
      .subscribe(
        data=>{
          this.readingsArr = data;
          this.setData(data);
        },
        error=>{
          console.log(error);
        }
      )
  }

  getFormatedTime(time:Date):string{
    var date = new Date(time);
    var formatedTime = [date.getMonth()+1,
        date.getDate(),
        date.getFullYear()].join('/')+' '+
      [date.getHours(),
        date.getMinutes(),
        date.getSeconds()].join(':');
    return formatedTime;
  }

  setData(readingData:Reading[]){
    for(let i = 0;i< readingData.length ; i++){
      this.speedData[0].data.push(readingData[i].speed);
      this.engineRpmData[0].data.push(readingData[i].engineRpm);
      this.engineHpData[0].data.push(readingData[i].engineHp);
      this.fuelVolumeData[0].data.push(readingData[i].fuelVolume);
      this.timestampArr.push(this.getFormatedTime(readingData[i].timestamp));
    }
  }

  readingWithTime(timeStamp:string,time:number):void{
    this.cleanDataFromArrays();
    this.getReadingsWithTime(this.vinNumer,timeStamp,time);
  }

  onChange(timeStamp){
    // may be needed in future use
  }

  cleanDataFromArrays():void{
    this.readingsArr = [];
    this.speedData[0].data = [];
    this.engineRpmData[0].data = [];
    this.engineHpData[0].data = [];
    this.fuelVolumeData[0].data = [];
    this.timestampArr = [];
  }
}
