
$(document).ready(function() {
	$("#deliverytable").dataTable({
		"processing": true,
		"paging": true
	});
	
	
            $('#deliverytable').DataTable().on( 'order.dt search.dt', function () {
                $('#deliverytable').DataTable().column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                         cell.innerHTML = i+1;
                     } );
                 }).draw();

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