//enable button
document.getElementById("blockSite").disabled = true;
document.getElementById("deleteSite").disabled = true;
document.getElementById("unBlockSite").disabled = true;
function EnableDisable(recordMsg) {

	var str = recordMsg.value.trim();
	if (recordMsg.value.length <= 4 || str == "") {
		document.getElementById("blockSite").disabled = true;
		document.getElementById("deleteSite").disabled = true;
		document.getElementById("unBlockSite").disabled = true;
	} else {
		document.getElementById("blockSite").disabled = false;
		document.getElementById("deleteSite").disabled = false;
		document.getElementById("unBlockSite").disabled = false;
	}
};

function clearDropDown(e) {
	e.empty();
}


/*function editsite(e){
	var editdata = {};
	var userval =  $('#offId').val();
	var s = e.value;
	editdata['siteCode']= e.id;
	editdata['siteName']= e.value;
	if (userval != "") {
				$.ajax({
					url: 'editSite',
					data: JSON.stringify(editdata),
                    dataType: "json",
					contentType: "application/json",
					type: "post",
					success: function(resp) {
						alert("cms here");
						window.location = "editSite";
					},
					fail: function(rs, e) {
						console.log(rs, responseText);
					}

				});

			} else {
				alert("No longer login-id required, please login again!.");
			}
}*/

function editsite(e){
	var userval =  $('#offId').val();
	if (userval != "") {
				$.ajax({
					url: 'editSite',
				    data: {
				        siteCode: e.id,
				        siteName: e.value
				    },
					type: "get",
					success: function(resp) {
					//	alert("cms here");
				        window.location.href = "/editSite?siteCode=" + encodeURIComponent(e.id) + "&siteName=" + encodeURIComponent(e.value);
				    },
					fail: function(rs, e) {
						console.log(rs, responseText);
					}

				});

			} else {
				alert("No longer login-id required, please login again!.");
			}
}


function sitedelete(del) {
	var deldata = {};
	var userval = $('#offId').val();
	$('.deleteresp').val("");
	$('#sitename').val(del.value);
	$('#sitecode').val(del.id);
	$('#delmsg').html("Please provide reasons for deleting this FPO site - " + del.value +  " (" + del.id + ") ");
	$('#myModaldel').modal('show');
}

$('#deleteSite').click(function() {

	var recordMsgValue = document.querySelector('textarea[name="deleteres"]').value;

	var reg =/<(.|\n)*?>/g; 
			 if (reg.test(recordMsgValue) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }

	    var deldata = {};
		deldata['siteCode'] = $('#sitecode').val();
		deldata['reason'] = $('.deleteresp').val();
		deldata['siteName'] = $('#sitename').val();
		$('#myModaldel').modal('hide');
		$('#delcnfmsg').html("Do you want to delete this FPO Site  - " +  $('#sitename').val() +  " (" +  $('#sitecode').val() + ") ?");
		$('#deletecnfrm').modal('show');
});
		
$('#sitedeletecnfrm').click(function() {
	 var deldata = {};
     var userval = $('#offId').val();
		deldata['siteCode'] = $('#sitecode').val();
		deldata['reason'] = $('.deleteresp').val();
		deldata['siteName'] = $('#sitename').val();
			if (userval != "") {
				$.ajax({
					url: 'deletefposite',
					data: JSON.stringify(deldata),
					dataType: "json",
					contentType: "application/json",
					type: "post",
					success: function(resp) {
						window.location = "viewactiveList";;
					},
					fail: function(rs, e) {
						console.log(rs, responseText);
					}

				});

			} else {
				alert("No longer login-id required, please login again!.");
			}

})

		
           $('#delcnfrmNo').click(function() {
			del.id = "";
			})

	
	 $('.deleteclose').click(function() {
			del.id = "";
			});	


function siteblock(blk) {
	var blckdata = {};
	var userval = $('#offId').val();
	 $('.blcresp').val("");
     $('#blkmsg').html("Please provide reasons for Blocking this FPO site - " + blk.value +  " (" + blk.id + ") ");
	$('#myModalBl').modal('show');
	$('#blockSite').click(function() {
	var reg =/<(.|\n)*?>/g;
			 if (reg.test($('#recordMsg').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
		blckdata['siteCode'] = blk.id;
		blckdata['reason'] = $('.blcresp').val();
		blckdata['siteName'] = blk.value;
		$('#myModalBl').modal('hide');
		$('#blkcnfmsg').html("Do you want to block this FPO Site - " + blk.value +  " (" + blk.id + ") ");
		$('#blckcnfrm').modal('show');
		$('#siteblkcnfrm').click(function() {
			if (userval != "") {
				$.ajax({
					url: 'blockfposite',
					data: JSON.stringify(blckdata),
					dataType: "json",
					contentType: "application/json",
					type: "post",
					success: function(resp) {
						window.location = "viewactiveList";
					},
    error: function(xhr, textStatus, errorThrown) {
        if (xhr.status === 400) {
             window.location = "error"; // Redirect to the error page
        } else {
            // Handle other error cases
            console.log(xhr, textStatus, errorThrown);
        }
    },

				});

			} else {
				alert("No longer login-id required, please login again!.");
			};

		});
			
		$('#blckcnfrmNo').click(function() {
			blk.id = "";
			});
	});
	
	$('#blcsiteclose').click(function() {
			blk.id = "";
			console.log(blk.id);
			});
};

function siteunblock(unblk) {
	var unblckdata = {};
	var userval = $('#offId').val();
	$('.unblckresp').val("");
	$('#unblkmsg').html("Please provide reasons for UnBlocking this FPO site - " + unblk.value +  " (" + unblk.id + ") ");
	$('#myModalunBlc').modal('show');
	$('#unBlockSite').click(function() {
			var reg =/<(.|\n)*?>/g;
			 if (reg.test($('.unblckresp').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
	
		unblckdata['siteCode'] = unblk.id;
		unblckdata['reason'] = $('.unblckresp').val();
		unblckdata['siteName'] = unblk.value;
		$('#myModalunBlc').modal('hide');
		$('#unblkcnfmsg').html("Do you want to Unblock this FPO Site - " + unblk.value +  " (" + unblk.id + ") ");
		$('#ublcnfrm').modal('show');
		$('#situnblkcnfrm').click(function() {
			if (userval != "") {
				$.ajax({
					url: 'unblockfposite',
					data: JSON.stringify(unblckdata),
					dataType: "json",
					contentType: "application/json",
					type: "post",
					success: function(resp) {
						window.location = "viewactiveList";
					},
    error: function(xhr, textStatus, errorThrown) {
        if (xhr.status === 400) {
             window.location = "error"; // Redirect to the error page
        } else {
            // Handle other error cases
            console.log(xhr, textStatus, errorThrown);
        }
    },
				});

			} else {
				alert("No longer login-id required, please login again!.");
			};

		});
			$('#unblckcnfNo').click(function() {
			unblk.id = "";
			});
	});
	
	$('#unblckclsebtn').click(function() {
			unblk.id = "";
			});
};

$(document).ready(function() {
	$("#Edittables tbody tr").each(function() {
		var sa = $(this).find("input#siteactivelist").val();

		if (sa == "Y") {
			$(this).find('#fpositestatus').text("Active");
			$(this).find('#fpositestatus').css('color', '#059004')
		}
		else {
			$(this).find('#fpositestatus').text("Not-Active");
			$(this).find('#fpositestatus').css('color', '#034ed8')
		}

		if (sa == "B") {
			$(this).find('#fpositestatus').text("Blocked");
			$(this).find('#fpositestatus').css('color', 'rgb(204, 13, 8)')
		}

	});
});

$(document).ready(function() {
	

$("#Edittables").dataTable({
		"processing": true,
		"paging": true,
		"ordering": false,
		"dom": '<"pull-left"Bl>frtip',
		//"dom": 'Bfrtip',
		//buttons: [{ extend: 'excel', className: 'excelButtonAll' }],
		
		
		buttons: [{extend: 'csvHtml5', text: '<i>EXCEL</i>',
				   	className: 'btn btn-primary mb-3',
				   	title:'FPO IMPORT APPLICATION - ICES',
					filename:'Edit/Block/Delete-FPO Site',
				  //filename:'Electronic Advance Data (EAD)-SET ASIDE LIST',
					message:'Edit/Block/Delete-FPO Site @ '},
				  {extend: 'pdf',
				    text:'<i>PDF</i>',
				    pageSize: 'A4',
                    className: ' btn btn-success mb-3 ',
					title: 'FPO IMPORT APPLICATION - ICES'+
							'\n\r'+'Edit/Block/Delete-FPO Site @ ',
					filename:'Edit/Block/Delete-FPO Site',
				     orientation: 'landscape',
				/*	exportOptions: {
        				columns: [ 0,1,2,3,4,5,6,7,8,9,10],
						margin: true,
						modifier:{
							selected:true
							},
      						}, */
		   		     customize: function(doc, header) {
					doc.content[1].table.widths = ['5%','10%','10%','10%','10%','10%','10%','10%','10%','10%'];
				   	 doc.defaultStyle.alignment = 'center'
					 //doc.styles.tableHeader.fillColor='#ADD8E6',
					 doc.styles.tableHeader = {
        						color: '#2D1D10',
								fillColor:'#ADD8E6',
        						fontSize: '10',
        						alignment: 'center'
    						},
					doc.content.splice(1, 0,{
					text: [{text:' Date:', bold: true,
					alignment: 'right',
					fontSize: 7,
					margin:[0,20,0,20],
					//watermark:'fpo', 
					},
								],
							});
					doc.content.splice(1, 0,{
          			text: [{text:' SSO ID: ', bold: true,
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
				  message:'Edit/Block/Delete-FPO Site @ '  ,
					filename:'Edit/Block/Delete-FPO Site',
					customize: function ( win ) {
					console.log("sucess");
						$(win.document.body).css( 'font-size', '8pt' )
						$(win.document.body).find( 'table' ).css( 'font-size', 'inherit' );
						},
						             
			}],
		
		
		
		initComplete: function() {
			$("#Edittables_filter").append('<button type="button" class="btn btn-info" id="clearing" '+
			'style="margin-left: 15px;margin-bottom: 5px;">Clear</button>');
          $("#Edittables_filter input[type='search']").attr('id', 'searchEdittables');
		$('#clearing').click(function() {	
		document.getElementById("Edittables").value=""
		var table = $('#Edittables').DataTable();
		table.search('').draw();
	   })
			this.api().columns([3, 4, 5]).every(function() {
				var column = this;
				var coldata = column[0];
				if (coldata == 3) {
					var select = $('<select style="background-color: #63cfe0; font-weight: 700"><option value="">DATE OF CREATION</option></select>')
						.appendTo($(column.header()).empty())
						.on('change', function() {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
							);

							column
								.search(val ? '^' + val + '$' : '', true, false)
								.draw();
						});

					column.data().unique().sort().each(function(d, j) {
						select.append('<option value="' + d + '">' + d + '</option>')
					});
				};

				if (coldata == 4) {
					var select = $('<select style="background-color: #63cfe0; font-weight: 700"><option value="">STATE</option></select>')
						.appendTo($(column.header()).empty())
						.on('change', function() {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
							);

							column
								.search(val ? '^' + val + '$' : '', true, false)
								.draw();
						});

					column.data().unique().sort().each(function(d, j) {
						select.append('<option value="' + d + '">' + d + '</option>')
					});
				};

				if (coldata == 5) {
					var select = $('<select style="background-color: #63cfe0; font-weight: 700"><option value="">STATUS</option></select>')
						.appendTo($(column.header()).empty())
						.on('change', function() {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
							);

							column
								.search(val ? '^' + val + '$' : '', true, false)
								.draw();
						});

					column.data().unique().sort().each(function(d, j) {
						select.append('<option value="' + d + '">' + d + '</option>')
					});
				};
			});
		}
		
		
		
		
	});
		   
	});


/*$('#blcsiteclose').click(function() {
		clearDropDown(siteblock);
	});*/

