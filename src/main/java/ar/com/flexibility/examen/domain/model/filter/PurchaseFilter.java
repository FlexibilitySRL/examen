package ar.com.flexibility.examen.domain.model.filter;

public class PurchaseFilter {

    private long limit;

    public PurchaseFilter(long limit) {
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }
}
