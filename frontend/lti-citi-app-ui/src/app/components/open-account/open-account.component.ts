import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { RegisterService } from 'src/app/services/register-service/register.service';
import { MessageDialogService } from 'src/app/services/dialog/message-dialog.service';
import { UserModel } from 'src/app/model/UserModel';
import { AccountModel } from 'src/app/model/AccountModel';

@Component({
  selector: 'app-open-account',
  templateUrl: './open-account.component.html',
  styleUrls: ['./open-account.component.css']
})
export class OpenAccountComponent implements OnInit {
 
  registerForm: FormGroup; 
  registrationGreetings: any; 
  user: any;
  account: any;

  constructor(private formBuilder: FormBuilder, private registerService: RegisterService, private dialogService: MessageDialogService) { 
    this.validateForm();
  }

  ngOnInit(): void {
  }

  validateForm(){
    this.registerForm = this.formBuilder.group({
      "firstName": new FormControl('',Validators.required),
      "lastName": new FormControl(), 
      "email": new FormControl('', [Validators.email, Validators.required]),
      "password": new FormControl('',[Validators.minLength(3), Validators.required]),
      "mobile": new FormControl('',[Validators.minLength(3), Validators.maxLength(10), Validators.required]),
      "location": new FormControl(),
      "accountType": new FormControl(),
      "initialValue": new FormControl()
    });
  }

  // code segments for password hide/show functionality starts
  hide:boolean = true;
  get emailInput() { 
    return this.registerForm.controls['email'].value;
  }

  get passwordInput() { 
    return this.registerForm.controls['password'].value; 
  } 
  // code segments for password hide/show functionality ends

  doRegister(){ 
    let userValues: UserModel = new UserModel(
        "", //initally userId should be null, will be generated by user API
        this.registerForm.controls['firstName'].value,
        this.registerForm.controls['lastName'].value,
        this.registerForm.controls['location'].value,
        this.registerForm.controls['email'].value,
        this.registerForm.controls['mobile'].value,
        this.registerForm.controls['password'].value,
        null, 
        "GENERAL",
        "ACTIVE",
        "", //create tstamp - will be generated by API
        ""  //update tstamp - will be generated by API
    ); 

    let accountValue: AccountModel = new AccountModel(
      "", //will be generated by server
      this.registerForm.controls['accountType'].value,
      this.registerForm.controls['initialValue'].value,
      "",
      "",
      ""
  ); 
    this.registerService.registerUser(userValues).subscribe(
      (success) => {
        //return message 
        this.user = success; 
        // proceed the user & account detail to create account with userId
        accountValue.associatedUserId = this.user.userId;

        this.registerService.createAccount(accountValue).subscribe(
          (success) => {
            this.account = success;

            this.registrationGreetings = "Welcome "+this.user.firstName+" "+this.user.lastName+"!";
      
            let options = {
              title: 'Open Account',
              message1: this.registrationGreetings,
              message2: "Account# : "+this.account.accountId,
              cancelText: 'Ok',
              confirmText: 'Ok'
            };
      
            this.dialogService.open(options);
            
            this.dialogService.confirmed().subscribe(confirmed => {
              if (confirmed) {
                  //alert("Jai Ram G ki");
                  //After clicking Ok on message dialog, form should reset
                  this.registerForm.reset();
                }
              });
          },
          (err) => {
            let options = {
              title: 'Open Account - error',
              message1: err.error.message,
              message2: "",
              cancelText: 'Ok',
              confirmText: 'Ok'
            };
       
            this.dialogService.open(options);
            
            this.dialogService.confirmed().subscribe(confirmed => {
              if (confirmed) {
                   //alert("Jai Ram G ki");
                   //After clicking Ok on message dialog, form should reset
                   this.registerForm.controls.email.reset();
                 }
               });
          }
        );
      },//end of success
      (err) => { 
        
        alert("error in user creation : "+err.error.message);
        
      }
    );
  }

}
