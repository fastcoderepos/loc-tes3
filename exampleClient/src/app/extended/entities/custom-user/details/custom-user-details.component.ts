import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { CustomUserExtendedService } from '../custom-user.service';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { AddressExtendedService } from 'src/app/extended/entities/address/address.service';

import { CustomUserDetailsComponent } from 'src/app/entities/custom-user/index';

@Component({
  selector: 'app-custom-user-details',
  templateUrl: './custom-user-details.component.html',
  styleUrls: ['./custom-user-details.component.scss']
})
export class CustomUserDetailsExtendedComponent extends CustomUserDetailsComponent implements OnInit {
	title:string='CustomUser';
	parentUrl:string='customuser';
	//roles: IRole[];  
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public global: Globals,
		public customUserExtendedService: CustomUserExtendedService,
		public pickerDialogService: PickerDialogService,
		public errorService: ErrorService,
		public addressExtendedService: AddressExtendedService,
	) {
		super(formBuilder, router, route, dialog, global, customUserExtendedService, pickerDialogService, errorService,
		addressExtendedService,
);
  }

	ngOnInit() {
		super.ngOnInit();
  }
  
}
