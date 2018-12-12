import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  /*getUsers(){
    this.http.get('http://localhost:8080/grupo23_war_exploded/user-controller/users/2',{
      headers: {'token':'2-123456'}
    })
      .subscribe(data => { console.log(data) });
  }*/

}
