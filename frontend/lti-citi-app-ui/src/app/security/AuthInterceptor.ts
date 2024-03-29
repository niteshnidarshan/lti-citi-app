import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { AuthenticationService } from '../services/security/authentication.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
    constructor(private authService: AuthenticationService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler) {
      /*  const authToken = this.authService.getToken();
        req = req.clone({
            setHeaders: {
                Authorization: "Bearer " + authToken
            }
        });
        return next.handle(req); */

        if(this.authService.isUserLoggedIn()){
            let token = this.authService.getToken();
            
            req = req.clone({
                setHeaders:{
                    Authorization: "Bearer " + token
                }
            });
        }
        return next.handle(req);
    }
}