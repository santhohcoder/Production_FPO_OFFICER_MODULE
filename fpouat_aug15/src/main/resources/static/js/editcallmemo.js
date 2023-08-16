y = n.getFullYear();
n = new Date();
m = n.getMonth() + 1;
d = n.getDate();
document.getElementById("date").innerHTML = m + "/" + d + "/" + y;

$.ajax({
	url: 'getTotalNoItems?cinNo=' + $('#cinNo').text(),
	data: JSON.stringify(""),
	dataType: "json",
	contentType: "application/json",
	type: "post",
	success: function(data) {

		$("#totalItems").text(data);
	},
	fail: function(rs, e) {
		console.log(rs, responseText);
	}
});