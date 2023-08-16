
var sitecode_name= $("#sitecode").val();


function clearDropDown(e) {
	e.empty();

}
var sitecdeval = {};
$(document).ready(function() {
	$("select.country").change(function() {
		var selectedsite = $(this).children("option:selected").val();
		var dvhide = document.getElementById("Lsmsitehstry");
		if (selectedsite == "--Select Site History--") {
			dvhide.style.display = "none";
		} else {
			showLoader();
			dvhide.style.display = "block";
			$.ajax({
				url: 'getLSMHstry?siteNme=' + selectedsite,
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(resp) {
					clearDropDown($("#viewhstrysts"));
					var viewdata = resp;
					
					$('#hstrytable').dataTable().fnDestroy();
					$("#viewhstrysts").empty("");  
					
					$.each(viewdata, function(index, value) {
						var iterator = index + 1;
						var val7 = value[7];
						var val8 = value[8];
						var val9 = value[9];
						if (val7 == null || val7=='')
						      val7='-';
						if (val8 == null || val8=='')
						      val8='-';
						if (val9 == null || val9=='')
						      val9='-';
						var markup = "<tr><td align= 'center'>" + iterator + "</td><td align= 'center' >" + value[0] + "</td><td align= 'center' >" + value[1] + "</td><td align= 'center'>" + value[2] + "</td><td align= 'center'>" + value[3] + "</td><td align= 'center'>" + value[4] + "</td><td align= 'center'>" + value[5] + "</td><td align= 'center'>" + value[6] + "</td><td align= 'center'>" + val7 + "</td><td align= 'center'>" + val8 + "</td><td align= 'center'>" + val9 + "</td></tr>"
						$("#viewhstrysts").append(markup);
						//$("#sitehstrybtn").show();
						$("#allsitehstry").hide();
						$("#sitehstry").show();
					});
					
					
	$("#hstrytable").DataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		buttons: [
				{extend: 'excel', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'PLA (Local System Administrator) History',
					message:'PLA (Local System Administrator) History @ ' + sitecode_name + '\n\r'+datetimes,
						}, 
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'PLA (Local System Administrator) History @ ' + sitecode_name,
					filename:'PLA (Local System Administrator) History',
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
				 filename:'PLA (Local System Administrator) History',
					message:'PLA (Local System Administrator) History @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
		"initComplete": function( settings ) {
	        $("#hstrytable_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');  
 						
						
   	}  
	
});
					
					
					
					
					

				},
				fail: function(rs, e) {
					console.log(rs, responseText);
				},

			});

		};
		hideLoader();
	});

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










