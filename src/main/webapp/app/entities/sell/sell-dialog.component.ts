import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Sell } from './sell.model';
import { SellPopupService } from './sell-popup.service';
import { SellService } from './sell.service';
import { Product, ProductService } from '../product';
import { Seller, SellerService } from '../seller';
import { Client, ClientService } from '../client';

@Component({
    selector: 'jhi-sell-dialog',
    templateUrl: './sell-dialog.component.html'
})
export class SellDialogComponent implements OnInit {

    sell: Sell;
    isSaving: boolean;

    products: Product[];

    sellers: Seller[];

    clients: Client[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private sellService: SellService,
        private productService: ProductService,
        private sellerService: SellerService,
        private clientService: ClientService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.productService.query()
            .subscribe((res: HttpResponse<Product[]>) => { this.products = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.sellerService.query()
            .subscribe((res: HttpResponse<Seller[]>) => { this.sellers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.clientService.query()
            .subscribe((res: HttpResponse<Client[]>) => { this.clients = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.sell.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sellService.update(this.sell));
        } else {
            this.subscribeToSaveResponse(
                this.sellService.create(this.sell));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Sell>>) {
        result.subscribe((res: HttpResponse<Sell>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Sell) {
        this.eventManager.broadcast({ name: 'sellListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProductById(index: number, item: Product) {
        return item.id;
    }

    trackSellerById(index: number, item: Seller) {
        return item.id;
    }

    trackClientById(index: number, item: Client) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-sell-popup',
    template: ''
})
export class SellPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sellPopupService: SellPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sellPopupService
                    .open(SellDialogComponent as Component, params['id']);
            } else {
                this.sellPopupService
                    .open(SellDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
