$("#siterefresh1").click(function() {
	window.location = "allocate_LSM";
	});
	
$("#siterefresh2").click(function() {
	window.location = "allocate_LSM";
	});


$("#refLsmpersite").click(function() {
								window.location = "allocate_LSM";
							});
							
$("#refreshlsm").click(function() {
											window.location = "current_lsm";
										});


$("#canceladdlsmsite").click(function() {
	window.location = "allocate_LSM";
	});

function validatephone() {
	var ph = $('#lsmphn').val();
		if (ph.length > 0) {
			if (isNaN(ph) || ph.indexOf(" ") != -1) {
				document.getElementById('updlsmsite').disabled = true;
				alert("Enter numeric value");
				return false;
			}
		else if (ph.length != 10) {
				alert("Invalid Mobile Number...");
				document.getElementById('updlsmsite').disabled = true;
				return false;
			}
		}
		else
		{
			document.getElementById('updlsmsite').disabled = false;
			return true;
		}
			
}


function validateEmailid() {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if ($('#emailid').val().length > 0) {
		if (!($('#emailid').val().match(mailformat))) {
			alert("Invalid email address!");
			document.getElementById('updlsmsite').disabled = true;
			return false;
		}
		else {
			document.getElementById('updlsmsite').disabled = false;
			return true;
		}
	}
	else
		{
			document.getElementById('updlsmsite').disabled = false;
			return true;
		}
			
}




//document.getElementById("ssoid").disabled = true;
document.getElementById("empname").disabled = true;
document.getElementById("sitecod").disabled = true;
document.getElementById('addlsmsite').disabled = true;
document.getElementById('canceladdlsmsite').disabled = true;
			document.getElementById("designation").disabled = true;
			document.getElementById("lsmphn").disabled = true;
			document.getElementById("emailid").disabled = true;
$(document).ready(function() {
	$("select.selsite").change(function() {
		$('#ssoid').val("");
		var selectedState = $(this).children("option:selected").val();
		if (selectedState == '--Select FPO Sites--') {
			$('#sitecod').val("");
			$('#siteadd').attr('disabled', true);
			document.getElementById("ssoid").disabled = true;
		} else {
			$.ajax({
				url: 'getsiteoflist?list=' + selectedState,
				success: function(data) {
					
				/*	var marker = JSON.stringify(data);
			        var json = jQuery.parseJSON(marker);
			        json = JSON.parse(json);

 			       $('#ssoid').empty();
			       var selectssoid = document.getElementById('selssoid');
			       for (i = 0; i < json.list.length; i++) {
				      var opt = document.createElement('option');
				      opt.value = json.list[i].value;
				      opt.innerHTML = json.list[i].data;
				      selectssoid.appendChild(opt);
			}
					*/
					
					
					
					
					$.each(data, function(key, value) {
						if (key == 0) {
							$('#sitecod').val(value[0]);
							sitecode1 = $('#sitecode1').text(value[0]);
							$('#displyoption').hide();
							$('#inputxt').show();
							$('#siteadd').attr('disabled', false);
							document.getElementById("ssoid").disabled = false;
							document.getElementById('addlsmsite').disabled = false;
							document.getElementById('canceladdlsmsite').disabled = false;
						} else {
							$('#inputxt').hide();
							$('#displyoption').show();
							document.getElementById("ssoid").disabled = true;
							$('#sitecod').val("");
							$('#sitecode2').text(value[0]);
						}

					});
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

		}
	});
});


$("#refSiteCmpre").click(function() {
					$("#siteCmpre").modal('hide');
				})
	
	
$("#refssonotfou").click(function() {
	        $("#ssonotfou").modal('hide');
});
				
				
var getlsmSitecde = null;
$("#ssoid").change(function() {
	var cmpr = $('#sitecod').val();
	var offid = $('#ssoid').val(); 
	var divcde = cmpr.substring(0, 5);
	$.ajax({
		url: 'getDesignandNme?ssoid='+offid+'&cusite='+cmpr,
		success: function(data) {
			if (data.length == 0)
			 $.ajax({
		        url: 'getssofou?ssoid='+offid+'&cusite='+cmpr,
                success:function(data){
	             if (data.length > 0)
	             {   $('#ssofoumsg').html(data[0]);
                    $("#ssonotfou").modal('show');}
                  else
                    $("#siteCmpre").modal('show');	
	          },
              });
            else {
	        $.ajax({
		     url: 'getplaalreadyfou?ssoid='+offid+'&cusite='+cmpr,
		     success: function(fou) {
			   if (fou > 0 )
			      $("#lsmfou").modal('show');            
			   else if (cmpr==data[0][2]) {
				if (data != "") {
					$('#empname').val(data[0][0]);
					$('#designation').val(data[0][1]);
					$('#lsmphn').val(data[0][3]);
					$('#emailid').val(data[0][4]);
					//document.getElementById("designation").disabled = false;
					//document.getElementById("lsmphn").disabled = false;
			        //document.getElementById("emailid").disabled = false;
					document.getElementById("ssoid").disabled = true;
					document.getElementById("sitecod").disabled = true;
				} 
			}
			},
			});
			}
		},

	});
	
	var name = "Do You Want to Proceed?";
    var content = document.createElement('div');
    content.innerHTML = '<strong>'+ name +'</strong>';
	$.ajax({
				url: 'getanyothroleLSM?userid=' + offid + '&cusite='+cmpr,
				async : false,
				success: function(rolecou) {
			if (rolecou  > 0)
	                   {
		    swal({
	        title: "Transaction and Miscellaneous roles allotted to this User for any of the FPO site in the application will be automatically removed , when PLA (Local System Administrator)  role is allotted.",
	    //    text: "Note: Article IDs mapped to this User for Transaction Roles like PAO, PAC, PIN, PSU or PBS would have to be reallocated to other User under 'Priority / Reallocation' Functionality by AC via PAC role.",
	        text: "Note: Article IDs mapped to this User for Transaction Roles like PAO, PAC, PIN, PSU or PBS would have to be reallocated to other User under 'Priority / Reallocation' Functionality by AC via PAC role.\n\nDo you want to proceed?",
       //     content:content,	        
            icon: "warning",
            html:true,
	        buttons: ["No", "Yes"],
	        dangerMode: false,
	         }).then((willDelete) => {
		   if (willDelete) {
			showLoader();
	        hideLoader();
           }
          else
          {
	       window.location = "allocate_LSM";
           }
        });
 }
else
{	showLoader();
	hideLoader();	
}
}
});
			

});



var substrng = null;
var slectedcde = null;
$(document).ready(function() {
	$("select.sitecde").change(function() {
		var selcde = $(this).children("option:selected").text();
		slectedcde = selcde;
		substrng = slectedcde.substring(0, 5);
		if (selcde != '--Select Site code--') {
			document.getElementById("ssoid").disabled = false;
		} else {
			document.getElementById("ssoid").disabled = true;
		}
		if (getlsmSitecde != null) {
			if (slectedcde.substring(0, 5) != getlsmSitecde) {
				$("#siteCmpre").modal('show');
				$("#refSiteCmpre").click(function() {
					window.location = "allocate_LSM";
				})
			}
		}
	});
});




var error =0;
var lsmsite = null;
$('#addlsmsite').click(function() {
	
	error =0;
	
	if ($('.selsite').val().trim() == '--Select FPO Sites--') {
		$('.selsite').next().show();
		$('.selsite').next().text('Select FPO Site*')
		$('.selsite').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('.selsite').css({ "border-color": "", "box-shadow": "" })

	}
	
	if ($('#ssoid').val().trim() == '') {
		$('#ssoid').next().show();
		$('#ssoid').next().text('Select SSOID*')
		$('#ssoid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#ssoid').css({ "border-color": "", "box-shadow": "" })

	}
	
	if ($('#empname').val().trim() == '') {
		$('#empname').next().show();
		$('#empname').next().text('Select Name*')
		$('#empname').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#empname').css({ "border-color": "", "box-shadow": "" })

	}
	
	if ($('#kyc-filetxt-1').val().trim() == '') {
		$('#kyc-filetxt-1').next().show();
		$('#kyc-filetxt-1').next().text('Please upload a document*')
		$('#kyc-filetxt-1').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
		$('#kyc-file-1').css({"display":"none"});
	} else {
		$('#kyc-filetxt-1').css({ "border-color": "", "box-shadow": "" })

	}
	
	if ($('#sitecod').val().trim() != '' || $('#sitecode1').text().trim() != '' || $('#sitecode2').text().trim() != '') {
		$('#sitecod').css({ "border-color": "", "box-shadow": "" })
		
	} else {
		
		$('#sitecod').next().show();
		$('#sitecod').next().text('Select SiteCode*')
		$('#sitecod').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
         error = error + 1;
	}
	
	/*if ($('#designation').val().trim() == '') {
		$('#designation').next().show();
		$('#designation').next().text('Select SSOID*')
		$('#designation').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#designation').css({ "border-color": "", "box-shadow": "" })

	}
	
	/*if ($('#sitecode').val().length != 6 ) {
		$('#sitecode').next().show();
		$('#sitecode').next().text('Site code must be 6digits*')
		$('#sitecode').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#sitecode').css({ "border-color": "", "box-shadow": "" })

	}*/
	
	/*if ($('#lsmphn').val().length <= 3) {
		$('#lsmphn').next().show();
		$('#lsmphn').next().text('Mobile is required*')
		$('#lsmphn').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#lsmphn').css({ "border-color": "", "box-shadow": "" })
	}
	
	if ($('#emailid').val().trim() == '') {
		$('#emailid').next().show();
		$('#emailid').next().text('Enter Mail-Id*')
		$('#emailid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#emailid').css({ "border-color": "", "box-shadow": "" })

	} */
	var oMyForm = new FormData(); 
	
	var lsmdata = {};
	/*lsmdata['userid'] = $("#ssoid").val();
	lsmdata['empName'] = $("#empname").val();
	lsmdata['design'] = $("#designation").val();*/
	
	oMyForm.append('design', $("#designation").val());
	oMyForm.append('empName', $("#empname").val());
	oMyForm.append('userid', $("#ssoid").val());
	var rmk = $('#rmks').val();
	oMyForm.append("docname",$("#kyc-filetxt-1").val());
	oMyForm.append("filename", kycFiles[$("#kyc-filetxt-1").attr('id').split('-')[$("#kyc-filetxt-1").attr('id').split('-').length - 1]].files[0]);
	if (slectedcde == null) {
	//	lsmdata['cusSite'] = $("#sitecod").val();  
		oMyForm.append('cusSite', $("#sitecod").val()); 
		lsmsite = $("#sitecod").val();
	} else {
	//	lsmdata['cusSite'] = slectedcde;
		oMyForm.append('cusSite', sletedcde); 
		lsmsite = slectedcde;
	}
	/*lsmdata['mobileno'] = $("#lsmphn").val();
	lsmdata['email'] = $("#emailid").val();
	lsmdata['reason']=$('#rmks').val();*/
	
	oMyForm.append('mobileno', $("#lsmphn").val());
	oMyForm.append('email', $("#emailid").val());
	oMyForm.append('reason', $("#rmks").val());
	
	console.log(oMyForm);
	
	if(error == 0) {
	if ($('#offcrlogin').val() != "") {
		$.ajax({
			url: 'getCountofLSM?LSMsitecod=' + lsmsite,
			success: function(Lsmcount) {
				var datacunt = Lsmcount;
				$.ajax({
					url: 'getlsmofficer',
					success: function(data) {
						var val1 = data;
						var count = 0;
						$.each(val1, function(index, value) {
							if ($("#ssoid").val() == value[1]) {
								count = 1;
								datacunt = 0;
								$("#addinglsm").modal('hide');
								$("#lsmadded").modal('show');
								$("#twoLsmperSite").modal('hide');
							}

						});
						if (datacunt == 2) {
							count = 1;
							$("#lsmadded").modal('hide');
							$("#addinglsm").modal('hide');
							$("#twoLsmperSite").modal('show');
						}
						
						if (datacunt == 1) {
							$("#lsmadded").modal('hide');
							$("#addinglsm").modal('hide');
							$("#Lsmaddtwice").modal('show');
						   $("#scndLsmadd").click(function() {
							if (count == 0) {
								count = 1;
								console.log(oMyForm);
								$.ajax({
									url: 'addLsm?reason='+rmk+'&usrid='+$("#ssoid").val()+'&cusSite='+$("#sitecod").val(),
								/*	data: JSON.stringify(lsmdata),
									dataType: "json",
									contentType: "application/json", */
									data: oMyForm,
									contentType: false,
									processData: false,
									enctype: 'multipart/form-data',
									type: "post",
									success: function(data) {
										$("#lsmadded").modal('hide');
										$("#addinglsm").modal('show');
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

							}
						});
						}
                       if (datacunt == 0) {
						if (count == 0) {
							$.ajax({
							/*	url: 'addLsm',
								data: JSON.stringify(lsmdata),
								dataType: "json",
								contentType: "application/json",
								type: "post", */
								url: 'addLsm?reason='+rmk+'&usrid='+$("#ssoid").val()+'&cusSite='+$("#sitecod").val()+"&rolenme=PLA",
								data: oMyForm, 
								contentType: false ,
								processData: false,
								enctype: 'multipart/form-data',
								type: 'POST',
								success: function(data) {
									$("#lsmadded").modal('hide');
									$("#addinglsm").modal('show');
								}
							});

						}
						
						}

					}

				});

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
	} else {
		$("#Useridreq").modal('show');
	}
	
	}
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

