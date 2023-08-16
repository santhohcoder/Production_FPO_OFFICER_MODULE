$("#processtable").dataTable({
	"processing": true,
	"paging": false,
	"ordering": false,
	scrollY: '45vh',
	scrollCollapse: true,
	scrollX: true,
});


$('#processtable').DataTable().on('order.dt search.dt', function() {
	$('#processtable').DataTable().column(0, {
		search: 'applied',
		order: 'applied'
	}).nodes().each(function(cell, i) {
		cell.innerHTML = i + 1;
	});
}).draw();


var fromdate = $("#reloadfromdate").val();
var todate = $("#reloadtodate").val();
$("#fromdate").datepicker({
	dateFormat: "dd/mm/yy",
	onSelect: function(date) {
		$("#todate").datepicker('option', 'minDate', date);

	}
});
$("#todate").datepicker({
	dateFormat: "dd/mm/yy",
});

$('#fromdate').datepicker('setDate', todate);
$("#todate").datepicker('option', 'minDate', todate);
$('#todate').datepicker('setDate', todate);


var alertname = 'view';

$('#add').css("display", "none");


$("#processtable").DataTable().columns([5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]).visible(false);


$("#button-list a").click(function() {
	$("#button-list a").removeClass("btn-success");
	$("#button-list a").addClass("btn-outline-success");
	$(this).removeClass("btn-outline-success");
	$(this).addClass('btn-success');
	$(this).addClass('btn-success');
	alertname = $(this).attr('name');

	alertclick(alertname);
});



function alertclick(alertname) {

	var oMyForm = new FormData();
	oMyForm.append("alertname", alertname);
	oMyForm.append("fromdate", $('#fromdate').val());
	oMyForm.append("todate", $('#todate').val());


	//showLoader();
	jQuery.ajax({
		url: "getallalertdata",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(data) {
			console.log('Tab change inside success');

			$("#tablediv").replaceWith(data);
			$("#processtable").DataTable().columns([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]).visible(true);
			if (alertname == 'view') {
				$('#add').css("display", "none");
				$('.date').css("display", "none");

				$("#processtable").DataTable().columns([5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]).visible(false);

			} else if (alertname == 'insertremovealert') {

				$('#add').css("display", "block");
				$('.date').css("display", "none");

				$("#processtable").DataTable().columns([5, 7, 8, 10, 11, 12, 13, 14, 15]).visible(false);
			} else if (alertname == 'history') {

				$('#add').css("display", "none");
				$('.date').css("display", "inline-flex");

				$("#processtable").DataTable().columns([6, 9, 11, 12, 13, 14, 15]).visible(false);
			}

		}
	});


}


function insertpopup() {
	$('#typemodal').modal('show');
	$('textarea').val('');
}



$("#specific").click(function() {
	$('#typemodal').modal('hide');
	$('#insertmodal').modal('show');
	var date = new Date();
	var mindate = moment(date).format("YYYY-MM-DDTkk:mm");
	$('#active_since').attr("min", mindate);
	$('#active_since').val(mindate);



	$('#specifictime').css("display", "none");
	$('#countryerr').css("display", "none");
	$('#alerterr').css("display", "none");
	$('#reasonerr').css("display", "none");
	$('#siteerr').css("display", "none");
});



$("#general").click(function() {

	$('#typemodal').modal('hide');
	$('#specificinsertmodal').modal('show');
	var date = new Date();
	var mindate = moment(date).format("YYYY-MM-DDTkk:mm");
	$('#specificactivetime').attr("min", mindate);
	$('#specificactivetime').val(mindate);

	$('#specificactivetimeerr').css("display", "none");
	$('#specificalerterr').css("display", "none");
	$('#specificreasonerr').css("display", "none");
});

function statechange(obj) {
	console.log($(obj).val());

}

function addallSpecificalertdetail() {

	if ($('#active_since').val() >= moment(new Date()).format("YYYY-MM-DDTkk:mm") && $('#country').val() != null && $('#alert').val() != '' && $('#reason').val() != '' && $('#site').val() != null) {
		Swal.fire({
			title: 'Please Confirm?',
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#28a745',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes!'
		}).then((result) => {
			var oMyForm = new FormData();
			oMyForm.append("country", $('#country').val());
			oMyForm.append("mailclass", $('#mailclass').val());
			oMyForm.append("itemcat", $('#itemcat').val());
			oMyForm.append("alert", $('#alert').val());
			oMyForm.append("reason", $('#reason').val());
			oMyForm.append("active_since", $('#active_since').val());
			oMyForm.append("site", $('#site').val());

			//showLoader();
			jQuery.ajax({
				url: "addallSpecificalertdetail",
				global: false,
				data: oMyForm,
				dataType: 'text',
				processData: false,
				contentType: false,
				type: 'POST',

				success: function(data) {
					/*var marker = JSON.stringify(data);
					var json = jQuery.parseJSON(marker);
					json=JSON.parse(json);*/


					$('#insertmodal').modal('hide');
					alertclick(alertname);
				}
			});
		});
	} else {

		if ($('#active_since').val() <= moment(new Date()).format("YYYY-MM-DDTkk:mm")) {
			$('#specifictime').css("display", "block");
		} if ($('#country').val() == null) {
			$('#countryerr').css("display", "block");

		} if ($('#alert').val() == '') {
			$('#alerterr').css("display", "block");

		} if ($('#reason').val() == '') {
			$('#reasonerr').css("display", "block");

		}if ($('#site').val() == null) {
			$('#siteerr').css("display", "block");

		}

	}



}
function addallgeneralalertdetail() {
	if ($('#specificactivetime').val() >= moment(new Date()).format("YYYY-MM-DDTkk:mm") && $('#specificalert').val() != '' && $('#specificreason').val() != '' && $('#generalsite').val() != null) {
		Swal.fire({
			title: 'Please Confirm?',
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#28a745',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes!'
		}).then((result) => {
			if (result.isConfirmed) {
				var oMyForm = new FormData();
				oMyForm.append("alert", $('#specificalert').val());
				oMyForm.append("reason", $('#specificreason').val());
				oMyForm.append("active_since", $('#specificactivetime').val());
				oMyForm.append("site", $('#generalsite').val());


				//showLoader();
				jQuery.ajax({
					url: "addallgeneralalertdetail",
					global: false,
					data: oMyForm,
					dataType: 'text',
					processData: false,
					contentType: false,
					type: 'POST',

					success: function(data) {
						/*var marker = JSON.stringify(data);
						var json = jQuery.parseJSON(marker);
						json=JSON.parse(json);*/


						$('#specificinsertmodal').modal('hide');
						alertclick(alertname);
					}
				});
			}
		});
	} else {

		if ($('#specificactivetime').val() <= moment(new Date()).format("YYYY-MM-DDTkk:mm")) {
			$('#specificactivetimeerr').css("display", "block");
		} if ($('#specificalert').val() == '') {
			$('#specificalerterr').css("display", "block");

		} if ($('#specificreason').val() == '') {
			$('#specificreasonerr').css("display", "block");

		}if ($('#generalsite').val() == null) {
			$('#generalsiteerr').css("display", "block");

		}

	}
}

function removealertpopup(obj) {
	$('#removeinsertmodal').modal('show');

	$('#mandreason').css('display', 'none');
	$('#removeid').val($(obj).attr('data-id'));
}
function removealert() {


	if ($('#removereason').val().length > 0) {
		Swal.fire({
			title: 'Are you sure to Remove The Alert?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#28a745',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes!'
		}).then((result) => {
			if (result.isConfirmed) {
				var oMyForm = new FormData();
				oMyForm.append("id", $('#removeid').val());
				oMyForm.append("removereason", $('#removereason').val());


				//showLoader();
				jQuery.ajax({
					url: "removealert",
					global: false,
					data: oMyForm,
					dataType: 'text',
					processData: false,
					contentType: false,
					type: 'POST',

					success: function(data) {
						/*var marker = JSON.stringify(data);
						var json = jQuery.parseJSON(marker);
						json=JSON.parse(json);*/




						$('#removeinsertmodal').modal('hide');
						alertclick(alertname);
					}
				});
			}
		});
	} else {
		$('#mandreason').css('display', 'block');
	}


}

function datesubmit() {
	alertclick(alertname);
}

$(function() {


	$('.multiselect')
		.multiselect({
			allSelectedText: 'All',
			maxHeight: 200,
			includeSelectAllOption: true
		})
		.multiselect('selectAll', true)
		.multiselect('updateButtonText');
});

$('#processtable').on('click', '.detailpopup', function() {

	var tr = $(this).closest("tr");
	var data = $("#processtable").DataTable().row(tr).data();
	$('#alertnoinfo').text(data[2]);
	$('#activeinfo').text(data[6]);
	$('#alertinfo').text(data[11]);
	$('#reasoninfo').text(data[12]);
		$('#cntryinfo').text(data[13]);
		$('#mailclsinfo').text(data[14]);
		$('#itemcatinfo').text(data[15]);
	
	getdetails(data[2],data[1]);

});


function getdetails(id,type) {


				var oMyForm = new FormData();
				oMyForm.append("id", id);


				//showLoader();
				jQuery.ajax({
					url: "getalertdetails",
					global: false,
					data: oMyForm,
					dataType: 'text',
					processData: false,
					contentType: false,
					type: 'POST',

					success: function(data) {
						var marker = JSON.stringify(data);
						var json = jQuery.parseJSON(marker);
						json=JSON.parse(json);




	if (type == 'Specific') {
		$('#specificcontent').css("display", "block");
		$('#mailclsinfo').text(json.mailclass);
		$('#itemcatinfo').text(json.itemcat);
	} else {
		$('#specificcontent').css("display", "none");
	}

	$('#infomodal').modal('show');


						
					}
				});
			


}

$("#active_since").on("change", function() {

	if ($('#active_since').val() >= moment(new Date()).format("YYYY-MM-DDTkk:mm")) {
		$('#specifictime').css("display", "none");
	} else {
		$('#specifictime').css("display", "block");
	}
});

$("#site").on("change", function() {

	if ($('#site').val() != null) {
		$('#siteerr').css("display", "none");
	} else {
		$('#siteerr').css("display", "block");
	}
});



$("#generalsite").on("change", function() {

	if ($('#generalsite').val() != null) {
		$('#generalsiteerr').css("display", "none");
	} else {
		$('#generalsiteerr').css("display", "block");
	}
});

$("#country").on("change", function() {

	if ($('#country').val() != null) {
		$('#countryerr').css("display", "none");
	} else {
		$('#countryerr').css("display", "block");
	}
});

$("#alert").on("change", function() {

	if ($('#alert').val() != '') {
		$('#alerterr').css("display", "none");
	} else {
		$('#alerterr').css("display", "block");
	}
});

$("#reason").on("change", function() {

	if ($('#reason').val() != '') {
		$('#reasonerr').css("display", "none");

	} else {
		$('#reasonerr').css("display", "block");
	}
});

$("#specificalert").on("change", function() {

	if ($('#specificalert').val() != '') {
		$('#specificalerterr').css("display", "none");

	} else {
		$('#specificalerterr').css("display", "block");
	}
});

$("#specificreason").on("change", function() {

	if ($('#specificreason').val() != '') {
		$('#specificreasonerr').css("display", "none");

	} else {
		$('#specificreasonerr').css("display", "block");
	}
});



$("#specificactivetime").on("change", function() {

	if ($('#specificactivetime').val() >= moment(new Date()).format("YYYY-MM-DDTkk:mm")) {
		$('#specificactivetimeerr').css("display", "none");
	} else {
		$('#specificactivetimeerr').css("display", "block");
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