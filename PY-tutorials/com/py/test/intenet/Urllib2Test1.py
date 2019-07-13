import urllib2

response = urllib2.urlopen("http://www.163.com");
html = response.read();
print html;