package com.jimi.pattern.chainresponsibility;

/**
 *
 * 日志在不同的级别的输出，选择的级别越低就可以输出更多的信息；
 * @author jimi
 * @version 1.0
 * @date 2020/5/22 14:43
 */
public class TestChainResponsibility {

    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        AbstractLogger debugLogger = new DebugLogger(AbstractLogger.DEBUG);
        AbstractLogger warnningLogger = new WarnningLogger(AbstractLogger.WARNNING);
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);

        consoleLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(warnningLogger);
        warnningLogger.setNextLogger(errorLogger);

        return consoleLogger;
    }


    public static void main(String[] args) {

        AbstractLogger logger = getChainOfLoggers();

        logger.logMessage(AbstractLogger.INFO,"THIS IS A INFO LOGGER MESSAGE");
        logger.logMessage(AbstractLogger.DEBUG,"THIS IS A DEBUG LEVEL LOGGER MESSAGE");
        logger.logMessage(AbstractLogger.WARNNING,"THIS IS A WARNNING LEVEL LOGGER MESSAGE");



    }
}
