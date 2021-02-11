import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';


@Injectable()
export class Jwt implements HttpInterceptor {
    constructor(
        private authenticationService: AuthenticationService,
      ) {}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const token = this.authenticationService.getToken();

/*         For any request that is not a login/register request, you need to provide a token from local storage.
        If a token exists put a token on the current request,
        else this is a login/register request and you do not need a token. */
        if (token) {
            const cloned = req.clone({
                headers: req.headers.set('Authorization', 'Bearer ' + token)
            });

            return next.handle(cloned);
        } else {
            return next.handle(req);
        }
    }

}