#!/usr/bin/python
# -*- coding: UTF-8 -*-
from page.SitePage import SitePage;
from page.req.PageRequest import PageRequest;
from parse.PageParse import PageParse;
import re;
import os;

class Main:
    def __init__(self):
        pass
    def subPageCrawl(self,pageStorePath,parentUrl,bankHrefArr,pageParsePath,parseAttr):



        for bankHref in bankHrefArr:
            bankPageRequest = PageRequest();
            bankPageRequest.pageData = {};
            temHref = bankHref;
            print temHref;
            if temHref.find("http") > -1:
                bankPageRequest.pageUrl = temHref;
            else:
                bankPageRequest.pageUrl = parentUrl + temHref;
                pass;

            subSitePage = SitePage();
            subSitePage.siteUrl = bankPageRequest.pageUrl;

            print "subSitePage.siteUrl {0}".format(subSitePage.siteUrl);
            subSitePage.nextUrlPath = "";
            subSitePage.pageRequest = bankPageRequest.getPageRequest();

            subSitePage.getPage2File();

            print "subSitePage.pageStorePath {0}".format(subSitePage.pageStorePath);

            subPageParse = PageParse();
            subPageParse.pageStorePath = subSitePage.pageStorePath;
            subPageParse.pageParsePath = pageParsePath;
            subPageParse.parseAttr = parseAttr;
            subParseRS = subPageParse.getParseResult();

            dataFile = open(subPageParse.pageStorePath+".data","w",1024);

            for subRs in subParseRS:
                dataFile.write(subRs+"\n");

            dataFile.close();


    def creawStart(self):
        pageRequest = PageRequest();
        pageRequest.pageUrl = "http://www.chakahao.com/";
        pageRequest.pageData = {};
        #
        # sitePage = SitePage();
        # sitePage.pageStorePath = "";
        # sitePage.siteUrl = pageRequest.pageUrl;
        # sitePage.nextUrlPath = "";
        # sitePage.pageRequest = pageRequest.getPageRequest();
        #
        # sitePage.getPage2File();

        pageParse = PageParse();
        pageParse.pageStorePath = "E:\www.chakahao.com\index.html";
        pageParse.pageParsePath = "div.cfrom div a";
        pageParse.parseAttr = "href";

        bankHrefArr = pageParse.getParseResult();
        htmlEndParttern = re.compile("(\.\w+)$");
        slashParttern = re.compile("\/$");

        parentUrl = pageRequest.pageUrl;
        searchRs = htmlEndParttern.search(parentUrl);
        if searchRs is not None:
            parentUrl = parentUrl.replace(searchRs.group(0),"");
            pass;
        searchRs = slashParttern.search(parentUrl);
        if searchRs is not None:
            pass;
        else:
            parentUrl = parentUrl + "/";

        pageStorePath = "E:/www.chakahao.com/";

        filePath = pageStorePath + "subhref.data";

        fileLines = open(filePath,"r");
        bankHrefArr = [];
        for line in fileLines:
            bankHrefArr.append(line.replace("\n",""));
        self.subPageCrawl(pageStorePath,parentUrl,bankHrefArr,pageParsePath="div[1] a",parseAttr="href");


if __name__ == '__main__':
    main = Main();
    main.creawStart();
    pass
