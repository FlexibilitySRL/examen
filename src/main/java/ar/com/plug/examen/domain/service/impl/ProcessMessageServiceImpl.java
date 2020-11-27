package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.ProcessMessageService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProcessMessageServiceImpl.class);

	
    @Override
    public Message processMessage(String message) {
    	
    	LOGGER.info("Processing message: " + message);
    	
        return new Message(message);
    }
}
