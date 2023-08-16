


function showLoader() {
	$("#overlay").css("display", "block");
	$('#overlay').height($("body").innerHeight());
}

function hideLoader() {
	setTimeout(function() {
		$("#overlay").css("display", "none");
	}, 500);
}



showLoader();
$(document).ready(function() {


	$("#processtable").dataTable({
		"processing": true,
		"paging": false,
		scrollY: 500,
		scrollX: true,
		"ordering": false,
		"dom": 'Bfrtip',
		buttons: [{
			text: 'Export to XLS',
			extend: 'excelHtml5',
			exportOptions: {
				columns: ':visible'
			}
		}]
	});

	$('#processtable tfoot th').each(function() {
		var title = $(this).text();
		$(this).html('<input type="text" placeholder="Search ' + title + '" />');
	});


	localStorage.setItem("columns", JSON.stringify([1]));

	$('#processtable').DataTable().on('order.dt search.dt', function() {
		$('#processtable').DataTable().column(0, {
			search: 'applied',
			order: 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();


	if ($("#processtable").dataTable().fnGetData().length > 0) {
		$("#ExcelExport").css("display", "block");
		//   $("#selectall").css("display", "block");
	} else {
		$("#ExcelExport").css("display", "none");
		//   $("#selectall").css("display", "none");
	}



	$("#ExcelExport").on('click', function() {
		$(".buttons-excel").trigger('click');
		this.blur();
	});
	$('#processtable').DataTable().columns([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49]).visible(false);

	hideLoader();
	columnsarray = JSON.parse(localStorage.getItem("columns"));
	columnsname = JSON.parse(localStorage.getItem("columns"));
	columnsname.forEach(function(el, index) {
		$('#' + el).toggleClass("columnselect");

		// $('#processtable').DataTable().column(el).visible(true);

		if ($('#' + el).hasClass("columnselect")) {
			$('#processtable').DataTable().column(el).visible(true);
			$('#select' + el).css("display", "block");
			$('#input' + el).css("display", "block");
			var checkBoxes = $("#check" + el);
			checkBoxes.prop("checked", !checkBoxes.prop("checked"));
		} else {
			$('#processtable').DataTable().column(el).visible(false);
			$('#select' + el).css("display", "none");
			$('#input' + el).css("display", "none");
			$('#input' + el).val("");
		}
	});

	$("#clearall").on('click', function() {
		// runquery();
		columndeselect = 0;
		$(":checkbox").attr("checked", false);
		$('.columnhighlight').toggleClass("columnselect");
		$('li').removeClass('columnselect');

		$('#1').toggleClass("columnselect");
		var checkBoxes = $("#check1");
		checkBoxes.prop("checked", !checkBoxes.prop("checked"));

		columnsname = JSON.parse(localStorage.getItem("columns"));
		columnsname.forEach(function(el, index) {



			$('#select' + el).css("display", "none");
			$('#input' + el).css("display", "none");
			$('#input' + el).val("");

		});
		$('select').val('');

		$('#fourdigit').css("display", "none");
		$("#16").removeClass('col-md-5');
		$("#16").addClass('col-md-7');

		$('#pincode').css("display", "none");
		$("#9").removeClass('col-md-4');
		$("#9").addClass('col-md-7');
		localStorage.setItem("columns", JSON.stringify([1]));

		columnsname = JSON.parse(localStorage.getItem("columns"));

		columnsarray = JSON.parse(localStorage.getItem("columns"));
	});

	$("#selectall").on('click', function() {
		$("#columnshighlight li").removeClass('columnselect');
		$("#columnshighlight li").addClass('columnselect');
		columndeselect = 0;
		window.localStorage.removeItem('columns');
		localStorage.setItem("columns", JSON.stringify([1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 43, 44, 45, 46, 47, 48, 49]));

		columnsname = JSON.parse(localStorage.getItem("columns"));

		columnsarray = JSON.parse(localStorage.getItem("columns"));

		columnsname.forEach(function(el, index) {

			$('#select' + el).val('All');
			if ($('#' + el).hasClass("columnselect")) {
				// $('#processtable').DataTable().column(el).visible(true);
				$('#select' + el).css("display", "block");
				$('#input' + el).css("display", "block");
				$('#check' + el).prop('checked', true);
			} else {
				// $('#processtable').DataTable().column(el).visible(false);
				$('#select' + el).css("display", "none");
				$('#input' + el).css("display", "none");
				$('#input' + el).val("");
				$('#check' + el).prop('checked', false);
			}
		});

		//   $(".change").trigger("change");
		//  runquery();

	});




	/* $(".change").on("change", function() {

		 var oMyForm = new FormData();
		 oMyForm.append("fromdate", $('#fromdate').val());
		 oMyForm.append("todate", $('#todate').val());

		 oMyForm.append("country", $('#select3').val());
		 oMyForm.append("state", $('#select8').val());
		 oMyForm.append("pincode", $('#select9').val());
		 oMyForm.append("pincode1", $('#pincode').val());
		 oMyForm.append("ead", $('#select10').val());
		 oMyForm.append("mailclass", $('#select11').val());
		 oMyForm.append("mailcategory", $('#select12').val());
		 oMyForm.append("totalduty", $('#select13').val());
		 oMyForm.append("amount", $('#select14').val());
		 oMyForm.append("fine", $('#select15').val());
		 oMyForm.append("penalty", $('#select16').val());
		 oMyForm.append("cth", $('#select17').val());
		 oMyForm.append("itemvalue", $('#select19').val());
		 oMyForm.append("itemno", $('#select20').val());
		 oMyForm.append("query", $('#select21').val());
		 oMyForm.append("reply", $('#select22').val());
		 oMyForm.append("arrival", $('#select24').val());
		 oMyForm.append("destination", $('#select25').val());
		 oMyForm.append("examination", $('#select26').val());
		 oMyForm.append("ooc", $('#select27').val());
		 oMyForm.append("ediqueue", $('#select28').val());
		 oMyForm.append("delivery", $('#select30').val());
		 oMyForm.append("detain", $('#select31').val());
		 oMyForm.append("weight", $('#select32').val());


		 oMyForm.append("YN", $('#YN').val());
		 oMyForm.append("Doc", $('#Doc').val());
		 oMyForm.append("Columns", JSON.parse(localStorage.getItem("columns")));


		 showLoader();
		 jQuery.ajax({
			 url: "reportdatechange",
			 global: false,
			 data: oMyForm,
			 dataType: 'text',
			 processData: false,
			 contentType: false,
			 type: 'POST',

			 success: function(data) {
				 console.log('inside success');

				 console.log('queryQueueshow inside success');

				 $("#queryQueueshow").replaceWith(data);
				 $('#overlay').height($("body").innerHeight());

				 $("#input18,#input6,#input4").trigger("keyup");
				 hideLoader();

			 }
		 });
	 });*/

});

var columndeselect = 0;

/*
$('#input4').keyup(function() {
	$('#processtable').DataTable().column(4).search($(this).val()).draw();
});

$('#input6').keyup(function() {
	$('#processtable').DataTable().column(6).search($(this).val()).draw();
});


$('#input16').keyup(function() {
	$('#processtable').DataTable().column(18).search($(this).val()).draw();
});
*/
$("#select9").on("change", function() {


	var oMyForm = new FormData();
	oMyForm.append("state", $('#select9').val());


	//showLoader();
	jQuery.ajax({
		url: "statechange",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);

			$('#pincode').empty();
			var selectdate = document.getElementById('pincode');
			for (i = 0; i < json.list.length; i++) {
				var opt = document.createElement('option');
				opt.value = json.list[i].value;
				opt.innerHTML = json.list[i].data;
				selectdate.appendChild(opt);

			}
			if ($('#select9').val() != 'All') {
				$('#pincode').css("display", "block");
				$("#9").removeClass('col-md-7');
				$("#9").addClass('col-md-4');
			} else {
				$('#pincode').css("display", "none");
				$("#9").removeClass('col-md-4');
				$("#9").addClass('col-md-7');
			}



			//  $(".change").trigger("change");

		}
	});
});

$("#select48").on("change", function() {


	var oMyForm = new FormData();
	oMyForm.append("deliery", $('#select48').val());


	//showLoader();
	jQuery.ajax({
		url: "deliveryackchange",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);

			$('#deliverystatus').empty();
			var selectdate = document.getElementById('deliverystatus');

			var opt = document.createElement('option');
			opt.value = 'All';
			opt.innerHTML = 'All';
			selectdate.appendChild(opt);

			for (i = 0; i < json.list.length; i++) {
				var opt = document.createElement('option');
				opt.value = json.list[i].value;
				opt.innerHTML = json.list[i].value;
				selectdate.appendChild(opt);

			}
			if ($('#select48').val() == 'Not Delivered' || $('#select48').val() == 'Delivered') {
				$('#deliverystatus').css("display", "block");
				$("#48").removeClass('col-md-7');
				$("#48").addClass('col-md-5');
			} else {
				$('#deliverystatus').css("display", "none");
				$("#48").removeClass('col-md-5');
				$("#48").addClass('col-md-7');
			}



			//  $(".change").trigger("change");

		}
	});
});

$("#select16").on("change", function() {


	var oMyForm = new FormData();
	oMyForm.append("twodigit", $('#select16').val());


	//showLoader();
	jQuery.ajax({
		url: "cthchange",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);

			$('#fourdigit').empty();
			var selectdate = document.getElementById('fourdigit');
			for (i = 0; i < json.list.length; i++) {
				var opt = document.createElement('option');
				opt.value = json.list[i];
				opt.innerHTML = json.list[i];
				selectdate.appendChild(opt);

			}
			if ($('#select16').val() != 'All') {
				$('#fourdigit').css("display", "block");
				$("#16").removeClass('col-md-7');
				$("#16").addClass('col-md-5');
			} else {
				$('#fourdigit').css("display", "none");
				$("#16").removeClass('col-md-5');
				$("#16").addClass('col-md-7');
			}

		}
	});
});



$("#select17").on("change", function() {


	var oMyForm = new FormData();
	oMyForm.append("twodigit", $('#select17').val());


	//showLoader();
	jQuery.ajax({
		url: "cthchange",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(data) {
			var marker = JSON.stringify(data);
			var json = jQuery.parseJSON(marker);
			json = JSON.parse(json);

			$('#revfourdigit').empty();
			var selectdate = document.getElementById('revfourdigit');
			for (i = 0; i < json.list.length; i++) {
				var opt = document.createElement('option');
				opt.value = json.list[i];
				opt.innerHTML = json.list[i];
				selectdate.appendChild(opt);

			}
			if ($('#select17').val() != 'All') {
				$('#revfourdigit').css("display", "block");
				$("#17").removeClass('col-md-7');
				$("#17").addClass('col-md-5');
			} else {
				$('#revfourdigit').css("display", "none");
				$("#17").removeClass('col-md-5');
				$("#17").addClass('col-md-7');
			}

		}
	});
});

/*$(".columnvalue").on("change", function() {

	var oMyForm = new FormData();
	oMyForm.append("fromdate", $('#fromdate').val());
	oMyForm.append("todate", $('#todate').val());

	oMyForm.append("country", $('#select3').val());
	oMyForm.append("state", $('#select8').val());
	oMyForm.append("pincode", $('#select9').val());
	oMyForm.append("pincode1", $('#pincode').val());
	oMyForm.append("ead", $('#select10').val());
	oMyForm.append("mailclass", $('#select11').val());
	oMyForm.append("mailcategory", $('#select12').val());
	oMyForm.append("totalduty", $('#select13').val());
	oMyForm.append("amount", $('#select14').val());
	oMyForm.append("fine", $('#select15').val());
	oMyForm.append("penalty", $('#select16').val());
	oMyForm.append("cth", $('#select17').val());
	oMyForm.append("itemvalue", $('#select19').val());
	oMyForm.append("itemno", $('#select20').val());
	oMyForm.append("query", $('#select21').val());
	oMyForm.append("reply", $('#select22').val());
	oMyForm.append("arrival", $('#select24').val());
	oMyForm.append("destination", $('#select25').val());
	oMyForm.append("examination", $('#select26').val());
	oMyForm.append("ooc", $('#select27').val());
	oMyForm.append("ediqueue", $('#select28').val());
	oMyForm.append("delivery", $('#select30').val());
	oMyForm.append("detain", $('#select31').val());
	oMyForm.append("weight", $('#select32').val());


	oMyForm.append("YN", $('#YN').val());
	oMyForm.append("Doc", $('#Doc').val());
	oMyForm.append("Columns", JSON.parse(localStorage.getItem("columns")));


	showLoader();
	jQuery.ajax({
		url: "reportdatechange",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(data) {
			console.log('inside success');

			console.log('queryQueueshow inside success');
			$("#queryQueueshow").replaceWith(data);
			$('#overlay').height($("body").innerHeight());

			hideLoader();

		}
	});
});*/



function columnclick(obj) {
	var column = $(obj).attr('value');
	if (column != columndeselect) {

		if ($('#select' + columndeselect).val() == null) {
			if ($('#select' + columndeselect).length) {

				$('#' + columndeselect).toggleClass("columnselect");
				$('#select' + columndeselect).css("display", "none");
				$('#input' + columndeselect).css("display", "none");
				$('#check' + columndeselect).prop('checked', false);
				$('#input' + columndeselect).val("");
				const index = columnsarray.indexOf(columndeselect);
				if (index > -1) {
					columnsarray.splice(index, 1);
				}
			}

		}
	}
	columndeselect = column;

	if (column != '') {
		var columnid = '#' + column;
		$(columnid).toggleClass("columnselect");

		if (column == 9) {
			$('#pincode').css("display", "none");
		}

		if ($(columnid).hasClass("columnselect")) {
			//    $('#processtable').DataTable().column(column).visible(true);
			$('#select' + column).css("display", "block");
			$('#input' + column).css("display", "block");
			$('#check' + column).prop('checked', true);
			columnsarray.push(column);
			/*if (column == 9) {

				if ($('#select9').val() != 'All') {
					$('#pincode').css("display", "block");
				} else {
					$('#pincode').css("display", "none");
				}
			}*/

		} else {
			//  $('#processtable').DataTable().column(column).visible(false);
			$('#select' + column).css("display", "none");
			$('#input' + column).css("display", "none");
			$('#check' + column).prop('checked', false);
			$('#input' + column).val("");
			const index = columnsarray.indexOf(column);
			if (index > -1) {
				columnsarray.splice(index, 1);
			}
			columndeselect = 0;
		}

		localStorage.setItem("columns", JSON.stringify(columnsarray));


	}
}

function runquery1() {
	Swal.fire({
		title: 'Are you sure to run the query?',
		text: "Are you selected required data elements!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes!'
	}).then((result) => {
		if (result.isConfirmed) {
			/*Swal.fire(
			  'Deleted!',
			  'Your file has been deleted.',
			  'success'
			)*/
			runquery();
		}
	});
}

function runquery() {


	var oMyForm = new FormData();
	oMyForm.append("fromdate", $('#fromdate').val());
	oMyForm.append("todate", $('#todate').val());

	oMyForm.append("country", $('#select3').val());
	oMyForm.append("state", $('#select8').val());
	oMyForm.append("pincode", $('#select9').val());
	oMyForm.append("pincode1", $('#pincode').val());
	oMyForm.append("ead", $('#select10').val());
	oMyForm.append("mailclass", $('#select11').val());
	oMyForm.append("mailcategory", $('#select12').val());
	oMyForm.append("totalduty", $('#select31').val());
	oMyForm.append("amount", $('#select32').val());
	oMyForm.append("fine", $('#select33').val());
	oMyForm.append("penalty", $('#select34').val());
	oMyForm.append("cth", $('#select18').val());
	oMyForm.append("itemvalue", $('#select19').val());
	oMyForm.append("assvalue", $('#select22').val());
	oMyForm.append("itemno", $('#select14').val());
	oMyForm.append("query", $('#select23').val());
	oMyForm.append("reply", $('#select28').val());
	oMyForm.append("arrival", $('#select35').val());
	oMyForm.append("destination", $('#select38').val());
	oMyForm.append("examination", $('#select41').val());
	oMyForm.append("ooc", $('#select44').val());
	oMyForm.append("ediqueue", $('#select45').val());
	oMyForm.append("delivery", $('#select48').val());
	oMyForm.append("detain", $('#select46').val());
	oMyForm.append("weight", $('#select13').val());


	oMyForm.append("cn23", $('#fourdigit').val());
	oMyForm.append("revcn23", $('#revfourdigit').val());
	oMyForm.append("examfind", $('#select42').val());


	oMyForm.append("sendername", $('#input4').val());
	oMyForm.append("recname", $('#input6').val());
	oMyForm.append("itemdesc", $('#input15').val());


	oMyForm.append("arrdt", $('#select31').val());
	oMyForm.append("examdt", $('#select43').val());
	oMyForm.append("deldt", $('#select47').val());


	oMyForm.append("delistatus", $('#deliverystatus').val());



	oMyForm.append("YN", $('#YN').val());
	oMyForm.append("Doc", $('#Doc').val());
	oMyForm.append("Columns", JSON.parse(localStorage.getItem("columns")));


	showLoader();
	jQuery.ajax({
		url: "reportdatechange",
		global: false,
		data: oMyForm,
		dataType: 'text',
		processData: false,
		contentType: false,
		type: 'POST',

		success: function(data) {
			console.log('inside success');

			console.log('queryQueueshow inside success');

			$("#queryQueueshow").replaceWith(data);
			if ($('#select48').val() == 'Not Delivered' || $('#select48').val() == 'Delivered' || $('#select48').val() == 'All') {
				$('#processtable').DataTable().column(49).visible(true);
			} else {
				$('#processtable').DataTable().column(49).visible(false);
			}


			if ($('#select35').val() == 'Yes' || $('#select35').val() == 'All') {
				$('#processtable').DataTable().column(36).visible(true);
				$('#processtable').DataTable().column(37).visible(true);
			} else {
				$('#processtable').DataTable().column(36).visible(false);
				$('#processtable').DataTable().column(37).visible(false);
			}


			if ($('#select38').val() == 'Yes' || $('#select38').val() == 'All') {
				$('#processtable').DataTable().column(39).visible(true);
				$('#processtable').DataTable().column(40).visible(true);
			} else {
				$('#processtable').DataTable().column(39).visible(false);
				$('#processtable').DataTable().column(40).visible(false);
			}


			if ($('#select23').val() == 'Yes' || $('#select23').val() == 'All') {
				$('#processtable').DataTable().column(24).visible(true);
				$('#processtable').DataTable().column(25).visible(true);
				$('#processtable').DataTable().column(26).visible(true);
				$('#processtable').DataTable().column(27).visible(true);
			} else {
				$('#processtable').DataTable().column(24).visible(false);
				$('#processtable').DataTable().column(25).visible(false);
				$('#processtable').DataTable().column(26).visible(false);
				$('#processtable').DataTable().column(27).visible(false);
			}

			if ($('#select28').val() == 'E - Reply' || $('#select28').val() == 'Physical Reply' || $('#select28').val() == 'All') {
				$('#processtable').DataTable().column(29).visible(true);
			} else {
				$('#processtable').DataTable().column(29).visible(false);
			}

				
				$('#processtable').DataTable().column(49).visible(false);
				$('#processtable').DataTable().column(36).visible(false);
				$('#processtable').DataTable().column(37).visible(false);
				$('#processtable').DataTable().column(40).visible(false);
				$('#processtable').DataTable().column(39).visible(false);
				$('#processtable').DataTable().column(24).visible(false);
				$('#processtable').DataTable().column(25).visible(false);
				$('#processtable').DataTable().column(26).visible(false);
				$('#processtable').DataTable().column(27).visible(false);
				$('#processtable').DataTable().column(29).visible(false);

			columnsname.forEach(function(el, index) {
				if (el==48 && ( $('#select48').val() == 'Not Delivered' || $('#select48').val() == 'Delivered' || $('#select48').val() == 'All' )) {
					$('#processtable').DataTable().column(49).visible(true);
				}
				if (el==35 && ( $('#select35').val() == 'Yes' || $('#select35').val() == 'All' )) {
				$('#processtable').DataTable().column(36).visible(true);
				$('#processtable').DataTable().column(37).visible(true);
				} 
				if (el==38 && ( $('#select38').val() == 'Yes' || $('#select38').val() == 'All' )) {
				$('#processtable').DataTable().column(40).visible(true);
				$('#processtable').DataTable().column(39).visible(true);
				} 
				if (el==23 && ( $('#select23').val() == 'Yes' || $('#select23').val() == 'All' )) {
				$('#processtable').DataTable().column(24).visible(true);
				$('#processtable').DataTable().column(25).visible(true);
				$('#processtable').DataTable().column(26).visible(true);
				$('#processtable').DataTable().column(27).visible(true);
				} 
				if (el==28 && ( $('#select28').val() == 'E - Reply' || $('#select28').val() == 'Physical Reply' || $('#select28').val() == 'All' )) {
					$('#processtable').DataTable().column(29).visible(true);
				}
			});

			$('#overlay').height($("body").innerHeight());
			//   $("#input16,#input6,#input4").trigger("keyup");

			hideLoader();

			//9,16,17,41

		}
	});



}

/*$("#fromdate").datepicker({
	dateFormat: "dd/mm/yy",
	onSelect: function(date) {
		$("#todate").datepicker('option', 'minDate', date);
*/



		/*var oMyForm = new FormData();
		oMyForm.append("fromdate", $('#fromdate').val());
		oMyForm.append("todate", $('#todate').val());

		oMyForm.append("country", $('#select3').val());
		oMyForm.append("state", $('#select8').val());
		oMyForm.append("pincode", $('#select9').val());
		oMyForm.append("pincode1", $('#pincode').val());
		oMyForm.append("ead", $('#select10').val());
		oMyForm.append("mailclass", $('#select11').val());
		oMyForm.append("mailcategory", $('#select12').val());
		oMyForm.append("totalduty", $('#select13').val());
		oMyForm.append("amount", $('#select14').val());
		oMyForm.append("fine", $('#select15').val());
		oMyForm.append("penalty", $('#select16').val());
		oMyForm.append("cth", $('#select17').val());
		oMyForm.append("itemvalue", $('#select19').val());
		oMyForm.append("itemno", $('#select20').val());
		oMyForm.append("query", $('#select21').val());
		oMyForm.append("reply", $('#select22').val());
		oMyForm.append("arrival", $('#select24').val());
		oMyForm.append("destination", $('#select25').val());
		oMyForm.append("examination", $('#select26').val());
		oMyForm.append("ooc", $('#select27').val());
		oMyForm.append("ediqueue", $('#select28').val());
		oMyForm.append("delivery", $('#select30').val());
		oMyForm.append("detain", $('#select31').val());
		oMyForm.append("weight", $('#select32').val());


		oMyForm.append("YN", $('#YN').val());
		oMyForm.append("Doc", $('#Doc').val());
		oMyForm.append("Columns", JSON.parse(localStorage.getItem("columns")));
		showLoader();
		jQuery.ajax({
			url: "reportdatechange",
			global: false,
			data: oMyForm,
			dataType: 'text',
			processData: false,
			contentType: false,
			type: 'POST',

			success: function(data) {
				console.log('inside success');

				console.log('queryQueueshow inside success');

				$("#queryQueueshow").replaceWith(data);
				$('#overlay').height($("body").innerHeight());
				$("#input18,#input6,#input4").trigger("keyup");

				hideLoader();

			}
		});
*/





	/*}
});
$("#todate").datepicker({
	dateFormat: "dd/mm/yy",
});
$('#fromdate').datepicker('setDate', fromdate);
$("#todate").datepicker('option', 'minDate', fromdate);
$('#todate').datepicker('setDate', todate);*/






      var currentDate=new Date();
$("#fromdate").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate: currentDate,
	onSelect: function(date) {
		$("#todate").datepicker('option', 'minDate', date);
		
		}
	});
$("#todate").datepicker({
	dateFormat: "dd/mm/yy",
	maxDate:currentDate,
	onSelect: function(date) {
		$("#fromdate").datepicker('option', 'maxDate', date);
		
	}
});
$('#fromdate').datepicker('setDate', fromdate);
$("#todate").datepicker('option', 'minDate', fromdate);
$('#todate').datepicker('setDate', todate); 