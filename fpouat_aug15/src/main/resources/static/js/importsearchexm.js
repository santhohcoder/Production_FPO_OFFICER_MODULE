$(document).ready(function() {
		$("#asstTable").dataTable({
			"processing" : true,
			"paging" : true,
			//"dom": 'Bfrtip',
	//	buttons: ['excel']
		"dom": '<"pull-left"Bl>frtip',
				//buttons: ['excel']
				buttons: [{extend: 'csv', text: '<i>EXCEL</i>',
				  	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Process Imported Articles - EXAMINATION QUEUE',
					message:'Process Imported Articles - EXAMINATION QUEUE @ ' + sitecode + '\n\r' + datetime,
					/*customize: function ( xlsx ) {
    				var sheet = xlsx.xl.worksheets['sheet1.xml'];
					$('c[r=A4] t', sheet).text( 'Custom text' );
					} */
					
				},
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Process Imported Articles - EXAMINATION QUEUE ' + sitecode,
					filename:'Process Imported Articles - EXAMINATION QUEUE',
				     orientation: 'landscape',
					exportOptions: {
        				//columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						},
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['6%','18%','13%','13%','10%','10%','10%','10%','10%'];
			
				doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
			
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
					},
								],
							});
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: ' + empid, bold: true,
					alignment: 'left',
					fontSize: 7,
					margin:[0,0,90,20],
					//watermark:'fpo', 
					},
								],
							});  		
					doc.defaultStyle.fontSize = 5;
					doc.defaultStyle.Arial=true;
					doc.styles.title.bold=true;
					doc.styles.title.fontSize=15;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 9;
					doc.styles.message.bold=true;
					doc.styles.message.alignment = "left";
					doc.styles.tableHeader.fontSize= 6;
					doc.styles.tableHeader.bold=true;
    		        },
					},
					//{extend: "excel", text: '<i>EXCEL</i>',	
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Process Imported Articles - EXAMINATION QUEUE @ ' + sitecode + '\n\r' + datetime ,
					filename:'Process Imported Articles - EXAMINATION QUEUE',	  
					
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
                       //.addClass( 'compact' )
                       
						}, 
					
					
					           
			}],	
					"initComplete": function( settings ) {
	     $("#asstTable_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#asstTable_filter input[type='search']").attr('id', 'searchasstTable');
		$('#clear6').click(function() {	
		document.getElementById("asstTable").value=""
		var table = $('#asstTable').DataTable();
		table.search('').draw();
	   })
    	}
		});
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
	
	function viewDetail(e) {
		updmvmnt(e.id);
	}

   function viewDet(e) {
		updmvmnt($('#atCin'+e.id).text());
	}

   function updmvmnt(e){
   var developerData = {};
   developerData['cinNo'] = e;
    var resObj = $.ajax({
        url: 'MOVQRYTOEXM',
        data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerDatas) {
         window.location.href = "import_exam_order?id=" + e;
         },
        fail: function(rs, e) {
         alert("Error in Picking for Examination by Inspector");
          }
      });
		//alert(e.id);
		//window.location.href = "import_list" ;
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
		url: 'getsearchEXMdata',
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
				cols += '<td><input type="button" class="btn btn-success" id='+ atNo +' onclick="viewDet(this)" value="EXAMINE"></td>';
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

function exceldownloadimprtExam(obj){
	$(".buttons-excel").trigger('click');
}

function pdfdownloadimprtExam(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_imprtartExamQueue',
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
	
	function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}