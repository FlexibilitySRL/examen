package ar.com.plug.examen.utils;

public class QueryUtils {
    public static final String QUERY_LAST_ID_ITEM="SELECT max(id_item) as last FROM ITEMS_LINES ORDER BY id_item DESC";
    public static final String QUERY_LAST_ID_SALE="SELECT max(id_sale) as last FROM SALE ORDER BY id_sale DESC";
    public static final String QUERY_COUNT_ROWS_SALE="SELECT count(id_sale) as count FROM SALE";
}
