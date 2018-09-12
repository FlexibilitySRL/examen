import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Seller } from './seller.model';
import { SellerService } from './seller.service';

@Injectable()
export class SellerPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private sellerService: SellerService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.sellerService.find(id)
                    .subscribe((sellerResponse: HttpResponse<Seller>) => {
                        const seller: Seller = sellerResponse.body;
                        if (seller.createdAt) {
                            seller.createdAt = {
                                year: seller.createdAt.getFullYear(),
                                month: seller.createdAt.getMonth() + 1,
                                day: seller.createdAt.getDate()
                            };
                        }
                        this.ngbModalRef = this.sellerModalRef(component, seller);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.sellerModalRef(component, new Seller());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    sellerModalRef(component: Component, seller: Seller): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.seller = seller;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
