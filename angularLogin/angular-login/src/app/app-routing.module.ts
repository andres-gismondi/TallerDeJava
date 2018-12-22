import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BillboardComponent } from 'src/app/_components/billboard/billboard.component';
import { LoginFormComponent } from 'src/app/_components/login-form/login-form.component';
import { HomeComponent } from './_components/home/home.component';

const routes: Routes = [
  { path: 'login', component: LoginFormComponent },
  { path: 'billboard', component: BillboardComponent },
  { path: '', component: HomeComponent },
  { path: '**', redirectTo: '' }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
