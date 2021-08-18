package ar.com.plug.examen.utils;

public class ConstantUtil {
    //Paths
    public static final String PATH_SEPARATOR = "/";
    public static final String API = "api";
    public static final String API_VERSION = "v1";
    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String CREATE_SALE_TRX = "create";
    public static final String PRODUCTS = "products";
    public static final String CUSTOMERS = "customers";
    public static final String SELLERS = "sellers";
    public static final String SALE_TRX = "sales";
    public static final String SALE_ALL = "saleAll";
    public static final String AVAILABLE_PRODUCTS = "availableProducts";
    public static final String PAYMENT_MEAN = "paymentMean";
    public static final String CONFIRM_SALE = "confirmSale";

    //Constant Tax
    public static final String TAX = "0.21";

    //Exception message
    public static final String INSUFFICIENT_BALANCE = "Insufficient Balance";
    public static final String CREATE_SALE_ERROR = "Cannot addItemTrx in a sale not initialized. Please, create of sale";
    public static final String DISABLE_PRODUCTS = "Disable products for the sale";

    //Constans Payments Mean
    public static final String PAYMENT_MEAN_EFFECTIVE = "Effective";
}
