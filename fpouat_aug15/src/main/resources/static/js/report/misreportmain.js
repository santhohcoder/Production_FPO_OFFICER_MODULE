
$(document).ready(function (){
	$('#mailclass').multiselect({
    allSelectedText: 'All',
    maxHeight: 200,
    includeSelectAllOption: true
});
$('#fposite').multiselect({
    allSelectedText: 'All',
    maxHeight: 200,
    includeSelectAllOption: true
});
$('#itemcat').multiselect({
    allSelectedText: 'All',
    maxHeight: 200,
    includeSelectAllOption: true
});
	
	});
	
	$(document).ready(function(){
	$('#mailclass').multiselect('selectAll', false);
    $('#mailclass').multiselect('updateButtonText');
$('#fposite').multiselect('selectAll', false);
    $('#fposite').multiselect('updateButtonText');
	
		runquery(1);
})

	

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
           		
					filename:'Total no of articles processed and cleared for the selected period  ',

     },
			
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
	});
	
	$("#ExcelExport").on('click', function() {
		$(".buttons-excel").trigger('click');
		this.blur();
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


 var currentDate = new Date();  
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
var startdate = moment(new Date()).subtract(1, 'months').startOf('month').format('DD/MM/YYYY');
	var lastdate = moment(new Date()).subtract(1, 'months').endOf('month').format('DD/MM/YYYY');
		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);
		

//written by santhosh
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
	var startdate = moment(new Date()).subtract(1, 'months').startOf('month').format('DD/MM/YYYY');
	var lastdate = moment(new Date()).subtract(1, 'months').endOf('month').format('DD/MM/YYYY');
		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);

*/
function runquery(intial) {
	
	
	/* TO NOTE  
	if the value of initial is one (1)--->  Data will not come,  only text will be displayed
	if the value of initial is zero (0)--->  Data will  come,  Required table wil be displayed 
	*/
	
	console.log("at raiseQry" + buttonName)
	console.log("checkinggg",consoleButton)
	
	var initial=intial;
	console.log(initial);
	var oMyForm = new FormData();
	
		$("#mailclassdiv").css("display", "block");
		$("#fpositediv").css("display", "inline-flex");
	
		var report = $('#report').val();
		$("#itemcatdiv").css("display", "none");
		$("#ssodiv").css("display", "none");
		

		oMyForm.append("mailclass", $('#mailclass').val());
		oMyForm.append("fposite", $('#fposite').val());

		var res = 0;

		var mailclass = $('#mailclass').val(); 
		var itemcat = $('#itemcat').val();
		var fposite = $('#fposite').val();
		if (fposite === null )res = 4;
		
	//1
	if (report == '1. Total no of articles processed and cleared for the selected period (OOC Given)') {
		$('#daterange').text('(Based on OOC Date)');
		url = 'misreportdatechange';
		
	//2	
	} else if (report == '2. Total no of Articles pending in various queues (OOC not given articles)') {
		$('#daterange').text('(Based on Posting Date)');
		url = 'misreportoocpending';
	}//3
	 else if (report == '3. Total no. of Article IDs received as EAD without PINCODE and mapped to this site') {
		$('#daterange').text('(Based on FPO ALLOT Date)');
		url = 'misreportwithoutpincode';
	} //4
	else if (report == '1. Total no of pending Articles for which Query is raised and status ( only for 1st query)') {
		$('#daterange').text('(Based on Dcall Generated  Date)');
		url = 'misreportqryraised';
	}//5 
	else if (report == '2. Total no of pending Articles for which more than one query is raised and status') {
		$('#daterange').text('(Based on Dcall Generated  Date)');
		url = 'misreportaddlqryraised';
	} 
	//6
	else if (report == '6. Top IMPORTS - Mail Class- countries of Postal Imports at the FPO site for last month or any select period ( based on EAD data received for select period)') {
		 $('#daterange').text('(Based on Posting Date)');
		url = 'misreportbymailclass';
	}
	//7 
	else if (report == '7. Top Imports - Item Category - countries of Postal Imports at the FPO site for last month or any select period - for Item Catgeories ( based on EAD data received for select period)') {
		 $('#daterange').text('(Based on Posting Date)');
		$("#itemcatdiv").css("display", "block");
		oMyForm.append("itemcat", $('#itemcat').val());
	
		if (itemcat == null ){res = 3;}
		url = 'misreportbyitemcat';
	} 
	//8
	else if (report == '1. Top Duty - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date )') {
		$('#daterange').text('(Based on OOC Date)');
		$("#mailclassdiv").css("display", "none"); 
		
		mailclass=1;
		url = 'misreportoocgivencountries';
	} 
	//9
	else if (report == '2. Top Duty - Mail Class - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date )') {
		$('#daterange').text('(Based on OOC Date)');
		url = 'mismailclassreportoocgivencountries';
	} 
	//10
	else if (report == '4. Total no. of Articles given OOC during last month or any select period and Delivery status') {
		 $('#daterange').text('(Based on OOC Date)');
		$("#mailclassdiv").css("display", "none");
		mailclass=1;
		url = 'misreportoocgivendelistatus';
	} 
	//11
	else if (report == '5. Articles given OOC during last month or any select period and Delivery status') {
		$('#daterange').text('(Based on OOC Date)');
		url = 'misreportoocgivendelistatusDetails';
	} 
	//12
	else if (report == '1. No. of Receptacle IDs processed at the OOE for last month or for any select period') {
		$('#daterange').text('(Based on Article Physically Received Date at OOE)');
		$("#mailclassdiv").css("display", "none");
		mailclass=1;
		url = 'misreportreceptacleprocess';
	} 
	//13
	else if (report == '2. Receptacle IDs processed at the OOE for last month or for any select period') {
	$('#daterange').text('(Based on Article Physically Received Date at OOE)');
		$("#mailclassdiv").css("display", "none");
		mailclass=1;
		url = 'misreportooereceptacleprocessdetails';
	} 
	//14
	else if (report == '3. No. of Bags processed at the FPO for last month or for any select period') {
		$('#daterange').text('(Based on Article Physically Received Date at FPO)');
		$("#mailclassdiv").css("display", "none");
		mailclass=1;
		url = 'misreportfporeceptacleprocess';
	} //15
	else if (report == '4. Bags processed at the FPO for last month or for any select period') {
	$('#daterange').text('(Based on Article Physically Received Date at FPO)');
	$("#mailclassdiv").css("display", "none");
	mailclass=1;
		url = 'misreportfporeceptacleprocessdetails';
	}
	//16
	 else if (report == '1. No of articles detained and processed during last month or for any select period') {
	$('#daterange').text('(Based on Article Detained Date)');
	$("#mailclassdiv").css("display", "none");
		url = 'misreportartcdetained';
		mailclass=1;
	}
	//17
	else if (report == '1. Article Status - Enter Article/ Item ID') {
		$("#mailclassdiv").css("display", "none");
		$(".datesel").css("display", "none");
		mailclass=1; res=0;
		$("#fpositediv").css("display", "none");
		url = 'misreportarticlestatus';
	} 
	//18
	else if (report == '8. No. of articles - OOC cancelled during last month or for any select period') {
	$('#daterange').text('(Based on OOC Date)');
	$("#mailclassdiv").css("display", "none");	
	mailclass=1;
		url = 'misreportooccancel';
	} 
	//19
	else if (report == '9. No. of Art/Item ID which are yet to arrive at FPO as on date') {
		
		if(initial == 1){
			oMyForm.set("mailclass", "none");
		}
		$(".datesel").css("display", "none");
		url = 'misreportarrivefpo';
	} 
	//20
	else if (report == '2. List of Art/Item IDs processed by an officer for any select period (Enter SSOID and Roles) (OOC given articles)') {
			 $('#daterange').text('(Based on OOC Date)');
			$("#ssodiv").css("display", "block");
			$("#namediv").css("display", "none");
			oMyForm.append("ssoid", $('#ssoid').val());
				if ($('#ssoid').val() != "") {
			res = 2;
		}

		url = 'misreportooficerarticle';
	} 
	//21
	else if (report == '10. Total no. of Article IDs Received as EAD during the period (Countrywise / Mail-Classwise / Item-Categorywise)') {
		 $('#daterange').text('(Based on EAD Received Date)');
		$("#itemcatdiv").css("display", "block");
		oMyForm.append("itemcat", $('#itemcat').val());
		if (itemcat == null ){res = 3;}
		url = 'misreporttoead';
	}
	//22
	else if (report == '1. Total no.of Article IDs Pending in Commercial Queue') {
		$('#daterange').text('(Based on Query Generated Date)');
		url = 'misreportcommercial';
	}
//23
else if (report == '2. List of Article IDs Processed under Commercial Imports') {
		 $('#daterange').text('(Based on Posting Date)');
		$("#mailclassdiv").css("display", "none");
		url = 'misreportcommercialunderprocess';
	}
	//24
	else if(report=='1. Duty-Forgone - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date ) with Notification Wise'){
		 $('#daterange').text('(Based on OOC Date)');
		$("#mailclassdiv").css("display", "none"); 
		mailclass=1;
		$("#notndiv").css("display", "block");
		url = 'misreportoocgivencountriesnotfwise';
	}
	//25
	else if (report == '3. Query Reply Status') {
		$('#daterange').text('(Based on Dcall Generated Date)');
		$("#mailclassdiv").css("display", "none");
		mailclass=1;
		url = 'queryreplystatuss';
	}
	//26
	else if (report == '3. Top Duty - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date ) with Delivery Status') {
		$('#daterange').text('(Based on OOC Date)');
		$("#mailclassdiv").css("display", "none"); 
		
		mailclass=1;
		url = 'misreportoocgivencountriesdelstatus';
	}
	//27
	else if (report == '4. Top Duty - Mail Class - countries of Postal Imports at the FPO Site for last month or any select period ( OOC given date ) with Delivery Status') {
	$('#daterange').text('(Based on OOC Date)');
		$("#mailclassdiv").css("display", "none"); 
		
		mailclass=1;
		url = 'mismailclassreportoocgivencountriesdelstatus';
	}
	if (mailclass == null )res = 1;
	if (res == 4)
			($('#fposite').val()===null ) ? res = 5 : res = 0;
			
	if (res == 0) {
if(initial==0){
	oMyForm.append("fromdate", $('#fromdate').val());
	oMyForm.append("todate", $('#todate').val());
	}
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
  console.log("value of initial checking",initial);
console.log(initial,report,buttonName,res);



    if (initial == 1 && report != '9. No. of Art/Item ID which are yet to arrive at FPO as on date' && buttonName == 'button-Group') {
	
    $("#queryQueueshow").hide();
	$("#noreport").text(" Please Choose the dates and Click RunQuery To display the Data");
	$(".datesel").css("display", "flex");
  
	
	if(report=='2. List of Art/Item IDs processed by an officer for any select period (Enter SSOID and Roles) (OOC given articles)'){
		$("#noreport").text(" Please Enter SSOID , Choose dates  and Click RunQuery To display the Data");
    }

	/*if(report!='1. Article Status - Enter Article/ Item ID'){
       $(".datesel").css("display", "flex");
    }*/

	if(report=='1. Article Status - Enter Article/ Item ID'){
	   $("#queryQueueshow").replaceWith(data);
	   $("#processtablediv").css("display", "none");
       $(".datesel").css("display", "none");
	   $("#noreport").text("Please Enter the Article ID and Click Submit");
    }

	$("#noreportdiv").css("display", "block");
	
   } 

else{
	
	$("#queryQueueshow").replaceWith(data);
	
	if(initial==1 && buttonName == 'button-Group'){
		$("#submain").css("display", "none");
		$("#noreport").text("Please choose the Mail Class and Click Runquery to display the data");
  		$("#noreportdiv").css("display", "block");
	}
	
	else  if(initial==0 && report=='2. List of Art/Item IDs processed by an officer for any select period (Enter SSOID and Roles) (OOC given articles)'){
		$("#noreport").text(" Please Enter SSOID , Choose dates  and Click RunQuery To display the Data");
		$("#noreportdiv").css("display", "block")
    }
	  else if(initial==0){
	 $("#noreportdiv").css("display", "none");
		}
	
	
	
	if(report=='1. Article Status - Enter Article/ Item ID')
       $(".datesel").css("display", "none !important");

	
	}
	
  hideLoader();
}

		});
	}
	
	 else if(res == 2){ 
		if(initial==0){
	oMyForm.append("fromdate", $('#fromdate').val());
	oMyForm.append("todate", $('#todate').val());
	}
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
				
				console.log(initial,report,buttonName);
				console.log('inside succes of res=2');
				console.log($("#rolesCountSSOID").val())
				console.log($('#ssoid').val());
				
					 $("#queryQueueshow").replaceWith(data);
				console.log($("#rolesCountSSOID").val())
					$("#noreportdiv").css("display", "none");
					
					if($('#rolesCountSSOID').val() == 1){
					$("#queryQueueshow").replaceWith(data);
					}
				
				else{
					swal('OOPS!', 'Invalid SSOID !', 'error');
				}				
			
			hideLoader();
			} 
		});
	}
	else {

		if(mailclass == null )
			swal('OOPS!', 'Select atleast one Mail Class Category ', 'error');
		else if (res==5) 
			swal('OOPS!', 'Select atleast one FPO Site Category ', 'error');
		else if(res==3 )
			swal('OOPS!', 'Select atleast one Item Category ', 'error');
		
		
	}
}



	$("#report").on("change",function(){
		
	
	$('#mailclass').multiselect('selectAll', false);
    $('#mailclass').multiselect('updateButtonText');
	$('#itemcat').multiselect('selectAll', false);
    $('#itemcat').multiselect('updateButtonText');
	$('#fposite').multiselect('selectAll', false);
    $('#fposite').multiselect('updateButtonText');

		runquery(1);
		
	
	})	;
/*
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
			||  report == '21.Total no. of Article IDs Received as EAD during last month or for any select period (Countrywise / Mail-Classwise / Item-Categorywise)'
				) {

		$('#fromdate').datepicker('setDate', startdate);
		$("#todate").datepicker('option', 'minDate', startdate);
		$('#todate').datepicker('setDate', lastdate);
	}
	runquery();
});
*/
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
         

