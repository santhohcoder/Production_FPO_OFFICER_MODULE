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


   
   function recqryreply(e){
	$('#recassPopup').modal('hide');
		if($("#replyFor").val()=='Pending Query Reply'){
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
		}else if($("#replyFor").val()=='Pending Additional Query Reply') {
			
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
	

  


$(document).ready(function() {
		$("#asstTable").dataTable({
			"processing" : true,
			"paging" : true,
			"dom": 'Bfrtip',
			//buttons: ['excel']
			"dom": '<"pull-left"Bl><"pull-right"f>rtip',
			//"dom": 'T<"clear">lfrtip',
			buttons: [{extend: "csv", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' Articles Arrived at FPO (AAF)Query Queue',
					message:' Articles Arrived at FPO (AAF)Query Queue @ ' + sitecode + '\n\r' + datetime, 
					},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['3%','8%','9%','9%','9%','10%','10%','9%','12%','12%','9%'];
				   	 doc.defaultStyle.alignment = 'center'
					 //doc.styles.tableHeader.fillColor='#ADD8E6',
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center'
    						},
					doc.content.splice(1, 0,{
					text: [{text:' Date:'+ datetime, bold: true,
					alignment: 'right',
					fontSize: 7,
					margin:[0,20,0,20],
					//watermark:'fpo', 
					},],});
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: ' + empid, bold: true,
					alignment: 'left',
					fontSize: 7,
					margin:[0,0,90,20],
					//watermark:'fpo', 
					},],}); 
					//doc.style.tableHeader		
					doc.defaultStyle.fontSize = 5;
					doc.styles.title.bold=true;
					doc.styles.title.fontSize=15;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 9;
					doc.styles.message.bold=true;
					//doc.styles.message.alignment = "right";
					doc.styles.tableHeader.fontSize= 5
					doc.styles.tableHeader.bold=true;
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Articles Arrived at FPO (AAF)Query Queue @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Articles Arrived at FPO (AAF)Query Queue ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' Articles Arrived at FPO (AAF)Query Queue  @ ' + sitecode + '\n\r' + datetime ,
					filename:'Articles Arrived at FPO (AAF)Query Queue',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
			 "initComplete": function( settings ) {
	                 $("#asstTable_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearAsstTable()" '+
			            'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
		            $("#asstTable_filter input[type='search']").attr('id', 'searchAsstTable');
    	         }

		});
	});

/*function exceldwnldimprtsrchlist(obj){
	$(".buttons-excel").trigger('click');
} */


 function clearAsstTable(){
    document.getElementById("searchAsstTable").value=""
    var table = $('#asstTable').DataTable();
    table.search('').draw();
}



function pdfdwnldimprtsrchlist(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_imprtartclesQryqueue',
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(response) {
			console.log('inside success');
			
			 $("#staticreportdownloadanchortag").attr("href","downloadPdfFile?filename=" + response);

            document.getElementById("staticreportdownloadanchortag").click();

		}
	});
	
	}
//	function ShowHideDiv(btnPassport) {
//		var dvPassport = document.getElementById("dvPassport");
//		if (btnPassport.value == "Yes") {
//			dvPassport.style.display = "block";
//			btnPassport.value = "No";
//		} else {
//			dvPassport.style.display = "none";
//			btnPassport.value = "Yes";
//		}
//	}
	
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

//	function viewDet(e) {
	 //   var res = $.ajax({
	  //  url :  '/movqrytoassdata',
	  //  data : JSON stringify(Data),
	  //  dataType : "json",
	  //  contentType : "application/json",
	  //  type:"post",
	  //  success: function(Data){
	  //  window.location.href="import_list",
	  //  },
	   // fail: function(rs, e) {
//			console.log(rs, responseText);
//		}
      //  alert(e.id);
      
  //    alert("cms here");
   //   alert($('#atCin'+e.id).text());
//		window.location.href = "import_list;
//	});
//	}
	
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
			fpoMain['id'] = $('#cin_No').val();
			fpoMain['item_ID'] = $('#item_Id').val();
			fpoMain['QRY_DT'] = $('#qry_Dt').val();
			fpoMain['send_CNTRY_CD'] = $('#org_Cd').val();
			$.ajax({
		url: 'getsearchQRYdata',
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
				//cols += '<td><input type="button"   class="btn btn-success" id='+ atNo +' onclick="viewDet(this)"   value="Move to ASS.Queue"></td>';
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
	
	function removeContent(text) {
		/*if(text == 'view-query')
		$('#asstTable').find('#'+$("#cinNoVal").val()).text('View Query Replied / Complete Assessment');
		if(text == 'view-additional-query')
		$('#asstTable').find('#'+$("#cinNoVal").val()).text('View Additional Query Reply / Complete Assessment');
		$('#qryreplycontent').html('')*/
		window.location.href="import_list";
	}
	
	function nextMove(e) {
		$("#qryreplymodal").modal('hide'); 
		$('#qryreplycontent').html('');
		$('#assediPopup').modal('show');
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