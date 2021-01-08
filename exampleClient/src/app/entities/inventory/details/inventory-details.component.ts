import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder, Validators} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { InventoryService } from '../inventory.service';
import { IInventory } from '../iinventory';
import { BaseDetailsComponent, Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { FilmService } from 'src/app/entities/film/film.service';

@Component({
  selector: 'app-inventory-details',
  templateUrl: './inventory-details.component.html',
  styleUrls: ['./inventory-details.component.scss']
})
export class InventoryDetailsComponent extends BaseDetailsComponent<IInventory> implements OnInit {
	title = 'Inventory';
	parentUrl = 'inventory';
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public global: Globals,
		public inventoryService: InventoryService,
		public pickerDialogService: PickerDialogService,
		public errorService: ErrorService,
		public filmService: FilmService,
	) {
		super(formBuilder, router, route, dialog, global, pickerDialogService, inventoryService, errorService);
  }

	ngOnInit() {
		this.entityName = 'Inventory';
		this.setAssociations();
		super.ngOnInit();
		this.setForm();
    	this.getItem();
    	this.setPickerSearchListener();
	}
  
  setForm(){
    this.itemForm = this.formBuilder.group({
      inventoryId: [{value: '', disabled: true}, Validators.required],
      lastUpdate: [''],
	  lastUpdateTime: [''],
      storeId: ['', Validators.required],
      filmId: ['', Validators.required],
      filmDescriptiveField : ['', Validators.required],
      
    });
    
    this.fields = [
			
			
        {
		  name: 'lastUpdate',
		  label: 'last Update',
		  isRequired: false,
		  isAutoGenerated: false,
		  type: 'date',
		},
		{
		  name: 'lastUpdateTime',
		  label: 'last Update Time',
		  isRequired: false,
		  isAutoGenerated: false,
		  type: 'time'
	    },
			
        {
		  name: 'storeId',
		  label: 'store Id',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'number',
	    },
      ];
      
  }
  
  onItemFetched(item: IInventory) {
    this.item = item;
    this.itemForm.patchValue(item);
    this.itemForm.get('lastUpdate').setValue(item.lastUpdate? new Date(item.lastUpdate): null);
    this.itemForm.get('lastUpdateTime').setValue(this.inventoryService.formatDateStringToAMPM(item.lastUpdate));
  }
  
  setAssociations(){
    this.associations = [
      {
        column: [
	        {
	          key: 'filmId',
	          value: undefined,
	          referencedkey: 'filmId'
			},
		],
		isParent: false,
		table: 'film',
		type: 'ManyToOne',
		label: 'film',
		service: this.filmService,
		descriptiveField: 'filmDescriptiveField',
	    referencedDescriptiveField: 'filmId',
		},
      {
        column: [
	        {
	          key: 'inventoryId',
	          value: undefined,
	          referencedkey: 'inventoryId'
			},
		],
		isParent: true,
		table: 'rental',
		type: 'OneToMany',
		label: 'rentals',
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
		let inventory = this.itemForm.getRawValue();
	    inventory.lastUpdate = this.dataService.combineDateAndTime(inventory.lastUpdate, inventory.lastUpdateTime);
	    delete inventory.lastUpdateTime;
		super.onSubmit(inventory);
		
	}
}
