#!/usr/bin/python
# -*- coding: UTF-8 -*-

# 打开一个文件
fo = open("foo.txt","r+");

str = fo.read(10);
print "读取文件的内容是：",str


#关闭打开的文件
fo.close();