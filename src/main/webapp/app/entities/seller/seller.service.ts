import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Seller } from './seller.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Seller>;

@Injectable()
export class SellerService {

    private resourceUrl =  SERVER_API_URL + 'api/sellers';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(seller: Seller): Observable<EntityResponseType> {
        const copy = this.convert(seller);
        return this.http.post<Seller>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(seller: Seller): Observable<EntityResponseType> {
        const copy = this.convert(seller);
        return this.http.put<Seller>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Seller>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Seller[]>> {
        const options = createRequestOption(req);
        return this.http.get<Seller[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Seller[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Seller = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Seller[]>): HttpResponse<Seller[]> {
        const jsonResponse: Seller[] = res.body;
        const body: Seller[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Seller.
     */
    private convertItemFromServer(seller: Seller): Seller {
        const copy: Seller = Object.assign({}, seller);
        copy.createdAt = this.dateUtils
            .convertLocalDateFromServer(seller.createdAt);
        return copy;
    }

    /**
     * Convert a Seller to a JSON which can be sent to the server.
     */
    private convert(seller: Seller): Seller {
        const copy: Seller = Object.assign({}, seller);
        copy.createdAt = this.dateUtils
            .convertLocalDateToServer(seller.createdAt);
        return copy;
    }
}
