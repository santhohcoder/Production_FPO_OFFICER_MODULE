document.getElementById("siteactve").disabled = true;
function EnableDisable(recordMsg) {
	var str = recordMsg.value.trim();
	if (recordMsg.value.length <= 4 || str == "") {
		document.getElementById("siteactve").disabled = true;
	} else {
		document.getElementById("siteactve").disabled = false;
}	
}	
function siteactivate(act) {

var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#recordMsg').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }

	var data = {};
	$("#actstereas").modal('show');
	$('#siteactve').click(function() {
		data['siteCode'] = act.id;
		data['siteName'] = act.value;
		data['reason'] = $('.activeresp').val();
		$("#activatesite").modal('show');
	$('#activestefpo').click(function() {
			$.ajax({
				url: 'fpositeactivate',
				data: JSON.stringify(data),
				contentType: "application/json",
				type: "post",
				success: function(data) {
					window.location = "siteAddActiveView";
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
});

}



$('#activeclose').click(function() {
	location.reload();
});

/*$(document).ready(function() {
	$("#mytable").dataTable({
		"processing": true,
		"paging": true
	});
});*/

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

function deletesite(act) {
	var data = {};
	$("#deleteSiteRe").modal('show');
	$('#siteDelte').click(function() {
		var reg =/<(.|\n)*?>/g;
			 if (reg.test($('.deleteresp').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
		data['siteCode'] = act.id;
		data['siteName'] = act.value;
		data['reason'] = $('.deleteresp').val();
		$("#deletesite").modal('show');
	$('#delsite').click(function() {
			$.ajax({
				url: 'deletefposite',
				data: JSON.stringify(data),
				contentType: "application/json",
				type: "post",
				success: function(data) {
					window.location = "siteAddActiveView";
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
});

}

/*function deletesite(act){
	var data = {};
	$("#deletesite").modal('show');
	
	$('#delsite').click(function() {
		
		var userval = $('#offId').val();
		
		
		
		data['siteCode'] = act.id;
		data['siteName'] = act.value;
		data['reason'] = $('.activeresp').val();
		//$("#activatesite").modal('show');
	//$('#delsite').click(function() {
			$.ajax({
					url: 'deletefposite',
					data: JSON.stringify(data),
					dataType: "json",
					contentType: "application/json",
					type: "post",
					success: function(resp) {
							window.location = "siteAddActiveView";
								//	window.location = "viewactiveList";
							
					},
					fail: function(rs, e) {
						console.log(rs, responseText);
					}

				});

	//});
});
	

}*/






