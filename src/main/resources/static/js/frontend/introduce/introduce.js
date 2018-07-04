$(function(){
	var ctx = localStorage.getItem("defaultUrl");
	if(!ctx){
		ctx = $('#ctx').val() || "";
		localStorage.setItem("defaultUrl", ctx);
	}
	new Header({ctx:ctx, nowpage: "introduce"});
	$(document).on("click", ".goregister", function(){
		window.open(ctx + '/contact/goregister', "_blank");
	});
	
});
