import {Component, OnInit, Input} from '@angular/core';
import {Alert} from "../../highalert/Alert.model";

@Component({
  selector: 'app-alert-box',
  templateUrl: './alert-box.component.html',
  styleUrls: ['./alert-box.component.css']
})
export class AlertBoxComponent implements OnInit {

  @Input() alertBox:Alert;
  constructor() { }

  ngOnInit() {
  }

}
