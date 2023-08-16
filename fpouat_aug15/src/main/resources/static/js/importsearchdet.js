$(document).ready(function() {
		$("#asstTable").dataTable({
			"processing" : true,
			"paging" : true
		});
	});

//	function ShowHideDiv(btnPassport) {
//		var dvPassport = document.getElementById("dvPassport");
//		if (btnPassport.value == "Yes") {
//			dvPassport.style.display = "block";
//			btnPassport.value = "No";
//		} else {
//			dvPassport.style.display = "none";
//			btnPassport.value = "Yes";
//		}
//	}
	
	function ShowHideDiv(btnPassport) {
		var dvPassport = document.getElementById("dvPassport");
		if (btnPassport.value == "Yes") {
			dvPassport.style.display = "none";
			btnPassport.value = "No";
		} else {
			dvPassport.style.display = "block";
			btnPassport.value = "Yes";
		}
	} 
	

	function viewDetail(e) {
		window.location.href = "import_submit?id=" + e.id;
	}

	$("table tbody tr").each(
			function() {
				var textval = $(this).find("td:eq(0)").text().trim();
				//	var textval2 = $(this).find("td:eq(1)").text().trim();
				if (textval > 1) {
					$('#' + textval).prop('disabled', true);
					//$(this).find("td:eq(0)").attr('disabled', true);
				} else {
					$("tr.rowDis input, tr.rowDis select, tr.rowDis textarea")
							.prop('disabled', true);
					//$('#submitBtn').prop('disabled', false);
				}
		});
		
		function searchshow(e){
			var fpoMain = {};
			fpoMain['id'] = null;
			fpoMain['item_ID'] = null;
			fpoMain['QRY_DT'] = null;
			fpoMain['send_CNTRY_CD'] = null;
			fpoMain['id'] = $('#cin_No').val();
			fpoMain['item_ID'] = $('#item_Id').val();
			fpoMain['QRY_DT'] = $('#qry_Dt').val();
			fpoMain['send_CNTRY_CD'] = $('#org_Cd').val();
			$.ajax({
		url: 'getsearchDETdata',
		data: JSON.stringify(fpoMain),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(data) {

			
			var counter = data.length;
			var stRow = 0;
			var atNo = 1;
			$("#articleDet tr").remove();
			while (stRow < counter) {
				var newRow = $("<tr>");
				var cols = "";
				cols += '<td th:text="0" id="atSl' + atNo + '"></td>';
				cols += '<td th:text="0" id="atCin' + atNo + '"></td>';
				cols += '<td th:text="0" id="atItemId' + atNo + '"></td>';
				cols += '<td th:text="0" id="atDate' + atNo + '" ></td>';
				cols += '<td th:text="0" id="atOrgCd' + atNo + '" ></td>';
				cols += '<td th:text="0" id="atAction' + atNo + '" ></td>';
				cols += '<td><input type="button" class="btn btn-success" th:id="atCin' + atNo + '" th:text="0" onclick="viewDetail(th:value);"  value="DETAINED"></td>';
				newRow.append(cols);
				$("table.table-articleDet").append(newRow);
				
				$('#atSl'+atNo).text(atNo);
				$('#atCin'+atNo).text(data[stRow][0]);
				$('#atItemId'+atNo).text(data[stRow][1]);
				$('#atDate'+atNo).text(data[stRow][2]);
				$('#atOrgCd'+atNo).text(data[stRow][3]);
				$('#atAction'+atNo).text(data[stRow][4]);
				
				
				atNo = atNo + 1;
				stRow = stRow + 1;
			}
					
			$('#onsearch').show();		
			
			
		},
		fail: function(rs, e) {
			console.log(rs, responseText);
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