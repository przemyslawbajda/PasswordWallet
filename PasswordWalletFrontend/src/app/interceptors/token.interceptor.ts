import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {AuthService} from "../services/auth.service";
import {Observable} from "rxjs";

@Injectable()
export class TokenInterceptor implements HttpInterceptor{

  constructor(private authService: AuthService ) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if(this.authService.isLoggedIn()){
      request = this.addToken(request, localStorage.getItem("JWT_TOKEN"));
    }

    return next.handle(request);

  }

  private addToken(request: HttpRequest<any>, token: String) {
    return request.clone({
      setHeaders: {
        'JwtToken': `${token}`,
      }
    });
  }
}
