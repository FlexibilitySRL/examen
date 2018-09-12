import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { SellComponent } from './sell.component';
import { SellDetailComponent } from './sell-detail.component';
import { SellPopupComponent } from './sell-dialog.component';
import { SellDeletePopupComponent } from './sell-delete-dialog.component';

export const sellRoute: Routes = [
    {
        path: 'sell',
        component: SellComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sells'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sell/:id',
        component: SellDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sells'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sellPopupRoute: Routes = [
    {
        path: 'sell-new',
        component: SellPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sells'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sell/:id/edit',
        component: SellPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sells'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sell/:id/delete',
        component: SellDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sells'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
