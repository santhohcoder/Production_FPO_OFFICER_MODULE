window.localStorage.removeItem("minutes");
window.localStorage.removeItem("seconds");
window.localStorage.removeItem("hours");
window.localStorage.removeItem("sessiontime");
window.localStorage.removeItem("prevUrl");
window.localStorage.removeItem("currentUrl");

function login(value) {
	var developerData = {};
	developerData['id'] = $("#username").val();
	developerData['pwd'] = $('#pwd').val();

	var resObj = $.ajax({
		url: 'loginUser',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
			if (developerDatas != "") {
			  /*if (developerDatas.length > 1)
			   window.location.href = "selrole";
			  else
				window.location.href = "dash";*/
				/*if(developerDatas[0][2] == 'NSA'){
					window.location.href = "nsm_module";
				}else if(developerDatas[0][2] == 'LSA'){
					window.location.href = "lsm_module";
				}else{
					window.location.href = "selrole";
				}*/
				window.location.href = "selrole";
			} else {
				$("#loginWarningMsg").modal('show');
			}
		},
		fail: function(rs, e) {
			alert("Error in Login");
		}
	});

};

$(document).on({
    "contextmenu": function(e) {
        console.log("ctx menu button:", e.which); 

        // Stop the context menu
        e.preventDefault();
    },
    "mousedown": function(e) { 
        console.log("normal mouse down:", e.which); 
    },
    "mouseup": function(e) { 
        console.log("normal mouse up:", e.which); 
    }
});

  localStorage.setItem("columns", JSON.stringify([1]));