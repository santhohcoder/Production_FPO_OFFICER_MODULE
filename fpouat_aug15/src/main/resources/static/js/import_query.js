$(document).ready(function() {
	$('.nxtbtn').attr("disabled", false);
});

$(document).ready(function() {
	if ($('#totDuty').val() > 500000 && "PAC" == $('#role').val()) {
		$("#aclWarningMsgQry").modal('show');
		$(".deptcmtsApr").attr("disabled", true);
		$(".deptcmtsAcl").show();
		$("#aclMsg").show();
		
	}
});

function showPrevious(){
	$('#additionalQueryModal').show();
}

function redirect(page) {
	window.location.href = "" + page + "?id=" + $('#cinno').val();
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



function additionalraise() {
	
$('#addqueryinfomodal').modal('show');
   
} 

$("#addqueryinfobtn").click(function() {
	$('#addqueryinfomodal').modal('hide');
	addQuery()
});



function addQuery() {
	$('#additionalQueryModal').modal('toggle');
	$("#additionalQueryForm").find("input, textarea").val("");
	$("#additionalQueryForm").find('small').remove();
	fetchAdditionQueryDetails($("#cinno").val());
}

function fetchAdditionQueryDetails(cinNo) {
	$.ajax({
		url: "getArticleAwaitingQuery?cinNo=" + $("#cinno").val() + "&qrytype=P",
		type: "get",
		success: function(data) {
			if (data.status == 'Success') {
				$("#dinNo").val(data.dinNo);
				$("#exampleModalLongTitle").text('Additional Query ');
				$("#mobile").val(data.fpoQuery.QRY_MOBILENO);
				$("#email").val(data.fpoQuery.QRY_EMAILID);

				/*if(data.fpoQuery.QRY_DEPCMTS_APR != null && data.fpoQuery.QRY_DEPCMTS_APR != 'undefined'){
					$("#additionquery").val(data.fpoQuery.QRY_DEPCMTS_APR);
				}else if(data.fpoQuery.QRY_DEPCMTS_ACL != null && data.fpoQuery.QRY_DEPCMTS_ACL != 'undefined'){
					$("#additionquery").val(data.fpoQuery.QRY_DEPCMTS_ACL);
				}*/
				$("#additionquery").val(data.fpoQuery.QRY_DESC);
				$("#additionalQueryForm").find("input, textarea").attr("disabled", "disabled");
				$("#submitQueryBtn").remove();
				$("#updateQueryBtn").remove();
				$("#closebtn").remove();
				$("#editQueryBtn").remove();
				$("#senbackbtn").remove();
				 if ($("#role").val() == 'PAC') {
					  $("#FooterAdditionalQuery").append(
					    '<button type="button" class="btn btn-primary" id="senbackbtn" onclick="sendback()">Send Back to AO</button>' +
					    '<button type="button" class="btn btn-success" id="editQueryBtn" onclick="editQuery()">Edit Query</button>'
					  );
					} else if ($("#role").val() == 'PAO') {
					  $("#FooterAdditionalQuery").append(
					    '<button type="button" class="btn btn-primary" id="senbackbtn" onclick="sendback()">Send Back to AC</button>' +
						'<button type="button" class="btn btn-success" id="editQueryBtn" onclick="editQuery()">Edit Query</button>'
					  );
				}
}
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});
}

function editQuery() {
	swal({
		title: "Do you want to edit the Query ?",
		icon: "warning",
		buttons: ["No", "Yes"],
		dangerMode: false,
	})
		.then((willDelete) => {
			if (willDelete) {
				$("#senbackbtn").remove();
				$("#additionalQueryForm").find("input, textarea").removeAttr("disabled");
				$("#editQueryBtn").remove();
				$("#FooterAdditionalQuery").append('<button type="button" class="btn btn-success" id="updateQueryBtn" onclick="updateQuery()">NEXT</button>')
			}
		});
}

function sendAprBack(value) {

	var role = "PAC";
	var developerData = {};

	developerData['cin_NO'] = $("#inputPassword").val();
	developerData['item_ID'] = $('#itemId').val();
	developerData['cmts'] = $('#sndbackcmts').val();
	developerData['role'] = role;
	developerData['entry_DT'] = new Date();
	developerData['off_ID'] = $('#offId').val();

	var resObj = $.ajax({
		url: 'sendBackApr',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			location.href = "import_list";
		},
		fail: function(rs, e) {
			alert("Error in Order");
		}
	});

};

function sendAclBack(value) {

    var role = "PAO";
    var developerData = {};

    developerData['cin_NO'] = $("#inputPassword").val();
    developerData['item_ID'] = $('#itemId').val();
    developerData['cmts'] = $('#aprsndbackcmts').val();
    developerData['role'] = role;
    developerData['entry_DT'] = new Date();
    developerData['off_ID'] = $('#offId').val();

    var resObj = $.ajax({
        url: 'sendBackAcl',
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
        success: function(developerDatas) {
            window.location.href = "process_ead";
        },
        fail: function(rs, e) {
            alert("Error in Order");
      }
    });

};

function successPAC(e) {
	if (document.getElementById(e).value === "") {
		document.getElementById('lockedV').disabled = true;
	} else {
		document.getElementById('lockedV').disabled = false;
	}
}

function success1(e) {
	if (document.getElementById(e).value === "") {
		document.getElementById('lockeded').disabled = true;
	} else {
		document.getElementById('lockeded').disabled = false;
	}
}

function sendback() {
    swal({
        title: "Do you want to send back the Query?",
        icon: "warning",
        buttons: ["No", "Yes"],
        dangerMode: false,
    })
    .then((willSendBack) => {
        if (willSendBack) {
		 $('#additionalQueryModal').hide();
	if($("#role").val() == 'PAO')
		$('#sendbacktoac').modal('toggle');
    else if($("#role").val() == 'PAC')
			 $('#sendbacktoao').modal('toggle');
		}
    });
}
function updateQuery() {
	var error = 0;
	error = validateQuery();
	if (error == 0) {
		
		addlqrydetail();
	}
}


function addlqrydetail() {
	$.ajax({
		url: "getaddlqrydetail?cinNo=" + $("#cinno").val(),
		type: "get",
		success: function(data) {
			if (data.status == 'Success') {
				var i = 0;

				data.fpoaddlQuery.forEach(function(result, index) {

					if (result.INP_REQ == '2') {
						if (i == 1) {
							$("#addlquery1" + result.QRY_DEF_SLNO).val(result.QRY_DESC);
							$("#addlquery1" + result.QRY_DEF_SLNO).removeAttr('readonly');

							i = 0;
						} else {
							$("#addlquery" + result.QRY_DEF_SLNO).val(result.QRY_DESC);
							$("#addlcheckbox" + result.QRY_DEF_SLNO).prop('checked', true);
							$("#addlquery" + result.QRY_DEF_SLNO).removeAttr('readonly');

							i++;
						}

					} else {
						$("#addlquery" + result.QRY_DEF_SLNO).val(result.QRY_DESC);
						$("#addlcheckbox" + result.QRY_DEF_SLNO).prop('checked', true);
						$("#addlquery" + result.QRY_DEF_SLNO).removeAttr('readonly');
					}
				});
			}

			submitQuery();
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});
}

function submitQuery() {
	var error = 0;
	error = validateQuery();
	if (error == 0) {
		/*$.ajax({
			url: "saveAdditionalQuery?cinNo=" + $("#inputPassword").val() + "&" + $("#additionalQueryForm").serialize(),
			type: "post",
			success: function(data) {
				if (data.status == 'Success') {
					swal("Success!", "Your Query has been raised successfully!", "success").then((value) => {

					   // generateDCallLetter($("#inputPassword").val());
							//movasstoqrydata() ;
					 $('#replymodal').modal('toggle');
					});
					$('#additionalQueryModal').modal('toggle');
				}
			},
			error: function(rs, e) {
				swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			}
		});*/
		$("#addlquery1").val($("#additionquery").val());
		$("#addlquery1").removeAttr('readonly');
		$('#additionalQueryModal').modal('toggle');
		$('#replymodal').modal('toggle');
	}

}

function onChangeEvent(obj) {
	$(obj).next().remove()
}
function validateQuery() {
	var error = 0;
	if ($("#additionquery").val().trim() == '') {
		error = error + 1;
		$("#additionquery").next().remove()
		$("#additionquery").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">Query Required</small>');
	}
	if ($("#email").val().trim() != '') {
		$("#email").next().remove()
		var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!regex.test($("#email").val().trim())) {
			error = error + 1;
			$("#email").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">Valid Email Id Only</small>');
		}
	}
	if ($("#mobile").val().trim() != '') {
		$("#mobile").next().remove()
		if ($("#mobile").val().length !== 10) {
			error = error + 1;
			$("#mobile").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">Mobile Number must be in length of 10 digits</small>');
		}
	}
/*	if ($("#dinNo").val().trim() == '') {
		error = error + 1;
		$("#dinNo").next().remove()
		$("#dinNo").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">Document Identification Number Required</small>');
	} 
	else 
	{
		/*var dinReg = /CBIC-[0-9]{4}0[1-9]|1[0-2][0-9]{4}[0-9]{6}/;
		var numberReg = /^[0-9]+$/;
		var din = $("#dinNo").val().trim();
		var inputVal = new Array(din);
		if (!dinReg.test(din)) {
			$("#dinNo").next().remove()
			$("#dinNo").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">CBIC-YYYYMMZCDRNNNNNN only</small>');
			error = error + 1;
		} else {
			var currentTime = new Date();
			var yearFromSystem = currentTime.getFullYear();
			var res = din.split("-");
			var yearFromUser = res[1].substring(0, 4);

			if (yearFromUser > yearFromSystem) {
				$("#dinNo").next().remove()
				$("#dinNo").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">CBIC-YYYYMMZCDRNNNNNN only</small>');
				error = error + 1;
			}
		}*/		
/*	var dinReg = /[0-9]{8}[A-Z]{2}[0-9]{7}[A-Z]{3}/;

	var din =  $("#dinNo").val().trim();

	
	if (!dinReg.test(din)) {
		$("#dinNo").next().remove()
		$("#dinNo").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">e.g. : 20220272MR0000222FBD</small>');
		error = error + 1;
	}else {

		var currentTime = new Date();
		var yearFromSystem = currentTime.getFullYear();
		
		var res = din.substring(0, 4);
		
		
			if (res > yearFromSystem) {
				$("#dinNo").next().remove()
				$("#dinNo").closest('div').append('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">CBIC-e.g. : 20220272MR0000222FBD</small>');
				error = error + 1;
			}
		 

		
	}

	}*/
	return error;
}

function cancelModal(){
	$('#exampleModalcancel').modal('toggle');
}

function deleteAddlquery(value) {
	var developerData = {};
	developerData['CIN_NO'] = $("#cinno").val();
	console.log($("#cinno").val())
	var resObj = $.ajax({
		url: 'deleteAddquery',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
		$('#exampleModal1').modal('hide');
		$('#firstBtn').hide();
		location.href = localStorage.getItem("currentUrl")
		},
		fail: function(rs, e) {
			alert("Error in Order");
		}
	});
}

function switchPopUp() {
	$("#additionquery").val($("#addlquery1").val());
	$('#additionalQueryModal').modal('toggle');
	$('#replymodal').modal('toggle');
}

function checkQueryRaised() {
	if ($("#addlquery1").val().trim() == '') {
		$("#addlquery1").next().remove();
		$("#addlquery1").after('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">Query Required</small>');
		return false;
	} else {
		$("#addlquery1").next().remove();
		return true;
	}
}

$(document).on('change', "#addlquery1", function() {
	checkQueryRaised();
});


function addlraiseqry(data) {
console.log(data)

	var check = checkQueryRaised();

	if (!check)
		return false;

	var oMyForm = new FormData();
	oMyForm.append("cinNo", $(data).attr('data-cin'));
	oMyForm.append("itemId", $(data).attr('data-itemid'));


	oMyForm.append("query", $('#additionquery').val());
	oMyForm.append("email", $('#email').val());
	oMyForm.append("mobile", $('#mobile').val());
	oMyForm.append("dinNo", $('#dinNo').val());

	oMyForm.append("qrytype", 'P');

	var res = 0;

	$("#replytable tbody tr input:checkbox:checked").each(function(i) {


		console.log($(this).closest("tr").find('.blankline').val());
		console.log($(this).closest("tr").find('li').attr('data-inpreq'));

		oMyForm.append("reply[" + i + "].inpReq", $(this).closest("tr").find('li').attr('data-inpreq'));

		oMyForm.append("reply[" + i + "].slno", $(this).val());

		oMyForm.append("reply[" + i + "].desc1", $(this).closest("tr").find('.blankline').val());

		if ($(this).val() == '3') {
			res = res + 0;
		} else if ($(this).closest("tr").find('.blankline').val() != null && $(this).closest("tr").find('.blankline').val() != undefined && $(this).closest("tr").find('.blankline').val() != '') {
			res = res + 0;
		} else {
			res = res + 1;
		}

		if ($(this).closest("tr").find('li').attr('data-inpreq') == '2') {
			oMyForm.append("reply[" + i + "].desc2", $(this).closest("tr").find('.blankline1').val());


			if($(this).val()=='3' || $(this).val()=='5' || $(this).val()=='6'){
				res = res + 0;
			} else if ($(this).closest("tr").find('.blankline1').val() != null && $(this).closest("tr").find('.blankline1').val() != undefined && $(this).closest("tr").find('.blankline1').val() != '') {
				res = res + 0;
			} else {
				res = res + 1;
			}
		}





	});
 $('#totassval').val()
 $('#role').val()
 $('#maxval').val()


var totassvalString = $('#totassval').val();
var maxvalString = $('#maxval').val();

var totassvalFloat = parseFloat(totassvalString);
var maxvalFloat = parseFloat(maxvalString);

	if (res == 0 ) {
		
	if( ( ( totassvalFloat > maxvalFloat) && $('#role').val() == 'PAC' ) || ( ( totassvalFloat < maxvalFloat ) && $('#role').val() == 'PAO' ))
	{
		swal({
			title: "Do you want to generate D-Call letter/Send SMS? Please Confirm",
			icon: "warning",
			buttons: ["No", "Yes"],
			dangerMode: false,
		})
			.then((willDelete) => {
				if (willDelete) {
					showLoader();
					var resObj = $.ajax({
						url: 'raiseqryreply',
						data: oMyForm,
						processData: false,
						contentType: false,
						type: "post",
						success: function(developerDatas) {
							hideLoader();
							$('#replymodal').modal('toggle');
							getpdf();
						},
						fail: function(rs, e) {
							alert("Error in reply");
						}
					});

				}
			});
	} 
	else{
		swal({
			title: "D-call Letter gets generated in AC's role ",
			icon: "warning",
			})
			.then((willDelete) => {
				if (willDelete) {
					showLoader();
					var resObj = $.ajax({
						url: 'raiseqryreply',
						data: oMyForm,
						processData: false,
						contentType: false,
						type: "post",
						success: function(developerDatas) {
							hideLoader();
							$('#replymodal').modal('toggle');
							location.href = "import_list";
							
							//getpdf();
						},
						fail: function(rs, e) {
							alert("Error in reply");
						}
					});

				}
			});
	}
	}
	else {
		swal('OOPS!', 'Please Enter Value For The Selected Categories!', 'error');
	}


}

/*

function addlraiseqryapproval(data) {

	var check = checkQueryRaised();

	if (!check)
		return false;

	var oMyForm = new FormData();
	oMyForm.append("cinNo", $(data).attr('data-cin'));
	oMyForm.append("itemId", $(data).attr('data-itemid'));


	oMyForm.append("query", $('#additionquery').val());
	oMyForm.append("email", $('#email').val());
	oMyForm.append("mobile", $('#mobile').val());
	oMyForm.append("dinNo", $('#dinNo').val());

	oMyForm.append("qrytype", 'P');

	var res = 0;

	$("#replytable tbody tr input:checkbox:checked").each(function(i) {


		console.log($(this).closest("tr").find('.blankline').val());
		console.log($(this).closest("tr").find('li').attr('data-inpreq'));

		oMyForm.append("reply[" + i + "].inpReq", $(this).closest("tr").find('li').attr('data-inpreq'));

		oMyForm.append("reply[" + i + "].slno", $(this).val());

		oMyForm.append("reply[" + i + "].desc1", $(this).closest("tr").find('.blankline').val());

		if ($(this).val() == '3') {
			res = res + 0;
		} else if ($(this).closest("tr").find('.blankline').val() != null && $(this).closest("tr").find('.blankline').val() != undefined && $(this).closest("tr").find('.blankline').val() != '') {
			res = res + 0;
		} else {
			res = res + 1;
		}

		if ($(this).closest("tr").find('li').attr('data-inpreq') == '2') {
			oMyForm.append("reply[" + i + "].desc2", $(this).closest("tr").find('.blankline1').val());


			if($(this).val()=='3' || $(this).val()=='5' || $(this).val()=='6'){
				res = res + 0;
			} else if ($(this).closest("tr").find('.blankline1').val() != null && $(this).closest("tr").find('.blankline1').val() != undefined && $(this).closest("tr").find('.blankline1').val() != '') {
				res = res + 0;
			} else {
				res = res + 1;
			}
		}





	});
	if (res == 0) {
		swal({
			title: "Do you want to generate D-Call letter/Send SMS? Please Confirm",
			icon: "warning",
			buttons: ["No", "Yes"],
			dangerMode: false,
		})
			.then((willDelete) => {
				if (willDelete) {
					showLoader();
					var resObj = $.ajax({
						url: 'raiseqryreply',
						data: oMyForm,
						processData: false,
						contentType: false,
						type: "post",
						success: function(developerDatas) {
							hideLoader();
							$('#replymodal').modal('toggle');
							getpdf();
						},
						fail: function(rs, e) {
							alert("Error in reply");
						}
					});

				}
			});
	} else {
		swal('OOPS!', 'Please Enter Value For The Selected Categories!', 'error');
	}


}
*/

var mailSent;

function getpdf() {
	var oMyForm = new FormData();
	oMyForm.append("cinNo", $("#inputPassword").val());
	oMyForm.append("que", 'P');
	oMyForm.append("din", $('#dinNo').val());
	$.ajax({
		url: "getpdf",
		data: oMyForm,
		processData: false,
		contentType: false,
		type: "post",
		success: function(data) {
			mailSent = data;
			viewEadDCallLetter();

		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});
}


function viewEadDCallLetter() {
	$.ajax({
		url: "getEadDCallLetter?cinNo=" + $("#inputPassword").val(),
		type: "get",
		success: function(data) {
			$("#dcallLetterContent").html(data);
			$("#embedpdf").attr('src', 'file/pdf/' + $('#pdffilename').val() + '#toolbar=0&navpanes=0;readonly=true;');
			$("#pdfModal").modal('toggle');
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});
}

function updatingdata(){
	console.log("successssss")
	
	var developerData = {};
    developerData['cin_NO'] =$('#cinno').val();
 	developerData['dep_comments']=$("#textsend1").val();
    var resObj = $.ajax({
        url: 'movasstoqrydata',
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerDatas) {
 
 					 location.href="import_list";
				
         },
        fail: function(rs, e) {
         alert("Error in Import Assessment");
          }
      });
	$('#exampleModal').modal('hide');
}

// this will work only if the role is PAO and value is le than threshold  and not sent to AC for approval 

function aafCallLetter() {
	showLoader();
	var docNameAAF = localStorage.getItem("docnameaaf")	
	console.log(docNameAAF)
		$.ajax({
                url: "aaf_callletter?"+ $("#aaf-callletter").serialize()+ '&others=' + docNameAAF,
                type: "get",
                success: function(data) {
                /*    $("#dcallLetterContent").html(data);
                	$("#dcallLetterModal").modal('toggle');
                	$('#exampleModal').modal('hide');*/
             	//windows.location.href="import_list";

 hideLoader();

			if($("#succ").val()==1){
				swal("Mail Sent Successfully !","", "success").then((value) => {
 					 $("#dcallLetterContent").html(data);
                    $("#embedpdf").attr('src','file/pdf/'+$('#pdffilename').val() + '#toolbar=0&navpanes=0;readonly=true;');
                	$("#pdfModal").modal('toggle');
                	$('#exampleModal').modal('hide');
				});
			}else{
					$("#dcallLetterContent").html(data);
                    $("#embedpdf").attr('src','file/pdf/'+$('#pdffilename').val() + '#toolbar=0&navpanes=0;readonly=true;');
                	$("#pdfModal").modal('toggle');
                	$('#exampleModal').modal('hide');
			}

 					
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });
		
	} 

// this will work during the close button in d call letter only

$(".scndcheck").click(function() {
	if ($(this).prop('checked') == true) {
		$(this).closest('tr').find('.blankline').removeAttr('readonly');
		$(this).closest('tr').find('.blankline1').removeAttr('readonly');
	} else {
		$(this).closest('tr').find('.blankline').attr('readonly', 'readonly');
		$(this).closest('tr').find('.blankline1').attr('readonly', 'readonly');
	}

});

	
$(".importqry").click(function() {
    
var developerData = {};
   developerData['cin_NO'] =$('#cinno').val();
    var resObj = $.ajax({
        url: 'movasstoqrydata',
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerDatas) {
        location.href="import_list";
          /*swal("Mail Sent Successfully !","", "success").then((value) => {
 					 location.href="import_list";
				});*/
         },
        fail: function(rs, e) {
         alert("Error in Import Assessment");
          }
      });
});

function goBack() {
	window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
}

$("#pdfModal").on("hidden.bs.modal",function(){location.href="import_list";});




function printdcall() {

	printcountupdate($('#cinno').val(), $('#dcallnoid').val());
	let objFra = document.getElementById('embedpdf');
	objFra.contentWindow.focus();
	objFra.contentWindow.print();
}

function downloaddcall() {

	printcountupdate($('#cinno').val(), $('#dcallnoid').val());
	let objFra = document.getElementById('embedpdf');
	objFra.contentWindow.focus();
	objFra.contentWindow.print();

}



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

			}
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});


}

function viewrlyEadDCallLetter() {
        $.ajax({
            url: "getrlyEadDCallLetter?cinNo=" + $("#cinno").val(),
            type: "get",
            success: function(data) {
                 $("#dcallLetterContent").html(data);
                    $("#rlyembedpdf").attr('src','file/pdf/'+$('#pdffilename').val() + '#toolbar=0&navpanes=0;readonly=true;');
                	$("#rlypdfModal").modal('toggle');
            },
            error: function(rs, e) {
                swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
            }
        });
    }

function viewEadQueryReply() {
    	$.ajax({
                url: "viewqueryreply?cinNo="+$("#cinno").val(),
                type: "get",
                success: function(data) {
                    $("#qryreplycontent").html(data);
					$("#qryreplymodal").find('h5').text('VIEW QUERY REPLY & UPLOADED DOCUMENTS');
					$("#previewKycSectionDiv").next().remove();
					$("#qryreplymodal").modal('show');
                	
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });
    }
	
function routeImpQry() {
  location.href = "final_declared?id=" + $("#cinno").val();
};

function goBack() {
	window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
}

    
    function viewAdditionQueryReply(artStage) {
    	$.ajax({
                url: "viewadditionalqueryreply?cinNo="+$("#cinno").val()+"&articleStage="+artStage,
                type: "get",
                success: function(data) {
                    $("#qryreplycontent").html(data);
					$("#qryreplymodal").find('h5').text('VIEW ADDITIONAL QUERY REPLY & UPLOADED DOCUMENTS');
					$("#previewKycSectionDiv").next().remove();
					$("#qryreplymodal").modal('show');
                	
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });
    }


    
    function viewDCallLetter(qrytype) {
    	$("#rlyembedpdf").attr('src','file/pdf/'+$('#additionalDcallFileName').attr(qrytype) + '#toolbar=0&navpanes=0;readonly=true;');
        $("#rlypdfModal").modal('toggle');
    } 
function deleteDataMapped() {
		$("#qryreplycontent").html('');
	}
	
	function exmorder(obj)
	{
  		window.location.href = "imp_order?id="+$(obj).attr("id");
	}
	
	function viewScanReport(obj) {
		$.ajax({
			url: 'articlereportscan?articleId=' + $(obj).attr('articleId'),
			type: "post",
			success: function(data) {
				$("#scanReportBody").html(data);
				$("#scanReportModal").modal('show');
			},
			error: function(rs, e) {
				swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			}
		});
	}
	
	function closeScanReportModal() {
		$("#scanReportBody").html('');
		$("#scanReportModal").modal('toggle');	
	}

