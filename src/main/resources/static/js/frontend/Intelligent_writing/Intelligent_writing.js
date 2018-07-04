

$(function(){
    var ctx = localStorage.getItem("defaultUrl");
    if(!ctx){
        ctx = $('#ctx').val() || "";
        localStorage.setItem("defaultUrl", ctx);
    }
    new Header({ctx:ctx, nowpage: "writing"});
    $(document).on("click", ".goregister", function(){
        window.open(ctx + '/contact/goregister', "_blank");      
    });
    // 立即注册按钮及效果
    $(".info_rigster").click(function (event) {
        var e = event || window.event;
        // 鼠标左键摁下效果
        if (e.which == 1) {
            $(this).removeClass("info_rigster_hover").addClass("info_rigster_enterKey");
            $(".register_key_bg").show();
        }
        window.location.href = ctx + 'contact/goregister';
    });
    $(".info_rigster").on("mouseenter", function () {
        var _this = this;
        $(_this).addClass("info_rigster_hover");
    }).on("mouseleave", function () {
        var _this = this;
        $(_this).removeClass("info_rigster_hover").removeClass("info_rigster_enterKey");
    });

    // 登录按钮及效果
    $(".info_login").click(function (event) {
        var e = event || window.event;
        // 鼠标左键摁下效果
        if (e.which == 1) {
            $(this).removeClass("info_login_hover").addClass("info_login_enterKey");
            $(".login_key_bg").show();
        }
        window.location.href = ctx + '/loginpage';
    });
    $(".info_login").on("mouseenter", function () {
        var _this = this;
        $(_this).addClass("info_login_hover");
    }).on("mouseleave", function () {
        var _this = this;
        $(_this).removeClass("info_login_hover").removeClass("info_login_enterKey");
    });

    $('.continue-knowe a').click(function(){
        window.location.href=ctx+'/gointroduce';
    })
});


