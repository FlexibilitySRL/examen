package ar.com.plug.examen.app.common;

public class RegexPatterns {

    public static final String REGEX_PHONE_NUMBER = "^([1-9][0-9]{1,7})$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9.!#$%&'*+\\=?^_`{|}~-]{0,30}@[a-zA-Z0-9.!#$%&'*+\\=?^_`{|}~-]{0,29}$";
    public static final String REGEX_STATUS = "^(APPROVED|CANCELED)$";

    private RegexPatterns() {

    }

}
