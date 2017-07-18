import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {Vehicle} from "../home/vehicle.model";
/**
 * Created by kaushik nandhan on 7/17/2017.
 */

@Injectable()
export class VehicleService{
  constructor(private http:Http){}

  getVehicleDetails(vinNumber:string):Observable<Vehicle>{
    return this.http.get(`http://localhost:9009/api/vehicles/${vinNumber}`)
      .map(response=>response.json())
      .catch(error=>Observable.throw(error));
  }

  getVehicleAlertsCount(vinNumber,type):Observable<number>{
    return this.http.get(`http://localhost:9009/api/alerts/totalcount/${vinNumber}/${type}`)
      .map(response=>response.json())
      .catch(error=>Observable.throw(error));
  }
}
