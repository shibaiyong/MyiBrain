
$(function() {
    var ctx = localStorage.getItem("defaultUrl");
    if(!ctx || ctx == 'undefined' || typeof(ctx) == undefined){
        ctx = $('#ctx').val() || "";
        localStorage.setItem("defaultUrl", ctx);
    }
    new Header({ctx: ctx});

    contentResize('#header', '.login_content', '#loginBottom');

    window.onresize = function () {
        // bottomResize();
        contentResize('#header', '.login_content', '#loginBottom');
    }
    
    var userid = localStorage.getItem('userId');
    console.log(userid)

    $('#complete').click(function (e) {
        var oldpassword = $('.oldpassword').val();
        var newpassword = $('.newpassword').val();
        var confirmpassword = $('.confirmpassword').val();
        if(oldpassword.trim()==''){
            $('.oldpassword_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.oldpassword_error_tip').css('visibility','hidden');
        }
        if($('.newpassword').val().trim()==''){
            $('.newpassword_error_tip').css('visibility','visible');
            return false;
        }else{
            $('.newpassword_error_tip').css('visibility','hidden');
        }
        if(confirmpassword.trim()==''){
            $('.confirmpassword_error_tip').css('visibility','visible');
            return false;
        }else if(confirmpassword.trim()!=newpassword.trim()){
            $('.confirmpassword_error_tip').css('visibility','visible').text('两次输入的新密码不一致');
            return false;
        }else{
            $('.confirmpassword_error_tip').css('visibility','hidden');
        }

        var data = {
            oldpassword: sha256_digest(oldpassword),
            newpassword: sha256_digest(newpassword),
            innerid: userid
        };

        $.ajax({
            url:"/user/updatePassword",
            type: 'post',
            data: data,
            dataType: 'json',
            success: function (data) {
                if(data.result){
                    loginOut(function(){
                        window.location.href = ctx + '/loginpage';
                    });
                }else{
                    alert(data.resultObj);
                }

            }
        })
    });

    //跳转到个人资料页面
    $('.per').click(function(){
        window.location.href ='/user/myinfo';
    })
    //跳转到我的服务页面
    $('.opt').click(function(){
        window.location.href ='/homePage';
    })
})

