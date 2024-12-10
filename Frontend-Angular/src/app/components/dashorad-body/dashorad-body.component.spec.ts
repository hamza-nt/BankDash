import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashoradBodyComponent } from './dashorad-body.component';

describe('DashoradBodyComponent', () => {
  let component: DashoradBodyComponent;
  let fixture: ComponentFixture<DashoradBodyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashoradBodyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashoradBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
