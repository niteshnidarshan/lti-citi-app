import { Component, OnInit, ViewChild } from '@angular/core';
import { TransactionService } from 'src/app/services/transaction.service';
import { Router } from '@angular/router';
import { AccountModel } from 'src/app/model/AccountModel';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ConfirmMessageDialogService } from 'src/app/services/dialog/confirm-message-dialog.service';
import { AccountService } from 'src/app/services/account.service';
import { MessageDialogService } from 'src/app/services/dialog/message-dialog.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  ELEMENT_DATA = new Array<AccountModel>();
  dataSource: any;
  user: any;
  account: any;

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  displayedColumns: string[] = [
    'accountId',
    'accountType', 
    'amount',
    'accountCreationTimeStamp',
    'accountLastModifiedTimeStamp',
    'operation'
  ];

  constructor(private transactionService: TransactionService, private accountService: AccountService, private router: Router, private confirmMessageDialog: ConfirmMessageDialogService, private messageDialog: MessageDialogService) {
    this.loadUser();
   }

  ngOnInit(): void { 
  }

  loadUser(){
    this.user = this.transactionService.getUser().subscribe(
      (success) => {
        this.user = success;
        
        this.loadData(); 
      },
      (err) => {
        alert(err.error.message);
      }
    );
  }

  openAccount(){
    this.router.navigate(['/addAccount']);
  }

  loadData(){ 
    this.accountService.getAccountsOfUser(this.user.userId).subscribe(
      (success)=>{
        this.account = success; 
        this.parseElement();
        this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);
        this.dataSource.sort = this.sort; 
        this.dataSource.paginator = this.paginator;
      },
      (err) => {
        alert("Error on load account data : "+err.error.message)
      }
    );
    
  }

  parseElement(){ 
    var i = 0; 
    this.ELEMENT_DATA = new Array<AccountModel>();
    for(let data of this.account){ 
      this.ELEMENT_DATA[i] = (new AccountModel(
        data.accountId,
        data.accountType,
        data.amount,
        data.associatedUserId,
        data.accountCreationTimeStamp, 
        data.accountLastModifiedTimeStamp 
      ));
        i++;
    }

  }

  deleteAccount(data){ 
    if(data.amount > 0){
      let options = {
        title: 'Delete Account',
        message1: "This account is having money [ $"+data.amount+" ].",
        message2: "Kindly transfer/consume money to delete account.",
        cancelText: 'Cancel',
        confirmText: 'Delete'
      };
  
      this.messageDialog.open(options) 
    }
    else{
      let options = {
        title: 'Delete Account',
        message1: "Account will be deleted permanently!",
        message2: "Do you really want to delete account?",
        cancelText: 'Cancel',
        confirmText: 'Delete'
      };

      this.confirmMessageDialog.open(options) 
      this.confirmMessageDialog.confirmed().subscribe(confirmed => { 
        if (confirmed) {  
            // Delete the record & refresh table list
            this.accountService.deleteAccount(data.accountId).subscribe(
              (success) => {
                //this.refreshList();
              },
              (err) => {
                let options = {
                  title: 'Delete Account',
                  message1: err.error.message,
                  message2: "",
                  cancelText: 'Cancel',
                  confirmText: 'Delete'
                };
            
                this.messageDialog.open(options) 
                this.refreshList();
              }
            );
            
      }
      }); 
    }
  }

  refreshList(){
    this.loadData(); 
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
