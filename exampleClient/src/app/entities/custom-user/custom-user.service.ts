
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ICustomUser } from './icustom-user';
import { GenericApiService } from 'src/app/common/shared';

@Injectable({
  providedIn: 'root'
})
export class CustomUserService extends GenericApiService<ICustomUser> { 
  constructor(protected httpclient: HttpClient) { 
    super(httpclient, "customUser");
  }
  
  
  
}
