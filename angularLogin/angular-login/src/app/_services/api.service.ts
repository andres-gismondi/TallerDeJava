import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable, BehaviorSubject } from "rxjs";
import { map } from "rxjs/operators";

import * as models from "src/app/_models/user";

@Injectable({
  providedIn: "root"
})
export class ApiService {
  private users: models.User = new models.User();
  public areUAtHome: boolean;
  private user: models.User = new models.User();
  private headers: HttpHeaders = new HttpHeaders();
  private body: HttpParams;
  private currentUserSubject: BehaviorSubject<models.User>;
  public currentUser: Observable<models.User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<models.User>(
      JSON.parse(localStorage.getItem("currentUser"))
    );
    this.currentUser = this.currentUserSubject.asObservable();
    this.areUAtHome = false;
    this.getUsers();
  }

  loginService(userName: string, password: string): Observable<any> {
    this.body = new HttpParams()
      .set("userName", userName)
      .set("password", password);
    return this.http
      .post(
        "http://localhost:8080/grupo23_war_exploded/user-controller/login",
        this.body,
        { observe: "response" }
      )
      .pipe(
        map(head => {
          if (head.headers.get("Authorization")) {
            this.user.email = userName;
            this.user.token = head.headers.get("Authorization");
            this.user.id = Number(
              head.headers.get("Authorization").split("-")[0]
            );

            localStorage.setItem("currentUser", JSON.stringify(this.user));

            this.currentUserSubject.next(this.user);
          }
          return this.user;
        })
      );
  }

  postBillboard(bill: models.BillboardUser) {
    let billJson = JSON.stringify(bill);
    return this.http.post<any>(
      "http://localhost:8080/grupo23_war_exploded/user-controller/create-billboard",
      billJson,
      this.getHeader()
    );
  }

  setCategoriesToBillboard(cateBill: models.CategoriesBillboard) {
    let catBillJson = JSON.stringify(cateBill);
    return this.http.post<any>(
      "http://localhost:8080/grupo23_war_exploded/billboard-controller/set-categories",
      catBillJson,
      this.getHeader()
    );
  }

  getHeader() {
    let token = this.currentUserSubject.value.token;
    this.headers = new HttpHeaders({
      "Content-Type": "application/json",
      Authorization: token
    });
    let options = { headers: this.headers };
    return options;
  }

  getBillboards() {
    return this.http.get<models.Billboard[]>(
      "http://localhost:8080/grupo23_war_exploded/user-controller/get-billboards",
      this.getHeader()
    );
  }

  getCategories() {
    return this.http.get<models.Category[]>(
      "http://localhost:8080/grupo23_war_exploded/category-controller/get-categories",
      this.getHeader()
    );
  }

  get currentUserValue(): models.User {
    return this.currentUserSubject.value;
  }

  logoutService() {
    localStorage.removeItem("currentUser");
    this.currentUserSubject.next(null);
  }

  private getUsers(): void {
    this.http
      .get<any>(
        "http://localhost:8080/grupo23_war_exploded/user-controller/users/1",
        {
          headers: { Authorization: "1-12345" }
        }
      )
      .subscribe(data => {
        this.users = data;
      });
  }
}
