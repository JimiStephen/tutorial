// 定义一个名为 button-counter 的新组件
Vue.component('button-counter', {
    //必须是一个函数
    data: function () {
        return {
            count: 0
        }
    },
    template: '<button v-on:click="count++">You clicked me {{ count }} times.</button>'
})

new Vue({ el: '#components-demo' })
/**********************************************************************************/

Vue.component('blog-post', {
    props: ['title'],
    template: '<h3>{{ title }}</h3>'
})
new Vue({
    el: '#blog-post-demo',
    data: {
        posts: [
            { id: 1, title: 'My journey with Vue' },
            { id: 2, title: 'Blogging with Vue' },
            { id: 3, title: 'Why Vue is so fun' }
        ]
    }
})
/**********************************************************************************/

Vue.component('todo-item', {
    template: '<li>This is a todo</li>'
})

var app = new Vue({
    el: '#app'
})
/**********************************************************************************/
Vue.component('todo-item', {
    props: ['todo'],
    template: '<li>{{ todo.text }}</li>'
})

var app1 = new Vue({
    el: '#app1',
    data: {
        groceryList: [
            { id: 0, text: 'Vegetables' },
            { id: 1, text: 'Cheese' },
            { id: 2, text: 'Whatever else humans are supposed to eat' }
        ]
    }
})