$(document).ready(function() {
	
	// 處理麵包屑及本頁表頭
	$("#breadcrumb").html('<li><a href="javascript:;" onclick="return home_action(this);"><i class="fa fa-home"></i> Home</a></li>');
	$("#page-heading").find('h1').html($("#pageLabel").val());
	
	grid = $("#grid").data("kendoGrid");
	msgInit();
	
	//閃爍效果 flash-effect
	var pauseSec = 1100;
	var speed = 300;
	setInterval(function simpleTimer() {
		
		// 特效說明
		//$(element).pulsate({
		//  color: $(this).css("background-color"), // set the color of the pulse
		//  reach: 20,                              // how far the pulse goes in px
		//  speed: 1000,          // how long one pulse takes in ms
		//  pause: 0,             // how long the pause between pulses is in ms
		//  glow: true,           // if the glow should be shown too
		//  repeat: true,         // will repeat forever if true, if given a number will repeat for that many times
		//  onHover: false        // if true only pulsate if user hovers over the element
		//});
		
		$(".flash-effect").fadeOut(100).fadeIn(100);
		$(".sos").closest('td').pulsate({
			pause: pauseSec,
			color: "#9b59b6",
			speed: speed,
			repeat: true,
			reach: 8,
			});
	}, pauseSec);
	
	//Grid reload by 5 sec
	setInterval(function simpleTimer() {
		$("#grid").data("kendoGrid").dataSource.read();
	},15000);
	
});

