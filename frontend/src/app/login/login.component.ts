import { Component,OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  user = new User();
  msg='';
  public ver : any;
  constructor(private _service : RegistrationService,private _router : Router){}
  ngOnInit(): void {
   
  }

  loginUser(){
    this._service.loginUserFromRemote(this.user).subscribe(res=>{
      console.log(res);
      this.ver=res;
      if(this.ver){
        console.log("Excelent");
        this._router.navigate(['/home']);
      }else{
        console.log("Este o eroare undeva!");
        this.msg = 'Bad credentials, please enter valid email and password!';
      }
    });
  }
  gotoregistration()
  {
    this._router.navigate(['/registration'])
  }

}
