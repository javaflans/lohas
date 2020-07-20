eventGridTooltip = {
	filter : "tr", // this filter selects the second column's cells
	position : "top",
	autoHide : true,
	show : function() {
		var thisHeight = $(this.popup.wrapper).height() + 5;
		var thisWidth = $(this.popup.wrapper).width() / 2;
		$(this.popup.wrapper).css({
			top : lastMouseY - thisHeight,
			left : lastMouseX - thisWidth
		});
	},
	width : "auto",
	height : "auto",
	content : function(e) {
		var data = $("#grid").data("kendoGrid")
				.dataItem(e.target.closest("tr"));
		var dataInfo = '<table class="gridTooltip">';
		dataInfo += "<tr><td nowrap='nowrap'>活動內容說明:</td></tr>";
		dataInfo += "<tr><td nowrap='nowrap'>"
				+ data.eventDesc + "</td></tr>";
		dataInfo += "</table>";
		var content = dataInfo;
		return content;
	}
}


messageConf = {
	height : "auto",
	width : "auto",
	autoHideAfter : 1500,
	animation : {
		open : {
			effects : "slideIn:left"
		},
		close : {
			effects : "slideIn:left",
			reverse : true
		}
	},
	position : {
		pinned : false,
		top : 0,
		left : null,
		bottom : null,
		right : 0
	}
}


getSingleChartConf = function(filePath) {
	var source = $.getJSON(filePath);
	
}

patiChartDataSource = new kendo.data.DataSource({
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

patiChartConf = {
	chartArea: {
		background:"transparent", // chartArea CSS 的這段設定是能疊上背景圖的關鍵!! 
		opacity: "0.2",	
	},
    title: {
        text: "高雄榮總 日照中心"
    },
    legend: {
        position: "bottom"
    },
    seriesDefaults: {
    	type: "scatter"
    },
    dataSource: patiChartDataSource,
    series: [{        	
      	xField: "posX",
      	yField: "posY",
    	markers:{
    		type: function (e) {
 			   return "circle";           		
     	},
    		rotation: "180",
    		size: 8    
	    }
	}],       	
    xAxis: {
        min: 0,
    	max: 900,
        visible: true
    },
    yAxis: {
        min: 0,
    	max: 600,
        visible: true
    }
}

multipleChartConf = {

}

Clock = kendo.Class.extend({
    init: function(container, options) {
        this.options = $.extend({}, this.options, options);
        this._center = Point.create(this.options.center);
        this._render(container);
        this.set(new Date());
    },

    options: {
        center: [0, 0],
        size: 100,
        offset: 0,
    },

    set: function(date) {
        var center = this._center;

        var seconds = date.getMilliseconds() / 1000;
        seconds += date.getSeconds();
        seconds += date.getMinutes() * 60;
        seconds += (this.options.offset + date.getHours()) * 60 * 60;

        var angle = (360 / 60) * seconds;
        this._seconds.transform(geom.transform().rotate(angle, center));

        angle = (360 / (60 * 60)) * seconds;
        this._minutes.transform(geom.transform().rotate(angle, center));

        angle = (360 / (12 * 60 * 60)) * seconds;
        this._hours.transform(geom.transform().rotate(angle, center));
    },

    _render: function(container) {
        // Stacking order matches element order
        var root = new Group();
        root.append(
            this._renderFrame(),
            this._renderTicks(),
            this._renderHands(),
            this._renderLabels(),
            this._renderTitle()
        );

        container.append(root);
    },

    _renderHands: function() {
        var center = this._center;
        this._seconds = new Path()
            .stroke("red", 1)
            .moveTo(center).lineTo(center.x, this._fromTop(0.05));

        this._minutes = new Path({
            stroke: {
                color: "black",
                width: 3,
                lineCap: "round",
                opacity: 0.6
            }
        }).moveTo(center).lineTo(center.x, this._fromTop(0.04));

        this._hours = new Path({
            stroke: {
                color: "black",
                width: 6,
                lineCap: "round",
                opacity: 0.6
            }
        }).moveTo(center).lineTo(center.x, this._fromTop(0.25));

        var hands = new Group();
        hands.append(this._seconds, this._minutes, this._hours);

        return hands;
    },

    _renderTicks: function () {
        var center = this._center;
        var ticks = new Group();

        for (var i = 0; i < 360; i += 30) {
            var p1 = new Point(center.x, this._fromTop(0.03)).rotate(i, center);
            var p2 = new Point(center.x, this._fromTop(0.055)).rotate(i, center);
            ticks.append(new Path({
                stroke: {
                    color: "#333",
                    width: 1
                }
            }).moveTo(p1).lineTo(p2));
        }

        return ticks;
    },

    _renderLabels: function() {
        var center = this._center;
        var labels = new Group();
        var font = "bold " + this.options.size * 0.08 + "px arial, helvetica, sans-serif";

        var hours = 12;
        for (var i = 0; i < 360; i += 90) {
            var pos = new Point(center.x, this._fromTop(0.12)).rotate(i, center);
            var text = new Text(hours, pos, {
                font: font,
                fill: {
                    color: "#333"
                }
            });

            this._centerText(text);

            hours = (hours + 3) % 12;
            labels.append(text);
        }

        return labels;
    },

    _renderFrame: function() {
        var frame = new Group();
        var size = this.options.size;

        var outerCircle = new geom.Circle(this._center, (size / 2) - 4);
        var innerCircle = new geom.Circle(this._center, size * 0.01);

        frame.append(
            new Circle(outerCircle, {
                stroke: {
                    color: "#333",
                    width: 1
                }
            }),
            new Circle(innerCircle).fill("black")
        );

        return frame;
    },

    _renderTitle: function() {
        var group = new Group();
        var title = this.options.title;

        if (title) {
            var pos = [this._center.x, this._fromTop(-0.1)];
            var text = new Text(title, pos, {
                font: "bold 16px arial",
                fill: {
                    color: "#333"
                }
            });

            this._centerText(text);
            group.append(text);
        }

        return group;
    },

    _centerText: function(text) {
        var bbox = text.bbox();
        text.position().translate(-bbox.width() / 2, -bbox.height() / 2);
    },

    _fromTop: function(ratio) {
        var size = this.options.size;
        var top = this._center.y - size / 2;
        return top + size * ratio;
    }
});