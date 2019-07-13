#!/usr/bin/python
# -*- coding: UTF-8 -*-

import urllib2;

reqImg = urllib2.Request("https://pages.anjukestatic.com/usersite/site/img/home/1/load.gif");

# reqImg.add_header("key","val");
# reqImg.add_data("data");
responseImg = urllib2.urlopen(reqImg);

imgData = responseImg.read();
f = file("load.gif","wb");
f.write(imgData);
f.close();
