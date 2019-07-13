#!/usr/bin/python
# -*- coding: UTF-8 -*-

pageStorePath = "E:/www.chakahao.com/";

filePath = pageStorePath + "subhref.data";

fileLines = open(filePath,"r");

for line in fileLines:
    print line;