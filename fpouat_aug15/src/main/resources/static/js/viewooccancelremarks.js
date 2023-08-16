 
		function showLoader() {
		    $("#overlay").css("display", "block");
		}
		
		function viewFilePdf(obj) {
		    $("#embeduploadedpdf").attr('src', 'file/pdf/' + $(obj).attr('value'));
		    $("#pdfView").modal('show');
		}
		
		function hideLoader() {
		    setTimeout(function() {
		        $("#overlay").css("display", "none");
		    }, 500);
		}
		
		function saveComment(obj) {
		    if ($("#remarkcomments").val().trim() == '') {
		        $("#remarkcomments").next().show();
		        $("#remarkcomments").addClass('invalid');
		        return false;
		    }
		    var cmtmsg = $(obj).attr('ac') === 'false' ? 'AC' : 'Appraiser';
		    swal({
		            title: "Are you sure to submit the entered comment to " + cmtmsg + " ?",
		            icon: "warning",
		            buttons: ["No", "Yes"],
		            dangerMode: false,
		        })
		        .then((willDelete) => {
		            if (willDelete) {
		                $.ajax({
		                    url: "saveooccancelcomments?itemId=" + $(obj).attr('articleid') + "&comment=" + $("#remarkcomments").val().trim(),
		                    type: "post",
		                    success: function(data) {
		                        if (data.status) {
		                            swal('Your Comment is saved and moved to ' + cmtmsg + ' !', "Thanks", 'success').then((value) => {
		                                $("#oocCancelRemarkModal").find('.close').click();
		                                $("#ooccancelprocesstable").DataTable().ajax.reload();
		                            });
		                        } else {
		                            swal('Error!', "Something went wrong !", 'error');
		                        }
		                    },
		                    error: function(rs, e) {
		                        swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		                    }
		                });
		            }
		        });
		}
		
		function moveToAss(obj) {
			if ($("#acremarks").val().trim() == '') {
		        $("#acremarks").next().show();
		        $("#acremarks").addClass('invalid');
		        return false;
		    }
		    swal({
		            title: "On Acceptance of OOC Cancellation, the article Id will move back to Assessment Queue ?",
		            icon: "warning",
		            buttons: ["No", "Yes"],
		            dangerMode: false,
		        })
		        .then((willDelete) => {
		            if (willDelete) {
		                $.ajax({
		                    url: "oocapproveorreject?ooc=true&itemId=" + $(obj).attr('articleid') +"&acremarks=" + $("#acremarks").val().trim(),
		                    type: "post",
		                    success: function(data) {
		                        if (data.status) {
		                            swal('Article Moved back to Assessment Queue', "Thanks", 'success').then((value) => {
		                               // $("#oocCancelRemarkModal").find('.close').click();
		                               // $("#ooccancelprocesstable").dataTable().fnDestroy();
		                                //$("#ooccancelhistorytable").dataTable().fnDestroy();
		                               // $("#ooccancelprocesstable").dataTable().ajax.reload();
		                               // $("#ooccancelhistorytable").dataTable().ajax.reload();
		                              location.href=localStorage.getItem("currentUrl");
		                            });
		                        } else {
		                            swal('Error!', "Something went wrong !", 'error');
		                        }
		                    },
		                    error: function(rs, e) {
		                        swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		                    }
		                });
		            }
		        });
		}
		
		function rejectOocCancel(obj) {
			if ($("#acremarks").val().trim() == '') {
		        $("#acremarks").next().show();
		        $("#acremarks").addClass('invalid');
		        return false;
		    }
		    swal({
		            title: "Are you sure to reject the OOC Cancellation ?",
		            icon: "warning",
		            buttons: ["No", "Yes"],
		            dangerMode: false,
		        })
		        .then((willDelete) => {
		            if (willDelete) {
		                $.ajax({
		                    url: "oocapproveorreject?ooc=false&itemId=" + $(obj).attr('articleid') +"&acremarks=" + $("#acremarks").val().trim(),
		                    type: "post",
		                    success: function(data) {
		                        if (data.status) {
		                            swal('OOC Cancel Rejected', "Thanks", 'success').then((value) => {
		                                $("#oocCancelRemarkModal").find('.close').click();
		                              //  $("#ooccancelprocesstable").DataTable().ajax.reload();
		                              //  $("#ooccancelhistorytable").DataTable().ajax.reload();
		                              //   $("#ooccancelprocesstable").dataTable().fnDestroy();
		                              //  $("#ooccancelhistorytable").dataTable().fnDestroy();
		                              //  $("#ooccancelprocesstable").dataTable().ajax.reload();
		                              //  $("#ooccancelhistorytable").dataTable().ajax.reload();
                                        location.href=localStorage.getItem("currentUrl");
		                            });
		                        } else {
		                            swal('Error!', "Something went wrong !", 'error');
		                        }
		                    },
		                    error: function(rs, e) {
		                        swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		                    }
		                });
		            }
		        });
		
		}
