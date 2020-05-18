package com.jimi.pattern.prototype;

import com.jimi.javase.lang.StringUtils;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 9:55
 */
public class TestPrototype {

    private static int MAX_SEND_COUNT = 6;

    public static void main(String[] args) {

        int index = 0;

        Mail mail =  new Mail(new AdvTemplate());

        mail.setSigner("XXX  XX市 XX 区 XX 街道 电话：520520520");

        while (index < MAX_SEND_COUNT){
            mail.setAppellation(StringUtils.getRandString(5) + "先生/女士");
            mail.setReceiver(StringUtils.getRandString(5)+"@"+StringUtils.getRandString(3) +".com");

            sendMail(mail);
            index ++;
        }




    }

    private static void sendMail(Mail mail) {
        System.out.println(String.format("标题：%s \t 收件人： %s \t ----发送成功",mail.getSubject(),mail.getReceiver()));
    }
}
