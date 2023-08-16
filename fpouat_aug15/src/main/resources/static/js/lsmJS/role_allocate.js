
function rolehstry() {
	window.location = "editroles";
}

function Nsmrolehstry() {
	window.location = "Nsm_editroles";
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
function goBack() {
	window.history.back();
}

// Hemanth code 
//User-ID name and Designation
var selecteduser = "";
var userDesc = "";
var NsmSiteCde = "";
$(document).ready(function() {
	$('.submitbutn').hide();
	$('.allroles').css('display', 'none');
	$("select.userid").change(function() {
		selecteduser = $(this).children("option:selected").val();
		if (selecteduser == '') {
			$('#LSMUser').hide();
			$('.adminrole').empty();
			$('.transroles').empty();
			$('.miscelroles').empty();
			$('.allroles').hide();
			$('.submitbutn').hide();
		} else {
			showLoader();
			$.ajax({
				url: 'getoffname&desg?userid=' + selecteduser,
				success: function(data) {
				if(data){
					$('.dropdown').css('display', 'none');
					$('.selrolehead').css('display', 'block');
					$('.adminrole').empty();
					$('.transroles').empty();
					$('.miscelroles').empty();
					$('.allroles').show();
					$('.submitbutn').show();
					$('.transroleslist').show();
					$('.miscellist').show();
					$('.emprofile').show();
					userDesc = "";
					NsmSiteCde = "";
					$('#LsmSSid').text(data[0][0]);
					$('#LsmNme').text(data[0][1]);
						var value = data[0][2]; 
					if (value === null) {
					  value = '--';
					}
					$('#LsmDes').text(value);
					$('#mcpao').text(data[1]);
					$('#mcpin').text(data[2]);
					$('#mcpsu').text(data[3]);
					userDesc = data[0][2];
					$('#LSMUser').show();
					mcinit();
					calladminrole();
					calldsprole();
					callmiserole();
					hideLoader();
				}
				else{   
					window.location = "error";
					
				}  
				}
			});
		}
	});
});

// Get Lsm roles
function calladminrole() {
	$.ajax({
		url: 'getadminrolenm?userdesc=' + userDesc,
		success: function(val) {
			if (val.length == 0) {
				$('.acadminrole').hide();
			} else {
				$.each(val, function(index, value) {
					$('.adminrole').append('<div class="card"><div class=" card-body" style="background: #2d2c2c;"><div class="round row"><input type="checkbox" value=' + value[1] + ' id="adminrole' + index + '" /><label for="adminrole' + index + '" style="font-weight: 600;font-size: larger; color: white;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + ' ' + "( For Designation - " + ' ' + value[2] + ' ' + ")" + '</p></div></div><br>');
				});
				$('.acadminrole').show();
			}
		},
	});
}



function calldsprole() {
	$.ajax({
		url: 'getdisprolenm?userdesc=' + userDesc,
		success: function(val) {
			if (val.length == 0) {
				$('.transroleslist').hide();
			} else {	            
				$.each(val, function(index, value) {
				 var opt=0;
				 if (value[1]=="PAO" || value[1]=="PSU" || value[1]=="PIN")
			      {  if (value[1]=="PAO" && $('#mcpao').text().includes('U',0) &&  $('#mcpao').text().includes('E',0) && $('#mcpao').text().includes('C',0) && $('#mcpao').text().includes('T',0) )
                        opt=1;
                     else if (value[1]=="PIN" && $('#mcpin').text().includes('U',0) &&  $('#mcpin').text().includes('E',0) && $('#mcpin').text().includes('C',0) && $('#mcpin').text().includes('T',0) )
						opt=1;
                     else if (value[1]=="PSU" && $('#mcpsu').text().includes('U',0) &&  $('#mcpsu').text().includes('E',0) && $('#mcpsu').text().includes('C',0) && $('#mcpsu').text().includes('T',0) )
					    opt=1;
	                 else
                        opt=0;
				   }
	              else
                        opt=0;
			        if (opt==0)	
					$('.transroles').append('<div class="card"><div class=" card-body" style="background: #5897fb;"><div class="round row"><input type="checkbox" name="roleselc" value=' + value[1] + ' id="trans-' + index + '" /><label for="trans-' + index + '" style="font-weight: 600;font-size: larger;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + ' ' + "( For Designation - " + ' ' + value[2] + ' ' + ")" + '</p></div></div><br>');
				});
				$('.transroleslist').show();
			}
		},
	});
}

function callmiserole() {
	$.ajax({
		url: 'getmiserole?userdesc=' + userDesc,
		success: function(val1) {
			$.each(val1, function(index1, value) {
				$('.miscelroles').append('<div class="card"><div class=" card-body" style="background: #8e949a;"><div class="round row"><input type="checkbox" name="miscroleselc" value=' + value[1] + ' id="misce-' + index1 + 1 + '" /><label for="misce-' + index1 + 1 + '" style="font-weight: 600;font-size: larger;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + ' ' + "( For Designation - " + ' ' + value[2] + ' ' + ")" + '</p></div></div><br>');
			});
		},

	});
}

function mcinit(){
	var mcpao = $('#mcpao').text();
	var mcpin = $('#mcpin').text();
	var mcpsu = $('#mcpsu').text();
	var i=0;
	if (mcpao.includes('U',0) && mcpao.includes('E',0) &&  mcpao.includes('C',0) && mcpao.includes('T',0) )
	{     $('#allmailclsao').prop('disabled', true);
	      $('#allmcpao').hide();  }
    else{
	if(mcpao.includes('U',0))
	 {  $("#lettersao").prop('disabled', true);
        $('#allmailclsao').prop('disabled', true);
        $('#allmcpao').hide();
        $('#upao').hide();
        i=i+1;}
    else
     $("#lettersao").prop('disabled', false);
    if(mcpao.includes('E',0))
	 { $("#emsao").prop('disabled', true);
       $('#allmailclsao').prop('disabled', true);
       $('#allmcpao').hide();
       $('#epao').hide();
       i=i+1;}
    else
     $("#emsao").prop('disabled', false);
    if(mcpao.includes('C',0))
	 { $("#parcelao").prop('disabled', true);
       $('#allmailclsao').prop('disabled', true);
       $('#allmcpao').hide();
       $('#cpao').hide();
       i=i+1;}
     else
     $("#parcelao").prop('disabled', false);
	 if(mcpao.includes('T',0))
	 { $("#emptyrecao").prop('disabled', true);
       $('#allmailclsao').prop('disabled', true);
       $('#allmcpao').hide();
       $('#tpao').hide();
       i=i+1;}
    else
     $("#empyrecao").prop('disabled', false);
	if (i==4)
	   $("#trans-1").prop('disabled', true); }
    i=0;
	if (mcpin.includes('U',0) && mcpin.includes('E',0) &&  mcpin.includes('C',0) && mcpin.includes('T',0) )
	{     $('#allmailclspin').prop('disabled', true);
	      $('#allmcpin').hide(); }
    else{
	if(mcpin.includes('U',0))
	 {  $("#letterspin").prop('disabled', true);
        $('#allmailclspin').prop('disabled', true);
        $('#allmcpin').hide();
        $("#upin").hide();
        i=i+1;}
    else
     $("#letterspin").prop('disabled', false);
    if(mcpin.includes('E',0))
	 { $("#emspin").prop('disabled', true);
       $('#allmailclspin').prop('disabled', true);
       $('#allmcpin').hide();
       $("#epin").hide();
       i=i+1;}
    else
     $("#emspin").prop('disabled', false);
    if(mcpin.includes('C',0))
	 { $("#parcelpin").prop('disabled', true);
       $('#allmailclspin').prop('disabled', true);
       $('#allmcpin').hide();
       $("#cpin").hide();
       i=i+1;}
     else
     $("#parcelpin").prop('disabled', false);
	 if(mcpin.includes('T',0))
	 { $("#emptyrecpin").prop('disabled', true);
       $('#allmailclspin').prop('disabled', true);
       $('#allmcpin').hide();
       $("#tpin").hide();
       i=i+1;}
    else
     $("#empyrecpin").prop('disabled', false);
	if (i==4)
	   $("#trans-2").prop('disabled', true); }
    i=0;
	if (mcpsu.includes('U',0) && mcpsu.includes('E',0) &&  mcpsu.includes('C',0) && mcpsu.includes('T',0) )
	{     $('#allmailclssup').prop('disabled', true); 
	      $('#allmcpsu').hide();}
    else{
	if(mcpsu.includes('U',0))
	 {  $("#letterssup").prop('disabled', true);
        $('#allmailclssup').prop('disabled', true);
        $("#upsu").hide();
        $('#allmcpsu').hide();
        i=i+1;}
    else
     $("#letterssup").prop('disabled', false);
    if(mcpsu.includes('E',0))
	 { $("#emssup").prop('disabled', true);
       $('#allmailclssup').prop('disabled', true);
       $("#epsu").hide();
       $('#allmcpsu').hide();
       i=i+1;}
    else
     $("#emssup").prop('disabled', false);
    if(mcpsu.includes('C',0))
	 { $("#parcelsup").prop('disabled', true);
       $('#allmailclssup').prop('disabled', true);
        $("#cpsu").hide();
        $('#allmcpsu').hide();
       i=i+1;}
     else
     $("#parcelsup").prop('disabled', false);
	 if(mcpsu.includes('T',0))
	 { $("#emptyrecsup").prop('disabled', true);
       $('#allmailclssup').prop('disabled', true);
       $("#tpsu").hide();
       $('#allmcpsu').hide();
       i=i+1;}
    else
     $("#empyrecsup").prop('disabled', false);
	if (i==4)
	   $("#trans-3").prop('disabled', true); }
}


var val = {};
var val1 = {};
var val2 = {};
var val3 = {};

// Mail class conditions for Ao
$('#allmailclsao').change(function() {
	if ($('#allmailclsao').is(":checked") == true) {
		$("#lettersao").prop('disabled', true).prop('checked', true);
		$('#emsao').prop('disabled', true).prop('checked', true);
		$('#parcelao').prop('disabled', true).prop('checked', true);
		$('#emptyrecao').prop('disabled', true).prop('checked', true);
	} else {
		$("#lettersao").prop('disabled', false).prop('checked', false);
		$('#emsao').prop('disabled', false).prop('checked', false);
		$('#parcelao').prop('disabled', false).prop('checked', false);
		$('#emptyrecao').prop('disabled', false).prop('checked', false);
	}
});


$('#lettersao').change(function() {
	if ($('#lettersao').is(":checked") == true || $('#emsao').is(":checked") == true || $('#parcelao').is(":checked") == true || $('#emptyrecao').is(":checked") == true) {
		$("#allmailclsao").prop('disabled', true);
	} else {
		$("#allmailclsao").prop('disabled', false);
	}
});

$('#emsao').change(function() {
	if ($('#lettersao').is(":checked") == true || $('#emsao').is(":checked") == true || $('#parcelao').is(":checked") == true || $('#emptyrecao').is(":checked") == true) {
		$("#allmailclsao").prop('disabled', true);
	} else {
		$("#allmailclsao").prop('disabled', false);
	}
});

$('#parcelao').change(function() {
	if ($('#lettersao').is(":checked") == true || $('#emsao').is(":checked") == true || $('#parcelao').is(":checked") == true || $('#emptyrecao').is(":checked") == true) {
		$("#allmailclsao").prop('disabled', true);
	} else {
		$("#allmailclsao").prop('disabled', false);
	}
});

$('#emptyrecao').change(function() {
	if ($('#lettersao').is(":checked") == true || $('#emsao').is(":checked") == true || $('#parcelao').is(":checked") == true || $('#emptyrecao').is(":checked") == true) {
		$("#allmailclsao").prop('disabled', true);
	} else {
		$("#allmailclsao").prop('disabled', false);
	}
});


$('#allmailclspin').change(function() {
	if ($('#allmailclspin').is(":checked") == true) {
		$("#letterspin").prop('disabled', true).prop('checked', true);
		$('#emspin').prop('disabled', true).prop('checked', true);
		$('#parcelpin]').prop('disabled', true).prop('checked', true);
		$('#emptyrecpin').prop('disabled', true).prop('checked', true);
	} else {
		$("#letterspin").prop('disabled', false).prop('checked', false);
		$('#emspin').prop('disabled', false).prop('checked', false);
		$('#parcelpin').prop('disabled', false).prop('checked', false);
		$('#emptyrecpin').prop('disabled', false).prop('checked', false);
	}
});

$('#letterspin').change(function() {
	if ($('#letterspin').is(":checked") == true || $('#emspin').is(":checked") == true || $('#parcelpin').is(":checked") == true || $('#emptyrecpin').is(":checked") == true) {
		$("#allmailclspin").prop('disabled', true);
	} else {
		$("#allmailclspin").prop('disabled', false);
	}
});

$('#emspin').change(function() {
	if ($('#letterspin').is(":checked") == true || $('#emspin').is(":checked") == true || $('#parcelpin').is(":checked") == true || $('#emptyrecpin').is(":checked") == true) {
		$("#allmailclspin").prop('disabled', true);
	} else {
		$("#allmailclspin").prop('disabled', false);
	}
});

$('#parcelpin').change(function() {
	if ($('#letterspin').is(":checked") == true || $('#emspin').is(":checked") == true || $('#parcelpin').is(":checked") == true || $('#emptyrecpin').is(":checked") == true) {
		$("#allmailclspin").prop('disabled', true);
	} else {
		$("#allmailclspin").prop('disabled', false);
	}
});

$('#emptyrecpin').change(function() {
	if ($('#letterspin').is(":checked") == true || $('#emspin').is(":checked") == true || $('#parcelpin').is(":checked") == true || $('#emptyrecpin').is(":checked") == true) {
		$("#allmailclspin").prop('disabled', true);
	} else {
		$("#allmailclspin").prop('disabled', false);
	}
});






// Mail class conditions for PSU
$('#allmailclssup').change(function() {
	if ($('#allmailclssup').is(":checked") == true) {
		$("#letterssup").prop('disabled', true).prop('checked', true);
		$('#emssup').prop('disabled', true).prop('checked', true);
		$('#parcelsup').prop('disabled', true).prop('checked', true);
		$('#emptyrecsup').prop('disabled', true).prop('checked', true);
	} else {
		$("#letterssup").prop('disabled', false).prop('checked', false);
		$('#emssup').prop('disabled', false).prop('checked', false);
		$('#parcelsup').prop('disabled', false).prop('checked', false);
		$('#emptyrecsup').prop('disabled', false).prop('checked', false);
	}
});

$('#letterssup').change(function() {
	if ($('#letterssup').is(":checked") == true || $('#emssup').is(":checked") == true || $('#parcelsup').is(":checked") == true || $('#emptyrecsup').is(":checked") == true) {
		$("#allmailclssup").prop('disabled', true);
	} else {
		$("#allmailclssup").prop('disabled', false);
	}
});

$('#emssup').change(function() {
	if ($('#letterssup').is(":checked") == true || $('#emssup').is(":checked") == true || $('#parcelsup').is(":checked") == true || $('#emptyrecsup').is(":checked") == true) {
		$("#allmailclssup").prop('disabled', true);
	} else {
		$("#allmailclssup").prop('disabled', false);
	}
});

$('#parcelsup').change(function() {
	if ($('#letterssup').is(":checked") == true || $('#emssup').is(":checked") == true || $('#parcelsup').is(":checked") == true || $('#emptyrecsup').is(":checked") == true) {
		$("#allmailclssup").prop('disabled', true);
	} else {
		$("#allmailclssup").prop('disabled', false);
	}
});

$('#emptyrecsup').change(function() {
	if ($('#letterssup').is(":checked") == true || $('#emssup').is(":checked") == true || $('#parcelsup').is(":checked") == true || $('#emptyrecsup').is(":checked") == true) {
		$("#allmailclssup").prop('disabled', true);
	} else {
		$("#allmailclssup").prop('disabled', false);
	}
});

//Show mail class when PAO and PSU is Selected
$(document).on('change', '[id^=trans-]', function() {

	if ($(this).is(":checked")) {

		if ($(this).val() == 'PAC') {
			$('[id^=trans-]').each(function() {
				if ($(this).val() != 'PAC') {
					$(this).prop('disabled', true);
				} else {
					$(this).prop('disabled', false);
				}
			})
		}

		if ($(this).val() == 'PBS' || $(this).val() == 'PIN') {
			$('[id^=trans-]').each(function() {
				if ($(this).val() != 'PIN' && $(this).val() != 'PBS') {
					$(this).prop('disabled', true);
				} else {
					$(this).prop('disabled', false);
				}
			})
		}

		if ($(this).val() == 'PAO' || $(this).val() == 'PSU' || $(this).val() == 'PIN') {
			$('[id^=trans-]').each(function() {
				if ($(this).val() != 'PAO' && $(this).val() != 'PSU' && $(this).val() != 'PIN') {
					$(this).prop('disabled', true);
				} else {
					$(this).prop('disabled', false);
				}
			})
		}

	}
	else {
		if ($(this).val() == 'PAC') {
			$('[id^=trans-]').each(function() {
				$(this).prop('disabled', false);
			})
		}
		if ($(this).val() == 'PIN' || $(this).val() == 'PBS') {
			if ($('[id^=trans-]').filter(function() {
				return $(this).is(":checked")
			}).length > 0 && ($($('[id^=trans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PIN' || $($('[id^=trans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PBS')) {
				$('[id^=trans-]').each(function() {
					if ($(this).val() == 'PIN' || $(this).val() == 'PBS') {
						$(this).prop('disabled', false);
					} else {
						$(this).prop('disabled', true);
					}
				})
			} else {
				$('[id^=trans-]').each(function() {
					$(this).prop('disabled', false);
				})
			}
		}

		if ($(this).val() == 'PAO' || $(this).val() == 'PSU' || $(this).val() == 'PIN') {
			if ($('[id^=trans-]').filter(function() {
				return $(this).is(":checked")
			}).length > 0 && ($($('[id^=trans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PAO' || $($('[id^=trans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PIN' || $($('[id^=trans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PSU')) {
				$('[id^=trans-]').each(function() {
					if ($(this).val() == 'PAO' || $(this).val() == 'PSU' || $(this).val() == 'PIN') {
						$(this).prop('disabled', false);
					} else {
						$(this).prop('disabled', true);
					}
				})
			} else {
				$('[id^=trans-]').each(function() {
					$(this).prop('disabled', false);
				})
			}
		}

	}
	
	if ($(this).is(":checked")) {
		if ($(this).val() == 'PAO') {
			mailclsAO.length = 0;
			$('#mailclascfrapr').modal('show')

		} else if ($(this).val() == 'PSU') {
			mailclsPSU.length = 0;
			$('#mailclascfrsup').modal('show')
		}
		else if ($(this).val() == 'PIN') {
			mailclsPIN.length = 0;
			$('#mailclascfrpin').modal('show')
		}


	} else if ($(this).val() == 'PAO') {
		$('#roleremvecnfrmAO').modal('show')
		$('#cnfrmrolermvAO').click(function() {
			mailclsAO.length = 0;
			$('#allmailclsao').prop('checked', false);
			$('#lettersao').prop('checked', false);
			$('#emsao').prop('checked', false);
			$('#parcelao').prop('checked', false);
			$('#emptyrecao').prop('checked', false);
		})
		$('#notsureAO').click(function() {
			$('[id^=trans-]').each(function() {
				if ($(this).val() == 'PAO') {
					$(this).prop('checked', true);
				}
			})
		});
	} else if ($(this).val() == 'PSU') {
		$('#roleremvecnfrmPSU').modal('show')
		$('#cnfrmrolermvPSU').click(function() {
			mailclsPSU.length = 0;
			$('#allmailclssup').prop('checked', false);
			$('#letterssup').prop('checked', false);
			$('#emssup').prop('checked', false);
			$('#parcelsup').prop('checked', false);
			$('#emptyrecsup').prop('checked', false);
		})
		$('#notsurePSU').click(function() {
			$('[id^=trans-]').each(function() {
				if ($(this).val() == 'PSU') {
					$(this).prop('checked', true);
				}
			})
		});
	} else if ($(this).val() == 'PIN') {
		$('#roleremvecnfrmPIN').modal('show')
		$('#cnfrmrolermvPIN').click(function() {
			mailclsPIN.length = 0;
			$('#allmailclspin').prop('checked', false);
			$('#letterspin').prop('checked', false);
			$('#emspin]').prop('checked', false);
			$('#parcelpin').prop('checked', false);
			$('#emptyrecpin').prop('checked', false);
		})
		$('#notsurePIN').click(function() {
			$('[id^=trans-]').each(function() {
				if ($(this).val() == 'PSU') {
					$(this).prop('checked', true);
				}
			})
		});
	}
})

// Max adminrole per Site
$(document).on('change', '[id^=adminrole]', function() {
	if ($(this).is(":checked")) {
		if ($(this).val() == 'PLA') {
			var role1 = 'PLA';
			$.ajax({
				url: 'getcountofPLA?roleNm=' + role1,
				async: false,
				success: function(countofPLA) {
					if (countofPLA >= 2) {
						$('#PLAmaxrole').modal('show');
						$('#PLArolelimit').click(function() {
							$('[id^=adminrole]').each(function() {
								if ($(this).val() == 'PLA')
									$(this).prop('checked', false);
							})
						})
					}
				}
			});
		}

		if ($(this).val() == 'PAA') {
			var role2 = 'PAA';
			$.ajax({
				url: 'getcountofPAA?roleNm=' + role2,
				async: false,
				success: function(countofPAA) {
					if (countofPAA >= 2) {
						$('#PAAmaxrole').modal('show');
						$('#PAArolelimit').click(function() {
							$('[id^=adminrole]').each(function() {
								if ($(this).val() == 'PAA')
									$(this).prop('checked', false);
							})
						})
					}
				}
			});
		}
	}
});

$(document).on('change', '[id^=misce-]', function() {
	if ($(this).is(":checked")) {
		if ($(this).val() == 'ARP') {
			var role3 = 'ARP';
			$.ajax({
				url: 'getcountofARP?roleNm=' + role3,
				async: false,
				success: function(countofARP) {
					alert(countofARP);
					if (countofARP >= 5) {
						$('#ARPmaxrole').modal('show');
						$('#ARProlelimit').click(function() {
							$('[id^=misce-]').each(function() {
								if ($(this).val() == 'ARP')
									$(this).prop('checked', false);
							})
						})
					}
				}
			});
		}
	}
});

//compulsary 1 mail class is sel for PAO and PSU,
var mailclsAO = [];
$('#mailclsAo').click(function() {
	if ($('#allmailclsao').is(":checked") == false && $('#lettersao').is(":checked") == false && $('#emsao').is(":checked") == false & $('#parcelao').is(":checked") == false && $('#emptyrecao').is(":checked") == false) {
		$('[id^=trans-]').each(function() {
			if ($(this).val() == 'PAO') {
				$(this).prop('checked', false);
			} if ($(this).val() != 'PAO') {
				$(this).prop('disabled', false);
			}
		})
	} else {
		$('[id^=trans-]').each(function() {
			if ($(this).val() == 'PAO') {
				$(this).prop('checked', true);
			}
		})
	}
	//Assign vlaue to selected Mail-class
	if ($('#allmailclsao').is(":checked") == true) {
		$('#allmailclsao').val('U,E,C,T');
		mailclsAO.push($('#allmailclsao').val());
	}else{ if ($('#lettersao').is(":checked") == true) {
		$('#lettersao').val('U');
		mailclsAO.push($('#lettersao').val());
	} if ($('#emsao').is(":checked") == true) {
		$('#emsao').val('E');
		mailclsAO.push($('#emsao').val());
	} if ($('#parcelao').is(":checked") == true) {
		$('#parcelao').val('C');
		mailclsAO.push($('#parcelao').val());
	} if ($('#emptyrecao').is(":checked") == true) {
		$('#emptyrecao').val('T');
		mailclsAO.push($('#emptyrecao').val());
	}
}
})

//compulsary 1 mail class is sel for PAO and PSU,
var mailclsPSU = [];
$('#mailclssup').click(function() {
	if ($('#allmailclssup').is(":checked") == false && $('#letterssup').is(":checked") == false && $('#emssup').is(":checked") == false && $('#parcelsup').is(":checked") == false && $('#emptyrecsup').is(":checked") == false) {
		$('[id^=trans-]').each(function() {
			if ($(this).val() == 'PSU') {
				$(this).prop('checked', false);
			} if ($(this).val() != 'PSU') {
				$(this).prop('disabled', false);
			}
		})
	} else {
		$('[id^=trans-]').each(function() {
			if ($(this).val() == 'PSU') {
				$(this).prop('checked', true);
			}
		})
	}
	//Assign vlaue to selected Mail-class
	if ($('#allmailclssup').is(":checked") == true) {
		$('#allmailclssup').val('U,E,C,T');
		mailclsPSU.push($('#allmailclssup').val());
	}else{ if ($('#letterssup').is(":checked") == true) {
		$('#letterssup').val('U');
		mailclsPSU.push($('#letterssup').val());
	} if ($('#emssup').is(":checked") == true) {
		$('#emssup').val('E');
		mailclsPSU.push($('#emssup').val());
	} if ($('#parcelsup').is(":checked") == true) {
		$('#parcelsup').val('C');
		mailclsPSU.push($('#parcelsup').val());
	} if ($('#emptyrecsup').is(":checked") == true) {
		$('#emptyrecsup').val('T');
		mailclsPSU.push($('#emptyrecsup').val());
	}
	}
})


var mailclsPIN = [];
$('#mailclspin').click(function() {
	if ($('#allmailclspin').is(":checked") == false && $('#letterspin').is(":checked") == false && $('#emspin').is(":checked") == false && $('#parcelpin').is(":checked") == false && $('#emptyrecpin').is(":checked") == false) {
		$('[id^=trans-]').each(function() {
			if ($(this).val() == 'PIN') {
				$(this).prop('checked', false);
			} if ($(this).val() != 'PIN') {
				$(this).prop('disabled', false);
			}
		})
	} else {
		$('[id^=trans-]').each(function() {
			if ($(this).val() == 'PIN') {
				$(this).prop('checked', true);
			}
		})
	}
	//Assign vlaue to selected Mail-class
	if ($('#allmailclspin').is(":checked") == true) {
		$('#allmailclspin').val('U,E,C,T');
		mailclsPIN.push($('#allmailclspin').val());
	}else{ if ($('#letterspin').is(":checked") == true) {
		$('#letterspin').val('U');
		mailclsPIN.push($('#letterspin').val());
	} if ($('#emspin').is(":checked") == true) {
		$('#emspin').val('E');
		mailclsPIN.push($('#emspin').val());
	} if ($('#parcelpin').is(":checked") == true) {
		$('#parcelpin').val('C');
		mailclsPIN.push($('#parcelpin').val());
	} if ($('#emptyrecpin').is(":checked") == true) {
		$('#emptyrecpin').val('T');
		mailclsPIN.push($('#emptyrecpin').val());
	}
	}
})



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





$('#CanceLsmbtn').click(function() {
	window.location = "roles_allocation";
	});


var modalcunt = 0;
var roleval = "";
var roleval1 = "";
//Save in Data base
$('#SaveLsmbtn').click(function() {
	showLoader();
	var arry = [];
	$('[id^=adminrole]').each(function() {
		if ($(this).is(":checked")) {
			arry.push($(this).val());
		}
	});
	$('[id^=misce-]').each(function() {
		if ($(this).is(":checked")) {
			arry.push($(this).val());
		}
	})

	$('[id^=trans-]').each(function() {
		if ($(this).is(":checked")) {
			arry.push($(this).val());
		}
	})

	var Lsmuser = $('#LsmOffid').val();
	if (arry.length != 0) {
		$('#selectedroles').append("Selected Roles - " + "<span class='label label-important' style='color : #1616e0'>" + arry + "</span>");
		$('#rolecnfrm').modal('show');
		hideLoader();
		$('#addrole').click(function() {
			showLoader();
			$.ajax({
				url: "assignroles?checkedarray=" + mailclsAO + "&rolenme=" + arry + "&usrid=" + selecteduser + "&cusSite=" + $('#lsmCusite').val() + "&mailclsSUP=" + mailclsPSU + "&mailclsPIN=" + mailclsPIN + "&LoginUser=" + Lsmuser,
				type: "get",
				success: function(data) {
					hideLoader();
					swal("Success!", "Roles have been assigned for the selected user!", "success")
						.then((value) => {
							window.location.href = "roles_allocation";
						});
				},
			});
		});

	} else {
		hideLoader();
		$('#min1role').modal('show');
	}

});

$('#rleback').click(function() {
	$('#selectedroles').text("");
});

$("#bcktorleallt").click(function() {
	window.location.href = "roles_allocation";
})


