package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "message")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageApi {

    @JsonProperty
    private String message;
    
     @JsonProperty
    private String message2;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the message2
     */
    public String getMessage2() {
        return message2;
    }

    /**
     * @param message2 the message2 to set
     */
    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
