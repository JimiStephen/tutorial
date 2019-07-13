package com.jimi.javase.sys;

import java.util.Enumeration;
import java.util.Properties;



public class SystemTutorials01 {

    /**
     *@description
     *@param args
     *@return
     *@creator xianyao.ye
     *@date 2019/1/2 10:50
     */
    public  static void main (String args[]){


            Properties  sysProperties = System.getProperties();
            Enumeration keys = sysProperties.keys();
            while (keys.hasMoreElements()){

                System.out.println(keys.nextElement());
            }

            System.out.println(sysProperties.getProperty("java.vm.info"));

    }



    public String testGetEmail(String a , String b, int c){


        return  "";
    }
}
