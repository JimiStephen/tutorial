import urllib2

req = urllib2.Request("https://www.csdn.net/");
response = urllib2.urlopen(req);
html = response.read();
print html;