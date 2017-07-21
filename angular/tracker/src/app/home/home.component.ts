import { Component, OnInit } from '@angular/core';
import {HomeService} from "./home.service";
import {Vehicle} from "./vehicle.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  vehicles:Vehicle[] = [];

  constructor(private homeService:HomeService) {

  }

  ngOnInit() {
    this.homeService.getVehciles()
      .subscribe(
        data=>this.vehicles = data,
        error=>{
          console.log(error)
        });
  }

}
