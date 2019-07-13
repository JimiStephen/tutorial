First, let's adjust our project a little bit:

project
```html

  webpack-demo
  |- package.json
  |- webpack.config.js
  |- /dist
  |- /src
    |- index.js
+   |- print.js
  |- /node_modules
```
Let's add some logic to our src/print.js file:

src/print.js
```js

export default function printMe() {
  console.log('I get called from print.js!');
}
```
And use that function in our src/index.js file:

src/index.js
```js

  import _ from 'lodash';
+ import printMe from './print.js';

  function component() {
    var element = document.createElement('div');
+   var btn = document.createElement('button');

    element.innerHTML = _.join(['Hello', 'webpack'], ' ');

+   btn.innerHTML = 'Click me and check the console!';
+   btn.onclick = printMe;
+
+   element.appendChild(btn);

    return element;
  }

  document.body.appendChild(component());
```
Let's also update our dist/index.html file, in preparation for webpack to split out entries:

dist/index.html
```html

  <!doctype html>
  <html>
    <head>
-     <title>Asset Management</title>
+     <title>Output Management</title>
+     <script src="./print.bundle.js"></script>
    </head>
    <body>
-     <script src="./bundle.js"></script>
+     <script src="./app.bundle.js"></script>
    </body>
  </html>
```

webpack.config.js
```js

  const path = require('path');

  module.exports = {
-   entry: './src/index.js',
+   entry: {
+     app: './src/index.js',
+     print: './src/print.js'
+   },
    output: {
-     filename: 'bundle.js',
+     filename: '[name].bundle.js',
      path: path.resolve(__dirname, 'dist')
    }
  };
```

####Setting up HtmlWebpackPlugin 
First install the plugin and adjust the webpack.config.js file:
```commandline

npm install --save-dev html-webpack-plugin
```

webpack.config.js
```js

  const path = require('path');
+ const HtmlWebpackPlugin = require('html-webpack-plugin');

  module.exports = {
    entry: {
      app: './src/index.js',
      print: './src/print.js'
    },
+   plugins: [
+     new HtmlWebpackPlugin({
+       title: 'Output Management'
+     })
+   ],
    output: {
      filename: '[name].bundle.js',
      path: path.resolve(__dirname, 'dist')
    }
  };
```

####Cleaning up the /dist folder 
As you might have noticed over the past guides and code example, our /dist folder has become quite cluttered. Webpack will generate the files and put them in the /dist folder for you, but it doesn't keep track of which files are actually in use by your project.

In general it's good practice to clean the /dist folder before each build, so that only used files will be generated. Let's take care of that.

A popular plugin to manage this is the clean-webpack-plugin so let's install and configure it.
```commandline

npm install --save-dev clean-webpack-plugin
```
webpack.config.js

```js

  const path = require('path');
  const HtmlWebpackPlugin = require('html-webpack-plugin');
+ const CleanWebpackPlugin = require('clean-webpack-plugin');

  module.exports = {
    entry: {
      app: './src/index.js',
      print: './src/print.js'
    },
    plugins: [
+     new CleanWebpackPlugin(['dist']),
      new HtmlWebpackPlugin({
        title: 'Output Management'
      })
    ],
    output: {
      filename: '[name].bundle.js',
      path: path.resolve(__dirname, 'dist')
    }
  };
  
```
Now run an npm run build and inspect the /dist folder. If everything went well you should now only see the files generated from the build and no more old files!

```js
npm install --save-dev webpack-dev-server

```