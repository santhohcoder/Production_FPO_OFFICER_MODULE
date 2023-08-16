$("select.selst").change(function() {
	var selectedsite = $(this).children("option:selected").val();
	var selval = $('#selst').val();
	var iterator = 0;
	
	if (selectedsite == "--Select StateName--") {
		document.getElementById("pincdetable").style.display = "none";
			document.getElementById("pincodedata").style.display = "none";
		}
	
	else{
		document.getElementById("pincodedata").style.display = "block";
		document.getElementById("pincdetable").style.display = "inline-table";
			
	showLoader();


	$.ajax({
		url: 'getpincodesstate?stname=' + selval,
		success: function(val1) {
			$('#pincdetable').dataTable().fnDestroy();
			$("#pincdedata").empty("");
			$.each(val1, function(index, val) {
				iterator = iterator + 1;
				var data1 = "<tr  height=45px; valign='middle' class='rowDis'><td align= 'center'>" + iterator + "</td><td align= 'center'>" + val[0] + "</td><td align= 'center'>" + val[1] + "</td><td align= 'center'>" + val[2] + "</td><td align= 'center'>" + val[3] + "</td><td align= 'center'>" + val[4] + "</td><td align= 'center'>" + val[5] + "</td><td align= 'center'>" + val[6] + "</td></tr>"
				$('#pincdedata').append(data1);
			})
			$("#pincdetable").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
					filename:'Pincode Directory',
				  //filename:'Electronic Advance Data (EAD)-SET ASIDE LIST',
					message:'Pincode Directory @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Pincode Directory @ ' + sitecode,
					filename:'Pincode Directory',
				     orientation: 'landscape',
				/*	exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						}, */
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['5%','13%','14%','13%','13%','13%','14%','14%'];
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
				  message:'Pincode Directory @ ' + sitecode + '\n\r' + datetime ,
					filename:'Pincode Directory',
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},
						             
			}],
		
		
			"initComplete": function( settings ) {
	     $("#pincdetable_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#pincdetable_filter input[type='search']").attr('id', 'searchpincdetable');
		$('#clear6').click(function() {	
		document.getElementById("searchpincdetable").value=""
		var table = $('#pincdetable').DataTable();
		table.search('').draw();
	   })
    	}
		
	});

		},
		
		error: function(jqXHR, textStatus, errorThrown) {
			           // console.error("Error occurred: " + textStatus, errorThrown);
			            
			            window.location = 'error';
			           
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