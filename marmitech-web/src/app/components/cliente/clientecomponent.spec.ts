import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Clientecomponent } from './clientecomponent';

describe('Clientecomponent', () => {
  let component: Clientecomponent;
  let fixture: ComponentFixture<Clientecomponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Clientecomponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Clientecomponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
