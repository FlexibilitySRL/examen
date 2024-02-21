package ar.com.plug.examen.shared.logs.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.shared.logs.LogDTO;
import ar.com.plug.examen.shared.logs.service.LoggingService;

@Service
public class LoggingServiceImpl implements LoggingService {
    private static final String EMPTY = " ";
    private static final String COUNTERBAR = "\\\"";
    private static final Logger LOGGER = LoggerFactory.getLogger("STDOUT");
    private Logger loggerInit = null;
    private Logger loggerStats = null;
    private Logger loggerAudit = null;
    private Logger loggerTroubl = null;

    public enum Level {
        TRACE, DEBUG, INFO, WARN, ERROR
    }

    public void logData(LogDTO logData) {
        this.log(Level.INFO, logData.toJson().replace(COUNTERBAR, EMPTY));
    }

    public void log(Level level, String txt) {
        if (isLogEnabled() && level != null) {
            switch (level) {
                case TRACE:
                    loggerInit.trace(txt);
                    break;
                case DEBUG:
                    loggerInit.debug(txt);
                    break;
                case INFO:
                    loggerInit.info(txt);
                    break;
                case WARN:
                    loggerInit.warn(txt);
                    break;
                case ERROR:
                    loggerInit.error(txt);
                    break;
                default:
                    loggerInit.info(txt);
                    break;
            }
        }
    }

    public void stats(Level level, String txt) {
        if (isStatsEnabled() && level != null) {
            switch (level) {
                case TRACE:
                    loggerStats.trace(txt);
                    break;
                case DEBUG:
                    loggerStats.debug(txt);
                    break;
                case INFO:
                    loggerStats.info(txt);
                    break;
                case WARN:
                    loggerStats.warn(txt);
                    break;
                case ERROR:
                    loggerStats.error(txt);
                    break;
                default:
                    loggerStats.info(txt);
                    break;
            }
        }
    }

    public void audit(Level level, String txt) {
        if (isAuditEnabled() && level != null) {
            switch (level) {
                case TRACE:
                    loggerAudit.trace(txt);
                    break;
                case DEBUG:
                    loggerAudit.debug(txt);
                    break;
                case INFO:
                    loggerAudit.info(txt);
                    break;
                case WARN:
                    loggerAudit.warn(txt);
                    break;
                case ERROR:
                    loggerAudit.error(txt);
                    break;
                default:
                    loggerAudit.info(txt);
                    break;
            }
        }
    }

    public void troubl(Level level, String txt) {
        if (isTroublEnabled() && level != null) {
            switch (level) {
                case TRACE:
                    loggerTroubl.trace(txt);
                    break;
                case DEBUG:
                    loggerTroubl.debug(txt);
                    break;
                case INFO:
                    loggerTroubl.info(txt);
                    break;
                case WARN:
                    loggerTroubl.warn(txt);
                    break;
                case ERROR:
                    loggerTroubl.error(txt);
                    break;
                default:
                    loggerTroubl.info(txt);
                    break;
            }
        }
    }

    public boolean isLogEnabled() {
        return (loggerInit != null);
    }

    public boolean isStatsEnabled() {
        return (loggerStats != null);
    }

    public boolean isAuditEnabled() {
        return (loggerAudit != null);
    }

    public boolean isTroublEnabled() {
        return (loggerTroubl != null);
    }

    @PostConstruct
    public void init() {

        try {
            loggerInit = LoggerFactory.getLogger("logAppender");

            if (loggerInit != null) {
                LOGGER.info("Logger loaded: logAppender");
            }

        } catch (Exception e) {
            LOGGER.info("Logger logger error: {}", e.getMessage());
        }

        try {
            loggerStats = LoggerFactory.getLogger("logAppenderStats");

            if (loggerStats != null) {
                LOGGER.info("Logger loaded: logAppenderStats");
            }

        } catch (Exception e) {
            LOGGER.info("Logger logAppenderStats error: {}", e.getMessage());
        }

        try {
            loggerAudit = LoggerFactory.getLogger("logAppenderAudit");

            if (loggerAudit != null) {
                LOGGER.info("Logger loaded: logAppenderAudit");
            }

        } catch (Exception e) {
            LOGGER.info("Logger logAppenderAudit error: {}", e.getMessage());
        }

        try {
            loggerTroubl = LoggerFactory.getLogger("logAppenderTroubl");

            if (loggerTroubl != null) {
                LOGGER.info("Logger loaded: logAppenderTroubl");
            }

        } catch (Exception e) {
            LOGGER.info("Logger logAppenderTroubl error: {}", e.getMessage());
        }
    }
}
