function getschdata(e) {
	//alert(e.id);
	//window.location.href = "#" + e.id;
	var fpoMain = {};
	if (e==1)
    {fpoMain['id'] = $('#cin_No').val();
    fpoMain['item_ID'] = "";
    $('#item_Id').val("ENTER ITEM_ID");
    }
    else
	{fpoMain['item_ID'] = $('#item_Id').val();
	 fpoMain['id'] = "";
	 $('#cin_No').val("ENTER CIN_NO");}
	$.ajax({
		url: 'getschdata',
		data: JSON.stringify(fpoMain),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
		var data1 = {};
		data1 = data.stringval;
		    $("#dispval").val(data1);
		},
		fail: function(rs, e) {
			alert("Error in nextPageRedirect");
		}
	});
}

 $("#cin_No").keyup(function(){
    el = $(this);
    if (el.val().length==20) {
        getschdata(1);}
});

$(document).ready(function() {
	hideLoader();
	$('#dashscreen').hide();
	$('#nodashboard').show();
});	
	

history.pushState(null, null, location.href);

    window.onpopstate = function () {
       // history.go(1);
window.location.href;
    };


/*$("#item_Id").keyup(function(){
    el = $(this);
		
	if (el.val().length <= 12) {  
		$("#dispval").val("Invalid Article ID");		
        }
if (el.val().length == 0) {  
		$("#dispval").val("TO VIEW STATUS/QUEUE");		
        }		
		
   	if (el.val().length==13) {
        getschdata(2);}

});*/


function validateAndAddCharacters() {
	
	  var textField = document.getElementById("item_Id");
  var input = textField.value;
	
	if (input.length === 1) {
  var regex = /^[a-zA-Z]$/;
  if (!regex.test(input)) {
    textField.value = "";
    return;
  } else {
    textField.value = input.toUpperCase();
  }
}

// Check second character
if (input.length === 2) {
  var regex = /^[a-zA-Z][a-zA-Z]*$/;
  if (!regex.test(input)) {
    textField.value = input[0].toUpperCase();
    return;
  }else {
    textField.value = input.toUpperCase();
  }
}

// Check next nine characters
if (input.length >= 3 && input.length <= 11) {
  var regex = /^[a-zA-Z]{0,2}\d{0,9}$/;
  if (!regex.test(input)) {
    textField.value = input.slice(0, -1);
    return;
  }
}

// Check last two characters
if (input.length >= 12) {
  var regex = /^[a-zA-Z]{0,2}\d{0,9}[a-zA-Z]{0,2}$/;
  if (!regex.test(input)) {
    textField.value = input.slice(0, -1);
    return;
  }else {
    textField.value = input.toUpperCase();
  }
}
	
	
	
	    el = $(this);
		
	if (input.length <= 12) {  
		$("#dispval").val("Invalid Article ID");		
        }
if (input.length == 0) {  
		$("#dispval").val("TO VIEW STATUS/QUEUE");		
        }		
		
   	if (input.length==13) {
        getschdata(2);}
	
	
	
	}




/*$("#item_Id").keyup(function(){
    el = $(this);
    if (el.val().length==13) {
        getschdata(2);}
});*/


function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}

/*document.addEventListener("contextmenu", function (e) {
       e.preventDefault();
    }, false);*/
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
	
	var modName=localStorage.getItem('dashName');
			var roleval = $('#s-role').val();
			oMyForm.append("modname",modName);
			oMyForm.append("empId",$("#empId").val());
			oMyForm.append("sttime",$("#sttime").val());
			oMyForm.append("comments",$("#comments").val());
			oMyForm.append("name",$("#name").val());
			oMyForm.append("email",$("#email").val());
			oMyForm.append("mobile",$("#mobile").val());
			oMyForm.append("filename",$("#kyc-filetxt-1").val());
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
					url: 'submitcomplnts',
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
		
		
		
		
		function checkDivAddUl(event) {
  console.log("success");
  var target = event.target || event.srcElement;
  localStorage.setItem('dashName', target.innerText);
  
  var siteDiv = document.getElementById("site");
  var commentsInput = document.getElementById("comments");
  var moduleTitle = document.getElementById("moduleTitle");
  
  $('#kyc-filetxt-1').val('');
  $('#mobile').val('');
  $('#email').val('');
  $('#comments').val('');
  $('#mobile').removeClass('is-invalid');
  $('#mobile').next('.invalid-feedback').text('');
  $('#email').removeClass('is-invalid');
  $('#email').next('.invalid-feedback').text('');
  $('#comments').removeClass('is-invalid');
  $('#comments').next('.invalid-feedback').text('');
  $('#comments').css({ "border-color": "", "box-shadow": "" });
  $('#mobile').css({ "border-color": "", "box-shadow": "" });
  $('#email').css({ "border-color": "", "box-shadow": "" });
  
  
  
	 	//  main Layer

			var items = document.querySelectorAll("#moduleSelect .parent .child li"), tab = [], index;
			var items1 = document.querySelectorAll("#moduleSelect .parent .child li a"), tab = [], index;
			for(var i = 0; i < items.length; i++){
			
			var type = "sub module";
						
			if(items[i].innerText == "Dashboard") type ="module";
			if(items1[i].innerText == target.innerText || items[i].innerHTML == target.innerHTML){
				siteDiv.style.display = "block";
      			commentsInput.value = "";
      			var str = "Report the issue noticed in '"+target.innerHTML;
      			moduleTitle.innerHTML = str+"' "+type;
			}
		}

		// Layer 1
		items = document.querySelectorAll("#moduleSelect .parent .child .parent .child li"), tab = [], index;
		items1 = document.querySelectorAll("#moduleSelect .parent .child .parent .child li a"), tab = [], index;
		
		var type = "functionality";
		
		for(var i = 0; i < items.length; i++){
			
			if(items[i].innerHTML == target.innerHTML|| items1[i].innerHTML == target.innerHTML){
				flag = true;
				siteDiv.style.display = "block";
      			commentsInput.value = "";
      			var str = "Report the issue noticed in '"+target.innerHTML;
      			moduleTitle.innerHTML = str+"' "+type;
			}
		}

		// Layer 2
		items = document.querySelectorAll("#moduleSelect .parent .child .parent .child .parent .child li"), tab = [], index;
		items1 = document.querySelectorAll("#moduleSelect .parent .child .parent .child .parent .child li a"), tab = [], index;
		
		var type = "functionality";
		
		for(var i = 0; i < items.length; i++){
			
			if(items[i].innerHTML == target.innerHTML || items1[i].innerHTML == target.innerHTML){
				flag = true;
				siteDiv.style.display = "block";
      			commentsInput.value = "";
      			var str = "Report the issue noticed in '"+target.innerHTML;
      			moduleTitle.innerHTML = str+"' "+type;
			}
		}
  

}

function goBack() {

	  var siteDiv = document.getElementById("site");
  var commentsInput = document.getElementById("comments");
  var moduleTitle = document.getElementById("moduleTitle");

	  siteDiv.style.display = "none";
      commentsInput.value = "";
      moduleTitle.innerHTML = "";


}

function goBacks() {
  // Reset the dropdown to its default value
  
  siteDiv.style.display = "none";
      commentsInput.value = "";
      moduleTitle.innerHTML = "";
  
  var dropdown = document.getElementById("mainLayer()");
  dropdown.selectedIndex = 0;
  dropdown.dispatchEvent(new Event('change')); // to trigger any change events
}

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)){
        return false;
    }
    return true;
}

  $('#exampleModal').on('hidden.bs.modal', function () {
    $('#email').val('');
    $('#mobile').val('');
    $('#comments').val('');
   $('#mobile').removeClass('is-invalid');
    $('#mobile').next('.invalid-feedback').text('');
    $('#email').removeClass('is-invalid');
    $('#email').next('.invalid-feedback').text('');
    $('#comments').removeClass('is-invalid');
    $('#comments').next('.invalid-feedback').text('');
  })


	 var kycFiles = {};
	var supportFiles = {};

function uploadFile(obj, count) {
				
					console.log("sucess");
					console.log(kycFiles);
	
            if (obj.files[0].type == 'application/pdf' && obj.files[0].size / 1024 / 1024 < 1) {
                var notSameName = true;
                for (var key in kycFiles) {
                    if (kycFiles[key].files[0].name == obj.files[0].name) {
                        notSameName = false;
                        break;
                    }
                }
                if (notSameName) {
                    for (var key in supportFiles) {
                        if (supportFiles[key].files[0].name == obj.files[0].name) {
                            notSameName = false;
                            break;
                        }
                    }
                    if (notSameName) {
                        $("#kyc-filetxt-" + count).val(obj.files[0].name);
                        $("#kyc-filetxt-" + count).addClass('pl-25');
                        kycFiles[count] = obj;
                        $(obj).next().remove();
                      //  $(obj).after('<span><i class="fa fa-file-pdf-o pdf-icon" aria-hidden="true"></i></span>');
                        $(obj).after('<span class="view-btn" id="viewbtn" onclick="viewFile(' + count + ')"><a>View</a></span>');
                        $(obj).after('<span class="delete-btn" onclick="deleteFile(this,' + count + ')"><a>Delete</a></span>');
                    } else {
                        swal('OOPS!', 'You already uploaded the file with this name !', 'error');
                    }
                } else {
                    swal('OOPS!', 'You already uploaded the file with this name !', 'error');
                }
            } else {
                if (obj.files[0].type != 'application/pdf') {
                    swal('OOPS!', 'Please upload the documents only in PDF !', 'error');
                } else {
                    swal('OOPS!', "File size should be within 1MB !", 'error');
                }
            }
        }


function fileUpload(count) {
            $("#kyc-file-" + count).click();
        }


 function viewFile(count) {
            //pdfLoader(kycFiles[count].files[0]);
            $("#embedpdf").attr('src', URL.createObjectURL(kycFiles[count].files[0]));
            $("#pdfView").modal('toggle');
            $("#qryreplymodal").scrollTop($("#pdfView").offset().top);
        }

 function deleteFile(obj, count) {
            $("#kyc-filetxt-" + count).val('');
            $("#kyc-filetxt-" + count).removeClass('pl-25');
            delete kycFiles[count];
            $(obj).next().remove();
            $(obj).next().remove();
            $(obj).after('<span class="upload-btn" onclick="fileUpload(' + count + ')"><a>Upload</a></span>');
            $(obj).remove();
        }
        
                function closePdfModal() {
            $("#pdfView").modal('hide');
        }