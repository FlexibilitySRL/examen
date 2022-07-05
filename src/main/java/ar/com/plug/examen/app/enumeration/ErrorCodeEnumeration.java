package ar.com.plug.examen.app.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnumeration {

    INTERNAL_ERROR("Error Code: 500", "500", "FAILED. The selected operation cannot be performed right now. Please retry later."),
    SERVICE_UNAVAILABLE("Error Code: 503", "503", "Try later.Processing request."),
    INVALID_FIELD("Error Code: 646", "400","Bad request. Please check fields and try again"),
    INVALID_PRODUCT("CG-650", "400","Invalid product code. Product code not found."),
    INVALID_DOCUMENT_NUMBER("Error Code: 633", "400","Invalid document number or not found."),
    INVALID_CLIENT("CG-601", "400", "Invalid document number. Client not found."),
    INVALID_SELLER("CG-602", "400", "Invalid seller id. Seller not found."),
    INVALID_RECEIPT("Error code: 603", "400","Invalid receipt id. Receipt id not found."),
    INVALID_STATUS("CG-604", "400","Invalid update. The receipt must have a status PENDING.");

    private String code;
    private String status;
    private String message;

}
