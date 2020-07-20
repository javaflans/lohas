$(document).ready(function() {
	
	// 處理麵包屑及本頁表頭
	$("#breadcrumb").html('<li><a href="javascript:;" onclick="return home_action(this);"><i class="fa fa-home"></i> Home</a></li>'+
			'<li class="active">'+$("#breadCrumbLabel").val()+'</li>');
	$("#page-heading").find('h1').html($("#pageLabel").val());
	
	
	$('#eventForm input').keypress(function(e) {
	    code = e.keyCode ? e.keyCode : e.which; // in case of browser compatibility
	    if(code == 13) { 
	        e.preventDefault();
	    }
	});
	
	
	//設定活動表單驗證規則
	eventForm = $('#eventForm');
	eventVerifyObj = eventForm.kendoValidator({
		rules: {
			verifyEventDateRequired: function(input){
				if (input.is("[name=eventDate]")){
					if(input.val() == ''){
						return false;
					}else {
						return true;
					}
				} else {
					return true;
				}
			},
			verifyEventStartTimeRequired: function(input){
				if (input.is("[name=eventStartTime]")){
					if(input.val() == ''){
						return false;
					}else {
						return true;
					}
				} else {
					return true;
				}
			},
			verifyEventEndTimeRequired: function(input){
				if (input.is("[name=eventEndTime]")){
					if(input.val() == ''){
						return false;
					}else {
						return true;
					}
				} else {
					return true;
				}
            },
            verifyEventStartTime: function(input){
				if (input.is("[name=eventStartTime]")){
					startTime = kendo.parseDate(input.val());
					endTime = kendo.parseDate($('#eventEndTime').val());
					if(startTime < endTime){
						return true;
					}else{
						return false;
					}
				} else {
					return true;
				}
            },
            verifyEventEndTime: function(input){
				if (input.is("[name=eventEndTime]")){
					startTime = kendo.parseDate($('#eventStartTime').val());
					endTime = kendo.parseDate(input.val());
					if(startTime < endTime){
						return true;
					}else{
						return false;
					}
				} else {
					return true;
				}
            }
        },
        messages:{
        	verifyEventDateRequired: "請填寫活動日期",
        	verifyEventStartTimeRequired: "請填寫活動起始時間",
        	verifyEventEndTimeRequired: "請填寫活動截止時間",
        	verifyEventEndTime: "起始時間不得前於截止時間",
        	verifyEventStartTime: "起始時間不得前於截止時間",
        	required: "請填寫欄位"
        }
    }).data("kendoValidator");
	
	
	//活動設定表單送出
	$('#eventSubmit').click(function(){
		if (eventVerifyObj.validate()) {
			var jsonDataArray = new Array();
			var jsonData = {};
			jsonData["eventType"] = $.trim($('#eventType').val().replace(" ",""));
			jsonData["eventDesc"] = $.trim($('#eventDesc').val().replace(" ",""));
			jsonData["eventDate"] = kendo.parseDate($('#eventDate').val());
			jsonData["eventStartTime"] = kendo.parseDate($('#eventStartTime').val());
			jsonData["eventEndTime"] = kendo.parseDate($('#eventEndTime').val());
			jsonData["beaconID"] = $.trim($('#beaconID').val().replace(" ",""));
			jsonDataArray.push(jsonData);
			
			
			$.ajax({
	            type: 'POST',
	            contentType : "application/json",
	            url: "api/eventFormSubmit",
	            data: JSON.stringify(jsonDataArray), 
	            dataType: 'json',
	            timeout : 100000,
	            success: function(data, result) {
	            	if(data.code!=200){
	            		$('.eventStatus').html("資料建立結果: " + data.message)
	            		.removeClass("valid").addClass("invalid");
	            	} else if(data.code == 200){
	            		$("#grid").data("kendoGrid").dataSource.read();
	            		$("#eventForm input[name='eventType'], #eventForm textarea").val('');
	            	} else {
	            		registVerifyObj.hideMessages();
	            	}
	            },
	            error: function(e) {
	            	alert(e.tostring());
	            }
	        });
		} else {
			$('.eventStatus').html("請重新輸入以上提示修正資料內容.")
			.removeClass("valid").addClass("invalid");
		}
		return false;
	});
	
	//For Grid
	grid = $("#grid").data("kendoGrid");
	msgInit();
	
	$("#grid").kendoTooltip(eventGridTooltip).data("kendoTooltip");
});

