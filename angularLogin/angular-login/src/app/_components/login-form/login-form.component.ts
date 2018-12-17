import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';

//Service
import { ApiService } from 'src/app/_services/api.service';
import { IUser } from 'src/app/_models/user';
import { Router, ActivatedRoute } from '@angular/router';

import * as models from 'src/app/_models/user';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  private loginForm: FormGroup;
  
  private user: models.IUser = new models.IUser();
  private returnUrl: string;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private apiServive: ApiService,
    private router: Router,
    private route: ActivatedRoute) {

    if (this.apiServive.currentUserValue) {
      this.router.navigate(['/']);
    }

  }

  ngOnInit() {
    
    this.getUsers()
    this.loginForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    })

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

  }

  get formControl() {
    return this.loginForm.controls;
  }

  getUsers(): void {
    this.http.get('http://localhost:8080/grupo23_war_exploded/user-controller/users/1', {
      headers: { 'Authorization': '1-12345' }
    })
      .subscribe(data => { console.log(data) });
  }

  

  onSubmit() {
    return this.apiServive.loginService(this.formControl.userName.value, this.formControl.password.value)
      .subscribe(user => {
        this.router.navigate([this.returnUrl])
      },
        error => {
          console.log(error);
        })
  }

}
