$(function(){
    var ctx = localStorage.getItem("defaultUrl");
    if(!ctx){
        ctx = $('#ctx').val() || "";
        localStorage.setItem("defaultUrl", ctx);
    }
    new Header({ctx:ctx, nowpage: "vediosearch"});

    $('.goregister').click(function(){
        window.location.href = ctx + '/contact/goregister';
    })
});
