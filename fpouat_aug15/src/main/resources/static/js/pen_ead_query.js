/*function success(e) {
	if (document.getElementById(e).value === "") {
		document.getElementById('lock').disabled = true;
	} else {
		document.getElementById('lock').disabled = false;
	}
}*/

function showLoader() {
    $("#overlay").css("display", "block");
    $('#overlay').height($("body").innerHeight());
}

function hideLoader() {
    setTimeout(function() {
        $("#overlay").css("display", "none");
    }, 500);
}

$(document).ready(function() {
$('.nxtbtn').attr("disabled", false);

	if ($('#alert').text() != null && $('#alert').text() != undefined && $('#alert').text() != '') {
		$('#alertpopupModal').modal('show');
	} else if ($('#localalert').text() != null && $('#localalert').text() != undefined && $('#localalert').text() != '') {
		$('#localalertpopupModal').modal('show');
	}
	
	if ($('#firstBtn').val() == false){
		$('.nxtbtn').attr("disabled", true);
	} else{
		$('.nxtbtn').attr("disabled", false);
	}

});

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
	
	function switchPopUp() {
		$("#additionquery").val($("#addlquery1").val());
		$('#additionalQueryModal').modal('toggle');
	    $('#replymodal').modal('toggle');
	}
	
	function checkQueryRaised() {
		if($("#addlquery1").val().trim()==''){
			$("#addlquery1").next().remove();
			$("#addlquery1").after('<small id="emailHelp" class="form-text text-muted" style="color:red !important;">Query Required</small>');
			return false;
		}else{
			$("#addlquery1").next().remove();
			return true;
		}
	}
	
	$(document).on('change', "#addlquery1", function() {
		checkQueryRaised();
	});
	
	function raiseqryreply(data) {
		
		var res=0;
		var dcall=1;
		console.log($('#role').val());
		console.log($('#totassval').val());
		console.log($('#maxval').val());
		console.log(dcall);
		
if($('#role').val()=="PAO"&&(parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()))){
	dcall=0;
	console.log(dcall);
	}
	var check=checkQueryRaised();
		
	if(!check)
		return false;

    var oMyForm = new FormData();
    oMyForm.append("cinNo", $(data).attr('data-cin'));
    oMyForm.append("itemId", $(data).attr('data-itemid'));


    oMyForm.append("query", $('#additionquery').val());
    oMyForm.append("email", $('#email').val());
    oMyForm.append("mobile", $('#mobile').val());
  //  oMyForm.append("dinNo", $('#dinNo').val());

    oMyForm.append("qrytype", 'N');

		var res=0;
   
	$("#replytable tbody tr input:checkbox:checked").each(function(i){
		

	   console.log($(this).closest("tr").find('.blankline').val());
	   console.log($(this).closest("tr").find('li').attr('data-inpreq'));

    oMyForm.append("reply[" + i + "].inpReq", $(this).closest("tr").find('li').attr('data-inpreq'));

		oMyForm.append("reply[" + i + "].slno", $(this).val());
		
		oMyForm.append("reply[" + i + "].desc1", $(this).closest("tr").find('.blankline').val());
		
		if($(this).val()=='3'){
			res=res+0;
		}else if($(this).closest("tr").find('.blankline').val() !=null && $(this).closest("tr").find('.blankline').val() !=undefined && $(this).closest("tr").find('.blankline').val() !=''){
			res=res+0;
		}else{
			res=res+1;
		}
		
		if($(this).closest("tr").find('li').attr('data-inpreq')=='2'){
			oMyForm.append("reply[" + i + "].desc2", $(this).closest("tr").find('.blankline1').val());
			
		
		if($(this).val()=='3' || $(this).val()=='5' || $(this).val()=='6'){
			res=res+0;
		}else if($(this).closest("tr").find('.blankline1').val() !=null && $(this).closest("tr").find('.blankline1').val() !=undefined && $(this).closest("tr").find('.blankline1').val() !=''){
			res=res+0;
		}else{
			res=res+1;
		}
		}
		
		
		


	});
if(res==0){
	if(dcall==0){
		console.log('check')
			$('#dinValidate').text("Assessable value of the article requires AC's concurrence. Additional D-Call letter gets generated in AC's role'");
			$("#aclClose").hide();
			$("#my_button").show();
			$("#replymodal").hide();
			
			$('.nxtbtn').attr("disabled", false);
		$('#exampleModal').modal('show');
		 var resObj = $.ajax({
        url: 'raiseqryreply',
        data: oMyForm,
        processData: false,
        contentType: false,
        type: "post",
        success: function(developerDatas) {
            $('#replymodal').modal('toggle');
			//getpdf();
        },
        fail: function(rs, e) {
            alert("Error in reply");
        }
    });
		
		
	}
	else{
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
}
else{
	swal('OOPS!', 'Please Enter Value For The Selected Categories!', 'error');
}


}



function additionalapproval(){
	
  console.log("santhosh testing again");
  console.log($('#role').val());
  console.log($('#totassval').val());
  console.log($('#maxval').val());

$('#aclpopupmsg').html("AO has raised the Additional query." + '<BR>' + "AC may alter AO's Additional  Query if required and complete the process.");

 

 $("#aclWarningMsg").modal('show');

  $('#okbtn').on('click', function() {
    addQuery();
  });
/*  if("${addqrygenerated && !replystatus}" && $('#role').val() == 'PAC' && parseFloat($('#totassval').val()) > parseFloat($('#maxval').val())) {
   // $(".sendbtnshow").show();
    
    if(parseInt($("#qryNo").val()) > 0 && ($('#updassdt').val() == null || $('#updassdt').val() == "")) {
      $('#aclpopupmsg').html("AO has raised the Additional query." + '<BR>' + "AC may alter AO's Additional  Query if required and complete the process.");
    } else if(parseInt($("#qryNo").val()) > 0 && ($('#updassdt').val() != null || $('#updassdt').val() != "")) {
      $('#aclpopupmsg').html("AO has done the assessment." + '<BR>' + "AC may alter AO's assessment if required and complete the process.");
    }
	
    $("#aclWarningMsg").modal('show');
	

  }
*/
	
}

function addlqrydetail() {
	 var reg =/<(.|\n)*?>/g; 
 if (reg.test($('#additionquery').val()) == true) {
        swal('OOPS!', 'Special character Not allowed!!', 'error');
		return false
    }
	    $.ajax({
	        url: "getaddlqrydetail?cinNo=" + $("#inputPassword").val(),
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
		         hideLoader();
	            swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
	        }
	    });
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
                    $("#FooterAdditionalQuery").append('<button type="button" class="btn btn-success" id="updateQueryBtn" onclick="updateQuery()">NEXT</button>');
                    addlqrydetail();
                }
            });
    }

function showPrevious(){
	$('#additionalQueryModal').show();
}





function sendback() {
    swal({
        title: "Do you want to send back the Query ?",
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
function success(e) {
    if (document.getElementById(e).value === "") {
        document.getElementById('locked').disabled = true;
    } else {
        document.getElementById('locked').disabled = false;
    }
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
            window.location.href = "process_ead";
        },
        fail: function(rs, e) {
            alert("Error in Order");
        }
    });

};


    function updateQuery() {
        var error = 0;
        error = validateQuery();
        if (error == 0) {
            /*swal({
                    title: "Are you sure to update the Query ?",
                    icon: "warning",
                    buttons: ["No", "Yes"],
                    dangerMode: false,
                })
                .then((willDelete) => {
                    if (willDelete) {
                        submitQuery()
						$('#replymodal').modal('toggle');
                    }
                });*/
            swal({
                title: "Do you want to edit the Query ?",
                icon: "warning",
                buttons: ["No", "Yes"],
                dangerMode: false,
            })
            .then((willDelete) => {
                if (willDelete) {
			addlqrydetail();}
			});
        }
    }

function success1(e) {
    if (document.getElementById(e).value === "") {
        document.getElementById('lock2').disabled = true;
    } else {
        document.getElementById('lock2').disabled = false;
    }
}

function getpdf() {
		 var oMyForm = new FormData();
	    oMyForm.append("cinNo", $("#inputPassword").val());	    
	    oMyForm.append("que", 'N');
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
		        hideLoader();
	            swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
	        }
	    });
	}

function alertview(alert) {

	var oMyForm = new FormData();
	oMyForm.append("itemid", $('#itemId').val());
	oMyForm.append("cinno", $('#inputPassword').val());
	oMyForm.append("level", 'AAA');
	oMyForm.append("alert", alert);


	jQuery.ajax({
		url: "alertview",
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


			if (alert == 'All India' && $('#localalert').text() != null && $('#localalert').text() != undefined && $('#localalert').text() != '') {
				$('#localalertpopupModal').modal('show');
			}




		}
	});



}

$(document).ready(function() {
$('#defualtQuery1').attr("disabled", true);
$('#defualtQuery2').attr("disabled", true);
$('#defualtQuery3').attr("disabled", true);
$('#defualtQuery4').attr("disabled", true);
});

$('#my_button').click(function() {
	setTimeout(function() { $('#exampleModal').modal('hide'); }, 1000);
   /* $('.nxtbtn').attr("disabled", false);*/
});


$(document).ready(function() {
	if ($('#totDuty').val() > 500000 && "PAC" == $('#role').val()) {
		$("#aclWarningMsgQry").modal('show');
		$(".deptcmtsApr").attr("disabled", true);
		$(".deptcmtsAcl").show();
		$("#aclMsg").show();
		
	}
});


$.each($("[id*=textsend]"), function(i, item) {
	var iid = $(this).attr('id');
	var fin = iid.replace('textsend', 'textsend' + (i + 1));
	$(this).attr('id', fin);
});

function goBack() {
	window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
}

function viewDetail1(e) {
	//alert(e.id);
	window.location.href = "pen_order?id=" + e.id;
}

/*function enableNum() {
	document.getElementById("randomNum").disabled = false;
	$(".nxtbtn").attr("disabled", false); 
}*/

var developerData = {};
var resObj = $.ajax({
	url: 'getDefualtQuery',
	data: JSON.stringify(developerData),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(data) {
	//	$("#queryOne").text(data[0]);
	//	$("#queryTwo").text(data[1]);
	//	$("#queryThree").text(data[2]);
	//	$("#queryFour").text(data[3]);
	//	console.log(data);

	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});


function validateForm() {
	/*var dinReg = /CBIC-[0-9]{4}0[1-9]|1[0-2][0-9]{4}[0-9]{6}/;
	var numberReg = /^[0-9]+$/;

	var din = $('#din').val();

	var inputVal = new Array(din);

	var inputMessage = new Array("Document Identification Number");

	$('.error').hide();

	if (inputVal[0] == "") {
		$('#dinErr').after(
			'<span class="error"> Please enter Valid  '
			+ inputMessage[0] + '</span>');
	} else if (!dinReg.test(din)) {
		$('#dinErr').after(
			'<span class="error"> CBIC-YYYYMMZCDRNNNNNN only</span>');
	} else {

		var currentTime = new Date();
		var yearFromSystem = currentTime.getFullYear();
		var res = din.split("-");
		var yearFromUser = res[1].substring(0, 4);

		if (yearFromUser > yearFromSystem)
			$('#dinErr')
				.after(
					'<span class="error"> CBIC-YYYYMMZCDRNNNNNN only</span>');
	}*/
/*	var dinReg = /[0-9]{8}[A-Z]{2}[0-9]{7}[A-Z]{3}/;

	var din = $('#din').val();

	var inputVal = new Array(din);

	var inputMessage = new Array("Document Identification Number");
	if (inputVal[0] == "") {
		$('.error').html('');
		$('#dinErr').after(
			'<span class="error"> Please enter Valid  '
			+ inputMessage[0] + '</span>');
			return false;
	} else if (!dinReg.test(din)) {
		$('.error').html('');
		$('#dinErr').after(
			'<span class="error"> e.g. : 20220272MR0000222FBD</span>');
			return false;
	}else {

		var currentTime = new Date();
		var yearFromSystem = currentTime.getFullYear();
		
		var res = din.substring(0, 4);
		
		
			if (res > yearFromSystem) {
				$('.error').html('');
					$('#dinErr')
						.after(
							'<span class="error"> e.g. : 20220272MR0000222FBD</span>');
				return false;
			}else{
				return true;
			}
		 

		
	}*/

}


/*function validateDin() {
	if ($('#totDuty').val() > 500000 && ("PAO" == $('#role').val() || "" == $('#role').val())){
		$('#dinValidate').text("Assessable value of the article requires AC's concurrence. D-Call letter gets generate in AC's role'");
		$("#aclClose").hide();
		$("#my_button").show();
		$('.nxtbtn').attr("disabled", false);
	} else {
		if (null == $('#din').val() || $('#din').val() == "") {
			$('#dinValidate').text("Do you want to proceed without DIN?");
			$("#aclClose").show();
			$("#my_button").show();
			
		} else {
			$('#dinValidate').text("Do you want to proceed?");
			$("#aclClose").show();
			$("#my_button").show();
			
		}

	}
}*/



function printdcall() {

	printcountupdate($('#inputPassword').val(), $('#dcallnoid').val());
	let objFra = document.getElementById('embedpdf');
	objFra.contentWindow.focus();
	objFra.contentWindow.print();
}

function downloaddcall() {

	printcountupdate($('#inputPassword').val(), $('#dcallnoid').val());
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