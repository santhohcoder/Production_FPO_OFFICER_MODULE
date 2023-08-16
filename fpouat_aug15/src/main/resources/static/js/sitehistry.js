function clearDropDown(e) {
	e.empty();

}

var sitecdeval = {};

	$("select.selsite").change(function() {
		var selectedsite = $(this).children("option:selected").val();
		var dvhide = document.getElementById("sitehstry");
		if(selectedsite == "--Select Site History--"){
			dvhide.style.display = "none";
			$('#sitename').val('');
		}else{
			showLoader();
	
		dvhide.style.display = "block";
		$('#sitename').val(selectedsite);
		$.ajax({
			url: 'getFpoSiteName?siteNme=' + selectedsite,
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(resp) {
				$('#hstrytable').dataTable().fnDestroy();				
				clearDropDown($("#viewhstrysts"));
				var viewdata = resp;
				$.each(viewdata, function(index, value) {
					sitecdeval = value[0];
					$('#sitecode').val(value[0]);
					if (value[2] == 'Y') {
						sitestatus = 'Active';
					} else if (value[2] == 'B') {  
						sitestatus = 'Blocked';
					} else if (value[2] == 'D') {
						sitestatus = 'Deleted';
					} else if (value[2] == 'N') {
						sitestatus = 'Yet to be activated';
					}
					var markup = "<tr border=1 bgcolor='white'><td align= 'center' class= 'font-weight-bold'>" + value[0] + "</td><td align= 'center' class= 'font-weight-bold'>" + value[1] + "</td><td align= 'center' class= 'font-weight-bold'>" + sitestatus + "</td></tr>"
					$("#viewhstrysts").append(markup);

					$("#sitehstrybtn").show();
					$("#allsitehstry").hide();
					$("#sitehstry").show();
				
					
				});						
			var sitecode_name= $("#sitecode").val();
     $('#hstrytable').DataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'VIEW FPO SITE HISTORY',
					message:'VIEW FPO SITE HISTORY @ ' + sitecode_name + '\n\r'+datetimes,
					
				}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    				className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'VIEW FPO SITE HISTORY @ ' + sitecode_name,
					filename:'VIEW FPO SITE HISTORY',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2],
						margin: true,
					
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['30%','35%','35%'];
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
				 filename:'VIEW FPO SITE HISTORY',
					message:'VIEW FPO SITE HISTORY @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
		"initComplete": function( settings ) {
	     $("#hstrytable_filter").append('<button type="button" class="btn btn-info" id="clearing" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#hstrytable_filter input[type='search']").attr('id', 'searchhstrytable');
		$('#clearing').click(function() {	
		document.getElementById("hstrytable").value=""
		var table = $('#hstrytable').DataTable();
		table.search('').draw();
	   })
    	}
	
});
				
				
				
				
				
				
				
				
			},
    error: function(xhr, textStatus, errorThrown) {
        if (xhr.status === 400) {
             window.location = "error"; // Redirect to the error page
        } else {
            // Handle other error cases
            console.log(xhr, textStatus, errorThrown);
        }
    },

		});
		
		hideLoader();

};
	});




	$("#sitehstrybtn").click(function() {
		 var selectedState = $('#sitecode').val();
	if ($('#sitename').val() == "All Sites")
	  selectedState="All Sites";
		/*var dvallhide = document.getElementById("allsitehstry");
			dvallhide.style.display = "block";*/
		$.ajax({
			url: 'showsitehstry?siteCde=' + selectedState,
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(resp) {
				var dataval = resp;
				clearDropDown($("#viewstshstry"));
				if(dataval != ""){
						
				$.each(dataval, function(index, value) {
					var iterator = index + 1;
					if (value[2] == 'Y') {
						sitestatus = 'Active';
					} else if (value[2] == 'B') {
						sitestatus = 'Blocked';
					} else if (value[2] == 'D') {
						sitestatus = 'Deleted';
					} else if (value[2] == 'U') {
						sitestatus = 'Unblocked';
					} else if (value[2] == 'N') {
						sitestatus = 'FPO Site Created';
						}
					var reas;
					if (value[3] == null || value[3]=='')
					    reas='-';
	                else
                        reas=value[3];
					var markup = "<tr border=1 bgcolor='white'><td align= 'center' class= 'font-weight-bold'>" + iterator + "</td><td align= 'center' class= 'font-weight-bold'>" + value[0] + "</td><td align= 'center' class= 'font-weight-bold'>" + value[1] + "</td><td align= 'center' class= 'font-weight-bold'>" + sitestatus + "</td><td align= 'center' class= 'font-weight-bold'>" + reas + "</td><td align= 'center' class= 'font-weight-bold'>" + value[4] + "</td><td align= 'center' class= 'font-weight-bold'>" + value[5] + "</td></tr>"
					$("#viewstshstry").append(markup);
					
				});
				var sitecode_name= $("#sitecode").val();
     $('#datatablenew').DataTable({
		
	
	"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',

		
		buttons: [
				{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'VIEW FPO SITE HISTORY',
					message:'VIEW FPO SITE HISTORY @ ' + sitecode_name + '\n\r'+datetimes,
					
				}, 
						
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    				className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'VIEW FPO SITE HISTORY @ ' + sitecode_name,
					filename:'VIEW FPO SITE HISTORY',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6],
						margin: true,
					
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['10%','15%','15%','15%','15%','15%','15%'];
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
				 filename:'VIEW FPO SITE HISTORY',
					message:'VIEW FPO SITE HISTORY @ '+ sitecode_name + '\n\r' + datetimes ,
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
		"initComplete": function( settings ) {
	        $("#datatablenew_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
 						
						
   	}
	
});
	
		
				
				
			$("#allsitehstry").show();
				
				}else{
					$("#allsitehstry").hide();
				alert("History None")
				
				}
				
			},
			fail: function(rs, e) {
				console.log(rs, responseText);
			},

		});

	});


function viewactivesites(){
	window.location.href = "viewfposite";
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
