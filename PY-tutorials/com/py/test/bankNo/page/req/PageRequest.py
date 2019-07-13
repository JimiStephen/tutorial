#!/usr/bin/python
# -*- coding: UTF-8 -*-
import urllib;
import urllib2;

class PageRequest:

    def __init__(self):
        self.pageUrl = "";
        self.pageData = {};
        self.pageCookies = "";
        self.pageMethod = "";


    @property
    def pageUrl(self):
        return self.pageUrl;

    @pageUrl.setter
    def pageUrl(self, value):
        self.pageUrl = value;

    @property
    def pageData(self):
        return self.pageData

    @pageData.setter
    def pageData(self, value):
        self.pageData = value;

    @property
    def pageCookies(self):
        return self.pageCookies;

    @pageCookies.setter
    def pageCookies(self, value):
        self.pageCookies = value;

    @property
    def pageMethod(self):
        return self.pageMethod;

    @pageMethod.setter
    def pageMethod(self, value):
        self.pageMethod = value;

    def getPageRequest(self):
        user_agent ="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.23 Safari/537.36";
        headers = {
                   'Host': 'www.chakahao.com',
                   'Referer': 'http://www.chakahao.com/cha_icbc.asp',
                   'Cookie': 'ASPSESSIONIDASCTCRTQ=JPPCAFAAAEGBPNIDLJLEFJJJ; Hm_lvt_8f5a9c5815fa16205b563bcb08e47516=1544581890; ASPSESSIONIDAARTRQDD=AFDANNKAHHFNMDJNHCIHGCNH; Hm_lpvt_8f5a9c5815fa16205b563bcb08e47516=1544691602',
                   'User-Agent':user_agent
                };
        data = urllib.urlencode(self.pageData);
        req = urllib2.Request(self.pageUrl,data,headers);
        return req;