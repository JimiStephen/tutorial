package com.jimi.pattern.command;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/21 9:38
 */
public class RequireAddCommand extends Command {

    @Override
    public void execute() {
        super.requirementGroup.find();
        super.requirementGroup.add();
        super.requirementGroup.plan();
    }
}
