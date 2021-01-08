import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { Router, ActivatedRoute } from '@angular/router';

import { CustomUserExtendedService } from '../custom-user.service';
import { CustomUserNewExtendedComponent } from '../new/custom-user-new.component';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { AddressExtendedService } from 'src/app/extended/entities/address/address.service';
import { CustomUserListComponent } from 'src/app/entities/custom-user/index';

@Component({
  selector: 'app-custom-user-list',
  templateUrl: './custom-user-list.component.html',
  styleUrls: ['./custom-user-list.component.scss']
})
export class CustomUserListExtendedComponent extends CustomUserListComponent implements OnInit {

	title:string = "CustomUser";
	constructor(
		public router: Router,
		public route: ActivatedRoute,
		public global: Globals,
		public dialog: MatDialog,
		public changeDetectorRefs: ChangeDetectorRef,
		public pickerDialogService: PickerDialogService,
		public customUserService: CustomUserExtendedService,
		public errorService: ErrorService,
		public addressService: AddressExtendedService,
	) { 
		super(router, route, global, dialog, changeDetectorRefs, pickerDialogService, customUserService, errorService,
		addressService,
)
  }

	ngOnInit() {
		super.ngOnInit();
	}
  
	addNew() {
		super.addNew(CustomUserNewExtendedComponent);
	}
  
}
