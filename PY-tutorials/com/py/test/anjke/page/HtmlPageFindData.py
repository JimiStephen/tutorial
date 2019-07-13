#!/usr/bin/python
# -*- coding: UTF-8 -*-

from HTMLParser import HTMLParser
from htmlentitydefs import name2codepoint
class MyHTMLParser(HTMLParser):
    def handle_starttag(self, tag, attrs):
        print "Start tag:", tag
        for attr in attrs:
            print "     attr:", attr

    def handle_endtag(self, tag):
        print "End tag  :", tag

    def handle_data(self, data):
        print "Data     :", data

    def handle_comment(self, data):
        print "Comment  :", data

    def handle_entityref(self, name):
        c = unichr(name2codepoint[name])
        print "Named ent:", c

    def handle_charref(self, name):
        if name.startswith('x'):
            c = unichr(int(name[1:], 16))
        else:
            c = unichr(int(name))
        print "Num ent  :", c

    def handle_decl(self, data):
        print "Decl     :", data

class HtmlPageFindData:

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


    def html2DataFind(self):
        htmlParser = MyHTMLParser();

        htmlParser.feed('<img src="python-logo.png" alt="The Python logo">');

        htmlParser.getpos();


        htmlParser.close();
        pass;


if __name__ == '__main__':
        htmlpagePage = HtmlPageFindData();

        htmlpagePage.fileCode = "UTF-8";
        htmlpagePage.fileName = "hellHtml.html";

        htmlpagePage.html2DataFind();



