function goBack() {
//alert($('#firstCheck').val());
//	if ($('#firstCheck').val() == "Y") {
//		$("#firstBtn").attr("disabled", false);
//	}
	if((localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer).includes('declared')){
		window.location=(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer)+"&back=true";
	}else{
		window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
	}
}

function listView() {
	window.location.href = "ead_list";
}

$(document).ready(function() {
//	if ((parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role1').val() || "" == $('#role1').val())) {
	if (($('#msgAprWarning').text()=="2") && ("PAO" == $('#role1').val() || "" == $('#role1').val()))
	  $('#msgAprWarning').text("Do you want to submit First Check Order? This needs AC's Approval.");
	else if (($('#msgAprWarning').text()=="2") && "PAC" == $('#role1').val())
		$('#msgAprWarning').text("Do you want to submit First Check Order?");
	else if	((parseFloat($('#totassval').val()) > parseFloat($('#maxval').val())) && ("PAO" == $('#role1').val() || "" == $('#role1').val())) {
		$('#msgAprWarning').text("Do you want to submit Exam. Order?  As the value demands AC/DC's permission, it will move to AC/DC.");
	} 
	else
	  $('#msgAprWarning').text("Do you want to submit Exam Order?");	
});


$(document).ready(function() {
	if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && "PAC" == $('#role1').val() ){	
		$("#aclWarningMsgOrder").modal('show');
		$("#aclMsg").show();
		$("#aclMsg1").show();
		$("#firstBtn").hide();
	}
	$('#textsend').val($('#ordrmk').val());
});




$(document).ready(function() {
	if ($('#firstCheck').val() == "Y"  && $('#role1').val()=='PAO') {
		$("#firstBtn").attr("disabled", false);
	}
});

function sendAprBack(value) {
	var developerData = {};

	developerData['cin_NO'] = $("#inputPassword").val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['cmts'] = $('#sndaccmts').val();
	developerData['role'] = $('#role1').val();
//	developerData['entry_DT'] = new Date();
	developerData['off_ID'] = $('#curoffId').val();

	var resObj = $.ajax({
		url: 'sendBackApr',
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

}


function viewDetail(e) {
	var reg =/<(.|\n)*?>/g; 
 if (reg.test($('#textsend1').val()) == true) {
        swal('OOPS!', 'Special character Not allowed!!', 'error');
		return false
    }	
    if ($('#qcou').val() == 0 && $('#ocou').val()==0 && parseFloat($('#totassval').val()) == 0)
       alert("Either Examination Order / Query is must...");
    else{
    if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role1').val() || "" == $('#role1').val()))
    {
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
	else {
		window.location.href = "ead_submit?id=" + e.id;
	}}
}


function sendAclcmts(value) {

	var role = $('#currole').val();
	var offId =$('#curoffid').val();
	var developerData = {};

	developerData['cin_NO'] = $('#inputPassword').val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['cmts'] = $('#sndbackcmts').val();
	developerData['role'] = $('#role1').val();
//	developerData['entry_DT'] = new Date();
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


function deleteFirstCheck(value) {
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
}

function success(e) {
    if (document.getElementById(e) != null)
	 { 
	 if (document.getElementById(e).value.length < 5) {	
		document.getElementById('lock').disabled = true;
	//	$("#" + $("#inputPassword").val() + "").attr("disabled", false);
	} else {
		document.getElementById('lock').disabled = false;
	//	$("#" + $("#inputPassword").val() + "").attr("disabled", true);
	}}
}

function successFirst(e) {
	if (document.getElementById(e).value.length < 5) {
		document.getElementById('lock_first').disabled = true;
		$("#" + $("#inputPassword").val() + "").attr("disabled", false);
	} else {
		document.getElementById('lock_first').disabled = false;
		$("#" + $("#inputPassword").val() + "").attr("disabled", true);
	}
}

function successFirst1(e) {
	if (document.getElementById(e).value.length < 5) {
		document.getElementById('lock_first1').disabled = true;
		$("#" + $("#inputPassword").val() + "").attr("disabled", false);
	} else {
		document.getElementById('lock_first1').disabled = false;
		$("#" + $("#inputPassword").val() + "").attr("disabled", true);
	}
}


function successPopup(e) {
	if (document.getElementById(e).value === "") {
		document.getElementById('lock1').disabled = true;
	} else {
		document.getElementById('lock1').disabled = false;
	}
}


$.each($("[id*=textsend]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('textsend', 'textsend' + (i + 1));
	$(this).attr('id', fin);
});

$('#scan').click(function() {

	if ($('#scan').is(':checked')) {
		$("#detain").prop("checked", false);
		$("#scanOpenExam").prop("checked", false);
		$("#detain").attr("disabled", true);
		$("#scanOpenExam").attr("disabled", true);
	} else {
		$("#detain").attr("disabled", false);
		$("#scanOpenExam").attr("disabled", false);
	}
});

$('#detain').click(function() {
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
});

$('#scanOpenExam').click(function() {
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