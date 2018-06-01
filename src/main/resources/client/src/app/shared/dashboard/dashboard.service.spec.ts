import { TestBed, inject } from '@angular/core/testing';

import { DashBoardService } from './dashboard.service';

describe('BeerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DashBoardService]
    });
  });

  it('should be created', inject([DashBoardService], (service: DashBoardService) => {
    expect(service).toBeTruthy();
  }));
});
