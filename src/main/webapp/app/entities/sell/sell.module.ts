import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExamenSharedModule } from '../../shared';
import {
    SellService,
    SellPopupService,
    SellComponent,
    SellDetailComponent,
    SellDialogComponent,
    SellPopupComponent,
    SellDeletePopupComponent,
    SellDeleteDialogComponent,
    sellRoute,
    sellPopupRoute,
} from './';

const ENTITY_STATES = [
    ...sellRoute,
    ...sellPopupRoute,
];

@NgModule({
    imports: [
        ExamenSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SellComponent,
        SellDetailComponent,
        SellDialogComponent,
        SellDeleteDialogComponent,
        SellPopupComponent,
        SellDeletePopupComponent,
    ],
    entryComponents: [
        SellComponent,
        SellDialogComponent,
        SellPopupComponent,
        SellDeleteDialogComponent,
        SellDeletePopupComponent,
    ],
    providers: [
        SellService,
        SellPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExamenSellModule {}
