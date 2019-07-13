#!/usr/bin/python
# -*- coding: UTF-8 -*-
import re;

stringUrl = "http://www.chakahao.com/index.html";
print stringUrl;

parttern = re.compile("(\.\w+)$|\/$");
searchRs = parttern.search(stringUrl);

print searchRs;

print searchRs.groups();
print searchRs.group(0);
print searchRs.group(1);

stringUrl = "cha_icbc.asp";
print stringUrl.find("cha");

