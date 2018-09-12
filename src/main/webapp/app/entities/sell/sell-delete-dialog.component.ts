import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Sell } from './sell.model';
import { SellPopupService } from './sell-popup.service';
import { SellService } from './sell.service';

@Component({
    selector: 'jhi-sell-delete-dialog',
    templateUrl: './sell-delete-dialog.component.html'
})
export class SellDeleteDialogComponent {

    sell: Sell;

    constructor(
        private sellService: SellService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sellService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'sellListModification',
                content: 'Deleted an sell'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sell-delete-popup',
    template: ''
})
export class SellDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sellPopupService: SellPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.sellPopupService
                .open(SellDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
