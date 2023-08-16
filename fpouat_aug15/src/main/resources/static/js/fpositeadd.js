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

$(document).ready(function() {
	$('#siteadd').attr('disabled', true);
});

$('#siteadd').click(function() {
	var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#sitecd').val()) == true || reg.test($('#sitetype').val()) == true || reg.test($('#rmks').val()) == true  ) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }

	$('#addsitepopup').modal('show');
});

$('#siteaddconf').click(function() {
	var nsmdata = {};
	var userval = $('#lginid').val();
	nsmdata['siteName'] = $('#sitename').text();
	nsmdata['siteCode'] = $('#sitecd').val();
//	nsmdata['siteState'] = $('#stname').val();
	nsmdata['siteState'] = $('#statename').val();
	nsmdata['siteType'] = $('#sitetype').val();
	if($('#sitecd').val()=='INKOC5')
	      nsmdata['mapcode'] = 'INCOK5';
	else
	      nsmdata['mapcode'] = $('#sitecd').val();
	if ($('#clus').prop('checked'))
	   nsmdata['clustered'] = 1;
	else
	   nsmdata['clustered'] = '';
    nsmdata['clussite']= $('#clussite').val();
	nsmdata['reason'] =  $('#rmks').val();
	/*$.ajax({
		url: 'getalladdedsite',
		success: function(data) {
			var val = data;
			var val1 = 0;
			$.each(val, function(index, value) {
				if ($('#sitecode').val() == value || slectedcde == value) {
					val1 = 1;
					$("#addsitepopup").modal('hide');
					$("#siteaddalrdy").modal('show');
					$("#siterefresh").click(function() {
						return;
					})
				}
			});
			if (val1 == 0) {
				$("#addsitepopup").modal('show');
				$('#siteadsve').click(function() {
				if (userval != "") {*/
					$.ajax({
						url: 'getfpositeadd',
						data: JSON.stringify(nsmdata),
						dataType: "json",
						contentType: "application/json",
						type: "post",
						success: function(data) {
							$("#siteaddsucces").modal('show');
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
					
					

			/*	} else {
					alert("No longer login-id required, please login again!.");

				};
				
				});
			}
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		},

	});*/

});


	$("#fpositesucc").click(function() {
								window.location = "siteAddActiveView";
							})

//Autocomplete search box
/*$(document).ready(function() {

	$('#sitename').typeahead({
		source: function(query, result) {
			$.ajax({
				url: 'getCiteCode?term=' + query,
				contentType: "application/json",
				type: "post",
				dataType: "json",
				success: function(data) {
					result($.map(data, function(item) {
						return item;
					}));

				},

			});
		}
	});

});
*/




$(document).ready(function() {
	var element=document.getElementById('clussite');
    element.style.display='none';
	/*$('#sitecode').val("");
	$('#clussite').hide();
	$('#sitestate').val("");
	$('#sitetype').val("");
	$('#siteadd').attr('disabled', true);
}else{
		$.ajax({
			url: 'getsiteoflist?list=' + selectedState,
			success: function(data) {
				$.each(data, function(key, value) {
					if (key == 0) {
						$('#sitecode').val(value[0]);
						$('#sitestate').val(value[1]);
						$('#sitetype').val(value[2]);
						$('#sitecode1').text(value[0]);
						$('#displyoption').hide();
						$('#inputxt').show();
						$('#siteadd').attr('disabled', false);
					} else {
						$('#inputxt').hide();
						$('#displyoption').show();
						$('#sitecode2').text(value[0]);
					}

				});
			},

		});
		
		}*/
});



$("#clus").change(function() {
	if($(this).prop('checked')){
		$("#cl-sites").modal('show');
	}else{
		$("#cl-sites").modal('hide');
		$('#clussite').hide();
		$('#clusdiv').hide();
	}
});


$('#yesclus').click(function(){
	var sitecd=$('#sitecd').val();
	$.ajax({
				url: 'getclussite?list=' + sitecd,
	success: function(data) {
 			       $('#selsitem').empty();
			       var selectsite = document.getElementById('selsitem');
                   if (data.length > 0)
                      $('#clussite').text(data[0][0]);
			       for (i = 0; i < data.length; i++) {
				      var opt = document.createElement('option');
				      opt.value = data[i][0];
				      opt.innerHTML = data[i][1];
				      selectsite.appendChild(opt);
			}
	$('#clussite').val("");
        var selectedState = $(this).children("option:selected").val();
        if (selectedState == '--Select FPO Sites with which is to be clustered--')
            $('#clussite').text("");
		else
			$('#clussite').text(selectedState);
	
	$('#clusdiv').show();
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

$('#noclus').click(function(){
	$("#cl-sites").modal('hide');
	$('#clus').prop('checked', false);
	$('#clusdiv').hide();
});


function EnableDisable1(recordMsg) {
	var str = recordMsg.value.trim();
	if (recordMsg.value.length <= 4 || str == "") {
		document.getElementById("siteadd").disabled = true;
	} else {
		document.getElementById("siteadd").disabled = false;
}	
}	

$("select.selstate").change(function() {
  var selectedState = $(this).children("option:selected").val();
  if(selectedState == '--Select State--')
	$('#statename').val("");
  else
   { $('#statename').val(selectedState);
   //  $('#siteadd').attr('disabled', false);
}
});


	$("select.selsitem").change(function() {
		$('#clussite').val("");
        var selectedState = $(this).children("option:selected").val();
        if (selectedState == '--Select FPO Sites with which is to be clustered--')
            $('#clussite').text("");
		else
			$('#clussite').text(selectedState);
	});
	
	
	$('#sitecd').attr('disabled', true);
	
	$("select.selsite").change(function() {
		$('#sitecd').val("");
		$('#sitetype').val("");
		var selectedState = $(this).children("option:selected").val();
		$('#sitename').text(selectedState);
		var pos = selectedState.indexOf(',');
		var len = selectedState.length;
		if (selectedState == '--Select FPO Sites--') {
			$('#sitecd').val("");
			$('#siteadd').attr('disabled', true);
			document.getElementById("sitecd").disabled = true;
		} else {
			$.ajax({
				url: 'getsiteinfo?list=' + selectedState,
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
							$('#sitecd').val(value[0]);
							$('#stname').val(value[2]);
						    $('#sitetype').val(selectedState.substr(0,pos));
		                    $('#cityname').text(selectedState.substr(pos+1,len));
						//	sitecode1 = $('#sitecode1').text(value[0]);
							$('#inputxt').show();
							$('#sitetype').attr('disabled', true);
							$('#stname').attr('disabled', true);
							document.getElementById('clussite').disabled = false;
							 $('#siteadd').attr('disabled', true);
						} else {
							//$('#inputxt').hide();
						//	$('#displyoption').show();
						//	document.getElementById("ssoid").disabled = true;
							$('#sitecd').val("");
							$('#sitetype').val("");
							$('#cityname').val("");
						//	$('#sitecode2').text(value[0]);
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
	


