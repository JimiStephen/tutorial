#!/usr/bin/python
# -*- coding: UTF-8 -*-

from pyquery import PyQuery as pq;
import urllib;
import codecs;
import traceback;

class PageParse:

    def __init__(self):
        self.pageStorePath = "";
        self.pageParsePath = "";
        self.dataResult = [];
        self.parseAttr = "";


    @property
    def pageStorePath(self):
        return self.pageStorePath;

    @pageStorePath.setter
    def pageStorePath(self, value):
        self.pageStorePath = value;

    @property
    def pageParsePath(self):
        return self.pageParsePath;

    @pageParsePath.setter
    def pageParsePath(self, value):
        self.pageParsePath = value;

    @property
    def parseAttr(self):
        return self.parseAttr;

    @parseAttr.setter
    def parseAttr(self, value):
        self.parseAttr = value;

    @property
    def dataResult(self):
        return self.dataResult;

    @dataResult.setter
    def dataResult(self, value):
        pass

    def getParseResult(self):

        try:
            # htmlParseDoc = pq(codecs.open(self.pageStorePath,"r","utf-8"));
            print "pageStorePath {0}".format(self.pageStorePath);
            htmlParseDoc = pq(codecs.open(self.pageStorePath).read());
            # with open(self.pageStorePath) as fp:
            #     htmlParseDoc = pq(fp.read());

            parseDoc = htmlParseDoc(self.pageParsePath);

            for item in parseDoc.items():
                itemVal = item.attr(self.parseAttr);
                print urllib.pathname2url(itemVal);
                self.dataResult.append(itemVal);

            print self.dataResult;
        except IOError,e:
            msg = traceback.format_exc();# 方式1
            print (msg);
            raise e;

        return self.dataResult;