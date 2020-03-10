package com.jimi.javase.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/3/2 10:54
 */
public class UISwingSample  extends  JApplet {

    public void createShowMainUI(){

        JFrame mainFrame = new JFrame();

        Container contentPane =  new  Container();

        Component component = new JEditorPane();
        contentPane.add(component,BorderLayout.class);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(new JMenu("新建"));
        jMenuBar.add(new JMenu("打开"));
        jMenuBar.add(new JMenu("关于"));
        jMenuBar.add(new JMenu("退出"));
        jMenuBar.setOpaque(true);
        jMenuBar.setBackground(new Color(154, 165, 127));
        jMenuBar.setPreferredSize(new Dimension(200, 20));

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(contentPane);
        mainFrame.setJMenuBar(jMenuBar);

    }

}
