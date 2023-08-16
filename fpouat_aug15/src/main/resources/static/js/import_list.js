$(document).ready(function() {
if ( ($('#role').val()=="PAO") || ($('#role').val()=="PAC") )
{	$('#show-hidden-menu').click(function() {
		$('.hidden-menu').slideToggle("slow");
		$('#aprview').slideToggle("slow");
		$('#allinitview').toggleClass('hiddenac');
		$('.aside-menu').hide();	
		$('#asideaprview').hide();
		
	});
	$('#showmacathidden').click(function() {
		$('.macathidden').slideToggle("slow");
		$('.itcathidden').hide();
	});
	$('#showitcathidden').click(function() {
		$('.itcathidden').slideToggle("slow");
		$('.macathidden').hide();
	});
	$('#show-aside-menu').click(function() {
		$('.aside-menu').slideToggle("slow");
		$('#asideaprview').slideToggle("slow");	
		$('.hidden-menu').hide();
		$('#aprview').hide();
	});
	$('#show-asideacl-menu').click(function() {
		$('.asideacl-menu').slideToggle("slow");
		$('#asideaclview').slideToggle("slow");		
	});}
	
	if  ($('#role').val()=="PAO")
	 if (parseFloat($('#qrycount').val()) > 0)
	     document.getElementById("qrycoudisp").classList.add("flash");
		if ( $('#role').val() == 'PAC' )
			$(".rebyacbtm").hide(); 


});

$(document).ready(function() {
	var x = parseInt($('#queCount').text()) + parseInt($('#queAprCount').text());
	parseInt($('#sum').text(x));
	if ($('#role').val()=="PAO")
	 { $('#showallot').show();
	   if (parseFloat($('#aprret').val()) > 0)
	     document.getElementById("show-hidden-menu").classList.add("flash");
	   if (parseFloat($('#setasideapr').val()) > 0)
	     document.getElementById("show-aside-menu").classList.add("flash");}
	 if ($('#role').val()=="PAC")
	 {  if (parseFloat($('#setasideacl').val()) > 0)
	     document.getElementById("show-asideacl-menu").classList.add("flash");	   
	 }
	 if ($('#role').val()=="PSU")
	   $('#showallotooc').show();
});

$(document).ready(function() {
	$(".oocTable").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 5
	});
});



function pushaaf(e) {
    var fpoMain = {};
    fpoMain['id'] = e.id;
    $.ajax({
      url: 'revokesetaside',
      data: JSON.stringify(fpoMain),
      dataType: "json", 
      contentType: "application/json",
      type: "post",
      success: function(fpoMains){
         window.location.href="import_list";
         },
      fail: function(rs, e) {
			alert("Error in pushingsetaside");
		}
	});
}

function allotoffid(e) {
    var developerData = {};

	developerData['id'] = e.id;

	var resObj = $.ajax({
		url: 'allotoffid',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			var s = "success";
		},
		fail: function(rs, e) {
			alert("Error in Alloting Officer Id");
		}
	});   
}
	
	
function removeContent(text) {
		if(text == 'view-query')
		$('#asstTable').find('#'+$("#cinNoVal").val()).text('View Query Replied / Complete Assessment');
		if(text == 'view-additional-query')
		$('#asstTable').find('#'+$("#cinNoVal").val()).text('View Additional Query Replied / Complete Assessment');
		$('#qryreplycontent').html('')
	}
	
function viewDetail(e) {
	//alert(e.id);
	//window.location.href = "#" + e.id;
	$('#cinno').val(e.id);
  if (e.innerText == "View Query Replied / Complete Assessment"){
    /*$('#assediPopup').modal('show');*/
    $.ajax({
                url: "viewqueryreply?cinNo="+e.id,
                type: "get",
                success: function(data) {
                    $("#qryreplycontent").html(data);
					$("#qryreplymodal").find('h5').text('VIEW QUERY REPLIED & UPLOADED DOCUMENTS');
					$("#qryreplymodal").modal('show');
                	
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });
    
    } else if(e.innerText == "View Additional Query Replied / Complete Assessment") {
		
		$.ajax({
                url: "viewadditionalqueryreply?cinNo="+e.id+"&articleStage=P",
                type: "get",
                success: function(data) {
                    $("#qryreplycontent").html(data);
					$("#qryreplymodal").find('h5').text('VIEW ADDITIONAL QUERY REPLY & UPLOADED DOCUMENTS');
					$("#qryreplymodal").modal('show');
                	
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });	

	}
	else
{    allotoffid(e);
	var fpoMvmnt = {};
	fpoMvmnt['cinNo'] = e.id;
	fpoMvmnt['role'] = "PAO";

	$.ajax({
		url: 'updateUSerEnterStatus',
		data: JSON.stringify(fpoMvmnt),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(fpoMvmnts) {
			/*if (null != developerDatas['page'] && developerDatas['page'] != "noramlFlag") {
				window.location.href = "" + developerDatas['page'] + "?id=" + cinNo;
			}*/
		},
		fail: function(rs, e) {
			alert("Error in nextPageRedirect");
		}
	});

	window.location.href = "import_main?id=" + e.id;}
}


function goBack()
  {
    window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
   }
   
	function nextMove(e) {
      /*  var formData = new FormData();
        formData.append("cinNo", $(e).attr('cinno'));
		$.ajax({
                url: "checkexaminationraised",
                type: "post",
                global: false,
                data: formData,
                dataType: 'text',
                processData: false,
                contentType: false,
                success: function(data) {
					if(JSON.parse(data).examRaised){
						$("#edique").hide();
						$("#exmque").show();
					}else{
						$("#edique").show();
						$("#exmque").hide();
					}
					$("#qryreplymodal").modal('hide'); 
					$('#qryreplycontent').html('');
					$('#assediPopup').modal('show');
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });*/
		window.location.href = "import_main?id=" + $(e).attr('cinNo');
	}
	
	function movexm(e) {
	    var fpoExam = {};
	    fpoExam['cinNo'] = $('#cinno').val();
	    $.ajax({
	        url: 'passexmdata',
	        data: JSON.stringify(fpoExam),
	        dataType: "json",
	        contentType: "application/json",
	        type: "post",
	        success: function(data) {
	            window.location.href = "import_list";
	        },
	        fail: function(rs, e) {
	            console.log(rs, responseText);
	        }
	    });
	}
	
   
   function movedi(e){
   var developerData = {};
   developerData['cin_NO'] = $('#cinno').val();
    var resObj = $.ajax({
        url: 'movqrytoedidata',
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerDatas) {
          window.location.href = "import_list";
         },
        fail: function(rs, e) {
         alert("Error in Import Assessment");
          }
      });
		//alert(e.id);
		//window.location.href = "import_list" ;
	}
	
	function movass(e) {  
	
    var developerData = {};

	developerData['id'] = $('#cinno').val();

	var resObj = $.ajax({
		url: 'allotoffid',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			var s = "success";
		},
		fail: function(rs, e) {
			alert("Error in Alloting Officer Id");
		}
	});
	var fpoMvmnt = {};
	fpoMvmnt['cinNo'] = $('#cinno').val();
	fpoMvmnt['role'] = "PAO";

	$.ajax({
		url: 'qrytoassdata',
		data: JSON.stringify(fpoMvmnt),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(fpoMvmnts) {
			/*if (null != developerDatas['page'] && developerDatas['page'] != "noramlFlag") {
				window.location.href = "" + developerDatas['page'] + "?id=" + cinNo;
			}*/
		},
		fail: function(rs, e) {
			alert("Error in nextPageRedirect");
		}
	});
		window.location.href = "import_main?id=" + $('#cinno').val();
	}
	
	
	
	function showview(cat,role)
	{
		if ( cat == 1 && role == 2){
			 $('#aprltrview').show();
			 $('#aprparview').hide();
			 $('#aprempview').hide();
			 $('#aprview').hide();
			 $('#apremsview').hide();}
		else if ( cat == 2 && role == 2){
			$('#apremsview').show();
			$('#aprltrview').hide();
			 $('#aprparview').hide();
			 $('#aprempview').hide();
			 $('#aprview').hide();}
		else if ( cat == 3 && role == 2){
			 $('#apremsview').hide();
			$('#aprltrview').hide();
			// $('#aprparview').hide();
			 $('#aprempview').hide();
			 $('#aprview').hide();
			$('#aprparview').show();}
		else if ( cat == 4 && role == 2){
			$('#aprltrview').hide();
			 $('#aprparview').hide();
			// $('#aprempview').hide();
			 $('#aprview').hide();
			 $('#apremsview').hide();
			$('#aprempview').show();}
		else if ( cat == 5 && role == 2) {
			$('#aprltrview').hide();
			 $('#aprparview').hide();
			 $('#aprempview').hide();
			// $('#aprview').hide();
			 $('#apremsview').hide();
			$('#aprview').show();}
		
	
	}
	
	
function viewquery(e) {
$('#cinno').val(e.id);
  if (e.innerHTML == "View Query Reply / Complete Assessment"){
    /*$('#assediPopup').modal('show');*/
    $.ajax({
                url: "viewqueryreply?cinNo="+e.id,
                type: "get",
                success: function(data) {
                    $("#qryreplycontent").html(data);
					$("#qryreplymodal").find('h5').text('VIEW QUERY REPLY & UPLOADED DOCUMENTS');
					$("#qryreplymodal").modal('show');
                	
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });
    
    } else if(e.innerHTML == "View Additional Query Reply / Complete Assessment") {
		
		$.ajax({
                url: "viewadditionalqueryreply?cinNo="+e.id+"&articleStage=P",
                type: "get",
                success: function(data) {
                    $("#qryreplycontent").html(data);
					$("#qryreplymodal").find('h5').text('VIEW ADDITIONAL QUERY REPLY & UPLOADED DOCUMENTS');
					$("#qryreplymodal").modal('show');
                	
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });	

	}
  else
  {
	$("#replyFor").val(e.innerHTML);  
      $('#recassPopup').modal('show');}
  
	}


		var aafname ;
	   function recqryreply(e){
	$('#recassPopup').modal('hide');
		if($("#replyFor").val()=="From (D-Call Letter Delivered, First Query Reply Not Received in Time / Complete Assessment)" || $("#replyFor").val()=="From (D-Call Letter Not Delivered, First Query Reply Not Received in Time / Complete Assessment)"){
		aafname="aafupdate";
		$.ajax({
                url: "queryreply?cinNo="+$("#cinno").val(),
                type: "get",
                success: function(data) {
                    $("#qryreplycontent").html(data);
					$("#qryreplymodal").find('h5').text('RECORD QUERY REPLY & UPLOAD DOCUMENTS');
					$("#qryreplymodal").modal('show');
                	$('#recassPopup').modal('hide');
                	
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });
		}else if($("#replyFor").val()=="From (D-Call Letter Delivered, Additional Query Reply Not Received in Time / Complete Assessment)" || $("#replyFor").val()=="From (D-Call Letter Not Delivered, Additional Query Reply Not Received in Time / Complete Assessment)") {
			aafname="aafupdate";
			$.ajax({
                url: "additionalqueryreply?cinNo="+$("#cinno").val()+"&articleStage=P",
                type: "get",
                success: function(data) {
                    $("#qryreplycontent").html(data);
					$("#qryreplymodal").find('h5').text('RECORD ADDITIONAL QUERY REPLY & UPLOADED DOCUMENTS');
					$("#qryreplymodal").modal('show');
                	
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });
			
		}
   }

function removeContent(text) {
		//showLoader();
		if(text == 'view-query' && aafname =='aafupdate')
		location.href=localStorage.getItem("currentUrl");	
		if(text == 'view-additional-query' && aafname =='aafupdate')
		location.href=localStorage.getItem("currentUrl");
	$('#qryreplycontent').html('')
	}

function movass(e) {
 var developerData = {};
   developerData['cin_NO'] = $('#cinno').val();
   var Obj = $.ajax({
        url: 'updnorply',
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerDatas) {
    var resObj = $.ajax({
        url: 'movqrytoassdata',
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerDatas) {
          window.location.href = "import_list";
         },
        fail: function(rs, e) {
         alert("Error in Import Assessment");
          }
      });
},
 fail: function(rs, e) {
         alert("Error in Import Assessment");
          }
      });
		//alert(e.id);
		//window.location.href = "import_li
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
