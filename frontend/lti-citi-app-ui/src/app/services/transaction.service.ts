import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  PROFILE_URL: string = "http://localhost:8765/lti-citi-app-user/api/user/get-by-userId";
  TRANSACTION_URL: string = "http://localhost:8765/lti-citi-app-transaction/api/transaction/get-all";
  constructor(private http: HttpClient) { }

  getUser(){
    return this.http.get(this.PROFILE_URL+"/"+sessionStorage.getItem("userId"));
  }

  getAllTransaction(userId: string){
    return this.http.get(this.TRANSACTION_URL+"/"+userId);
  }
}
