import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormArray } from '@angular/forms';
import { HttpClient, HttpParams} from '@angular/common/http';

//Service
import { ApiService } from 'src/app/_services/api.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  loginForm: FormGroup;

  private body = new HttpParams()
    .set('userName', 'julian@julian.com.ar')
    .set('password', 'julian1234');

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private apiServive: ApiService) { }

  ngOnInit() {
    
    this.loginForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    })

  }

  get formControl(){
    return this.loginForm.controls;
  }

  getUsers(): void {
    this.http.get('http://localhost:8080/grupo23_war_exploded/user-controller/users/1', {
      headers: { 'token': '1-12345' }
    })
      .subscribe(data => { console.log(data) });
  }

  login() {
    this.apiServive.loginService(this.formControl.userName.value,this.formControl.password.value)
      .subscribe(head => {console.log(JSON.stringify(head.headers.get('Authorization')))})  
  }

  onSubmit(){
    console.log(this.formControl.userName.value)
    console.log(this.formControl.password.value)
    this.login()
  }

}
