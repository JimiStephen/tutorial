#!/usr/bin/python
# -*- coding: UTF-8 -*-
import os;

filePath = "E:\\www.chakahao.com\\A\\index.html";

print os.path.basename(filePath);
print os.path.dirname(filePath);
print os.path.exists(os.path.dirname(filePath));