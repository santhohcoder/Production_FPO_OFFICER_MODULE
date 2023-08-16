
/*document.getElementById("submitresn").disabled =  true;
function EnableDisable(recordMsg) {

	var str = recordMsg.value.trim();
	if (recordMsg.value.length <= 4 || str == "") {
		document.getElementById("submitresn").disabled =  true;
	} else {
		document.getElementById("submitresn").disabled =  false;
	}
	}*/
var error = 0;
$(document).ready(function() {
	
	
	
	$('#prioritystge').click(function() {
		var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#pusharticlereason').val()) == true || reg.test($('#aritcleid').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
		var error = 0;
		if(($('#aritcleid').val() == "")){
		$('#aritcleid').next().show();
		$('#aritcleid').next().text('Article-ID is required*')
		$('#aritcleid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	}else if ($('#aritcleid').val().length < 13) {
		$('#aritcleid').next().show();
		$('#aritcleid').next().text('Article-ID is must in 13-digits*')
		$('#aritcleid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#aritcleid').css({ "border-color": "", "box-shadow": "" })
	}
	
	if(($('#pusharticlereason').val() == "")){
		$('#pusharticlereason').next().show();
		$('#pusharticlereason').next().text('Enter Reason*')
		$('#pusharticlereason').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	}else{
		  $('#pusharticlereason').next().text('')
			$('#pusharticlereason').css({ "border-color": "", "box-shadow": "" })
	}
		if(error == 0) {
		/*	$('#priorityreas').modal('show')
		$('#submitresn').click(function () {*/
			var prioresp = "";
			prioresp = $('#pusharticlereason').val();
			$('#cnfrm').modal('show');
		$('#setpriority').click(function ()  {
			var itmid = $('#aritcleid').val();
		$.ajax({
			url: 'getitemidstage?getsts=' + itmid + '&logincs=' + $('#siteprio').val(),
			success: function(data) {
				/*var stge = data*/
			
				if(data == "0"){
				swal("Oops!", "No data required for this article-id!")
					.then((value) => {
						window.location = "priority"
					});
				}else if(data == "2"){
					swal("Oops!", "This Item doesn't belongs to this Site!")
					.then((value) => {
						window.location = "priority"
					});
				}
				
				if(data == "1"){	
						
						$.ajax({
							url: 'updatepriority?itmid=' + itmid + '&sitecde=' + $('#siteprio').val() + '&resp=' + prioresp,
			success: function(data1) {
				
				if(data1!=null && data1!=""){
					swal("Oops!", "Article-ID not in assessment Queue!")
					.then((value) => {
						window.location = "priority"
					});
				}else{
					swal("Success!", "priority given!", "success")
					.then((value) => {
						window.location = "priority"
					});
				}
				
				}
				});			
				
					}
			}
		});
		});
	
		}
		
		});
});
$("#PriorityHistory").dataTable({
		"paging": true,		
		"lengthMenu": [ 5, 10, 20, 30, 40, 50 ],
		"pageLength": 10,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": '<"pull-left"B><"pull-left"l>frtip', 
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' PRIORITY HISTORY ',
					message:' PRIORITY HISTORY @ ' + sitecode + '\n\r' + datetime, 
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
		   		    doc.content[1].table.widths = ['8%','15%','15%','15%','10%','07%','15%','15%'];
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
							'\n\r'+'PRIORITY HISTORY@ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'PRIORITY HISTORY',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' PRIORITY HISTORY @ ' + sitecode + '\n\r' + datetime ,
					filename:'PRIORITY HISTORY',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],



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