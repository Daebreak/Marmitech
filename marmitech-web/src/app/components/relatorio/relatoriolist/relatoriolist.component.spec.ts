import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoriolistComponent } from './relatoriolist.component';

describe('RelatoriolistComponent', () => {
  let component: RelatoriolistComponent;
  let fixture: ComponentFixture<RelatoriolistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatoriolistComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoriolistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
