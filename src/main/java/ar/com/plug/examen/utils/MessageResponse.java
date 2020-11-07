package ar.com.plug.examen.utils;

import org.springframework.http.ResponseEntity;

/**
 * Provides error message when service fail
 *
 * @author Camilo Villate
 */

public class MessageResponse {
    String messsage;

    public MessageResponse(String messsage) {
        this.messsage = messsage;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    /**
     * Retrieve response according to param
     *
     * @param result - int value for result 1 its ok 0 it bad
     * @return - response type o ResponseEntity
     */
    public static ResponseEntity<?> getIntegerResponseEntity(int result) {
        if (result == 1) {
            return ResponseEntity.ok().body(new MessageResponse("Proceso correcto"));
        }
        return ResponseEntity.badRequest().build();
    }

}