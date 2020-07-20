<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>

<jsp:include page="components/header.jsp" />
<!-- 表頭資料區 -->
<input type="hidden" id="breadCrumbLabel" value="${breadCrumb}"/>
<input type="hidden" id="pageLabel" value="${pageLabel}"/>

<!-- 群組 -->
<div id="chart1"></div>

<p id="series_demo"></p>

<p id="series_demo_add"></p>


<span id="message"></span>
<span id="toolTip"></span>

<!-- 引入 本程式的 JS, CSS -->
<script src="resources/js/${menuName}.js" type="text/javascript" ></script>
<link   href="resources/css/${menuName}.css" type='text/css' rel="stylesheet" />

<jsp:include page="components/footer.jsp" />

