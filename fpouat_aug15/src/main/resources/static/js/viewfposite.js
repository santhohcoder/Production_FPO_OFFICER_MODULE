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

function sitehstry() {
	/*window.location = "sitehstry";*/
	$('#fpositeHstry').css('display', 'block');
	$('#SiteactiveSts').css('display', 'none');
}

function viewactivesites() {
	/*window.location = "sitehstry";*/
	$('#fpositeHstry').css('display', 'none');
	$('#SiteactiveSts').css('display', 'block');
}



$(document).ready(function() {
	$('#acttableY').DataTable({
		"ordering": false
	});

});

$(document).ready(function() {
	$('#blctableB').DataTable({
		"ordering": false
	});

});

$(document).ready(function() {
	$('#deltableD').DataTable({
		"ordering": false
		
	});

});

$(document).ready(function() {
	$('#notacttableN').DataTable({
		"ordering": false
	});

});

// All Status
$("#datatable tbody tr").each(function() {
	var sa = $(this).find("input#siteactive").val();
	var day = $(this).find("input#activedateY").val();
	var dab = $(this).find("input#blckdateB").val();
	var dad = $(this).find("input#deldateD").val();

	if (sa == "Y") {
		$(this).find('#status').text("Active");
		$(this).find('#status').css('color', 'rgb(5, 144, 4)');
		$(this).find('#stsdate').text(day);
		/*$(this).find('#status').css('font-weight', 500)*/
	}
	else {
		$(this).find('#status').text("Yet to be activated");
		$(this).find('#status').css('color', '#034ed8');
		$(this).find('#stsdate').text("-NIL-");
	}

	if (sa == "B") {
		$(this).find('#status').text("Blocked");
		$(this).find('#status').css('color', 'rgb(204, 13, 8)');
		$(this).find('#stsdate').text(dab);
	}
	else if (sa == "D") {
		$(this).find('#status').text("Deleted");
		$(this).find('#status').css('color', '#000000');
		$(this).find('#stsdate').text(dad);
	}

});

// ActivestatusY
$("#acttableY tbody tr").each(function() {
	var sa = $(this).find("input#activestsY").val();
	var acy = $(this).find("input#actdateY").val();
	if (sa == "Y") {
		$(this).find('#stsY').text("Active");
		$(this).find('#stsY').css('color', 'rgb(5, 144, 4)');
		$(this).find('#actvedate').text(acy);
		/*$(this).find('#status').css('font-weight', 500)*/
	}

});

// ActiveStatusB
$("#blctableB tbody tr").each(function() {
	var sa = $(this).find("input#blckstsB").val();
	var acb = $(this).find("input#blckB").val();
	if (sa == "B") {
		$(this).find('#stsB').text("Blocked");
		$(this).find('#stsB').css('color', 'rgb(204, 13, 8)');
		$(this).find('#blckdate').text(acb);
	}

});

// ActiveStatusD
$("#deltableD tbody tr").each(function() {
	var sa = $(this).find("input#delstsD").val();
	var acd = $(this).find("input#delD").val();

	if (sa == "D") {
		$(this).find('#stsD').text("Deleted");
		$(this).find('#stsD').css('color', '#000000');
		$(this).find('#deldate').text(acd);
	}

});

$("#notacttableN tbody tr").each(function() {
	var sa = $(this).find("input#notactstsN").val();
	if (sa == "N") {
		$(this).find('#stsN').text("Yet to be activated");
		$(this).find('#stsN').css('color', '#034ed8');
		$(this).find('#ntactdate').text("-NIL-");
	}


});

$(document).ready(function() {
	$("select.country").change(function() {
		var selectedCountry = $(this).children("option:selected").val();
		if (selectedCountry == "Active") {
			$('#allsts').hide();
			$('#showblcsite').hide();
			$('#showdelsite').hide();
			$('#shwntactsite').hide();
			$('#showactvesite').show();
		}
		if (selectedCountry == "Status") {
			location.reload();
		}
		if (selectedCountry == "Blocked") {
			$('#allsts').hide();
			$('#showactvesite').hide();
			$('#showdelsite').hide();
			$('#shwntactsite').hide();
			$('#showblcsite').show();
		}

		if (selectedCountry == "Deleted") {
			$('#allsts').hide();
			$('#showactvesite').hide();
			$('#showblcsite').hide();
			$('#shwntactsite').hide();
			$('#showdelsite').show();
		}

		if (selectedCountry == "Not-Active") {
			$('#allsts').hide();
			$('#showactvesite').hide();
			$('#showblcsite').hide();
			$('#showdelsite').hide();
			$('#shwntactsite').show();
		}
	});
});

var sitecode_name= $("#sitecode").val();
$(document).ready(function() {
	$('#datatable').DataTable({
		"ordering": false,
		"dom": 'Bfrtip',
	
		initComplete: function() {
			this.api().columns([3, 4, 5]).every(function() {
				var column = this;
				var coldata = column[0];
				if (coldata == 3) {
					var select = $('<select style="background-color: #63cfe0; font-weight: 700" id="picker"><option value="">DATE OF CREATION</option></select>')
						.appendTo($(column.header()).empty())

						.on('change', function() {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
							);

							column
								.search(val ? '^' + val + '$' : '', true, false)
								.draw();
						});

					column.data().unique().sort().each(function(d, j) {
						select.append('<option value="' + d + '">' + d + '</option>')
					});
				};
				if (coldata == 4) {
					var select = $('<select style="background-color: #63cfe0; font-weight: 700"><option value="">STATE</option></select>')
						.appendTo($(column.header()).empty())
						.on('change', function() {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
							);

							column
								.search(val ? '^' + val + '$' : '', true, false)
								.draw();
						});

					column.data().unique().sort().each(function(d, j) {
						select.append('<option value="' + d + '">' + d + '</option>')
					});
				};
				if (coldata == 5) {
					var select = $('<select style="background-color: #63cfe0; font-weight: 700"><option value="">ALL-STATUS</option></select>')
						.appendTo($(column.header()).empty())
						.on('change', function() {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
							);

							column
								.search(val ? '^' + val + '$' : '', true, false)
								.draw();
						});

					column.data().unique().sort().each(function(d, j) {
						select.append('<option value="' + d + '">' + d + '</option>')
					});
				};
				if (coldata == 6) {
					var select = $('<select style="background-color: #63cfe0; font-weight: 700"><option value="">STATUS DATE</option></select>')
						.appendTo($(column.header()).empty())
						.on('change', function() {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
							);

							column
								.search(val ? '^' + val + '$' : '', true, false)
								.draw();
						});

					column.data().unique().sort().each(function(d, j) {
						select.append('<option value="' + d + '">' + d + '</option>')
					});
				};

			});  
		},
		
		buttons: [
				{extend: 'excel', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'VIEW FPO SITE ',
					message:'VIEW FPO SITE  @ ' + sitecode_name + '\n\r'+datetimes,
					exportOptions: {
						format: {
        					header: function ( data, column ) {
						if ( column == 0 ) 
        					return "SI.No."
						if ( column == 1 ) 
        					return "FPO SITE NAME"
						if ( column == 2 ) 
        					return "SITE CODE"
           				 if ( column == 3 ) 
        					return "DATE OF CREATION"
						if ( column == 4 ) 
        					return "STATE"
						if ( column == 5 ) 
        					return "ALL-STATUS"
						if ( column == 6 ) 
        					return "Last Updated Date"
						if ( column == 7 ) 
        					return "Clustered"
        				}
   					}},}, 
		
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'VIEW FPO SITE  @ ' + sitecode_name,
					filename:'VIEW FPO SITE ',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7],
						margin: true,
						format: {
        					header: function ( data, column ) {
						if ( column == 0 ) 
        					return "SI.No."
						if ( column == 1 ) 
        					return "FPO SITE NAME"
						if ( column == 2 ) 
        					return "SITE CODE"
           				 if ( column == 3 ) 
        					return "DATE OF CREATION"
						if ( column == 4 ) 
        					return "STATE"
						if ( column == 5 ) 
        					return "ALL-STATUS"
						if ( column == 6 ) 
        					return "Last Updated Date"
						if ( column == 7 ) 
        					return "Clustered"
        				}}}, 

		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['12%','12%','12%','12%','12%','12%','12%','16%'];
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
					//doc.styles.tableHeader.
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
				 filename:'VIEW FPO SITE HISTORY',
					message:'VIEW FPO SITE HISTORY @ '+ sitecode_name + '\n\r' + datetimes ,
					exportOptions: {
						format: {
        					header: function ( data, column ) {
						if ( column == 0 ) 
        					return "SI.No."
						if ( column == 1 ) 
        					return "FPO SITE NAME"
						if ( column == 2 ) 
        					return "SITE CODE"
           				 if ( column == 3 ) 
        					return "DATE OF CREATION"
						if ( column == 4 ) 
        					return "STATE"
						if ( column == 5 ) 
        					return "ALL-STATUS"
						if ( column == 6 ) 
        					return "Last Updated Date"
						if ( column == 7 ) 
        					return "Clustered"
        				}}},
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
						"initComplete": function( settings ) {
	     $("#datatable_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#datatable_filter input[type='search']").attr('id', 'searchdatatable');
		$('#clear6').click(function() {	
		document.getElementById("searchdatatable").value=""
		var table = $('#datatable').DataTable();
		table.search('').draw();
	   })
    	}
	});
});


$(document).ready(function() {
	$('#calendar').mobiscroll().datepicker({
		controls: ['calendar']
	});
});

function exceldownload(obj) {
	$(".buttons-excel").trigger('click');
}

function pdfdownload(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: "pdfdownload_viewfpoSites",
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
