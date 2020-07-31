import { Component, OnInit } from '@angular/core';
import { TransactionService } from 'src/app/services/transaction.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  user: any;

  constructor(private transactionService: TransactionService, private router: Router) {
    this.loadUser();
   }

  ngOnInit(): void {
  }

  loadUser(){
    this.user = this.transactionService.getUser().subscribe(
      (success) => {
        this.user = success; 
      },
      (err) => {
        alert(err.error.message);
      }
    );
  }

  openAccount(){
    this.router.navigate(['/addAccount']);
  }

}
