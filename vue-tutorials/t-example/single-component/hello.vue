<template>
    <p>{{greeting}},welcome to vue world</p>
</template>

<script>
    export default {
        name: "hello",
        data: function () {
            return{
                greeting:"hell jimi"
            }
        },
        components: {

        }
    }
</script>

<style scoped>
    p {
        font-size: 2em;
        text-align: center;
    }
</style>