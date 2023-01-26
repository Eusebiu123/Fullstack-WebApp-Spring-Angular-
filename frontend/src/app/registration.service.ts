import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  public response : any;
  postid: any;

  constructor(private _http : HttpClient) { }

  public loginUserFromRemote(user :User):Observable<number>{
       return this._http.post<any>("http://localhost:8080/api/v1/login",user);
         
  }
  public registerUserFromRemote(user :User):Observable<number>{
    return this._http.post<any>("http://localhost:8080/api/v1/register",user);
      
}

}
