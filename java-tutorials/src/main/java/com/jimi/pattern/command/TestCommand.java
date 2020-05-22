package com.jimi.pattern.command;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/21 9:41
 */
public class TestCommand {

    public static void main(String[] args) {
        Invoker lisi = new Invoker();

        Command command =  new RequireAddCommand();

        lisi.setCommand(command);

        lisi.action();
        Command command2 =  new UIDeleteCommand();

        lisi.setCommand(command2);

        lisi.action();
    }
}
