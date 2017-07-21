import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {HighAlert} from "./highalert.model";
import {Alert} from "./Alert.model";
/**
 * Created by kaushik nandhan on 7/16/2017.
 */

@Injectable()
export class HighAlertService{

  constructor(private http:Http){}

  getHighAlerts():Observable<HighAlert[]>{
    return this.http.get(`http://localhost:9009/api/alerts/highalerts`)
      .map(response=>response.json())
      .catch(error=>Observable.throw(error));
  }

  getAlertsofVehicle(vin:String,type:string):Observable<Alert[]>{
    return this.http.get(`http://localhost:9009/api/alerts/${vin}/${type}`)
      .map(response=>response.json())
      .catch(error=>Observable.throw(error));
  }
}
