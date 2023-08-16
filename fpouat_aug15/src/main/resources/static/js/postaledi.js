function showCommercialimport() {
			$("#quQueue").hide();
			$("#queryQueueshow").show('slow');
        }
        
        function showCommercialimportStatus() {
			$("#quQueue").show();
			$("#queryQueueshow").hide('slow');
        }
	
	
	
	$("#queryQueue").dataTable({
		"processing": true,
		"paging": true,
		//"dom": 'Bfrtip',
		"dom": '<"pull-left"Bl>frtip',
		//buttons: ['excel']
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Art.IDs Under Process / Processed in Commercial Queue',
					message:'Art.IDs Under Process / Processed in Commercial Queue @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Art.IDs Under Process / Processed in Commercial Queue @ ' + sitecode,
					filename:"Art.IDs Under Process / Processed in Commercial Queue",
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['4%','6%','6%','6%','6%','6%','6%','6%','6%','6%','6%','6%','6%','6%','6%','6%','6%'];
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
					},},
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Art.IDs Under Process / Processed in Commercial Queue @ ' + sitecode + '\n\r' + datetime ,
					filename:'Art.IDs Under Process / Processed in Commercial Queue',	
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						
				"initComplete": function( settings ) {
	        $("#queryQueue_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearQueryQueue()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#queryQueue_filter input[type='search']").attr('id', 'searchQueryQueue');
    	}
			
	
	});
	
  function clearQueryQueue(){
     document.getElementById("searchQueryQueue").value=""
     var table = $('#queryQueue').DataTable();
     table.search('').draw();
    }	

	

//$(document).ready(function() {
	$("#postaleditable").dataTable({
		"processing": true,
		"paging": true,
		//"dom": 'Bfrtip',
		"dom": '<"pull-left"Bl>frtip',
		//buttons: ['excel']
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'BE Declaration and Assessment',
					message:'BE Declaration and Assessment @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'BE Declaration and Assessment @ ' + sitecode,
					filename:'BE Declaration and Assessment',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['7%','15%','15%','15%','15%','15%','18%'];
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
					},},
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'BE Declaration and Assessment @ ' + sitecode + '\n\r' + datetime ,
					filename:'BE Declaration and Assessments',	
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}], 
		
		
		 "initComplete": function( settings ) {
	        $("#postaleditable_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearPostaleditable()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#postaleditable_filter input[type='search']").attr('id', 'searchPostaleditable');
    	}
		
		
	});
	
            $('#postaleditable').DataTable().on( 'order.dt search.dt', function () {
                $('#postaleditable').DataTable().column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                         cell.innerHTML = i+1;
                     } );
                 }).draw();
//});


function viewDetail(e)
{
  window.location.href = "edi_decl?id=" + e.id;
showLoader();
}
	
	
	  function clearPostaleditable(){
      document.getElementById("searchPostaleditable").value=""
      var table = $('#postaleditable').DataTable();
      table.search('').draw();
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
	