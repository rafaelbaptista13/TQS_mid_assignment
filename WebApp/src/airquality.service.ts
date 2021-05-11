import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json',
    'access-control-allow-origin': 'http://localhost:8080/'})
};

@Injectable({
  providedIn: 'root'
})
export class AirQualityService {
  // URL inicial da api
  private URL = 'http://localhost:8080/api/airquality/';
  //private httpOptions: { headers: HttpHeaders };
  constructor(private http: HttpClient) { }

  getAirQuality(city: any): Observable<any> {
    let url = this.URL + '?city=' + city;
    return this.http.get(url, httpOptions);
  }

  getAirQualityByDay(lat: any, lon: any, from: any) {
    let url = this.URL + 'coords?lat=' + lat + '&lng=' + lon + '&from=' + from;
    return this.http.get(url, httpOptions);
  }

  getCache() {
    let url = this.URL + 'cache';
    return this.http.get(url, httpOptions);
  }
}