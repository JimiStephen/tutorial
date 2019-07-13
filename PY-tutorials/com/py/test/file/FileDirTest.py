#!/usr/bin/python
# -*- coding: UTF-8 -*-

import os

#删除目录
os.rmdir("A")
# 创建目录A
os.mkdir("A",777);

# 进入到目录
os.chdir("A");
print "当前的目录是：",os.getcwd();