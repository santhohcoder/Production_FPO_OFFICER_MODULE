$('.check input:checkbox').click(function() {
	$('.check input:checkbox').not(this).prop('checked', false);
});

function redirectDetensiontoOrder() {
	window.location.href = "order?id=" + $('#cinNo').val();
}

$(document).ready(function() {
	if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && "PAC" == $('#role').val() ){	
		$("#aclMsg").show();
	}
});


function stopBlink(){
$('#enableButton').removeClass('blink1');
}


/*function submitDecision() {
    $("#overlay").css("display", "block");
	var cat="";
	if ($('#assstop').val()=='ASSSTOP')
     cat='ASSSTOP';
    	$.ajax({
        url: 'decisionSubmitResponse?'+$('form').serialize()+'&cat='+cat,
        type: "post",
        success: function(data) {
		$("#overlay").css("display", "none");
			if(data.mailSent){
				localStorage.removeItem('docName');
				swal("Mail Sent Successfully !","", "success").then((value) => {
 					 window.location.href = "ead_list";
				});
			}else{
				localStorage.removeItem('docName');
				window.location.href = "ead_list";
			}
		},
        fail: function(rs, e) {
            console.log(rs, responseText);
        }
    });
}*/

function submitDecision() {
    $("#overlay").css("display", "block");
	var cat="";
	if ($('#assstop').val()=='ASSSTOP')
     cat='ASSSTOP';
    	$.ajax({
        url: 'decisionSubmitResponse?'+$('form').serialize()+'&cat='+cat,
        type: "post",
        success: function(data) {
		$("#overlay").css("display", "none");
			if(data.mailSent){
				localStorage.removeItem('docName');
				swal("Mail Sent Successfully !","", "success").then((value) => {
 					// window.location.href = "ead_list";
				eadlist_direct();
				});
			}else{
				localStorage.removeItem('docName');
				//window.location.href = "ead_list";
				eadlist_direct();
			}
		},
        fail: function(rs, e) {
            console.log(rs, responseText);
        }
    });
}



function eadlist_direct(){
	var flag = $("#flag").val();
	$.ajax({
        url: 'ead_main_direct',
        type: "post",
        success: function(data) {
		   if (flag == "showPAC" || flag == "showExp" || flag == "showDemin" || flag == "showdoc" || flag == "showaSide"){
				window.location.href="ead_list";
			         }
			else if ($('#role2blink').val() !='PAC'){
				   localStorage.setItem("blinking", true);
				   window.location.href="ead_main?id="+data;
			} else {
				window.location.href = "ead_list";
			}
		}
        
    });
}



$(document).ready(function() {
	if ($('#firstCheck').val() == "Y" && "PAC" == $('#role').val()) {
		$("#aclMsgFirst").show();
			$("#aclMsg").hide();
	}
});


$(document).ready(function() {
	if ($('#assstop').val()=='ASSSTOP')
		{
				$('#1').attr("disabled", true);
				$('#2').attr("disabled", true);
				$('#3').attr("disabled", true);
				$('#4').attr("disabled", true);
				$('#5').attr("disabled", true);
				$('#6').attr("disabled", true);
				$('#9').attr("disabled", true);			
		}
});




$.ajax({
	url: 'decisionSubmitStatus?cinNo=' + $('#cinNo').val(),
	data: JSON.stringify(""),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(data) {
		if ($('#assstop')=='ASSSTOP')
		{
				$('#1').attr("disabled", true);
				$('#2').attr("disabled", true);
				$('#3').attr("disabled", true);
				$('#4').attr("disabled", true);
				$('#6').attr("disabled", true);
				$('#9').attr("disabled", true);			
		}
	     else {
	    if (parseInt(data[5]) >0) {

		if (parseInt(data[4]) > 0) {
			$('#2').attr("disabled", true);
			$('#3').attr("disabled", true);
			$('#4').attr("disabled", true);
			$('#5').attr("disabled", true);
			$('#9').attr("disabled", true);
		} 
		else {

			if (parseInt(data[0]) == 0 && parseInt(data[3]) == 0 && parseInt(data[1]) > 0) {
				$('#2').attr("disabled", true);
				$('#3').attr("disabled", true);
				$('#4').attr("disabled", true);
			}

			if (parseInt(data[1]) == 0 && parseInt(data[3]) > 0 && parseInt(data[0]) > 0) {
				$('#4').attr("disabled", true);
				$('#9').attr("disabled", true);
			}

			if (parseInt(data[1]) > 0 && parseInt(data[3]) > 0 && parseInt(data[0]) > 0) {
				$('#1').attr("disabled", true);
				$('#2').attr("disabled", true);
				$('#3').attr("disabled", true);
				$('#4').attr("disabled", true);
				$('#6').attr("disabled", true);
				$('#7').attr("disabled", true);
				$('#8').attr("disabled", true);
				$('#9').attr("disabled", true);
			}

			if (parseInt(data[1]) > 0 && parseInt(data[3]) > 0 && parseInt(data[0]) == 0) {
				$('#1').attr("disabled", true);
				$('#2').attr("disabled", true);
				$('#3').attr("disabled", true);
				$('#4').attr("disabled", true);
				$('#6').attr("disabled", true);
				$('#7').attr("disabled", true);
				$('#8').attr("disabled", true);
				$('#9').attr("disabled", true);
			}

			if (parseInt(data[0]) == 0 && parseInt(data[3]) > 0 && parseInt(data[1]) == 0) {
				$('#1').attr("disabled", true);
				$('#3').attr("disabled", true);
				$('#5').attr("disabled", true);
				$('#6').attr("disabled", true);
				$('#7').attr("disabled", true);
				$('#8').attr("disabled", true);
				$('#9').attr("disabled", true);
			}

			if (parseInt(data[0]) > 0 && parseInt(data[1]) > 0 && parseInt(data[3]) == 0) {
				$('#2').attr("disabled", true);
				$('#3').attr("disabled", true);
				$('#4').attr("disabled", true);
				$('#9').attr("disabled", true);
			}
			}}
			else
			{
	     		$('#2').attr("disabled", true);
				$('#3').attr("disabled", true);
				$('#4').attr("disabled", true);
				if (parseInt(data[1]) > 0)
				  $('#9').attr("disabled", false);
				else
				  $('#9').attr("disabled", false);
				}
		}

	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});

sessionStorage.setItem("one", 0);
sessionStorage.setItem("two", 0);
sessionStorage.setItem("thr", 0);
sessionStorage.setItem("fur", 0);
sessionStorage.setItem("fiv", 0);
sessionStorage.setItem("six", 0);
sessionStorage.setItem("egt", 0);
sessionStorage.setItem("nin", 0);


$(".big-checkbox").click(function() {
	if ($('.big-checkbox').is(':checked')) {
		$("#okModal").hide();
		if ($('#assstop').val()!='ASSSTOP'){
		var decFlag = $(this).val();
		var countOne = 0;
		var countTwo = 0;
		var countThr = 0;
		var countFur = 0;
		var countFiv = 0;
		var countSix = 0;
		var countEgt = 0;
		var countNin = 0;

		var developerData = {};
		developerData['cin_NO'] = $('#cinNo').val();
		var resObj = $.ajax({
			url: 'decisionSubmitStatus?cinNo=' + $('#cinNo').val(),
			data: JSON.stringify(""),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(developerDatas) {
				if ("Detain" == developerDatas[2] && sessionStorage.getItem("one") == "0") {
					countOne = countOne + 1;
				} else {
					count = 0;
				} if ("Detain" == developerDatas[2] && sessionStorage.getItem("two") == "0") {
					countTwo = countTwo + 1;
				} else {
					count = 0;
				} if ("Detain" == developerDatas[2] && sessionStorage.getItem("thr") == "0") {
					countThr = countThr + 1;
				} else {
					count = 0;
				} if ("Detain" == developerDatas[2] && sessionStorage.getItem("fur") == "0") {
					countFur = countFur + 1;
				} else {
					count = 0;
				} if ("Detain" == developerDatas[2] && sessionStorage.getItem("fiv") == "0") {
					countFiv = countFiv + 1;
				} else {
					count = 0;
				} if ("Detain" == developerDatas[2] && sessionStorage.getItem("six") == "0") {
					countSix = countSix + 1;
				} else {
					count = 0;
				} if ("Detain" == developerDatas[2] && sessionStorage.getItem("egt") == "0") {
					countEgt = countEgt + 1;
				} else {
					count = 0;
				} if ("Detain" == developerDatas[2] && sessionStorage.getItem("nin") == "0") {
					countNin = countNin + 1;
				} else {
					count = 0;
				}
				if (decFlag == 2 || decFlag == 4) {
					if (countTwo == 0) {
						$('#dinValidate1').text("");
						if (developerDatas[0] <= 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("You are completing the assessment item will be moved to OOC Queue on arrival of the articles.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] <= 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Item will be moved to Examination Queue on arrival of the articles.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						}

						if (developerDatas[0] <= 0) {
							$('#dinValidate').text("You are completing the assessment item will be moved to OOC Queue on arrival of the articles.");
							$("#submitModal").modal('show');
						} else {
							$('#dinValidate').text("Item will be moved to Examination Queue on arrival of the articles.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						}
					} else {
						$("#detentionModal").modal('show');
						sessionStorage.setItem("two", 1);
					}

				} else if (decFlag == 1) {
					if (countOne == 0) {
						$('#dinValidate1').text("");
						if (developerDatas[0] <= 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Examination Order is Mandatory, please fill it.");
							$('#dinValidate1').text(" ");
							$("#submitModal").modal('show');
						} else if (developerDatas[0] <= 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Item will be moved to Examination Queue on arrival of the articles.");
							$('#dinValidate1').text(" ");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						}
					} else {
						$("#detentionModal").modal('show');
						sessionStorage.setItem("one", 1);
					}
				} else if (decFlag == 6) {
					if (countSix == 0) {
						$('#dinValidate1').text("");
						if (developerDatas[0] <= 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Examination Order is Mandatory, please fill it.");
							$("#submitModal").modal('show');
						} else if (developerDatas[0] <= 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Item will be moved to Examination Queue on arrival of the articles.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						}
					} else {
						$("#detentionModal").modal('show');
						sessionStorage.setItem("six", 1);
					}
				} else if (decFlag == 3) {
					if (countThr == 0) {
						$('#dinValidate1').text("");
						if (developerDatas[0] <= 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Examination Order is Mandatory, please fill it.");
							$("#submitModal").modal('show');
						} else if (developerDatas[0] <= 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Item will be moved to Examination Queue on arrival of the articles.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						}
					} else {
						$("#detentionModal").modal('show');
						sessionStorage.setItem("thr", 1);
					}
				} else if (decFlag == 7) {
					$('#dinValidate1').text("");
					if (developerDatas[0] <= 0 && developerDatas[1] <= 0) {
						$('#dinValidate').text("Examination Order is Mandatory, please fill it.");
						$("#submitModal").modal('show');
					} else if (developerDatas[0] <= 0 && developerDatas[1] > 0) {
						$('#dinValidate').text("Item will be moved to Query Queue.");
						$('#orderPage').hide();
						$("#okModal").show();
						$("#submitModal").modal('show');
					} else if (developerDatas[0] > 0 && developerDatas[1] <= 0) {
						$('#dinValidate').text("Item will be moved to Examination Queue on arrival of the articles.");
						$('#orderPage').hide();
						$("#okModal").show();
						$("#submitModal").modal('show');
					} else if (developerDatas[0] > 0 && developerDatas[1] > 0) {
						$('#dinValidate').text("Item will be moved to Query Queue.");
						$('#orderPage').hide();
						$("#okModal").show();
						$("#submitModal").modal('show');
					}
				} else if (decFlag == 8) {
					if (countEgt == 0) {
						$('#dinValidate1').text("");
						if (developerDatas[0] <= 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Examination Order is Mandatory, please fill it.");
							$("#submitModal").modal('show');
						} else if (developerDatas[0] <= 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Item will be moved to Examination Queue on arrival of the articles.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue. .");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						}
					} else {
						$("#detentionModal").modal('show');
						sessionStorage.setItem("egt", 1);
					}
				} else if (decFlag == 5) {
					if (countFiv == 0) {
						$('#dinValidate1').text("");
						if (developerDatas[0] <= 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Examination Order is Mandatory, please fill it.");
							$("#submitModal").modal('show');
						} else if (developerDatas[0] <= 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Examination Order is Mandatory, please fill it.");
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Item will be moved to Examination Queue on arrival of the articles.");
							$('#dinValidate1').text("Exercise the option of raising query with the importer for submission of documents related to clearance from other government department if any required.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Item will be moved to Query Queue.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						}
					} else {
						$("#detentionModal").modal('show');
						sessionStorage.setItem("fiv", 1);
					}
				} else if (decFlag == 9) {
					if (countNin == 0) {
						$('#dinValidate1').text("");
						if (developerDatas[0] <= 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Item will be moved to Commercial Queue. ");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] <= 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Article ID will move to Pending 'Query' Reply queue. Only after processing Query reply in AAA / AAF module,  Article ID can be moved to Commercial Import queue. ");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');;
						} else if (developerDatas[0] > 0 && developerDatas[1] <= 0) {
							$('#dinValidate').text("Item will be moved to Examination Queue on arrival of the articles.");
							$('#orderPage').hide();
							$("#okModal").show();
							$("#submitModal").modal('show');
						} else if (developerDatas[0] > 0 && developerDatas[1] > 0) {
							$('#dinValidate').text("Article ID will move to Pending 'Query' Reply queue. Only after processing Query reply in AAA / AAF module,  Article ID can be moved to Commercial Import queue.");
							$("#okModal").show();
							$("#submitModal").modal('show');
						}
					} else {
						$("#detentionModal").modal('show');
						sessionStorage.setItem("fiv", 1);
					}
				}
			},
			fail: function(rs, e) {
				alert("Error in Assessment");
			}
		});

}

		if ($(this).is(":checked")) {
			$('#enableButton').attr("disabled", false);
			var blink = document.getElementById('enableButton');
			$('#enableButton').addClass('blink1');
     // setInterval(function() {
      //  blink.style.opacity = (blink.style.opacity == 0 ? 1 : 0);
      //}, 500);
      document.getElementById("enableButton").focus();
		} else {
			$('#enableButton').attr("disabled", true);
		}
	} else {
		count = 0;
		$("#submitModal").modal('hide');
		$('#enableButton').attr("disabled", true);
	}
});

function redirect() {
	window.location.href = "order?id=" + $('#cinNo').val();
}

function closeModal() {
	$(".big-checkbox").prop("checked", false);
	$('#enableButton').attr("disabled", true);
}

function okModal() {
	$("#submitModal").modal('hide');
	document.getElementById("enableButton").focus();
}

$('#enableButton').attr("disabled", true);

function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}
