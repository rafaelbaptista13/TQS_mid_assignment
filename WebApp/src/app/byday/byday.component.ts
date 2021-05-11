import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AirQualityService } from 'src/airquality.service';

@Component({
  selector: 'app-byday',
  templateUrl: './byday.component.html',
  styleUrls: ['./byday.component.css']
})
export class BydayComponent implements OnInit {
  filterForm: FormGroup;
  values: any;
  result_ok: Boolean;
  loading: Boolean = false;
  error: Boolean = false;

  constructor(private airQualityService: AirQualityService) {}

  initForm(): void{
    this.filterForm = new FormGroup({
      lat: new FormControl('', [Validators.required]),
      lon: new FormControl('', [Validators.required]),
      day: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
    this.initForm();
    this.result_ok = false;
  }

  onSubmit(): void {
    if (this.filterForm.valid) {
      this.loading = true;
      this.airQualityService.getAirQualityByDay(this.filterForm.value.lat, this.filterForm.value.lon, this.filterForm.value.day).subscribe(vals => {
        this.values = vals; 
        this.result_ok = true; 
        this.loading = false;
      }, error => {
        this.error =  true;
        this.result_ok = false;
        this.loading = false;
      })
    }
  }
}
