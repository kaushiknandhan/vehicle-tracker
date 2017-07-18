import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import {Vehicle} from "./vehicle.model";
/**
 * Created by kaushik nandhan on 7/16/2017.
 */

@Injectable()
export class HomeService{
  constructor(private http:Http){}

  getVehciles():Observable<Vehicle[]>{
    return this.http.get('http://localhost:9009/api/'+'vehicles')
      .map(
        response=>response.json()
      )
      .catch(
        error=>Observable.throw(error.statusText)
      );
  }


}
