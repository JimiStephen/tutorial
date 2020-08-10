//babel.config.js
module.exports={
	presets:[
		["@babel/preset-env",{
			"useBuiltIns":"usage"
		}]
	],
	plugins:[
	//>npm install @babel/plugin-syntax-dynamic-import --save-dev
	'@babel/plugin-syntax-dynamic-import'
	]
}