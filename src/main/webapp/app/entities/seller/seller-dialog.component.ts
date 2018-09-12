import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Seller } from './seller.model';
import { SellerPopupService } from './seller-popup.service';
import { SellerService } from './seller.service';
import { User, UserService } from '../../shared';

@Component({
    selector: 'jhi-seller-dialog',
    templateUrl: './seller-dialog.component.html'
})
export class SellerDialogComponent implements OnInit {

    seller: Seller;
    isSaving: boolean;

    users: User[];
    createdAtDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private sellerService: SellerService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: HttpResponse<User[]>) => { this.users = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.seller.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sellerService.update(this.seller));
        } else {
            this.subscribeToSaveResponse(
                this.sellerService.create(this.seller));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Seller>>) {
        result.subscribe((res: HttpResponse<Seller>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Seller) {
        this.eventManager.broadcast({ name: 'sellerListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-seller-popup',
    template: ''
})
export class SellerPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sellerPopupService: SellerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sellerPopupService
                    .open(SellerDialogComponent as Component, params['id']);
            } else {
                this.sellerPopupService
                    .open(SellerDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
