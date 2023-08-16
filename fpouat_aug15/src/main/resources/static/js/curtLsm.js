var sitecode_name= $("#sitecode").val();

	/* table 1*/
	$('#crtLsmtable').DataTable({
		
	
	"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
			
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'List Of Current Local System Administrator (PLA)',
					message:'List Of Current Local System Administrator (PLA) @ ' + sitecode_name + '\n\r'+datetimes,
					
						}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'List Of Current Local System Administrator (PLA) @ ' + sitecode_name,
					filename:'List Of Current Local System Administrator (PLA)r',
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
				 filename:'List Of Current Local System Administrator (PLA)',
					message:'List Of Current Local System Administrator (PLA) @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
		"initComplete": function( settings ) {
	     $("#crtLsmtable_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#crtLsmtable_filter input[type='search']").attr('id', 'searchcrtLsmtable');
		$('#clear6').click(function() {	
		document.getElementById("searchcrtLsmtable").value=""
		var table = $('#crtLsmtable').DataTable();
		table.search('').draw();
	   })
    	}
	
});




$( document ).ready(function() {
	var data = $('#getsitecde').val();
	if(data!=null){
 $('#removeLsm').attr('disabled', false)
	}else{
		$('#removeLsm').attr('disabled', true)
	}
  
});

/*function exceldownload(obj){
	
	$(".buttons-excel").trigger('click');
}

function pdfdownload(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: "pdfdownload_curntLSM",
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
	}, 500);
}