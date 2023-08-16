
function clearDropDown(e) {
	e.empty();

}

var selctuser = "";
$(document).ready(function() {
	$("select.selsite").change(function() {
		var selectedsite = $(this).children("option:selected").val();
		var dvuserview = document.getElementById("activeusers");
		var dvallusertable = document.getElementById("allusertble");
		var pdfexcel = document.getElementById("pdf&excelExport1");
		selctuser = selectedsite;
		if (selectedsite == "--Select Active Sites--") {
			pdfexcel.style.display = "none";
			dvuserview.style.display = "none";
			dvallusertable.style.display = "block";
		} else {
			$.ajax({
				url: 'gethstryofroles?userstecde=' + selectedsite,
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(resp) {
					$('#ViewuserHstrySite').dataTable().fnDestroy();
					clearDropDown($("#viewactveuser"));
					$.each(resp, function(index, value) {
						var endsts = "";
						if(value[6] == null){
							endsts = "Till-Date"
						}else {
							endsts = value[6];
						}
						var iterator = index + 1;
						var markup = "<tr><td align= 'center' class= 'font-weight-medium'>" + iterator + "</td><td align= 'center' class= 'font-weight-medium'>" + value[1] + "</td><td align= 'center' class= 'font-weight-medium'>" + value[2] + "</td><td align= 'center' class= 'font-weight-medium'>" + value[0] + "</td><td align= 'center' class= 'font-weight-medium'>" + value[3] + "</td></td><td align= 'center' class= 'font-weight-medium'>" + value[4] + "</td></td><td align= 'center' class= 'font-weight-medium'>" + value[5] + "</td></td><td align= 'center' class= 'font-weight-medium'>" + endsts + "</td></tr>"
						$("#viewactveuser").append(markup);

					});
					$('#ViewuserHstrySite').dataTable({
				"ordering": false,
				"dom": 'Bfrtip',
				buttons: ['excel']
	});
					dvuserview.style.display = "block";
					pdfexcel.style.display = "block";
					
				}
			});
		}
	});
});

function exceldownload1(obj){
	$(".buttons-excel").trigger('click');
}

function pdfdownload1(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_Lsmhstryrolesbyoffcr?actveSite=' + selctuser,
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






