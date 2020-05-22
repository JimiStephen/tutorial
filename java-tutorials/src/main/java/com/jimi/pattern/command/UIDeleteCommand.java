package com.jimi.pattern.command;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/21 9:38
 */
public class UIDeleteCommand extends Command {

    @Override
    public void execute() {
        super.uiGroup.find();
        super.uiGroup.delete();
        super.uiGroup.plan();
    }
}
