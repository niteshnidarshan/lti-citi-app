import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  PROFILE_URL: string = "http://localhost:8765/lti-citi-app-user/api/user/get-by-userId";
  constructor(private http: HttpClient) { }

  getUser(){
    return this.http.get(this.PROFILE_URL+"/"+sessionStorage.getItem("userId"));
  }
}
