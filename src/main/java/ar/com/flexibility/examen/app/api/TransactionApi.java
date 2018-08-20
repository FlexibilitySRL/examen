package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionApi implements GenericApi {

    private static final long serialVersionUID = 6952185374892307205L;
    @JsonProperty
    private Long id;

    @JsonProperty("transaction_id")
    private Long transactionId;

    @NotNull(message = "Date can not be null!")
    @JsonProperty("date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm")
    private Date dateCreated;

    @JsonProperty
    private Client client;

    @JsonProperty
    private Product product;

    @JsonProperty
    private BigDecimal amount;

    @JsonProperty
    private Boolean approved;

    private TransactionApi() {
        //Just to avoid public instantiation.
    }

    public TransactionApi(Long id, Long transactionId, Date dateCreated, Client client, Product product, BigDecimal amount, Boolean approved) {
        this.id = id;
        this.transactionId = transactionId;
        this.dateCreated = dateCreated;
        this.client = client;
        this.product = product;
        this.amount = amount;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Client getClient() {
        return client;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Boolean getApproved() {
        return approved;
    }

    public static TransactionApiBuilder newBuilder() {
        return new TransactionApiBuilder();
    }

    public static class TransactionApiBuilder {
        private Long id;
        private Long transactionId;
        private Date dateCreated;
        private Client client;
        private Product product;
        private BigDecimal amount;
        private Boolean approved;

        public TransactionApiBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public TransactionApiBuilder setTransactionId(Long transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public TransactionApiBuilder setDateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public TransactionApiBuilder setClient(Client client) {
            this.client = client;
            return this;
        }

        public TransactionApiBuilder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public TransactionApiBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public TransactionApiBuilder setApproved(Boolean approved) {
            this.approved = approved;
            return this;
        }

        public TransactionApi build() {
            return new TransactionApi(id, transactionId, dateCreated, client, product, amount, approved);
        }
    }
}
