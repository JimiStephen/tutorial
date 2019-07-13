#!/usr/bin/python
# -*- coding: UTF-8 -*-

# 打开一个文件
fo = open("foo.txt","r+");
str = fo.read(10);

print "读取文件的字符串是：",str

#查找当前文件位置
position = fo.tell();
print "当前文件位置是：",position

#指针再次指向到文件头
position = fo.seek(0,0);
str = fo.read(10);
print "再次读取文件的内容是：",str

fo.close();