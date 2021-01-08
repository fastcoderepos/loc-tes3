import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { CustomerExtendedService } from '../customer.service';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { AddressExtendedService } from 'src/app/extended/entities/address/address.service';

import { CustomerDetailsComponent } from 'src/app/entities/customer/index';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.scss']
})
export class CustomerDetailsExtendedComponent extends CustomerDetailsComponent implements OnInit {
	title:string='Customer';
	parentUrl:string='customer';
	//roles: IRole[];  
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public global: Globals,
		public customerExtendedService: CustomerExtendedService,
		public pickerDialogService: PickerDialogService,
		public errorService: ErrorService,
		public addressExtendedService: AddressExtendedService,
	) {
		super(formBuilder, router, route, dialog, global, customerExtendedService, pickerDialogService, errorService,
		addressExtendedService,
);
  }

	ngOnInit() {
		super.ngOnInit();
  }
  
}
