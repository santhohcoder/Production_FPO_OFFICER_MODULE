function success(e) {
	if (document.getElementById(e).value.length > 5) {
		document.getElementById('lock').disabled = false;
	} else {
		document.getElementById('lock').disabled = true;
	}
}

function saveenbl(e) {
	document.getElementById('lock').disabled = false;
}

$(".queryinfobtncancel").click(function() {
	$('.nxtorderbtn').trigger('click');
});


function eadCallLetter() {
	showLoader();
//	var docName = localStorage.getItem("docName")	
//	console.log(docName)
	$.ajax({
		url: "ead_callletter?" + $("#ead-callletter").serialize(),
		type: "get",
		success: function(data) {
		if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && "PAO" == $('#role').val())	
			window.location.href = "ead_list";
		else
		{
			$("#dcallLetterContent").html(data);
			$("#embedpdf").attr('src', 'file/pdf/' + $('#pdffilename').val() + '#toolbar=0&navpanes=0;readonly=true;');
		//	$("#embedpdf").attr('src', $('#pathnm').val() + $('#pdffilename').val() + '#toolbar=0&navpanes=0;readonly=true;');*/
			$("#pdfModal").modal('toggle');
			$('#exampleModal').modal('hide');}
		//	window.open("viewfile", "DCALLLETTER", "height=800,width=1550,left=150,top=150,status=0,scrollbars=1,location=0,toolbar=0;resizable=0,menubar=0,titlebar=0,fullscreen=no");			
	    hideLoader();
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
		
	});

}

function eadlist(){
    window.location.href = "ead_list";
}

$(document).ready(function() {
	$('.nxtbtn').attr("disabled", true);
});

$('#my_button').click(function() {
	/*setTimeout(function() { $('#exampleModal').modal('toggle'); }, 1000);*/
	$('.nxtbtn').attr("disabled", false);
});


$('#ent_button').click(function() {
	setTimeout(function() { $('#exampleModal').modal('hide'); }, 1000);
	// $('.nxtbtn').attr("disabled", false);
	$('#din').focus();
});

$(document).ready(function() {
	if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && "PAC" == $('#role').val()) {
		$("#aclWarningMsgQry").modal('show');
		$(".deptcmtsApr").attr("disabled", true);
		$(".deptcmtsAcl").show();
		$("#aclMsg").show();

	}
});


$.each($("[id*=textsend]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('textsend', 'textsend' + (i + 1));
	$(this).attr('id', fin);
});

function goBack() {
	if ((localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer).includes('declared')) {
		window.location = (localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer) + "&back=true";
	} else {
		window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
	}
}

function viewDetail1(e) {
	//alert(e.id);
	var s = $("#inputPassword").val();
	window.location.href = "order?id=" + s;
}

/*function enableNum() {
		var reg =/<(.|\n)*?>/g; 
 if (reg.test($('#textsend1').val()) == true) {
        swal('OOPS!', 'Special character Not allowed!!', 'error');
		return false
    }
		if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role').val() || "" == $('#role').val())) {
			$('#dinValidate').text("Assessable value of the article requires AC's concurrence. D-Call letter gets generate in AC's role'");
			$("#aclClose").hide();
			$("#my_button").show();
			$("#ent_button").hide();
			$('.nxtbtn').attr("disabled", false);
		$('#exampleModal').modal('show');
		}else{
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var mailid = $('#mail').val();
	var mobno = $('#phoneno').val();
	if (mailid.length > 0) {
		if (!(mailid.match(mailformat))) {
			
			swal('OOPS!', 'Please Enter Valid Email ID...','error');
	//		document.getElementById("randomNum").disabled = true;
			$("#mail").focus();
		}
		else{
	//		document.getElementById("randomNum").disabled = false;
	//		$("#din-tab-div").css("display","flex");
			}
	}
//	else{
//		document.getElementById("randomNum").disabled = false;
//			$("#din-tab-div").css("display","flex");
//		}
	if (mobno.length > 0) {
		if (mobno.length != 10) {
			
			swal('OOPS!', 'Please Enter Valid Mobile No...','error');
			document.getElementById("randomNum").disabled = true;
			$("#phoneno").focus();
		}
		else{
	//		document.getElementById("randomNum").disabled = false;
	//		$("#din-tab-div").css("display","flex");
	$('#dinValidate').text("Do you want to proceed?");
	$("#aclClose").show();
			$("#my_button").show();
			$("#ent_button").hide();
			$('#exampleModal').modal('show');
			}
	}
	else{
	//	document.getElementById("randomNum").disabled = false;
	//		$("#din-tab-div").css("display","flex");
	$('#dinValidate').text("Do you want to proceed?");
	$("#aclClose").show();
			$("#my_button").show();
			$("#ent_button").hide();
			$('#exampleModal').modal('show');
		}
		}
}*/


function enableNum() {
		var reg =/<(.|\n)*?>/g; 
 if (reg.test($('#textsend1').val()) == true) {
        swal('OOPS!', 'Special character Not allowed!!', 'error');
		return false
    }
		if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role').val() || "" == $('#role').val())) {
			$('#dinValidate').text("Assessable value of the article requires AC's concurrence. D-Call letter gets generate in AC's role'");
			$("#aclClose").hide();
			$("#my_button").show();
			$("#ent_button").hide();
			$('.nxtbtn').attr("disabled", false);
		$('#exampleModal').modal('show');
		}else{
	/*var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var mailid = $('#mail').val();
	var mobno = $('#phoneno').val();
	if (mailid.length > 0) {
		if (!(mailid.match(mailformat))) {
			
			swal('OOPS!', 'Please Enter Valid Email ID...','error');
	//		document.getElementById("randomNum").disabled = true;
			$("#mail").focus();
		}
		else{
	//		document.getElementById("randomNum").disabled = false;
	//		$("#din-tab-div").css("display","flex");
			}
	}
//	else{
//		document.getElementById("randomNum").disabled = false;
//			$("#din-tab-div").css("display","flex");
//		}
	if (mobno.length > 0) {
		if (mobno.length != 10) {
			
			swal('OOPS!', 'Please Enter Valid Mobile No...','error');
			document.getElementById("randomNum").disabled = true;
			$("#phoneno").focus();
		}
		else{
	//		document.getElementById("randomNum").disabled = false;
	//		$("#din-tab-div").css("display","flex");
	$('#dinValidate').text("Do you want to proceed?");
	$("#aclClose").show();
			$("#my_button").show();
			$("#ent_button").hide();
			$('#exampleModal').modal('show');
			}
	}
	else{*/
	//	document.getElementById("randomNum").disabled = false;
	//		$("#din-tab-div").css("display","flex");
	$('#dinValidate').text("Do you want to proceed?");
	$("#aclClose").show();
			$("#my_button").show();
			$("#ent_button").hide();
			$('#exampleModal').modal('show');
		
		}
}

var developerData = {};
var resObj = $.ajax({
	url: 'getDefualtQuery',
	data: JSON.stringify(developerData),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(data) {
		$("#queryOne").text(data[0]);
		$("#queryTwo").text(data[1]);
		$("#queryThree").text(data[2]);
		$("#queryFour").text(data[3]);
		console.log(data);

	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});


function validateForm() {
	/*var dinReg = /CBIC-[0-9]{4}0[1-9]|1[0-2][0-9]{4}[0-9]{6}/;
	var numberReg = /^[0-9]+$/;

	var din = $('#din').val();

	var inputVal = new Array(din);

	var inputMessage = new Array("Document Identification Number");

	$('.error').hide();

	if (inputVal[0] == "") {
		$('#dinErr').after(
			'<span class="error"> Please enter Valid  '
			+ inputMessage[0] + '</span>');
	} else if (!dinReg.test(din)) {
		$('#dinErr').after(
			'<span class="error"> CBIC-YYYYMMZCDRNNNNNN only</span>');
	} else {

		var currentTime = new Date();
		var yearFromSystem = currentTime.getFullYear();
		var res = din.split("-");
		var yearFromUser = res[1].substring(0, 4);

		if (yearFromUser > yearFromSystem)
			$('#dinErr')
				.after(
					'<span class="error"> CBIC-YYYYMMZCDRNNNNNN only</span>');
	}*/
	/*var dinReg = /[0-9]{8}[A-Z]{2}[0-9]{7}[A-Z]{3}/;

	var din = $('#din').val();

	var inputVal = new Array(din);

	var inputMessage = new Array("Document Identification Number");
	if (inputVal[0] == "") {
		$('.error').html('');
		$('#dinErr').after(
			'<span class="error"> Please enter Valid  '
			+ inputMessage[0] + '</span>');
			return false;
	} else if (!dinReg.test(din)) {
		$('.error').html('');
		$('#dinErr').after(
			'<span class="error"> e.g. : 20220272MR0000222FBD</span>');
			return false;
	}else {

		var currentTime = new Date();
		var yearFromSystem = currentTime.getFullYear();
		
		var res = din.substring(0, 4);
		
		
			if (res > yearFromSystem) {
				$('.error').html('');
					$('#dinErr')
						.after(
							'<span class="error"> e.g. : 20220272MR0000222FBD</span>');
				return false;
			}else{
				return true;
			}
		 

		
	}*/

}


function validateEmailid() {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if ($('#mail').val().length > 0) {
		if (!($('#mail').val().match(mailformat))) {
			swal('OOPS!', 'Invalid email address!', 'error');
			document.getElementById('lock').disabled = true;
			return false;
		}
		else {
			document.getElementById('lock').disabled = false;
			return true;
		}
	}
	else
		return true;
}

function validatephone() {
	var ph = $('#phoneno').val();
	if (validateEmailid()) {
		if (ph.length > 0) {
			if (isNaN(ph) || ph.indexOf(" ") != -1) {
				swal('OOPS!', 'Enter numeric value', 'error');
				return false;
			}
			if (ph.length != 10) {
				swal('OOPS!', 'Invalid Mobile Number...','error');
				return false;
			}
		}
		else
			return true;
	}
}


/*function validateDin() {
	var mails=($("#oemailid").find('input').val().trim() != '' && $("#oemailid").find('input').val().trim() != '- NIL -')
	|| $("#mail").val().trim() != '' ? true : false;
	var res=true;
	if(mails)
	 res= validateForm();
	if(res){
		if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && ("PAO" == $('#role').val() || "" == $('#role').val())) {
			$('#dinValidate').text("Assessable value of the article requires AC's concurrence. D-Call letter gets generate in AC's role'");
			$("#aclClose").hide();
			$("#my_button").show();
			$("#ent_button").hide();
			$('.nxtbtn').attr("disabled", false);
		}
		else if ((null == $('#din').val() || $('#din').val() == "") && mails) {
			//	$('#dinValidate').text("Do you want to proceed without DIN?");
			$('#dinValidate').text("Please Enter Valid DIN...");
			$("#aclClose").hide();
			$("#my_button").hide();
			$("#ent_button").show();
		} else {
			$('#dinValidate').text("Do you want to proceed?");
			$("#aclClose").show();
			$("#my_button").show();
			$("#ent_button").hide();
		}
		$('#exampleModal').modal('show');
	}else{
		$('#exampleModal').modal('hide');
	}
	

}*/

function printdcall() {

	printcountupdate($('#inputPassword').val(), $('#dcallnoid').val());
	let objFra = document.getElementById('embedpdf');
	objFra.contentWindow.focus();
	objFra.contentWindow.print();
}

function downloaddcall() {

	printcountupdate($('#inputPassword').val(), $('#dcallnoid').val());
	let objFra = document.getElementById('embedpdf');
	objFra.contentWindow.focus();
	objFra.contentWindow.print();

}



function printcountupdate(cinno, dcall_no) {


	var oMyForm = new FormData();
	oMyForm.append("cinno", cinno);
	oMyForm.append("dcall_no", dcall_no);

	$.ajax({
		url: "printcountupdate",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);
			if (json.status == 'Success') {

			}
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});


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

