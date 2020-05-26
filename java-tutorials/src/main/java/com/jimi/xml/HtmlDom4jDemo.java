package com.jimi.xml;

import org.dom4j.*;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;

import javax.sql.rowset.spi.XmlReader;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class HtmlDom4jDemo {

    public static void main(String[] args) {

        try {
            DocumentFactory documentFactory = DocumentFactory.getInstance();
            Element element = documentFactory.createElement("name");

            SAXReader reader = new SAXReader();
            String fileStr = ClassLoader.getSystemResource("data/bookmarks_2020_3_4.html").getFile();
            System.out.println(fileStr);
            File file = new File(fileStr);
            Document document =  reader.read(file);
            DOMReader domReader = new DOMReader();
            System.out.println(document.toString());
            Node node =  document.getDocument();
            node.selectNodes("/a/@href");
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }
}
