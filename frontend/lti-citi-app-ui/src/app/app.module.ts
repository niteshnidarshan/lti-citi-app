import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; 
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input'; 
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion'; 
import {MatDatepickerModule} from '@angular/material/datepicker'; 
import {MatFormFieldModule, MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';  
import {MatCardModule} from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import {MatTableModule} from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatTooltipModule} from '@angular/material/tooltip';
import { MatSelectModule  } from '@angular/material/select';
import {MatTabsModule} from '@angular/material/tabs'; 
import {MatBadgeModule} from '@angular/material/badge';  
import { MatProgressBarModule } from '@angular/material/progress-bar';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core'; 
import {MatRadioModule} from '@angular/material/radio';
import { AuthInterceptor } from './security/AuthInterceptor';
import { LoginComponent } from './components/login/login.component';
import { OpenAccountComponent } from './components/open-account/open-account.component';
import { TransferComponent } from './components/transfer/transfer.component';
import { LogoutComponent } from './components/logout/logout.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { MessageDialogComponent } from './components/dialog/message-dialog/message-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OpenAccountComponent,
    TransferComponent,
    LogoutComponent,
    TransactionComponent,
    MessageDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatDividerModule,
    MatExpansionModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatCardModule,
    FormsModule, ReactiveFormsModule,
    HttpClientModule ,
    MatDialogModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatTooltipModule,
    MatSelectModule,
    MatTabsModule,
    MatBadgeModule,
    MatProgressBarModule,
    MatNativeDateModule, 
    MatRippleModule,
    MatRadioModule
  ],
  providers: [
  { 
    provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, 
    useValue: { appearance: 'fill' } 
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  },],
  bootstrap: [AppComponent]
})
export class AppModule { }
