package com.jimi.pattern.command;

/**
 *
 * UI需求
 * @author jimi
 * @version 1.0
 * @date 2020/5/21 9:28
 */
public class UIGroup extends Group {

    @Override
    public void find() {
        System.out.println("找到UI设计组");
    }

    @Override
    public void add() {
        System.out.println("客户要求添加一个新的功能页面。");

    }

    @Override
    public void delete() {
        System.out.println("客户要求删除一个页面。");

    }

    @Override
    public void change() {
        System.out.println("客户要求变更一个变更页面。。");

    }

    @Override
    public void plan() {
        System.out.println("根据客户要求需求组添加功能页面计划");
    }
}
