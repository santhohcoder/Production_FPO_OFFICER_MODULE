    var articleTable = "";
    $(function() {
        $("#fromdatepicker").datepicker({
            dateFormat: "dd/mm/yy",
            onSelect: function(date) {
                $("#todatepicker").datepicker('option', 'minDate', date);
            }
        });

        $("#todatepicker").datepicker({
            dateFormat: "dd/mm/yy"
        });

        $("#senderCountry").select2();
        $("#receiverCountry").select2();
        $("#mailCategory").select2();
        $("#mailClass").select2();

        articleTable = $("#articleTable").DataTable({
            "processing": true,
            "paging": true,
			"dom": '<"pull-left"Bl>frtip',
			buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:"View Import Article IDs & It's Movement",
					message:"View Import Article IDs & It's Movement @ "  + sitecode + '\n\r' + datetime,},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+"View Import Article IDs & It's Movement @ " + sitecode,
					filename:"View Import Article IDs & It's Movement",
				     orientation: 'landscape',
		   		     customize: function(doc, header) {
					 doc.content[1].table.widths = ['5%','15%','15%','15%','15%','15%','20%'];
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
				  message:"View Import Article IDs & It's Movement @ " + sitecode + '\n\r' + datetime ,
					filename:"View Import Article IDs & It's Movement",	
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
			
			 "initComplete": function( settings ) {
	        $("#articleTable_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearArticleTable()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#articleTable_filter input[type='search']").attr('id', 'searchArticleTable');
    	},
			
            "oLanguage": {
        "sEmptyTable": "Enter Article ID to view"
    	},
            "ordering": false,
          /*  "ajax": {
                url: "getArticleDatas",
                "async": false,
                "data": function(data) {
                    //process data before sent to server.
                }
            },*/
            "columnDefs": [{
                "targets": '_all',
                "defaultContent": ""
            }],
            "columns": [{
                    "data": "itemId",
                    "render": function(data, type, row, meta) {
                    	return meta.row+1;
                    }
                },
                {
                    "data": "itemId",
                },
                {
                    "data": "postingDate"
                },
                {
                    "data": "country"
                },
                {
                    "data": "mailClass"
                },
                {
                    "data": "mailCategory"
                },
                {
                    "data": "status"
                }
            ]
        });
	
	$('#articleTable tbody').on( 'click', 'tr', function () {
		
    	window.location.href='article-details?itemId='+articleTable.row(this).data().itemId;
	} );
    });


  function clearArticleTable(){
       document.getElementById("searchArticleTable").value=""
       var table = $('#articleTable').DataTable();
       table.search('').draw();
   }  


    var articleDatas = [];

     function getArticleData() {
        articleDatas = [];
        var formData = new FormData();

        $('.mr-10:checkbox:checked').each(function() {
            if ($(this).attr('rel') === 'item-posted') {
                formData.append("itemPosted", true);
            }
            if ($(this).attr('rel') === 'ead-arrival') {
                formData.append("eadArrival", true);
            }
            if ($(this).attr('rel') === 'query-date') {
                formData.append("queryRaised", true);
            }
            if ($(this).attr('rel') === 'query-reply-date') {
                formData.append("queryReplied", true);
            }
            if ($(this).attr('rel') === 'aaaooe') {
                formData.append("articleArrivalOOE", true);
            }
            if ($(this).attr('rel') === 'aaafpo') {
                formData.append("articleArrivalFPO", true);
            }
            if ($(this).attr('rel') === 'ooc-given') {
                formData.append("oocGiven", true);
            }
        })
        formData.append("fromDate", $("#fromdatepicker").val());
        formData.append("toDate", $("#todatepicker").val());
        formData.append("senderCountry", $("#senderCountry").val());
        formData.append("mailCategory", $("#mailCategory").val());
        formData.append("mailClass", $("#mailClass").val());
        formData.append("articleId", $("#articleId").val());
		$.ajax({
		        url: "getArticleDatas?articleId=" + $("#articleId").val(),
		        type: "get",
		        success: function(data) {
			var checkArtID = data.checkArtID;
            var checkArtID1 = data.checkArtID1;
            if (checkArtID && checkArtID1) {
        		const datas = [...formData.entries()];
		 		const asString = datas
		      	.map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
		      	.join('&');
		  		articleTable.ajax.url('getArticleDatas?'+asString).load();
		  		if(articleTable.rows()[0].length<1){
		  		$("#articleTable").removeClass('emptytable');
			  	}else{
			  	$("#articleTable").addClass('emptytable');
			  	}
	} else {
			  if (!checkArtID) {
		                /* swal('OOPS!', 'Invalid Article ID !', 'error');*/

                  swal({
                   title: 'OOPS!',
                   text: 'Invalid Article ID !',
                   icon: 'error',
                   buttons: {
                   confirm: {
                   text: 'OK',
                   className: 'ok-button'
              }
            }
        });                    

		         } else if (!checkArtID1) {
			
			           /* swal('OOPS!', 'Belongs to Other Site !', 'error');*/
		            swal({
                  title: 'OOPS!',
                  text: 'Belongs to Other Site !',
                   icon: 'error',
                   buttons: {
                   confirm: {
                   text: 'OK',
                   className: 'ok-button'
                 }
               }
  });
}


			   }  
	
	   } 
        });  
	 }
      

    
     $(document).ready(function() {
	$('#dashscreen').show();
	$('#nodashboard').hide();
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

 function validateAndAddCharacters2() {
  var textField = document.getElementById("articleId");
  var input = textField.value;

  // Check first character
  if (input.length === 1) {
  var regex = /^[a-zA-Z]$/;
  if (!regex.test(input)) {
    textField.value = "";
    return;
  } else {
    textField.value = input.toUpperCase();
  }
}

// Check second character
if (input.length === 2) {
  var regex = /^[a-zA-Z][a-zA-Z]*$/;
  if (!regex.test(input)) {
    textField.value = input[0].toUpperCase();
    return;
  }else {
    textField.value = input.toUpperCase();
  }
}

// Check next nine characters
if (input.length >= 3 && input.length <= 11) {
  var regex = /^[a-zA-Z]{0,2}\d{0,9}$/;
  if (!regex.test(input)) {
    textField.value = input.slice(0, -1);
    return;
  }
}

// Check last two characters
if (input.length >= 12) {
  var regex = /^[a-zA-Z]{0,2}\d{0,9}[a-zA-Z]{0,2}$/;
  if (!regex.test(input)) {
    textField.value = input.slice(0, -1);
    return;
  }else {
    textField.value = input.toUpperCase();
  }
}
}
