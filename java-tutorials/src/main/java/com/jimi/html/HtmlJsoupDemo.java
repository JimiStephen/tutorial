package com.jimi.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class HtmlJsoupDemo {

    public static void main(String[] args) {
        try {
            Document document = Jsoup.parse(new File(ClassLoader.getSystemResource("./data/bookmarks_2020_3_4.html").getFile()), Charset.forName("UTF-8").name());

            Elements elements = document.select("a[href='http://www.ckplayer.com/']");


            if(elements != null){
                int size = elements.size();
                for (int i = 0; i < size; i++) {
                    Element element = elements.get(i);
                    element.remove();
                   // element.prepend("<a href=\"http://www.ckplayer.com/\" add_date=\"1402062669\" icon=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAADG0lEQVQ4jW1TXUxTBxT+zr23LYW2tL3hpwYLtCgvuozQaBxzpmQbYPZi48tCMn8SCT6aPZjFuDEIGb44htsg+8k2ZcsWpzHbnIn/jjA0EkMVEzRqVpLxM8uFQuH2/vSePbQjojtv5+T8fOec7wPWGjFA/zlcAwfXo5ABaTXWAWFNAV4wJn637HXIwX3wrn8Z3nInivwK0vNDGDk3QJ9OTTBABPALDW621ni2Oh/1ocizB4UuAEIu1b0OkCsAXZvHcrqTDl/vfR4BbTt0vOBByauDpxeOxBoz1wB5I2ArAExLR0axI/UUIDtQUgasZN6jY5M9yI3oEADwk8ArbYZbju0J9qr3ypsA0X4R8oYmI7J7Rza8ow2B2nG4ioDZhIalmaPcbtuyisDXM1pc6pbGlIwV9LiKhDrh767TA41dX3/PW9bVYnQnkTZztr+09PaxHwnLUWQXAMX4kL5ChwQAW9e7G5ZMrppWVRa1dPznzdHOSNuo/a3pxKCx5Ji+Ov7kYPmmUJzbsR8F3v3QJRdg/AHk31NXVljzZ9Jiw1LJZOEcomTuHkq4f324mEoiu00WtCvRb+92U3zzAD6m9589vAAAIa/d5nPaYJhZSERJMNO9SWBm2aDJZMq4kzTl+Ix+POC8/BrXw/Z7S42D87USAPyjGk89okWCKEEkfglEHBxK4JZCkuhw2sRUcnhxLn3U7Gu6nvv/o7UI7s9qI9A11QG2HHYxdvjS42DP9sr5lbRuWdOzny1+eeYNs6/5Gvv8LSzLR9hu/4CB7XniMQHArp8mTlX1T3Bl35i189T4aPuYXofPM6F8jk0Nhw9wILDEHg+zy8VcURHLrZCnkpnVu92ENxMrRmm82Fcf+e3MRb7Se8lobZ2SGhrqSNcbkU5b8HqB6uofojdu/MJEtMpEAFz50XCj4iwe9KQWAsNf7EVlZi7HRi0D+HxAOAxUV59HLPYONTcrz2mBCSB2XODabz7Z1/H2yNko/CUEXzEQiQCh0BQU5Tuoaj+dOKH9vxqZCUTMgIiTJwsgCCJaWvCX3y9UEaUJMAHgWTX+C6yDQ4C9SWV7AAAAAElFTkSuQmCC\">ckplayer-超酷网页视频播放器1</a>");
                }
            }

            document.body().append("<a href=\"http://www.ckplayer.com/\" add_date=\"1402062669\" icon=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAADG0lEQVQ4jW1TXUxTBxT+zr23LYW2tL3hpwYLtCgvuozQaBxzpmQbYPZi48tCMn8SCT6aPZjFuDEIGb44htsg+8k2ZcsWpzHbnIn/jjA0EkMVEzRqVpLxM8uFQuH2/vSePbQjojtv5+T8fOec7wPWGjFA/zlcAwfXo5ABaTXWAWFNAV4wJn637HXIwX3wrn8Z3nInivwK0vNDGDk3QJ9OTTBABPALDW621ni2Oh/1ocizB4UuAEIu1b0OkCsAXZvHcrqTDl/vfR4BbTt0vOBByauDpxeOxBoz1wB5I2ArAExLR0axI/UUIDtQUgasZN6jY5M9yI3oEADwk8ArbYZbju0J9qr3ypsA0X4R8oYmI7J7Rza8ow2B2nG4ioDZhIalmaPcbtuyisDXM1pc6pbGlIwV9LiKhDrh767TA41dX3/PW9bVYnQnkTZztr+09PaxHwnLUWQXAMX4kL5ChwQAW9e7G5ZMrppWVRa1dPznzdHOSNuo/a3pxKCx5Ji+Ov7kYPmmUJzbsR8F3v3QJRdg/AHk31NXVljzZ9Jiw1LJZOEcomTuHkq4f324mEoiu00WtCvRb+92U3zzAD6m9589vAAAIa/d5nPaYJhZSERJMNO9SWBm2aDJZMq4kzTl+Ix+POC8/BrXw/Z7S42D87USAPyjGk89okWCKEEkfglEHBxK4JZCkuhw2sRUcnhxLn3U7Gu6nvv/o7UI7s9qI9A11QG2HHYxdvjS42DP9sr5lbRuWdOzny1+eeYNs6/5Gvv8LSzLR9hu/4CB7XniMQHArp8mTlX1T3Bl35i189T4aPuYXofPM6F8jk0Nhw9wILDEHg+zy8VcURHLrZCnkpnVu92ENxMrRmm82Fcf+e3MRb7Se8lobZ2SGhrqSNcbkU5b8HqB6uofojdu/MJEtMpEAFz50XCj4iwe9KQWAsNf7EVlZi7HRi0D+HxAOAxUV59HLPYONTcrz2mBCSB2XODabz7Z1/H2yNko/CUEXzEQiQCh0BQU5Tuoaj+dOKH9vxqZCUTMgIiTJwsgCCJaWvCX3y9UEaUJMAHgWTX+C6yDQ4C9SWV7AAAAAElFTkSuQmCC\">ckplayer-超酷网页视频播放器1</a>");
            System.out.println(document.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
