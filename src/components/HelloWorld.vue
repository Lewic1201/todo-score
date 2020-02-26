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
        <a href="https://github.com/Lewic1201" target="_blank">
          github
        </a>
      </li>
      <li>
        <a href="https://www.cnblogs.com/lewic/" target="_blank">
          blog
        </a>
      </li>
    </ul>
  </div>
</template>

<script type="text/javascript">
//背景线条特效---------------------------------------------------------------------------------------------
!function () {
  function n(n, e, t) {
    return n.getAttribute(e) || t
  }

  function e(n) {
    return document.getElementsByTagName(n)
  }

  function t() {
    var t = e("script"), o = t.length, i = t[o - 1];
    return {l: o, z: n(i, "zIndex", -1), o: n(i, "opacity", .5), c: n(i, "color", "0,0,0"), n: n(i, "count", 99)}
  }

  function o() {
    a = m.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth, c = m.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
  }

  function i() {
    r.clearRect(0, 0, a, c);
    var n, e, t, o, m, l;
    s.forEach(function (i, x) {
      for (i.x += i.xa, i.y += i.ya, i.xa *= i.x > a || i.x < 0 ? -1 : 1, i.ya *= i.y > c || i.y < 0 ? -1 : 1, r.fillRect(i.x - .5, i.y - .5, 1, 1), e = x + 1; e < u.length; e++) n = u[e], null !== n.x && null !== n.y && (o = i.x - n.x, m = i.y - n.y, l = o * o + m * m, l < n.max && (n === y && l >= n.max / 2 && (i.x -= .03 * o, i.y -= .03 * m), t = (n.max - l) / n.max, r.beginPath(), r.lineWidth = t / 2, r.strokeStyle = "rgba(" + d.c + "," + (t + .2) + ")", r.moveTo(i.x, i.y), r.lineTo(n.x, n.y), r.stroke()))
    }), x(i)
  }

  var a, c, u, m = document.createElement("canvas"), d = t(), l = "c_n" + d.l, r = m.getContext("2d"),
    x = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function (n) {
      window.setTimeout(n, 1e3 / 45)
    }, w = Math.random, y = {x: null, y: null, max: 2e4};
  m.id = l, m.style.cssText = "position:fixed;top:0;left:0;z-index:" + d.z + ";opacity:" + d.o, e("body")[0].appendChild(m), o(), window.onresize = o, window.onmousemove = function (n) {
    n = n || window.event, y.x = n.clientX, y.y = n.clientY
  }, window.onmouseout = function () {
    y.x = null, y.y = null
  };
  for (var s = [], f = 0; d.n > f; f++) {
    var h = w() * a, g = w() * c, v = 2 * w() - 1, p = 2 * w() - 1;
    s.push({x: h, y: g, xa: v, ya: p, max: 6e3})
  }
  u = s.concat([y]), setTimeout(function () {
    i()
  }, 100)
}();

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
