import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder, Validators} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { CustomUserService } from '../custom-user.service';
import { ICustomUser } from '../icustom-user';
import { BaseDetailsComponent, Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { AddressService } from 'src/app/entities/address/address.service';

@Component({
  selector: 'app-custom-user-details',
  templateUrl: './custom-user-details.component.html',
  styleUrls: ['./custom-user-details.component.scss']
})
export class CustomUserDetailsComponent extends BaseDetailsComponent<ICustomUser> implements OnInit {
	title = 'CustomUser';
	parentUrl = 'customuser';
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public global: Globals,
		public customUserService: CustomUserService,
		public pickerDialogService: PickerDialogService,
		public errorService: ErrorService,
		public addressService: AddressService,
	) {
		super(formBuilder, router, route, dialog, global, pickerDialogService, customUserService, errorService);
  }

	ngOnInit() {
		this.entityName = 'CustomUser';
		this.setAssociations();
		super.ngOnInit();
		this.setForm();
    	this.getItem();
    	this.setPickerSearchListener();
	}
  
  setForm(){
    this.itemForm = this.formBuilder.group({
      emailAdd: ['', Validators.required],
      enabled: [false, Validators.required],
      firstName: ['', Validators.required],
      isEmailcon: [false, Validators.required],
      lastName: ['', Validators.required],
      phone: [''],
      pwd: ['', Validators.required],
      uname: ['', Validators.required],
      version: ['', Validators.required],
      addressId: [''],
      addressDescriptiveField : [''],
      
    });
    
    this.fields = [
			
        {
		  name: 'emailAdd',
		  label: 'email Add',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'enabled',
		  label: 'enabled',
		  isRequired: true,
		  isAutoGenerated: false,
          type: 'boolean',
	    },
			
        {
		  name: 'firstName',
		  label: 'first Name',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'isEmailcon',
		  label: 'is Emailcon',
		  isRequired: true,
		  isAutoGenerated: false,
          type: 'boolean',
	    },
			
        {
		  name: 'lastName',
		  label: 'last Name',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'phone',
		  label: 'phone',
		  isRequired: false,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'pwd',
		  label: 'pwd',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'uname',
		  label: 'uname',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'version',
		  label: 'version',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'number',
	    },
      ];
      
  }
  
  onItemFetched(item: ICustomUser) {
    this.item = item;
    this.itemForm.patchValue(item);
  }
  
  setAssociations(){
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
		table: 'address',
		type: 'ManyToOne',
		label: 'address',
		service: this.addressService,
		descriptiveField: 'addressDescriptiveField',
	    referencedDescriptiveField: 'addressId',
		},
		];
		
		this.childAssociations = this.associations.filter(association => {
			return (association.isParent);
		});

		this.parentAssociations = this.associations.filter(association => {
			return (!association.isParent);
		});
	}
	
	onSubmit() {
		let customUser = this.itemForm.getRawValue();
		super.onSubmit(customUser);
		
	}
}