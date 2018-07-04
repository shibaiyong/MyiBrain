$(function(){
	var ctx = localStorage.getItem("defaultUrl");
	if(!ctx){
		ctx = $('#ctx').val() || "";
		localStorage.setItem("defaultUrl", ctx);
	}
	new Header({ctx:ctx, nowpage: "index"});
	
	//查看更多及按钮效果
	$(".tip_content_item_btn").click(function(event){
		var e = event || window.event;
		// 鼠标左键摁下效果
		if (e.which == 1) {
			$(this).removeClass("btn_hover").addClass("btn_enterKey");
		}
		var $this = $(this), dataType = $this.attr("data-type"), url = $this.attr("data-url");
		if( dataType == 1){
			window.location.href = url;
		}else{
			window.location.href = ctx + url;
		}
	});
	$(".tip_content_item_btn").on("mouseenter", function () {
		var _this = this;
		$(_this).addClass("btn_hover");
	}).on("mouseleave", function () {
		var _this = this;
		$(_this).removeClass("btn_hover").removeClass("btn_enterKey");
	});


	// 选中事件
	$(document).on("click", ".tip_top_item li", function(){
		var $this = $(this), $bgmClass = ".item_";
		var dataType = $this.attr("data-type");
		if (!$this.hasClass("item_active")) {
			var contentSelector = ".content_" + dataType;
			var imgSelected = $bgmClass + $(".item_active").attr("data-type") + "_selected";
			var imgUnSelected = $bgmClass + $(".item_active").attr("data-type");
			// 先将之前的选中状态移除
			$activeImg = $(".item_active").find(".thumbnail").find(".item_img");
			$activeImg.find(imgSelected).addClass("hide");
			$activeImg.find(imgUnSelected).removeClass("hide");

			$(".tip_top_item li").removeClass("item_active");
			$(this).addClass("item_active");
			var nowImgSelected = $bgmClass + dataType + "_selected";
			var nowImgUnSelected = $bgmClass + dataType;
			$(this).find(".thumbnail").find(".item_img").find(nowImgUnSelected).addClass("hide");
			$(this).find(".thumbnail").find(".item_img").find(nowImgSelected).removeClass("hide");

			// 下方对应的内容
			$(".tip_content li").removeClass("content_active").addClass("hide");
			$(contentSelector).addClass("content_active").removeClass("hide");
		}
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

});

window.onload=function(){
	var clientwidth = window.innerWidth;
	if(clientwidth>=1200&&clientwidth<=1600){
        $('.shadow').attr('src', './images/index/index_small_banner.png');
	}else{
        $('.shadow').attr('src', './images/index/view_banner.png');
	}
    // $('.ani_moveToRight').animate({right:"0px"},1500,function(){
    //     $('.shadow').show();
    //     $('.text_content').animate({opacity:"1"},2000);
	// });
}