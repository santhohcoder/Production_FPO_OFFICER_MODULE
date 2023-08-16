
$(document).ready(function() {
	showLoader();
	hideLoader();
	
	});


$("#processtable").dataTable({
	"processing": true,
	"paging": false,
	"ordering": false,
	scrollY: '45vh',
	scrollCollapse: true,
	scrollX: true,
	"dom": '<"pull-left"Bl>frtip',
	buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Manage Alerts For FPO Site view active alerts ',
					message:'Manage Alerts For FPO Site view active alerts @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Manage Alerts For FPO Site view active alerts @ ' + sitecode,
					filename:'Manage Alerts For FPO Site view active alerts',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4],
						margin: true,
						modifier:{
							selected:true
							},
      						},
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['12%','22%','22%','22%','22%'];
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
					},},
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Manage Alerts For FPO Site view active alerts @ ' + sitecode + '\n\r' + datetime ,
					filename:'Manage Alerts For FPO Site view active alerts',	
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}], 
	
	
});


$('#processtable').DataTable().on('order.dt search.dt', function() {
	$('#processtable').DataTable().column(0, {
		search: 'applied',
		order: 'applied'
	}).nodes().each(function(cell, i) {
		cell.innerHTML = i + 1;
	});
}).draw();


var fromdate = $("#reloadfromdate").val();
var todate = $("#reloadtodate").val();


/*
$("#fromdate").datepicker({
	dateFormat: "dd/mm/yy",
	onSelect: function(date) {
		$("#todate").datepicker('option', 'minDate', date);

	}
});
$("#todate").datepicker({
	dateFormat: "dd/mm/yy",
});

$('#fromdate').datepicker('setDate', todate);
$("#todate").datepicker('option', 'minDate', todate);
$('#todate').datepicker('setDate', todate);
*/


 var currentDate= new Date();
  $("#fromdate").datepicker({
	dateFormat: "dd/mm/yy",
	 maxDate: currentDate,
	onSelect: function(date) {
		$("#todate").datepicker('option', 'minDate', date);

	}
});
$("#todate").datepicker({
	dateFormat: "dd/mm/yy",
	 maxDate: currentDate,
	onSelect: function(date) {
		$("#fromdate").datepicker('option', 'maxDate', date);

	}
});

$('#fromdate').datepicker('setDate', todate);
$("#todate").datepicker('option', 'minDate', todate);
$('#todate').datepicker('setDate', todate);


var alertname = 'view';

$('#add').css("display", "none");


$("#processtable").DataTable().columns([5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]).visible(false);


$("#button-list a").click(function() {
	$("#button-list a").removeClass("btn-success");
	$("#button-list a").addClass("btn-outline-success");
	$(this).removeClass("btn-outline-success");
	$(this).addClass('btn-success');
	$(this).addClass('btn-success');
	alertname = $(this).attr('name');

	alertclick(alertname);
});



function alertclick(alertname) {
showLoader();
	
	var oMyForm = new FormData();
	oMyForm.append("alertname", alertname);
	oMyForm.append("fromdate", $('#fromdate').val());
	oMyForm.append("todate", $('#todate').val());


	//showLoader();
	jQuery.ajax({
		url: "getalertdata",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(data) {
			console.log('Tab change inside success');
			
			$("#tablediv").replaceWith(data);
			$("#processtable").DataTable().columns([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]).visible(true);
	if (alertname == 'view') {
				$('#add').css("display", "none");
				$('.date').css("display", "none");

				$('#processtable').dataTable().fnDestroy();
				$("#processtable").dataTable({
					"dom": '<"pull-left"Bl>frtip',
				buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Manage Alerts For FPO Site view active alerts',
					message:'Manage Alerts For FPO Site view active alerts @ ' + sitecode + '\n\r' + datetime, 
					exportOptions: {
        				columns: [ 0,1,2,3,4],
						margin: true,
      						},
					customize: function (doc) {}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4],
      						},
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['12%','22%','22%','22%','22%'];
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
							'\n\r'+'Manage Alerts For FPO Site view active alerts@ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Manage Alerts For FPO Site view active alerts',  },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Manage Alerts For FPO Site view active alerts@ ' + sitecode + '\n\r' + datetime ,
					filename:'Manage Alerts For FPO Site view active alerts',
					exportOptions: {
        				columns: [ 0,1,2,3,4],
      						},
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
               });
hideLoader();
				$("#processtable").DataTable().columns([5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]).visible(false);
			} else if (alertname == 'insertremovealert') {
showLoader();
	
				$('#add').css("display", "block");
				$('.date').css("display", "none");
				
				$('#processtable').dataTable().fnDestroy();
				$("#processtable").dataTable({
					"dom": '<"pull-left"Bl>frtip',
				buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Manage Alerts For FPO Site Insert/Remove alerts',
					message:'Manage Alerts For FPO Site Insert/Remove alerts @ ' + sitecode + '\n\r' + datetime, 
					exportOptions: {
        				columns: [ 0,1,2,3,4,6,9],
						margin: true,
      						},
					customize: function (doc) {}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,6,9],
      						},
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['10%','15%','15%','15%','15%','15%','15%'];
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
							'\n\r'+'Manage Alerts For FPO Site Insert/Remove alerts@ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Manage Alerts For FPO Site Insert/Remove alerts',  },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Manage Alerts For FPO Site view active alerts@ ' + sitecode + '\n\r' + datetime ,
					filename:'Manage Alerts For FPO Site view active alerts',
					exportOptions: {
        				columns: [ 0,1,2,3,4,6,9],
      						},
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
               });
				
				hideLoader();
				$("#processtable").DataTable().columns([5, 7, 8, 10, 11, 12, 13, 14, 15]).visible(false);
			} else if (alertname == 'history') {
			showLoader();
	
				$('#add').css("display", "none");
				$('.date').css("display", "inline-flex");
				
				$('#processtable').dataTable().fnDestroy();
				$("#processtable").dataTable({
					"dom": '<"pull-left"Bl>frtip',
				buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Manage Alerts For FPO Site History',
					message:'Manage Alerts For FPO Site History @ ' + sitecode + '\n\r' + datetime, 
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,7,8,10],
						margin: true,
      						},
					customize: function (doc) {}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					//watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,7,8,10],
      						},
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['10%','15%','15%','15%','15%','9%','7%','7%','7%'];
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
							'\n\r'+'Manage Alerts For FPO Site History@ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Manage Alerts For FPO Site History',  },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Manage Alerts For FPO Site History@ ' + sitecode + '\n\r' + datetime ,
					filename:'Manage Alerts For FPO Site History',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,7,8,10],
      						},
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
               });
				
				$("#processtable").DataTable().columns([6, 9, 11, 12, 13, 14, 15]).visible(false);
			}
hideLoader();
		}
	});


}


function insertpopup() {
	$('#typemodal').modal('show');
	$('textarea').val('');
}



$("#specific").click(function() {
	$('#typemodal').modal('hide');
	$('#insertmodal').modal('show');
	var date = new Date();
	var mindate = moment(date).format("YYYY-MM-DDTkk:mm");
	$('#active_since').attr("min", mindate);
	$('#active_since').val(mindate);



	$('#specifictime').css("display", "none");
	$('#countryerr').css("display", "none");
	$('#alerterr').css("display", "none");
	$('#reasonerr').css("display", "none");
});



$("#general").click(function() {

	$('#typemodal').modal('hide');
	$('#specificinsertmodal').modal('show');
	var date = new Date();
	var mindate = moment(date).format("YYYY-MM-DDTkk:mm");
	$('#specificactivetime').attr("min", mindate);
	$('#specificactivetime').val(mindate);

	$('#specificactivetimeerr').css("display", "none");
	$('#specificalerterr').css("display", "none");
	$('#specificreasonerr').css("display", "none");
});

function statechange(obj) {
	console.log($(obj).val());

}

function addgenericalertdetail() {

	if ($('#active_since').val() >= moment(new Date()).format("YYYY-MM-DDTkk:mm") && $('#country').val() != null && $('#alert').val() != '' && $('#reason').val() != '') {
		Swal.fire({
			title: 'Please Confirm?',
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#28a745',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes!'
		}).then((result) => {
			var oMyForm = new FormData();
			oMyForm.append("country", $('#country').val());
			oMyForm.append("mailclass", $('#mailclass').val());
			oMyForm.append("itemcat", $('#itemcat').val());
			oMyForm.append("alert", $('#alert').val());
			oMyForm.append("reason", $('#reason').val());
			oMyForm.append("active_since", $('#active_since').val());

			//showLoader();
			jQuery.ajax({
				url: "addgenericalertdetail",
				global: false,
				data: oMyForm,
				dataType: 'text',
				processData: false,
				contentType: false,
				type: 'POST',

				success: function(data) {
					/*var marker = JSON.stringify(data);
					var json = jQuery.parseJSON(marker);
					json=JSON.parse(json);*/


					$('#insertmodal').modal('hide');
					alertclick(alertname);
				}
			});
		});
	} else {

		if ($('#active_since').val() <= moment(new Date()).format("YYYY-MM-DDTkk:mm")) {
			$('#specifictime').css("display", "block");
		} if ($('#country').val() == null) {
			$('#countryerr').css("display", "block");

		} if ($('#alert').val() == '') {
			$('#alerterr').css("display", "block");

		} if ($('#reason').val() == '') {
			$('#reasonerr').css("display", "block");

		}

	}



}
function addspecificalertdetail() {
	if ($('#specificactivetime').val() >= moment(new Date()).format("YYYY-MM-DDTkk:mm") && $('#specificalert').val() != '' && $('#specificreason').val() != '') {
		Swal.fire({
			title: 'Please Confirm?',
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#28a745',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes!'
		}).then((result) => {
			if (result.isConfirmed) {
				var oMyForm = new FormData();
				oMyForm.append("alert", $('#specificalert').val());
				oMyForm.append("reason", $('#specificreason').val());
				oMyForm.append("active_since", $('#specificactivetime').val());


				//showLoader();
				jQuery.ajax({
					url: "addspecificalertdetail",
					global: false,
					data: oMyForm,
					dataType: 'text',
					processData: false,
					contentType: false,
					type: 'POST',

					success: function(data) {
						/*var marker = JSON.stringify(data);
						var json = jQuery.parseJSON(marker);
						json=JSON.parse(json);*/


						$('#specificinsertmodal').modal('hide');
						alertclick(alertname);
					}
				});
			}
		});
	} else {

		if ($('#specificactivetime').val() <= moment(new Date()).format("YYYY-MM-DDTkk:mm")) {
			$('#specificactivetimeerr').css("display", "block");
		} if ($('#specificalert').val() == '') {
			$('#specificalerterr').css("display", "block");

		} if ($('#specificreason').val() == '') {
			$('#specificreasonerr').css("display", "block");

		}

	}
}

function removealertpopup(obj) {
	$('#removeinsertmodal').modal('show');

	$('#mandreason').css('display', 'none');
	$('#removeid').val($(obj).attr('data-id'));
}
function removealert() {

var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#removereason').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }

	if ($('#removereason').val().length > 0) {
		Swal.fire({
			title: 'Are you sure to Remove The Alert?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#28a745',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes!'
		}).then((result) => {
			if (result.isConfirmed) {
				var oMyForm = new FormData();
				oMyForm.append("id", $('#removeid').val());
				oMyForm.append("removereason", $('#removereason').val());


				//showLoader();
				jQuery.ajax({
					url: "removealert",
					global: false,
					data: oMyForm,
					dataType: 'text',
					processData: false,
					contentType: false,
					type: 'POST',

					success: function(data) {
						/*var marker = JSON.stringify(data);
						var json = jQuery.parseJSON(marker);
						json=JSON.parse(json);*/




						$('#removeinsertmodal').modal('hide');
						alertclick(alertname);
					}
				});
			}
		});
	} else {
		$('#mandreason').css('display', 'block');
	}


}

function datesubmit() {
	alertclick(alertname);
}

$(function() {


	$('.multiselect')
		.multiselect({
			allSelectedText: 'All',
			maxHeight: 200,
			includeSelectAllOption: true
		})
		.multiselect('selectAll', true)
		.multiselect('updateButtonText');
});



$('#processtable').on('click', '.detailpopup', function() {

	var tr = $(this).closest("tr");
	var data = $("#processtable").DataTable().row(tr).data();
	$('#alertnoinfo').text(data[2]);
	$('#activeinfo').text(data[6]);
	$('#alertinfo').text(data[11]);
	$('#reasoninfo').text(data[12]);
		$('#cntryinfo').text(data[13]);
		$('#mailclsinfo').text(data[14]);
		$('#itemcatinfo').text(data[15]);
	
	getdetails(data[2],data[1]);

});

function getdetails(id,type) {


				var oMyForm = new FormData();
				oMyForm.append("id", id);


				//showLoader();
				jQuery.ajax({
					url: "getalertdetails",
					global: false,
					data: oMyForm,
					dataType: 'text',
					processData: false,
					contentType: false,
					type: 'POST',

					success: function(data) {
						var marker = JSON.stringify(data);
						var json = jQuery.parseJSON(marker);
						json=JSON.parse(json);




	if (type == 'Specific') {
		$('#specificcontent').css("display", "block");
		$('#mailclsinfo').text(json.mailclass);
		$('#itemcatinfo').text(json.itemcat);
	} else {
		$('#specificcontent').css("display", "none");
	}

	$('#infomodal').modal('show');


						
					}
				});
			


}

$("#active_since").on("change", function() {

	if ($('#active_since').val() >= moment(new Date()).format("YYYY-MM-DDTkk:mm")) {
		$('#specifictime').css("display", "none");
	} else {
		$('#specifictime').css("display", "block");
	}
});



$("#country").on("change", function() {

	if ($('#country').val() != null) {
		$('#countryerr').css("display", "none");
	} else {
		$('#countryerr').css("display", "block");
	}
});

$("#alert").on("change", function() {

	if ($('#alert').val() != '') {
		$('#alerterr').css("display", "none");
	} else {
		$('#alerterr').css("display", "block");
	}
});

$("#reason").on("change", function() {

	if ($('#reason').val() != '') {
		$('#reasonerr').css("display", "none");

	} else {
		$('#reasonerr').css("display", "block");
	}
});

$("#specificalert").on("change", function() {

	if ($('#specificalert').val() != '') {
		$('#specificalerterr').css("display", "none");

	} else {
		$('#specificalerterr').css("display", "block");
	}
});

$("#specificreason").on("change", function() {

	if ($('#specificreason').val() != '') {
		$('#specificreasonerr').css("display", "none");

	} else {
		$('#specificreasonerr').css("display", "block");
	}
});



$("#specificactivetime").on("change", function() {

	if ($('#specificactivetime').val() >= moment(new Date()).format("YYYY-MM-DDTkk:mm")) {
		$('#specificactivetimeerr').css("display", "none");
	} else {
		$('#specificactivetimeerr').css("display", "block");
	}
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
