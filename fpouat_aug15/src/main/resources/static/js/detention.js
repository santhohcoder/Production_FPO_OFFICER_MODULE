function closeDetentionModal() {
	$("#detentionRemarkBody").html('');
	$("#detentionRemarkModal").modal('toggle');
	$('body').css('overflow-y', 'initial');
}

function closeDeptCommentsModal() {
	$("#deptCommentsBody").html('');
	$("#deptCommentsModal").modal('hide');
	$("#detentionRemarkModal").modal('show');
	$('body').css('overflow-y', 'initial');
}

function viewDetainedRemarks(obj) {
	$.ajax({
		url: 'detremarksbyarticleidanddetainedno?itemId=' + $(obj).attr('articleId') + '&detainedNo=' + $(obj).attr('detainedNo'),
		type: "post",
		success: function(data) {
			$("#detentionRemarkBody").html(data);
			$("#detained-view-summary").remove();
			$("#detentionRemarkModal").modal('show');
			$('body').css('overflow-y', 'hidden');
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});
}

function viewDeptComments(obj) {
	$.ajax({
		url: 'getdeptcomments?itemId=' + $(obj).attr('articleId'),
		type: "post",
		success: function(data) {
			$("#deptCommentsBody").html(data);
			$("#deptCommentsTitle").text('Department Comments');
			$("#detentionRemarkModal").modal('hide');
			$("#deptCommentsModal").modal('show');
			$('body').css('overflow-y', 'hidden');
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});
}

function viewSummary(obj) {
	$.ajax({
		url: 'getSummary?id=' + $(obj).attr('articleId'),
		type: "post",
		success: function(data) {
			$("#deptCommentsBody").html(data);
			$("#deptCommentsTitle").text('Article Summary');
			$("#detentionRemarkModal").modal('hide');
			$("#deptCommentsModal").modal('show');
			$('body').css('overflow-y', 'hidden');
		},
		error: function(rs, e) {
			swal('OOPS!', 'Something went wrong. Please try afer sometime !!', 'error');
		}
	});
}
function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}