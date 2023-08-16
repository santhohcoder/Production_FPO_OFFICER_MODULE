$(document).ready(function() {
		$("#asstTable").dataTable({
			"processing" : true,
			"paging" : true
		});
	});

	//function ShowHideDiv(btnPassport) {
//		var dvPassport = document.getElementById("dvPassport");
//		if (btnPassport.value == "Yes") {
//			dvPassport.style.display = "block";
//			btnPassport.value = "No";
//		} else {
//			dvPassport.style.display = "none";
//			btnPassport.value = "Yes";
//		}
//	}

  
$('.detooc').click(function(){
	
		if ($(".detooc:checkbox:checked").length > 0){
			$("#issooc").attr("disabled", false);
		}
		else{
		   $("#issooc").attr("disabled", true);
		}
	
});


function ShowHideDiv(btnPassport) {
		var dvPassport = document.getElementById("dvPassport");
		if (btnPassport.value == "Yes") {
			dvPassport.style.display = "none";
			btnPassport.value = "No";
		} else {
			dvPassport.style.display = "block";
			btnPassport.value = "Yes";
		}
	} 
	
	
	
    function updmvmnt(id)
    {
     var developerData = {};
   developerData['cinNo'] = id;
    var resObj = $.ajax({
		url: 'MOVQRYTOOOC',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
           window.location.href = "import_submit?id=" + id; 
           },
        fail: function(rs, e) {
			 alert("Error in MOVEMENT while moving to OOC QUEUE");
		} 
     });  
    }
      
function dispalldet(e)
{
    var developerData = {};
    var currow=e.id;
    var pagelen =$('#ooctab').DataTable().page.info().length;
    var row=currow % pagelen;
    if (row==0)
       row=row+pagelen;
    var cinno= $('#ooctab').find('tr').eq(row).find("input#cinno").val();
    var itmid= $('#ooctab').find('tr').eq(row).find("input#itmid").val();
    $('#ooctab').find('tr').eq(row).css('background-color','#1d90b3');
    $('#ooctab').find('tr').eq(row).css('font-size','18');
    $('#ooctab').find('tr').eq(row).css('color','yellow');
    $('#ooctab').find('tr').eq(row).css('border','darkgreen');
    developerData['id'] = cinno;
    var table=$('#OOCITEMREC');
    var resObj = $.ajax({
    url: 'oocdispmaindata',
    data: JSON.stringify(developerData),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(developerDatas) {
	     if (developerDatas.length > 0)
	        {
	           document.getElementById("itmiddisp").innerHTML = itmid;
	           document.getElementById("rname").innerHTML = developerDatas[0][0];
	           document.getElementById("raddr").innerHTML = developerDatas[0][1];
	           document.getElementById("gwt").innerHTML = developerDatas[0][4];
	           document.getElementById("totduty").innerHTML = developerDatas[0][5];
	           document.getElementById("pen").innerHTML = developerDatas[0][6];
	           document.getElementById("fin").innerHTML = developerDatas[0][7];    
	        } 	
		},
	fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
    var table = document.getElementById('OOCITEMREC');
    var rowCount = table.rows.length - 1;
     var i=1;
     while ( i < rowCount )
    {
      document.getElementById("OOCITEMREC").deleteRow(-1);
      i=i+1;}
       developerData = {};
       developerData['cinNo'] = cinno;
	    var resObj = $.ajax({
		url: 'oocdispdata',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			var rowcount = developerDatas.length;
			var stRow = 0;
			var table = document.getElementById('OOCITEMREC');
            var oldrc = table.rows.length - 1;
            var i=1;
            while ( i < oldrc )
            {
              document.getElementById("OOCITEMREC").deleteRow(-1);
              i=i+1;}
			$('#rowcount').val(rowcount);
			while (stRow < rowcount){
			  var cols = "";
			  cols += '<tr><td class="dlinfo" style="color: black;" th:id="itmno">' + "  " + developerDatas[stRow][0]  + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="itmdesc">' + "  " + developerDatas[stRow][1] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="cth">' + "  " + developerDatas[stRow][2] + "  " + '</td>';
			   cols += '<td class="dlinfo" style="color: black;" th:id="nou">' + "  " + developerDatas[stRow][3] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="assval">' + "  " + developerDatas[stRow][4] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="duty">' + "  " + developerDatas[stRow][5] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="bcd">' + "  " + developerDatas[stRow][6] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="igst">' + "  " + developerDatas[stRow][7] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="sws">' + "  " + developerDatas[stRow][8] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="sws">' + "  " + developerDatas[stRow][9] + "  " + '</td>';
			 // cols += '<td class="dlinfo" style="color: black;" th:id="sws">' + "  " + developerDatas[stRow][10] + '</td>';
			 // cols += '<td class="dlinfo" style="color: black;" th:id="oth">' + "  " + developerDatas[stRow][11] + '</td></tr>';
			  cols += '</tr>';
			  $('#dataid').append(cols);
			  stRow=stRow+1;
		}
		 $("#dispalldetpopup").modal('show');	
		},
     fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
   
}

 function oocsubmitall()
    {
       $("#exampleModal").modal('show');
    }

function  deltabledata()
{
var table = document.getElementById('OOCITEMREC');
 var rowCount = table.rows.length - 1;
 $("#ooctab tbody tr").each(function() {
 var textval = $(this).find("td:eq(0)").text().trim();
 $(this).css('font-size','14');
 if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');	
		 }
		 else
		 {
		   $(this).css('color','#130101');
		   $(this).css('background-color','#e2ecf7');
		 }
  });
  var i=1;
  while ( i < rowCount )
  {
   document.getElementById("OOCITEMREC").deleteRow(-1);
   i=i+1;}
}


$('.quickooc').click(function(){
		if ($(".quickooc:checkbox:checked").length > 0){
			$("#quickoocbtn").attr("disabled", false);
		}
		else{
		   $("#quickoocbtn").attr("disabled", true);
		}
	});




function selall(){
    var table = document.getElementById("ooctab"); 
    var checkboxes = table.querySelectorAll("input[type='checkbox']:not(:disabled)");
    var selectAllCheckbox = document.getElementById('chkselall');

    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = selectAllCheckbox.checked;
    }
}

$("#ooctab tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		/*var fac=$(this).find("input#fac").val();
		var ass=$(this).find("input#ass").val();
		var exm=$(this).find("input#exm").val();*/
		var det=$(this).find("input#det").val();
		var img = document.createElement('img'); 
        img.src = 'images/detention.jfif';

		$(this).css('font-size','14px');
		/*if (exm == "0")
		 {$(this).find('#showcheckexm').hide();
          $(this).find('#showcloseexm').show();
         }
        else
         {$(this).find('#showcheckexm').show();
          $(this).find('#showcloseexm').hide();
         }
         if (ass == "0")
         {$(this).find("#showcheckass").show();
          $(this).find("#showcloseass").hide();
         }
        else
         { $(this).find("#showcheckass").hide();
          $(this).find("#showcloseass").show();
         }
          if (fac == "0")
         {$(this).find("#showcheckfac").hide();
          $(this).find("#showclosefac").show();
         }
        else
         {$(this).find("#showcheckfac").show();
          $(this).find("#showclosefac").hide();
         }*/
        if (det == "0")
         $(this).find("#showdet").hide();
        else
         {$(this).find("#showdet").show();
          $(this).find("input#chkbox").prop('disabled',true);}
     
   
	//	if (textval==1)
	//	  {
		 //  $(this).css('background-color','dimgray');
		//   $(this).find("td:eq(10)").css('background-color','green');
		//   $(this).css('font-size','18px');
		//   $(this).css('font-weight','bold');
		//   $(this).css('color','#130101');	 
		
//		   }
//		else  if(textval % 2 == 0)
		if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');	
		  // $(this).find("button#"+textval).css('background-color','white');
		  // $(this).find("button#"+textval).css('color','black');
		  // $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');
		   $(this).css('background-color','#e2ecf7');
		  // $(this).find("button#"+textval).css('background-color','dimgray');
		  // $(this).find("button#"+textval).css('color','white');
		  // $(this).find("button#"+textval).css('border','dimgray');
		 }
		//if (textval == 1) {
		//	$(this).find("button#"+textval).prop('disabled', false);
		//} 
		  // $(this).find("button#"+textval).css('background-color','green');
		  // $(this).find("button#"+textval).css('color','white');
		  // $(this).find("button#"+textval).css('border','green');
         //  $(this).find("td:eq(10)").css('background-color','green');
		 //  $(this).css('font-size','18px');
		//   $(this).css('font-weight','bold');
		 //  $(this).css('color','#130101');	 		
	});
	

/*function oocsuball()
	{
	 var table = document.getElementById('ooctab');
        var rowCount = table.rows.length;
        var developerData = {};
        var cin ;
         for (var i = 1; i < rowCount; i++)    
     {      
           cin = $('#ooctab').find('tr').eq(i).find('td:eq(2)').text().substr(0,20);
           if ($('#ooctab').find('tr').eq(i).find('[name="chkbox[]"]').prop('checked'))
            {  developerData['cin_NO'] = cin;
			$.ajax({
 		        url: 'oocsubmit',
 		       data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerData) {
             if (i == (rowCount-1) )
		         {
                   success="yes";		           
	             }					
         },
        fail: function(rs, e) {
         alert("Error in Import Submit");
          }
          
      });
      }
 }   
      if (success="yes")
        $("#oocModal").modal('show');

}*/



function oocsuball(checkbox_count) {
	showLoader();
  var table = document.getElementById('ooctab');
  var rowCount = table.rows.length;
  var successCount = 0; // Counter for successful AJAX requests
 
  // Define a recursive function to handle sequential AJAX requests
  function sendAjaxRequest(index) {
    var cin = $('#ooctab').find('tr').eq(index).find('td:eq(2)').text().substr(0, 20);

    if ($('#ooctab').find('tr').eq(index).find('[name="chkbox[]"]').prop('checked')) {
	  
      var developerData = { 'cin_NO': cin }; 
	 
      submitDeveloperData(developerData, index, rowCount, function() {
       
        successCount++;

        if (successCount === checkbox_count) {
		  hideLoader();
		  $("#exampleModal").modal('hide');
          $("#oocModal").modal('show');
        } else {
          sendAjaxRequest(index + 1);
        }
      });
    } else {
      sendAjaxRequest(index + 1);
    }
  }
  sendAjaxRequest(1);
}

function submitDeveloperData(developerData, currentIndex, rowCount, callback) {
  $.ajax({
    url: 'oocsubmit',
    data: JSON.stringify(developerData),
    dataType: 'json',
    contentType: 'application/json',
    type: 'post',
    success: function (developerData) {
      
      callback();
    },
    error: function (rs, e) {
      alert('Error in Import Submit');
    }
  });
}

function suballchecked(){
var checkboxes = document.querySelectorAll('input[type="checkbox"]');
var checkbox_count = 0;
var index=1;
	for (var i = 0; i < checkboxes.length; i++) {
	  if ($('#ooctab').find('tr').eq(index).find('[name="chkbox[]"]').prop('checked')) {
	    checkbox_count++;
	  }
	 index++;
	}
console.log(checkbox_count);

oocsuball(checkbox_count)

}




 function rmsview()
 {
 }
 
 
 
	function viewdetooc(e) {
        /*updmvmnt(e.id);*/  
		window.location.href = "import_submit?id=" + e.id;
	}


   function viewDet(e) {
        updmvmnt( $('#atCin'+e.id).text());
		window.location.href = "import_submit?id=" + $('#atCin'+e.id).text();
	}

	$("table tbody tr").each(
			function() {
				var textval = $(this).find("td:eq(0)").text().trim();
				//	var textval2 = $(this).find("td:eq(1)").text().trim();
				if (textval > 1) {
					$('#' + textval).prop('disabled', true);
					//$(this).find("td:eq(0)").attr('disabled', true);
				} else {
					$("tr.rowDis input, tr.rowDis select, tr.rowDis textarea")
							.prop('disabled', true);
					//$('#submitBtn').prop('disabled', false);
				}
		});
		
		function searchshow(e){
			var fpoMain = {};
			fpoMain['id'] = null;
			fpoMain['item_ID'] = null;
			fpoMain['QRY_DT'] = null;
			fpoMain['send_CNTRY_CD'] = null;
			
			fpoMain['id'] = $('#cinNo').val();
			fpoMain['item_ID'] = $('#item_Id').val();
			fpoMain['QRY_DT'] = $('#qry_Dt').val();
			fpoMain['send_CNTRY_CD'] = $('#org_Cd').val();
			
			$.ajax({
		url: 'getsearchOOCdata',
		data: JSON.stringify(fpoMain),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {

			
			var counter = data.length;
			var stRow = 0;
			var atNo = 1;
			$("#articleDet tr").remove();
			while (stRow < counter) {
				var newRow = $("<tr>");
				var cols = "";
				cols += '<td th:text="0" id="atSl' + atNo + '"></td>';
				cols += '<td th:text="0" id="atCin' + atNo + '"></td>';
				cols += '<td th:text="0" id="atItemId' + atNo + '"></td>';
				cols += '<td th:text="0" id="atDate' + atNo + '" ></td>';
				cols += '<td th:text="0" id="atOrgCd' + atNo + '" ></td>';
				cols += '<td th:text="0" id="atAction' + atNo + '" ></td>';
				cols += '<td><input type="button" class="btn btn-success" id='+ atNo +' onclick="viewDet(this)"  value="OOC"></td>';
				newRow.append(cols);
				$("table.table-articleDet").append(newRow);
				
				$('#atSl'+atNo).text(atNo);
				$('#atCin'+atNo).text(data[stRow][0]);
				$('#atItemId'+atNo).text(data[stRow][1]);
				$('#atDate'+atNo).text(data[stRow][2]);
				$('#atOrgCd'+atNo).text(data[stRow][3]);
				$('#atAction'+atNo).text(data[stRow][4]);
				
				
				atNo = atNo + 1;
				stRow = stRow + 1;
			}
					
			$('#onsearch').show();		
			
			
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});
			
		}

function goLastPage(){
			window.location="/import_role";			
		}
		
	var currentDate = new Date();
					 var day = currentDate.getDate();
					 var month = currentDate.getMonth() + 1;
					 var year = currentDate.getFullYear();
					 var hour=currentDate.getHours();
					 var min=currentDate.getMinutes();
					 var second=currentDate.getSeconds();
					 var datetime = day + "/" + month + "/" + year + " " + hour + ":" + min +":" + second;
					 var empid = $("#empId").val();
					 var sitecode = $("#sitecode").val();
					


$("#chkselall").click(function(){
	
		if ($(this).is(":checked")){
			$("#quickoocbtn").attr("disabled", false);
		}
		else{
		   $("#quickoocbtn").attr("disabled", true);
		}
	
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



		