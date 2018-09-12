import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Sell } from './sell.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Sell>;

@Injectable()
export class SellService {

    private resourceUrl =  SERVER_API_URL + 'api/sells';

    constructor(private http: HttpClient) { }

    create(sell: Sell): Observable<EntityResponseType> {
        const copy = this.convert(sell);
        return this.http.post<Sell>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(sell: Sell): Observable<EntityResponseType> {
        const copy = this.convert(sell);
        return this.http.put<Sell>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Sell>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Sell[]>> {
        const options = createRequestOption(req);
        return this.http.get<Sell[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Sell[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Sell = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Sell[]>): HttpResponse<Sell[]> {
        const jsonResponse: Sell[] = res.body;
        const body: Sell[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Sell.
     */
    private convertItemFromServer(sell: Sell): Sell {
        const copy: Sell = Object.assign({}, sell);
        return copy;
    }

    /**
     * Convert a Sell to a JSON which can be sent to the server.
     */
    private convert(sell: Sell): Sell {
        const copy: Sell = Object.assign({}, sell);
        return copy;
    }
}
