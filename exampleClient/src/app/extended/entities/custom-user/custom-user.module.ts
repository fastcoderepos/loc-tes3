import { NgModule } from '@angular/core';

import { CustomUserExtendedService, CustomUserDetailsExtendedComponent, CustomUserListExtendedComponent, CustomUserNewExtendedComponent } from './';
import { CustomUserService } from 'src/app/entities/custom-user';
import { CustomUserModule } from 'src/app/entities/custom-user/custom-user.module';
import { customUserRoute } from './custom-user.route';

import { SharedModule  } from 'src/app/common/shared';
import { GeneralComponentsExtendedModule } from 'src/app/common/general-components/extended/general-extended.module';

const entities = [
    CustomUserDetailsExtendedComponent, CustomUserListExtendedComponent, CustomUserNewExtendedComponent 
  ]
@NgModule({
	declarations: entities,
	exports: entities,
  imports: [
    customUserRoute,
    CustomUserModule,
    SharedModule,
    GeneralComponentsExtendedModule,
  ],
  providers: [{ provide: CustomUserService, useClass: CustomUserExtendedService }],
})
export class CustomUserExtendedModule {
}
