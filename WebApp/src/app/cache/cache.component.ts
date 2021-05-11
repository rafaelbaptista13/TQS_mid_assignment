import { Component, OnInit } from '@angular/core';
import { AirQualityService } from 'src/airquality.service';

@Component({
  selector: 'app-cache',
  templateUrl: './cache.component.html',
  styleUrls: ['./cache.component.css']
})
export class CacheComponent implements OnInit {
  values: any;

  constructor(private airQualityService: AirQualityService) {}

  ngOnInit(): void {
    this.airQualityService.getCache().subscribe(vals => {this.values = vals})
  }

}
