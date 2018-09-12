import { BaseEntity } from './../../shared';

export class Sell implements BaseEntity {
    constructor(
        public id?: number,
        public productId?: number,
        public sellerId?: number,
        public clientId?: number,
    ) {
    }
}
