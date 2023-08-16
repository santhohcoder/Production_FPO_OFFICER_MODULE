$('#logoutuser').click(function() {
	$('#logoutpopup').modal('show');

});

$('#logoutpage').click(function() {
	window.location = "";
	function preback() { window.history.forward(); }
	setTimeout("preback", 0);
	window.onunload = function() { null };
});

$('#notready').click(function() {
	$('#logoutpopup').modal('hide');

});
	function noBack() {
		window.history.forward();
	}
