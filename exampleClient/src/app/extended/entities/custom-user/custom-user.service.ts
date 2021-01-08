
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CustomUserService } from 'src/app/entities/custom-user/custom-user.service';
@Injectable({
  providedIn: 'root'
})
export class CustomUserExtendedService extends CustomUserService {
	constructor(protected httpclient: HttpClient) { 
    super(httpclient);
    this.url += '/extended';
  }
}
