package com.jimi.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HtmlJsoupDemo {

    public static void main(String[] args) throws Exception {

        paraseBookMark();

    }

    private static void paraseBookMark() throws IOException {
        File fileA = Paths.get("java-tutorials/src/main/resources/data/bookmarks_2020_3_4.html").toFile();
        File fileB = Paths.get("java-tutorials/src/main/resources/data/bookmarks_2020_5_7_m.html").toFile();
        Document documentA = Jsoup.parse(fileA, Charset.forName("UTF-8").name());
        Document documentB = Jsoup.parse(fileB, Charset.forName("UTF-8").name());

        Elements elements = documentA.select("a[href]");
        List<String> exitsBookMarkName = new ArrayList<>();
        if (elements != null) {
            int size = elements.size();
            for (int i = 0; i < size; i++) {
                Element element = elements.get(i);

                String eleHref = element.attr("href");
                StringBuilder cssSelectorSb = new StringBuilder("");
                cssSelectorSb.append("a[href='");
                cssSelectorSb.append(eleHref);
                cssSelectorSb.append("']");
                Elements belements = documentB.select(cssSelectorSb.toString());
                if (belements.size() > 0) {
                    //链接在B文件存在；
                } else {

                   Elements hrefParentEle =  element.parent().parent().parent().select("h3:first-child");
                   String folderName = hrefParentEle.isEmpty()? "": hrefParentEle.get(0).text();
                   if(!exitsBookMarkName.contains(folderName) && !hrefParentEle.isEmpty()){
                        Elements praentEles = documentB.select("h3:contains("+folderName+")");

                        if(praentEles.isEmpty()){
                            Element folderEle = new Element("dt");
                            folderEle.appendChild(hrefParentEle.get(0));
                            folderEle.appendChild(new Element("dl").appendChild(new Element("p")).appendChild(new Element("dt").appendChild(element)));
                            documentB.body().appendChild(folderEle);
                        }else{
                            praentEles.get(0).parent().selectFirst("dl").appendChild(new Element("dt").appendChild(element));
                        }
                        exitsBookMarkName.add(folderName);
                   }else if(hrefParentEle.isEmpty()){
                       documentB.body().appendChild(element);
                   }

                }
            }
        }

        System.out.println(documentB.html());
        System.out.println(exitsBookMarkName);
        File file = Paths.get("java-tutorials/src/main/resources/data/bookmarks_m.html").toFile();
        wirteHtml2File(file,documentB);
    }

    private static void testDistrinctDocumentHref() throws Exception {
        File fileA = Paths.get("java-tutorials/src/main/resources/data/bookmarks_2020_3_4.html").toFile();
        File fileB = Paths.get("java-tutorials/src/main/resources/data/bookmarks_2020_5_7_m.html").toFile();
        distinctDoucmentHref(fileA);
        distinctDoucmentHref(fileB);
    }

    private static void distinctDoucmentHref(File file) throws Exception {
        Document document = Jsoup.parse(file, Charset.forName("UTF-8").name());
        Elements elements = document.select("a[href]");


        if (elements != null) {
            int size = elements.size();
            for (int i = 0; i < size; i++) {
                Element element = elements.get(i);

                String eleHref = element.attr("href");
                StringBuilder cssSelectorSb = new StringBuilder("");
                cssSelectorSb.append("a[href='");
                cssSelectorSb.append(eleHref);
                cssSelectorSb.append("']");
                Elements aelements = document.select(cssSelectorSb.toString());
                if (aelements.size() > 1) {
                    aelements.removeAll(aelements.subList(1, aelements.size()));
                }
            }
        }


        wirteHtml2File(file, document);
    }

    private static void wirteHtml2File(File fileA, Document document) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileA);
        FileChannel fileChannel = fos.getChannel();

        byte[] bytes = document.html().getBytes();


        ByteBuffer src = ByteBuffer.wrap(bytes);
        fileChannel.write(src);
        fos.flush();
        fos.close();

    }
}
