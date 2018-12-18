import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';

import * as models from 'src/app/_models/user';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  public areUAtHome: boolean;
  private user: models.User = new models.User();
  private headers: HttpHeaders = new HttpHeaders();
  private body: HttpParams;
  private currentUserSubject: BehaviorSubject<models.User>;
  public currentUser: Observable<models.User>;

  constructor(private http: HttpClient, ) {
    this.currentUserSubject = new BehaviorSubject<models.User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
    this.areUAtHome = false;
  }

  loginService(userName: string, password: string):Observable<any>{
    this.body = new HttpParams().set('userName', userName).set('password', password);
    return this.http.post('http://localhost:8080/grupo23_war_exploded/user-controller/login', this.body, { observe: 'response' })
      .pipe(map(head => {
        if (head.headers.get('Authorization')) {
          this.user.email = userName;
          this.user.token = head.headers.get('Authorization');
          this.user.id = Number(head.headers.get('Authorization').split('-')[0]);
          localStorage.setItem('currentUser', JSON.stringify(this.user));
          this.currentUserSubject.next(this.user);
        }
        return this.user;
      }));
  }

  postBillboard(bill:models.BillboardUser){
    let billJson = JSON.stringify(bill);
    return this.http.post<any>('http://localhost:8080/grupo23_war_exploded/user-controller/create-billboard',billJson,this.getHeader());
  }

  getHeader(){
    let token = this.currentUserSubject.value.token;
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token });
    let options = { headers: this.headers };
    return options;
  }

  getBillboards(){
    return this.http.get<models.Billboard[]>('http://localhost:8080/grupo23_war_exploded/billboard-controller/get-billboards',this.getHeader())
  }

  get currentUserValue(): models.User{
    console.log(this.currentUserSubject.value)
    return this.currentUserSubject.value;
  }

  logoutService(){
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

}
