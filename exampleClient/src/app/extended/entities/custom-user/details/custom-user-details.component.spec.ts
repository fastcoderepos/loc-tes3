import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { DetailsComponent, ListComponent, FieldsComp } from 'src/app/common/general-components';

import { TestingModule, EntryComponents } from 'src/testing/utils';
import { CustomUserExtendedService, CustomUserDetailsExtendedComponent, CustomUserListExtendedComponent } from '../';
import { ICustomUser } from 'src/app/entities/custom-user';
describe('CustomUserDetailsExtendedComponent', () => {
  let component: CustomUserDetailsExtendedComponent;
  let fixture: ComponentFixture<CustomUserDetailsExtendedComponent>;
  let el: HTMLElement;
  
  describe('Unit Tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [
          CustomUserDetailsExtendedComponent,
          DetailsComponent
        ],
        imports: [TestingModule],
        providers: [
          CustomUserExtendedService,
        ],
        schemas: [NO_ERRORS_SCHEMA]  
      }).compileComponents();
    }));
  
    beforeEach(() => {
      fixture = TestBed.createComponent(CustomUserDetailsExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

  });
  
  describe('Integration Tests', () => {
    beforeEach(async(() => {

      TestBed.configureTestingModule({
        declarations: [
          CustomUserDetailsExtendedComponent,
          CustomUserListExtendedComponent,
          DetailsComponent,
          ListComponent,
          FieldsComp
        ].concat(EntryComponents),
        imports: [
          TestingModule,
          RouterTestingModule.withRoutes([
            { path: 'customuser', component: CustomUserDetailsExtendedComponent },
            { path: 'customuser/:id', component: CustomUserListExtendedComponent }
          ])
        ],
        providers: [
          CustomUserExtendedService
        ]

      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(CustomUserDetailsExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

  });
  
});
