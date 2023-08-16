$('#removelsmSite').attr('disabled', true);
function EnableDisable(recrdReasn) {
	if (recrdReasn.value.length <= 4) {
		$('#removelsmSite').attr('disabled', true);
	} else {
		$('#removelsmSite').attr('disabled', false);
	}

};

document.getElementById("remvempname").disabled = true;
document.getElementById('removelsmSite').disabled = true;
document.getElementById('cancelsmSite').disabled = true;
			document.getElementById("remvdesg").disabled = true;
			document.getElementById("remvlsmphn").disabled = true;
			document.getElementById("remvemailid").disabled = true;
			document.getElementById("reasonlsm").disabled = true;
			
$("#removelsmSite").click(function() {
var reg =/<(.|\n)*?>/g;
			 if (reg.test($('#reasonlsm').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
	error = 0;
	if ($('#reasonlsm').val() == '') {
		$('#reasonlsm').next().show();
		$('#reasonlsm').next().text('Please Fill the Remarks*')
		$('#reasonlsm').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#reasonlsm').css({ "border-color": "", "box-shadow": "" })
		$("#Useridreq").modal('hide');
		$('#removepopup').modal('show');
	}
 if($('#offcrlogin').val()!=""){
	var ssoid = {};
	var lsmreason = {};
	/*$("#Useridreq").modal('hide');
		$('#removepopup').modal('show');*/
	$("#clearNsm").click(function() {
		$('#remvssoid').val("");
	});

}else{
	$("#Useridreq").modal('show');
}
});




$("#cancelsmSite").click(function() {
window.location = "remove_LSM";
});


$("#confremlsm").click(function() {
	
	     ssoid = $('#remvssoid').text();
	
		lsmreason = $('#reasonlsm').val();

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

	if(error == 0) {
	if ($('#offcrlogin').val() != "") {
		$.ajax({
			url: 'deletelsm?ssoid=' + ssoid,
			data: JSON.stringify(lsmreason),
			contentType: "application/json",
			type: "post",
			success: function(data) {

				window.location = "current_lsm";

			},

		});
}}

	});





$("select.selsite").change(function() {
		var selectedState = $(this).children("option:selected").val();
		var cusite = selectedState;
		$('#remvesitecde').val(cusite);
		$('#remvesitecde').attr('disabled', true);
        if(cusite == '--Select FPO Sites--'){
	$('#removedata input[type=text]').val(''); 
	//   $('#selctoffid').hide();
	$('#lsmssid').show();
}
$.ajax({
         url: 'getlistofssoid?cusSite=' + cusite,
		success: function(data) {
                            var selectssoid = document.getElementById('selssoid');
document.getElementById('selsite').disabled = true;
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
});
});


$("select.selssoid").change(function() {
		var selectedssoid = $(this).children("option:selected").val();
        var cussite = $('#remvesitecde').val();
$.ajax({
	url: 'Lsmssoiddata?SSOid=' + selectedssoid,
	success: function(data) {
		$('#remvempname').val(data[0][0]);
		$('#remvdesg').val(data[0][1]);
		$('#remvlsmphn').val(data[0][2]);
		$('#remvemailid').val(data[0][3]);
		$('#remvssoid').text(selectedssoid);
		$('#reasonlsm').val("");
		document.getElementById("reasonlsm").disabled = false;
		document.getElementById("removelsmSite").disabled = false;
		document.getElementById("cancelsmSite").disabled = false;
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


