$('#singledata').DataTable({
		"ordering": false
	});
	
	$('#singledata1').DataTable({
		"ordering": false
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
