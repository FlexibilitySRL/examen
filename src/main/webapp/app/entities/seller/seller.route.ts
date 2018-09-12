import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { SellerComponent } from './seller.component';
import { SellerDetailComponent } from './seller-detail.component';
import { SellerPopupComponent } from './seller-dialog.component';
import { SellerDeletePopupComponent } from './seller-delete-dialog.component';

export const sellerRoute: Routes = [
    {
        path: 'seller',
        component: SellerComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sellers'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'seller/:id',
        component: SellerDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sellers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sellerPopupRoute: Routes = [
    {
        path: 'seller-new',
        component: SellerPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sellers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'seller/:id/edit',
        component: SellerPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sellers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'seller/:id/delete',
        component: SellerDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sellers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
