import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SessionChoosingComponent} from './session-choosing.component';

describe('SessionChoosingComponent', () => {
  let component: SessionChoosingComponent;
  let fixture: ComponentFixture<SessionChoosingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SessionChoosingComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SessionChoosingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
