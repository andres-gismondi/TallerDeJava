import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/_services/api.service';
import { Router, ActivatedRoute } from '@angular/router';

import * as models from 'src/app/_models/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private billboards: models.Billboard[] = []

  filtersLoader: Promise<boolean>;

  constructor(private apiServive: ApiService,
    private router: Router) {

    if (!this.apiServive.currentUserValue) {
      this.router.navigate(['/login']);
    }
    this.apiServive.areUAtHome = true;
    console.log('---------')
    console.log(this.apiServive.currentUserValue)
  }

  ngOnInit() {
    this.getBillboards();
  }

  getBillboards(): void{
    this.apiServive.getBillboards().subscribe((result:Array<models.Billboard>) => {
      this.billboards = result;
      this.filtersLoader = Promise.resolve(true);
    })
  }

  get allBillboards(){
    console.log(this.billboards)
    return this.billboards;
  }

}
