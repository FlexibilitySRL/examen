import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Seller } from './seller.model';
import { SellerService } from './seller.service';

@Component({
    selector: 'jhi-seller-detail',
    templateUrl: './seller-detail.component.html'
})
export class SellerDetailComponent implements OnInit, OnDestroy {

    seller: Seller;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private sellerService: SellerService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSellers();
    }

    load(id) {
        this.sellerService.find(id)
            .subscribe((sellerResponse: HttpResponse<Seller>) => {
                this.seller = sellerResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSellers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sellerListModification',
            (response) => this.load(this.seller.id)
        );
    }
}
