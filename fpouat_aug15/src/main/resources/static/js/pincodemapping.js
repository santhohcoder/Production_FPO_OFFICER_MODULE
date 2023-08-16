
$("#pincdemaptable").dataTable({
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
					doc.content[1].table.widths = ['5%','19%','19%','19%','19%','19%'];
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
	        $("#pincdemaptable_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});




function viewmappedpincode(){
	$('.viewmappedpincode').css('display', 'block');
	$('.mappingpincoderange').css('display', 'none');
}

function pincdechange(){
	$('.viewmappedpincode').css('display', 'none');
	$('.mappingpincoderange').css('display', 'block');
}

$('#submitresn').prop('disabled', true);
function EnableDisable(recordMsg) {
	var str = recordMsg.value.trim();
	if (recordMsg.value.length <= 4 || str == "") {
		document.getElementById("submitresn").disabled = true;
	} else {
		document.getElementById("submitresn").disabled = false;
	}
}


$("select.selcusite").change(function() {
	var selectedsite = $(this).children("option:selected").val();
	var selval = $('#selcusite').val();
	var iterator = 0;
	
	if (selectedsite == "--Select CusSite--") {
		document.getElementById("pincdemaptable").style.display = "none";
			document.getElementById("pincodedata").style.display = "none";
		}
		else
		{
				document.getElementById("pincodedata").style.display = "block";
		document.getElementById("pincdemaptable").style.display = "inline-table";
			
	showLoader();
	$.ajax({
		url: 'getpincdemappingdt?Cs=' + selval,
		success: function(val1) {
			$('#pincdemaptable').dataTable().fnDestroy();
			$("#mappingdata").empty("");
			$.each(val1, function(index, val) {
				iterator = iterator + 1;
				var data1 = "<tr><td align= 'center'>" + iterator + "</td><td align= 'center'>" + val[0] + "</td><td align= 'center'>" + val[1] + "</td><td align= 'center'>" + val[2] + "</td><td align= 'center'>" + val[3] + "</td><td align= 'center'>" + val[4] + "</td></tr>"
				$('#mappingdata').append(data1);
			})
			
			/*
			$('#pincdemaptable').dataTable({
				"ordering": false,
				"dom": 'Bfrtip',
				buttons: ['excel']
			});*/
$("#pincdemaptable").dataTable({
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
					doc.content[1].table.widths = ['5%','19%','19%','19%','19%','19%'];
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
	     $("#pincdemaptable_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#pincdemaptable_filter input[type='search']").attr('id', 'searchpincdemaptable');
		$('#clear6').click(function() {	
		document.getElementById("searchpincdemaptable").value=""
		var table = $('#pincdemaptable').DataTable();
		table.search('').draw();
	   })
    	}
		
	});


hideLoader();

		},
		
			error: function(jqXHR, textStatus, errorThrown) {
			           // console.error("Error occurred: " + textStatus, errorThrown);
			            
			            window.location = 'error';
			           
			        }
		
		
	});
	}
});

/*$(document).ready(function() {
	$('#pincdemaptable').DataTable({
		"ordering": false,
	});
});*/

//pincode mapping to new Site
var valcs = "";
$("select.newcS").change(function() {
	valcs = $(this).children("option:selected").text();
})

$('select.selpartpincode').change(function() {
	var pin = $(this).children("option:selected").val();
	if (pin == 'PR') {
		$(".partdiv").hide();
		$(".pinrange").show();
		$("#partinsertpin").hide();
	} else {
		$(".partdiv").show();
		$(".pinrange").hide();
		$("#partinsertpin").show();
	}
})

$('#partpin').change(function() {
	var inpt = $('#partpin').val();
	$.ajax({
		url: 'getmappedSite?singlepin=' + inpt,
		success: function(mapdata) {
			var csval = "";
			if (mapdata.length == 0) {
				$('#mappedSt').val('');
				$('#mappedCs').val('');
			} else {
				$.each(mapdata, function(index, val) {
					csval = val[1];
					$('#mappedSt').val(val[0]);
					$('#mappedCs').val(val[1]);
				});
			}
			$.ajax({
				url: 'getUnmapCs?mapCs=' + csval,
				success: function(Csdata) {
					if (Csdata.length == 0) {
						$('.chngeCs').empty();
						$('.chngeCs').append($('<option>' + '--Select CusSite--' + '</option>)'));
					} else {

						$.each(Csdata, function(index, Cs) {
							$('.chngeCs').append($('<option>' + Cs + '</option>)'));
						});
					}
				}
			});
		}
	});
});


$('#partinsertpin').click(function() {
	$('#recordMsg').val('');
	$('#priorityreas').modal('show');
	$('#submitresn').click(function() {
		var insrtdata = {};
		insrtdata['start_pincode'] = $('#partpin').val();
		insrtdata['mapped_state_name'] = $('#mappedSt').val();
		insrtdata['mapped_site_code'] = $('#mappedCs').val();
		insrtdata['new_map_cussite'] = $('#rangecS').val();
		insrtdata['reason'] = $('#recordMsg').val();
		insrtdata['pincode_typ'] = 'P';
		$('#cnfrmreall').modal('show');
		$('#cnfmpopup').click(function() {
			$.ajax({
				url: 'pincodemapinsert',
				data: JSON.stringify(insrtdata),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(val1) {
					swal("Success!", 'Articles destined for a pincode / range of pincodes entered is / are re-assigned to the selected ' + ' ' + $('.chngeCs').val() + ' ' + ' Customs FPO Site!', "success")
						.then((value) => {
							location.reload();
						});
				}
			});

		});
	});

})

$('#insertrangepin').click(function() {
	$('#recordMsg').val('');
	$('#priorityreas').modal('show');
	$('#submitresn').click(function() {
		var pinrange = {};
		pinrange['start_pincode'] = $('#strtpin').val();
		pinrange['end_pincode'] = $('#endpin').val();
		pinrange['mapped_state_name'] = $('#Stmapped').val();
		pinrange['mapped_site_code'] = $('#Csmapped').val();
		pinrange['new_map_cussite'] = $('#rangecS').val();
		pinrange['reason'] = $('#recordMsg').val();
		pinrange['pincode_typ'] = 'R';
		pinrange['new_state'] = $('.rangestate').val();
		$('#cnfrmreall').modal('show');
		$('#cnfmpopup').click(function() {
			$.ajax({
				url: 'pincodemapinsert',
				data: JSON.stringify(pinrange),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(val1) {
					swal("Success!", 'Articles destined for a pincode / range of pincodes entered is / are re-assigned to the selected ' + ' ' + $('.rangecS').val() + ' ' + ' Customs FPO Site!', "success")
						.then((value) => {
							location.reload();
						});
				}
			});

		});

	});
});



$('.rangecS').change(function() {
	var rangecs = $(this).children("option:selected").val();
	$('#rangecS').val(rangecs.substr(0,6));
});


$('.chngeCs').change(function() {
	var rangecs = $(this).children("option:selected").val();
	$('#rangecS').val(rangecs.substr(0,6));
});




var strtpin = "";
var endpin = "";
$('#strtpin').change(function() {
	strtpin = $('#strtpin').val();
	if (strtpin != "" && endpin != "") {
		process();
	} else {
		$('#Stmapped').val('');
		$('#Csmapped').val('');
		$('.rangecS').empty();
		$('.rangecS').append($('<option>' + '--Select CusSite--' + '</option>)'));
	}

});

$('#endpin').change(function() {
	endpin = $('#endpin').val();
	if (strtpin != "" && endpin != "") {
		process();
	} else {
		$('#Stmapped').val('');
		$('#Csmapped').val('');
		$('.rangecS').empty();
		$('.rangecS').append($('<option>' + '--Select CusSite--' + '</option>)'));
	}
})

var defstrtpin = "";
var defendpin = "";
function process() {
	$.ajax({
		url: 'mappedstateandpin?startpin=' + strtpin + '&endpin=' + endpin,
		success: function(mapdata) {
			var sitecd = "";
			if (mapdata.length == 0) {
				$('#Stmapped').val('');
				$('#Csmapped').val('');
			} else {
				$.each(mapdata, function(index, val) {
					sitecd = val[1];
					defstrtpin = val[2];
					defendpin = val[3];
					$('#Stmapped').val(val[0]);
					$('#Csmapped').val(val[1]);
				});
			}
			$.ajax({
				url: 'getUnmapCs?mapCs=' + sitecd,
				success: function(Csdata) {
					if (Csdata.length == 0) {
						$('.rangecS').empty();
						$('.rangecS').append($('<option>' + '--Select CusSite--' + '</option>)'));
					} else {
						$('.rangecS').empty();
						$.each(Csdata, function(index, Cs) {
							$('.rangecS').append($('<option>' + Csdata[index][1] + '</option>)' + Csdata[index][0]) );
						});
					}
				}
			});
		}
	});

	$.ajax({
		url: 'getnewmapstate',
		success: function(newstate) {
			if (newstate.length == 0) {
				$('.rangestate').empty();
				$('.rangestate').append($('<option>' + '--Select State--' + '</option>)'));
			} else {
				$('.rangestate').empty();
				$.each(newstate, function(index, St) {
					$('.rangestate').append($('<option>' + St + '</option>)'));
				});
			}
		}

	});

}

$('select.statelist').change(function() {
	var stateName = $(this).children("option:selected").val();
	if (stateName != "--Select State--")
		$.ajax({
			url: 'getstatepincodes?StNm=' + stateName,
			success: function(newstate) {
				$('.pinstNme').empty();
				$('#qryreplycontent').html(newstate);
				$('#qryreplymodal').modal('show');
				$('.pinstNme').append(stateName);
			},
		});

});

function exceldownload(obj) {
	$(".buttons-excel").trigger('click');
}

function pdfdownload(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url:'pdfdwnld_PINMAP',
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(response) {
			console.log('inside success');
			
			 $("#staticreportdownloadanchortag").attr("href","downloadPdfFile?filename=" + response);
		
	//	    $("#embedpdf").attr('src', 'file/pdf/' + $('#pdffilename').val() + '#toolbar=0&navpanes=0;readonly=true;');// newly added line for above
	
	 //    $("#embedpdf").attr('src', 'file/pdf/' + response + '#toolbar=0&navpanes=0;readonly=true;');
            document.getElementById("staticreportdownloadanchortag").click();

		}
	});
	
	}
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





