import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { ConfirmMessageDialogComponent } from 'src/app/components/dialog/confirm-message-dialog/confirm-message-dialog.component';
import { Observable } from 'rxjs';
import { take, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ConfirmMessageDialogService {

  constructor(private dialog: MatDialog, private http: HttpClient) { }

  dialogRef: MatDialogRef<ConfirmMessageDialogComponent>;

  public open(options) {
    this.dialogRef = this.dialog.open(ConfirmMessageDialogComponent, {    
      data: {
        title: options.title,
        message1: options.message1,
        message2: options.message2,
        cancelText: options.cancelText,
        confirmText: options.confirmText 
      }
 });  
  }  
  public confirmed(): Observable<any> {
    return this.dialogRef.afterClosed().pipe(take(1), map(res => {
      return res;
    }
  ));
  }
}
