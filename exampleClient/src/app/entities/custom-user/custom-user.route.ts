
import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from "@angular/core";
// import { CanDeactivateGuard } from 'src/app/common/shared';

// import { CustomUserDetailsComponent, CustomUserListComponent, CustomUserNewComponent } from './';

const routes: Routes = [
	// { path: '', component: CustomUserListComponent, canDeactivate: [CanDeactivateGuard] },
	// { path: ':id', component: CustomUserDetailsComponent, canDeactivate: [CanDeactivateGuard] },
	// { path: 'new', component: CustomUserNewComponent },
];

export const customUserRoute: ModuleWithProviders = RouterModule.forChild(routes);