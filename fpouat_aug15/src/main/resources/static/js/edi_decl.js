var error=0;

function chkiec(){
if ($('#IEC').val().length == 10)
	{
	 var iec= $('#IEC').val();
	$.ajax({
		url:"chkiec?iec="+iec,
		global: false,
		type: "post",
		dataType: "text",
		success: function(data) {
			 if(data == "")
				{swal('OOPS!', 'IEC Number Not Found!', 'error');error=1;}
				},});}
}
	
	
	
	
function movpers(){
	 $("#movpersModal").modal('toggle');	
}

function movpersconf(){	
	var itemid = $('#itemId').val();
	var cinno=$('#cinno').val();
	$.ajax({
		url:"movpers?itemid="+itemid,
		global:false,
		type:"post",
		dataType: "text",
		success: function(data){
			if (data=="success")
			location.href = "import_main?id="+cinno;
			else
			location.href = "pen_main?id="+cinno;
			},
		});
	}

	
function chkgst(){
if ($('#GSTIN_ID').val().length == 15)
	{
	 var gst= $('#GSTIN_ID').val();
	$.ajax({
		url:"chkgst?gst="+gst,
		global: false,
		type: "post",
		dataType: "text",
		success: function(data) {
			if(data == "")
				{swal('OOPS!', 'GST Number Not Found!', 'error');error=1;}
	},});}
}	

function chkad()
{
	if ($('#ADCOD').val().length == 7 )
	{
	var adcode= $('#ADCOD').val() ;
		$.ajax({
			url:"chkadcd?adcode="+adcode,
			global : false,
			type : "post",
			dataType : "text",
			success: function(data){
			  if(data == "")
					{swal('OOPS!' , 'ADCODE Number Not Found!', 'error');error=1;}
					},
				}); 
				}	
}


   $(function() {
		$("#BEDT").datepicker({
            dateFormat: "dd/mm/yy ",
        });
	});
	
	   $(function() {
		$("#CHLDT").datepicker({
            dateFormat: "dd/mm/yy",
        });
	});



//function chkedi()

$("#EDI_SITE").keyup(function()
{
	chkedisite();
});

function chkedisite()
{
	var edi= $("#EDI_SITE").val();
	var len=edi.length;
	error=0;
	if (len > 0){
	edi=edi.toUpperCase();
	$("#EDI_SITE").val(edi);
	var ilet=edi.substr(0, 1);
	var nlet=edi.substr(1, 1);
	var alet;
	if ((ilet !='I') || (nlet !='N'))
	   {swal('OOPS!', 'First 2 characters should be IN!', 'error');error=1;}
	else if ((len==3 || len==4 || len==5 || len==6) && error==0){
		alet=edi.substr(2, 1);
		if (!(alet >= 'A' && alet <= 'Z')) {  
		{swal('OOPS!', 'Middle 3 characters should be Alphabets!', 'error');error=1;}
	 			   	 }
	    if (len==4 || len==5)
	    {   alet=edi.substr(3, 1);
		if (!(alet >= 'A' && alet <= 'Z')) {  
		swal('OOPS!', 'Middle 3 characters should be Alphabets!', 'error');error=1;}
	 			   	 }
		if (len==4 || len==5)
	    {   alet=edi.substr(4, 1);
		if (!(alet >= 'A' && alet <= 'Z')) {  
		swal('OOPS!', 'Middle 3 characters should be Alphabets!', 'error');error=1;}
	 			   	 }
	}
	if (len==6 && error==0){	
		var num =edi.substr(5, 1); 	
		if (!(num >= '0' && num <= '9'))
	       swal('OOPS!', 'Last character should be  a digit!', 'error');error=1;}}
}

/*function validatedecl()
{
	//alert("decl data is to be saved...");
	//	if (ieclen=10 && gstin==15 && adcode==7) {
		var error=0;
		if ($('#BE_NO').val() == '')
		   {swal('OOPS!', 'Please enter BE Number!', 'error');error=1;}
		if (error==0)
		{if ($('#BEDT').val() == ''  )
		  {swal('OOPS!' , 'Please Select MBE Date!', 'error');error=1;}
	    }
        if (error==0)
		{if ($('#IEC').val().length != 10)
		   {swal('OOPS!', 'IEC Number not correct!', 'error');error=1;}
		else 
		   chkiec();}
		if (error==0)
        {if ($('#GSTIN_ID').val().length != 15)
		   {swal('OOPS!', 'GST Number not correct!', 'error');error=1;}
		else
		chkgst();}
		if (error==0)
		{if ($('#ADCOD').val().length != 7 )
		  {swal('OOPS!' , 'ADCODE Number not correct!', 'error');error=1;}
	    else
	    chkad();}
		if (error==0)
		{if ($('#SCHEME_CD').val() == '' )
		  {swal('OOPS!' , 'Please Enter Scheme!', 'error');error=1;}
	    }
		if (error==0)
		{if ($('#LICENSE_NO').val() == '' )
		  {swal('OOPS!' , 'Please Enter License No!', 'error');error=1;}
	   	}
		if (error==0)
		{if ($('#PERMISSION_NO').val() == '' )
		  {swal('OOPS!' , 'Please Enter Permission No!', 'error');error=1;}
	    }
				if (error==0)
		 {if ($("#EDI_SITE").val()=='' || $('#EDI_SITE').val().length != 6)
	       {swal('OOPS!', 'Please enter EDI SITE!', 'error');error=1;}
         else
		   chkedisite();}
		if (error==0)
		{if ($('#CHALLLAN_NO').val() == '' )
		  {swal('OOPS!' , 'Please Enter Challan No!', 'error');error=1;}
	    }
		if (error==0)
		{if ($('#CHLDT').val() == '' )
		  {swal('OOPS!' , 'Please Select Challan Date!', 'error');error=1;}
	    }

		if (error==0)
			 $("#saveModal").modal('toggle');
				
	//		}else{
}*/


function validatedecl()
{
	//alert("decl data is to be saved...");
	//	if (ieclen=10 && gstin==15 && adcode==7) {
		var error=0;
		if ($('#BE_NO').val() == '')
		   {swal('OOPS!', 'Please enter BE Number!', 'error');error=1;}
		if (error==0)
		{if ($('#BEDT').val() == ''  )
		  {swal('OOPS!' , 'Please Select MBE Date!', 'error');error=1;}
	    }
        if (error==0)
		{if ($('#IEC').val().length != 10)
		   {swal('OOPS!', 'IEC Number not correct!', 'error');error=1;}
		else 
		   chkiec();}
		if (error==0)
        {if ($('#GSTIN_ID').val().length != 15)
		   {swal('OOPS!', 'GST Number not correct!', 'error');error=1;}
		else
		chkgst();}
		if (error==0)
		{if ($('#ADCOD').val().length != 7 )
		  {swal('OOPS!' , 'ADCODE Number not correct!', 'error');error=1;}
	    else
	    chkad();}
		if($('#SCHEME_CD').val() != ''){
		if (error==0)
		{if ($('#SCHEME_CD').val() == '' )
		  {swal('OOPS!' , 'Please Enter Scheme!', 'error');error=1;}
	    }
		if (error==0)
		{if ($('#LICENSE_NO').val() == '' )
		  {swal('OOPS!' , 'Please Enter License No!', 'error');error=1;}
	   	}
		if (error==0)
		{if ($('#PERMISSION_NO').val() == '' )
		  {swal('OOPS!' , 'Please Enter Permission No!', 'error');error=1;}
	    }
				if (error==0)
		 {if ($("#EDI_SITE").val()=='' || $('#EDI_SITE').val().length != 6)
	       {swal('OOPS!', 'Please enter EDI SITE!', 'error');error=1;}
         else
		   chkedisite();}
		}
		if (error==0)
		{if ($('#CHALLLAN_NO').val() == '' )
		  {swal('OOPS!' , 'Please Enter Challan No!', 'error');error=1;}
	    }
		if (error==0)
		{if ($('#CHLDT').val() == '' )
		  {swal('OOPS!' , 'Please Select Challan Date!', 'error');error=1;}
	    }

		if (error==0)
			 $("#saveModal").modal('toggle');
				
	//		}else{
}
				
						
			


/*function savedecl()
{
		
	$('#edi_decl_add_id').trigger('submit');

}*/

function uploaddoc(){
	
	var error=0;
		if ($('#BE_NO').val() == '')
		   {swal('OOPS!', 'Please enter BE Number!', 'error');error=1;}
		if (error==0)
		{if ($('#BEDT').val() == ''  )
		  {swal('OOPS!' , 'Please Select MBE Date!', 'error');error=1;}
	    }
        if (error==0)
		{if ($('#IEC').val().length != 10)
		   {swal('OOPS!', 'IEC Number not correct!', 'error');error=1;}
		else 
		   chkiec();}
		if (error==0)
        {if ($('#GSTIN_ID').val().length != 15)
		   {swal('OOPS!', 'GST Number not correct!', 'error');error=1;}
		else
		chkgst();}
		if (error==0)
		{if ($('#ADCOD').val().length != 7 )
		  {swal('OOPS!' , 'ADCODE Number not correct!', 'error');error=1;}
	    else
	    chkad();}
		if($('#SCHEME_CD').val() != ''){
		if (error==0)
		{if ($('#SCHEME_CD').val() == '' )
		  {swal('OOPS!' , 'Please Enter Scheme!', 'error');error=1;}
	    }
		if (error==0)
		{if ($('#LICENSE_NO').val() == '' )
		  {swal('OOPS!' , 'Please Enter License No!', 'error');error=1;}
	   	}
		if (error==0)
		{if ($('#PERMISSION_NO').val() == '' )
		  {swal('OOPS!' , 'Please Enter Permission No!', 'error');error=1;}
	    }
				if (error==0)
		 {if ($("#EDI_SITE").val()=='' || $('#EDI_SITE').val().length != 6)
	       {swal('OOPS!', 'Please enter EDI SITE!', 'error');error=1;}
         else
		   chkedisite();}
		}
		if (error==0)
		{if ($('#CHALLLAN_NO').val() == '' )
		  {swal('OOPS!' , 'Please Enter Challan No!', 'error');error=1;}
	    }
		if (error==0)
		{if ($('#CHLDT').val() == '' )
		  {swal('OOPS!' , 'Please Select Challan Date!', 'error');error=1;}
	    }

		if (error==0)
	$("#uploadpopup").modal('toggle');
}


$('#saveandproceed').click(function() {
		
			Form1.append("ITEM_ID",$("#ITEM_ID").val());
			Form1.append("CIN_NO",$("#CIN_NO").val());
			Form1.append("CINDT",$("#CINDT").val());
			Form1.append("BE_NO",$("#BE_NO").val());
			Form1.append("BEDT",$("#BEDT").val());
			Form1.append("IEC",$("#IEC").val());
			Form1.append("GSTIN_ID",$("#GSTIN_ID").val());
			Form1.append("ADCOD",$("#ADCOD").val());
			Form1.append("SCHEME_CD",$("#SCHEME_CD").val());
			Form1.append("LICENSE_NO",$("#LICENSE_NO").val());
			Form1.append("PERMISSION_NO",$("#PERMISSION_NO").val());
			Form1.append("EDI_SITE",$("#EDI_SITE").val());
			Form1.append("CHALLLAN_NO",$("#CHALLLAN_NO").val());
			Form1.append("CHLDT",$("#CHLDT").val());
			Form1.append("BE_DOC",$("#BE_DOC-1").val());
			Form1.append("INV_DOC",$("#INV_DOC-1").val());
			Form1.append("CHL_DOC",$("#CHALLAN_DOC-1").val());
			if($("#BE_DOC-1").val().length > 0){
				Form1.append("filename1",kycFiles[$("#BE_DOC-1").attr('id').split('-')[$("#BE_DOC-1").attr('id').split('-').length - 1]].files[0]);
				}
	
			if($("#INV_DOC-1").val().length > 0){
				Form1.append("filename2",kycFiles1[$("#INV_DOC-1").attr('id').split('-')[$("#INV_DOC-1").attr('id').split('-').length - 1]].files[0]);
			}
	
	
			if($("#CHALLAN_DOC-1").val().length > 0){
				Form1.append("filename3",kycFiles2[$("#CHALLAN_DOC-1").attr('id').split('-')[$("#CHALLAN_DOC-1").attr('id').split('-').length - 1]].files[0]);
			}
				
				/*var bedocName1 = $("#BE_DOC-1").val();
				var invdocName1 = $("#INV_DOC-1").val();
				var challandocName1 = $("#CHALLAN_DOC-1").val();
				if(bedocName1 == ''){
					bedocName1 = 1;
					Form1.append("bedoc1",bedocName1);
				}
				if(invdocName1 == ''){
					invdocName1 = 1;
					Form1.append("invdoc1",invdocName1);
				}
				if(challandocName1 == ''){
					challandocName1 = 1;
					Form1.append("chldoc1",challandocName1);
				}*/
				
				var bedocName1 = 1;
				Form1.append("bedoc1",bedocName1);
				var invdocName1 = 1;
				Form1.append("invdoc1",invdocName1);
				var challandocName1 = 1;
				Form1.append("chldoc1",challandocName1);
				$.ajax({
					url: 'edi_decl_add' ,
					// data: JSON.stringify(resp),
					data: Form1,
					// dataType: "json",
					//contentType: "application/json",
					contentType: false,
					processData: false,
					type: "POST",
					enctype: 'multipart/form-data',
					success: function(data) {
					movpersconf()
					},
				});
			
      
		});
		
		
		
		var Form1 = new FormData();

		$('.commercialsave').click(function() {
		
			Form1.append("ITEM_ID",$("#ITEM_ID").val());
			Form1.append("CIN_NO",$("#CIN_NO").val());
			Form1.append("CINDT",$("#CINDT").val());
			Form1.append("BE_NO",$("#BE_NO").val());
			Form1.append("BEDT",$("#BEDT").val());
			Form1.append("IEC",$("#IEC").val());
			Form1.append("GSTIN_ID",$("#GSTIN_ID").val());
			Form1.append("ADCOD",$("#ADCOD").val());
			Form1.append("SCHEME_CD",$("#SCHEME_CD").val());
			Form1.append("LICENSE_NO",$("#LICENSE_NO").val());
			Form1.append("PERMISSION_NO",$("#PERMISSION_NO").val());
			Form1.append("EDI_SITE",$("#EDI_SITE").val());
			Form1.append("CHALLLAN_NO",$("#CHALLLAN_NO").val());
			Form1.append("CHLDT",$("#CHLDT").val());
			Form1.append("BE_DOC",$("#BE_DOC-1").val());
			Form1.append("INV_DOC",$("#INV_DOC-1").val());
			Form1.append("CHL_DOC",$("#CHALLAN_DOC-1").val());
			if($("#BE_DOC-1").val().length > 0){
				Form1.append("filename1",kycFiles[$("#BE_DOC-1").attr('id').split('-')[$("#BE_DOC-1").attr('id').split('-').length - 1]].files[0]);
				}
	
			if($("#INV_DOC-1").val().length > 0){
				Form1.append("filename2",kycFiles1[$("#INV_DOC-1").attr('id').split('-')[$("#INV_DOC-1").attr('id').split('-').length - 1]].files[0]);
			}
	
	
			if($("#CHALLAN_DOC-1").val().length > 0){
				Form1.append("filename3",kycFiles2[$("#CHALLAN_DOC-1").attr('id').split('-')[$("#CHALLAN_DOC-1").attr('id').split('-').length - 1]].files[0]);
			}
				
				 var bedocName1 = 2;
					Form1.append("bedoc1",bedocName1);
				 var invdocName = 2;
					Form1.append("invdoc1",invdocName);
				 var challandocName = 2;
					Form1.append("chldoc1",challandocName);
			
				
				$.ajax({
					url: 'edi_decl_add' ,
					// data: JSON.stringify(resp),
					data: Form1,
					// dataType: "json",
					//contentType: "application/json",
					contentType: false,
					processData: false,
					type: "POST",
					enctype: 'multipart/form-data',
					success: function(data) {
					movpersconf()
					},
				});
			
      
		});


$(function(){
	$("#BEDT").datepicker({
		dateformat: "dd/mm/yy",
		onSelect: function(date) {
			$("#BEDT").datepicker('option','minDate', date);
		}
	});
});

$(function(){
	$("#CHLDT").datepicker({
		dateformat: "dd/mm/yy",
		onSelect: function(date) {
			$("#CHLDT").datepicker('option','minDate', date);
		}
	});
});

	
	
	
