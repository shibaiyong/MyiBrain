
//通知提示框
function ShowToast(options){
    this.elem = options.elem;
    this.content = options.content;
    this.comfirmButton = options.comfirmText;
    this.cancelButton = options.cancelText;
}

ShowToast.prototype={
    inite:function(callbacks){
        $(this.elem).show();
        $(this.elem).find('.showtoastcontent').text(this.content);
        $(this.elem).find('.confirm').text(this.comfirmButton);
        $(this.elem).find('.cancel').text(this.cancelButton);
        this.confirmCallback(callbacks.confirmfun);
        this.cancelCallback(callbacks.cancelfun);
    },
    confirmCallback:function(confirmcallback){
        var elem = $(this.elem);
        if(!this.comfirmButton){
            return false;
        }
        $(this.elem).find('.confirm').unbind();
        $(this.elem).find('.confirm').click(function(){
            elem.hide();
            if(confirmcallback){
                confirmcallback();
            }
        })
    },
    cancelCallback:function(cancelcallback){
        var elem = $(this.elem);
        if(!this.cancelButton){
            return false;
        }
        $(this.elem).find('.cancel').unbind();
        $(this.elem).find('.cancel').click(function(){
            elem.hide();
            if(cancelcallback){
                cancelcallback();
            }
        })
    }
}

//js各个配置项示例
// var showtoast = new ShowToast({
//         elem:'.showtoast',
//         content:'hahahhahahahhahahahhahahjhahahhahahhahhahahahha',
//         comfirmText:'no',
//         cancelText:'ok'
//     })
//
//     function fun1(){
//         alert(1)
//     }
//     function fun2(){
//         alert(2)
//     }
//     $('.checkbox label').click(function(){
//         //回调函数可选
//         showtoast.inite({
//             confirmfun:fun1,
//             cancelfun:fun2
//         });
//     })

//html页面结构

// {/*<div class="showtoast">*/}
//     {/*<div class="toast_title">提示</div>*/}
//     {/*<p class="showtoastcontent">提示信息？</p>*/}
//     {/*<div class="showtoast_footer">*/}
//         {/*<button class="btn btn-danger btn-sm confirm">确认</button>*/}
//         {/*<button class="btn btn-default btn-sm cancel">取消</button>*/}
//     {/*</div>*/}
// {/*</div>*/}

