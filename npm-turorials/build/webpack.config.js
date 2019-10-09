//build/webpack.config.js
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack')
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports={
	//package mode
	mode:'development',
	entry:{
	  //config entry file 
	 // main:path.resolve(__dirname,'../src/main.js')
	  main:["@babel/ployfill",path.resolve(__dirname,'../src/main.js')]
	},
	output:{
		//config package file output directory
		path:path.resolve(__dirname,'../dist'),
		//genatater js file name
		filename:'js/[name].[hash:8].js',
		//genatater chunk name
		chunkFilename:'js/[name].[hash:8].js',
		//resource quote path
		publicPath:'./'
	},
	devServer:{
		hot:true,
		port:3000,
		contentBase:'./dist'
	},
	resolve:{
		alias:{
			vue$:'vue/dist/vue.runtime.esm.js'
		}
	},
	module:{
		rules:[
		//webpack distinguish vue
		{
			test:/\.vue$/,
			use:[
				{
					loader:'cache-loader'
				},{
					loader:'thread-loader'
				},{
					loader:'vue-loader',
					options:{
						compilerOptions:{
							preserveWhitespace:false
						}
					}
				},
			]
		},
		{
			test:/\.jsx?$/,
			use:[
				{
					loader:'cache-loader'
				},{
					loader:'thread-loader'
				},{
					loader:'babel-loader-loader'
				},
			]
		},
		{
			test:/\.jsx?$/,
			exclude:/node_modules/,
			use:[{
				loader:'babel-loader'
			}]
		},
		//npm install sass-loader dart-sass css-loader style-loader -D
		{
			test:/\.(scss|sass)$/,
			use:[
				{
					loader:'sytle-loader'
				},{
					loader:'css-loader'
				},{
					loader:'sass-loader',
					options:{
						implementation:require('dart-sass')
					}
				},{
					//npm install postcss-loader autoprefixer -D
					loader:'postcss-loader'
				}
			]
		},
		//webpack 打包图片 媒体 字体等文件
		{
        test: /\.(jpe?g|png|gif)$/i,
        use: [
          {
            loader: 'url-loader',
            options: {
              limit: 4096,
              fallback: {
                loader: 'file-loader',
                options: {
                    name: 'img/[name].[hash:8].[ext]'
                }
              }
            }
          }
        ]
      },
      {
        test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
        use: [
          {
            loader: 'url-loader',
            options: {
              limit: 4096,
              fallback: {
                loader: 'file-loader',
                options: {
                  name: 'media/[name].[hash:8].[ext]'
                }
              }
            }
          }
        ]
      },
      {
        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/i,
        use: [
          {
            loader: 'url-loader',
            options: {
              limit: 4096,
              fallback: {
                loader: 'file-loader',
                options: {
                  name: 'fonts/[name].[hash:8].[ext]'
                }
              }
            }
          }
        ]
      },
 
	 ]
	},
	plugins:[
		new HtmlWebpackPlugin({
			template:path.resolve(__dirname,'../public/index.html')
		}),
		//npm install webpack-dev-server -D 代码热更新
		new webpack.NamedModulesPlugin(),
		new webpack.HotModuleReplacementPlugin(),
		//定义环境变量
		 new webpack.DefinePlugin({
		  'process.env': {
			VUE_APP_BASE_URL: JSON.stringify('http://localhost:3000')
		  }
		})
	]
}