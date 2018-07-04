function ShowTip(options){
    this.elem = options.elem;
    this.content = options.content;
    this.callback=options.callback;
}

ShowTip.prototype={
    inite:function(){
        $(this.elem).show();
        $(this.elem).find('.showtoastcontent').text(this.content);
        this.timeOutHide();
        this.clearTimeOut();
    },
    timeOutHide:function(){
        var $this = this;
        this.time = setTimeout(function(){
            $($this.elem).hide();
            $this.callback();
        },5000)
    },
    clearTimeOut:function(){
        this.time = null;
    }
}