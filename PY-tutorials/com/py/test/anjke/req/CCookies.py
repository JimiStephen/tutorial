#!/usr/bin/python
# -*- coding: UTF-8 -*-

class CCookies:
    '请求的cookie'

    def __init__(self):
        self.cookieMap = {};

    def addCookie(self,name,value):
        if(self.cookieMap.has_key(name)):
            self.cookieMap.update({name:value});
        else:
            self.cookieMap.update({name:value});

    def displayCookis(self):
        print self.cookieMap;

if __name__ == '__main__':
    ccookies = CCookies();
    ccookies.addCookie("a","b");
    ccookies.addCookie("b","b");
    ccookies.addCookie("a","e");
    ccookies.displayCookis();