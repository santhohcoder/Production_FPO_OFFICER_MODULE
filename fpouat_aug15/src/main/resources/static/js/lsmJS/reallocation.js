$(document).ready(function() {
	
	$('#reallocate').click(function() {
		error = 0;

		if ($('.selresn').val().trim() == '--Select Reason--') {
			$('.selresn').next().show();
			$('.selresn').next().text('Select reason*')
			$('.selresn').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
			error = error + 1;
		} else {
			$('.selresn').css({ "border-color": "", "box-shadow": "" })
			$('.selresn').next().text('');

		}

		if ($('.selresn').val().trim() == 'OTHERS') {
			if ($('#otherreason').val() == '') {
				$('#otherreason').next().show();
				$('#otherreason').next().text('Enter reason*')
				$('#otherreason').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
				error = error + 1;
			}
			else {
				$('#otherreason').css({ "border-color": "", "box-shadow": "" });
				$('#otherreason').next().text('');
			}
		}

		if ($('#tossoid').val().trim() == '') {
			$('#tossoid').next().show();
			$('#tossoid').next().text('Enter SSOID*')
			$('#tossoid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
			error = error + 1;
		} else {
			$('#tossoid').css({ "border-color": "", "box-shadow": "" })
			$('#tossoid').next().text('')

		}

		if ($('#fromssoid').val().trim() == '') {
			$('#fromssoid').next().show();
			$('#fromssoid').next().text('Enter SSOID*')
			$('#fromssoid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
			error = error + 1;
		} else {
			$('#fromssoid').css({ "border-color": "", "box-shadow": "" })
			$('#fromssoid').next().text('')

		}
		
		if ($('#selectmailclas').val().trim() == '--Select Mail Class--') {
			$('#selectmailclas').next().show();
			$('#selectmailclas').next().text('Select Mail Class-Code*')
			$('#selectmailclas').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
			error = error + 1;
		} else {
			$('#selectmailclas').css({ "border-color": "", "box-shadow": "" })
             $('#selectmailclas').next().text('');
		}

		if ($('#selectart').val().trim() == 'Specify-ID') {
			if ($('#aritcleid').val() == '') {
				$('#aritcleid').next().show();
				$('#aritcleid').next().text('Enter article-id*')
				$('#aritcleid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
				error = error + 1;
			}
			else {
				$('#aritcleid').css({ "border-color": "", "box-shadow": "" });
				$('#aritcleid').next().text('');
			}
			if ($('#aritcleid').val().length != 13) {
				$('#aritcleid').next().show();
				$('#aritcleid').next().text('Article-id must in 13 digits*')
				$('#aritcleid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
				error = error + 1;
			} else {
				$('#aritcleid').css({ "border-color": "", "box-shadow": "" })
				$('#aritcleid').next().text('');
			}
		}
var sndval = "";
	$.each($('#selctroles').val(), function(index, roles1) {
		if(roles1 == "PAC"){
			sndval = "APR";
		}else if(roles1 == "PSU"){
			sndval = "SUP";
		}else if(roles1 == "PAO"){
			sndval = "ACL";
		}else if(roles1 == "PIN"){
			sndval = "INS";
		}
		
		});
		var getdata = {};
		getdata['from_ssoid'] = $('#fromssoid').val();
		getdata['to_ssoid'] = $('#tossoid').val();
		getdata['reason_cd'] = $('#resnval').val();
		getdata['reason_others'] = $('#otherreason').val();
		getdata['select_article'] = $('#selectart').val();
		getdata['mail_class'] = $('#selectmailclas').val();
		getdata['article_id'] = $('#aritcleid').val();
		$('#cnfrmreall').modal('show');
		if (error == 0) {
			$('#cnfmpopup').click(function() {
			$.ajax({
				url: 'getreallocationdata?rolesassigned=' + sndval,
				data: JSON.stringify(getdata),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(success) {
					swal("Success!", "Reallocated Successfuly!", "success")
						.then((value) => {
							location.reload();
						});
				}
			})
});
		}
	});

});

function selectresn(val) {
	$('.selresn').css({ "border-color": "", "box-shadow": "" })
	$('.selresn').next().text('');

	if (val.value == "D") {
		$('.otherresn').show('slow');
	} else {
		$('.otherresn').hide('slow');
	}
}

function selectarticl(val1) {
	if (val1.value == "Specify-ID") {
		$('.articlid').show('slow');
	} else {
		$('.articlid').hide('slow');
	}
}

 /*function selctmailcls(mailcls){
	alert(mailcls.value);
}*/

var mailclsval = [];
var mailclsjson={};
function entrssoid(val3) {

	if (val3.value != "") {
		$.ajax({
			url: 'Lsmgetassignedroles?ssoid=' + val3.value,
			success: function(success) {
               	$('.mailclass').html('');
				if (success != "") {
					$('#selctroles').empty();
					var html = '';
					$.each(success, function(index, value) {
						var assrole = "";
						if (value == 'APR') {
							assrole = 'PAC';
						} else if (value == 'ACL') {
							assrole = 'PAO';
						} else if (value == 'INS') {
							assrole = 'PIN';
						} else if (value == 'SUP') {
							assrole = 'PSU'
						}
						html = html == '' ? html + '<span style="color: blue">' + assrole + '</span>' : html + ', <span style="color: blue">' + assrole + '</span>';
						$('#selctroles').append('<option>' + assrole + '</option>');

						$.ajax({
							url: 'getmailClas?mailcls=' + value + '&ssoid=' + val3.value,
							success: function(success) {
								var mailcls = success[0].split(',');
								var joincls = "";
								var joinclsArr=[];
								$.each(mailcls, function(index, mailclsdata) {
									if (mailclsdata == 'U') {
										joincls = joincls != "" ? joincls + ", Letters" :joincls + "Letters";
										joinclsArr.push("Letters");
									} else if ((mailclsdata == 'E')) {
										joincls = joincls != "" ? joincls + ", EMS" :joincls + "EMS";
										joinclsArr.push("EMS");
									} else if ((mailclsdata == 'C')) {
										joincls = joincls != "" ? joincls + ", Parcels" :joincls + "Parcels";
										joinclsArr.push("Parcels");
									} else if ((mailclsdata == 'T')) {
										joincls = joincls != "" ? joincls + ", Empty receptacles" :joincls + "Empty receptacles";
										joinclsArr.push("Empty receptacles");
									}
									mailclsval.push(mailclsdata);
								});
								mailclsjson[assrole]=joinclsArr;
								$('.mailclass').append('<li><span style="color: #000000;">Mail Class given for</span>' + '<span>&nbsp;&nbsp;-&nbsp;&nbsp;</span>' + " " + '<span style="color: #00000">' + assrole + '</span>' + '<span style="color: blue">' + " " + "-" + " " + joincls + '</span></li>');
							}
						});
					});
					$('.rolesassigned').html('<span style="color: #000000;">Roles Assigned to the user</span>' + '<span>&nbsp;&nbsp;-&nbsp;&nbsp;</span>' + " " + html);
					$('div.tossoid').css('margin-top', 40);
				} else {
					$('.rolesassigned').html('<span style="color: red;">No Users Mapped</span>')
					$('#selctroles').append('');
				}
			},

		});
	} else {
		$('.rolesassigned').html('');
		$('#selctroles').html('<option>' + '--Select Roles--' + '</option>');
		$('.mailclass').html('');
	}
}

function entrtossoid(toid) {
	if (toid.value != "") {
		$.ajax({
			url: 'tossoidroles?ssoid=' + toid.value,
			success: function(data) {

				if (data != "") {
					var html = '';
					$.each(data, function(index, value) {
						var assothrole = "";
						if (value == 'APR') {
							assothrole = 'PAC';
						} else if (value == 'ACL') {
							assothrole = 'PAO';
						} else if (value == 'INS') {
							assothrole = 'PIN';
						} else if (value == 'SUP') {
							assothrole = 'PSU'
						}
						html = html == '' ? html + '<span style="color: blue">' + assothrole + '</span>' : html + ',<span style="color: blue">' + assothrole + '</span>';
						frmssidroles.push(assothrole);
					});

					$('.rolesassigned1').html('<span style="color: #000000;">Roles Assigned to the user</span>' + '<span>&nbsp;&nbsp;-&nbsp;&nbsp;</span>' + " " + html);
					$('.roleshow').show('slow');
				} else {
					$('.rolesassigned1').html('<span style="color: red;">No Users Mapped</span>')
				}
			},
		});
	} else {
		$('.rolesassigned1').html('');
	}
}
var rolesSelected = ""
var frmssidroles = [];
$("select.selrole").change(function() {
	rolesSelected = "";
	var selectedrole = $(this).children("option:selected").val();
	if (selectedrole == null) {
		return false;
	} else if (!frmssidroles.includes(selectedrole)) {
		$('#reallocpopup').text("This role has not been allocated to this ssoid. To re-allocate this role, first this role has to be allocated to this ssoid by LSM.");
		$('#rolepopup').modal('show');
		$('#selctroles').val('');
	}
         
});

mailclsval = [];
var rolesdt = [];
var cmpre = "";
$("select.selmailcls").change(function() {
	var selectedrole = $(this).children("option:selected").val();
	if(selectedrole == 'U'){
	  cmpre = "Letters"
	}else if(selectedrole == 'C'){
		cmpre = "Parcels"
	}else if(selectedrole == 'E'){
		cmpre = "EMS"
	}else if(selectedrole == 'T'){
		cmpre = "Empty receptacles"
		}
	if (selectedrole == null) {
		return false;
	}  if(mailclsval.includes(selectedrole)) {
		$('#selectmailclas').val(selectedrole);
	}else{
		$('#selectmailclas').val('--Select Mail Class--');
	}
	rolesdt = $("#selctroles").val();
	var mailclsflag=false;
	$.each(rolesdt, function(index, value) {
		    var llo = mailclsjson[value];
            var exist=llo.filter(mc => mc==cmpre);
			if(exist.length>0){
				mailclsflag=true;
				return false;
			}
		});
		if(mailclsflag == false){
			$('#selectmailclas').val('--Select Mail Class--');
		}
});


$(document).ready(function() {
	$('.js-example-basic-multiple').select2({
		placeholder: "Select Role"
	});

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
	
/*function blink_text() {
$('.blink').fadeOut(500);
$('.blink').fadeIn(500);
}
setInterval(blink_text, 1000);*/

