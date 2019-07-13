#!/usr/bin/python
# -*- coding: UTF-8 -*-


class CReqparms:

    def __init__(self):
        self.paramsMap = {};

    def addParam(self,name,value):
        if(self.paramsMap.has_key(name)):
            self.paramsMap.update({name:value});
        else:
            self.paramsMap.update({name:value});


    def getParam(self):
        return self.paramsMap;