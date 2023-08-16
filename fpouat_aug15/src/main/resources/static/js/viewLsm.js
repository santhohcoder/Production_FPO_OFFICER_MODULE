function validatephone() {
	var ph = $('#lsmphn').val();
		if (ph.length > 0) {
			if (isNaN(ph) || ph.indexOf(" ") != -1) {
				//document.getElementById('viewlsmsite').disabled = true;
				alert("Enter numeric value");
				return false;
			}
		else if (ph.length != 10) {
			document.getElementById('viewlsmsite').disabled = true;
				alert("Invalid Mobile Number...");
				return false;
			}
		}
		else
		{
			document.getElementById('viewlsmsite').disabled = false;
			return true;
		}
			
}


function validateEmailid() {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if ($('#emailid').val().length > 0) {
		if (!($('#emailid').val().match(mailformat))) {
			alert("Invalid email address!");
			document.getElementById('viewlsmsite').disabled = true;
			return false;
		}
		else {
			document.getElementById('viewlsmsite').disabled = false;
			return true;
		}
	}
	else
		return true;
}


/*$("#viewlsmsite").click(function() {
 if($('#offcrlogin').val()!=""){
	var ssoid = {};
	var lsmreason = {};
	$("#Useridreq").modal('hide');
	$('#updpopup').modal('show');
	$("#clearNsm").click(function() {
	//	$('#ssoid').val("");
	});

}else{
	$("#Useridreq").modal('show');
}
});*/


$("#viewlsmsite").click(function() {
window.location = "view_LSM";
});

$("#confupdlsm").click(function() {
	
	     ssoid = $('#ssoid').text();
	
	//	lsmreason = $('#rmks').val();
error =0;
	
	if ($('.selsite').val().trim() == '--Select FPO Sites--') {
		$('.selsite').next().show();
		$('.selsite').next().text('Select FPO Site*')
		$('.selsite').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('.selsite').css({ "border-color": "", "box-shadow": "" })

	}
	
	if ($('.selssoid').val().trim() == '--Select FPO Sites--') {
		$('.selssoid').next().show();
		$('.selssoid').next().text('Select FPO Site*')
		$('.selssoid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('.selssoid').css({ "border-color": "", "box-shadow": "" })

	}
	
	if (!(validatephone()))
	    error=error + 1;

    if (!(validateEmailid()))
        error=error + 1;
	   
var lsmdata = {};
	lsmdata['userid'] = ssoid;
	lsmdata['cusSite'] = $('.selsite').val();
	lsmdata['empName'] = $("#empname").val();
	lsmdata['design'] = $("#designation").val();
	lsmdata['mobileno'] = $("#lsmphn").val();
	lsmdata['email'] = $("#emailid").val();
	//lsmdata['reason']=$("#rmks").val();
	if(error == 0) {
	if ($('#offcrlogin').val() != "") {		
		$.ajax({
			url: 'updLsm',
			data: JSON.stringify(lsmdata),
			contentType: "application/json",
			type: "post",
			success: function(data) {

				window.location = "current_lsm";

			},
    error: function(xhr, textStatus, errorThrown) {
        if (xhr.status === 400) {
             window.location = "error"; // Redirect to the error page
        } else {
            // Handle other error cases
            console.log(xhr, textStatus, errorThrown);
        }
    },
		});
}}

	});


document.getElementById("empname").disabled = true;
document.getElementById("sitecode").disabled = true;
document.getElementById('viewlsmsite').disabled = true;
			document.getElementById("designation").disabled = true;
			document.getElementById("lsmphn").disabled = true;
			document.getElementById("emailid").disabled = true;


$("select.selsite").change(function() {
		var selectedState = $(this).children("option:selected").val();
		var cusite = selectedState;
		$('#sitecod').val(cusite);
		$('#sitecod').attr('disabled', true);
		$('#empname').val("");
	    $('#designation').val("");
		$('#lsmphn').val("");
		$('#emailid').val("");
        if(cusite == '--Select FPO Sites--'){
	$('#removedata input[type=text]').val(''); 
	//   $('#selctoffid').hide();
	$('#lsmssid').show();
	
}
$.ajax({
         url: 'getlistofssoid?cusSite=' + cusite,

		success: function(data) {
			             //  $('#selssoid').empty();
document.getElementById("selsite").disabled = true;
                            var selectssoid = document.getElementById('selssoid');
                         //   if (data.length > 0)
                         //    $('#clussite').val(data[0][0]);
                            for (i = 0; i < data.length; i++) {
				                  var opt = document.createElement('option');
				                  opt.value = data[i];
				                  opt.innerHTML = data[i];
                                  selectssoid.appendChild(opt);
				                 // document.getElementByid('selssoid').appendChild(data[i]);
			                }
						//	$('#ssoid1').text(valdata[0]);
						//	$('#ssoid2').text(valdata[1]);
						//	$('#lsmssid').hide();
						//	$('#selctoffid').show();
          },
              error: function(xhr, textStatus, errorThrown) {
        if (xhr.status === 400) {
             window.location = "error"; // Redirect to the error page
        } else {
            // Handle other error cases
            console.log(xhr, textStatus, errorThrown);
        }
    },
});
});


$("select.selssoid").change(function() {
		var selectedssoid = $(this).children("option:selected").val();
        var cussite = $('#remvesitecde').val();
$('#empname').val("");
	    $('#designation').val("");
		$('#lsmphn').val("");
		$('#emailid').val("");
		$('#ssoid').text(selectedssoid);
$.ajax({
	url: 'Lsmssoiddata?SSOid=' + selectedssoid,
	success: function(data) {
		$('#empname').val(data[0][0]);
	    $('#designation').val(data[0][1]);
		$('#lsmphn').val(data[0][2]);
		$('#emailid').val(data[0][3]);
		$('#ssoid').text(selectedssoid);
	//	$('#rmks').val("");
		//document.getElementById("designation").disabled = false;
		//			document.getElementById("lsmphn").disabled = false;
		//	        document.getElementById("emailid").disabled = false;
					document.getElementById("ssoid").disabled = true;
					document.getElementById("sitecode").disabled = true;
	//	document.getElementById("rmks").disabled = false;
		document.getElementById("viewlsmsite").disabled = false;
		},
    error: function(xhr, textStatus, errorThrown) {
        if (xhr.status === 400) {
             window.location = "error"; // Redirect to the error page
        } else {
            // Handle other error cases
            console.log(xhr, textStatus, errorThrown);
        }
    },
});

});
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
/*var lsmssoid = null;
$(document).ready(function() {
	$("select.selsite").change(function() {
		var selectedState = $(this).children("option:selected").val();
		var cusite = selectedState;
        if(cusite == '--Select FPO Sites--'){
	$('#removedata input[type=text]').val(''); 
	   $('#selctoffid').hide();
	$('#lsmssid').show();

}
		$.ajax({
			url: 'getCountofLSM?LSMsitecode=' + cusite,
			success: function(count) {
				var Lsmcunt = count

				if (Lsmcunt != 2) {
					$.ajax({
						url: 'removeLsmdata?cusSite=' + cusite,
						success: function(data) {
							var getdata = data;
							$('#remvssoid').val(getdata[0][0]);
							$('#remvempname').val(getdata[0][1]);
							$('#remvesitecde').val(getdata[0][2]);
							$('#remvdesg').val(getdata[0][3]);
							$('#remvlsmphn').val(getdata[0][5]);
							$('#remvemailid').val(getdata[0][4]);
							$('#reasonlsm').val("");
							$('#selctoffid').hide();
							$('#lsmssid').show();
						},

					});
				} else {
					$.ajax({
						url: 'getlistofssoid?cusSite=' + cusite,
						success: function(ssoid) {
							var valdata = ssoid;
							$('#ssoid1').text(valdata[0]);
							$('#ssoid2').text(valdata[1]);
							$('#lsmssid').hide();
							$('#selctoffid').show();

							$("select.Lsmoffid").change(function() {
								var selectedid = $(this).children("option:selected").text();
								lsmssoid = selectedid;
                                 if(selectedid == '--Select SSOID--'){
	                                   $('#remvempname').val("");
										$('#remvesitecde').val("");
										$('#remvdesg').val("");
										$('#remvlsmphn').val("");
										$('#remvemailid').val("");
										$('#reasonlsm').val("");
}
								$.ajax({
									url: 'Lsmssoiddata?SSOid=' + lsmssoid,
									success: function(Lsmdata) {
										var Lsmval = Lsmdata;
										$('#remvempname').val(Lsmval[0][0]);
										$('#remvesitecde').val(Lsmval[0][1]);
										$('#remvdesg').val(Lsmval[0][2]);
										$('#remvlsmphn').val(Lsmval[0][3]);
										$('#remvemailid').val(Lsmval[0][4]);
										$('#reasonlsm').val("");
									}
								});
							});
						},
					});
				}
			}
		});
	});
});*/


