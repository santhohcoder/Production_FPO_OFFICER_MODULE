   
//by santhosh
$("#queryreplyhistorytable").DataTable({
        	"processing": false,
            "paging": true,
            "autoWidth": false,
            "ordering": false,
             "dom": '<"pull-left"Bl>frtip',
              buttons: [{extend: "excel", text: '<i>EXCEL</i>',
				   className: 'btn btn-primary mb-3',
				   title:'FPO IMPORT APPLICATION - ICES',
					filename:' Query Reply History ',
					message:' Query Reply History @ ' + sitecode + '\n\r' + datetime, 
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
		   		    doc.content[1].table.widths = ['3%','8%','8%','8%','9%','8%','8%','8%','8%','8%','8%','8%','8%'];
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
							'\n\r'+'Query Reply History @ ' + sitecode,
           			//message: empid,
					//message:datetime, 
					filename:'Query Reply History  ',

     },
			//Print
		         {extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:' Query Reply History @ ' + sitecode + '\n\r' + datetime ,
					filename:'Query Reply History ',
					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},}],
             
              "initComplete": function( settings ) {
	        $("#queryreplyhistorytable_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearqueryreplyhistorytable()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#queryreplyhistorytable_filter input[type='search']").attr('id', 'searchqueryreplyhistorytable');
    	},   
             
            "ajax": {
                 url: "getqryreplyarticleshistory?fromDate=" + formatDate(firstDay) + "&toDate=" + formatDate(lastDay),
                  "beforeSend": function() {
                var start=formatDate(firstDay);
                var end=formatDate(lastDay);
                
              
                
              $("#fromdatepicker").attr("value", start);
  				$("#todatepicker").attr("value", end);
  			
                    showLoader();
                },
            },
            "fnDrawCallback": function(oSettings) {
                hideLoader();
            },
            "columnDefs": [{
                "targets": '_all',
                "defaultContent": ""
            }],
            "columns": [{
                    "data": "articleId",
                    "render": function(data, type, row, meta) {
                        return meta.row + 1;
                    }
                },
                {
                    "data": "articleId",
			        "createdCell":  function (td, cellData, rowData, row, col) {
			           $(td).attr('detainedNo', rowData.detainedNo); 
			        }
                },
                {
                    "data": "articleDate",
                    "render": function(data, type, row, meta) {
                        if (data != undefined) {
                            var articleDt = new Date(data);
                            var articleDayOfDate = '' + articleDt.getDate();
                            if (articleDayOfDate.length < 2)
                                articleDayOfDate = '0' + articleDayOfDate;
                            return articleDayOfDate + '-' + new Date(data).toLocaleString('default', {
                                month: 'short'
                            }) + '-' + articleDt.getFullYear();
                        }
                    }
                },
                {
                    "data": "originCountry"
                },
                {
                    "data": "mailClass"
                },
                {
                    "data": "itemCategory"
                },
                {
                    "data": "arrivalOOEDate",
                    "render": function(data, type, row, meta) {
                        if (data != undefined) {
                            var arrivalOOEDt = new Date(data);
                            var arrivalOOEDayOfDate = '' + arrivalOOEDt.getDate();
                            if (arrivalOOEDayOfDate.length < 2)
                                arrivalOOEDayOfDate = '0' + arrivalOOEDayOfDate;
                            return arrivalOOEDayOfDate + '-' + new Date(data).toLocaleString('default', {
                                month: 'short'
                            }) + '-' + arrivalOOEDt.getFullYear();
                        }
                    }
                },
                {
                    "data": "arrivalFPODate",
                    "render": function(data, type, row, meta) {
                        if (data != undefined) {
                            var arrivalFPODt = new Date(data);
                            var arrivalFPODayOfDate = '' + arrivalFPODt.getDate();
                            if (arrivalFPODayOfDate.length < 2)
                                arrivalFPODayOfDate = '0' + arrivalFPODayOfDate;
                            return arrivalFPODayOfDate + '-' + new Date(data).toLocaleString('default', {
                                month: 'short'
                            }) + '-' + arrivalFPODt.getFullYear();
                        }
                    }
                },
 				{
                    "data": "replyDate",
                    "render": function(data, type, row, meta) {
                        if (data != undefined) {
                            var replyDt = new Date(data);
                            var replyDayOfDate = '' + replyDt.getDate();
                            if (replyDayOfDate.length < 2)
                                replyDayOfDate = '0' + replyDayOfDate;
                            return replyDayOfDate + '-' + new Date(data).toLocaleString('default', {
                                month: 'short'
                            }) + '-' + replyDt.getFullYear();
                        }
                    }
                },
              	{
                    "data": "officerId"
                },
                {
                    "data": "officerId",
                    "render": function(data, type, row, meta) {
                        if (row.additionalQry) {
                            return '<td>ADDITIONAL QUERY</td>';
                        } else {
                            return '<td>INITIAL QUERY</td>';

                        }
                    }
                },
                {
                    "data": "articleId",
                    "render": function(data, type, row, meta) {
                        if (row.additionalQry) {
                            return '<i style="cursor:pointer;" textMsg="VIEW ADDITIONAL QUERY REPLY & UPLOADED DOCUMENTS" url="viewadditionalqueryreply?cinNo=' + row.cinNo + '&articleStage=' + row.articleStage + '" onclick="viewQueryReply(this)" class="fa fa-eye" aria-hidden="true"></i>';
                        } else {
                            return '<i style="cursor:pointer;" textMsg="VIEW QUERY REPLY & UPLOADED DOCUMENTS" url="viewqueryreply?cinNo=' + row.cinNo + '" onclick="viewQueryReply(this)" class="fa fa-eye" aria-hidden="true"></i>';

                        }
                    }
                },
                {
                    "data": "currentStatus"
                }
            ]
            
            
            
            
        });


function clearqueryreplyhistorytable(){
        document.getElementById("searchqueryreplyhistorytable").value=""
        var table = $('#queryreplyhistorytable').DataTable();
        table.search('').draw();
      }





 function getQueryReplyHistory() {
            if ($("#fromdatepicker").val() == '') {
                swal('OOPS!', "Please Select From Date Range", 'error');
                return false;
            }
            if ($("#todatepicker").val() == '') {
                swal('OOPS!', "Please Select To Date Range", 'error');
                return false;
            }
         articleDatas = [];
        var formData = new FormData();
        formData.append("fromDate", $("#fromdatepicker").val());
        formData.append("toDate", $("#todatepicker").val());
        const data = [...formData.entries()];
       const asString = data
         .map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
         .join('&');
               $("#queryreplyhistorytable").DataTable().ajax.url('getqryreplyarticleshistory?'+asString).load();
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