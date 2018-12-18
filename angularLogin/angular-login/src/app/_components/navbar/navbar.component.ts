import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/_services/api.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private apiServive: ApiService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    
  }

  logout(){
    this.apiServive.logoutService();
    this.router.navigate(['/login']);
  }

  get atHome(){
    return this.apiServive.areUAtHome;
  }

}
