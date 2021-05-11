import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BydayComponent } from './byday.component';

describe('BydayComponent', () => {
  let component: BydayComponent;
  let fixture: ComponentFixture<BydayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BydayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BydayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
