
    $(function(){
        var ctx = localStorage.getItem("defaultUrl");
        if(!ctx){
            ctx = $('#ctx').val() || "";
            localStorage.setItem("defaultUrl", ctx);
        }
        new Header({ctx:ctx, nowpage: "word2voice"});
    });
