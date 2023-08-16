/*$(document).ready(function() {
	$('#ViewuserHstrySite').DataTable({
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: ['excel']
		});
		});*/
	
	
	


/*function exceldownload(obj){
	$(".buttons-excel").trigger('click');
}
*/
function clearDropDown(e) {
	e.empty();
}
var selctSite = "";
$(document).ready(function() {
	$("select.selsite").change(function() { 
		var selectedsite = $(this).children("option:selected").val();
		var dvuserview = document.getElementById("activeusers");
		var pdfexcel = document.getElementById("pdf&excelExport");
		selctSite = selectedsite;
		if (selectedsite == "--Select Active Sites--") {
			
document.getElementById("ViewuserHstrySite").style.display = "none";
document.getElementById("activeusers").style.display = "none";
		
		} else {
			document.getElementById("ViewuserHstrySite").style.display = "inline-table";
			document.getElementById("activeusers").style.display = "block";
			
			showLoader();
			$.ajax({
				url: 'getusersite?userstecde=' + selectedsite,
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(resp) {
					$('#ViewuserHstrySite').dataTable().fnDestroy();
					$("#viewactveuser").empty("");
					
					clearDropDown($("#viewactveuser"));
					$.each(resp, function(index, value) {
						var endsts = "";
						if (value[6] == null) {
							endsts = "Till-Date"
						} else {
							endsts = value[6];
						}
						var iterator = index + 1;
						var markup = "<tr><td align= 'center' class= 'font-weight-medium'>" + iterator + "</td><td align= 'center' class= 'font-weight-medium'>" + value[1] + "</td><td align= 'center' class= 'font-weight-medium'>" + value[2] + "</td><td align= 'center' class= 'font-weight-medium'>" + value[0] + "</td><td align= 'center' class= 'font-weight-medium'>" + value[3] + "</td></td><td align= 'center' class= 'font-weight-medium'>" + value[4] + "</td></td><td align= 'center' class= 'font-weight-medium'>" + value[5] + "</td></td><td align= 'center' class= 'font-weight-medium'>" + endsts + "</td></tr>"
						$("#viewactveuser").append(markup);
		
					});
					
					var sitecode_name= $("#sitecode").val();

	
	$('#ViewuserHstrySite').DataTable({
		
	
	"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
			
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'List of Local System Administrator(PLAs) User History Of FPO Site',
					message:'List of Local System Administrator(PLAs) User History Of FPO Site @ ' + sitecode_name + '\n\r'+datetimes,
					
						}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'List of Local System Administrator(PLAs) User History Of FPO Site @ ' + sitecode_name,
					filename:'List of Local System Administrator(PLAs) User History Of FPO Site',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7],
						margin: true,
					
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['9%','13%','13%','13%','13%','13%','13%','13%'];
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
				 filename:'List of Local System Administrator(PLAs) User History Of FPO Site',
					message:'List of Local System Administrator(PLAs) User History Of FPO Site @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
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
	
});	/*
			        pdfexcel.style.display = "block";
					dvuserview.style.display = "block";*/

				}
				
			});
			hideLoader();
		}
	});
});
/*
function pdfdownload(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_Lsmuserhstry?siteSelct=' + selctSite,
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
	}, 500);
}	

