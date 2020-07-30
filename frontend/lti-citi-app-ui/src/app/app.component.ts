import { Component } from '@angular/core';
import { AuthenticationService } from './services/security/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'lti-citi-app-ui'; 
  constructor(public authService: AuthenticationService){}
}
