package ar.com.plug.examen.config;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.h2.tools.Server.createWebServer;

@Component
public class H2Server
{
    private static  final Logger log = LoggerFactory.getLogger( H2Server.class );
    private Server webServer;

    @Value("${h2-server.port}")
    Integer h2ConsolePort;

    @EventListener( ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        log.debug("starting h2 console at port "+ h2ConsolePort);
        this.webServer = createWebServer("-webPort", h2ConsolePort.toString(),
                                         "-tcpAllowOthers").start();
        System.out.println(webServer.getURL());
    }

    @EventListener( ContextClosedEvent.class)
    public void stop() {
        log.debug("stopping h2 console at port "+h2ConsolePort);
        this.webServer.stop();
    }
}