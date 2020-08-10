export default function install (Vue) {
  Vue.prototype.$pluginDemo = (message) => {
    console.log(message)
  }
}
