import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ApiService } from "src/app/_services/api.service";
import { Router } from "@angular/router";

import * as models from "src/app/_models/user";

@Component({
  selector: "app-billboard",
  templateUrl: "./billboard.component.html",
  styleUrls: ["./billboard.component.css"]
})
export class BillboardComponent implements OnInit {
  billboardForm: FormGroup;
  billboardUser: models.BillboardUser;
  billboards: models.Billboard[] = [];
  categories: models.Category[] = [];
  categoriesToPost: models.Category[] = [];

  filtersLoader: Promise<boolean>;
  filtersLoader2: Promise<boolean>;

  constructor(
    private fb: FormBuilder,
    private apiServive: ApiService,
    private router: Router
  ) {
    this.billboardForm = this.fb.group({
      title: ["", Validators.required],
      description: [""]
    });

    if (!this.apiServive.currentUserValue) {
      this.router.navigate(["/login"]);
    }
    this.apiServive.areUAtHome = false;
  }

  ngOnInit() {
    this.getCategories();
  }

  async onSubmit() {
    this.billboardUser = new models.BillboardUser();

    let bill = new models.Billboard();
    bill.title = this.billboardForm.controls["title"].value;
    bill.description = this.billboardForm.controls["description"].value;
    bill.date = new Date().toISOString().slice(0, 10);

    let user = new models.User();
    user.email = this.apiServive.currentUserValue.email;

    this.billboardUser.billboard = bill;
    this.billboardUser.user = user;

    await this.apiServive.postBillboard(this.billboardUser).subscribe(resp => {
      this.router.navigate(["/home"]);
    });
    if (await this.setCategoriesToBillboard()) {
      let newCategories: models.Category[] = this.categoriesToPost;
      let billWithCategories: models.CategoriesBillboard = new models.CategoriesBillboard();
      billWithCategories.billboard = bill;
      billWithCategories.categories = newCategories;
      await this.apiServive
        .setCategoriesToBillboard(billWithCategories)
        .subscribe(resp => {
          this.router.navigate(["/home"]);
        });
    }
  }

  private setCategoriesToBillboard() {
    if (this.postCategories.length > 0) {
      return true;
    }
    return false;
  }

  onCancel() {
    this.router.navigate(["/home"]);
  }

  getBillboards(): void {
    this.apiServive
      .getBillboards()
      .subscribe((result: Array<models.Billboard>) => {
        this.billboards = result;
        this.filtersLoader = Promise.resolve(true);
      });
  }

  getCategories() {
    this.apiServive
      .getCategories()
      .subscribe((result: Array<models.Category>) => {
        this.categories = result;
        this.filtersLoader2 = Promise.resolve(true);
      });
  }

  get allCategories() {
    return this.categories;
  }

  get atHome() {
    return this.apiServive.areUAtHome;
  }

  selectMe(com: models.Category, index: number) {
    console.log(com);
    console.log(index);
    this.categoriesToPost.push(com);
    this.categories.splice(index, 1);
  }

  unSelectMe(com: models.Category, index: number) {
    console.log(com);
    this.categories.push(com);
    this.categoriesToPost.splice(index, 1);
  }

  get postCategories() {
    return this.categoriesToPost;
  }
}
