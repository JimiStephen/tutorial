package com.jimi.pattern.chainresponsibility;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/22 14:39
 */
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void writeLog(String message) {
        System.out.println("error console::logger:" + message);
    }
}
