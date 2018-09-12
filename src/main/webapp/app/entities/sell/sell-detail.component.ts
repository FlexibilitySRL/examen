import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Sell } from './sell.model';
import { SellService } from './sell.service';

@Component({
    selector: 'jhi-sell-detail',
    templateUrl: './sell-detail.component.html'
})
export class SellDetailComponent implements OnInit, OnDestroy {

    sell: Sell;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private sellService: SellService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSells();
    }

    load(id) {
        this.sellService.find(id)
            .subscribe((sellResponse: HttpResponse<Sell>) => {
                this.sell = sellResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSells() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sellListModification',
            (response) => this.load(this.sell.id)
        );
    }
}
