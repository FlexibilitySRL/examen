import { BaseEntity } from './../../shared';

export class Seller implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public lastname?: string,
        public username?: string,
        public createdAt?: any,
        public userLogin?: string,
        public userId?: number,
    ) {
    }
}
