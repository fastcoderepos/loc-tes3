import { Component, OnInit, Inject } from '@angular/core';
import { CustomUserExtendedService } from '../custom-user.service';

import { ActivatedRoute,Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { AddressExtendedService } from 'src/app/extended/entities/address/address.service';

import { CustomUserNewComponent } from 'src/app/entities/custom-user/index';

@Component({
  selector: 'app-custom-user-new',
  templateUrl: './custom-user-new.component.html',
  styleUrls: ['./custom-user-new.component.scss']
})
export class CustomUserNewExtendedComponent extends CustomUserNewComponent implements OnInit {
  
    title:string = "New CustomUser";
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public dialogRef: MatDialogRef<CustomUserNewComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,
		public global: Globals,
		public pickerDialogService: PickerDialogService,
		public customUserExtendedService: CustomUserExtendedService,
		public errorService: ErrorService,
		public addressExtendedService: AddressExtendedService,
	) {
		super(formBuilder, router, route, dialog, dialogRef, data, global, pickerDialogService, customUserExtendedService, errorService,
		addressExtendedService,
		);
	}
 
	ngOnInit() {
		super.ngOnInit();
  }
     
}
