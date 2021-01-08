
import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from "@angular/core";
import { CanDeactivateGuard } from 'src/app/common/shared';
import { CustomUserDetailsExtendedComponent, CustomUserListExtendedComponent , CustomUserNewExtendedComponent } from './';

const routes: Routes = [
	{ path: '', component: CustomUserListExtendedComponent, canDeactivate: [CanDeactivateGuard] },
	{ path: ':id', component: CustomUserDetailsExtendedComponent, canDeactivate: [CanDeactivateGuard] },
	{ path: 'new', component: CustomUserNewExtendedComponent },
];

export const customUserRoute: ModuleWithProviders = RouterModule.forChild(routes);