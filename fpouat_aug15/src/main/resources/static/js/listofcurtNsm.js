var sitecode_name= $("#sitecode").val();

	/* table 1*/
	$('#cutNsmtable').DataTable({
		
	
	"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
			
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'List Of Current National System Administrator',
					message:'List Of Current National System Administrator @ ' + sitecode_name + '\n\r'+datetimes,
					
						}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'List Of Current National System Administrator @ ' + sitecode_name,
					filename:'List Of Current National System Administrator',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
					
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['5%','10%','9%','10%','9%','9%','9%','9%','10%','10%','10%'];
				   	 doc.defaultStyle.alignment = 'center'
					
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center'
    						},
				
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: '+empid, bold: true,
					alignment: 'left',
					fontSize: 7,
					margin:[0,90,0,20],
					
					},],});  
						doc.content.splice(1, 0,{
					text: [{text:' Date:'+datetimes, bold: true,
					alignment: 'right',
					fontSize: 7,
					margin:[0,90,0,20],
					
					},],});		
					doc.defaultStyle.fontSize = 5;
					doc.styles.title.bold=true;
					doc.styles.title.fontSize=15;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 9;
					doc.styles.message.bold=true;
					doc.styles.message.alignment = "left";
					doc.styles.tableHeader.fontSize= 6;
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
				 filename:'List Of Current National System Administrator',
					message:'List Of Current National System Administrator @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
	"initComplete": function( settings ) {
	     $("#cutNsmtable_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#cutNsmtable_filter input[type='search']").attr('id', 'searchcutNsmtable');
		$('#clear6').click(function() {	
		document.getElementById("searchcutNsmtable").value=""
		var table = $('#cutNsmtable').DataTable();
		table.search('').draw();
	   })
    	}
	
});



var sitecode_name= $("#sitecode").val();

	/* table 2*/
	$('#cutPnatable').DataTable({
		
	
	"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
			
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'List Of Past National System Administrator (PNA)',
					message:'List Of Past National System Administrator (PNA) @ ' + sitecode_name + '\n\r'+datetimes,
					
						}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'List Of Past National System Administrator (PNA) @ ' + sitecode_name,
					filename:'List Of Past National System Administrator (PNA)',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8,9,10,11,12],
						margin: true,
					
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['5%','9%','7%','7%','9%','8%','7%','9%','8%','8%','7%','8%','8%'];
				   	 doc.defaultStyle.alignment = 'center'
					
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center'
    						},
				
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: '+empid, bold: true,
					alignment: 'left',
					fontSize: 7,
					margin:[0,90,0,20],
					
					},],});  
						doc.content.splice(1, 0,{
					text: [{text:' Date:'+datetimes, bold: true,
					alignment: 'right',
					fontSize: 7,
					margin:[0,90,0,20],
					
					},],});		
					doc.defaultStyle.fontSize = 5;
					doc.styles.title.bold=true;
					doc.styles.title.fontSize=15;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 9;
					doc.styles.message.bold=true;
					doc.styles.message.alignment = "left";
					doc.styles.tableHeader.fontSize= 6;
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
				 filename:'List Of Past National System Administrator (PNA)',
					message:'List Of Past National System Administrator (PNA) @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
	"initComplete": function( settings ) {
	     $("#cutPnatable_filter").append('<button type="button" class="btn btn-info" id="clearing" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#cutPnatable_filter input[type='search']").attr('id', 'searchcutPnatable');
		$('#clearing').click(function() {	
		document.getElementById("cutPnatable").value=""
		var table = $('#cutPnatable').DataTable();
		table.search('').draw();
	   })
    	}
	
});



