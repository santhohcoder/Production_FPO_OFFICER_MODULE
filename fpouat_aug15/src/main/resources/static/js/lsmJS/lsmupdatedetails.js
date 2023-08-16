$('#Updatedetails').click(function() {
	var usrid = $('#getusrid').val();
	var mblnum = $('#getmblenum').val();
	var mailid = $('#getmailid').val();
 
if(usrid!="--Select SSOID--"){
				$.ajax({
					url: "updateuserdetails?userdetls=" + usrid + "&mblenum=" + mblnum + "&mailid=" + mailid,
					type: "get",
					success: function(data) {
						swal("Success!", "User Details Updated!", "success")
					.then((value) => {
						location.reload();
					});
						
					},
				});
				
				}else {
					
					alert("Select SSOID")
	
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