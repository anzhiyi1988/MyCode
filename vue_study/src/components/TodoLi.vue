<template>
    <li>
        <slot name="todo-li-slot"></slot>
    </li>
</template>

<script>
    export default {
        name: "TodoLi",
        props: ['value']
    }
</script>

<style scoped>

</style>