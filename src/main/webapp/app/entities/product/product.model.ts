import { BaseEntity } from './../../shared';

export class Product implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public createdAt?: any,
        public stock?: number,
    ) {
    }
}
