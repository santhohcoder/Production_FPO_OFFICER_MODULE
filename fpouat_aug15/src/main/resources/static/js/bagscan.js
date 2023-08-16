	$("#next-titletab").html($("#next-titletab").html().replace(/oblique/, '<span style="color: black">/</span>'));
	
	var bagArticlesArray = [];
	var bagArray = [];
	var tableID ;
	$("#scanbagtable").dataTable({
	    "processing": true,
	    "ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' Art-IDs for which Article Arrival Information Received in full ',
					message:' Art-IDs for which Article Arrival Information Received in full @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','12%','10%','9%','9%','9%','10%','10%','12%','14%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Art-IDs for which Article Arrival Information Received in full @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Art-IDs for which Article Arrival Information Received in full ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Art-IDs for which Article Arrival Information Received in full @ ' + sitecode + '\n\r' + datetime ,
					filename:'Art-IDs for which Article Arrival Information Received in full',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
				"initComplete": function( settings ) {
	     $("#scanbagtable_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#scanbagtable_filter input[type='search']").attr('id', 'searchscanbagtable');
		$('#clear6').click(function() {	
		document.getElementById("searchscanbagtable").value=""
		var table = $('#scanbagtable').DataTable();
		table.search('').draw();
	   })
    	}
		
	});
	
/*	$("#pentab").dataTable({
	"ordering": false,
	    "processing": true,
		"paging": true,		
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		buttons: ['excel']
	});*/
	

function reassign() {
	$("#confirmArrivalnHistoryDiv").hide();
		$("#confirmArrivalDiv").hide('slow');
		$("#confirmSubmitBtn").hide('slow');
		$("#confirmArrivalHistoryDiv").hide('slow');
		$("#PendingArrivalBags").hide('slow');
	$('#reasssignDiv').show();
}
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	
	    if (month.length < 2) 
	        month = '0' + month;
	    if (day.length < 2) 
	        day = '0' + day;
	
	    return [ day, month, year].join('/');
	}
	
	var firstDay;
	var lastDay;
	
	function currentDate() {
		var date = new Date(), y = date.getFullYear(), m = date.getMonth();
		firstDay = new Date(y, m, 1);
		lastDay = new Date(y, m + 1, 0);
	}
	
	currentDate();
	
	/*var confirmHistoryTable=$("#confirmHistorytable").DataTable({
	    "processing": false,
	    "paging": true,
	    "autoWidth": false,
	    "ordering": false,
        "dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' Confirmation History For A',
					message:' Confirmation History For A @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,9],
						margin: true,
						
      						},
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
						
						
		   		    doc.content[1].table.widths = ['5%','17%','9%','12%','9%','9%','9%','10%','20%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Confirmation History For A @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Confirmation History For A ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Confirmation History For A @ ' + sitecode + '\n\r' + datetime ,
					filename:'Confirmation History For A',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						 
						},}],
	   
		"ajax": {
                "url": "getarrivalhistory?fromDate="+formatDate(firstDay)+"&toDate="+formatDate(lastDay),
                "beforeSend" : function(){
           
			showLoader();
                },
            },
            "fnDrawCallback": function( oSettings ) {
				hideLoader();
			},
            "columnDefs": [{
                "targets": '_all',
                "defaultContent": ""
            }],

            "columns": [{
	
                    "data": "bagNo",
                    "render": function(data, type, row, meta) {
                    	return meta.row+1;
                    }
                },
                {
                    "data": "bagNo",
                    "className":"ta-left",
                    "render": function(data, type, row, meta) {
                    	return '<td><span style='+(row.bagNoFlag=="Y" ? "color:#bb0e0e;font-weight:bold;" : "color:blue;font-weight:bold;")+'>'+(row.bagNoFlag=="Y" ? "BAG-" : "RID-")+'</span><span>'+data+'</span></td>';
                    }
                },
                {
                    "data": "receivedDate",
                    "render": function(data, type, row, meta) {
                    	var receivedDt=new Date(data);
	        			var	receivedDay = '' + receivedDt.getDate();
		    			if (receivedDay.length < 2) 
		        			receivedDay = '0' + receivedDay;
                    	return receivedDay+'-'+new Date(data).toLocaleString('default', { month: 'short' })+'-'+receivedDt.getFullYear();
                    }
                },
                {
                    "data": "totalCount"
                },
                {
                    "data": "withEadCurrSite"
                },
                {
                    "data": "withEadOtherSite"
                },
                {
                    "data": "withEadSiteNotAllot"
                },
                {
                    "data": "withoutEad"
                },
                {
                    "data": "bagNo",
                    "render": function(data, type, row, meta) {
                    	return '<i style="cursor:pointer;" bagflag='+(row.bagNoFlag)+' id='+(row.bagNo)+' view="Y" onclick="viewArticles(this)" class="fa fa-eye" aria-hidden="true"></i>';
                    }
                },
                {
                    "data": "scannedDate",
                    "render": function(data, type, row, meta) {
                    	var scannedDt=new Date(data);
	        			var	scannedDay = '' + scannedDt.getDate();
		    			if (scannedDay.length < 2) 
		        			scannedDay = '0' + scannedDay;
                    	return scannedDay+'-'+new Date(data).toLocaleString('default', { month: 'short' })+'-'+scannedDt.getFullYear();
                    }
                }
            ]
	});*/
	
			function showConfirmArrival() {
		$("#confirmArrivalHistoryDiv").hide();
		$("#confirmArrivalDiv").show('slow');
		$("#confirmSubmitBtn").show('slow');
		$("#PendingArrivalBags").hide('slow');
		$("#confirmArrivalnHistoryDiv").hide('slow');
		$('#reassignDiv').hide();
		$("#regulationtableshow").hide();
		$("#Bagregulationtableshow").hide();
		$("#reassignHistoryDiv").hide();
		$("#BagreglntableshowHistry").hide();
		$("#ReceptIDHistrytableshow").hide();
	}
	
	function showConfirmArrivalHistory() {
		$("#confirmArrivalHistoryDiv").show();
		$("#confirmArrivalDiv").hide('slow');
		$("#confirmArrivalnHistoryDiv").hide('slow');
		$("#confirmSubmitBtn").hide('slow');
		$("#PendingArrivalBags").hide('slow');
		$('#reassignDiv').hide();
		$("#regulationtableshow").hide();
		$("#Bagregulationtableshow").hide();
		$("#reassignHistoryDiv").hide();
		$("#BagreglntableshowHistry").hide();
		$("#ReceptIDHistrytableshow").hide();
	}
	
    function showConfirmArrivalnHistory() {
	showLoader();
	getConfirmReportHistory();
	hideLoader();
		$("#confirmArrivalnHistoryDiv").show();
		$("#confirmArrivalDiv").hide('slow');
		$("#confirmSubmitBtn").hide('slow');
		$("#confirmArrivalHistoryDiv").hide('slow');
		$("#PendingArrivalBags").hide('slow');
		$('#reassignDiv').hide();
		$("#regulationtableshow").hide();
		$("#Bagregulationtableshow").hide();
		$("#reassignHistoryDiv").hide();
		$("#BagreglntableshowHistry").hide();
		$("#ReceptIDHistrytableshow").hide();
	}
	
	
	function pendscan(){
		$("#confirmArrivalHistoryDiv").hide();
		$("#reassignDiv").hide();
		$("#confirmArrivalnHistoryDiv").hide('slow');
		$("#confirmArrivalDiv").hide('slow');
		$("#confirmSubmitBtn").hide('slow');
		$("#PendingArrivalBags").show();
		$('#pentabf').show();
		$("#regulationtableshow").hide();
		$("#Bagregulationtableshow").hide();
		$("#reassignHistoryDiv").hide();
		$("#BagreglntableshowHistry").hide();
		$("#ReceptIDHistrytableshow").hide();
	}
	
	function reassign() {
		$("#confirmArrivalHistoryDiv").hide();
		$("#confirmArrivalnHistoryDiv").hide('slow');
		$("#confirmArrivalDiv").hide('slow');
		$("#confirmSubmitBtn").hide('slow');
		$("#PendingArrivalBags").hide();
		$('#pentabf').hide();
		$("#reassignDiv").show('slow');
		$('#button1').show();
  		$('#button2').show();
$("#reassignHistoryDiv").hide();
$("#ReceptIDHistrytableshow").hide();
$("#BagreglntableshowHistry").hide();
		}
		
	function reassignHistory(){
		$("#confirmArrivalHistoryDiv").hide();
		$("#confirmArrivalnHistoryDiv").hide('slow');
		$("#confirmArrivalDiv").hide('slow');
		$("#confirmSubmitBtn").hide('slow');
		$("#PendingArrivalBags").hide();
		$('#pentabf').hide();
		$("#reassignDiv").hide();
		$("#reassignHistoryDiv").show('slow');
		$('#button3').show();
  		$('#button4').show();
$("#regulationtableshow").hide();
		$("#Bagregulationtableshow").hide();
	}
		
		$('#button1').click(function() {
	$("#regulationtableshow").show('slow');
	$("#regulationtable").show('slow');
	$("#Bagregulationtableshow").hide();
	$("#Bagregulationtable").hide();
	$("#ReceptIDHistrytableshow").hide();
	$("#ReceptIDHistrytable").hide();
	$("#BagreglntableshowHistry").hide();
	$("#BagreglntableHistry").hide();
});

$('#button2').click(function() {
	$("#regulationtableshow").hide();
	$("#regulationtable").hide();
	$("#Bagregulationtableshow").show('slow');
	$("#Bagregulationtable").show('slow');
	$("#ReceptIDHistrytableshow").hide();
	$("#ReceptIDHistrytable").hide();
	$("#BagreglntableshowHistry").hide();
	$("#BagreglntableHistry").hide();
});


$('#button3').click(function() {
	$("#regulationtableshow").hide('slow');
	$("#regulationtable").hide('slow');
	$("#Bagregulationtableshow").hide();
	$("#Bagregulationtable").hide();
	$("#ReceptIDHistrytableshow").show();
	$("#ReceptIDHistrytable").show();
	$("#BagreglntableshowHistry").hide();
	$("#BagreglntableHistry").hide();
	
	showLoader();
		    $.ajax({
		        url: "getRecpIdReglsnHistry",
		        type: "get",
		       
			success: function(data) {
							hideLoader();
						
						$('#ReceptIDHistrytable').dataTable().fnDestroy();
		$("#recpIdHistryRegn").empty("");
						 let html = '';
    for (let index = 0; index < data.length; index++) {
      html += `<tr>                
<td>${index + 1}</td>
<td>${data[index].article_id !== null ? data[index].article_id : '-'}</td>
<td>${data[index].scan_dt !== null ? data[index].scan_dt : '-'}</td>
<td>${data[index].bag_no !== null ? data[index].bag_no : '-'}</td>
<td>${data[index].recd_event_dt !== null ? data[index].recd_event_dt : '-'}</td>
<td>${data[index].recept_id !== null ? data[index].recept_id : '-'}</td>
<td>${data[index].ooc_dt !== null ? data[index].ooc_dt : '-'}</td>

	</td>
      </tr>`;
 
    }
 $("#recpIdHistryRegn").html(html); 
  /*  bagArticlesArray = [];*/
							$('#ReceptIDHistrytable').show();							
							$('#recpIdHistryRegn').show();
$("#ReceptIDHistrytable").dataTable({
		"processing": true,
	    "ordering": false,
		//"paging": true,	
    	//"searching": false,	
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'  REASSAIGN HISTORY - RECEPT ID',
					message:'  REASSAIGN HISTORY - RECEPT ID @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','20%','15%','15%','15%','20%','10%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+' REASSAIGN HISTORY - RECEPT ID @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:' REASSAIGN HISTORY - RECEPT ID',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'  REASSAIGN HISTORY - RECEPT ID @ ' + sitecode + '\n\r' + datetime ,
					filename:' REASSAIGN HISTORY - RECEPT ID',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],

"initComplete": function(settings) {
  $("#ReceptIDHistrytable_filter").append('<button type="button" class="btn btn-info" id="clearing" ' +
    'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
  $("#ReceptIDHistrytable_filter input[type='search']").attr('id', 'searchReceptIDHistrytable');
  
  $('#ReceptIDHistrytable_filter').on('click', '#clearing', function() {
    var table = $('#confirmnHistorytable').DataTable();
    table.search('').draw();
    $("#searchReceptIDHistrytable").val(""); // Clear the search input field
  });
}

               });  

							},
							  error: function(rs, e) {
                                  swal('OOPS!', "Something went wrong", 'error');
                            }
						});
});



$('#button4').click(function() {
	$("#regulationtableshow").hide('slow');
	$("#regulationtable").hide('slow');
	$("#Bagregulationtableshow").hide();
	$("#Bagregulationtable").hide();
	$("#ReceptIDHistrytableshow").hide();
	$("#ReceptIDHistrytable").hide();
	$("#BagreglntableshowHistry").show();
	$("#BagreglntableHistry").show();
	showLoader();
		    $.ajax({
		        url: "bagNORegulationHstry",
		        type: "get",
		       
			success: function(data) {
							hideLoader();
						$('#BagreglntableHistry').dataTable().fnDestroy();
		$("#BagRegnhstry").empty("");
						
						 let html = '';
    for (let index = 0; index < data.length; index++) {
      html += `<tr>                
<td>${index + 1}</td>
<td>${data[index][0] !== null ? data[index][0] : '-'}</td>
<td>${data[index][1] !== null ? data[index][1] : '-'}</td>
<td>${data[index][2] !== null ? data[index][2] : '-'}</td>
<td>${data[index][3] !== null ? data[index][3] : '-'}</td>
<td>${data[index][4] !== null ? data[index][4] : '-'}</td>
<td>${data[index][6] !== null ? data[index][6] : '-'}</td>

	</td>
      </tr>`;
 
    }
 $("#BagRegnhstry").html(html); 
  /*  bagArticlesArray = [];*/
							$('#BagreglntableHistry').show();							
							$('#BagRegnhstry').show();
$("#BagreglntableHistry").dataTable({
		"processing": true,
	    "ordering": false,
		//"paging": true,	
    	//"searching": false,	
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'   REASSAIGN HISTORY - BAG NO ',
					message:' REASSAIGN HISTORY - BAG NO @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','20%','15%','15%','15%','20%','10%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'REASSAIGN HISTORY - BAG NO @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'REASSAIGN HISTORY - BAG NO ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' REASSAIGN HISTORY - BAG NO @ ' + sitecode + '\n\r' + datetime ,
					filename:'REASSAIGN HISTORY - BAG NO',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],

"initComplete": function(settings) {
  $("#BagreglntableHistry_filter").append('<button type="button" class="btn btn-info" id="clearing" ' +
    'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
  $("#BagreglntableHistry_filter input[type='search']").attr('id', 'searchBagreglntableHistry');
  
  $('#BagreglntableHistry_filter').on('click', '#clearing', function() {
    var table = $('#BagreglntableHistry').DataTable();
    table.search('').draw();
    $("#searchBagreglntableHistry").val(""); // Clear the search input field
  });
}

               });  
  
							},
							  error: function(rs, e) {
                                  swal('OOPS!', "Something went wrong", 'error');
                            }
						});
});

	
	function checkOocGiven() {
		    showLoader();
		    $.ajax({
		        url: "checkoocgiven?itemId=" + $("#articleIdEntered").val(),
		        type: "get",
		        success: function(data) {
		            hideLoader();
		            if (data.oocGiven != undefined && data.oocGiven) {
		                swal('OOPS!', 'OOC is given for entered article !', 'error');
		            }
					else{
						showLoader();
					    $.ajax({
		                  url:"getbagdata?itemId=" + $("#articleIdEntered").val(),
						  type: "get",
 						   success: function(data) {
							hideLoader();
							$('#dispoldbagdet').html(data);
							},
							  error: function(rs, e) {
                                  swal('OOPS!', "Something went wrong", 'error');
                            }
						});
					}
},
});
}
	
	
	function bodyScroll() {
	    $("body").css('overflow-y', 'auto');
	}
	
	/*function checkAllBagRecpt(obj) {
	    if ($(obj).prop("checked")) {
	        swal({
	            title: "Do you want to Confirm Arrival for all Recpt. / Bag ?",
	            icon: "warning",
	            buttons: ["No", "Yes"],
	            dangerMode: false,
	        }).then((willDelete) => {
	            if (willDelete) {
	                $($("#scanbagtable").dataTable().$('input[type="checkbox"]').map(function() {
	                    $(this).prop('checked', 'checked');
	                }));
	            } else {
	                $(obj).removeProp("checked");
	            }
	        });
	    } else {
	        $($("#scanbagtable").dataTable().$('input[type="checkbox"]:checked').map(function() {
	            if (!($(this).css('pointer-events') == 'none')) {
	                $(this).removeProp('checked');
	            }
	        }));
	    }
	}*/
	
	
	function checkAllBagRecpt()
	{
     var checkboxes = document.getElementsByTagName('input');
      var pagelen =$('#scanbagtable').DataTable().page.info().length+2;
         for (var i = 1; i < pagelen; i++) {
           var row=i % pagelen;
               if ($('#chkallscan').is(":checked") )
                $('#scanbagtable').find('tr').eq(row).find("input#chkboxarr").prop('checked',true);
               else
                $('#scanbagtable').find('tr').eq(row).find("input#chkboxarr").prop('checked',false);
             }
       
}
	
	
	
/*	function checkAllnoBagRecpt()
	{
     var checkboxes = document.getElementsByTagName('input');
     var pagelen =$('#pentabo').DataTable().page.info().length+1;
      for (var i = 1; i < pagelen; i++) {
           var row=i % pagelen;
               if ($('#chkallnoscan').is(":checked") )
                $('#pentabo').find('tr').eq(row).find("input#chkboxnoarr").prop('checked',true);
               else
                $('#pentabo').find('tr').eq(row).find("input#chkboxnoarr").prop('checked',false);
             }
       
}

	function checkAllnbBagRecpt()
	{
     var checkboxes = document.getElementsByTagName('input');
     var pagelen =$('#pentabb').DataTable().page.info().length+1;
      for (var i = 1; i < pagelen; i++) {
           var row=i % pagelen;
               if ($('#chkallnbscan').is(":checked") )
                $('#pentabb').find('tr').eq(row).find("input#chkboxnbarr").prop('checked',true);
               else
                $('#pentabb').find('tr').eq(row).find("input#chkboxnbarr").prop('checked',false);
             }
       
}*/
	
function checkAllnfBagRecpt()
{
     var checkboxes = document.getElementsByTagName('input');
     var pagelen =$('#pentabf').DataTable().page.info().length+1;
         for (var i = 1; i < pagelen; i++) {
             var row=i % pagelen;
               if ($('#chkallnfscan').is(":checked") )
                 $('#pentabf').find('tr').eq(row).find("input#chkboxnfarr").prop('checked',true);
               else
                 $('#pentabf').find('tr').eq(row).find("input#chkboxnfarr").prop('checked',false);
            
         }
 
}

/*function checkAllnfbBagRecpt()
{
     var checkboxes = document.getElementsByTagName('input');
     var pagelen =$('#pentabfb').DataTable().page.info().length+1;
         for (var i = 1; i < pagelen; i++) {
             var row=i % pagelen;
               if ($('#chkallnfscan').is(":checked") )
                 $('#pentabfb').find('tr').eq(row).find("input#chkboxnfbarr").prop('checked',true);
               else
                 $('#pentabfb').find('tr').eq(row).find("input#chkboxnfbarr").prop('checked',false);
            
         }
 
}*/
	
/*$("#pentabo").dataTable({

		"ordering": false,
	    "processing": true,
		"paging": true,		
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//buttons: ['excel'],
		buttons: ['print'],
		
		

		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					message:'PENDING ARTICLE ARRIVAL INFORMATION @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}	
						},
           
		{
    	extend: 'pdfHtml5',
		text:'<i>PDF</i>',
		pageSize: 'A3',
		className: ' btn btn-success mb-3 ',
		orientation: 'portrait',
		title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'PENDING ARTICLE ARRIVAL INFORMATION @ ' + sitecode +
           					'\n\r' + ' SSO ID: ' + empid,
		
		message:datetime, 
					filename:'PENDING ARTICLE ARRIVAL INFORMATION',
		customize: function(doc, header) {
				   	 doc.defaultStyle.alignment = 'center'
      			   	 //doc.styles.tableHeader.fontSize = 10;
					 //doc.styles.tableHeader.fillColor='#ADD8E6',
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center',
								bold: true,
								italics: true
    						},
					doc.styles.title.fontSize=20;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 12;
				    doc.styles.message.alignment = "right";
    		        },

    
  },
{
extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'PENDING ARTICLE ARRIVAL INFORMATION @ ' + sitecode + '\n\r' + datetime ,


}]	


 });	*/



/*
$("#pentabb").dataTable({

		"ordering": false,
	    "processing": true,
		"paging": true,		
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//buttons: ['excel'],
		buttons: ['print'],
		
		

		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					message:' @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}	
						},
           
		{
    	extend: 'pdfHtml5',
		text:'<i>PDF</i>',
		pageSize: 'A3',
		className: ' btn btn-success mb-3 ',
		orientation: 'portrait',
		title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+' @ ' + sitecode +
           					'\n\r' + ' SSO ID: ' + empid,
		
		message:datetime, 
					filename:'PENDING ARTICLE ARRIVAL INFORMATION',
		customize: function(doc, header) {
				   	 doc.defaultStyle.alignment = 'center'
      			   	 //doc.styles.tableHeader.fontSize = 10;
					 //doc.styles.tableHeader.fillColor='#ADD8E6',
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center',
								bold: true,
								italics: true
    						},
					doc.styles.title.fontSize=20;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 12;
				    doc.styles.message.alignment = "right";
    		        },

    
  },
{
extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' @ ' + sitecode + '\n\r' + datetime ,


}]	


 });*/

	
	
	
	
	function submitScannedBag() {
	    if ($($("#scanbagtable").dataTable().$('input[type="checkbox"]:checked')).length < 1) {
	        swal('OOPS!', 'Please select atleast one confirm arrival !', 'error');
	        return false;
	    }
	    swal({
	        title: "Are you sure to submit confirmation for checked Recpt. ID / Bag which is arrived at this site, " + $('#sitecode').val() + "?",
	      //  text: "Once Submitted can't be changed !",
	        icon: "warning",
	        buttons: ["No", "Yes"],
	        dangerMode: false,
	    }).then((willDelete) => {
	        if (willDelete) {
	            showLoader();
	            $($("#scanbagtable").dataTable().$('input[type="checkbox"]:checked').map(function() {
	                var bagNameType = $(this).closest('tr').find('td:nth-child(2)').text();
	                var site = $(this).closest('tr').before().find('input').attr('site');
	                if (!($(this).css('pointer-events') == 'none')) {
	                    var obj = {};
	                    obj["bagNo"] = bagNameType.substring(4);
	                    obj["bagType"] = bagNameType.substring(0, 1);
	                    obj["site"] = site;
	                    bagArticlesArray.push(obj);
	                }
	            }));
	            var oMyForm = new FormData();
	            bagArticlesArray.forEach((item, index) => {
	                oMyForm.append("fpoScansInfo[" + index + "].bagNo", item.bagNo);
	                oMyForm.append("fpoScansInfo[" + index + "].bagType", item.bagType);
	                oMyForm.append("fpoScansInfo[" + index + "].site", item.site);
	                if (item.articleId != undefined)
	                    oMyForm.append("fpoScansInfo[" + index + "].articleId", item.articleId);
	                if (item.scanReport != undefined)
	                    oMyForm.append("fpoScansInfo[" + index + "].scanReport", item.scanReport);
	            });
	            $.ajax({
	                url: "savescannedbag",
	                type: "POST",
	                contentType: false,
	                processData: false,
	                data: oMyForm,
	                success: function(data) {
	                    if (data.status) {
	                        location.href=localStorage.getItem("currentUrl");
	                    } else {
	                        swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
	                    }
	                },
	                error: function(rs, e) {
	                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
	                }
	            });
	        }
	    });
	}
	
	
/*	function editScanRepno() {
		tableID =$("#pentabo");
	    if ($($("#pentabo").dataTable().$('input[type="checkbox"]:checked')).length < 1) {
	        swal('OOPS!', 'Please select atleast one confirm arrival !', 'error');
	        return false;
	    }
	    swal({
	    //    title: "Are you sure to submit confirmation for checked Articles which is arrived at this site?",
            title: "You are confirming the arrival of the selected Articles, arrived in India. Are you sure?",
	      //  text: "Once Submitted can't be changed !",
	        icon: "warning",
	        buttons: ["No", "Yes"],
	        dangerMode: false,
	    }).then((willDelete) => {
	        if (willDelete) {
	            showLoader();
                bagArticlesArray = [];
	              $($("#pentabo").dataTable().$('input[type="checkbox"]:checked').map(function() {
	                var articleId = $(this).closest('tr').find('td:nth-child(2)').text();
	                 articleId = $(this).closest('tr').find('td:eq(1)').text();
					var bagno = $(this).closest('tr').find('td:eq(12)').text();
					var site = $(this).closest('tr').find('td:eq(5)').text();
	                if (!($(this).css('pointer-events') == 'none')) {
	                    var obj = {};

	                    obj["articleId"] =articleId;  
	                    obj["bagNo"] = bagno;
                        obj["precpid"]=$(this).closest('tr').find('td:eq(8)').text();
						obj["arecpid"]=$(this).closest('tr').find('td:eq(9)').text();
						obj["bagNo"] = bagno;
						obj["cussite"] = site;
	                  //  obj["site"] = site;
	                    bagArticlesArray.push(obj);
						}
	                 }));
	                   var table = document.getElementById('bagarticletablen');
                       var rowCount = table.rows.length - 1;
     				   var i=0;
     				   while ( i < rowCount )
   								 {
     								 document.getElementById("bagarticletablen").deleteRow(1);
      								 i=i+1;}
	                        
						$('#bagarticletablen').dataTable().fnDestroy();
						$("#editnotarrdet").empty("");
						var sno=0;
						$.each(bagArticlesArray, function(index, value) {
							sno=sno+1;
					     var markup = "<tr   style='valign:middle;' class='rowDis'><input type='hidden' id='itemid' value='" + value.articleId + "'><td  width='1%' align='center' class= 'font-weight-medium'>" + sno + "</td><td  width='4%' id='" + index + "' onclick=viewSummary(this,'bagarticletablen','" + value.articleId + "')>" + value.articleId + "</td><td width='4%'>" + value.cussite + "</td><td width='4%'>" + value.precpid + "</td>";
                        markup=markup + value.precpid + "</td>";
					    markup=markup+"<td><textarea type='textbox' id='scanrepn' name='scanrepn' rows=2 style='width: 100%;' placeholder='ENTER SCAN REPORT'></textarea></td></tr>";      
                        $("#editnotarrdet").append(markup);
						});
	             
     	        hideLoader();
                if ($('#bagtype').val()=='B')
                   {
						$('#recpiddisp').hide();
						$('#arecpid').hide();
						$('#bagnodisp').show();
						$('#bagno').show();
                       }
                else if ($('#bagtype').val()=='R')
                   {
						$('#recpiddisp').show();
						$('#arecpid').show();
						$('#bagnodisp').hide();
						$('#bagno').hide();
                       }
                $("#bagArticleModaln").modal('show');
	        }
	    });
	}*/
	
	
	/*function editScanRepnb() {
		tableID =$("#pentabb");
	    if ($($("#pentabb").dataTable().$('input[type="checkbox"]:checked')).length < 1) {
	        swal('OOPS!', 'Please select atleast one confirm arrival !', 'error');
	        return false;
	    }
	    swal({
	    //    title: "Are you sure to submit confirmation for checked Articles which is arrived at this site?",
            title: "You are confirming the arrival of the selected Articles, arrived at this site. Are you sure?",
	      //  text: "Once Submitted can't be changed !",
	        icon: "warning",
	        buttons: ["No", "Yes"],
	        dangerMode: false,
	    }).then((willDelete) => {
	        if (willDelete) {
	            showLoader();
                bagArticlesArray = [];
	            $($("#pentabb").dataTable().$('input[type="checkbox"]:checked').map(function() {
	                var articleId = $(this).closest('tr').find('td:nth-child(2)').text();
	                 articleId = $(this).closest('tr').find('td:eq(1)').text();
					var bagno = $(this).closest('tr').find('td:eq(12)').text();
					var site = $(this).closest('tr').find('td:eq(5)').text();
	                if (!($(this).css('pointer-events') == 'none')) {
	                    var obj = {};

	                    obj["articleId"] =articleId;  
	                    obj["bagNo"] = bagno;
                        obj["precpid"]=$(this).closest('tr').find('td:eq(8)').text();
						obj["arecpid"]=$(this).closest('tr').find('td:eq(9)').text();
						obj["bagNo"] = bagno;
						obj["cussite"] = site;
	                  //  obj["site"] = site;
	                    bagArticlesArray.push(obj);
						}
	                 }));
	                   var table = document.getElementById('bagarticletablen');
                       var rowCount = table.rows.length - 1;
     				   var i=0;
     				   while ( i < rowCount )
   								 {
     								 document.getElementById("bagarticletablen").deleteRow(1);
      								 i=i+1;}
	                        
						$('#bagarticletablen').dataTable().fnDestroy();
						$("#editnotarrdet").empty("");
						var sno=0;
						$.each(bagArticlesArray, function(index, value) {
							sno=sno+1;
					     var markup = "<tr   style='valign:middle;' class='rowDis'><input type='hidden' id='itemid' value='" + value.articleId + "'><td  width='1%' align='center' class= 'font-weight-medium'>" + sno + "</td><td  width='4%' id='" + index + "' onclick=viewSummary(this,'bagarticletablen','" + value.articleId + "')>" + value.articleId + "</td><td width='4%'>" + value.cussite + "</td><td width='4%'>" + value.precpid + "</td>";
                        markup=markup + value.precpid + "</td>";
					    markup=markup+"<td><textarea type='textbox' id='scanrepn' name='scanrepn' rows=2 style='width: 100%;' placeholder='ENTER SCAN REPORT'></textarea></td></tr>";      
                        $("#editnotarrdet").append(markup);
						});
	             
     	        hideLoader();
 if ($('#bagtype').val()=='B')
                   {
						$('#recpiddisp').hide();
						$('#arecpid').hide();
						$('#bagnodisp').show();
						$('#bagno').show();
                       }
                else if ($('#bagtype').val()=='R')
                   {
						$('#recpiddisp').show();
						$('#arecpid').show();
						$('#bagnodisp').hide();
						$('#bagno').hide();
                       }
                $("#bagArticleModaln").modal('show');
	        }
	    });
	}*/
	
	function proceedsubmit()
	{
		// $('#Modalnoscanrep').modal('hide');
	     var count = document.getElementById('bagarticletablen').rows.length-1;
		 var tit="";
	     $('#once').val(0);
	          //  showLoader();
              bagArray = [];
              for (i=1; i < count+1;i++ ){
                        var artid = $('#bagarticletablen').find('tr').eq(i).find('td:eq(1)').text();
                        var site = $('#bagarticletablen').find('tr').eq(i).find('td:eq(2)').text();
                        var scanrepn=$('#bagarticletablen').find('tr').eq(i).find(':last-child textarea').val();
                        var bagtype = $('#bagtype').val();
                        var bagno;
                        var remarks=$('#rmks').val();
                        if (bagtype=='R')
						   bagno=$('#arecpid').val();
			            else if  (bagtype=='B')
						   bagno=$('#bagno').val();	
			            if (bagno.length == 0)
                          bagno='NEF' + bagtype;
                        var once=0;				
						if ($('#articleOverallReport').val().length > 0)
					         {
						       var obj = {};
							   obj["article_Id"] = artid;
				               obj["cus_site"] = site;          
						       obj['scan_report'] = $('#articleOverallReport').val();
                               obj["bag_type"] = bagtype;
                               obj['bag_no'] = bagno;
                               $('#once').val(1);
							   obj['remarks']= remarks;
							   obj['scanned']='O';
					           bagArray.push(obj);
                               once=1;
	                         }
                        if (scanrepn.length > 0 || once==0)
                    		 { 
	                           var obj = {};
                               obj["article_Id"] = artid;
				               obj["cus_site"] = site;          
						       obj['scan_report'] = scanrepn;
                               if (scanrepn.length > 0)
                                  $('#once').val(1);
                               obj["bag_type"] = bagtype;
                               obj['bag_no'] = bagno;
							   obj['remarks']= remarks;
							   obj['scanned']='M';
					           bagArray.push(obj); 
	                         }
	                }
	
	 if ($('#once').val()==0)
	           $('#Modalnoscanrep').modal('show');
	 else
	           contsubmit();
	 }         
	           
	           
	function contsubmit(){
	$('#Modalnoscanrep').modal('hide');
               var oMyForm = new FormData();
               var ooe = document.getElementById("fpo").value;
	            bagArray.forEach((item, index) => {
		     
		            oMyForm.append("fpoScansInfo[" + index + "].articleId", item.article_Id);
	                oMyForm.append("fpoScansInfo[" + index + "].cusSite", item.cus_site);
                    oMyForm.append("fpoScansInfo[" + index + "].bagType", item.bag_type);
	                if (item.scan_report != undefined)
					    oMyForm.append("fpoScansInfo[" + index + "].scanReport", item.scan_report);
				    if (item.bag_no != undefined)
					    oMyForm.append("fpoScansInfo[" + index + "].bagNo", item.bag_no);
	                else
                        oMyForm.append("fpoScansInfo[" + index + "].bagNo","NEF"+$('#bagtype').val());
	                if (item.remarks != undefined)
					    oMyForm.append("fpoScansInfo[" + index + "].remarks", item.remarks); 
					oMyForm.append("fpoScansInfo[" + index + "].scanned",item.scanned);
					if ($('#bagtype').val()=='B')
	                    oMyForm.append("fpoScansInfo["+ index + "].OOE",ooe);
	            });
	          $.ajax({
	                url: "savescannedbag",
	                type: "POST",
	                contentType: false,
	                processData: false,
                    global: false,
                    dataType: 'text',
	                data: oMyForm,
	                success: function(data) {
		        //       hideLoader();
	                   // if (data) {
    					//    
	                       $('#bagArticleModaln').modal('hide');
                           showLoader();
                           $('#pentabf').hide();
                           $("#rmks").val('');
                           $("#articleOverallReport").val('');
                           $("#arecpid").val('');
                           $("#bagno").val('');
                        //   $("#fpo").val('');
						// location.href=localStorage.getItem("currentUrl");
                           getfiltdata();
                            /*for (var i = 0; i < rowCount; i++) {
                                  var row = tableID.DataTable().row(i).node();
                                  var chkbox = row.cells[0].childNodes[0];
                                  if (null != chkbox && true == chkbox.checked) {
                                    tableID.dataTable().fnDeleteRow(i); 
                                    rowCount--;
                                  }
                            }*/
                        /*   ckList = document.querySelectorAll("input[type=checkbox]:checked");
                           ckList.forEach(function(el) {
                              el.parentElement.parentElement.remove();
						   document.show;
                               });*/

							
   
  /*   try
        { 
          
            var table = document.getElementById('pentabf');
            var rowCount = table.rows.length ;
            for(var i=1; i<rowCount; i++) 
            {
                var row = table.rows[i];
                var chkbox = row.cells[13].childNodes[0];
                if(null != chkbox && true == chkbox.checked)
                {
                   // table.deleteRow(i);
                    $("tbody tr:eq(" + i + ")").remove();
                    rowCount--;
                    i--;
                }
            }
        }
        catch(e){alert(e);}*/
   
    
						
	                //   } else {
	                //        swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
	                //   }
	                },
	                error: function(rs, e) {
	                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
	                }
	            });
}
	
	
	function submitScannedBagn(){
							var reg =/<(.|\n)*?>/g; 
 if (reg.test($('#rmks').val()) == true) {
        swal('OOPS!', 'Special character Not allowed!!', 'error');
		return false
    }
			var reg =/<(.|\n)*?>/g; 
 if (reg.test($('#articleOverallReport').val()) == true) {
        swal('OOPS!', 'Special character Not allowed!!', 'error');
		return false
    }
			var reg =/<(.|\n)*?>/g; 
 if (reg.test($('#scanrepn').val()) == true) {
        swal('OOPS!', 'Special character Not allowed!!', 'error');
		return false
    }
		var count = document.getElementById('bagarticletablen').rows.length-1;
		 if (count < 1) {
	        swal('OOPS!', 'Please select atleast one confirm arrival !', 'error');
	        return false;
	    }
        if ($("#rmks").val().trim() == '') {
            swal('OOPS!', "Please Enter Departmental Comments which is Mandatory.", 'error');
            return false;
        }
       if ($('#articleOverallReport')!= null && $('#bagno')== null)
		{	 swal('OOPS!', "Please Enter Bag No. which is Mandatory.", 'error');
            return false;
        }
       var proceed=1;
     //  if ($("#articleOverallReport").val().length == 0)
	 //         $('#Modalnoscanrep').modal('show');
     //  else 
     if ($('#bagtype').val()=='B')
            tit = "Are you sure to submit arrival confirmation for selected  Article IDs at this Destination FPO site, " +  $('#sitecode').val() + "?";
        else
            tit="Are you sure to submit arrival confirmation into India for selected  Article IDs?";  
	    swal({
	          title: tit,
	      //  text: "Once Submitted can't be changed !",
	        icon: "warning",
	        buttons: ["No", "Yes"],
	        dangerMode: false,
	    }).then((willDelete) => {
	        if (willDelete) {
           proceedsubmit();}
        });
     } 
     
	function pushdata(e){
	/*	var sno=e;
		var pagelen= $('#pentabf').DataTable().page.info().length;
		var row=sno%pagelen;
			 var articleId = $('#pentabf').find('tr').eq(row).find('td:eq(1)').text();
					var bagno = $('#pentabf').find('tr').eq(row).find('td:eq(12)').text();
	                var site =$('#pentabf').find('tr').eq(row).find('td:eq(5)').text();
	                    var obj = {};
						obj["articleId"] =articleId;
						obj["precpid"]= $('#pentabf').find('tr').eq(row).find('td:eq(8)').text();
						obj["arecpid"]= $('#pentabf').find('tr').eq(row).find('td:eq(9)').text();
	                    obj["bagNo"] = bagno;
	               //     obj["bagType"] = bagNameType.substring(0, 1);
	                    obj["cussite"] = site;
        		if ($('#pentabf').find('tr').eq(row).find("input#chkboxnfarr").is(":checked"))
	                    bagArticlesArray.push(obj);			    
                else
                       {var index = bagArticlesArray.indexOf(articleId);
						if (index !== -1) {
                           bagArticlesArray.splice(index, 1);
 bagArticlesArray.splice(bagArticlesArray.indexOf(articleId),1);	}}*/
	}
	
var scanrepn= [];
	
	function chscanartrep(index,e)
	{     		
	  scanrepn[index] = $('#bagarticletablen').find('tr').eq(index).find('input#scanrep').text();
	}
	
	function editScanRepnf() {
		tableID =$("#pentabf");
		var bagtype=$('#bagtype').val();
		var fpo=$('#fpo').val();
		var cntry=$('#cntry').val();
		if ($('#bagtype').val()==null || $('#bagtype').val()==''){
			swal('OOPS!', 'Please Choose RID / Bag !', 'error');
	        return false;
		}
		/*if (bagtype=='R' && (cntry==null || cntry=='')){
	        swal('OOPS!', 'Please select from which Country the RID Bag has come!', 'error');
	        return false;
	    }*/
        if (bagtype=='B' && (fpo==null || fpo=='')){
	        swal('OOPS!', 'Please select from which OOE the bag is Transitted!', 'error');
	        return false;
	    }
	    if ($($("#pentabf").dataTable().$('input[type="checkbox"]:checked')).length < 1) {
	        swal('OOPS!', 'Please select atleast one confirm arrival !', 'error');
	        return false;
	    }
        var tit="";
         if ($('#bagtype').val()=='B')
            tit = "Are you sure to submit confirmation for selected  Article IDs which is arrived at this site?";
        else
            tit="Are you sure to submit confirmation for selected  Article IDs which is arrived at India?";  
	    swal({
	          title: tit,	      //  text: "Once Submitted can't be changed !",
	        icon: "warning",
	        buttons: ["No", "Yes"],
	        dangerMode: false,
	    }).then((willDelete) => {
	        if (willDelete) {
	            showLoader();
              /*  bagArticlesArray = [];
                var norecords=$('#pentabf').DataTable().page.info().recordsTotal;
                var pagelen =$('#pentabf').DataTable().page.info().length;
				for (var row = 1; row < norecords; row++) {
					   var pageno=Math.floor(row/pagelen);      			
               if ($('#pentabf').find('page').eq(pageno).find('tr').eq(row).find("input#chkboxnfarr").is(":checked"))
	             {
                    var articleId = $('#pentabf').find('page').eq(pageno).find('tr').eq(row).find('td:eq(1)').text();
					var bagno = $('#pentabf').find('page').eq(pageno).find('tr').eq(row).find('td:eq(12)').text();
	                var site =$('#pentabf').find('page').eq(pageno).find('tr').eq(row).find('td:eq(5)').text();
	                    var obj = {};
						obj["articleId"] =articleId;
						obj["precpid"]= $('#pentabf').find('page').eq(pageno).find('tr').eq(row).find('td:eq(8)').text();
						obj["arecpid"]= $('#pentabf').find('page').eq(pageno).find('tr').eq(row).find('td:eq(9)').text();
	                    obj["bagNo"] = bagno;
	               //     obj["bagType"] = bagNameType.substring(0, 1);
	                    obj["cussite"] = site;
	                    bagArticlesArray.push(obj);
						}
	                }*/
					 bagArticlesArray=[];
                    $($("#pentabf").dataTable().$('input[type="checkbox"]:checked').map(function() {
	                var articleId = $(this).closest('tr').find('td:nth-child(2)').text().trim();
	                 articleId = $(this).closest('tr').find('td:eq(1)').text().trim();
					var bagno = $(this).closest('tr').find('td:eq(12)').text();
					var site = $(this).closest('tr').find('td:eq(5)').text();
	                if (!($(this).css('pointer-events') == 'none')) {
	                    var obj = {};
	                    obj["articleId"] =articleId;  
	                    obj["bagNo"] = bagno;
                        obj["precpid"]=$(this).closest('tr').find('td:eq(8)').text();
						obj["arecpid"]=$(this).closest('tr').find('td:eq(9)').text();
						obj["bagNo"] = bagno;
						obj["cussite"] = site;
	                  //  obj["site"] = site;
	                    bagArticlesArray.push(obj);
	                }
	            }));
	                   var table = document.getElementById('bagarticletablen');
                       var rowCount = table.rows.length - 1;
     				   var i=0;
     				   while ( i < rowCount )
   								 {
     								 document.getElementById("bagarticletablen").deleteRow(1);
      								 i=i+1;}
	                        
						$('#bagarticletablen').dataTable().fnDestroy();
						$("#editnotarrdet").empty("");
						var sno=0;
						$.each(bagArticlesArray, function(index, value) {
							sno=sno+1;
						 var iterator = index+1;
					     var markup = "<tr   style='valign:middle;' class='rowDis'><input type='hidden' id='itemid' value='" + value.articleId + "'><td  width='1%' align='center' class= 'font-weight-medium'>" + sno + "</td><td  width='4%''>" + value.articleId + "</td><td width='4%'>" + value.cussite + "</td><td width='4%'>" + value.precpid + "</td>";
                        markup=markup + value.precpid + "</td>";
					    markup=markup+"<td><textarea type='textbox' id='scanrepn' name='scanrepn'  rows=2 style='width: 100%;' placeholder='ENTER SCAN REPORT'></textarea></td></tr>";      
                        $("#editnotarrdet").append(markup);
						});
	             
     	        hideLoader();
                if ($('#bagtype').val()=='B')
                   {
						$('#recpiddisp').hide();
						$('#arecpid').hide();
						$('#bagnodisp').show();
						$('#bagno').show();
                       }
                else if ($('#bagtype').val()=='R')
                   {
						$('#recpiddisp').show();
						$('#arecpid').show();
						$('#bagnodisp').hide();
						$('#bagno').hide();
                       }
                $("#bagArticleModaln").modal('show');
	        }
	    });
	}
	
	
	
	
	var today = new Date();
var startdate = new Date();
startdate.setMonth(startdate.getMonth() - 1);

$("#fromdatepicker").datepicker({
  dateFormat: "dd/mm/yy",
  defaultDate: startdate,
  maxDate: new Date(),
  onSelect: function(date) {
    $("#todatepicker").datepicker('option', 'minDate', date);
    var selectedDate = $(this).datepicker('getDate'); // Get selected date from #fromdatepicker
    if (selectedDate != null) {
      // Set maxDate option of #todatepicker to today's date
      $("#todatepicker").datepicker('option', 'maxDate', new Date());
      $("#todatepicker").datepicker('option', 'beforeShowDay', function(date) {
        return [true]; // Enable all dates
      });
    } else {
      // If no #fromdatepicker date is selected, reset #todatepicker selection and disable all dates except the current date
      $("#todatepicker").datepicker('setDate', null);
      $("#todatepicker").datepicker('option', 'beforeShowDay', function(date) {
        var currentDate = new Date();
        var isCurrentDate = date.toDateString() === currentDate.toDateString();
        return [isCurrentDate]; // Disable all dates except the current date
      });
    }
  }
});

$("#todatepicker").datepicker({
  dateFormat: "dd/mm/yy",
  maxDate: null, // Remove maxDate restriction initially
  defaultDate: today,
  beforeShowDay: function(date) {
    var fromDate = $("#fromdatepicker").datepicker('getDate'); // Get selected date from #fromdatepicker
    if (fromDate != null) {
      // If #fromdatepicker date is selected, enable dates within the range
      var toDate = $("#todatepicker").datepicker('getDate');
      return [date >= fromDate && date <= toDate];
    }
    // If no #fromdatepicker date is selected, disable all dates except the current date
    var currentDate = new Date();
    var isCurrentDate = date.toDateString() === currentDate.toDateString();
    return [isCurrentDate];
  }
});

$("#fromdatepicker").datepicker('setDate', startdate);
$("#todatepicker").datepicker('setDate', today);
$("#todatepicker").datepicker('option', 'beforeShowDay', function(date) {
  var currentDate = new Date();
  var isCurrentDate = date.toDateString() === currentDate.toDateString();
  return [isCurrentDate]; // Disable all dates except the current date initially
});

	
	function getScannedReportHistory(){
		if($("#fromdatepicker").val()=='') {
			swal('OOPS!', "Please Select From Date Range", 'error');
			return false;
		}
		if($("#todatepicker").val()=='') {
			swal('OOPS!', "Please Select To Date Range", 'error');
			return false;	
		}
		
		showLoader();
 		
 		$.ajax({ 
		url: "getarrivalhistory?fromDate="+$("#fromdatepicker").val()+'&toDate='+$("#todatepicker").val() ,
 			type : "get",
		
 
 			success: function(data){
	hideLoader();
	/*var confirmnHistorytable = document.getElementById("confirmnHistorytable");*/
		$('#confirmHistorytable').dataTable().fnDestroy();
		$("#getArivalHistryBody").empty("");

    let html = '';
    for (let index = 0; index < data.length; index++) {
      html += `<tr>                
<td>${index + 1}</td>
 <td><span style="${data[index][1] == 'Y' ? 'color: #bb0e0e;font-weight:bold;' : 'color: blue;font-weight:bold;'}">
   ${data[index][1] == 'Y' ? 'BAG-' : 'RID-'}
</span><span>${data[index][0]}</span></td>

<td>${data[index][2] !== null ? data[index][2] : '-'}</td>
<td>${(data[index][3] === null || data[index][3] === 0) ? data[index][4] : data[index][3]}</td>
<td>${data[index][4]}</td>
<td>${data[index][5]=== null || data[index][5] === 0 ? '-' : data[index][5]}</td>
<td>${data[index][6] === null || data[index][6] === 0 ? '-' : data[index][6]}</td>
<td>${data[index][7] === 0 ? '-' : data[index][7]}</td>
<td><i style="cursor:pointer;" type='button' bagflag=${data[index][1]} id=${data[index][0]} view='Y' onclick="viewArticles(this)" class="fa fa-eye"></i></td>
<td>${data[index][9]}
	</td>
      </tr>`;
 
    }
 $("#getArivalHistryBody").html(html); 
  /*  bagArticlesArray = [];*/
							$('#confirmHistorytable').show();							
							$('#getArivalHistryBody').show();
  $("#confirmHistorytable").dataTable({
		"processing": true,
	    "ordering": false,
		//"paging": true,	
    	//"searching": false,	
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'  Confirmation History For A ',
					message:' Confirmation History For A @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','30%','15%','6%','6%','10%','8%','8%','8%','8%','8%','8%','10%','30%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Confirmation History For A @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Confirmation History For A ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' Confirmation History For A @ ' + sitecode + '\n\r' + datetime ,
					filename:'Confirmation History For A',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],



               });  
  

  
 			},
 			error: function(re, e){
 			hideLoader();	
			swal('OOPS!', 'Something', 'error');
 			}
 		 });
	
	}/*function showConfirmArrivalHistory(){
		
			confirmHistoryTable.ajax.url('getarrivalhistory'.load());
	}*/
	
	 /*function showConfirmArrivalnHistory(){
		confirmHistoryTable.ajax.url('confirmationHistotyB'.load());
		console.log('confirmationHistotyB')
	}*/
	
	//santhosh
	function resetScannedReportHistory(eve){
	
var fromdt = eve.id;
var todt = eve.value;

// Convert 'fromdt' to the desired format
var fromParts = fromdt.split('-');
var fromDay = fromParts[0];
var fromMonth = fromParts[1];
var fromYear = fromParts[2];

var fromDate = new Date(fromMonth + ' ' + fromDay + ', ' + fromYear);
var formattedFromDate = fromDate.getDate() + '/' + (fromDate.getMonth() + 1) + '/' + fromDate.getFullYear();

// Convert 'todt' to the desired format
var toParts = todt.split('-');
var toDay = toParts[0];
var toMonth = toParts[1];
var toYear = toParts[2];

var toDate = new Date(toMonth + ' ' + toDay + ', ' + toYear);
var formattedToDate = toDate.getDate() + '/' + (toDate.getMonth() + 1) + '/' + toDate.getFullYear();

console.log("Required dates during reset are " + formattedFromDate + " and " + formattedToDate);

$("#fromdatepicker").val(formattedFromDate);
$("#todatepicker").val(formattedToDate);
 		showLoader();
 		$.ajax({ 
		url: "getarrivalhistory?fromDate="+fromdt+'&toDate='+todt ,
 			type : "get",
		
 
 			success: function(data){
hideLoader();
	/*var confirmnHistorytable = document.getElementById("confirmnHistorytable");*/
		$('#confirmHistorytable').dataTable().fnDestroy();
		$("#getArivalHistryBody").empty("");

    let html = '';
    for (let index = 0; index < data.length; index++) {
      html += `<tr>                
<td>${index + 1}</td>
 <td><span style="${data[index][1] == 'Y' ? 'color: #bb0e0e;font-weight:bold;' : 'color: blue;font-weight:bold;'}">
   ${data[index][1] == 'Y' ? 'BAG-' : 'RID-'}
</span><span>${data[index][0]}</span></td>

<td>${data[index][2] !== null ? data[index][2] : '-'}</td>
<td>${(data[index][3] === null || data[index][3] === 0) ? data[index][4] : data[index][3]}</td>
<td>${data[index][4]}</td>
<td>${data[index][5]=== null || data[index][5] === 0 ? '-' : data[index][5]}</td>
<td>${data[index][6] === null || data[index][6] === 0 ? '-' : data[index][6]}</td>
<td>${data[index][7] === 0 ? '-' : data[index][7]}</td>
<td><i style="cursor:pointer;" type='button' bagflag=${data[index][1]} view='Y' id=${data[index][0]} onclick="viewArticles(this)" class="fa fa-eye"></i></td>
<td>${data[index][9]}
	</td>
      </tr>`;
 
    }
 $("#getArivalHistryBody").html(html); 
  /*  bagArticlesArray = [];*/
							$('#confirmHistorytable').show();							
							$('#getArivalHistryBody').show();
  $("#confirmHistorytable").dataTable({
		"processing": true,
	    "ordering": false,
		//"paging": true,	
    	//"searching": false,	
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'  Confirmation History For A ',
					message:' Confirmation History For A @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','30%','15%','6%','6%','10%','8%','8%','8%','8%','8%','8%','10%','30%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Confirmation History For A @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Confirmation History For A ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' Confirmation History For A @ ' + sitecode + '\n\r' + datetime ,
					filename:'Confirmation History For A',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],



               });  
  

  
 			},
 			error: function(re, e){
 		hideLoader();	
			swal('OOPS!', 'Something', 'error');
 			}
 		 });
	
	}


	

	function showLoader() {
	    $("#overlay").css("display", "block");
	}
	
	function hideLoader() {
	    setTimeout(function() {
	        $("#overlay").css("display", "none");
	    }, 500);
	}
	
	function deleteScanReport(obj) {
	    swal({
	        title: "Are you sure to delete the Entered Scan Report for checked Recpt. / Bag ?",
	        icon: "warning",
	        buttons: ["No", "Yes"],
	        dangerMode: false,
	    }).then((willDelete) => {
	        if (willDelete) {
	            $(obj).closest('tr').find(':last-child input').removeProp('checked');
	            $(obj).closest('tr').find(':last-child input').css({
	                'pointer-events': ''
	            });
	            var clickedBagNo = $(obj).closest('tr').find('td:nth-child(2) span:last').html();
	            var clickedBagType = $(obj).closest('tr').find('td:nth-child(2) span').html().substring(0, 3);
	            var clickedBagFlag = clickedBagType === 'RID' ? 'N' : 'Y';
	            bagArticlesArray = bagArticlesArray.filter(a => a.bagNo === clickedBagNo ? false : true);
	            $(obj).closest('tr').find('td:nth-child(9)').html('<input class="btn-grn" type="button" bagflag="' + clickedBagFlag + '" id="' + clickedBagNo + '" onclick="viewArticles(this)" value="Click">');
	        }
	    });
	}
	
var today = new Date();
var startdate = new Date();
startdate.setMonth(startdate.getMonth() - 1);

$("#fromdatepick").datepicker({
  dateFormat: "dd/mm/yy",
  defaultDate: startdate,
  maxDate: new Date(),
  onSelect: function(date) {
    $("#todatepick").datepicker('option', 'minDate', date);
    var selectedDate = $(this).datepicker('getDate'); // Get selected date from #fromdatepicker
    if (selectedDate != null) {
      // Set maxDate option of #todatepicker to today's date
      $("#todatepick").datepicker('option', 'maxDate', new Date());
      $("#todatepick").datepicker('option', 'beforeShowDay', function(date) {
        return [true]; // Enable all dates
      });
    } else {
      // If no #fromdatepicker date is selected, reset #todatepicker selection and disable all dates except the current date
      $("#todatepick").datepicker('setDate', null);
      $("#todatepick").datepicker('option', 'beforeShowDay', function(date) {
        var currentDate = new Date();
        var isCurrentDate = date.toDateString() === currentDate.toDateString();
        return [isCurrentDate]; // Disable all dates except the current date
      });
    }
  }
});

$("#todatepick").datepicker({
  dateFormat: "dd/mm/yy",
  maxDate: null, // Remove maxDate restriction initially
  defaultDate: today,
  beforeShowDay: function(date) {
    var fromDate = $("#fromdatepick").datepicker('getDate'); // Get selected date from #fromdatepicker
    if (fromDate != null) {
      // If #fromdatepicker date is selected, enable dates within the range
      var toDate = $("#todatepick").datepicker('getDate');
      return [date >= fromDate && date <= toDate];
    }
    // If no #fromdatepicker date is selected, disable all dates except the current date
    var currentDate = new Date();
    var isCurrentDate = date.toDateString() === currentDate.toDateString();
    return [isCurrentDate];
  }
});

$("#fromdatepick").datepicker('setDate', startdate);
$("#todatepick").datepicker('setDate', today);
$("#todatepick").datepicker('option', 'beforeShowDay', function(date) {
  var currentDate = new Date();
  var isCurrentDate = date.toDateString() === currentDate.toDateString();
  return [isCurrentDate]; // Disable all dates except the current date initially
});

	function getConfirmReportHistory(){
		if($("#fromdatepick").val()=='') {
			swal('OOPS!', "Please Select From Date Range", 'error');
			return false;
		}
		if($("#todatepick").val()=='') {
			swal('OOPS!', "Please Select To Date Range", 'error');
			return false;	
		}
/*		confirmnHistorytable.ajax.url('bagscan?fromDate='+$("#fromdatepick").val()+'&toDate='+$("#todatepick").val()).load();*/
		showLoader();
 		$.ajax({ 
		url: "confirmationHistotyB?fromDate="+$("#fromdatepick").val()+'&toDate='+$("#todatepick").val() ,
 			type : "get",
		
 success: function(data){
	hideLoader();
		$('#confirmnHistorytable').dataTable().fnDestroy();
		$("#getConfirmBo").empty("");
		let html = '';
 for (let index = 0; index < data.length; index++) {
      html += `<tr>
 <td>${index + 1}</td>
<td style="width: 20%;" class="ta-left">
          <span style="${data[index].bagType === 'R' ? 'color:blue;font-weight:bold;' : 'color: #bb0e0e;font-weight:bold;'}">
            ${data[index].bagType === 'R' ? 'RID-' : 'BAG-'}
          </span>
          <span>${data[index].bagNo}</span>
        </td>
        <td>${data[index].recd_dt != null ? data[index].recd_dt : '-'}</td>
 
        <td>${data[index].totalCount == null || data[index].totalCount == 0 ? data[index].withEadCurrSite : data[index].totalCount}</td>
        <td>${data[index].withEadCurrSite}</td>
        <td>${data[index].withEadOtherSite== null || data[index].withEadOtherSite == 0 ? '-' : data[index].withEadOtherSite}</td>
        <td>${data[index].withoutEad== null || data[index].withoutEad == 0 ? '-' : data[index].withoutEad}</td>
        <td>${data[index].withEadSiteNotAllot != 0 ? data[index].withEadSiteNotAllot : '-'}</td>
        <td>
          <i style="cursor:pointer;" type='button' id="${data[index].bagNo}" onclick="viewArticleHistry(this)" class="fa fa-eye" value='Click' >
          </i>
        </td>
        <td>${data[index].scan_dt}</td>
      </tr>`;
 
    }
 			/*success: function(data){
	
		$('#confirmnHistorytable').dataTable().fnDestroy();
		$("#getConfirmBo").empty("");
		let html = '';
 for (let index = 0; index < data.length; index++) {
      html += `<tr>
 <td>${index + 1}</td>
<td style="width: 20%;" class="ta-left">
          <span style="${data[index][1] === 'R' ? 'color:blue;font-weight:bold;' : 'color: #bb0e0e;font-weight:bold;'}">
            ${data[index][1] === 'R' ? 'RID-' : 'BAG-'}
          </span>
          <span>${data[index][0]}</span>
        </td>
        <td>${data[index][2] != null ? data[index][2] : '-'}</td>
        <td>${data[index][4] === null || data[index][4] === 0 ? data[index][5] : data[index][4]}</td>
        <td>${data[index][5]}</td>
        <td>${data[index][6]=== null || data[index][6] === 0 ? '-' : data[index][6]}</td>
        <td>${data[index][7]=== null || data[index][7] === 0 ? '-' : data[index][7]}</td>
        <td>${data[index][8] != 0 ? data[index][8] : '-'}</td>
        <td>
          <i style="cursor:pointer;" type='button' id="${data[index][0]}" onclick="viewArticleHistry(this)" class="fa fa-eye" value='Click' >
          </i>
        </td>
        <td>${data[index][3]}</td>
      </tr>`;
 
    }*/
 $("#getConfirmBo").html(html); 
    bagArticlesArray = [];
							$('#confirmnHistorytable').show();							
							$('#getConfirmBo').show();
  $("#confirmnHistorytable").dataTable({
		"processing": true,
	    "ordering": false,
		//"paging": true,	
    	//"searching": false,	
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'  Confirmation History For B ',
					message:' Confirmation History For B @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','30%','15%','6%','6%','10%','8%','8%','8%','8%','8%','8%','10%','30%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Confirmation History For B @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Confirmation History For B ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' Confirmation History For B @ ' + sitecode + '\n\r' + datetime ,
					filename:'Confirmation History For B',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],

"initComplete": function(settings) {
  $("#confirmnHistorytable_filter").append('<button type="button" class="btn btn-info" id="clearing" ' +
    'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
  $("#confirmnHistorytable_filter input[type='search']").attr('id', 'searchconfirmnHistorytable');
  
  $('#confirmnHistorytable_filter').on('click', '#clearing', function() {
    var table = $('#confirmnHistorytable').DataTable();
    table.search('').draw();
    $("#searchconfirmnHistorytable").val(""); // Clear the search input field
  });
}

               });  
  

  
 			},
 			error: function(re, e){
	hideLoader();
 				swal('OOPS!', 'Something', 'error');
 			}
 		 });
	
	}
	
	 /*function showConfirmArrivalnHistory(){
		confirmHistoryTable.ajax.url('confirmationHistotyB'.load());
		console.log('confirmationHistotyB')
	}*/
	//santhosh
	function resetConfirmReportHistory(eve){
		
var fromdt = eve.id;
var todt = eve.value;

// Convert 'fromdt' to the desired format
var fromParts = fromdt.split('-');
var fromDay = fromParts[0];
var fromMonth = fromParts[1];
var fromYear = fromParts[2];

var fromDate = new Date(fromMonth + ' ' + fromDay + ', ' + fromYear);
var formattedFromDate = fromDate.getDate() + '/' + (fromDate.getMonth() + 1) + '/' + fromDate.getFullYear();

// Convert 'todt' to the desired format
var toParts = todt.split('-');
var toDay = toParts[0];
var toMonth = toParts[1];
var toYear = toParts[2];

var toDate = new Date(toMonth + ' ' + toDay + ', ' + toYear);
var formattedToDate = toDate.getDate() + '/' + (toDate.getMonth() + 1) + '/' + toDate.getFullYear();

console.log("Required dates during reset are " + formattedFromDate + " and " + formattedToDate);

$("#fromdatepick").val(formattedFromDate);
$("#todatepick").val(formattedToDate);
 	showLoader();	
	$.ajax({ 
		url: 'confirmationHistotyB?fromDate='+fromdt+'&toDate='+todt ,
 			type : "get",
		
 success: function(data){
	hideLoader();
		$('#confirmnHistorytable').dataTable().fnDestroy();
		$("#getConfirmBo").empty("");
		let html = '';
 for (let index = 0; index < data.length; index++) {
      html += `<tr>
 <td>${index + 1}</td>
<td style="width: 20%;" class="ta-left">
          <span style="${data[index].bagType === 'R' ? 'color:blue;font-weight:bold;' : 'color: #bb0e0e;font-weight:bold;'}">
            ${data[index].bagType === 'R' ? 'RID-' : 'BAG-'}
          </span>
          <span>${data[index].bagNo}</span>
        </td>
        <td>${data[index].recd_dt != null ? data[index].recd_dt : '-'}</td>
 
        <td>${data[index].totalCount == null || data[index].totalCount == 0 ? data[index].withEadCurrSite : data[index].totalCount}</td>
        <td>${data[index].withEadCurrSite}</td>
        <td>${data[index].withEadOtherSite== null || data[index].withEadOtherSite == 0 ? '-' : data[index].withEadOtherSite}</td>
        <td>${data[index].withoutEad== null || data[index].withoutEad == 0 ? '-' : data[index].withoutEad}</td>
        <td>${data[index].withEadSiteNotAllot != 0 ? data[index].withEadSiteNotAllot : '-'}</td>
        <td>
          <i style="cursor:pointer;" type='button' id="${data[index].bagNo}" onclick="viewArticleHistry(this)" class="fa fa-eye" value='Click' >
          </i>
        </td>
        <td>${data[index].scan_dt}</td>
      </tr>`;
 
    }
 $("#getConfirmBo").html(html); 
    bagArticlesArray = [];
							$('#confirmnHistorytable').show();							
							$('#getConfirmBo').show();
  $("#confirmnHistorytable").dataTable({
		"processing": true,
	    "ordering": false,
		//"paging": true,	
    	//"searching": false,	
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'  Confirmation History For B ',
					message:' Confirmation History For B @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','30%','15%','6%','6%','10%','8%','8%','8%','8%','8%','8%','10%','30%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Confirmation History For B @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Confirmation History For B ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' Confirmation History For B @ ' + sitecode + '\n\r' + datetime ,
					filename:'Confirmation History For B',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],



               });  
  

  
 			},
 			error: function(re, e){
 		hideLoader();	
			swal('OOPS!', 'Something', 'error');
 			}
 		 });
	
	}

	
	function editScanReport(obj) {
	    swal({
	        title: "Are you sure to edit the Entered Scan Report for checked Recpt. / Bag ?",
	        icon: "warning",
	        buttons: ["No", "Yes"],
	        dangerMode: false,
	    }).then((willDelete) => {
	        if (willDelete) {
	            var clickedBagNo = $(obj).closest('tr').find('td:nth-child(2) span:last').html();
	            var clickedBagType = $(obj).closest('tr').find('td:nth-child(2) span').html().substring(0, 3);
	            var clickedBagFlag = clickedBagType === 'RID' ? 'N' : 'Y';
	            $(obj).attr('bagflag',clickedBagFlag);
	            $(obj).attr('id',clickedBagNo);
	            viewArticles(obj);
	            var submittedTextReports=bagArticlesArray.filter(a => a.bagNo === clickedBagNo && a.articleId==undefined);
	            var submittedScanReports=bagArticlesArray.filter(a => a.bagNo === clickedBagNo && a.articleId!=undefined);
	            if(submittedTextReports.length==1 && submittedTextReports[0].articleId==undefined){
	            	$("#articleOverallReport").val(submittedTextReports[0].scanReport);
	            }
	            	$($("#bagarticletable").dataTable().$('input[type="checkbox"]').map(function() {
	                	var matchedArticle=submittedScanReports.filter(a => a.articleId === $(this).closest('tr').find('td:nth-child(3)').text());
	                	if(matchedArticle.length>0){
	                		$(this).closest('tr').find('td:nth-child(1) input').prop("checked","checked");
	                		$(this).closest('tr').find(':last-child textarea').removeProp('disabled');
	                		$(this).closest('tr').find(':last-child textarea').val(matchedArticle[0].scanReport);
	                	}
	            	}));
	       }
	    });
	}
	
	
	 function submitArticleReport() {
	 var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#articleOverallReport').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
	 
        var bagNo = $("#bagNoDetails").val().substring(4);
        var bagType = $("#bagNoDetails").val().substring(0, 1);
        var site = $("#bagNoDetails").attr("site");
        if ($(bagArticleTable.$('input[type="checkbox"]:checked')).length < 1 && $("#articleOverallReport").val().trim() == '') {
            swal('OOPS!', "Please Enter Scan Findings / Report for the Recpt.Id / Bag (or) Scan Report must selected / Entered", 'error');
            return false;
        }
        var checkedReport=$(bagArticleTable.$('input[type="checkbox"]:checked').filter(function() { return $(this).closest('tr').find(':last-child textarea').val()==''}));
        if(checkedReport.length>0){
        	swal('OOPS!', "Please Enter Scan Findings / Report for the selected articles", 'error');
        	return false;
        }
	    bagArticlesArray = bagArticlesArray.filter(a => a.bagNo === bagNo ? false : true);
	    if($("#articleOverallReport").val().trim() != ''){
		    var obj = {};
	        obj["bagNo"] = bagNo;
	        obj["bagType"] = bagType;
	        obj["scanReport"] = $("#articleOverallReport").val().trim();
	        obj["site"] = site;
	        obj["scanned"]="A";
	        bagArticlesArray.push(obj);
	    }
        $(bagArticleTable.$('input[type="checkbox"]:checked').map(function() {
            var obj = {};
            obj["bagNo"] = bagNo;
            obj["bagType"] = bagType;
            obj["articleId"] = $(this).closest('tr').find('td:nth-child(3)').text();
            obj["scanReport"] = $(this).closest('tr').find(':last-child textarea').val();
	        obj["site"] = site;
	        obj["scanned"]="A";
            bagArticlesArray.push(obj);
        }));
        $("#" + bagNo).closest('tr').find(':last-child input').prop('checked', 'checked');
        $("#" + bagNo).closest('tr').find(':last-child input').css('pointer-events', 'none');
        $("#" + bagNo).closest('tr').find('td:nth-child(9)').html('<i class="fa fa-pencil-square-o" onclick="editScanReport(this)" aria-hidden="true" style="cursor: pointer;"></i><i class="fa fa-trash" onclick="deleteScanReport(this)" aria-hidden="true" style="margin-left: 25px; cursor: pointer;"></i>');
        bodyScroll();
        $("#bagArticleModal").modal('hide');
    }
	
	function viewArticles(obj) {
	    showLoader();
	    $.ajax({
	        url: "bagarticles?bagNoOrRecpId=" + $(obj).attr('id') + "&bagFlag=" + $(obj).attr('bagflag')+ "&view=" + $(obj).attr('view'),
	        type: "get",
	        async: false,
	        success: function(data) {
	            var bagNoDetails = ($(obj).attr('bagflag') == 'Y' ? 'BAG-' : 'RID-') + $(obj).attr('id');
	            if($(obj).attr('view')=='Y'){
	            	$("#bagArticlesModalTitle").text("Article IDs of " + bagNoDetails);
	            }else{
	            	$("#bagArticlesModalTitle").text("Select Article IDs of " + bagNoDetails + " to enter scan findings / report");
	            }
	            $('#bagArticlesContent').html(data);
	            $("#bagNoDetails").val(bagNoDetails);
	            $("#bagNoDetails").attr("site",$(obj).attr('site'));
	            if ($(obj).attr('bagflag') == 'Y') {
	                $("#bagArticlesModalTitle").html($("#bagArticlesModalTitle").html().replace(bagNoDetails, '<span style="color: #bb0e0e">' + bagNoDetails + '</span>'));
	            } else {
	                $("#bagArticlesModalTitle").html($("#bagArticlesModalTitle").html().replace(bagNoDetails, '<span style="color: blue">' + bagNoDetails + '</span>'));
	            }
	            $("#bagArticleModal").modal('show');
	            $("body").css('overflow-y', 'hidden');
	            hideLoader();
	        },
	        error: function(rs, e) {
	            swal('OOPS!', "Something went wrong", 'error');
	            hideLoader();
	        }
	    });
	}
	
	
	function viewSummary(e) {
	    var developerData = {};
        var currow=e;
        var pagelen; 
        var row;
       
/*if (tabnm='pentabb')
   {    pagelen=document.getElementById('pentabb').rows.length;
            row=currow % pagelen;
       if (row==0)
           row=row+pagelen;
     //  var cinno= $('#doctab').find('tr').eq(row).find("input#cinno").val();
       var itmid= null;
      
           itmid = $('#pentabb').find('tr').eq(row).find("input#itemid").val();
            $('#pentabb').find('tr').eq(row).css('background-color','green');
            $('#pentabb').find('tr').eq(row).css('font-size','18');
            $('#pentabb').find('tr').eq(row).css('color','yellow');
            $('#pentabb').find('tr').eq(row).css('border','darkgreen');}
else if (tabnm='pentabo')
   {    pagelen=document.getElementById('pentabo').rows.length;
            row=currow % pagelen;
       if (row==0)
           row=row+pagelen;
     //  var cinno= $('#doctab').find('tr').eq(row).find("input#cinno").val();
       var itmid= null;
      
           itmid = $('#pentabo').find('tr').eq(row).find("input#itemid").val();
            $('#pentabo').find('tr').eq(row).css('background-color','green');
            $('#pentabo').find('tr').eq(row).css('font-size','18');
            $('#pentabo').find('tr').eq(row).css('color','yellow');
            $('#pentabo').find('tr').eq(row).css('border','darkgreen');}
else if  (tabnm='pentabf')*/
   {    //pagelen=document.getElementById('pentabf').rows.length-1;
        var totpages = $('#pentabf').DataTable().page.info().pages;
        pagelen = $('#pentabf').DataTable().page.info().length; 
        var norecords=$('#pentabf').DataTable().page.info().recordsTotal;
        row=currow % pagelen;
        if (row==0)
           row=pagelen;
     //  var cinno= $('#doctab').find('tr').eq(row).find("input#cinno").val();
       var itmid= null;
      
           itmid = $('#pentabf').find('tr').find("input#itemid").val();
            $('#pentabf').find('tr').eq(row).css('background-color','green');
            $('#pentabf').find('tr').eq(row).css('font-size','18');
            $('#pentabf').find('tr').eq(row).css('color','yellow');
            $('#pentabf').find('tr').eq(row).css('border','darkgreen');}
		
	    $.ajax({
                url: 'getSummary?id=' + itmid,
                type: "post",
                success: function(data) {
					$("#summaryModal").modal('show');
					$("#summaryModalTitle").text('ARTICLE SUMMARY - '+itmid);
                    $("#summaryModalBody").html(data);
                    //$('#doctab').modal('show');
                },
                error: function(rs, e) {
                    alert("Error in nextPageRedirect");
                }
            });
      };





function closeSummaryModal(){
 
$("#pentabf tbody tr").each(function() {
 var textval = $(this).find("td:eq(0)").text().trim();
 $(this).css('font-size','14');
 if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');	
		 }
		 else
		 {
		   $(this).css('color','#130101');
		   $(this).css('background-color','#e2ecf7');
		 }	
  });




$("#summaryModal").modal('hide');
}


function closebagarticlesnModal(){
	
	$('#bagArticleModaln').modal('hide');
}

 /*$("select.cntry").change(function(){
	getfiltdata();
});

$("select.mc").change(function(){
	getfiltdata();
});*/


$("select.bagtype").change(function(){
	var bagtype=document.getElementById("bagtype").value;
	
	if (bagtype=='B')
	{   $('#cntrysel').hide();
	    $('#fposel').show();
        $('#tosite').show();
        $('#sitetype').text("DESTN. FPO");
        $('#recpidnote').text('');
    //    resetfiltdata();
       $('#penscanf').hide();
       $('#notedispbag').text(' Note : "-" in DESTN.FPO (Destination FPO) column as against EAD Article IDs indicates that those are not assigned to any FPO site for want of PINCODE. However, on submission of Arrival Confirmation here, those Article IDs will be mapped to this site.');
$('#notedisprid').text("");	  
$('#notedisprid1').text("");	  
$('#pentabf').hide();
       }
    else if (bagtype=='R')
    {   $('#cntrysel').show();
        $('#fposel').hide();
        $('#tosite').hide();
        $('#sitetype').text("ASSESSMENT SITE*");
       $('#notedispbag').text("");
        $('#recpidnote').text("(Pls check the PREDES RID, which has to be same as RECEPTACLE ID mostly.)");
    //   resetfiltdata();
      $('#penscanf').hide();
$('#notedispbag').text("");
$('#notedisprid').text('Note : "-" in Assessment Site column as against EAD Article IDs indicates that those are not assigned to any FPO site for want of PINCODE.');
$('#notedisprid1').text('* - The Assessment Site may be different from Destination FPO Site in case of Clustered Sites/FPOs at  Mumbai and Chennai.');
	  $('#pentabf').hide();
}
			
});


$('#bagno').keyup(function() {
$(this).val($(this).val().toUpperCase());
	var doc1 = $("#bagno").val() ;
	var len=doc1.length;
	var char_bag  = doc1.substr(0,3);
	var let_Sc  =doc1.substr(3,9);
	var let_On = doc1.substr(12, 1);
				if (len == 1)
				{
					this.value = this.value.replace(/[^A-Z]/g, '');
				}
			    else if (len == 2){
					this.value = this.value.replace(/[^A-Z]/g, '');
				}
				else if (len == 3){
					this.value = this.value.replace(/[^A-Z]/g, '');
				}
				else if (len == 4){
					this.value = char_bag + this.value.replace(/[^0-9]/g, '');
				}
				else if (len == 5){
					this.value = char_bag + this.value.replace(/[^0-9]/g, '');
				}
				else if (len == 6){
					this.value = char_bag + this.value.replace(/[^0-9]/g, '');
				}
				else if (len == 7){
					this.value = char_bag + this.value.replace(/[^0-9]/g, '');
				}
				else if (len == 8){
					this.value = char_bag + this.value.replace(/[^0-9]/g, '');
				}
				else if (len == 9){
					this.value = char_bag + this.value.replace(/[^0-9]/g, '');
				}
				else if (len == 10){
					this.value = char_bag + this.value.replace(/[^0-9]/g, '');
				}
				else if (len == 11){
					this.value = char_bag + this.value.replace(/[^0-9]/g, '');
				}
				else if (len == 12){
					this.value = char_bag + this.value.replace(/[^0-9]/g, '');
				}
				else if (len == 13){
					if ((let_On >= '0' && let_On <= '9')) {
							$('#rmks').focus();
							this.value = char_bag + let_Sc + let_On ;} 
				     else {
				 		this.value = char_bag + let_Sc;
				        }
				}
});




$('#arecpid').keyup(function() {
$(this).val($(this).val().toUpperCase());
		var docar = $("#arecpid").val() ;
		var len=docar.length;
		var char_Ar  = docar.substr(0, 15);
		var Let_Ar  = docar.substr(15, 13);
		var Let_Ar1  = docar.substr(28, 1);
		
			if (len == 1){
				this.value = this.value.replace(/[^A-Z]/g, '');}
			    else if (len == 2){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 3){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 4){
					this.value = this.value.replace(/[^A-Z]/g, '');}
			    else if (len == 5){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 6){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 7){
					this.value = this.value.replace(/[^A-Z]/g, '');}
			    else if (len == 8){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 9){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 10){
					this.value = this.value.replace(/[^A-Z]/g, '');}
			    else if (len == 11){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 12){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 13){
					this.value = this.value.replace(/[^A-Z]/g, '');}
			    else if (len == 14){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 15){
					this.value = this.value.replace(/[^A-Z]/g, '');}
				else if (len == 16){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 17){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 18){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 19){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 20){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 21){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 22){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 23){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 24){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 25){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 26){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 27){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 28){
					this.value = char_Ar + this.value.replace(/[^0-9]/g, '');}
				else if (len == 29){
					if ((Let_Ar1 >= '0' && Let_Ar1 <= '9')) {
							$('#rmks').focus();
							this.value = char_Ar + Let_Ar + Let_Ar1 ;} 
				     else {
				 		this.value = char_Ar + Let_Ar;
				       }
				}
				
});



var today = new Date();

$("#postfrdt").datepicker({
  dateFormat: "dd/mm/yy",
  maxDate: today,
  onSelect: function(date) {
    $("#posttodt").datepicker('option', 'minDate', date || null);
    $("#posttodt").datepicker('option', 'maxDate', null); // Reset maxDate option
    var selectedDate = $(this).datepicker('getDate'); // Get selected date from #postfrdt
    if (selectedDate != null) {
      // Set maxDate option of #posttodt to today's date
      $("#posttodt").datepicker('option', 'maxDate', today);
    }
  }
});

$("#posttodt").datepicker({
  dateFormat: "dd/mm/yy",
  maxDate: today
});

		
		
							
function resetfiltdata()
{
	$('#penscanf').hide();
/*	$('#penscanooe').hide();
	$('#penscanfpo').show();
	$('#pentabo').hide();	*/
	$('#pentabf').hide();
//	$('#pentabb').show();
	$('#mc').val('');
	$('#bagtype').val('');
	$('#cntry').val('');
	$('#postfrdt').val('');
	$('#posttodt').val('');
}


var currentDate = new Date();
					 var day = currentDate.getDate();
					 var month = currentDate.getMonth() + 1;
					 var year = currentDate.getFullYear();
					 var hour=currentDate.getHours();
					 var min=currentDate.getMinutes();
					 var second=currentDate.getSeconds();
					 var datetime = day + "/" + month + "/" + year + " " + hour + ":" + min +":" + second;
					 var empid = $("#empId").val();
					 var sitecode = $("#sitecode").val();

/*function getfiltdata() {
		var mc=document.getElementById("mc").value;
		var bagtype=document.getElementById("bagtype").value;
		if (bagtype==null || bagtype==''){
	        swal('OOPS!', 'Please select Bag Type !', 'error');
	        return false;
	    }
		var cntry = '';
		if (bagtype=='R')
		  {if  (document.getElementById("cntry").value!=null)
		    cntry=document.getElementById("cntry").value;}
		var fpo= '';
		if (bagtype=='B')
		  {if  (document.getElementById("fpo").value!=null)
		     fpo=document.getElementById("fpo").value;}
		var frdt = document.getElementById("postfrdt").value;
		var todt = document.getElementById("posttodt").value;
      
       /* if (bagtype=='B' && (mc==null || mc=='')){
	        swal('OOPS!', 'Please select MAIL CLASS!', 'error');
	        return false;
	    }
        if (bagtype=='B' && (fpo==null || fpo=='')){
	        swal('OOPS!', 'Please select from which OOE the bag is Transitted!', 'error');
	        return false;
	    }
      /*  if (bagtype=='B' && (fpo==null || fpo=='')){
	        swal('OOPS!', 'Please select from which OOE the articles have come!', 'error');
	        return false;
	    }*/
		
/*		$.ajax({
			//	url: 'getcntrymcfiltdata?cntry=' + cntry + '&fpo=' + fpo + '&mc=' + mc + '&frdt=' + frdt + '&todt=' + todt + '&bagtype=' + bagtype,  
				url: 'getcntrymcfiltdata?cntry=' + cntry + '&mc=' + mc + '&frdt=' + frdt + '&todt=' + todt + '&bagtype=' + bagtype,  
				"beforeSend" : function(){
                	showLoader();  
                 },
				success: function(data) {
					var val1 = data;
						var count = 0;
							var table = document.getElementById('pentabf');
                            var rowCount = table.rows.length - 1;
     						var i=0;
     						while ( i < rowCount )
   								 {
     								 document.getElementById("pentabf").deleteRow(1);
      								 i=i+1;}
	                        
								$('#pentabf').dataTable().fnDestroy();
								 $("#pentabbody").empty("");
						     $.each(val1, function(index, value) {
							   var iterator = index + 1;
				               var ooe,ooedt,precp,arecp,fpo,fpodt,bagno;
                               if (value.cussite == "" || value.cussite == null)
                                  cussite="-";
                               else
                                  cussite=value.cussite;
				               if (value.ooe=="" || value.ooe==null)
                                   ooe='-';
                               else
                                   ooe=value.ooe;
							   if (value.recdeventDt=="" || value.recdeventDt==null)
                                   ooedt='-';
                               else
                                   ooedt=value.recdeventDt;
							   if (value.precpid=="" || value.precpid==null)
                                   precp='-';
                               else
                                   precp=value.precpid;
                               if (value.arecpid=="" || value.arecpid==null)
                                   arecp='-';
                               else
                                   arecp=value.arecpid;
                               if (value.recdfpo=="" || value.recdfpo==null)
                                   fpo='-';
                               else
                                   fpo=value.recdfpo;
							   if (value.recdDt=="" || value.recdDt==null)
                                   fpodt='-';
                               else
                                   fpodt=value.recdDt;
								if (value.bagno=="" || value.bagno==null)
                                   bagno='-';
                               else
                                   bagno=value.bagno;
                                var markup = "<tr   style='valign:middle;' class='rowDis'><input type='hidden' id='itemid' value='" + value.articleId + "'><td  width='1%' align='center' class= 'font-weight-medium'>" + iterator + "</td><td  width='4%' th:id='" + iterator + "'><span onclick=viewSummary('" + iterator + "')> " + value.articleId + "</span></td><td width='4%'>" + value.postingdt + "</td><td width='5%'>" + value.cntryCD + "</td><td width='3%'>" + value.codeDesc + "</td><td width='3%'>" + cussite + "</td><td width='5%'>" + ooe + "</td><td width='5%'>" + ooedt + "</td><td width='5%'>";
							    markup=markup + precp + "</td><td width='5%'>" + arecp + "</td><td width='5%'>" + fpo +  "</td><td width='5%'>" + fpodt +  "</td><td width='5%'>" + bagno +  "</td>"
			                    markup=markup+" <td width='5%' style='color: black;vertical-align: middle;'><input type='checkbox' id='chkboxnfarr' name='chkboxnfarr[]'  onclick='pushdata(" + iterator + ");' style='width:24px;height:24px;' title='Check for Arrival Confrmation'></td></tr>";                              	                    
                                $("#pentabbody").append(markup);
							});
						hideLoader();			
							$('#penscanooe').hide();
							$('#pentabb').hide();
							$('#pentabo').hide();
							$('#penscanfpo').hide();
							 bagArticlesArray = [];
							$('#pentabf').show();
							      
	}*/
	
	
	function getfiltdata() {
		var mc=document.getElementById("mc").value;
		var bagtype=document.getElementById("bagtype").value;
		if (bagtype==null || bagtype==''){
	        swal('OOPS!', 'Please select Bag Type !', 'error');
	        return false;
	    }
		var cntry = '';
		if (bagtype=='R')
		  {if  (document.getElementById("cntry").value!=null)
		    cntry=document.getElementById("cntry").value;}
		var fpo= '';
		if (bagtype=='B')
		  {if  (document.getElementById("fpo").value!=null)
		     fpo=document.getElementById("fpo").value;}
		var frdt = document.getElementById("postfrdt").value;
		var todt = document.getElementById("posttodt").value;
        if (bagtype=='R' && (cntry==null || cntry=='')) {
	        swal('OOPS!', 'Please select from which Country the RID Bag has come!', 'error');
	        return false;
	    }
       /* if (bagtype=='B' && (mc==null || mc=='')){
	        swal('OOPS!', 'Please select MAIL CLASS!', 'error');
	        return false;
	    }*/
        if (bagtype=='B' && (fpo==null || fpo=='')){
	        swal('OOPS!', 'Please select from which OOE the bag is Transitted!', 'error');
	        return false;
	    }
      /*  if (bagtype=='B' && (fpo==null || fpo=='')){
	        swal('OOPS!', 'Please select from which OOE the articles have come!', 'error');
	        return false;
	    }*/
		
		$.ajax({
			//	url: 'getcntrymcfiltdata?cntry=' + cntry + '&fpo=' + fpo + '&mc=' + mc + '&frdt=' + frdt + '&todt=' + todt + '&bagtype=' + bagtype,  
				url: 'getcntrymcfiltdata?cntry=' + cntry + '&mc=' + mc + '&frdt=' + frdt + '&todt=' + todt + '&bagtype=' + bagtype + '&fpo=' + fpo,   
				"beforeSend" : function(){
                	showLoader();  
                 },
				success: function(data) {
					var val1 = data;
						var count = 0;
							var table = document.getElementById('pentabf');
                            var rowCount = table.rows.length - 1;
     						var i=0;
     						while ( i < rowCount )
   								 {
     								 document.getElementById("pentabf").deleteRow(1);
      								 i=i+1;}
	                        
								$('#pentabf').dataTable().fnDestroy();
								 $("#pentabbody").empty("");
						     $.each(val1, function(index, value) {
							   var iterator = index + 1;
				               var ooe,ooedt,precp,arecp,fpo,fpodt,bagno;
				               if (value.ooe=="" || value.ooe==null)
                                   ooe='-';
                               else
                                   ooe=value.ooe;
							   if (value.recdeventDt=="" || value.recdeventDt==null)
                                   ooedt='-';
                               else
                                   ooedt=value.recdeventDt;
                               if (value.cussite == "" || value.cussite == null)
                                  cussite="-";
                               else
                                  cussite=value.cussite;
							   if (value.precpid=="" || value.precpid==null)
                                   precp='-';
                               else
                                   precp=value.precpid;
                               if (value.arecpid=="" || value.arecpid==null)
                                   arecp='-';
                               else
                                   arecp=value.arecpid;
                               if (value.recdfpo=="" || value.recdfpo==null)
                                   fpo='-';
                               else
                                   fpo=value.recdfpo;
							   if (value.recdDt=="" || value.recdDt==null)
                                   fpodt='-';
                               else
                                   fpodt=value.recdDt;
								if (value.bagno=="" || value.bagno==null)
                                   bagno='-';
                               else
                                   bagno=value.bagno;
                               var markup = "<tr   style='valign:middle;' class='rowDis'><input type='hidden' id='itemid' value='" + value.articleId + "'><td  width='1%' align='center' class= 'font-weight-medium'>" + iterator + "</td><td  width='4%' th:id='" + iterator + "'><a href='javascript:;' onclick=viewSummary('" + iterator + "')> " + value.articleId + "</a></td><td width='4%'>" + value.postingdt + "</td><td width='5%'>" + value.cntryCD + "</td><td width='3%'>" + value.codeDesc + "</td><td width='3%'>" + cussite + "</td><td width='5%'>" + ooe + "</td><td width='5%'>" + ooedt + "</td><td width='5%'>";
							    markup=markup + precp + "</td><td width='5%'>" + arecp + "</td><td width='5%'>" + fpo +  "</td><td width='5%'>" + fpodt +  "</td><td width='5%'>" + bagno +  "</td>"
			                    markup=markup+" <td width='5%' style='color: black;vertical-align: middle;'><input type='checkbox' id='chkboxnfarr' name='chkboxnfarr[]'  onclick='pushdata(" + iterator + ");' style='width:24px;height:24px;' title='Check for Arrival Confrmation'></td></tr>";                              	                    
                                $("#pentabbody").append(markup);
								});
						hideLoader();			
						
							 bagArticlesArray = [];
							$('#penscanf').show();							
							$('#pentabf').show();
							

$("#pentabf").dataTable({
		"paging": true,		
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
		exportOptions: {
						format: {
        					header: function ( data, column ) {
						  
						if ( column == 5 ) 
        					return "Assessment Site";
						if ( column == 6 ) 
        					return "OOE";
        				else
        					return data;
						
        				}}},
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received ',
					message:' Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
					exportOptions: {
						format: {
        					header: function ( data, column ) {
						  
						if ( column == 5 ) 
        					return "Assessment Site";
						if ( column == 6 ) 
        					return "OOE";
        				else
        					return data;
						
        				}}},
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['3%','8%','6%','6%','6%','7%','8%','8%','8%','8%','8%','8%','9%','7%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  exportOptions: {
						format: {
        					header: function ( data, column ) {
						  
						if ( column == 5 ) 
        					return "Assessment Site";
						if ( column == 6 ) 
        					return "OOE";
        				else
        					return data;
						
        				}}},
				  message:' Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received@ ' + sitecode + '\n\r' + datetime ,
					filename:'Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
				
				initComplete: function() {
      this.api().columns([5][6]).every(function() {
        var column = this;
        var coldata = column[0];
        if (coldata == 5) {
          var select = $('<select style="background-color: #A1C4E6; font-weight: 700"><option value="">ASSESSMENT SITE</option></select>')
            .appendTo($(column.header()).empty())
            .on('change', function() {
              var val = $.fn.dataTable.util.escapeRegex($(this).val());
              column.search(val ? '^' + val + '$' : '', true, false).draw();
            });
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>');
          });
        }
          if (coldata == 6) {
          var select = $('<select style="background-color: #A1C4E6; font-weight: 700"><option value="">OOE</option></select>')
            .appendTo($(column.header()).empty())
            .on('change', function() {
              var val = $.fn.dataTable.util.escapeRegex($(this).val());
              column.search(val ? '^' + val + '$' : '', true, false).draw();
            });
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>');
          });
        }
      });

      $("#pentabf_filter").append('<button type="button" class="btn btn-info" id="clear" style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
      $('#clear').on('click', function() {
        $('#pentabf_filter input[type="search"]').val('').trigger('input');
        var table = $('#pentabf').DataTable();
        table.search('').draw();
      });
    }



               });    

},
				fail: function(rs, e) {
			          console.log(rs, responseText);}
});
	
	}	
	
	
/*		function submitRid() {
  if ($($("#regulationtable").dataTable().$('input[type="checkbox"]:checked')).length < 1) {
    swal('OOPS!', 'Please select atleast one confirm arrival !', 'error');
    return false;
  }
  swal({
    title: "Do You want to Regularize ?",
	text:"Recpt ID / BAG NO as per article arrival Information will be Assigned to this selected Articles !",
    icon: "warning",
    buttons: ["No", "Yes"],

    dangerMode: true, // set dangerMode to true to make the "Yes" button red


  }).then((willDelete) => {
    if (willDelete) {
      var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
      var data = [];
      checkboxes.forEach(function(checkbox) {
        var row = checkbox.parentNode.parentNode;
        var article_id = row.cells[1].textContent;
        var bag_no = row.cells[3].textContent;
        var recept_id = row.cells[5].textContent;
        data.push({
          article_id: article_id,
          bag_no: bag_no,
          recept_id: recept_id
        });
			row.parentNode.removeChild(row);
      });
showLoader();
      $.ajax({
        url: "sendregulation",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(result) {
	hideLoader();
          swal("Success!", "Bag Numbers are Regularized!", "success");
		updateRidSlNumbers();

 //showLoader();
// location.href=localStorage.getItem("currentUrl");
          console.log(result);
        },
        error: function(xhr, status, error) {
	 hideLoader();
	 	swal('OOPS!', "Something went wrong", 'error'); 
          console.log(error);
        }
      });
    } else {
      swal("Cancelled", "The Selected Bag Numbers are not Regularized.", "warning");
    }
  });
}*/
function submitRid() {
  if ($($("#regulationtable").dataTable().$('input[type="checkbox"]:checked')).length < 1) {
    swal('OOPS!', 'Please select atleast one confirm arrival !', 'error');
    return false;
  }
  swal({
    title: "Do You want to Regularize ?",
	text:"Recpt ID / BAG NO as per article arrival Information will be Assigned to this selected Articles !",
    icon: "warning",
    buttons: ["No", "Yes"],

    dangerMode: true, // set dangerMode to true to make the "Yes" button red


  }).then((willDelete) => {
    if (willDelete) {
      var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
      var data = [];
      checkboxes.forEach(function(checkbox) {
        var row = checkbox.parentNode.parentNode;
        var article_id = row.cells[1].textContent;
        var bag_no = row.cells[3].textContent;
        var recept_id = row.cells[5].textContent;
        data.push({
          article_id: article_id,
          bag_no: bag_no,
          recept_id: recept_id
        });
// row.parentNode.removeChild(row);
      });
showLoader();
      $.ajax({
        url: "sendregulationRid",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(data) {
	hideLoader();
	
		$('#regulationtable').dataTable().fnDestroy();
		$("#recpIdRegn").empty("");
						 let html = '';
    for (let index = 0; index < data.length; index++) {
      html += `<tr>                
<td>${index + 1}</td>
<td>${data[index].article_id !== null ? data[index].article_id : '-'}</td>
<td>${data[index].scan_dt !== null ? data[index].scan_dt : '-'}</td>
<td>${data[index].bag_no !== null ? data[index].bag_no : '-'}</td>
<td>${data[index].recd_event_dt !== null ? data[index].recd_event_dt : '-'}</td>
<td>${data[index].recept_id !== null ? data[index].recept_id : '-'}</td>
<td>${data[index].ooc_dt !== null ? data[index].ooc_dt : '-'}</td>
<td style="width: 12%;"><input type="checkbox"   id="chkbox" name="chkbox[]" th:enabled="${data.ooc_dt == null }" ></td>

	</td>
      </tr>`;
 
    }
 $("#recpIdRegn").html(html); 
  /*  bagArticlesArray = [];*/
							$('#regulationtable').show();							
							$('#recpIdRegn').show();

                    swal('Success!', "Bag Numbers are Regularized!", 'success');
  $("#regulationtable").dataTable({
		"processing": true,
	    "ordering": false,
		//"paging": true,	
    	//"searching": false,	
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' REASSAIGN Bag No ',
					message:' REASSAIGN Bag No - RECEPT ID @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','20%','15%','15%','15%','20%','5%','5%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'REASSAIGN Bag No - RECEPT ID  @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'REASSAIGN Bag No - RECEPT ID  ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' REASSAIGN Bag No - RECEPT ID  @ ' + sitecode + '\n\r' + datetime ,
					filename:'REASSAIGN Bag No - RECEPT ID ',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],

	"initComplete": function( settings ) {
	     $("#regulationtable_filter").append('<button type="button" class="btn btn-info" id="clearing" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#regulationtable_filter input[type='search']").attr('id', 'searchregulationtable');
		$('#clearing').click(function() {	
		document.getElementById("regulationtable").value=""
		var table = $('#regulationtable').DataTable();
		table.search('').draw();
	   })
    	}

               });  
                },
                error: function (xhr, status, error) {
                    swal('OOPS!', "Something went wrong", 'error');
                    hideLoader();
                    console.log(error);
                }
            });
        } else {
            swal("Cancelled", "The Selected Bag Numbers are not Regularized.", "warning");
        }
    });
}
	

function submitbag() {
  if ($($("#Bagregulationtable").dataTable().$('input[type="checkbox"]:checked')).length < 1) {
    swal('OOPS!', 'Please select atleast one confirm arrival !', 'error');
    return false;
  }
  swal({
    title: "Do You want to Regularize ?",
	text:"Recpt ID / BAG NO as per article arrival Information will be Assigned to this selected Articles !",
    icon: "warning",
    buttons: ["No", "Yes"],

    dangerMode: true, 
  }).then((willDelete) => {
    if (willDelete) {
      var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
      var data = [];
      checkboxes.forEach(function(checkbox) {
        var row = checkbox.parentNode.parentNode;
        var article_id = row.cells[1].textContent;
        var bag_no = row.cells[3].textContent;
        var recept_id = row.cells[5].textContent;
        data.push({
          article_id: article_id,
          bag_no: bag_no,
          recept_id: recept_id
        });
      });
showLoader();
      $.ajax({
        url: "sendregulationBag",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(data) {
	hideLoader();
	
		$('#Bagregulationtable').dataTable().fnDestroy();
		$("#BagRegn").empty("");
						 let html = '';
    for (let index = 0; index < data.length; index++) {
      html += `<tr>                
<td>${index + 1}</td>
<td>${data[index][0] !== null ? data[index][0] : '-'}</td>
<td>${data[index][1] !== null ? data[index][1] : '-'}</td>
<td>${data[index][2] !== null ? data[index][2] : '-'}</td>
<td>${data[index][3] !== null ? data[index][3] : '-'}</td>
<td>${data[index][4] !== null ? data[index][4] : '-'}</td>
<td>${data[index][6] !== null ? data[index][6] : '-'}</td>
<td style="width: 12%;"><input type="checkbox"   id="chkbox" name="chkbox[]" th:enabled="${data[index][6] == null }" ></td>

	</td>
      </tr>`;
 
                     
    }$("#BagRegn").html(html); 
  /*  bagArticlesArray = [];*/
							$('#Bagregulationtable').show();							
							$('#BagRegn').show();

                    swal('Success!', "Bag Numbers are Regularized!", 'success');
 $("#Bagregulationtable").dataTable({
		"processing": true,
	    "ordering": false,
		//"paging": true,	
    	//"searching": false,	
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'  REASSAIGN BAG NO - BAG NO   ',
					message:'  REASSAIGN BAG NO - BAG NO @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','20%','15%','15%','15%','20%','5%','5%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+' REASSAIGN BAG NO - BAG NO @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:' REASSAIGN BAG NO - BAG NO ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'  REASSAIGN BAG NO - BAG NO @ ' + sitecode + '\n\r' + datetime ,
					filename:' REASSAIGN BAG NO - BAG NO',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],

"initComplete": function( settings ) {
	     $("#Bagregulationtable_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#Bagregulationtable_filter input[type='search']").attr('id', 'searchBagregulationtable');
		$('#clear6').click(function() {	
		document.getElementById("Bagregulationtable").value=""
		var table = $('#Bagregulationtable').DataTable();
		table.search('').draw();
	   })
    	}

               });  
            
                },
                error: function (xhr, status, error) {
                    swal('OOPS!', "Something went wrong", 'error');
                    hideLoader();
                    console.log(error);
                }
            });
        } else {
            swal("Cancelled", "The Selected Bag Numbers are not Regularized.", "warning");
        }
    });
}



/*function submitbag() {
  if ($($("#Bagregulationtable").dataTable().$('input[type="checkbox"]:checked')).length < 1) {
    swal('OOPS!', 'Please select atleast one confirm arrival !', 'error');
    return false;
  }
  swal({
    title: "Do You want to Regularize ?",
	text:"Recpt ID / BAG NO as per article arrival Information will be Assigned to this selected Articles !",
    icon: "warning",
    buttons: ["No", "Yes"],

    dangerMode: true, 
  }).then((willDelete) => {
    if (willDelete) {
      var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
      var data = [];
      checkboxes.forEach(function(checkbox) {
        var row = checkbox.parentNode.parentNode;
        var article_id = row.cells[1].textContent;
        var bag_no = row.cells[3].textContent;
        var recept_id = row.cells[5].textContent;
        data.push({
          article_id: article_id,
          bag_no: bag_no,
          recept_id: recept_id
        });
		row.parentNode.removeChild(row);
      });
showLoader();
      $.ajax({
        url: "sendregulation",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(result) {
	hideLoader();
          swal("Success!", "Bag Numbers are Regularized!", "success");
		updateBagSlNumbers();
// showLoader();
 //location.href=localStorage.getItem("currentUrl");
          console.log(result);
        },
        error: function(xhr, status, error) {
	 hideLoader();
	 	swal('OOPS!', "Something went wrong", 'error');     
          console.log(error);
        }
      });
    } else {
      swal("Cancelled", "The Selected Bag Numbers are not Regularized.", "warning");
    }
  });
}
*/
	


	/*$("#confirmnHistorytable").dataTable({
	    "processing": true,
	    "ordering": true,
		"paging": false,
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Confirmation History For B ',
					message:' Confirmation History For B @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['5%','12%','10%','9%','9%','9%','10%','10%','12%','14%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Confirmation History For B @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Confirmation History For B',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Confirmation History For B @ ' + sitecode + '\n\r' + datetime ,
					filename:'Confirmation History For B',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],  
		
		
		

	
	});  */
	
	
	
/*	function viewArticleHistry(e) {
		var bagNo=e.id;
		
		if (bagNo==null){
	        swal('OOPS!', 'Please select Bag Type !', 'error');
	        return false;
	    }
		
		
		$.ajax({
			
				url: 'getcnfirmhistrytdata?bagNo=' + bagNo , 
				"beforeSend" : function(){
                	showLoader();  
                 },
				success: function(data) {
					var val1 = data;
						var count = 0;
							var table = document.getElementById('ConfirmHistrytable-B');
                            var rowCount = table.rows.length - 1;
     						var i=0;
     						while ( i < rowCount )
   								 {
     								 document.getElementById("ConfirmHistrytable-B").deleteRow(1);
      								 i=i+1;}
	                        
								$('#ConfirmHistrytable-B').dataTable().fnDestroy();
								 $("#HistryBody").empty("");
						     $.each(val1, function(index, value) {
							   var iterator = index + 1;
				               var aid,ead,mpd,clst,dfpo,orgc,exodr,snrpot;
				             	   if (value.articleId=="" || value.articleId==null)
                                   aid='-';
                               else
                                   aid=value.articleId;
                               if (value.eadExist == "" || value.eadExist == null)
                                  ead="-";
                               else
                                  ead=value.eadExist;
							   if (value.mapped=="" || value.mapped==null)
                                   mpd='-';
                               else
                                   mpd=value.mapped;
                               if (value.clrsite=="" || value.clrsite==null)
                                   clst='-';
                               else
                                   clst=value.clrsite;
                               if (value.destinationFpo=="" || value.destinationFpo==null)
                                   dfpo='-';
                               else
                                   dfpo=value.destinationFpo;
							   
								if (value.originCountry1=="" || value.originCountry1==null)
                                   orgc='-';
                               else
                                   orgc=value.originCountry1;
								if (value.ExamOrderExist=="" || value.ExamOrderExist==null)
                                   exodr='-';
                               else
                                   exodr=value.ExamOrderExist;
								if (value.scanReport=="" || value.scanReport==null)
                                   snrpot='-';
                               else
                                   snrpot=value.scanReport;
								


                               var markup = "<tr   style='valign:middle;' class='rowDis'><input type='hidden' id='itemid' value='" + value.articleId + "'><td  width='1%' align='center' class= 'font-weight-medium'>" + iterator + "</td><td  width='4%' th:id='" + iterator + "'><a href='javascript:;' onclick=viewSummary('" + iterator + "')> " + value.articleId +  "</td><td width='5%'>" + aid + "</td><td width='5%'>" + ead + "</td><td width='5%'>";
							    markup=markup + mpd + "</td><td width='5%'>" + clst + "</td><td width='5%'>" + dfpo +  "</td><td width='5%'>" + orgc +  "</td><td width='5%'>" + exodr +  "</td><td width='5%'>" + snrpot +  "</td>"
			                    markup=markup+" <td width='5%' style='color: black;vertical-align: middle;'><input type='checkbox' id='chkboxnfarr' name='chkboxnfarr[]'  onclick='pushdata(" + iterator + ");' style='width:24px;height:24px;' title='Check for Arrival Confrmation'></td></tr>";                              	                    
                                $("#HistryBody").append(markup);
								});  
				
						    bagArticlesArray = [];							
							$('#ConfirmHistrytable-B').show();

}

});
$("#ConfirmHistrytable-B").dataTable({
		"paging": true,		
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received ',
					message:' Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['3%','8%','6%','6%','6%','7%','8%','8%','8%','8%','8%','8%','9%','7%'];
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
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
    		        },
			title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received@ ' + sitecode + '\n\r' + datetime ,
					filename:'Art-IDs for which Art.Arrival Info Received Partial / Art.Arrival Information Not Received',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],



               });
}*/
