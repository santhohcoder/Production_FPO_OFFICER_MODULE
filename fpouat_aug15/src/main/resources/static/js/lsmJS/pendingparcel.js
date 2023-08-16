
 var sitecode_name= $("#sitecode").val();

	/* table 1*/
	$('#NotmappedSite').DataTable({
		
	
	"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
			
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Electronic Advance Data (EAD)-NOT MAPPED TO SITE',
					message:'Electronic Advance Data (EAD)-NOT MAPPED TO SITE @ ' + sitecode_name + '\n\r'+datetimes,
					
						}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-NOT MAPPED TO SITE @ ' + sitecode_name,
					filename:'Electronic Advance Data (EAD)-NOT MAPPED TO SITE',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5],
						margin: true,
					
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['5%','19%','19%','19%','19%','19%'];
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
				 filename:'Electronic Advance Data (EAD)-NOT MAPPED TO SITE',
					message:'Electronic Advance Data (EAD)-NOT MAPPED TO SITE @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
						
		
		"initComplete": function( settings ) {
	        $("#NotmappedSite_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
	
});

/* table 2*/
	$('#yettobemapSite').DataTable({
		
	
	"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
			
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Electronic Advance Data (EAD)-ARRIVED AT SITE AND YET TO BE MAPPED',
					message:'Electronic Advance Data (EAD)-ARRIVED AT SITE AND YET TO BE MAPPED @ +' + sitecode_name + '\n\r'+datetimes,
					
						}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-ARRIVED AT SITE AND YET TO BE MAPPED @ ' + sitecode_name,
					filename:'Electronic Advance Data (EAD)-ARRIVED AT SITE AND YET TO BE MAPPED',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7],
						margin: true,
					
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['5%','13%','14%','14%','13%','14%','14%','13%'];
				   	 doc.defaultStyle.alignment = 'center'
					
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center'
    						},
					doc.content.splice(1, 0,{
					text: [{text:' Date:'+datetimes, bold: true,
					alignment: 'right',
					fontSize: 7,
					margin:[0,20,0,20],
					
					},],});
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: '+empid, bold: true,
					alignment: 'left',
					fontSize: 7,
					margin:[0,0,90,20],
					
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
				 filename:'Electronic Advance Data (EAD)-ARRIVED AT SITE AND YET TO BE MAPPED',
					message:'Electronic Advance Data (EAD)-ARRIVED AT SITE AND YET TO BE MAPPED @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
						
		
		"initComplete": function( settings ) {
	        $("#yettobemapSite_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
	
});


/* table 3*/
	$('#maptospefcSite').DataTable({
		
	
	"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
			
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Electronic Advance Data (EAD)-MAPPED BY OFFICER TO SPECIFIC SITES',
					message:'Electronic Advance Data (EAD)-MAPPED BY OFFICER TO SPECIFIC SITES @ +' + sitecode_name + '\n\r'+datetimes,
					
						}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-MAPPED BY OFFICER TO SPECIFIC SITES @ ' + sitecode_name,
					filename:'Electronic Advance Data (EAD)-MAPPED BY OFFICER TO SPECIFIC SITES',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
					
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['5%','10%','9%','12%','9%','6%','12%','9%','9%','9%','10%'];
				   	 doc.defaultStyle.alignment = 'center'
					
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center'
    						},
					doc.content.splice(1, 0,{
					text: [{text:' Date:'+datetimes, bold: true,
					alignment: 'right',
					fontSize: 7,
					margin:[0,20,0,20],
					
					},],});
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: '+empid, bold: true,
					alignment: 'left',
					fontSize: 7,
					margin:[0,0,90,20],
					
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
				 filename:'Electronic Advance Data (EAD)-NOT MAPPED TO SITE',
					message:'Electronic Advance Data (EAD)-NOT MAPPED TO SITE @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
						
		
		"initComplete": function( settings ) {
	        $("#maptospefcSite_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
	
});

function yettomap() {
	$('#Notmappedtbl').hide();
	$('#maptospectbl').hide();
	$('#yettobemaptbl').css('display', 'block');
}

function maptospecSite() {
	$('#yettobemaptbl').hide();
	$('#Notmappedtbl').hide();
	$('#maptospectbl').css('display', 'block');
}


function ntmappSite() {
	$('#Notmappedtbl').show();
	$('#maptospectbl').css('display', 'none');
	$('#yettobemaptbl').css('display', 'none');
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






