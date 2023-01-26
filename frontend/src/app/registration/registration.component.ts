import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { User } from '../user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  user = new User();
  msg=''
  public ver : any;

  constructor(private _service : RegistrationService, private _router : Router){}

  ngOnInit(){

  }
  registerUser(){
    this._service.registerUserFromRemote(this.user).subscribe(res=>{
      console.log(res);
      this.ver=res;
      if(this.ver){
        console.log("Excelent");
        this._router.navigate(['/login']);
        this.msg="Registration successful!";
      }else{
        console.log("Este o eroare undeva!");
        this.msg = 'Bad credentials, please enter valid email and password!';
      }
    });
  }

}
