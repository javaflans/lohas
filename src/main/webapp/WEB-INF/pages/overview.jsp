<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="components/header.jsp" />
<!-- 表頭資料區 -->
<input type="hidden" id="pageLabel" value="${menus.get(0).getLabel()}"/>

<kendo:grid name="grid" sortable="true"
	filterable="true" style="height:450px;" columnMenu="true" selectable="multiple row">
	<kendo:grid-columns>
		<kendo:grid-column title="姓名" width="120" field="linkedName" 
			template="<div class='customer-photo' style='background-image: url(resources/photo/resident/#:data.residentPhotoPath#);'></div><div class='customer-name'>#:data.linkedName #</div>">
		</kendo:grid-column>
		<kendo:grid-column-group title="狀態" >
			<kendo:grid-column-group-columns>
				<kendo:grid-column title="狀態圖示" width="180" field="locatLV1" 
					template="#
						if(data.isLinked){
						#<div class='customer-photo' style='background-image: url(resources/data/status/linked.png);'></div>#
						} else {
						#<div class='customer-photo' style='background-image: url(resources/data/status/disLinked.png);'></div>#
						}
						if(data.isBeacon && data.beaconID != '00000'){
						#<div class='customer-photo' style='background-image: url(resources/data/status/beacon.png);'></div>#
						}else{
						#<div class='customer-photo' style='background-image: url(resources/data/status/no_beacon.png);'></div>#
						}
						if(data.isFreezed2){
						#<div class='customer-photo' style='background-image: url(resources/data/status/freezed2.png);'></div>#
						} else if(data.isFreezed1){
						#<div class='customer-photo' style='background-image: url(resources/data/status/freezed1.png);'></div>#
						} 
						if(data.isGPS){
						#<div class='customer-photo' style='background-image: url(resources/data/status/gps.png);'></div>#
						}
						if(data.isLost){
						#<div class='customer-photo' style='background-image: url(resources/data/status/lost.png);'></div>#
						}
						if(data.isLowBattery){
						#<div class='customer-photo flash-effect' style='background-image: url(resources/data/status/low_battery.png);'></div>#
						}
						if(data.isSOS){
						#<div class='soscontent'><div class='customer-photo sos' style='background-image: url(resources/data/status/sos.png);'></div></div>#
						}
					#" />				
				<kendo:grid-column title="活動內容" width="130" field="eventType" />
			</kendo:grid-column-group-columns>
		</kendo:grid-column-group>
		<kendo:grid-column-group title="住民資訊">
			<kendo:grid-column-group-columns>
				<kendo:grid-column title="資料時間" width="165" field="tagTime" />
				<kendo:grid-column title="性別" width="65" field="residentSex" />
				<kendo:grid-column title="生日" width="100" field="residentBirthday" />
			</kendo:grid-column-group-columns>
		</kendo:grid-column-group>
		<kendo:grid-column-group title="定位資訊">
			<kendo:grid-column-group-columns>
				<kendo:grid-column title="位置" width="180" field="locatLV1" 
					template="#:data.locatLV1##if(data.locatLV2!=''){#/#}##:data.locatLV2#" />
			</kendo:grid-column-group-columns>
		</kendo:grid-column-group>
		<kendo:grid-column-group title="聯絡人資訊">
			<kendo:grid-column-group-columns>
				<kendo:grid-column title="聯絡人" width="80" field="contactLocalName" />
				<kendo:grid-column title="Mail" width="100" field="contactMail" />
				<kendo:grid-column title="聯絡電話" width="100" field="contactPhone" />
				<kendo:grid-column title="聯絡地址" width="240" field="contactCity" 
					template="(#:data.contactZipCode#)#:data.contactCity##:data.contactArea##:data.contactAddress#" />
			</kendo:grid-column-group-columns>
		</kendo:grid-column-group>
	</kendo:grid-columns>
	<kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5">
	</kendo:grid-pageable>
	<kendo:dataSource pageSize="20">
		<kendo:dataSource-schema>
			<kendo:dataSource-schema-model>
				<kendo:dataSource-schema-model-fields>
					<kendo:dataSource-schema-model-field name="tagTime"	type="string" />
					<kendo:dataSource-schema-model-field name="linkedName"	type="string" />
					<kendo:dataSource-schema-model-field name="residentSex" type="string" />
					<kendo:dataSource-schema-model-field name="residentBirthday" type="string" />
					<kendo:dataSource-schema-model-field name="contactLocalName" type="string" />
					<kendo:dataSource-schema-model-field name="contactMail" type="string" />
					<kendo:dataSource-schema-model-field name="contactPhone" type="string" />
					<kendo:dataSource-schema-model-field name="contactCity" type="string" />
					<kendo:dataSource-schema-model-field name="locatLV1" type="string" />
					<kendo:dataSource-schema-model-field name="eventType" type="string" />
				</kendo:dataSource-schema-model-fields>
			</kendo:dataSource-schema-model>
		</kendo:dataSource-schema>
		<kendo:dataSource-transport>
			<kendo:dataSource-transport-read type="POST" url="api/getTrackHistory" />
		</kendo:dataSource-transport>
	</kendo:dataSource>
</kendo:grid>

<!-- 引入 本程式的 JS, CSS -->

<script src="<c:url value='/resources/js/${menuName}.js'/>" type="text/javascript" ></script>
<link   href="<c:url value='/resources/css/${menuName}.css' />" type='text/css' rel="stylesheet" />

<jsp:include page="components/footer.jsp" />

