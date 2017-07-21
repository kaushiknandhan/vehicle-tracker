import { Pipe, PipeTransform } from '@angular/core';
import {HighAlert} from "../highalert/highalert.model";

@Pipe({
  name: 'sortPipe'
})
export class SortPipePipe implements PipeTransform {

  transform(alertArray:HighAlert[],sortyType:string): HighAlert[] {
    if(sortyType === 'Descending'){
     return alertArray.sort((a1,a2)=> a2.alertCount-a1.alertCount);
    }else{
      return alertArray.sort((a1,a2)=> a1.alertCount-a2.alertCount);
    }
  }
}
