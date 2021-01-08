import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ChangeDetectorRef, NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';

import { EntryComponents, TestingModule } from 'src/testing/utils';
import { CustomUserExtendedService, CustomUserDetailsExtendedComponent, CustomUserListExtendedComponent, CustomUserNewExtendedComponent } from '../';
import { ICustomUser } from 'src/app/entities/custom-user';
import { ListFiltersComponent, ServiceUtils } from 'src/app/common/shared';
import { ListComponent, DetailsComponent, NewComponent, FieldsComp } from 'src/app/common/general-components';

describe('CustomUserListExtendedComponent', () => {
  let fixture: ComponentFixture<CustomUserListExtendedComponent>;
  let component: CustomUserListExtendedComponent;
  let el: HTMLElement;

  describe('Unit tests', () => {
  
    beforeEach(async(() => {
      
      TestBed.configureTestingModule({
        declarations: [
          CustomUserListExtendedComponent,
          ListComponent
        ],
        imports: [TestingModule],
        providers: [
          CustomUserExtendedService,      
          ChangeDetectorRef,
        ],
        schemas: [NO_ERRORS_SCHEMA]   
      }).compileComponents();

    }));
    
    beforeEach(() => {
      fixture = TestBed.createComponent(CustomUserListExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
  });
  
  describe('Integration tests', () => {

    beforeEach(async(() => {

      TestBed.configureTestingModule({
        declarations: [
          CustomUserListExtendedComponent,
          CustomUserNewExtendedComponent,
          NewComponent,
          CustomUserDetailsExtendedComponent,
          ListComponent,
          DetailsComponent,
          FieldsComp
        ].concat(EntryComponents),
        imports: [
          TestingModule,
          RouterTestingModule.withRoutes([
            { path: 'customuser', component: CustomUserListExtendedComponent },
            { path: 'customuser/:id', component: CustomUserDetailsExtendedComponent }
          ])
        ],
        providers: [
          CustomUserExtendedService,
          ChangeDetectorRef,
        ]

      }).compileComponents();

    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(CustomUserListExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

    

  });
        
});
