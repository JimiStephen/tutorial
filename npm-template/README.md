#  Getting Start

## 1.介绍
1.npm install和npm run需要分开执行

2.在npm install之前先执行 npm install --save node-sass替换工程内版本，目前使用了公司的npm本地库
npm install --save node-sass --registry=https://registry.npm.taobao.org --disturl=https://npm.taobao.org/dist --sass-binary-site=http://npm.taobao.org/mirrors/node-sass

如果先执行npm install后报错，可执行如下解决：

npm uninstall --save node-sass  
npm install --save node-sass  
4.之后再执行npm run dev


http://localhost/#/user/list visi pages;