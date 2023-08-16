/*function EnableDisable(recrdReasn) {
	if (recrdReasn.value.length <= 4) {
		$('#removelsmSite').attr('disabled', true);
	} else {
		$('#removelsmSite').attr('disabled', false);
	}

};*/

var bug = 0;
function validateField(obj) {
	var artid = $(obj).val();
	bug = 0;

	if ($(obj).attr('name') == 'Articleid') {
		if ($(obj).val().length != 13) {
			$(obj).next().show();
			$(obj).next().text('Article-ID must be 13-digits is required*')
			$(obj).addClass('invalid');
			$(obj).removeClass('valid');
			$(obj).css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
			bug = bug + 1;
		} else {
			$(obj).next().hide();
			$(obj).addClass('valid');
			$(obj).removeClass('invalid');
			$(obj).css({ "border-color": "", "box-shadow": "" })

		}

		if ($(obj).val().trim() == '') {
			$(obj).next().show();
			$(obj).next().text('')
			$(obj).css({ "border-color": "", "box-shadow": "" })
		}
	}

	if (bug == 0) {
		$.ajax({
			url: 'getfpocusite?articleid=' + artid,
			success: function(data) {
				$('#pushtosite').empty();
				$('#pushtosite').append('<option>--Select FPO Sites--</option>');
				$('#fromfposite').val(data.articlecus);
				$.each(data.pushtoSite, function(index, value) {
					$('#pushtosite').append('<option>' + value + '</option>');
				});
			}
		});

	};
};



var error = 0;
$('#pushArticle').click(function() {

	error = 0;
	var pushval = {};
	pushval['itemid'] = $('#aritcleid').val();
	pushval['fromcussite'] = $('#fromfposite').val();
	pushval['reason'] = $('#pusharticlereason').val()
	pushval['tocussite'] = $('#pushtosite').val();

	if ($('#aritcleid').val().length != 13) {
		$('#aritcleid').next().show();
		$('#aritcleid').next().text('Article-ID must be 13-digits is required*')
		$('#aritcleid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#aritcleid').css({ "border-color": "", "box-shadow": "" })
		$('#aritcleid').next().text('')
		$('#aritcleid').next().show();
	}

	if ($('#aritcleid').val() == '') {
		$('#aritcleid').next().show();
		$('#aritcleid').next().text('Article-ID is required*')
		$('#aritcleid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	}

	if ($('#pusharticlereason').val().length == '') {
		$('#pusharticlereason').next().show();
		$('#pusharticlereason').next().text('Remarks is required*')
		$('#pusharticlereason').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {

		$('#pusharticlereason').next().text('')
		$('#pusharticlereason').css({ "border-color": "", "box-shadow": "" })
	}

	if ($('#pushtosite').val() == '--Select FPO Site--') {
		$('#pushtosite').next().show();
		$('#pushtosite').next().text('Select FPO Site*')
		$('#pushtosite').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#pushtosite').css({ "border-color": "", "box-shadow": "" })
		$('#pushtosite').next().text('')
	}

	if ($('#fromfposite').val() == '') {
		$('#fromfposite').next().show();
		$('#fromfposite').next().text('SiteCode is required*')
		$('#fromfposite').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
		error = error + 1;
	} else {
		$('#fromfposite').css({ "border-color": "", "box-shadow": "" })
		$('#fromfposite').next().text('')
	}

	if (error == 0) {
		$.ajax({
			url: "pushitems",
			data: JSON.stringify(pushval),
			dataType: "json",
			contentType: "application/json",
			type: "post",
			success: function(data) {
				swal("Success!", "Article push to another site!", "success")
					.then((value) => {
						location.reload();
					});
			},
		});

	}
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



