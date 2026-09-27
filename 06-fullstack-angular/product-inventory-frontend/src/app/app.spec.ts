import { TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';

import { App } from './app';

describe('App', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [App],
      providers: [provideRouter([])],   // the header's routerLinks need a router
    }).compileComponents();
  });

  it('renders the header and a router outlet', async () => {
    const fixture = TestBed.createComponent(App);
    await fixture.whenStable();
    const page = fixture.nativeElement as HTMLElement;
    expect(page.querySelector('app-header nav')).toBeTruthy();
    expect(page.querySelector('router-outlet')).toBeTruthy();
  });
});
