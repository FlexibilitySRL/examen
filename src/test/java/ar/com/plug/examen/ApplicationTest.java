package ar.com.plug.examen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void starts_application_and_load_context(){
        assertDoesNotThrow(()-> Application.main(new String[] {}));
    }
}