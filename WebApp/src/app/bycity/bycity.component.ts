import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AirQualityService } from 'src/airquality.service';

@Component({
  selector: 'app-bycity',
  templateUrl: './bycity.component.html',
  styleUrls: ['./bycity.component.css']
})
export class BycityComponent implements OnInit {
  filterForm: FormGroup;
  values: any;
  result_ok: Boolean;
  loading: Boolean = false;
  error: Boolean = false;

  constructor(private airQualityService: AirQualityService) {}

  initForm(): void{
    this.filterForm = new FormGroup({
      city: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
    this.initForm();
    this.result_ok = false;
  }

  onSubmit(): void {
    if (this.filterForm.valid) {
      this.airQualityService.getAirQuality(this.filterForm.value.city).subscribe(vals => {
        this.values = vals; 
        this.result_ok = true;
        this.loading = false;
      }, error => {
        this.error = true;
        this.result_ok = false;
        this.loading = false;
      })
    }
  }
}
