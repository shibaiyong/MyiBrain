
$(function() {
	//防止出现ctx为undefined时,会在localStorage中存入"undefined"字符串
	var ctx = $('#ctx').val() || "";
    localStorage.setItem('defaultUrl', ctx);
    
    new Header({ctx: ctx});
    console.log('lfksf'+ctx);
    
    EnterPress();

    // bottomResize();
    contentResize('#header', '.login_content', '#loginBottom');
    
    window.onresize = function () {
        // bottomResize();
        contentResize('#header', '.login_content', '#loginBottom');
    }
    
    var userTemp = "";
    var targetId = "";
    
    $("#userName").click(function(e){
    	userTemp = $('#userName').val();
    	targetId = e.target.id;
    	if(userTemp && targetId != "userName"){
    		$("#passWord").unbind("keyup");
    	}
    	$(this).unbind("click");
    });
    
    $("#passWord").keyup(function(e){
    	userTemp = $('#userName').val();
    	if(userTemp && targetId != "userName"){
    		$('#userName').val("");
    		$(this).val("");
    		$('.user_error_tip').text('用户名不能为空！');
            $('.user_error_tip').css('visibility','visible');
    	}
    	$(this).unbind("keyup");
    });
    
    /*enter键进入*/
	function EnterPress(){
		$(document).keydown(function(event){ 
			var e = event || window.event; 
			var k = e.keyCode || e.which; 
			if(k == 13){
				$('#submit').click();
			}
		});
	}
    
    //点击登陆按钮
    $('#submit').click(function (e) {
        var username = $('input[name="username"]').val();
        var password = sha256_digest($('input[name="password"]').val());
        if(username.trim()==''){
        	$('.user_error_tip').text('用户名不能为空！');
            $('.user_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.user_error_tip').css('visibility','hidden');
        }
        if($('input[name="password"]').val().trim()==''){
            $('.password_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.password_error_tip').css('visibility','hidden');
        }
        var data = {
            "userName": username,
            "password": password
        };
        $.ajax({
            url:"/login",
            type: 'post',
            data: data,
            dataType: 'json',
            success: function (data) {
                console.log(data)
                if(!data.result){
                	$('.user_error_tip').text('用户名或者密码不正确');
                	$('.user_error_tip').css('visibility','visible');
                	localStorage.setItem('userId',"");
                }else{
                    var time = new Date().getTime();
                    localStorage.setItem('userId',data.resultObj.innerid);
                    localStorage.setItem('loginTime',time);
                    window.location.href ='/homePage';
                }
            }
        })
    });
    
    //直接注册
    $("#goRegister").click(function(e){
    	window.location.href = ctx + '/contact/goregister';
    });


    function bottomResize() {
        if ($('body').height() > 900) {
             $("#header").css({
                'height': '270px'
             });
            $("#loginBottom").addClass("footer_resize");
        } else {
            $("#header").css({
                'height': '120px'
            });
            $("#loginBottom").removeClass("footer_resize");
        }
    }

})


