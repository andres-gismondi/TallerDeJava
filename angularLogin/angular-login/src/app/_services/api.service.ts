import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';

import * as models from 'src/app/_models/user';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private user: models.IUser = new models.IUser();
  //private headers: HttpHeaders = new HttpHeaders();
  private body: HttpParams;
  private currentUserSubject: BehaviorSubject<models.IUser>;
  public currentUser: Observable<models.IUser>;

  constructor(private http: HttpClient, ) {
    this.currentUserSubject = new BehaviorSubject<models.IUser>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  loginService(userName: string, password: string):Observable<any>{
    this.body = new HttpParams().set('userName', userName).set('password', password);
    return this.http.post('http://localhost:8080/grupo23_war_exploded/user-controller/login', this.body, { observe: 'response' })
      .pipe(map(head => {
        if (head.headers.get('Authorization')) {
          this.user.userName = userName;
          this.user.token = head.headers.get('Authorization');
          this.user.id = Number(head.headers.get('Authorization').split('-')[0]);
          localStorage.setItem('currentUser', JSON.stringify(this.user));
          this.currentUserSubject.next(this.user);
        }
        return this.user;
      }));
  }

  getBillboards(){
    let headers: HttpHeaders = new HttpHeaders();
    
    headers.set('Content-Type','application/json').set('Authorization',/*JSON.stringify(this.user.id)*/'1-12345')
    return this.http.get<models.Billboard[]>('http://localhost:8080/grupo23_war_exploded/billboard-controller/get-billboards',{
      headers: { 'Authorization': '1-12345' }
    })
  }

  get currentUserValue(): models.IUser{
    return this.currentUserSubject.value;
  }

  logoutService(){
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

}
