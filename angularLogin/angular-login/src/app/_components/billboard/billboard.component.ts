import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/_services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-billboard',
  templateUrl: './billboard.component.html',
  styleUrls: ['./billboard.component.css']
})
export class BillboardComponent implements OnInit {

  billboardForm: FormGroup;

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

  }

  get atHome(){
    return this.apiServive.areUAtHome;
  }

}
