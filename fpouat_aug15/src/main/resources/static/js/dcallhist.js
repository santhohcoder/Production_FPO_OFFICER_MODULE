$(document).ready(function() {
	

	var currentyear = new Date().getFullYear();
	var currentMonth = new Date().getMonth()+1;

	/*if (currentMonth <= 1) {
		currentMonth = 12 + currentMonth;
		currentyear = new Date().getFullYear() - 1;
	}*/

	if (currentMonth < 10) {
		currentMonth = '0' + currentMonth;
	}

	currentyear = currentyear + '-' + currentMonth;
	
	//$('#historystart').attr('min', currentyear);
	$('#historystart').val( currentyear);

	$("#dcallhisttable").dataTable({
		"processing": true,
		"paging": true,
		"dom": '<"pull-left"Bl>frtip',
		scrollY: '45vh',
		scrollCollapse: true,
		"ordering": false,
		scrollX: true,
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' D-Call Letter',
					message:' D-Call Letter @ ' + sitecode + '\n\r' + datetime,
					//customize: function (doc) {}
					},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
		   		    customize: function(doc, header) {
		   		    doc.content[1].table.widths = ['5%','9%','8%','8%','8%','8%','9%','9%','9%','9%','9%','9%'];
				   	doc.defaultStyle.alignment = 'center'
					 //doc.styles.tableHeader.fillColor='#ADD8E6',
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center'
    						},
					doc.content.splice(1, 0,{
					text: [{text:' Date:'+ datetime, bold: true,
					alignment: 'right',
					fontSize: 7,
					margin:[0,20,0,20],
					//watermark:'fpo', 
					},],});
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: ' + empid, bold: true,
					alignment: 'left',
					fontSize: 7,
					margin:[0,0,90,20],
					//watermark:'fpo', 
					},],}); 
					//doc.style.tableHeader		
					doc.defaultStyle.fontSize = 5;
					doc.styles.title.bold=true;
					doc.styles.title.fontSize=15;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 9;
					doc.styles.message.bold=true;
					//doc.styles.message.alignment = "right";
					doc.styles.tableHeader.fontSize= 6;
					doc.styles.tableHeader.bold=true;
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+' D-Call Letter @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:' D-Call Letter ',
},
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' D-Call Letter @ ' + sitecode + '\n\r' + datetime ,
					filename:' D-Call Letter',
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},	}],
			"initComplete": function( settings ) {
	     $("#dcallhisttable_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#dcallhisttable_filter input[type='search']").attr('id', 'searchdcallhisttable');
		$('#clear6').click(function() {	
		document.getElementById("dcallhisttable").value=""
		var table = $('#dcallhisttable').DataTable();
		table.search('').draw();
	   })
    	}
		
	}); 


	$('#dcallhisttable').DataTable().on('order.dt search.dt', function() {
		$('#dcallhisttable').DataTable().column(0, {
			//search: 'applied',
			//order: 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
	
	
/*	$('#processtable').dataTable().fnDestroy();
	$("#dcallhisttable").dataTable({
		"processing": true,
		"paging": false,
		scrollY: '45vh',
		scrollCollapse: true,
		"ordering": false,
		scrollX: true,
	});*/
	
	
});


function pdfview(e) {
	$("#embedpdf").attr('src', 'file/pdf/' + $(e).attr('data-filename') + '#toolbar=0&navpanes=0;readonly=true;');
	var url = $("#embedpdf").attr('src');
	if (url != "") {
		$.ajax({
			url: url,
			type: 'HEAD',
			error: function() {
				swal('OOPS!', "File doesn't exists!", 'error');
			},
			success: function() {
				viewcountupdate($(e).attr('data-cinno'), $(e).attr('data-dcall_no'));
			}
		});
	}




}

function viewcountupdate(cinno, dcall_no) {


	var oMyForm = new FormData();
	oMyForm.append("cinno", cinno);
	oMyForm.append("dcall_no", dcall_no);

	$.ajax({
		url: "viewcountupdate",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);
			if (json.status == 'Success') {

				$("#pdfModal").modal('toggle');
			}
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});


}





function print(e) {
	$("#embedpdf").attr('src', 'file/pdf/' + $(e).attr('data-filename'));
	var url = $("#embedpdf").attr('src');;
	if (url != "") {
		$.ajax({
			url: url,
			type: 'HEAD',
			error: function() {
				swal('OOPS!', "File doesn't exists!", 'error');
			},
			success: function() {
				//viewcountupdate($(e).attr('data-cinno'));
				let objFra = document.getElementById('embedpdf');
				objFra.contentWindow.focus();
				objFra.contentWindow.print();
				printcountupdate($(e).attr('data-cinno'), $(e).attr('data-dcall_no'));
				console.log('tested');
			}
		});
	}




}



var roweditid;
$('#dcallhisttable').on('click', '.vprint', function() {
	roweditid = $(this).closest("tr");


	var cinno = $(this).attr('data-cinno');
	var dcall_no = $(this).attr('data-dcall_no');

	$("#embedpdf").attr('src', 'file/pdf/' + $(this).attr('data-filename'));

	var url = $("#embedpdf").attr('src');;

	if (url != "") {
		$.ajax({
			url: url,
			type: 'HEAD',
			error: function() {
				swal('OOPS!', "File doesn't exists!", 'error');
			},
			success: function() {
				//viewcountupdate($(e).attr('data-cinno'));

				printcountupdate(cinno, dcall_no);
				console.log('tested');
			}
		});
	}

});

function printcountupdate(cinno, dcall_no) {


	var oMyForm = new FormData();
	oMyForm.append("cinno", cinno);
	oMyForm.append("dcall_no", dcall_no);

	$.ajax({
		url: "printcountupdate",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);
			if (json.status == 'Success') {
				let objFra = document.getElementById('embedpdf');
				objFra.contentWindow.focus();
				objFra.contentWindow.print();



				if (json.printcou != null && json.printcou != undefined) {
					roweditid.find('#infoprint').text(String.fromCharCode(9989));
					roweditid.find('#infoprint').append("<strong class='printcou'>  (" + json.printcou + ")</strong>");

					if (json.printcou == 1) {
						$('.pendingprint').text(Number($('.pendingprint').text()) - 1);
						$('.printed').text(Number($('.printed').text()) + 1);
					}

				} else {
					roweditid.find('#infoprint').text(String.fromCharCode(10060));
				}



			}
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});


}
function sendemail(e) {
	$('#filename').val($(e).attr('data-full_path'));
	$('#item_id').val($(e).attr('data-itemid'));
	$('#dcallno').val($(e).attr('data-dcall_no'));
	$('#cinno').val($(e).attr('data-cinno'));
	$('#recp_name').val($(e).attr('data-recp_name'));

	if ($(e).attr('data-mail1') == undefined || $(e).attr('data-mail1') == null) {
		$('#email1').val('--NIL--');
	} else {
		$('#email1').val($(e).attr('data-mail1'));
	}


	if ($(e).attr('data-mail2') == undefined || $(e).attr('data-mail2') == null) {
		$('#email2').val(null);
	} else {
		$('#email2').val($(e).attr('data-mail2'));
	}

	$('#articleid').text($(e).attr('data-itemid'));
	$('#dcallnos').text($(e).attr('data-dcall_no'));



	var oMyForm = new FormData();

	oMyForm.append("cinno", $(e).attr('data-cinno'));

	$.ajax({
		url: "getdcallqrygendetail",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);
			if (json.status == 'Success') {


				if (json.mail_to == undefined || json.mail_to == null) {
					$('#email1').val('--NIL--');
				} else {
					$('#email1').val(json.mail_to);
				}


				if (json.mail_cc == undefined || json.mail_cc == null) {
					$('#email2').val(null);
				} else {
					$('#email2').val(json.mail_cc);
				}

				if (json.mobile_no_1 == undefined || json.mobile_no_1 == null) {
					$('#mobile1').val('--NIL--');
				} else {
					$('#mobile1').val(json.mobile_no_1);
				}


				if (json.mobile_no_2 == undefined || json.mobile_no_2 == null) {
					$('#mobile2').val(null);
				} else {
					$('#mobile2').val(json.mobile_no_2);
				}
				$('#emailmodal').modal('show');
			}
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});



};



function smsmodal(e) {
	$('#smsfilename').val($(e).attr('data-full_path'));
	$('#smsitem_id').val($(e).attr('data-itemid'));
	$('#smsdcallno').val($(e).attr('data-dcall_no'));
	$('#smscinno').val($(e).attr('data-cinno'));
	$('#smsrecp_name').val($(e).attr('data-recp_name'));


	var oMyForm = new FormData();

	oMyForm.append("cinno", $(e).attr('data-cinno'));

	$.ajax({
		url: "getdcallqrygendetail",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);
			if (json.status == 'Success') {


				if (json.mobile_no_1 == undefined || json.mobile_no_1 == null) {
					$('#mobile1').val('--NIL--');
				} else {
					$('#mobile1').val(json.mobile_no_1);
				}


				if (json.mobile_no_2 == undefined || json.mobile_no_2 == null) {
					$('#mobile2').val(null);
				} else {
					$('#mobile2').val(json.mobile_no_2);
				}
				$('#smsmodal').modal('show');
			}
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});

};


function validateQuery() {
	var error = 0;

	if ($("#email2").val().trim() != '') {
		$("#email2").next().remove()
		var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!regex.test($("#email2").val().trim())) {
			error = error + 1;
			$("#emailHelp").remove();
			$("#email2").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">Valid Email Id Only</small>');
		}
	} else if ($("#email2").val().trim() == '') {
		error = error + 1;
		$("#emailHelp").remove();
		$("#email2").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">Please Enter Email Id</small>');
	}
	return error;
}



$('#dcallhisttable').on('click', '.mail', function() {
	roweditid = $(this).closest("tr");
	$("#emailHelp").remove();
	$("#mobileHelp").remove();


	sendemail(this);

});


function submitQuery() {
	var error = 0;
	var error1 = 0;
	error = validateQuery();
	error1 = smsvalidateQuery();
	showLoader();
	if (error == 0 || error1 == 0) {

		$("#emailHelp").remove();
		$("#mobileHelp").remove();
		
		var oMyForm = new FormData();
		oMyForm.append("filename", $('#filename').val());
		oMyForm.append("item_id", $('#item_id').val());
		oMyForm.append("mobile", $('#mobile2').val());

		oMyForm.append("dcallno", $('#dcallno').val());
		oMyForm.append("email", $('#email2').val());
		oMyForm.append("cinno", $('#cinno').val());
		oMyForm.append("recp_name", $('#recp_name').val());
		oMyForm.append("din1", $('#din1').val());
		$.ajax({
			url: "sendmail",
			global: false,
			data: oMyForm,
			dataType: 'text',
			processData: false,
			contentType: false,
			type: 'POST',
			success: function(data) {
				var marker = JSON.stringify(data);
				var json = jQuery.parseJSON(marker);
				json = JSON.parse(json);
				if (json.status == 'Success') {
					swal("Success!", "Mail & Sms has been Sent successfully!", "success").then((value) => {

						$('#emailmodal').modal('hide');


						if (json.smscou != null && json.smscou != undefined) {
							$('#infosms').text(String.fromCharCode(9989));
							$('#infosms').append("<strong class='smscou'>  (" + json.smscou + ")</strong>");
							if (json.smscou == 1) {
								$('.smscount').text(Number($('.smscount').text()) - 1);
							}
						} else {
							$('#infosms').text(String.fromCharCode(10060));
						}


						if (json.emailcou != null && json.emailcou != undefined) {
							$('#infomail').text(String.fromCharCode(9989));
							$('#infomail').append("<strong class='emailcou'>  (" + json.emailcou + ")</strong>");
							if (json.emailcou == 1) {
								$('.mailcount').text(Number($('.mailcount').text()) - 1);
							}


						} else {
							$('#infomail').text(String.fromCharCode(10060));
						}

						/*if (json.smscou != undefined && json.smscou != null) {
							roweditid.find('.smscou').text(' (' + json.smscou + ')');
						}
						if (json.emailcou != undefined && json.emailcou != null) {
							roweditid.find('.emailcou').text(' (' + json.emailcou + ')');
						}*/
					});
				}
			},
			error: function(rs, e) {
				swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			}
		});
	}
hideLoader();
}



function smsvalidateQuery() {
	var error = 0;
	if ($("#mobile2").val().trim() != '') {
		$("#mobile2").next().remove()
		if ($("#mobile2").val().length !== 10) {
			error = error + 1;
			$("#mobileHelp").remove();
			$("#mobile2").closest('div').append('<small id="mobileHelp" class="form-text text-muted" style="color:red !important;">Mobile Number must be in length of 10 digits</small>');
		}
	}
	else if ($("#mobile2").val().trim() == '') {
		error = error + 1;
		$("#mobileHelp").remove();
		$("#mobile2").closest('div').append('<small id="mobileHelp" class="form-text text-muted" style="color:red !important;">Please Enter Mobile Number</small>');
	}
	return error;
}


function itemidclick(e) {


	var oMyForm = new FormData();
	oMyForm.append("cinno", $(e).attr('data-cinno'));

	$.ajax({
		url: "pdfinfo",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);
			if (json.status == 'Success') {

				$('#pdfinfoModal').modal('show');
				$('#infoitemid').text(json.item_ID);
				$('#mobilenum').val(json.mobile_no_2);
				$('#gmailid').val(json.mail_cc);

				$('.printcou').remove();
				$('.emailcou').remove();
				$('.smscou').remove();

				if (json.printcou != null && json.printcou != undefined) {
					$('#infoprint').text(String.fromCharCode(9989));
					$('#infoprint').append("<strong class='printcou'>  (" + json.printcou + ")</strong>");
				} else {
					$('#infoprint').text(String.fromCharCode(10060));
				}

				if (json.smscou != null && json.smscou != undefined) {
					$('#infosms').append("<strong class='smscou'>  (" + json.smscou + ")</strong>");
				}

				if (json.emailcou != null && json.emailcou != undefined) {
					$('#infomail').append("<strong class='emailcou'>  (" + json.emailcou + ")</strong>");
				}
			}
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});



}

var fromdate = $("#reloadfromdate").val();
var todate = $("#reloadtodate").val();



/*$("#fromdate").datepicker({
	dateFormat: "dd/mm/yy",
	onSelect: function(date) {
		$("#todate").datepicker('option', 'minDate', date);

	}
});
$("#todate").datepicker({
	dateFormat: "dd/mm/yy",
});

$('#fromdate').datepicker('setDate', todate);
$("#todate").datepicker('option', 'minDate', todate);
$('#todate').datepicker('setDate', todate);*/



 var currentDate = new Date();  
$("#fromdate").datepicker({
	dateFormat: "dd/mm/yy",
	 maxDate: currentDate,
	
	onSelect: function(date) {
		$("#todate").datepicker('option', 'minDate', date);

	}
});
$("#todate").datepicker({
	dateFormat: "dd/mm/yy",
	 maxDate: currentDate,
      onSelect: function(date) {
		$("#fromdate").datepicker('option', 'maxDate', date);

	}
});



$('#fromdate').datepicker('setDate', todate);
$("#todate").datepicker('option', 'minDate', todate);
$('#todate').datepicker('setDate', todate);


/*$("#dcallfromdate").datepicker({
	dateFormat: "dd/mm/yy",
	onSelect: function(date) {
		$("#dcalltodate").datepicker('option', 'minDate', date);

	}
});
$("#dcalltodate").datepicker({
	dateFormat: "dd/mm/yy",
});

$('#dcallfromdate').datepicker('setDate', moment(new Date()).subtract(3, 'months').format("DD/MM/YYYY"));
$("#dcalltodate").datepicker('option', 'minDate', todate);
$('#dcalltodate').datepicker('setDate', todate);*/

  $("#dcallfromdate").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate: currentDate, 
	onSelect: function(date) {
		$("#dcalltodate").datepicker('option', 'minDate', date);

	}
});
$("#dcalltodate").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate: currentDate, 
	onSelect: function(date) {
		$("#dcallfromdate").datepicker('option', 'maxDate', date);

	}
});

$('#dcallfromdate').datepicker('setDate', moment(new Date()).subtract(3, 'months').format("DD/MM/YYYY"));
$("#dcalltodate").datepicker('option', 'minDate', todate);
$('#dcalltodate').datepicker('setDate', todate);


/*$("#periodselect").click(function() {
	$("#periodselectdiv").css('display', 'block');
	$("#historystart").css('display', 'none');
	$("#periodspeedpostdiv").css('display', 'none');
	$("#periodspeedpoststatusdiv").css('display', 'none');
	filterrecord();
});
*/


function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	});
}

 function todayrecord() {
$("#periodspeedpoststatusdiv").css('display', 'none');
	$("#periodselectdiv").css('display', 'none');
	 $("#historystart").css('display', 'none');
    $("#periodspeedpostdiv").css('display', 'none');
	showLoader();
	var oMyForm = new FormData();
	oMyForm.append("todate", $('#todate').val());

	$.ajax({
		url: "pushdcalltodayrecord",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			$('#pushdcallshow').replaceWith(data);
			
			

			hideLoader();
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			hideLoader();
		}
	});

}

 function pendingprint() {
	$("#periodspeedpoststatusdiv").css('display', 'none');
	 $("#periodselectdiv").css('display', 'none');
	 $("#historystart").css('display', 'none');
     $("#periodspeedpostdiv").css('display', 'none');
	showLoader();
	var oMyForm = new FormData();
	oMyForm.append("fromdate", $('#fromdate').val());
	oMyForm.append("todate", $('#todate').val());
	
	$.ajax({
		url: "pendingprint",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			$('#pushdcallshow').replaceWith(data);
			hideLoader();	
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			hideLoader()
		}});
}


function filterrecord() {
		$("#historystart").css('display', 'none');
	$("#periodspeedpostdiv").css('display', 'none');
	$("#periodspeedpoststatusdiv").css('display', 'none');
	$("#periodselectdiv").css('display', 'block');
	showLoader();
	var oMyForm = new FormData();
	oMyForm.append("fromdate", $('#fromdate').val());
	oMyForm.append("todate", $('#todate').val());

	$.ajax({
		url: "pushdcallfilterrecord",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			$('#pushdcallshow').replaceWith(data);
			hideLoader();
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			hideLoader()
		}
	});

}

function sentdetail(e, type) {

	showLoader();
	var oMyForm = new FormData();
	oMyForm.append("dcall_no", $(e).attr('data-dcall_no'));
	oMyForm.append("type", type);

	$.ajax({
		url: "dcallemailandsmsdetails",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			$('#emailsmsdetail').replaceWith(data);
			var str = "<p style=font-weight:lighter;margin-bottom:0;>" + 'Article ID - ' + $(e).attr('data-itemid') + "</p><p style=font-weight:lighter;margin-bottom:0;>" + 'DCALL Letter No  - ' + $(e).attr('data-dcall_no') + "</p><p style=font-weight:lighter;margin-bottom:0;>" + 'DCALL Letter Date - ' + $(e).attr('data-gen_dt') + "</p>";
			$('#infotitle').text(type + ' Information ').append(str);
			hideLoader();
			$("#smsinfoModal").modal('toggle');
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			hideLoader()
		}
	});

}

/*$("#dcallfrom").datepicker({
	dateFormat: "dd/mm/yy",
	onSelect: function(date) {
		$("#dcallto").datepicker('option', 'minDate', date);

	}
});
$("#dcallto").datepicker({
	dateFormat: "dd/mm/yy",
});

$('#dcallfrom').datepicker('setDate', moment(new Date()).subtract(1, 'months').format("DD/MM/YYYY"));
$("#dcallto").datepicker('option', 'minDate', todate);
$('#dcallto').datepicker('setDate', todate);*/


$("#dcallfrom").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate: currentDate,
	onSelect: function(date) {
		$("#dcallto").datepicker('option', 'minDate', date);

	}
});
$("#dcallto").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate: currentDate,
	onSelect: function(date) {
		$("#dcallfrom").datepicker('option', 'maxDate', date);

	}
});

$('#dcallfrom').datepicker('setDate', moment(new Date()).subtract(1, 'months').format("DD/MM/YYYY"));
$("#dcallto").datepicker('option', 'minDate', todate);
$('#dcallto').datepicker('setDate', todate);


//$("#historystart").on("change", function() {
function historystarts() {
	/*var val = $('#historystart').val();
	console.log(val + ' Month Selected');

	if (val != '' && val != undefined && val != null) {*/


		showLoader();
		var oMyForm = new FormData();
		/*oMyForm.append("month", val);
		oMyForm.append("order", 'asc');
		*/
		oMyForm.append("fromdate", $('#dcallfrom').val());
	oMyForm.append("todate", $('#dcallto').val());
		oMyForm.append("order", 'asc');

		$.ajax({
			url: "pushdcallfilterhistory",
			global: false,
			data: oMyForm,
			dataType: 'text',
			processData: false,
			contentType: false,
			type: 'POST',
			success: function(data) {
				
				
				$('#pushdcallshow').replaceWith(data);
				hideLoader();
			/*	$("#dcallhisttable").DataTable().column(17).visible(false);*/

				
				/*$("#dcallhisttable").DataTable().column(11).visible(true);
				
				$("#dcallhisttable").DataTable().column(12).visible(true);
				
				$("#dcallhisttable").DataTable().column(13).visible(true);
				
				$("#dcallhisttable").DataTable().column(14).visible(true);
				
				$("#dcallhisttable").DataTable().column(15).visible(true);*/
				
				/*var oneDate = moment($('#historystart').val(), 'YYYY-MM');
   			var monthName = oneDate.format('MMMM - YYYY');

				$('#statusmnth').text(monthName);
				*/
			},
			error: function(rs, e) {
				swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
				hideLoader()
			}
		});
}
	/*}*/

//});
$("#History").click(function() {
	$("#periodselectdiv").css('display', 'none');
//	$("#historystart").css('display', 'inline');
$("#historystart").css('display', 'block');
	$("#periodspeedpostdiv").css('display', 'none');
	$("#periodspeedpoststatusdiv").css('display', 'none');
	//$("#historystart").trigger("change");
});
/*function historystart(){
	$("#historystart").trigger("change");
}*/


$(".btnhighlight").on("click", function() {
	$(".btnhighlight").css('background-color', '#17a2b8');
	$(".btnhighlight1").css('background-color', 'darkslategrey');
	$("#periodspeedpostdiv").css('display', 'none');
	$(this).css('background-color', 'darkgreen');
	hideLoader();
});

$(".btnhighlight1").on("click", function() {
	$(".btnhighlight").css('background-color', '#17a2b8');
	$(".btnhighlight1").css('background-color', 'darkslategrey');
	$(this).css('background-color', 'darkgreen');
});



$("#speedpost").click(function() {
	$("#periodselectdiv").css('display', 'none');
	 $("#historystart").css('display', 'none');
$("#periodspeedpoststatusdiv").css('display', 'none');
	$("#periodspeedpostdiv").css('display', 'block');
	speedpostrecord();
});
function speedpostrecord() {
	
	/* $("#periodselectdiv").css('display', 'none');
	 $("#historystart").css('display', 'none');*/

	showLoader();
	var oMyForm = new FormData();
	oMyForm.append("fromdate", $('#dcallfromdate').val());
	oMyForm.append("todate", $('#dcalltodate').val());

	$.ajax({
		url: "speedpostrecord",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			$('#pushdcallshow').replaceWith(data);
			hideLoader();
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			hideLoader()
		}
	});

}


$("#updatespeedpostno").click(function() {
	updatespeedpostno();
});

function updatespeedpostno() {
	
	 $("#periodselectdiv").css('display', 'none');
	 $("#historystart").css('display', 'none');

	showLoader();
	var oMyForm = new FormData();
	oMyForm.append("fromdate", $('#dcallfromdate').val());
	oMyForm.append("todate", $('#dcalltodate').val());

	$.ajax({
		url: "updatespeedpostno",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			$('#pushdcallshow').replaceWith(data);
			hideLoader();
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			hideLoader()
		}
	});

}



	
/*$('#speedpostnostatus').on('click', function() {
$("#periodselectdiv").css('display', 'none');
	$("#periodspeedpostdiv").css('display', 'none');
	$("#historystart").css('display', 'none');
	showLoader();
	var oMyForm = new FormData();
	oMyForm.append("fromdate", $('#dcallfromdate').val());
	oMyForm.append("todate", $('#dcalltodate').val());

	$.ajax({
		url: "speedpostrecordstatus",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			$('#pushdcallshow').replaceWith(data);
			hideLoader();
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			hideLoader();
		}
	});
       
});*/

/*$("#dcallfrmdt").datepicker({
	dateFormat: "dd/mm/yy",
	onSelect: function(date) {
		$("#dcalltodt").datepicker('option', 'minDate', date);

	}
});
$("#dcalltodt").datepicker({
	dateFormat: "dd/mm/yy",
});

$('#dcallfrmdt').datepicker('setDate', moment(new Date()).subtract(2, 'months').format("DD/MM/YYYY"));
$("#dcalltodt").datepicker('option', 'minDate', todate);
$('#dcalltodt').datepicker('setDate', todate);*/



   $("#dcallfrmdt").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate: currentDate,
	onSelect: function(date) {
		$("#dcalltodt").datepicker('option', 'minDate', date);

	}
});
$("#dcalltodt").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate: currentDate,
	onSelect: function(date) {
		$("#dcallfrmdt").datepicker('option', 'maxDate', date);

	}
});

$('#dcallfrmdt').datepicker('setDate', moment(new Date()).subtract(2, 'months').format("DD/MM/YYYY"));
$("#dcalltodt").datepicker('option', 'minDate', todate);
$('#dcalltodt').datepicker('setDate', todate);
   

function speedpostrecordstatusV() {
$("#historystart").css('display', 'none');
$("#periodselectdiv").css('display', 'none');
$("#periodspeedpostdiv").css('display', 'none');	
$("#periodspeedpoststatusdiv").css('display', 'block');
	//$("#periodselectdiv").css('display', 'none');
	//$("#historystart").css('display', 'none'); 
	//$("#periodspeedpostdiv").css('display', 'none');
	showLoader();
	var oMyForm = new FormData();
	oMyForm.append("fromdate", $('#dcallfrmdt').val());
	oMyForm.append("todate", $('#dcalltodt').val());
console.log("before ajax")
	$.ajax({
			url: "speedpostrecordstatus",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(data) {
			$('#pushdcallshow').replaceWith(data);
			hideLoader();
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			hideLoader();
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