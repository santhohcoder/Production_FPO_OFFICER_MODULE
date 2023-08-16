

/*function refreshthreshold(){
	window.location = "chngethreshold";
	$("#thresholdval").hide();
			$("#maxassval").show('slow');
}*/




function thresholdvalue() {
			$("#thersholdvaluehistoryhtml").hide();
			$("#thresholdval").show('slow');
			window.location = "chngethreshold";
        }

$("#userhstry").click(function(){
	$("#thresholdval").hide();
	$('#thersholdvaluehistoryhtml').show()
//	$("div #content").attr("style", "display: block !important");
$.ajax({
    url: 'thresholdvaluehistory', 
    success: function(data) {
	
        $('#thersholdvaluehistoryhtml').html(data);
    }
});
});














/*
    function exporttoexcel() {
            // Get the table HTML element
            const table = document.getElementById("yettobemapSite");

            // Convert the table to a worksheet
            const ws = XLSX.utils.table_to_sheet(table);

            // Convert the worksheet to a workbook
            const wb = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(wb, ws, "Table");

            // Save the workbook as an Excel file
            XLSX.writeFile(wb, "table.xlsx");
        }

		// Function to print the table
		function exporttoprint() {
			// Get the table HTML element
			const table = document.getElementById("yettobemapSite");

			// Open the print dialog
			window.print();
		}
 function exportToPdf() {
            // Get the table
            var table = document.getElementById("yettobemapSite");

            // Use html2canvas to create a canvas element from the table
            html2canvas(table).then(function(canvas) {
                // Use jsPDF to create a PDF document from the canvas
                var pdf = new jsPDF('p', 'pt', 'letter');
                pdf.addImage(canvas.toDataURL('image/png'), 'PNG', 0, 0, canvas.width, canvas.height);
                pdf.save("table.pdf");
            });
        }



function pdfdownload(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: "pdfdownload_viewfpoSites",
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


function downloadPDF() {
  const doc = new jsPDF();
  const table = document.getElementById("yettobemapSite"); // replace "my-table" with the ID of your table
  doc.autoTable({ html: table });
  doc.save("table.pdf");
}

function downloadExcel() {
  const table = document.getElementById("yettobemapSite"); // replace "my-table" with the ID of your table
  const workbook = XLSX.utils.table_to_book(table);
  XLSX.writeFile(workbook, "table.xlsx");
}


*/


/*
function sendingvalue(act){
	var data ={};
	var maxvalue = act.id;
	var doc_name = act.value;

	$.ajax({
		url:"viewingthresholdletter?maxassval="  + maxvalue + "&docname=" + doc_name,
		type:"get",
		success: function(data) {
                 $("#zcallLetterContent").html(data);
                    $("#zthresholdpdf").attr('src','file/pdf/'+$('#zfilethresh').val() + '#toolbar=0&navpanes=0;readonly=true;');
                	$("#zthresholdmodal").modal('toggle');

            },
            error: function(rs, e) {
                swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
            }
		
	})
	
	
	
}*/

function sendingvalue(act){
	var data ={};
	/*var maxvalue = act.id;*/
	var from_dt = act.value;

	$.ajax({
		url:"viewingthresholdletter?fromdate="  + from_dt ,
		type:"get",
		success: function(data) {
                 $("#zcallLetterContent").html(data);
                    $("#zthresholdpdf").attr('src','file/pdf/'+$('#zfilethresh').val() + '#toolbar=0&navpanes=0;readonly=true;');
                	$("#zthresholdmodal").modal('toggle');

            },
            error: function(rs, e) {
                swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
            }
		
	})
	
	
	
}


/*var error =0;*/
var oMyForm = new FormData();

function clearFormAndHideModal() {
    oMyForm = new FormData(); 
    $('#asspopup1').modal('hide'); 
}

$(document).ready(function() {
    $('#updthreshldval').click(function() {
        $('#Asspopup').modal('show');
    });

    $('#chngethreval').click(function() {
        var reg = /<(.|\n)*?>/g;
        var assVal = $('#assval').val().trim();
        if (reg.test(assVal)) {
            swal('OOPS!', 'Special character Not allowed!!', 'error');
            return false;
        }

        if (assVal === "") {
            $('#assval').next().show().text('Please Enter the threshold value!!!');
            $('#assval').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" });
            return false;
        }

        $('#assval').css({ "border-color": "", "box-shadow": "" });
        $('#Asspopup').modal('hide');
        $('#asspopup1').modal('show');
    });

    $('#updatemaxval').click(function() {
        var reg = /<(.|\n)*?>/g;
        var recordMsg = $('#recordMsg').val().trim();
        if (reg.test(recordMsg)) {
            swal('OOPS!', 'Special character Not allowed!!', 'error');
            return false;
        }

        if (recordMsg === "") {
            $('#recordMsg').next().show().text('Please Enter Record Reason*');
            $('#recordMsg').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" });
            return false;
        }

        $('#recordMsg').css({ "border-color": "", "box-shadow": "" });

        if ($("#kyc-filetxt-1").val().length > 0) {
            oMyForm.append("filename", kycFiles[$("#kyc-filetxt-1").attr('id').split('-').pop()].files[0]);
        }

        var docName1 = ($('#kyc-filetxt-1').val() === '') ? 1 : 2;
        oMyForm.append("docname2", docName1);

        oMyForm.append("maxassval", $("#assval").val());
        oMyForm.append("reason", recordMsg);
        oMyForm.append("docname", $('#kyc-filetxt-1').val());

        if ($('#lginid').val() !== "") {
            $.ajax({
                url: 'getAssmaxval',
                data: oMyForm,
                contentType: false,
                processData: false,
                type: "POST",
                enctype: 'multipart/form-data',
            }).done(function(data) {
                if (routeToHome === '0')
                    window.location = "chngethreshold";
                else
                    window.location = "Home?role=PNA";
            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log(jqXHR, textStatus, errorThrown);
            });
        } else {
            $('#Asspopup').modal('hide');
            alert("No longer login-id required, please login again!.");
        }
    });

    $('#cleartheForm').click(function() {
		$('#Asspopup').modal('show');
        clearFormAndHideModal();
    });

	$('#cleartheFormBack').click(function() {
		$('#Asspopup').modal('show');
        clearFormAndHideModal();
    });
});

/*$('#updthreshldval').click(function() {

	$('#Asspopup').modal('show');
	var userval = $('#lginid').val();
	$('#chngethreval').click(function() {
		oMyForm.append("maxassval",$("#assval").val());
		var maxval = $('#assval').val();
		$('#Asspopup').modal('hide');
		$('#asspopup1').modal('show');

		$('#updatemaxval').click(function() {
			
			var reg =/<(.|\n)*?>/g; 
 if (reg.test($('#recordMsg').val()) == true) {
        swal('OOPS!', 'Special character Not allowed!!', 'error');
		return false
    }
			error =0;
			if ($('#recordMsg').val().trim() == "") {
		$('#recordMsg').next().show();
		$('#recordMsg').next().text('Please Enter Record Reason*')
		$('#recordMsg').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#recordMsg').css({ "border-color": "", "box-shadow": "" })

	}
			oMyForm.append("reason",$("#recordMsg").val());
			oMyForm.append("docname",$('#kyc-filetxt-1').val());
			if($("#kyc-filetxt-1").val().length > 0){
				oMyForm.append("filename",kycFiles[$("#kyc-filetxt-1").attr('id').split('-')[$("#kyc-filetxt-1").attr('id').split('-').length - 1]].files[0]);
				}
			
			var resp = $('#recordMsg').val();
		var docname = $('#kyc-filetxt-1').val();
		var doc = $('#kyc-file-1').val();
		
		if(docname == ''){
	var docName1 = 1;
					oMyForm.append("docname2",docName1);
}else{
	var docName1 = 2;
					oMyForm.append("docname2",docName1);
}
		console.log(docname);
		if(error == 0){
			if (userval != "") {
				$.ajax({
					url: 'getAssmaxval',
					// data: JSON.stringify(resp),
					data: oMyForm,
					// dataType: "json",
					//contentType: "application/json",
					contentType: false,
					processData: false,
					type: "POST",
					enctype: 'multipart/form-data',
					success: function(data) {
						window.location = "chngethreshold";
					},
					fail: function(rs, e) {
						console.log(rs, responseText);
					}
				});
			} else {
				$('#Asspopup').modal('hide');
				alert("No longer login-id required, please login again!.");
			};
      }
		});
	});
});*/

    console.log("success");
    function viewZ() {
        $.ajax({
            url: "zthresholdletter",
            type: "get",
            success: function(data) {
                 $("#zcallLetterContent").html(data);
                    $("#zthresholdpdf").attr('src','file/pdf/'+$('#zfilethresh').val() + '#toolbar=0&navpanes=0;readonly=true;');
                	$("#zthresholdmodal").modal('toggle');
            },
            error: function(rs, e) {
                swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
            }
        });
    }

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