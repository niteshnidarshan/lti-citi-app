import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PingService {

  /**
   * As Heroku server sleeps in every 30 minutes. 
   * So that API goes down.
   * To resolve this need to warm every microservices in withing 30 minutes.
   */

  ACCOUNT_URL: string = "https://lti-citi-ag.herokuapp.com/lti-citi-app-account/api/account/message";
 
  TRANSACTION_URL: string = "https://lti-citi-ag.herokuapp.com/lti-citi-app-transaction/api/transaction/message";

  PROFILE_URL: string = "https://lti-citi-ag.herokuapp.com/lti-citi-app-user/api/user/message";

  constructor(private http: HttpClient) { }

  getProfileUp(){
    return this.http.get(this.PROFILE_URL);
  }

  getTransactionUp(){
    return this.http.get(this.TRANSACTION_URL);
  }

  getAccountUp(){
    return this.http.get(this.ACCOUNT_URL);
  }

}
