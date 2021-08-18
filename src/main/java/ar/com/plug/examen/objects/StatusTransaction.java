package ar.com.plug.examen.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
public enum StatusTransaction {

    ACTIVE(1, "ACTIVE"),
    INACTIVE(2, "INACTIVE"),
    PENDING(3, "PENDING"),
    AVAILABLE(4, "AVAILABLE"),
    DELETE(5, "DELETE"),

    BAD_REQUEST(6, "400"),
    UNEXPECTED(7, "500"),
    NO_FOUND(8, "404");


    private static final long serialVersionUID = 1L;
    private final Integer id;
    private final String code;

    public static StatusTransaction fromId(Integer id){
        return Arrays.stream(values()).filter(statusTransaction -> Objects.equals(id, statusTransaction.id)).findAny().orElse(null);
    }

    public static StatusTransaction fromCode(String code){
        return Arrays.stream(values()).filter(statusTransaction -> Objects.equals(code, statusTransaction.code)).findAny().orElse(null);
    }
}
