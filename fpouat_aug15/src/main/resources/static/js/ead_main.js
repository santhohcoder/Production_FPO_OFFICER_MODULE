/*var queryRaised;
$.ajax({
            url: 'getdisphead?id=' + $("#cinno").val(),
            type: "post",
            success: function(data) {
               $("#disphead").html(data);
               queryRaised = $("#disphead").find("[name='queryraised']").val();
               console.log(queryRaised)
           },
           error: function(rs, e) {
               alert("Error in nextPageRedirect");
           }
});	
*/

	
	function viewDetail(e) {
		showLoader();
		window.location.href = "declared?id=" + e.id;
	}
	
	function fviewDetail(e) {
		showLoader();
		console.log(queryRaised)
		
		if(queryRaised == "true"){
		window.location.href = "import_query?id=" + e.id;
		}
		else{
		window.location.href = "final_declared?id=" + e.id;
	}
}
	
	function pviewDetail(e) {
		window.location.href = "pen_ead_query?id=" + e.id;
	}

 

  /*  function successFirst1(e) {
	if (document.getElementById().value.length < 5) 
		document.getElementByName('setasideyes').disabled = true;
	else 
		document.getElementByName('setasideyes').disabled = false;
	}*/
    


// written by santhosh for setaside validation
const textarea = document.getElementById('setasidetxt');
const yesButton = document.querySelector('button[name="setasideyes"]');

// Disable the "Yes" button initially
yesButton.disabled = true;

// Add an event listener to the textarea for the "keyup" event
textarea.addEventListener('keyup', function() {
  // Enable the "Yes" button if the entered text length is at least 5 characters
  if (textarea.value.length > 5) {
    yesButton.disabled = false;
  } else {
    yesButton.disabled = true;
  }
});


    function savesetaside(e) {
    var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#setasidetxt').val()) == true ) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
    
    var developerData = {};

	developerData['cin_No'] = e.id;
    developerData['reas'] = $('#setasidetxt').val();
showLoader();
	var resObj = $.ajax({
		url: 'setaside',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			hideLoader();
			window.location.href = "ead_list?id=" + e.id;
		},
		fail: function(rs, e) {
			alert("Error in Set Aside");
		}
	});
	}
	
	
	 function fsavesetaside(e) {
    var developerData = {};

	developerData['cin_No'] = e.id;
    developerData['reas'] = $('#setasidetxt').val();
showLoader();
	var resObj = $.ajax({
		url: 'setaside',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			hideLoader();
		//	location.href = "import_list?id=" + e.id;
			location.href = "import_list";
		},
		fail: function(rs, e) {
			alert("Error in Set Aside");
		}
	});
        
    }
    
    
    function psavesetaside(e) {
    var developerData = {};
    
	developerData['cin_No'] = e.id;
    developerData['reas'] = $('#setasidetxt').val();
	var resObj = $.ajax({
		url: 'setaside',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			window.location.href = "process_ead?id=" + e.id;
		},
		fail: function(rs, e) {
			alert("Error in Set Aside");
		}
	});   
        
    }
    
	function viewDetail1(e) {
		window.location.href = "first_check?id=" + e.id;
	}
	
	function pviewDetail1(e) {
		window.location.href = "pfirst_check?id=" + e.id;
	}
	

       $(document).ready(function() {
	   if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && "PAO" == $('#role').val()  && $('#acldata').text() != '' )
		  {  $("#aclcmtsback").show();
		  $("#aprWarningMsg").modal('show');}
	   else if ("PAO" == $('#role').val() && $('#firstCheck').val() == "Y")
          $("#aclcmtsback").show();
       else if ("PAC" == $('#role').val() && $('#firstCheck').val() == "Y" && $('#aprdata').text()!= "")
          $("#aprcmtsback").show();    
	   else if (parseFloat($('#totassval').val()) > parseFloat($('#maxval').val()) && "PAC" == $('#role').val() 
			&& $('#aprdata').text() != "")  
		    $("#aprcmtsback").show();
	 
	  });

	$(document).ready(function() {
		if ($('#firstCheck').val() == "Y" && "PAC" == $('#role').val()) {
			$("#WarningMsgFirst").modal('show');
		}
	});

	$(document).ready(function() {
		if ($('#firstCheck').val() == "Y") {
			$(".check").hide();
		}
	});
	
	var blinkInterval;
	$(document).ready(function() {
		
		var processTypeFromList = localStorage.getItem("processType")
		
		if(processTypeFromList == "for Assessment Approval"){
			
		var toBlink = $('.frblink');
         blinkInterval = setInterval(function() {
            toBlink.fadeOut(500, function() {
                toBlink.fadeIn(500);
            });
        }, 1000);

        localStorage.removeItem("processType");
			
		}  if (processTypeFromList == "for First Check Approval"){
			
		var toBlink = $('.toblink');
         blinkInterval = setInterval(function() {
            toBlink.fadeOut(500, function() {
                toBlink.fadeIn(500);
            });
        }, 1000);

        localStorage.removeItem("processType");
			
		} else if (processTypeFromList == "for Query Approval"){
			
			localStorage.setItem("processType" , "for Query Approval")
		
		}
		
	})
	
	function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}
