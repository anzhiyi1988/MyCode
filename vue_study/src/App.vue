<template>
    <div id="app">
        <img alt="Vue logo" src="./assets/logo.png">
        <HelloWorld msg="Welcome to Your Vue.js App"/>
        <div>
            <input type="text" v-model="in_value"/>
            <button v-on:click="addValue">add value</button>
        </div>
        <ul>
            <TodoLi v-for="v in in_values" v-bind:value="v">
                <template v-slot:todo-li-slot>
                    <span>{{v}}</span>
                </template>
            </TodoLi>
        </ul>
    </div>
</template>

<script>
    import HelloWorld from './components/HelloWorld.vue'
    import TodoLi from "./components/TodoLi";

    export default {
        name: 'app',
        components: {
            TodoLi,
            HelloWorld
        },
        data() {
            return {
                in_value: '',
                in_values: []
            }
        },
        methods: {
            addValue() {
                console.log(this.in_value);
                this.in_values.push(this.in_value);
                this.in_value = '';
            }

        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
