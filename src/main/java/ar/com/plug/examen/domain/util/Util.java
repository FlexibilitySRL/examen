package ar.com.plug.examen.domain.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class Util {
    private Util() {
    }
    public static boolean isBlank(String value) {
        return StringUtils.isBlank(value);
    }
}
