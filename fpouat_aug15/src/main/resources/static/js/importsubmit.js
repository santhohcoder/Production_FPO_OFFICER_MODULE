 //  function oocchk(e) {
  //    if($("#myCheck").is(':checked')==true)
  //      movASS(e);
  //    else if  ($("#myCheck1").is(':checked')==true)
   //     movDET(e);
    //  else if   ($("#myCheck2").is(':checked')==true)
    //    oocsubmit(e);
//	}

 $(document).ready(function() {
	            if ( $('#role').val() != 'PSU' ) 
	                window.location.href=("/");
	          });


$(document).ready(function() {
	if ($('#enbl').val() == 0) {
			$(".hideOoc").attr("disabled", false);
		} else {
		
		$(".hideOoc").attr("disabled", true);
		
		}
		
		if ($('#detsave').val()=='Yes')
		  $('#oocsub').text("This Article ID is to be detained. However, you are giving OOC. Do you want to proceed for OOC?");
		else
		  $('#oocsub').text("Do you want to give OOC ?");
		
});


function viewScanReport(obj) {
		$.ajax({
			url: 'articlereportscan?articleId=' + $(obj).attr('articleId'),
			type: "post",
			success: function(data) {
				$("#scanReportBody").html(data);
				$("#scanReportModal").modal('show');
			},
			error: function(rs, e) {
				swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
			}
		});
	}


function goBack() {
			window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
		}

var totduty = $('#TOT_DUTY').text();
totduty= parseFloat(totduty).toFixed(2);
$('#TOT_DUTY').text(totduty);
var totass =  $('#TOT_ASS_VAL').text();
totass= parseFloat(totass).toFixed(2);
$('#TOT_ASS_VAL').text(totass);
var bcd =  $('#bcd').text();
bcd= parseFloat(bcd).toFixed(2);
$('#bcd').text(bcd);
var igst =  $('#igst').text();
igst= parseFloat(igst).toFixed(2);
$('#igst').text(igst);
var sws =  $('#sws').text();
sws= parseFloat(sws).toFixed(2);
$('#sws').text(sws);
var oth =  $('#oth').text();
oth= parseFloat(oth).toFixed(2);
$('#oth').text(oth);
var duty =  $('#duty').text();
duty= parseFloat(duty).toFixed(2);
$('#duty').text(duty);
var fine =  $('#fine').text();
fine= parseFloat(fine).toFixed(2);
$('#fine').text(fine);
var penal =  $('#penal').text();
penal= parseFloat(penal).toFixed(2);
$('#penal').text(penal);
var totduty =  $('#totduty').text();
totduty= parseFloat(totduty).toFixed(2);
$('#totduty').text(totduty);
var totpay =  $('#totpay').text();
totpay= parseFloat(totpay).toFixed(2);
$('#totpay').text(totpay);
var i=1;
 $("#OOCREC tr").each(function () {
 if (i > 1)
{ var chk=parseFloat($(this).find("td").eq(9).text()).toFixed(2);
$(this).find("td").eq(9).text(parseFloat($(this).find("td").eq(9).text()).toFixed(2));
var chk=parseFloat($(this).find("td").eq(10).text()).toFixed(2);
$(this).find("td").eq(10).text(parseFloat($(this).find("td").eq(10).text()).toFixed(2));
var chk=parseFloat($(this).find("td").eq(5).text()).toFixed(2);
$(this).find("td").eq(5).text(parseFloat($(this).find("td").eq(5).text()).toFixed(2));
var chk=parseFloat($(this).find("td").eq(4).text()).toFixed(2);
$(this).find("td").eq(4).text(parseFloat($(this).find("td").eq(4).text()).toFixed(2));
var chk=parseFloat($(this).find("td").eq(6).text()).toFixed(2);
$(this).find("td").eq(6).text(parseFloat($(this).find("td").eq(6).text()).toFixed(2));
var chk=parseFloat($(this).find("td").eq(7).text()).toFixed(2);
$(this).find("td").eq(7).text(parseFloat($(this).find("td").eq(7).text()).toFixed(2));
var chk=parseFloat($(this).find("td").eq(8).text()).toFixed(2);
$(this).find("td").eq(8).text(parseFloat($(this).find("td").eq(8).text()).toFixed(2));
}
 i=i+1;
});	

	function movASS(e)
	{
	   var fpoooc = {};
  fpoooc['cinNo'] = e.id;
    var resObj = $.ajax({
        url: 'oocassdata',
        data: JSON.stringify(fpoooc),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerDatas) {
         window.location=localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer;
         },
        fail: function(rs, e) {
         alert("Error in Import Submit");
          }
      });
	}
	
	function exmview(e)
	{
      var fpoExam = {};
      fpoExam['cinNo']=e.id;
      var resObj = $.ajax({
        url: 'exm_view',
        data: JSON.stringify(fpoExam),
        dataType : "json",
        contentType: "application/json",
        type: "post",
        success: function(fpoExam) {
          window.location.href = "import_exam_view";
         },
      fail: function(rs, e) {
         alert("Error in Import Submit");
          }
      }); 
	}
	
	
	function movDET(e)
	{
	   var fpoooc = {};
       fpoooc['cinNo'] = e.id;
        var resObj = $.ajax({
        url: 'oocdetdata',
        data: JSON.stringify(fpoooc),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(fpoooc) {
          window.location=localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer;
         },
        fail: function(rs, e) {
         alert("Error in Import Submit");
          }
      });
	}
	
	
	function oocsubmit(e) {
			//window.location.href = "import_submit?id=" + e.id;
	
 var developerData = {};
   developerData['cin_NO'] = e.id;
    var resObj = $.ajax({
        url: 'oocsubmit',
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerData) {
          window.location=localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer;
        //window.history.back();
         },
        fail: function(rs, e) {
         alert("Error in Import Submit");
          }
      });
		//alert(e.id);
		//window.location.href = "import_list" ;
	}
	
	function goBack() {
			window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
		}
		
		$('#myCheck2').click(function() {
//			 ($('#myCheck2').is(':checked')) 
//				$("#myCheck1").prop("checked", false);
//			    $("#myCheck").prop("checked", false);
//				if($("#myCheck2").is(':checked')==true){
					depcmtdiv.style.display = "none";
					$("#savedepcmts").hide();
					 document.getElementById("opt").value = 3;
					  $("#exampleModal").modal('show');
					  //exampleModal.style.display="block";
//				}
		}); 
			
			
	function proceedsave(){
		var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#depcmtfield').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
	
	if ($('#opt').val() == 1)
	   {$("#assModal").modal('show') ; }
	else if  ($('#opt').val() == 2)
	   {$("#detModal").modal('show') ; }
	}
	
	
	function savedepcmts(e){
	   var fpoooc = {};
       fpoooc['cinNo'] = e.id;
	   fpoooc['itemId'] = $(e).attr('itemId');
       fpoooc['depcmts'] = $('#depcmtfield').val();
       if ($('#opt').val() == 1)
          fpoooc['status']="ASS";
       else if ($('#opt').val() == 2)
          fpoooc['status']="DET";       
       var resObj = $.ajax({
        url: 'insoocdata',
        data: JSON.stringify(fpoooc),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(fpoooc) {
           if ($('#opt').val() == 1)
              movASS(e);
           else if  ($('#opt').val() == 2)
              movDET(e);
         },
        fail: function(rs, e) {
         alert("Error in Saving Dep. Comments");
          }
      });
		//alert(e.id);
		//window.location.href = "import_list" ;
	}
        
        $("#depcmtfield").keyup(function(){
    el = $(this);
    if ((el.val().length > 5) && (el.val() != null) && (el.val().trim().length > 5)) {
        $("#savedepcmts").show();
    } else {
    $("#savedepcmts").hide();
    }
});
        	
		
		$("#myCheck").click(function(){
//		($("#myCheck").is(':checked'))
//			$("#myCheck1").prop("checked",false);
//			$("#myCheck2").prop("checked",false);
//			if($("#myCheck").is(':checked')==true){
				depcmtdiv.style.display = "block";
//				$('#opt')=1;
//			}else{
//				depcmt.style.display = "none";
				document.getElementById("opt").value = 1;
//			}
			
	});
	
	 	$("#myCheck1").click(function() {
  //      		 ($("#myCheck1").is(':checked')) 
//				$("#myCheck2").prop("checked", false);
//					$("#myCheck").prop("checked", false);
//					if($("#myCheck1").is(':checked')==true){
						depcmtdiv.style.display = "block";
						document.getElementById("opt").value = 2;
//						$('#opt')=2;
//					}else{
//						depcmt.style.display = "none";
						
//					}
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

	