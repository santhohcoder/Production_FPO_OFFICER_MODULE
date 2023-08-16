var textX = "";
var textY = "";
var textX1 = "";
var textY1 = "";
var data = [];
var noArt = [];
var noAss = [];
var country = [];
var EnoArt = [];
var EnoAss = [];
var Ecountry = [];

var mcCountry = [];
var mcNoAss = [];
var mcAsLtrList = [];
var mcAsValEmsList = [];
var mcAssValPar = [];
var mctotAssValEmp = [];

var cwMcCountry = [];
var cwMcAssValCountry = [];
var cwMcNoVol = [];
var colors = ['#87CEFA', '#C71585', '#FFD700', '#228B22', '#f27474', '#d52222', '#d7f33e', '#2bc9ac'];

var pieColors = ["#ff5959", "#ff7b7b", "#ff8f8f", "#ffa8a8", "#ffc8c8", "#ffe1e1"];
var pieColorsMid = ["#ff8100", "#ff9e3b", "#ffb264", "#ffc488"];
var pieColorsInn = ["#f1ca08", "#f1d33d", "#ebd254", "#f5df71", "#edde94", "#f5e8a8"];

var secPieColors = ["#4d755d", "#69977b", "#86bb9a", "#a1dfb9", "#bff7d4", "#d6fde4"];
var secPieColorsMid = ["#3e829d", "#5395af", "#6ca9c1", "#88c1d7"];
var secPieColorsInn = ["#3598e7", "#51a3e5", "#54a0dd", "#65a7dd", "#76b0df", "#8dbbe1"];

console.log("Chart founded!");
$(function() {
	var eadcount = $("#counts").attr("eadcount");
	var aaacount = $("#counts").attr("aaacount");
	var aafcount = $("#counts").attr("aafcount");
	var total = +eadcount + +aaacount + +aafcount;
	Highcharts.setOptions({
		colors: ['#87CEFA', '#C71585', '#FFD700', '#228B22', '#f27474', '#d52222', '#d7f33e', '#2bc9ac']
	});
	// Create the chart
	chart1 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				},

			}]
		},
		chart: {
			renderTo: 'doughnutChart',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0,

		},
		title: {
			text: 'Total</br>Art.ID in<br> Modules</br>(' + total + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 10,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			width: 1,
			itemStyle: '{ "word-wrap": "break-word"}',
			x: -174

		},
		series: [{
			name: 'Process EAD',
			data: [["EAD(" + eadcount + ")", parseInt(eadcount)], ["AAA(" + aaacount + ")", parseInt(aaacount)], ["AAF(" + aafcount + ")", parseInt(aafcount)]],
			size: '100%',
			innerSize: '65%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			}
		}],
	})
	$(window).resize(function() {
		/*if (this.resizeTO) clearTimeout(this.resizeTO);
		this.resizeTO = setTimeout(function () {*/
		$(this).trigger('resizeEnd');
		console.log('called')
		/*   }, 100);*/
	});

	$(window).bind('resizeEnd', function() {
		var W = this.innerWidth,
			h = $('#doughnutChart').height();

		/*chart1.setSize(W - 20, h, false);
	   if(W < 412) {
			chart1.series[0].update({
				center: ['50%','50%']
			});
		} else {
			chart1.series[0].update({
				center: [h/2 - 23,'50%']
			});
		}*/
	});

});

//donut chart for EAD
$(function() {
	var totcoultr = $('#counts').attr("totcoultr");
	var totcouems = $('#counts').attr("totcouems");
	var totcoupar = $('#counts').attr("totcoupar");
	var totcouemp = $('#counts').attr("totcouemp");
	var total = +totcoultr + +totcouems + +totcoupar + +totcouemp;
	// Create the chart
	chart2 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: 'container',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0
		},
		title: {
			text: 'Mail</br>class<br>(' + total + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 10,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			//width:70,
			itemStyle: '{ "word-wrap": "break-word"}',
			x: -45

		},
		series: [{
			name: 'Process EAD',
			data: [["Letters(U) (" + totcoultr + ")", parseInt(totcoultr)], ["EMS(E) (" + totcouems + ")", parseInt(totcouems)], ["Parcels(C) (" + totcoupar + ")", parseInt(totcoupar)], ["Emp.Recp(T) (" + totcouemp + ")", parseInt(totcouemp)]],
			size: '100%',
			innerSize: '0%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			}
		}],
	})

});

$(function() {
	var totgift = $('#counts').attr("totgift");
	var totsg = $('#counts').attr("totsg");
	var totcs = $('#counts').attr("totcs");
	var totrg = $('#counts').attr("totrg");
	var totdoc = $('#counts').attr("totdoc");
	var tototh = $('#counts').attr("tototh");
	var total = +totgift + +totsg + +totcs + +totrg + +totdoc + +tototh;
	// Create the chart
	chart3 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: 'chartdiv',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0
		},
		title: {
			text: 'Item<br>Category</br>(' + total + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 10,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			itemStyle: '{ "word-wrap": "break-word"}',

		},
		series: [{
			name: 'Item Category',
			data: [["Gift (" + totgift + ")", parseInt(totgift)],
			["Sale Of Goods (" + totsg + ")", parseInt(totsg)], ["Commercial Sample (" + totcs + ")", parseInt(totcs)], [" Returned Goods (" + totrg + ")", parseInt(totrg)],
			[" Documents (" + totdoc + ")", parseInt(totdoc)], [" Others (" + tototh + ")", parseInt(tototh)]],
			size: '100%',
			innerSize: '65%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			}
		}],
	})

});


$(function() {
	var couass = $('#counts').attr("curcouass");
	var couexm = $('#counts').attr("curcouexm");
	var couooc = $('#counts').attr("curcouooc");
	var coudet = $('#counts').attr("curcoudet");
	var couqry = $('#counts').attr("curcouqry");
	var ooccancel = $('#counts').attr("curcouooccancel");
	var total = +couass + +couexm + +couooc + +coudet + +ooccancel + +couqry;
	// Create the chart
	chart4 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: 'chartdivAAF',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0
		},
		title: {
			text: 'Articles<br>Arrived<br>at FPO </br>(' + total + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 10,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
				startAngle: -90,
				endAngle: 90,
			},

		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			itemStyle: '{ "word-wrap": "break-word"}',
			x : -50
		},
		series: [{
			name: 'aaf',
			data: [["Assess (" + couass + ")", parseInt(couass)], ["Query (" + couqry + ")", parseInt(couqry)],["Exam (" + couexm + ")", parseInt(couexm)], ["OOC (" + couooc + ")", parseInt(couooc)], ["Detain (" + coudet + ")", parseInt(coudet)],["OOC Cancel (" + ooccancel + ")", parseInt(ooccancel)]],
			size: '100%',
			innerSize: '65%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			}
		}],
	})

});

$(function() {
	var personalImports = $('#counts').attr("personalImports");
	var commercialImports = $('#counts').attr("commercialImports");
	chart5 = new Highcharts.Chart({
		chart: {
			colors: ['rgb(241, 92, 128)'],
			renderTo: 'import-container',
			type: 'column',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			style: {
				color: '#fff',
			}
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		title: {
			text: '',
			style: {
				color: '#fff'
			}
		},
		xAxis: {
			tickWidth: 0,
			labels: {
				style: {
					color: '#000',
				}
			},
			categories: ['Personal Imports(' + personalImports + ')', 'Commercial Imports(' + commercialImports + ')']
		},
		yAxis: {
			gridLineWidth: .5,
			gridLineDashStyle: 'dash',
			gridLineColor: 'black',
			title: {
				text: '',
				style: {
					color: '#000'
				}
			},
			labels: {
				formatter: function() {
					return Highcharts.numberFormat(this.value, 0, '', ',');
				},
				style: {
					color: '#000',
				}
			}
		},
		legend: {
			enabled: false,
		},
		credits: {
			enabled: false
		},
		tooltip: {
			valuePrefix: ''
		},
		plotOptions: {
			column: {
				borderRadius: 0,
				pointPadding: 0,
				groupPadding: 0.05
			}
		},
		series: [{
			name: 'imports',
			data: [parseInt(personalImports), parseInt(commercialImports)]
		}]
	});
})

$(function() {
	var len = 0;
	$.each(pincodeCount, function(k, v) {
		var list = ['' + v.cus_site + '(' + v.no_articles + ')', parseInt(v.no_articles)];

		if (len < v.no_articles) {
			len = v.no_articles;
		}

		data.push(list);
	})

	chart6 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: 'pchart-container',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 170, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0
		},
		title: {
			text: "Articles Mapped and Not Mapped To FPO Sites",
			y: 180,
			x: -80,
			style: {
				fontSize: 'small',
				fontWeight: 'bold'
			}
		},
		plotOptions: {
			pie: {
				shadow: false,
				startAngle: -90,
				endAngle: 90,
			},
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			itemStyle: '{ "word-wrap": "break-word"}',
			width: 100,
			x: -35

		},

		series: [{
			name: 'aaf',
			data: data,
			size: '100%',
			innerSize: '0%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			},
			center: ['50%', '50%']
		}],

	})
	colors = [{
		radialGradient: {
			cx: 0.5,
			cy: 0.3,
			r: 0.7
		}, stops: [
			[0, 'rgb(255, 255, 255)'],
			[1, 'rgb(242, 116, 116)']
		]
	}];
	var point = chart6.series[0].data[chart6.series[0].data.length - 1];
	point.select();
	point.update(
		point.y += len + 5, point.color = colors[0]
	);

	chart6.tooltip.refresh(point);
});


//bar-chart-pending country----
/*$(function() {
	
	$.each(countrywisePendingArticles, function(k, v) {
		var cList = [v.country];
		country.push(cList);
		var asList = [v.totAssVal];
		noAss.push(asList);
		var arList = [v.noArticles];
		noArt.push(arList);
	})


	chart7 = new Highcharts.chart('pendingCtrContainer', {
		chart: {
			type: 'column',
			renderTo: 'pendingCtrContainer',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
		},
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		title: {
			text: ''
		},
		xAxis: {
			categories: country
		},
		yAxis: [{
			min: 0,
			}],
		legend: {
			shadow: false
		},
		tooltip: {
			shared: true
		},
		plotOptions: {
			column: {
				grouping: false,
				shadow: false,
				borderWidth: 0
			}
		},
		series: [{
			name: 'No-Of-Articles',
			color: 'rgba(165,170,217,1)',
			data: noArt,
			pointWidth: 30
		}, {
			name: 'Assess value',
			color: 'rgba(126,86,134,.9)',
			data: noAss,
			pointWidth: 10
		}]
	});
})
//line chart eadInbound
$(function() {
	
	$.each(eadInboundArticles, function(k, v) {
		var cList = [v[0]];
		Ecountry.push(cList);
		var asList = [v.totAssVal];
		EnoAss.push(asList);
		var arList = [v.noArticles];
		EnoArt.push(arList);
	})
	
	chart8 = new Highcharts.chart({
		chart: {
			type: 'line',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			renderTo:eadIBContainer
		},
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		title: {
			text: 'EAD INBOUND ARTICLES'
		},
		xAxis: {
			categories: Ecountry
		},
		plotOptions: {
			line: {
				dataLabels: {
					enabled: true
				},
				enableMouseTracking: false
			}
		},
		series: [{
			name: 'No-Of-Articles',
			data: EnoArt
		}, {
			name: 'Assess value',
			data: EnoAss
		}]
	});
})*/

//------



//donut-ooc-percentage
$(function() {
	var withDutyArticles = $('#counts').attr("withDutyArticles");
	//var noDutyArticles = $('#counts').attr("noDutyArticles");
	var noArticles = $('#counts').attr("noArticles");
	var dutyPayable = $('#counts').attr("dutyPayable");
	var withDutyPercentage = $('#counts').attr("withDutyPercentage");
	//var noDutyPercentage = $('#counts').attr("noDutyPercentage");
	var lastMonth = $('#counts').attr("lastMonth");
	var noDutyConcession = $('#counts').attr("noDutyConcession");
	var noDutyConcessionPercentage = $('#counts').attr("noDutyConcessionPercentage");
	//var noDutyPayable = $('#counts').attr("noDutyPayable");
	//var totAmount = $('#counts').attr("totAmount");
	//totAmount=3400;
	//dutyPayable=3400;
	//noArticles=18;
	// Create the chart
	/*chart9 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: 'oocPrContainer',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0
		},
		title: {
			text: 'Total No-Of<br>Articles OOC Given </br>' + lastMonth + ' = ' + noArticles + '<br>Duty payable=' + dutyPayable + '<br>No Duty ='+noDutyPayable+'<br>No Duty Concession=' + totAmount,
			floating: true,
			style: {
				fontSize: 'smaller',
			},
			verticalAlign: 'middle',
			y: 4,
			x: -75
		},
		plotOptions: {
			pie: {
				shadow: false,
				center: ['60%', '50%']
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> %';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			itemStyle: '{ "word-wrap": "break-word"}',

		},
		series: [{
			name: 'Ooc Percentage',
			data: [["With Duty (" + withDutyPercentage + "%)", parseInt(withDutyPercentage)],
			["No Duty (" + noDutyPercentage + "%)", parseInt(noDutyPercentage)],
			["No Duty Concession (" + noDutyConcessionPercentage + "%)", parseInt(noDutyConcessionPercentage)]],
			size: '100%',
			innerSize: '65%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			}
		}],
	})*/
	chart9 = new Highcharts.Chart({
		chart: {
			type: 'bar',
			renderTo: 'oocPrContainer',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
		},
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		title: {
			text: 'Total No-Of<br>Articles OOC Given for ' + lastMonth,
			style: {
				fontSize: 'small',
				fontWeight: 'bold'
			}
		},
		subtitle:{
			text: 'No-of Articles - '+noArticles,
			style: {
				fontSize: 'small',
				fontWeight: 'bold'
			}
		},
		xAxis: {
			categories: ['Articles']
		},
		yAxis: {
			min: 0,
			title: {
				text: ' '
			}
		},
		legend: {
			reversed: true
		},
		plotOptions: {
			series: {
				stacking: 'normal',
				/*dataLabels: {
					enabled: true,
						let plotSize = this.series.chart.plotSizeY;
					formatter: function() {
						if (this.point.yBottom !== plotSize) {
							return this.point.stackTotal;
						}
					}
				}*/
			}
		},
		
		tooltip: {
			headerFormat: '<b>{point.x}</b><br/>',
			pointFormat: '{series.name}<br/>'
		},
		series: [{
			name: 'Without Duty('+noDutyConcession+') - ( < 1k INR value) - ('+noDutyConcessionPercentage+'%)',
			data: [parseInt(noDutyConcessionPercentage)]
			//data:[9]
		},{
			name: 'With Duty('+withDutyArticles+') - ('+dutyPayable+' Duty Amount (INR) ) - ('+withDutyPercentage+'%)',
			data: [parseInt(withDutyPercentage)]
			//data:[9]
		}]
	});
});

//countrywiseMailclass
/*$(function() {
	$.each(countryWiseMailClass, function(k, v) {
		var coList = [v.country];
		mcCountry.push(coList);
		var asList = [v.totAssVal];
		mcNoAss.push(asList);
		var asltrList = [v.totAssValLtr];
		mcAsLtrList.push(asltrList);
		var assvalemsList = [v.totAssValEms];
		mcAsValEmsList.push(assvalemsList);
		var totAssValParList = [v.totAssValPar];
		mcAssValPar.push(totAssValParList);
		var totAssValEmpList = [v.totAssValEmp];
		mctotAssValEmp.push(totAssValEmpList);
	})
	chart10 = new Highcharts.Chart({
		chart: {
			type: 'column',
			renderTo: 'countryWMCContainer'
		},
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		title: {
			text: 'Top 5 Countries Mail Class(Assess value)'
		},
		xAxis: {
			categories: mcCountry
		},
		yAxis: {
			min: 0,
			/*stackLabels: {
				enabled: false,
				style: {
					fontWeight: 'bold',
					color: ( // theme
						Highcharts.defaultOptions.title.style &&
						Highcharts.defaultOptions.title.style.color
					) || 'gray'
				}
			}
		},
		legend: {
			align: 'right',
			x: -10,
			verticalAlign: 'top',
			y: 25,
			floating: true,
			backgroundColor:
				Highcharts.defaultOptions.legend.backgroundColor || 'white',
			borderColor: '#CCC',
			borderWidth: 1,
			shadow: false
		},
		tooltip: {
			headerFormat: '<b>{point.x}</b><br/>',
			pointFormat: '{series.name}: {point.y}<br/>'
		},
		plotOptions: {
			column: {
				stacking: 'normal',
				dataLabels: {
					enabled: true,
					formatter: function() {
						let plotSize = this.series.chart.plotSizeY;

						if (this.point.yBottom !== plotSize) {
							return this.point.stackTotal;
						}
					}
				},
				events: {
					legendItemClick: function() {
						var index = this.index;
						if (this.visible || this.name == "Total Assess Value") {
							if (this.name == "Total Assess Value") {
								$.each(this.chart.series, function(key, value) {
									value.setVisible(true);
								})
								this.setVisible(false);
								return false
							} else {
								$.each(this.chart.series, function(key, value) {
									if (value.index != index)
										value.setVisible(false);
								})
								this.setVisible(true);
								return false;
							}
						} else {
							$.each(this.chart.series, function(key, value) {
								if (value.index != index)
									value.setVisible(false);
							})
							this.setVisible(true);
							return false;
						}
					}


				},
			}


		},
		series: [{
			name: 'Total Assess Value',
			data: mcNoAss,
			pointWidth: 30,
			visible: false
		}, {
			name: 'Total value of letters',
			data: mcAsLtrList,
			pointWidth: 30,
			//visible: false
		}, {
			name: 'Total value of EMS',
			data: mcAsValEmsList,
			pointWidth: 30,
			//visible: false
		}, {
			name: 'Total value of parcel',
			data: mcAssValPar,
			pointWidth: 30,
			//visible: false
		}, {
			name: 'Total value of EMP',
			data: mctotAssValEmp,
			pointWidth: 30,
			//visible: false
		}
		]
	})
})*/

//countrywse Multiple:
function chart12Popup(chartId) {
	var colorshade = [];
	//var colrcode=488091;
	/*var pieColors =["#ff0000","#ff3c3c","#ff5a5a","#ff7979","#ffa9a9","#ffe1e1"];(function() {
				var colors = [],
					base = "#ff0000",
					i;
				for (i = 0; i < 10; i += 1) {
					// Start out with a darkened base color (negative brighten), and end
					// up with a much brighter color
					colors.push(Highcharts.Color(base).brighten((i - -1) / 7).get());
				}

				return colors;

			}());
	var pieColorsMid = ["#ff8100","#ff9e3b","#ffb264","#ffc488"];(function() {
				var colors = [],
					base ="#ffa500",
					i;
				for (i = 0; i < 10; i += 1) {
					colors.push(Highcharts.Color(base).brighten((i - 2) / 7).get());
				}

				return colors;

			}());
	 var pieColorsInn = ["#f1ca08","#f1d33d","#ebd254","#f5df71","#edde94","#f5e8a8"];(function() {
				var colors = [],
					base = "#ffff00",
					i;
				for (i = 0; i < 10; i += 1) {
					colors.push(Highcharts.Color(base).brighten((i - -1) / 7).get());
				}

				return colors;

			}());*/
	var lastMonth = "";
	cwMcCountry = [];
	$.each(countryWisePercentage, function(k, v) {
		conList = [v.country + '<br>(' + v.noOfValue + ')<br>' + v.volumePercentage + '%', parseInt(v.volumePercentage)];
		cwMcCountry.push(conList);
		totalArt = v.totArt;
		lastMonth = v.lastMonth;

	})


	var letters = $('#counts').attr("lettersCWS");
	var ems = $('#counts').attr("emsCWS");
	var parcel = $('#counts').attr("parcelsCWS");
	var empRecep = $('#counts').attr("empRecepCWS");

	var lettersValue = $('#counts').attr("ltrValCWS");
	var emsValue = $('#counts').attr("emsValCWS");
	var parcelValue = $('#counts').attr("parValCWS");
	var empRecepValue = $('#counts').attr("empRecepValCWS");



	var gift = $('#counts').attr("giftCWS");
	var saleOfGoods = $('#counts').attr("saleOfGoodsCWS");
	var returnedGoods = $('#counts').attr("returnedGoodsCWS");
	var commercialSample = $('#counts').attr("commercialSampleCWS");
	var documents = $('#counts').attr("documentsCWS");
	var others = $('#counts').attr("othersCWS");

	var giftValue = $('#counts').attr("giftValCWS");
	var saleOfGoodsValue = $('#counts').attr("saleOfGoodsValCWS");
	var returnedGoodsValue = $('#counts').attr("returnedGoodsValCWS");
	var commercialSampleValue = $('#counts').attr("commercialSampleValCWS");
	var documentsValue = $('#counts').attr("documentsValCWS");
	var othersValue = $('#counts').attr("othersValCWS");


	chart12 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: chartId,
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
		},
		title: {
			text: 'Total<br>Number of<br>Articles During<br>' + lastMonth + '<br>=' + totalArt,
			floating: true,
			style: {
				fontSize: 'smaller',
				fontWeight: 'Bold'
			},
			verticalAlign: 'middle',
			y: 10,
			x: -110
		},
		plotOptions: {
			pie: {
				shadow: false,
				colors: pieColors
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> %';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			itemStyle: '{ "word-wrap": "break-word"}',
			enabled: true
		},
		series: [{
			name: 'Outer',
			data: cwMcCountry,
			size: '100%',
			innerSize: '75%',
			showInLegend: false,
			dataLabels: {
				enabled: true,
				distance: -25,
				itemStyle: '{ "word-wrap": "break-word"}',
				style: {
					fontSize: 9
				}
			}
		}, {
			name: 'Middle',
			data: [['Letters<br>' + lettersValue + '<br>' + letters + '%', parseInt(letters)], ['EMS<br>' + emsValue + '<br>' + ems + '%', parseInt(ems)],
			['Parcel<br>' + parcelValue + '<br>' + parcel + '%', parseInt(parcel)], ['EMRP<br>' + empRecepValue + '<br>' + empRecep + '%', parseInt(empRecep)]],
			size: '70%',
			innerSize: '75%',
			showInLegend: false,
			dataLabels: {
				enabled: true,
				distance: -15,
				style: {
					fontSize: 9
				}
			}
		}, {
			name: 'Inner',
			data: [['gift (' + giftValue + ') ' + gift + '%', parseInt(gift)], ['Sale Of Goods (' + saleOfGoodsValue + ') ' + saleOfGoods + '%', parseInt(saleOfGoods)],
			['Returned Goods (' + returnedGoodsValue + ') ' + returnedGoods + '%', parseInt(returnedGoods)], ['Commercial Sample (' + commercialSampleValue + ') ' + commercialSample + '%', parseInt(commercialSample)],
			['Documents (' + documentsValue + ') ' + documents + '%', parseInt(documents)], ['Others (' + othersValue + ') ' + others + '%', parseInt(others)]],
			size: '47%',
			innerSize: '76%',
			showInLegend: true,
			dataLabels: {
				enabled: false,
				distance: -20
			}
		}]
	})
	changeColur(chart12, pieColorsMid, pieColorsInn);
	 renderDiv(chart12,430,50,pieColors,pieColorsMid,pieColorsInn,chartId);

	/*   var arr = chart12.options.exporting.buttons.contextButton.menuItems;
	  var index = arr.indexOf("viewData");
	  if (index !== -1) arr.splice(index, 1);
	  var index = arr.indexOf("downloadCSV");
	  if (index !== -1) arr.splice(index, 1);
	  var index = arr.indexOf("downloadXLS");
	  if (index !== -1) arr.splice(index, 1);*/

	//$(document).on("click", '.expand', function(event){
	$('.expand').on('click', function() {
		var chartId = 'chart-modal';
		chart12Popup(chartId);
		$('.expand').hide();
		chart12.update({
			title: {
				x: -210,
				y: 20,
				style: {
					fontSize: 17
				}
			},
			series: [{
				name: 'Outer',
				dataLabels: {
					distance: -25,
					style: {
						fontSize: 13
					}
				}
			}, {
				name: 'Middle',
				dataLabels: {
					distance: -15,
					style: {
						fontSize: 13
					}
				}
			}, {
				name: 'Inner',
				dataLabels: {
					distance: -20
				}
			}]
		})
		$('#count-chart').modal('toggle');
		$('.leg-container').addClass('leg-container-1');
		  renderDiv(chart12,700,130,pieColors,pieColorsMid,pieColorsInn,chartId);
		$('.leg-container-1').hide();
		changeColur(chart12, pieColorsMid, pieColorsInn);
		$('.expand').hide()
		$('.view-more-chart-md').css('display','none');
	})
	$('.close').on('click', function() {
		$('.expand').show();
		$('.leg-container-1').show();
		 $('.view-more-chart-md').css('display','block');
	})
}

function renderDiv(chart,a,b,outerclr,middleclr,innerclr,chartId){
	var ctrytext = "";
	$.each(outerclr, function(k, v) {
		ctrytext += "<span class='dot' style='background-color:" + v + "'></span> ";
	})
	var mctext = "";
	$.each(middleclr, function(k, v) {
		mctext += "<span class='dot' style='background-color:" + v + "'></span> ";
	})
	var ictext = "";
	$.each(innerclr, function(k, v) {
		ictext += "<span class='dot' style='background-color:" + v + "'></span> ";
	})
	labelText = '<div class="leg-container"><div class="row"><div class="col-5">' + ctrytext + '</div><div class="col-7 leg-text">Countries</div></div>' +
		'<div class="row"><div class="col-5">' + mctext + '</div><div class="col-7 leg-text">Mail Classes</div></div>' +
		'<div class="row"><div class="col-5">' + ictext + '</div><div class="col-7 leg-text">Item Category</div></div></div>';
		
	var exclassname='expand';
       if(chartId=='countryWsMultichart'){
               exclassname='expand';
       }else if(chartId=='countryWsAssValMultichart'){
               exclassname='expand-1';
       }
	
	iconText='<div class="row" style="justify-content: end; padding-right: 9px;"><i class="fa fa-expand '+exclassname+'" style="font-size:20px; color:#a19b9b; cursor:pointer; position:relative;"></i></div>';
	
	chart.renderer.text(labelText, a,b, labelText)
		.attr({
			zIndex: 5
		})
		.css({
			fontSize: '12px'
		})
		.add();
	
	chart.renderer.text(iconText, a-30,b-20, iconText)
		.attr({
			zIndex: 5
		})
		.css({
			fontSize: '12px'
		})
		.add();
}



//countrywise AssesVAlue
function chart13Popup(chartId) {
	var lastMonth = "";
	var cwMcAssValCountry = [];
	$.each(countryWiseAssValPercentage, function(key, value) {
		var conListAssVal = [value.country + '<br>(' + value.noOfValue + ')<br>' + value.volumePercentage + '%', parseInt(value.volumePercentage)];
		cwMcAssValCountry.push(conListAssVal);
		totalValue = value.totArt;
		/*colrcode=(colrcode-9);
		var colr="#"+colrcode;
		colorshade.push(colr);*/
		lastMonth = value.lastMonth;
	})


	var lettersAsVal = $('#counts').attr("lettersAssValCWS");
	var emsAsVal = $('#counts').attr("emsAssValCWS");
	var parcelAsVal = $('#counts').attr("parcelsAssValCWS");
	var empRecepAsVal = $('#counts').attr("empRecepAssValCWS");

	var lettersAssValue = $('#counts').attr("ltrValAsCWS");
	var emsAssValue = $('#counts').attr("emsValAsCWS");
	var parcelAssValue = $('#counts').attr("parValAsCWS");
	var empRecepAssValue = $('#counts').attr("empRecepValAsCWS");

	var giftAsVal = $('#counts').attr("giftAssValCWS");
	var saleOfGoodsAsVal = $('#counts').attr("saleOfGoodsAssValCWS");
	var returnedGoodsAsVal = $('#counts').attr("returnedGoodsAssValCWS");
	var commercialSampleAsVal = $('#counts').attr("commercialSampleAssValCWS");
	var documentsAsVal = $('#counts').attr("documentsAssValCWS");
	var othersAsVal = $('#counts').attr("othersAssValCWS");

	var noOfgiftAsVal = $('#counts').attr("noOfgiftValCWS");
	var noOfsaleOfGoodsAsVal = $('#counts').attr("noOfsaleOfGoodsValCWS");
	var noOfreturnedGoodsAsVal = $('#counts').attr("noOfreturnedGoodsValCWS");
	var noOfcommercialSampleAsVal = $('#counts').attr("noOfcommercialSampleValCWS");
	var noOfdocumentsAsVal = $('#counts').attr("noOfdocumentsValCWS");
	var noOfothersAsVal = $('#counts').attr("noOfothersValCWS");

	chart13 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: chartId,
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
		},
		title: {
			text: 'Total<br>Assessable <br>Value During<br>' + lastMonth + '<br>=' + totalValue,
			floating: true,
			style: {
				fontSize: 'smaller',
				fontWeight: 'Bold'
			},
			verticalAlign: 'middle',
			y: 10,
			x: -120
		},
		plotOptions: {
			pie: {
				size: '100%',
				shadow: false,
				colors: secPieColors
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> %';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			itemStyle: '{ "word-wrap": "break-word"}',
			enabled: true
		},
		series: [{
			name: 'Outer',
			data: cwMcAssValCountry,
			size: '100%',
			innerSize: '75%',
			showInLegend: false,
			dataLabels: {
				enabled: true,
				distance: -25,
				itemStyle: '{ "word-wrap": "break-word"}',
				style: {
					fontSize: 9
				}
			}
		}, {
			name: 'Middle',
			data: [['Letters<br>' + lettersAssValue + '<br>' + lettersAsVal + '%', parseInt(lettersAsVal)], ['EMS<br>' + emsAssValue + '<br>' + emsAsVal + '%', parseInt(emsAsVal)],
			['Parcel<br>' + parcelAssValue + '<br>' + parcelAsVal + '%', parseInt(parcelAsVal)], ['EMRP<br>' + empRecepAssValue + '<br>' + empRecepAsVal + '%', parseInt(empRecepAsVal)]],
			size: '70%',
			innerSize: '75%',
			showInLegend: false,
			dataLabels: {
				enabled: true,
				distance: -15,
				style: {
					fontSize: 9
				}
			}
		}, {
			name: 'Inner',
			data: [['gift (' + noOfgiftAsVal + ') ' + giftAsVal + '%', parseInt(giftAsVal)], ['Sale of Goods (' + noOfsaleOfGoodsAsVal + ') ' + saleOfGoodsAsVal + '%', parseInt(saleOfGoodsAsVal)],
			['Returned Goods (' + noOfreturnedGoodsAsVal + ') ' + returnedGoodsAsVal + '%', parseInt(returnedGoodsAsVal)], ['Commercial Sample (' + noOfcommercialSampleAsVal + ') ' + commercialSampleAsVal + '%', parseInt(commercialSampleAsVal)],
			['Documents (' + noOfdocumentsAsVal + ') ' + documentsAsVal + '%', parseInt(documentsAsVal)], ['Others (' + noOfothersAsVal + ') ' + othersAsVal + '%', parseInt(othersAsVal)]],
			size: '47%',
			innerSize: '76%',
			showInLegend: true,
			dataLabels: {
				enabled: false,
				distance: -20

			}

		}]
	})
	changeColur(chart13, secPieColorsMid, secPieColorsInn);
	renderDiv(chart13,410,50,secPieColors,secPieColorsMid,secPieColorsInn,chartId);
	$('.expand-1').on('click', function() {
		var chartId = 'chart-modal';
		chart13Popup(chartId);
		$('.expand-1').hide();
		$('.expand').hide();
		chart13.update({
			title: {
				x: -235,
				y: 20,
				style: {
					fontSize: 17
				}
			},
			series: [{
				name: 'Outer',
				dataLabels: {
					distance: -25,
					style: {
						fontSize: 13
					}
				}
			}, {
				name: 'Middle',
				dataLabels: {
					distance: -15,
					style: {
						fontSize: 13
					}
				}
			}, {
				name: 'Inner',
				dataLabels: {
					distance: -20
				}
			}]
		})
		if ($(window).width() <= 1583) {
			chart13.update({
				title: {
					y: 10,
					x:-210,
					style: {
						fontSize: 14
					}
				},
				plotOptions: {
					pie: {
						shadow: false,
						center: ['45%', '50%']
					}
				},
				series: [{
					name: 'Outer',
					size: '85%',
					innerSize: '73%',
					dataLabels: {
						distance: -25,
						style: {
							fontSize: 13
						}
					}
				}, {
					name: 'Middle',
					size: '55%',
					innerSize: '70%',
					dataLabels: {
						distance: -15,
						style: {
							fontSize: 13
						}
					}
				}, {
					size: '33%',
					innerSize: '70%',
					name: 'Inner',
					dataLabels: {
						distance: -20
					}
				}],
				legend: {
					x: -100,
					width: 250
				}
			})
		}
		$('#count-chart').modal('toggle');
		$('.leg-container').addClass('leg-container-1');
		renderDiv(chart13, 655, 130, secPieColors, secPieColorsMid, secPieColorsInn, chartId);
		$('.leg-container-1').hide();
		changeColur(chart13, secPieColorsMid, secPieColorsInn);
		$('.expand-1').hide();
		$('.expand').hide();
		$('.view-more-chart-md').css('display', 'none');

	})
	$('.close').on('click', function() {
		$('.expand-1').show();
		$('.expand').show();
		$('.leg-container-1').show();
		$('.view-more-chart-md').css('display','block');
	})
}


function changelegPosition(chart, labelText, left) {
	chart.renderer.text(labelText, left, 50, labelText)
		.attr({
			zIndex: 5
		})
		.css({
			fontSize: '12px'
		})
		.add();

}

function changeColur(chart, colorMid, colorInn) {
	var series1 = chart.series[1].data;
	var series2 = chart.series[2].data;

	$.each(series1, function(k, v) {
		$.each(colorMid, function(k1, v1) {
			if (k == k1) {
				v.color = v1;
			}
		});
	});
	$.each(series2, function(k, v) {
		$.each(colorInn, function(k1, v1) {
			if (k == k1) {
				v.color = v1;
			}
		});
	});
}



function chart19Popup() {
	var specificcount = palAlerts!=null ? palAlerts.specific:0;
	var genericcount =palAlerts!=null ? palAlerts.generic:0;
	color3Dc1 = ['#e6fff2', '#ffcfcf'];
	// Create the chart
	chart19 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				},

			}]
		},
		chart: {
			renderTo: 'userPAL',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0,
			options3d: {
				enabled: true,
				alpha: 40
			}
		},
		title: {
			text: 'No of active alerts',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 10,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
				depth: 25,
				colors: Highcharts.map(color3Dc1, function(color) {
					return {
						radialGradient: {
							cx: 0.5,
							cy: 0.3,
							r: 0.7
						},
						stops: [
							[0, color],
							[1, Highcharts.color(color).brighten(-0.3).get('rgb')] // darken
						]
					};
				})
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			width: 1,
			itemStyle: '{ "word-wrap": "break-word"}',
			x: -174

		},
		series: [{
			name: 'alerts',
			data: [["Specific-Alerts(" + specificcount + ")", parseInt(specificcount)], ["Generic-Alerts(" + genericcount + ")", parseInt(genericcount)]],
			showInLegend: true,
			size: '100%',
			innerSize: '0%',
			dataLabels: {
				enabled: false

			}
		}],
	})
}


function chart15Popup() {
	var uweadcount = userWiseArticlesCount.ead;
	var uwaaacount = userWiseArticlesCount.aaa;
	var uwaafcount = userWiseArticlesCount.aaf;
	var total = +uweadcount + +uwaaacount + +uwaafcount;
	color3Dc1 = ['#e6fff2', '#ffcfcf', '#fbffb6'];
	// Create the chart
	
	var sessionrole = $("#s-role").val();
	 var data = [];
  
		  if (sessionrole == 'PIN' || sessionrole == 'PSU') {
		    data = [["AAF(" + uwaafcount + ")", parseInt(uwaafcount)]];
		  } else {
			data = [
			      ["EAD(" + uweadcount + ")", parseInt(uweadcount)],
			      ["AAA(" + uwaaacount + ")", parseInt(uwaaacount)],
			      ["AAF(" + uwaafcount + ")", parseInt(uwaafcount)]
			    ];
			  }
	
	
	
	chart15 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				},

			}]
		},
		chart: {
			renderTo: 'doughnutChart-user',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0,
			options3d: {
				enabled: true,
				alpha: 40
			}
		},
		title: {
			text: 'Total</br>Art.ID in<br> Modules</br>(' + total + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 10,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
				depth: 25,
				colors: Highcharts.map(color3Dc1, function(color) {
					return {
						radialGradient: {
							cx: 0.5,
							cy: 0.3,
							r: 0.7
						},
						stops: [
							[0, color],
							[1, Highcharts.color(color).brighten(-0.3).get('rgb')] // darken
						]
					};
				})
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			width: 1,
			itemStyle: '{ "word-wrap": "break-word"}',
			x: -174

		},
		series: [{
			name: 'Process EAD',
			//data: [["EAD(" + uweadcount + ")", parseInt(uweadcount)], ["AAA(" + uwaaacount + ")", parseInt(uwaaacount)], ["AAF(" + uwaafcount + ")", parseInt(uwaafcount)]],
			
			data:data,
			showInLegend: true,
			size: '100%',
			innerSize: '0%',
			dataLabels: {
				enabled: false

			}
		}],
	})
}

function chart15pbsPopup() {
	var uweadcount = userWiseArticlesCount.ead;
	var uwaaacount = userWiseArticlesCount.aaa;
	var uwaafcount = userWiseArticlesCount.aaf;
	var total = +uweadcount + +uwaaacount + +uwaafcount;
	color3Dc1 = ['#e6fff2', '#ffcfcf', '#fbffb6'];
	// Create the chart
	chart15 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				},

			}]
		},
		chart: {
			renderTo: 'doughnutChart-user',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0,
			options3d: {
				enabled: true,
				alpha: 40
			}
		},
		title: {
			text: 'Total<br>Arrival<br>Bags</br>(' + total + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 10,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
				depth: 25,
				colors: Highcharts.map(color3Dc1, function(color) {
					return {
						radialGradient: {
							cx: 0.5,
							cy: 0.3,
							r: 0.7
						},
						stops: [
							[0, color],
							[1, Highcharts.color(color).brighten(-0.3).get('rgb')] // darken
						]
					};
				})
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			width: 1,
			itemStyle: '{ "word-wrap": "break-word"}',
			x: -174

		},
		series: [{
			name: 'Process EAD',
			data: [["EAD(" + uweadcount + ")", parseInt(uweadcount)], ["AAA(" + uwaaacount + ")", parseInt(uwaaacount)], ["TOTAL(" + uwaafcount + ")", parseInt(uwaafcount)]],
			showInLegend: true,
			size: '100%',
			innerSize: '0%',
			dataLabels: {
				enabled: false

			}
		}],
	})
}





/*function chart25Popup() {
	var totdet = $('#totdetain').val();
	color3Dc1 = ['#e6fff2', '#ffcfcf', '#fbffb6'];
	// Create the chart
	chart25 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				},

			}]
		},
		chart: {
			renderTo: 'doughnutChart-user',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0,
			options3d: {
				enabled: true,
				alpha: 40
			}
		},
		title: {
			text: 'Total</br>Detained <br> Art.ID in<br> Modules</br>(' + totdet + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 10,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
				depth: 25,
				colors: Highcharts.map(color3Dc1, function(color) {
					return {
						radialGradient: {
							cx: 0.5,
							cy: 0.3,
							r: 0.7
						},
						stops: [
							[0, color],
							[1, Highcharts.color(color).brighten(-0.3).get('rgb')] // darken
						]
					};
				})
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			width: 1,
			itemStyle: '{ "word-wrap": "break-word"}',
			x: -174

		},
		series: [{
			name: 'Process EAD',
			data: [["DET(" + totdet + ")"]],
			showInLegend: true,
			size: '100%',
			innerSize: '0%',
			dataLabels: {
				enabled: false

			}
		}],
	})
}*/




function chart16Popup(){
	var usWstotcoultr = userwiseMc.totcoultr;
	var usWstotcouems = userwiseMc.totcouems;
	var usWstotcoupar = userwiseMc.totcoupar;
	var usWstotcouemp = userwiseMc.totcouemp;
	var usWstotal = +usWstotcoultr + +usWstotcouems + +usWstotcoupar + +usWstotcouemp;
	color3Dc1 = ['#ff8f8f', '#cdff36', '#6ac2ff', '#ff8af6'];
	// Create the chart
	chart16 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: 'userEADcontainer',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0,
			options3d: {
				enabled: true,
				alpha: 40
			}
		},
		title: {
			text: 'Mail</br>class<br>(' + usWstotal + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 15,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
				depth: 25,
				colors: Highcharts.map(color3Dc1, function(color) {
					return {
						radialGradient: {
							cx: 0.5,
							cy: 0.3,
							r: 0.7
						},
						stops: [
							[0, color],
							[1, Highcharts.color(color).brighten(-0.3).get('rgb')] // darken
						]
					};
				})
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			//width:70,
			itemStyle: '{ "word-wrap": "break-word"}',
			x: -45

		},
		series: [{
			name: 'Process EAD',
			data: [["Letters(U) (" + usWstotcoultr + ")", parseInt(usWstotcoultr)], ["EMS(E) (" + usWstotcouems + ")", parseInt(usWstotcouems)],
			["Parcels(C) (" + usWstotcoupar + ")", parseInt(usWstotcoupar)], ["Emp.Recp(T) (" + usWstotcouemp + ")", parseInt(usWstotcouemp)]],
			size: '100%',
			innerSize: '70%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			}
		}],
	})

}


function chart16pbsPopup(){
	var recpcou = $("#counts").attr("recpcou");
	var bagcou = $("#counts").attr("bagcou");;
	var coutot = +recpcou + +bagcou;
	color3Dc1 = ['#ff8f8f', '#cdff36'];
	// Create the chart
	chart16 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
		//	renderTo: 'userEADcontainer',
			renderTo: 'doughnutChart-user',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0,
			options3d: {
				enabled: true,
				alpha: 40
			}
		},
		title: {
			text: 'Bag<br> Arrival<br>(' + coutot+ ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 15,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
				depth: 25,
				colors: Highcharts.map(color3Dc1, function(color) {
					return {
						radialGradient: {
							cx: 0.5,
							cy: 0.3,
							r: 0.7
						},
						stops: [
							[0, color],
							[1, Highcharts.color(color).brighten(-0.3).get('rgb')] // darken
						]
					};
				})
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			//width:70,
			itemStyle: '{ "word-wrap": "break-word"}',
			x: -45

		},
		series: [{
			name: 'Bag Arrival Confirmation',
			data: [["Recp ID Bags (" + recpcou + ")", parseInt(recpcou)], ["Bags (" + bagcou + ")", parseInt(bagcou)]],
			size: '100%',
			innerSize: '70%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			}
		}],
	})

}





function chart17Popup(){
	var usWstotgift = totalUsWsIcCount.gift;
	var usWstotsg = totalUsWsIcCount.saleOfGoods;
	var usWstotcs = totalUsWsIcCount.commercialSample;
	var usWstotrg = totalUsWsIcCount.returnedGoods;
	var usWstotdoc = totalUsWsIcCount.documents;
	var usWstototh = totalUsWsIcCount.others;
	var usWsIctotal = +usWstotgift + +usWstotsg + +usWstotcs + +usWstotrg + +usWstotdoc + +usWstototh;
	color3Dc1 = ['#d8fd80', '#ffebeb', '#c2b3ff', '#ff8af6', '#fba02f', '#e8ccf3'];
	// Create the chart
	chart17 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: 'userIc',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0,
			options3d: {
				enabled: true,
				alpha: 40
			}
		},
		title: {
			text: 'Item<br>Category</br>(' + usWsIctotal + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 10,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
				depth: 25,
				colors: Highcharts.map(color3Dc1, function(color) {
					return {
						radialGradient: {
							cx: 0.5,
							cy: 0.3,
							r: 0.7
						},
						stops: [
							[0, color],
							[1, Highcharts.color(color).brighten(-0.3).get('rgb')] // darken
						]
					};
				})
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			itemStyle: '{ "word-wrap": "break-word"}',

		},
		series: [{
			name: 'Item Category',
			data: [["Gift (" + usWstotgift + ")", parseInt(usWstotgift)],
			["Sale Of Goods (" + usWstotsg + ")", parseInt(usWstotsg)], ["Commercial Sample (" + usWstotcs + ")", parseInt(usWstotcs)], [" Returned Goods (" + usWstotrg + ")", parseInt(usWstotrg)],
			[" Documents (" + usWstotdoc + ")", parseInt(usWstotdoc)], [" Others (" + usWstototh + ")", parseInt(usWstototh)]],
			size: '100%',
			innerSize: '0%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			}
		}],
	})

}



function chart18Popup(){
	var couass = $('#counts').attr("couass");
	var couexm = $('#counts').attr("couexm");
	var couooc = $('#counts').attr("couooc");
	var coudet = $('#counts').attr("coudet");
	var total = +couass + +couexm + +couooc + +coudet;
	color3Dc1 = ['#83cbff', '#80ffd0', '#ffa4f8', '#c1fff6'];
	// Create the chart
	chart18 = new Highcharts.Chart({
		credits: {
			enabled: false
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						enabled: true,
					}
				}
			}]
		},
		chart: {
			renderTo: 'userAAF',
			type: 'pie',
			backgroundColor: 'rgba(255, 255, 255, 0.0)',
			margin: [0, 190, 0, 0],
			spacingTop: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			spacingRight: 0,
			options3d: {
				enabled: true,
				alpha: 20
			}
		},
		title: {
			text: 'Articles<br>Arrived<br>at FPO </br>(' + total + ')',
			floating: true,
			style: {
				fontSize: 'small',
			},
			verticalAlign: 'middle',
			y: 20,
			x: -95
		},
		plotOptions: {
			pie: {
				shadow: false,
				startAngle: -90,
				endAngle: 90,
				depth: 25,
				colors: Highcharts.map(color3Dc1, function(color) {
					return {
						radialGradient: {
							cx: 0.5,
							cy: 0.3,
							r: 0.7
						},
						stops: [
							[0, color],
							[1, Highcharts.color(color).brighten(-0.3).get('rgb')] // darken
						]
					};
				})
			},

		},
		tooltip: {
			formatter: function() {
				return '<b>' + this.point.name + '</b> ';
			}
		},
		legend: {
			verticalAlign: 'middle',
			align: 'right',
			itemStyle: '{ "word-wrap": "break-word"}',
			width: 100,
			x: -70,
			y: -25,

		},
		series: [{
			name: 'aaf',
			data: [["Assess (" + couass + ")", parseInt(couass)], ["Exam (" + couexm + ")", parseInt(couexm)], ["OOC (" + couooc + ")", parseInt(couooc)], ["Detain (" + coudet + ")", parseInt(coudet)]],
			size: '100%',
			innerSize: '65%',
			showInLegend: true,
			dataLabels: {
				enabled: false

			}
		}],
	})

}

$(document).ready(function() {
	console.log('ready');
	var sessionrole = $("#s-role").val();
	console.log(sessionrole)
	if(sessionrole == "PAL"){
		chart19Popup();
		$('#userPAL').css('display','block');
	}else if(sessionrole == "PAL"){
		chart19Popup();
		$('#userNAL').css('display','block');
	}else if(sessionrole=="PAO" || sessionrole=="PIN" || sessionrole=="PAC" || sessionrole=="PSU"){		
		chart15Popup();
		chart16Popup();
		chart17Popup();
		chart18Popup();}
		else if (sessionrole=="PBS")
		{
		  
		  chart16pbsPopup();}
	//	if(sessionrole=="PAO" || sessionrole=="PAC"){
	//		chart25Popup();}
		if(sessionrole=="PIN" || sessionrole=="PSU" || sessionrole=="PBS"){
			var uwaafcount = userWiseArticlesCount.aaf;
			if (sessionrole=='PBS')
			var data=[["TOTAL(" + uwaafcount + ")", parseInt(uwaafcount)]];
			else
			var data=[["AAF(" + uwaafcount + ")", parseInt(uwaafcount)]];
		//	chart15.series[0].update({
		//		data:data
			
		}
	chart12Popup('countryWsMultichart')
    chart13Popup('countryWsAssValMultichart');
	chart20Popup('countryWMCContainer');
	chart21Popup('oocChargableChart');

	//Media Query
	function myFunction(x) {
		if (x.matches) {
			chart13.series[0].update({
				size: '120%',
				innerSize: '73%',
				dataLabels: {
					distance: -12,
					style: {
						fontSize: 9
					}
				}
			})
			chart13.series[1].update({
				size: '80%',
				innerSize: '73%',
				dataLabels: {
					distance: -12,
					style: {
						fontSize: 9
					}
				}
			})
			chart13.series[2].update({
				size: '50%',
				innerSize: '73%',
				dataLabels: {
					distance: -12,
					style: {
						fontSize: 9
					}
				}
			})
			changeColur(chart13, secPieColorsMid, secPieColorsInn);

			chart12.series[0].update({
				size: '105%',
				innerSize: '73%',
				itemStyle: '{ "word-wrap": "break-word"}',
				dataLabels: {
					distance: -16,
					style: {
						fontSize: 9
					}
				}
			})
			chart12.series[1].update({
				size: '70%',
				innerSize: '73%',
				itemStyle: '{ "word-wrap": "break-word"}',
				dataLabels: {
					distance: -12,
					style: {
						fontSize: 9
					}
				}
			})
			chart12.series[2].update({
				size: '45%',
				innerSize: '73%',
				itemStyle: '{ "word-wrap": "break-word"}',
				dataLabels: {
					distance: -12,
					style: {
						fontSize: 9
					}
				}
			})
			changeColur(chart12, pieColorsMid, pieColorsInn);
		}

	}
	function myFunctionS1(y) {
		if (y.matches) {
			$("[id^='col-c']").removeClass();
			$("[id^='col-c']").addClass('col-md-9');
			$("[id^='col-c']").css('padding-right', '0px')
			chart1.series[0].update({
				size: '110%',
				innerSize: '67%',
			})
			chart2.series[0].update({
				size: '110%',
			})
			chart3.series[0].update({
				size: '110%',
				innerSize: '67%',
			})
			chart4.series[0].update({
				size: '110%',
				innerSize: '67%',
			})
			chart6.update({
				title: {
					style: {
						fontSize: '12px'
					}
				}
			})
			chart15.series[0].update({
				size: '110%',
				innerSize: '0%',
			})
			chart16.series[0].update({
				size: '110%'
			})
			chart17.series[0].update({
				size: '110%',
				innerSize: '0%',
			})
			chart18.series[0].update({
				size: '110%',
				innerSize: '67%',
			})
			/*chart9.series[0].update({
				size: '135%',
				innerSize: '67%'
			})*/
		}
	}
	function myFunctionS2(z) {
		if (z.matches) {
			/*chart9.series[0].update({
				size: '115%',
				innerSize: '67%',
			})*/
		}
	}
	function myFunctionS3(a) {
		if (a.matches) {
			/*chart9.series[0].update({
				size: '120%',
				innerSize: '67%',
			})
			chart9.update({
				chart: {
					margin: [0, 190, 0, 0]
				}
			})*/
			chart13.series[0].update({
				size: '115%',
				innerSize: '75%',
				dataLabels: {
					distance: -29,
					style: {
						fontSize: 9
					}
				}
			})

			$('.icon-row').css('position', 'relative');
			$('.icon-row').css('left', '-100px');
		}
	}
	function myFunctionS4(b) {
		if (b.matches) {
			$("[id^='col-c']").css('padding-left', '2px');
			$('.icon').css('margin-left', '-10px')
			$('.icon').find("[class^='fa']").css('font-size', 'large')
		} else {
			$('.icon').find("[class^='fa']").css('font-size', 'x-large')
		}
	}

	var x = window.matchMedia("(min-width: 1583px) and (max-width: 1590px)");
	var y = window.matchMedia("(min-width: 1492px) and (max-width: 1531px)");
	var z = window.matchMedia("(min-width: 1590px) and (max-width: 1717px)");
	var a = window.matchMedia("(min-width: 1531px) and (max-width: 1583px)");
	var b = window.matchMedia("(max-width: 1492px)");

	myFunction(x)
	myFunctionS1(y)
	myFunctionS2(z)
	myFunctionS3(a)
	myFunctionS4(b)

	x.addListener(myFunction);
	y.addListener(myFunctionS1);
	z.addListener(myFunctionS2);
	a.addListener(myFunctionS3);
	b.addListener(myFunctionS4);
hideLoader();
}); // hrer ite ends

$('#vwMore').on('click', function(){
	$('#viewMore').modal('toggle');
})

function chart21Popup(chartID){
	chart21= new Highcharts.chart({
		chart: {
			type: 'column',
			renderTo: chartID
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		title: {
			text: ''
		},
		xAxis: {
			categories: ['Duty Chargable','Duty FG','Duty Payable','Fine/Penalty'],
			title: {
				text: null
			}
		},
		yAxis: {
			min: 0,
			labels: {
				overflow: 'justify'
			}
		},
		plotOptions: {
			column: {
				dataLabels: {
					enabled: true
				}
			}
		},
		credits: {
			enabled: false
		},
		series: [{
			name: 'Value',
			data: [parseInt(oocTotalDutyDetails.totDuty),parseInt(oocTotalDutyDetails.totDutyFg),parseInt(oocTotalDutyDetails.totAmtPayable),parseInt(oocTotalDutyDetails.finePenal)],
			showInLegend: false,
		}]
	});
}


function chart20Popup(chartID) {
	/*chart20 = new am4core.create("countryWMCContainer", "SlicedChart");
	am4core.useTheme(am4themes_animated);
	//chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
	data=[];
	var lastMonth = "";
	$.each(allCountryWiseAssValPercentage, function(k, v) {
		conList = {
			country: v.country,
			value: parseInt(v.volumePercentage)
		};
		data.push(conList);
		totalArt = v.totArt;
		lastMonth = v.lastMonth;

	})
	chart20.data = data;
	var series = chart20.series.push(new am4charts.FunnelSeries());
	series.colors.step = 2;
	series.dataFields.value = "value";
	series.dataFields.category = "country";
	series.alignLabels = true;
	//series.orientation= "horizontal";
	series.labelsContainer.paddingLeft = 5;
	series.labelsContainer.width = 100;
	series.labelsContainer.fontSize=10;
	//series.orientation = "horizontal";
	//series.bottomRatio = 1;

	//chart20.legend = new am4charts.Legend();
	//chart20.legend.position = "right";
	//chart20.legend.valign = "bottom";
	chart20.logo.visible=false;
	//chart20.legend.margin(5, 5, 5, 5);
	//chart20.legend.valign = "top";*/
country=[];
	assVal=[];
	var lastMonth = "";
	var serial=0;
	$.each(allCountryWiseAssValPercentage, function(k, v) {
		serial= serial+ +1;
		conList = ['<b>('+serial+'.)</b> <span class="countries"><b>'+v.country+'</b></span>('+v.volumePercentage+'%)<span class="contrycnt"><b> (count :'+v.totalArtCount+')</b></span>'];
		country.push(conList);
		assvalue =[v.noOfValue];
		assVal.push(assvalue);
		if(v.country!='OTHERS'){
			totalArt = v.totArt;
		}
		lastMonth = v.lastMonth;

	})
	chart20= new Highcharts.chart({
		chart: {
			type: 'bar',
			renderTo: chartID
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		title: {
			text: 'postal imports countrywise value for '+lastMonth,
			style: {
				fontWeight: 'Bold'
			},
		},
		/*subtitle: {
			text: 'postal imports countrywise value for '+lastMonth
		},*/
		xAxis: {
			categories: country,
			title: {
				text: null
			},
			labels: {
            	align: 'right'
        	}
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Assess value',
				align: 'high',
				style: {
					fontWeight: 'Bold'
				},
			},
			labels: {
				overflow: 'justify'
			}
		},
		plotOptions: {
			bar: {
				dataLabels: {
					enabled: true
				}
			}
		},
		credits: {
			enabled: false
		},
		series: [{
			name: 'Assess Value',
			data: assVal,
			showInLegend: false,
		}]
	});
	renderIcon(chart20,620,20);
	$('.expandAC').on('click', function() {
		$('#country-ass-value-chart-modal').modal('toggle');
		chart20Popup('country-ass-value-chart');
		$('.expandAC').hide();
		$('.view-more-chart-md').hide();
	})
	$('.Ac-close').on('click', function() {
		$('.expandAC').show();
		$('.view-more-chart-md').show();
		$('#viewMore').css('padding','10px');
	})
	$('.countries').attr('fill','red');
	$('.contrycnt').attr('fill','#87cefa');
}

function renderIcon(chart,a,b){
	iconText = '<div class="row icon-row" style="justify-content: end; padding-right: 9px;"><i class="fa fa-expand expandAC" style="font-size:20px; color:#a19b9b; cursor:pointer; position:relative;"></i></div>';

	chart.renderer.text(iconText, a, b, iconText)
		.attr({
			zIndex: 5
		})
		.css({
			fontSize: '12px'
		})
		.add();
}


function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}