import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel } from 'src/app/model/UserModel';
import { AccountModel } from 'src/app/model/AccountModel';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  USER_REGISTER_URL: string = "http://localhost:8765/register-user";

  ACCOUNT_REGISTER_URL: string = "http://localhost:8765/lti-citi-app-account/api/account/create";

  constructor(private http: HttpClient) { }

  registerUser(data: UserModel){
    return this.http.post(this.USER_REGISTER_URL, data);
  }

  createAccount(data: AccountModel){
    return this.http.post(this.ACCOUNT_REGISTER_URL, data);
  }
}
