function validatetext(event) {
 var input = event.target;
 var regex = /^[a-zA-Z\s]*$/; 
  
  if (!regex.test(input.value)) {
    input.value = input.value.replace(/[^a-zA-Z\s]/g, '');

  }

var val = event.target.value;
var len = val.length;
  
        if (len == 1 )
            input.value = val.toUpperCase();

}



 function preventE(event) {
    const inputElement = $(event.target);
    const forbiddenCharacters = /[<>\/]/;

    if (forbiddenCharacters.test(inputElement.val())) {
      inputElement.val(inputElement.val().replace(forbiddenCharacters, ''));
    
    }
    
    //  const email = inputElement.val();
    
    var email= $('#fpomailid').val();

    if (!isValidEmail(email)) {
      //$('#error-message').text('E-Mail is invalid*');
		$('#fpomailid').next().show();
		$('#fpomailid').next().text('Invalid   E-Mail Id*')
		$('#fpomailid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d"});
    } else {
	    $('#fpomailid').next().hide();
        $('#fpomailid').css({ "border-color": "", "box-shadow": "" })
    }
    
    
    
  }





function refreshthreshold(){
	window.location = "Lsm_siteUpdate";
	$("#thresholdval").hide();
			$("#maxassval").show('slow');
}











	/*var sitecode_name= $("#sitecode").val();
     $('#ViewuserHstrySite').DataTable({
		
	
	"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'PROCESS UPDATE HISTORY',
					message:'PROCESS UPDATE HISTORY @ ' + sitecode_name + '\n\r'+datetimes,
					
				}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    				className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'PROCESS UPDATE HISTORY @ ' + sitecode_name,
					filename:'PROCESS UPDATE HISTORY',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8,9,10,11,12,13],
						margin: true,
					
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['8%','8%','8%','8%','8%','8%','8%','8%','8%','8%','8%','4%','4%','4%'];
				   	 doc.defaultStyle.alignment = 'center'
					
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center'
    						},
				
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: '+empid, bold: true,
					alignment: 'left',
					fontSize: 7,
					margin:[0,90,0,20],
					
					},],});  
						doc.content.splice(1, 0,{
					text: [{text:' Date:'+datetimes, bold: true,
					alignment: 'right',
					fontSize: 7,
					margin:[0,90,0,20],
					
					},],});		
					doc.defaultStyle.fontSize = 5;
					doc.styles.title.bold=true;
					doc.styles.title.fontSize=15;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 9;
					doc.styles.message.bold=true;
					doc.styles.message.alignment = "left";
					doc.styles.tableHeader.fontSize= 6;
					doc.styles.tableHeader.bold=true;
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
				},
				
				
				 {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				 filename:'PROCESS UPDATE HISTORY',
					message:'PROCESS UPDATE HISTORY @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
			"initComplete": function(settings) {
		  $("#ViewuserHstrySite_filter").append('<button type="button" class="btn btn-info" id="clear" '+
		    'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
		  $('#clear').on('click', function() {
		    $('#ViewuserHstrySite_filter input[type="search"]').val('').trigger('input');
				var table = $('#ViewuserHstrySite').DataTable();
				table.search('').draw();
		
		  });
		}
	
});
*/


/*function clearAlltab(){
	document.getElementById("searchAlltab").value=""
 	var table = $('#ViewuserHstrySite').DataTable();
    table.search('').draw();
}*/













var MyForm = new FormData();
$("#contactnum").focus(function(e) {
    var oldvalue=$(this).val();
    var field=this;
    setTimeout(function () {
        if(field.value.indexOf('+91') !== 0) {
            $(field).val(oldvalue);
        } 
    }, 1);
});


var error = 0;
$("#ProcessDetails").click(function(){
		/*var siteAllotInfo={};*/
	
	
	/*siteAllotInfo['letterAir']=$('.let-air').prop('checked')==true ? 1:0;
	siteAllotInfo['letterSal']=$('.let-sal').prop('checked')==true ? 1:0;
	siteAllotInfo['letterSea']=$('.let-sea').prop('checked')==true ? 1:0;
	
	siteAllotInfo['emsAir']=$('.ems-air').prop('checked')==true ? 1:0;
	siteAllotInfo['emsSal']=$('.ems-sal').prop('checked')==true ? 1:0;
	siteAllotInfo['emsSea']=$('.ems-sea').prop('checked')==true ? 1:0;
	
	siteAllotInfo['parcelAir']=$('.par-air').prop('checked')==true ? 1:0;
	siteAllotInfo['parcelSal']=$('.par-sal').prop('checked')==true ? 1:0;
	siteAllotInfo['parcelSea']=$('.par-sea').prop('checked')==true ? 1:0;*/
	
	$("#sitedocpopup").modal('show');

$('#updatesite').click(function() {
	MyForm.append("letterAir",$('.let-air').prop('checked')==true ? 1:0);
	MyForm.append("letterSal",$('.let-sal').prop('checked')==true ? 1:0);
	MyForm.append("letterSea",$('.let-sea').prop('checked')==true ? 1:0);
	
	MyForm.append("emsAir",$('.ems-air').prop('checked')==true ? 1:0);
	MyForm.append("emsSal",$('.ems-sal').prop('checked')==true ? 1:0);
	MyForm.append("emsSea",$('.ems-sea').prop('checked')==true ? 1:0);
	
	MyForm.append("parcelAir",$('.par-air').prop('checked')==true ? 1:0);
	MyForm.append("parcelSal",$('.par-sal').prop('checked')==true ? 1:0);
	MyForm.append("parcelSea",$('.par-sea').prop('checked')==true ? 1:0);
	MyForm.append("docname",$('#kyc-filetxt-1').val());
	
	if($("#kyc-filetxt-1").val().length > 0){
		MyForm.append("filename",kycFiles[$("#kyc-filetxt-1").attr('id').split('-')[$("#kyc-filetxt-1").attr('id').split('-').length - 1]].files[0]);
		}
	//MyForm.append("filename",kycFiles[$("#kyc-filetxt-1").attr('id').split('-')[$("#kyc-filetxt-1").attr('id').split('-').length - 1]].files[0]);
var docname = $('#kyc-filetxt-1').val();
if(docname == ''){
	var docName1 = 1;
					MyForm.append("docname1",docName1);
}else{
	var docName1 = 2;
					MyForm.append("docname1",docName1);
}
	$.ajax({
			url: 'addSiteAllotDetails?docName=' + docname,
			/*data: JSON.stringify(siteAllotInfo),*/
			data: MyForm,
			/*dataType: "json",
			contentType: "application/json",
			type: "post",*/
			contentType: false,
			processData: false,
			type: "POST",
			enctype: 'multipart/form-data',
			success: function(data) {
			if (data) {
				$("#sitedocpopup").modal('hide');
				swal("Success!", "Site Process Details Updated!", "success")
					.then((value) => {
						window.location = "Lsm_siteUpdate"
					});
			}
			else {
						
					window.location='error';	
						
					}
					}
		});
})
});


function isValidEmail(email) {
    const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    return emailRegex.test(email);
  }



$('#siteDetails').click(function() {


const email = $('#fpomailid').val();
	
	if (!isValidEmail(email)) {
		$('#fpomailid').next().show();
        $('#fpomailid').text('E-Mail is invalid*');
		$('#fpomailid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
         return false; 
  		}






var reg = /<(.|\n)*?>|<|>/g; 
	if (reg.test($('#fpoadres').val()) == true || reg.test($('#fpositenme').val()) == true || reg.test($('#fpositcde').val()) == true  || reg.test($('#city').val()) == true || reg.test($('#visithrs').val()) == true) {
	        swal('OOPS!', 'Special character Not allowed!!', 'error');
			return false
	    }
	else if (reg.test($('#state').val()) == true || reg.test($('#pincode').val()) == true || reg.test($('#fpomailid').val()) == true || reg.test($('#contactnum').val()) == true )
   {
	        swal('OOPS!', 'Special character Not allowed!!', 'error');
			return false
	    }



	error = 0;
	var siteinfo = {};

	if ($('#contactnum').val().length <= 3) {
		$('#contactnum').next().show();
		$('#contactnum').next().text('Mobile Number is required*')
		$('#contactnum').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#contactnum').css({ "border-color": "", "box-shadow": "" })

	}

	if ($('#pincode').val().trim() == '') {
		$('#pincode').next().show();
		$('#pincode').next().text('Pincode is required*')
		$('#pincode').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else if ($('#pincode').val().length != 6) {
		$('#pincode').next().text('Pincode must be 6 digits*')
		$('#pincode').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#pincode').css({ "border-color": "", "box-shadow": "" })

	}

	if ($('#visithrs').val().trim() == '') {
		$('#visithrs').next().show();
		$('#visithrs').next().text('Visiting hours is required*')
		$('#visithrs').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#visithrs').css({ "border-color": "", "box-shadow": "" })

	}

	if ($('#state').val().trim() == '') {
		$('#state').next().show();
		$('#state').next().text('State is required*')
		$('#state').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#state').css({ "border-color": "", "box-shadow": "" })

	}

	if ($('#city').val().trim() == '') {
		$('#city').next().show();
		$('#city').next().text('City is required*')
		$('#city').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#city').css({ "border-color": "", "box-shadow": "" })

	}

	if ($('#fpoadres').val().trim() == '') {
		$('#fpoadres').next().show();
		$('#fpoadres').next().text('Address is required*')
		$('#fpoadres').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#fpoadres').css({ "border-color": "", "box-shadow": "" })

	}

	if ($('#fpositcde').val().trim() == '') {
		$('#fpositcde').next().show();
		$('#fpositcde').next().text('Sitecode is required*')
		$('#fpositcde').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#fpositcde').css({ "border-color": "", "box-shadow": "" })

	}

	if ($('#fpositenme').val().trim() == '') {
		$('#fpositenme').next().show();
		$('#fpositenme').next().text('SiteName is required*')
		$('#fpositenme').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#fpositenme').css({ "border-color": "", "box-shadow": "" })

	}

	if ($('#fpomailid').val().trim() == '') {
		$('#fpomailid').next().show();
		$('#fpomailid').next().text('E-Mail is required*')
		$('#fpomailid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#fpomailid').css({ "border-color": "", "box-shadow": "" })

	}
/*
	if (error == 0) {

		siteinfo['fpositeName'] = $('#fpositenme').val();
		siteinfo['fposite'] = $('#fpositcde').val();
		siteinfo['address'] = $('#fpoadres').val();
		siteinfo['email'] = $('#fpomailid').val();
		siteinfo['city'] = $('#city').val();
		siteinfo['state'] = $('#state').val();
		siteinfo['phne'] = $('#contactnum').val();
		siteinfo['visithrs'] = $('#visithrs').val();
		siteinfo['pincode'] = $('#pincode').val();

		$.ajax({
			url: 'addsiteinfo',
			data: JSON.stringify(siteinfo),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(data) {
				swal("Success!", "Site Details Updated!", "success")
					.then((value) => {
						window.location = "Lsm_siteUpdate"
					});
			}
		});

	}*/
	
	if (error == 0) {

		siteinfo['fpositeName'] = $('#fpositenme').val();
		siteinfo['fposite'] = $('#fpositcde').val();
		siteinfo['address'] = $('#fpoadres').val();
		siteinfo['email'] = $('#fpomailid').val();
		siteinfo['city'] = $('#city').val();
		siteinfo['state'] = $('#state').val();
		siteinfo['phne'] = $('#contactnum').val();
		siteinfo['visithrs'] = $('#visithrs').val();
		siteinfo['pincode'] = $('#pincode').val();

	$.ajax({
  url: 'addsiteinfo',
  data: JSON.stringify(siteinfo),
  dataType: "json",
  contentType: "application/json",
  type: "post",
  success: function(data) {
    if (data) {
      swal("Success!", "Site Details Updated!", "success")
        .then((value) => {
	//alert("success")
          window.location = "Lsm_siteUpdate";
        });
    } 
  },
  error: function() {
	
	
        window.location = "error"; 
     
  }
});


	}
	
	
	

});
function validateField(obj) {
	if ($(obj).attr('name') == 'mobileNumber') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('Mobile Number is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {

			if (!($(obj).val().length <= 13)) {
				$(obj).next().show();
				$(obj).addClass('invalid');
				$(obj).removeClass('valid');

			} else {
				$(obj).next().hide();
				$(obj).addClass('valid');
				$(obj).removeClass('invalid');
				$(obj).css({ "border-color": "", "box-shadow": "" })

			}

		}
	}

	if ($(obj).attr('name') == 'pincode') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('Pincode is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {

			if (!($(obj).val().length == 6)) {
				$(obj).next().show();
				$(obj).next().text('Pincode must be in length of 6 digits*')
				$(obj).addClass('invalid');
				$(obj).removeClass('valid');

			} else {
				$(obj).next().hide();
				$(obj).addClass('valid');
				$(obj).removeClass('invalid');
				$(obj).css({ "border-color": "", "box-shadow": "" })

			}

		}
	}

	if ($(obj).attr('name') == 'Sitename') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('Sitename is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {
			$(obj).next().hide();
			$(obj).addClass('valid');
			$(obj).removeClass('invalid');
			$(obj).css({ "border-color": "", "box-shadow": "" })

		}

	}

	if ($(obj).attr('name') == 'Sitecode') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('Sitecode is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {
			$(obj).next().hide();
			$(obj).addClass('valid');
			$(obj).removeClass('invalid');
			$(obj).css({ "border-color": "", "box-shadow": "" })

		}

	}

	if ($(obj).attr('name') == 'fpoadres') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('Address is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {
			$(obj).next().hide();
			$(obj).addClass('valid');
			$(obj).removeClass('invalid');
			$(obj).css({ "border-color": "", "box-shadow": "" })

		}

	}

	if ($(obj).attr('name') == 'Sitename') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('Sitename is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {
			$(obj).next().hide();
			$(obj).addClass('valid');
			$(obj).removeClass('invalid');
			$(obj).css({ "border-color": "", "box-shadow": "" })

		}

	}

	if ($(obj).attr('name') == 'city') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('city is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {
			$(obj).next().hide();
			$(obj).addClass('valid');
			$(obj).removeClass('invalid');
			$(obj).css({ "border-color": "", "box-shadow": "" })

		}

	}

	if ($(obj).attr('name') == 'state') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('State is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {
			$(obj).next().hide();
			$(obj).addClass('valid');
			$(obj).removeClass('invalid');
			$(obj).css({ "border-color": "", "box-shadow": "" })

		}

	}

	if ($(obj).attr('name') == 'Mailid') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('Mail-ID is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {
			$(obj).next().hide();
			$(obj).addClass('valid');
			$(obj).removeClass('invalid');
			$(obj).css({ "border-color": "", "box-shadow": "" })

		}

	}

	if ($(obj).attr('name') == 'vistinghrs') {
		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('Hours is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "", "box-shadow": "" })
		} else {
			$(obj).next().hide();
			$(obj).addClass('valid');
			$(obj).removeClass('invalid');
			$(obj).css({ "border-color": "", "box-shadow": "" })

		}

	}
}

$(document).ready(function() {
	console.log(fpoSiteAllotDetails);
	if(fpoSiteAllotDetails[1]==1)
	$('.let-air').prop('checked',true);
	if(fpoSiteAllotDetails[2]==1)
	$('.let-sal').prop('checked',true);
	if(fpoSiteAllotDetails[3]==1)
	$('.let-sea').prop('checked',true);
	
	if(fpoSiteAllotDetails[4]==1)
	$('.ems-air').prop('checked',true);
	if(fpoSiteAllotDetails[5]==1)
	$('.ems-sal').prop('checked',true);
	if(fpoSiteAllotDetails[6]==1)
	$('.ems-sea').prop('checked',true);
	
	if(fpoSiteAllotDetails[7]==1)
	$('.par-air').prop('checked',true);
	if(fpoSiteAllotDetails[8]==1)
	$('.par-sal').prop('checked',true);
	if(fpoSiteAllotDetails[9]==1)
	$('.par-sea').prop('checked',true);
})


/*$('#siteUpdate').click(function() {
	$('.process-update').hide();
	$("#processupdatehistory").hide();
	$('.site-update').show();
	
});
 
 
$('#processUpdate').click(function() {
	$('.site-update').hide();
	$("#processupdatehistory").hide();
	$('.process-update').show();
});

$("#processhistory").click(function(){
	$(".process-update").hide();
	$('.site-update').hide();
	$("#processupdatehistory").show();
});*/


$('#siteUpdate').click(function() {
	showLoader();
	$('.process-update').hide();
	$("#content").hide();
	
	$('.site-update').show();
	$("div #content").attr("style", "display: none !important");
	hideLoader();
	
	
});
 
 
$('#processUpdate').click(function() {
	showLoader();
	$('.site-update').hide();
	$("#content").hide();
	
	$('.process-update').show();
	$("div #content").attr("style", "display: none !important");
	hideLoader();
});



$('#siteDetailsMc').click(function() {
	var siteAllotInfo={};
	
	siteAllotInfo['letterAir']=$('.let-air').prop('checked')==true ? 1:null;
	siteAllotInfo['letterSal']=$('.let-sal').prop('checked')==true ? 1:null;
	siteAllotInfo['letterSea']=$('.let-sea').prop('checked')==true ? 1:null;
	
	siteAllotInfo['emsAir']=$('.ems-air').prop('checked')==true ? 1:null;
	siteAllotInfo['emsSal']=$('.ems-sal').prop('checked')==true ? 1:null;
	siteAllotInfo['emsSea']=$('.ems-sea').prop('checked')==true ? 1:null;
	
	siteAllotInfo['parcelAir']=$('.par-air').prop('checked')==true ? 1:null;
	siteAllotInfo['parcelSal']=$('.par-sal').prop('checked')==true ? 1:null;
	siteAllotInfo['parcelSea']=$('.par-sea').prop('checked')==true ? 1:null;
	
	$.ajax({
			url: 'addSiteAllotDetails',
			data: JSON.stringify(siteAllotInfo),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(data) {
				swal("Success!", "Site Details Updated!", "success")
					.then((value) => {
						window.location = "Lsm_siteUpdate"
					});
			}
		});
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




$("#processhistory").click(function(){
	showLoader();
	$(".process-update").hide();
	$('.site-update').hide();
$('#content').show()
//	$("div #content").attr("style", "display: block !important");
$.ajax({
    url: 'processUpdateHistory', 
    success: function(data, textStatus, XMLHttpRequest) {
	
        $('#content').html(data);
    }
});
hideLoader();
});






