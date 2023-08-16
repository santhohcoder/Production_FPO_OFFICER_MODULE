function goBack() {
	window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
}


function viewDetail1(e) {
if(null!=$('#query').val() && $('#query').val() != "")
	window.location.href = "ead_query?id=" + e.id;
	else
	window.location.href = "order?id=" + e.id;
}

function viewDetail(e) {
	//alert(e.id);
	window.location.href = "declared?id=" + e.id;
}

$(document).ready(function() {
	$("#myModal").modal('show');
});

if($('#allItemDuty').val() >= 1000){$('#dutyPayable').val($('#allItemDuty').val());}

$.ajax({
	url: 'getTotalNoItems?cinNo=' + $('#inputPassword').val(),
	data: JSON.stringify(""),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(data) {

		$("#noOfItems").text(data);
		$("#1").text($("#noOfItems").text());
	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});

function addNewItemDetails() {
	var id = $('#inputPassword').val();
	var developerData = {};
	//developerData["cinNo"] = id;
	//	developerData["item_NO"] = value;
	var resObj = $.ajax({
		url: 'addNewItemDetails?id=' + id + '&itemNo=' + "1",
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemCurCdChangeAdd").val(data.currcd);
			$("#itemBcdRtaChangeAdd").val(data.bcd_RTA);
			$("#itemIgstRtaChangeAdd").val(data.igst_RTA);
			$("#itemSwRtaChangeAdd").val(data.sw_RTA);
			$("#itemRateChangeAdd").val(data.rate);
			$("#itemBcdAmtFgChangeAdd").val(data.bcd_AMT_FG);
			$("#itemIgstAmtFgChangeAdd").val(data.igst_AMT_FG);
			$("#itemSwAmtFgChangeAdd").val(data.sw_AMT_FG);
			$("#itemBcdAmtChangeAdd").val(data.bcd_AMT);
			$("#itemIgstAmtChangeAdd").val(data.igst_AMT);
			$("#itemSwAmtChangeAdd").val(data.sw_AMT);
			$("#itemDutyChangeAdd").val(data.duty);
			$("#itemDutyFgChangeAdd").val(data.duty_FG);
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function changeDetails(value) {

	var id = $('#inputPassword').val();
	var developerData = {};
	var developerData1 = {};
	var developerData2 = {};
	var developerData3 = {};
	var developerData4 = {};
	var developerData5 = {};
	var developerData6 = {};
	//developerData["cinNo"] = id;
	//	developerData["item_NO"] = value;
	var resObj = $.ajax({
		url: 'changeItem?id=' + id + '&itemNo=' + value,
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemNoChange").val(data.item_NO);
			$("#itemNouChange").val(data.nou);
			$("#itemDESChange").val(data.item_DESC);
			$("#itemCthChange").val(data.cth);
			$("#itemRevisedCthChange").val(data.cth_REVISED);
			$("#itemNetWtChange").val(data.netwt);
			$("#itemDeclValChange").val(data.decl_VAL);
			$("#itemCurCdChange").val(data.currcd);
			$("#itemOrgCntryCdChange").val(data.origcntrycd);
			$("#itemCth option:contains(" + data.cth + ")").attr('selected', 'selected');
			$.ajax({
				url: 'getBcdNotification?cth=' + data.cth,
				data: JSON.stringify(developerData1),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(datas) {
					$.each(datas, function(i, item) {
						$('#itemBcdNoNtChange').append($('<option>', {
							text: item
						}));
					});
					$("#itemBcdNoNtChange option:contains(" + data.bcd_NOTN + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$.ajax({
				url: 'getBcdSerialNo?notificationNo=' + data.bcd_NOTN + '&cth=' + data.cth,
				data: JSON.stringify(developerData2),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(datas) {
					$.each(datas, function(i, item) {
						$('#itemBcdNsNoChange').append($('<option>', {
							text: item
						}));

					});
					$("#itemBcdNsNoChange option:contains(" + data.bcd_NSNO + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$("#itemBcdRtaChange").val(data.bcd_RTA);
			$("#itemBcdAmtChange").val(data.bcd_AMT);
			$("#itemBcdAmtFgChange").val(data.bcd_AMT_FG);
			$.ajax({
				url: 'getIgstNotification?cth=' + data.cth,
				data: JSON.stringify(developerData3),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(datas) {
					$.each(datas, function(i, item) {
						$('#itemIgstNoNtChange').append($('<option>', {
							text: item
						}));
					});
					$("#itemIgstNoNtChange option:contains(" + data.igst_NOTN + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$.ajax({
				url: 'getIgstSerialNo?notificationNo=' + data.igst_NOTN + '&cth=' + data.cth,
				data: JSON.stringify(developerData4),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(datas) {
					$.each(datas, function(i, item) {
						$('#itemIgstNsNoChange').append($('<option>', {
							text: item
						}));

					});
					$("#itemIgstNsNoChange option:contains(" + data.igst_NSNO + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$("#itemIgstRtaChange").val(data.igst_RTA);
			$("#itemIgstAmtChange").val(data.igst_AMT);
			$("#itemIgstAmtFgChange").val(data.igst_AMT_FG);
			$.ajax({
				url: 'getSwNotification?cth=' + data.cth,
				data: JSON.stringify(developerData5),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(datas) {
					$.each(datas, function(i, item) {
						$('#itemSwNoNtChange').append($('<option>', {
							text: item
						}));
					});
					$("#itemSwNoNtChange option:contains(" + data.sw_NOTN + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$.ajax({
				url: 'getSwSerialNo?notificationNo=' + data.sw_NOTN + '&cth=' + data.cth,
				data: JSON.stringify(developerData6),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(datas) {
					$.each(datas, function(i, item) {
						$('#itemSwNsNoChange').append($('<option>', {
							text: item
						}));

					});
					$("#itemSwNsNoChange option:contains(" + data.sw_NSNO + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$("#itemSwRtaChange").val(data.sw_RTA);
			$("#itemSwAmtChange").val(data.sw_AMT);
			$("#itemSwAmtFgChange").val(data.sw_AMT_FG);
			$("#itemRateChange").val(data.rate);
			$("#itemDutyChange").val(data.duty);
			$("#itemDutyFgChange").val(data.duty_FG);
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function updateFn(e) {
	//window.location.href = "eadItemUpdate?id=" + e.id;

	//$("#exampleModalLong").hide();
}

function deleteDetail(value) {
	var id = $('#inputPassword').val();
	window.location.href = "deleteItem?id=" + id + "&itemNo=" + value;
}

$('#itemCth').change(function() {
	var developerData = {};
	var developerData2 = {};
	var developerData3 = {};
	var resObj = $.ajax({
		url: 'getBcdNotification?cth=' + $(this).val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemBcdNoNtChange').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});

	var resObj2 = $.ajax({
		url: 'getIgstNotification?cth=' + $(this).val(),
		data: JSON.stringify(developerData2),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemIgstNoNtChange').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});

	var resObj3 = $.ajax({
		url: 'getSwNotification?cth=' + $(this).val(),
		data: JSON.stringify(developerData3),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemSwNoNtChange').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemCthAdd').change(function() {
	var developerData = {};
	var developerData2 = {};
	var developerData3 = {};
	var resObj = $.ajax({
		url: 'getBcdNotification?cth=' + $(this).val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemBcdNoNtChangeAdd').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});

	var resObj2 = $.ajax({
		url: 'getIgstNotification?cth=' + $(this).val(),
		data: JSON.stringify(developerData2),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemIgstNoNtChangeAdd').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});

	var resObj3 = $.ajax({
		url: 'getSwNotification?cth=' + $(this).val(),
		data: JSON.stringify(developerData3),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemSwNoNtChangeAdd').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemBcdNoNtChange').change(function() {
	var developerData = {};
	var resObj = $.ajax({
		url: 'getBcdSerialNo?notificationNo=' + $(this).val() + '&cth=' + $('#itemCth').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemBcdNsNoChange').append($('<option>', {
					text: item
				}));

			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemBcdNoNtChangeAdd').change(function() {
	var developerData = {};
	var resObj = $.ajax({
		url: 'getBcdSerialNo?notificationNo=' + $(this).val() + '&cth=' + $('#itemCthAdd').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemBcdNsNoChangeAdd').append($('<option>', {
					text: item
				}));

			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemBcdNsNoChange').change(function() {
	var developerData = {};
	var previousDuty = $("#itemDutyChange").val();
	var previousBcdFg = $("#itemBcdAmtChange").val();
	var resObj = $.ajax({
		url: 'getBcdRate?getBcdSlNo=' + $(this).val() + '&cth=' + $('#itemCth').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemBcdRtaChange").val(data);
			var currentBcdFg = $("#itemBcdAmtChange").val(parseFloat(parseFloat($('#itemDeclValChange').val()) * (parseFloat($('#itemBcdRtaChange').val()) * 0.01) * parseFloat($('#itemRateChange').val())).toFixed(2));
			$("#itemBcdAmtFgChange").val(parseFloat(parseFloat(previousBcdFg) - parseFloat(currentBcdFg.val())).toFixed(2));
			var currentDuty = $("#itemDutyChange").val(parseFloat(parseFloat($("#itemBcdAmtChange").val()) + parseFloat($("#itemIgstAmtChange").val()) + parseFloat($("#itemSwAmtChange").val())).toFixed(2));
			$("#itemDutyFgChange").val(parseFloat(parseFloat(previousDuty) - parseFloat(currentDuty.val())).toFixed(2));
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemBcdNsNoChangeAdd').change(function() {
	var developerData = {};
	var previousDuty = $("#itemDutyChangeAdd").val();
	var previousBcdFg = $("#itemBcdAmtChangeAdd").val();
	var resObj = $.ajax({
		url: 'getBcdRate?getBcdSlNo=' + $(this).val() + '&cth=' + $('#itemCthAdd').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemBcdRtaChangeAdd").val(data);
			var currentBcdFgAdd = $("#itemBcdAmtChangeAdd").val(parseFloat(parseFloat($('#itemDeclValChangeAdd').val()) * (parseFloat($('#itemBcdRtaChangeAdd').val()) * 0.01) * parseFloat($('#itemRateChangeAdd').val())).toFixed(2));
			$("#itemBcdAmtFgChangeAdd").val(parseFloat(parseFloat(previousBcdFg) - parseFloat(currentBcdFgAdd.val())).toFixed(2));
			var currentDuty = $("#itemDutyChangeAdd").val(parseFloat(parseFloat($("#itemBcdAmtChangeAdd").val()) + parseFloat($("#itemIgstAmtChangeAdd").val()) + parseFloat($("#itemSwAmtChangeAdd").val())).toFixed(2));
			$("#itemDutyFgChangeAdd").val(parseFloat(parseFloat(previousDuty) - parseFloat(currentDuty.val())).toFixed(2));
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});


$('#itemIgstNoNtChange').change(function() {
	var developerData = {};
	var resObj = $.ajax({
		url: 'getIgstSerialNo?notificationNo=' + $(this).val() + '&cth=' + $('#itemCth').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemIgstNsNoChange').append($('<option>', {
					text: item
				}));

			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemIgstNoNtChangeAdd').change(function() {
	var developerData = {};
	var resObj = $.ajax({
		url: 'getIgstSerialNo?notificationNo=' + $(this).val() + '&cth=' + $('#itemCthAdd').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemIgstNsNoChangeAdd').append($('<option>', {
					text: item
				}));

			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});


$('#itemIgstNsNoChange').change(function() {
	var developerData = {};
	var previousDuty = $("#itemDutyChange").val();
	var previousIgstFg = $("#itemIgstAmtChange").val();
	var resObj = $.ajax({
		url: 'getIgstRate?getSlNo=' + $(this).val() + '&cth=' + $('#itemCth').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemIgstRtaChange").val(data);
			var currentIgstFg = $("#itemIgstAmtChange").val(parseFloat((parseFloat($("#itemSwAmtChange").val()) + parseFloat($("#itemBcdAmtChange").val()) + (parseFloat($('#itemDeclValChange').val()) * parseFloat($('#itemRateChange').val()))) * (parseFloat($('#itemIgstRtaChange').val()) * 0.01)).toFixed(2));
			$("#itemIgstAmtFgChange").val(parseFloat(parseFloat(previousIgstFg) - parseFloat(currentIgstFg.val())).toFixed(2));
			var currentDuty = $("#itemDutyChange").val(parseFloat(parseFloat($("#itemBcdAmtChange").val()) + parseFloat($("#itemIgstAmtChange").val()) + parseFloat($("#itemSwAmtChange").val())).toFixed(2));
			//$("#itemIgstAmtChange").val($('#itemDeclValChange').val() * $('#itemIgstRtaChange').val() * $('#itemRateChange').val());
			$("#itemDutyFgChange").val(parseFloat(parseFloat(previousDuty) - parseFloat(currentDuty.val())).toFixed(2));
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemIgstNsNoChangeAdd').change(function() {
	var developerData = {};
	var previousDuty = $("#itemDutyChangeAdd").val();
	var previousIgstFg = $("#itemIgstAmtChangeAdd").val();
	var resObj = $.ajax({
		url: 'getIgstRate?getSlNo=' + $(this).val() + '&cth=' + $('#itemCthAdd').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemIgstRtaChangeAdd").val(data);
			var currentIgstFgAdd = $("#itemIgstAmtChangeAdd").val(parseFloat((parseFloat($("#itemSwAmtChangeAdd").val()) + parseFloat($("#itemBcdAmtChangeAdd").val()) + (parseFloat($('#itemDeclValChangeAdd').val()) * parseFloat($('#itemRateChangeAdd').val()))) * (parseFloat($('#itemIgstRtaChangeAdd').val()) * 0.01)).toFixed(2));
			$("#itemIgstAmtFgChangeAdd").val(parseFloat(parseFloat(previousIgstFg) - parseFloat(currentIgstFgAdd.val())).toFixed(2));
			var currentDuty = $("#itemDutyChangeAdd").val(parseFloat(parseFloat($("#itemBcdAmtChangeAdd").val()) + parseFloat($("#itemIgstAmtChangeAdd").val()) + parseFloat($("#itemSwAmtChangeAdd").val())).toFixed(2));
			//$("#itemIgstAmtChange").val($('#itemDeclValChange').val() * $('#itemIgstRtaChange').val() * $('#itemRateChange').val());
			$("#itemDutyFgChangeAdd").val(parseFloat(parseFloat(previousDuty) - parseFloat(currentDuty.val())).toFixed(2));
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemSwNoNtChange').change(function() {
	var developerData = {};
	var resObj = $.ajax({
		url: 'getSwSerialNo?notificationNo=' + $(this).val() + '&cth=' + $('#itemCth').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemSwNsNoChange').append($('<option>', {
					text: item
				}));

			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemSwNoNtChangeAdd').change(function() {
	var developerData = {};
	var resObj = $.ajax({
		url: 'getSwSerialNo?notificationNo=' + $(this).val() + '&cth=' + $('#itemCthAdd').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemSwNsNoChangeAdd').append($('<option>', {
					text: item
				}));

			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemSwNsNoChange').change(function() {
	var developerData = {};
	var previousDuty = $("#itemDutyChange").val();
	var previousSwFg = $("#itemSwAmtChange").val();
	var resObj = $.ajax({
		url: 'getSwRate?getSlNo=' + $(this).val() + '&cth=' + $('#itemCth').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemSwRtaChange").val(data);
			var currentSwFgA = $("#itemSwAmtChange").val(parseFloat((parseFloat($("#itemBcdAmtChange").va())) * (parseFloat($('#itemSwRtaChange').val()) * 0.01)).toFixed(2));
			$("#itemSwAmtFgChange").val(parseFloat(parseFloat(previousSwFg) - parseFloat(currentSwFgA.val())).toFixed(2));
			var currentDuty = $("#itemDutyChange").val(parseFloat(parseFloat($("#itemBcdAmtChange").val()) + parseFloat($("#itemIgstAmtChange").val()) + parseFloat($("#itemSwAmtChange").val())).toFixed(2));
			//$("#itemSwAmtChange").val($('#itemDeclValChange').val() * $('#itemSwRtaChange').val() * $('#itemRateChange').val());
			$("#itemDutyFgChange").val(parseFloat(parseFloat(previousDuty) - parseFloat(currentDuty.val())).toFixed(2));
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});

$('#itemSwNsNoChangeAdd').change(function() {
	var developerData = {};
	var previousDuty = $("#itemDutyChangeAdd").val();
	var previousSwFgAdd = $("#itemSwAmtChangeAdd").val();
	var resObj = $.ajax({
		url: 'getSwRate?getSlNo=' + $(this).val() + '&cth=' + $('#itemCthAdd').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemSwRtaChangeAdd").val(data);
			var currentSwFgAdd = $("#itemSwAmtChangeAdd").val(parseFloat((parseFloat($("#itemBcdAmtChangeAdd").va())) * (parseFloat($('#itemSwRtaChangeAdd').val()) * 0.01)).toFixed(2));
			$("#itemSwAmtFgChangeAdd").val(parseFloat(parseFloat(previousSwFgAdd) - parseFloat(currentSwFgAdd.val())).toFixed(2));
			var currentDuty = $("#itemDutyChangeAdd").val(parseFloat(parseFloat($("#itemBcdAmtChangeAdd").val()) + parseFloat($("#itemIgstAmtChangeAdd").val()) + parseFloat($("#itemSwAmtChangeAdd").val())).toFixed(2));
			//$("#itemSwAmtChange").val($('#itemDeclValChange').val() * $('#itemSwRtaChange').val() * $('#itemRateChange').val());
			$("#itemDutyFgChangeAdd").val(parseFloat(parseFloat(previousDuty) - parseFloat(currentDuty.val())).toFixed(2));
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
});


function nextItemNo(value) {
	var developerData = {};
	developerData['cinNo'] = $('#inputPassword').val();
	developerData['itemId'] = $('#itemId').val();
	developerData['itemNumber'] = $('#itemNo').val();

	var resObj = $.ajax({
		url: '' + value,
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {

			$("#itemSwAmtChange2").val(developerDatas['sw_AMT']);
			$("#itemIgstAmtChange2").val(developerDatas['igst_AMT']);
			$("#itemBcdAmtChange2").val(developerDatas['bcd_AMT']);
			$("#itemSwAmtFgChange2").val(developerDatas['sw_AMT_FG']);
			$("#itemIgstAmtFgChange2").val(developerDatas['igst_AMT_FG']);
			$("#itemBcdAmtFgChange2").val(developerDatas['bcd_AMT_FG']);
			$("#itemDutyFgChange2").val(developerDatas['duty_FG']);
			$("#itemNo2").val(developerDatas['item_NO']);
			$("#itemDESChange2").val(developerDatas['item_DESC']);
			$("#itemCthChange2").val(developerDatas['gen_CTH']);
			$("#itemRevisedCthChange2").val(developerDatas['cth_REVISED']);
			$("#itemNouChange2").val(developerDatas['nou']);
			$("#itemNetWtChange2").val(developerDatas['netwt']);
			$("#itemDeclValChange2").val(developerDatas['decl_VAL']);
			$("#itemCurCdChange2").val(developerDatas['currcd']);
			$("#itemOrgCntryCdChange2").val(developerDatas['origcntrycd']);
			$("#itemRateChange2").val(developerDatas['rate']);
			$("#itemPagination2").text(developerDatas['item_NO'] + "/" + $("#noOfItems").text());
			$("#nextItemPegination2").text(developerDatas['item_NO']);
			$("#itemDutyChange2").val(developerDatas['duty']);
			$("#assessVal2").text(developerDatas['assess_VAL']);
			$("#itemSwRtaChange2").val(developerDatas['sw_RTA']);
			$("#itemIgstRtaChange2").val(developerDatas['igst_RTA']);
			$("#itemBcdRtaChange2").val(developerDatas['bcd_RTA']);
			$("#query2").val(developerDatas['query']);
			$("#allItemDuty").val(developerDatas['allItemDuty']);
			$("#allItemDutyFg").val(developerDatas['allItemDutyFg']);
			$("#itemOrgCntryCdChange2").val(developerDatas['cus_SITE']);
			$("#itemBcdNoNtChange2").val(developerDatas['bcd_NOTN']);
			$("#itemBcdNsNoChange2").val(developerDatas['bcd_NSNO']);
			$("#itemIgstNoNtChange2").val(developerDatas['igst_NOTN']);
			$("#itemIgstNsNoChange2").val(developerDatas['igst_NSNO']);
			$("#itemSwNoNtChange2").val(developerDatas['sw_NONT']);
			$("#itemSwNsNoChange2").val(developerDatas['sw_NSNO']);

			$.ajax({
				url: 'getLatestAmendOnCinAndItemNo',
				data: JSON.stringify(developerDatas),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(developerDataBasic) {

					$("#itemSwAmtChange").val(developerDataBasic['sw_AMT']);
					$("#itemIgstAmtChange").val(developerDataBasic['igst_AMT']);
					$("#itemBcdAmtChange").val(developerDataBasic['bcd_AMT']);
					$("#itemSwAmtFgChange").val(developerDataBasic['sw_AMT_FG']);
					$("#itemIgstAmtFgChange").val(developerDataBasic['igst_AMT_FG']);
					$("#itemBcdAmtFgChange").val(developerDataBasic['bcd_AMT_FG']);
					$("#itemDutyFgChange").val(developerDataBasic['duty_FG']);
					$("#itemNo").val(developerDatas['item_NO']);
					$("#itemDESChange").val(developerDataBasic['item_DESC']);
					$("#itemCthChange").val(developerDataBasic['gen_CTH']);
					$("#itemRevisedCthChange").val(developerDataBasic['cth_REVISED']);
					$("#itemNouChange").val(developerDataBasic['nou']);
					$("#itemNetWtChange").val(developerDataBasic['netwt']);
					$("#itemDeclValChange").val(developerDataBasic['decl_VAL']);
					$("#itemCurCdChange").val(developerDataBasic['currcd']);
					$("#itemOrgCntryCdChange").val(developerDataBasic['origcntrycd']);
					$("#itemRateChange").val(developerDataBasic['rate']);
					$("#itemPagination").text(developerDataBasic['item_NO'] + "/" + $("#noOfItems").text());
					$("#nextItemPegination").text(developerDataBasic['item_NO']);
					$("#itemDutyChange").val(developerDataBasic['duty']);
					$("#assessVal").text(developerDataBasic['assess_VAL']);
					$("#itemSwRtaChange").val(developerDataBasic['sw_RTA']);
					$("#itemIgstRtaChange").val(developerDataBasic['igst_RTA']);
					$("#itemBcdRtaChange").val(developerDataBasic['bcd_RTA']);
					$("#query").val(developerDataBasic['query']);
					$("#itemOrgCntryCdChange").val(developerDataBasic['cus_SITE']);
					$("#itemBcdNoNtChange").val(developerDataBasic['bcd_NOTN']);
					$("#itemBcdNsNoChange").val(developerDataBasic['bcd_NSNO']);
					$("#itemIgstNoNtChange").val(developerDataBasic['igst_NOTN']);
					$("#itemIgstNsNoChange").val(developerDataBasic['igst_NSNO']);
					$("#itemSwNoNtChange").val(developerDataBasic['sw_NONT']);
					$("#itemSwNsNoChange").val(developerDataBasic['sw_NSNO']);

				},
				fail: function(rs, e) {
					alert("Error in Assessment");
				}
			});

		},
		fail: function(rs, e) {
			alert("Error in Assessment");
		}
	});

	if ($('#itemDESChange').val() != $('#itemDESChange2').val()) {
		$('#itemDESChange2').addClass('ammendclr');
	}

	if ($('#hs').val() != $('#hs2').val()) {
		$('#hs2').addClass('ammendclr');
	}

	if ($('#itemRevisedCthChange').val() != $('#itemRevisedCthChange2').val()) {
		$('#itemRevisedCthChange2').addClass('ammendclr');
	}

	if ($('#nou').val() != $('#nou2').val()) {
		$('#nou2').addClass('ammendclr');
	}

	if ($('#itemNetWtChange').val() != $('#itemNetWtChange2').val()) {
		$('#itemNetWtChange2').addClass('ammendclr');
	}

	if ($('#itemDeclValChange').val() != $('#itemDeclValChange2').val()) {
		$('#itemDeclValChange2').addClass('ammendclr');
	}

	if ($('#itemCurCdChange').val() != $('#itemCurCdChange2').val()) {
		$('#itemCurCdChange2').addClass('ammendclr');
	}

	if ($('#itemOrgCntryCdChange').val() != $('#itemOrgCntryCdChange2').val()) {
		$('#itemOrgCntryCdChange2').addClass('ammendclr');
	}

	if ($('#itemCthChange').val() != $('#itemCthChange2').val()) {
		$('#itemCthChange2').addClass('ammendclr');
	}

	if ($('#itemBcdNoNtChange').val() != $('#itemBcdNoNtChange2').val()) {
		$('#itemBcdNoNtChange2').addClass('ammendclr');
	}

	if ($('#itemBcdNsNoChange').val() != $('#itemBcdNsNoChange2').val()) {
		$('#itemBcdNsNoChange2').addClass('ammendclr');
	}

	if ($('#itemBcdRtaChange').val() != $('#itemBcdRtaChange2').val()) {
		$('#itemBcdRtaChange2').addClass('ammendclr');
	}

	if ($('#itemBcdAmtChange').val() != $('#itemBcdAmtChange2').val()) {
		$('#itemBcdAmtChange2').addClass('ammendclr');
	}

	if ($('#itemBcdAmtFgChange').val() != $('#itemBcdAmtFgChange2').val()) {
		$('#itemBcdAmtFgChange2').addClass('ammendclr');
	}

	if ($('#itemIgstNoNtChange').val() != $('#itemIgstNoNtChange2').val()) {
		$('#itemIgstNoNtChange2').addClass('ammendclr');
	}

	if ($('#itemIgstNsNoChange').val() != $('#itemIgstNsNoChange1').val()) {
		$('#itemIgstNsNoChange1').addClass('ammendclr');
	}

	if ($('#itemIgstRtaChange').val() != $('#itemIgstRtaChange1').val()) {
		$('#itemIgstRtaChange1').addClass('ammendclr');
	}

	if ($('#itemIgstAmtChange').val() != $('#itemIgstAmtChange2').val()) {
		$('#itemIgstAmtChange2').addClass('ammendclr');
	}

	if ($('#itemIgstAmtFgChange').val() != $('#itemIgstAmtFgChange2').val()) {
		$('#itemIgstAmtFgChange2').addClass('ammendclr');
	}

	if ($('#itemSwNoNtChange').val() != $('#itemSwNoNtChange2').val()) {
		$('#itemSwNoNtChange2').addClass('ammendclr');
	}

	if ($('#itemSwNsNoChange').val() != $('#itemSwNsNoChange2').val()) {
		$('#itemSwNsNoChange2').addClass('ammendclr');
	}

	if ($('#itemSwRtaChange').val() != $('#itemSwRtaChange2').val()) {
		$('#itemSwRtaChange2').addClass('ammendclr');
	}

	if ($('#itemSwAmtChange').val() != $('#itemSwAmtChange2').val()) {
		$('#itemSwAmtChange2').addClass('ammendclr');
	}

	if ($('#itemSwAmtFgChange').val() != $('#itemSwAmtFgChange2').val()) {
		$('#itemSwAmtFgChange2').addClass('ammendclr');
	}

	if ($('#itemRateChange').val() != $('#itemRateChange2').val()) {
		$('#itemRateChange2').addClass('ammendclr');
	}

	if ($('#itemDutyChange').val() != $('#itemDutyChange2').val()) {
		$('#itemDutyChange2').addClass('ammendclr');
	}

	if ($('#itemDutyFgChange').val() != $('#itemDutyFgChange2').val()) {
		$('#itemDutyFgChange2').addClass('ammendclr');
	}
}



if ($('#itemDESChange').val() != $('#itemDESChange2').val()) {
	$('#itemDESChange2').addClass('ammendclr');
}

if ($('#hs').val() != $('#hs2').val()) {
	$('#hs2').addClass('ammendclr');
}

if ($('#itemRevisedCthChange').val() != $('#itemRevisedCthChange2').val()){
	$('#itemRevisedCthChange2').addClass('ammendclr');
}

if ($('#nou').val() != $('#nou2').val()) {
	$('#nou2').addClass('ammendclr');
}

if ($('#itemNetWtChange').val() != $('#itemNetWtChange2').val()) {
	$('#itemNetWtChange2').addClass('ammendclr');
}

if ($('#itemDeclValChange').val() != $('#itemDeclValChange2').val()) {
	$('#itemDeclValChange2').addClass('ammendclr');
}

if ($('#itemCurCdChange').val() != $('#itemCurCdChange2').val()) {
	$('#itemCurCdChange2').addClass('ammendclr');
}

if ($('#itemOrgCntryCdChange').val() != $('#itemOrgCntryCdChange2').val()) {
	$('#itemOrgCntryCdChange2').addClass('ammendclr');
}

if ($('#itemCthChange').val() != $('#itemCthChange2').val()){
	$('#itemCthChange2').addClass('ammendclr');
}

if ($('#itemBcdNoNtChange').val() != $('#itemBcdNoNtChange2').val()){
	$('#itemBcdNoNtChange2').addClass('ammendclr');
}

if ($('#itemBcdNsNoChange').val() != $('#itemBcdNsNoChange2').val()){
	$('#itemBcdNsNoChange2').addClass('ammendclr');
}

if ($('#itemBcdRtaChange').val() != $('#itemBcdRtaChange2').val()){
	$('#itemBcdRtaChange2').addClass('ammendclr');
}

if ($('#itemBcdAmtChange').val() != $('#itemBcdAmtChange2').val()) {
	$('#itemBcdAmtChange2').addClass('ammendclr');
}

if ($('#itemBcdAmtFgChange').val() != $('#itemBcdAmtFgChange2').val()) {
	$('#itemBcdAmtFgChange2').addClass('ammendclr');
}

if ($('#itemIgstNoNtChange').val() != $('#itemIgstNoNtChange2').val()){
	$('#itemIgstNoNtChange2').addClass('ammendclr');
}

if ($('#itemIgstNsNoChange').val() != $('#itemIgstNsNoChange1').val()){
	$('#itemIgstNsNoChange1').addClass('ammendclr');
}

if ($('#itemIgstRtaChange').val() != $('#itemIgstRtaChange1').val()) {
	$('#itemIgstRtaChange1').addClass('ammendclr');
}

if ($('#itemIgstAmtChange').val() != $('#itemIgstAmtChange2').val()) {
	$('#itemIgstAmtChange2').addClass('ammendclr');
}

if ($('#itemIgstAmtFgChange').val() != $('#itemIgstAmtFgChange2').val()) {
	$('#itemIgstAmtFgChange2').addClass('ammendclr');
}

if ($('#itemSwNoNtChange').val() != $('#itemSwNoNtChange2').val()) {
	$('#itemSwNoNtChange2').addClass('ammendclr');
}

if ($('#itemSwNsNoChange').val() != $('#itemSwNsNoChange2').val()) {
	$('#itemSwNsNoChange2').addClass('ammendclr');
}

if ($('#itemSwRtaChange').val() != $('#itemSwRtaChange2').val()) {
	$('#itemSwRtaChange2').addClass('ammendclr');
}

if ($('#itemSwAmtChange').val() != $('#itemSwAmtChange2').val()){
	$('#itemSwAmtChange2').addClass('ammendclr');
}

if ($('#itemSwAmtFgChange').val() != $('#itemSwAmtFgChange2').val()) {
	$('#itemSwAmtFgChange2').addClass('ammendclr');
}

if ($('#itemRateChange').val() != $('#itemRateChange2').val()) {
	$('#itemRateChange2').addClass('ammendclr');
}

if ($('#itemDutyChange').val() != $('#itemDutyChange2').val()) {
	$('#itemDutyChange2').addClass('ammendclr');
}

if ($('#itemDutyFgChange').val() != $('#itemDutyFgChange2').val()) {
	$('#itemDutyFgChange2').addClass('ammendclr');
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
