function chrole(e){
 var developerData = {};
	developerData['roleName'] = $('#selrole').val();
	var resObj = $.ajax({
		url: 'selrolenxt',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
				window.location.href = "dash";
		},
		fail: function(rs, e) {
			alert("Error in Login");
		}
	});
	window.location.href = "dash";
}

function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}


$('.roletype').attr('onclick','ShowHideDiv(this)');
$('.roletype1').attr('onclick','ShowHideDiv(this)');
$('.roletype').trigger('click');

$(document).ready(function() {
$('.admin').attr('title','NO Admin. Roles Have Been Allotted to this SSOID');
$('.trans').attr('title','NO Transaction. Roles Have Been Allotted to this SSOID');
$('.misc').attr('title','NO MISC. Roles Have Been Allotted to this SSOID');
   $(".nav li.disabled").click(function() {
     return false;
   });

});

function ShowHideDiv(e) {
	var name = $(e).attr('name');
	
	if(name == 'admin'){
		$('#adminrole').css('display','block');
		$('#transactionrole').css('display','none');
		$('#miscelaneousrole').css('display','none');
		
	}else if(name == 'transaction'){
		$('#adminrole').css('display','none');
		$('#transactionrole').css('display','block');
		$('#miscelaneousrole').css('display','none');
		
	}else if(name == 'miscellaneous'){
		$('#adminrole').css('display','none');
		$('#transactionrole').css('display','none');
		$('#miscelaneousrole').css('display','block');
		
	}
}

var oMyForm = new FormData();
var kycFiles = {};
var error = 0;

$('#subcomplnts').click(function() {
		var reg =/<(.|\n)*?>/g; 
 if (reg.test($('#comments').val()) == true) {
        swal('OOPS!', 'Special character Not allowed!!', 'error');
		return false
    }
  console.log("success");
  error = 0;
  
  // Get input values and trim them
  var mobile = $('#mobile').val().trim();
  var email = $('#email').val().trim();
  var comments = $('#comments').val().trim();

  // Define regex patterns
  var mobileRegex = /^\d{10}$/;
  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  // Check mobile input
  if (mobile.length === 0) {
    $('#mobile').addClass('is-invalid');
    $('#mobile').next('.invalid-feedback').text('Mobile number is required');
 $('#mobile').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
    error = error + 1;
  } else if (!mobileRegex.test(mobile)) {
    $('#mobile').addClass('is-invalid');
    $('#mobile').next('.invalid-feedback').text('Invalid mobile number (must be 10 digits)');
 $('#mobile').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
    error = error + 1;
  } else {
    $('#mobile').removeClass('is-invalid');
    $('#mobile').next('.invalid-feedback').text('');
  }

  // Check email input
  if (email.length === 0) {
    $('#email').addClass('is-invalid');
    $('#email').next('.invalid-feedback').text('Email address is required');
$('#email').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
    error = error + 1;
  } else if (!emailRegex.test(email)) {
    $('#email').addClass('is-invalid');
    $('#email').next('.invalid-feedback').text('Invalid email address');
$('#email').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
    error = error + 1;
  } else {
    $('#email').removeClass('is-invalid');
    $('#email').next('.invalid-feedback').text('');
  }

  // Check comments input
  if (comments === '') {
    $('#comments').addClass('is-invalid');
    $('#comments').next('.invalid-feedback').text();
    $('#comments').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
	error = error + 1;
  } else if (comments.length < 6) {
    $('#comments').addClass('is-invalid');
    $('#comments').next('.invalid-feedback').text();
    $('#comments').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
    error = error + 1;
  } else {
	
    $('#comments').removeClass('is-invalid');
    $('#comments').next('.invalid-feedback').text('');
	 $('#comments').css({ "border-color": "", "box-shadow": "" });
  }

  // Hide error messages when user starts typing in each input field
  $('#mobile, #email, #comments').on('input', function() {
    $(this).removeClass('is-invalid');
    $(this).next('.invalid-feedback').text('');
	$(this).css({ "border-color": "", "box-shadow": "" });
  });

	if (error === 0) {		
	
	
			var roleval = $('#s-role').val();
			oMyForm.append("s-role",$("#moduleSelect").val());
			oMyForm.append("empId",$("#empId").val());
			oMyForm.append("sttime",$("#sttime").val());
			oMyForm.append("comments",$("#comments").val());
			oMyForm.append("name",$("#name").val());
			oMyForm.append("email",$("#email").val());
			oMyForm.append("mobile",$("#mobile").val());
			var filename = $("#kyc-filetxt-1").val();	
			if (filename=='')
			     oMyForm.append("filename",null);
			else
		 	     oMyForm.append("filename",kycFiles[$("#kyc-filetxt-1").attr('id').split('-')[$("#kyc-filetxt-1").attr('id').split('-').length - 1]].files[0]);
		
		
		if(error == 0)
		
			if (roleval != "") {
				if (filename==""){
				$.ajax({
					url: 'submitcomplntsnull',
					// data: JSON.stringify(resp),
					data: oMyForm,
					// dataType: "json",
					//contentType: "application/json",
					contentType: false,
					processData: false,
					type: "POST",
				success: function(data) {
				swal("Success!", "Your complaint has been successfully submitted!", "success")
				
					.then((value) => {
						window.location = "dash"
						$("#overlay").css("display", "block");
	                    $('#overlay').height($("body").innerHeight());
					});
			},
					fail: function(rs, e) {
						console.log(rs, responseText);
					}
				});	
					
					
					
					
				}
				else
				{$.ajax({
					url: 'submitcomplnts?filename=' + filename,
					// data: JSON.stringify(resp),
					data: oMyForm,
					// dataType: "json",
					//contentType: "application/json",
					contentType: false,
					processData: false,
					type: "POST",
					enctype: 'multipart/form-data',
				success: function(data) {
				swal("Success!", "Your complaint has been successfully submitted!", "success")
				
					.then((value) => {
						window.location = "dash"
						$("#overlay").css("display", "block");
	                    $('#overlay').height($("body").innerHeight());
					});
			},
					fail: function(rs, e) {
						console.log(rs, responseText);
					}
				});
			}} else {
				
				alert("No longer login-id required, please login again!.");
			};
			
      }
		});


/*$(document).ready(function() {
var admcou = $('#dispcoua').val();
for (var j = 0; j < admcou; j++)   
{ 
var opt =$('#admrole').find('tr').eq(j).find("input#admopt").val(); 
var role =$('#admrole').find('tr').eq(j).find("input#rolex").val(); 
 var txta =$('#admrole').find('tr').eq(j).find("input#atxta").val();
 var txtb =$('#admrole').find('tr').eq(j).find("input#atxtb").val();
var hrf="";
 if (opt==1)
  {  $('#admrole').find('tr').eq(j).find('td').eq(3).css('background-color','#8dd6ec');
     $('#admrole').find('tr').eq(j).find('td').eq(3).css('font-weight','bolder');
     $('#admrole').find('tr').eq(j).find('td').eq(3).css('font-size','20px');
     $('#admrole').find('tr').eq(j).css('height','50px');
      $('#admrole').find('tr').eq(j).find('td').eq(1).css('width','40px');
      $('#admrole').find('tr').eq(j).find('td').eq(1).css('height','40px');
      $('#admrole').find('tr').eq(j).find('td').eq(1).css('border', '6px solid darkblue');
       hrf='<a href="dash?role=' + role + '" style="text-decoration: none;">';}
 else
  {  $('#admrole').find('tr').eq(j).css('background-color','transparent'); 
    $('#admrole').find('tr').eq(j).css('font-weight','normal');}
var aimg = $('#admrole').find('tr').eq(j).find("input#admimg").val(); 
 var html1= '<a href="dash?role=' + role + '"><img src=' + aimg + '  width="40px" height="40px" >';
admdisp[j].innerHTML =html1;
if (opt==1)
      var txthtml=hrf + '<font color="darkblue">&nbsp;&nbsp;&nbsp;'+  txtb + ' - ' + txta + '</font>';
else
      var txthtml=hrf + '<font color="c3c3c3">'+ '  '+ txtb + ' - ' + txta + '</font>';
admtxt[j].innerHTML='  '+txthtml;
}
});

$(document).ready(function() {
var trancou = $('#dispcoub').val();
 for (var i = 0; i < trancou; i++)   
 {
 var opt =$('#disproleb').find('tr').eq(i).find("input#tranopt").val(); 
 var role =$('#disproleb').find('tr').eq(i).find("input#roley").val(); 
 var txta =$('#disproleb').find('tr').eq(i).find("input#txta").val();
 var txtb =$('#disproleb').find('tr').eq(i).find("input#txtb").val();
 var hrf="";
 if (opt==1)
   {  $('#disproleb').find('tr').eq(i).find('td').eq(3).css('background-color','#8dd6ec'); 
     $('#disproleb').find('tr').eq(i).find('td').eq(3).css('font-weight','bolder');
     $('#disproleb').find('tr').eq(i).find('td').eq(3).css('font-size','20px');
      $('#disproleb').find('tr').eq(i).find('td').eq(3).css('color','white');
      $('#disproleb').find('tr').eq(i).css('height','50px');
      $('#disproleb').find('tr').eq(i).find('td').eq(1).css('width','40px');
      $('#disproleb').find('tr').eq(i).find('td').eq(1).css('height','40px');
      $('#disproleb').find('tr').eq(i).find('td').eq(1).css('border', '6px solid darkblue');
      hrf='<a href="dash?role=' + role + '" style="text-decoration: none;">';}
 else
   {  $('#disproleb').find('tr').eq(i).css('background-color','transparent'); 
     $('#disproleb').find('tr').eq(i).css('font-weight','normal');
      }
 var timg =$('#disproleb').find('tr').eq(i).find("input#tranimg").val(); 
 var html2= hrf+'<img src=' + timg + '  width="40px" height="40px" >';
transdisp[i].innerHTML = html2;
if (opt==1)
    var txthtml=hrf + '<font color="darkblue">&nbsp;&nbsp;&nbsp;'+ txtb + ' - ' + txta + '</font>';
else
    var txthtml=hrf + '<font color="c3c3c3">'+ txtb + ' - ' + txta + '</font>';
trantxt[i].innerHTML='  '+txthtml;
}
});

$(document).ready(function() {
var misccou= $('#dispcouc').val();
 for (var k = 0; k < misccou; k++)   
{
var opt =$('#miscrole').find('tr').eq(k).find("input#miscopt").val(); 
var role =$('#miscrole').find('tr').eq(k).find("input#rolez").val(); 
var txta =$('#miscrole').find('tr').eq(k).find("input#mtxta").val();
var txtb =$('#miscrole').find('tr').eq(k).find("input#mtxtb").val();
 var hrf="";
 if (opt==1)
 {  
     $('#miscrole').find('tr').eq(k).find('td').eq(3).css('background-color','#8dd6ec'); 
     $('#miscrole').find('tr').eq(k).find('td').eq(3).css('font-weight','bolder');
     $('#miscrole').find('tr').eq(k).find('td').eq(3).css('font-size','20px');
      $('#miscrole').find('tr').eq(k).find('td').eq(3).css('color','white');
       $('#miscrole').find('tr').eq(k).css('height','50px');
      $('#miscrole').find('tr').eq(k).find('td').eq(1).css('width','40px');
      $('#miscrole').find('tr').eq(k).find('td').eq(1).css('height','40px');
      $('#miscrole').find('tr').eq(k).find('td').eq(1).css('border', '6px solid darkblue');
      hrf='<a href="dash?role=' + role + '" style="text-decoration: none;">';}
 else
   {  $('#miscrole').find('tr').eq(k).css('background-color','transparent'); 
     $('#miscrole').find('tr').eq(k).css('font-weight','normal');}
 var mimg = $('#miscrole').find('tr').eq(k).find("input#miscimg").val(); 
 var html3= hrf+'<a href="dash?role=' + role + '"><img src=' + mimg + '  width="40px" height="40px" >';
imgdispc[k].innerHTML = html3;
if (opt==1)
     var txthtml=hrf + '<font color="darkblue">&nbsp;&nbsp;&nbsp;'+  txtb + ' - ' + txta + '</font>';
else
      var txthtml=hrf + '<font color="c3c3c3">'+ txtb + ' - ' + txta + '</font>';
misctxt[k].innerHTML='  '+txthtml;
}
});*/


	
	function goBack() {
			window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
		}
function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}
