function goBack() {
	window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
}

var interval1;
var interval2;
var interval3;
var interval4;

function viewDetail1(e) {
	//alert(e.id);
	window.location.href = "order?id=" + e.id;
}

function chngdutytooth(e)
{
  calTotDuty(e);
}



function updatedepcmts(id,value){
			var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#paodepcmts').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
	$('#cmtsentPopup').modal('hide');
var developerData = {};	
	developerData['cin_NO'] = $("#inputPassword").val();
	developerData['cmts'] = $("#paodepcmts").val();
	developerData['stage'] = "AAA";
	developerData['item_ID'] = $("#itemId").val();
	showLoader();
	var resObj = $.ajax({
		              url: 'updpaocmts',
                      data: JSON.stringify(developerData),
		              dataType: "json",
		              contentType: "application/json",
		              type: "post",
                      success: function(developerDatas) {	
	                           hideLoader();
	                           swal('Success!', "Departmental Comments Updated Successfully !", 'success');
					},
					 fail: function(rs, e) {
						hideLoader();
                      alert("error in updating Departmental Comments");
                  }
});
        
}

function cmtsentpopup(id, value) {
	$('#cmtsentPopup').modal('show');
}

function aclWarningMsg() {
    if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && "PAC" == $('#role').val()) {
        $("#aclWarningMsg").modal('show');
        $(".sendbtnshow").show();
    }
    if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && "PAO" == $('#role').val() && $('#acloffid').val() != '') 
        $(".sendpaobtnshow").show();
    else
         $(".sendpaobtnshow").hide();
}

if ($('#itemcou').val()==1)
  { $('.nextitmu').attr('hidden',true);
    $('.nextitmd').attr('hidden',true);
    $('.previousitmd').attr('hidden',true);
    $('.previousitmu').attr('hidden',true);
}


$(document).ready(function() {

if ($('#itemcou').val()==1)
  { $('.nextitmu').attr('disabled',true);
    $('.nextitmd').attr('disabled',true);
    $('.previousitmd').attr('disabled',true);
    $('.previousitmu').attr('disabled',true);
}

});


$(".sendbtnshow").hide();

$(".sendpaobtnshow").hide();
function movOOC()
{
  $("#nxtModal").modal('show') ;
}

function commpsamp_process(id)
{
//	$('.updateAss').attr("disabled", true);
	$("#commsampPopup").modal('hide');	
	document.getElementById("queryraisebtns").focus();
		aclWarningMsg();
}

function OOCDet(e) {
	$("#nxtModal").modal('hide') ;
		     var fpoExam = {};
			fpoExam['cinNo'] = $('#inputPassword').val();
				$.ajax({
		url: 'passoocdata',
		data: JSON.stringify(fpoExam),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
	   
		 console.log("success");
		 window.location.href = "process_ead";
			
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	
	});
        }
			

$(".QryEad").keyup(function(){
		
	/*	if (null != $(".QryEad").val()) {
			$(".txthide").attr("disabled", false);
		}

		if ($(".QryEad").val() == "") {
			$(".txthide").attr("disabled", true);
		}   */
		
		 el = $(this);
    if ((el.val().length > 5) && (el.val() != null) && (el.val().trim().length > 5)) 
        $(".txthide").attr("disabled", false);
    else
        $(".txthide").attr("disabled", true);
		
		});
		
		

$(document).ready(function() {
	if ($('#role').val() == "PAO" && (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val())))
	   {   $('.nxtooc').attr('disabled',false);
$('.nxtooc').hide();}
	$('#paodepcmts').text($('#fpoassdepcmts').val());
	$('#paodepcmtsead').text($('#fpoassdepcmtsead').val());
	$('#eadpaodepcmts').text($('#fpoassdepcmtsead').val());
	if(!$("#back").val())
	$("#myModal").modal('show');
	$("#manualduty").show();
	$("#manualdutyO").hide();
	if (parseFloat($('#acfirst').val())==1 && $('#role').val()=='PAC')
	{
	  
	 $('.updateAss').attr("disabled", false);
	   $('.ChangeAss').attr("disabled", true);}
	else if (($('#assdt').val() == null || $('#assdt').val() == "") && ($('#updassdt').val() == null || $('#updassdt').val() == ""))
	{ //$('.updateAss').attr("disabled", false);
	   $('.ChangeAss').attr("disabled", true);}
    else if ($('#role').val()=='PAC' || ($('#role').val()=='PAO' && ($('#acloffid').val()!=null && $('#acloffid').val()!='') ))	  {
	//  $('.updateAss').attr("disabled", true);
	   $('.ChangeAss').attr("disabled", false);}
	else
	 {
	  if ($('#acloffid').val() == null)
	  { $('.updateAss').attr("disabled", false);
	   $('.ChangeAss').attr("disabled", true);}
	 }
	    
	    
});

$("#next").click(function() {
	if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && "PAC" == $('#role').val() ) {
		$("#aclWarningMsg").modal('show');
		$(".sendbtnshow").show();
	}
});

$(".sendbtnshow").hide();

function sendAprBack(value) {

    var role = "PAC";
	var developerData = {};

	developerData['cin_NO'] = $("#inputPassword").val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['cmts'] = $('#sndbackcmts').val();
	developerData['role'] = role;
	developerData['entry_DT'] = new Date();
	developerData['off_ID'] = $('#offId').val();

	var resObj = $.ajax({
		url: 'sendBackApr',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			window.location.href = "process_ead";
		},
		fail: function(rs, e) {
			alert("Error in Order");
		}
	});

};

function redirect(que,page){
            	if(que=='N3'){
            		window.location.href=""+page+"?back=true&id="+$("#inputPassword").val();
            	}else if(que=='N1'){
            		if(page != 'pen_order'){
            			window.location.href=""+page+"?back=true&id="+$("#inputPassword").val();
            		}
            	}else if(que=='N2'){
            		if(page != 'pen_order' && page != 'pen_declared'){
            			window.location.href=""+page+"?back=true&id="+$("#inputPassword").val();
            		}
            	}
            }

function nextPage(cinNo) {
	window.location.href='pen_order?id='+cinNo;
}

function success(e) {
	if (document.getElementById(e).value === "") {
		document.getElementById('lock').disabled = true;
	} else {
		document.getElementById('lock').disabled = false;
	}
}





//by kani veeem santh		

$(document).ready(function() {
  console.log("santhosh testing");
  console.log($('#role').val());
  console.log($('#totassval').val());
  console.log($('#maxval').val());
  

	 if($('#role').val()=="PAC"&&(parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()))){
		$(".sendbtnshow").show();
		
	if($('#role').val()=='PAC'){
				
		if ((parseInt($("#qryNo").val())>0) && ($('#updassdt').val() == null || $('#updassdt').val() == ""))
		    $('#aclpopupmsg').html("AO has raised the query."+ '<BR>' + "AC may alter AO's  Query if required and complete the process.");
         else if ((parseInt($("#qryNo").val())>0) && ($('#updassdt').val() != null || $('#updassdt').val() != ""))
			$('#aclpopupmsg').html("AO has done the assessment."+ '<BR>' + "AC may alter AO's assesment if required and complete the process.");	
     }
		
	$("#aclWarningMsg").modal('show');
	}
	
});


$(document).ready(function() {
	$('#show-hidden-menu').click(function() {
		$('.hidden-menu').slideToggle("slow");
	});
});

$(document).ready(function() {
	$('#show-hidden-menu1').click(function() {
		$('.hidden-menu1').slideToggle("slow");
	});
});

$(document).ready(function() {
	$('#show-hidden-menu2').click(function() {
		$('.hidden-menu2').slideToggle("slow");
	});
});

$(document).ready(function() {
	$('#show-hidden-menu3').click(function() {
		$('.hidden-menu3').slideToggle("slow");
	});
});

$(document).ready(function() {
	$('#show-hidden-menu4').click(function() {
		$('.hidden-menu4').slideToggle("slow");
	});
});

function inputValue(el) {
	el.value = el.value.replace(/[Sec. +]/g, '');
	el.value = 'Sec. ' + el.value;
}

var dropCount = 0;

// Certificate Details	
$(document).ready(function() {

	$("#addrow1").on("click", function() {
		var counter = $('#tbOtrDt tr').length + 1;
		var newRow = $("<tr>");
		var cols = "";
		cols += '<td><select type="text" id="customDropDown' + counter + '" onchange="othersNotn(this)" class="custom-select" name="DUTY[' + counter + ']" ><option>Select Duty</option></select></td>';
		cols += '<td><input type="text" id="rateDuty' + counter + '" class="form-control" readonly="readonly" name="RATE_OF_DUTY[' + counter + ']" /></td>';
		cols += '<td><select type="text" id="notn' + counter + '" onchange="othersSlNo(this)" class="custom-select" name="NOTN[' + counter + ']" ><option>Select NOTN</option></select></td>';
		cols += '<td><select type="text" id="slno' + counter + '" onchange="selectEachNsNo(this)" class="custom-select" name="SL.NO[' + counter + ']" ><option>Select SL. No</option></select></td>';
		cols += '<td><input type="text"  id="effRate' + counter + '" class="form-control" readonly="readonly" name="EFF.RATE[' + counter + ']" /></td>';
		cols += '<td><input type="text"  id="dutyAmount' + counter + '" onblur="calTotDuty(2)" class="form-control" style="text-align: right;" name="DUTY_AMOUNT[' + counter + ']" /></td>';
		cols += '<td><input type="text"  id="dutyAmountFg' + counter + '" onblur="calTotDutyFg()" class="form-control" style="text-align: right;" name="DUTY_FG[' + counter + ']" /></td>';
		cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
		newRow.append(cols);
		$("table.order-list1").append(newRow);
		$.ajax({
			url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(datas) {
				$.each(datas, function(i, item) {
					$('#customDropDown' + (counter - 1) + '').append($('<option>', {
						text: item[1],
						value: item[0]
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
		counter++;
	});
	$("table.order-list1").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		calTotDuty(2);
		calTotDutyFg();
	});
});

$(document).ready(function() {
	$("#addrow6").on("click", function() {
		var counter = $('#enterOthDuty tr').length + 1;
		var newRow = $("<tr>");
		var cols = "";
		cols += '<td><input type="text" class="form-control" name="DUTY[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control"  name="RATE_OF_DUTY[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control" value="Enter NOTN"  name="NOTN[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control" value="Enter Sl.NO" name="SL.NO[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control" onblur="calTotDuty(2)"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control" name="DUTY_FG[' + counter + ']" /></td>';
		cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
		newRow.append(cols);
		$("table.order-list6").append(newRow);
		$.ajax({
			url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(datas) {
				$.each(datas, function(i, item) {
					$('#customDropDown' + (counter - 1) + '').append($('<option>', {
						text: item[1],
						value: item[0]
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
		counter++;
	});
	$("table.order-list6").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		calTotDuty(2);
	});
});

$(document).ready(function() {

    $("#addrow7").on("click", function() {
        var counter = $('#etr98DutyAss tr').length;
        var newRow = $("<tr>");
        var cols = "";
        cols += '<td><input type="text" class="form-control"  name="DUTY[' + counter + ']" /></td>';
        cols += '<td><input type="text" class="form-control" name="RATE_OF_DUTY[' + counter + ']" /></td>';
        cols += '<td><input type="text" class="form-control" name="NOTN[' + counter + ']" /></td>';
        cols += '<td><input type="text" class="form-control" name="SL.NO[' + counter + ']" /></td>';
        cols += '<td><input type="text" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
        cols += '<td><input type="text" class="form-control" onblur="calTotDutyNineEgtAss(' + "'B'" + ')"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
        cols += '<td><input type="text" class="form-control" onblur="calTotDutyNineEgtAss(' + "'C'" + ')" name="DUTY_FG[' + counter + ']" /></td>';
        cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
        newRow.append(cols);
        $("table.order-list7").append(newRow);
        $.ajax({
            url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
            data: JSON.stringify(""),
            dataType: "json",
            contentType: "application/json",
            type: "post",
            success: function(datas) {
                $.each(datas, function(i, item) {
                    $('#customDropDown' + (counter - 1) + '').append($('<option>', {
                        text: item[1],
                        value: item[0]
                    }));
                });
            },
            fail: function(rs, e) {
                console.log(rs, responseText);
            }
        });
        counter++;
    });
    $("table.order-list7").on("click", ".ibtnDel", function(event) {
        $(this).closest("tr").remove();
        calTotDutyNineEgtAss('B');
        calTotDutyNineEgtAss('C');
    });
});

$(document).ready(function() {
	$("#addrow8").on("click", function() {
		var counter = $('#etr98Amnd tr').length + 1;
		var newRow = $("<tr>");
		var cols = "";
		cols += '<td><input type="text" id="etr98DrpDnAm' + counter + '" class="form-control" name="DUTY[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="etr98RateDutyAm' + counter + '" class="form-control" name="RATE_OF_DUTY[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="enter98NotnAm' + counter + '" class="form-control" name="NOTN[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="enter98SlnoAm' + counter + '" class="form-control" name="SL.NO[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="enter98EffRateAm' + counter + '" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="enter98DutyAmountAm' + counter + '" class="form-control" onblur="calTotDutyNineEgtAmd(' + "'U'" + ')"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
		cols += '<td><-+-input type="text" id="enter98DutyFgAmtAm' + counter + '" class="form-control" onblur="calTotDutyNineEgtAmd(' + "'U'" + ')" name="DUTY_FG[' + counter + ']" /></td>';
		cols += '<td><input type="button" id="enter98DltAm' + counter + '" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
		newRow.append(cols);
		$("table.order-list8").append(newRow);
		$.ajax({
			url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(datas) {
				$.each(datas, function(i, item) {
					$('#customDropDown' + (counter - 1) + '').append($('<option>', {
						text: item[1],
						value: item[0]
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
		counter++;
	});
	$("table.order-list8").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		calTotDutyNineEgtAmd("U");
	});
});

$(document).ready(function() {
	$("#addrow9").on("click", function() {
		var counter = $('#amOtEnter tr').length + 1;
		var newRow = $("<tr>");
		var cols = "";
		cols += '<td><input type="text" id="enterDropDownAm' + counter + '" class="form-control" name="DUTY[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="enterRateDutyAm' + counter + '" class="form-control" name="RATE_OF_DUTY[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="enterNotnAm' + counter + '" class="form-control" name="NOTN[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="enterSlnoAm' + counter + '" class="form-control" name="SL.NO[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="enterEffRateAm' + counter + '" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="enterDutyAmountAm' + counter + '" class="form-control" onblur="calTotDutyAmd()"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
		cols += '<td><input type="text" id="dutyFgAmtAm' + counter + '" class="form-control" onblur="calTotDutyAmd()" name="DUTY_FG[' + counter + ']" /></td>';
		cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
		newRow.append(cols);
		$("table.order-list9").append(newRow);
		$.ajax({
			url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(datas) {
				$.each(datas, function(i, item) {
					$('#customDropDown' + (counter - 1) + '').append($('<option>', {
						text: item[1],
						value: item[0]
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
		counter++;
	});
	$("table.order-list9").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		calTotDutyAmd();
	});
});

// Certificate Details	
$(document).ready(function() {
	$("#addrow").on("click", function() {
		var counter = $('#amOt tr').length + 1;
		var newRow = $("<tr>");
		var cols = "";
		cols += '<td><select type="text" id="cusAmDrpDn' + counter + '" onchange="getSpcOtherDigiAmend(this)" class="custom-select" name="DUTY[' + counter + ']" ><option>Select Duty</option></select></td>';
		cols += '<td><input type="text" id="cusAmDtRt' + counter + '" class="form-control" readonly="readonly" name="RATE_OF_DUTY[' + counter + ']" /></td>';
		cols += '<td><select type="text" id="cusAmNt' + counter + '" onchange="othersSlNoAmd(this)" class="custom-select" name="NOTN[' + counter + ']" ><option>Select NOTN</option></select></td>';
		cols += '<td><select type="text" id="cusAmSln' + counter + '" onchange="selectEachNsNoAmd(this)" class="custom-select" name="SL.NO[' + counter + ']" ><option>Select SL. No</option></select></td>';
		cols += '<td><input type="text"  id="cusAmRt' + counter + '" class="form-control" readonly="readonly" name="EFF.RATE[' + counter + ']" /></td>';
		cols += '<td><input type="text"  id="cusAmAmt' + counter + '" onblur="calTotDutyAmd()" class="form-control" style="text-align: right;" name="DUTY_AMOUNT[' + counter + ']" /></td>';
		cols += '<td><input type="text"  id="cusAmAmtFg' + counter + '" onblur="calTotDutyAmd()" class="form-control" style="text-align: right;" name="DUTY_FG[' + counter + ']" /></td>';
		cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger "  value=" - "></td>';
		newRow.append(cols);
		$("table.order-list").append(newRow);
		$.ajax({
			url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(datas) {
				$.each(datas, function(i, item) {
					$('#cusAmDrpDn' + (counter - 1) + '').append($('<option>', {
						text: item[1],
						value: item[0]
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
		counter++;
	});
	$("table.order-list").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		calTotDutyAmd();
	});
});

var totalDutyDiff = $('#allItemDuty').val();
var totalDutyDiffFg = $('#allItemDutyFg').val();

function calTotDuty(i) {
	var calTotDutyArray = [];
	var calTotDutyFgArray = [];
	var totalDutyCal = 0;
	var prvallitemduty = $('#allItemDuty').val();
    var prvDutyDiffFg = $('#allItemDutyFg').val();
    var prvtotitemduty = $('#totalOthersDuty').val();

    if (i==1)
       var prvtotitemduty = $('#itemDutyChange1').val();
//if (i==2)
// {
//   $('#allItemDuty').val((parseFloat(prvallitemduty)-parseFloat(prvtotitemduty)).toFixed(2));
// }
// else
// {
	$("#tbOtrDt").find("tr").each(function() {
		if (null != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()) {
			calTotDutyArray.push(parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()));
			totalDutyCal = totalDutyCal + parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val());
		}

		if (null != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val()) {
			calTotDutyFgArray.push($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
		}
	});

	$("#enterOthDuty").find("tr").each(function() {
	 
		if (null != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()) {
			calTotDutyArray.push(parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()));
			totalDutyCal = totalDutyCal + parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val());
		}

		if (null != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val()) {
			calTotDutyFgArray.push($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
		}
	});

	$('#totalOthersDuty').val(totalDutyCal);
	var totalItem = $('#noOfItems').text();
	var num = parseInt(totalItem, 10);
	if (num > 1) {
		var totalItemDuty = $('#totalOthersDuty').val();
		var basicTotalItemDuty = $('#itemDutyChange1').val();
		var totalDiff = (parseFloat(totalDutyDiff) - parseFloat(basicTotalItemDuty)).toFixed(2);

		$('#allItemDuty').val((parseFloat(prvallitemduty) + parseFloat(totalItemDuty) - parseFloat(prvtotitemduty)).toFixed(2));
	} else {
		$('#allItemDuty').val($('#totalOthersDuty').val());
	}
//}
	setFlagReq('O');
	calTotPayable();
}

var totalDutyDiffAmd = $('#allItemDuty').val();
var totalDutyDiffAmdFg = $('#allItemDutyFg').val();
function calTotDutyAmd() {
	var calTotDutyArrayAmd = [];
	var calTotDutyFgArrayAmd = [];
	var totalDutyCalAmd = 0;
	var totalDutyCalAmdFg = 0;

	$("#amOt").find("tr").each(function() {
		if (null != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()) {
			calTotDutyArrayAmd.push(parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()));
			totalDutyCalAmd = totalDutyCalAmd + parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val());
		}

		if (null != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val()) {
			calTotDutyFgArrayAmd.push($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
			totalDutyCalAmdFg = totalDutyCalAmdFg + parseFloat($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
		}
	});

	$("#amOtEnter").find("tr").each(function() {
		if (null != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()) {
			calTotDutyArrayAmd.push(parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()));
			totalDutyCalAmd = totalDutyCalAmd + parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val());
		}

		if (null != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val()) {
			calTotDutyFgArrayAmd.push($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
			totalDutyCalAmdFg = totalDutyCalAmdFg + parseFloat($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
		}
	});

	var totalItemAmd = $('#noOfItems').text();
	var numAmd = parseInt(totalItemAmd, 10);
	if (numAmd > 1) {
		var totalItemDutyAmd = $('#totalOthersDuty').val();
		var totalItemDutyAmdFg = $('#totalOthersDutyFg').val();
		var basicTotalItemDutyAmd = $('#itemDutyChange2').val();
		var basicTotalItemDutyAmdFg = $('#itemDutyFgChange2').val();

		var totalDiffAmd = (parseFloat(totalDutyDiffAmd) - parseFloat(basicTotalItemDutyAmd)).toFixed(2);
		var totalDiffAmdFg = (parseFloat(totalDutyDiffAmdFg) - parseFloat(basicTotalItemDutyAmdFg)).toFixed(2);

		$('#allItemDuty').val((parseFloat(totalItemDutyAmd) + parseFloat(totalDiffAmd)).toFixed(2));
		$('#allItemDutyFg').val((parseFloat(totalItemDutyAmdFg) + parseFloat(totalDiffAmdFg)).toFixed(2));

	} else {
		$('#allItemDuty').val((parseFloat(totalDutyCalAmd)).toFixed(2));
		$('#allItemDutyFg').val((parseFloat(totalDutyCalAmdFg)).toFixed(2));
	}
	$('#itemDutyChange2').val(totalDutyCalAmd);
	$('#itemDutyFgChange2').val((parseFloat(totalDutyCalAmdFg)).toFixed(2));

	setFlagReq('O');
}

function calTotDutyNineEgtAss(option) {
    //var calTotDutyArray = [];
    var calTotDutyFgArray = [];
    var totalDutyCal = 0;
    var totalDutyFg = 0;
    var bcdfg =  $("#itemBcdAmtFgChange1").val()=='' ? 0 : $("#itemBcdAmtFgChange1").val();
    var igstfg =  $("#itemIgstAmtFgChange1").val()=='' ? 0 : $("#itemIgstAmtFgChange1").val();
    var swfg =  $("#itemSwAmtFgChange").val()=='' ? 0 : $("#itemSwAmtFgChange").val();
    var bcd =  $("#itemBcdAmtChange1").val()=='' ? 0 : $("#itemBcdAmtChange1").val();
    var igst =  $("#itemIgstAmtChange1").val()=='' ? 0 : $("#itemIgstAmtChange1").val();
    var sw =  $("#itemSwAmtChange1").val()=='' ? 0 : $("#itemSwAmtChange1").val(); 
    var olditmduty = $("#itemDutyChange1").val()=='' ? 0 : $("#itemDutyChange1").val();
    var olditmdutyfg = $("#itemDutyFgChange1").val()=='' ? 0 : $("#itemDutyFgChange1").val();
    var totalItemDuty=0;
    var totalItemDutyFg=0;

     $("#etr98AssTab").find("tr").each(function() {
        if (null != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()) {
            //calTotDutyArray.push(parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()));
            totalDutyCal = totalDutyCal + parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val());
        }
        totalItemDuty = parseFloat(bcd) + parseFloat(igst) + parseFloat(sw) + parseFloat(totalDutyCal);

        if (null != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val()) {
        //    calTotDutyFgArray.push($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
            totalDutyFg = totalDutyFg + parseFloat($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
        }
        totalItemDutyFg = parseFloat(bcdfg) + parseFloat(igstfg) + parseFloat(swfg) + parseFloat(totalDutyFg);
    });

    var totalItem = $('#noOfItems').text();
    var num = parseInt(totalItem, 10);
    var oldallitemduty= $('#allItemDuty').val()=='' ? 0 : $('#allItemDuty').val();
    var oldallitemdutyfg=  $('#allItemDutyFg').val()=='' ? 0 : $('#allItemDutyFg').val();
    if ($('#allItemDutyFg').val() == null)
        oldallitemdutyfg = 0;
  //  var totalItemDutyFg = parseFloat($('#itemBcdAmtFgChange1').val()) + parseFloat($('#itemIgstAmtFgChange1').val()) + parseFloat($('#itemSwAmtFgChange').val()) + parseFloat(totalDutyFg);
   /* if (option == "A") {
        if (num > 1) {
            var basicTotalItemDuty = $('#itemDutyChange1').val();
            var totalDiff = (parseFloat(totalDutyDiff) - parseFloat(basicTotalItemDuty)).toFixed(2);
            $('#allItemDuty').val((parseFloat(totalItemDuty) + parseFloat(totalDiff)).toFixed(2));
            $('#itemDutyChange1').val((parseFloat(totalItemDuty)).toFixed(2));
        } else {
            $('#itemDutyChange1').val((parseFloat(totalItemDuty)).toFixed(2));
            $('#allItemDuty').val((parseFloat(totalItemDuty)).toFixed(2));
        }
    } else*/
      if (option == "B") {
	    $("#itemDutyChange1").val(parseFloat(totalItemDuty).toFixed(2));
        $('#allItemDuty').val((parseFloat($("#itemDutyChange1").val()) + parseFloat(oldallitemduty) - parseFloat(olditmduty)).toFixed(2));
        calTotPayable();
    }
      else if (option=='C'){
	    $("#itemDutyFgChange1").val(parseFloat(totalItemDutyFg).toFixed(2));
        $('#allItemDutyFg').val((parseFloat($("#itemDutyFgChange1").val()) + parseFloat(oldallitemdutyfg) - parseFloat(olditmdutyfg)).toFixed(2));}   
}


function calTotDutyNineEgtAmd(option) {
	var calTotDutyArrayAmd = [];
	var calTotDutyFgArrayAmd = [];
	var totalDutyCalAmd = 0;
	var totalDutyCalAmdFg = 0;
	$("#etr98Amnd").find("tr").each(function() {
		if (null != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()) {
			calTotDutyArrayAmd.push(parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()));
			totalDutyCalAmd = totalDutyCalAmd + parseFloat($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val());
		}

		if (null != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val()) {
			calTotDutyFgArrayAmd.push($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
			totalDutyCalAmdFg = totalDutyCalAmdFg + parseFloat($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
		}
	});

	var bcdAmtFg = $('#itemBcdAmtFgChange2s').val();
	var igstAmtFg = $('#itemIgstAmtFgChange2s').val();
	if (bcdAmtFg == 'NaN')
		bcdAmtFg = 0;

	if (igstAmtFg == '')
		igstAmtFg = 0;

	var totalItemAmd = $('#noOfItems').text();
	var numAmd = parseInt(totalItemAmd, 10);
	var totalItemDutyAmd = parseFloat($('#itemBcdAmtChange2s').val()) + parseFloat($('#itemIgstAmtChange2s').val()) + parseFloat($('#itemSwAmtChange2s').val()) + parseFloat(totalDutyCalAmd);
	var totalItemDutyAmdFg = parseFloat(bcdAmtFg) + parseFloat(igstAmtFg) + parseFloat(0) + parseFloat(totalDutyCalAmdFg);

	if (numAmd > 1) {
		var basicTotalItemDutyAmd = $('#itemDutyChange3s').val();
		var basicTotalItemDutyAmdFg = $('#itemDutyFgChange3s').val();
		var totalDiffAmd = (parseFloat(totalDutyDiffAmd) - parseFloat(basicTotalItemDutyAmd)).toFixed(2);
		var totalDiffAmdFg = (parseFloat(totalDutyDiffAmdFg) - parseFloat(basicTotalItemDutyAmdFg)).toFixed(2);

		$('#allItemDuty').val((parseFloat(totalItemDutyAmd) + parseFloat(totalDiffAmd)).toFixed(2));
		$('#allItemDutyFg').val((parseFloat(totalItemDutyAmdFg) + parseFloat(totalDiffAmdFg)).toFixed(2));
		$('#itemDutyChange3s').val((parseFloat(totalItemDutyAmd)).toFixed(2));
		$('#itemDutyFgChange3s').val((parseFloat(totalItemDutyAmdFg)).toFixed(2));
	} else {
		$('#itemDutyChange3s').val((parseFloat(totalItemDutyAmd)).toFixed(2));
		$('#allItemDuty').val((parseFloat(totalItemDutyAmd)).toFixed(2));
		$('#itemDutyFgChange3s').val((parseFloat(totalItemDutyAmdFg)).toFixed(2));
		$('#allItemDutyFg').val((parseFloat(totalItemDutyAmdFg)).toFixed(2));
	}
	setFlagReqNinEght('N');
}

// Certificate Details	
$(document).ready(function() {
	var counter = 1;
	$("#addrow2").on("click", function() {
		var newRow = $("<tr>");
		var cols = "";
		cols += '<td><input type="text" class="form-control" style="text-align: right;" id="addFine' + counter + '" onblur="addFine()" name="FINE[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control" value="Sec. " oninput="inputValue(this)" name="FINE_US[' + counter + ']" /></td>';
		cols += '<td><input type="button" name="addFinebtn" class="ibtnDel btn btn-sm btn-danger delbtn"  value="Del Fine"></td>';
		newRow.append(cols);
		$("table.order-list2").append(newRow);
		$.ajax({
			url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(datas) {
				$.each(datas, function(i, item) {
					$('#customDropDown' + (counter - 1) + '').append($('<option>', {
						text: item
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
		counter++;
	});
	$("table.order-list2").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		addFine();
	});
});

function addFine() {
	var caladdFineArray = [];
	var addFineCal = 0;
	$("#fineId").find("tr").each(function() {
		if (null != $(this).find("td").eq(0).find("input[name*='FINE']").val() && "" != $(this).find("td").eq(0).find("input[name*='FINE']").val()) {
			caladdFineArray.push(parseFloat($(this).find("td").eq(0).find("input[name*='FINE']").val()));
			addFineCal = addFineCal + parseFloat($(this).find("td").eq(0).find("input[name*='FINE']").val());
		}
	});
	$('#totalFine').val(addFineCal);
	calTotPayable();
}

$(document).ready(function() {
	var counter = 1;
	$("#addrow3").on("click", function() {
		var newRow = $("<tr>");
		var cols = "";
		cols += '<td><input type="text" class="form-control" style="text-align: right;" id="addPenalty' + counter + '" onblur="addPenalty()" name="PENALTY[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control" value="Sec. " oninput="inputValue(this)" name="PENALTY_US[' + counter + ']" /></td>';
		cols += '<td><input type="button" name="addPenaltybtn" class="ibtnDel btn btn-sm btn-danger delbtn"  value="Del Fine"></td>';
		newRow.append(cols);
		$("table.order-list3").append(newRow);
		$.ajax({
			url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(datas) {
				$.each(datas, function(i, item) {
					$('#customDropDown' + (counter - 1) + '').append($('<option>', {
						text: item
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
		counter++;
	});
	$("table.order-list3").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		addPenalty();
	});
});

function addPenalty() {
	var caladdPenaltyArray = [];
	var addPenaltyCal = 0;
	$("#penaltyId").find("tr").each(function() {
		if (null != $(this).find("td").eq(0).find("input[name*='PENALTY']").val() && "" != $(this).find("td").eq(0).find("input[name*='PENALTY']").val()) {
			caladdPenaltyArray.push(parseFloat($(this).find("td").eq(0).find("input[name*='PENALTY']").val()));
			addPenaltyCal = addPenaltyCal + parseFloat($(this).find("td").eq(0).find("input[name*='PENALTY']").val());
		}
	});
	$('#totalPenalty').val(addPenaltyCal);
	calTotPayable();
}

function finePenal() {
	var fpoaddFine;
	var fpoaddPenalty;
	var fpoaddFineList = [];
	var penaltyList = [];
	var fineArray = [];
	var FineUSArray = [];
	var penalArray = [];
	var penalUSArray = [];

	$("#penaltyId").find("tr").each(function() {
		fpoaddPenalty = {};
		if (null != $(this).find("td").eq(0).find("input[name*='PENALTY']").val() && "" != $(this).find("td").eq(0).find("input[name*='PENALTY']").val()) {
			fpoaddPenalty['penalAmt'] = $(this).find("td").eq(0).find("input[name*='PENALTY']").val();
		}

		if (null != $(this).find("td").eq(1).find("input[name*='PENALTY_US']").val() && "" != $(this).find("td").eq(1).find("input[name*='PENALTY_US']").val()) {
			fpoaddPenalty['penalUs'] = $(this).find("td").eq(1).find("input[name*='PENALTY_US']").val();
		}

		if (null != fpoaddPenalty['penalAmt'] && fpoaddPenalty['penalAmt'] != "") {
			fpoaddPenalty['cinNo'] = $('#inputPassword').val();
			fpoaddPenalty['cinDT'] = new Date($('#cinDt').val());
			fpoaddPenalty['item_ID'] = $('#itemId').val();
			fpoaddPenalty['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoaddPenalty['offId'] = $('#offId').val();
			fpoaddPenalty['cus_SITE'] = $('#cusSite').val();
			fpoaddPenalty['role'] = $('#role').val();
			fpoaddPenalty['ass_DT'] = new Date();
			penaltyList.push(fpoaddPenalty);
		}
	});

	$("#fineId").find("tr").each(function() {
		fpoaddFine = {};
		if (null != $(this).find("td").eq(0).find("input[name*='FINE']").val() && "" != $(this).find("td").eq(0).find("input[name*='FINE']").val()) {
			fpoaddFine['fineAmt'] = $(this).find("td").eq(0).find("input[name*='FINE']").val();
		}
		if (null != $(this).find("td").eq(1).find("input[name*='FINE_US']").val() && "" != $(this).find("td").eq(1).find("input[name*='FINE_US']").val()) {
			fpoaddFine['fineUs'] = $(this).find("td").eq(1).find("input[name*='FINE_US']").val();
		}

		if (null != fpoaddFine['fineAmt'] && fpoaddFine['fineAmt'] != "") {
			fpoaddFine['cinNo'] = $('#inputPassword').val();
			fpoaddFine['cinDT'] = new Date($('#cinDt').val());
			fpoaddFine['item_ID'] = $('#itemId').val();
			fpoaddFine['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoaddFine['offId'] = $('#offId').val();
			fpoaddFine['cus_SITE'] = $('#cusSite').val();
			fpoaddFine['role'] = $('#role').val();
			fpoaddFine['ass_DT'] = new Date();
			fpoaddFine['fpoPenalList'] = penaltyList;
			fpoaddFineList.push(fpoaddFine);
			fpoaddFine['Comments'] = $('#deptComts').val();
			fpoaddFine['totFine'] = $('#totalFine').val();
			fpoaddFine['totPenalty'] = $('#totalPenalty').val();
			fpoaddFine['totalDutyPayable'] = $('#totaldutyPayable').val();
		}
	});

	$.ajax({
		url: 'getFinePenal',
		data: JSON.stringify(fpoaddFineList),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(fpoaddFineList) {

			$("#fineId").find("tr").each(function() {
				$(this).find("td").eq(0).find("input[name*='FINE']").attr("disabled", true);
				$(this).find("td").eq(1).find("input[name*='FINE_US']").attr("disabled", true);
			});

			$("#penaltyId").find("tr").each(function() {
				$(this).find("td").eq(0).find("input[name*='PENALTY']").attr("disabled", true);
				$(this).find("td").eq(1).find("input[name*='PENALTY_US']").attr("disabled", true);
			});

			$("#deptComts").attr("disabled", true);
			$("#saveBtn").attr("disabled", true);
			$("#addrow2").attr("disabled", true);
			$("#addrow3").attr("disabled", true);
			$(".delbtn").attr("disabled", true);
			$(".amendBtn").attr("disabled", false);

		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

// Amend Fine
$(document).ready(function() {
	var counter = 1;
	$("#addrow4").on("click", function() {
		var newRow = $("<tr>");
		var cols = "";
		cols += '<td><input type="text" class="form-control" onblur="addAmendFine()" style="text-align: right;" name="FINE[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control" value="Sec. " oninput="inputValue(this)" name="FINE_US[' + counter + ']" /></td>';
		cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger"  value="Del"></td>';
		newRow.append(cols);
		$("table.order-list4").append(newRow);
		$.ajax({
			url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(datas) {
				$.each(datas, function(i, item) {
					$('#customDropDown' + (counter - 1) + '').append($('<option>', {
						text: item
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
		counter++;
	});
	$("table.order-list4").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		addAmendFine();
	});
});


function addAmendFine() {
	var totalAmendFine = [];
	var addAmendFineCal = 0;
	$("#fineAmendId").find("tr").each(function() {
		if (null != $(this).find("td").eq(0).find("input[name*='FINE']").val() && "" != $(this).find("td").eq(0).find("input[name*='FINE']").val()) {
			totalAmendFine.push(parseFloat($(this).find("td").eq(0).find("input[name*='FINE']").val()));
			addAmendFineCal = addAmendFineCal + parseFloat($(this).find("td").eq(0).find("input[name*='FINE']").val());
		}
	});
	$('#totalAmendFine').val(addAmendFineCal);
}

$(document).ready(function() {
	var counter = 1;
	$("#addrow5").on("click", function() {
		var newRow = $("<tr>");
		var cols = "";
		cols += '<td><input type="text" class="form-control" style="text-align: right;" onblur="addAmendPenalty()" name="PENALTY[' + counter + ']" /></td>';
		cols += '<td><input type="text" class="form-control " value="Sec. " oninput="inputValue(this)" name="PENALTY_US[' + counter + ']" /></td>';
		cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger"  value="Del"></td>';
		newRow.append(cols);
		$("table.order-list5").append(newRow);
		$.ajax({
			url: '' + $('#inputPassword').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(datas) {
				$.each(datas, function(i, item) {
					$('#customDropDown' + (counter - 1) + '').append($('<option>', {
						text: item
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
		counter++;
	});
	$("table.order-list5").on("click", ".ibtnDel", function(event) {
		$(this).closest("tr").remove();
		addAmendPenalty();
	});
});

function addAmendPenalty() {
	var totalAmendPenalty = [];
	var addAmendPenaltyCal = 0;
	$("#AmendpenaltyId").find("tr").each(function() {
		if (null != $(this).find("td").eq(0).find("input[name*='PENALTY']").val() && "" != $(this).find("td").eq(0).find("input[name*='PENALTY']").val()) {
			totalAmendPenalty.push(parseFloat($(this).find("td").eq(0).find("input[name*='PENALTY']").val()));
			addAmendPenaltyCal = addAmendPenaltyCal + parseFloat($(this).find("td").eq(0).find("input[name*='PENALTY']").val());
		}
	});
	$('#totalAmendPenalty').val(addAmendPenaltyCal);

}

function finePenalAmend() {
	var fpoaddFine;
	var fpoaddPenalty;
	var fpoaddFineList = [];
	var penaltyList = [];
	var fineArray = [];
	var FineUSArray = [];
	var penalArray = [];
	var penalUSArray = [];

	$("#AmendpenaltyId").find("tr").each(function() {
		fpoaddPenalty = {};
		if (null != $(this).find("td").eq(0).find("input[name*='PENALTY']").val() && "" != $(this).find("td").eq(0).find("input[name*='PENALTY']").val()) {
			fpoaddPenalty['penalAmt'] = $(this).find("td").eq(0).find("input[name*='PENALTY']").val();
		}

		if (null != $(this).find("td").eq(1).find("input[name*='PENALTY_US']").val() && "" != $(this).find("td").eq(1).find("input[name*='PENALTY_US']").val()) {
			fpoaddPenalty['penalUs'] = $(this).find("td").eq(1).find("input[name*='PENALTY_US']").val();
		}

		if (null != fpoaddPenalty['penalAmt'] && fpoaddPenalty['penalAmt'] != "") {
			fpoaddPenalty['cinNo'] = $('#inputPassword').val();
			fpoaddPenalty['cinDT'] = new Date($('#cinDt').val());
			fpoaddPenalty['item_ID'] = $('#itemId').val();
			fpoaddPenalty['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoaddPenalty['offId'] = $('#offId').val();
			fpoaddPenalty['cus_SITE'] = $('#cusSite').val();
			fpoaddPenalty['role'] = $('#role').val();
			fpoaddPenalty['ass_DT'] = new Date();
			penaltyList.push(fpoaddPenalty);
		}
	});

	$("#fineAmendId").find("tr").each(function() {
		fpoaddFine = {};
		if (null != $(this).find("td").eq(0).find("input[name*='FINE']").val() && "" != $(this).find("td").eq(0).find("input[name*='FINE']").val()) {
			fpoaddFine['fineAmt'] = $(this).find("td").eq(0).find("input[name*='FINE']").val();
		}
		if (null != $(this).find("td").eq(1).find("input[name*='FINE_US']").val() && "" != $(this).find("td").eq(1).find("input[name*='FINE_US']").val()) {
			fpoaddFine['fineUs'] = $(this).find("td").eq(1).find("input[name*='FINE_US']").val();
		}

		if (null != fpoaddFine['fineAmt'] && fpoaddFine['fineAmt'] != "") {
			fpoaddFine['cinNo'] = $('#inputPassword').val();
			fpoaddFine['cinDT'] = new Date($('#cinDt').val());
			fpoaddFine['item_ID'] = $('#itemId').val();
			fpoaddFine['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoaddFine['offId'] = $('#offId').val();
			fpoaddFine['cus_SITE'] = $('#cusSite').val();
			fpoaddFine['role'] = $('#role').val();
			fpoaddFine['ass_DT'] = new Date();
			fpoaddFine['fpoPenalList'] = penaltyList;
			fpoaddFineList.push(fpoaddFine);
			fpoaddFine['Comments'] = $('#deptComts1').val();
			fpoaddFine['totFine'] = $('#totalAmendFine').val();
			fpoaddFine['totPenalty'] = $('#totalAmendPenalty').val();
		}
	});

	$.ajax({
		url: 'getAmendFinePenal',
		data: JSON.stringify(fpoaddFineList),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(fpoaddFineList) {
			alert("Fine and Penalty Updated Successfully");
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

$.ajax({
	url: 'getTotalNoItems?cinNo=' + $('#inputPassword').val(),
	data: JSON.stringify(""),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(data) {

		$("#noOfItems").text(data);
		$('#itemPagination1').text($('#itemPagination1').text() + "/" + data);
	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});

$.ajax({
	url: 'getItemAllCth?cinNo=' + $('#inputPassword').val(),
	data: JSON.stringify(""),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(datas) {
		$.each(datas, function(i, item) {
			$("#" + (i + 1) + " option:contains(" + item + ")").attr('selected', 'selected');
		});
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

function changeDetails(value, opt) {
    if (opt == 1)
     {
      alert("AMENDMENT IS NOT ALLOWED AT ARTICLES AWAITING FOR ARRIVAL (AAA) STAGE.");
      $("#exampleModalLong").modal('hide');
     // $("#exampleModalLong").hide();
     // $("#exampleModalLongAddItem").(hide);
      $("#exampleModalLongAddItem").modal('hide');
      $("#exampleModalLongAddItem").hide();
     }
    else
      contamend(value);
  }
 
 function contamend(value)
 {
	clearDropDown($("#itemBcdNoNtChange2s"));
	clearDropDown($("#itemIgstNoNtChange2s"));
	clearDropDown($("#itemSwNoNtChange2s"));
	clearDropDown($("#itemBcdNsNoChange2s"));
	clearDropDown($("#itemIgstNsNoChange2s"));
	clearDropDown($("#itemSwNsNoChange2s"));
	clearDropDown($("#cusAmDrpDn"));

	var id = $('#inputPassword').val();
	var developerData = {};
	var developerData1 = {};
	var developerData2 = {};
	var developerData3 = {};
	var developerData4 = {};
	var developerData5 = {};
	var developerData6 = {};

	developerData["cinNo"] = id;
	developerData["item_NO"] = $("#itemNoChange").val();
	$.ajax({
		url: 'changeItem?id=' + id + '&itemNo=' + $("#itemNoChange").val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerData) {
			$("#itemNoChanges").val(developerData['item_NO']);
			$("#itemNouChanges").val(developerData['nou']);
			$("#itemDESChanges").val(developerData['item_DESC']);
			$("#itemCthChanges").val(developerData['cth']);
			$("#itemRevisedCthChanges").val(developerData['cth_REVISED']);
			$("#itemNetWtChanges").val(developerData['netwt']);
			$("#itemDeclValChanges").val(developerData['decl_VAL']);
			$("#itemCurCdChanges").val(developerData['currcd']);
			$("#itemOrgCntryCdChanges").val(developerData['origcntrycd']);
			$("#queryChange").val(developerData['query']);
			$("#2 option:contains(" + developerData['gen_CTH'] + ")").attr('selected', 'selected');
			$.ajax({
				url: 'getBcdNotification?cth=' + developerData['gen_CTH'],
				data: JSON.stringify(developerData1),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(data) {

					$("#itemBcdNoNtChange2s").append($('<option>', {
						text: "Select NOTN"
					}));
					$.each(data, function(i, item) {
						$('#itemBcdNoNtChange2s').append($('<option>', {
							text: item
						}));
					});
					$("#itemBcdNoNtChange2s option:contains(" + developerData['bcd_NOTN'] + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$.ajax({
				url: 'getBcdSerialNo?notificationNo=' + developerData['bcd_NOTN'] + '&cth=' + developerData['gen_CTH'],
				data: JSON.stringify(developerData2),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(data) {
					$("#itemBcdNsNoChange2s").append($('<option>', {
						text: "Select NSNO"
					}));
					$.each(data, function(i, item) {
						$('#itemBcdNsNoChange2s').append($('<option>', {
							text: item
						}));

					});
					$("#itemBcdNsNoChange2s option:contains(" + developerData['bcd_NSNO'] + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$("#itemBcdRtaChange2s").val(developerData['bcd_RTA']);
			$("#itemBcdAmtChange2s").val(developerData['bcd_AMT']);
			$("#itemBcdAmtFgChange2s").val(developerData['bcd_AMT_FG']);
			$.ajax({
				url: 'getIgstNotification?cth=' + developerData['gen_CTH'],
				data: JSON.stringify(developerData3),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(data) {
					$("#itemIgstNoNtChange2s").append($('<option>', {
						text: "Select NOTN"
					}));
					$.each(data, function(i, item) {
						$('#itemIgstNoNtChange2s').append($('<option>', {
							text: item
						}));
					});
					$("#itemIgstNoNtChange2s option:contains(" + developerData['igst_NOTN'] + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$.ajax({
				url: 'getIgstSerialNo?notificationNo=' + developerData['igst_NOTN'] + '&cth=' + developerData['gen_CTH'],
				data: JSON.stringify(developerData4),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(data) {
					$("#itemIgstNsNoChange2s").append($('<option>', {
						text: "Select NSNO"
					}));
					$.each(data, function(i, item) {
						$('#itemIgstNsNoChange2s').append($('<option>', {
							text: item
						}));

					});
					$("#itemIgstNsNoChange2s option:contains(" + developerData['igst_NSNO'] + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$("#itemIgstRtaChange2s").val(developerData['igst_RTA']);
			$("#itemIgstAmtChange2s").val(developerData['igst_AMT']);
			$("#itemIgstAmtFgChange2s").val(developerData['igst_AMT_FG']);
			$.ajax({
				url: 'getSwNotification?cth=' + developerData['gen_CTH'],
				data: JSON.stringify(developerData5),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(data) {
					$("#itemSwNoNtChange2s").append($('<option>', {
						text: "Select NOTN"
					}));
					$.each(data, function(i, item) {
						$('#itemSwNoNtChange2s').append($('<option>', {
							text: item
						}));
					});
					$("#itemSwNoNtChange2s option:contains(" + developerData['sw_NOTN'] + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$.ajax({
				url: 'getSwSerialNo?notificationNo=' + developerData['sw_NOTN'] + '&cth=' + developerData['gen_CTH'],
				data: JSON.stringify(developerData6),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(data) {
					$("#itemSwNsNoChange2s").append($('<option>', {
						text: "Select NSNO"
					}));
					$.each(data, function(i, item) {
						$('#itemSwNsNoChange2s').append($('<option>', {
							text: item
						}));

					});
					$("#itemSwNsNoChange2s option:contains(" + developerData['sw_NSNO'] + ")").attr('selected', 'selected');
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
			$("#itemSwRtaChange2s").val(developerData['sw_RTA']);
			$("#itemSwAmtChange2s").val(developerData['sw_AMT']);
			$("#itemSwAmtFgChange2s").val(developerData['sw_AMT_FG']);
			$("#itemRateChange2s").val(developerData['rate']);
			$("#itemDutyChange2s").val(developerData['duty']);
			$("#itemDutyFgChange2s").val(developerData['duty_FG']);

			var fpoItemDetOthDuty = {};
			fpoItemDetOthDuty['cin_NO'] = $('#inputPassword').val();
			fpoItemDetOthDuty['item_NO'] = 1;

			$.ajax({
				url: 'getAllOthrOnCinNor',
				data: JSON.stringify(fpoItemDetOthDuty),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(fpoItemDetOthDutyList) {

					var counter = fpoItemDetOthDutyList.length;
					var stRow = 0;
					var custRow = 1;
					var entRow = 1;
					var ent98Row = 1;
					dropCount = 0;
					$("#etr98Amnd tr").remove();
					$("#amOt tr").remove();
					$("#amOtEnter tr").remove();
					while (stRow < counter) {
						if (null != fpoItemDetOthDutyList[stRow].duty_CD && fpoItemDetOthDutyList[stRow].duty_CD != "" && !(fpoItemDetOthDutyList[stRow].duty_CD.toString()=='Select')) {
							var newRow = $("<tr>");
							var cols = "";
							cols += '<td><select type="text" id="cusAmDrpDn' + custRow + '" onchange="othersNotn(this)" class="custom-select" name="DUTY[' + custRow + ']" ><option>Select Duty</option></select></td>';
							cols += '<td><input type="text" id="cusAmDtRt' + custRow + '" class="form-control" readonly="readonly" name="RATE_OF_DUTY[' + custRow + ']" /></td>';
							cols += '<td><select type="text" id="cusAmNt' + custRow + '" onchange="othersSlNo(this)" class="custom-select" name="NOTN[' + custRow + ']" ><option>Select NOTN</option></select></td>';
							cols += '<td><select type="text" id="cusAmSln' + custRow + '" onchange="selectEachNsNo(this)" class="custom-select" name="SL.NO[' + custRow + ']" ><option>Select SL. No</option></select></td>';
							cols += '<td><input type="text"  id="cusAmRt' + custRow + '" class="form-control" readonly="readonly" name="EFF.RATE[' + custRow + ']" /></td>';
							cols += '<td><input type="text"  id="cusAmAmt' + custRow + '" onblur="calTotDutyAmd()" class="form-control" style="text-align: right;" name="DUTY_AMOUNT[' + custRow + ']" /></td>';
							cols += '<td><input type="text"  id="cusAmAmtFg' + custRow + '" onblur="calTotDutyAmd()" class="form-control" style="text-align: right;" name="DUTY_FG[' + custRow + ']" /></td>';
							cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + custRow + ']" value=" - "></td>';
							newRow.append(cols);
							$("table.order-list").append(newRow);

							$.ajax({
								url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
								data: JSON.stringify(""),
								dataType: "json",
								contentType: "application/json",
								type: "post",
								success: function(datas) {
									$.each(datas, function(i, item) {
										$('#cusAmDrpDn' + (dropCount + 1) + '').append($('<option>', {
											text: item[1],
											value: item[0]
										}));
									});
									$("#cusAmDrpDn" + (dropCount + 1) + " option:contains(" + $("#cusAmDrpDn" + (dropCount + 1) + " option[value=" + fpoItemDetOthDutyList[dropCount].duty_CD + "]").text() + ")").attr('selected', 'selected');
									//$("#cusAmDrpDn" + (dropCount + 1) + "").attr("disabled", true);
									$("#cusAmDtRt" + (dropCount + 1) + "").val(fpoItemDetOthDutyList[dropCount].duty_RTA);
									//$("#cusAmDtRt" + (dropCount + 1) + "").attr("disabled", true);
									$("#cusAmRt" + (dropCount + 1) + "").val(fpoItemDetOthDutyList[dropCount].rate);
									//$("#cusAmRt" + (dropCount + 1) + "").attr("disabled", true);
									$("#cusAmAmt" + (dropCount + 1) + "").val(fpoItemDetOthDutyList[dropCount].duty_AMT);
									//$("#cusAmAmt" + (dropCount + 1) + "").attr("disabled", true);
									$("#cusAmAmtFg" + (dropCount + 1) + "").val(fpoItemDetOthDutyList[dropCount].duty_FG);
									//$("#cusAmAmtFg" + (dropCount + 1) + "").attr("disabled", true);

									$("#cusAmNt" + (dropCount + 1)).append($('<option>', {
										text: fpoItemDetOthDutyList[dropCount].duty_NOTN
									}));
									$("#cusAmNt" + (dropCount + 1) + " option:contains(" + fpoItemDetOthDutyList[dropCount].duty_NOTN + ")").attr('selected', 'selected');
									//$("#cusAmNt" + (dropCount + 1) + "").attr("disabled", true);

									$("#cusAmSln" + (dropCount + 1)).append($('<option>', {
										text: fpoItemDetOthDutyList[dropCount].duty_SLNO
									}));
									$("#cusAmSln" + (dropCount + 1) + " option:contains(" + fpoItemDetOthDutyList[dropCount].duty_SLNO + ")").attr('selected', 'selected');
									//$("#cusAmSln" + (dropCount + 1) + "").attr("disabled", true);

									/*if (null != developerDatas && null != developerDatas['ass_DT'] && fpoItemDetOthDutyList.length != 0 && new Date(developerDatas['ass_DT']) < new Date(fpoItemDetOthDutyList.ass_DT)) {
										$("#1 option:contains(" + "Others" + ")").prop("selected", true);
										$('#1').attr("disabled", true);
										$(".blueClass").show();
										$(".yellowClass").hide();
									} else {
										if (null != developerDatas && null == developerDatas['ass_DT']) {
											$("#1 option:contains(" + "Others" + ")").prop("selected", true);
											$('#1').attr("disabled", true);
											$(".blueClass").show();
											$(".yellowClass").hide();
										}
									}*/

									$('#getOtherDigitAmend').append($('<option>', {
										text: fpoItemDetOthDutyList[dropCount].cth
									}));
									$("#getOtherDigitAmend option:contains(" + fpoItemDetOthDutyList[dropCount].cth + ")").attr('selected', 'selected');
									//$('#getOtherDigitAmend').attr("disabled", true);

									$('#getOtherEightDigitAmend').append($('<option>', {
										text: fpoItemDetOthDutyList[dropCount].cth.substr(0, 4)
									}));
									$("#getOtherEightDigitAmend option:contains(" + fpoItemDetOthDutyList[dropCount].cth.substr(0, 4) + ")").attr('selected', 'selected');
									//	$('#getOtherEightDigitAmend').attr("disabled", true);

									$('#getOtherFourDigitAmend').append($('<option>', {
										text: fpoItemDetOthDutyList[dropCount].cth.substr(0, 2)
									}));
									$("#getOtherFourDigitAmend option:contains(" + fpoItemDetOthDutyList[dropCount].cth.substr(0, 2) + ")").attr('selected', 'selected');
									//	$('#getOtherFourDigitAmend').attr("disabled", true);

									dropCount = dropCount + 1;
								},
								fail: function(rs, e) {
									console.log(rs, responseText);
								}
							});
							custRow = custRow + 1;
						} else if (fpoItemDetOthDutyList[stRow].cth != "98049000" && fpoItemDetOthDutyList[stRow].cth != "98041000") {
							var newRow = $("<tr>");
							var cols = "";
							cols += '<td><input type="text" id="enterDropDownAm' + entRow + '" class="form-control" name="DUTY[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enterRateDutyAm' + entRow + '" class="form-control" name="RATE_OF_DUTY[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enterNotnAm' + entRow + '" class="form-control" name="NOTN[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enterSlnoAm' + entRow + '" class="form-control" name="SL.NO[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enterEffRateAm' + entRow + '" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enterDutyAmountAm' + entRow + '" class="form-control" onblur="calTotDutyAmd()"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="dutyFgAmtAm' + entRow + '" class="form-control" onblur="calTotDutyAmd()" name="DUTY_FG[' + counter + ']" /></td>';
							cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
							newRow.append(cols);
							$("table.order-list9").append(newRow);

							$('#enterDropDownAm' + entRow).val(fpoItemDetOthDutyList[stRow].duty_DESC);
							//$('#enterDropDownAm'+entRow).attr("disabled", true);
							$('#enterRateDutyAm' + entRow).val(fpoItemDetOthDutyList[stRow].duty_RTA);
							//$('#enterRateDutyAm'+entRow).attr("disabled", true);
							$('#enterNotnAm' + entRow).val(fpoItemDetOthDutyList[stRow].duty_NOTN);
							//$('#enterNotnAm'+entRow).attr("disabled", true);
							$('#enterSlnoAm' + entRow).val(fpoItemDetOthDutyList[stRow].duty_SLNO);
							//$('#enterSlnoAm'+entRow).attr("disabled", true);
							$('#enterEffRateAm' + entRow).val(fpoItemDetOthDutyList[stRow].rate);
							//$('#enterEffRateAm'+entRow).attr("disabled", true);
							$('#enterDutyAmountAm' + entRow).val(fpoItemDetOthDutyList[stRow].duty_AMT);
							//$('#enterDutyAmountAm'+entRow).attr("disabled", true);
							$('#dutyFgAmtAm' + entRow).val(fpoItemDetOthDutyList[stRow].duty_FG);
							//$('#dutyFgAmtAm'+entRow).attr("disabled", true);
							entRow = entRow + 1;
						} else {
							var newRow = $("<tr>");
							var cols = "";
							cols += '<td><input type="text" id="etr98DrpDnAm' + ent98Row + '" class="form-control" name="DUTY[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="etr98RateDutyAm' + ent98Row + '" class="form-control" name="RATE_OF_DUTY[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enter98NotnAm' + ent98Row + '" class="form-control" name="NOTN[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enter98SlnoAm' + ent98Row + '" class="form-control" name="SL.NO[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enter98EffRateAm' + ent98Row + '" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enter98DutyAmountAm' + ent98Row + '" class="form-control" onblur="calTotDutyNineEgtAmd(' + "'U'" + ')"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
							cols += '<td><input type="text" id="enter98DutyFgAmtAm' + ent98Row + '" class="form-control" onblur="calTotDutyNineEgtAmd(' + "'U'" + ')" name="DUTY_FG[' + counter + ']" /></td>';
							cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
							newRow.append(cols);
							$("table.order-list8").append(newRow);

							$('#etr98DrpDnAm' + ent98Row).val(fpoItemDetOthDutyList[stRow].duty_DESC);
							//$('#etr98DropDown'+ent98Row).attr("disabled", true);
							$('#etr98RateDutyAm' + ent98Row).val(fpoItemDetOthDutyList[stRow].duty_RTA);
							//$('#etr98RateDuty'+ent98Row).attr("disabled", true);
							$('#enter98NotnAm' + ent98Row).val(fpoItemDetOthDutyList[stRow].duty_NOTN);
							//$('#etr98Notn'+ent98Row).attr("disabled", true);
							$('#enter98SlnoAm' + ent98Row).val(fpoItemDetOthDutyList[stRow].duty_SLNO);
							//$('#etr98Slno'+ent98Row).attr("disabled", true);
							$('#enter98EffRateAm' + ent98Row).val(fpoItemDetOthDutyList[stRow].rate);
							//$('#etr98EffRate'+ent98Row).attr("disabled", true);
							$('#enter98DutyAmountAm' + ent98Row).val(fpoItemDetOthDutyList[stRow].duty_AMT);
							//$('#etr98DutyAmount'+ent98Row).attr("disabled", true);
							$('#enter98DutyFgAmtAm' + ent98Row).val(fpoItemDetOthDutyList[stRow].duty_FG);
							//$('#etr98dtFgAmt'+ent98Row).attr("disabled", true);
							ent98Row = ent98Row + 1;
						}
						stRow = stRow + 1;
					}

					if ($("#amOtEnter tr").length == 0) {

						var newRow = $("<tr>");
						var cols = "";
						cols += '<td><input type="text" id="enterDropDownAm1" class="form-control" name="DUTY[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enterRateDutyAm1" class="form-control" name="RATE_OF_DUTY[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enterNotnAm1" class="form-control" name="NOTN[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enterSlnoAm1" class="form-control" name="SL.NO[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enterEffRateAm1" class="form-control" name="EFF.RATE[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enterDutyAmountAm1" class="form-control" onblur="calTotDutyAmd()"  name="DUTY_AMOUNT[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="dutyFgAmtAm1" class="form-control" onblur="calTotDutyAmd()" name="DUTY_FG[' + 1 + ']" /></td>';
						cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + 1 + ']" value=" - "></td>';
						newRow.append(cols);
						$("table.order-list9").append(newRow);

						if ($("#getOtherDigitAmend").val() == "Select") {
							$('#enterDropDownAm1').attr("disabled", true);
							$('#enterRateDutyAm1').attr("disabled", true);
							$('#enterNotnAm1').attr("disabled", true);
							$('#enterSlnoAm1').attr("disabled", true);
							$('#enterEffRateAm1').attr("disabled", true);
							$('#enterDutyAmountAm1').attr("disabled", true);
							$('#dutyFgAmtAm1').attr("disabled", true);
							$('#addrow9').attr("disabled", true);
						}
					}

					if ($("#amOt tr").length == 0) {

						var newRow = $("<tr>");
						var cols = "";
						cols += '<td><select type="text" id="cusAmDrpDn1" onchange="getSpcOtherDigiAmend(this)" class="custom-select" name="DUTY[' + 1 + ']" ><option>Select Duty</option></select></td>';
						cols += '<td><input type="text" id="cusAmDtRt1" class="form-control" readonly="readonly" name="RATE_OF_DUTY[' + 1 + ']" /></td>';
						cols += '<td><select type="text" id="cusAmNt1" onchange="othersSlNoAmd(this)" class="custom-select" name="NOTN[' + 1 + ']" ><option>Select NOTN</option></select></td>';
						cols += '<td><select type="text" id="cusAmSln1" onchange="selectEachNsNoAmd(this)" class="custom-select" name="SL.NO[' + 1 + ']" ><option>Select SL. No</option></select></td>';
						cols += '<td><input type="text"  id="cusAmRt1" class="form-control" readonly="readonly" name="EFF.RATE[' + 1 + ']" /></td>';
						cols += '<td><input type="text"  id="cusAmAmt1" onblur="calTotDutyAmd()" class="form-control" style="text-align: right;" name="DUTY_AMOUNT[' + 1 + ']" /></td>';
						cols += '<td><input type="text"  id="cusAmAmtFg1" onblur="calTotDutyAmd()" class="form-control" style="text-align: right;" name="DUTY_FG[' + 1 + ']" /></td>';
						//cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + 1 + ']" value="Delete"></td>';
						newRow.append(cols);
						$("table.order-list").append(newRow);

						if ($("#getOtherDigitAmend").val() == "Select") {
							$('#cusAmDrpDn1').attr("disabled", true);
							$('#cusAmDtRt1').attr("disabled", true);
							$('#cusAmNt1').attr("disabled", true);
							$('#cusAmSln1').attr("disabled", true);
							$('#cusAmRt1').attr("disabled", true);
							$('#cusAmAmt1').attr("disabled", true);
							$('#cusAmAmtFg1').attr("disabled", true);
							$('#addrow').attr("disabled", true);
						}

						$.ajax({
							url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
							data: JSON.stringify(""),
							dataType: "json",
							contentType: "application/json",
							type: "post",
							success: function(datas) {
								$.each(datas, function(i, item) {
									$('#cusAmDrpDn1').append($('<option>', {
										text: item[1],
										value: item[0]
									}));
								});
								//$("#cusAmDrpDn1 option:contains(" + $("#cusAmDrpDn1 option[value=" + fpoItemDetOthDutyList[dropCount].duty_CD + "]").text() + ")").attr('selected', 'selected');
								//$("#cusAmDrpDn1").attr("disabled", true);
								/*$("#cusAmDtRt1").val(fpoItemDetOthDutyList[dropCount].duty_RTA);
								//$("#cusAmDtRt" + (dropCount + 1) + "").attr("disabled", true);
								$("#cusAmRt1").val(fpoItemDetOthDutyList[dropCount].rate);
								//$("#cusAmRt" + (dropCount + 1) + "").attr("disabled", true);
								$("#cusAmAmt1").val(fpoItemDetOthDutyList[dropCount].duty_AMT);
								//$("#cusAmAmt1").attr("disabled", true);
								$("#cusAmAmtFg1").val(fpoItemDetOthDutyList[dropCount].duty_FG);
								//$("#cusAmAmtFg" + (dropCount + 1) + "").attr("disabled", true);

								$("#cusAmNt1").append($('<option>', {
									text: fpoItemDetOthDutyList[dropCount].duty_NOTN
								}));
								$("#cusAmNt1 option:contains(" + fpoItemDetOthDutyList[dropCount].duty_NOTN + ")").attr('selected', 'selected');
								//$("#cusAmNt" + (dropCount + 1) + "").attr("disabled", true);

								$("#cusAmSln1").append($('<option>', {
									text: fpoItemDetOthDutyList[dropCount].duty_SLNO
								}));
								$("#cusAmSln1") + " option:contains(" + fpoItemDetOthDutyList[dropCount].duty_SLNO + ")").attr('selected', 'selected');
								//$("#cusAmSln" + (dropCount + 1) + "").attr("disabled", true);

								$('#getOtherDigitAmend').append($('<option>', {
									text: fpoItemDetOthDutyList[dropCount].cth
								}));
								$("#getOtherDigitAmend option:contains(" + fpoItemDetOthDutyList[dropCount].cth + ")").attr('selected', 'selected');
								//$('#getOtherDigitAmend').attr("disabled", true);

								$('#getOtherEightDigitAmend').append($('<option>', {
									text: fpoItemDetOthDutyList[dropCount].cth.substr(0, 4)
								}));
								$("#getOtherEightDigitAmend option:contains(" + fpoItemDetOthDutyList[dropCount].cth.substr(0, 4) + ")").attr('selected', 'selected');
								//	$('#getOtherEightDigitAmend').attr("disabled", true);

								$('#getOtherFourDigitAmend').append($('<option>', {
									text: fpoItemDetOthDutyList[dropCount].cth.substr(0, 2)
								}));
								$("#getOtherFourDigitAmend option:contains(" + fpoItemDetOthDutyList[dropCount].cth.substr(0, 2) + ")").attr('selected', 'selected');
								//	$('#getOtherFourDigitAmend').attr("disabled", true);*/

							},
							fail: function(rs, e) {
								console.log(rs, responseText);
							}
						});
					}

					if ($("#etr98Amnd tr").length == 0) {

						var newRow = $("<tr>");
						var cols = "";
						cols += '<td><input type="text" id="etr98DrpDnAm1" class="form-control" name="DUTY[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="etr98RateDutyAm1" class="form-control" name="RATE_OF_DUTY[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enter98NotnAm1" class="form-control" name="NOTN[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enter98SlnoAm1" class="form-control" name="SL.NO[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enter98EffRateAm1" class="form-control" name="EFF.RATE[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enter98DutyAmountAm1" class="form-control" onblur="calTotDutyNineEgtAmd(' + "'U'" + ')"  name="DUTY_AMOUNT[' + 1 + ']" /></td>';
						cols += '<td><input type="text" id="enter98DutyFgAmtAm1" class="form-control" onblur="calTotDutyNineEgtAmd(' + "'U'" + ')" name="DUTY_FG[' + 1 + ']" /></td>';
						cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + 1 + ']" value=" - "></td>';
						newRow.append(cols);
						$("table.order-list8").append(newRow);
					}

					if ("Others" != ($('#2').val())) {
						$(".yellowClass1").show();
						$(".blueClass1").hide();
					} else {
						$(".yellowClass1").hide();
						$(".blueClass1").show();

					}

				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}

	});
}

$('.blueClass1').hide();


function selectSnAmend(e) {

	if ($("#" + e.id + "").val() == "Others") {
		$(".blueClass1").show();
		$(".yellowClass1").hide();
		setFlagReq('O');
	} else {
		$(".yellowClass1").show();
		$(".blueClass1").hide();
		setFlagReqNinEght('N');
	}

	clearDropDown($("#itemBcdNoNtChange2s"));
	clearDropDown($("#itemIgstNoNtChange2s"));
	clearDropDown($("#itemSwNoNtChange2s"));
	var developerData = {};
	var developerData2 = {};
	var developerData3 = {};
	var resObj = $.ajax({
		url: 'getBcdNotification?cth=' + $("#2").val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemBcdNoNtChange2s").append($('<option>', {
				text: "Select NOTN"
			}));
			$.each(data, function(i, item) {
				$("#itemBcdNoNtChange2s").append($('<option>', {
					text: item
				}));
			});

			setFlagReqNinEght('N');
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});

	var resObj2 = $.ajax({
		url: 'getIgstNotification?cth=' + $("#2").val(),
		data: JSON.stringify(developerData2),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {

			$("#itemIgstNoNtChange2s").append($('<option>', {
				text: "Select NOTN"
			}));


			$.each(data, function(i, item) {
				$("#itemIgstNoNtChange2s").append($('<option>', {
					text: item
				}));
			});
			setFlagReqNinEght('N');
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});

	var resObj3 = $.ajax({
		url: 'getSwNotification?cth=' + $("#2").val(),
		data: JSON.stringify(developerData3),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {

			$("#itemSwNoNtChange2s").append($('<option>', {
				text: "Select NOTN"
			}));


			$.each(data, function(i, item) {
				$("#itemSwNoNtChange2s").append($('<option>', {
					text: item
				}));
			});
			setFlagReqNinEght('N');
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function selectBcdNoNtAmend(e) {
	clearDropDown($("#itemBcdNsNoChange2s"));
	var developerData = {};
	var resObj = $.ajax({
		url: 'getBcdSerialNo?notificationNo=' + $("#itemBcdNoNtChange2s").val() + '&cth=' + $("#2").val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemBcdNsNoChange2s").append($('<option>', {
				text: " "
			}));
			$.each(data, function(i, item) {
				$("#itemBcdNsNoChange2s").append($('<option>', {
					text: item
				}));
			});
			setFlagReqNinEght('N');
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function selectBcdNsNoAmend(e) {

	totalDutyDiff = $('#allItemDuty').val();
	totalDutyDiffFg = $('#allItemDutyFg').val();

	var developerData = {};

	developerData['cinNo'] = $('#inputPassword').val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['item_NO'] = $('#itemNoChange').val();
	developerData['item_DESC'] = $('#itemDESChanges').val();
	developerData['cth'] = $('#itemCthChanges').val();
	developerData['cth_REVISED'] = $('#itemNouChanges').val();
	developerData['netwt'] = $('#itemNetWtChanges').val();
	developerData['decl_VAL'] = $('#itemDeclValChanges').val();
	developerData['currcd'] = $('#itemCurCdChanges').val();
	developerData['origcntrycd'] = $('#itemOrgCntryCdChanges').val();
	developerData['rate'] = $('#itemRateChanges').val();
	developerData['gen_CTH'] = $('#2').val();
 developerData['assval_insfrt'] = $('#assessVal').val();

	if (!($("#itemBcdNoNtChange2s").val() == "Select NOTN"))
		developerData['bcd_NOTN'] = $("#itemBcdNoNtChange2s").val();
	if (!($("#itemBcdNsNoChange2s").val() == "Select SL.NO"))
		developerData['bcd_NSNO'] = $("#itemBcdNsNoChange2s").val();
	developerData['bcd_RTA'] = $("#itemBcdRtaChange2s").val();
	developerData['bcd_AMT'] = $("#itemBcdAmtChange2s").val();
	developerData['bcd_AMT_FG'] = $("#itemBcdAmtFgChange2s").val();
	if (!($("#itemIgstNoNtChange2s").val() == "Select NOTN"))
		developerData['igst_NOTN'] = $("#itemIgstNoNtChange2s").val();
	if (!($("#itemIgstNsNoChange2s").val() == "Select SL.NO"))
		developerData['igst_NSNO'] = $("#itemIgstNsNoChange2s").val();
	developerData['igst_RTA'] = $("#itemIgstRtaChange2s").val();
	developerData['igst_AMT'] = $("#itemIgstAmtChange2s").val();
	developerData['igst_AMT_FG'] = $("#itemIgstAmtFgChange2s").val();
	if (!($("#itemSwNoNtChange2s").val() == "Select NOTN"))
		developerData['sw_NOTN'] = $("#itemSwNoNtChange2s").val();
	if (!($("#itemSwNsNoChange2s").val() == "Select SL.NO"))
		developerData['sw_NSNO'] = $("#itemSwNsNoChange2s").val();
	developerData['sw_RTA'] = $("#itemSwRtaChange2s").val();
	developerData['sw_AMT'] = $("#itemSwAmtChange2s").val();
	developerData['sw_AMT_FG'] = $("#itemSwAmtFgChange2s").val();
	developerData['duty'] = $("#itemDutyChange2s").val();
	developerData['duty_FG'] = $("#itemDutyFgChange2s").val();
	developerData['allItemDuty'] = $("#allItemDuty").val();
	developerData['allItemDutyFg'] = $("#allItemDutyFg").val();
	developerData['dutyPayable'] = $("#totaldutyPayable").val();
	developerData['query'] = $("#queryChange").val();
	developerData['rate'] = $("#itemRateChange1").val();

	var previousDuty = $("#itemDutyChange2s").val();
	var previousBcdFg = $("#itemBcdAmtChange2s").val();
	var resObj = $.ajax({
		url: 'getBcdRate?getBcdSlNo=' + $("#itemBcdNsNoChange2s").val() + '&cth=' + $("#2").val() + '&cinNo=' + $('#inputPassword').val() + '&itemNo=' + $('#itemNoChanges').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {

			$("#itemBcdRtaChange2s").val(data[0]);
			$("#itemBcdAmtChange2s").val(data[1]);
			$("#itemBcdAmtFgChange2s").val(data[2]);
			$("#itemDutyChange3s").val(data[3]);
			$("#itemDutyFgChange3s").val(data[4]);
			$("#allItemDuty").val(data[5]);
			$("#allItemDutyFg").val(data[6]);

			if ($("#allItemDuty").val() > $('#dutylim').val()) {
				$("#totaldutyPayable").val($("#allItemDuty").val());
			} else if ($("#cat").val()==$("#category").val())
			     $("#totaldutyPayable").val($("#allItemDuty").val());
			  else
			   {$("#totaldutyPayable").val(0);}
			calTotDutyNineEgtAmd('U');
			setFlagReqNinEght('N');
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function selectIgstNoNtAmend(e) {
	clearDropDown($("#itemIgstNsNoChange2s"));
	var developerData = {};
	var resObj = $.ajax({
		url: 'getIgstSerialNo?notificationNo=' + $("#itemIgstNoNtChange2s").val() + '&cth=' + $("#2").val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemIgstNsNoChange2s").append($('<option>', {
				text: " "
			}));
			$.each(data, function(i, item) {
				$("#itemIgstNsNoChange2s").append($('<option>', {
					text: item
				}));

			});
			setFlagReqNinEght('N');
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function selectIgstNsNoAmend(e) {
	var developerData = {};

	developerData['cinNo'] = $('#inputPassword').val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['item_NO'] = $('#itemNoChange').val();
	developerData['item_DESC'] = $('#itemDESChanges').val();
	developerData['cth'] = $('#itemCthChanges').val();
	developerData['cth_REVISED'] = $('#itemNouChanges').val();
	developerData['netwt'] = $('#itemNetWtChanges').val();
	developerData['decl_VAL'] = $('#itemDeclValChanges').val();
	developerData['currcd'] = $('#itemCurCdChanges').val();
	developerData['origcntrycd'] = $('#itemOrgCntryCdChanges').val();
	developerData['rate'] = $('#itemRateChanges').val();
	developerData['gen_CTH'] = $('#2').val();
	developerData['assess_VAL'] = $('#assessVal').val();

	if (!($("#itemBcdNoNtChange2s").val() == "Select NOTN"))
		developerData['bcd_NOTN'] = $("#itemBcdNoNtChange2s").val();
	if (!($("#itemBcdNsNoChange2s").val() == "Select SL.NO"))
		developerData['bcd_NSNO'] = $("#itemBcdNsNoChange2s").val();
	developerData['bcd_RTA'] = $("#itemBcdRtaChange2s").val();
	developerData['bcd_AMT'] = $("#itemBcdAmtChange2s").val();
	developerData['bcd_AMT_FG'] = $("#itemBcdAmtFgChange2s").val();
	if (!($("#itemIgstNoNtChange2s").val() == "Select NOTN"))
		developerData['igst_NOTN'] = $("#itemIgstNoNtChange2s").val();
	if (!($("#itemIgstNsNoChange2s").val() == "Select SL.NO"))
		developerData['igst_NSNO'] = $("#itemIgstNsNoChange2s").val();
	developerData['igst_RTA'] = $("#itemIgstRtaChange2s").val();
	developerData['igst_AMT'] = $("#itemIgstAmtChange2s").val();
	developerData['igst_AMT_FG'] = $("#itemIgstAmtFgChange2s").val();
	if (!($("#itemSwNoNtChange2s").val() == "Select NOTN"))
		developerData['sw_NOTN'] = $("#itemSwNoNtChange2s").val();
	if (!($("#itemSwNsNoChange2s").val() == "Select SL.NO"))
		developerData['sw_NSNO'] = $("#itemSwNsNoChange2s").val();
	developerData['sw_RTA'] = $("#itemSwRtaChange2s").val();
	developerData['sw_AMT'] = $("#itemSwAmtChange2s").val();
	developerData['sw_AMT_FG'] = $("#itemSwAmtFgChange2s").val();
	developerData['duty'] = $("#itemDutyChange2s").val();
	developerData['duty_FG'] = $("#itemDutyFgChange2s").val();
	developerData['allItemDuty'] = $("#allItemDuty").val();
	developerData['allItemDutyFg'] = $("#allItemDutyFg").val();
	developerData['dutyPayable'] = $("#totaldutyPayable").val();
	developerData['query'] = $("#queryChange").val();
	developerData['rate'] = $("#itemRateChange1").val();

	var previousDuty = $("#itemDutyChange1").val();
	var previousIgstFg = $("#itemIgstAmtChange1").val();
	var resObj = $.ajax({
		url: 'getIgstRate?getSlNo=' + $("#itemIgstNsNoChange2s").val() + '&cth=' + $("#2").val() + '&cinNo=' + $('#inputPassword').val() + '&itemNo=' + $('#itemNoChanges').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {

			$("#itemIgstRtaChange2s").val(data[0]);
			$("#itemIgstAmtChange2s").val(data[1]);
			$("#itemIgstAmtFgChange2s").val(data[2]);
			$("#itemDutyChange3s").val(data[3]);
			$("#itemDutyFgChange3s").val(data[4]);
			$("#allItemDuty").val(data[5]);
			$("#allItemDutyFg").val(data[6]);

			if ($("#allItemDuty").val() > $('#dutylim').val()) {
				$("#totaldutyPayable").val($("#allItemDuty").val());
			} else if ($("#cat").val()==$("#category").val())
			     $("#totaldutyPayable").val($("#allItemDuty").val());
			  else
			   {$("#totaldutyPayable").val(0);}
			calTotDutyNineEgtAmd('U');
			setFlagReqNinEght('N');
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function selectSwNoNtAmend(e) {
	clearDropDown($("#itemSwNsNoChange1"));
	var developerData = {};
	var resObj = $.ajax({
		url: 'getSwSerialNo?notificationNo=' + $("#itemSwNoNtChange2s").val() + '&cth=' + $("#2").val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {

			$("#itemSwNsNoChange2s" + e.id + "").append($('<option>', {
				text: " "
			}));
			$.each(data, function(i, item) {
				$("#itemSwNsNoChange2s").append($('<option>', {
					text: item
				}));

			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function selectSwNsNoAmend(e) {
	var developerData = {};

	developerData['cinNo'] = $('#inputPassword').val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['item_NO'] = $('#itemNoChange').val();
	developerData['item_DESC'] = $('#itemDESChanges').val();
	developerData['cth'] = $('#itemCthChanges').val();
	developerData['cth_REVISED'] = $('#itemNouChanges').val();
	developerData['netwt'] = $('#itemNetWtChanges').val();
	developerData['decl_VAL'] = $('#itemDeclValChanges').val();
	developerData['currcd'] = $('#itemCurCdChanges').val();
	developerData['origcntrycd'] = $('#itemOrgCntryCdChanges').val();
	developerData['rate'] = $('#itemRateChanges').val();
	developerData['gen_CTH'] = $('#2').val();
	developerData['assess_VAL'] = $('#assessVal').val();

	if (!($("#itemBcdNoNtChange2s").val() == "Select NOTN"))
		developerData['bcd_NOTN'] = $("#itemBcdNoNtChange2s").val();
	if (!($("#itemBcdNsNoChange2s").val() == "Select SL.NO"))
		developerData['bcd_NSNO'] = $("#itemBcdNsNoChange2s").val();
	developerData['bcd_RTA'] = $("#itemBcdRtaChange2s").val();
	developerData['bcd_AMT'] = $("#itemBcdAmtChange2s").val();
	developerData['bcd_AMT_FG'] = $("#itemBcdAmtFgChange2s").val();
	if (!($("#itemIgstNoNtChange2s").val() == "Select NOTN"))
		developerData['igst_NOTN'] = $("#itemIgstNoNtChange2s").val();
	if (!($("#itemIgstNsNoChange2s").val() == "Select SL.NO"))
		developerData['igst_NSNO'] = $("#itemIgstNsNoChange2s").val();
	developerData['igst_RTA'] = $("#itemIgstRtaChange2s").val();
	developerData['igst_AMT'] = $("#itemIgstAmtChange2s").val();
	developerData['igst_AMT_FG'] = $("#itemIgstAmtFgChange2s").val();
	if (!($("#itemSwNoNtChange2s").val() == "Select NOTN"))
		developerData['sw_NOTN'] = $("#itemSwNoNtChange2s").val();
	if (!($("#itemSwNsNoChange2s").val() == "Select SL.NO"))
		developerData['sw_NSNO'] = $("#itemSwNsNoChange2s").val();
	developerData['sw_RTA'] = $("#itemSwRtaChange2s").val();
	developerData['sw_AMT'] = $("#itemSwAmtChange2s").val();
	developerData['sw_AMT_FG'] = $("#itemSwAmtFgChange2s").val();
	developerData['duty'] = $("#itemDutyChange2s").val();
	developerData['duty_FG'] = $("#itemDutyFgChange2s").val();
	developerData['allItemDuty'] = $("#allItemDuty").val();
	developerData['allItemDutyFg'] = $("#allItemDutyFg").val();
	developerData['dutyPayable'] = $("#totaldutyPayable").val();
	developerData['query'] = $("#queryChange").val();
	developerData['rate'] = $("#itemRateChange1").val();

	var previousDuty = $("#itemDutyChange1").val();
	var previousSwFg = $("#itemSwAmtChange1").val();
	var resObj = $.ajax({
		url: 'getSwRate?getSlNo=' + $("#itemSwNsNoChange2s").val() + '&cth=' + $("#2").val() + '&cinNo=' + $('#inputPassword').val() + '&itemNo=' + $('#itemNoChanges').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {

			$("#itemSwRtaChange1").val(data[0]);
			$("#itemSwAmtChange2s").val(data[1]);
			$("#itemSwAmtFgChange2s").val(data[2]);
			$("#itemDutyChange3s").val(data[3]);
			$("#itemDutyFgChange3s").val(data[4]);
			$("#allItemDuty").val(data[5]);
			$("#allItemDutyFg").val(data[6]);

			if ($("#allItemDuty").val() > $('#dutylim').val()) {
				$("#totaldutyPayable").val($("#allItemDuty").val());
			} else if ($("#cat").val()==$("#category").val())
			     $("#totaldutyPayable").val($("#allItemDuty").val());
			  else
			   {$("#totaldutyPayable").val(0);}
			calTotDutyNineEgtAmd('U');
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
			setFlagReq('N');
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
			setFlagReq('N');
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
			setFlagReq('N');
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
		url: 'getIgstSerialNo?notificationNo=' + $(this).val() + '&cth=' + $('#itemCthChange').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$.each(data, function(i, item) {
				$('#itemIgstNsNoChange').append($('<option>', {
					text: item
				}));
				setFlagReq('N');

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
		url: 'getSwSerialNo?notificationNo=' + $(this).val() + '&cth=' + $('#itemCthChange').val(),
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
		url: 'getSwRate?getSlNo=' + $(this).val() + '&cth=' + $('#itemCthChange').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#itemSwRtaChange").val(data);
			var currentSwFgA = $("#itemSwAmtChange").val(parseFloat((parseFloat($("#itemBcdAmtChange").va())) * (parseFloat($('#itemSwRtaChange').val()) * 0.01)).toFixed(2));
			$("#itemSwAmtFgChange").val(parseFloat(parseFloat(previousSwFg) - parseFloat(currentSwFgA.val())).toFixed(2));
			var currentDuty = $("#itemDutyChange").val(parseFloat(parseFloat($("#itemBcdAmtChange").val()) + parseFloat($("#itemIgstAmtChange").val()) + parseFloat($("#itemSwAmtChange").val())).toFixed(2));
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

var othrCth = "";
var ninCth = 0;

function updateAmendment(id) {
	var developerData = {};
	developerData['cinNo'] = $('#inputPassword').val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['item_NO'] = $('#itemNoChanges').val();
	developerData['item_DESC'] = $('#itemDESChanges').val();
	developerData['cth'] = $('#itemCthChanges').val();
	developerData['cth_REVISED'] = $('#itemRevisedCthChanges').val();
	developerData['netwt'] = $('#itemNetWtChanges').val();
	developerData['decl_VAL'] = $('#itemDeclValChange2s').val();
	developerData['currcd'] = $('#itemCurCdChanges').val();
	developerData['origcntrycd'] = $('#itemOrgCntryCdChanges').val();
	developerData['rate'] = $('#itemRateChange2s').val();
	developerData['gen_CTH'] = $('#2').val();
	developerData['assess_VAL'] = $('#assessVal').val();
	developerData['nou'] = $('#itemNouChanges').val();
	developerData['flagRequest'] = glFlagNinEght;
	developerData['flagRequestQry'] = glFlagQry;

	if (!($("#itemBcdNoNtChange2s").val() == "Select NOTN"))
		developerData['bcd_NOTN'] = $("#itemBcdNoNtChange2s").val();
	if (!($("#itemBcdNsNoChange2s").val() == "Select SL.NO"))
		developerData['bcd_NSNO'] = $("#itemBcdNsNoChange2s").val();
	developerData['bcd_RTA'] = $("#itemBcdRtaChange2s").val();
	developerData['bcd_AMT'] = $("#itemBcdAmtChange2s").val();
	developerData['bcd_AMT_FG'] = $("#itemBcdAmtFgChange2s").val();
	if (!($("#itemIgstNoNtChange2s").val() == "Select NOTN"))
		developerData['igst_NOTN'] = $("#itemIgstNoNtChange2s").val();
	if (!($("#itemIgstNsNoChange2s").val() == "Select SL.NO"))
		developerData['igst_NSNO'] = $("#itemIgstNsNoChange2s").val();
	developerData['igst_RTA'] = $("#itemIgstRtaChange2s").val();
	developerData['igst_AMT'] = $("#itemIgstAmtChange2s").val();
	developerData['igst_AMT_FG'] = $("#itemIgstAmtFgChange2s").val();
	if (!($("#itemSwNoNtChange2s").val() == "Select NOTN"))
		developerData['sw_NOTN'] = $("#itemSwNoNtChange2s").val();
	if (!($("#itemSwNsNoChange2s").val() == "Select SL.NO"))
		developerData['sw_NSNO'] = $("#itemSwNsNoChange2s").val();
	developerData['sw_RTA'] = $("#itemSwRtaChange2s").val();
	developerData['sw_AMT'] = $("#itemSwAmtChange2s").val();
	developerData['sw_AMT_FG'] = $("#itemSwAmtFgChange2s").val();
	developerData['duty'] = $("#itemDutyChange3s").val();
	developerData['duty_FG'] = $("#itemDutyFgChange3s").val();
	developerData['allItemDuty'] = $("#allItemDuty").val();
	developerData['allItemDutyFg'] = $("#allItemDutyFg").val();
	developerData['dutyPayable'] = $("#totaldutyPayable").val();
	developerData['query'] = $("#queryChange").val();
	developerData['rate'] = $("#itemRateChange1").val();

	$.ajax({
		url: 'eadItemDeclaredUpdate',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {

			clearDropDown($("#itemBcdNsNoChange1"));
			clearDropDown($("#itemIgstNsNoChange1"));
			clearDropDown($("#itemSwNsNoChange1"));

			$("#itemSwAmtChange1").val(developerData['sw_AMT']);
			$("#itemIgstAmtChange1").val(developerData['igst_AMT']);


			$("#itemBcdAmtChange1").val(developerData['bcd_AMT']);

			$("#itemSwAmtFgChange1").val(developerData['sw_AMT_FG']);
			$("#itemIgstAmtFgChange1").val(developerData['igst_AMT_FG']);
			$("#itemBcdAmtFgChange1").val(developerData['bcd_AMT_FG']);
			$("#itemDutyFgChange1").val(developerData['duty_FG']);

			$("#itemNoChange").val(developerData['item_NO']);
			$("#itemDESChange").val(developerData['item_DESC']);
			$("#itemCthChange" + developerData['itemNumber'] + "").val(developerDatas['gen_CTH']);
			$("#itemRevisedCthChange").val(developerData['cth_REVISED']);
			$("#itemNouChange").val(developerData['nou']);
			$("#itemNetWtChange").val(developerData['netwt']);
			$("#itemDeclValChange1").val(developerData['decl_VAL']);
			$("#itemCurCdChange").val(developerData['currcd']);
			$("#itemOrgCntryCdChange").val(developerData['origcntrycd']);
			$("#itemRateChange1").val(developerData['rate']);
			$("#itemPagination1").text(developerData['item_NO'] + "/" + $("#noOfItems").text());
			$("#nextItemPegination").text(developerData['item_NO']);
			$("#itemDutyChange1").val(developerData['duty']);
			$("#assessVal").val(developerData['assess_VAL']);
			$("#itemSwRtaChange1").val(developerData['sw_RTA']);
			$("#itemIgstRtaChange1").val(developerData['igst_RTA']);
			$("#itemBcdRtaChange1").val(developerData['bcd_RTA']);
			$("#query").val(developerData['query']);
			$("#allItemDuty").val(developerData['allItemDuty']);
			$("#allItemDutyFg").val(developerData['allItemDutyFg']);
			$("#itemOrgCntryCdChange").val(developerData['cus_SITE']);


			$("#1 option:contains(" + developerData['gen_CTH'] + ")").attr('selected', 'selected');
			$("#itemBcdNoNtChange1").append($('<option>', {
				text: developerData['bcd_NOTN']
			}));
			$("#itemBcdNoNtChange1 option:contains(" + developerData['bcd_NOTN'] + ")").attr('selected', 'selected');
			$("#itemBcdNsNoChange1").append($('<option>', {
				text: developerData['bcd_NSNO']
			}));
			$("#itemBcdNsNoChange1 option:contains(" + developerData['bcd_NSNO'] + ")").attr('selected', 'selected');
			$("#itemIgstNoNtChange1").append($('<option>', {
				text: developerData['igst_NOTN']
			}));
			$("#itemIgstNoNtChange1 option:contains(" + developerData['igst_NOTN'] + ")").attr('selected', 'selected');
			$("#itemIgstNsNoChange1").append($('<option>', {
				text: developerData['igst_NSNO']
			}));
			$("#itemIgstNsNoChange1 option:contains(" + developerData['igst_NSNO'] + ")").attr('selected', 'selected');
			$("#itemSwNoNtChange1").append($('<option>', {
				text: developerData['sw_NONT']
			}));
			$("#itemSwNoNtChange1 option:contains(" + developerData['sw_NONT'] + ")").attr('selected', 'selected');
			$("#itemSwNsNoChange1").append($('<option>', {
				text: developerData['sw_NSNO']
			}));
			$("#itemSwNsNoChange1 option:contains(" + developerData['sw_NSNO'] + ")").attr('selected', 'selected');

			if ($("#allItemDuty").val() > $('#dutylim').val()) {
				$("#totaldutyPayable").val($("#allItemDuty").val());
			} else if ($("#cat").val()==$("#category").val())
			     $("#totaldutyPayable").val($("#allItemDuty").val());
			  else
			   {$("#totaldutyPayable").val(0);}
            if (!(parseFloat($('#acfirst').val())==1 && $('#role').val()=='PAC')){
			$("#itemBcdNoNtChange1").attr("disabled", true);
			$("#itemBcdNsNoChange1").attr("disabled", true);
			$("#itemIgstNoNtChange1").attr("disabled", true);
			$("#itemIgstNsNoChange1").attr("disabled", true);
			$("#itemSwNoNtChange1").attr("disabled", true);
			$("#itemSwNsNoChange1").attr("disabled", true);
			$("#" + developerData['itemNumber'] + "").attr("disabled", true);
			$("#query").attr("disabled", true);
			$('.updateAss').attr("disabled", true);
			$('.changeAss').attr("disabled", false);
			if (glFlagNinEght == "N")
				amendNinEght();
			if ($('#2').val() == "Others" && glFlag == "O") {
				amendOthers();
			} else {
				alert("Amend Values Are Updated Successfully");
				$("#exampleModalLongAddItem .close").click();
				setFlagReq('');
				setFlagReqQry('');
				setFlagReqNinEght('');
				$(".blueClass").hide();
				$(".yellowClass").show();
				$('#qryShHd').hide();
				$('#1').val($('#2').val());
				ninCth = $('#2').val();
			}}
		},
		fail: function(rs, e) {
			alert("Error in Assessment");
		}
	});
}


function amendOthers() {

	var fpoItemDetOthDuty;
	var fpoOthersList = [];
	var dutyArray = [];
	var rateUSArray = [];
	var notnArray = [];
	var slnoUSArray = [];
	var effRate = [];
	var dutyAmtArray = [];
	var dutyFg = [];

	$("#amOt").find("tr").each(function() {
		fpoItemDetOthDuty = {};

		if (null != $(this).find("td").eq(0).find("option:selected").val() && "" != $(this).find("td").eq(0).find("option:selected").val()) {
			dutyArray.push($(this).find("td").eq(0).find("select[name*='DUTY']").val());
			fpoItemDetOthDuty['duty_CD'] = $(this).find("td").eq(0).find("select[name*='DUTY']").val();
		}

		if (null != $(this).find("td").eq(1).find("input[name*='RATE_OF_DUTY']").val() && "" != $(this).find("td").eq(1).find("input[name*='RATE_OF_DUTY']").val()) {
			rateUSArray.push($(this).find("td").eq(1).find("input[name*='RATE_OF_DUTY']").val());
			fpoItemDetOthDuty['duty_RTA'] = $(this).find("td").eq(1).find("input[name*='RATE_OF_DUTY']").val();
		}

		if (null != $(this).find("td").eq(2).find("option:selected").val() && "" != $(this).find("td").eq(2).find("option:selected").val()) {
			notnArray.push($(this).find("td").eq(2).find("option:selected").val());
			fpoItemDetOthDuty['duty_NOTN'] = $(this).find("td").eq(2).find("option:selected").val();
		}

		if (null != $(this).find("td").eq(3).find("option:selected").val() && "" != $(this).find("td").eq(3).find("option:selected").val()) {
			slnoUSArray.push($(this).find("td").eq(3).find("option:selected").val());
			fpoItemDetOthDuty['duty_SLNO'] = $(this).find("td").eq(3).find("option:selected").val();
		}

		if (null != $(this).find("td").eq(4).find("input[name*='EFF.RATE']").val() && "" != $(this).find("td").eq(4).find("input[name*='EFF.RATE']").val()) {
			effRate.push($(this).find("td").eq(4).find("input[name*='EFF.RATE']").val());
			fpoItemDetOthDuty['rate'] = $(this).find("td").eq(4).find("input[name*='EFF.RATE']").val();
		}

		if (null != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()) {
			dutyAmtArray.push($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val());
			fpoItemDetOthDuty['duty_AMT'] = $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val();
		}

		if (null != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val()) {
			dutyFg.push($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
			fpoItemDetOthDuty['duty_FG'] = $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val();
		}

		if (null != fpoItemDetOthDuty['duty_CD'] && fpoItemDetOthDuty['duty_CD'] != "" && !(fpoItemDetOthDuty['duty_CD'].toString().startsWith('Select Duty'))) {
			fpoItemDetOthDuty['cin_NO'] = $('#inputPassword').val();
			fpoItemDetOthDuty['item_NO'] = $('#itemNoChange').val();
			fpoItemDetOthDuty['cin_DT'] = new Date($('#cinDt').val());
			fpoItemDetOthDuty['item_ID'] = $('#itemId').val();
			fpoItemDetOthDuty['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoItemDetOthDuty['off_ID'] = $('#offId').val();
			fpoItemDetOthDuty['cus_SITE'] = $('#cusSite').val();
			fpoItemDetOthDuty['role'] = $('#role').val();
			fpoItemDetOthDuty['cth'] = $('#getOtherDigitAmend').val();
			fpoItemDetOthDuty['ass_DT'] = new Date();
			fpoItemDetOthDuty['totAllAmount'] = $('#itemDutyChange2').val();
			fpoItemDetOthDuty['totAllAmountFg'] = $('#itemDutyFgChange2').val();
			fpoItemDetOthDuty['allItemAmount'] = $('#allItemDuty').val();
			fpoItemDetOthDuty['allItemAmountFg'] = $('#allItemDutyFg').val();
			fpoOthersList.push(fpoItemDetOthDuty);
		}
	});

	$("#amOtEnter").find("tr").each(function() {
		fpoItemDetOthDuty = {};

		if (null != $(this).find("td").eq(0).find("[name*='DUTY']").val() && "" != $(this).find("td").eq(0).find("[name*='DUTY']").val()) {
			fpoItemDetOthDuty['duty_DESC'] = $(this).find("td").eq(0).find("[name*='DUTY']").val();
		}

		if (null != $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val() && "" != $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val()) {
			fpoItemDetOthDuty['duty_RTA'] = $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val();
		}

		if (null != $(this).find("td").eq(2).find("[name*='NOTN']").val() && "" != $(this).find("td").eq(2).find("[name*='NOTN']").val()) {
			fpoItemDetOthDuty['duty_NOTN'] = $(this).find("td").eq(2).find("[name*='NOTN']").val();
		}

		if (null != $(this).find("td").eq(3).find("[name*='SL.NO']").val() && "" != $(this).find("td").eq(3).find("[name*='SL.NO']").val()) {
			fpoItemDetOthDuty['duty_SLNO'] = $(this).find("td").eq(3).find("[name*='SL.NO']").val();
		}

		if (null != $(this).find("td").eq(4).find("[name*='EFF.RATE']").val() && "" != $(this).find("td").eq(4).find("[name*='EFF.RATE']").val()) {
			fpoItemDetOthDuty['rate'] = $(this).find("td").eq(4).find("[name*='EFF.RATE']").val();
		}

		if (null != $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val()) {
			fpoItemDetOthDuty['duty_AMT'] = $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val();
		}

		if (null != $(this).find("td").eq(6).find("[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("[name*='DUTY_FG']").val()) {
			fpoItemDetOthDuty['duty_FG'] = $(this).find("td").eq(6).find("[name*='DUTY_FG']").val();
		}
		if (null != fpoItemDetOthDuty['duty_DESC'] && fpoItemDetOthDuty['duty_DESC'] != "") {
			fpoItemDetOthDuty['cin_NO'] = $('#inputPassword').val();
			fpoItemDetOthDuty['duty_CD']=null;
			fpoItemDetOthDuty['item_NO'] = $('#itemNoChange').val();
			fpoItemDetOthDuty['cin_DT'] = new Date($('#cinDt').val());
			fpoItemDetOthDuty['item_ID'] = $('#itemId').val();
			fpoItemDetOthDuty['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoItemDetOthDuty['off_ID'] = $('#offId').val();
			fpoItemDetOthDuty['cus_SITE'] = $('#cusSite').val();
			fpoItemDetOthDuty['role'] = $('#role').val();
			fpoItemDetOthDuty['cth'] = $('#getOtherDigitAmend').val();
			fpoItemDetOthDuty['ass_DT'] = new Date();
			fpoItemDetOthDuty['totAllAmount'] = $('#itemDutyChange2').val();
			fpoItemDetOthDuty['totAllAmountFg'] = $('#itemDutyFgChange2').val();
			fpoItemDetOthDuty['allItemAmount'] = $('#allItemDuty').val();
			fpoItemDetOthDuty['allItemAmountFg'] = $('#allItemDutyFg').val();
			fpoItemDetOthDuty['deltFlag'] = 'O';
			fpoOthersList.push(fpoItemDetOthDuty);
		}
	});

	if (fpoOthersList.length > 0) {
		$.ajax({
			url: 'saveOthersAmendList',
			data: JSON.stringify(fpoOthersList),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(fpoItemDetOthDutyList) {

				alert("Amend Values Are Updated Successfully");
				$("#exampleModalLongAddItem .close").click();
				setFlagReq('');
				setFlagReqQry('');
				setFlagReqNinEght('');
				var custCount = 1;
				var selCount = 1;
				var enterCount = 0;

				if (null != fpoItemDetOthDutyList && fpoItemDetOthDutyList != "") {

					$("#1 option:contains(" + "Others" + ")").attr('selected', 'selected');
					$('#1').attr("disabled", true);

					$('#getOtherDigit').append($('<option>', {
						text: fpoItemDetOthDutyList[0].cth
					}));
					$("#getOtherDigit option:contains(" + fpoItemDetOthDutyList[0].cth + ")").attr('selected', 'selected');
					$('#getOtherDigit').attr("disabled", true);

					$('#getOtherEightDigi').append($('<option>', {
						text: fpoItemDetOthDutyList[0].cth.substr(0, 4)
					}));
					$("#getOtherEightDigi option:contains(" + fpoItemDetOthDutyList[0].cth.substr(0, 4) + ")").attr('selected', 'selected');
					$('#getOtherEightDigi').attr("disabled", true);

					$('#getOtherFourDigi').append($('<option>', {
						text: fpoItemDetOthDutyList[0].cth.substr(0, 2)
					}));
					$("#getOtherFourDigi option:contains(" + fpoItemDetOthDutyList[0].cth.substr(0, 2) + ")").attr('selected', 'selected');
					$('#getOtherFourDigi').attr("disabled", true);
					$("#enterOthDuty tr").remove();
					$("#tbOtrDt tr").remove();
					$.each(fpoItemDetOthDutyList, function(i, item) {
						if (null != item.duty_CD && item.duty_CD != "" && !(fpoItemDetOthDutyList[stRow].duty_CD.toString()=='Select')) {
							fpoItemDetOthDuty['duty_CD'] = item.duty_CD;

							var newRow = $("<tr>");
							var cols = "";
							cols += '<td><select type="text" id="customDropDown' + custCount + '" onchange="othersNotn(this)" class="custom-select" name="DUTY[' + custCount + ']" ><option>Select Duty</option></select></td>';
							cols += '<td><input type="text" id="rateDuty' + custCount + '" class="form-control" readonly="readonly" name="RATE_OF_DUTY[' + custCount + ']" /></td>';
							cols += '<td><select type="text" id="notn' + custCount + '" onchange="othersSlNo(this)" class="custom-select" name="NOTN[' + custCount + ']" ><option>Select NOTN</option></select></td>';
							cols += '<td><select type="text" id="slno' + custCount + '" onchange="selectEachNsNo(this)" class="custom-select" name="SL.NO[' + custCount + ']" ><option>Select SL. No</option></select></td>';
							cols += '<td><input type="text"  id="effRate' + custCount + '" class="form-control" readonly="readonly" name="EFF.RATE[' + custCount + ']" /></td>';
							cols += '<td><input type="text"  id="dutyAmount' + custCount + '" onblur="calTotDuty(2)" class="form-control" style="text-align: right;" name="DUTY_AMOUNT[' + custCount + ']" /></td>';
							cols += '<td><input type="text"  id="dutyAmountFg' + custCount + '" onblur="calTotDutyFg()" class="form-control" style="text-align: right;" name="DUTY_FG[' + custCount + ']" /></td>';
							newRow.append(cols);
							$("table.order-list1").append(newRow);

							$.ajax({
								url: 'getDUTYOnCd',
								data: JSON.stringify(fpoItemDetOthDuty),
								dataType: "json",
								contentType: "application/json",
								type: "post",
								success: function(fpoItemDetOthDutys) {
									$('#customDropDown' + selCount + '').append($('<option>', {
										text: fpoItemDetOthDutys['cdDesc']
									}));
									$("#customDropDown" + selCount + " option:contains(" + fpoItemDetOthDutys['cdDesc'] + ")").attr('selected', 'selected');
									$("#customDropDown" + selCount).attr("disabled", true);
									selCount = selCount + 1;
								},
								fail: function(rs, e) {
									console.log(rs, responseText);
								}
							});

							$('#notn' + custCount).append($('<option>', {
								text: item.duty_NOTN
							}));
							$("#notn" + custCount + " option:contains(" + item.duty_NOTN + ")").attr('selected', 'selected');
							$("#notn" + custCount).attr("disabled", true);

							$('#slno' + custCount).append($('<option>', {
								text: item.duty_SLNO
							}));
							$("#slno" + custCount + " option:contains(" + item.duty_SLNO + ")").attr('selected', 'selected');
							$("#slno" + custCount).attr("disabled", true);

							$("#rateDuty" + custCount).val(item.duty_RTA);
							$("#effRate" + custCount).val(item.rate);
							$("#dutyAmount" + custCount).val(item.duty_AMT);
							$("#dutyAmountFg" + custCount).val(item.duty_FG);

							$("#1").attr("disabled", true);
							$("#dutyAmount" + custCount).attr("disabled", true);
							$("#dutyAmountFg" + custCount).attr("disabled", true);

							$('#bothAssQry').attr("disabled", true);
							$('#addrow1').attr("disabled", true);
							$('.updateAss').attr("disabled", true);
							$('#changeButton').attr("disabled", false);

							custCount = custCount + 1;
						} else {

							var newRow = $("<tr>");
							var cols = "";
							cols += '<td><input type="text" id="enterDropDown' + (enterCount + 1) + '" class="form-control" name="DUTY[' + (enterCount + 1) + ']" /></td>';
							cols += '<td><input type="text" id="enterRateDuty' + (enterCount + 1) + '" class="form-control" name="RATE_OF_DUTY[' + (enterCount + 1) + ']" /></td>';
							cols += '<td><input type="text" id="enterNotn' + (enterCount + 1) + '" class="form-control" name="NOTN[' + (enterCount + 1) + ']" /></td>';
							cols += '<td><input type="text" id="enterSlno' + (enterCount + 1) + '" class="form-control" name="SL.NO[' + (enterCount + 1) + ']" /></td>';
							cols += '<td><input type="text" id="enterEffRate' + (enterCount + 1) + '" class="form-control" name="EFF.RATE[' + (enterCount + 1) + ']" /></td>';
							cols += '<td><input type="text" id="enterDutyAmount' + (enterCount + 1) + '" class="form-control" onblur="calTotDuty(2)"  name="DUTY_AMOUNT[' + (enterCount + 1) + ']" /></td>';
							cols += '<td><input type="text" id="dutyFgAmt' + (enterCount + 1) + '" class="form-control" name="DUTY_FG[' + (enterCount + 1) + ']" /></td>';
							//cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + (enterCount + 1) + ']" value=" - "></td>';
							newRow.append(cols);
							$("table.order-list6").append(newRow);

							$("#enterDropDown" + (enterCount + 1)).val(item.duty_DESC);
							$("#enterRateDuty" + (enterCount + 1)).val(item.duty_RTA);
							$("#enterNotn" + (enterCount + 1)).val(item.duty_NOTN);
							$("#enterSlno" + (enterCount + 1)).val(item.duty_SLNO);
							$("#enterEffRate" + (enterCount + 1)).val(item.rate);
							$("#enterDutyAmount" + (enterCount + 1)).val(item.duty_AMT);
							$("#dutyFgAmt" + (enterCount + 1)).val(item.duty_FG);

							$("#enterDropDown" + (enterCount + 1)).attr("disabled", true);
							$("#enterRateDuty" + (enterCount + 1)).attr("disabled", true);
							$("#enterNotn" + (enterCount + 1)).attr("disabled", true);
							$("#enterSlno" + (enterCount + 1)).attr("disabled", true);
							$("#enterEffRate" + (enterCount + 1)).attr("disabled", true);
							$("#enterDutyAmount" + (enterCount + 1)).attr("disabled", true);
							$("#dutyFgAmt" + (enterCount + 1)).attr("disabled", true);
							$("#addrow6").attr("disabled", true);
							enterCount = enterCount + 1;
						}

					});
					$(".blueClass").show();
					$(".yellowClass").hide();
					$('#qryShHd').hide();
					othrCth = "Others";
				}

			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
	}
}

function amendNinEght() {

	var fpoItemDetOthDuty;
	var fpoOthersList = [];

	$("#etr98Amnd").find("tr").each(function() {
		fpoItemDetOthDuty = {};

		if (null != $(this).find("td").eq(0).find("[name*='DUTY']").val() && "" != $(this).find("td").eq(0).find("[name*='DUTY']").val()) {
			fpoItemDetOthDuty['duty_DESC'] = $(this).find("td").eq(0).find("[name*='DUTY']").val();
		}

		if (null != $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val() && "" != $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val()) {
			fpoItemDetOthDuty['duty_RTA'] = $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val();
		}

		if (null != $(this).find("td").eq(2).find("[name*='NOTN']").val() && "" != $(this).find("td").eq(2).find("[name*='NOTN']").val()) {
			fpoItemDetOthDuty['duty_NOTN'] = $(this).find("td").eq(2).find("[name*='NOTN']").val();
		}

		if (null != $(this).find("td").eq(3).find("[name*='SL.NO']").val() && "" != $(this).find("td").eq(3).find("[name*='SL.NO']").val()) {
			fpoItemDetOthDuty['duty_SLNO'] = $(this).find("td").eq(3).find("[name*='SL.NO']").val();
		}

		if (null != $(this).find("td").eq(4).find("[name*='EFF.RATE']").val() && "" != $(this).find("td").eq(4).find("[name*='EFF.RATE']").val()) {
			fpoItemDetOthDuty['rate'] = $(this).find("td").eq(4).find("[name*='EFF.RATE']").val();
		}

		if (null != $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val()) {
			fpoItemDetOthDuty['duty_AMT'] = $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val();
		}

		if (null != $(this).find("td").eq(6).find("[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("[name*='DUTY_FG']").val()) {
			fpoItemDetOthDuty['duty_FG'] = $(this).find("td").eq(6).find("[name*='DUTY_FG']").val();
		}

		if (null != fpoItemDetOthDuty['duty_DESC'] && fpoItemDetOthDuty['duty_DESC'] != "") {
			fpoItemDetOthDuty['cin_NO'] = $('#inputPassword').val();
			fpoItemDetOthDuty['item_NO'] = $('#itemNoChanges').val();
			fpoItemDetOthDuty['duty_CD']=null;
			fpoItemDetOthDuty['cin_DT'] = new Date($('#cinDt').val());
			fpoItemDetOthDuty['item_ID'] = $('#itemId').val();
			fpoItemDetOthDuty['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoItemDetOthDuty['off_ID'] = $('#offId').val();
			fpoItemDetOthDuty['cus_SITE'] = $('#cusSite').val();
			fpoItemDetOthDuty['role'] = $('#role').val();
			fpoItemDetOthDuty['cth'] = $('#2').val();
			fpoItemDetOthDuty['ass_DT'] = new Date();
			fpoItemDetOthDuty['totAllAmount'] = $('#itemDutyChange3s').val();
			fpoItemDetOthDuty['totAllAmountFg'] = $('#itemDutyFgChange3s').val();
			fpoItemDetOthDuty['allItemAmount'] = $('#allItemDuty').val();
			fpoItemDetOthDuty['allItemAmountFg'] = $('#allItemDutyFg').val();
			fpoItemDetOthDuty['deltFlag'] = 'N';
			fpoOthersList.push(fpoItemDetOthDuty);
		}
	});

	if (fpoOthersList.length > 0) {
		$.ajax({
			url: 'saveOthersAmendList',
			data: JSON.stringify(fpoOthersList),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(fpoItemDetOthDutyList) {
				var insertCount = 1;

				if (null != fpoItemDetOthDutyList && fpoItemDetOthDutyList != "") {

					$("#etr98DutyAss tr").remove();
					$.each(fpoItemDetOthDutyList, function(i, item) {

						var newRow = $("<tr>");
						var cols = "";
						cols += '<td><input type="text" id="etr98DropDown' + insertCount + '" class="form-control" name="DUTY[' + insertCount + ']" /></td>';
						cols += '<td><input type="text" id="etr98RateDuty' + insertCount + '" class="form-control" name="RATE_OF_DUTY[' + insertCount + ']" /></td>';
						cols += '<td><input type="text" id="etr98Notn' + insertCount + '" class="form-control" name="NOTN[' + insertCount + ']" /></td>';
						cols += '<td><input type="text" id="etr98Slno' + insertCount + '" class="form-control" name="SL.NO[' + insertCount + ']" /></td>';
						cols += '<td><input type="text" id="etr98EffRate' + insertCount + '" class="form-control" name="EFF.RATE[' + insertCount + ']" /></td>';
						cols += '<td><input type="text" id="etr98DutyAmount' + insertCount + '" class="form-control" onblur="calTotDuty(2)"  name="DUTY_AMOUNT[' + insertCount + ']" /></td>';
						cols += '<td><input type="text" id="etr98dtFgAmt' + insertCount + '" class="form-control" name="DUTY_FG[' + insertCount + ']" /></td>';
						//cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
						newRow.append(cols);
						$("table.order-list7").append(newRow);

						$("#etr98DropDown" + (insertCount)).val(item.duty_DESC);
						$("#etr98RateDuty" + (insertCount)).val(item.duty_RTA);
						$("#etr98Notn" + (insertCount)).val(item.duty_NOTN);
						$("#etr98Slno" + (insertCount)).val(item.duty_SLNO);
						$("#etr98EffRate" + (insertCount)).val(item.rate);
						$("#etr98DutyAmount" + (insertCount)).val(item.duty_AMT);
						$("#etr98dtFgAmt" + (insertCount)).val(item.duty_FG);
						$('#1').val(item.cth);

						$("#etr98DropDown" + (insertCount)).attr("disabled", true);
						$("#etr98RateDuty" + (insertCount)).attr("disabled", true);
						$("#etr98Notn" + (insertCount)).attr("disabled", true);
						$("#etr98Slno" + (insertCount)).attr("disabled", true);
						$("#etr98EffRate" + (insertCount)).attr("disabled", true);
						$("#etr98DutyAmount" + (insertCount)).attr("disabled", true);
						$("#etr98dtFgAmt" + (insertCount)).attr("disabled", true);
						$("#addrow7").attr("disabled", true);
						insertCount = insertCount + 1;

					});
				}

				$('.updateAss').attr("disabled", true);
				$('#changeButton').attr("disabled", false);
				$('#1').attr("disabled", true);
				$('#addrow1').attr("disabled", true);
				$('#addrow7').attr("disabled", true);
				$('#bothAssQry').attr("disabled", true);
				$(".blueClass").hide();
				$(".yellowClass").show();
				$('#qryShHd').hide();
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
	}
}

//$.session.clear()
var val = $('#modify').val();
if ((val == "") || !("Y" == val)) {
	//$("#hideDeclared").hide();
	$("#idAmdComplDetls").hide();
	$("#idAmnDtls").hide();
	$(".col-sm-6").addClass("col-sm-10");
	$("#divAlign").addClass("col-sm-10");
	$("#divAlign").removeClass("col-md-6");
	$('#divAlign').attr('align', 'center');
	//$("#changeClass").addClass("col-sm-12");
} else {
	//$("#showeDeclared").hide();
	//$("#hideDeclared").show();
	$("#idAmdComplDetls").show();
	$("#idAmnDtls").show();
	$(".col-sm-12").addClass("col-sm-6");
	$("#exampleModalLong").modal("hide");
}

$.each($("[id*=itemBcdNoNtChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemBcdNoNtChange', 'itemBcdNoNtChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemBcdNsNoChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemBcdNsNoChange', 'itemBcdNsNoChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemIgstNoNtChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemIgstNoNtChange', 'itemIgstNoNtChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemIgstNsNoChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemIgstNsNoChange', 'itemIgstNsNoChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemSwNoNtChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemSwNoNtChange', 'itemSwNoNtChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemSwNsNoChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemSwNsNoChange', 'itemSwNsNoChange' + (i + 1));
	$(this).attr('id', fin);
});


$.each($("[id*=itemPagination]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemPagination', 'itemPagination' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemDutyChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemDutyChange', 'itemDutyChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemIgstAmtChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemIgstAmtChange', 'itemIgstAmtChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemSwAmtChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemSwAmtChange', 'itemSwAmtChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemDutyFgChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemDutyFgChange', 'itemDutyFgChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemBcdAmtChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemBcdAmtChange', 'itemBcdAmtChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemDeclValChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemDeclValChange', 'itemDeclValChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemBcdRtaChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemBcdRtaChange', 'itemBcdRtaChange' + (i + 1));
	$(this).attr('id', fin);
});


$.each($("[id*=itemRateChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemRateChange', 'itemRateChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemBcdAmtFgChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemBcdAmtFgChange', 'itemBcdAmtFgChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemIgstRtaChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemIgstRtaChange', 'itemIgstRtaChange' + (i + 1));
	$(this).attr('id', fin);
});


$.each($("[id*=itemIgstAmtFgChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemIgstAmtFgChange', 'itemIgstAmtFgChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=itemSwRtaChange]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('itemSwRtaChange', 'itemSwRtaChange' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=customDropDown]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('customDropDown', 'customDropDown' + (i + 1));
	$(this).attr('id', fin);
});


$.each($("[id*=enterDropDown]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('enterDropDown', 'enterDropDown' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=enterRateDuty]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('enterRateDuty', 'enterRateDuty' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=enterNotn]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('enterNotn', 'enterNotn' + (i + 1));
	$(this).attr('id', fin);
});


$.each($("[id*=enterSlno]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('enterSlno', 'enterSlno' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=enterEffRate]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('enterEffRate', 'enterEffRate' + (i + 1));
	$(this).attr('id', fin);
});


$.each($("[id*=enterDutyAmount]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('enterDutyAmount', 'enterDutyAmount' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=dutyFgAmt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('dutyFgAmt', 'dutyFgAmt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=notn]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('notn', 'notn' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=slno]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('slno', 'slno' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id=dutyAmount]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('dutyAmount', 'dutyAmount' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id=dutyAmountFg]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('dutyAmountFg', 'dutyAmountFg' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=effRate]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('effRate', 'effRate' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=etr98DropDown]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('etr98DropDown', 'etr98DropDown' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=etr98RateDuty]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('etr98RateDuty', 'etr98RateDuty' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=etr98Notn]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('etr98Notn', 'etr98Notn' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=etr98Slno]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('etr98Slno', 'etr98Slno' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=etr98EffRate]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('etr98EffRate', 'etr98EffRate' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=etr98DutyAmount]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('etr98DutyAmount', 'etr98DutyAmount' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=etr98dtFgAmt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('etr98dtFgAmt', 'etr98dtFgAmt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmDrpDn]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmDrpDn', 'cusAmDrpDn' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmDtRt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmDtRt', 'cusAmDtRt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmNt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmNt', 'cusAmNt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmSln]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmSln', 'cusAmSln' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmRt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmRt', 'cusAmRt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmAmt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmAmt', 'cusAmAmt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmAmtFg]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmAmtFg', 'cusAmAmtFg' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmDrpDn]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmDrpDn', 'cusAmDrpDn' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmDtRt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmDtRt', 'cusAmDtRt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmNt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmNt', 'cusAmNt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmSln]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmSln', 'cusAmSln' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmRt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmRt', 'cusAmRt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmAmt]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmAmt', 'cusAmAmt' + (i + 1));
	$(this).attr('id', fin);
});

$.each($("[id*=cusAmAmtFg]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('cusAmAmtFg', 'cusAmAmtFg' + (i + 1));
	$(this).attr('id', fin);
});

$.ajax({
	url: 'getTotalNoItemsPagination?cinNo=' + $('#inputPassword').val(),
	data: JSON.stringify(""),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(datas) {

		$.each(datas, function(i, item) {
			$("#itemPagination" + item + "").text("" + (i + 1) + "/" + datas.length);
		});
	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});

function clearDropDown(e) {
	e.empty();
}

$('.blueClass').hide();

function getOtherDigiAmend(e) {

	$(".blueClass").show();
	$(".yellowClass").hide();
	$("#cusAmDrpDn1").attr("disabled", false);
	$("#cusAmNt1").attr("disabled", false);
	$("#cusAmSln1").attr("disabled", false);
	$("#cusAmAmt1").attr("disabled", false);
	$("#cusAmAmtFg1").attr("disabled", false);
	$("#addrow").attr("disabled", false);

	$("#enterDropDownAm1").attr("disabled", false);
	$("#enterRateDutyAm1").attr("disabled", false);
	$("#enterNotnAm1").attr("disabled", false);
	$("#enterSlnoAm1").attr("disabled", false);
	$("#enterEffRateAm1").attr("disabled", false);
	$("#enterDutyAmountAm1").attr("disabled", false);
	$("#dutyFgAmtAm1").attr("disabled", false);
	$("#addrow9").attr("disabled", false);

	clearDropDown($("#cusAmNt1"));
	var cth = $('#getOtherDigitAmend').val();
	var duty;
	var count = 0;
	var i = 1;

	$("#amOt").find("tr").each(function() {
		count = count + 1;
		if (null != $(this).find("td").eq(0).find("option:selected").val() && "" != $(this).find("td").eq(0).find("option:selected").val()) {
			duty = $(this).find("td").eq(0).find("select[name*='DUTY']").val();
			if (null != duty && null != cth && duty != "Select Duty" && cth != "Select Value") {

				var fpoItemDet = {};
				fpoItemDet['cinNo'] = $('#inputPassword').val();
				fpoItemDet['cth'] = $('#getOtherDigitAmend').val();
				fpoItemDet['dutyCdOthers'] = duty;

				$.ajax({
					url: 'getDutyOthersDet',
					data: JSON.stringify(fpoItemDet),
					dataType: "json",
					contentType: "application/json",
					type: "post",
					success: function(fpoItemDets) {
						//while (i <= count) {
						$('#cusAmDtRt' + i).val(fpoItemDets['dutyRateOthers']);
						clearDropDown($("#cusAmNt" + i));
						$("#cusAmNt" + i).append($('<option>', {
							text: "Select NSNO"
						}));
						$.each(fpoItemDets['listObjects'], function(i, item) {
							$('#cusAmNt' + i).append($('<option>', {
								text: item
							}));
						});
						i = i + 1;
						//}
					},
					fail: function(rs, e) {
						console.log(rs, responseText);
					}
				});
			}
		}

	});

}

function getSpcOtherDigiAmend(e) {

	var inx = e.id.substring(e.id.length - 1);
	clearDropDown($("#cusAmNt"));
	var cth = $('#getOtherDigitAmend').val();
	var duty;
	var count = 0;
	var i = 1;
	$("#amOt").find("tr").eq(inx - 1).find("td").eq(0).find("option:selected").val();
	if (null != $("#amOt").find("tr").eq(inx - 1).find("td").eq(0).find("option:selected").val() && "" != $("#amOt").find("tr").eq(inx - 1).find("td").eq(0).find("option:selected").val()) {
		duty = $("#amOt").find("tr").eq(inx - 1).find("td").eq(0).find("option:selected").val();
		if (null != duty && null != cth && duty != "Select Duty" && cth != "Select Value") {

			var fpoItemDet = {};
			fpoItemDet['cinNo'] = $('#inputPassword').val();
			fpoItemDet['cth'] = $('#getOtherDigitAmend').val();
			fpoItemDet['dutyCdOthers'] = duty;

			$.ajax({
				url: 'getDutyOthersDet',
				data: JSON.stringify(fpoItemDet),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(fpoItemDets) {
					//while (i <= count) {
					$('#cusAmDtRt' + inx).val(fpoItemDets['dutyRateOthers']);
					clearDropDown($("#cusAmNt" + inx));
					$("#cusAmNt" + inx).append($('<option>', {
						text: "Select NSNO"
					}));
					$.each(fpoItemDets['listObjects'], function(i, item) {
						$('#cusAmNt' + inx).append($('<option>', {
							text: item
						}));
					});
					i = i + 1;
					//}
				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				}
			});
		}
	}

}

function dispothrs(e)
{
        $("#otherdutyPopup").modal('show');
        if (e==2)
		   $('#manualdutyO').show();
		$(".blueClass").show();
		$(".yellowClass").hide();
		$("#customDropDown1").attr("disabled", false); // changed for reupdate for AAA
		$("#customDropDown1").attr("disabled", false); // changed for reupdate for AAA
		$("#notn1").attr("disabled", false); // changed for reupdate for AAA
		$("#slno1").attr("disabled", false); // changed for reupdate for AAA
		$("#dutyAmount1").attr("disabled", false); // changed for reupdate for AAA
		$("#dutyAmountFg1").attr("disabled", false); // changed for reupdate for AAA
		$("#addrow1").attr("disabled", false); // changed for reupdate for AAA

		//enterduty
		$("#enterDropDown1").attr("disabled", true);
		$("#enterRateDuty1").attr("disabled", true);
		$("#enterNotn1").attr("disabled", true);
		$("#enterSlno1").attr("disabled", true);
		$("#enterEffRate1").attr("disabled", true);
		$("#enterDutyAmount1").attr("disabled", true);
		$("#dutyFgAmt1").attr("disabled", true);
		$("#addrow6").attr("disabled", true);
	//	clearDropDown($("#itemBcdNoNtChange1"));
	//    clearDropDown($("#itemIgstNoNtChange1"));
	//    clearDropDown($("#itemSwNoNtChange1"));
}

function dispmain()
{
        $(".yellowClass").show();
		$(".blueClass").hide();
		$('#manualdutyO').hide();
}

function selectSn(e) {
	if ($("#" + e.id + "").val() == "Others") {
		dispothrs(2);
	} else {
		dispmain();
	}
    chngdutytooth(1);
	
	var developerData = {};
	var developerData2 = {};
	var developerData3 = {};
	var resObj = $.ajax({
		url: 'getBcdNotification?cth=' + $("#" + e.id + "").val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
	//		$("#itemBcdNoNtChange1").append($('<option>', {
	//			text: "Select NOTN"
	//		}));
			$.each(data, function(i, item) {
				$("#itemBcdNoNtChange1").append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});

	var resObj2 = $.ajax({
		url: 'getIgstNotification?cth=' + $("#" + e.id + "").val(),
		data: JSON.stringify(developerData2),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {


//			$("#itemIgstNoNtChange1").append($('<option>', {
//				text: "Select NOTN"
//			}));


			$.each(data, function(i, item) {
				$("#itemIgstNoNtChange1").append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});

	var resObj3 = $.ajax({
		url: 'getSwNotification?cth=' + $("#" + e.id + "").val(),
		data: JSON.stringify(developerData3),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {

	//		$("#itemSwNoNtChange1").append($('<option>', {
	//			text: "Select NOTN"
//			}));


			$.each(data, function(i, item) {
				$("#itemSwNoNtChange1").append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function selectBcdNoNt(e) {
    clearDropDown($("#itemBcdNsNoChange1"));
    var developerData = {};
if ($('#itemBcdNoNtChange1').val()=='Select NOTN'){ /* swasthik 20/04/2023 */
       $('#basiceffrateModal').modal('show');
		$("#itemBcdNsNoChange1").append($('<option>', {text: " Select SNO  "}));
		}
    else{
    var resObj = $.ajax({
        url: 'getBcdSerialNo?notificationNo=' + $("#" + e.id + "").val() + '&cth=' + $("#" + e.id.substring(e.id.length - 1) + "").val(),
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
        success: function(data) {
            $("#itemBcdNsNoChange1").append($('<option>', {
                text: " Select SNO  "
            }));
            $.each(data, function(i, item) {
                $("#itemBcdNsNoChange1").append($('<option>', {
                    text: item
                }));
            });
        },
        fail: function(rs, e) {
            console.log(rs, responseText);
        }
    });}
}
/* swasthik 20/04/2023 */
function calcbasiceffrt(){
	 var developerData = {};
$('#itemBcdNsNoChange1').val("Select SNO");
   var totval = $('#totassval').val();

    developerData['cinNo'] = $('#inputPassword').val();
    developerData['itemId'] = $('#itemId').val();
    developerData['item_NO'] = $('#itemNoChange').val();
    if (!($("#itemBcdNoNtChange1").val() == "Select NOTN"))
        developerData['bcd_NOTN'] = $("#itemBcdNoNtChange1").val();
    if (!($("#itemBcdNsNoChange1").val() == "Select SNO"))
        developerData['bcd_NSNO'] = $("#itemBcdNsNoChange1").val();
    developerData['bcd_RTA'] = $("#itemBcdRtaChange1").val();
    developerData['bcd_AMT'] = $("#itemBcdAmtChange1").val();
    developerData['bcd_AMT_FG'] = $("#itemBcdAmtFgChange1").val();
    if (!($("#itemIgstNoNtChange1").val() == "Select NOTN"))
        developerData['igst_NOTN'] = $("#itemIgstNoNtChange1").val();
    if (!($("#itemIgstNsNoChange1").val() == "Select SNO"))
        developerData['igst_NSNO'] = $("#itemIgstNsNo1").val();
    developerData['igst_RTA'] = $("#itemIgstRtaChange1").val();
    developerData['igst_AMT'] = $("#itemIgstAmtChange1").val();
    developerData['igst_AMT_FG'] = $("#itemIgstAmtFgChange1").val();
    if (!($("#itemSwNoNtChange1").val() == "Select NOTN"))
        developerData['sw_NONT'] = $("#itemSwNoNtChange1").val();
    if (!($("#itemSwNsNoChange1").val() == "Select SNO"))
        developerData['sw_NSNO'] = $("#itemSwNsNoChange1").val();
    developerData['sw_RTA'] = $("#itemSwRtaChange1").val();
    developerData['sw_AMT'] = $("#itemSwAmtChange1").val();
    developerData['sw_AMT_FG'] = $("#itemSwAmtFgChange1").val();
    developerData['duty'] = $("#itemDutyChange1").val();
    developerData['duty_FG'] = $("#itemDutyFgChange1").val();
    developerData['allItemDuty'] = $("#allItemDuty").val();
    developerData['allItemDutyFg'] = $("#allItemDutyFg").val();
    developerData['dutyPayable'] = $("#totaldutyPayable").val();
    developerData['query'] = $("#query" + $('#itemNoChange').val()).val();
    developerData['assval_INSFRT'] = $("#assessVal").val();
    developerData['rate'] = $("#itemRateChange1").val();
    var previousDuty = $("#itemDutyChange1").val();
    var previousDutyFg = $("#itemDutyFgChange1").val()=='' ? 0 : $("#itemDutyFgChange1").val();
    var prvtotitmduty = $("#allItemDuty").val();
    var prvtotitmdutyfg = $("#allItemDutyFg").val()=='' ? 0 :  $("#allItemDutyFg").val();
    var previousBcdFg = $("#itemBcdAmtChange1").val();
    var prvbcd = $("#itemBcdAmtChange1").val();
    var prvigst = $("#itemIgstAmtChange1").val();
    var prvsw = $("#itemSwAmtChange1").val();
     var prvbcdfg = $("#itemBcdAmtFgChange1").val()=='' ? 0 : $("#itemBcdAmtFgChange1").val();
    var prvigstfg =$("#itemIgstAmtFgChange1").val()=='' ? 0 : $("#itemIgstAmtFgChange1").val();
    var prvswfg = $("#itemSwAmtFgChange").val()=='' ? 0 : $("#itemSwAmtFgChange").val();
    var resObj = $.ajax({
	      url: 'getbasiceffrt?cinNo=' + $('#inputPassword').val() + '&itemNo=' + $('#itemNoChange').val(),
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
        success: function(data) {
	 $('#basiceffrateModal').modal('hide');
	   $("#itemBcdRtaChange1").val(data[0]);
            $("#itemBcdAmtChange1").val(data[1]);
            $("#itemBcdAmtFgChange1").val(data[2]);
            $("#itemDutyChange1").val(data[3]);
            $("#itemDutyFgChange1").val(data[4]);
            
            
            $("#itemIgstRtaChange1").val(data[7]);
            $("#itemIgstAmtChange1").val(data[8]);
            $("#itemIgstAmtFgChange1").val(data[9]);
            
            $("#itemSwRtaChange1").val(data[10]);
            $("#itemSwAmtChange1").val(data[11]);
            $("#itemSwAmtFgChange").val(data[12]);
            
            
            var curbcd = $("#itemBcdAmtChange1").val();
            var curigst =  $("#itemIgstAmtChange1").val();
            var cursw = $("#itemSwAmtChange1").val();
             var curbcdfg = $("#itemBcdAmtFgChange1").val()=='' ? 0 : $("#itemBcdAmtFgChange1").val();
            var curigstfg = $("#itemIgstAmtFgChange1").val()=='' ? 0 : $("#itemIgstAmtFgChange1").val();
            var curswfg = $("#itemSwAmtFgChange").val()=='' ? 0 : $("#itemSwAmtFgChange").val();
            
         /*   var curtotitmduty = (parseFloat(prvtotitmduty) - parseFloat(prvbcd) + parseFloat(curbcd) - parseFloat(prvigst) + parseFloat(curigst) - parseFloat(prvsw) + parseFloat(cursw)).toFixed(2);
            var curtotitmdutyfg = (parseFloat(prvtotitmdutyfg) - parseFloat(prvbcdfg) + parseFloat(curbcdfg) - parseFloat(prvigstfg) + parseFloat(curigstfg) - parseFloat(prvswfg) + parseFloat(curswfg)).toFixed(2);
           
             
            $("#allItemDuty").val(curtotitmduty);
            $("#allItemDutyFg").val(curtotitmdutyfg); */
        
  
           var curitmduty = (parseFloat(curbcd)+parseFloat(curigst)+parseFloat(cursw)+parseFloat(previousDuty)-parseFloat(prvbcd)-parseFloat(prvigst)-parseFloat(prvsw)).toFixed(2);
            
     
           var curitmdutyfg = (parseFloat(curbcdfg)+parseFloat(curigstfg)+parseFloat(curswfg)+parseFloat(previousDutyFg)-parseFloat(prvbcdfg)-parseFloat(prvigstfg)-parseFloat(prvswfg)).toFixed(2);
            
            $("#itemDutyChange1").val(curitmduty);
            
            
            $("#itemDutyFgChange1").val(curitmdutyfg);
            
            
            var curtotitmduty =( parseFloat(prvtotitmduty) - parseFloat(previousDuty) + parseFloat(curitmduty)).toFixed(2);
           
            
            var curtotitmdutyfg = (parseFloat(prvtotitmdutyfg) -  parseFloat(previousDutyFg) + parseFloat(curitmdutyfg)).toFixed(2);
           
            $("#allItemDuty").val(curtotitmduty);
            $("#allItemDutyFg").val(curtotitmdutyfg);


            if (totval > $('#dutylim').val()) {
                $("#totaldutyPayable").val($("#allItemDuty").val());
            } else if ($("#cat").val() == $("#category").val())
                $("#totaldutyPayable").val($("#allItemDuty").val());
            else {
                $("#totaldutyPayable").val(0);
            }
            
            
            
            totalDutyDiff = $('#allItemDuty').val();
            totalDutyDiffFg = $('#allItemDutyFg').val();
          //  calTotDutyNineEgtAss('A');
            calTotPayable();
        },
        fail: function(rs, e) {
	 $('#basiceffrateModal').modal('hide');
            console.log(rs, responseText);
        }
    });
}
function selectBcdNsNo(e) {

    var developerData = {};
    var totval = $('#totassval').val();

    developerData['cinNo'] = $('#inputPassword').val();
    developerData['itemId'] = $('#itemId').val();
    developerData['item_NO'] = $('#itemNoChange').val();
    if (!($("#itemBcdNoNtChange1").val() == "Select NOTN"))
        developerData['bcd_NOTN'] = $("#itemBcdNoNtChange1").val();
    if (!($("#itemBcdNsNoChange1").val() == "Select SNO"))
        developerData['bcd_NSNO'] = $("#itemBcdNsNoChange1").val();
    developerData['bcd_RTA'] = $("#itemBcdRtaChange1").val();
    developerData['bcd_AMT'] = $("#itemBcdAmtChange1").val();
    developerData['bcd_AMT_FG'] = $("#itemBcdAmtFgChange1").val();
    if (!($("#itemIgstNoNtChange1").val() == "Select NOTN"))
        developerData['igst_NOTN'] = $("#itemIgstNoNtChange1").val();
    if (!($("#itemIgstNsNoChange1").val() == "Select SNO"))
        developerData['igst_NSNO'] = $("#itemIgstNsNoChange1").val();
    developerData['igst_RTA'] = $("#itemIgstRtaChange1").val();
    developerData['igst_AMT'] = $("#itemIgstAmtChange1").val();
    developerData['igst_AMT_FG'] = $("#itemIgstAmtFgChange1").val();
    if (!($("#itemSwNoNtChange1").val() == "Select NOTN"))
        developerData['sw_NONT'] = $("#itemSwNoNtChange1").val();
    if (!($("#itemSwNsNoChange1").val() == "Select SNO"))
        developerData['sw_NSNO'] = $("#itemSwNsNoChange1").val();
    developerData['sw_RTA'] = $("#itemSwRtaChange1").val();
    developerData['sw_AMT'] = $("#itemSwAmtChange1").val();
    developerData['sw_AMT_FG'] = $("#itemSwAmtFgChange1").val();
    developerData['duty'] = $("#itemDutyChange1").val();
    developerData['duty_FG'] = $("#itemDutyFgChange1").val();
    developerData['allItemDuty'] = $("#allItemDuty").val();
    developerData['allItemDutyFg'] = $("#allItemDutyFg").val();
    developerData['dutyPayable'] = $("#totaldutyPayable").val();
    developerData['query'] = $("#query" + $('#itemNoChange').val()).val();
    developerData['assval_INSFRT'] = $("#assessVal").val();
    developerData['rate'] = $("#itemRateChange1").val();
    var previousDuty = $("#itemDutyChange1").val();
    var previousDutyFg = $("#itemDutyFgChange1").val()=='' ? 0 : $("#itemDutyFgChange1").val();
    var prvtotitmduty = $("#allItemDuty").val();
    var prvtotitmdutyfg = $("#allItemDutyFg").val()=='' ? 0 :  $("#allItemDutyFg").val();
    var previousBcdFg = $("#itemBcdAmtChange1").val();
    var prvbcd = $("#itemBcdAmtChange1").val();
    var prvigst = $("#itemIgstAmtChange1").val();
    var prvsw = $("#itemSwAmtChange1").val();
     var prvbcdfg = $("#itemBcdAmtFgChange1").val()=='' ? 0 : $("#itemBcdAmtFgChange1").val();
    var prvigstfg =$("#itemIgstAmtFgChange1").val()=='' ? 0 : $("#itemIgstAmtFgChange1").val();
    var prvswfg = $("#itemSwAmtFgChange").val()=='' ? 0 : $("#itemSwAmtFgChange").val();
    var resObj = $.ajax({
        url: 'getBcdRate?getBcdSlNo=' + $("#" + e.id + "").val() + '&cth=' + $("#" + e.id.substring(e.id.length - 1) + "").val() + '&cinNo=' + $('#inputPassword').val() + '&itemNo=' + $('#itemNoChange').val(),
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
        success: function(data) {
            $("#itemBcdRtaChange1").val(data[0]);
            $("#itemBcdAmtChange1").val(data[1]);
            $("#itemBcdAmtFgChange1").val(data[2]);
            $("#itemDutyChange1").val(data[3]);
            $("#itemDutyFgChange1").val(data[4]);
            
            
            $("#itemIgstRtaChange1").val(data[7]);
            $("#itemIgstAmtChange1").val(data[8]);
            $("#itemIgstAmtFgChange1").val(data[9]);
            
            $("#itemSwRtaChange1").val(data[10]);
            $("#itemSwAmtChange1").val(data[11]);
            $("#itemSwAmtFgChange").val(data[12]);
            
            
            var curbcd = $("#itemBcdAmtChange1").val();
            var curigst =  $("#itemIgstAmtChange1").val();
            var cursw = $("#itemSwAmtChange1").val();
             var curbcdfg = $("#itemBcdAmtFgChange1").val()=='' ? 0 : $("#itemBcdAmtFgChange1").val();
            var curigstfg = $("#itemIgstAmtFgChange1").val()=='' ? 0 : $("#itemIgstAmtFgChange1").val();
            var curswfg = $("#itemSwAmtFgChange").val()=='' ? 0 : $("#itemSwAmtFgChange").val();
            
         /*   var curtotitmduty = (parseFloat(prvtotitmduty) - parseFloat(prvbcd) + parseFloat(curbcd) - parseFloat(prvigst) + parseFloat(curigst) - parseFloat(prvsw) + parseFloat(cursw)).toFixed(2);
            var curtotitmdutyfg = (parseFloat(prvtotitmdutyfg) - parseFloat(prvbcdfg) + parseFloat(curbcdfg) - parseFloat(prvigstfg) + parseFloat(curigstfg) - parseFloat(prvswfg) + parseFloat(curswfg)).toFixed(2);
           
           
            $("#allItemDuty").val(curtotitmduty);
            $("#allItemDutyFg").val(curtotitmdutyfg); */
            
           var curitmduty = (parseFloat(curbcd)+parseFloat(curigst)+parseFloat(cursw)+parseFloat(previousDuty)-parseFloat(prvbcd)-parseFloat(prvigst)-parseFloat(prvsw)).toFixed(2);
            
     
           var curitmdutyfg = (parseFloat(curbcdfg)+parseFloat(curigstfg)+parseFloat(curswfg)+parseFloat(previousDutyFg)-parseFloat(prvbcdfg)-parseFloat(prvigstfg)-parseFloat(prvswfg)).toFixed(2);
            
            
            $("#itemDutyChange1").val(curitmduty);
            
            
            $("#itemDutyFgChange1").val(curitmdutyfg);
            
            
            var curtotitmduty =( parseFloat(prvtotitmduty) - parseFloat(previousDuty) + parseFloat(curitmduty)).toFixed(2);
           
            
            var curtotitmdutyfg = (parseFloat(prvtotitmdutyfg) -  parseFloat(previousDutyFg) + parseFloat(curitmdutyfg)).toFixed(2);
           
            $("#allItemDuty").val(curtotitmduty);
            $("#allItemDutyFg").val(curtotitmdutyfg);

            if (totval > $('#dutylim').val()) {
                $("#totaldutyPayable").val($("#allItemDuty").val());
            } else if ($("#cat").val() == $("#category").val())
                $("#totaldutyPayable").val($("#allItemDuty").val());
            else {
                $("#totaldutyPayable").val(0);
            }
            
    
            
            
            
            totalDutyDiff = $('#allItemDuty').val();
            totalDutyDiffFg = $('#allItemDutyFg').val();
          //  calTotDutyNineEgtAss('A');
            calTotPayable();
        },
        fail: function(rs, e) {
            console.log(rs, responseText);
        }
    });
}

function selectIgstNoNt(e) {
  clearDropDown($("#itemIgstNsNoChange1"));
    var developerData = {};
    var resObj = $.ajax({
        url: 'getIgstSerialNo?notificationNo=' + $("#" + e.id + "").val() + '&cth=' + $("#" + e.id.substring(e.id.length - 1) + "").val(),
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
        success: function(data) {
            $("#itemIgstNsNoChange1").append($('<option>', {
                text: " Select SNO "
            }));
            $.each(data, function(i, item) {
                $("#itemIgstNsNoChange1").append($('<option>', {
                    text: item
                }));

            });
        },
        fail: function(rs, e) {
            console.log(rs, responseText);
        }
    });
}

function selectIgstNsNo(e) {
    var developerData = {};
    var totval = $('#totassval').val();
    
    developerData['cinNo'] = $('#inputPassword').val();
    developerData['itemId'] = $('#itemId').val();
    developerData['item_NO'] = $('#itemNoChange').val();
    if (!($("#itemBcdNoNtChange1").val() == "Select NOTN"))
        developerData['bcd_NOTN'] = $("#itemBcdNoNtChange1").val();
    if (!($("#itemBcdNsNoChange1").val() == "Select SNO"))
        developerData['bcd_NSNO'] = $("#itemBcdNsNoChange1").val();
    developerData['bcd_RTA'] = $("#itemBcdRtaChange1").val();
    developerData['bcd_AMT'] = $("#itemBcdAmtChange1").val();
    developerData['bcd_AMT_FG'] = $("#itemBcdAmtFgChange1").val();
    if (!($("#itemIgstNoNtChange1").val() == "Select NOTN"))
        developerData['igst_NOTN'] = $("#itemIgstNoNtChange1").val();
    if (!($("#itemIgstNsNoChange1").val() == "Select SNO"))
        developerData['igst_NSNO'] = $("#itemIgstNsNoChange1").val();
    developerData['igst_RTA'] = $("#itemIgstRtaChange1").val();
    developerData['igst_AMT'] = $("#itemIgstAmtChange1").val();
    developerData['igst_AMT_FG'] = $("#itemIgstAmtFgChange1").val();
    if (!($("#itemSwNoNtChange1").val() == "Select NOTN"))
        developerData['sw_NONT'] = $("#itemSwNoNtChange1").val();
    if (!($("#itemSwNsNoChange1").val() == "Select SNO"))
        developerData['sw_NSNO'] = $("#itemSwNsNoChange1").val();
    developerData['sw_RTA'] = $("#itemSwRtaChange1").val();
    developerData['sw_AMT'] = $("#itemSwAmtChange1").val();
    developerData['sw_AMT_FG'] = $("#itemSwAmtFgChange1").val();
    developerData['duty'] = $("#itemDutyChange1").val();
    developerData['duty_FG'] = $("#itemDutyFgChange1").val();
    developerData['allItemDuty'] = $("#allItemDuty").val();
    developerData['allItemDutyFg'] = $("#allItemDutyFg").val();
    developerData['dutyPayable'] = $("#totaldutyPayable").val();
    developerData['query'] = $("#query" + $('#itemNoChange').val()).val();
     developerData['assval_INSFRT'] = $("#assessVal").val();
    developerData['rate'] = $("#itemRateChange1").val();
    
    var previousDuty = $("#itemDutyChange1").val();
    var previousDutyFg = $("#itemDutyFgChange1").val()=='' ? 0 : $("#itemDutyFgChange1").val();
    var prvtotitmduty = $("#allItemDuty").val();
    var prvtotitmdutyfg = $("#allItemDutyFg").val()=='' ? 0 :  $("#allItemDutyFg").val();
    var previousBcdFg = $("#itemBcdAmtChange1").val();
    var prvbcd = $("#itemBcdAmtChange1").val();
    var prvigst = $("#itemIgstAmtChange1").val();
    var prvsw = $("#itemSwAmtChange1").val();
     var prvbcdfg = $("#itemBcdAmtFgChange1").val()=='' ? 0 : $("#itemBcdAmtFgChange1").val();
    var prvigstfg =$("#itemIgstAmtFgChange1").val()=='' ? 0 : $("#itemIgstAmtFgChange1").val();
    var prvswfg = $("#itemSwAmtFgChange").val()=='' ? 0 : $("#itemSwAmtFgChange").val();
    var resObj = $.ajax({
        url: 'getIgstRate?getSlNo=' + $("#" + e.id + "").val() + '&cth=' + $("#" + e.id.substring(e.id.length - 1) + "").val() + '&cinNo=' + $('#inputPassword').val() + '&itemNo=' + $('#itemNoChange').val(),
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
        success: function(data) {

            $("#itemIgstRtaChange1").val(data[0]);
            $("#itemIgstAmtChange1").val(data[1]);
            $("#itemIgstAmtFgChange1").val(data[2]);
            $("#itemDutyChange1").val(data[3]);
            $("#itemDutyFgChange1").val(data[4]);       
            
            var curbcd = $("#itemBcdAmtChange1").val();
            var curigst =  $("#itemIgstAmtChange1").val();
            var cursw = $("#itemSwAmtChange1").val();
             var curbcdfg = $("#itemBcdAmtFgChange1").val()=='' ? 0 : $("#itemBcdAmtFgChange1").val();
            var curigstfg = $("#itemIgstAmtFgChange1").val()=='' ? 0 : $("#itemIgstAmtFgChange1").val();
            var curswfg = $("#itemSwAmtFgChange").val()=='' ? 0 : $("#itemSwAmtFgChange").val();


           var curitmduty = (parseFloat(curbcd)+parseFloat(curigst)+parseFloat(cursw)+parseFloat(previousDuty)-parseFloat(prvbcd)-parseFloat(prvigst)-parseFloat(prvsw)).toFixed(2);
            
     
           var curitmdutyfg = (parseFloat(curbcdfg)+parseFloat(curigstfg)+parseFloat(curswfg)+parseFloat(previousDutyFg)-parseFloat(prvbcdfg)-parseFloat(prvigstfg)-parseFloat(prvswfg)).toFixed(2);
            
            
            $("#itemDutyChange1").val(curitmduty);
            
            
            $("#itemDutyFgChange1").val(curitmdutyfg);
            
            
            var curtotitmduty =( parseFloat(prvtotitmduty) - parseFloat(previousDuty) + parseFloat(curitmduty)).toFixed(2);
           
            
            var curtotitmdutyfg = (parseFloat(prvtotitmdutyfg) -  parseFloat(previousDutyFg) + parseFloat(curitmdutyfg)).toFixed(2);
           
            $("#allItemDuty").val(curtotitmduty);
            $("#allItemDutyFg").val(curtotitmdutyfg);

            if (totval > $('#dutylim').val()) {
                $("#totaldutyPayable").val($("#allItemDuty").val());
            } else if ($("#cat").val() == $("#category").val())
                $("#totaldutyPayable").val($("#allItemDuty").val());
            else {
                $("#totaldutyPayable").val(0);
            }
            
    
            
            
            
            totalDutyDiff = $('#allItemDuty').val();
            totalDutyDiffFg = $('#allItemDutyFg').val();
     //       calTotDutyNineEgtAss('A');
            calTotPayable();
        },
        fail: function(rs, e) {
            console.log(rs, responseText);
        }
    });
}

function selectSwNoNt(e) {
    clearDropDown($("#itemSwNsNoChange1"));
    var developerData = {};
    var resObj = $.ajax({
        url: 'getSwSerialNo?notificationNo=' + $("#" + e.id + "").val() + '&cth=' + $("#" + e.id.substring(e.id.length - 1) + "").val(),
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
        success: function(data) {

            $("#itemSwNsNoChange1").append($('<option>', {
                text: " Select SNO "
            }));
            $.each(data, function(i, item) {
                $("#itemSwNsNoChange1").append($('<option>', {
                    text: item
                }));

            });
        },
        fail: function(rs, e) {
            console.log(rs, responseText);
        }
    });
}

function selectSwNsNo(e) {
    var developerData = {};
    var totval = $('#totassval').val();
    
    developerData['cinNo'] = $('#inputPassword').val();
    developerData['itemId'] = $('#itemId').val();
    developerData['item_NO'] = $('#itemNoChange').val();
    if (!($("#itemBcdNoNtChange1").val() == "Select NOTN"))
        developerData['bcd_NOTN'] = $("#itemBcdNoNtChange1").val();
    if (!($("#itemBcdNsNoChange1").val() == "Select SNO"))
        developerData['bcd_NSNO'] = $("#itemBcdNsNoChange1").val();
    developerData['bcd_RTA'] = $("#itemBcdRtaChange1").val();
    developerData['bcd_AMT'] = $("#itemBcdAmtChange1").val();
    developerData['bcd_AMT_FG'] = $("#itemBcdAmtFgChange1").val();
    if (!($("#itemIgstNoNtChange1").val() == "Select NOTN"))
        developerData['igst_NOTN'] = $("#itemIgstNoNtChange1").val();
    if (!($("#itemIgstNsNoChange1").val() == "Select SNO"))
        developerData['igst_NSNO'] = $("#itemIgstNsNoChange1").val();
    developerData['igst_RTA'] = $("#itemIgstRtaChange1").val();
    developerData['igst_AMT'] = $("#itemIgstAmtChange1").val();
    developerData['igst_AMT_FG'] = $("#itemIgstAmtFgChange1").val();
    if (!($("#itemSwNoNtChange1").val() == "Select NOTN"))
        developerData['sw_NONT'] = $("#itemSwNoNtChange1").val();
    if (!($("#itemSwNsNoChange1").val() == "Select SNO"))
        developerData['sw_NSNO'] = $("#itemSwNsNoChange1").val();
    developerData['sw_RTA'] = $("#itemSwRtaChange1").val();
    developerData['sw_AMT'] = $("#itemSwAmtChange1").val();
    developerData['sw_AMT_FG'] = $("#itemSwAmtFgChange1").val();
    developerData['duty'] = $("#itemDutyChange1").val();
    developerData['duty_FG'] = $("#itemDutyFgChange1").val();
    developerData['allItemDuty'] = $("#allItemDuty").val();
    developerData['allItemDutyFg'] = $("#allItemDutyFg").val();
    developerData['dutyPayable'] = $("#totaldutyPayable").val();
    developerData['query'] = $("#query" + $('#itemNoChange').val()).val();
     developerData['assval_INSFRT'] = $("#assessVal").val();
    developerData['rate'] = $("#itemRateChange1").val();

    var previousDuty = $("#itemDutyChange1").val();
    var previousDutyfg = $("#itemDutyFgChange1").val();
    var prvtotitmduty = $("#allItemDuty").val();
    var prvtotitmdutyfg = $("#allItemDutyFg").val()=='' ? 0 :  $("#allItemDutyFg").val();
    var previousBcdFg = $("#itemBcdAmtChange1").val();
    var prvbcd = $("#itemBcdAmtChange1").val();
    var prvigst = $("#itemIgstAmtChange1").val();
    var prvsw = $("#itemSwAmtChange1").val();
    var prvbcdfg = $("#itemBcdAmtFgChange1").val()=='' ? 0 : $("#itemBcdAmtFgChange1").val();
    var prvigstfg =$("#itemIgstAmtFgChange1").val()=='' ? 0 : $("#itemIgstAmtFgChange1").val();
    var prvswfg = $("#itemSwAmtFgChange").val()=='' ? 0 : $("#itemSwAmtFgChange").val();
    var resObj = $.ajax({
        url: 'getSwRate?getSlNo=' + $("#" + e.id + "").val() + '&cth=' + $("#" + e.id.substring(e.id.length - 1) + "").val() + '&cinNo=' + $('#inputPassword').val() + '&itemNo=' + $('#itemNoChange').val(),
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
        success: function(data) {

            $("#itemSwRtaChange1").val(data[0]);
            $("#itemSwAmtChange1").val(data[1]);
            $("#itemSwAmtFgChange1").val(data[2]);
            $("#itemDutyChange1").val(data[3]);
            $("#itemDutyFgChange1").val(data[4]);
            
            
      
           // $("#itemIgstRtaChange1").val(data[7]);
            $("#itemIgstAmtChange1").val(data[7]);
            $("#itemIgstAmtFgChange1").val(data[8]);
           
            
            var curbcd = $("#itemBcdAmtChange1").val();
            var curigst =  $("#itemIgstAmtChange1").val();
            var cursw = $("#itemSwAmtChange1").val();
            var curbcdfg = $("#itemBcdAmtFgChange1").val()=='' ? 0 : $("#itemBcdAmtFgChange1").val();
            var curigstfg = $("#itemIgstAmtFgChange1").val()=='' ? 0 : $("#itemIgstAmtFgChange1").val();
            var curswfg = $("#itemSwAmtFgChange").val()=='' ? 0 : $("#itemSwAmtFgChange").val();


           var curitmduty = (parseFloat(curbcd)+parseFloat(curigst)+parseFloat(cursw)+parseFloat(previousDuty)-parseFloat(prvbcd)-parseFloat(prvigst)-parseFloat(prvsw)).toFixed(2);
            
     
           var curitmdutyfg = (parseFloat(curbcdfg)+parseFloat(curigstfg)+parseFloat(curswfg)+parseFloat(previousDutyFg)-parseFloat(prvbcdfg)-parseFloat(prvigstfg)-parseFloat(prvswfg)).toFixed(2);
            
         
            $("#itemDutyChange1").val(curitmduty);
            
            
            $("#itemDutyFgChange1").val(curitmdutyfg);
            
            
            var curtotitmduty =( parseFloat(prvtotitmduty) - parseFloat(previousDuty) + parseFloat(curitmduty)).toFixed(2);
           
            
            var curtotitmdutyfg = (parseFloat(prvtotitmdutyfg) -  parseFloat(previousDutyFg) + parseFloat(curitmdutyfg)).toFixed(2);
           
            $("#allItemDuty").val(curtotitmduty);
            $("#allItemDutyFg").val(curtotitmdutyfg);
            
            

            if (totval > $('#dutylim').val()) {
                $("#totaldutyPayable").val($("#allItemDuty").val());
            } else if ($("#cat").val() == $("#category").val())
                $("#totaldutyPayable").val($("#allItemDuty").val());
            else {
                $("#totaldutyPayable").val(0);
            }
            
    
            
            
            
            totalDutyDiff = $('#allItemDuty').val();
            totalDutyDiffFg = $('#allItemDutyFg').val();
         //   calTotDutyNineEgtAss('A');
            calTotPayable();
        },
        fail: function(rs, e) {
            console.log(rs, responseText);
        }
    });
}


function updateQry(id, value) {
		var developerData = {};
		developerData['cinNo'] = id;
		developerData['itemId'] = $('#itemId').val();
		developerData['itemNumber'] = $('#itemNoChange').val();
		developerData['query'] = $("#query").val();
		if ($('#isqry').val() == ""  || $('#isqry').val() == null)
		   $('#isqry').val("Y");
		var resObj = $.ajax({
			url: 'eadItemQryUpdate',
			data: JSON.stringify(developerData),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(developerDatas) {
			//	alert("Queries Are Updated Successfully");
				if (null == developerDatas['cinNo']) {
					//$("#updatePopup").modal('show');
					$('#nextPageButton').attr("disabled", false);
				} else {
					$('#nextPageButton').attr("disabled", true);
				}
                      
				$("#itemBcdNoNtChange1").attr("disabled", true);
				$("#itemBcdNsNoChange1").attr("disabled", true);
				$("#itemIgstNoNtChange1").attr("disabled", true);
				$("#itemIgstNsNoChange1").attr("disabled", true);
				$("#itemSwNoNtChange1").attr("disabled", true);
				$("#itemSwNsNoChange1").attr("disabled", true);
				$("#1").attr("disabled", true);
				$("#query").attr("disabled", true);
				$(".txthide").attr("disabled", true); 
				ass_hide();
				$('.updateAss').attr("disabled", true);
				$("#bothAssQry").attr("disabled", false);
			//	$('#changeButton').attr("disabled", false);
			},
			fail: function(rs, e) {
				alert("Error in Assessment");
			}
		});


}

function pedifile(id) {
	
	if ($('#clrsite').val()!='' && $('#clrsite').val()!= $('#sitecode').val())
	      $('#commclrPopup').modal('show');
	else
       $('#edifilePopup').modal('show');
}

function edipopup(){
 $('#edifilePopup').modal('show');
}
// ---------------------------------  // changed and commented previous function and added new function for reupdate in AAA --------------------------------------------

/*function Dutyentpopup(id, value) {
var opt = 0;
if (!($('#itemBcdNsNoChange1').val()==null) && !($('#itemBcdNsNoChange1').val()=='Select SNO'))
   opt =1;
if (!($('#itemIgstNsNoChange1').val()==null) && !($('#itemIgstNsNoChange1').val()=='Select SNO'))
   opt =1;
if (!($('#itemSwNsNoChange1').val()==null) && !($('#itemSwNsNoChange1').val()=='Select SNO'))
   opt=1;
if ($('#1').val() == "Others")
   opt=2;
if ((opt == 1)  || (opt == 2)) {
	$('#upddutyentPopup').modal('show');
	} 
else {
		updateAssessment(id, value);
	 }
}*/



function Dutyentpopup(id, value) {
    var opt = 0;
    if (!($('#itemBcdNsNoChange1').val() == null) && !($('#itemBcdNsNoChange1').val() == 'Select SNO'))
        opt = 1;
    if (!($('#itemIgstNsNoChange1').val() == null) && !($('#itemIgstNsNoChange1').val() == 'Select SNO'))
        opt = 1;
    if (!($('#itemSwNsNoChange1').val() == null) && !($('#itemSwNsNoChange1').val() == 'Select SNO'))
        opt = 1;
    if ($('#1').val() == "Others")
        opt = 2;
    var developerData = {};
	            developerData['cinNo'] = $("#inputPassword").val();
	             var resObj = $.ajax({
		              url: 'chkquery',
		              data: JSON.stringify(developerData),
		              dataType: "json",
		              contentType: "application/json",
		              type: "post",
		         success: function(developerDatas) {			      
			        if ($('#updateassvalue').val() == 'updated' && developerDatas) {
				     $('#assinfomodal').modal('show');
			         $('#updateqryid').val(id);
			         $('#updateqryval').val(value);
			         } 
                    else if ((opt == 1) || (opt == 2))
			           $('#upddutyentPopup').modal('show');
			       else 
			            updateAssessment(id, value);			          
                  },
                  fail: function(rs, e) {
                      alert("error in chkquery");
                  }
});
        
}

// -------------------------------------------------   end ----------------------------------------------------------------------------------------------------


function updpopup_change() {
	var blink = document.getElementById('nextPageButton');
      setInterval(function() {
        blink.style.opacity = (blink.style.opacity == 0 ? 1 : 0);
      }, 500);
    if ($('#isqry').val() == "Y") {
        if ($('#role').val() == "PAO" && (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val())))
            $("#qryupdatePopupACL").modal('show');
        else
            $("#qryupdatePopup").modal('show');
    } else {
        if ($('#role').val() == "PAO" && (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val())))
            $("#updatePopupACL").modal('show');
        else
            $("#updatePopup").modal('show');
    }
      document.getElementById('nextPageButton').focus();
}

function pmovedi(id) {
	var developerData = {};
	developerData['cinNo'] = id;
	var resObj = $.ajax({
                        url: 'pmovedi',
						data: JSON.stringify(developerData),
						dataType: "json",
						contentType: "application/json",
						type: "post",
						success: function(developerDatas) {
							window.location.href = "process_ead";
						},
						fail: function(rs, e) {
							alert("Error in Login");
						}
        });
		window.location.href = "process_ead";
}

function updateAssessment(id, value) {
    $('#upddutyentPopup').modal('hide');
    if ($('#1').val() != "Others") {
        updateNinEgtData();
        var developerData = {};
        developerData['cinNo'] = id;
        developerData['itemId'] = $('#itemId').val();
        developerData['itemNumber'] = $('#itemNoChange').val();
        if (!($("#itemBcdNoNtChange1").val() == "Select NOTN"))
            developerData['bcdNt'] = $("#itemBcdNoNtChange1").val();
        if (!($("#itemBcdNsNoChange1").val() == "Select SL.NO"))
            developerData['bcdNtNo'] = $("#itemBcdNsNoChange1").val();
        developerData['bcdRate'] = $("#itemBcdRtaChange1").val();
        developerData['bcdAmt'] = $("#itemBcdAmtChange1").val();
        developerData['bcdAmtFg'] = $("#itemBcdAmtFgChange1").val();
        if (!($("#itemIgstNoNtChange1").val() == "Select NOTN"))
            developerData['igstNt'] = $("#itemIgstNoNtChange1").val();
        if (!($("#itemIgstNsNoChange1").val() == "Select SL.NO"))
            developerData['igstNtNo'] = $("#itemIgstNsNoChange1").val();
        developerData['igstRate'] = $("#itemIgstRtaChange1").val();
        developerData['igstAmt'] = $("#itemIgstAmtChange1").val();
        developerData['igstAmtFg'] = $("#itemIgstAmtFgChange1").val();
        if (!($("#itemSwNoNtChange1").val() == "Select NOTN"))
            developerData['swNt'] = $("#itemSwNoNtChange1").val();
        if (!($("#itemSwNsNoChange1").val() == "Select SL.NO"))
            developerData['swNtNo'] = $("#itemSwNsNoChange1").val();
        developerData['swRate'] = $("#itemSwRtaChange1").val();
        developerData['swAmt'] = $("#itemSwAmtChange1").val();
        developerData['swAmtFg'] = $("#itemSwAmtFgChange").val();
        developerData['singleItemDuty'] = $("#itemDutyChange1").val();
        developerData['dutyFg'] = $("#itemDutyFgChange1").val();
        developerData['allItemDuty'] = $("#allItemDuty").val();
        developerData['allItemDutyFg'] = $("#allItemDutyFg").val();
        developerData['dutyPayable'] = $("#totaldutyPayable").val();
        developerData['query'] = $("#query" + $('#itemNoChange').val()).val();
        developerData['assess_VAL'] = $("#assessVal").val();
        developerData['rate'] = $("#itemRateChange1").val();
        developerData['gen_CTH'] = $("#1").val();
        developerData['calcFlag'] = "A";

        var resObj = $.ajax({
            url: 'eadItemAssessmentUpdate',
            data: JSON.stringify(developerData),
            dataType: "json",
            contentType: "application/json",
            type: "post",
            success: function(developerDatas) {
                //	alert("Values Are Updated Successfully");

                if (null == developerDatas['cinNo']) {
                    updpopup_change();
                    $('#nextPageButton').attr("disabled", false);
                } else {
                    $('#nextPageButton').attr("disabled", true);
                    var blink = document.getElementsByClassName('nextitmu')[0];
                    interval1=setInterval(function() {
                   blink.style.opacity = (blink.style.opacity == 0 ? 1 : 0);
                   blink.style.background = 'rgb(235, 71, 71)';
                   blink.style.color = 'white';
                   $($(blink).find('span')[0]).text('Click for next item >>');
                     }, 500);
                     var blink1 = document.getElementsByClassName('nextitmd')[0];
                    interval2=setInterval(function() {
                   blink1.style.opacity = (blink1.style.opacity == 0 ? 1 : 0);
                   blink1.style.background = 'rgb(235, 71, 71)';
                   blink1.style.color = 'white';
                   $($(blink1).find('span')[0]).text('Click for next item >>');
                     }, 500);
                   
                }

                //   if (!(parseFloat($('#acfirst').val())==1 && $('#role').val()=='PAC')){
              /*  $("#itemBcdNoNtChange1").attr("disabled", true);
                $("#itemBcdNsNoChange1").attr("disabled", true);
                $("#itemIgstNoNtChange1").attr("disabled", true);
                $("#itemIgstNsNoChange1").attr("disabled", true);
                $("#itemSwNoNtChange1").attr("disabled", true);
                $("#itemSwNsNoChange1").attr("disabled", true);
                $("#1").attr("disabled", true);*/
                // $("#query" + $('#itemNoChange').val()).attr("disabled", true);
                //$("#queryraisebtns").attr("disabled", true);
              //  $('.updateAss').attr("disabled", true);
                $('#changeButton').attr("disabled", false);
				$('#updatevalue').val('updated');
                //}
            },
            fail: function(rs, e) {
                alert("Error in Assessment");
            }
        });

    } else {
        updateOthersData();
    }
    
}

function updateNinEgtData() {
	var fpoItemDetOthDuty;
	var fpoOthersList = [];

	$("#etr98AssTab").find("tr").each(function() {
		fpoItemDetOthDuty = {};

		if (null != $(this).find("td").eq(0).find("[name*='DUTY']").val() && "" != $(this).find("td").eq(0).find("[name*='DUTY']").val()) {
			fpoItemDetOthDuty['duty_DESC'] = $(this).find("td").eq(0).find("[name*='DUTY']").val();
		}

		if (null != $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val() && "" != $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val()) {
			fpoItemDetOthDuty['duty_RTA'] = $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val();
		}

		if (null != $(this).find("td").eq(2).find("[name*='NOTN']").val() && "" != $(this).find("td").eq(2).find("[name*='NOTN']").val()) {
			fpoItemDetOthDuty['duty_NOTN'] = $(this).find("td").eq(2).find("[name*='NOTN']").val();
		}

		if (null != $(this).find("td").eq(3).find("[name*='SL.NO']").val() && "" != $(this).find("td").eq(3).find("[name*='SL.NO']").val()) {
			fpoItemDetOthDuty['duty_SLNO'] = $(this).find("td").eq(3).find("[name*='SL.NO']").val();
		}

		if (null != $(this).find("td").eq(4).find("[name*='EFF.RATE']").val() && "" != $(this).find("td").eq(4).find("[name*='EFF.RATE']").val()) {
			fpoItemDetOthDuty['rate'] = $(this).find("td").eq(4).find("[name*='EFF.RATE']").val();
		}

		if (null != $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val()) {
			fpoItemDetOthDuty['duty_AMT'] = $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val();
		}

		if (null != $(this).find("td").eq(6).find("[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("[name*='DUTY_FG']").val()) {
			fpoItemDetOthDuty['DUTY_FG'] = $(this).find("td").eq(6).find("[name*='DUTY_FG']").val();
		}

		if (null != fpoItemDetOthDuty['duty_DESC'] && fpoItemDetOthDuty['duty_DESC'] != "") {
			fpoItemDetOthDuty['cin_NO'] = $('#inputPassword').val();
			fpoItemDetOthDuty['duty_CD'] = null;
			fpoItemDetOthDuty['item_NO'] = $('#itemNoChange').val();
			fpoItemDetOthDuty['cin_DT'] = new Date($('#cinDt').val());
			fpoItemDetOthDuty['item_ID'] = $('#itemId').val();
			fpoItemDetOthDuty['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoItemDetOthDuty['off_ID'] = $('#offId').val();
			fpoItemDetOthDuty['cus_SITE'] = $('#cusSite').val();
			fpoItemDetOthDuty['role'] = $('#role').val();
			fpoItemDetOthDuty['cth'] = $('#1').val();
			fpoItemDetOthDuty['ass_DT'] = new Date();
			fpoItemDetOthDuty['totAllAmount'] = $('#totalOthersDuty').val();
			fpoItemDetOthDuty['allItemAmount'] = $('#allItemDuty').val();
			fpoOthersList.push(fpoItemDetOthDuty);
		}
	});

	$.ajax({
		url: 'saveFpoItemNinEgtOthrList',
		data: JSON.stringify(fpoOthersList),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(fpoOthersLists) {
			$("#etr98AssTab").find("tr").each(function() {
				$(this).find("td").eq(0).find("[name*='DUTY']").attr("disabled", true);
				$(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").attr("disabled", true);
				$(this).find("td").eq(2).find("[name*='NOTN']").attr("disabled", true);
				$(this).find("td").eq(3).find("[name*='SL.NO']").attr("disabled", true);
				$(this).find("td").eq(4).find("[name*='EFF.RATE']").attr("disabled", true);
				$(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").attr("disabled", true);
				$(this).find("td").eq(6).find("[name*='DUTY_FG']").attr("disabled", true);
				$(this).find("td").eq(7).find("[name*='DELETE']").attr("disabled", true);
			});
			$('#addrow7').attr("disabled", true);
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function updateOthersData() {

	var fpoItemDetOthDuty;
	var fpoOthersList = [];
	var dutyArray = [];
	var rateUSArray = [];
	var notnArray = [];
	var slnoUSArray = [];
	var effRate = [];
	var dutyAmtArray = [];
	var dutyFg = [];

	$("#tbOtrDt").find("tr").each(function() {
		fpoItemDetOthDuty = {};

		if (null != $(this).find("td").eq(0).find("option:selected").val() && "" != $(this).find("td").eq(0).find("option:selected").val()) {
			dutyArray.push($(this).find("td").eq(0).find("select[name*='DUTY']").val());
			fpoItemDetOthDuty['duty_CD'] = $(this).find("td").eq(0).find("select[name*='DUTY']").val();
		}

		if (null != $(this).find("td").eq(1).find("input[name*='RATE_OF_DUTY']").val() && "" != $(this).find("td").eq(1).find("input[name*='RATE_OF_DUTY']").val()) {
			rateUSArray.push($(this).find("td").eq(1).find("input[name*='RATE_OF_DUTY']").val());
			fpoItemDetOthDuty['duty_RTA'] = $(this).find("td").eq(1).find("input[name*='RATE_OF_DUTY']").val();
		}

		if (null != $(this).find("td").eq(2).find("option:selected").val() && "" != $(this).find("td").eq(2).find("option:selected").val()) {
			notnArray.push($(this).find("td").eq(2).find("option:selected").val());
			fpoItemDetOthDuty['duty_NOTN'] = $(this).find("td").eq(2).find("option:selected").val();
		}

		if (null != $(this).find("td").eq(3).find("option:selected").val() && "" != $(this).find("td").eq(3).find("option:selected").val()) {
			slnoUSArray.push($(this).find("td").eq(3).find("option:selected").val());
			fpoItemDetOthDuty['duty_SLNO'] = $(this).find("td").eq(3).find("option:selected").val();
		}

		if (null != $(this).find("td").eq(4).find("input[name*='EFF.RATE']").val() && "" != $(this).find("td").eq(4).find("input[name*='EFF.RATE']").val()) {
			effRate.push($(this).find("td").eq(4).find("input[name*='EFF.RATE']").val());
			fpoItemDetOthDuty['rate'] = $(this).find("td").eq(4).find("input[name*='EFF.RATE']").val();
		}

		if (null != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val()) {
			dutyAmtArray.push($(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val());
			fpoItemDetOthDuty['duty_AMT'] = $(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").val();
		}

		if (null != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val()) {
			dutyFg.push($(this).find("td").eq(6).find("input[name*='DUTY_FG']").val());
			fpoItemDetOthDuty['DUTY_FG'] = $(this).find("td").eq(6).find("input[name*='DUTY_FG']").val();
		}

		if (null != fpoItemDetOthDuty['duty_CD'] && fpoItemDetOthDuty['duty_CD'] != "" && !(fpoItemDetOthDuty['duty_CD'].toString().startsWith('Select Duty'))) {
			fpoItemDetOthDuty['cin_NO'] = $('#inputPassword').val();
			fpoItemDetOthDuty['item_NO'] = $('#itemNoChange').val();
			fpoItemDetOthDuty['cin_DT'] = new Date($('#cinDt').val());
			fpoItemDetOthDuty['item_ID'] = $('#itemId').val();
			fpoItemDetOthDuty['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoItemDetOthDuty['off_ID'] = $('#offId').val();
			fpoItemDetOthDuty['cus_SITE'] = $('#cusSite').val();
			fpoItemDetOthDuty['role'] = $('#role').val();
			fpoItemDetOthDuty['cth'] = $('#getOtherDigit').val();
			fpoItemDetOthDuty['ass_DT'] = new Date();
			fpoItemDetOthDuty['query'] = $("#query").val();
			fpoItemDetOthDuty['totAllAmount'] = $('#totalOthersDuty').val();
			fpoItemDetOthDuty['allItemAmount'] = $('#allItemDuty').val();
			fpoOthersList.push(fpoItemDetOthDuty);
		}
	});

	$("#enterOthDuty").find("tr").each(function() {
		fpoItemDetOthDuty = {};

		if (null != $(this).find("td").eq(0).find("[name*='DUTY']").val() && "" != $(this).find("td").eq(0).find("[name*='DUTY']").val()) {
			fpoItemDetOthDuty['duty_DESC'] = $(this).find("td").eq(0).find("[name*='DUTY']").val();
		}

		if (null != $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val() && "" != $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val()) {
			fpoItemDetOthDuty['duty_RTA'] = $(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").val();
		}

		if (null != $(this).find("td").eq(2).find("[name*='NOTN']").val() && "" != $(this).find("td").eq(2).find("[name*='NOTN']").val()) {
			fpoItemDetOthDuty['duty_NOTN'] = $(this).find("td").eq(2).find("[name*='NOTN']").val();
		}

		if (null != $(this).find("td").eq(3).find("[name*='SL.NO']").val() && "" != $(this).find("td").eq(3).find("[name*='SL.NO']").val()) {
			fpoItemDetOthDuty['duty_SLNO'] = $(this).find("td").eq(3).find("[name*='SL.NO']").val();
		}

		if (null != $(this).find("td").eq(4).find("[name*='EFF.RATE']").val() && "" != $(this).find("td").eq(4).find("[name*='EFF.RATE']").val()) {
			fpoItemDetOthDuty['rate'] = $(this).find("td").eq(4).find("[name*='EFF.RATE']").val();
		}

		if (null != $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val() && "" != $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val()) {
			fpoItemDetOthDuty['duty_AMT'] = $(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").val();
		}

		if (null != $(this).find("td").eq(6).find("[name*='DUTY_FG']").val() && "" != $(this).find("td").eq(6).find("[name*='DUTY_FG']").val()) {
			fpoItemDetOthDuty['DUTY_FG'] = $(this).find("td").eq(6).find("[name*='DUTY_FG']").val();
		}
		if (null != fpoItemDetOthDuty['duty_DESC'] && fpoItemDetOthDuty['duty_DESC'] != "") {
			fpoItemDetOthDuty['cin_NO'] = $('#inputPassword').val();
			fpoItemDetOthDuty['duty_CD']=null;
			fpoItemDetOthDuty['item_NO'] = $('#itemNoChange').val();
			fpoItemDetOthDuty['cin_DT'] = new Date($('#cinDt').val());
			fpoItemDetOthDuty['item_ID'] = $('#itemId').val();
			fpoItemDetOthDuty['postingdt'] = $('#postDate').val() + "T" + $('#postTime').val();
			fpoItemDetOthDuty['off_ID'] = $('#offId').val();
			fpoItemDetOthDuty['cus_SITE'] = $('#cusSite').val();
			fpoItemDetOthDuty['role'] = $('#role').val();
			fpoItemDetOthDuty['cth'] = $('#getOtherDigit').val();
			fpoItemDetOthDuty['query'] = $("#query").val();
			fpoItemDetOthDuty['ass_DT'] = new Date();
			fpoItemDetOthDuty['totAllAmount'] = $('#totalOthersDuty').val();
			fpoItemDetOthDuty['allItemAmount'] = $('#allItemDuty').val();
			fpoOthersList.push(fpoItemDetOthDuty);
		}
	});

	$.ajax({
		url: 'getFpoItemOthersList',
		data: JSON.stringify(fpoOthersList),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			var data1 = {};
			data1 = data.stringval;
			$("#tbOtrDt").find("tr").each(function() {
				
				// ------ // changed Commented for reUpdate in AAA ------
				
				/*$(this).find("td").eq(0).find("select[name*='DUTY']").attr("disabled", true);
				$(this).find("td").eq(1).find("input[name*='RATE_OF_DUTY']").attr("disabled", true);
				$(this).find("td").eq(2).find("select[name*='NOTN']").attr("disabled", true);
				$(this).find("td").eq(3).find("select[name*='SL.NO']").attr("disabled", true);
				$(this).find("td").eq(4).find("input[name*='EFF.RATE']").attr("disabled", true);
				$(this).find("td").eq(5).find("input[name*='DUTY_AMOUNT']").attr("disabled", true);
				$(this).find("td").eq(6).find("input[name*='DUTY_FG']").attr("disabled", true);
				$(this).find("td").eq(7).find("input[name*='DELETE']").attr("disabled", true);*/
				
				
			});

			$("#enterOthDuty").find("tr").each(function() {
			/*	$(this).find("td").eq(0).find("[name*='DUTY']").attr("disabled", true);
				$(this).find("td").eq(1).find("[name*='RATE_OF_DUTY']").attr("disabled", true);
				$(this).find("td").eq(2).find("[name*='NOTN']").attr("disabled", true);
				$(this).find("td").eq(3).find("[name*='SL.NO']").attr("disabled", true);
				$(this).find("td").eq(4).find("[name*='EFF.RATE']").attr("disabled", true);
				$(this).find("td").eq(5).find("[name*='DUTY_AMOUNT']").attr("disabled", true);
				$(this).find("td").eq(6).find("[name*='DUTY_FG']").attr("disabled", true);
				$(this).find("td").eq(7).find("[name*='DELETE']").attr("disabled", true);*/
				
				// --------------------- end -----------------
			});

			if (null == data1) {
				updpopup_change();
				$('#nextPageButton').attr("disabled", false);
				
				 var blink = document.getElementsByClassName('nextitmu')[0]; 
                    interval3=setInterval(function() {
                   blink.style.opacity = (blink.style.opacity == 0 ? 1 : 0);
                   blink.style.background = 'rgb(235, 71, 71)';
                   blink.style.color = 'white';
                   $($(blink).find('span')[0]).text('Click for next item >>');
                     }, 500);
				
				
			}
			else {
				$('#nextPageButton').attr("disabled", true);
                    var blink = document.getElementsByClassName('nextitmu')[0];
                    interval3=setInterval(function() {
                   blink.style.opacity = (blink.style.opacity == 0 ? 1 : 0);
                   blink.style.background = 'rgb(235, 71, 71)';
                   blink.style.color = 'white';
                   $($(blink).find('span')[0]).text('Click for next item >>');
                     }, 500);
			}
			$('.updateAss').attr("disabled", false);  // changed for reUpdate in AAF
			$('.updateAss').addClass('blink1');  // changed for reUpdate in AAF
			$('#changeButton').attr("disabled", false);
			//$('#nextPageButton').attr("disabled", false);
			$('#1').attr("disabled", false); // changed for reUpdate in AAF
			$('#getOtherFourDigi').attr("disabled", false); // changed for reUpdate in AAF
			$('#getOtherEightDigi').attr("disabled", false); // changed for reUpdate in AAF
			$('#getOtherDigit').attr("disabled", false); // changed for reUpdate in AAF
			$('#addrow1').attr("disabled", false); // changed for reUpdate in AAF
			$('#addrow7').attr("disabled", true);
			$('#bothAssQry').attr("disabled", true);
			$("#qryShHd").hide();
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

if (!(null == $("#itemDutyFgChange1").val()) && !("" == $("#itemDutyFgChange1").val()) && !($("#itemDutyFgChange1").val() == "0.0")) {
	var developerData = {};
	developerData['cinNo'] = $('#inputPassword').val();
	developerData['itemId'] = $('#itemId').val();
	developerData['itemNumber'] = $('#itemNoChange').val();
	$.ajax({
		url: 'updatedSpecificItemValue',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			$("#itemBcdNoNtChange1 option:contains(" + developerDatas['bcd_NOTN'] + ")").attr('selected', 'selected');
			$("#itemBcdNsNoChange1").append($('<option>', {
				text: developerDatas['bcd_NSNO']
			}));
			$("#itemBcdNsNoChange1 option:contains(" + developerDatas['bcd_NSNO'] + ")").attr('selected', 'selected');
			$("#itemIgstNoNtChange1 option:contains(" + developerDatas['igst_NOTN'] + ")").attr('selected', 'selected');
			$("#itemIgstNsNoChange1").append($('<option>', {
				text: developerDatas['igst_NSNO']
			}));
			$("#itemIgstNsNoChange1 option:contains(" + developerDatas['igst_NSNO'] + ")").attr('selected', 'selected');
			$("#itemSwNoNtChange1 option:contains(" + developerDatas['sw_NONT'] + ")").attr('selected', 'selected');
			$("#itemSwNsNoChange1").append($('<option>', {
				text: developerDatas['sw_NSNO']
			}));
			$("#itemSwNsNoChange1 option:contains(" + developerDatas['sw_NSNO'] + ")").attr('selected', 'selected');
			$("#1 option:contains(" + developerDatas['cth'] + ")").attr('selected', 'selected');
			$("#query").text(developerData['query']);
			$("#itemPagination1").text(developerDatas['item_NO'] + "/" + $("#noOfItems").text());

		//	$("#itemBcdNoNtChange1").attr("disabled", true);
		//	$("#itemBcdNsNoChange1").attr("disabled", true);
		//	$("#itemIgstNoNtChange1").attr("disabled", true);
		//	$("#itemIgstNsNoChange1").attr("disabled", true);
		//	$("#itemSwNoNtChange1").attr("disabled", true);
		//	$("#itemSwNsNoChange1").attr("disabled", true);
		//	$("#bothAssQry").attr("disabled", true);
		//	$("#1").attr("disabled", true);
		//	$("#query").attr("disabled", true);
		//	$('.updateAss').attr("disabled", true);
			
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}
else {
	$('#changeButton').attr("disabled", true);
}

function nextItemNo(value) {
    var developerData = {};
    var navi = 0;
    if (value == 'previousItem')
        navi = 1;
    else
        navi = 2;
    developerData['cinNo'] = $('#inputPassword').val();
    developerData['itemId'] = $('#itemId').val();
    developerData['itemNumber'] = $('#itemNoChange').val();
    dropCount = 0;
    var resObj = $.ajax({
        url: '' + value,
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
        success: function(developerDatas) {
         /*   clearDropDown($("#itemBcdNsNoChange1"));
            clearDropDown($("#itemIgstNsNoChange1"));
            clearDropDown($("#itemSwNsNoChange1"));*/
           // $(".txthide").attr("disabled", true);
            $("#itemNoChange").val(developerDatas['item_NO']);

			if(( $("#query" + $('#itemNoChange').val()).val() != null && $("#query" + $('#itemNoChange').val()).val() != '') || ($("#generalRemarks").val() != null && $("#generalRemarks").val() != '') ){
				$('#updateassvalue').val('updated');
			}else{
				$('#updateassvalue').val('');
			}
			
			

           /* if (developerDatas['ass_DT'] == null) {
                // $("#query" + $('#itemNoChange').val()).attr("disabled", false);
              //  $("#query" + $('#itemNoChange').val()).val("");
                $("#bothAssQry").attr("disabled", true);
                ass_show();
                ass_enable();
            } else {
                if (developerDatas['query'] != null) {
                  //  $("#query" + $('#itemNoChange').val()).val(developerDatas['query']);
                    // $("#query" + $('#itemNoChange').val()).attr("disabled", true);
                    if (developerDatas['updass_DT'] != null)
                        ass_show();
                    else
                        ass_hide();
                    ass_disable();
                    $("#bothAssQry").attr("disabled", true);
                }
            }*/

            $("#itemSwAmtChange1").val(developerDatas['sw_AMT']);
            $("#itemIgstAmtChange1").val(developerDatas['igst_AMT']);
            $("#itemBcdAmtChange1").val(developerDatas['bcd_AMT']);

            $("#itemSwAmtFgChange").val(developerDatas['sw_AMT_FG']);
            $("#itemIgstAmtFgChange1").val(developerDatas['igst_AMT_FG']);
            $("#itemBcdAmtFgChange1").val(developerDatas['bcd_AMT_FG']);
            $("#itemDutyFgChange1").val(developerDatas['duty_FG']);
            $('#1').val(developerDatas['gen_CTH']);
            $("#itemNoChange").val(developerDatas['item_NO']);
            $("#itemDESChange").val(developerDatas['item_DESC']);
            $("#itemCthChange" + developerData['itemNumber'] + "").val(developerDatas['gen_CTH']);
            $("#itemRevisedCthChange").val(developerDatas['cth_REVISED']);
            $("#itemNouChange").val(developerDatas['nou']);
            $("#itemNetWtChange").val(developerDatas['netwt']);
            $("#itemDeclValChange1").val(developerDatas['decl_VAL']);
            $("#itemCurCdChange").val(developerDatas['currcd']);
            $("#itemOrgCntryCdChange").val(developerDatas['origcntrycd']);
            $("#itemRateChange1").val(developerDatas['rate']);
            $("#itemPagination1").text(developerDatas['item_NO'] + "/" + $("#noOfItems").text());
            $("#nextItemPegination2").text(developerDatas['item_NO']);
            $("#nextItemPegination1").text(developerDatas['item_NO']);
            clearInterval(interval1);
            clearInterval(interval2);
            clearInterval(interval3);
            clearInterval(interval4);
            var blink = document.getElementsByClassName('nextitmu')[0];
            var blink1 = document.getElementsByClassName('nextitmd')[0];
            var blink2 = document.getElementsByClassName('previousitmu')[0];
            var blink3 = document.getElementsByClassName('previousitmd')[0];
            $(blink).removeAttr('style');
            $(blink1).removeAttr('style');
            $($(blink).find('span')[0]).text('»');
            $($(blink1).find('span')[0]).text('»');
            if(parseInt(developerDatas['item_NO'])===parseInt($("#noOfItems").text())){
	            $(blink).css('display','none');
	            $(blink1).css('display','none');
            }else{
				$(blink).css('display','initial');
	            $(blink1).css('display','initial');
			}
			if(parseInt(developerDatas['item_NO'])==1){
	            $(blink2).css('display','none');
	            $(blink3).css('display','none');
			}else{
	            $(blink2).css('display','initial');
	            $(blink3).css('display','initial');
			}
            $("#itemDutyChange1").val(developerDatas['duty']);
            $("#assessVal").val(developerDatas['assval_INSFRT']);
            $("#itemSwRtaChange1").val(developerDatas['sw_RTA']);
            $("#itemIgstRtaChange1").val(developerDatas['igst_RTA']);
            $("#itemBcdRtaChange1").val(developerDatas['bcd_RTA']);
            $("#allItemDuty").val(developerDatas['allItemDuty']);
            $("#allItemDutyFg").val(developerDatas['allItemDutyFg']);
            $("#itemOrgCntryCdChange").val(developerDatas['cus_SITE']);
            $("#1 option:contains(" + "Other than 9804" + ")").prop("selected", false);
            if ((null == developerDatas['ass_DT'] && null == developerDatas['amend_DT']) || (null == developerDatas['fpoItemDetOthDuty'] || developerDatas.fpoItemDetOthDuty.length == 0 || developerDatas.fpoItemDetOthDuty[0].cth == '98041000' || developerDatas.fpoItemDetOthDuty[0].cth == '98049000')) {

/*				$("#itemBcdNoNtChange1").val($("#itemBcdNoNtChange1 option:first").val());
				$("#itemBcdNoNtChange1").change();*/
				$("#itemIgstNoNtChange1").val($("#itemIgstNoNtChange1 option:first").val());
				$("#itemIgstNoNtChange1").change();
				$("#itemSwNoNtChange2").val($("#itemSwNoNtChange2 option:first").val());
				$("#itemSwNoNtChange2").change();
                $("#" + developerData['itemNumber'] + " option:contains(" + developerDatas['gen_CTH'] + ")").attr('selected', 'selected');
                $("#itemBcdNoNtChange1 option:contains(" + "Select NOTN" + ")").attr('selected', 'selected');
                $("#itemBcdNsNoChange1 option:contains(" + "Select SL.NO" + ")").attr('selected', 'selected');
                $("#itemIgstNoNtChange1 option:contains(" + "Select NOTN" + ")").attr('selected', 'selected');
                $("#itemIgstNsNoChange1 option:contains(" + "Select SL.NO" + ")").attr('selected', 'selected');
                $("#itemSwNoNtChange1 option:contains(" + "Select NOTN" + ")").attr('selected', 'selected');
                $("#itemSwNsNoChange1 option:contains(" + "Select SL.NO" + ")").attr('selected', 'selected');

                $("#customDropDown1 option:contains(" + "Select Duty" + ")").attr('selected', 'selected');
                $("#notn1 option:contains(" + "Select NOTN" + ")").attr('selected', 'selected');
                $("#slno1 option:contains(" + "Select SL.NO" + ")").attr('selected', 'selected');
                $("#getOtherEightDigi option:contains(" + "Select" + ")").attr('selected', 'selected');
                $("#getOtherDigit option:contains(" + "Select" + ")").attr('selected', 'selected');
                $("#getOtherFourDigi option:contains(" + "Select" + ")").attr('selected', 'selected');
                //$("#1 option:contains(" + "Other than 9804" + ")").attr('selected', 'selected');

                $("#effRate1").val(0);
                $("#dutyAmount1").val(0);
                $("#dutyAmountFg1").val(0);
                $("#totalOthersDuty").val(0);
                $("#totalOthersDutyFg").val(0);
                $("#rateDuty1").val(0);

                if (null == developerDatas['ass_DT']) {
                    $("#itemBcdNoNtChange1").attr("disabled", false);
                    $("#itemBcdNsNoChange1").attr("disabled", false);
                    $("#itemIgstNoNtChange1").attr("disabled", false);
                    $("#itemIgstNsNoChange1").attr("disabled", false);
                    $("#itemSwNoNtChange1").attr("disabled", false);
                    $("#itemSwNsNoChange1").attr("disabled", false);
                    $("#1").attr("disabled", false);
                    if (!($('#role').val() == 'PAC') && ($('#role').val() == 'PAO' && $('#acloffid') != null)) {
                        $('.updateAss').attr("disabled", false);
                   //     $('.changeAss').attr("disabled", true);
                    }
                } else {
                    if (developerDatas.fpoItemDetOthDuty.length == 0)
                        $('#manualduty').hide();
                    else
                        $('#manualduty').show();
                    /*$("#itemBcdNoNtChange1").attr("disabled", true);
                    $("#itemBcdNsNoChange1").attr("disabled", true);
                    $("#itemIgstNoNtChange1").attr("disabled", true);
                    $("#itemIgstNsNoChange1").attr("disabled", true);
                    $("#itemSwNoNtChange1").attr("disabled", true);
                    $("#itemSwNsNoChange1").attr("disabled", true);
                    $("#1").attr("disabled", true);*/
                    // $("#query" + $('#itemNoChange').val()).attr("disabled", true);
                //$("#queryraisebtns").attr("disabled", true);
                  //  $('.updateAss').attr("disabled", true);
                //    $('.changeAss').attr("disabled", false);
                }

                $("#customDropDown1").attr("disabled", false);
                $("#notn1").attr("disabled", false);
                $("#slno1").attr("disabled", false);
                $("#getOtherEightDigi").attr("disabled", false);
                $("#getOtherDigit").attr("disabled", false);
                $("#getOtherFourDigi").attr("disabled", false);

                $("#effRate1").attr("disabled", false);
                $("#dutyAmount1").attr("disabled", false);
                $("#dutyAmountFg1").attr("disabled", false);
                $("#totalOthersDuty").attr("disabled", false);
                $("#totalOthersDutyFg").attr("disabled", false);
                $("#tbOtrDt tr").remove();
                $("#etr98DutyAss tr").remove();
                $("#enterOthDuty tr").remove();

                var counter = 1;
                var newRow = $("<tr>");
                var cols = "";
                cols += '<td><select type="text" id="customDropDown' + counter + '" onchange="othersNotn(this)" class="custom-select" name="DUTY[' + counter + ']" ><option>Select Duty</option></select></td>';
                cols += '<td><input type="text" id="rateDuty' + counter + '" class="form-control" readonly="readonly" name="RATE_OF_DUTY[' + counter + ']" /></td>';
                cols += '<td><select type="text" id="notn' + counter + '" onchange="othersSlNo(this)" class="custom-select" name="NOTN[' + counter + ']" ><option>Select NOTN</option></select></td>';
                cols += '<td><select type="text" id="slno' + counter + '" onchange="selectEachNsNo(this)" class="custom-select" name="SL.NO[' + counter + ']" ><option>Select SL. No</option></select></td>';
                cols += '<td><input type="text"  id="effRate' + counter + '" class="form-control" readonly="readonly" name="EFF.RATE[' + counter + ']" /></td>';
                cols += '<td><input type="text"  id="dutyAmount' + counter + '" onblur="calTotDuty(2)" class="form-control" style="text-align: right;" name="DUTY_AMOUNT[' + counter + ']" /></td>';
                cols += '<td><input type="text"  id="dutyAmountFg' + counter + '" onblur="calTotDutyFg()" class="form-control" style="text-align: right;" name="DUTY_FG[' + counter + ']" /></td>';
                newRow.append(cols);
                $("table.order-list1").append(newRow);

                var newRow = $("<tr>");
                var cols = "";
                cols += '<td><input type="text" id="enterDropDown' + counter + '" class="form-control"  name="DUTY[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="enterRateDuty' + counter + '" class="form-control" name="RATE_OF_DUTY[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="enterNotn' + counter + '" class="form-control" name="NOTN[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="enterSlno' + counter + '" class="form-control" name="SL.NO[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="enterEffRate' + counter + '" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="enterDutyAmount' + counter + '" class="form-control" onblur="calTotDuty(2)"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="dutyFgAmt' + counter + '" class="form-control" name="DUTY_FG[' + counter + ']" /></td>';
                /*cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " onclick = "calTotDutyNineEgtAss('+"'D'"+')" name="DELETE[' + counter + ']" value=" - "></td>';*/
                newRow.append(cols);
                $("table.order-list6").append(newRow);
                $("#addrow6").attr("disabled", false);

                var newRow = $("<tr>");
                var cols = "";
                cols += '<td><input type="text" id="etr98DropDown' + counter + '" class="form-control"  name="DUTY[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="etr98RateDuty' + counter + '" class="form-control" name="RATE_OF_DUTY[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="etr98Notn' + counter + '" class="form-control" name="NOTN[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="etr98Slno' + counter + '" class="form-control" name="SL.NO[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="etr98EffRate' + counter + '" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="etr98DutyAmount' + counter + '" class="form-control" onblur="calTotDutyNineEgtAss(' + "'B'" + ')"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
                cols += '<td><input type="text" id="etr98dtFgAmt' + counter + '" class="form-control" name="DUTY_FG[' + counter + ']" /></td>';
                /*cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " onclick = "calTotDutyNineEgtAss('+"'D'"+')" name="DELETE[' + counter + ']" value=" - "></td>';*/
                newRow.append(cols);
                $("table.order-list7").append(newRow);
                $("#addrow7").attr("disabled", false);

                //$("#bothAssQry").attr("disabled", false);

                if (null != developerDatas['ass_DT']) {
                    $("#addrow7").attr("disabled", true);
                    /*$('#etr98DropDown1').attr("disabled", true);
                    $('#etr98RateDuty1').attr("disabled", true);
                    $('#etr98Notn1').attr("disabled", true);
                    $('#etr98Slno1').attr("disabled", true);
                    $('#etr98EffRate1').attr("disabled", true);
                    $('#etr98DutyAmount1').attr("disabled", true);
                    $('#etr98dtFgAmt1').attr("disabled", true);*/
                }
                var stRow = 0;
                var ent98Row = 1;
                var counter = developerDatas.fpoItemDetOthDuty.length;
                var totitmduty = developerDatas['duty'];
                while (stRow < counter) {
                    $('#etr98DropDown' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_DESC);
                    $('#etr98DropDown' + ent98Row).attr("disabled", true);
                    $('#etr98RateDuty' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_RTA);
                    $('#etr98RateDuty' + ent98Row).attr("disabled", true);
                    $('#etr98Notn' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_NOTN);
                    $('#etr98Notn' + ent98Row).attr("disabled", true);
                    $('#etr98Slno' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_SLNO);
                    $('#etr98Slno' + ent98Row).attr("disabled", true);
                    $('#etr98EffRate' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].rate);
                    $('#etr98EffRate' + ent98Row).attr("disabled", true);
                    $('#etr98DutyAmount' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_AMT);
                    totitmduty = parseFloat(totitmduty) + parseFloat(developerDatas.fpoItemDetOthDuty[stRow].duty_AMT);
                    stRow = stRow + 1;
                    ent98Row = ent98Row + 1;
                }
                if (developerDatas["ass_DT"] == null)
                    $("#itemDutyChange1").val(parseFloat(totitmduty).toFixed(2));
                else
                    $("#itemDutyChange1").val(developerDatas["duty"]);
                $.ajax({
                    url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
                    data: JSON.stringify(""),
                    dataType: "json",
                    contentType: "application/json",
                    type: "post",
                    success: function(datas) {
                        $.each(datas, function(i, item) {
                            $('#customDropDown1').append($('<option>', {
                                text: item[1],
                                value: item[0]
                            }));
                        });
                    },
                    fail: function(rs, e) {
                        console.log(rs, responseText);
                    }
                });

                $('#qryShHd').show();
                $(".blueClass").hide();
                $(".yellowClass").show();
				if(developerDatas['bcd_NOTN'] != null && developerDatas['bcd_NSNO'] != null){
	                /*$("#itemBcdNoNtChange1").append($('<option>', {
	                    text: developerDatas['bcd_NOTN']
	                }));*/
	                $("#itemBcdNoNtChange1 option:contains(" + developerDatas['bcd_NOTN'] + ")").prop('selected', 'selected');
	                $("#itemBcdNsNoChange1").append($('<option>', {
	                    text: developerDatas['bcd_NSNO']
	                }));
	                $("#itemBcdNsNoChange1 option:contains(" + developerDatas['bcd_NSNO'] + ")").prop('selected', 'selected');
				}
				if(developerDatas['igst_NOTN'] != null && developerDatas['igst_NSNO'] != null){
	                /*$("#itemIgstNoNtChange1").append($('<option>', {
	                    text: developerDatas['igst_NOTN']
	                }));*/
	                $("#itemIgstNoNtChange1 option:contains(" + developerDatas['igst_NOTN'] + ")").prop('selected', 'selected');
	                $("#itemIgstNsNoChange1").append($('<option>', {
	                    text: developerDatas['igst_NSNO']
	                }));
	                $("#itemIgstNsNoChange1 option:contains(" + developerDatas['igst_NSNO'] + ")").prop('selected', 'selected');
				}
				if(developerDatas['sw_NOTN'] != null && developerDatas['sw_NSNO'] != null){
                /*$("#itemSwNoNtChange1").append($('<option>', {
                    text: developerDatas['sw_NOTN']
                }));*/
                $("#itemSwNoNtChange1 option:contains(" + developerDatas['sw_NOTN'] + ")").prop('selected', 'selected');
                $("#itemSwNsNoChange1").append($('<option>', {
                    text: developerDatas['sw_NSNO']
                }));
                $("#itemSwNsNoChange1 option:contains(" + developerDatas['sw_NSNO'] + ")").prop('selected', 'selected');
				}
            } else {

                var counter = developerDatas.fpoItemDetOthDuty.length;
                var stRow = 0;
                var custRow = 1;
                var entRow = 1;
                var ent98Row = 1;
                dispothrs(1);
                if (developerDatas.fpoItemDetOthDuty[0].gen_CTH != null && developerDatas.fpoItemDetOthDuty[0].gen_CTH != '98041000' && developerDatas.fpoItemDetOthDuty[0].gen_CTH != '98049000')
                    $('#1').val("Other than 9804");
                $("#tbOtrDt tr").remove();
                $("#enterOthDuty tr").remove();
                $("#etr98DutyAss tr").remove();
                $("#addrow7").attr("disabled", true);
                $("#addrow1").attr("disabled", false); // changed for reupdate for AAA

                var totitmduty = 0;
                while (stRow < counter) {

                    if (null != developerDatas.fpoItemDetOthDuty[stRow].duty_CD && developerDatas.fpoItemDetOthDuty[stRow].duty_CD != "" && !(developerDatas.fpoItemDetOthDuty[stRow].duty_CD.toString() == 'Select')) {
                        var newRow = $("<tr>");
                        var cols = "";
                        cols += '<td><select type="text" id="customDropDown' + custRow + '" onchange="othersNotn(this)" class="custom-select" name="DUTY[' + counter + ']" ><option>Select Duty</option></select></td>';
                        cols += '<td><input type="text" id="rateDuty' + custRow + '" class="form-control" readonly="readonly" name="RATE_OF_DUTY[' + counter + ']" /></td>';
                        cols += '<td><select type="text" id="notn' + custRow + '" onchange="othersSlNo(this)" class="custom-select" name="NOTN[' + counter + ']" ><option>Select NOTN</option></select></td>';
                        cols += '<td><select type="text" id="slno' + custRow + '" onchange="selectEachNsNo(this)" class="custom-select" name="SL.NO[' + counter + ']" ><option>Select SL. No</option></select></td>';
                        cols += '<td><input type="text"  id="effRate' + custRow + '" class="form-control" readonly="readonly" name="EFF.RATE[' + counter + ']" /></td>';
                        cols += '<td><input type="text"  id="dutyAmount' + custRow + '" onblur="calTotDuty(2)" class="form-control" style="text-align: right;" name="DUTY_AMOUNT[' + counter + ']" /></td>';
                        cols += '<td><input type="text"  id="dutyAmountFg' + custRow + '" onblur="calTotDutyFg()" class="form-control" style="text-align: right;" name="DUTY_FG[' + counter + ']" /></td>';
                        //cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value="Delete"></td>';
                        newRow.append(cols);
                        $("table.order-list1").append(newRow);

                        $.ajax({
                            url: 'getCustomDropDown?cinNo=' + $('#inputPassword').val(),
                            data: JSON.stringify(""),
                            dataType: "json",
                            contentType: "application/json",
                            type: "post",
                            success: function(datas) {
                                $.each(datas, function(i, item) {
                                    $('#customDropDown' + (dropCount + 1) + '').append($('<option>', {
                                        text: item[1],
                                        value: item[0]
                                    }));
                                });
                                $("#customDropDown" + (dropCount + 1) + " option:contains(" + $("#customDropDown" + (dropCount + 1) + " option[value=" + developerDatas.fpoItemDetOthDuty[dropCount].duty_CD + "]").text() + ")").attr('selected', 'selected');
                                $("#customDropDown" + (dropCount + 1) + "").attr("disabled", false); // changed for reupdate for AAA
                                $("#rateDuty" + (dropCount + 1) + "").val(developerDatas.fpoItemDetOthDuty[dropCount].duty_RTA);
                                $("#rateDuty" + (dropCount + 1) + "").attr("disabled", true); 
                                $("#effRate" + (dropCount + 1) + "").val(developerDatas.fpoItemDetOthDuty[dropCount].rate);
                                $("#effRate" + (dropCount + 1) + "").attr("disabled", true);
                                $("#dutyAmount" + (dropCount + 1) + "").val(developerDatas.fpoItemDetOthDuty[dropCount].duty_AMT);
                                $("#dutyAmount" + (dropCount + 1) + "").attr("disabled", false); // changed for reupdate for AAA
                                $("#dutyAmountFg" + (dropCount + 1) + "").val(developerDatas.fpoItemDetOthDuty[dropCount].duty_FG);
                                $("#dutyAmountFg" + (dropCount + 1) + "").attr("disabled", false); // changed for reupdate for AAA
                                totitmduty = parseFloat(totitmduty) + parseFloat(developerDatas.fpoItemDetOthDuty[dropCount].duty_AMT);
                                $('#totalOthersDuty').val(parseFloat(totitmduty).toFixed(2));
                                $("#notn" + (dropCount + 1)).append($('<option>', {
                                    text: developerDatas.fpoItemDetOthDuty[dropCount].duty_NOTN
                                }));
                                $("#notn" + (dropCount + 1) + " option:contains(" + developerDatas.fpoItemDetOthDuty[dropCount].duty_NOTN + ")").attr('selected', 'selected');
                                $("#notn" + (dropCount + 1) + "").attr("disabled", false); // changed for reupdate for AAA

                                $("#slno" + (dropCount + 1)).append($('<option>', {
                                    text: developerDatas.fpoItemDetOthDuty[dropCount].duty_SLNO
                                }));
                                $("#slno" + (dropCount + 1) + " option:contains(" + developerDatas.fpoItemDetOthDuty[dropCount].duty_SLNO + ")").attr('selected', 'selected');
                                $("#slno" + (dropCount + 1) + "").attr("disabled", false); // changed for reupdate for AAA

                                if (null != developerDatas && null != developerDatas['ass_DT'] && developerDatas.fpoItemDetOthDuty.length != 0 && new Date(developerDatas['ass_DT']) < new Date(developerDatas.fpoItemDetOthDuty[0].ass_DT)) {
                                    $("#1 option:contains(" + "Other than 9804" + ")").prop("selected", true);
                                    $('#1').attr("disabled", false); // changed for reupdate for AAA
                                    $(".blueClass").show();
                                    $(".yellowClass").hide();
                                } else {
                                    if (null != developerDatas && null == developerDatas['ass_DT']) {
                                        $("#1 option:contains(" + "Other than 9804" + ")").prop("selected", true);
                                        $('#1').attr("disabled", false); // changed for reupdate for AAA
                                        $(".blueClass").show();
                                        $(".yellowClass").hide();
                                    }
                                }

                                $('#getOtherDigit').append($('<option>', {
                                    text: developerDatas.fpoItemDetOthDuty[dropCount].cth
                                }));
                                $("#getOtherDigit option:contains(" + developerDatas.fpoItemDetOthDuty[dropCount].cth + ")").attr('selected', 'selected');
                                $('#getOtherDigit').attr("disabled", false); // changed for reupdate for AAA

                                $('#getOtherEightDigi').append($('<option>', {
                                    text: developerDatas.fpoItemDetOthDuty[dropCount].cth.substr(0, 4)
                                }));
                                $("#getOtherEightDigi option:contains(" + developerDatas.fpoItemDetOthDuty[dropCount].cth.substr(0, 4) + ")").attr('selected', 'selected');
                                $('#getOtherEightDigi').attr("disabled", false); // changed for reupdate for AAA

                                $('#getOtherFourDigi').append($('<option>', {
                                    text: developerDatas.fpoItemDetOthDuty[dropCount].cth.substr(0, 2)
                                }));
                                $("#getOtherFourDigi option:contains(" + developerDatas.fpoItemDetOthDuty[dropCount].cth.substr(0, 2) + ")").attr('selected', 'selected');
                                $('#getOtherFourDigi').attr("disabled", false); // changed for reupdate for AAA

                                dropCount = dropCount + 1;
                            },
                            fail: function(rs, e) {
                                console.log(rs, responseText);
                            }
                        });
                        custRow = custRow + 1;
                    } else if (developerDatas.fpoItemDetOthDuty[stRow].cth != "98049000" && developerDatas.fpoItemDetOthDuty[stRow].cth != "98041000") {

                        $('#manualdutyO').show();
                        var newRow = $("<tr>");
                        var cols = "";
                        cols += '<td><input type="text" id="enterDropDown' + entRow + '" class="form-control" name="DUTY[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="enterRateDuty' + entRow + '" class="form-control" name="RATE_OF_DUTY[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="enterNotn' + entRow + '" class="form-control" name="NOTN[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="enterSlno' + entRow + '" class="form-control" name="SL.NO[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="enterEffRate' + entRow + '" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="enterDutyAmount' + entRow + '" class="form-control" onblur="calTotDuty(2)"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="dutyFgAmt' + entRow + '" class="form-control" name="DUTY_FG[' + counter + ']" /></td>';
                        //cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
                        newRow.append(cols);
                        $("table.order-list6").append(newRow);

                        $('#enterDropDown' + entRow).val(developerDatas.fpoItemDetOthDuty[stRow].duty_DESC);
                        $('#enterDropDown' + entRow).attr("disabled", true);
                        $('#enterRateDuty' + entRow).val(developerDatas.fpoItemDetOthDuty[stRow].duty_RTA);
                        $('#enterRateDuty' + entRow).attr("disabled", true);
                        $('#enterNotn' + entRow).val(developerDatas.fpoItemDetOthDuty[stRow].duty_NOTN);
                        $('#enterNotn' + entRow).attr("disabled", true);
                        $('#enterSlno' + entRow).val(developerDatas.fpoItemDetOthDuty[stRow].duty_SLNO);
                        $('#enterSlno' + entRow).attr("disabled", true);
                        $('#enterEffRate' + entRow).val(developerDatas.fpoItemDetOthDuty[stRow].rate);
                        $('#enterEffRate' + entRow).attr("disabled", true);
                        $('#enterDutyAmount' + entRow).val(developerDatas.fpoItemDetOthDuty[stRow].duty_AMT);
                        totitmduty = parseFloat(totitmduty) + parseFloat(developerDatas.fpoItemDetOthDuty[stRow].duty_AMT);
                        $('#totalOthersDuty').val(parseFloat(totitmduty).toFixed(2));
                        $('#enterDutyAmount' + entRow).attr("disabled", true);
                        $('#dutyFgAmt' + entRow).val(developerDatas.fpoItemDetOthDuty[stRow].duty_FG);
                        $('#dutyFgAmt' + entRow).attr("disabled", true);
                        entRow = entRow + 1;
                    } else {
                        var newRow = $("<tr>");
                        var cols = "";
                        cols += '<td><input type="text" id="etr98DropDown' + ent98Row + '" class="form-control" name="DUTY[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="etr98RateDuty' + ent98Row + '" class="form-control" name="RATE_OF_DUTY[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="etr98Notn' + ent98Row + '" class="form-control" name="NOTN[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="etr98Slno' + ent98Row + '" class="form-control" name="SL.NO[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="etr98EffRate' + ent98Row + '" class="form-control" name="EFF.RATE[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="etr98DutyAmount' + ent98Row + '" class="form-control" onblur="calTotDuty(2)"  name="DUTY_AMOUNT[' + counter + ']" /></td>';
                        cols += '<td><input type="text" id="etr98dtFgAmt' + ent98Row + '" class="form-control" name="DUTY_FG[' + counter + ']" /></td>';
                        //cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
                        newRow.append(cols);
                        $("table.order-list7").append(newRow);

                        $('#etr98DropDown' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_DESC);
                        $('#etr98DropDown' + ent98Row).attr("disabled", true);
                        $('#etr98RateDuty' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_RTA);
                        $('#etr98RateDuty' + ent98Row).attr("disabled", true);
                        $('#etr98Notn' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_NOTN);
                        $('#etr98Notn' + ent98Row).attr("disabled", true);
                        $('#etr98Slno' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_SLNO);
                        $('#etr98Slno' + ent98Row).attr("disabled", true);
                        $('#etr98EffRate' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].rate);
                        $('#etr98EffRate' + ent98Row).attr("disabled", true);
                        $('#etr98DutyAmount' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_AMT);
                        totitmduty = parseFloat(totitmduty) + parseFloat(developerDatas.fpoItemDetOthDuty[stRow].duty_AMT);
                        $('#totalOthersDuty').val(parseFloat(totitmduty).toFixed(2));
                        $('#etr98DutyAmount' + ent98Row).attr("disabled", true);
                        $('#etr98dtFgAmt' + ent98Row).val(developerDatas.fpoItemDetOthDuty[stRow].duty_FG);
                        $('#etr98dtFgAmt' + ent98Row).attr("disabled", true);
                        ent98Row = ent98Row + 1;
                    }
                    stRow = stRow + 1;
                    //	$('#totalOthersDuty').val(parseFloat(totitmduty).toFixed(2));
                }



                //  $('#totalOthersDuty').val(parseFloat(totitmduty).toFixed(2));
                $(".blueClass").show();
                $(".yellowClass").hide();
                /*$("#itemBcdNoNtChange1").append($('<option>', {
                    text: developerDatas['bcd_NOTN']
                }));*/
                $("#itemBcdNoNtChange1 option:contains(" + developerDatas['bcd_NOTN'] + ")").prop('selected', 'selected');
                /*$("#itemBcdNsNoChange1").append($('<option>', {
                    text: developerDatas['bcd_NSNO']
                }));*/
                $("#itemBcdNsNoChange1 option:contains(" + developerDatas['bcd_NSNO'] + ")").prop('selected', 'selected');
                /*$("#itemIgstNoNtChange1").append($('<option>', {
                    text: developerDatas['igst_NOTN']
                }));*/
                $("#itemIgstNoNtChange1 option:contains(" + developerDatas['igst_NOTN'] + ")").prop('selected', 'selected');
                /*$("#itemIgstNsNoChange1").append($('<option>', {
                    text: developerDatas['igst_NSNO']
                }));*/
                $("#itemIgstNsNoChange1 option:contains(" + developerDatas['igst_NSNO'] + ")").prop('selected', 'selected');
                /*$("#itemSwNoNtChange1").append($('<option>', {
                    text: developerDatas['sw_NOTN']
                }));*/
                $("#itemSwNoNtChange1 option:contains(" + developerDatas['sw_NOTN'] + ")").prop('selected', 'selected');
                /*$("#itemSwNsNoChange1").append($('<option>', {
                    text: developerDatas['sw_NSNO']
                }));*/
                $("#itemSwNsNoChange1 option:contains(" + developerDatas['sw_NSNO'] + ")").prop('selected', 'selected');

             /*   $("#itemBcdNoNtChange1").attr("disabled", true);
                $("#itemBcdNsNoChange1").attr("disabled", true);
                $("#itemIgstNoNtChange1").attr("disabled", true);
                $("#itemIgstNsNoChange1").attr("disabled", true);
                $("#itemSwNoNtChange1").attr("disabled", true);
                $("#itemSwNsNoChange1").attr("disabled", true);
                $("#1").attr("disabled", true);*/
                // $("#query" + $('#itemNoChange').val()).attr("disabled", true);
                //$("#queryraisebtns").attr("disabled", true);
            //    $('.updateAss').attr("disabled", true);
                navi = 2;
             //   $('.changeAss').attr("disabled", false);
            }
            if ($("#allItemDuty").val() > $('#dutylim').val()) {
                $("#totaldutyPayable").val($("#allItemDuty").val());
            } else if ($("#cat").val() == $("#category").val())
                $("#totaldutyPayable").val($("#allItemDuty").val());
            else {
                $("#totaldutyPayable").val(0);
            }

            if (navi != 2) {
                if ($("#enterOthDuty tr").length > 0 || $("#tbOtrDt tr").length > 0) {
                    $("#1 option:contains(" + developerDatas['gen_CTH'] + ")").prop("selected", true);
                    $(".blueClass").hide();
                    $(".yellowClass").show();
                } else {
                    $("#1 option:contains(" + developerDatas['gen_CTH'] + ")").prop("selected", true);
                    $(".blueClass").hide();
                    $(".yellowClass").show();
                }
            }

		/*if (null != $(".itemqry").val() && !$(".itemqry").val() == "") {
		    $("#bothAssQry").attr("disabled", false);
		    ass_hide();
		} else {
		    $("#bothAssQry").attr("disabled", true);
		    ass_show();
		}*/
        },
        fail: function(rs, e) {
            alert("Error in Assessment");
        }
    });
    dropCount = 0;
}


/*if (parseFloat($("#allItemDuty").val()) > $('#dutylim').val()) {
    $("#dutyPayable").val($("#allItemDuty").val());
} else if ($("#cat") == $("#category"))
    $("#dutyPayable").val($("#allItemDuty").val());
else {
    $("#dutyPayable").val(0);
}*/

function pnextPageRedirect(cinNo) {

	var developerData = {};
	developerData['cinNo'] = cinNo;

	$.ajax({
		url: 'pnextPageRedirect',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			if (null != developerDatas['page'] && developerDatas['page'] != "noramlFlag") {
				window.location.href = "" + developerDatas['page'] + "?id=" + cinNo;
			}
		},
		fail: function(rs, e) {
			alert("Error in nextPageRedirect");
		}
	});

}



function ass_disable()
{
$("#itemBcdNoNtChange1").attr("disabled", true);
		$("#itemBcdNsNoChange1").attr("disabled", true);
		$("#itemIgstNoNtChange1").attr("disabled", true);
		$("#itemIgstNsNoChange1").attr("disabled", true);
		$("#itemSwNoNtChange2").attr("disabled", true);
		$("#itemSwNsNoChange1").attr("disabled", true);
		$('.updateAss').attr("disabled", true);  
		
		$("#1").attr("disabled", true);
		$('#calcShHd').hide();
}

function pnextPageRedirect(cinNo) {

	var developerData = {};
	developerData['cinNo'] = cinNo;

	$.ajax({
		url: 'pnextPageRedirect',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			if (null != developerDatas['page'] && developerDatas['page'] != "noramlFlag") {
				window.location.href = "" + developerDatas['page'] + "?id=" + cinNo;
			}
		},
		fail: function(rs, e) {
			alert("Error in nextPageRedirect");
		}
	});

}



function ass_enable()
{
	$("#itemBcdNoNtChange1").attr("disabled", false);
		$("#itemBcdNsNoChange1").attr("disabled", false);
		$("#itemIgstNoNtChange1").attr("disabled", false);
		$("#itemIgstNsNoChange1").attr("disabled", false);
		$("#itemSwNoNtChange2").attr("disabled", false);
		$("#itemSwNsNoChange1").attr("disabled", false);
		$("#1").attr("disabled", false);
		if (!($('#role').val()=='PAC') && ($('#role').val()=='PAO' && $('#acloffid')!= null) )
				{$('.updateAss').attr("disabled", false);
				$('.changeAss').attr("disabled", true);}
		
		$('#calcShHd').show();
}

$(document).ready(function() {
if( $("#query").val() == ""){
$("#bothAssQry").attr("disabled", true);
}
});

function ass_show()
{
$("#itemBcdNoNtChange1").attr("disabled", false);
$("#itemBcdNsNoChange1").attr("disabled", false);
$("#itemIgstNoNtChange1").attr("disabled", false);
$("#itemIgstNsNoChange1").attr("disabled", false);
$("#itemSwNoNtChange2").attr("disabled", false);
$("#itemSwNsNoChange1").attr("disabled", false);
$("#1").attr("disabled", false);
if (!($('#role').val()=='PAC') && ($('#role').val()=='PAO' && $('#acloffid')!= null) )
				{$('.updateAss').attr("disabled", false);
				$('.changeAss').attr("disabled", true);}
$('#calcShHd').show();
}

function ass_hide()
{
$("#itemBcdNoNtChange1").attr("disabled", true);
$("#itemBcdNsNoChange1").attr("disabled", true);
$("#itemIgstNoNtChange1").attr("disabled", true);
$("#itemIgstNsNoChange1").attr("disabled", true);
$("#itemSwNoNtChange2").attr("disabled", true);
$("#itemSwNsNoChange1").attr("disabled", true);
$('.updateAss').attr("disabled", true);
$("#1").attr("disabled", true);
$('#calcShHd').hide();
}

$("#bothAssQry").click(function() {

if ($(this).is(":checked")){					
if (null != $("#query").val() && !$("#query").val() == "") {

ass_show();
}
}else{
ass_hide();
}
}); 

function nextPageRedirect(cinNo) {

	var developerData = {};
	developerData['cinNo'] = cinNo;

	$.ajax({
		url: 'nextPageRedirect',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			if (null != developerDatas['page'] && developerDatas['page'] != "noramlFlag") {
				window.location.href = "" + developerDatas['page'] + "?id=" + cinNo;
			}
		},
		fail: function(rs, e) {
			alert("Error in nextPageRedirect");
		}
	});

}

var fpoItemDet = {};
fpoItemDet['cinNo'] = $('#inputPassword').val();
$.ajax({
	url: 'nextPageRedirect',
	data: JSON.stringify(fpoItemDet),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(developerDatas) {
		if (null == developerDatas['page'] && developerDatas['page'] != "ead_query" && developerDatas['page'] != "ead_item" && developerDatas['page'] != "order")
			$('#nextPageButton').attr("disabled", true);
		else
          { 
			if (developerDatas['couupdass_dt'] > 1)
			    $('#nextPageButton').attr("disabled", true);
			else 
			    $('#nextPageButton').attr("disabled", false);
			}
	},
	fail: function(rs, e) {
		alert("Error in nextPageRedirect");
	}
});

function getOtherFourDigit(e) {
	clearDropDown($("#getOtherEightDigi"));
	var fpoItemDet = {};
	fpoItemDet['cth'] = $('#getOtherFourDigi').val();
	$.ajax({
		url: 'getOtherFourDigi',
		data: JSON.stringify(fpoItemDet),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#getOtherEightDigi").append($('<option>', {
				text: "Select Value"
			}));
			$.each(data, function(i, item) {
				if (item != "9804") 
				{
					$('#getOtherEightDigi').append($('<option>', {
						text: item
					}));
				}
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function getOtherEightDigit(e) {
	clearDropDown($("#getOtherDigit"));
	var fpoItemDet = {};
	fpoItemDet['cth'] = $('#getOtherEightDigi').val();
	$.ajax({
		url: 'getOtherEightDigi',
		data: JSON.stringify(fpoItemDet),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#getOtherDigit").append($('<option>', {
				text: "Select Value"
			}));
			$.each(data, function(i, item) {
				$('#getOtherDigit').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function getOtherFourDigitAmend(e) {
	clearDropDown($("#getOtherEightDigi"));
	var fpoItemDet = {};
	fpoItemDet['cth'] = $('#getOtherFourDigitAmend').val();
	$.ajax({
		url: 'getOtherFourDigi',
		data: JSON.stringify(fpoItemDet),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#getOtherEightDigi").append($('<option>', {
				text: "Select Value"
			}));
			$.each(data, function(i, item) {
				$('#getOtherEightDigi').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function getOtherEightDigitAmend(e) {
	clearDropDown($("#getOtherDigit"));
	var fpoItemDet = {};
	fpoItemDet['cth'] = $('#getOtherEightDigi').val();
	$.ajax({
		url: 'getOtherEightDigi',
		data: JSON.stringify(fpoItemDet),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#getOtherDigit").append($('<option>', {
				text: "Select Value"
			}));
			$.each(data, function(i, item) {
				$('#getOtherDigit').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function changeExRt() {

	$("#myModal").modal('hide');
	if ($('#itemRateChange1').val() == "0.0") {
		var fpoItemDet = {};
		fpoItemDet['currcd'] = $('#itemCurCdChange').val();
		$.ajax({
			url: 'getCountryName',
			data: JSON.stringify(fpoItemDet),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(data) {
				$('#countryName').val(data[0]);
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});

		$("#exchangeRate").modal('show');
		$("#confirmBtton").attr("disabled", true);
	}
}

function updateExRt() {

	var fpoItemDet = {};
	fpoItemDet['cinNo'] = $('#inputPassword').val();
	fpoItemDet['currcd'] = $('#itemCurCdChange').val();
	fpoItemDet['rate'] = $('#upExRt').val();
	fpoItemDet['decl_VAL'] = $('#itemDeclValChange1').val();
	fpoItemDet['calcFlag'] = "E";
	$.ajax({
		url: 'updateExchangeRate',
		data: JSON.stringify(fpoItemDet),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerData) {
			$("#exchangeRate").modal('hide');
			$("#itemSwAmtChange1").val(developerData['sw_AMT']);
			$("#itemIgstAmtChange1").val(developerData['igst_AMT']);
			$("#itemBcdAmtChange1").val(developerData['bcd_AMT']);

			$("#itemSwAmtFgChange1").val(developerData['sw_AMT_FG']);
			$("#itemIgstAmtFgChange1").val(developerData['igst_AMT_FG']);
			$("#itemBcdAmtFgChange1").val(developerData['bcd_AMT_FG']);
			$("#itemDutyFgChange1").val(developerData['duty_FG']);
			$("#itemDeclValChange1").val(developerData['decl_VAL']);
			$("#itemRateChange1").val(developerData['rate']);
			$("#itemDutyChange1").val(developerData['duty']);
			$("#assessVal").val(developerData['assess_VAL']);
			$("#allItemDuty").val(developerData['allItemDuty']);
			$("#allItemDutyFg").val(developerData['allItemDutyFg']);
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});

}

function validate() {

	if ($('#upExRt').val() == "") {
		$('#dinErr').text("Empty Rate");
		$("#confirmBtton").attr("disabled", true);
	}
	else if ($('#upExRt').val() <= 0) {
		$('#dinErr').text("Rate should be greater than zero");
		$("#confirmBtton").attr("disabled", true);
	} else {
		$("#confirmBtton").attr("disabled", false);
		$('#dinErr').text("");
	}
}

$('#upExRt').keypress(function(e) {
	if (this.value.length == 0 && e.which == 48) {
		return false;
	}
});

if (null != $('#itemDutyFgChange1').val() && $('#itemDutyFgChange1').val() > 0 && $('#bothAssQry').val() == 'N') {
	$('#qryShHd').hide();
	$("#bothAssQry").attr("disabled", true);
} else {
	$('#qryShHd').show();
	$("#bothAssQry").attr("disabled", false);
}

if (null != $('#query').text() && $('#bothAssQry').val() == 'N') {

	$('#calcShHd').hide();
	$("#query").attr("disabled", true);
	$(".updateAss").attr("disabled", true);
	$("#changeButton").attr("disabled", false);
	$("#bothAssQry").attr("disabled", true);
} else {
	$('#calcShHd').show();
	$("#bothAssQry").attr("false", true);
}

function bcdOthersNotn(e) {

	$("#customDropDown1").attr("disabled", false);
	$("#notn1").attr("disabled", false);
	$("#slno1").attr("disabled", false);
	$("#dutyAmount1").attr("disabled", false);
	$("#dutyAmountFg1").attr("disabled", false);
	$("#addrow1").attr("disabled", false);

	//enterduty
	$("#enterDropDown1").attr("disabled", false);
	$("#enterRateDuty1").attr("disabled", false);
	$("#enterNotn1").attr("disabled", false);
	$("#enterSlno1").attr("disabled", false);
	$("#enterEffRate1").attr("disabled", false);
	$("#enterDutyAmount1").attr("disabled", false);
	$("#dutyFgAmt1").attr("disabled", false);
	$("#addrow6").attr("disabled", false);

	var cth = $('#getOtherDigit').val();
	var duty;
	var count = 0;
	var k = 1;

	$("#tbOtrDt").find("tr").each(function() {
		count = count + 1;
		if (null != $(this).find("td").eq(0).find("option:selected").val() && "" != $(this).find("td").eq(0).find("option:selected").val()) {
			duty = $(this).find("td").eq(0).find("select[name*='DUTY']").val();
			if (null != duty && null != cth && duty != "Select Duty" && cth != "Select Value") {

				var fpoItemDet = {};
				fpoItemDet['cinNo'] = $('#inputPassword').val();
				fpoItemDet['cth'] = $('#getOtherDigit').val();
				fpoItemDet['dutyCdOthers'] = duty;

				$.ajax({
					url: 'getDutyOthersDet',
					data: JSON.stringify(fpoItemDet),
					dataType: "json",
					contentType: "application/json",
					type: "post",
					success: function(fpoItemDets) {
						//	while (k <= count) {
						$('#rateDuty' + k).val(fpoItemDets['dutyRateOthers']);
						clearDropDown($("#notn" + k));
						$("#notn" + k).append($('<option>', {
							text: "Select NSNO"
						}));
						$.each(fpoItemDets['listObjects'], function(i, item) {
							$('#notn' + k).append($('<option>', {
								text: item
							}));
						});
						k = k + 1;
						//	}
					},
					fail: function(rs, e) {
						console.log(rs, responseText);
					}
				});
			}
		}

	});

}

function othersNotn(e) {
	var idVal = e.id;
	var idNor = idVal.substring(idVal.length - 1);
	$("#" + idVal + "").attr("disabled", false);
	$("#notn1").attr("disabled", false);
	$("#slno1").attr("disabled", false);
	$("#dutyAmount1").attr("disabled", false);
	$("#dutyAmountFg1").attr("disabled", false);
	$("#addrow1").attr("disabled", false);
	var duty = $('#customDropDown' + idNor).val();
	var cth = $('#getOtherDigit').val();

	if (null != duty && null != cth && duty != "Select Duty" && cth != "Select Value") {

		var fpoItemDet = {};
		fpoItemDet['cinNo'] = $('#inputPassword').val();
		fpoItemDet['cth'] = cth;
		fpoItemDet['dutyCdOthers'] = duty;

		var resObj = $.ajax({
			url: 'getDutyOthersDet',
			data: JSON.stringify(fpoItemDet),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(fpoItemDets) {
				$('#rateDuty' + idNor).val(fpoItemDets['dutyRateOthers']);
				clearDropDown($("#notn" + idNor));
				$("#notn" + idNor).append($('<option>', {
					text: "Select NSNO"
				}));
				$.each(fpoItemDets['listObjects'], function(i, item) {
					$('#notn' + idNor).append($('<option>', {
						text: item
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
	}
}

function othersNotnAmd(e) {
	var idVal = e.id;
	var idNor = idVal.substring(idVal.length - 1);
	$("#" + idVal + "").attr("disabled", false);
	$("#cusAmNt").attr("disabled", false);
	$("#cusAmSln1").attr("disabled", false);
	$("#cusAmAmt1").attr("disabled", false);
	$("#cusAmAmtFg1").attr("disabled", false);
	$("#addrow").attr("disabled", false);
	var duty = $('#cusAmDrpDn' + idNor).val();
	var cth = $('#getOtherDigitAmend').val();

	if (null != duty && null != cth && duty != "Select Duty" && cth != "Select Value") {

		var fpoItemDet = {};
		fpoItemDet['cinNo'] = $('#inputPassword').val();
		fpoItemDet['cth'] = cth;
		fpoItemDet['dutyCdOthers'] = duty;

		var resObj = $.ajax({
			url: 'getDutyOthersDet',
			data: JSON.stringify(fpoItemDet),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(fpoItemDets) {
				$('#cusAmDtRt' + idNor).val(fpoItemDets['dutyRateOthers']);
				clearDropDown($("#notn" + idNor));
				$("#cusAmNt" + idNor).append($('<option>', {
					text: "Select NSTN"
				}));
				$.each(fpoItemDets['listObjects'], function(i, item) {
					$('#cusAmNt' + idNor).append($('<option>', {
						text: item
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
	}
}

function othersSlNo(e) {
	var idVal = e.id;
	var idNor = idVal.substring(idVal.length - 1);
	var duty = $("#customDropDown" + idNor).val();
	var cth = $('#getOtherDigit').val();

	if (null != duty && null != cth && duty != "Select Duty" && cth != "Select Value") {

		var fpoItemDet = {};
		fpoItemDet['cinNo'] = $('#inputPassword').val();
		fpoItemDet['cth'] = $('#getOtherDigit').val();
		fpoItemDet['dutyCdOthers'] = $("#customDropDown" + idNor).val();
		fpoItemDet['bcd_NOTN'] = $('#' + idVal).val();

		var resObj = $.ajax({
			url: 'getDutyOthersSlNo',
			data: JSON.stringify(fpoItemDet),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(fpoItemDets) {
				clearDropDown($("#slno" + idNor));
				$("#slno" + idNor).append($('<option>', {
					text: "Select NSNO"
				}));
				$.each(fpoItemDets['listObjects'], function(i, item) {
					$('#slno' + idNor).append($('<option>', {
						text: item
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
	}
}

function othersSlNoAmd(e) {
	var idVal = e.id;
	var idNor = idVal.substring(idVal.length - 1);
	var duty = $("#cusAmDrpDn" + idNor).val();
	var cth = $('#getOtherDigitAmend').val();

	if (null != duty && null != cth && duty != "Select Duty" && cth != "Select Value") {

		var fpoItemDet = {};
		fpoItemDet['cinNo'] = $('#inputPassword').val();
		fpoItemDet['cth'] = $('#getOtherDigitAmend').val();
		fpoItemDet['dutyCdOthers'] = $("#cusAmDrpDn" + idNor).val();
		fpoItemDet['bcd_NOTN'] = $('#' + idVal).val();

		var resObj = $.ajax({
			url: 'getDutyOthersSlNo',
			data: JSON.stringify(fpoItemDet),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(fpoItemDets) {
				clearDropDown($("#cusAmSln" + idNor));
				$("#cusAmSln" + idNor).append($('<option>', {
					text: "Select NSNO"
				}));
				$.each(fpoItemDets['listObjects'], function(i, item) {
					$('#cusAmSln' + idNor).append($('<option>', {
						text: item
					}));
				});
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			}
		});
	}
}

function getOtherFourDigiAmend(e) {
	clearDropDown($("#getOtherEightDigitAmend"));
	var fpoItemDet = {};
	fpoItemDet['cth'] = $('#getOtherFourDigitAmend').val();
	$.ajax({
		url: 'getOtherFourDigi',
		data: JSON.stringify(fpoItemDet),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#getOtherEightDigitAmend").append($('<option>', {
				text: "Select Value"
			}));
			$.each(data, function(i, item) {
				if (item != "9804") {
					$('#getOtherEightDigitAmend').append($('<option>', {
						text: item
					}));
				}
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function getOtherEightDigiAmend(e) {
	clearDropDown($("#getOtherDigitAmend"));
	var fpoItemDet = {};
	fpoItemDet['cth'] = $('#getOtherEightDigitAmend').val();
	$.ajax({
		url: 'getOtherEightDigi',
		data: JSON.stringify(fpoItemDet),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
			$("#getOtherDigitAmend").append($('<option>', {
				text: "Select Value"
			}));
			$.each(data, function(i, item) {
				$('#getOtherDigitAmend').append($('<option>', {
					text: item
				}));
			});
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function selectEachNsNo(e) {
	var idVal = e.id;
	var idNor = idVal.substring(idVal.length - 1);
	var fpoItemDet = {};
	fpoItemDet['cinNo'] = $('#inputPassword').val();
	fpoItemDet['cth'] = $('#getOtherDigit').val();
	fpoItemDet['bcd_NSNO'] = $('#slno' + idNor).val();
	fpoItemDet['dutyCdOthers'] = $("#customDropDown" + idNor).val();
	var resObj = $.ajax({
		url: 'getEachRate',
		data: JSON.stringify(fpoItemDet),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(fpoItemDets) {
			$('#effRate' + idNor).val(fpoItemDets['bcd_RTA']);
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}

function selectEachNsNoAmd(e) {
	var idVal = e.id;
	var idNor = idVal.substring(idVal.length - 1);
	var fpoItemDet = {};
	fpoItemDet['cinNo'] = $('#inputPassword').val();
	fpoItemDet['cth'] = $('#getOtherDigitAmend').val();
	fpoItemDet['bcd_NSNO'] = $('#cusAmSln' + idNor).val();
	fpoItemDet['dutyCdOthers'] = $("#cusAmDrpDn" + idNor).val();
	var resObj = $.ajax({
		url: 'getEachRate',
		data: JSON.stringify(fpoItemDet),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(fpoItemDets) {
			$('#cusAmRt' + idNor).val(fpoItemDets['bcd_RTA']);
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
}


$('#totalOthersDuty').val($('#itemDutyChange1').val());
$('#totalOthersDutyFg').val($('#itemDutyFgChange1').val());
var fpoItemDetOthDuty = {};
fpoItemDetOthDuty['cin_NO'] = $('#inputPassword').val();
fpoItemDetOthDuty['item_NO'] = 1;
var enterCount = 0;
var custCount = 1;
var selCount = 1;
$.ajax({
	url: 'getOthrOnCinNorItem',
	data: JSON.stringify(fpoItemDetOthDuty),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(fpoItemDetOthDutyList) {

		if (null != fpoItemDetOthDutyList && fpoItemDetOthDutyList != "") {
			$("#1 option:contains(" + "Others" + ")").attr('selected', 'selected');			  

			$('#getOtherDigit').append($('<option>', {
				text: fpoItemDetOthDutyList[0].cth
			}));
			$("#getOtherDigit option:contains(" + fpoItemDetOthDutyList[0].cth + ")").attr('selected', 'selected');
			   

			$('#getOtherEightDigi').append($('<option>', {
				text: fpoItemDetOthDutyList[0].cth.substr(0, 4)
			}));
			$("#getOtherEightDigi option:contains(" + fpoItemDetOthDutyList[0].cth.substr(0, 4) + ")").attr('selected', 'selected');
			

			$('#getOtherFourDigi').append($('<option>', {
				text: fpoItemDetOthDutyList[0].cth.substr(0, 2)
			}));
			$("#getOtherFourDigi option:contains(" + fpoItemDetOthDutyList[0].cth.substr(0, 2) + ")").attr('selected', 'selected');
            
            if (!(parseFloat($('#acfirst').val())==1 && $('#role').val()=='PAC')) {
            $('#1').attr("disabled", false);  // changed for reupdate in AAA
            $('#getOtherDigit').attr("disabled", false); // changed for reupdate in AAA
			$('#getOtherEightDigi').attr("disabled", false); // changed for reupdate in AAA
			$('#getOtherFourDigi').attr("disabled", false); // changed for reupdate in AAA
			$("#enterOthDuty tr").remove();
			// $("#tbOtrDt tr").remove(); // changed for reupdate in AAA
			} 
			$.each(fpoItemDetOthDutyList, function(i, item) {
				if (null != item.duty_CD && item.duty_CD != ""  && !(item.duty_CD.toString()=='Select')) {
					fpoItemDetOthDuty['duty_CD'] = item.duty_CD;
					var newRow = $("<tr>");
					var cols = "";
					cols += '<td><select type="text" id="customDropDown' + custCount + '" onchange="othersNotn(this)" class="custom-select" name="DUTY[' + custCount + ']" ><option>Select Duty</option></select></td>';
					cols += '<td><input type="text" id="rateDuty' + custCount + '" class="form-control" readonly="readonly" name="RATE_OF_DUTY[' + custCount + ']" /></td>';
					cols += '<td><select type="text" id="notn' + custCount + '" onchange="othersSlNo(this)" class="custom-select" name="NOTN[' + custCount + ']" ><option>Select NOTN</option></select></td>';
					cols += '<td><select type="text" id="slno' + custCount + '" onchange="selectEachNsNo(this)" class="custom-select" name="SL.NO[' + custCount + ']" ><option>Select SL. No</option></select></td>';
					cols += '<td><input type="text"  id="effRate' + custCount + '" class="form-control" readonly="readonly" name="EFF.RATE[' + custCount + ']" /></td>';
					cols += '<td><input type="text"  id="dutyAmount' + custCount + '" onblur="calTotDuty(2)" class="form-control" style="text-align: right;" name="DUTY_AMOUNT[' + custCount + ']" /></td>';
					cols += '<td><input type="text"  id="dutyAmountFg' + custCount + '" onblur="calTotDutyFg()" class="form-control" style="text-align: right;" name="DUTY_FG[' + custCount + ']" /></td>';
					cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + (enterCount + 1) + ']" value=" - "></td>'; // changed for reupdate in AAA
					// newRow.append(cols);
					// $("table.order-list1").append(newRow);

					$.ajax({
						url: 'getDUTYOnCd',
						data: JSON.stringify(fpoItemDetOthDuty),
						dataType: "json",
						contentType: "application/json",
						type: "post",
						success: function(fpoItemDetOthDutys) {
							$('#customDropDown' + selCount + '').append($('<option>', {
								text: fpoItemDetOthDutys['cdDesc']
							}));
							$("#customDropDown" + selCount + " option:contains(" + fpoItemDetOthDutys['cdDesc'] + ")").attr('selected', 'selected');
							$("#customDropDown" + selCount).attr("disabled", false); // changed for reupdate in AAA
							selCount = selCount + 1;
						},
						fail: function(rs, e) {
							console.log(rs, responseText);
						}
					});

					$('#notn' + custCount).append($('<option>', {
						text: item.duty_NOTN
					}));
					$("#notn" + custCount + " option:contains(" + item.duty_NOTN + ")").attr('selected', 'selected');

					$('#slno' + custCount).append($('<option>', {
						text: item.duty_SLNO
					}));
					$("#slno" + custCount + " option:contains(" + item.duty_SLNO + ")").attr('selected', 'selected');

					$("#rateDuty" + custCount).val(item.duty_RTA);
					$("#effRate" + custCount).val(item.rate);
					$("#dutyAmount" + custCount).val(item.duty_AMT);
					$("#dutyAmountFg" + custCount).val(item.duty_FG);
                    
                   if (!(parseFloat($('#acfirst').val())==1 && $('#role').val()=='PAC')){
                    $("#notn" + custCount).attr("disabled", false); // changed for reupdate in AAA
                    $("#slno" + custCount).attr("disabled", false); // changed for reupdate in AAA
					$("#1").attr("disabled", false); // changed for reupdate in AAA
					$("#dutyAmount" + custCount).attr("disabled", false); // changed for reupdate in AAA
					$("#dutyAmountFg" + custCount).attr("disabled", false); // changed for reupdate in AAA

					$('#bothAssQry').attr("disabled", true);
					$('#addrow1').attr("disabled", false); // changed for reupdate in AAA
					
					
			    	$('.updateAss').attr("disabled", false); // changed for reupdate in AAA
					$('#changeButton').attr("disabled", false);

					$(".blueClass").show();
					$(".yellowClass").hide();
					$('#qryShHd').hide();}
					custCount = custCount + 1;
				} else {

					var newRow = $("<tr>");
					var cols = "";
					cols += '<td><input type="text" id="enterDropDown' + (enterCount + 1) + '" class="form-control" name="DUTY[' + (enterCount + 1) + ']" /></td>';
					cols += '<td><input type="text" id="enterRateDuty' + (enterCount + 1) + '" class="form-control" name="RATE_OF_DUTY[' + (enterCount + 1) + ']" /></td>';
					cols += '<td><input type="text" id="enterNotn' + (enterCount + 1) + '" class="form-control" name="NOTN[' + (enterCount + 1) + ']" /></td>';
					cols += '<td><input type="text" id="enterSlno' + (enterCount + 1) + '" class="form-control" name="SL.NO[' + (enterCount + 1) + ']" /></td>';
					cols += '<td><input type="text" id="enterEffRate' + (enterCount + 1) + '" class="form-control" name="EFF.RATE[' + (enterCount + 1) + ']" /></td>';
					cols += '<td><input type="text" id="enterDutyAmount' + (enterCount + 1) + '" class="form-control" onblur="calTotDuty(2)"  name="DUTY_AMOUNT[' + (enterCount + 1) + ']" /></td>';
					cols += '<td><input type="text" id="dutyFgAmt' + (enterCount + 1) + '" class="form-control" name="DUTY_FG[' + (enterCount + 1) + ']" /></td>';
					//cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + (enterCount + 1) + ']" value=" - "></td>';
					newRow.append(cols);
					$("table.order-list6").append(newRow);

					/*$('#enterDropDown'+entRow).val(fpoItemDetOthDutyList[stRow].duty_DESC);
					$('#enterDropDown'+entRow).attr("disabled", true);
					$('#enterRateDuty'+entRow).val(fpoItemDetOthDutyList[stRow].duty_RTA);
					$('#enterRateDuty'+entRow).attr("disabled", true);
					$('#enterNotn'+entRow).val(fpoItemDetOthDutyList[stRow].duty_NOTN);
					$('#enterNotn'+entRow).attr("disabled", true);
					$('#enterSlno'+entRow).val(fpoItemDetOthDutyList[stRow].duty_SLNO);
					$('#enterSlno'+entRow).attr("disabled", true);
					$('#enterEffRate'+entRow).val(fpoItemDetOthDutyList[stRow].rate);
					$('#enterEffRate'+entRow).attr("disabled", true);
					$('#enterDutyAmount'+entRow).val(fpoItemDetOthDutyList[stRow].duty_AMT);
					$('#enterDutyAmount'+entRow).attr("disabled", true);
					$('#dutyFgAmt'+entRow).val(fpoItemDetOthDutyList[stRow].duty_FG);
					$('#dutyFgAmt'+entRow).attr("disabled", true);
					entRow = entRow +1;		*/



					$("#enterDropDown" + (enterCount + 1)).val(item.duty_DESC);
					$("#enterRateDuty" + (enterCount + 1)).val(item.duty_RTA);
					$("#enterNotn" + (enterCount + 1)).val(item.duty_NOTN);
					$("#enterSlno" + (enterCount + 1)).val(item.duty_SLNO);
					$("#enterEffRate" + (enterCount + 1)).val(item.rate);
					$("#enterDutyAmount" + (enterCount + 1)).val(item.duty_AMT);
					$("#dutyFgAmt" + (enterCount + 1)).val(item.duty_FG);

					$("#enterDropDown" + (enterCount + 1)).attr("disabled", true);
					$("#enterRateDuty" + (enterCount + 1)).attr("disabled", true);
					$("#enterNotn" + (enterCount + 1)).attr("disabled", true);
					$("#enterSlno" + (enterCount + 1)).attr("disabled", true);
					$("#enterEffRate" + (enterCount + 1)).attr("disabled", true);
					$("#enterDutyAmount" + (enterCount + 1)).attr("disabled", true);
					$("#dutyFgAmt" + (enterCount + 1)).attr("disabled", true);
					$("#addrow6").attr("disabled", true);
					enterCount = enterCount + 1;
				}

			});
		}
	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});

var insertCount = 1;
$.ajax({
	url: 'getOthrOnCinNor',
	data: JSON.stringify(fpoItemDetOthDuty),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(fpoItemDetOthDutyList) {

		if (null != fpoItemDetOthDutyList && fpoItemDetOthDutyList != "") {

			/*$("#1 option:contains(" + "Others" + ")").attr('selected', 'selected');
			$('#1').attr("disabled", true);*/
			$("#etr98DutyAss tr").remove();
			$.each(fpoItemDetOthDutyList, function(i, item) {

				var newRow = $("<tr>");
				var cols = "";
				cols += '<td><input type="text" id="etr98DropDown' + insertCount + '" class="form-control" name="DUTY[' + insertCount + ']" /></td>';
				cols += '<td><input type="text" id="etr98RateDuty' + insertCount + '" class="form-control" name="RATE_OF_DUTY[' + insertCount + ']" /></td>';
				cols += '<td><input type="text" id="etr98Notn' + insertCount + '" class="form-control" name="NOTN[' + insertCount + ']" /></td>';
				cols += '<td><input type="text" id="etr98Slno' + insertCount + '" class="form-control" name="SL.NO[' + insertCount + ']" /></td>';
				cols += '<td><input type="text" id="etr98EffRate' + insertCount + '" class="form-control" name="EFF.RATE[' + insertCount + ']" /></td>';
				cols += '<td><input type="text" id="etr98DutyAmount' + insertCount + '" class="form-control" onblur="calTotDuty(2)"  name="DUTY_AMOUNT[' + insertCount + ']" /></td>';
				cols += '<td><input type="text" id="etr98dtFgAmt' + insertCount + '" class="form-control" name="DUTY_FG[' + insertCount + ']" /></td>';
				//cols += '<td><input type="button" class="ibtnDel btn btn-sm btn-danger " name="DELETE[' + counter + ']" value=" - "></td>';
				newRow.append(cols);
				$("table.order-list7").append(newRow);

				$("#etr98DropDown" + (insertCount)).val(item.duty_DESC);
				$("#etr98RateDuty" + (insertCount)).val(item.duty_RTA);
				$("#etr98Notn" + (insertCount)).val(item.duty_NOTN);
				$("#etr98Slno" + (insertCount)).val(item.duty_SLNO);
				$("#etr98EffRate" + (insertCount)).val(item.rate);
				$("#etr98DutyAmount" + (insertCount)).val(item.duty_AMT);
				$("#etr98dtFgAmt" + (insertCount)).val(item.duty_FG);

				$("#etr98DropDown" + (insertCount)).attr("disabled", true);
				$("#etr98RateDuty" + (insertCount)).attr("disabled", true);
				$("#etr98Notn" + (insertCount)).attr("disabled", true);
				$("#etr98Slno" + (insertCount)).attr("disabled", true);
				$("#etr98EffRate" + (insertCount)).attr("disabled", true);
				$("#etr98DutyAmount" + (insertCount)).attr("disabled", true);
				$("#etr98dtFgAmt" + (insertCount)).attr("disabled", true);
				$("#addrow7").attr("disabled", true);
				insertCount = insertCount + 1;

			});
		}
	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});


//New code 
$(document).ready(function() {
	$("#firstBtn").attr("disabled", false);
	$("#firstBtn").hide(); 
     if (($('#role').val() == 'PAO' && $('#acloffid').val()!= '') && (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) ))
	    $("#firstBtn").show();
	else
	    $("#firstBtn").hide();   
    var totAllItem = $('#allItemDuty').val();
    var totass=$('#totassval').val();
   
    if (parseFloat(totass) > $('#dutylim').val())
        $('#totaldutyPayable').val((parseFloat(totAllItem)).toFixed(2));
    else if ($("#cat").val() == $("#category").val())
        $('#totaldutyPayable').val((parseFloat(totAllItem)).toFixed(2));
    else {
        $('#totaldutyPayable').val((parseFloat($('#totFin').val() != undefined ? $('#totFin').val() : 0) + parseFloat($('#totPen').val() != undefined ? $('#totPen').val() : 0)).toFixed(2));
    }

});
//


function calTotPayable() {
    var totAllItem = $('#allItemDuty').val();
    var totass=$('#totassval').val();
    var totFin;
    var totPen;
    if ($('#totalFine').val() == "") {
        totFin = 0;
    } else {
        totFin = $('#totalFine').val();
    }

    if ($('#totalPenalty').val() == "") {
        totPen = 0;
    } else {
        totPen = $('#totalPenalty').val();
    }

    if (parseFloat(totass) > $('#dutylim').val())
        $('#totaldutyPayable').val((parseFloat(totAllItem) + parseFloat(totFin) + parseFloat(totPen)).toFixed(2));
    else if ($("#cat").val() == $("#category").val())
        $('#totaldutyPayable').val((parseFloat(totAllItem) + parseFloat(totFin) + parseFloat(totPen)).toFixed(2));
    else
        $('#totaldutyPayable').val((parseFloat(totFin) + parseFloat(totPen)).toFixed(2));
     if ($("#cat").val() == $("#category").val())
    {
	 if ($('#totaldutyPayable').val() <= $('#catlimit').val())
        	$('#totaldutyPayable').val(0);
    }
}


if (parseFloat($('#totalFine').val()) > 0) {
	$("#fineId").find("tr").each(function() {
		$(this).find("td").eq(0).find("input[name*='FINE']").attr("disabled", true);
		$(this).find("td").eq(1).find("input[name*='FINE_US']").attr("disabled", true);
		$(this).find("td").eq(2).find("input[name*='addFinebtn']").attr("disabled", true);
	});

	$("#deptComts").attr("disabled", true);
	$("#saveBtn").attr("disabled", true);
	$("#addrow2").attr("disabled", true);

} else {

	$(".amendBtn").attr("disabled", true);
}

if (parseFloat($('#totalPenalty').val()) > 0) {
	$("#penaltyId").find("tr").each(function() {
		$(this).find("td").eq(0).find("input[name*='PENALTY']").attr("disabled", true);
		$(this).find("td").eq(1).find("input[name*='PENALTY_US']").attr("disabled", true);
		$(this).find("td").eq(2).find("input[name*='addPenaltybtn']").attr("disabled", true);
	});

	$("#deptComts").attr("disabled", true);
	$("#saveBtn").attr("disabled", true);
	$("#addrow3").attr("disabled", true);

}

var glFlag = "D";
function setFlagReq(flag) {
	glFlag = flag;
}

var glFlagQry = "D";
function setFlagReqQry(flag) {
	glFlagQry = flag;
}

var glFlagNinEght = "D";
function setFlagReqNinEght(flag) {
	glFlagNinEght = flag;
}

var fpoItemDetOthDuty = {};
fpoItemDet['cin_NO'] = $('#inputPassword').val();
fpoItemDet['item_NO'] = 1;
$.ajax({
	url: 'getDeclaredStatus',
	data: JSON.stringify(fpoItemDet),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(fpoItemResp) {
		var flag = fpoItemResp['flagRequest'];
		if (flag == "O") {
			$(".blueClass").show();
			$(".yellowClass").hide();
			$('#qryShHd').hide();
			$("#1 option:contains(" + "Others" + ")").prop("selected", true);
			$('#1').attr("disabled", true);
		} else if (flag == "N") {
			$(".blueClass").hide();
			$(".yellowClass").show();
			$('#qryShHd').hide();
			$("#1 option:contains(" + ninCth + ")").prop("selected", true);
			$('#1').attr("disabled", true);
		} else if (flag == "Q") {
		//	$("#calcShHd").hide();
	
		    $("#calcShHd").show();
			$('#qryShHd').show();
			$("#bothAssQry").attr("disabled", true);
		} else if (flag == "Y") {
			$("#calcShHd").show();
			$('#qryShHd').show();
			$("#bothAssQry").attr("disabled", true);

		}
	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});
function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}
