#!/usr/bin/python
# -*- coding: UTF-8 -*-

import os

fo = open("test1.txt","w");
fo.write("111");

fo.close();

#重命名文件test1.txt到test2.txt
os.rename("test1.txt","test2.txt");

# 删除一个已经存在的文件test2.txt
os.remove("test2.txt")