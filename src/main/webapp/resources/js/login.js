registVerifyObj = null;
registValidate = false;
cityArea = new CityArea('city_select', 'area_select', "street_select", $('#milkmidiUrl').attr('href')+'/');
citySelected=null;
areaSelected=null;
registDialog = null;
forgetDialog = null;
regist = null;
forget = null;


$(function() {
	
	registDialog = $('#registDialog'), forgetDialog = $('#forgetDialog'), regist = $('#regist'), forget = $('#forget');
	
	var registForm = $("#registForm");
	//註冊帳號Dialog 點擊觸發事件
	regist.click(function() {
		kendo.init(registForm);
		registVerifyObj.hideMessages();
		$('#registForm input').val('');
		$('#registForm select').val(-1);
		$('.registStatus').html('');
		$("#registContent").show();
		registDialog.data("kendoDialog").content($("#registContent"));
		registDialog.data("kendoDialog").open();
	});
	
	var forgetForm = $("#forgetForm");
	//忘記密碼 Dialog 點擊觸發事件
	forget.click(function(){
		kendo.init(forgetForm);
		forgetVerifyObj.hideMessages();
		$('#forgetForm input').val('');
		$('#forgetForm select').val(-1);
		$('.forgetStatus').html('');
		$("#forgetContent").show();
		forgetDialog.data("kendoDialog").content($("#forgetContent"));
		forgetDialog.data("kendoDialog").open();
	})
	
	//設定註冊帳號表單驗證規則
	registVerifyObj = registForm.kendoValidator({
		rules: {
            verifyPasswords: function(input){
               var ret = true;
               if (input.is("[name=userMail]")){
            	   $('#userMail').val(input.val());
               }
               if (input.is("[name=city_select option:selected]")){
            	   $('#city_select').text(input.text());
               }
               if (input.is("[name=area_select option:selected]")){
            	   $('#area_select').text(input.text());
               }
               if (input.is("[name=userPassword]")) {
            	   $('#userPassword').val(input.val());
               }
               if (input.is("[name=confirmPassword]")) {
                   ret = (input.val() === $("#userPassword").val());
               }
               return ret;
            }
        }
    }).data("kendoValidator");
	
	forgetVerifyObj = forgetForm.kendoValidator().data("kendoValidator");

	
	$('#registContent input,#forgetContent input').keypress(function(e) {
	    code = e.keyCode ? e.keyCode : e.which; // in case of browser compatibility
	    if(code == 13) { 
	        e.preventDefault();
	    }
	});
	

});

//註冊帳號表單送出
function registSubmit(event) {
	if (registVerifyObj.validate()) {
		
		var jsonDataArray = new Array();
		var jsonData = {};
		jsonData["userMail"] = $.trim($('#userMail').val().replace(" ",""));
		jsonData["userName"] = $.trim($('#userName').val().replace(" ",""));
		jsonData["userPassword"] = $.trim($('#userPassword').val().replace(" ",""));
		jsonData["userLocalName"] = $.trim($('#userLocalName').val().replace(" ",""));
		jsonData["userBirthday"] = kendo.parseDate($.trim($('#userBirthday').val()));
		jsonData["userPhone"] = $.trim($('#userPhone').val().replace(" ",""));
		jsonData["userCity"] = $.trim($('#city_select option:selected').text().replace(" ",""));
		jsonData["userArea"] = $.trim($('#area_select option:selected').text().replace(" ",""));
		jsonData["userAddress"] = $.trim($('#userAddress').val().replace(" ",""));
		jsonData["zipCode"] = $.trim($('#zipCode').val().replace(" ",""));
		jsonDataArray.push(jsonData);
		
		$.ajax({
            type: 'POST',
            contentType : "application/json",
            url: "registFormSubmit",
            data: JSON.stringify(jsonDataArray), 
            dataType: 'json',
            timeout : 100000,
            success: function(data, result) {
            	if(data.code!=200){
            		registDialog.data("kendoDialog").open();
            		$('.registStatus').html(data.message)
            		.removeClass("valid").addClass("invalid");
            	} else if(data.code == 200){
            		alert(data.message);
            	} else {
            		registVerifyObj.hideMessages();
            		$('#registForm input').val('');
            		$('#registForm select').val(-1);
            		$('.registStatus').html('');
            	}
            },
            error: function(e) {
            	alert(e.tostring());
            }
        });
		
	} else {
		$('.registStatus').html("請重新輸入以上提示修正資料內容.")
		.removeClass("valid").addClass("invalid");
		event.preventDefault();
	}
	
}

//註冊帳號表單取消
function registCancel(){
	registVerifyObj.hideMessages();
	$('#registForm input').val('');
	$('#registForm select').val(-1);
	$('.registStatus').html('');
}

//重設帳號表單送出
function forgetSubmit(event) {
	if (forgetVerifyObj.validate()) {
		var jsonDataArray = new Array();
		var jsonData = {};
		jsonData["userMail"] = $.trim($('#forgetForm #userMail').val().replace(" ",""));
		jsonDataArray.push(jsonData);
		
		$.ajax({
            type: 'POST',
            contentType : "application/json",
            url: "forgetFormSubmit",
            data: JSON.stringify(jsonDataArray), 
            dataType: 'json',
            timeout : 100000,
            success: function(data, result) {
            	if(data.code!=200){
            		forgetDialog.data("kendoDialog").open();
            		$('.forgetStatus').html(data.message)
            		.removeClass("valid").addClass("invalid");
            	} else if(data.code == 200){
            		alert(data.message);
            	} else {
            		forgetVerifyObj.hideMessages();
            		$('#forgetForm input').val('');
            		$('#forgetForm select').val(-1);
            		$('.forgetStatus').html('');
            	}
            },
            error: function(e) {
            	alert(e.tostring());
            }
        });
		
	} else {
		$('.forgetStatus').html("請重新輸入以上提示修正資料內容.")
		.removeClass("valid").addClass("invalid");
		event.preventDefault();
	}
}

//重設帳號表單取消
function forgetCancel(){
	forgetVerifyObj.hideMessages();
	$('#forgetForm input').val('');
	$('#forgetForm select').val(-1);
	$('.forgetStatus').html('');
}
