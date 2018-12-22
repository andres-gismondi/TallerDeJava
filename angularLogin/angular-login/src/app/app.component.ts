import { Component } from '@angular/core';
import { User } from './_models/user';
import { ApiService } from './_services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-login';

  private currentUser: User;

  constructor(private apiService: ApiService,
    private router: Router) {
    this.apiService.currentUser.subscribe(user => this.currentUser = user);
  }

  logout() {
    this.apiService.logoutService();
    this.router.navigate(['/login']);
  }

}
