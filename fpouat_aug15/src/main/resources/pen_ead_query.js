/*function success(e) {
	if (document.getElementById(e).value === "") {
		document.getElementById('lock').disabled = true;
	} else {
		document.getElementById('lock').disabled = false;
	}
}*/

$(document).ready(function() {
$('.nxtbtn').attr("disabled", false);
});

$(document).ready(function() {
$('#defualtQuery1').attr("disabled", true);
$('#defualtQuery2').attr("disabled", true);
$('#defualtQuery3').attr("disabled", true);
$('#defualtQuery4').attr("disabled", true);
});

$('#my_button').click(function() {
	setTimeout(function() { $('#exampleModal').modal('hide'); }, 1000);
   /* $('.nxtbtn').attr("disabled", false);*/
});


$(document).ready(function() {
	if ($('#totDuty').val() > 500000 && "PAC" == $('#role').val()) {
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
	window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
}

function viewDetail1(e) {
	//alert(e.id);
	window.location.href = "pen_order?id=" + e.id;
}

/*function enableNum() {
	document.getElementById("randomNum").disabled = false;
	$(".nxtbtn").attr("disabled", false); 
}*/

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


/*function validateDin() {
	if ($('#totDuty').val() > 500000 && ("PAO" == $('#role').val() || "" == $('#role').val())){
		$('#dinValidate').text("Assessable value of the article requires AC's concurrence. D-Call letter gets generate in AC's role'");
		$("#aclClose").hide();
		$("#my_button").show();
		$('.nxtbtn').attr("disabled", false);
	} else {
		if (null == $('#din').val() || $('#din').val() == "") {
			$('#dinValidate').text("Do you want to proceed without DIN?");
			$("#aclClose").show();
			$("#my_button").show();
			
		} else {
			$('#dinValidate').text("Do you want to proceed?");
			$("#aclClose").show();
			$("#my_button").show();
			
		}

	}
}*/