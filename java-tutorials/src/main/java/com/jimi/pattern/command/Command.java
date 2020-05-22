package com.jimi.pattern.command;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/21 9:35
 */
public abstract class Command {

    protected RequirementGroup requirementGroup = new RequirementGroup();
    protected UIGroup uiGroup  = new UIGroup();
    protected CoderGroup coderGroup = new CoderGroup();


    public abstract void execute();
}
