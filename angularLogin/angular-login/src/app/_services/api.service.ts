import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  headers: HttpHeaders = new HttpHeaders();
  body: HttpParams;

  constructor(private http: HttpClient) { }

  loginService(userName:string, password:string):Observable<any>{
    this.body = new HttpParams().set('userName',userName).set('password',password);
    return  this.http.post<any>('http://localhost:8080/grupo23_war_exploded/user-controller/login',this.body, {observe:'response'})
  }

}
