$(function(){	
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

	var userid = localStorage.getItem('userId');
	myService();
	
	
	$(document).on("click",".server-tip1",function(){
	    var clickUrl = $(this).attr("data-flag");
	    if(clickUrl == "remoteINews"){
	        ajaxMethod();
	    }else if( clickUrl == 'iwriting' || clickUrl == 'voicetrans'){
	    	console.log();
	    }else{
	    	window.location.href = $(this).attr("data-url");
	    }
	});
	
//	$(document).on("mouseenter",".gomore",function(){
//	    $(".magicdata").addClass("toSmall");
//	    $(this).addClass("moreBig");
//	});
//	$(document).on("mouseout",".gomore",function(){
//	    $(".magicdata").removeClass("toSmall");
//	    $(this).removeClass("moreBig");
//	});
	function ajaxMethod(){
	    $.ajax({
	        url:ctx + '/remoteINews',
	        type:'get',
	        data:{innerid:userid},
	        dataType:'json',
	        success:function( res ){
	            console.log(res);
	            if(res.result){
	                $('input[name="access_token"]').val(res.resultObj.access_token);
	                $('input[name="signature"]').val(res.resultObj.signature);
	                $('input[name="account"]').val(res.resultObj.account);
	                $('input[name="redirectUrl"]').val(res.resultObj.redirectUrl);
	                $("#sub").submit();
	            }else{
	                alert("系统异常，请稍后再试！");
	            }
	        }
	    })
	}
	
	function myService(){
		$.ajax({
	        url:ctx + '/menu/myservice',
	        type:'get',
	        data:{innerid:userid},
	        dataType:'json',
	        success:function( data ){
	            if(data && data.length>0){
					var myServiceHtml = "";
					// 暂时隐藏无链接的内容
					var dataArray = [];
					data.forEach(function(item, index) {
						if(item.id !=1 && item.id !=3){
							dataArray.push(item);
						}
					});

	            	$.each(dataArray, function (index, item) {
	            		myServiceHtml += '<div class="server-content server-tip1" data-flag="'+item.flag+'" data-url="'+item.clickUrl+'">'
	            						+	'<img src="'+item.imgUrl+'"/>'
	            						+ 	'<div class=bottom-wrapper>'
	            						+		'<a href="javascript:void(0);" class="'+item.flag+'">'+item.name+'</a>'
	            						+   '</div>'
	            						+ '</div>';
	            	});
	            	myServiceHtml += '<div class="server-content server-tip1" style="visibility:hidden; display:none;">'
	            					+ 		'<img src=""/>'
	            						+ 	'<div class=bottom-wrapper>'
	            						+		'<a href="javascript:void(0);"></a>'
	            						+   '</div>'
	            						+ '</div>';
	            	$("#myService").html(myServiceHtml);
//	            	$("div[data-flag=magicdata]").find('.magicdata').after('<a class="gomore" href="http://test.hzfanews.com/v2/share/customization/more.html">更多产品</a>');
	            }
	        }
	  	});
	}
	
	function Process( res ){
	    console.log(res);
	}
	
	$('.pas').click(function(){
	    window.location.href ='/user/goupdatepwd';
	})
	
	$('.per').click(function(){
	    window.location.href ='/user/myinfo';
	})
});
