# -*- coding:UTF-8 -*-
import urllib,urllib2;
import cookielib;

url = r'http://www.renren.com/ajaxLogin';
#创建一个cj的cookie的容器
cj = cookielib.CookieJar();
opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj));

#将要POST的数据进行编码
data = urllib.urlencode({'email':'aaaa@qq.com','password':'123456'});
res = opener.open(url,data);
print cj;
