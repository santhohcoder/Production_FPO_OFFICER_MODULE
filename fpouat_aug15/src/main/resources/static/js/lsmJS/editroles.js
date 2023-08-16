
// Hemanth code 
//User-ID name and Designation
var selecteduser = {};
var userrole = "";
var getval1 = "";
var getval2 = "";
var mailclssplitPAO = [];
var mailclssplitPSU = [];
var mailclssplitPIN = [];
$(document).ready(function() {
	$('.editroles').hide();
	$("select.userid").change(function() {
		selecteduser = $(this).children("option:selected").val();
		if (selecteduser == '') {
			$('#LSMroles').hide();
			$('#LSMUser').hide();
			$('.editroles').hide();
		} else {
			showLoader();
			$.ajax({
				url: 'getoffname&desgedit?userid=' + selecteduser,
				success: function(data) {
					$('.editadminrole').empty();
					$('.edittransroles').empty();
					$('.editmiscelroles').empty();
					$('.editroles').show();
					$('.editacadminrole').show();
					$('.edittransroleslist').show();
					$('.editmiscellist').show();
					$('.submitbutn').show();
					$('.chkbxnote').show();
					userrole = "";
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
					userrole = data[0][2];
					$('#LSMUser').show();
					$('.editselrole').hide();
					calleditadminrole();
					calleditdsprole();
					calleditmiserole();
					mcinit();

					if ($('[id^=edittrans-]').length == 0) {
						$('.edittransroleslist').hide();
					}
					$.ajax({
						url: 'getroleandmailcls?userid=' + selecteduser,
						success: function(role) {
							selectval.length = 0;
							mailclasPAO.length = 0;
							mailclasPSU.length = 0;
							getval1 = "";
							getval2 = "";
							getval3 = "";

							$.each(role, function(index, value) {
								selectval.push(value[0]);
								editroledata.push(value[0]);

							});

							$.each(selectval, function(index, value) {

								$('[id^=editadminrole]').each(function() {
									if ($(this).val() == value) {
										$(this).prop('checked', true);
									}
								});

								$('[id^=editmisce-]').each(function() {
									if ($(this).val() == value) {
										$(this).prop('checked', true);
									}
								});
								$('[id^=edittrans-]').each(function() {
									if ($(this).val() == value) {
										$(this).prop('checked', true);
									}

								});

								if (value == "PAO") {
									getval1 = "PAO";
								}
				

								if (value == "PSU") {
									getval2 = "PSU";
								}
								
								if (value == "PIN") {
									getval3 = "PIN";
								}

							});

							jsonObj = {};
							for (var i = 0; i < role.length; i++) {
								jsonObj[role[i][0]] = role[i][1];
							}
							var mcpao = $('#mcpao').text();
							var mcpin = $('#mcpin').text();
							var mcpsu = $('#mcpsu').text();
							
							if ((getval1 == '' || getval1 == null) &&  (mcpao.includes('U',0) && mcpao.includes('E',0) &&  mcpao.includes('C',0) && mcpao.includes('T',0) ))
							    { $("#edittrans-1").prop('disabled', true); 
								  document.getElementById("edittrans-1").style.display="none";
								  $("#editlabtrans-1").prop('hidden', true); 
						          $("#edittextrans-1").prop('hidden', true); 
			                      $("#editlabtrans-1").css('color', 'dimgray'); 
								  $("#edittrans-1").prop('hidden', true); 
								  document.getElementById("editlabtrans-1").innerHTML='PAO Mail Classes already allotted';
			                      $("#edittrans-1").hide();}

							if ((getval2 == '' || getval2 == null) &&  (mcpsu.includes('U',0) && mcpsu.includes('E',0) &&  mcpsu.includes('C',0) && mcpsu.includes('T',0) ))
							    { $("#edittrans-3").prop('disabled', true); 
			                       $("#editlabtrans-3").prop('hidden', true); 
                                   $("#edittextrans-3").prop('hidden', true); 
			   					  document.getElementById("edittrans-3").style.display="none";
								  document.getElementById("editlabtrans-3").innerHTML='PSU Mail Classes already allotted';
						          $("#editlabtrans-3").css('color', 'dimgray'); 
			                      $("#edittrans-3").hide();}

							if ((getval3 == '' || getval3 == null) &&  (mcpin.includes('U',0) && mcpin.includes('E',0) &&  mcpin.includes('C',0) && mcpin.includes('T',0) ))
							    { $("#edittrans-2").prop('disabled', true); 
			                      $("#editlabtrans-2").prop('hidden', true); 
                                  $("#edittextrans-2").prop('hidden', true); 
			                      $("#editlabtrans-2").css('color', 'dimgray'); 
                                  document.getElementById("edittrans-2").style.display="none";
								  document.getElementById("editlabtrans-2").innerHTML='PIN Mail Classes already allotted';
			                      $("#edittrans-2").hide();}


							if (getval1 == "PAO") {
								var val1= jsonObj["PAO"];
								if (val1.includes('U',0) || !(mcpao.includes('U',0)))
									{$('#editlettersao').prop('disabled', false);
									 $('#upao').show();}
							    else
									{$('#editlettersao').prop('disabled', true);  $('#upao').hide();}
								if (val1.includes('E',0)  || !(mcpao.includes('E',0)))
									{$('#editemsao').prop('disabled', false); $('#epao').show();}
								else
								    {$('#editemsao').prop('disabled', true); $('#epao').hide();}
								if (val1.includes('C',0)  || !(mcpao.includes('C',0)))
									{$('#editparcelao').prop('disabled', false); $('#cpao').show();}
								else
									{$('#editparcelao').prop('disabled', true); $('#cpao').hide();} 
								if (val1.includes('T',0)  || !(mcpao.includes('T',0)))
									{$('#editemptyrecao').prop('disabled', false); $('#tpao').show();}
								else
								    {$('#editemptyrecao').prop('disabled', true); $('#tpao').hide();}
								/*if (val1.includes('U',0) && val1.includes('E',0) &&  val1.includes('C',0) && val1.includes('T',0) ) {
									$("#editallmailclsao").prop('disabled', false);
									$("#editallmailclsao").prop('checked', true);
								} else*/ {
									mailclssplitPAO = jsonObj["PAO"].toString().split(",");
									$.each(mailclssplitPAO, function(index, valuePAO) {
										if (valuePAO == 'U') {
											$('#editlettersao').prop('checked', true);
											$("#editallmailclsao").prop('disabled', false);
										} if (valuePAO == 'E') {
											$('#editemsao').prop('checked', true);
											$("#editallmailclsao").prop('disabled', false);
										} if (valuePAO == 'C') {
											$('#editparcelao').prop('checked', true);
											$("#editallmailclsao").prop('disabled', false);
										} if (valuePAO == 'T') {
											$('#editemptyrecao').prop('checked', true);
											$("#editallmailclsao").prop('disabled', false);
										}
									});
									editmailclsAO.push(jsonObj["PAO"]);
								}

							}
							
							
							if (getval3 == "PIN") {
								var val1= jsonObj["PIN"];
								if (val1.includes('U',0) || !(mcpin.includes('U',0)))
									{$('#editletterspin').prop('disabled', false);
									 $('#upin').show();}
							    else
									{$('#editletterspin').prop('disabled', true);  $('#upin').hide();}
								if (val1.includes('E',0)  || !(mcpin.includes('E',0)))
									{$('#editemspin').prop('disabled', false); $('#epin').show();}
								else
								    {$('#editemspin').prop('disabled', true); $('#epin').hide();}
								if (val1.includes('C',0)  || !(mcpin.includes('C',0)))
									{$('#editparcelpin').prop('disabled', false); $('#cpin').show();}
								else
									{$('#editparcelpin').prop('disabled', true); $('#cpin').hide();} 
								if (val1.includes('T',0)  || !(mcpin.includes('T',0)))
									{$('#editemptyrecpin').prop('disabled', false); $('#tpin').show();}
								else
								    {$('#editemptyrecpin').prop('disabled', true); $('#tpin').hide();}
								/*if (val1.includes('U',0) && val1.includes('E',0) &&  val1.includes('C',0) && val1.includes('T',0) ) {
									$("#editallmailclspin").prop('disabled', false);
									$("#editallmailclspin").prop('checked', true); $("#allmcpin").hide();   
								} else*/ {
									mailclssplitPIN = jsonObj["PIN"].toString().split(",");
									$.each(mailclssplitPIN, function(index, valuePIN) {
										if (valuePIN == 'U') {
											$('#editletterspin').prop('checked', true);
											$("#editallmailclspin").prop('disabled', false);
										} if (valuePIN == 'E') {
											$('#editemspin').prop('checked', true);
											$("#editallmailclspin").prop('disabled', false);
										} if (valuePIN == 'C') {
											$('#editparcelpin').prop('checked', true);
											$("#editallmailclspin").prop('disabled', false);
										} if (valuePIN == 'T') {
											$('#editemptyrecpin').prop('checked', true);
											$("#editallmailclpin").prop('disabled', false);
										}
									});
									editmailclsPIN.push(mailclssplitPIN);
								}

							}
							
							
							
							if (getval2 == "PSU") {
							var val1= jsonObj["PSU"];
								if (val1.includes('U',0) || !(mcpsu.includes('U',0)))
									{$('#editletterssup').prop('disabled', false);
									 $('#upsu').show();}
							    else
									{$('#editletterssup').prop('disabled', true);  $('#upsu').hide();}
								if (val1.includes('E',0)  || !(mcpsu.includes('E',0)))
									{$('#editemssup').prop('disabled', false); $('#epsu').show();}
								else
								    {$('#editemssup').prop('disabled', true); $('#epsu').hide();}
								if (val1.includes('C',0)  || !(mcpsu.includes('C',0)))
									{$('#editparcelsup').prop('disabled', false); $('#cpsu').show();}
								else
									{$('#editparcelsup').prop('disabled', true); $('#cpsu').hide();} 
								if (val1.includes('T',0)  || !(mcpsu.includes('T',0)))
									{$('#editemptyrecsup').prop('disabled', false); $('#tpsu').show();}
								else
								    {$('#editemptyrecsup').prop('disabled', true); $('#tpsu').hide();}
								/*if (val1.includes('U',0) && val1.includes('E',0) &&  val1.includes('C',0) && val1.includes('T',0) ) {
									$("#editallmailclssup").prop('disabled', false);
									$("#editallmailclssup").prop('checked', true);
								} 
							
								else*/ {
									mailclssplitPSU = jsonObj["PSU"].toString().split(",");
									$.each(mailclssplitPSU, function(index, valuePSU) {
										if (valuePSU == 'U') {
											$('#editletterssup').prop('checked', true);
											$('#editallmailclssup').prop('disabled', false);
										} if (valuePSU == 'E') {
											$('#editemssup').prop('checked', true);
											$('#editallmailclssup').prop('disabled', false);
										} if (valuePSU == 'C') {
											$('#editparcelsup').prop('checked', true);
											$('#editallmailclssup').prop('disabled', false);
										} if (valuePSU == 'T') {
											$('#editemptyrecsup').prop('checked', true);
											$('#editallmailclssup').prop('disabled', false);
										}
									});

									editmailclsPSU.push(mailclssplitPSU);
								}
							}
						},
						
		error: function(jqXHR, textStatus, errorThrown) {
                            window.location = 'error';
			           
			        }
					});

				},
				
		error: function(jqXHR, textStatus, errorThrown) {
            window.location = 'error';
           
        }

			});
			hideLoader();
		}

	});

});

function mcinit(){
	var mcpao = $('#mcpao').text();
	var mcpin = $('#mcpin').text();
	var mcpsu = $('#mcpsu').text();
	var i=0;
	if (mcpao.includes('U',0) && mcpao.includes('E',0) &&  mcpao.includes('C',0) && mcpao.includes('T',0) )
	{     $('#editallmailclsao').prop('disabled', true);
	      $('#allmcpao').hide();  }
    else{
	if(mcpao.includes('U',0))
	 {  $("#editlettersao").prop('disabled', true);
        $('#editallmailclsao').prop('disabled', true);
        $('#allmcpao').hide();
        $('#upao').hide();
        i=i+1;}
    else
     $("#editlettersao").prop('disabled', false);
    if(mcpao.includes('E',0))
	 { $("#editemsao").prop('disabled', true);
       $('#editallmailclsao').prop('disabled', true);
       $('#allmcpao').hide();
       $('#epao').hide();
       i=i+1;}
    else
     $("#editemsao").prop('disabled', false);
    if(mcpao.includes('C',0))
	 { $("#editparcelao").prop('disabled', true);
       $('#editallmailclsao').prop('disabled', true);
       $('#allmcpao').hide();
       $('#cpao').hide();
       i=i+1;}
     else
     $("#editparcelao").prop('disabled', false);
	 if(mcpao.includes('T',0))
	 { $("#editemptyrecao").prop('disabled', true);
       $('#editallmailclsao').prop('disabled', true);
       $('#allmcpao').hide();
       $('#tpao').hide();
       i=i+1;}
    else
     $("#editempyrecao").prop('disabled', false);
	if (i==4)
	   $("#edittrans-1").prop('disabled', true); }
    i=0;
	if (mcpin.includes('U',0) && mcpin.includes('E',0) &&  mcpin.includes('C',0) && mcpin.includes('T',0) )
	{     $('#editallmailclspin').prop('disabled', true);
	      $('#allmcpin').hide(); }
    else{
	if(mcpin.includes('U',0))
	 {  $("#editletterspin").prop('disabled', true);
        $('#editallmailclspin').prop('disabled', true);
        $('#allmcpin').hide();
        $("#upin").hide();
        i=i+1;}
    else
     $("#editletterspin").prop('disabled', false);
    if(mcpin.includes('E',0))
	 { $("#editemspin").prop('disabled', true);
       $('#editallmailclspin').prop('disabled', true);
       $('#allmcpin').hide();
       $("#epin").hide();
       i=i+1;}
    else
     $("#editemspin").prop('disabled', false);
    if(mcpin.includes('C',0))
	 { $("#editparcelpin").prop('disabled', true);
       $('#editallmailclspin').prop('disabled', true);
       $('#allmcpin').hide();
       $("#cpin").hide();
       i=i+1;}
     else
     $("#editparcelpin").prop('disabled', false);
	 if(mcpin.includes('T',0))
	 { $("#editemptyrecpin").prop('disabled', true);
       $('#editallmailclspin').prop('disabled', true);
       $('#allmcpin').hide();
       $("#tpin").hide();
       i=i+1;}
    else
     $("#editempyrecpin").prop('disabled', false);
	if (i==4)
	   $("#edittrans-2").prop('disabled', true); }
    i=0;
	if (mcpsu.includes('U',0) && mcpsu.includes('E',0) &&  mcpsu.includes('C',0) && mcpsu.includes('T',0) )
	{     $('#editallmailclssup').prop('disabled', true); 
	      $('#allmcpsu').hide();}
    else{
	if(mcpsu.includes('U',0))
	 {  $("#editletterssup").prop('disabled', true);
        $('#editallmailclssup').prop('disabled', true);
        $("#upsu").hide();
        $('#allmcpsu').hide();
        i=i+1;}
    else
     $("#editletterssup").prop('disabled', false);
    if(mcpsu.includes('E',0))
	 { $("#editemssup").prop('disabled', true);
       $('#editallmailclssup').prop('disabled', true);
       $("#epsu").hide();
       $('#allmcpsu').hide();
       i=i+1;}
    else
     $("#editemssup").prop('disabled', false);
    if(mcpsu.includes('C',0))
	 { $("#editparcelsup").prop('disabled', true);
       $('#editallmailclssup').prop('disabled', true);
        $("#cpsu").hide();
        $('#allmcpsu').hide();
       i=i+1;}
     else
     $("#editparcelsup").prop('disabled', false);
	 if(mcpsu.includes('T',0))
	 { $("#editemptyrecsup").prop('disabled', true);
       $('#editallmailclssup').prop('disabled', true);
       $("#tpsu").hide();
       $('#allmcpsu').hide();
       i=i+1;}
    else
     $("#editempyrecsup").prop('disabled', false);
	if (i==4)
	   $("#edittrans-3").prop('disabled', true); }
}





function calleditadminrole() {
	$.ajax({
		url: 'getadminrolenm?userdesc=' + userrole,
		async: false,
		success: function(val) {
			$.each(val, function(index, value) {
				$('.editadminrole').append(' <div class="card"><div class=" card-body" style="background: #2d2c2c;"><div class="round row"><input type="checkbox" value=' + value[1] + ' id="editadminrole' + index + '" /><label for="editadminrole' + index + '" style="font-weight: 600;font-size: larger; color: white;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + ' ' + "( For Designation - " + ' ' + value[2] + ' '+")"+'</p></div></div><br>');
			});
		},
	});
}

function calleditdsprole() {
	$.ajax({
		url: 'getdisprolenm?userdesc=' + userrole,
		async: false,
		success: function(val) {
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
			  // if (opt==0)	
				$('.edittransroles').append('<div ><div style="background: #5897fb;"><div><input type="checkbox" name="roleselc" value=' + value[1] + ' id="edittrans-' + index + '" /><label for="edittrans-' + index + '" id="editlabtrans-' + index + '" style="font-weight: 600;font-size: larger;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" id="edittextrans-' + index + '" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + ' ' + "( For Designation - " + ' ' + value[2] + ' '+")"+'</p></div></div><br>');
			});
		},
	});
}

function calleditmiserole() {
	$.ajax({
		url: 'getmiserole?userdesc=' + userrole,
		async: false,
		success: function(val1) {
			$.each(val1, function(index1, value) {
				$('.editmiscelroles').append('<div class="card"><div class=" card-body" style="background: #8e949a;"><div class="round row"><input type="checkbox" name="miscroleselc" value=' + value[1] + ' id="editmisce-' + index1 + 1 + '" /><label for="editmisce-' + index1 + 1 + '" style="font-weight: 600;font-size: larger;margin-left: 18px">&nbsp;' + value[1] + '</label></div></div><p class="card-text" style="color: #7e808c;margin-top: 5px;margin-left: 5px;">' + value[0] + '' + "( For Designation - " + ' ' + value[2] + ' '+")"+'</p></div></div><br>');
			});
		},

	});
}

$(document).on('change', '[id^=edittrans-]', function() {

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
			$('[id^=edittrans-]').each(function() {
				if ($(this).val() != 'PIN' && $(this).val() != 'PBS') {
					$(this).prop('disabled', true);
				} else {
					$(this).prop('disabled', false);
				}
			})
		}

		if ($(this).val() == 'PAO' || $(this).val() == 'PSU') {
			$('[id^=edittrans-]').each(function() {
				if ($(this).val() != 'PAO' && $(this).val() != 'PSU') {
					$(this).prop('disabled', true);
				} else {
					$(this).prop('disabled', false);
				}
			})
		}

	}
	else {
		if ($(this).val() == 'PAC') {
			$('[id^=edittrans-]').each(function() {
				$(this).prop('disabled', false);
			})
		}
		if ($(this).val() == 'PIN' || $(this).val() == 'PBS') {
			if ($('[id^=edittrans-]').filter(function() {
				return $(this).is(":checked")
			}).length > 0 && ($($('[id^=edittrans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PIN' || $($('[id^=edittrans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PBS')) {
				$('[id^=edittrans-]').each(function() {
					if ($(this).val() == 'PIN' || $(this).val() == 'PBS') {
						$(this).prop('disabled', false);
					} else {
						$(this).prop('disabled', true);
					}
				})
			} else {
				$('[id^=edittrans-]').each(function() {
					$(this).prop('disabled', false);
				})
			}
		}

		if ($(this).val() == 'PAO' || $(this).val() == 'PIN' || $(this).val() == 'PSU' ) {
			if ($('[id^=edittrans-]').filter(function() {
				return $(this).is(":checked")
			}).length > 0 && ($($('[id^=edittrans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PAO' || $($('[id^=edittrans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PIN' || $($('[id^=edittrans-]').filter(function() {
				return $(this).is(":checked")
			})[0]).val() == 'PSU')){
				$('[id^=edittrans-]').each(function() {
					if ($(this).val() == 'PAO' || $(this).val() == 'PIN' || $(this).val() == 'PSU') {
						$(this).prop('disabled', false);
					} else {
						$(this).prop('disabled', true);
					}
				})
			} else {
				$('[id^=edittrans-]').each(function() {
					$(this).prop('disabled', false);
				})
			}
		}

	}

	if ($(this).is(":checked")) {
		if ($(this).val() == 'PAO') {
			editmailclsAO.length = 0;
			$('#editmailclascfrapr').modal('show')

		} else if ($(this).val() == 'PIN') {
			editmailclsPIN.length = 0;
			$('#editmailclascfrpin').modal('show');
		} else if ($(this).val() == 'PSU') {
			editmailclsPSU.length = 0;
			$('#editmailclascfrsup').modal('show');
		}
		


	} else if ($(this).val() == 'PAO') {
		$('#editmailclascfrapr').modal('show')
		$('#editcnfrmrolermvAO').click(function() {
			editmailclsAO.length = 0;
			$('#editallmailclsao').prop('checked', false);
			$('#editlettersao').prop('checked', false);
			$('#editemsao').prop('checked', false);
			$('#editparcelao').prop('checked', false);
			$('#editemptyrecao').prop('checked', false);
		})
		$('#editnotsureAO').click(function() {
			$('[id^=edittrans-]').each(function() {
				if ($(this).val() == 'PAO') {
					$(this).prop('checked', true);
				}
			})
		});
	} else if ($(this).val() == 'PSU') {
		$('#editmailclascfrsup').modal('show')
		$('#editcnfrmrolermvPSU').click(function() {
			editmailclsPSU.length = 0;
			$('#editallmailclssup').prop('checked', false);
			$('#editletterssup').prop('checked', false);
			$('#editemssup').prop('checked', false);
			$('#editparcelsup').prop('checked', false);
			$('#editemptyrecsup').prop('checked', false);
		})
		$('#editnotsurePSU').click(function() {
			$('[id^=edittrans-]').each(function() {
				if ($(this).val() == 'PSU') {
					$(this).prop('checked', true);
				}
			})
		});
	} else if ($(this).val() == 'PIN') {
		$('#editmailclascfrpin').modal('show');
		$('#editcnfrmrolermvPIN').click(function() {
			editmailclsPIN.length = 0;
			$('#editallmailclspin').prop('checked', false);
			$('#editletterspin').prop('checked', false);
			$('#editemspin').prop('checked', false);
			$('#editparcelpin').prop('checked', false);
			$('#editemptyrecpin').prop('checked', false);
		})
		$('#editnotsurePIN').click(function() {
			$('[id^=edittrans-]').each(function() {
				if ($(this).val() == 'PIN') {
					$(this).prop('checked', true);
				}
			})
		});
	}
})

// Max adminrole per Site
$(document).on('change', '[id^=editadminrole]', function() {
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
							$('[id^=editadminrole]').each(function() {
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
							$('[id^=editadminrole]').each(function() {
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

$(document).on('change', '[id^=editmisce-]', function() {
	if ($(this).is(":checked")) {
		if ($(this).val() == 'ARP') {
			var role3 = 'ARP';
			$.ajax({
				url: 'getcountofARP?roleNm=' + role3,
				async: false,
				success: function(countofARP) { 
					if (countofARP >= 5) {
						$('#ARPmaxrole').modal('show');
						$('#ARProlelimit').click(function() {
							$('[id^=editmisce-]').each(function() {
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
var editmailclsAO = [];
$('#editmailclsAo').click(function() {
	mailclssplitPAO.length = 0;
	editmailclsAO.length = 0;
	if ($('#editallmailclsao').is(":checked") == false && $('#editlettersao').is(":checked") == false && $('#editemsao').is(":checked") == false && $('#editparcelao').is(":checked") == false && $('#editemptyrecao').is(":checked") == false) {
		$('[id^=edittrans-]').each(function() {
			if ($(this).val() == 'PAO') {
				$(this).prop('checked', false);
			} if ($(this).val() != 'PAO') {
				$(this).prop('disabled', false);
			}
		})
	} else {
		$('[id^=edittrans-]').each(function() {
			if ($(this).val() == 'PAO') {
				$(this).prop('checked', true);
			}
		})
	}
	//Assign vlaue to selected Mail-class
	if ($('#editallmailclsao').is(":checked") == true) {
		$('#editallmailclsao').val('U,E,C,T');
		editmailclsAO.push($('#editallmailclsao').val());
	}else{ if ($('#editlettersao').is(":checked") == true) {
		$('#editlettersao').val('U');
		editmailclsAO.push($('#editlettersao').val());
	} if ($('#editemsao').is(":checked") == true) {
		$('#editemsao').val('E');
		editmailclsAO.push($('#editemsao').val());
	} if ($('#editparcelao').is(":checked") == true) {
		$('#editparcelao').val('C');
		editmailclsAO.push($('#editparcelao').val());
	} if ($('#editemptyrecao').is(":checked") == true) {
		$('#editemptyrecao').val('T');
		editmailclsAO.push($('#editemptyrecao').val());
	}
}
})

//compulsary 1 mail class is sel for PAO and PSU,
var editmailclsPSU = [];
$('#editmailclssup').click(function() {
	mailclssplitPSU.length = 0;
	editmailclsPSU.length = 0;
	if ($('#editallmailclssup').is(":checked") == false && $('#editletterssup').is(":checked") == false && $('#editemssup').is(":checked") == false && $('#editparcelsup').is(":checked") == false && $('#editemptyrecsup').is(":checked") == false) {
		$('[id^=edittrans-]').each(function() {
			if ($(this).val() == 'PSU') {
				$(this).prop('checked', false);
			} if ($(this).val() != 'PSU') {
				$(this).prop('disabled', false);
			}
		})
	} else {
		$('[id^=edittrans-]').each(function() {
			if ($(this).val() == 'PSU') {
				$(this).prop('checked', true);
			}
		})
	}
	//Assign vlaue to selected Mail-class
	if ($('#editallmailclssup').is(":checked") == true) {
		$('#editallmailclssup').val('U,E,C,T');
		editmailclsPSU.push($('#editallmailclssup').val());
	}else{ if ($('#editletterssup').is(":checked") == true) {
		$('#editletterssup').val('U');
		editmailclsPSU.push($('#editletterssup').val());
	} if ($('#editemssup').is(":checked") == true) {
		$('#editemssup').val('E');
		editmailclsPSU.push($('#editemssup').val());
	} if ($('#editparcelsup').is(":checked") == true) {
		$('#editparcelsup').val('C');
		editmailclsPSU.push($('#editparcelsup').val());
	} if ($('#editemptyrecsup').is(":checked") == true) {
		$('#editemptyrecsup').val('T');
		editmailclsPSU.push($('#editemptyrecsup').val());
	}
	}
})


var editmailclsPIN = [];
$('#editmailclspin').click(function() {
	mailclssplitPIN.length = 0;
	editmailclsPIN.length = 0;
	if ($('#editallmailclspin').is(":checked") == false && $('#editletterspin').is(":checked") == false && $('#editemspin').is(":checked") == false && $('#editparcelpin').is(":checked") == false && $('#editemptyrecpin').is(":checked") == false) {
		$('[id^=edittrans-]').each(function() {
			if ($(this).val() == 'PIN') {
				$(this).prop('checked', false);
			} if ($(this).val() != 'PIN') {
				$(this).prop('disabled', false);
			}
		})
	} else {
		$('[id^=edittrans-]').each(function() {
			if ($(this).val() == 'PIN') {
				$(this).prop('checked', true);
			}
		})
	}
	//Assign vlaue to selected Mail-class
	if ($('#editallmailclspin').is(":checked") == true) {
		$('#editallmailclspin').val('U,E,C,T');
		editmailclsPIN.push($('#editallmailclspin').val());
	}else{
		
	if ($('#editletterspin').is(":checked") == true) {
		$('#editletterspin').val('U');
		editmailclsPIN.push($('#editletterspin').val());
	} if ($('#editemspin').is(":checked") == true) {
		$('#editemspin').val('E');
		editmailclsPIN.push($('#editemspin').val());
	} if ($('#editparcelpin').is(":checked") == true) {
		$('#editparcelpin').val('C');
		editmailclsPIN.push($('#editparcelpin').val());
	} if ($('#editemptyrecpin').is(":checked") == true) {
		$('#editemptyrecpin').val('T');
		editmailclsPIN.push($('#editemptyrecpin').val());
	}
	} 
})






$('#editallmailclsao').change(function() {
	if ($('#editallmailclsao').is(":checked") == true) {
		$("#editlettersao").prop('disabled', true).prop('checked', true);
		$('#editemsao').prop('disabled', true).prop('checked', true);
		$('#editparcelao').prop('disabled', true).prop('checked', true);
		$('#editemptyrecao').prop('disabled', true).prop('checked', true);
	} else {
		$("#editlettersao").prop('disabled', false).prop('checked', false);
		$('#editemsao').prop('disabled', false).prop('checked', false);
		$('#editparcelao').prop('disabled', false).prop('checked', false);
		$('#editemptyrecao').prop('disabled', false).prop('checked', false);
	}
});


$('#editlettersao').change(function() {
	if ($('#editlettersao').is(":checked") == true || $('#editemsao').is(":checked") == true || $('#editparcelao').is(":checked") == true || $('#editemptyrecao').is(":checked") == true) {
		$("#editallmailclsao").prop('disabled', true);
	} else {
		$("#editallmailclsao").prop('disabled', false);
	}
});

$('#editemsao').change(function() {
	if ($('#editlettersao').is(":checked") == true || $('#editemsao').is(":checked") == true || $('#editparcelao').is(":checked") == true || $('#editemptyrecao').is(":checked") == true) {
		$("#editallmailclsao").prop('disabled', true);
	} else {
		$("#editallmailclsao").prop('disabled', false);
	}
});

$('#editparcelao').change(function() {
	if ($('#editlettersao').is(":checked") == true || $('#editemsao').is(":checked") == true || $('#editparcelao').is(":checked") == true || $('#editemptyrecao').is(":checked") == true) {
		$("#editallmailclsao").prop('disabled', true);
	} else {
		$("#editallmailclsao").prop('disabled', false);
	}
});

$('#editemptyrecao').change(function() {
	if ($('#editlettersao').is(":checked") == true || $('#editemsao').is(":checked") == true || $('#editparcelao').is(":checked") == true || $('#editemptyrecao').is(":checked") == true) {
		$("#editallmailclsao").prop('disabled', true);
	} else {
		$("#editallmailclsao").prop('disabled', false);
	}
});

// Mail class conditions for PSU
$('#editallmailclssup').change(function() {
	if ($('#editallmailclssup').is(":checked") == true) {
		$("#editletterssup").prop('disabled', true).prop('checked', true);
		$('#editemssup').prop('disabled', true).prop('checked', true);
		$('#editparcelsup').prop('disabled', true).prop('checked', true);
		$('#editemptyrecsup').prop('disabled', true).prop('checked', true);
	} else {
		$("#editletterssup").prop('disabled', false).prop('checked', false);
		$('#editemssup').prop('disabled', false).prop('checked', false);
		$('#editparcelsup').prop('disabled', false).prop('checked', false);
		$('#editemptyrecsup').prop('disabled', false).prop('checked', false);
	}
});

$('#editletterssup').change(function() {
	if ($('#editletterssup').is(":checked") == true || $('#editemssup').is(":checked") == true || $('#editparcelsup').is(":checked") == true || $('#editemptyrecsup').is(":checked") == true) {
		$("#editallmailclssup").prop('disabled', true);
	} else {
		$("#editallmailclssup").prop('disabled', false);
	}
});

$('#editemssup').change(function() {
	if ($('#editletterssup').is(":checked") == true || $('#editemssup').is(":checked") == true || $('#editparcelsup').is(":checked") == true || $('#editemptyrecsup').is(":checked") == true) {
		$("#editallmailclssup").prop('disabled', true);
	} else {
		$("#editallmailclssup").prop('disabled', false);
	}
});

$('#editparcelsup').change(function() {
	if ($('#editletterssup').is(":checked") == true || $('#editemssup').is(":checked") == true || $('#editparcelsup').is(":checked") == true || $('#editemptyrecsup').is(":checked") == true) {
		$("#editallmailclssup").prop('disabled', true);
	} else {
		$("#editallmailclssup").prop('disabled', false);
	}
});

$('#editemptyrecsup').change(function() {
	if ($('#editletterssup').is(":checked") == true || $('#editemssup').is(":checked") == true || $('#editparcelsup').is(":checked") == true || $('#editemptyrecsup').is(":checked") == true) {
		$("#editallmailclssup").prop('disabled', true);
	} else {
		$("#editallmailclssup").prop('disabled', false);
	}
});


$('#editallmailclspin').change(function() {
	if ($('#editallmailclspin').is(":checked") == true) {
		$("#editletterspin").prop('disabled', true).prop('checked', true);
		$('#editemspin').prop('disabled', true).prop('checked', true);
		$('#editparcelpin').prop('disabled', true).prop('checked', true);
		$('#editemptyrecpin').prop('disabled', true).prop('checked', true);
	} else {
		$("#editletterspin").prop('disabled', false).prop('checked', false);
		$('#editemspin').prop('disabled', false).prop('checked', false);
		$('#editparcelpin').prop('disabled', false).prop('checked', false);
		$('#editemptyrecpin').prop('disabled', false).prop('checked', false);
	}
});

$('#editletterspin').change(function() {
	if ($('#editletterspin').is(":checked") == true || $('#editemspin').is(":checked") == true || $('#editparcelpin').is(":checked") == true || $('#editemptyrecpin').is(":checked") == true) {
		$("#editallmailclspin").prop('disabled', true);
	} else {
		$("#editallmailclspin").prop('disabled', false);
	}
});

$('#editemspin').change(function() {
	if ($('#editletterspin').is(":checked") == true || $('#editemspin').is(":checked") == true || $('#editparcelpin').is(":checked") == true || $('#editemptyrecpin').is(":checked") == true) {
		$("#editallmailclspin").prop('disabled', true);
	} else {
		$("#editallmailclspin").prop('disabled', false);
	}
});

$('#editparcelpin').change(function() {
	if ($('#editletterspin').is(":checked") == true || $('#editemspin').is(":checked") == true || $('#editparcelpin').is(":checked") == true || $('#editemptyrecpin').is(":checked") == true) {
		$("#editallmailclspin").prop('disabled', true);
	} else {
		$("#editallmailclspin").prop('disabled', false);
	}
});

$('#editemptyrecpin').change(function() {
	if ($('#editletterspin').is(":checked") == true || $('#editemspin').is(":checked") == true || $('#editparcelpin').is(":checked") == true || $('#editemptyrecpin').is(":checked") == true) {
		$("#editallmailclspin").prop('disabled', true);
	} else {
		$("#editallmailclspin").prop('disabled', false);
	}
});



var selectval = [];
var editroledata = [];
var val = {};
var val1 = {};
var val2 = {};
var val3 = {};
var mailclasPAO = [];
$('.mailClas input[type=checkbox]').change(function() {
	if ($(this).prop("checked")) {
		mailclasPAO.push($(this).val());
	} else {
		mailclasPAO.remove($(this).val());
	}

});

var mailclasPSU = [];
$('.mailclasSUP input[type=checkbox]').change(function() {
	if ($(this).prop("checked")) {
		mailclasPSU.push($(this).val());
	} else {
		mailclasPSU.remove($(this).val());
	}
});

var mailclasPIN = [];
$('.mailclasPIN input[type=checkbox]').change(function() {
	if ($(this).prop("checked")) {
		mailclasPIN.push($(this).val());
	} else {
		mailclasPIN.remove($(this).val());
	}
});



var roleval = "";
var roleval1 = "";
var Lsmuser = $('#LsmOffid').val();
editroledata.empty;
$('#Updatederles').click(function() {
	showLoader();
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

	$('[id^=edittrans-]').each(function() {
		if ($(this).is(":checked")) {
			editroledata.push($(this).val());
		}
	})

	if (editroledata.length != 0) {
		$('#selectedroles').append("Selected Roles - " + "<span class='label label-important' style='color : #1616e0'>" + editroledata.toString().split(",").join(", ") + "</span>");
		$('#rolecnfrm').modal('show');
		hideLoader();
		$('#addrole').click(function() {
			showLoader();
			$.ajax({
				url: "editassignroles?checkedarray=" + editmailclsAO + "&rolenme=" + editroledata + "&usrid=" + selecteduser + "&cusSite=" + $('#lsmCusite').val() + "&mailclsSUP=" + editmailclsPSU + "&mailclsPIN=" + editmailclsPIN + "&LoginUser=" + Lsmuser,
				type: "get",
				success: function(data) {
					hideLoader();
					swal("Success!", "Roles Updated Successfuly!", "success")
						.then((value) => {
							window.location.href = "editroles";
						});
				},
			});

		});
	} else {
		hideLoader();
		$('#selectedroles').append("Do you want to remove all roles?")
		$('#rolecnfrm').modal('show');
		$('#addrole').click(function() {
			showLoader();
			$.ajax({
				url: "removeallroles?usrid=" + selecteduser + "&LoginUser=" + Lsmuser + "&cusSite=" + $('#lsmCusite').val(),
				type: "get",
				success: function(data) {
					hideLoader();
					swal("Success!", "All roles removed successfuly!", "success")
						.then((value) => {
							window.location.href = "editroles";
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
	$('[id^=editadminrole]').prop('checked', false);
	$('[id^=editmisce-]').prop('checked', false);
	$('[id^=edittrans-]').prop('checked', false);
});

function alloctrole() {
	window.location.href = "roles_allocation";
}

function Nsmalloctrole() {
	window.location.href = "nsm_allotrole";
}

$('#cancelderles').click(function() {
	window.location.href = "editroles";
})

$('#bcktoselrole').click(function() {
	window.location.href = "editroles";
})

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