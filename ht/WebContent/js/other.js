 $(function() {
      $("#nav li").hover(
          function() {
            $(this).find("ul").show(100);
          },
          function() {
            $(this).find("ul").hide(300);
          }
      );
    });