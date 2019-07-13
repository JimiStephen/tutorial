#!/usr/bin/python
# -*- coding: UTF-8 -*-
import urllib2;
from urllib2 import HTTPError,URLError;
import urllib;
import os;
from os import path;
import traceback;

class SitePage:

    def __init__(self):
        self.siteUrl = "";
        self.nextUrlPath = "";
        self.pageRequest = "";
        self.pageStorePath = "";

    @property
    def siteUrl(self):
        return self.siteUrl;

    @siteUrl.setter
    def siteUrl(self, value):
        self.siteUrl = value;


    @property
    def nextUrlPath(self):
        return self.nextUrlPath;

    @nextUrlPath.setter
    def nextUrlPath(self, value):
        self.nextUrlPath = value;

    @property
    def pageRequest(self):
        return self.pageRequest;

    @pageRequest.setter
    def pageRequest(self, value):
        self.pageRequest = value;

    @property
    def pageStorePath(self):
        return self.pageStorePath;

    @pageStorePath.setter
    def pageStorePath(self, value):
        self.pageStorePath = value;


    def getPage2File(self):
        inputStream = None;
        try:
            inputStream = urllib2.urlopen(self.pageRequest);
            ## S:\xm.fang.anjuke.com\tuangou\50789.html
            filePath = urllib.url2pathname(self.siteUrl);
            filePath = filePath.replace("S:","E:").replace("P:","E:");
            if(filePath.endswith("/")):
                os.mkdir(filePath);
                filePath = filePath + "\index.html";

            self.pageStorePath(filePath);
            if(path.exists(path.dirname(self.pageStorePath)) == False):
                os.mkdir(path.dirname(self.pageStorePath));

            print "wirte data into file {0}".format(self.pageStorePath);
            ajkHtmlFile = open(self.pageStorePath,"w",1024);

            if inputStream is not None:
                for line in inputStream:
                    ajkHtmlFile.write(line);
            inputStream.close();
        except TypeError, e:
            print traceback.format_exc();
            raise e;
        except URLError,e:
            print traceback.format_exc();
            raise e;
        except HTTPError,e:
            print traceback.format_exc();
            raise e;
        except StandardError,e:
            print traceback.format_exc();
            raise e;

        pass;