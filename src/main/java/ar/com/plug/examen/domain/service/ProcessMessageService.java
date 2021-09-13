package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Message;

public interface ProcessMessageService {

    Message processMessage(String string);
    
    public boolean init();
}
