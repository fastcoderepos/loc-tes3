import { NgModule } from '@angular/core';

import { CustomUserDetailsComponent, CustomUserListComponent, CustomUserNewComponent} from './';
import { customUserRoute } from './custom-user.route';

import { SharedModule } from 'src/app/common/shared';
import { GeneralComponentsModule } from 'src/app/common/general-components/general.module';

const entities = [
    CustomUserDetailsComponent, CustomUserListComponent,CustomUserNewComponent
  ]
@NgModule({
	declarations: entities,
	exports: entities,
  imports: [
    customUserRoute,
    SharedModule,
    GeneralComponentsModule,
  ]
})
export class CustomUserModule {
}
