package com.jimi.pattern.command;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/21 9:40
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void action(){
        this.command.execute();
    }
}
