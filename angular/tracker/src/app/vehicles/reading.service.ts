import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {Reading} from "./Reading.model";
/**
 * Created by kaushik nandhan on 7/18/2017.
 */

@Injectable()
export class ReadingService{
  constructor(private http:Http){}

  getVehicleReadings(vinNumber:string,timeType:string,time:number):Observable<Reading[]>{
    return this.http.get(`http://localhost:9009/api/readings/${vinNumber}/${timeType}/${time}`)
      .map(response=>response.json())
      .catch(error=>Observable.throw(error))
  }
}
