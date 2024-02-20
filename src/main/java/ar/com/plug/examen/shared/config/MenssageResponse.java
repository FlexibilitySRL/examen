package ar.com.plug.examen.shared.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ms")
public class MenssageResponse {
    private Map<String, String> messages;
    public static final String SP = ";";
    public static final String OK = "0";
    public static final String BR400 = "400";
    public static final String C401 = "C401";
    public static final String C402 = "C402";
    public static final String C403 = "C403";
    public static final String C404 = "C404";
    public static final String C405 = "C405";

    public static final String P401 = "P401";
    public static final String P402 = "P402";
    public static final String P403 = "P403";
    public static final String P404 = "P404";
    public static final String P405 = "P405";
    public static final String P406 = "P406";
    public static final String P407 = "P407";
    public static final String P408 = "P408";

}
