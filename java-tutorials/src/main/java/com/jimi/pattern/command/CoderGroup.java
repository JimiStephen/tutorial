package com.jimi.pattern.command;

/**
 * 码农组
 *
 * @author jimi
 * @version 1.0
 * @date 2020/5/21 9:28
 */
public class CoderGroup extends Group {

    @Override
    public void find() {
        System.out.println("找到程序猿组");
    }

    @Override
    public void add() {
        System.out.println("客户要求添加一个新的功能需求开发。");

    }

    @Override
    public void delete() {
        System.out.println("客户要求删除一个功能需求相关代码。");

    }

    @Override
    public void change() {
        System.out.println("客户要求变更一个需求功能业务逻辑代码。。");

    }

    @Override
    public void plan() {
        System.out.println("根据客户要求需求组添加代码变更计划");
    }
}
