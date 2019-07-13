#!/usr/bin/python
# -*- coding: UTF-8 -*-
import json;
import demjson;
import urllib;
import io;
import urllib2;
import codecs;

class HtmlPageParsed:

    def __init__(self):
        self.fileCode = "utf-8";
        self.fileName = "";
        self.inputStream = file;

    @property
    def fileName(self):
        return self.fileName;

    @fileName.setter
    def fileName(self, value):
        self.fileName = value;
        pass

    @property
    def fileCode(self):
        return self.fileCode

    @fileCode.setter
    def fileCode(self, value):
        self.fileCode = value;
        pass


    def html2File(self):

        ajkHtmlFile = open(self.fileName,"w",1024);
        # ajkHtmlFile = io.open(self.fileName,"w",1024,"UTF-8");
        # ajkHtmlFile = codecs.open(self.fileName, "w", "utf-8-sig","strict",1024);

        for line in self.inputStream:
            ajkHtmlFile.write(line);
            # print line;

        self.inputStream.close();


if __name__ == '__main__':
        htmlpagePage = HtmlPageParsed();

        htmlpagePage.fileCode = "UTF-8";
        htmlpagePage.fileName = "hellHtml.html";
        anjukeUrl = "https://xm.anjuke.com";

        ajkRequest = urllib2.Request(anjukeUrl)

        ajkRequest.add_header("authority","xm.anjuke.com");
        ajkRequest.add_header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        # ajkRequest.add_header("accept-encoding","gzip, deflate, br");
        # ajkRequest.add_header("accept-language","zh-CN,zh;q=0.9;UTF-8");
        ajkRequest.add_header("upgrade-insecure-requests","1");
        ajkRequest.add_header("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.23 Safari/537.36");


        anjuke = urllib2.urlopen(ajkRequest);


        # anjuke = urllib.urlopen(anjukeUrl);
        htmlpagePage.inputStream = anjuke;

        htmlpagePage.html2File();

