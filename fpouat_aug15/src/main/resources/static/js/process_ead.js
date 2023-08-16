function ShowHideDiv(btnPassport) {
	var dvPassport = document.getElementById("dvPassport");
	if (btnPassport.value == "Yes") {
		dvPassport.style.display = "block";
		btnPassport.value = "No";
		$("#queryQueueshow").hide(); 
		$("#dvPassport").show();
		$("#hideset").hide();
	$("#examQueue").show();
	$("#oocQueue").show();
	$("#ediQueue").show();
	} else {
		dvPassport.style.display = "none";
		btnPassport.value = "Yes";
		$("#queryQueueshow").hide(); 
		$("#dvPassport").show();
		$("#hideset").hide();
	$("#examQueue").show();
	$("#oocQueue").show();
	$("#ediQueue").show();
	}
}

//$(document).ready(function() {
$('#queryQueue').click(function() {
	$("#hideset").show();
	
	$("#queryQueueshow").show();  //all div
	$("#examQueueshow").hide();   
	$("#oocQueueshow").hide();
	$("#ediQueueshow").hide();
	$("#examQueue").hide();
	$("#oocQueue").hide();
	$("#ediQueue").hide();
	
	//$(".asstTable").hide();
	
});
//});



$(document).ready(function() {
	$(".asstTable").dataTable({
		"processing": true,
		 //destroy: true,
		//"dom": 'Bfrtip',
		//buttons: ['excel']
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: "csv", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' Query Queue ',
					message:'  Query Queue @ ' + sitecode + '\n\r' + datetime, },
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','11%','12%','12%','12%','12%','12%','12%','12%'];
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
					console.log(sitecode);
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Query Queue @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Query Queue ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' Query Queue @ ' + sitecode + '\n\r' + datetime ,
					filename:'Query Queue ',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
		
		
		
	//});
	});
	
 $("#pentable").dataTable({
			"processing": true,
		//"dom": 'Bfrtip',
		"dom": '<"pull-left"Bl>frtip',
		//buttons: [{ extend: 'excel', className: 'Qryqueue' }]
		buttons: [{extend: "csv", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., ',
					message:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., @ ' + sitecode + '\n\r' + datetime, 
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
		   		    doc.content[1].table.widths = ['5%','11%','12%','12%','12%','12%','12%','12%','12%'];
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
					console.log(sitecode);
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., @ ' + sitecode + '\n\r' + datetime ,
					filename:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., ',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
			"initComplete": function( settings ) {
	       $("#pentable_filter").append('<button type="button" class="btn btn-info" id="clear4" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#pentable_filter input[type='search']").attr('id', 'searchpentable');
					$('#clear4').click(function() {	
					 	document.getElementById("searchpentable").value=""
						var table = $('#pentable').DataTable();
			     		table.search('').draw();
					})
    	}

	});
	
	
	$("#pentableacl").dataTable({
			"processing": true,
		//"dom": 'Bfrtip',
		"dom": '<"pull-left"Bl>frtip',
		//buttons: [{ extend: 'excel', className: 'Qryqueue' }]
		buttons: [{extend: "csv", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., ',
					message:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., @ ' + sitecode + '\n\r' + datetime, 
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
		   		    doc.content[1].table.widths = ['7%','9%','9%','9%','9%','9%','9%','9%','10%','10%','10%'];
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
					console.log(sitecode);
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., @ ' + sitecode + '\n\r' + datetime ,
					filename:'Query Queue for Allotted Mail Class : U - Letters , E - EMS , C - Parcels , T - Emp.Recep., ',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
			"initComplete": function( settings ) {
	       $("#pentableacl_filter").append('<button type="button" class="btn btn-info" id="clear4" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#pentableacl_filter input[type='search']").attr('id', 'searchpentable');
					$('#clear4').click(function() {	
					 	document.getElementById("searchpentable").value=""
						var table = $('#pentableacl').DataTable();
			     		table.search('').draw();
					})
    	}

	});
	
	
	
	
	 $("#examassTable").dataTable({
		"processing": true,
		//"dom": 'Bfrtip',
		"dom": '<"pull-left"Bl>frtip',
		//buttons: [{ extend: 'excel', className: 'Eaxmqueue' }]
		buttons: [{extend: "csv", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Examination Queue ',
					message:'Examination Queue @ ' + sitecode + '\n\r' + datetime, },
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','13%','14%','14%','14%','13%','13%','14%'];
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
					console.log(sitecode);
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Examination Queue @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Examination Queue ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Examination Queue @ ' + sitecode + '\n\r' + datetime ,
					filename:'Examination Queue ',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
						
		"initComplete": function( settings ) {
	     $("#examassTable_filter").append('<button type="button" class="btn btn-info" id="clear" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#examassTable_filter input[type='search']").attr('id', 'searchYettobemapSite');
		$('#clear').click(function() {	
		document.getElementById("searchYettobemapSite").value=""
		var table = $('#examassTable').DataTable();
		table.search('').draw();
	   })
    	}

	});
	
	 $("#oocastTable").dataTable({  
		"processing": true,
		//"dom": 'Bfrtip',
		"dom": '<"pull-left"Bl>frtip',
		//buttons: [{ extend: 'excel', className: 'OOCqueue' }]
		buttons: [{extend: "csv", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'OOC Queue ',
					message:'OOC Queue @ ' + sitecode + '\n\r' + datetime, 
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
		   		    doc.content[1].table.widths = ['5%','13%','14%','14%','14%','13%','13%','14%'];
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
					console.log(sitecode);
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'OOC Queue @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'OOC Queue ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'OOC Queue @ ' + sitecode + '\n\r' + datetime ,
					filename:'OOC Queue ',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
			 "initComplete": function( settings ) {
	       $("#oocastTable_filter").append('<button type="button" class="btn btn-info" id="clear2" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#oocastTable_filter input[type='search']").attr('id', 'searchoocastTable');
					$('#clear2').click(function() {	
					 	document.getElementById("searchoocastTable").value=""
						var table = $('#oocastTable').DataTable();
			     		table.search('').draw();
					})
    	} 
   			
						
	});
	
	$("#ediasstTable").dataTable({
		"processing": true,
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'EDIqueue' }]
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: "csv", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Commercial Imports Queue ',
					message:'Commercial Imports Queue @ ' + sitecode + '\n\r' + datetime, },
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','13%','14%','14%','14%','13%','13%','14%'];
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
					console.log(sitecode);
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Commercial Imports Queue @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Commercial Imports Queue ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Commercial Imports Queue @ ' + sitecode + '\n\r' + datetime ,
					filename:'Commercial Imports Queue ',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
			"initComplete": function( settings ) {
	     $("#ediasstTable_filter").append('<button type="button" class="btn btn-info" id="clear1" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#ediasstTable_filter input[type='search']").attr('id', 'searchediasstTable');
		$('#clear1').click(function() {	
		document.getElementById("searchediasstTable").value=""
		var table = $('#ediasstTable').DataTable();
		table.search('').draw();
	   })
    	} 
	});
});

function exceldownloadQryqueue(obj){
	$(".Qryqueue").trigger('click');
}

function exceldownloadexam(obj){
	$(".Eaxmqueue").trigger('click');
}

function exceldownloadOOC(obj){
	$(".OOCqueue").trigger('click');
}

function exceldownloadEDI(obj){
	$(".EDIqueue").trigger('click');
}

$('#examQueue').click(function() {
	$("#examQueueshow").show();
	$("#queryQueueshow").hide();
	$("#oocQueueshow").hide();
	$("#ediQueueshow").hide();
	$("#categoryofU").hide();
	$("#categoryofE").hide();
	$("#categoryofC").hide();
	$("#categoryofT").hide();
	/*var examcount = $(".exam_gray");
	examcount.style.display = "block";*/	
	$("#examcount").show();
	$("#ooccount").hide();
	$("#edicount").hide();
	/*$('.col-md-12.queues').css('top', -166);*/

});



$('#oocQueue').click(function() { 
	$("#oocQueueshow").show();
	$("#ooccount").show();
	$("#examQueueshow").hide();
	$("#queryQueueshow").hide();
	$("#ediQueueshow").hide();
	$("#categoryofU").hide();
	$("#categoryofE").hide();
	$("#categoryofC").hide();
	$("#categoryofT").hide();
	$("#examcount").hide();
	$("#edicount").hide();
	/*$('.col-md-12.queues').css('top', -166);*/
});

$('#ediQueue').click(function() {
	$("#ediQueueshow").show();
	$("#examQueueshow").hide();
	$("#queryQueueshow").hide();
	$("#oocQueueshow").hide();
	$("#categoryofU").hide();
	$("#categoryofE").hide();
	$("#categoryofC").hide();
	$("#categoryofT").hide();
	$("#examcount").hide();
	$("#ooccount").hide();
	$("#edicount").show();
	/*$('.col-md-12.queues').css('top', -166);*/
});

function clearDropDown(e) {
	e.empty();

}

function pendingquery(e) {
	
  /*if(e.innerHTML!= "View Query Reply / Complete Assessment"){
	clearDropDown($("#qry_date"));
	clearDropDown($("#query_din"));
	clearDropDown($("#item_id"));
	clearDropDown($("#item_Desc"));
	clearDropDown($("#item_qry"));
	clearDropDown($("#Def_qury"));
	clearDropDown($("#qryid"));
	clearDropDown($("#defQrey"));
	document.getElementById("defQrey").value = "";
	document.getElementById("dpcments").value = "";
	document.getElementById('pnext').disabled = true;
	document.getElementById("qry_date").value = "";
	document.getElementById("query_din").value = "";

	$("#tb2").show();

	var id = e.id;
	var pendingquery = {};
	var item_add = [];

	pendingquery["cinNo"] = id;

	$.ajax({
		url: 'pendingdata?id=' + id,
		data: JSON.stringify(pendingquery),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerData) {

			pendingdata = developerData;
			$("#penaltyId").empty();
			$.each(developerData, function(index, value) {

				var itemid1 = "";
				var markup = "<tr><td align= 'center' id= 'item_id1'class= 'font-weight-bold'>" + value[0] + "</td><td align= 'center' id='item_Desc1' class= 'font-weight-bold'>" + value[2] + "</td><td align= 'center' id= 'item_qry1' class= 'font-weight-bold'>" + value[1] + "</td><td id= 'rply' class= 'font-weight-bold'><textarea id='itemresp' class='form-control font-weight-normal'rows='1' placeholder= 'Enter response' name='Recrdresp'></textarea></td></tr>";
				$("#penaltyId").append(markup);
				itemid1 = value[0];
				item_add.push(itemid1);
			});

			$.ajax({
				url: 'fpoquerydin?id=' + id,
				data: JSON.stringify(pendingquery),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(developerData1) {
                    clearDropDown($("#query_din"));
					if (developerData1.length == 0) {
						$("#tb2").hide();
					} else{

						$("#query_din").val(developerData1[0].uniqueNo);
					}
					$.ajax({
						url: 'querydate?id=' + id,
						data: JSON.stringify(""),
						dataType: "json",
						contentType: "application/json",
						type: "post",
						success: function(QryDate) {

							$("#qry_date").val(QryDate.date);

							$.ajax({
								url: 'fpodefaultQry?id=' + id,
								data: JSON.stringify(pendingquery),
								dataType: "json",
								contentType: "application/json",
								type: "post",
								success: function(developerData3) {

									var data_defqry = {};

									var def_Qry = [];

									if (developerData3.length != 0) {
										for (i = 0; i < developerData3.length; i++) {
											data_defqry = (developerData3[i]);
											def_Qry.push(data_defqry)
										}
										$.each(def_Qry, function(index, value) {
											$("#Def_qury").append(index + 1 + ". " + value + '<br>' + '<br>');
										});
									}
									else {
										$("#tb2").hide();
									}

								}
							});

						}

					});
				},

			});

		},

		fail: function(rs, e) {
			console.log(rs, responseText);
		}

	});
	
	$("#dpcments").keyup(function(){
		
		if (null != $("#dpcments").val()) {
			$("#pnext").attr("disabled", false);
		}

		if ($("#dpcments").val() == "") {
			$("#pnext").attr("disabled", true);
		}
		
		});

	$(document).ready(function() {
		var developerData5 = {};
		var card_val = [];
		var card_value = {};
		var defQrydata = {};

		$(".submit").click(function() {
			$("textarea[name^='Recrdresp']").each(function() {
				card_value = ($(this).val());
				card_val.push(card_value);
			});

			developerData5['cinNo'] = id;

			for (i = 0; i < item_add.length; i++) {
				developerData5['item_no'] = item_add[i];
				developerData5['rply'] = card_val[i];

				$.ajax({
					url: 'insertpendingdata',
					data: JSON.stringify(developerData5),
					dataType: "json",
					contentType: "application/json",
					type: "post",
					success: function() {

					}
				});

			}
			var defQry = $("#defQrey").val();
			defQrydata['dpcmts'] = $("#dpcments").val();
			defQrydata['rply'] = defQry;
			defQrydata['cinNo'] = id;
			$.ajax({
				url: 'defqryresp',
				data: JSON.stringify(defQrydata),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function() {
                   
				}

			});

			$.ajax({
				url: 'assdonernot?id=' + id,
				data: JSON.stringify(""),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(data) {              
                
					if (data >= 1) {
						$("#asspop").modal('show');
						$('#assement').text("Assessment completed, it will move to Examination queue.")

					} else {
						$("#asspop").modal('show');
						$('#assement').text("Assessment not completed, it will move to Assessment page.")
					}

				}

			});
			$("#pendingpopup").modal('hide');

			$('#asspop').modal({
				backdrop: 'static',
				keyboard: false
			})
		});

	});
	$('#pendingpopup').modal({
		backdrop: 'static',
		keyboard: false
	})
    var updexamque = {};        
    $("#assbtn").click(function() {
		$("#asspop").modal('hide');	
		updexamque['pen_cin'] = id;
		if($('#assement').text() == "Assessment completed, it will move to Examination queue."){
			$.ajax({
				url: 'updatetoexamqueue',
				data: JSON.stringify(updexamque),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function() {

				}

			});
				
            location.reload();
		}else{
			window.location.href = "pen_main?id=" + e.id;
		}
		
	});	
	
	}else{
		window.location.href = "pen_main?id=" + e.id;
	}*/
	
	if(e.innerHTML == "Pending Query Reply"){
		$.ajax({
                url: "queryreply?cinNo="+e.id,
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
	} else if(e.innerHTML == "Pending Additional Query Reply") {
		
		$.ajax({
                url: "additionalqueryreply?cinNo="+e.id+"&articleStage=N",
                type: "get",
                success: function(data) {
                    $("#qryreplycontent").html(data);
					$("#qryreplymodal").find('h5').text('RECORD ADDITIONAL QUERY REPLY & UPLOAD DOCUMENTS');
					$("#qryreplymodal").modal('show');
                	
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });
			
	} else if(e.innerHTML == "View Query Replied / Complete Assessment") {
		
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
			
	} else if(e.innerHTML == "View Additional Query Replied / Complete Assessment") {
		
		$.ajax({
                url: "viewadditionalqueryreply?cinNo="+e.id+"&articleStage=N",
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

	} else if(e.innerHTML == "D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment" || e.innerHTML == "D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment") {
		window.location.href = "pen_main?id=" + e.id;	

	}
	else
	  window.location.href = "pen_main?id=" + e.id;	

};

function removeContent(text) {
	if(text == 'view-query')
	$('#pentable').find('#'+$("#cinNoVal").val()).text('View Query Replied / Complete Assessment');
	if(text == 'view-additional-query')
	$('#pentable').find('#'+$("#cinNoVal").val()).text('View Additional Query Replied / Complete Assessment');
	$('#qryreplycontent').html('')
}
	
function nextMove(obj) {
	window.location.href = "pen_main?id=" + $(obj).attr('cinNo');		
}
	
$("#closepopup").click(function() {
	$("#pendingpopup").modal('hide');
});

$("#otherqueues").click(function() {
    //$("#hideset").hide();
	$(".gray_note").hide();
	$("#categoryofU").hide();
	$("#categoryofE").hide();
	$("#categoryofC").hide();
	$("#categoryofT").hide();
	$('.rcrd1').addClass('fas fa-undo');
	$("span.rcrdresp").css("font-size", 12);
	$("span.rcrdresp").css('margin-left', 0);
	/*$("span.rcrdresp").css('top', 22);*/
	$("span.rcrdresp").css("text-align", 'left');
	$("span.rcrdresp").css("font-weight", 650);
	$("span.rcrdresp").css('text-indent', 0);
	$("span.rcrdresp").css('margin-top', -5);
	$("span.otherresp").css("margin-left", 2);   
    $("span.otherresp").css("font-size", 22);
	$("span.otherresp").css("margin-top", 0);
	$("span.otherresp").css("margin-right", 10);
	$("span.otherresp").css("padding-right", 10);
	$("span.otherresp").css("padding-left", 10);
	$('.sapceinside').removeClass('col-md-6').addClass('col-md-2');
	$('.spacebefore').removeClass('col-md-3');
	$('.otherqry').removeClass('col-md-2').addClass('col-md-3');
	$('.centerspace').removeClass('col-md-1').addClass('col-md-2');
	$('.otherqry').css("margin-left", 50);
	$('.otherqry').css("padding-right", 46);
	$('.otherqry').css("padding-left", 63);
	$("#queryQueue").on('click', myFunc);

});

var currentsize = $("span.rcrdresp").css("font-size");
var currentsizenum = parseFloat(currentsize);

var myFunc = function() {
	$("#examcount").hide();
	$("#ooccount").hide();
	$("#edicount").hide();
	$("#examQueueshow").hide();
	$("#oocQueueshow").hide();
	$("#ediQueueshow").hide();
	$("#examQueue").hide();
	$("#oocQueue").hide();
	$("#ediQueue").hide();
	$("#queryQueueshow").show();
	$(".gray_note").show();

	$('.otherqry').removeClass('col-md-3').addClass('col-md-2');
	$('.sapceinside').removeClass('col-md-3').addClass('col-md-6');
	$('.spacebefore').addClass('col-md-3');
	$('.centerspace').removeClass('col-md-2').addClass('col-md-1');
	$("span.rcrdresp").css("font-size", 22);
	$("span.otherresp").css("font-size", 18);
	/*	$("span.rcrdresp").css('top', 27);*/
	$("span.rcrdresp").css("font-weight", 'normal'); 
	$("span.rcrdresp").css('margin-top', 0);
	$("span.rcrdresp").css('margin-left', 18);
	$("span.otherresp").css("margin-top", 4);
	$("span.otherresp").css("margin-right", 10);
	$("span.otherresp").css("padding-right", 10);
	$("span.otherresp").css("margin-left", 16);
	$("span.otherresp").css("padding-left", 0);
	/*$("span.otherresp").css('top', 27);*/
	$('.rcrdresp1').remove();
	$('.rcrd1').removeClass('fas fa-undo');
	$("span.rcrdresp").css('text-indent', 0);
	$('.otherqry').css("margin-left", 0);
	//	$('.col-md-12.queues').css('top', -70);
	/*$("span.rcrdresp").css('font-weight', 'normal');*/
	$('.otherqry').css("padding-right", 18);
	$('.otherqry').css("padding-left", 16);





};

$(document).ready(function() {
	$("#queryQueue").off('click');
	 
}); 

// Mail-Category

function showview(e) {
	$("#queryQueueshow").hide();
	$("#categoryofU").show();
	$("#categoryofE").hide();
	$("#categoryofC").hide();
	$("#categoryofT").hide();
	$("#hideset").show();
	
}

function showviewE(e) {
	$("#queryQueueshow").hide();
	$("#categoryofU").hide();
	$("#categoryofE").show();
	$("#categoryofC").hide();
	$("#categoryofT").hide();
	
	$("#hideset").show();
	
	
}

function showviewC(e) {
	$("#queryQueueshow").hide();
	$("#categoryofU").hide();
	$("#categoryofE").hide();
	$("#categoryofC").show();
	$("#categoryofT").hide();
	
	$("#hideset").show();
	
}

function showviewT(e) {
	$("#queryQueueshow").hide();
	$("#categoryofU").hide();
	$("#categoryofE").hide();
	$("#categoryofC").hide();
	$("#categoryofT").show();
	
	$("#hideset").show();
	
}

function showviewAll(e) {
	$("#queryQueueshow").show();
	$("#categoryofU").hide();
	$("#categoryofE").hide();
	$("#categoryofC").hide();
	$("#categoryofT").hide();
	
	$("#hideset").show();
	
}

function pdfdownloadQryqueue(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_PenQryqueue',
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
	
	function pdfdownloadexam(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_PenExamqueue',
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



	function pdfdownloadOOC(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_PenOOCqueue',
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
	
	
	function pdfdownloadEDI(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_PenEdiqueue',
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
		if($("#role").val()=='PIN')	{
			$("#queryQueue").remove()
			$("#otherqueues a").click();
			$("#otherqueues").closest('.nav-pills').remove();
			$("#examQueue").click();
			$("#dvPassport").remove();
		}else if($("#role").val()=='PSU'){
			$("#queryQueue").remove()
			$("#otherqueues a").click();
			$("#otherqueues").closest('.nav-pills').remove();
			$("#oocQueue").click();
			$("#dvPassport").remove();
		}else if(!($("#role").val()=='PAC' || $("#role").val()=='PAO')){
			location.href="dash";
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
hideLoader();












