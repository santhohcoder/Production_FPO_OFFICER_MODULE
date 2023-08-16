$(document).ready(function() {
	
	$("#processtable").dataTable({
		"processing": true,
		"paging": false,
		scrollY: 320,
		scrollX: true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": 'Bfrtip',
	/*	buttons: [
			'excel', 'pdf', 'print'
		] */
		buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' Total no of articles processed and cleared for the selected period ',
					message:'  Total no of articles processed and cleared for the selected period @ ' + sitecode + '\n\r' + datetime, 
					customize: function (doc) {
					}},
				//pdf
    			  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
					watermark:'fpo',
                    className: ' btn btn-success mb-3 ',				
				    orientation: 'landscape',
				    
		   		    customize: function(doc, header) {
		   		    // doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		   		    doc.content[1].table.widths = ['3%','8%','6%','6%','9%','7%','7%','4%','4%','4%','4%','4%','4%','4%','4%','4%','10%','8%'];
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
					//margin:[0,20,0,20],
					//watermark:'fpo', 
					},],});
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: ' + empid, bold: true,
					alignment: 'left',
					fontSize: 7,
					//margin:[0,0,90,20],
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
							'\n\r'+'Total no of articles processed and cleared for the selected period @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Total no of articles processed and cleared for the selected period  ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' Total no of articles processed and cleared for the selected period  @ ' + sitecode + '\n\r' + datetime ,
					filename:'Total no of articles processed and cleared for the selected period ',
					customize: function ( win ) {
						
						
						$(win.document.body).css( 'font-size', '8pt' );
						$(win.document.body)
						.addClass( 'compact' )
						.find( 'table' ).css( 'font-size', 'inherit' );
                        //alert("cms here");
						},}],
			  "initComplete": function( settings ) {
	                   $("#processtable_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearprocesstable()" '+
			                 'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
		              $("#processtable_filter input[type='search']").attr('id', 'searchprocesstable');
    	           }


		
	});
	
	
	
	
	$("#ExcelExport").on('click', function() {
		$(".buttons-excel").trigger('click');
		this.blur();
	});


	$(function() {


		$('.multiselect')
			.multiselect({
				allSelectedText: 'All',
				maxHeight: 200,
				includeSelectAllOption: true
			})
			.multiselect('selectAll', false)
			.multiselect('updateButtonText');
	});



	$('#processtable').DataTable().on('order.dt search.dt', function() {
		$('#processtable').DataTable().column(0, {
			search: 'applied',
			order: 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();


  

});


	 function clearprocesstable(){
    document.getElementById("searchprocesstable").value=""
    var table = $('#processtable').DataTable();
    table.search('').draw();
}








	$("#fromdate").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate: new Date(),
	onSelect: function(date) {
		$("#todate").datepicker('option', 'minDate', date);
		$("#todate").datepicker('option', 'maxDate', null); // Reset maxDate option
		var selectedDate = $(this).datepicker('getDate'); // Get selected date from #fromdate
		if (selectedDate != null) {
			// Set maxDate option of #todate to today's date
			$("#todate").datepicker('option', 'maxDate', new Date());
		}
	}
	
});

$("#todate").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate: new Date() // Set the initial maxDate to today's date
});
	var startdate = moment(new Date()).subtract(1, 'months').startOf('month').format('DD/MM/YYYY');
	var lastdate = moment(new Date()).subtract(1, 'months').endOf('month').format('DD/MM/YYYY');
	
		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);

function runquery() {
	var oMyForm = new FormData();

	var url = 'misreportdatechange';
	var report = $('#report').val();
		
		$("#mailclassdiv").css("display", "none");
		$("#itemcatdiv").css("display", "none");
		$("#ssodiv").css("display", "none");

	var res = 0;

	if (report == '1. Total no of articles processed and cleared for the selected period (OOC Given)') {
		url = 'misreportdatechange';
	} else if (report == '2. Total no of Articles pending in various queues (OOC not given articles)') {
		url = 'misreportoocpending';
	} else if (report == '3. Total no. of Article IDs received as EAD without PINCODE and mapped to this site') {
		url = 'misreportwithoutpincode';
	} else if (report == '4. Total no of pending Articles for which Query is raised and status ( only for 1st query)') {
		url = 'misreportqryraised';
	} else if (report == '5. Total no of pending Articles for which more than one query is raised and status') {
		url = 'misreportaddlqryraised';
	} else if (report == '6. Top IMPORTS - Mail Class- countries of Postal Imports at the FPO site for last month or any select period ( based on EAD data received for select period)') {

		
		$("#mailclassdiv").css("display", "block");

		if ($('#mailclass').val() == null) {
			res = 1;
		}

		url = 'misreportbymailclass';
		oMyForm.append("mailclass", $('#mailclass').val());
	} else if (report == '7. Top Imports - Item Category - countries of Postal Imports at the FPO site for last month or any select period - for Item Catgeories ( based on EAD data received for select period)') {

		
		$("#mailclassdiv").css("display", "block");
		$("#itemcatdiv").css("display", "block");



		if ($('#mailclass').val() == null || $('#itemcat').val() == null) {
			res = 1;
		}
		oMyForm.append("mailclass", $('#mailclass').val());
		oMyForm.append("itemcat", $('#itemcat').val());

		url = 'misreportbyitemcat';
	} else if (report == '8. Top Duty - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date )') {


		url = 'misreportoocgivencountries';
	} else if (report == '9. Top Duty - Mail Class - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date )') {


		url = 'mismailclassreportoocgivencountries';
	} else if (report == '10. Total no. of Articles given OOC during last month or any select period and Delivery status') {


		url = 'misreportoocgivendelistatus';
	} else if (report == '11. Articles given OOC during last month or any select period and Delivery status') {


		url = 'misreportoocgivendelistatusDetails';
	} else if (report == '12.No. of Receptacle IDs processed at the OOE for last month or for any select period') {


		url = 'misreportreceptacleprocess';
	} else if (report == '13. Receptacle IDs processed at the OOE for last month or for any select period') {


		url = 'misreportooereceptacleprocessdetails';
	} else if (report == '14. No. of Bags processed at the FPO for last month or for any select period') {


		url = 'misreportfporeceptacleprocess';
	} else if (report == '15. Bags processed at the FPO for last month or for any select period') {


		url = 'misreportfporeceptacleprocessdetails';
	} else if (report == '16. No of articles detained and processed during last month or for any select period') {


		url = 'misreportartcdetained';
	}else if (report == '17. Article Status - Enter Article/ Item ID') {


		url = 'misreportarticlestatus';
	} else if (report == '18. No. of articles - OOC cancelled during last month or for any select period') {


		url = 'misreportooccancel';
	} else if (report == '19. No. of Art/Item ID which are yet to arrive at FPO as on date') {


		url = 'misreportarrivefpo';
	} else if (report == '20. List of Art/Item IDs processed by an officer for any select period (Enter SSOID and Roles) (OOC given articles)') {
			$("#ssodiv").css("display", "block");
			oMyForm.append("ssoid", $('#ssoid').val());
				if ($('#ssoid').val() == null) {
			res = 2;
		}

		url = 'misreportooficerarticle';
	} /*else if (report == '21. No of Art/Item IDs for which D-Call letter is sent again in email for any select period') {


		url = 'misreportdcall';
	}*/
	else if (report == '21.Total no. of Article IDs Received as EAD during last month or for any select period (Countrywise / Mail-Classwise / Item-Categorywise)') {


		url = 'misreporttotead';
	}

	if (res == 0) {

	oMyForm.append("fromdate", $('#fromdate').val());
	oMyForm.append("todate", $('#todate').val());
	oMyForm.append("report", $('#report').val());
	oMyForm.append("itemid", $('#articleid').val());
	oMyForm.append("ssoid", $('#ssoid').val());
	oMyForm.append("roles", $('#role').val());
		showLoader();
		jQuery.ajax({
			url: url,
			global: false,
			data: oMyForm,
			dataType: 'text',
			processData: false,
			contentType: false,
			type: 'POST',

			success: function(data) {
				console.log('inside success');


				$("#queryQueueshow").replaceWith(data);


				hideLoader();


			}
		});
	} else {
		if (report == 'Top IMPORTS - Mail Class- countries of Postal Imports at the FPO site for last month or any select period ( based on EAD data received for select period)') {
			swal('OOPS!', 'Select atleast one MailClass !', 'error');
		} else if (report == 'Top Imports - Item Category - countries of Postal Imports at the FPO site for last month or any select period - for Item Catgeories ( based on EAD data received for select period)') {
			swal('OOPS!', 'Select atleast any one MailClass and ItemCategory !', 'error');
		}
	//	else if (report == 'List of Art/Item IDs processed by an officer for any select period (Enter SSOID and Roles) (OOC given articles)') {
	//		swal('OOPS!', 'Enter SSO ID !', 'error');
	//	}

	}
}

$("#report").on("change", function() {
	
	var report = $('#report').val();
	var startdate = moment(new Date()).subtract(1, 'months').startOf('month').format('DD/MM/YYYY');
	var lastdate = moment(new Date()).subtract(1, 'months').endOf('month').format('DD/MM/YYYY');

	
	 if (report == '6. Top IMPORTS - Mail Class- countries of Postal Imports at the FPO site for last month or any select period ( based on EAD data received for select period)'
			|| 	report == '7. Top Imports - Item Category - countries of Postal Imports at the FPO site for last month or any select period - for Item Catgeories ( based on EAD data received for select period)'
			||	report == '8. Top Duty - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date )'
			||	report == '9. Top Duty - Mail Class - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date )'
			||	report == '10. Total no. of Articles given OOC during last month or any select period and Delivery status'
			||	report == '11. Articles given OOC during last month or any select period and Delivery status'
			||	report == '12. No. of Receptacle IDs processed at the OOE for last month or for any select period'
			||	report == '13. Receptacle IDs processed at the OOE for last month or for any select period'
			||	report == '14. No. of Bags processed at the FPO for last month or for any select period'
			||	report == '15. Bags processed at the FPO for last month or for any select period'
			||	report == '16. No of articles detained and processed during last month or for any select period'
			||  report == '18. No. of articles - OOC cancelled during last month or for any select period'
			||  report == '19. No. of Art/Item ID which are yet to arrive at FPO as on date'
			||  report == '20. List of Art/Item IDs processed by an officer for any select period (Enter SSOID and Roles) (OOC given articles)'
			//||  report == 'No of Art/Item IDs for which D-Call letter is sent again in email for any select period'
			||  report == '21.Total no. of Article IDs Received as EAD during last month or for any select period (Countrywise / Mail-Classwise / Item-Categorywise)'
				) {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);
		
	}/* else if (report == 'Top Imports - Item Category - countries of Postal Imports at the FPO site for last month or any select period - for Item Catgeories ( based on EAD data received for select period)') {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);
		

	} else if (report == 'Top Duty - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date )') {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);

	} else if (report == 'Total no. of Articles given OOC during last month or any select period and Delivery status') {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);

	} else if (report == 'No. of Receptacle IDs processed at the OOE for last month or for any select period') {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);

	} else if (report == 'Receptacle IDs processed at the OOE for last month or for any select period') {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);

	} else if (report == 'No. of Bags processed at the FPO for last month or for any select period') {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);

	} else if (report == 'Bags processed at the FPO for last month or for any select period') {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);

	} else if (report == 'No of articles detained and processed during last month or for any select period') {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);
	}*/
	runquery();
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





	function getdatetime(){
		let hh = date.getHours();
		let mm = date.getMinutes();
		let ss = date.getSeconds();
		//let session = "AM";


		/*if(hh > 12){
		  //  hh = hh - 12;
			session = "PM";
		 }*/

		hh = (hh < 10) ? "0" + hh : hh;
		mm = (mm < 10) ? "0" + mm : mm;
		ss = (ss < 10) ? "0" + ss : ss;


		var today = new Date();
		var dd = today.getDate();

		var month = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		if (dd < 10) {
			dd = '0' + dd;
		}

		if (month < 10) {
			month = '0' + month;
		}
		today = dd + '/' + month + '/' + yyyy;

		let time = today + " " + hh + ":" + mm + ":" + ss;
		$('#datetime').val(time);
	}
         

