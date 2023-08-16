

$(document).ready(function() {
	$('#btnwithouteead').attr("disabled", true);
	showLoader();
	


	$('.mapTable').DataTable({
		"ordering": false,
	    "processing": true,
		"paging": true,		
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 5,
		//"dom": '<"dt-top-container"<l><"dt-center-in-div"B><f>r>t<"dt-filter-spacer"f><ip>',
		//buttons: ['excel']
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: 'excel', text: '<i>EXCEL</i>',
				  	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'LIST OF ARTICLE-IDs WITHOUT PINCODE MAPPED TO THIS SITE',
					message:'LIST OF ARTICLE-IDs WITHOUT PINCODE MAPPED TO THIS SITE @ ' + sitecode + '\n\r' + datetime,
					/*customize: function ( xlsx ) {
    				var sheet = xlsx.xl.worksheets['sheet1.xml'];
					$('c[r=A4] t', sheet).text( 'Custom text' );
					} */
					
				},
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'LIST OF ARTICLE-IDs WITHOUT PINCODE MAPPED TO THIS SITE ' + sitecode,
					filename:'LIST OF ARTICLE-IDs WITHOUT PINCODE MAPPED TO THIS SITE',
				     orientation: 'landscape',
					exportOptions: {
        				//columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						},
		   		     customize: function(doc, header) {
			
				doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
			
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
					doc.defaultStyle.Arial=true;
					doc.styles.title.bold=true;
					doc.styles.title.fontSize=15;
					doc.styles.title.alignment="center",
					doc.styles.message.fontSize = 9;
					doc.styles.message.bold=true;
					doc.styles.message.alignment = "left";
					doc.styles.tableHeader.fontSize= 6;
					doc.styles.tableHeader.bold=true;
    		        },
					},
					//{extend: "excel", text: '<i>EXCEL</i>',	
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'LIST OF ARTICLE-IDs WITHOUT PINCODE MAPPED TO THIS SITE@ ' + sitecode + '\n\r' + datetime ,
					filename:'LIST OF ARTICLE-IDs WITHOUT PINCODE MAPPED TO THIS SITE',	  
					
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
                       //.addClass( 'compact' )
						}, }], 
						
						
						
	});
		
		

	
	
	
	// $('.arrTable').DataTable({ 
		$('#Tablewithoutpincdsnotmapped').DataTable({ 
		"ordering": false,
	    "processing": false,
		"paging": true,		
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		//"dom": '<"dt-top-container"<l><"dt-center-in-div"B><f>r>t<ip>',
		//buttons: ['excel']
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: 'excel', text: '<i>EXCEL</i>',
				  	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'LIST OF ARTICLE-IDs OF EAD  WITHOUT PINCODE  - ALL INDIA',
					message:'LIST OF ARTICLE-IDs OF EAD  WITHOUT PINCODE  - ALL INDIA @ ' + sitecode + '\n\r' + datetime,
				},
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'LIST OF ARTICLE-IDs OF EAD  WITHOUT PINCODE  - ALL INDIA @' + sitecode,
					filename:'LIST OF ARTICLE-IDs OF EAD  WITHOUT PINCODE  - ALL INDIA',
				     orientation: 'landscape',
					exportOptions: {
        				//columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						},
		   		     customize: function(doc, header) {
					 //doc.content[1].table.widths = ['3%','8%','6%','6%','9%','7%','7%','4%','4%','4%','4%','4%','4%','4%','4%','4%','10%','8%'];
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
			
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
					doc.styles.tableHeader.fontSize= 6;
					doc.styles.tableHeader.bold=true;
    		        },
					},
				
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'LIST OF ARTICLE-IDs OF EAD  WITHOUT PINCODE  - ALL INDIA@ ' + sitecode + '\n\r' + datetime ,
					filename:'LIST OF ARTICLE-IDs OF EAD  WITHOUT PINCODE  - ALL INDIA',	 
					
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
                       //.addClass( 'compact' )
                       
						$(win.document.body).find('message').css('font-size', '15pt');

						}, }],
						

"initComplete": function( settings ) {
  $("#Tablewithoutpincds_filter").append('<button type="button" class="btn btn-info clear-btn" id="clearTablewithoutpincds" style="margin-left: 15px; margin-bottom: 5px;">Clear</button>');
  $("#Tablewithoutpincds_filter input[type='search']").attr('id', 'searchTablewithoutpincds');

  $('#clearTablewithoutpincds').click(function() {	
    document.getElementById("searchTablewithoutpincds").value = "";
    var table = $('#Tablewithoutpincds').DataTable();
    table.search('').draw();
  });

  $("#Tablewithoutpincdsnotmapped_filter").append('<button type="button" class="btn btn-info clear-btn" id="clearTablewithoutpincdsnotmapped" style="margin-left: 15px; margin-bottom: 5px;">Clear</button>');
  $("#Tablewithoutpincdsnotmapped_filter input[type='search']").attr('id', 'searchTablewithoutpincdsnotmapped');

  $('#clearTablewithoutpincdsnotmapped').click(function() {	
    document.getElementById("searchTablewithoutpincdsnotmapped").value = "";
    var table = $('#Tablewithoutpincdsnotmapped').DataTable();
    table.search('').draw();
  });
}
	
	});

	
	
	$('.arrTable').DataTable({ 
		"ordering": false,
	    "processing": false,
		"paging": true,		
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 10,
		//"dom": '<"dt-top-container"<l><"dt-center-in-div"B><f>r>t<ip>',
		//buttons: ['excel']
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: 'excel', text: '<i>EXCEL</i>',
				  	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'LIST OF ARTICLES ARRIVED PHYSICALLY AT THIS SITE AND AWAITING FOR OOC',
					message:'LIST OF ARTICLES ARRIVED PHYSICALLY AT THIS SITE AND AWAITING FOR OOC @ ' + sitecode + '\n\r' + datetime,
				},
				{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'LIST OF ARTICLES ARRIVED PHYSICALLY AT THIS SITE AND AWAITING FOR OOC @' + sitecode,
					filename:'LIST OF ARTICLES ARRIVED PHYSICALLY AT THIS SITE AND AWAITING FOR OOC',
				     orientation: 'landscape',
					exportOptions: {
        				//columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						},
		   		     customize: function(doc, header) {
					 //doc.content[1].table.widths = ['3%','8%','6%','6%','9%','7%','7%','4%','4%','4%','4%','4%','4%','4%','4%','4%','10%','8%'];
					doc['footer']=(function(page, pages) {
							return {
								columns: ['',{
								alignment: 'right',
										text: ['Page  ',{ text: page.toString(), italics: true,fontSize:7},'  of ',
											{ text: pages.toString(), italics: true,fontSize:6 }],italics: true, fontSize:6  }],
										margin: [15, 0]}});
			
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
					doc.styles.tableHeader.fontSize= 6;
					doc.styles.tableHeader.bold=true;
    		        },
					},
				
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'LIST OF ARTICLES ARRIVED PHYSICALLY AT THIS SITE AND AWAITING FOR OOC@ ' + sitecode + '\n\r' + datetime ,
					filename:'LIST OF ARTICLES ARRIVED PHYSICALLY AT THIS SITE AND AWAITING FOR OOC',	 
					
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
                       //.addClass( 'compact' )
                       
						$(win.document.body).find('message').css('font-size', '15pt');

						}, }],
						
		"initComplete": function( settings ) {
	     $("#Tablewithoutpincds_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#Tablewithoutpincds_filter input[type='search']").attr('id', 'searchTablewithoutpincds');
		$('#clear6').click(function() {	
		document.getElementById("searchTablewithoutpincds").value=""
		var table = $('#Tablewithoutpincds').DataTable();
		table.search('').draw();
	   })
    	}	
	});
	
	
	
	
	hideLoader();
		
		
		$('#Tablewithoutead').DataTable({
		"ordering": false
	});

	$('#singledatawithoutEAD').DataTable({
		"ordering": false
	});

	/*$('#singledata').DataTable({
		"ordering": false
	});*/


});
	
	

var selstVal = "";
var destfpo = "";
function entrarticleid(val) {
	jQuery.ajax({
		url: 'particularid?artid=' + val.value,
		"async": false,
		type: 'POST',
		success: function(data) {
			destfpo = "";
			$('.hidetb').empty();
			$('.backto').css("display", "block");
			$('.headingtyp').css("display", "block");
			$("#tblewithoutcusite").replaceWith(data);
			$('#withouteadtbl').css("display", "none");
			destfpo = $('#destfpo').text();
			var sub = destfpo.substring(0, 5);
			$('.hidetb').css('display', 'block');
			var checkval = $('#searchval').val();
			if (checkval == 0) {
				$('#singrecdwithoutEAD').css("display", "block");
				$('#tablesinglerecrd').css("display", "none");
				$('.witheadinfo').css("display", "none");
				$('#btnwithouteead').attr("disabled", false);
				$('#btnwithEAD').attr("disabled", true);
				$('br').remove();
			} else {
				$('#singrecdwithoutEAD').css("display", "none");
				$('#tablesinglerecrd').css("display", "block");
				$('.withoutEADinfo').css("display", "none");
				$('#singrecdwithoutEAD').css("display", "none");
				$('#btnwithEAD').css("display", "none");
				/*$('#btnwithouteead').attr("disabled", true);*/
			}

			if (destfpo != "") {
				$.ajax({
					url: 'getdestnationFpoState?destnFpo=' + sub,
					success: function(state) {
						selstVal = "";
						$.each(state, function(index, value) {
							$(".selstates").append('<option>' + value[0] + '</option>');
							selstVal = value[1];

						});
					}
				});
			}
		}

	});

}
var selstate = "";
function statechange(e) {
	selstate = "";
	selstate = $(e).children("option:selected").text();
	if (selstate == "BIHAR +JHARKHAND") {
		$(".seldist").html('<option>--Select District--</option>');
		var mydata = selstate.split("+");
		$.each(mydata, function(index, value) {
			selstate = "";
			selstate = value;
			$.ajax({
				url: 'getdistrict?state=' + selstate,
				success: function(district) {
					$.each(district, function(index, value) {
						$(".seldist").append('<option>' + value + '</option>');
					});

				}
			})

		});
	} else {
		$.ajax({
			url: 'getdistrict?state=' + selstate,
			success: function(district) {
				$(".seldist").html('<option>--Select District--</option>');
				$.each(district, function(index, value) {
					$(".seldist").append('<option>' + value + '</option>');
				});

			}
		})
	}
}

function updateFpoState() {
	var error = 0;
	var getdata = {};
	if ($('.selstates').val().trim() == '--Select State--') {
		$('.selstates').next().show();
		$('.selstates').next().text('Select State*')
		$('.selstates').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('.selstates').css({ "border-color": "", "box-shadow": "" })
		$('.selstates').next().text('');

	}
	if (error == 0) {
		$('#addpopupreas').empty();
		$("#addpopupreas").append("Please confirm the destination state " + selstate + " and Customs FPO Site " + selstVal + " for this article-id.")
		$('#cnfrmpopup').modal('show');
		$('#updtfpoallot').click(function() {
			getdata['article_id'] = $('#entaritcleid').val();
			getdata['state'] = selstate;
			getdata['district'] = $('.seldist').val();
			getdata['cus_site'] = selstVal;
			getdata['reason'] = $('#otherreason').val();
			$.ajax({
				url: 'updtefpoallot',
				data: JSON.stringify(getdata),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(developerData1) {
					swal("Success!", "Site Details Updated! (Selected Article-ID mapped to this Site is moved to Assessment Queue of AAF Sub-module (if EAD is available))", "success")
						.then((value) => {
							location.reload();
						});
				}
			});
		});
	}

}


function selall(){
    var table = document.getElementById("Tablewithoutpincds"); // Replace "your_table_id" with the actual ID of your table
    var checkboxes = table.querySelectorAll("input[type='checkbox']:not(:disabled)");
    var selectAllCheckbox = document.getElementById('chkselall');

    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = selectAllCheckbox.checked;
    }
}


function selallwithout(){
    var table = document.getElementById("Tablewithoutpincdsnotmapped"); // Replace "your_table_id" with the actual ID of your table
    var checkboxes = table.querySelectorAll("input[type='checkbox']:not(:disabled)");
    var selectAllCheckbox = document.getElementById('chkselallw');

    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = selectAllCheckbox.checked;
    }
}




function closeSummaryModal(){
 $("#Tablewithoutpincds tbody tr").each(function() {
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
$("#Tablewithoutpincdsnotmapped tbody tr").each(function() {
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

//$("#summaryModalBody").html('');
		$("#summaryModal").modal('hide');
		
	}



function viewSummary(e) {
		 var developerData = {};
	     var currow=e.id;
         var pagelen; 
         var row;
  var pagelen =$('#Tablewithoutpincds').DataTable().page.info().length;
        var row=currow % pagelen;
       if (row==0)
           row=pagelen;   
       var itmid= $('#Tablewithoutpincds').find('tr').eq(row).find("input#itemid").val();
       $('#Tablewithoutpincds').find('tr').eq(row).css('background-color','green');
       $('#Tablewithoutpincds').find('tr').eq(row).css('font-size','18');
       $('#Tablewithoutpincds').find('tr').eq(row).css('color','yellow');
       $('#Tablewithoutpincds').find('tr').eq(row).css('border','darkgreen');
       developerData['id'] = itmid;
		
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


function viewSummary1(e) {
		 var developerData = {};
	     var currow=e.id;
         var pagelen; 
         var row;
        var pagelen =$('#Tablewithoutpincdsnotmapped').DataTable().page.info().length;
        var row=currow % pagelen;
       if (row==0)
           row=pagelen;   
       var itmid= $('#Tablewithoutpincdsnotmapped').find('tr').eq(row).find("input#itemid").val();
       $('#Tablewithoutpincdsnotmapped').find('tr').eq(row).css('background-color','green');
       $('#Tablewithoutpincdsnotmapped').find('tr').eq(row).css('font-size','18');
       $('#Tablewithoutpincdsnotmapped').find('tr').eq(row).css('color','yellow');
       $('#Tablewithoutpincdsnotmapped').find('tr').eq(row).css('border','darkgreen');
       developerData['id'] = itmid;
		
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



 function mapsubmitall()
    {
       $("#exampleModal").modal('show');
    }   

 function notmapsubmitall()
    {
       $("#Modalfornotmap").modal('show');
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


/*$(document).ready(function() {
	$(".mapTable").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 5
	});
});*/



function mapsuball()
	{
	 var table = document.getElementById('Tablewithoutpincds');
        var rowCount = table.rows.length;
        var developerData = {};
        var itemid ;
        var cussite;
         for (var i = 1; i < rowCount; i++)    
     {      
           itemid = $('#Tablewithoutpincds').find('tr').eq(i).find('td:eq(1)').text().substr(0,13);
           cussite = $('#Tablewithoutpincds').find('tr').eq(i).find('td:eq(11)').text().substr(0,6);
           if ($('#Tablewithoutpincds').find('tr').eq(i).find('[name="chkbox[]"]').prop('checked'))
            {  developerData['article_id'] = itemid;
               developerData['reason'] = "BULK MAPPING";
               developerData['cus_site'] = cussite;
			$.ajax({
 		       url: 'updtefpoallot',
 		       data: JSON.stringify(developerData),
        dataType: "json",
		async : false,
        contentType: "application/json",
        type: "post",
       success: function(developerData) {
             if (i == (rowCount-1) )
		         {
                   success="yes";		           
	             }					
         },
        fail: function(rs, e) {
         alert("Error in Pincode Mapping");
          }
          
      });
      }
 }   
      if (success="yes")
        $("#mapModal").modal('show');

}


function notmapsuball()
	{
	 var table = document.getElementById('Tablewithoutpincdsnotmapped');
        var rowCount = table.rows.length;
        var developerData = {};
        var itemid ;
        var cussite;
         for (var i = 1; i < rowCount; i++)    
     {      
           itemid = $('#Tablewithoutpincdsnotmapped').find('tr').eq(i).find('td:eq(1)').text().substr(0,13);
           cussite = $('#Tablewithoutpincdsnotmapped').find('tr').eq(i).find('td:eq(8)').text().substr(0,6);
           if ($('#Tablewithoutpincdsnotmapped').find('tr').eq(i).find('[name="chkbox[]"]').prop('checked'))
            {  developerData['article_id'] = itemid;
               developerData['reason'] = "BULK MAPPING";
               developerData['cus_site'] = cussite;
			$.ajax({
 		       url: 'updtefpoallot',
 		       data: JSON.stringify(developerData),
        dataType: "json",
		async : false,
        contentType: "application/json",
        type: "post",
       success: function(developerData) {
             if (i == (rowCount-1) )
		         {
                   success="yes";		           
	             }					
         },
        fail: function(rs, e) {
         alert("Error in Pincode Mapping");
          }
          
      });
      }
 }   
	location.href=localStorage.getItem("currentUrl");
	$('#tblewithoutcusite').hide('slow');
    /*  if (success="yes")
        $("#mapModal").modal('show');
	*/
}





function refresh()
    {
      location.href=localStorage.getItem("currentUrl");
       
    }



/*function refreshnotmap()
{
	  $('#tblewithoutcusitenotmapped').show('slow');
      location.href=localStorage.getItem("currentUrl");
	  //$('#tblewithoutcusite').hide('slow');
	  
    
}	

*/

function viewDetails(obj) {
	$("#entaritcleid").val($('#itemid').val());
	$("#entaritcleid").change();
}

function viewArtDetails(obj) {
	$("#viewentaritcleid").val($(obj).text());
	$("#viewentaritcleid").change();
}

function viewentrarticleid(val) {
		$.ajax({
		url: 'viewoneart?artid=' + val.value,
		type: 'POST',
		success: function(data) {
			destfpo = "";
			$('.viewhidetb').empty();
			$('.viewbackto').css("display", "block");
		/*	$('.headingtyp').css("display", "block");*/
		 $('#viewwithouteadtbl').css("display", "none");
			$("#viewsingtable").replaceWith(data);
			
			/*destfpo = $('#destfpo').text();
			var sub = destfpo.substring(0, 5);*/
			$('.viewhidetb').css('display', 'block');
			var checkval = $('#viewsearchval').val();
			if (checkval == 0) {
				$('#viewsingrecdwithoutEAD').css("display", "block");
				$('#viewtablesinglerecrd').css("display", "none");
				$('.viewwitheadinfo').css("display", "none");
				$('#viewbtnwithouteead').attr("disabled", false);
				$('#viewbtnwithEAD').attr("disabled", true);
				$('.viewwithoutEADarrinfo').css("display", "block");
				$('br').remove();
			} if(checkval == 1){
				$('#viewsingrecdwithoutEAD').css("display", "none");
				$('#viewtablesinglerecrd').css("display", "block");
				$('.viewwithoutEADinfo').css("display", "none");
				$('#withouteadtbl').css("display", "none");
				$('#viewbtnwithEAD').attr("disabled", false);
				$('#viewbtnwithouteead').attr("disabled", true);
			}

		
			
		}

	});
}

$('.bckto').click(function() {
	window.location.href = "alloSite";
});

$('.viewbackto').click(function() {
	window.location.href = "viewarrinfo";
});

$('#btnwithEAD').click(function() {
	$('#tblewithoutcusite').css("display", "block");
	$('#withouteadtbl').css("display", "none");
})

$('#btnwithouteead').click(function() {
	$('#tblewithoutcusite').css("display", "none");
	$('#withouteadtbl').css("display", "block");
})

$('#viewbtnwithouteead').click(function() {
	$('#viewsingtable').css("display", "none");
	$('#viewwithouteadtbl').css("display", "block");
    $('#weadhdr').show();
    $('#weadhdr3').show();
    $('#eadhdr').hide();
})

$('#viewbtnwithEAD').click(function() {
	$('#viewsingtable').css("display", "block");
	$('#viewwithouteadtbl').css("display", "none");
    $('#eadhdr').show();
    $('#weadhdr').hide();
    $('#weadhdr3').hide();
})

function pdfdownload(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: "pdfdownload_withEAD",
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

function pdfdownloadnm(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: "pdfdownload_notmpd",
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
 

$(".chckbox").click(function(){
	
		if ($(".chckbox:checkbox:checked").length > 0){
			$("#mapsuball").attr("disabled", false);
		}
		else{
		   $("#mapsuball").attr("disabled", true);
		}
	
});


$("#chkselall").click(function(){
	
		if ($(this).is(":checked")){
			$("#mapsuball").attr("disabled", false);
		}
		else{
		   $("#mapsuball").attr("disabled", true);
		}
	
});


$(".chcknotmap").click(function(){
	
		if ($(".chcknotmap:checkbox:checked").length > 0){
			$("#notmapsub").attr("disabled", false);
		}
		else{
		   $("#notmapsub").attr("disabled", true);
		}
	
});

$("#chkselallw").click(function(){
	
		if ($(this).is(":checked")){
			$("#notmapsub").attr("disabled", false);
		}
		else{
		   $("#notmapsub").attr("disabled", true);
		}
	
});






function exceldownload(obj){
	
	$(".buttons-excel").trigger('click');
}
function exceldownloadnm(obj){
	
	$(".excelButtonNM").trigger('click');
}




	


