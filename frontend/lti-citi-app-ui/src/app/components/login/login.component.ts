import { Component, OnInit } from '@angular/core';
import { Validators, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/security/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  mobile: FormControl;
  password: FormControl;
  loginFormGroup: FormGroup;
  user: any; 

  constructor(public formBuilder: FormBuilder, private authService: AuthenticationService) {
    this.mobile = new FormControl('', [Validators.required]);
    this.password = new FormControl('', Validators.required);
    this.loginFormGroup = formBuilder.group({ 
      "mobile": this.mobile,
      "password": this.password
    });
   }

  ngOnInit(): void {
  }

  // code segments for password hide/show functionality starts
  hide:boolean = true;
  get emailInput() { 
    return this.mobile.value;
  }

  get passwordInput() { 
    return this.password.value; 
  } 
// code segments for password hide/show functionality ends
 
  doLogin(){
    
    this.authService.doLogin(this.mobile.value, this.password.value);

  }

}
