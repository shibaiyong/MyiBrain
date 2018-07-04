var Header = function(config){
	var ctx = config.ctx || "";
	//若不存在nowpage则默认都处于不选中状态
	var nowpage = config.nowpage || "";
	
	// 处理当前页面选中状态
	showNowNav(nowpage, ctx);
	var mobileAlert = setTimeout(onlyMobile, 500);
	showUserName();
	
	$(".index_logo").click(function(){
		window.location.href = ctx + '/';
	});
	$("#logOut").click(function(){
		var e = event || window.event;
		// 鼠标左键摁下效果
		if (e.which == 1) {
			$(this).removeClass("hover").addClass("font_enterKey");
		}
		loginOut(function(){
            window.location.href = ctx + "/loginpage";
		});
	});
	// 采用mouseenter和mouseout
	$("#logOut").on("mouseenter", function () {
		var _this = this;
		$(_this).addClass("hover");
	}).on("mouseleave", function () {
		var _this = this;
		$(_this).removeClass("hover").removeClass("font_enterKey");
	});

	function showNowNav(nowpage, ctx){
		var navList = $(".login_container ul li");
//		if(navList.length <6){
//			return;
//		}
		navList.eq(0).find("a").attr("href",ctx+"/");
//		navList.eq(1).find("a").attr("href",ctx+"/");
//		navList.eq(2).find("a").attr("href",ctx+"/iwriting");
//		navList.eq(3).find("a").attr("href",ctx+"/gointroduce");
//		navList.eq(4).find("a").attr("href",ctx+"/voicetrans");
//		navList.eq(5).find("a").attr("href",ctx+'/magicdata');
//      navList.eq(6).find("a").attr("href",ctx+'/videosearch');
	}
	function onlyMobile(){
		var ua = navigator.userAgent.toLocaleLowerCase();
		if(/(iphone|ipad|ipod|ios)/i.test(ua) 
		|| /(android)/i.test(ua) 
		|| ua.match(/MicroMessenger/i) == 'micromessenger'){
			setModalContent("请在电脑中浏览器打开!","确认", function(){
            	mobileAlert = null;
            });
			return false;
		}
	}

};
var ctx = localStorage.getItem("defaultUrl");
ctx = ctx ? ctx : '';
function loginOut(func){
    console.log(func)
    $.ajax({
        type:"get",
        url: ctx + "/logout",
        dataType: "json",
        success: function(data){
            if(data.result){
                $(".index_logoOut").addClass("hidden");
                $(".no_login").removeClass("hidden");
                localStorage.setItem("userId","");
                func();
            }else{
                setModalContent("系统异常，请联系管理员!","确认");
            }
        }
    });
}
//获取登录用户信息
function showUserName(){
	var userName = localStorage.getItem('userName');
	if(!userName){
		var userId = localStorage.getItem('userId');
		if(userId){				
			$.ajax({
				type: 'get',
				url:'/user/getUser/'+userId ,
				async:true,
				dataType: 'json',
				success: function(data){
					console.log(data);
					var obj = data.resultObj;
					if( data.result && obj != null){
						userName = obj.name;
						localStorage.setItem('userName', userName);
						$("#userInfo").text(userName);
					}
				}
			});	
		}
	}else{			
		$("#userInfo").text(userName);
	}
	$(".y_login").removeClass("hidden");
}
 
//公用的模态框方法
function setModalContent(content,confirm,callback){
    $('#alertDialog').modal('show');
    $('#alertDialog .modal-body p').text(content);
    $('#alertDialog .modal-body .centerBtn').text(confirm);
//  $('#alertDialog .modal-body .btn-default').text(cancel);
    $('.centerBtn').click(function(){
        $(this).unbind();
        $('#alertDialog').modal('hide');
        if(callback){
            callback();
        }else{
            return false;
        }
    })
}

// 动态计算内容区域的高度
function contentResize(headerSelector, contentSelector, bottomSelector) {
	var headerHeight = $(headerSelector).height();
	var contentHeight = $(contentSelector).height();
	var bottomHeight = $(bottomSelector).height();
	var bodyHeight = $('body').height();
	var marginNum = parseInt((bodyHeight - (headerHeight + contentHeight + bottomHeight)) / 2);
	var bottomNum = Math.abs(marginNum);
	
	if (marginNum <= 0  || Math.abs(marginNum) <= 70) {
		marginNum = 70;
		bottomNum = 70;
	} else {
		marginNum = 140;
	}
	// 注册页面特殊处理
	if ($('body').width() > 1900) {
		marginNum = 140;
	}
	$(contentSelector).css({
		'marginTop': marginNum + 'px',
		'marginBottom': bottomNum + 'px'
	});
	$(contentSelector).removeClass('visiHide');

}