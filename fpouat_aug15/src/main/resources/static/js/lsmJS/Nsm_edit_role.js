
// Hemanth code 
//User-ID name and Designation
var selecteduser = '';
var userrole = "";
var getval1 = "";
var getval2 = "";
var mailclssplitPAO = [];
var mailclssplitPSU = [];
var NsmSiteCde = "";
$(document).ready(function() {
	$('.editroles').hide();
	$("select.userid").change(function() {
		selecteduser = $(this).children("option:selected").val();
		if (selecteduser == '') {
			$('#LSMroles').hide();
			$('#LSMUser').hide();
			$('.editroles').hide();
			$('.editadminrole').empty();
			$('.editmiscelroles').empty();
			$('.emprofile').hide();
		} else {
			$.ajax({
				url: 'getoffname&desgedit?userid=' + selecteduser,
				async : false,
				success: function(data) {
					$('.editroles').show();
					$('.editmiscellist').show();
					$('.submitbutn').show();
					$('.chkbxnote').show();
					$('.emprofile').show();
					userrole = "";
					NsmSiteCde = "";
					$('#LsmSSid').text(data[0][0]);
					$('#LsmNme').text(data[0][1]);
					$('#LsmDes').text(data[0][2]);
					userrole = data[0][2];
					if (data[0][2]==null ||data[0][2]=='')
					     { $('#LsmDes').text('-');
                           }
					else
					     {$('#LsmDes').text(data[0][2]);
                          }
					$('#LSMUser').show();
					$('.editselrole').hide();

					if ($('[id^=editmisce-]').length == 0) {
						$('.editmiscellist').hide();
					}

					if (data[0][3] == 'INNSA5') {
						NsmSiteCde = 'INNSA5';
						editcallNsmadminrole();
						editcallNsmmiserole();
					} else {
						NsmSiteCde = 'ALL';

					}
					/*if (!(data[0][4]==null ||data[0][4]=='' || data[0][4]=='null'))
                         $('#sex').text(data[0][4]);
                    else
                         $('#sex').text('-');
					if (!(data[0][5]==null ||data[0][5]==''  || data[0][5]=='null'))
                         $('#stdt').text(data[0][5]);
					else					
                         $('#stdt').text('-');
                    if (!(data[0][6]==null ||data[0][6]==''  || data[0][6]=='null'))
                         $('#endt').text(data[0][6]);
					else
                         $('#endt').text('-');
                    if (!(data[0][7]==null ||data[0][7]==''  || data[0][7]=='null'))
                         $('#mgrid').text(data[0][7]);
					else
                         $('#mgrid').text('-');
                    if (!(data[0][8]==null ||data[0][8]==''  || data[0][8]=='null'))
                         $('#addrs').text(data[0][8]);
					else					
                         $('#addrs').text('-');
                    if (data[0][9]==null ||data[0][9]==''  || data[0][9]=='null')
                         $('#mobno').text('-');
					else					
                         $('#mobno').text(data[0][9]);
                    if (data[0][10]==null ||data[0][10]==''  || data[0][10]=='null')
                         $('#dob').text('-');
					else					
                         $('#dob').text(data[0][10]);
					 if (data[0][11]==null ||data[0][11]==''  || data[0][11]=='null')
                         $('#comsnr').text('-');
					else					
                         $('#comsnr').text(data[0][11]);
					 if (data[0][12]==null ||data[0][12]==''  || data[0][12]=='null')
                         $('#email').text('-');
					else					
                         $('#email').text(data[0][12]);
					 if (data[0][13]==null ||data[0][13]==''  || data[0][13]=='null')
                         $('#site').text('-');
					else					
                         $('#site').text(data[0][13]);
					 if (data[0][14]==null ||data[0][14]==''  || data[0][14]=='null')
                         $('#stat').text('-');
					else					
                         $('#stat').text(data[0][14]);*/


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

			$.ajax({
				url: 'getassignedroleforNsm?userid=' + selecteduser,
				async : false,
				success: function(role) {
					selectval.length = 0;
					getval1 = "";
					getval2 = "";

					$.each(role, function(index, value) {
						selectval.push(value);
						editroledata.push(value);

						if (value == "PNA") {
							$('#editadminrole').prop('checked', true);
							$('[id^=editmisce-]').each(function() {
								if ($(this).val() == 'NAL') {
									//$(this).prop('disabled', true);
								}
							});
						}
						
						if(value == 'NAL'){
							//$('[id=editadminrole]').prop('disabled', true);
						}else{
							//$('[id=editadminrole]').prop('disabled', false);
						}
						$('[id^=editmisce-]').each(function() {
							if ($(this).val() == value) {
								$(this).prop('checked', true);
							}
						});
					});

				}
			});
			
			
			$.ajax({
				url: 'getanyothroleNSM?userid=' + selecteduser,
				async : false,
				success: function(rolecou) {
			if (rolecou  > 0)
	                   {
		    swal({
	        title: "Transaction and Miscellaneous roles allotted to this User for any of the FPO site in the application will be automatically removed , when PNA (National System Administrator)  role is allotted.",
	        text: "Note: Article IDs mapped to this User for Transaction Roles like PAO, PAC, PIN, PSU or PBS would have to be reallocated to other User under 'Priority / Reallocation' Functionality by AC via PAC role. Do you want to proceed?",
	        icon: "warning",
	        buttons: ["No", "Yes"],
	        dangerMode: false,
	         }).then((willDelete) => {
		   if (willDelete) {
			showLoader();
	        hideLoader();
           }
          else
          {
	       window.location = "Nsm_editroles";
           }
        });
    }
else
{	showLoader();
	hideLoader();	
}
}
});
			
		}

	});

});



// Get NSM Admin roles
function editcallNsmadminrole() {
	$.ajax({
		url: 'getNsmadminrolenm?userdesc=' + userrole + '&NsmSitAccess=' + NsmSiteCde,
		async : false,
		success: function(val) {
			if (val.length == 0) {
				$('.editacadminrole').hide();
			} else {
				$.each(val, function(index, value) {
					$('.editadminrole').append('<div class="card"><div class=" card-body" style="background: #2d2c2c;"><div class="round row"><input type="checkbox" value=' + value[1] + ' id="editadminrole" /><label for="editadminrole" style="font-weight: 600;font-size: larger; color: white;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + '' + "( For Designation - " + ' ' + value[1] + ' ' + ")" + '</p></div></div><br>');
				});
				$('.editacadminrole').show();
			}
		},
	});
}

// Get NSM miserole
function editcallNsmmiserole() {
	$.ajax({
		url: 'getNsmmiserole?userdesc=' + userrole + '&NsmSitAccess=' + NsmSiteCde,
		async : false,
		success: function(val1) {
			$.each(val1, function(index1, value) {
				if ((($('#NsmOffid').val()==$('#LsmSSid').text()) && value[1] != "NAL") || ($('#NsmOffid').val()!=$('#LsmSSid').text()))
				$('.editmiscelroles').append('<div class="card"><div class=" card-body" style="background: #8e949a;"><div class="round row"><input type="checkbox" name="miscroleselc" value=' + value[1] + ' id="editmisce-' + index1 + 1 + '" /><label for="editmisce-' + index1 + 1 + '" style="font-weight: 600;font-size: larger;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + '' + "( For Designation - " + ' ' + value[1] + ' ' + ")" + '</p></div></div><br>');
			});
			$('.editmiscellist').show();
		},
	});
}

$('#CanceNsmbtn').click(function() {
	window.location = "Nsm_editroles";
	});

$('#userprofile').click(function(){
	var selecteduser=$('#LsmSSid').text();
$.ajax({
	url: 'getUserProfile?userid=' + selecteduser,
	success: function(uprofdata) {
		    $("#userprofileBody").html(uprofdata);
            $("#userprofileModal").modal('show');
	},
});
});


function closeuserprofModal()
{
$('#userprofileModal').modal('hide');
};



var selectval = [];
var editroledata = [];
var editmailclsAO = null;
var editmailclsPSU = null;
var Nsmuser = $('#NsmOffid').val();
$('#Updatederles').click(function() {

	editroledata.length = 0;

	$('[id^=editadminrole]').each(function() {
		if ($(this).is(":checked")) {
			editroledata.push($(this).val());
		}
	});
	$('[id^=editmisce-]').each(function() {
		if ($(this).is(":checked")) {
			editroledata.push($(this).val());
		}
	})

	if (editroledata.length != 0) {
		$('#selectedroles').append("Selected Roles - " + "<span class='label label-important' style='color : #1616e0'>" + editroledata.toString().split(",").join(", ") + "</span>");
		$('#rolecnfrm').modal('show');
		$('#addrole').click(function() {
			$.ajax({
				url: "editassignroles?checkedarray=" + editmailclsAO + "&rolenme=" + editroledata + "&usrid=" + selecteduser + "&cusSite=" + $('#lsmCusite').val() + "&mailclsSUP=" + editmailclsPSU + "&LoginUser=" + Nsmuser,
				type: "get",
				success: function(data) {
					swal("Success!", "Roles Updated Successfuly!", "success")
						.then((value) => {
							window.location.href = "Nsm_editroles";
						});
				},
			});

		});
	} else {
		$('#removerolespopup').append("Do you want to remove all roles?")
		$('#remverolecnfrm').modal('show');
		$('#cnfrmrmvearole').click(function() {
			$.ajax({
				url: "removeallroles?usrid=" + selecteduser + "&LoginUser=" + Nsmuser + "&cusSite=" + $('#lsmCusite').val(),
				type: "get",
				success: function(data) {
					swal("Success!", "All roles removed successfuly!", "success")
						.then((value) => {
							window.location.href = "Nsm_editroles";
						});
				},
			});

		});
	}


	$('#notsure').click(function() {
		$('#selectedroles').text("");
	});
});

$('#clearchckbox').click(function() {
	if  ($('#NsmOffid').val() !=   $('#LsmSSid').text())  
	    $('[id^=editadminrole]').prop('checked', false);
	$('[id^=editmisce-]').prop('checked', false);
	if  ($('#NsmOffid').val() ==   $('#LsmSSid').text())  
	  $('[id=editadminrole]').prop('disabled', false);
	$('[id^=editmisce-]').prop('disabled', false);
});



$(document).ready(function() {
	if  ($('#NsmOffid').val() ==   $('#LsmSSid').text())  
	  $('[id=editadminrole]').prop('disabled', false);
});



var Pnacunt
$(document).on('change', '[id=editadminrole]', function() {
	var chk=0;
if ($('#editadminrole').is(":checked")) 
    {   if ($('#editmisce-01').is(":checked"))
     
         {   $('[id=editadminrole]').prop('checked', false); 
             $('#noPNApopup').modal('show'); chk=1;}    
     }      
else 
{if  ($('#NsmOffid').val() ==   $('#LsmSSid').text())   

    { $('#noPNArmvpopup').modal('show');
      $('[id=editadminrole]').prop('checked', true);
    }
}
if ($('#editadminrole').is(":checked") &&  ($('#NsmOffid').val() !=   $('#LsmSSid').text()) ) {
		$.ajax({
			url: "getPnaoffid",
			success: function(PNAoffid) {
				Pnacunt = PNAoffid.length;
				if ((Pnacunt < 3)  && ($('#editadminrole').is(":checked"))) {
					if ($('#editmisce-01').is(":checked"))
					   {
						$('#NSAminrole').modal('show');
					}
				//	$('#NSApopup').modal('show')
				//	$('#NSAaddrole').click(function() {
				//		$('[id^=editmisce-]').each(function() {
				//			if ($(editmisce-]).val() == 'NAL') {
				//				$(this).prop('disabled', true);
				//			}
				//		});
				//	});
				//	$('#NSAback').click(function() {
				//		$('[id=editadminrole]').prop('checked', false);

				//	});

				} 
				else if ((Pnacunt == 1) && !($('#editadminrole').is(":checked")))
				{
					if ($('#NsmOffid').val()==$('#LsmSSid').text())
					   {$('#NSAminrole').modal('show');
					    $('#NSArolelimit1').click(function() {
						$('[id=editadminrole]').prop('checked', true);
					});}
					
				}				
				else if ((Pnacunt == 3)  && ($('#editadminrole').is(":checked"))){
					if (PNAoffid[0]!=$('#LsmSSid').text() && PNAoffid[1]!=$('#LsmSSid').text() && PNAoffid[2]!=$('#LsmSSid').text() )
					{
					$('#NSAmaxrole').modal('show');
					$('#NSArolelimit').click(function() {
						$('[id=editadminrole]').prop('checked', false);
					});}
				}
	        // else {
		    //   $('[id^=editmisce-]').each(function() {
			//       if ($(this).val() == 'NAL')
		//		    $(this).prop('disabled', false);
        //      });
	//	});
	},
});}
});




$('#PNAnorole1').click(function() {
						//$('[id=editadminrole]').prop('checked', false);
						 $('#noPNApopup').modal('hide');
					});



var Arpcunt
$(document).on('change', '[id^=editmisce-]', function() {
	if ($(this).is(":checked")) {
		$.ajax({
			url: "getARPcount",
			success: function(count) {
				Arpcunt = count;
				$('[id^=editmisce-]').each(function() {
					if ($(this).val() == 'ARP') {
						if (Arpcunt >= 30) {
							$('#ARPmaxrole').modal('show');
							$('#ARProlelimit').click(function() {
								$(this).prop('checked', false);
							});

						}
					}
				});

			}
		});


		if ($(this).val() == 'NAL') {
			if ($('#editadminrole').is(":checked"))
			{ $('#editmisce-01').prop('checked',false);
              $('#NALnopopup').modal('show')}
            else{
     			$('#DRIpopup').modal('show')
			$('#DRIaddrole').click(function() {
			//	$('[id=editadminrole]').prop('disabled', true);
			});}

			$('#DRIback').click(function() {
				$('[id^=editmisce-]').each(function() {
					if ($(this).val() == 'NAL')
						$(this).prop('checked', false);
				});

			});
		}
	} else {
		//$('[id=editadminrole]').prop('disabled', false);
	}

});


function Nsmalloctrole() {
	window.location.href = "nsm_allotrole";
}

$('#bcktoselrole').click(function() {
	window.location.href = "Nsm_editroles";
})

function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}

