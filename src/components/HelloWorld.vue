<template>
  <!--    <div>-->
  <!--    </div>-->

  <div class="hello">
    <img src="../assets/logo.png">
    <h1>{{ msg }}</h1>
    <h2>Custom</h2>
    <ul>
      <li>
        <router-link to="/task" active-class="router-active">
          my-task
        </router-link>
      </li>
      <li>
        <router-link to="/taskRecord" active-class="router-active">
          my-task-history
        </router-link>
      </li>
      <li>
        <router-link to="/todayTask" active-class="router-active">
          my-today-task
        </router-link>
      </li>
      <li>
        <router-link to="/cycleType" active-class="router-active">
          my-cycle-type-manage
        </router-link>
      </li>
    </ul>
    <h2>Html Template</h2>
    <ul>
      <li>
        <a href="../../static/html/pointer/index.html" target="_blank">
          pointer
        </a>
      </li>
      <li>
        <a href="../../static/html/404/index.html" target="_blank">
          404
        </a>
      </li>
      <li>
        <a href="../../static/html/input/index.html" target="_blank">
          input
        </a>
      </li>
      <li>
        <a href="../../static/html/textillate/index.html" target="_blank">
          textillate
        </a>
      </li>
      <li>
        <a href="../../static/html/numberProgress/index.html" target="_blank">
          number progress
        </a>
      </li>
      <li>
        <a href="../../static/html/msgBoard/index.html" target="_blank">
          message board
        </a>
      </li>
      <br/>
      <li>
        <a href="../../static/html/clock/index.html" target="_blank">
          clock
        </a>
      </li>
      <li>
        <a href="../../static/html/clock2/index.html" target="_blank">
          clock2
        </a>
      </li>
      <li>
        <a href="../../static/html/photo1/index.html" target="_blank">
          photo1
        </a>
      </li>
      <li>
        <a href="../../static/html/photo2/index.html" target="_blank">
          photo2
        </a>
      </li>
      <li>
        <a href="../../static/html/photo3/index.html" target="_blank">
          photo3
        </a>
      </li>
      <li>
        <a href="../../static/html/login1/index.html" target="_blank">
          login1
        </a>
      </li>
      <li>
        <a href="../../static/html/login2/index.html" target="_blank">
          login2
        </a>
      </li>
      <li>
        <a href="../../static/html/login3/default/index.html" target="_blank">
          login3
        </a>
      </li>
    </ul>
    <h2>H5 Game</h2>
    <ul>
      <li>
        <a href="../../static/html/game1/index.html" target="_blank">
          六边形下降
        </a>
      </li>
      <li>
        <a href="../../static/html/game2/index.html" target="_blank">
          像素小鸟
        </a>
      </li>
      <li>
        <a href="../../static/html/game3/index.html" target="_blank">
          扫雷
        </a>
      </li>
      <li>
        <a href="../../static/html/game4/index.html" target="_blank">
          五子棋
        </a>
      </li>
      <li>
        <a href="../../static/html/game5/index.html" target="_blank">
          贪吃蛇
        </a>
      </li>
    </ul>
    <h2>Interesting</h2>
    <ul>
      <li>
        <a href="../../static/html/lines/index.html" target="_blank">
          lines
        </a>
      </li>
      <li>
        <a href="../../static/html/num/index.html" target="_blank">
          num
        </a>
      </li>
      <li>
        <a href="../../static/html/keyboard/index.html" target="_blank">
          keyboard
        </a>
      </li>
      <li>
        <a href="../../static/html/ballPool/index.html" target="_blank">
          ball pool
        </a>
      </li>
      <br/>
      <li>
        <a href="../../static/html/love1/index.html" target="_blank">
          l1
        </a>
      </li>
      <li>
        <a href="../../static/html/love2/index.html" target="_blank">
          l2
        </a>
      </li>
      <li>
        <a href="../../static/html/love3/index.html" target="_blank">
          l3
        </a>
      </li>
      <li>
        <a href="../../static/html/love4/index.html" target="_blank">
          l4
        </a>
      </li>
      <li>
        <a href="../../static/html/love5/index.html" target="_blank">
          l5
        </a>
      </li>
      <li>
        <a href="../../static/html/love6/index.html" target="_blank">
          l6
        </a>
      </li>
      <li>
        <a href="../../static/html/love7/index.html" target="_blank">
          l7
        </a>
      </li>
    </ul>
    <h2>code</h2>
    <ul>
      <li>
        <a
          href="https://github.com/Lewic1201"
          target="_blank"
        >
          my github
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: 'HelloWorld',
    data() {
      return {
        msg: 'Welcome to lewic Vue.js App'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
