function back() {
	window.location = "dash";
	showLoader()
	
}
showLoader();
$(document).ready(function() {
	showLoader();
	hideLoader();
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