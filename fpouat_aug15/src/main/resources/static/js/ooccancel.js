      function closePdfModal() {
            $("#pdfView").modal('hide');
        }
	
		function formatDate(date) {
		    var d = new Date(date),
		        month = '' + (d.getMonth() + 1),
		        day = '' + d.getDate(),
		        year = d.getFullYear();
		
		    if (month.length < 2) 
		        month = '0' + month;
		    if (day.length < 2) 
		        day = '0' + day;
		
		    return [ day, month, year].join('/');
		}
		
		var firstDay;
		var lastDay;
		
		function currentDate() {
			var date = new Date(), y = date.getFullYear(), m = date.getMonth();
			firstDay = new Date(y, m, 1);
			lastDay = new Date(y, m + 1, 0);
		}
		
		currentDate();
	
		/*$(function() {
			$("#fromdatepicker").datepicker({
	            dateFormat: "dd/mm/yy",
	            onSelect: function(date) {
	                $("#todatepicker").datepicker('option', 'minDate', date);
	            }
	        });
	
	        $("#todatepicker").datepicker({
	            dateFormat: "dd/mm/yy"
	        });
		});*/
		
		
		
              $(function() {
		var currentDate= new Date();	
			$("#fromdatepicker").datepicker({
	             dateFormat: "dd/mm/yy",
                 maxDate: currentDate,
	            onSelect: function(date) {
	                $("#todatepicker").datepicker('option', 'minDate', date);
	            }
	        });
	
	        $("#todatepicker").datepicker({
	            dateFormat: "dd/mm/yy",
                 maxDate: currentDate,
	             onSelect: function(date) {
	                $("#fromdatepicker").datepicker('option', 'maxDate', date);
	            }
	        });
		});
	
	
	 function closeSummaryModal(){
		$("#summaryModal").modal('hide');
}	
    
		function getOocCancelHistory(){
			if($("#fromdatepicker").val()=='') {
				swal('OOPS!', "Please Select From Date Range", 'error');
				return false;
			}
			if($("#todatepicker").val()=='') {
				swal('OOPS!', "Please Select To Date Range", 'error');
				return false;	
			}
			$("#ooccancelhistorytable").DataTable().ajax.url('getooccancelledarticleshistory?fromDate='+$("#fromdatepicker").val()+'&toDate='+$("#todatepicker").val()).load();	
		}
		
	function resetOocCancelHistory() {
			currentDate();
			var fromDateInput = document.getElementById("fromdatepicker");
var toDateInput = document.getElementById("todatepicker");

 var fromDate=formatDate(firstDay);
 var toDate=formatDate(lastDay);
fromDateInput.value = fromDate;
toDateInput.value = toDate;
			$("#ooccancelhistorytable").DataTable().ajax.url('getooccancelledarticleshistory?fromDate='+formatDate(firstDay)+'&toDate='+formatDate(lastDay)).load();
			
		}
        
        $("#ooccancelprocesstable").DataTable({
        	"processing": false,
            "paging": true,
            "autoWidth": false,
            "ordering": false,
			"dom": '<"pull-left"Bl>frtip',
			buttons: [{extend: 'excel', text: '<i>EXCEL</i>',
				  	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Process OOC Cancellation',
					message:'Process OOC CancellationL @ ' + sitecode + '\n\r' + datetime,
				},
				{extend: 'pdf',
				
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Process OOC Cancellation @ ' + sitecode,
					filename:'Process OOC Cancellation',
				     orientation: 'landscape',
					exportOptions: {
        				//columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						},
		   		     customize: function(doc, header) {
		   		    doc.content[1].table.widths = ['5%','10%','10%','10%','10%','10%','10%','10%','10%','15%'];	
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
					//{extend: "excel", text: '<i>EXCEL</i>',	
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Process OOC Cancellation @ ' + sitecode + '\n\r' + datetime ,
					filename:'Process OOC Cancellation',	   

					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
                       //.addClass( 'compact' )
                       
						}, 
				
			}],
			 "initComplete": function( settings ) {
	        $("#ooccancelprocesstable_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearOoccancelprocesstable()" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			$("#ooccancelprocesstable_filter input[type='search']").attr('id', 'searchOoccancelprocesstable');
    	},
			
            "ajax": {
                url: "processooccancellation",
                "beforeSend": function() {
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
                    "data": "articleId"
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
                    "data": "detentionDate",
                    "render": function(data, type, row, meta) {
                        if (data != undefined) {
                            var detentionDt = new Date(data);
                            var detentionDayOfDate = '' + detentionDt.getDate();
                            if (detentionDayOfDate.length < 2)
                                detentionDayOfDate = '0' + detentionDayOfDate;
                            return detentionDayOfDate + '-' + new Date(data).toLocaleString('default', {
                                month: 'short'
                            }) + '-' + detentionDt.getFullYear();
                        }
                    }
                },
                {
                    "data": "articleId",
                    "render": function(data, type, row, meta) {
	                    var val;
	                    if (row.currentOfficerRole=='PAC' && $("#role").val()=='PAO' ){
                    		return '<div style="margin-bottom: 5px;"><input class="btn-grn" '+
                    		'articleId=' + (row.articleId) + ' onclick="getPopup(this,true,false)" '+
                    		'type="button" value="Click"><span>(with AC) </span></div>';
                        }
                        else if (row.currentOfficerRole=='PAO' && $("#role").val()=='PAC' ){
                    		return '<div style="margin-bottom: 5px;"><input class="btn-grn" '+
                    		'articleId=' + (row.articleId) + ' onclick="getPopup(this,true,false)" '+
                    		'type="button" value="Click"><span>(with PAO) </span></div>';
                        }
                          
                    	else if (row.currentOfficerRole != undefined && row.currentOfficerRole == $("#roleAppraiser").val()){
                    		return '<div style="margin-bottom: 5px;"><input class="btn-grn" '+
                    		'articleId=' + (row.articleId) + ' onclick="getPopup(this,true,'+(row.currentOfficerRole != undefined)+')" '+
                    		'type="button" value="Click"></div><span class="blink-span">AC Reply Received </span>';}
                        else{
                        	return '<input class="btn-grn" articleId=' + (row.articleId) + ' onclick="getPopup(this,true,'+(row.currentOfficerRole != undefined)+')" type="button" value="Click" />';
                        }
                    }
                }
            ],

			



        });
        

 function clearOoccancelprocesstable(){
    document.getElementById("searchOoccancelprocesstable").value=""
    var table = $('#ooccancelprocesstable').DataTable();
    table.search('').draw();
  }

        $("#ooccancelhistorytable").DataTable({
        	"processing": false,
            "paging": true,
            "autoWidth": false,
            "ordering": false,
			"dom": '<"pull-left"Bl>frtip',
			buttons: [{extend: 'excel', text: '<i>EXCEL</i>',
				  	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
				 	filename:'Ooc cancelled History',
					message:'Ooc cancelled History @ ' + sitecode + '\n\r' + datetime,
				},
				{extend: 'pdf',
				
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Ooc cancelled History @ ' + sitecode,
					filename:'Ooc cancelled History',
				     orientation: 'landscape',
					exportOptions: {
        				//columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						},
		   		     customize: function(doc, header) {
		   		    doc.content[1].table.widths = ['5%','7%','7%','7%','8%','10%','10%','10%','8%','10%','10%','8%'];		   		     
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
					//{extend: "excel", text: '<i>EXCEL</i>',	
			{extend: "print" ,
				  className: 'btn btn-danger mb-3',
				  title: 'FPO IMPORT APPLICATION - ICES',
			      text: '<i>PRINT</i>',
				  message:'Ooc cancelled History @ ' + sitecode + '\n\r' + datetime ,
					filename:'Ooc cancelled History',	   

					customize: function ( win ) {
						$(win.document.body).css( 'font-size', '8pt' )
						
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
                       //.addClass( 'compact' )
                       
						}, 
				
			}],
			
			"initComplete": function( settings ) {
	           $("#ooccancelhistorytable_filter").append('<button type="button" class="btn btn-info" id="clear" onclick="clearOoccancelhistorytable()" '+
			  'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
			   $("#ooccancelhistorytable_filter input[type='search']").attr('id', 'searchOoccancelhistorytable');
    	   },

			
             "ajax": {
                url: "getooccancelledarticleshistory?fromDate="+formatDate(firstDay)+"&toDate="+formatDate(lastDay),
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
			           $(td).attr('oocCancelNo', rowData.detainedNo); 
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
                    "data": "detentionDate",
                    "render": function(data, type, row, meta) {
                        if (data != undefined) {
                            var detentionDt = new Date(data);
                            var detentionDayOfDate = '' + detentionDt.getDate();
                            if (detentionDayOfDate.length < 2)
                                detentionDayOfDate = '0' + detentionDayOfDate;
                            return detentionDayOfDate + '-' + new Date(data).toLocaleString('default', {
                                month: 'short'
                            }) + '-' + detentionDt.getFullYear();
                        }
                    }
                },
                {
                	"data": "detainDecision"
                },
                {
                    "data": "detainDecisionDate",
                    "render": function(data, type, row, meta) {
                        if (data != undefined) {
                            var detainDecisionDt = new Date(data);
                            var detainDecisionDayOfDate = '' + detainDecisionDt.getDate();
                            if (detainDecisionDayOfDate.length < 2)
                                detainDecisionDayOfDate = '0' + detainDecisionDayOfDate;
                            return detainDecisionDayOfDate + '-' + new Date(data).toLocaleString('default', {
                                month: 'short'
                            }) + '-' + detainDecisionDt.getFullYear();
                        }
                    }
                },
                {
                    "data": "currentStatus"
                }
            ]
        });
        
        function showLoader() {
            $("#overlay").css("display", "block");
        }

 function clearOoccancelhistorytable(){
        document.getElementById("searchOoccancelhistorytable").value=""
        var table = $('#ooccancelhistorytable').DataTable();
        table.search('').draw();
      } 

        function hideLoader() {
            setTimeout(function() {
                $("#overlay").css("display", "none");
            }, 500);
        }

        hideLoader();

        function getPopup(obj,view,cmtflows) {
            $.ajax({
                url: "ooccancellationremarks?itemId=" + $(obj).attr('articleid') + "&view=" +view + "&cmtflows=" +cmtflows,
                type: "get",
                success: function(data) {
                    $("#oocCancelRemarkBody").html(data);
                    $("#oocCancelRemarkTitle").text('OOC Cancelled Details');
                    $("#oocCancelRemarkModal").modal('show');
                },
                error: function(rs, e) {
                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
                }
            });
        }

        function closeOocCancelModal() {
            $("#oocCancelRemarkBody").html('');
            $("#oocCancelRemarkModal").modal('toggle');
        }
        
        function switchDiv(obj1,obj2,obj3) {
			$('#' + obj1).show('slow');
			$('#' + obj2).hide();
			$('#' + obj3).hide();
        }
        
		function checkOocGiven() {
		    showLoader();
		    $.ajax({
		        url: "checkoocgiven?itemId=" + $("#articleIdEntered").val(),
		        type: "get",
		        success: function(data) {
		            hideLoader();
                    if(data.checkArtID != undefined && !data.checkArtID){
	                    swal('OOPS!', 'Invalid Article ID !', 'error');
                   } else if(data.checkArtID != undefined && !data.checkArtID1 ){
	                    swal('OOPS!', 'Belongs to Other Site !', 'error');
                    } else if (data.oocGiven != undefined && !data.oocGiven) {
		                swal('OOPS!', 'OOC is not given for entered article !', 'error');
		            } else if (data.ooccancelprocess != undefined && data.ooccancelprocess) {
		                swal('ON PROCESS!', 'OOC Cancel for entered article is on process !', 'warning');
		            } else if (data.oocDelivery.oocDate==undefined)
					  swal('OOPS!', 'OOC was given at ' +  data.clrsite + '. Hence OOC cancellation and further proceedings if any can be initiated from that site only');
					  else {
		            	var titletxt = "<h5>OOC IS GIVEN ON DATE : <span style='color:blue'>"+formatDate(data.oocDelivery.oocDate)+"</span></h5>";
		            	if(data.oocDelivery.deliveryDate != undefined){
		            		titletxt = titletxt + "<h5>DELIVERY DATE OF ARTICLE : <span style='color:blue'>"+formatDate(data.oocDelivery.deliveryDate)+"</span></h5>";
		            	}
		            	if(data.oocDelivery.deliveryStatus != undefined){
		            		titletxt = titletxt + "<h5>DELIVERY STATUS OF ARTICLE : <span style='color:blue'>"+data.oocDelivery.deliveryStatus+"</span></h5>";
		            	}else{
		            		titletxt = titletxt + "<h5>DELIVERY STATUS OF ARTICLE : <span style='color:blue'>PENDING ACKNOWLEDGEMENT FROM INDIA POST</span></h5>";
		            	}

		                Swal.fire({
		                        title: titletxt,
		                        text: "Are you sure to initiate OOC Cancellation ?",
			                    icon: "warning",
			                    showCancelButton: true,
			                    confirmButtonColor: '#3085d6',
			                    cancelButtonColor: '#d33',
			                    confirmButtonText: 'Yes'
		                    })
		                    .then((result) => {
		                        if (result.isConfirmed) {
		                            $.ajax({
		                                url: "ooccancellationremarks?itemId=" + $("#articleIdEntered").val() + "&view=false&cmtflows=false",
		                                type: "get",
		                                success: function(data) {
		                                    if (data.oocGiven != undefined && !data.oocGiven) {
		                                        swal('OOPS!', 'OOC is not given for entered article !', 'error');
		                                    } else if (data.ooccancelprocess != undefined && data.ooccancelprocess) {
		                                        swal('ON PROCESS!', 'OOC Cancel for entered article is on process !', 'warning');
		                                    } else {
		                                        $("#oocCancelRemarksSection").html(data);
		                                    }
		                                },
		                                error: function(rs, e) {
		                                    swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		                                }
		                            });
		                        }
		                    });
		            }
		        },
		        error: function(rs, e) {
		            hideLoader();
		            swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		        }
		    });
		}
        
       function viewSummary(obj) {
	$.ajax({
		url: 'getSummary?id=' + $(obj).attr('articleId'),
		type: "post",
		success: function(data) {
			$("#summaryModalBody").html(data);
			$("#summaryModalTitle").text('Article Summary');
			//$("#detentionRemarkModal").modal('hide');
			$("#summaryModal").modal('show');
			$('body').css('overflow-y', 'hidden');
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});
}
        
        function closeDeptCommentsModal() {
        	$("#deptCommentsBody").html('');
	        $("#deptCommentsModal").modal('hide');
        	$("#oocCancelRemarkModal").modal('show');
        }

	    $(document).on('mousemove', '#ooccancelhistorytable tbody tr', function(e) {
	        var rowData = "Click to view details ";
	        $("#tooltip").text(rowData).animate({
	            left: e.pageX,
	            top: e.pageY
	        }, 1);
	
	        if (!$("#tooltip").is(':visible')) $("#tooltip").show();
	
	        $(document).on('mouseleave', 'table', function(e) {
	            $("#tooltip").hide();
	        })
	    });
	    
	    $('#ooccancelhistorytable tbody').on('click', 'tr', function() {
	    	var articleId=$(this).children('td:eq(1)').text();
	        $.ajax({
	            url: 'ooccancelremarksbyarticleidanddetainedno?itemId=' + articleId + '&oocCancelNo=' + $(this).children('td:eq(1)').attr('oocCancelNo'),
	            type: "post",
	            success: function(data) {
                    $("#oocCancelRemarkBody").html(data);
                    $("#oocCancelRemarkTitle").text('OOC Cancelled Details');
        			$("#oocCancelRemarkModal").modal('show');
	            },
	            error: function(rs, e) {
	                swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
	            }
	        });
    	});

   function validateAndAddCharacters() {
  var textField = document.getElementById("articleIdEntered");
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