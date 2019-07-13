# -*- coding:UTF-8 -*-
import urllib;
import urllib2;

url = 'http://www.baidu.com';
values = {'wd':'D_in'};
data = urllib.urlencode(values);
print data
url2 = url + '?' +data;
response = urllib2.urlopen(url2);
the_page = response.read();
print the_page;
