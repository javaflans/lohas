<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="components/header.jsp" />
<!-- 表頭資料區 -->
<input type="hidden" id="breadCrumbLabel" value="${breadCrumb}"/>
<input type="hidden" id="pageLabel" value="${pageLabel}"/>

<table>
	<tr>
		<td width="39%">
			<div id="eventContent">
				<form id="eventForm">
					<fieldset id="event">
						<legend>活動資料</legend>
						<table>
							<tr>
								<td colspan="3">
									<div class="labelSpace">
										<label for="eventType" class="required">活動名稱</label>
									</div> 
									<input type="text" id="eventType" name="eventType" class="k-textbox"
									placeholder="活動名稱" required /><br/>
									<span class="k-invalid-msg" data-for="eventType"></span>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div class="labelSpace">
										<label for="eventDesc">活動內容</label>
									</div> 
									<textarea id="eventDesc" name="eventDesc"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div class="labelSpace">
										<label for="eventDesc">活動日期</label>
									</div> 
									<kendo:datePicker name="eventDate" format="yyyy/MM/dd" value="${today}" min="${minDay}" max="${maxDay}" style="width: 100%;">
									</kendo:datePicker><br/>
									<div style="height: 15px">
										<span class="k-invalid-msg" data-for="eventDate"></span>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="labelSpace">
										<label for="eventStartDate">活動起始時間</label>
									</div> 
									<kendo:timePicker name="eventStartTime" format="hh:mm tt" value="${startValue}" min="${startMin}" max="${startMax}"  style="width: 100%;">
									</kendo:timePicker><br/>
									<div style="height: 15px">
										<span class="k-invalid-msg" data-for="eventStartTime"></span>
									</div>
								</td>
								<td><div>　</div></td>
								<td>
									<div class="labelSpace">
										<label for="eventDesc">活動截止時間</label>
									</div> 
									<kendo:timePicker name="eventEndTime" format="hh:mm tt" value="${endValue}" min="${endMin}" max="${endMax}"  style="width: 100%;">
									</kendo:timePicker><br/>
									<div style="height: 15px">
										<span class="k-invalid-msg" data-for="eventEndTime"></span>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div class="labelSpace">
										<label for="eventDesc">活動地點</label>
									</div> 
									<kendo:dropDownList name="beaconID" dataTextField="locatLV1" dataValueField="beaconID" >
								        <kendo:dataSource data="${beaconList}"></kendo:dataSource>
								    </kendo:dropDownList>
							    </td>
							</tr>
						</table>
					</fieldset>
					<span class="eventStatus"></span>
					<br/><br/>
					<kendo:button name="eventSubmit" tag="a" class="eventSubmit">新增活動</kendo:button>
				</form>
			</div>
		</td>
		<td>
			<kendo:grid name="grid" sortable="true"
				filterable="true" style="height:480px;min-height:550px;" columnMenu="true" selectable="multiple row">
				<kendo:grid-columns>
					<kendo:grid-column title="活動名稱" width="105" field="eventType" />
					<kendo:grid-column title="起始時間" field="eventStartDate" format="{0:MM/dd/yyyy HH:mm:ss}" />
					<kendo:grid-column title="截止時間" field="eventEndDate" format="{0:MM/dd/yyyy HH:mm:ss}" />
					<kendo:grid-column title="活動地點" field="locatLV1" template="<div>#:data.locatLV0 #-#:data.locatLV1 #</div>" />
					<kendo:grid-column title="動作" width="90">
		                <kendo:grid-column-command>
		                   <kendo:grid-column-commandItem name="viewDetails" text="刪除">
		                        <kendo:grid-column-commandItem-click>
		                            <script>
		                            function showDetails(e) {
		                            	var jsonDataArray = new Array();
		                                var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
		                                var jsonData = {};
		                    			jsonData["id"] = dataItem.id;
		                                jsonDataArray.push(jsonData);
                                    	
                                       	$.ajax({
                            	            type: 'POST',
                            	            contentType : "application/json",
                            	            url: "api/deleteEventView",
                            	            data: JSON.stringify(jsonDataArray), 
                            	            dataType: 'json',
                            	            timeout : 100000,
                            	            success: function(data, result) {
                            	            	if(data.code == 200){
                            	            		$("#grid").data("kendoGrid").dataSource.read();
                            	            	}
                            	            },
                            	            error: function(e) {
                            	            	alert(e.tostring());
                            	            }
                            	        });
		                            }
		                            </script>
		                        </kendo:grid-column-commandItem-click>
		                   </kendo:grid-column-commandItem>
		                </kendo:grid-column-command>
		            </kendo:grid-column>
				</kendo:grid-columns>
				<kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5">
				</kendo:grid-pageable>
				<kendo:dataSource pageSize="10">
					<kendo:dataSource-schema>
						<kendo:dataSource-schema-model>
							<kendo:dataSource-schema-model-fields>
								<kendo:dataSource-schema-model-field name="eventType"	type="string" />
								<kendo:dataSource-schema-model-field name="eventStartDate" type="string" />
								<kendo:dataSource-schema-model-field name="eventEndDate" type="string" />
								<kendo:dataSource-schema-model-field name="locatLV1" type="string" />
							</kendo:dataSource-schema-model-fields>
						</kendo:dataSource-schema-model>
					</kendo:dataSource-schema>
					<kendo:dataSource-transport>
						<kendo:dataSource-transport-read type="POST" url="api/getEventList" />
						<kendo:dataSource-transport-parameterMap>
			            	<script>
			             		function parameterMap(options) { 
			            			return JSON.stringify(options);
			             		}
			            	</script>
			            </kendo:dataSource-transport-parameterMap>
					</kendo:dataSource-transport>
				</kendo:dataSource>
			</kendo:grid>
			<div style="margin-left: 20px; font-size: 18px; color:#ff8040; font-weight: bolder;">請注意! 顯示結果不為當日過去的資料</div>
		</td>
	</tr>
</table>

	

<!-- 引入 本程式的 JS, CSS -->
<script src="<c:url value='/resources/js/${menuName}.js'/>" type="text/javascript" ></script>
<link   href="<c:url value='/resources/css/${menuName}.css' />" type='text/css' rel="stylesheet" />

<jsp:include page="components/footer.jsp" />

