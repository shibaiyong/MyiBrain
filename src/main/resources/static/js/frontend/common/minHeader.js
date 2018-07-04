var Header = function(config){
	var ctx = config.ctx || "";
	//若不存在nowpage则默认都处于不选中状态
	var nowpage = config.nowpage || "";
	var loginstatus = $('#loginStatus').val();
	// 处理当前页面选中状态
	showNowNav(nowpage, ctx);
	// 处理登录状态
	loginState();
	var mobileAlert = setTimeout(onlyMobile, 500);
	showUserName();
	
	$("#goLogin").click(function(event){
		var e = event || window.event;
		// 鼠标左键摁下效果
		if (e.which == 1) {
			$(this).removeClass("hover").addClass("font_enterKey");
		}
		localStorage.setItem('userId',"");
		localStorage.setItem('userName', "");
		$("#userInfo").text("");
		window.location.href = ctx + '/loginpage';
	});
	// 采用mouseenter和mouseout
	$("#goLogin").on("mouseenter", function () {
		var _this = this;	
		$(_this).addClass("hover");
	}).on("mouseleave", function () {
		var _this = this;
		$(_this).removeClass("hover");
	});

	$("#goRegister").click(function (event) {
		var e = event || window.event;
		// 鼠标左键摁下效果
		if (e.which == 1) {
			$(this).removeClass("hover").addClass("font_enterKey");
		}
		localStorage.setItem('userId',"");
		localStorage.setItem('userName',"");
		$("#userInfo").text("");
		window.open(ctx + '/contact/goregister', "_blank");
	});
	// 采用mouseenter和mouseout
	$("#goRegister").on("mouseenter", function () {
		var _this = this;
		$(_this).addClass("hover");
	}).on("mouseleave", function () {
		var _this = this;
		$(_this).removeClass("hover").removeClass("font_enterKey");
	});


	$("#logOut").click(function(){
		loginOut(
		    function(){
		        window.location.href = ctx + "/";
		});
	});
	$("#userInfo").click(function(){
        checkSession();

	});
	$('.index_logo').click(function(){
	    window.location.href = ctx + '/';
	});
	
	function showNowNav(nowpage, ctx){
		var navList = $(".login_container ul li");
		if(navList.length <6){
			return;
		}
		navList.removeClass("active").removeClass("mediumfont");
		navList.eq(0).find("a").attr("href",ctx+"/");
        navList.eq(1).find("a").attr("href",ctx+"/iwriting");
		navList.eq(2).find("a").attr("href",ctx+"/gointroduce");
        navList.eq(3).find("a").attr("href",ctx+"/voicetrans");
        navList.eq(4).find("a").attr("href",ctx+'/magicdata');
        navList.eq(5).find("a").attr("href",ctx+'/videosearch');
		switch(nowpage){
			case "index":  //首页
				navList.eq(0).addClass("active").addClass("mediumfont");
				break;
            case "writing": //智能写作
                navList.eq(1).addClass("active").addClass("mediumfont");
                break;
			case "introduce": //智媒引擎
				navList.eq(2).addClass("active").addClass("mediumfont");
				break;
            case "word2voice": //语音转写
                navList.eq(3).addClass("active").addClass("mediumfont");
                break;
            case "magicdata": //数据魔方
                navList.eq(4).addClass("active").addClass("mediumfont");
                break;
            case "vediosearch": //视频搜索
                navList.eq(5).addClass("active").addClass("mediumfont");
                break;
			default: 
				break;
		}
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
	}
	//退出操作
	function loginOut(func){
		$.ajax({
			type:"get",
			url: ctx + "/logout",
			dataType: "json",
			success: function(data){
				if(data.result){
					$(".y_login").addClass("hidden");
					$(".no_login").removeClass("hidden");
					localStorage.setItem("userId","");
					func();
				}else{
					setModalContent("系统异常，请联系管理员!","确认");
				}
			}
		});
	}
	//根据登录状态，切换登录按钮
	function loginState(){
		if(!loginstatus){
            $(".no_login").removeClass("hidden");
            $(".y_login").addClass("hidden");
		}else{
            $(".y_login").removeClass("hidden");
            $(".no_login").addClass("hidden");
		}
	}
	//检验用户是否登录超时
	function checkSession() {
        $.ajax({
            type:"get",
            url: ctx + "/checksession",
            dataType: "json",
            success: function(data){
            	console.log(data)
                if(!data){
                    var showtip = new ShowTip({
                        elem:'.showtoast1',
                        content:'登录超时，请重新登录！',
                        callback:function(){
                            window.location.href = ctx + '/loginpage';
                        }
                    })
                    showtip.inite();
                }else{
                    window.location.href = ctx + '/homePage';
				}
            }
        });
    }

};



//公共模态框方法
function setModalContent( content,confirm,callback){
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
