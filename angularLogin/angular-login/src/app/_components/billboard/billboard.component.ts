import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/_services/api.service';
import { Router } from '@angular/router';

import * as models from 'src/app/_models/user';

@Component({
  selector: 'app-billboard',
  templateUrl: './billboard.component.html',
  styleUrls: ['./billboard.component.css']
})
export class BillboardComponent implements OnInit {

  billboardForm: FormGroup;
  billboardUser: models.BillboardUser;
  constructor(private fb: FormBuilder, 
    private apiServive: ApiService,
    private router: Router) {
    this.billboardForm = this.fb.group({
      title: ['', Validators.required],
      description: ['']
    })

    if (!this.apiServive.currentUserValue) {
      this.router.navigate(['/login']);
    }
    this.apiServive.areUAtHome = false;
  }

  ngOnInit() {
  }

  onSubmit() {
    console.log( this.apiServive.currentUserValue.email)
    this.billboardUser = new models.BillboardUser();

    let bill = new models.Billboard();
    bill.title = this.billboardForm.controls['title'].value;
    bill.description = this.billboardForm.controls['description'].value;
    bill.date = new Date(Date.now());

    let user = new models.User();
    user.email = this.apiServive.currentUserValue.email;

    this.billboardUser.billboard = bill;
    this.billboardUser.user = user;

    console.log(this.billboardUser)
    
    this.apiServive.postBillboard(this.billboardUser).subscribe(resp => console.log(resp));
  }

  get atHome(){
    return this.apiServive.areUAtHome;
  }

}
