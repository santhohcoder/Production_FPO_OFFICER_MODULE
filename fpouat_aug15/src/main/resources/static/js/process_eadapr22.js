function ShowHideDiv(btnPassport) {
	var dvPassport = document.getElementById("dvPassport");
	if (btnPassport.value == "Yes") {
		dvPassport.style.display = "block";
		btnPassport.value = "No";
	} else {
		dvPassport.style.display = "none";
		btnPassport.value = "Yes";
	}
}

$(document).ready(function() {
	$(".asstTable").dataTable({
		"processing": true,
		"paging": true
	});
});


/*$(document).ready(function() {
	$(".btn-success").click(function() {
		$("#pendingQuery").modal('show');

	});


});*/

$('#examQueue').click(function() {
	$("#examQueueshow").show();
	$("#queryQueueshow").hide();
	$("#oocQueueshow").hide();
	$("#ediQueueshow").hide();
});

$('#queryQueue').click(function() {
	$("#queryQueueshow").show();
	$("#examQueueshow").hide();
	$("#oocQueueshow").hide();
	$("#ediQueueshow").hide();
});

$('#oocQueue').click(function() {
	$("#oocQueueshow").show();
	$("#examQueueshow").hide();
	$("#queryQueueshow").hide();
	$("#ediQueueshow").hide();
});

$('#ediQueue').click(function() {
	$("#ediQueueshow").show();
	$("#examQueueshow").hide();
	$("#queryQueueshow").hide();
	$("#oocQueueshow").hide();
});

function clearDropDown(e) {
	e.empty();

}


function pendingquery(e) { 

    clearDropDown($("#qry_date"));
    clearDropDown($("#query_din"));
	clearDropDown($("#item_id"));
	clearDropDown($("#item_Desc"));
	clearDropDown($("#item_qry"));
	clearDropDown($("#Def_qury"));
	clearDropDown($("#qryid"));
	clearDropDown($("#defQrey"));
	document.getElementById("defQrey").value = "";
	document.getElementById("dpcments").value = "";
	document.getElementById('next').disabled = true;
	document.getElementById("qry_date").value = "";
	document.getElementById("query_din").value = "";
	
	$("#tb2").show();

	var id = e.id;
	var pendingquery = {};
	var item_add = [];

	pendingquery["cinNo"] = id;

	$.ajax({
		url: 'pendingdata?id=' + id,
		data: JSON.stringify(pendingquery),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerData) {

			pendingdata = developerData;
			$("#penaltyId").empty();
			$.each(developerData, function(index, value) {

				var itemid1 = "";
				var markup = "<tr><td id= 'item_id1'class= 'font-weight-bold'>" + value[0] + "</td><td id='item_Desc1' class= 'font-weight-bold'>" + value[2] + "</td><td id= 'item_qry1' class= 'font-weight-bold'>" + value[1] + "</td><td id= 'rply' class= 'font-weight-bold'><textarea id='itemresp' class='form-control font-weight-normal'rows='1' placeholder= 'Enter response' name='Recrdresp'></textarea></td></tr>";
				$("#penaltyId").append(markup);
				itemid1 = value[0];
				item_add.push(itemid1);
			});

			$.ajax({
				url: 'fpoquerydin?id=' + id,
				data: JSON.stringify(pendingquery),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(developerData1) {
					
					if (developerData1.length == 0) {
						   $("#tb2").hide();
						}

					$("#query_din").val(developerData1[0].uniqueNo);
					
					$.ajax({
								url: 'querydate?id=' + id,
								data: JSON.stringify(""),
								dataType: "json",
								contentType: "application/json",
								type: "post",
								success: function(QryDate) {

									$("#qry_date").val(QryDate.date);

					$.ajax({
						url: 'fpodefaultQry?id=' + id,
						data: JSON.stringify(pendingquery),
						dataType: "json",
						contentType: "application/json",
						type: "post",
						success: function(developerData3) {

							var data_defqry = {};

							var def_Qry = [];

							if (developerData3.length != 0) {
								for (i = 0; i < developerData3.length; i++) {
									data_defqry = (developerData3[i]);
									def_Qry.push(data_defqry)
								}
								$.each(def_Qry, function(index, value) {
									$("#Def_qury").append(index + 1 + ". " + value + '<br>' + '<br>');
								});
							}
							else {
								$("#tb2").hide();
							}
							
						}
					});
					
					}
					
					});
				},

			});

		},

		fail: function(rs, e) {
			console.log(rs, responseText);
		}

	});

	$("#dpcments").blur(function() {

		if (null != $("#dpcments").val()) {
			$("#next").attr("disabled", false);
		}

		if ($("#dpcments").val() == "") {
			$("#next").attr("disabled", true);
		}
	});

	$(document).ready(function() {
		var developerData5 = {};
		var card_val = [];
		var card_value = {};
		var defQrydata = {};

		$(".submit").click(function() {
			$("textarea[name^='Recrdresp']").each(function() {
				card_value = ($(this).val());
				card_val.push(card_value);
			});

			developerData5['cinNo'] = id;

			for (i = 0; i < item_add.length; i++) {
				developerData5['item_no'] = item_add[i];
				developerData5['rply'] = card_val[i];

				$.ajax({
					url: 'insertpendingdata',
					data: JSON.stringify(developerData5),
					dataType: "json",
					contentType: "application/json",
					type: "post",
					success: function() {

					}
				});

			}
			var defQry = $("#defQrey").val();
			defQrydata['dpcmts'] = $("#dpcments").val();
			defQrydata['rply'] = defQry;
			defQrydata['cinNo'] = id;
			$.ajax({
				url: 'defqryresp',
				data: JSON.stringify(defQrydata),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function() {

				}

			});

			$.ajax({
				url: 'assdonernot?id=' + id,
				data: JSON.stringify(""),
				dataType: "json",
				contentType: "application/json",
				type: "post",
				success: function(data) {

					if (data >= 1) {
						$("#asspop").modal('show');
						$('#assement').text("Assessment completed it will move to Examination queue.")

					} else {
						$("#asspop").modal('show');
						$('#assement').text("Assessment not completed it will move to Assessment page.")
					}

				}

			});
			$("#pendingpopup").modal('hide');
			
			$('#asspop').modal({
				backdrop: 'static',
				keyboard: false
			})
		});

	});
	$('#pendingpopup').modal({
		backdrop: 'static',
		keyboard: false
	})

};
$("#closepopup").click(function() {
	$("#pendingpopup").modal('hide');
});




