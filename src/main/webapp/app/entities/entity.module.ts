import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ExamenProductModule } from './product/product.module';
import { ExamenClientModule } from './client/client.module';
import { ExamenSellerModule } from './seller/seller.module';
import { ExamenSellModule } from './sell/sell.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ExamenProductModule,
        ExamenClientModule,
        ExamenSellerModule,
        ExamenSellModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExamenEntityModule {}
