import { Injectable } from '@angular/core';
import { UserService } from '../user.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginModel } from 'src/app/model/LoginModel';
import { MessageDialogService } from '../dialog/message-dialog.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  user: any;

  constructor(private loginService: UserService, private http: HttpClient, private router: Router, private dialogService: MessageDialogService) { }


  doLogin(mobile: string, password: string){
    let loginModel: LoginModel = new LoginModel(mobile, password);
    this.loginService.doLogin(loginModel).subscribe(
      (success) => {
        this.user = success;  
      
        sessionStorage.setItem("userId", this.user.userId);
        sessionStorage.setItem("userName", this.user.firstName+" "+this.user.lastName); 
        sessionStorage.setItem("userType", this.user.userType);
        sessionStorage.setItem("token", this.user.token);
 
         
        this.router.navigate(['/home']);
      },
      (err) => { 
        let options = {
          title: 'Login',
          message1: err.error.message,
          message2: "Kindly login with valid Mobile Id & Social Security Number.",
          cancelText: 'Ok',
          confirmText: 'Ok'
        };
   
        this.dialogService.open(options);
      }
    );
  }
  doLogOut(){
    sessionStorage.clear();
  }

  isUserLoggedIn(): boolean{
    //If userId exist, means user is logged in
    let token = sessionStorage.getItem("token");
     
    if(token == null){
      return false;
    }
    else{
      return true;
    } 
  }
  isUserAdmin(): boolean{
    let userType = sessionStorage.getItem("userType"); 
    if(userType == "ADMIN"){  
      return true;
    }
    else{
      return false;
    } 
  }
  isUserSuperUser(): boolean{
    let userType = sessionStorage.getItem("userType"); 
    if(userType == "SUPER_USER"){ 
      return true;
    }
    else{
      return false;
    } 
  }

  getToken() {
    return sessionStorage.getItem("token");
  }
}
