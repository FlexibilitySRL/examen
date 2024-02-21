package ar.com.plug.examen.shared.logs;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class LogUtils {

    private LogUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, Object> getHeaders(HttpServletRequest request) {
        return Collections.list((request).getHeaderNames()).stream()
                .collect(Collectors.toMap(h -> h, (request)::getHeader));
    }

    public static String createDocId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isNullOrEmpty(String text) {
        return (text == null || text.isEmpty());
    }

    public static String getJsonMessage(String msg, String messagefield, String extrafields) {
        JSONObject jsonmsg = null;
        String response;
        try {
            jsonmsg = new JSONObject().put(messagefield, msg);

            response = addExtraFields(jsonmsg, extrafields).toString();
        } catch (Exception e) {
            response = null;
        }
        return response;
    }

    public static String getJsonMessage(String msg, String extrafields) {
        JSONObject jsonmsg = null;
        String response;
        try {
            jsonmsg = new JSONObject(msg);

            response = addExtraFields(jsonmsg, extrafields).toString();
        } catch (Exception e) {
            response = null;
        }
        return response;
    }

    private static JSONObject addExtraFields(JSONObject jsonmsg, String extrafields) {
        if (!isNullOrEmpty(extrafields)) {
            for (String field : extrafields.split(",")) {
                jsonmsg.put(field.split("=")[0], field.split("=")[1]);
            }
        }
        return jsonmsg;
    }

    public static LogDTO format(Map<String, Object> headers, Object body, Map<String, String[]> queryParameters) {
        LogDTO log = new LogDTO();
        log.setPayload(body);
        log.setQueryParams(queryParameters);
        log.setHeaders(headers);
        return log;
    }
}
