package com.jimi.pattern.command;

/**
 *
 * 需求组
 * @author jimi
 * @version 1.0
 * @date 2020/5/21 9:28
 */
public class RequirementGroup extends Group {

    @Override
    public void find() {
        System.out.println("找到需求组");
    }

    @Override
    public void add() {
        System.out.println("客户要求添加一个新的功能需求。");

    }

    @Override
    public void delete() {
        System.out.println("客户要求删除一个功能需求。");

    }

    @Override
    public void change() {
        System.out.println("客户要求变更一个需求功能。。");

    }

    @Override
    public void plan() {
        System.out.println("根据客户要求需求组添加功能计划");
    }
}
