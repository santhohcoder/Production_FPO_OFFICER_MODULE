$(document).ready(function() {
showLoader();
	
	$('#prioritystge').click(function() {
	var reg =/<(.|\n)*?>/g; 
			 if (reg.test($('#recallreason').val()) == true || reg.test($('#aritcleid').val()) == true) {
			        swal('OOPS!', 'Special character Not allowed!!', 'error');
					return false
			    }
		error = 0;

		var error = 0;
		if (($('#aritcleid').val() == "")) {
			$('#aritcleid').next().show();
			$('#aritcleid').next().text('Article-ID is required*')
			$('#aritcleid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
			error = error + 1;
		} else if ($('#aritcleid').val().length < 13) {
			$('#aritcleid').next().show();
			$('#aritcleid').next().text('Article-ID is must in 13-digits*')
			$('#aritcleid').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
			error = error + 1;
		} else {
			$('#aritcleid').css({ "border-color": "", "box-shadow": "" })
			$('#aritcleid').next().text('')
		}

		if (($('#recallreason').val() == "")) {
			$('#recallreason').next().show();
			$('#recallreason').next().text('Enter Reason*')
			$('#recallreason').css({ "border-color": "red", "box-shadow": "2px 2px #c90d0d" })
			error = error + 1;
		} else {
			$('#recallreason').next().text('')
			$('#recallreason').css({ "border-color": "", "box-shadow": "" })
		}

		if (error == 0) {
			$.ajax({
				url: 'getqrytypeStg?getartid=' + $('#aritcleid').val(),
				success: function(QryStg) {
					var er = 0;
					var officer = "";
					var qrytyp = "";
					if (QryStg == "") {
						swal("Article-ID ", "No Such Article ID required.", "error")
							.then((value) => {
								window.location = "recallid"
								er = 0;
							});
					} else if (QryStg == 'P4') {
						$('.appendtxt').text("The Selected Article ID is Currently in 'Examination Queue'. Do you want to re-call this Article ID to 'Assessment Queue.'");
						er = 1;
						qrytyp = "P1";
						officer = "PIN"
					} else if (QryStg == 'P3') {
						$('.appendtxt').text("The Selected Article ID is Currently in 'OOC Queue'. Do you want to re-call this Article ID to 'Assessment Queue.'");
						er = 1;
						qrytyp = "P1";
						officer = "PSU"
					} else {
						swal("Article-ID ", "No Such Article ID required.", "error")
							.then((value) => {
								window.location = "recallid"
							});
					}
					if (er == 1) {
						$('#cnfrm').modal('show');
						$('#reCallidcnfrm').click(function() {
							var recalldata = {};
							recalldata['item_id'] = $('#aritcleid').val();
							recalldata['reason'] = $('#recallreason').val();
							recalldata['artclestg'] = qrytyp;
							$.ajax({
								url: 'insertre-calldata?setrole=' + officer,
								data: JSON.stringify(recalldata),
								dataType: "json",
								contentType: "application/json",
								type: "post",
								success: function(developerData) {
									swal("Article-ID Recalled", "Article-ID is moved to Assesment Queue.", "success")
										.then((value) => {
											window.location = "recallid"
										});
								},

							});

						});

					}
				}
			});
		}
	});
	
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

