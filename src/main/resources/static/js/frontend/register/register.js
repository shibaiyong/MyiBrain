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
	
	var emailTemp = "";
    var targetId = "";
    
    $("#inputMail").click(function(e){
    	emailTemp = $('#inputMail').val();
    	targetId = e.target.id;
    	if(targetId != "email"){
    		$("#inputPwd").unbind("keyup");
    	}
    	$(this).unbind("click");
    });
    
    $("#inputPwd").keyup(function(e){
    	emailTemp = $('#inputMail').val();
    	if(emailTemp && targetId != "email"){
    		$('#inputMail').val("");
    		$(this).val("");
    		$('.email_error_tip').text('邮箱不能为空！');
            $('.email_error_tip').css('visibility','visible');
    	}
    	$(this).unbind("keyup");
    });
    
    //注册完成按钮
    $('#register').click(function (e) {
        var username = $.trim($('#inputName').val() || "");
        var cellphone = $.trim($('#inputMobile').val() || "");
        var email = $.trim($('#inputMail').val() || "");
        var password = $.trim($('#inputPwd').val() || "");
        var wechat = $.trim($('#wechat').val() || "");
        var company = $.trim($('#inputUnit').val() || "");
        var job = $.trim($('#inputPosition').val() || "");
        if(username == ''){
            $('.user_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.user_error_tip').css('visibility','hidden');
        }
		if(cellphone == ''){
            $('.mobile_error_tip').css('visibility','visible');
            return false;
        }else if(!mobileValidate(cellphone)){
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
        }else if(!emailValidatte(email)){
            $('.email_error_tip').text("您输入的邮箱不合法，请重新输入！");
            $('.email_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.email_error_tip').text("邮箱不能为空！");
            $('.email_error_tip').css('visibility','hidden');
        }

        if(password == ''){
            $('.password_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.password_error_tip').css('visibility','hidden');
        }
        if(password.length <6){
        	$('.password_error_tip').text("您输入的密码不合法，请重新输入！");
            $('.password_error_tip').css('visibility','visible');
            return false;
        }else{
        	$('.password_error_tip').text("密码不能为空！");
            $('.password_error_tip').css('visibility','hidden');
        }
//      if(wechat == ''){
//          $('.wechat_error_tip').css('visibility','visible');
//          return false;
//      }else{
//          $('.wechat_error_tip').css('visibility','hidden');
//      }
     if(company == ''){
         $('.company_error_tip').css('visibility','visible');
         return false;
     }else{
         $('.company_error_tip').css('visibility','hidden');
     }
     if(job == ''){
         $('.job_error_tip').css('visibility','visible');
         return false;
     }else{
         $('.job_error_tip').css('visibility','hidden');
     }
        var options = {
            userName: username,
            password: password,
            cellphone: cellphone,
            email:email,
            wechat:wechat,
            company:company,
            job:job
        };

        $.ajax({
        	type:"post",
        	url: ctx + '/contact/applyForUse',
        	dataType: 'json',
        	data: options,
        	async: false, //此处采用同步方式，因为在chrome中存在后台还未返回，页面已经刷新导致请求中断的问题
        	success: function(data){
        		if(data.result &&　data.resultObj != ""){
        			setModalContent(data.resultObj, "确认",function(){
        				window.location.href = ctx + '/';
        			});
            	}else{
                    setModalContent('注册失败,请稍后再试!','确认');
            	}
        	},
        	fail: function(data){
        		console.log(data);
        	},
        	error: function(err){
        		console.log(err);
        	}
        });
        return false;
    });
	
	//直接登录
	$("#goLogin").click(function(e){
		window.location.href = ctx + "/loginpage";
	});
	
    //封装的ajax请求
    function ajaxMethod(url, type, options){
        $.ajax({
            url:url,
            type:type,
            data:options.data,
            dataType:'json',
            success:function( res ){
                if(options.callback){
                    if(options.ele){
                        options.callback(res,options.ele);
                    }else{
                        options.callback(res);
                    }
                }else{
                    return;
                }
            }
        })
    }
    
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
})