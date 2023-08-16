$(document).ready(function() {
	//  $('#order').submit(function(){
	if ($('#role1').val() == 'PAO')
		$("#firstBtn").attr("disabled", false);
	// });
	if ($('#role1').val() == 'PAC') {
		$("#lock").attr("disabled", false);
		$('#aclMsg').show();
		$('#aclMsg1').show();
	}
});

$(document).ready(function() {
	if ($('#role1').val() == 'PAO' && $('#firstCheck').val() == 'Y')
		$('#aprCmtsModel').show();
	if ($('#role1').val() == 'PAO' && $('#acl_offid').val()!= null)
	    $('#firstBtn').show();
	else
	    $('#firstBtn').hide();
});


function validateExam() {
	if ($("#scan").is(":checked") || $("#detain").is(":checked") || $("#scanOpenExam").is(":checked")) {
		$("#exampleModal").modal('toggle');
	} else {
		swal('OOPS!', 'Select atleast any one order !', 'error');
	}
}

function submitExam() {
	$("#exampleModal").modal('toggle');
	if ($("#scan").is(":checked") || $("#detain").is(":checked") || $("#scanOpenExam").is(":checked")) {
		$('#order').trigger('submit');
	} else {
		swal('OOPS!', 'Select atleast any one order !', 'error');
	}
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
