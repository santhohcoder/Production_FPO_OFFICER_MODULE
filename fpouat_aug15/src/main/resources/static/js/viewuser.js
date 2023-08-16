$("#viewusertble").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
					filename:'List of Active Users of All FPO Sites',
				  //filename:'Electronic Advance Data (EAD)-SET ASIDE LIST',
					message:'List of Active Users of All FPO Sites @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'List of Active Users of All FPO Sites @ ' + sitecode,
					filename:'List of Active Users of All FPO Sites',
				     orientation: 'landscape',
				/*	exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						}, */
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['12%','22%','22%','22%','22%'];
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
					doc.styles.title.bold=true;
					doc.styles.title.fontSize=15;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 9;
					doc.styles.message.bold=true;
					doc.styles.message.alignment = "left";
					doc.styles.tableHeader.fontSize=6;
					doc.styles.tableHeader.bold=true;
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
					
    		        },
					},
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'List of Active Users of All FPO Sites@ ' + sitecode + '\n\r' + datetime ,
					filename:'List of Active Users of All FPO Sites',
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},
						             
			}],
		
		
		 "initComplete": function( settings ) {
	              $("#viewusertble_filter").append('<button type="button" class="btn btn-info" id="clear12" '+
			     'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
                 $("#viewusertble_filter input[type='search']").attr('id', 'searchprocesstable');
		         $('#clear12').click(function() {	
		           document.getElementById("searchprocesstable").value=""
		         var table = $('#viewusertble').DataTable();
		        table.search('').draw();
	          })
          }
		
	});
	
	
	
	
	
	


















/*

function exceldownload(obj){
	
	$(".buttons-excel").trigger('click');
}


function exceldownload1(obj){
	
	$(".buttons-excel").trigger('click');
}


function pdfdownload(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: "pdfdownload_viewactiveusers",
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

}*/


/*function pdfdownload1(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: "pdfdwnld_Lsmuserhstry",
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

}*/


function clearDropDown(e) {
	e.empty();

}

var selctuser = "";

	$("select.selsite").change(function() {
		
		var selectedsite = $(this).children("option:selected").val();
		var dvuserview = document.getElementById("activeusers");
		var dvallusertable = document.getElementById("activeusers");
		var pdfexcel = document.getElementById("pdf&excelExport1");
		selctuser = selectedsite;
		if (selectedsite == "--Select Active Sites--") {
			pdfexcel.style.display = "none";
			dvuserview.style.display = "none";
			dvallusertable.style.display = "block";
		} else {
			showLoader();
	
			$.ajax({
				url: 'gethstryofroles?userstecde=' + selectedsite,
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(resp) {
					$('#activeusers').css('display', 'block');
					$('#ViewuserHstrySite').dataTable().fnDestroy();
					
	
					
					
					
					clearDropDown($("#viewactveuser"));
					$.each(resp, function(index, value) {
						var endsts = "";
						if(value[6] == null){
							endsts = "Till-Date"
						}else {
							endsts = value[6];
						}
						var iterator = index + 1;
						var markup = "<tr  style='valign:middle;background-color:white;' class='rowDis'><td align= 'center' class= 'font-weight-medium'>" + iterator + "</td><td align= 'center' class= 'font-weight-medium'>" + value[1] + "</td><td align= 'center' class= 'font-weight-medium'>" + value[2] + "</td><td align= 'center' class= 'font-weight-medium'>" + value[0] + "</td><td align= 'center' class= 'font-weight-medium'>" + value[3] + "</td></td><td align= 'center' class= 'font-weight-medium'>" + value[4] + "</td></td><td align= 'center' class= 'font-weight-medium'>" + value[5] + "</td></td><td align= 'center' class= 'font-weight-medium'>" + endsts + "</td></tr>"
						$("#viewactveuser").append(markup);

					});
					
					
					
					$("#ViewuserHstrySite").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
					filename:'Roles Held by Users of All FPO Sites-SiteWise',
				  //filename:'Electronic Advance Data (EAD)-SET ASIDE LIST',
					message:'Roles Held by Users of All FPO Sites-SiteWise @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Roles Held by Users of All FPO Sites-SiteWise @ ' + sitecode,
					filename:'Roles Held by Users of All FPO Sites-SiteWise',
				     orientation: 'landscape',
				/*	exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						}, */
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['9%','13%','13%','13%','13%','13%','13%','13%'];
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
					doc.styles.title.bold=true;
					doc.styles.title.fontSize=15;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 9;
					doc.styles.message.bold=true;
					doc.styles.message.alignment = "left";
					doc.styles.tableHeader.fontSize=6;
					doc.styles.tableHeader.bold=true;
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
					
    		        },
					},
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Roles Held by Users of All FPO Sites-SiteWise@ ' + sitecode + '\n\r' + datetime ,
					filename:'Roles Held by Users of All FPO Sites-SiteWise',
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},
						             
			}],
		
		
		"initComplete": function( settings ) {
	     $("#ViewuserHstrySite_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#ViewuserHstrySite_filter input[type='search']").attr('id', 'searchViewuserHstrySite');
		$('#clear6').click(function() {	
		document.getElementById("searchViewuserHstrySite").value=""
		var table = $('#ViewuserHstrySite').DataTable();
		table.search('').draw();
	   })
    	}
	});
					
					
					
					
					
					dvuserview.style.display = "block";
				//	pdfexcel.style.display = "block";
					
				}
			});
			
			hideLoader();
		}
	});
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


function editsite(e){
	var editdata = {};
	var id = editdata['siteCode']= e.id;
	var value = editdata['siteName']= e.value;
    showLoader();
				$.ajax({
					url: 'userSite?siteCode='+id + '&siteName=' + value,
					data: JSON.stringify(editdata),
                    contentType: false,
					processData: false,
					type: "POST",
					success: function(data) {
						 $('body').html(data);
					},
					fail: function(rs, e) {
						console.log(rs, responseText);
						console.log("error")
					}
				});
			hideLoader();
      
	}


