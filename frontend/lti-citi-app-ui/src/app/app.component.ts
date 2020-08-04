import { Component } from '@angular/core';
import { AuthenticationService } from './services/security/authentication.service';
import { PingService } from './services/ping-service/ping.service';
import { interval } from 'rxjs';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'lti-citi-app-ui'; 
  constructor(public authService: AuthenticationService, private pingService: PingService){
    const secondsCounter = interval(900000); //every 15 minutes
    // Subscribe to begin publishing values
    secondsCounter.subscribe(
      (n) =>
        {
          this.pingService.getProfileUp().subscribe();
          this.pingService.getTransactionUp().subscribe();
          this.pingService.getAccountUp().subscribe(); 

          console.log(`It's been ${n} seconds since subscribing!`);
        }
      );
  }


}
