import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { OpenAccountComponent } from './components/open-account/open-account.component';
import { TransferComponent } from './components/transfer/transfer.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { AddAccountComponent } from './components/add-account/add-account.component';


const routes: Routes = [
  {path:"login", component:LoginComponent},
  {path:"logout", component:LogoutComponent},
  {path:"register", component:OpenAccountComponent},
  {path:"transfer", component:TransferComponent},
  {path:"transaction", component:TransactionComponent},
  {path:"addAccount", component:AddAccountComponent},
  {path:'', redirectTo:'/login', pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
