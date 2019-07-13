#!/usr/bin/python
# -*- coding: UTF-8 -*-

from pyquery import PyQuery as pq;
import urllib;
import codecs;
#from lxml import etree;

# d = pq("<html></html>")#传入字符串
# d = pq(etree.fromstring("<html></html>"))#传入lxml
# d = pq(url='http://google.com/') #传入url

#传入文件
# doc = pq(open("hellHtml.html","r",1024).read());
doc = pq(codecs.open('hellHtml.html', 'r', encoding='utf-8').read());

# html()方法获取当前选中的 html 块
print doc.html()

# 相当于 class 选择器，选取 class 为 item-1 的 html 块
print doc('.item-1')
print "================================================"
print doc('#city-panel dd a');

print "================================================"
# 选取 <li> 元素
data = doc('#newhouse-scroll-ul li')

# 遍历 data 中的 <li> 元素
for li in data.items():
    # 选取第3个 <td> 元素中的文本块
    temp = li('a').attr("href");
    print li('a').attr("title");
    print li('a').text();
    try:
        print urllib.url2pathname(temp);
        print urllib.pathname2url(temp);
    except IOError as ioErr:
        print "=========执行urllib.url2pathname 或者urllib.pathname2url方法出错：========"
        print ioErr.message;
        print ioErr.__doc__;
        print "======================================================================="
    except BaseException as error:
        print "其它错误{0}，{1}".format(error.__str__(),error.message);
    print temp



