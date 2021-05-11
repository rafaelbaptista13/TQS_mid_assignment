import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BycityComponent } from './bycity.component';

describe('BycityComponent', () => {
  let component: BycityComponent;
  let fixture: ComponentFixture<BycityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BycityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BycityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
