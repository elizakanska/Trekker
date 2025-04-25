import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseTrailComponent } from './choose-trail.component';

describe('ChooseTrailComponent', () => {
  let component: ChooseTrailComponent;
  let fixture: ComponentFixture<ChooseTrailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChooseTrailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChooseTrailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
