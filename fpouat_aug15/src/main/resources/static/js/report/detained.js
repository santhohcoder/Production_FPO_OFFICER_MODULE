
$(document).ready(function() {
	$("#detainedtable").dataTable({
		"processing": true,
		"paging": true,
		"dom": 'Bfrtip',
		buttons: ['excel']
	});
	
	
            $('#detainedtable').DataTable().on( 'order.dt search.dt', function () {
                $('#detainedtable').DataTable().column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                         cell.innerHTML = i+1;
                     } );
                 }).draw();
});

function exceldwnlddetainedartcl(obj){
	$(".buttons-excel").trigger('click');
}

function pdfdwnlddetainedartcl(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_detainedimprtartcl',
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(response) {
			console.log('inside success');
			
			 $("#staticreportdownloadanchortag").attr("href","downloadPdfFile?filename=" + response);

            document.getElementById("staticreportdownloadanchortag").click();

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