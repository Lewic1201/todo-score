import jQuery from 'jquery'
/* 鼠标特效 */
var a_idx = 0;
jQuery(document).ready(function ($) {
  $("body").click(function (e) {
    var a = ["✊加油⇑", "💪健康卐", "❤웃心动유❤", "😃未来可期😃", "✯优秀如我☪", "💎暴富💰",
      "🍇🍈🍉🍊🍋🍌🍍🍎", "🍏🍐🍑🍒🍓🍅🍆🌽", "🍄🌰🍞🍖🍗🍔🍟🍕", "🍤🍥🍡🍦🍧🍨🍩🍪", "🎂🍰🍫🍬🍭🍮🍯🍼",
      "☕🍵🍶🍷🍸🍹🍺🍻", "💩💩💩💩💩💩💩", "⚡雷厉风行🌠", "⭐清新脱俗🌟", "💗💙💚💛💜", "😜😜😜😜😜😝",
      "₡不卑不亢₡₮", "ღ诚信✠", "✡奋起▲", "✌胜利在望✌", "☯攀登扎营♛", "♈♉♊♋♌♍♎♏♐♑♒♓⛎"];
    var $i = $("<span></span>").text(a[a_idx]);
    a_idx = (a_idx + 1) % a.length;
    var x = e.pageX,
      y = e.pageY;
    $i.css({
      "z-index": 999999999999999999999999999999999999999999999999999999999999999999999,
      "top": y - 20,
      "left": x,
      "position": "absolute",
      "font-weight": "bold",
      "color": "rgb(" + ~~(255 * Math.random()) + "," + ~~(255 * Math.random()) + "," + ~~(255 * Math.random()) + ")"
    });
    $("body").append($i);
    $i.animate({
        "top": y - 180,
        "opacity": 0
      },
      1500,
      function () {
        $i.remove();
      });
  });
});
