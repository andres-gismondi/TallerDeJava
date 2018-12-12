import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormArray } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

//Service
import { ApiService } from '../api.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor(
    private http:HttpClient,
    private fb:FormBuilder,
    private apiSerive: ApiService) { }

  ngOnInit() {
    //console.log(this.apiSerive.getUsers());
    this.getUsers();
  }

  getUsers():void{
    this.http.get('http://localhost:8080/grupo23_war_exploded/user-controller/users/2',{
      headers: {'token':'2-123456'}
    })
      .subscribe(data => { console.log(data) });
  }

}
