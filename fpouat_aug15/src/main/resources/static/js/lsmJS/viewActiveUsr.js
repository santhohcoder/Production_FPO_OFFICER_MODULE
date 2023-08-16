function clearDropDown(e) {
	e.empty();

}
var selctuser = "";
$(document).ready(function() {
	$("select.userid").change(function() {
		selecteduser = $(this).children("option:selected").val();
		var dvhide = document.getElementById("lsmActiveUsr");
		var pdfexcel = document.getElementById("pdf&excelExport");
		selctuser = selecteduser;
		if (selecteduser == "") {
			
			dvhide.style.display = "none";
		}
		if(selecteduser == 'All-Users'){
			showLoader();
			$.ajax({
			url: 'getallLsmactiveusr',
			success: function(Users) {
				$('#lSMUsers').dataTable().fnDestroy();
				clearDropDown($('#getLsmUsers'));
			$.each(Users, function(index, value){
				if(value[3] == null){
					value[3] = '-NIL-'
				}
				var iterator = index + 1;
						var markup = "<tr><td align= 'center'>" + iterator + "</td><td align= 'center' >" + value[0] + "</td><td align= 'center' >" + value[1] + "</td><td align= 'center'>" + value[5] + "</td><td align= 'center'>" + value[3] + "</td><td align= 'center'>" + value[2] + "</td><td align= 'center'>" + value[4] + "</td></tr>"
						$("#getLsmUsers").append(markup);
						$('#lsmActiveUsr').show();
			})
			$('#lSMUsers').dataTable({
				"paging": true,
				"ordering": false,
				//"dom": 'Bfrtip',
				"dom": '<"pull-left"Bl>frtip',
				//buttons: ['excel']
				buttons: [{extend: 'csv', text: '<i>EXCEL</i>',
				  	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'View Active Users',
					message:'View Active Users @ ' + sitecode + '\n\r' + datetime,
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
							'\n\r'+'View Active Users ' + sitecode,
					filename:'View Active Users',
				     orientation: 'landscape',
					exportOptions: {
        				//columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						},
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['6%','18%','13%','13%','20%','15%','15%'];
			
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
				  message:'View Active Users @ ' + sitecode + '\n\r' + datetime ,
					filename:'View Active Users',	  
					
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
                       //.addClass( 'compact' )
                       
						}, 
					           
			}],	
				
	});
	
	hideLoader();
	

			}
		});

		}else if(selecteduser != ''){
		showLoader();
		$.ajax({
			url: 'viewActiveUserLsm?userid=' + selecteduser,
			success: function(Users) {
				$('#lSMUsers').dataTable().fnDestroy();
					$("#getLsmUsers").empty("");
				clearDropDown($('#getLsmUsers'));
			$.each(Users, function(index, value){
				if(value[3] == null){
					value[3] = '-NIL-'
				}
				var iterator = index + 1;
						var markup = "<tr><td align= 'center'>" + iterator + "</td><td align= 'center' >" + value[0] + "</td><td align= 'center' >" + value[1] + "</td><td align= 'center'>" + value[5] + "</td><td align= 'center'>" + value[3] + "</td><td align= 'center'>" + value[2] + "</td><td align= 'center'>" + value[4] + "</td></tr>"
						$("#getLsmUsers").append(markup);
						$('#lsmActiveUsr').show();
			})
			$('#lSMUsers').dataTable({
				"paging": true,
				"ordering": false,
				//"dom": 'Bfrtip',
				"dom": '<"pull-left"Bl>frtip',
				//buttons: ['excel']
				buttons: [{extend: 'csv', text: '<i>EXCEL</i>',
				  	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'View Active Users',
					message:'View Active Users @ ' + sitecode + '\n\r' + datetime,
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
							'\n\r'+'View Active Users ' + sitecode,
					filename:'View Active Users',
				     orientation: 'landscape',
					exportOptions: {
        				//columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						},
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['6%','18%','13%','13%','20%','15%','15%'];
			
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
				  message:'View Active Users @ ' + sitecode + '\n\r' + datetime ,
					filename:'View Active Users',	  
					
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
                       //.addClass( 'compact' )
                       
						}, 
					
					
					           
			}],	
		
				"initComplete": function(settings) {
  $("#lSMUsers_filter").append('<button type="button" class="btn btn-info" id="clearing" ' +
    'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
  $("#lSMUsers_filter input[type='search']").attr('id', 'searchlSMUsers');
  
  $('#lSMUsers_filter').on('click', '#clearing', function() {
    var table = $('#lSMUsers').DataTable();
    table.search('').draw();
    $("#searchlSMUsers").val(""); // Clear the search input field
  });
}

	});
		hideLoader();
		pdfexcel.style.display = "block";
			},
			
	error: function(jqXHR, textStatus, errorThrown) {
            window.location = 'error';
           
        }
			
			
			
			
		});
       }
	});
});

function exceldownload(obj){
	$(".buttons-excel").trigger('click');
}

function pdfdownload(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_Lsmactiveusers?userid=' + selctuser,
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
	