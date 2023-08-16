function goBack() {
	window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
}

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

/*function listView() {
	window.location.href = "ead_list";
}*/

function listView() {
	if($('#role1').val() == 'PAC'){
		movEXM();
	}
	window.location.href = "process_ead";
}

/*$(document).ready(function() {
	if ($('#totDuty').val() > 500000 && ("PAO" == $('#role').val() || "" == $('#role').val())) {
		$('#msgAprWarning').text("Do you want to submit First Check Exam. Order?  As the value demands AC/DC's permission, it will move to AC/DC.");
	} else {
		$('#msgAprWarning').text("Do you want to submit First Check Exam. Order?");
	}
});*/


/*$(document).ready(function() {
	if ($('#totDuty').val() > 500000 && "PAC" == $('#role').val()) {
		$("#PACWarningMsgOrder").modal('show');
		$("#aclMsg").show();
		$("#aclMsg1").show();
		$("#firstBtn").hide();
	}
});
*/

/*$(document).ready(function() {
	$(".ordersave").attr("disabled", true);  
	if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role1').val() || "" == $('#role1').val())) 
	
	   { $('.ordersave').attr('disabled',true);
		$('.sendac').attr('disabled',false);$('sendac').show();}
    else
       { $('.sendac').attr('disabled',true);$('.sendac').hide();
         
}
});*/

//by santhosh altered


$(document).ready(function() {
	$(".ordersave").attr("disabled", true);  
	if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role1').val() || "" == $('#role1').val())) 
	
	   { 
		$('.ordersave').attr('disabled',true);
		$('.sendac').attr('disabled',false);$('sendac').show();
		}
    
else if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAC" == $('#role1').val())) 
{
		$('.ordersave').attr('disabled',true);
		$('.nxtooc').show();
		$('.sendac').attr('disabled',true);
		$('.sendac').hide();	
}

else
{ 
	$('.sendac').attr('disabled',true);
	$('.sendac').hide();
	$('.nxtooc').hide();  
}
});


//by santhosh added for ooc moving


function movOOC()
{
  $("#nxtModals").modal('show') ;
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




/*$("#lock").click(function() {
	$(".ordersave").attr("disabled", false);
	});*/

/*$(document).ready(function() {
	if ($('#firstCheck').val() == "Y") {
		$("#firstBtn").attr("disabled", false);
	}
});*/

function sendAprBack(value) {
	var developerData = {};

	developerData['cin_NO'] = $("#inputPassword").val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['cmts'] = $('#sndbackcmts').val();
	developerData['role'] = $('#role').val();
	developerData['entry_DT'] = new Date();
	developerData['off_ID'] = $('#empId').val();

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



function movEXM()
{
	$("#penexampleModal").modal('hide') ; 
	$('#pen_exam_order_add_id').trigger('submit');
		     var fpoExam = {};
			fpoExam['cinNo'] = $('#inputPassword').val();
				$.ajax({
		url: 'passexmdata',
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

function sendAC(e){

	if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role1').val() || "" == $('#role1').val())){
	
			if ("PAO" == $('#role1').val() && $('#acloffid').val() != "") {
				$("#aprCmtsModel").modal('show');
			} else {
			var developerData = {};
			developerData['cin_NO'] = $("#inputPassword").val();
			var resObj = $.ajax({
				url: 'updateMainRole',
				data: JSON.stringify(developerData),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(developerDatas) {
					$("#alertmsg").modal('show');
			},
				fail: function(rs, e) {
					alert("Error in Order");
				}
			});
		}
	
	}
}

/*function viewDetail(e) {
	if(!($("#scan").is(":checked") || $("#detain").is(":checked") || $("#scanOpenExam").is(":checked"))){
		swal('OOPS!', 'Select atleast any one order !', 'error');
		return false;
	}
  if ($('#qcou').val() == 0 && $('#ocou').val()==0 && parseFloat($('#totassval').val()) == 0)
       alert("Either Examination Order / OOC as assessment is over...");
    else{
    if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role1').val() || "" == $('#role1').val()))
    {
		if ("PAO" == $('#role1').val() && $('#acloffid').val() != "") {
			$("#aprCmtsModel").modal('show');
		} else {
			$('#pen_exam_order_add_id').trigger('submit');
			$("#alertmsg").modal('show');
		}
	}
	else
	{
				$("#penOrderpopup").text("This Article ID will move to Examination Queue  on receipt of the  arrival message.");
		        $("#penexampleModal").modal('show');
	}
	}
	

}*/

function viewDetail(e) {
			var reg =/<(.|\n)*?>/g; 
		 if (reg.test($('#textsend1').val()) == true) {
		        swal('OOPS!', 'Special character Not allowed!!', 'error');
				return false
		    }
	if(!($("#scan").is(":checked") || $("#detain").is(":checked") || $("#scanOpenExam").is(":checked"))){
		swal('OOPS!', 'Select atleast any one order !', 'error');
		return false;
	}
  if ($('#qcou').val() == 0 && $('#ocou').val()==0 && parseFloat($('#totassval').val()) == 0)
       alert("Either Examination Order / OOC as assessment is over...");
    else{
    if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()))
    {
		if ("PAO" == $('#role1').val() && $('#acloffid').val() != "") {
			$("#aprCmtsModel").modal('show');
		} else {
			if("PAO" == $('#role1').val()){
				$("#penOrderpopup").text("As the value demands AC/DC's permission, it will move to AC/DC.");
			}
			else{
				$("#penOrderpopup").text("This Article ID will move to Examination Queue  on receipt of the  arrival message.");
			}
			$("#penexampleModal").modal('show');
		}
	}
	else
	{
				$("#penOrderpopup").text("This Article ID will move to Examination Queue  on receipt of the  arrival message.");
		        $("#penexampleModal").modal('show');
	}
	}
	

}



 //  if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role1').val() || "" == $('#role1').val()))
 //  		if ("PAO" == $('#role1').val() && $('#acloffid').val() != "") {
//			$("#aprCmtsModel").modal('show');
//		} else {
//			var developerData = {};
//			developerData['cin_NO'] = $("#inputPassword").val();
//			var resObj = $.ajax({
//				url: 'updateMainRole',
//				data: JSON.stringify(developerData),
//				dataType: "json",
//				contentType: "application/json",
	//			type: "post",
		//		success: function(developerDatas) {
					//$("#alertmsg").modal('show');
	//			},
//				fail: function(rs, e) {
//					alert("Error in Order");
//				}
//			});
			
		
	



/*function sendAclcmts(value) {

	var role = "PAO";
	var offId = "80000416";
	var developerData = {};

	developerData['cin_NO'] = $("#inputPassword").val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['cmts'] = $('#sndaclcmts').val();
	developerData['role'] = role;
	developerData['entry_DT'] = new Date();
	developerData['off_ID'] = offId;

	var resObj = $.ajax({
		url: 'sendBackAcl',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			window.location.href = "ead_list";
		},
		fail: function(rs, e) {
			alert("Error in Order");
		}
	});

};
*/

/*function deleteFirstCheck(value) {
	var developerData = {};
	developerData['cin_NO'] = $("#inputPassword").val();
	var resObj = $.ajax({
		url: 'deleteFirstCheckApr',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			window.location.href = "ead_main?id=" + $("#inputPassword").val();
		},
		fail: function(rs, e) {
			alert("Error in Order");
		}
	});
};
*/
/*function success(e) {
	if (document.getElementById(e).value === "") {
		$(".ordersave").attr("disabled", true);
		$("#" + $("#inputPassword").val() + "").attr("disabled", true);
	} else {
		$(".ordersave").attr("disabled", false);
		$("#" + $("#inputPassword").val() + "").attr("disabled", false);
	}
	
}*/

function success(e) {
	if (document.getElementById(e).value === "") {
		$(".ordersave").attr("disabled", false);
		$("#" + $("#inputPassword").val() + "").attr("disabled", true);
	} else {
		$(".ordersave").attr("disabled", true);
		$("#" + $("#inputPassword").val() + "").attr("disabled", false);
	}
	
}

/*function successFirst(e) {
	if (document.getElementById(e).value === "") {
		document.getElementById('lock_first').disabled = true;
		$("#" + $("#inputPassword").val() + "").attr("disabled", false);
	} else {
		document.getElementById('lock_first').disabled = false;
		$("#" + $("#inputPassword").val() + "").attr("disabled", true);
	}
}
*/
/*function successFirst1(e) {
	if (document.getElementById(e).value === "") {
		document.getElementById('lock_first1').disabled = true;
		$("#" + $("#inputPassword").val() + "").attr("disabled", false);
	} else {
		document.getElementById('lock_first1').disabled = false;
		$("#" + $("#inputPassword").val() + "").attr("disabled", true);
	}
}*/


/*function successPopup(e) {
	if (document.getElementById(e).value === "") {
		document.getElementById('lock1').disabled = true;
	} else {
		document.getElementById('lock1').disabled = false;
	}
}*/


$.each($("[id*=textsend]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('textsend', 'textsend' + (i + 1));
	$(this).attr('id', fin);
});

/*$('#scan').click(function() {

	if ($('#scan').is(':checked')) {
		$("#detain").prop("checked", false);
		$("#scanOpenExam").prop("checked", false);
		$("#detain").attr("disabled", true);
		$("#scanOpenExam").attr("disabled", true);
	} else {
		$("#detain").attr("disabled", false);
		$("#scanOpenExam").attr("disabled", false);
	}
});*/


$('#scan').click(function() {

	if ($('#scan').is(':checked')) {
		$("#detain").prop("checked", false);
		$("#scanOpenExam").prop("checked", false);
		$("#detain").attr("disabled", true);
		$("#scanOpenExam").attr("disabled", true);
		$('.sendac').attr('disabled',true);
	} else {
		$("#detain").attr("disabled", false);
		$("#scanOpenExam").attr("disabled", false);
		$('.sendac').attr('disabled',false);
	}
});

/*$('#detain').click(function() {
	if ($('#detain').is(':checked')) {
		$("#scanOpenExam").prop("unchecked", true);
		$("#scan").attr("disabled", true);
	} else {
		if ($('#scanOpenExam').is(':checked')) {
			$("#scan").attr("disabled", true);
		} else {
			$("#scan").attr("disabled", false);
		}
	}
});*/


$('#detain').click(function() {
	if ($('#detain').is(':checked')) {
		$("#scanOpenExam").prop("unchecked", true);
		$("#scan").attr("disabled", true);
		$('.sendac').attr('disabled',true);
	} else {
		if ($('#scanOpenExam').is(':checked')) {
			$("#scan").attr("disabled", true);
			$('.sendac').attr('disabled',true);
		} else {
			$("#scan").attr("disabled", false);
			$('.sendac').attr('disabled',true);
		}
		if($('#detain').is(':checked') && $('#scanOpenExam').is(':checked')){
			$('.sendac').attr('disabled',true);
		}else if($('#detain').is(':checked') || $('#scanOpenExam').is(':checked')){
			$('.sendac').attr('disabled',true);
		}else{
			$('.sendac').attr('disabled',false);
		}
	
	
	}
	
});

/*$('#scanOpenExam').click(function() {
	if ($('#scanOpenExam').is(':checked')) {
		$("#detain").prop("unchecked", true);
		$("#scan").attr("disabled", true);
	} else {
		if ($('#detain').is(':checked')) {
			$("#scan").attr("disabled", true);
		} else {
			$("#scan").attr("disabled", false);
		}
	}
});*/

$('#scanOpenExam').click(function() {
	if ($('#scanOpenExam').is(':checked')) {
		$("#detain").prop("unchecked", true);
		$("#scan").attr("disabled", true);
		$('.sendac').attr('disabled',true);
	} else {
		if ($('#detain').is(':checked')) {
			$("#scan").attr("disabled", true);
			$('.sendac').attr('disabled',true);
		} else {
			$("#scan").attr("disabled", false);
			$('.sendac').attr('disabled',true);
		}
		
		if($('#detain').is(':checked') && $('#scanOpenExam').is(':checked')){
			$('.sendac').attr('disabled',true);
		}else if($('#detain').is(':checked') || $('#scanOpenExam').is(':checked')){
			$('.sendac').attr('disabled',true);
		}else{
			$('.sendac').attr('disabled',false);
		}
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