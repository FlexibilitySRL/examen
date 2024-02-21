package ar.com.plug.examen.shared.logs;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LogDTO {
    private String eventType;
    private String method;
    private Map<String, Object> headers;
    private Object payload;
    private Object queryParams;
    private Object pathParams;
    private String uri;
    

    public Map<String, Object> logData() {
        Map<String, Object> logData = new HashMap<>();
        logData.put("eventType", this.eventType);
        logData.put("header", this.headers);
        logData.put("payload", this.payload);
        logData.put("queryParameters", this.queryParams);
        logData.put("pathParameters", this.pathParams);
        logData.put("method", this.method);
        logData.put("uri", this.uri);
        return logData;

    }

    public String toJson() {
        return new Gson().toJson(this);
    }

}
