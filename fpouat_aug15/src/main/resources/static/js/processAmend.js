function nextItemNo(id) {
	var developerData = {};
	developerData['cinNo'] = id;
	developerData['itemId'] = $('#itemId').val();
	developerData['itemNumber'] = $('#itemNoChange').val();
	
	var resObj = $.ajax({
		url: 'processAmend?cinNo=' + id + '&itemNumber=' + $('#itemNoChange').val() + '&itemId=' + $('#itemId').val(),
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerData) {
		alert("Assessment Successfully Completed");
		$('.updateAss').text("Change");
		},
		fail: function(rs, e) {
			alert("Error in Assessment");
		}
	});
}