        $(document).ready(function() {
	            if ( $('#role').val() != 'PIN' ) 
	                window.location.href=("/");
	          });
		
		$('.check input:checkbox').click(function() {
			$('.check input:checkbox').not(this).prop('checked', false);
		});
		function goBack() {
			window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
		}

		function viewDetail(e) {
			//alert(e.id);
			window.location.href = "import_submit?id=" + e.id;
		}
		
		const textArea = document.getElementById("genrmks");
	     /*const textArea1 = document.getElementById("itrmks");*/
         const button = document.getElementById("saveexm");

       textArea.addEventListener("input", function() {
          if (textArea.value.length > 6)  {
         	 		button.disabled = false;
          			button.classList.add("blink");
	           }  else {
				button.disabled = true;
	          button.classList.remove("blink");
			}
      });

		
	 const saveButton = document.getElementById("ysbtn");
     const nextButtons = document.getElementsByClassName("movOOC");
     const textArea2 = document.getElementById("genrmks");


    textArea2.addEventListener("input", function() {
          if (textArea2.value.length > 6)  {
         	saveButton.addEventListener("click", function() {
				  for (let i = 0; i < nextButtons.length; i++) {
				    nextButtons[i].classList.add("blink");
                    button.classList.remove("blink");
				  }
				});
	           }  else {
				
				  for (let i = 0; i < nextButtons.length; i++) {
				    nextButtons[i].classList.remove("blink");
				  }
				
			}
      });



		
	$(document).ready(function() {
         $('.movOOC ').on('click', function() {
         	var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#itrmks').val()) == true || reg.test($('#genrmks').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
	
         
         
       if ($('#det').prop('checked')) {
         $('#nxtModal').find('.dynamicBody').text('This will move to OOC queue for Detention.');
     } else {
      $('#nxtModal').find('.dynamicBody').text('This will move to OOC queue for Clearance or Detention.');
    }
    $('#nxtModal').modal('show');
  });
});
		
		
		
		/*function savenbl(e){
		if (document.getElementById(e).value.length < 5) {
                document.getElementById('saveexm').disabled = true;
             //  $("#" + $("#inputPassword").val() + "").attr("disabled", false);
                } 
         else {
                document.getElementById('saveexm').disabled = false;
              // $("#" + $("#inputPassword").val() + "").attr("disabled", true);
              }
        }*/

		function OOCDet(e) {
		     var fpoExam = {};
			fpoExam['cinNo'] = $('#cinno').val();
				$.ajax({
		url: 'exmoocdata',
		data: JSON.stringify(fpoExam),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {
	   
	      window.location.href = "import_exm_search";
		 console.log("success");
		
			
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	
	});
        }
			
			
		function chck(e){
		var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#itrmks').val()) == true || reg.test($('#genrmks').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
		
		 if (e==1){
		 $("#assModal").modal('show') ; 
		 }
		 else if (e==2){
		   $("#nxtModal").modal('show') ;
		   
		    }
		}

		
		function ASSDet(e) {
			//alert(e.id);
				     var fpoExam = {};
			fpoExam['cinNo'] = $('#cinno').val();
				$.ajax({
		url: 'exmassdata',
		data: JSON.stringify(fpoExam),
		dataType: "json",
		contentType: "application/json",
		type: "post",
			success: function(data) {
			window.location.href = "import_exm_search";
			 console.log("success");			
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	
	});
		}
		

		//if ($('#choice').val() == 3) {

			//$('#text').show();

		//}
		
//{	      
        //    alert("cms here");
  //         $('#movOOC').prop('disabled', true);
   //        alert("check");
	//       $(".movOOC").attr("disabled", true);
	//        alert("over");
		
		
			
	//	}

		if ($('#choice').val() == 1) {
			$('#opt1').show();
			$('#opt4').show();
			$('#opt6').show();
			$('#text').show();
		}

		else if ($('#choice').val() == 2) {
			$('#opt2').show();
			$('#opt3').show();
			$('#opt6').show();
			$('#text').show();
		} 
		
		else if ($('#choice').val() == 3) {
			$('#opt2').show();
			$('#opt4').show();
			$('#opt5').show();
			$('#text').show();
		} 
		
		else if ($('#choice').val() == 4) {
			$('#opt2').show();
			$('#opt3').show();
			$('#opt5').show();
			$('#text').show();
		}


        function storeall(){
        var item_fou="";
        var itrmks = "";
        var success="";
        var itno;
        var once=0;
        var table = document.getElementById('EXMREC');
        var rowCount = table.rows.length;
        var fpoExam = {};
         for (var i = 1; i < rowCount; i++)        
          {      
           itrmks = ($('#EXMREC').find('tr').eq(i).find('[name="itrmks[]"]').val());
           itno = ($('#EXMREC').find('tr').eq(i).find('[name="itemno[]"]').val());
           if ($('#EXMREC').find('tr').eq(i).find('[name="chkbox[]"]').prop('checked'))
             item_fou=1;
           else
            { item_fou=0; once=1;}
           fpoExam['cinNo'] = $('#cinno').val();
		   fpoExam['item_no'] =  itno;
		   fpoExam['item_fou'] = item_fou;
		   fpoExam['rmks'] = itrmks;
		   if ( i == 1)
        	{	   fpoExam['genrmks'] = $('#genrmks').val(); 
                   if ($('#det').prop('checked'))
                     fpoExam['detain'] = 1;
                   else
                     fpoExam['detain'] = 0;
   } 
           else
            { 	   fpoExam['genrmks'] = null; 
                   fpoExam['detain'] = null;}
			$.ajax({
 		        url: 'insexmdata',
 		        data: JSON.stringify(fpoExam),
		        dataType: "json",
		        contentType: "application/json",
		        type: "post",
		        success: function(data) {		
		         if (i == (rowCount-1) )
		         {
                   success="yes";		           
	             }					
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
		});
        }
        if (success="yes")
        {
	              if ($('#det').prop('checked'))
                   $(".movASS").attr("disabled", true);
                  else
                  $(".movASS").attr("disabled", false);
                  if (once==1)
		             $(".movOOC").attr("disabled", true);
		          else
		             $(".movOOC").attr("disabled", false);
	              $("#saveexm").attr("disabled", false);
                  $("#det").attr("disabled", false);
	     }
     }
        
		function itmfou(s) {
			if (!(s.checked))
			  $("#exampleModal1").modal('show');
		}
		
		// 		function myFunction() {
		// 			var checkBox = document.getElementById("myCheck");
		// 			var text = document.getElementById("text");
		// 			if (checkBox.checked == true) {
		// 				text.style.display = "block";
		// 			} else {
		// 				text.style.display = "none";
		// 			}
		// 		}


		    $('.tab-link').click(function() {
		    $("#tab-view").find('.selected').each(function() {
		        $(this).removeClass('selected');
		        $(this).addClass('completed');
		        $(this).find('span').text('');
		        $(this).find('p').removeClass('white-color');
		    })
		    $(this).removeClass('completed')
		    $(this).addClass('selected')
		    $(this).find('span').text($(this).attr('tab-count'))
		    $(this).find('p').addClass('white-color')
		    var urlLink = '';
		    if ($(this).attr('tab-count') === '1') {
		        urlLink = 'getSummary?id=' + $("#itemId").val();
		    } else if ($(this).attr('tab-count') === '2') {
		        urlLink = 'getAssessment?id=' + $("#itemId").val() +'&ead=true';
		    } else if ($(this).attr('tab-count') === '3') {
		        urlLink = 'getdcallLetter?id=' + $("#itemId").val();
		    } else {
		        location.href=localStorage.getItem("currentUrl");
		    }
		    $.ajax({
		        url: urlLink,
		        type: "post",
		        success: function(data) {
		            $("#main-content").html(data);
		        },
		        error: function(rs, e) {
		            if(this.url.includes('getExamFindings?id=')){
		            	$("#main-content").html('<h1 style="text-align: center;margin: 100px;color: ghostwhite;text-transform: uppercase;">Examination not ordered</h1>');
		            }
		        }
		    });
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
	
	function chkdet(s1)
	{
		if ((s1.checked))
		  $('#detconfModal').modal('show');
		  $(".movASS").attr("disabled",true);
          $(".movOOC").attr("disabled", false);
	}
	
	/*var modal = document.getElementById("detconfModal");

// Get the checkbox
var checkbox = document.getElementById("det");



// When the user clicks the checkbox, open the modal
checkbox.onclick = function() {
  if (checkbox.checked) {
    modal.style.display = "block";
  } else {
    modal.style.display = "none";
  }
}

*/





	
	function closeScanReportModal() {
		$("#scanReportBody").html('');
		$("#scanReportModal").modal('toggle');	
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