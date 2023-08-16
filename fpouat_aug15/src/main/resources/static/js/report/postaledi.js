function showCommercialimport() {
			$("#processcomercialdata").hide();
			$("#queryQueueshow").show('slow');
        }
        
        /*function showCommercialimportStatus() {
			$("#quQueue").show();
			$("#queryQueueshow").hide('slow');
        }*/






$("#processcommercial").click(function(){
	$("#queryQueueshow").hide();
	$('#processcomercialdata').show()
//	$("div #content").attr("style", "display: block !important");
$.ajax({
    url: 'postaledi1', 
    success: function(data) {
	
        $('#processcomercialdata').html(data);
    }
});
});








function exceldownloadpostaledi(obj){
	$(".buttons-excel").trigger('click');
}


function viewDetail(e)
{
  location.href = "edi_decl?id=" + e.id;
showLoader();
}
	
function pdfdownloadpostaledi(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_postaledi',
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
	