 var documentFiles = {};

    $(function() {
        triggerDatePicker();
    });

    function triggerDatePicker() {
        $("[id^='document-date-']").datepicker({
            dateFormat: "dd/mm/yy",
			maxDate: new Date(),
            onSelect: function(date) {
                if ($(this).closest('div').find('span').length < 1) {
                    $(this).closest('div').find('label').after('<span><i class="fa fa-times cross-icon" onclick="removeDate(this)" aria-hidden="true"></i></span>');
                }
            }
        });
    }

    function removeDate(obj) {
        $(obj).closest('div').find('input').val('');
        $(obj).closest('span').remove();
    }

    function addDocumentSection(obj) {
        var addedNumber = [],
            count = 6,
            missingNumber = [];
        $("[id^='document-section-']").map(function() {
            addedNumber.push(parseInt($(this).attr('id').substr($(this).attr('id').length - 1)));
        });
        for (var i = 1; i <= count; i++) {
            if (addedNumber.indexOf(i) == -1) {
                missingNumber.push(i);
            }
        }
        var nextCount = Math.min(...missingNumber);
        var documentsection = '<div id="document-section-' + (nextCount) + '"><div class="col-lg-3 col-md-3 col-sm-3"><div class="form-group floating">' +
            '<select class="control" onchange="checkDivAdd(this)" id="document-type-' + (nextCount) + '">' + $("#document-type-1").html() +
            '</select><div class="invalid-feedback">Document Type is required</div><label class="float">Type of Upload Document</label></div></div>' +
            '<div class="col-lg-3 col-md-3 col-sm-3"><div class="form-group floating">' +
            '<input type="text" id="document-date-' + (nextCount) + '" Placeholder="Select Document Date" readonly="" class="picker form-control form_txt change"></input>' +
            '<div class="invalid-feedback">Document Date is required</div><label class="float">Document Date</label></div></div>' +
            '<div class="col-lg-3 col-md-3 col-sm-3"><div class="form-group floating">' +
            '<input class="control" type="text" id="document-filetxt-' + (nextCount) + '" placeholder="Please Upload File" disabled>' +
            '<div class="invalid-feedback">File attachment is required</div><p class="text-muted">(Note: Only PDF is allowed.Maximum File size must be in 1 MB.)</p><label class="float">File Upload</label><input type="file" id="document-file-' + (nextCount) + '" onchange="uploadFile(this,' + nextCount + ')" onclick="this.value=null;" accept="application/pdf"' +
            ' style="display: none;"><span class="upload-btn" onclick="fileUpload(' + (nextCount) + ')"><a>Upload</a></span></div></div><div class="mt-10">' +
            '<span class="remove-btn" onclick="removeDocumentSection(this,' + nextCount + ')">X</span></div></div></div>';
        $("#document-section-1").after(documentsection);
        $(obj).attr('id', 'add-document-' + (nextCount));
        if ($("[id^='document-section-']").length == count) {
            $("[id^='add-document-']").closest('div').remove();
        }
        triggerDatePicker();
    }

    function removeDocumentSection(obj, count) {
        $(obj).closest("[id^='document-section-']").remove();
        delete documentFiles[count];
        if ($("[id^='add-document-']").length == 0) {
            var nxtCount = count - 1;
            $("#document-section-1").append('<div class="mt-10"><span class="add-btn"><img id="add-document-' + (nxtCount) + '" src="images/add.png" onclick="addDocumentSection(this)" alt="add-icon"></span></div>');
        } else {
            $("[id^='add-document-']").attr('id', 'add-document-' + (count - 1));
        }
    }
    
    
    function closeSummaryModal(){
		$("#summaryModal").modal('hide');
}	
    

    function fileUpload(count) {
        $("#document-file-" + count).click();
    }

	function uploadFile(obj, count) {
	    if (obj.files[0].type == 'application/pdf' && obj.files[0].size / 1024 / 1024 < 1) {
	        var notSameName = true;
	        for (var key in documentFiles) {
	            if (documentFiles[key].files[0].name == obj.files[0].name) {
	                notSameName = false;
	                break;
	            }
	        }
	            if (notSameName) {
	                var reader = new FileReader();
	                reader.onload = function(event) {
	                    var contents = event.target.result;
	                    if (contents.indexOf('Encrypt') !== -1) {
	                        swal('OOPS!', 'Please upload unencrypted PDF !', 'error');
	                    } else {
	                        $("#document-filetxt-" + count).val(obj.files[0].name);
	                        $("#document-filetxt-" + count).addClass('pl-25');
	                        documentFiles[count] = obj;
	                        $(obj).next().remove();
	                        $(obj).after('<span><i class="fa fa-file-pdf-o pdf-icon" aria-hidden="true"></i></span>');
	                        $(obj).after('<span class="view-btn" onclick="viewFile(' + count + ')"><a>View</a></span>');
	                        $(obj).after('<span class="delete-btn" onclick="deleteFile(this,' + count + ')"><a>Delete</a></span>');
	                    }
	                };
	
	                reader.onerror = function(event) {
	                    swal('OOPS!', 'Something went wrong !', 'error');
	                };
	
	                reader.readAsText(obj.files[0]);
	        } else {
	            swal('OOPS!', 'You already uploaded the file with this name !', 'error');
	        }
	    } else {
	        if (obj.files[0].type != 'application/pdf') {
	            swal('OOPS!', 'Please upload the documents only in PDF !', 'error');
	        } else {
	            swal('OOPS!', "File size should be within 1MB !", 'error');
	        }
	    }
	}

    function viewFile(count) {
        $("#embedpdf").attr('src', URL.createObjectURL(documentFiles[count].files[0]));
        $("#pdfView").modal('toggle');
        $("#detentionRemarkModal").scrollTop($("#pdfView").offset().top);
    }

    function deleteFile(obj, count) {
        $("#document-filetxt-" + count).val('');
        $("#document-filetxt-" + count).removeClass('pl-25');
        delete documentFiles[count];
        $(obj).next().remove();
        $(obj).next().remove();
        $(obj).after('<span class="upload-btn" onclick="fileUpload(' + count + ')"><a>Upload</a></span>');
        $(obj).remove();
    }

    function showLoader() {
        $("#overlay").css("display", "block");
    }

    function hideLoader() {
        setTimeout(function() {
            $("#overlay").css("display", "none");
        }, 500);
    }

	function checkDivAdd(obj) {
	var count = parseInt($(obj).attr('id').substr($(obj).attr('id').length - 1));
	    if ($(obj).val()=='Other Documents') {
	        var div = '<div class="col-lg-3 col-md-3 col-sm-3" id="otherDocNameDiv-'+count+'"><div class="form-group floating">' +
	            '<input class="control" type="text" id="otherDocName-'+count+'" placeholder="Enter name for Other Document">' +
	            '<div class="invalid-feedback">Name for other document is required</div><label class="float">Document Name' +
	            '</label></div></div>';
	        $(obj).closest('.col-lg-3.col-md-3.col-sm-3').after(div);
	    }else{
	    	$('#otherDocNameDiv-'+count).remove();
	    }
	}

    function displayOocCancelRemarkSection() {
        $("#oocCancelFullSection").show();
        $("#previewOocCancelFullSection").hide('slow');
        $("html, body").animate({
            scrollTop: 0
        }, "slow");
    }

    var oMyForm = new FormData();

    function scanningDocuments() {
	  var reg =/<(.|\n)*?>/g;
			 if (reg.test($('#oocCancelRemarks').val()) == true || reg.test($('#oocCancelReason').val()) == true  ) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
        var error = 0;
        oMyForm = new FormData();
        var iteration = 0;
        var docSectionAdded = false;
        $("#previewDocUploadTitleDiv").remove();
        if (!$("#docsec-full-div").is(":hidden")) {
            $("[id^='document-section-']").each(function(i) {
                var docListNo = parseInt($(this).attr('id').substr($(this).attr('id').length - 1));
                var checkall = false;
                var docUploadFields = ['document-type','otherDocName', 'document-date', 'document-filetxt'];
                docUploadFields.forEach(function(e) {
                    if ($('#' + e + '-' + docListNo).length>0 && $('#' + e + '-' + docListNo).val().trim() != '') {
                        checkall = true;
                    }
                });
                if (checkall) {
                    if ($("#previewDocUploadTitleDiv").length < 1) {
	                        $("#previewOocCancelRemarkDiv").after('<div class="row addd" id="previewDocUploadTitleDiv"><div class="col-lg-12 col-md-12 col-sm-12" ' +
	                            'style="display: flex;"><h4 class="subtitle">Document Upload</h4><p class="text-muted" style="margin-top: 10px; margin-left: 10px;">' +
	                            '(Note: Maximum of 6 files can be upload.)</p></div></div>');
                    }
                    $("#previewDocUploadTitleDiv").append('<div id="preview-document-section-' + (docListNo) + '">' +
                        '<div class="col-lg-3 col-md-3 col-sm-3"><div class="form-group floating">' +
                        '<input type="text" disabled class="control valid-box" id="preview-document-type-' + (docListNo) + '">' +
                        '<label class="float">Type of Document</label></div></div>' +
                        '<div class="col-lg-3 col-md-3 col-sm-3"><div class="form-group floating">' +
                        '<input type="text" disabled class="control valid-box" id="preview-otherDocName-' + (docListNo) + '">' +
                        '<label class="float">Document Name</label></div></div>' +
                        '<div class="col-lg-3 col-md-3 col-sm-3"><div class="form-group floating">' +
                        '<input type="text" disabled id="preview-document-date-' + (docListNo) + '" class="valid-box picker form-control form_txt change">' +
                        '<label class="float">Document Date</label></div></div>' +
                        '<div class="col-lg-3 col-md-3 col-sm-3"><div class="form-group floating">' +
                        '<input class="control valid-box" type="text" id="preview-document-filetxt-' + (docListNo) + '"' +
                        ' placeholder="Please Upload File" disabled style="padding-left:25px;">' +
                        '<span><i class="fa fa-file-pdf-o pdf-icon" aria-hidden="true"></i></span>' +
                        '<span class="upload-btn" onclick="viewFile(' + (docListNo) + ')"><a>View</a></span>' +
                        '<p class="text-muted">(Note: Only PDF is allowed.Maximum File size must be in 1 MB.)</p>' +
                        '<label class="float">File Upload</label></div></div></div>');
                    docUploadFields.forEach(function(e) {
                    	if($('#' + e + '-' + docListNo).length>0  && !$('#' + e + '-' + docListNo).is(":hidden")) {
	                        if ($('#' + e + '-' + docListNo).val().trim() == '') {
	                            $('#' + e + '-' + docListNo).next().show();
	                            $('#' + e + '-' + docListNo).addClass('invalid');
	                            error = error + 1;
	                        } else {
	                            $('#' + e + '-' + docListNo).next().hide();
	                            $('#' + e + '-' + docListNo).removeClass('invalid');
	                            $('#preview-' + e + '-' + docListNo).val($('#' + e + '-' + docListNo).val());
	                            docSectionAdded = true;
	                            oMyForm.append("documentSections[" + iteration + "]." + (e == 'document-type' ? 'documentType' : e == 'document-date' ? 'documentDate' : e == 'otherDocName' ? 'documentName' : 'documentFile'), e == 'document-filetxt' ? documentFiles[docListNo].files[0] : $('#' + e + '-' + docListNo).val());
	                        }
                        }else{
                        	$('#preview-' + e + '-' + docListNo).closest('.col-lg-3.col-md-3.col-sm-3').remove();
                        }
                    });
                    iteration = iteration + 1;
                } else {
                    docUploadFields.forEach(function(e) {
                        $('#' + e + '-' + docListNo).next().hide();
                        $('#' + e + '-' + docListNo).removeClass('invalid');
                    });
                }
            });
        }
        if ($("#oocCancelRemarks").val().trim() == '') {
            $("#oocCancelRemarks").next().show();
            $("#oocCancelRemarks").addClass('invalid');
            error = error + 1;
        } else {
            $("#oocCancelRemarks").next().hide();
            $("#oocCancelRemarks").removeClass('invalid');
            oMyForm.append("oocCancelRemarks", $("#oocCancelRemarks").val().trim());
            $("#previewOocCancelRemarks").text($("#oocCancelRemarks").val().trim());
        }
        if ($("#oocCancelReason").val().trim() == '') {
            $("#oocCancelReason").next().show();
            $("#oocCancelReason").addClass('invalid');
            error = error + 1;
        } else {
            $("#oocCancelReason").next().hide();
            $("#oocCancelReason").removeClass('invalid');
            oMyForm.append("oocCancelReason", $("#oocCancelReason").val().trim());
            $("#previewOocCancelReason").val($("#oocCancelReason").val().trim());
        }     
        if (error == 0) {
            oMyForm.append("cinNo", $("#cinNo").val().trim());
            showLoader();
            if (docSectionAdded) {
                $.ajax({
                    url: 'scanningooccanceldocuments',
                    data: oMyForm,
                    contentType: false,
                    processData: false,
                    enctype: 'multipart/form-data',
                    type: 'POST',
                    success: function(data) {
                        var documentMalicious = [];
                        data.maliciousFiles.forEach(item => {
                            if (Object.keys(documentFiles).find(key => documentFiles[key].files[0].name === item) != undefined) {
                                documentMalicious.push(Object.keys(documentFiles).find(key => documentFiles[key].files[0].name === item));
                            }
                        });
                        documentMalicious.forEach(doc => {
                            $("#preview-document-filetxt-" + doc).addClass('invalid');
                            $("#preview-document-filetxt-" + doc).removeClass('valid-box');
                            $("#document-filetxt-" + doc).addClass('invalid');
                            $("#document-filetxt-" + doc).next().text('Uploaded Document is found to be not safe.Please upload malware free Document.');
                            $("#document-filetxt-" + doc).next().show();
                            $("#preview-document-filetxt-" + doc).after('<div class="invalid-feedback" style="display: block;">Uploaded Document is found to be not safe.Please upload malware free Document.</div>');
                        });
                        if (data.maliciousFiles.length > 0) {
                            $("#submitScannedDocuments").hide();
                        } else {
                            $("#submitScannedDocuments").show();
                        }
                        hideLoader();
                        $("#oocCancelFullSection").hide('slow');
                        $("#previewOocCancelFullSection").show();
                        $("html, body").animate({
                            scrollTop: 0
                        }, "slow");
                    },
                    error: function(e) {
                        swal('Error!', "Something went wrong !", 'error');
                    }
                });
            } else {
                hideLoader();
                $("#oocCancelFullSection").hide('slow');
                $("#previewOocCancelFullSection").show();
                $("html, body").animate({
                    scrollTop: 0
                }, "slow");
            }
        }
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

    function submitDocuments() {
      //  event.preventDefault();
        var textmsg='This Article will move back to assessment queue only after AC Approval';
        swal({
                title: "Do you want to submit ?",
                text: textmsg,
                icon: "warning",
                buttons: ["No", "Yes"],
                dangerMode: false,
            })
            .then((willDelete) => {
                if (willDelete) {
                    $.ajax({
                        url: 'saveooccancelreply',
                        data: oMyForm,
                        contentType: false,
                        processData: false,
                        enctype: 'multipart/form-data',
                        type: 'POST',
                        success: function(data) {
                            if (data.status) {
                                swal('OOC Cancellation Initiated !', "Thanks", 'success').then((value) => {
                                    $("#oocCancelRemarksSection").html('');
                                    $("#articleIdEntered").val('');
                                    $("#ooccancelprocesstable").dataTable().fnDestroy();
                                    $("#ooccancelprocesstable").dataTable().fnDestroy();
                                    $("#ooccancelprocesstable").dataTable().ajax.reload();
                                    $("#ooccancelhistorytable").dataTable().ajax.reload();
                                });
                            } else {
                                swal('Error!', "Something went wrong !", 'error');
                            }
                        },
                        error: function(e) {
                            swal('Error!', "Something went wrong !", 'error');
                        }
                    });
                }
            });
    }