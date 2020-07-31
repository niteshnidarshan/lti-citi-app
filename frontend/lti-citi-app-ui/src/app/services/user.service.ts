import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginModel } from '../model/LoginModel';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  LOGIN_URL: string = "http://localhost:8765/authenticate";

  constructor(private http: HttpClient) { }

  doLogin(data: LoginModel){
    //return this.http.post(this.LOGIN_URL+"/login",data);
    return this.http.post(this.LOGIN_URL,data);
  }


}
