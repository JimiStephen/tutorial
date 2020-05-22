package com.jimi.pattern.chainresponsibility;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/22 14:32
 */
public abstract class AbstractLogger {

    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;
    public static int WARNNING = 4;

    protected int level  ;

    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level,String message){
        if(this.level <= level){
            writeLog(message);
        }

        if(nextLogger != null){
            nextLogger.logMessage(level,message);
        }
    }

    abstract protected void writeLog(String message);
}
