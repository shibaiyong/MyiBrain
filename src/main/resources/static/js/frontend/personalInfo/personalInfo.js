$(function() {
	var ctx = localStorage.getItem("defaultUrl");
	if(!ctx){
		ctx = $('#ctx').val() || "";
		localStorage.setItem("defaultUrl", ctx);
	}
	new Header({ctx: ctx});

	contentResize('#header', '.login_content', '#loginBottom');

	window.onresize = function () {
		// bottomResize();
		contentResize('#header', '.login_content', '#loginBottom');
	}
	
	var userId = localStorage.getItem("userId") || "";
	if(!userId){
		
	}else{
	    //获取登录用户信息。展示到个人资料页面。
		$.ajax({
			type: 'get',
			url:'/user/getUser/'+userId ,
			async:true,
			dataType: 'json',
			success: function(data){
				console.log(data);
				var obj = data.resultObj;
				if( data.result && obj != null){
					$('.username').val(obj.name || "");
					$('.mobile').val(obj.mobilePhone || "");
					$('.email').val(obj.email || "");
                    $('.wechat').val(obj.wechat || "");
					//单位和职称后端暂时不提供对应的字段信息，前端考虑是否暂时隐藏这两项输入框？？？
					$('.company').val('');
					$('.job').val('');
					$('#inputUserName').attr("disabled","disabled");
					$('#inputMobile').attr("disabled","disabled");
					$('#inputEmail').attr("disabled","disabled");
					$('#inputUnit').attr("disabled","disabled");
					$('#wechat').attr("disabled","disabled");
				}
			}
		});
	}

    //显示个人资料修改页面
    $('#infoUpdate').click(function (e) {
        $('.login_content').css('display','none');
        $('.modify_login_content').css('display','block');
    });
    // 更新个人资料
    $('#complete').click(function (e) {
    	var username = $.trim($('.modify_login_content .username').val() || "");
        var cellphone = $.trim($('.modify_login_content .mobile').val() || "");
        var email = $.trim($('.modify_login_content .email').val() || "");
        var company = $.trim($('.modify_login_content .company').val() || "");
        var job = $.trim($('.modify_login_content .job').val() || "");
        var wechat = $.trim($('.modify_login_content .wechat').val() || "");
        if(username == ''){
            $('.user_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.user_error_tip').css('visibility','hidden');
        }
		if(cellphone == ''){
            $('.mobile_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.mobile_error_tip').css('visibility','hidden');
        }
        if(!mobileValidate(cellphone)){
        	$('.mobile_error_tip').text("您输入的手机号不合法,请重新输入！");
            $('.mobile_error_tip').css('visibility','visible');
            return false;
        }else{
        	$('.mobile_error_tip').text("手机号不能为空！");
            $('.mobile_error_tip').css('visibility','hidden');
        }
        if(email == ''){
            $('.email_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.email_error_tip').css('visibility','hidden');
        }
		if(!emailValidatte(email)){
        	$('.email_error_tip').text("您输入的邮箱不合法，请重新输入！");
            $('.email_error_tip').css('visibility','visible');
            return false;
        }else{
        	$('.email_error_tip').text("邮箱不能为空！");
            $('.email_error_tip').css('visibility','hidden');
        }
        var options = {
            name: username,
            mobilePhone: cellphone,
            email:email,
            company:company,
            job:job,
            innerid:userId,
            wechat:wechat,
        };
        $.ajax({
        	type:"post",
        	url: '/user/updateInfo',
        	dataType: 'json',
        	data: options,
        	async: false, //此处采用同步方式，因为在chrome中存在后台还未返回，页面已经刷新导致请求中断的问题
        	success: function(data){
        		if(data.result &&　data.resultObj != ""){
        			console.log(data.resultObj);
        			localStorage.setItem('userName',username);
            		window.location.href ='/homePage';
            	}else{
            		alert("保存失败,请稍后再试!");
            	}
        	},
        	fail: function(data){
        		console.log(data);
        	},
        	error: function(err){
        		console.log(err);
        	}
        });
    });

    //跳转到修改密码页
    $('.pas').click(function(){
        window.location.href ='/user/goupdatepwd';
    })
    //跳转到我的服务页面
    $('.opt').click(function(){
        window.location.href ='/homePage';
    })
    
    // 验证手机号码的合法性
    function mobileValidate(mobileNum){
    	if(!mobileNum){
    		return false;
    	}
    	var mobileReg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
    	if( !mobileReg.test(mobileNum)){
    		return false;
    	}else{
    		return true;
    	}
    }
    //验证邮箱合法性(匹配邮箱中名称可以包含汉字、字母、数字，域名只允许为英文和数字)
    function emailValidatte(email){
    	if(!email){
    		return false;
    	}
    	var emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    	if( !emailReg.test(email)){
    		return false;
    	}else{
    		return true;
    	}
    }
});