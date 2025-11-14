import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoriodetailsComponent } from './relatoriodetails.component';

describe('RelatoriodetailsComponent', () => {
  let component: RelatoriodetailsComponent;
  let fixture: ComponentFixture<RelatoriodetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatoriodetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoriodetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
