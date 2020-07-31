import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TransactionModel } from '../model/TransactionModel';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  ACCOUNT_FETCH_URL: string = "http://localhost:8765/lti-citi-app-account/api/account/getAll";

  TRANSACTION_URL: string = "http://localhost:8765/lti-citi-app-transaction/api/transaction/transfer";

  constructor(private http: HttpClient) { }

  getAccountsOfUser(userId: string){
    return this.http.get(this.ACCOUNT_FETCH_URL+"/"+userId);
  }

  doTransfer(data: TransactionModel){ 
    return this.http.post(this.TRANSACTION_URL, data);
  }
}
