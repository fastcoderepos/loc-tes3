import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { ICustomUser } from '../icustom-user';
import { CustomUserService } from '../custom-user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomUserNewComponent } from '../new/custom-user-new.component';
import { BaseListComponent, Globals, listColumnType, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { AddressService } from 'src/app/entities/address/address.service';

@Component({
  selector: 'app-custom-user-list',
  templateUrl: './custom-user-list.component.html',
  styleUrls: ['./custom-user-list.component.scss']
})
export class CustomUserListComponent extends BaseListComponent<ICustomUser> implements OnInit {

	title = 'CustomUser';
	constructor(
		public router: Router,
		public route: ActivatedRoute,
		public global: Globals,
		public dialog: MatDialog,
		public changeDetectorRefs: ChangeDetectorRef,
		public pickerDialogService: PickerDialogService,
		public customUserService: CustomUserService,
		public errorService: ErrorService,
		public addressService: AddressService,
	) { 
		super(router, route, dialog, global, changeDetectorRefs, pickerDialogService, customUserService, errorService)
  }

	ngOnInit() {
		this.entityName = 'CustomUser';
		this.setAssociation();
		this.setColumns();
		this.primaryKeys = ['firstName', 'uname', ]
		super.ngOnInit();
	}
  
  
	setAssociation(){
  	
		this.associations = [
			{
				column: [
            {
					  	key: 'addressId',
					  	value: undefined,
					  	referencedkey: 'addressId'
					  },
					  
				],
				isParent: false,
				descriptiveField: 'addressDescriptiveField',
				referencedDescriptiveField: 'addressId',
				service: this.addressService,
				associatedObj: undefined,
				table: 'address',
				type: 'ManyToOne',
				url: 'customUsers',
				listColumn: 'address',
				label: 'address',

			},
		];
	}
  
  	setColumns(){
  		this.columns = [
    		{
				column: 'emailAdd',
				searchColumn: 'emailAdd',
				label: 'email Add',
				sort: true,
				filter: true,
				type: listColumnType.String
			},
    		{
				column: 'enabled',
				searchColumn: 'enabled',
				label: 'enabled',
				sort: true,
				filter: true,
				type: listColumnType.Boolean
			},
    		{
				column: 'firstName',
				searchColumn: 'firstName',
				label: 'first Name',
				sort: true,
				filter: true,
				type: listColumnType.String
			},
    		{
				column: 'isEmailcon',
				searchColumn: 'isEmailcon',
				label: 'is Emailcon',
				sort: true,
				filter: true,
				type: listColumnType.Boolean
			},
    		{
				column: 'lastName',
				searchColumn: 'lastName',
				label: 'last Name',
				sort: true,
				filter: true,
				type: listColumnType.String
			},
    		{
				column: 'phone',
				searchColumn: 'phone',
				label: 'phone',
				sort: true,
				filter: true,
				type: listColumnType.String
			},
    		{
				column: 'pwd',
				searchColumn: 'pwd',
				label: 'pwd',
				sort: true,
				filter: true,
				type: listColumnType.String
			},
    		{
				column: 'uname',
				searchColumn: 'uname',
				label: 'uname',
				sort: true,
				filter: true,
				type: listColumnType.String
			},
    		{
				column: 'version',
				searchColumn: 'version',
				label: 'version',
				sort: true,
				filter: true,
				type: listColumnType.Number
			},
			{
	  			column: 'addressDescriptiveField',
				searchColumn: 'address',
				label: 'address',
				sort: true,
				filter: true,
				type: listColumnType.String
	  		},
		  	{
				column: 'actions',
				label: 'Actions',
				sort: false,
				filter: false,
				type: listColumnType.String
			}
		];
		this.selectedColumns = this.columns;
		this.displayedColumns = this.columns.map((obj) => { return obj.column });
  	}
  addNew(comp) {
	if(!comp){
		comp = CustomUserNewComponent;
	}
	super.addNew(comp);
  }
  
}
