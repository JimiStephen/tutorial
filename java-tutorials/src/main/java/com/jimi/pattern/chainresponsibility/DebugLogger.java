package com.jimi.pattern.chainresponsibility;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/22 14:39
 */
public class DebugLogger extends AbstractLogger {
    public DebugLogger(int level) {
        this.level = level;
    }

    @Override
    protected void writeLog(String message) {
        System.out.println("DEBUG console::logger:" + message);
    }
}
