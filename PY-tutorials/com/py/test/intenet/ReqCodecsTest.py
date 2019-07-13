#!/usr/bin/python
# -*- coding: UTF-8 -*-
import requests;
import codecs    #codecs可以很方便地对文本文件进行编码和解码
user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'
headers = {'User-Agent':user_agent}
#由于我的终端不支持汉语，下面链接中的汉字在复制的过程中已经被转码，不要紧
url = "https://baike.baidu.com/item/%E8%B4%BE%E8%B7%83%E4%BA%AD"
r = requests.get(url, headers = headers)
r.encoding = 'utf-8'    #使用utf-8对内容编码
f = codecs.open('./test.txt', 'w', encoding='utf-8')
f.write(r.text);
f.close();