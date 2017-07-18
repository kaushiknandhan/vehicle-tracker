import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {Alert} from "../../highalert/Alert.model";
/**
 * Created by kaushik nandhan on 7/18/2017.
 */

@Injectable()
export class AlertService{
  constructor(private http:Http){}

  getAlertTypeCount(vinNumber:string,type:string):Observable<number>{
    return this.http.get(`http://localhost:9009/api/alerts/totalcount/${vinNumber}/${type}`)
      .map(response=>response.json())
      .catch(error=>Observable.throw(error));
  }

  getVehicleAlerts(vinNumber:string,type:string):Observable<Alert[]>{
  return  this.http.get(`http://localhost:9009/api/alerts/${vinNumber}/${type}`)
    .map(response=>response.json())
      .catch(error=>Observable.throw(error));
}
}
