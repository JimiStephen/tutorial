ref:https://webpack.js.org/guides/getting-started/

#### Pre-requisites 
> Before we begin, make sure you have a fresh version of Node.js installed. The current Long Term Support (LTS) release is an ideal starting point. You may run into a variety of issues with the older versions as they may be missing functionality webpack and/or its related packages require.

#### Local Installation 
>The latest webpack release is:
>
>GitHub release
>
>To install the latest release or a specific version, run one of the following commands:

``` 
npm install --save-dev webpack
npm install --save-dev webpack@<version>
``` 
> If you're using webpack v4 or later, you'll need to install the CLI.

```
npm install --save-dev webpack-cli
```
> Installing locally is what we recommend for most projects. This makes it easier to upgrade projects individually when breaking changes are introduced. Typically webpack is run via one or more npm scripts which will look for a webpack installation in your local node_modules directory:

```ecmascript 6
"scripts": {
    "start": "webpack --config webpack.config.js"
}

```
> To run the local installation of webpack you can access its bin version as node_modules/.bin/webpack .

#### execise

step one 
创建目录 t-webpack 进入到目录下面
执行 npm init -y
```html
D:\workspaces\tutorial\vue-tutorials\t-example\t-webpack>npm init -y
Wrote to D:\workspaces\tutorial\vue-tutorials\t-example\t-webpack\package.json:

{
  "name": "t-webpack",
  "version": "1.0.0",
  "description": "> Before we begin, make sure you have a fresh version of Node.js installed. The current Long Term Support (LTS) release is an ideal starting point. You may run into a variety of issues
with the older versions as they may be missing functionality webpack and/or its related packages require.",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC"
}



D:\workspaces\tutorial\vue-tutorials\t-example\t-webpack>npm install webpack webpack-cli --save-dev
npm notice created a lockfile as package-lock.json. You should commit this file.
npm WARN t-webpack@1.0.0 No repository field.
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.4 (node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.4: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})

+ webpack-cli@3.1.0
+ webpack@4.17.1
added 400 packages in 84.301s

```
创建文件
index.html

|-src
   |-index.js

修改package.json 文件，
删除： "main": "index.js",
添加： "private":true,
防止代码的发布
关于package.json解释
https://docs.npmjs.com/files/package.json

修改前src/index.js
```javascript
function component() {
    let element = document.createElement('div');

    // Lodash, currently included via a script, is required for this line to work
    element.innerHTML = _.join(['Hello', 'webpack'], ' ');

    return element;
}

document.body.appendChild(component());
```
修改后的src/index.js
```javascript

import _ from 'lodash';

function component() {
    let element = document.createElement('div');

    element.innerHTML = _.join(['Hello', 'webpack'], ' ');

    return element;
}

document.body.appendChild(component());
```


执行以下的打包命令会生成
 dist 文件夹 main.js

```html

D:\workspaces\tutorial\vue-tutorials\t-example\t-webpack>npx webpack
npx: installed 1 in 4.311s
Path must be a string. Received undefined
D:\workspaces\tutorial\vue-tutorials\t-example\t-webpack\node_modules\webpack\bin\webpack.js
Hash: 7f3f5f60bf72490b6c8a
Version: webpack 4.17.1
Time: 392ms
Built at: 2018-09-01 14:17:16
  Asset      Size  Chunks             Chunk Names
main.js  1.03 KiB       0  [emitted]  main
Entrypoint main = main.js
[0] ./src/index.js 286 bytes {0} [built]

WARNING in configuration
The 'mode' option has not been set, webpack will fallback to 'production' for this value. Set 'mode' option to 'development' or 'production' to enable defaults for each environment.
You can also set it to 'none' to disable any default behavior. Learn more: https://webpack.js.org/concepts/mode/

D:\workspaces\tutorial\vue-tutorials\t-example\t-webpack>

```
 所以我们在dist文件夹下面创建index.html文件如下
```html

<!doctype html>
<html>
<head>
    <title>Getting Started</title>
</head>
<body>
  <script src="main.js"></script>
</body>
</html>
```

####Modules  
> The import and export statements have been standardized in ES2015 and are supported in most browsers. Some older browsers still lag behind but webpack supports modules out of the box.

> Behind the scenes, webpack actually "transpiles" the code so that older browsers can also run it. If you inspect dist/main.js, you might be able to see how webpack does this, it's quite ingenious! Besides import and export, webpack supports various other module syntaxes as well, see Module API for more information.

####Using a Configuration 
> As of version 4, webpack doesn't require any configuration, but most projects will need a more complex setup, which is why webpack supports a configuration file.

webpack.config.js
```javascript
const path = require('path');

module.exports = {
  entry: './src/index.js',
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'dist')
  }
};
 
```
执行 npx webpack --config webpack.config.js 就可以从新的配置

####NPM Scripts 
> Given it's not particularly fun to run a local copy of webpack from the CLI, we can set up a little shortcut. Let's adjust our package.json by adding an npm script:

package.json
修改如下 ：
```javascript

"scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "build": "webpack"
  }
```
我们执行 npm run build 也可以进行打包。因为我们的本地安装webpack所需要的库。可以
用这个命令来代替 npx 命令。