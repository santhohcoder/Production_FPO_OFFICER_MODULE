var returnbyac;
var flag = $("#flag").val();
const urlParams = new URLSearchParams(window.location.search);
var ead_list_page = urlParams.get('ead_list_page');

$(document).ready(function() {	
	showLoader();
	if($("#true").val() == true)
	{
	    $('#allinitview').show();
		
	}

//var flag = $("#flag").val();	
	if (flag == "showPAC" && !ead_list_page){	
		$('.hidden-menu').slideToggle("slow"); 	
		$('#aprview').slideToggle("slow");	
		$('.aside-menu').hide();		
		$('#asideaprview').hide();	
		$('#allinitview').toggleClass('hiddenac');	
		$('#clear').toggleClass('hiddenac');	
	}
	else if (flag =="showExp" && !ead_list_page)	
	{	
		sessionStorage.setItem('btnclick', JSON.stringify(true));
		bulkexpcont();
		
		}	
	else if(flag =="showDemin" && !ead_list_page)	
	{
		sessionStorage.setItem('btnclick', JSON.stringify(true));
		bulkcont();
		}	
	else if (flag =="showdoc" && !ead_list_page)	
	{
		sessionStorage.setItem('btnclick', JSON.stringify(true));
		bulkdocuments();
		
		}	
	
	showLoader();
	});
	


$(document).ready(function() {
	$('#backeadlist').css('display','none');
if ( ($('#role').val()=="PAO") || ($('#role').val()=="PAC") )
{	
	$('#show-hidden-menu').click(function() {
		$('#returnbyac').val("showPAC");	
	    returnbyac = $('#returnbyac').val();
		if(!$('.aside-menu').is(":hidden")){
			$('#show-aside-menu').click();
		}
		$('.hidden-menu').slideToggle("slow");
		$('#aprview').slideToggle("slow");
		$('.aside-menu').hide();	
		$('#asideaprview').hide();
		//$('#allinitview').hide();
		$('#allinitview').toggleClass('hiddenac');
		$('#clear').toggleClass('hiddenac');
		
		/*if(!$('#bulkview').is(":hidden"))
		     $('#bulkview').hide();*/
		
		
    if ($('#bulkexpview').hasClass('hiddenac') && $('#bulkexpview').is(":hidden") && !$('#allinitview').hasClass('hiddenac')  || $('#bulkview').hasClass('hiddenac') &&  $('#bulkview').is(":hidden") && !$('#allinitview').hasClass('hiddenac') || $('#itdocview').hasClass('hiddenac') && $('#itdocview').is(":hidden") && !$('#allinitview').hasClass('hiddenac') )		{
			$('#bulkexpview').toggleClass('hiddenac');
			//$('#bulkview').toggle();
			$('#bulkview').toggleClass('hiddenac');
			$('#itdocview').toggleClass('hiddenac');
			$('#allinitview').toggleClass('hiddenac');
		}
		else if(!$('#bulkexpview').is(":hidden") && !$('#allinitview').hasClass('hiddenac') || !$('#bulkview').is(":hidden") && !$('#allinitview').hasClass('hiddenac') || !$('#itdocview').is(":hidden") && !$('#allinitview').hasClass('hiddenac')) 
		{
			$('#allinitview').toggleClass('hiddenac');
			$('#bulkexpview').toggleClass('hiddenac');
			$('#bulkview').toggleClass('hiddenac');
			$('#itdocview').toggleClass('hiddenac');
		}
		else if (!$('#itdocview').is(":hidden") || !$('#bulkview').is(":hidden") || !$('#bulkexpview').is(":hidden") ){
			$('#itdocview').toggleClass('hiddenac');
			$('#bulkexpview').toggleClass('hiddenac');
			$('#bulkview').toggleClass('hiddenac');
			
		}
		
		
		
		
		
		
	});
	$('#showmacathidden').click(function() {
		$('.macathidden').slideToggle("slow");
		$('.itcathidden').hide();
	});
	$('#showitcathidden').click(function() {
		$('.macathidden').hide();
		$('.itcathidden').toggle("slow");//, function(){
		
		//});
	});
	
	
$('#bulkdoc').click(function() {	
		$('#blkassmsg').html("You are selecting the Bulk processing option for 'De minimis' Article IDs where the System calculated duty is ZERO, based on the value declared in EAD. <br><br><h2>System by default processes the inbound postal article's Electronic Advance Data (EAD) for tariff heading '9804' as intended for Personal Use.</h2><br><font size=2>(However, To modify assessment, raise query and/or examination order for any article Id, select Process EAD tab against the respective Article ID. )</font> <br> Do you want to proceed?");	
		$('#bulkconf').modal('show');	
	});	
		
	$('#bulkass').click(function() {	
		$('#blkexpassmsg').html("You are selecting the Bulk Assessment  option for Article IDs with the system calculated duty.<br><br><h2>System by default processes the inbound postal article's Electronic Advance Data (EAD) for tariff heading '9804' as intended for Personal Use.</h2><br><font size=2>(However, To modify assessment, raise query and/or examination order for any article Id, select Process EAD tab against the respective Article ID. ) </font> <br> Do you want to proceed?");	
		$('#bulkexpassconf').modal('show');	
	});
	
	
	$('#processdoc').click(function() {
		$('#blkdocassmsg').html("You are selecting the Bulk processing option for 'Documents' Article IDs where the System calculated duty is ZERO, based on the value declared in EAD. <br><br><h2>System by default processes the inbound postal article's Electronic Advance Data (EAD) for tariff heading '9804' as intended for Personal Use.</h2><br><font size=2>(However, To modify assessment, raise query and/or examination order for any article Id, select Process EAD tab against the respective Article ID. )</font> <br> Do you want to proceed?");	
		$('#bulkdocconf').modal('show');	
	//	bulkdocuments();
	
	});
		/*$(".itcathidden").slideToggle(function() {
  			if($(this).is(':hidden')){
			$(this).closest('div').css('position','relative');
			$(this).closest('div').css('top','15px');
			$(this).closest('div').css('right','0');	
		}else{
			$(this).closest('div').css('position','absolute');
			$(this).closest('div').css('top','112px');
			$(this).closest('div').css('right','25px');
			$(this).closest('div').css('zIndex','999');
		}
		});*/
	$('#show-aside-menu').click(function() {
		returnbyac = "showaSide";
		if(!$('.hidden-menu').is(":hidden")){
			$('#show-hidden-menu').click();
		}
		$('.aside-menu').slideToggle("slow");
		$('#asideaprview').slideToggle("slow");	
		$('.hidden-menu').hide();
		$('#aprview').hide();
		$('#allinitview').toggleClass('hiddenac');
		$('#clear').toggleClass('hiddenac');
		
      if ($('#bulkexpview').hasClass('hiddenac') && $('#bulkexpview').is(":hidden") && !$('#allinitview').hasClass('hiddenac')  || $('#bulkview').hasClass('hiddenac') &&  $('#bulkview').is(":hidden") && !$('#allinitview').hasClass('hiddenac') || $('#itdocview').hasClass('hiddenac') && $('#itdocview').is(":hidden") && !$('#allinitview').hasClass('hiddenac') )	{
			$('#bulkexpview').toggleClass('hiddenac');
			$('#bulkview').toggleClass('hiddenac');
			$('#itdocview').toggleClass('hiddenac');
			$('#allinitview').toggleClass('hiddenac');
		}
		else if(!$('#bulkexpview').is(":hidden") && !$('#allinitview').hasClass('hiddenac') || !$('#bulkview').is(":hidden") && !$('#allinitview').hasClass('hiddenac') || !$('#itdocview').is(":hidden") && !$('#allinitview').hasClass('hiddenac')) 
		{
			$('#allinitview').toggleClass('hiddenac');
			$('#bulkexpview').toggleClass('hiddenac');
			$('#bulkview').toggleClass('hiddenac');
			$('#itdocview').toggleClass('hiddenac');
		}
		else if (!$('#itdocview').is(":hidden") || !$('#bulkview').is(":hidden") || !$('#bulkexpview').is(":hidden") ){
			$('#itdocview').toggleClass('hiddenac');
			$('#bulkexpview').toggleClass('hiddenac');
			$('#bulkview').toggleClass('hiddenac');
			
		}
		
		
	});
	$('#show-asideacl-menu').click(function() {
		$('#acltab').toggleClass('hiddenac');
		$('.asideacl-menu').slideToggle("slow");
		$('#asideaclview').slideToggle("slow");		
	});}
	
	if ($('#showprodoc').val() == 1)
	  {
		$('#allinitview').hide();
		$('#itdocview').show();
		bulkdocuments();
	/*	$('#bulkview').hide();
		$('#bulkexpview').hide();
		$('#backeadlist').css('display','block');
		$('#processdoc').hide();
		$('#bulkdoc').hide();
		$('#bulkass').hide();*/
      }
   else if ($('#showbulk').val() == 1)
	  {
		$('#allinitview').hide();
		$('#itdocview').hide();
		
		bulkcont();
		/*$('#bulkview').show();
		$('#bulkexpview').hide();
		$('#backeadlist').css('display','block');
	    $('#processdoc').hide();
		$('#bulkdoc').hide();
		$('#bulkass').hide();*/
      }
  else if ($('#showbulkexp').val() == 1)
	  {
		$('#allinitview').hide();
		$('#itdocview').hide();
		
		bulkexpcont();
		/*$('#bulkexpview').show();
		
		$('#bulkass').hide();
		$('#backeadlist').css('display','block');
		$('#processdoc').hide();
		$('#bulkdoc').hide();
		$('#bulkass').hide();*/
}
   else 
{
   	    $('#allinitview').show();
		$('#itdocview').hide();
		$('#bulkview').hide();
		$('#bulkexpview').hide();
}
});


function refreshingpage(){
	
	let buttonclicked = JSON.parse(sessionStorage.getItem('btnclick'));
		if(buttonclicked){
		window.location = "ead_list?ead_list_page=" + true ;
		sessionStorage.setItem('btnclick', JSON.stringify(false));
		}
}

$('#btn1').click(function() {
	showLoader();
    
     
		$('#itdocview').hide();
		$('#bulkexpview').hide();
		$('#bulkass').hide();
		$('#backeadlist').hide();
		$('#processdoc').show();
		$('#bulkdoc').show();
		$('#bulkass').show();
		
		refreshingpage();
		hideLoader();
		
		
});
	
$('#btn2').click(function() {
	showLoader();

		$('#itdocview').hide();
		$('#bulkexpview').hide();
		$('#bulkass').hide();
		$('#backeadlist').hide();
		$('#processdoc').show();
		$('#bulkdoc').show();
		$('#bulkass').show();
	
	refreshingpage();
		hideLoader();
});

$('#btn3').click(function() {
		showLoader();
		$('#itdocview').hide();
		$('#bulkexpview').hide();
		$('#bulkass').hide();
		$('#backeadlist').hide();
		$('#processdoc').show();
		$('#bulkdoc').show();
		$('#bulkass').show();

refreshingpage();        
hideLoader();

});

$('#btn4').click(function() {
		showLoader();

		$('#itdocview').hide();
		$('#bulkexpview').hide();
		$('#bulkass').hide();
		$('#backeadlist').hide();
		$('#processdoc').show();
		$('#bulkdoc').show();
		$('#bulkass').show();
		refreshingpage();
		 hideLoader();

});

$('#btn5').click(function() {
	   showLoader();

         hideLoader();
		$('#itdocview').hide();
		$('#bulkexpview').hide();
		$('#bulkass').hide();
		$('#backeadlist').hide();
		$('#processdoc').show();
		$('#bulkdoc').show();
		$('#bulkass').show();
	refreshingpage();
		hideLoader();

});


$(document).ready(function() {
	var x = parseInt($('#queCount').text()) + parseInt($('#queAprCount').text());
	parseInt($('#sum').text(x));
	if ($('#role').val()=="PAO")
	 { $('#showallot').show();
     
	   if (parseFloat($('#aprret').val()) > 0)
	     document.getElementById("show-hidden-menu").classList.add("flash");
	   if (parseFloat($('#setasideapr').val()) > 0)
	     document.getElementById("show-aside-menu").classList.add("flash");}
	 if ($('#role').val()=="PAC")
	 {  if (parseFloat($('#setasideacl').val()) > 0)
	     document.getElementById("show-asideacl-menu").classList.add("flash");	   
	 }
});


function bulkexpcont() {
	
showLoader()

if (!$('.hidden-menu').is(":hidden"))
		{
			$('#allinitview').toggleClass('hiddenac');
			$('.hidden-menu').slideToggle("slow");
			$('#aprview').slideToggle("slow");
		}
		
 if (!$('.aside-menu').is(":hidden"))
	{
		$('.aside-menu').slideToggle("slow");
		$('#asideaprview').slideToggle("slow");
		$('#allinitview').toggleClass('hiddenac');
		
	}
     returnbyac = "showExp"
			var flagset;
		 if(returnbyac != undefined){	
				flagset= returnbyac;}	
			else if(flag =="showPAC" || flag == "showdoc" || flag == "showDemin" || flag == "showExp" || flag == "showaSide"){
				flagset = $("#flag").val();}	


//"ead_list?back=" + back ;

  $.ajax({
    url: 'ead_list_express?flagset=' + flagset,
    type: "get",
    //data: { list: "bulkexpview" },




    success: function(data) {
      $('#showprodoc').val(0);
      $('#showbulkexp').val(1);
      $('#showbulk').val(0);
      $('#bulkass').attr('hidden', true);
      $('#bulkexpassconf').modal('hide');
      $('#allinitview').toggleClass('hiddenac');
      $('#backeadlist').show();
	 if($('#bulkexpview').hasClass('hiddenac'))	
	     $('#bulkexpview').toggleClass('hiddenac');
      $('#backeadlist').attr('visible', true);
      $('#backeadlist').css('display', 'block');
       $('.barHiding').css('display', 'none');
      $('#bulkexpview').show();
	  $('#asideaprview').hide();
      $('#processdoc').attr('hidden', true);
      $('#bulkdoc').attr('hidden', true);
		hideLoader();

      // Update the bulkexpview table with the received data
       var tableBody = $('#bulkexptabbody');
			$('#bulkexptab').dataTable().fnDestroy();
      		tableBody.empty();

      // Iterate over the data.bulkexpview list and generate table rows
      for (var i = 0; i < data.length; i++) {
        var row = data[i];
        var rowHtml = '<tr valign="middle" class="rowDis" style="color:black">' +
          '<td width="1%" style="height:15px;vertical-align: middle;">' + (i + 1) + '</td>' +
          '<input type="hidden" id="cinno" name="cinno" value="' + row[0] + '">' +
          '<input type="hidden" id="itmid" name="itmid" value="' + row[3] + '">' +
          '<td width="10%" style="vertical-align: middle;;background-color:#dec3c3;" id="' + (i + 1) + '" onclick="viewSummaryc(this)">' + row[3] + '</td>' +
          '<td width="1%" style="vertical-align: middle;">' + row[2] + '</td>' +
          '<td width="1%" style="vertical-align: middle;">' + row[4] + '</td>' +
          '<td width="5%" style="vertical-align: middle;">' + row[5] + '</td>' +
          '<td width="6%" style="vertical-align: middle;">' + row[6] + '</td>' +
          '<td width="19%" style="vertical-align: middle;">' + row[7] + '</td>' +
          '<td width="7%" style="vertical-align: middle;">' + row[8] + '</td>' +
          '<td width="23%" style="vertical-align: middle;">' + row[9] + '</td>' +
          '<td width="5%" style="vertical-align: middle;">' + row[10] + '</td>' +
          '<td width="5%" style="vertical-align: middle;background-color:#dec3c3;" id="' + (i + 1) + '" onclick="dispitmdet(this)">' + row[11] + '</td>' +
          '<td width="5%" style="vertical-align: middle;background-color:#dec3c3;" id="' + (i + 1) + '" onclick="dispitmdet(this)">' + row[12] + '</td>' +
          '<td width="5%" style="color: black;vertical-align: middle;"><input type="checkbox" onclick="chkBoxClickexpress()" class="chckbxblkexp" name="chkboxblkexp[]" style="width:24px;height:24px" title="Check to finish ASSESSMENT..."></td>' +
          '<td width="10%" align="center" style="vertical-align: middle;"><i id="' + row[0] + '" onclick="viewDetail(this);"><button id="' + (i + 1) + '" type="button" class="btnDis rowDis" style="padding: 0px;color: #fff;background-color:green">Process EAD</button></i></td>' +
          '</tr>';
        tableBody.append(rowHtml);
      }



$("#bulkexptab").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"lengthMenu": [ 5, 10, 15 ],
		"pageLength": 5,
		"dom": '<"pull-left"Bl>frtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		buttons: [
			{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Process "Express Assessment',
					message:'Process "Express Assessment" @ ' + sitecode + '\n\r' + datetime,
					          },
				{extend: 'pdfHtml5',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Process "Express Assessment" @ ' + sitecode,
					filename:'Process "Express Assessment',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['3%','10%','7%','7%','7%','7%','10%','7%','7%','7%','7%','7%','7%','7%'];
				   	 doc.defaultStyle.alignment = 'center'
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
					}, ],});  	
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
				  responsive: true,
				  pageSize: 'A4',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Process "Expres Assessment @ ' + sitecode + '\n\r' + datetime ,
					filename:'Process "Express Assessment',	
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '7pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},
					
					             
			}],  
		
		
						"initComplete": function(settings) {
  $("#bulkexptab_filter").append('<button type="button" class="btn btn-info" id="clearing" ' +
    'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
  $("#bulkexptab_filter input[type='search']").attr('id', 'searchbulkexptab');
  
  $('#bulkexptab_filter').on('click', '#clearing', function() {
    var table = $('#bulkexptab').DataTable();
    table.search('').draw();
    $("#searchbulkexptab").val(""); // Clear the search input field
  });
}
		
	});
    },
    fail: function(rs, e) {
      console.log(rs, responseText);
      hideLoader()
    }
  });
	
	

}



function bulkdocuments(){
	
	showLoader()
	
	$('#bulkdocconf').modal('hide');	
	
	if (!$('.hidden-menu').is(":hidden"))
		{
			$('#allinitview').toggleClass('hiddenac');
			$('.hidden-menu').slideToggle("slow");
			$('#aprview').slideToggle("slow");
		}
		
 if (!$('.aside-menu').is(":hidden"))
	{
		$('.aside-menu').slideToggle("slow");
		$('#asideaprview').slideToggle("slow");
		$('#allinitview').toggleClass('hiddenac');
		
	}
	
	   returnbyac = "showdoc";
	    var flagset;
		 if(returnbyac != undefined){	
				flagset= returnbyac;}	
			else if(flag =="showPAC" || flag == "showdoc" || flag == "showDemin" || flag == "showExp" || flag == "showaSide"){
				flagset = $("#flag").val();}




			$('#showprodoc').val(1);
		
		$.ajax({
    url: 'ead_list_documents?flagset=' + flagset,
    type: "get",
    success: function(data) {
     $('#showprodoc').val(1);
		$('#showbulk').val(0);
		$('#showbulkexp').val(0);
		$('#bulkexpview').hide();
		$('.dochidden').toggle("slow");//, function(){
		$('#allinitview').toggleClass('hiddenac');
		$('#processdoc').attr('hidden',true);
		$('#bulkdoc').attr('hidden',true);
		$('#bulkass').attr('hidden',true);
		$('backeadlist').show();
		if($('#itdocview').hasClass('hiddenac'))	
			$('#itdocview').toggleClass('hiddenac');
		
		
		$('#asideaprview').hide();
		$('backeadlist').attr('visible',true);
	    $('#backeadlist').css('display','block');
	     $('.barHiding').css('display', 'none');
		//$('#itdocview').toggle('slow');
		if($('#itdocview').is(":hidden")){
			$('#itdocview').show();
		}
	//	else
	//	 {  $('#itdocview').hide();
	 //       $('#showprodoc').val(0);
      //      $('#allinitview').show();}
		//});

      // Update the bulkexpview table with the received data
       var tableBody = $('#doctabbody');
		$('#doctab').dataTable().fnDestroy();
      tableBody.empty();

      // Iterate over the data.bulkexpview list and generate table rows
      for (var i = 0; i < data.length; i++) {
        var row = data[i];
        var rowHtml = '<tr valign="middle" class="rowDis" style="color:black">' +
          '<td width="1%" style="height:15px;vertical-align: middle;">' + (i + 1) + '</td>' +
          '<input type="hidden" id="cinno" name="cinno" value="' + row[0] + '">' +
          '<input type="hidden" id="itmid" name="itmid" value="' + row[3] + '">' +
          '<td width="10%" style="vertical-align: middle;" id="' + (i + 1) + '" onclick="viewSummary(this)">' + row[3] + '</td>' +
          '<td width="1%" style="vertical-align: middle;">' + row[2] + '</td>' +
          '<td width="1%" style="vertical-align: middle;">' + row[4] + '</td>' +
          '<td width="5%" style="vertical-align: middle;">' + row[5] + '</td>' +
          '<td width="6%" style="vertical-align: middle;">' + row[6] + '</td>' +
          '<td width="11%" style="vertical-align: middle;">' + row[0] + '</td>' +
          '<td width="10%" style="vertical-align: middle;">' + row[1] + '</td>' +
          '<td width="10%" style="vertical-align: middle;">' + row[8] + '</td>' +
          '<td width="12%" style="vertical-align: middle;">' + row[7] + '</td>' +
          '<td width="7%" style="vertical-align: middle;">' + row[9] + '</td>' +
          '<td width="7%" style="vertical-align: middle;">' + row[10] + '</td>' +
          '<td width="8%" style="color: black;vertical-align: middle;"><input type="checkbox" id="chkboxass" onclick="chkBoxClickdoc()" class="chckbx" name="chkboxass[]" style="width:24px;height:24px" title="Check to finish ASSESSMENT..."></td>' +
          '<td width="20%" align="center" style="vertical-align: middle;"><i id="' + row[0] + '" onclick="viewDetail(this);"><button id="' + (i + 1) + '" type="button" class="btnDis rowDis" style="padding: 0px;color: #fff;background-color:green">Process EAD</button></i></td>' +
          '</tr>';

        tableBody.append(rowHtml);
      }



$("#doctab").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 5,
		"dom": '<"pull-left"Bl>frtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		buttons: [
			{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Process "DOCUMENTS',
					message:'Process "DOCUMENTS" @ ' + sitecode + '\n\r' + datetime,
					          },
				{extend: 'pdfHtml5',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Process "DOCUMENTS" @ ' + sitecode,
					filename:'Process "DOCUMENTS',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['3%','10%','7%','7%','7%','7%','10%','7%','7%','7%','7%','7%','7%','7%'];
				   	 doc.defaultStyle.alignment = 'center'
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
					}, ],});  	
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
				  responsive: true,
				  pageSize: 'A4',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Process "DOCUMENTS @ ' + sitecode + '\n\r' + datetime ,
					filename:'Process "DOCUMENTS',	
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '7pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},
					
					             
			}],  
		
		
						"initComplete": function(settings) {
  $("#doctab_filter").append('<button type="button" class="btn btn-info" id="clearing" ' +
    'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
  $("#doctab_filter input[type='search']").attr('id', 'searchdoctab');
  
  $('#doctab_filter').on('click', '#clearing', function() {
    var table = $('#doctab').DataTable();
    table.search('').draw();
    $("#searchdoctab").val(""); // Clear the search input field
  });
}
		
	});
	
	
    },
    fail: function(rs, e) {
      console.log(rs, responseText);
    }
  });
		
		hideLoader()
		
	};




function bulkcont(){
	
	showLoader()
	
	$('#bulkconf').modal('hide');	
	if (!$('.hidden-menu').is(":hidden"))
		{
			$('#allinitview').toggleClass('hiddenac');
			$('.hidden-menu').slideToggle("slow");
			$('#aprview').slideToggle("slow");
		}
		
 if (!$('.aside-menu').is(":hidden"))
	{
		$('.aside-menu').slideToggle("slow");
		$('#asideaprview').slideToggle("slow");
		$('#allinitview').toggleClass('hiddenac');
		
	}
	$('#returnbyac').val("showDemin");	
	returnbyac = $('#returnbyac').val();
	
	    var flagset;
		 if(returnbyac != undefined){	
				flagset= returnbyac;}	
			else if(flag =="showPAC" || flag == "showdoc" || flag == "showDemin" || flag == "showExp" || flag == "showaSide"){
				flagset = $("#flag").val();}
	
	
	$.ajax({
    url: 'ead_list_deminimis?flagset=' + flagset,
    type: "get",
    //data: { list: "bulkexpview" },
    success: function(data) {
 
	$('#showprodoc').val(0);
	$('#showbulkexp').val(0);
	$('#bulkass').attr('hidden',true);
		$('#showbulk').val(1);
	    $('#bulkdocconf').modal('hide');
		$('.dochidden').toggle("slow");//, function(){
		$('#allinitview').toggleClass('hiddenac');
		$('#bulkdoc').attr('hidden',true);
		$('#processdoc').attr('hidden',true);
		$('backeadlist').show();
		if($('#bulkview').hasClass('hiddenac'))	
			$('#bulkview').toggleClass('hiddenac');
		$('#bulkexpview').hide();
		$('#asideaprview').hide();
		$('backeadlist').attr('visible',true);
	    $('#backeadlist').css('display','block');
	     $('.barHiding').css('display', 'none');
		//$('#itdocview').toggle('slow');
		if($('#bulkdoc').is(":hidden")){
			$('#bulkview').show();
		}
	
	//	else
	//	 {  $('#itdocview').hide();
	 //       $('#showprodoc').val(0);
      //      $('#allinitview').show();}
		//});

      // Update the bulkexpview table with the received data
       var tableBody = $('#bulktabbody');
     $('#bulktab').dataTable().fnDestroy();
      tableBody.empty();

      // Iterate over the data.bulkexpview list and generate table rows
      for (var i = 0; i < data.length; i++) {
        var row = data[i];
        var rowHtml = '<tr valign="middle" class="rowDis" style="color:black">' +
          '<td width="1%" style="height:15px;vertical-align: middle;">' + (i + 1) + '</td>' +
          '<input type="hidden" id="cinno" name="cinno" value="' + row[0] + '">' +
          '<input type="hidden" id="itmid" name="itmid" value="' + row[3] + '">' +
          '<td width="10%" style="vertical-align: middle;" id="' + (i + 1) + '" onclick="viewSummaryb(this)">' + row[3] + '</td>' +
          '<td width="1%" style="vertical-align: middle;">' + row[2] + '</td>' +
          '<td width="1%" style="vertical-align: middle;">' + row[4] + '</td>' +
          '<td width="5%" style="vertical-align: middle;">' + row[5] + '</td>' +
          '<td width="6%" style="vertical-align: middle;">' + row[6] + '</td>' +
          '<td width="11%" style="vertical-align: middle;">' + row[0] + '</td>' +
          '<td width="10%" style="vertical-align: middle;">' + row[1] + '</td>' +
          '<td width="10%" style="vertical-align: middle;">' + row[8] + '</td>' +
          '<td width="12%" style="vertical-align: middle;">' + row[7] + '</td>' +
          '<td width="7%" style="vertical-align: middle;">' + row[9] + '</td>' +
          '<td width="7%" style="vertical-align: middle;">' + row[10] + '</td>' +
          '<td width="8%" style="color: black;vertical-align: middle;"><input type="checkbox" id="chkboxblk"  onclick="chkBoxClickprocess()" class="chckbxblkexp" name="chkboxblk[]" style="width:24px;height:24px" title="Check to finish ASSESSMENT..."></td>' +
          '<td width="20%" align="center" style="vertical-align: middle;"><i id="' + row[0] + '" onclick="viewDetail(this);"><button id="' + (i + 1) + '" type="button" class="btnDis rowDis" style="padding: 0px;color: #fff;background-color:green">Process EAD</button></i></td>' +
          '</tr>';

        tableBody.append(rowHtml);
      }

							


$("#bulktab").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"lengthMenu": [ 5, 10, 15, 20, 25 ],
		"pageLength": 5,
		"dom": '<"pull-left"Bl>frtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		buttons: [
			{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Process "De minimis',
					message:'Process "De minimis" @ ' + sitecode + '\n\r' + datetime,
					          },
				{extend: 'pdfHtml5',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Process "De minimis" @ ' + sitecode,
					filename:'Process "De minimis',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['3%','10%','7%','7%','7%','7%','10%','7%','7%','7%','7%','7%','7%','7%'];
				   	 doc.defaultStyle.alignment = 'center'
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
					}, ],});  	
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
				  responsive: true,
				  pageSize: 'A4',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Process "De minimis @ ' + sitecode + '\n\r' + datetime ,
					filename:'Process "De minimis',	
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '7pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},
					
					             
			}],  
		
		
					"initComplete": function(settings) {
  $("#bulktab_filter").append('<button type="button" class="btn btn-info" id="clearing" ' +
    'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
  $("#bulktab_filter input[type='search']").attr('id', 'searchbulktab');
  
  $('#bulktab_filter').on('click', '#clearing', function() {
    var table = $('#bulktab').DataTable();
    table.search('').draw();
    $("#searchbulktab").val(""); // Clear the search input field
  });
}
		
	});
	
    },
    fail: function(rs, e) {
      console.log(rs, responseText);
    }
  });
	
	hideLoader()
	}
	
function exceldownload(obj){
	$(".excelButtonAll").trigger('click');
}

function exceldownloadLet(obj){
	$(".excelButtonLET").trigger('click');
}

function exceldownloadEms(obj){
	$(".excelButtonEMS").trigger('click');
}

function exceldownloadPar(obj){
	$(".excelButtonPAR").trigger('click');
}

function exceldownloadEmp(obj){
	$(".excelButtonEMP").trigger('click');
}

$(document).ready(function() {

	/*$(".asstTable").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false
	});*/
	showLoader();
	$("#asideapr").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
					filename:'Electronic Advance Data (EAD)-SET ASIDE LIST',
				  //filename:'Electronic Advance Data (EAD)-SET ASIDE LIST',
					message:'Electronic Advance Data (EAD)-SET ASIDE LIST @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-SET ASIDE LIST @ ' + sitecode,
					filename:'Electronic Advance Data (EAD)-SET ASIDE LIST',
				     orientation: 'landscape',
				/*	exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						}, */
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['3%','12%','10%','12%','10%','8%','8%','8%','8%','8%','13%'];
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
				  message:'Electronic Advance Data (EAD)-SET ASIDE LIST @ ' + sitecode + '\n\r' + datetime ,
					filename:'Electronic Advance Data (EAD)-SET ASIDE LIST',
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},
						             
			}],
		
		
		"initComplete": function( settings ) {
	        $("#asideapr_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
	$("#alltabapr").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:'Electronic Advance Data (EAD)-RETURNED BY AC',
					message:'Electronic Advance Data (EAD)-RETURNED BY AC @ ' + sitecode + '\n\r' + datetime,
					/*customize: function (doc) {
					}	 */
						}, 
						{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-RETURNED BY AC @ ' + sitecode,
					filename:'Electronic Advance Data (EAD)-RETURNED BY AC',
				     orientation: 'landscape',
					exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8],
						margin: true,
					/*	modifier:{
							selected:true
								}, */
      						}, 
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['3%','10%','12%','12%','13%','13%','12%','12%','13%'];
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
    		        },
				},
				 {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Electronic Advance Data (EAD)-RETURNED BY AC @ ' + sitecode + '\n\r' + datetime ,
					filename:'Electronic Advance Data (EAD)-RETURNED BY AC',
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
		
		
		
		"initComplete": function( settings ) {
	        $("#alltabapr_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
	$("#emptabacl").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		"initComplete": function( settings ) {
	        $("#emptabacl_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
	$("#partabacl").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		"initComplete": function( settings ) {
	        $("#partabacl_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
	$("#emstabacl").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		"initComplete": function( settings ) {
	        $("#emstabacl_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
	$("#ltrtabacl").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		"initComplete": function( settings ) {
	        $("#ltrtabacl_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
		
	
	
	
	$("#asideacl").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		"initComplete": function( settings ) {
	        $("#asideacl_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
	$(".asst1Table").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		buttons: [
			{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Electronic Advance Data (EAD)-ALL',
					message:'Electronic Advance Data (EAD)-ALL @ ' + sitecode + '\n\r' + datetime,
					 },
			{extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-ALL @ ' + sitecode,
					filename:'Process "DOCUMENTS',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['3%','12%','10%','12%','10%','8%','8%','8%','8%','8%','13%'];
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
				  message:'Electronic Advance Data (EAD)-ALL @ ' + sitecode + '\n\r' + datetime ,
					filename:'Electronic Advance Data (EAD)-ALL',	
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},		
			}], 
		
	  "initComplete": function( settings ) {
	        $("#alltab_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearAlltab()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#alltab_filter input[type='search']").attr('id', 'searchAlltab');
    	}

		
	});
	
	$("#emstab").dataTable({  
		"processing": true,
		"paging": true,
		"ordering": false,
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonLET' }],
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
					filename:'Electronic Advance Data (EAD)-E Letter',
				  //filename:'Electronic Advance Data (EAD)-U Letter',
					message:'Electronic Advance Data (EAD)-E Letter @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-E Letter @ ' + sitecode,
					filename:'Electronic Advance Data (EAD)-E Letter',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['3%','12%','10%','12%','10%','8%','8%','8%','8%','8%','13%'];
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
				  message:'Electronic Advance Data (EAD)-E Letter @ ' + sitecode + '\n\r' + datetime ,
					filename:'Electronic Advance Data (EAD)-E Letter',
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},	             
			}],


 "initComplete": function( settings ) {
	         $("#emstab_filter").append('<button type="button" class="btn btn-info" id="clear123" style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
      $('#clear123').on('click', function() {
        $('#emstab_filter input[type="search"]').val('').trigger('input');
        var table = $('#emstab').DataTable();
        table.search('').draw();
      });
			
    	}

	});
	
	
	
	
	$(".LetasstTable").dataTable({  
		"processing": true,
		"paging": true,
		"ordering": false,
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonLET' }],
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
					filename:'Electronic Advance Data (EAD)-U Letter',
				  //filename:'Electronic Advance Data (EAD)-U Letter',
					message:'Electronic Advance Data (EAD)-U Letter @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-U Letter @ ' + sitecode,
					filename:'Electronic Advance Data (EAD)-U Letter',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['3%','12%','10%','12%','10%','8%','8%','8%','8%','8%','13%'];
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
				  message:'Electronic Advance Data (EAD)-U Letter @ ' + sitecode + '\n\r' + datetime ,
					filename:'Electronic Advance Data (EAD)-U Letter',
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},	             
			}],
	  "initComplete": function( settings ) {
	        $("#ltrtab_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearAlltab1()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#ltrtab_filter input[type='search']").attr('id', 'searchAlltab');
    	}
	});
	$(".EmsasstTable").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonEMS' }],
		"dom": '<"pull-left"Bl>frtip',
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Electronic Advance Data (EAD)-E EMS',
					message:'Electronic Advance Data (EAD)-E EMS @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-E EMS @ ' + sitecode,
					filename:'Electronic Advance Data (EAD)-E EMS',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['3%','12%','10%','12%','10%','8%','8%','8%','8%','8%','13%'];
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
				  message:'Electronic Advance Data (EAD)--E EMS @ ' + sitecode + '\n\r' + datetime ,
					filename:'Electronic Advance Data (EAD)-E EMS',	
				customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},             
			}], 
		
		
		"initComplete": function( settings ) {
	        $("#emstab_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
	});
	$(".ParasstTable").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonPAR' }],
		"dom": '<"pull-left"Bl>frtip',
		
		"initComplete": function( settings ) {
	        $("#partab_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	},
			
			buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Electronic Advance Data (EAD)-C Parcel',
					message:'Electronic Advance Data (EAD)-C Parcel @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-C Parcel @ ' + sitecode,
					filename:'Electronic Advance Data (EAD)-C Parcel',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['3%','12%','10%','12%','10%','8%','8%','8%','8%','8%','13%'];
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
				  message:'Electronic Advance Data (EAD)-C Parcel @ ' + sitecode + '\n\r' + datetime ,
					filename:'Electronic Advance Data (EAD)-C Parcel',	
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}], 




	});
	$(".EmptyasstTable").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		//"dom": 'Bfrtip',
		"dom": '<"pull-left"Bl>frtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonEMP' }],
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Electronic Advance Data (EAD)-T Emp.Recep',
					message:'Electronic Advance Data (EAD)-T Emp.Recep @ ' + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Electronic Advance Data (EAD)-T Emp.Recep @ ' + sitecode,
					filename:'Electronic Advance Data (EAD)-T Emp.Recep',
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['3%','12%','10%','12%','10%','8%','8%','8%','8%','8%','13%'];
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
				  message:'Electronic Advance Data (EAD)-T Emp.Recep @ ' + sitecode + '\n\r' + datetime ,
					filename:'Electronic Advance Data (EAD)-T Emp.Recep',	
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}], 
		
		
		"initComplete": function( settings ) {
	        $("#emptab_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
	});
	
	$("#ltrtabapr").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		"initComplete": function( settings ) {
	        $("#partabacl_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
	$("#emstabapr").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		"initComplete": function( settings ) {
	        $("#partabacl_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
	$("#partabapr").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		"initComplete": function( settings ) {
	        $("#partabacl_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="refreshPage()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
    	}
		
	});
	
	
	$("#alltabacl").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{ extend: 'excel', className: 'excelButtonEMP' }],
		"initComplete": function( settings ) {
	     $("#alltabacl_filter").append('<button type="button" class="btn btn-info" id="clear6" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#alltabacl_filter input[type='search']").attr('id', 'searchalltabacl');
		$('#clear6').click(function() {	
		document.getElementById("searchalltabacl").value=""
		var table = $('#alltabacl').DataTable();
		table.search('').draw();
	   })
    	}
	});
hideLoader();
	var dispmailcat = "";
	var fou1=0;
	var fou2=0;
	var fou3=0;
	var fou4=0;
	var fou5=0;
	if (!(parseFloat($('#mailcat').val().indexOf("U")) >= 0))
	  { $('#btn1').prop('disabled', true);
	    $('#btn1').css('font-weight', 200);
	    $('#btn11').prop('disabled', true);
	    $('#btn11').css('font-weight', 200);
	    }
	else
	    fou1=1; 
	if (!(parseFloat($('#mailcat').val().indexOf("E")) >= 0))
	  { $('#btn2').prop('disabled', true);
	    $('#btn2').css('font-weight', 200);
	    $('#btn12').prop('disabled', true);
	    $('#btn12').css('font-weight', 200);
	    }
	else
	    fou2=1;	
	if (!(parseFloat($('#mailcat').val().indexOf("C")) >= 0))
	  { $('#btn3').prop('disabled', true);
	    $('#btn3').css('font-weight', 200);
	    $('#btn13').prop('disabled', true);
	    $('#btn13').css('font-weight', 200);
	   }
	else
	    fou3=1; 	
	if (!(parseFloat($('#mailcat').val().indexOf("T")) >= 0))
	  { $('#btn4').prop('disabled', true);
	    $('#btn4').css('font-weight', 200);
	    $('#btn4').css('font-size', 12);
	    $('#btn14').prop('disabled', true);
	  }
	else
	    fou4=1;

    
});

function refreshPage() {
	location.href=localStorage.getItem("currentUrl");
}


function showview(cat,role)
{
	if($('#allinitview').hasClass('hiddenac')){
		$('#allinitview').toggleClass('hiddenac');
		$('#clear').toggleClass('hiddenac');
	}
	$('#returnbyac').hide();	
  if ( cat == 1 && role == 1)
   { $('#ltrview').show();
     $('#allotview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();

$('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');    
$('#processdoc').attr('hidden',false);

/*if ($('#showprodoc').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}
else if ($('#showbulk').val()==1){
	window.location = "ead_list?ead_list_page=" + true;
	 $('#allinitview').show();}
else if ($('#showbulkexp').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}*/
	
	
$('.aside-menu').hide();	
$('#asideaprview').hide();
$('#bulkdoc').attr('hidden',false);	
$('#bulkass').attr('hidden',false);
flag = 0;returnbyac=0;
   }
  else if ( cat == 5 && role ==1)
   {
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').show();
      $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
$('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

/*if ($('#showprodoc').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}
else if ($('#showbulk').val()==1){
	window.location = "ead_list?ead_list_page=" + true;
	 $('#allinitview').show();}
else if ($('#showbulkexp').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}*/
	
$('.aside-menu').hide();	
$('#asideaprview').hide();	
$('#bulkdoc').attr('hidden',false);	
$('#bulkass').attr('hidden',false);	
flag = 0;returnbyac=0;

   }
   else if ( cat == 2 && role == 1)
   {
     $('#ltrview').hide();
     $('#emsview').show();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
      $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
 $('#itdocview').hide(); 
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

/*if ($('#showprodoc').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}
else if ($('#showbulk').val()==1){
	window.location = "ead_list?ead_list_page=" + true;
	 $('#allinitview').show();}
else if ($('#showbulkexp').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}
	*/
	
$('.aside-menu').hide();	
$('#asideaprview').hide();	
$('#bulkdoc').attr('hidden',false);	
$('#bulkass').attr('hidden',false);	
flag = 0;returnbyac=0;
    }
    else if ( cat == 3 && role == 1)
   {
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').show();
     $('#empview').hide();
     $('#allotview').hide();
      $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
$('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

/*if ($('#showprodoc').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}
else if ($('#showbulk').val()==1){
	window.location = "ead_list?ead_list_page=" + true;
	 $('#allinitview').show();}
else if ($('#showbulkexp').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}*/
	
	
$('.aside-menu').hide();	
$('#asideaprview').hide();	
$('#bulkdoc').attr('hidden',false);	
$('#bulkass').attr('hidden',false);	
flag = 0;returnbyac=0;
   }
    else if ( cat == 4 && role == 1)
   {
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').show();
     $('#allotview').hide();
     $('#aprview').hide();
     $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
$('#itdocview').hide();   
$('#bulkview').hide();  
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

/*if ($('#showprodoc').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}
else if ($('#showbulk').val()==1){
	window.location = "ead_list?ead_list_page=" + true;
	 $('#allinitview').show();}
else if ($('#showbulkexp').val()==1){
	window.location = "ead_list?ead_list_page=" + true ;
	$('#allinitview').show();}*/
	
	
$('.aside-menu').hide();	
$('#asideaprview').hide();	
$('#bulkdoc').attr('hidden',false);	
$('#bulkass').attr('hidden',false);	
flag = 0;returnbyac=0;
   }
   else if ( cat == 1 && role == 2)
   { $('#aprltrview').show();
     $('#aprview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
     $('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

$('.aside-menu').hide();
$('#returnbyac').show();
$('#allinitview').toggleClass('hiddenac');
$('#allotview').show();
   }
  else if ( cat == 5 && role ==2)
   {
     $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').show();
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
    $('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

$('#returnbyac').show();
$('.aside-menu').hide();
$('#allinitview').toggleClass('hiddenac');
$('#allotview').show();
   }
   else if ( cat == 2 && role == 2)
   {
     $('#aprltrview').hide();
     $('#apremsview').show();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
   $('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

$('.aside-menu').hide();
$('#returnbyac').show();
$('#allinitview').toggleClass('hiddenac');
$('#allotview').show();
    }
    else if ( cat == 3 && role == 2)
   {
     $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').show();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
$('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

$('#returnbyac').show();
$('.aside-menu').hide();
$('#allinitview').toggleClass('hiddenac');
$('#allotview').show();
   }
    else if ( cat == 4 && role == 2)
   {
     $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').show();
     $('#aprview').hide();
      $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
     $('#ACLltrview').hide();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
$('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

$('#returnbyac').show();
$('.aside-menu').hide();
   }
    else if ( cat == 1 && role == 3)
   { $('#ACLltrview').show();
     $('#ACLview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
     $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
 $('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);
   }
  else if ( cat == 5 && role ==3)
   {
     $('#ACLltrview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
     $('#ACLview').show();
     $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
    $('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

$('.asideacl-menu').hide();
$('#asideaclview').hide();
$('#acltab').removeClass('hiddenac');
$('#acltab').show();
   }
   else if ( cat == 2 && role == 3)
   {
     $('#ACLltrview').hide();
     $('#ACLemsview').show();
     $('#ACLparview').hide();
     $('#ACLempview').hide();
     $('#ACLview').hide();
     $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
  $('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

$('.asideacl-menu').hide();
$('#asideaclview').hide();
$('#acltab').removeClass('hiddenac');
$('#acltab').show();
    }
    else if ( cat == 3 && role == 3)
   {
     $('#ACLltrview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').show();
     $('#ACLempview').hide();
     $('#ACLview').hide();
    $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
    $('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

$('.asideacl-menu').hide();
$('#asideaclview').hide();
$('#acltab').removeClass('hiddenac');
$('#acltab').show();
   }
    else if ( cat == 4 && role == 3)
   {
     $('#ACLltrview').hide();
     $('#ACLemsview').hide();
     $('#ACLparview').hide();
     $('#ACLempview').show();
     $('#ACLview').hide();
     $('#aprltrview').hide();
     $('#apremsview').hide();
     $('#aprparview').hide();
     $('#aprempview').hide();
     $('#aprview').hide();
     $('#ltrview').hide();
     $('#emsview').hide();
     $('#parview').hide();
     $('#empview').hide();
     $('#allotview').hide();
$('#itdocview').hide();
$('#bulkview').hide();
$('#bulkexpview').hide();
$('#backeadlist').css('display','none');
$('#processdoc').attr('hidden',false);

$('.asideacl-menu').hide();
$('#asideaclview').hide();
$('#acltab').removeClass('hiddenac');
$('#acltab').show(); 
   }
}

$(document).ready(function() {
	if ( $('#role').val() == 'PAO' ) 
	{
		$("#showAPR").show();
		$("#showACL").hide();
		$('#allotview').show();
		$('#allotdisp').show();
	} 
	else if ( $('#role').val() == 'PAC' ) 
	{
		$("#showACL").show();
		$("#showAPR").hide();
		$('#ACLview').show();
	}
	
	else 
	{
	    $("#showACL").hide();
		$("#showAPR").hide();
		
	} 
	dt();
});

function dt()
{
/* var today = new Date();
var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
var dateTime = date+' '+time;
var utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
alert(utilDate);
		document.getElementById("currentDateTime").innerHTML = utilDate; */
		var today = new Date();
		var dd = today.getDate();

		var mm = today.getMonth()+1; 
		var yyyy = today.getFullYear();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		if(dd<10) 
		{
		    dd='0'+dd;
		} 

		if(mm<10) 
		{
		    mm='0'+mm;
		}
		today = dd+'/'+mm+'/'+yyyy;
		console.log(today);
		var dateTime = today+' '+time;
		document.getElementById("currentDateTime").innerHTML = dateTime;
}



function allotoffid(e) {
    var developerData = {};

	developerData['id'] = e.id;
    showLoader();
	var resObj = $.ajax({
		url: 'allotoffid',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			var s = "success";
			hiderLoader();
		},
		fail: function(rs, e) {
			alert("Error in Alloting Officer Id");
		}
	});   
}

function pushead(e) {
    var fpoMain = {};
    fpoMain['id'] = e.id;	
    showLoader();
    $.ajax({
      url: 'revokesetaside',
      data: JSON.stringify(fpoMain),
      dataType: "json", 
      contentType: "application/json",
      type: "post",
      success: function(fpoMains){
	hideLoader();
         window.location.href="ead_list";
         },
      fail: function(rs, e) {
			alert("Error in pushingsetaside");
		}
	});
}

function pushaaa(e) {
    var fpoMain = {};
    fpoMain['id'] = e.id;
    $.ajax({
      url: 'revokesetaside',
      data: JSON.stringify(fpoMain),
      dataType: "json", 
      contentType: "application/json",
      type: "post",
      success: function(fpoMains){
         window.location.href="process_ead";
         },
      fail: function(rs, e) {
			alert("Error in pushingsetaside");
		}
	});
}
	
	
function clearAlltab(){
	document.getElementById("searchAlltab").value=""
      var table = $('#alltab').DataTable();
      table.search('').draw();
}

function clearAlltab1(){
	document.getElementById("searchAlltab").value=""
      var table = $('#ltrtab').DataTable();
      table.search('').draw();
}
	
function viewDetail(e) {
	//alert(e.id);
	//window.location.href = "#" + e.id;
    allotoffid(e);
	var fpoMvmnt = {};
	fpoMvmnt['cinNo'] = e.id;
	fpoMvmnt['role'] = "PAO";
	if(returnbyac != undefined){	
		fpoMvmnt['flag'] = returnbyac;}	
	else if(flag =="showPAC" || flag == "showdoc" || flag == "showDemin" || flag == "showExp" || flag == "showaSide"){
		fpoMvmnt['flag'] = $("#flag").val();}	
	
	var processType = e.textContent;
    showLoader();
	$.ajax({
		url: 'updateUSerEnterStatus',
		data: JSON.stringify(fpoMvmnt),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(fpoMvmnts) {
			/*if (null != developerDatas['page'] && developerDatas['page'] != "noramlFlag") {
				window.location.href = "" + developerDatas['page'] + "?id=" + cinNo;
			}*/
			localStorage.setItem("processType" , processType)
			hideLoader();
		},
		fail: function(rs, e) {
			alert("Error in nextPageRedirect");
		}
	});

	window.location.href = "ead_main?id=" + e.id;
}


$("#alltabapr tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).find("td:eq(10)").css('padding','0');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   $(this).find("button#"+textval).css('width','100%');
		   }
		else if (textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});



$("#ltrtabapr tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});
	
$("#doctab tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
	/*	if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }*/
	  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
	//	if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
			$(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
	//	} 
		
	});


$("#bulktab tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
	/*	if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }*/
	  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
	//	if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
			$(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
	//	} 
		
	});
	
	
$("#bulkexptab tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
	/*	if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }*/
	  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
	//	if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
			$(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
	//	} 
		
	});	
	



$("#emstabapr tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});


$("#partabapr tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});


$("#emptabapr tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});


$("#ltrtabacl tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});


$("#emstabacl tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});


$("#partabacl tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});


$("#emptabacl tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});


$("#alltabacl tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		$(this).find("button#"+textval).prop('disabled', true);
		if (textval==1)
		  {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
		
	});

$("#alltab tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).find("td:eq(10)").css('padding','0');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   $(this).find("button#"+textval).css('width','100%');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1)  {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});
	
	
$("#ltrtab tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1)  {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});
	
$("#emstab tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});


$("#partab tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1)  {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});
	
	
	$("#emptab tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
		if (textval==1)
		  {
		   $(this).css('background-color','dimgray');
		   $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
		   }
		else  if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		   $(this).find("button#"+textval).css('background-color','gray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		   $(this).find("button#"+textval).css('background-color','dimgray');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','dimgray');
		 }
		if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		} 
		
	});
	
	
	$("#asideacl tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
	//	if (textval==1)
	//	  {
		 //  $(this).css('background-color','dimgray');
		//   $(this).find("td:eq(10)").css('background-color','green');
		//   $(this).css('font-size','18px');
		//   $(this).css('font-weight','bold');
		//   $(this).css('color','#130101');	 
		
//		   }
//		else  if(textval % 2 == 0)
		if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		  // $(this).find("button#"+textval).css('background-color','gray');
		  // $(this).find("button#"+textval).css('color','white');
		  // $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		  // $(this).find("button#"+textval).css('background-color','dimgray');
		  // $(this).find("button#"+textval).css('color','white');
		  // $(this).find("button#"+textval).css('border','dimgray');
		 }
		//if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		//} 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
           $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 		
	});
	
		$("#asideapr tbody tr").each(function() {
		var textval = $(this).find("td:eq(0)").text().trim();
	//	if (textval==1)
	//	  {
		 //  $(this).css('background-color','dimgray');
		//   $(this).find("td:eq(10)").css('background-color','green');
		//   $(this).css('font-size','18px');
		//   $(this).css('font-weight','bold');
		//   $(this).css('color','#130101');	 
		
//		   }
//		else  if(textval % 2 == 0)
		if(textval % 2 == 0)
		 {
		   $(this).css('background-color','white');
		   $(this).css('color','#130101');		
		  // $(this).find("button#"+textval).css('background-color','gray');
		  // $(this).find("button#"+textval).css('color','white');
		  // $(this).find("button#"+textval).css('border','gray');
		 }
		 else
		 {
		   $(this).css('color','#130101');	
		   $(this).css('background-color','#e2ecf7');
		  // $(this).find("button#"+textval).css('background-color','dimgray');
		  // $(this).find("button#"+textval).css('color','white');
		  // $(this).find("button#"+textval).css('border','dimgray');
		 }
		//if (textval == 1) {
			$(this).find("button#"+textval).prop('disabled', false);
		//} 
		   $(this).find("button#"+textval).css('background-color','green');
		   $(this).find("button#"+textval).css('color','white');
		   $(this).find("button#"+textval).css('border','green');
           $(this).find("td:eq(10)").css('background-color','green');
		   $(this).css('font-size','18px');
		   $(this).css('font-weight','bold');
		   $(this).css('color','#130101');	 		
	});
	
	
	
	 function asssubmitall()
    {
	$('#assmsg').html("Do you want to Complete EAD Assessment for all Selected Article IDs(DOCUMENTS)?");
       $("#exampleModalass").modal('show');
    }

 function asssubmitallblk()
    {
	//$('#assmsg').html("Do you want to Complete EAD Assessment for all Selected Article IDs(De minimis)?");
//	$('#assmsg').html("You are selecting the Bulk processing option for 'De minimis' Article IDs where the System calculated duty is ZERO, based on the value declared in EAD. <br><font size=2>(However, detailed assessment can also be carried out from Process EAD tab, if required)</font>");
	$('#assmsg').html("Do you want to Complete EAD Assessment for all Selected Article IDs(De minimis)?");
       $("#exampleModalass").modal('show');
    }
	
	
	 function asssubmitallblkexp()
    {
	//$('#assmsg').html("Do you want to Complete EAD Assessment for all Selected Article IDs(De minimis)?");
//	$('#assmsg').html("You are selecting the Bulk processing option for 'De minimis' Article IDs where the System calculated duty is ZERO, based on the value declared in EAD. <br><font size=2>(However, detailed assessment can also be carried out from Process EAD tab, if required)</font>");
	$('#assmsg').html("Do you want to Complete EAD Assessment for all Selected Article IDs(Express Assessment)?");
       $("#exampleModalass").modal('show');
    }
	
    
    function refresh()
    {
	
	sessionStorage.setItem('btnclick', JSON.stringify(true));
	
	 if ($('#showprodoc').val()==1)
     location.href= "ead_list?showprodoc=1&showbulk=0&showbulkexp=0";
    else if ($('#showbulk').val()==1)
     location.href= "ead_list?showprodoc=0&showbulk=1&showbulkexp=0";
    else if ($('#showbulkexp').val()==1)
     location.href= "ead_list?showprodoc=0&showbulk=0&&showbulkexp=1";

    }

function movooc()
	{
		showLoader();	
		$('#decisionModal').modal('hide');	
		// udpate ead decision for all the selected articles and move to OOC queue
		 if ($('#showbulk').val()==1){
		
	           movoocblk();

}
         else if ($('#showbulkexp').val()==1){
	
	           movoocblkexp();

}
        else
{
	
		var table = document.getElementById('doctab');
        var rowCount = table.rows.length;
        var developerData = {};
        var cin ;
         for (var i = 1; i < rowCount; i++)    
     {      
           cin = $('#doctab').find('tr').eq(i).find('td:eq(6)').text().substr(0,20);
           itemid = $('#doctab').find('tr').eq(i).find('td:eq(1)').text().substr(0,13);
           if ($('#doctab').find('tr').eq(i).find('[name="chkboxass[]"]').prop('checked'))
            {  developerData['cin_NO'] = cin;
               developerData['deci_CD'] = "1";
               developerData['item_ID']= itemid;
               developerData['qry_TYPE']= "P3";
			$.ajax({
 		        url: 'movbulkasstoooc',
 		       data: JSON.stringify(developerData),
               dataType: "json",
               contentType: "application/json",
               type: "post",
				async: false,
               success: function(developerData) {
                  if (i == (rowCount-1) )
		            {
                      success="yes";		           
	                }					
                },
              fail: function(rs, e) {
                   alert("Error in Import Submit");
               }
          
       });
      }
 }   
      if (success=="yes")
	{	refresh();
	$('.dochidden').hide();
	$('#allinitview').hide();
	 $('#bulkview').hide();	
	    $('#itdocview').show();		
     }
	
}	
	hideLoader();
	
	}
	
	


function asssuball()
	{
		$('#exampleModalass').modal('hide');
     if ($('#showbulk').val()==1)
         asssuballblk();
      else if ($('#showbulkexp').val()==1)
         asssuballexpblk();
     else
{	 var table = document.getElementById('doctab');
        var rowCount = table.rows.length;
        var developerData = {};
        var cin ;
         for (var i = 1; i < rowCount; i++)    
     {      
           cin = $('#doctab').find('tr').eq(i).find('td:eq(6)').text().substr(0,20);
           if ($('#doctab').find('tr').eq(i).find('[name="chkboxass[]"]').prop('checked'))
            {  developerData['id'] = cin;
			$.ajax({
 		        url: 'asssubmit',
 		       data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerData) {
             if (i == (rowCount-1) )
		         {
                   success="yes";		           
	             }					
         },
        fail: function(rs, e) {
         alert("Error in Import Submit");
          }
          
      });
      }
 }   
      if (success="yes")
{
	$('#compmsg').html("EAD ASSESSMENT IS COMPLETED FOR ALL SELECTED ARTICLE IDs ( DOCUMENTS )");
        $("#decisionModal").modal('show');
}
}
}

function asssuballblk()
	{
		$('#exampleModalass').modal('hide');
	 var table = document.getElementById('bulktab');
        var rowCount = table.rows.length;
        var developerData = {};
        var cin ;
         for (var i = 1; i < rowCount; i++)    
     {      
           cin = $('#bulktab').find('tr').eq(i).find('td:eq(6)').text().substr(0,20);
           if ($('#bulktab').find('tr').eq(i).find('[name="chkboxblk[]"]').prop('checked'))
            {  developerData['id'] = cin;
			$.ajax({
 		        url: 'asssubmit',
 		       data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerData) {
             if (i == (rowCount-1) )
		         {
                   success="yes";		           
	             }					
         },
        fail: function(rs, e) {
         alert("Error in Import Submit");
          }
          
      });
      }
 }   
          if (success="yes")
{
	$('#compmsg').html("EAD ASSESSMENT IS COMPLETED FOR ALL SELECTED ARTICLE IDs ( De minimis )");
        $("#decisionModal").modal('show');
}

}

function asssuballexpblk()
	{
		$('#exampleModalass').modal('hide');
	 var table = document.getElementById('bulkexptab');
        var rowCount = table.rows.length;
        var developerData = {};
        var cin ;
         for (var i = 1; i < rowCount; i++)    
     {      
           cin = $('#bulkexptab').find('tr').eq(i).find('td:eq(6)').text().substr(0,20);
           if ($('#bulkexptab').find('tr').eq(i).find('[name="chkboxblkexp[]"]').prop('checked'))
            {  developerData['id'] = cin;
			$.ajax({
 		        url: 'asssubmit',
 		       data: JSON.stringify(developerData),
        dataType: "json",
        contentType: "application/json",
        type: "post",
       success: function(developerData) {
             if (i == (rowCount-1) )
		         {
                   success="yes";		           
	             }					
         },
        fail: function(rs, e) {
         alert("Error in Import Submit");
          }
          
      });
      }
 }   
          if (success="yes")
{
	$('#compmsg').html("EAD ASSESSMENT IS COMPLETED FOR ALL SELECTED ARTICLE IDs ( Express Assessment )");
        $("#decisionModal").modal('show');
}

}


	function selallass() {
     var checkboxes = document.getElementsByTagName('input');
      var pagelen =$('#doctab').DataTable().page.info().length+2;
         for (var i = 1; i < pagelen; i++) {
           var row=i % pagelen;
               if ($('#chkselallass').is(":checked") )
                $('#doctab').find('tr').eq(row).find("input#chkboxass").prop('checked',true);
               else
                $('#doctab').find('tr').eq(row).find("input#chkboxass").prop('checked',false);
             }
}


    function selallblk(){
    var checkboxes = document.getElementsByTagName('input');
     var pagelen =$('#bulktab').DataTable().page.info().length+2;
         for (var i = 1; i < pagelen; i++) {
           var row=i % pagelen;
               if ($('#chkselblkass').is(":checked") )
                $('#bulktab').find('tr').eq(row).find("input#chkboxblk").prop('checked',true);
               else
                $('#bulktab').find('tr').eq(row).find("input#chkboxblk").prop('checked',false);
             }

}


 function selallblkexp(){
    var checkboxes = document.getElementsByTagName('input');
     var pagelen =$('#bulkexptab').DataTable().page.info().length+2;
         for (var i = 1; i < pagelen; i++) {
           var row=i % pagelen;
               if ($('#chkselblkass').is(":checked") )
                $('#bulkexptab').find('tr').eq(row).find("input#chkboxblkexp").prop('checked',true);
               else
                $('#bulkexptab').find('tr').eq(row).find("input#chkboxblkexp").prop('checked',false);
             }

}


	
	

function cleartable(e){
e.empty();
}


 function viewSummary(e) {
		 var developerData = {};
         var currow=e.id;
        var pagelen =$('#doctab').DataTable().page.info().length;
        var row=currow % pagelen;
       if (row==0)
           row=row+pagelen;
       var cinno= $('#doctab').find('tr').eq(row).find("input#cinno").val();
       var itmid= $('#doctab').find('tr').eq(row).find("input#itmid").val();
       $('#doctab').find('tr').eq(row).css('background-color','green');
       $('#doctab').find('tr').eq(row).css('font-size','18');
       $('#doctab').find('tr').eq(row).css('color','yellow');
       $('#doctab').find('tr').eq(row).css('border','darkgreen');
       developerData['id'] = cinno;
		
	    $.ajax({
                url: 'getSummary?id=' + itmid,
                type: "post",
                success: function(data) {
                    $("#summaryModalBody").html(data);
					$("#summaryModalTitle").text('ARTICLE SUMMARY - '+itmid);
	                $("#summaryModal").modal('show');
                    //$('#doctab').modal('show');
                },
                error: function(rs, e) {
                    alert("Error in nextPageRedirect");
                }
            });
      };


function viewSummaryb(e) {
		 var developerData = {};
         var currow=e.id;
        var pagelen =$('#bulktab').DataTable().page.info().length;
        var row=currow % pagelen;
       if (row==0)
           row=row+pagelen;
       var cinno= $('#bulktab').find('tr').eq(row).find("input#cinno").val();
       var itmid= $('#bulktab').find('tr').eq(row).find("input#itmid").val();
       $('#bulktab').find('tr').eq(row).css('background-color','green');
       $('#bulktab').find('tr').eq(row).css('font-size','18');
       $('#bulktab').find('tr').eq(row).css('color','yellow');
       $('#bulktab').find('tr').eq(row).css('border','darkgreen');
       developerData['id'] = cinno;
		
	    $.ajax({
                url: 'getSummary?id=' + itmid,
                type: "post",
                success: function(data) {
                    $("#summaryModalBody").html(data);
					$("#summaryModalTitle").text('ARTICLE SUMMARY - '+itmid);
	                $("#summaryModal").modal('show');
                    //$('#doctab').modal('show');
                },
                error: function(rs, e) {
                    alert("Error in nextPageRedirect");
                }
            });
      };




//By sasi mam
function dispitmdet(e)
{
    var developerData = {};
    var currow=e.id;
    var pagelen =$('#bulkexptab').DataTable().page.info().length;
    var row=currow % pagelen;
    if (row==0)
       row=row+pagelen;
    row=row+1;
    var cinno= $('#bulkexptab').find('tr').eq(row).find("input#cinno").val();
    var itmid= $('#bulkexptab').find('tr').eq(row).find("input#itmid").val();
    $('#dispitmid').html("DUTY DETAILS FOR ARTICLE ID : <font color='blue'>"+itmid+"</font>");
    $('#bulkexptab').find('tr').eq(row).css('background-color','#2a4d69');
    $('#bulkexptab').find('tr').eq(row).find('td').eq(1).css('background-color','#2a4d69');
    $('#bulkexptab').find('tr').eq(row).find('td').eq(10).css('background-color','#2a4d69');
    $('#bulkexptab').find('tr').eq(row).find('td').eq(11).css('background-color','#2a4d69');
    $('#bulkexptab').find('tr').eq(row).css('font-size','18');
    $('#bulkexptab').find('tr').eq(row).css('color','yellow');
    $('#bulkexptab').find('tr').eq(row).css('border','#2a4d69');
    developerData['id'] = cinno;
//    var table=$('#ITEMREC');
  /*  var resObj = $.ajax({
    url: 'oocdispmaindata',
    data: JSON.stringify(developerData),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(developerDatas) {
	     if (developerDatas.length > 0)
	        {
	           document.getElementById("itmiddisp").innerHTML = itmid;
	           document.getElementById("rname").innerHTML = developerDatas[0][0];
	           document.getElementById("raddr").innerHTML = developerDatas[0][1];
	           document.getElementById("gwt").innerHTML = developerDatas[0][4];
	           document.getElementById("totduty").innerHTML = developerDatas[0][5];
	           document.getElementById("pen").innerHTML = developerDatas[0][6];
	           document.getElementById("fin").innerHTML = developerDatas[0][7];    
	        } 	
		},
	fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});*/
  var table = document.getElementById('ITEMREC');
    var rowCount = table.rows.length - 1;
     var i=1;
     while ( i < rowCount )
    {
      document.getElementById("ITEMREC").deleteRow(-1);
      i=i+1;}
       developerData = {};
       developerData['cinNo'] = cinno;
	    var resObj = $.ajax({
		url: 'oocdispdata',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			var rowcount = developerDatas.length;
			var stRow = 0;
			var table = document.getElementById('ITEMREC');
            var oldrc = table.rows.length - 1;
            var i=1;
            while ( i < oldrc )
            {
              document.getElementById("ITEMREC").deleteRow(-1);
              i=i+1;}
			$('#rowcount').val(rowcount);
			while (stRow < rowcount){
			  var cols = "";
			  cols += '<tr><td class="dlinfo" style="color: black;" th:id="itmno">' + "  " + developerDatas[stRow][0]  + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="itmdesc">' + "  " + developerDatas[stRow][1] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="cth">' + "  " + developerDatas[stRow][2] + "  " + '</td>';
			   cols += '<td class="dlinfo" style="color: black;" th:id="nou">' + "  " + developerDatas[stRow][3] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="assval">' + "  " + developerDatas[stRow][4] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="duty">' + "  " + developerDatas[stRow][5] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="bcd">' + "  " + developerDatas[stRow][6] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="igst">' + "  " + developerDatas[stRow][7] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="sws">' + "  " + developerDatas[stRow][8] + "  " + '</td>';
			  cols += '<td class="dlinfo" style="color: black;" th:id="sws">' + "  " + developerDatas[stRow][9] + "  " + '</td>';
			 // cols += '<td class="dlinfo" style="color: black;" th:id="sws">' + "  " + developerDatas[stRow][10] + '</td>';
			 // cols += '<td class="dlinfo" style="color: black;" th:id="oth">' + "  " + developerDatas[stRow][11] + '</td></tr>';
			  cols += '</tr>';
			  $('#dataid').append(cols);
			  stRow=stRow+1;
		}
		 $("#dispalldetpopup").modal('show');	
		},
     fail: function(rs, e) {
			console.log(rs, responseText);
		}
	});	
	
}
function  deltabledata()
{
var table = document.getElementById('ITEMREC');
 var rowCount = table.rows.length - 1;
 $("#bulkexptab tbody tr").each(function() {
 var textval = $(this).find("td:eq(0)").text().trim();
 $(this).css('font-size','14');
 $(this).find("td:eq(1)").css('background-color','#dec3c3');
 $(this).find("td:eq(10)").css('background-color','#dec3c3');
 $(this).find("td:eq(11)").css('background-color','#dec3c3');
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
  var i=1;
  while ( i < rowCount )
  {
   document.getElementById("ITEMREC").deleteRow(-1);
   i=i+1;}
}
//


function viewSummaryc(e) {
		 var developerData = {};
         var currow=e.id;
        var pagelen =$('#bulkexptab').DataTable().page.info().length;
        var row=currow % pagelen;
       if (row==0)
           row=row+pagelen;
       row=row+1;
       var cinno= $('#bulkexptab').find('tr').eq(row).find("input#cinno").val();
       var itmid= $('#bulkexptab').find('tr').eq(row).find("input#itmid").val();
       $('#bulkexptab').find('tr').eq(row).css('background-color','green');
       $('#bulkexptab').find('tr').eq(row).find('td').eq(1).css('background-color','green');
       $('#bulkexptab').find('tr').eq(row).find('td').eq(10).css('background-color','green');
       $('#bulkexptab').find('tr').eq(row).find('td').eq(11).css('background-color','green');
       $('#bulkexptab').find('tr').eq(row).css('font-size','18');
       $('#bulkexptab').find('tr').eq(row).css('color','yellow');
       $('#bulkexptab').find('tr').eq(row).css('border','darkgreen');
       developerData['id'] = cinno;
		
	    $.ajax({
                url: 'getSummary?id=' + itmid,
                type: "post",
                success: function(data) {
                    $("#summaryModalBody").html(data);
					$("#summaryModalTitle").text('ARTICLE SUMMARY - '+itmid);
	                $("#summaryModal").modal('show');
                    //$('#doctab').modal('show');
                },
                error: function(rs, e) {
                    alert("Error in nextPageRedirect");
                }
            });
      };



function goback() {	
    ead_list_page = true;	
    //var back=true;
    window.location = "ead_list?ead_list_page=" + ead_list_page ;	
}



function goBack()
  {
    window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
   }	

// Pdf Download
   function pdfdownload(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_EADdata',
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
	
	//PDFDWNLETTERS
	function pdfdownloadLet(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_EADLet',
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
	
	
	
/*	 function clearAlltab(){
	document.getElementById("searchAlltab").value=""
}*/
    
	
	//PDWDWNEMS
	function pdfdownloadEms(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_EADEms',
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
	
	function pdfdownloadPar(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_EADpar',
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
	
	
	
	
	    /*$(document).on('mousemove', '#doctab tbody tr', function(e) {
	        var rowData = " Click any row to view the CN22 / 23  Summary of the Article ID ";
	        $("#tooltip").text(rowData).animate({
	            left: e.pageX,
	            top: e.pageY
	        }, 1);
	
	        if (!$("#tooltip").is(':visible')) $("#tooltip").show();
	
	        $(document).on('mouseleave', 'table', function(e) {
	            $("#tooltip").hide();
	        })
	    });*/
	 

function movoocblk()
	{
		$('#decisionModal').modal('hide');	
		// udpate ead decision for all the selected articles and move to OOC queue
		var table = document.getElementById('bulktab');
        var rowCount = table.rows.length;
        var developerData = {};
        var cin ;
         for (var i = 1; i < rowCount; i++)    
     {      
           cin = $('#bulktab').find('tr').eq(i).find('td:eq(6)').text().substr(0,20);
           itemid = $('#bulktab').find('tr').eq(i).find('td:eq(1)').text().substr(0,13);
           if ($('#bulktab').find('tr').eq(i).find('[name="chkboxblk[]"]').prop('checked'))
            {  developerData['cin_NO'] = cin;
               developerData['deci_CD'] = "1";
               developerData['item_ID']= itemid;
               developerData['qry_TYPE']= "P3";
			$.ajax({
 		        url: 'movbulkasstoooc',
 		       data: JSON.stringify(developerData),
               dataType: "json",
               contentType: "application/json",
               type: "post",
				async: false,
               success: function(developerData) {
                  if (i == (rowCount-1) )
		            {
                      success="yes";		           
	                }					
                },
              fail: function(rs, e) {
                   alert("Error in Import Submit");
               }
          
       });
      }
 }   
      if (success=="yes")
	{	
		refresh();
	$('.dochidden').hide();
	$('#allinitview').hide();
	    $('#bulkview').show();
 $('#itdocview').hide();			
     }
	}
	
	
	function movoocblkexp()
	{
		$('#decisionModal').modal('hide');	
		// udpate ead decision for all the selected articles and move to OOC queue
		var table = document.getElementById('bulkexptab');
        var rowCount = table.rows.length;
        var developerData = {};
        var cin,itemid;
         for (var i = 1; i < rowCount; i++)    
     {      
        //   cin = $('#bulkexptab').find('tr').eq(i).find('td:eq(6)').text().substr(0,20);
            cin= $('#bulkexptab').find('tr').eq(i).find('#cinno').val();
           itemid = $('#bulkexptab').find('tr').eq(i).find('td:eq(1)').text().substr(0,13);
           if ($('#bulkexptab').find('tr').eq(i).find('[name="chkboxblkexp[]"]').prop('checked'))
            {  developerData['cin_NO'] = cin;
               developerData['deci_CD'] = "1";
               developerData['item_ID']= itemid;
               developerData['qry_TYPE']= "P3";
			$.ajax({
 		        url: 'movbulkasstoooc',
 		       data: JSON.stringify(developerData),
               dataType: "json",
               contentType: "application/json",
               type: "post",
				async: false,
               success: function(developerData) {
                  if (i == (rowCount-1) )
		            {
                      success="yes";		           
	                }					
                },
              fail: function(rs, e) {
                   alert("Error in Import Submit");
               }
          
       });
      }
 }   
      if (success=="yes")
	{	refresh();
	$('.dochidden').hide();
	$('#allinitview').hide();
	 $('#bulkview').hide();
	    $('#bulkexpview').show();
 $('#itdocview').hide();			
     }
	}


	function closeSummaryModal(){
 $("#doctab tbody tr").each(function() {
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
		
		$("#bulktab tbody tr").each(function() {
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


$("#bulkexptab tbody tr").each(function() {
 var textval = $(this).find("td:eq(0)").text().trim();
 $(this).css('font-size','14');

$(this).find("td:eq(1)").css('background-color','#dec3c3');
 $(this).find("td:eq(10)").css('background-color','#dec3c3');
 $(this).find("td:eq(11)").css('background-color','#dec3c3');



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
	
	
	function asscomp(){
	$('#assModal').modal('hide');	
	$('#decisionModal').modal('show');			
	}
	
	function pdfdownloadEmp(obj) {
	var oMyForm = new FormData();
	jQuery.ajax({
		url: 'pdfdwnld_EADempty',
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
	

/*$(".chckbx").click(function(){
	
		if ($(this).is(":checked")){
			$("#suball").attr("disabled", false);
		}
		else{
			
			$("#suball").attr("disabled",true);
		}
});*/

$('input[type=checkbox]').click(function(){
	
		if ($(".chckbx:checkbox:checked").length > 0){
			$("#suball").attr("disabled", false);
		}
		else{
			
			$("#suball").attr("disabled",true);
		}
});

$('input[type=checkbox]').click(function(){
	
		if ($(".chckbxblk:checkbox:checked").length > 0){
			$("#suballblk").attr("disabled", false);
		}
		else{
			
			$("#suballblk").attr("disabled",true);
		}
});


function chkBoxClickprocess(){
		if ($(".chckbxblkexp:checkbox:checked").length > 0){
			$("#suballblk").attr("disabled", false);
		}
		else{
			$("#suballblk").attr("disabled",true);
		}
}
function chkBoxClickdoc(){
		if ($(".chckbx:checkbox:checked").length > 0){
			$("#suball").attr("disabled", false);
		}
		else{
			$("#suball").attr("disabled",true);
		}
}

function chkBoxClickexpress(){
		if ($(".chckbxblkexp:checkbox:checked").length > 0){
			$("#suballblkexp").attr("disabled", false);
		}
		else{
			
			$("#suballblkexp").attr("disabled",true);
		}
}


$("#chkselallass").click(function(){
	
		if ($(this).is(":checked")){
			$("#suball").attr("disabled", false);
		}
		else{
			
			$("#suball").attr("disabled",true);
		}
		selallass();
});

$("#chkselblkass").click(function(){
	
		if ($(this).is(":checked")){
			$("#suballblk").attr("disabled", false);
		}
		else{
			
			$("#suballblk").attr("disabled",true);
		}
		selallblk();
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

document.addEventListener("contextmenu", function (e) {
        e.preventDefault();
    }, false);