package com.jimi.javase.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 叶宪耀(xianyao.ye @ ucarinc.com)
 * @Date Create date 2018/9/26 14:41
 * <p>类的说明</p>
 * @since 1.0.
 */
public class StringRegTest {
    public static void main (String args[]){


        String str = "123470979034";
        str = str.replaceAll("\\w{3}(.*)\\w{4}","$1");

        System.out.println("--------------------------------" + str);

        Pattern pattern = Pattern.compile("[\\w\\d]{3}(.*)[\\w\\d\\.]{4}");
        str = "macom";
        Matcher matcher = pattern.matcher(str);
        if(str.length() > 7 && matcher.matches()){

            System.out.println("------------"  + matcher.groupCount());
            String repStr = matcher.group(1);
            System.out.println(repStr);
            str = str.replace(repStr,"****");
            System.out.println("-------------"+ str);
        }else{
            StringBuilder sb = new StringBuilder("");
            sb.append(str.substring(0,3));
            sb.append("****");
            sb.append(str.substring(3));
            str  =  sb.toString();
            System.out.println("+++++++++"+str);
        }

        Pattern pattern1 = Pattern.compile("\\d+");
        matcher = pattern1.matcher("我们");
        System.out.println(matcher.matches());

        matcher = pattern1.matcher("23sd");
        System.out.println(matcher.matches());
        matcher = pattern1.matcher("809773");
        System.out.println(matcher.matches());
    }
}
