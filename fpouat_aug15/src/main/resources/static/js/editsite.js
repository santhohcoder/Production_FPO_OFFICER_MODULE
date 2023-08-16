document.getElementById("editSite").disabled = true;
function EnableDisable(recordMsg) {
	var str = recordMsg.value.trim();
	if (recordMsg.value.length <= 4 || str == "") {
		document.getElementById("editSite").disabled = true;
	} else {
		document.getElementById("editSite").disabled = false;
	}
}

$('#siteadd').click(function() {
	$("#editsitepup").modal('show');
});

$('#editSite').click(function() {
var reg =/<(.|\n)*?>/g;
			 if (reg.test($('.editsiteresp').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
	var nsmdata = {};

	nsmdata['siteName'] = $('#sitename').val();
	if (slectedcde == null) {
		nsmdata['siteCode'] = $('#sitecode1').val();
	} else {
		nsmdata['siteCode'] = slectedcde;
	}
	nsmdata['siteState'] = $('#sitestate').val();
	nsmdata['siteType'] = $('#sitetype').val();
	if ($('#clus').prop('checked'))
	   nsmdata['clustered'] = 1;
	else
	   nsmdata['clustered'] = '';
	nsmdata['clussite'] = $('#clussite').val();
	nsmdata['reason'] = $('.editsiteresp').val();
	$("#editsitepup").modal('hide');
	$('#addsitepopup').modal('show');
	/*nsmdata['id'] = $('#fpositeid').val();*/
	$('#editsave').click(function() {
		if ($('#sitecode').val() != "" && $('#officerid').val() != "") {
			$.ajax({
				url: 'editfpositesave',
				data: JSON.stringify(nsmdata),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(data) {
					window.location = "viewactiveList";
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
			alert("No-Longer officer-id required!, please login again.")
		}

	});
});

$('#yesclus').click(function(){
	var sitecode=$('#sitecode').val();
	$.ajax({
				url: 'getclussite?list=' + sitecode,
	success: function(data) {
 			       $('#selsitem').empty();
			       var selectsite = document.getElementById('selsitem');
                   if (data.length > 0)
                        $('#clussite').val(data[0][0]);
			       for (i = 0; i < data.length; i++) {
				      var opt = document.createElement('option');
				      opt.value = data[i][0];
				      opt.innerHTML = data[i][1];
				      selectsite.appendChild(opt);
			}
        var selectedState =document.getElementById('selsitem');
        if (selectedState == '--Select FPO Sites with which is to be clustered--')
            $('#clussite').val("");
		else
			$('#clussite').val(selectedState[0].value);
	
	$('#clusdiv').show();
	$('#clussitedisp').show();
	},		
	});
	
});

$('#noclus').click(function(){
	$("#cl-sites").modal('hide');
	$('#clus').prop('checked', false);
	$('#clusdiv').hide();
	$('#clussitedisp').hide();
});


$("#clus").change(function() {
	if($(this).prop('checked')){
		$("#cl-sites").modal('show');
		$('#clusdiv').show();
	$('#clussitedisp').show();
	}else{
		$("#cl-sites").modal('hide');
		$('#clussite').val("");
		$('#clusdiv').hide();
		$('#clussitedisp').hide();
	}
});


	$("select.selsitem").change(function() {
		$('#clussite').val("");
        var selectedState = $(this).children("option:selected").val();
        if (selectedState == '--Select FPO Sites with which is to be clustered--')
            $('#clussite').val("");
		else
			$('#clussite').val(selectedState);
	});
	

$(document).ready(function() {
	if ($('#isclus').val()==1)
	{
	$('#clusdiv').show();
	$('#clussitedisp').show();
	$('#clus').prop('checked',true);
	}
	else
	{
	$('#clusdiv').hide();
	$('#clussitedisp').hide();
	 $('#clus').prop('checked',false);
    	var selectedState = $('#selsitem').children("option:selected").val();
	  if (selectedState == '--Select FPO Sites with which is to be clustered--')
                   	 $('#clussite').val("");
		else
			$('#clussite').val(selectedState);
	}
	
});



$("select.selstate").change(function() {
		var selectedState = $(this).children("option:selected").val();       
$('#sitestate').val(selectedState);
});

$(document).ready(function() {
	$("select.selsite").change(function() {
		var selectedState = $(this).children("option:selected").val();

		$.ajax({
			url: 'getsiteoflist?list=' + selectedState,
			success: function(data) {
				$.each(data, function(key, value) {
					if (key == 0) {
						$('#sitecode').val(value[0]);
						$('#sitestate').val(value[1]);
						$('#sitetype').val(value[2]);
						$('#sitecode1').text(value[0]);
						$('#clustered').text(value[3]);
						if (value[3] ==1)
						  $('#clus').prop('checked',true);
			            else
                          $('#clus').prop('checked',false);
						$('#clussite').val(value[4]);
						$('#displyoption').hide();
						$('#inputxt').show();
						$('#clusdiv').show();
	                    $('#clussitedisp').show();
					} else {
						$('#inputxt').hide();
						$('#displyoption').show();
						$('#sitecode2').text(value[0]);
						$('#clusdiv').hide();
	                    $('#clussitedisp').hide();
					}

				});
			},

		});
	});
});

var slectedcde = null;
$(document).ready(function() {
	$("select.sitecde").change(function() {
		var selectedcde = $(this).children("option:selected").text();
		slectedcde = selectedcde;
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
