import { Component, OnInit, ViewChild } from '@angular/core';
import { TransactionModel } from 'src/app/model/TransactionModel';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { TransactionService } from 'src/app/services/transaction.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-my-transaction',
  templateUrl: './my-transaction.component.html',
  styleUrls: ['./my-transaction.component.css']
})
export class MyTransactionComponent implements OnInit {

  ELEMENT_DATA = new Array<TransactionModel>();
  dataSource: any;
  transactions: any;

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  displayedColumns: string[] = [
    'transactionId',
    'transactionType',
    'senderAccountId',
    'receiversAccountId',
    'amount', 
    //'associatedUserId',
    'transactionStatus',
    'transactionTimeStamp'
  ]; 

  constructor(private transactionService: TransactionService) { 
    this.loadData();
  }

  ngOnInit(): void {
  }

  loadData(){ 
    this.transactionService.getAllTransaction(sessionStorage.getItem("userId")).subscribe(
      (result) => {
        this.transactions = result;  
        this.parseElement();
        this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);
        this.dataSource.sort = this.sort; 
        this.dataSource.paginator = this.paginator;
      },
      (err) => {
        alert(err.error.message);
      }
    );
  }

  parseElement(){ 
    var i = 0; 
    this.ELEMENT_DATA = new Array<TransactionModel>();
    for(let data of this.transactions){ 
      this.ELEMENT_DATA[i] = (new TransactionModel(
        data.transactionId,
        data.transactionType,
        data.senderAccountId,
        data.receiversAccountId,
        data.amount, 
        data.associatedUserId,
        data.transactionStatus,
        data.transactionTimeStamp
      ));
        i++;
    }

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
