import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

import { TestingModule, EntryComponents } from 'src/testing/utils';
import { CustomUserExtendedService, CustomUserNewExtendedComponent } from '../';
import { ICustomUser } from 'src/app/entities/custom-user';
import { NewComponent, FieldsComp } from 'src/app/common/general-components';

describe('CustomUserNewExtendedComponent', () => {
  let component: CustomUserNewExtendedComponent;
  let fixture: ComponentFixture<CustomUserNewExtendedComponent>;
  
  let el: HTMLElement;

  describe('Unit tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [
          CustomUserNewExtendedComponent,
            NewComponent,
            FieldsComp
        ],
        imports: [TestingModule],
        providers: [
          CustomUserExtendedService,
          { provide: MAT_DIALOG_DATA, useValue: {} }
        ],
        schemas: [NO_ERRORS_SCHEMA] 
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(CustomUserNewExtendedComponent);
      component = fixture.componentInstance;
      spyOn(component, 'manageScreenResizing').and.returnValue();
      fixture.detectChanges();
    });

    
  })

  describe('Integration tests', () => {

    describe('', () => {
      beforeEach(async(() => {
        TestBed.configureTestingModule({
          declarations: [
            CustomUserNewExtendedComponent,
            NewComponent,
            FieldsComp
          ].concat(EntryComponents),
          imports: [TestingModule],
          providers: [
            CustomUserExtendedService,
            { provide: MAT_DIALOG_DATA, useValue: {} }
          ]
        });
      }));
  
      beforeEach(() => {
        fixture = TestBed.createComponent(CustomUserNewExtendedComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
      });

    })

  })
  
});