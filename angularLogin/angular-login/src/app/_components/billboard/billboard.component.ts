import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/_services/api.service';
import { Router } from '@angular/router';

import * as models from 'src/app/_models/user';

@Component({
  selector: 'app-billboard',
  templateUrl: './billboard.component.html',
  styleUrls: ['./billboard.component.css']
})
export class BillboardComponent implements OnInit {

  billboardForm: FormGroup;
  billboardUser: models.BillboardUser;
  billboards: models.Billboard[] = [];
  categories: models.Category[] = [];
  categoriesToPost: models.Category[] = [];


  filtersLoader: Promise<boolean>;
  filtersLoader2: Promise<boolean>;

  constructor(private fb: FormBuilder,
    private apiServive: ApiService,
    private router: Router) {
    this.billboardForm = this.fb.group({
      title: ['', Validators.required],
      description: ['']
    })

    if (!this.apiServive.currentUserValue) {
      this.router.navigate(['/login']);
    }
    this.apiServive.areUAtHome = false;
  }

  ngOnInit() {
    this.getCategories();
  }

  onSubmit() {
    this.billboardUser = new models.BillboardUser();

    let bill = new models.Billboard();
    bill.title = this.billboardForm.controls['title'].value;
    bill.description = this.billboardForm.controls['description'].value;
    bill.date = new Date(Date.now());

    let user = new models.User();
    user.email = this.apiServive.currentUserValue.email;

    this.billboardUser.billboard = bill;
    this.billboardUser.user = user;

    this.apiServive.postBillboard(this.billboardUser).subscribe(resp => {
      this.router.navigate(['/home'])
    });
    if (this.setCategoriesToBillboard()) {
      let newCategories: models.Category[] = this.categoriesToPost;
      let billWithCategories: models.CategoriesBillboard = new models.CategoriesBillboard();
      billWithCategories.billboard = bill;
      billWithCategories.categories = newCategories;
      this.apiServive.setCategoriesToBillboard(billWithCategories).subscribe(resp => { this.router.navigate(['/home']) })
    }
  }

  private setCategoriesToBillboard() {
    if (this.postCategories.length > 0) {
      console.log("TRUE")
      return true;
    }
    return false;
  }

  getBillboards(): void {
    this.apiServive.getBillboards().subscribe((result: Array<models.Billboard>) => {
      this.billboards = result;
      this.filtersLoader = Promise.resolve(true);
    })
  }

  getCategories() {
    this.apiServive.getCategories().subscribe((result: Array<models.Category>) => {
      this.categories = result;
      this.filtersLoader2 = Promise.resolve(true);
    })
  }

  get allCategories() {
    return this.categories;
  }

  get atHome() {
    return this.apiServive.areUAtHome;
  }

  selectMe(com: models.Category, index: number) {
    this.categoriesToPost.push(com)
    this.categories.splice(index,1)
  }

  get postCategories() {
    return this.categoriesToPost;
  }

}
