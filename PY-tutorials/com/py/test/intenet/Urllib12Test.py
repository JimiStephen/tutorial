# -*- coding:UTF-8 -*-
import urllib;
import urllib2;

url = 'http://www.someserver.com/cgi-bin/register.cgi'
# 将user_agent写入头信息
user_agent ="Mozilla//4.0 (compatible; MSIE 5.5; Windows NT)"
values = {'name':'who','password':'123456'};
headers = {'User-Agent':user_agent};
data = urllib.urlencode(values);
req = urllib2.Request(url,data,headers);
reponse = urllib2.urlopen(req);
the_page = reponse.read();

for line in the_page:
    print line;
