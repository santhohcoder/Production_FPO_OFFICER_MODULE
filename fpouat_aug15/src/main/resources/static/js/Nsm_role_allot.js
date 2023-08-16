
function Nsmrolehstry() {
	window.location = "Nsm_editroles";
}

function goBack() {
	window.history.back();
}


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
			$('.miscelroles').empty();
			$('.allroles').hide();
			$('.submitbutn').hide();
			$('.emprofile').hide();
		} else {
			$.ajax({
				url: 'getoffname&desg?userid=' + selecteduser,
				success: function(data) {
					$('.dropdown').css('display', 'none');
					$('.selrolehead').css('display', 'block');
					$('.allroles').show();
					$('.submitbutn').show();
					$('.miscellist').show();
					$('.emprofile').show();
					userDesc = "";
					NsmSiteCde = "";
					$('#LsmSSid').text(data[0][0]);
					$('#LsmNme').text(data[0][1]);
					if (data[0][2]==null ||data[0][2]=='')
					     { $('#LsmDes').text('-');
                           }
					else
					     {$('#LsmDes').text(data[0][2]);
                          }
					userDesc = data[0][2];
					$('#LSMUser').show();
					if (data[0][3] == 'INNSA5') {
						NsmSiteCde = 'INNSA5';
						callNsmadminrole();
						callNsmmiserole();

					} else {
						NsmSiteCde = 'ALL';

					}
				/*	if (!(data[0][4]==null ||data[0][4]=='' || data[0][4]=='null'))
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

			});

		}
	});
});

// Get NSM Admin roles
function callNsmadminrole() {
	$.ajax({
		url: 'getNsmadminrolenm?userdesc=' + userDesc + '&NsmSitAccess=' + NsmSiteCde,
		success: function(val) {
			if (val.length == 0) {
				$('.acadminrole').hide();
			} else {
				if ($('#NsmOffid').val()!=$('#LsmSSid').text())
				{	$.each(val, function(index, value) {
					$('.adminrole').append('<div class="card"><div class=" card-body" style="background: #2d2c2c;"><div class="round row"><input type="checkbox" value=' + value[1] + ' id="adminrole" /><label for="adminrole" style="font-weight: 600;font-size: larger; color: white;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + '' + "( For Designation - " + ' ' + value[1] + ' ' + ")" + '</p></div></div><br>');
				});
				$('.acadminrole').show();}
			}
		},
	});
}


$('#CanceNsmbtn').click(function() {
	window.location = "nsm_allotrole";
	});


// Get NSM miserole
function callNsmmiserole() {
	$.ajax({
		url: 'getNsmmiserole?userdesc=' + userDesc + '&NsmSitAccess=' + NsmSiteCde,
		success: function(val1) {
			$.each(val1, function(index1, value) {
				if ((($('#NsmOffid').val()==$('#LsmSSid').text()) && value[1] != "NAL") || ($('#NsmOffid').val()!=$('#LsmSSid').text()))
				$('.miscelroles').append('<div class="card"><div class=" card-body" style="background: #8e949a;"><div class="round row"><input type="checkbox" name="miscroleselc" value=' + value[1] + ' id="misce-' + index1 + 1 + '" /><label for="misce-' + index1 + 1 + '" style="font-weight: 600;font-size: larger;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + '' + "( For Designation - " + ' ' + value[1] + ' ' + ")" + '</p></div></div><br>');
			});
			/*$('.miscellist').show();*/
		},
	});
}

var mailclsAO = null;
var mailclsPSU = null;
var mailclsPIN = null;
//Save in Data base
$('#saveNsmbtn').click(function() {

	var Nsmarry = [];

	$('[id^=adminrole]').each(function() {
		if ($(this).is(":checked")) {
			Nsmarry.push($(this).val());
		}
	});
	$('[id^=misce-]').each(function() {
		if ($(this).is(":checked")) {
			Nsmarry.push($(this).val());
		}
	})


	var Nsmuser = $('#NsmOffid').val();
	if (Nsmarry.length != 0) {
		$('#selectedroles').append("Selected Roles - " + "<span class='label label-important' style='color : #1616e0'>" + Nsmarry + "</span>");
		$('#rolecnfrm').modal('show');
		$('#addrole').click(function() {
			$.ajax({
				url: "assignroles?checkedarray=" + mailclsAO + "&rolenme=" + Nsmarry + "&usrid=" + selecteduser + "&cusSite=" + NsmSiteCde + "&mailclsSUP=" + mailclsPSU +  + "&mailclsPIN=" + mailclsPIN + "&LoginUser=" + Nsmuser,
				type: "get",
				success: function(data) {
					swal("Success!", "Roles have been assigned for the selected user!", "success")
						.then((value) => {
							window.location.href = "nsm_allotrole";
						});

				},
			});

		});

	} else {
		$('#min1role').modal('show');
	}

});


$('#rleback').click(function() {
	$('#selectedroles').text("");
});

$("#Nsmbcktorleallt").click(function() {
	window.location.href = "nsm_allotrole";
})

var Pnacunt 
$(document).on('change', '[id=adminrole]', function() { 

	if ($(this).is(":checked")) {
		$.ajax({
				url: "getPnacount",
				success: function(count) {
					Pnacunt = count ;
					
				if(parseFloat(Pnacunt) < 3){
		$('#NSApopup').modal('show')
		$('#NSAaddrole').click(function() {
			$('[id^=misce-]').each(function() {
				if ($(this).val() == 'NAL') {
					$(this).prop('disabled', true);
				}
			});
		});
		$('#NSAback').click(function() {
			$('[id=adminrole]').prop('checked', false);

		});
		
		}else{
			$('#NSAmaxrole').modal('show')
			$('#NSArolelimit').click(function() {
				$('[id=adminrole]').prop('checked', false);
				});
		}
		}
		});
	}else{
		$('[id^=misce-]').each(function() {
					if ($(this).val() == 'NAL')
						$(this).prop('disabled', false);

				});
	}
});

var Arpcunt 
$(document).on('change', '[id^=misce-]', function() {
	if ($(this).is(":checked")) {
		$.ajax({
				url: "getARPcount",
				success: function(count) {
					Arpcunt = count;
					$('[id^=misce-]').each(function() {
					if($(this).val() == 'ARP'){
						if(Arpcunt >= 30){
						$('#ARPmaxrole').modal('show');
						$('#ARProlelimit').click(function () {
						$(this).prop('checked', false);
				});
						
					}
					}
					});
					
					}
					});
					
					
		if ($(this).val() == 'NAL') {
			$('#DRIpopup').modal('show')
			$('#DRIaddrole').click(function() {
				$('[id=adminrole]').prop('disabled', true);

			});
			
			$('#DRIback').click(function() {
				$('[id^=misce-]').each(function() {
					if ($(this).val() == 'NAL')
						$(this).prop('checked', false);
				});

			});
		}
	}else{
		$('[id=adminrole]').prop('disabled', false);
	}	
	
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

