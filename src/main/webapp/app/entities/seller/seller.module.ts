import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExamenSharedModule } from '../../shared';
import { ExamenAdminModule } from '../../admin/admin.module';
import {
    SellerService,
    SellerPopupService,
    SellerComponent,
    SellerDetailComponent,
    SellerDialogComponent,
    SellerPopupComponent,
    SellerDeletePopupComponent,
    SellerDeleteDialogComponent,
    sellerRoute,
    sellerPopupRoute,
} from './';

const ENTITY_STATES = [
    ...sellerRoute,
    ...sellerPopupRoute,
];

@NgModule({
    imports: [
        ExamenSharedModule,
        ExamenAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SellerComponent,
        SellerDetailComponent,
        SellerDialogComponent,
        SellerDeleteDialogComponent,
        SellerPopupComponent,
        SellerDeletePopupComponent,
    ],
    entryComponents: [
        SellerComponent,
        SellerDialogComponent,
        SellerPopupComponent,
        SellerDeleteDialogComponent,
        SellerDeletePopupComponent,
    ],
    providers: [
        SellerService,
        SellerPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExamenSellerModule {}
