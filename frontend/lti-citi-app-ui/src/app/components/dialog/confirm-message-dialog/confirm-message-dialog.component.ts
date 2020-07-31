import { Component, OnInit, HostListener, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-message-dialog',
  templateUrl: './confirm-message-dialog.component.html',
  styleUrls: ['./confirm-message-dialog.component.css']
})
export class ConfirmMessageDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: {
    cancelText: string,
    confirmText: string,
    message1: string,
    message2: string,
    title: string 
  }, private mdDialogRef: MatDialogRef<ConfirmMessageDialogComponent>) { 
    
  } 

  ngOnInit(): void {
  }
 
  public cancel() {  
    this.close(false);
  }
  public close(value) {  
    this.mdDialogRef.close(value);
  }
  
  public confirm() {  
    this.close(true);
  }
  
@HostListener("keydown.esc")
  public onEsc() { 
    this.close(false);
  }

}
