$(document).ready(function() {
	// 處理麵包屑及本頁表頭
	$("#breadcrumb").html('<li><a href="javascript:;" onclick="return home_action(this);"><i class="fa fa-home"></i> Home</a></li>'+
			'<li class="active">'+$("#breadCrumbLabel").val()+'</li>');
	$("#page-heading").find('h1').html($("#pageLabel").val());
	
	createChart1(); // 以js方式產生群組 scatter chart 方式
	
	setInterval(function simpleTimer() {
		patiChartDataSource.read();
		$('#chart1').data("kendoChart").refresh();
	},15000);
		
});

$(document).bind("kendo:skinChange", createChart1);

var locationSeriesData=[];
var xAxisVisible = true;
var yAxisVisible = true;

function createChart1(){
	$("#chart1").kendoChart(patiChartConf);
}

function refreshChart() {	    
    // 更新 群組 data source
    var chart1 = $("#chart1").data("kendoChart");
	var dataSource1 = new kendo.data.DataSource( {
        transport: {
            read: {
                url: "api/getTrackHistoryDynamic",
                dataType: "json"
            }
        },
        group: {
            field: "linkedName"
        },
        sort: {
            field: "linkedName",
            dir: "asc"
        }
    });
	chart1.setDataSource(dataSource1);
}


