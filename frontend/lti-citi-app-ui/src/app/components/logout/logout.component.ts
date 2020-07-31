import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/security/authentication.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private auth: AuthenticationService) {
    this.auth.doLogOut();
  }

  ngOnInit(): void {
  }

}
