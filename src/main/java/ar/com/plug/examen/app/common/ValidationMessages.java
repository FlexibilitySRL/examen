package ar.com.plug.examen.app.common;

public class ValidationMessages {

    public static final String NOT_BLANK_NULL_NOR_EMPTY = " value must not be blank, null nor empty";
    public static final String SIZE_MIN_AND_MAX = " length must be between {min} and {max}";
    public static final String RANGE_MIN_AND_MAX = " value must be between {min} and {max}";
    public static final String DOCUMENT_NUMBER_VALID_VALUES = " value must be between {min} and {max}";
    public static final String EMAIL_VALID_FORMAT = "The maximum size of the user field is 30 characters, and the maximum size for the domain field is 29 characters";
    public static final String PHONE_NUMBER_VALID_VALUES = " value must be a number between 1 and 99999999";
    public static final String RECEIPT_ID_VALID_VALUES = " value must be a number between 1 and 999999999999";
    public static final String STATUS_VALID_VALUES = " valid values are: APPROVED or CANCELED";

    private ValidationMessages() {
    }

}
