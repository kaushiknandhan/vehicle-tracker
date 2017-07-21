/**
 * Created by kaushik nandhan on 7/18/2017.
 */
export interface Reading{
  readingId:string;
  vin:string;
  latitude:number;
  longitude:number;
  timestamp:Date;
  fuelVolume:number;
  speed:number;
  engineHp:number;
  checkEngineLightOn:boolean;
  engineCoolantLow:boolean;
  cruiseControlOn:boolean;
  engineRpm:number;
}
