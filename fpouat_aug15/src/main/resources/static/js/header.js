
$(document).keydown(function (event) {

  if(event.keyCode == 123) {
     return false;
  }
  else if(event.ctrlKey && event.shiftKey && event.keyCode == 'I'.charCodeAt(0)) {
     return false;
  }
  else if(event.ctrlKey && event.shiftKey && event.keyCode == 'C'.charCodeAt(0)) {
     return false;
  }
  else if(event.ctrlKey && event.shiftKey && event.keyCode == 'J'.charCodeAt(0)) {
     return false;
  }
  else if(event.ctrlKey && event.keyCode == 'U'.charCodeAt(0)) {
     return false;
  }

});

function sessionTime() {
	var m1 = $('#sttime').val();
     
//	var m2 = moment(new Date(), 'DD-MM-YYYY HH:mm:ss'); 
     var m2 = new Date();
	var hh1 = m1.substring(11,13);
	var mm1 = m1.substring(14,16);
	var ss1 = m1.substring(17,19);
	
	var hh2 = m2.getHours();
	var mm2 = m2.getMinutes();
	var ss2 = m2.getSeconds();
	
    var hh=0,h = 0; 
	var mm=0,m = 0;  
	var ss=0,s = 0;  
	
	if (parseInt(ss2) >= parseInt(ss1))
	  s=parseInt(ss2)-parseInt(ss1);
    else
     { s=60+parseInt(ss2)-parseInt(ss1); }

    if (parseInt(mm2) >= parseInt(mm1))
	  m=parseInt(mm2)-parseInt(mm1);
    else
     { m=60+parseInt(mm2)-parseInt(mm1);}
    
    if (parseInt(hh2) >= parseInt(hh1))
	  h=parseInt(hh2)-parseInt(hh1);
    else
      h=24+parseInt(hh2)-parseInt(hh1);

    if ( parseInt(ss2)  <  parseInt(ss1))
      { m=parseInt(m)-1;}
    if (parseInt(mm2)  <  parseInt(mm1))
      { h=parseInt(h)-1;}
	/*
	 var hh = Math.abs(hh2-hh1);
	var mm = Math.abs(mm2-mm1);
	if (mm2 < mm1)
	  hh=hh+1;
	var ss = Math.abs(ss2-ss1);
    if (ss2<ss1)	
      mm=mm+1;*/
	
//	var time =  moment(new Date()).startOf('day').seconds(ss).format('HH:mm:ss');
	
/*	if(hh >= 24){
		var diff=hh-23;
		hh= hh-(diff*24);
	}*/
	

	
	if (h==0)
	   hh='00';
    else if (h==1)
       hh='01';
    else if (h==2)
       hh='02';
    else if (h==3)
       hh='03';
    else if (h==4)
       hh='04';
    else if (h==5)
       hh='05';
    else if (h==6)
       hh='06';
    else if (h==7)
       hh='07';
    else if (h==8)
       hh='08';
    else if (h==9)
       hh='09';
    else
       hh=h;

    if (m==0)
	   mm='00';
    else if (m==1)
       mm='01';
    else if (m==2)
       mm='02';
    else if (m==3)
       mm='03';
    else if (m==4)
       mm='04';
    else if (m==5)
       mm='05';
    else if (m==6)
       mm='06';
    else if (m==7)
       mm='07';
    else if (m==8)
       mm='08';
    else if (m==9)
       mm='09';
    else
       mm=m;
  
    if (s==0)
	   ss='00';
    else if (s==1)
       ss='01';
    else if (s==2)
       ss='02';
    else if (s==3)
       ss='03';
    else if (s==4)
       ss='04';
    else if (s==5)
       ss='05';
    else if (s==6)
       ss='06';
    else if (s==7)
       ss='07';
    else if (s==8)
       ss='08';
    else if (s==9)
       ss='09';
    else
       ss=s;
	
	
	var time =  hh + ":" + mm + ":" + ss;
	console.log(time);
	document.getElementById("clock1").innerText = time;
	let t = setTimeout(function() { sessionTime() }, 1000);
	}








function currentTime() {
	let date = new Date();
	let hh = date.getHours();
	let mm = date.getMinutes();
	let ss = date.getSeconds();
	//let session = "AM";


	/*if(hh > 12){
	  //  hh = hh - 12;
		session = "PM";
	 }*/

	hh = (hh < 10) ? "0" + hh : hh;
	mm = (mm < 10) ? "0" + mm : mm;
	ss = (ss < 10) ? "0" + ss : ss;


	var today = new Date();
	var dd = today.getDate();

	var month = today.getMonth() + 1;
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = '0' + dd;
	}

	if (month < 10) {
		month = '0' + month;
	}
	today = dd + '/' + month + '/' + yyyy;

	let time = today + " " + hh + ":" + mm + ":" + ss;

	document.getElementById("clock").innerText = time;
	let t = setTimeout(function() { currentTime() }, 1000);

}



var date = new Date();
var hours = date.getHours();
var seconds = date.getSeconds();
var minutes = date.getMinutes();

if (localStorage.getItem("hours") != null || localStorage.getItem("seconds") != null || localStorage.getItem("minutes") != null) {

	hours = parseInt(localStorage.getItem("hours"));
	seconds = parseInt(localStorage.getItem("seconds"));
	minutes = parseInt(localStorage.getItem("minutes"));
} else {
	localStorage.setItem("hours", hours);
	localStorage.setItem("seconds", seconds);
	localStorage.setItem("minutes", minutes);
}


var currentDate = new Date();
					 var day = currentDate.getDate();
					 var month = currentDate.getMonth() + 1;
					 var year = currentDate.getFullYear();
					 var hour=currentDate.getHours();
					 var min=currentDate.getMinutes();
					 var second=currentDate.getSeconds();
				var datetime = day + "/" + month + "/" + year + " " + hour + ":" + min +":" + second;
				
				// creating this because there was a clash between same name
				
					 var datetimes = day + "/" + month + "/" + year + " " + hour + ":" + min +":" + second;
					 var empid = $("#empId").val();
					 var sitecode = $("#sitecode").val();



function currentTime1() {
	
	let date = new Date();

	let hh = date.getHours() - hours;
	let mm = date.getMinutes() - minutes;
	if (mm < 0) {
		mm = 60 - mm;
	}
	let ss = date.getSeconds() - seconds;

	if (ss < 0) {
		ss = 60 + ss;
		if (mm != 0) {
			mm = mm - 1;
		}
	}


	localStorage.setItem("hours", hours);
	localStorage.setItem("seconds", seconds);
	localStorage.setItem("minutes", minutes);

	/*if(hh > 12){
	  //  hh = hh - 12;
		session = "PM";
	 }*/

	hh = (hh < 10) ? "0" + hh : hh;
	mm = (mm < 10) ? "0" + mm : mm;
	ss = (ss < 10) ? "0" + ss : ss;


	let time = hh + ":" + mm + ":" + ss;

	document.getElementById("clock1").innerText = time;
	let t = setTimeout(function() { currentTime1() }, 1000);

}


$(document).ready(function() {

history.pushState(null, null, location.href);

});

    window.onpopstate = function () {
        history.go(1);
    };


var idleTime = 0

//document.addEventListener('mousemove', resetIdleTime, false);
//document.addEventListener('keypress', resetIdleTime, false);

function resetIdleTime() {
	idleTime = 0
}

//function noBack() {
//	window.history.forward();
//}

function chngrole(){
	$('#chngrlpopup').modal('hide');
	window.location="selrole";
}

$('#chngroles').click(function(){
	$('#chngrlpopup').modal('show');
});


 let inactivityTime = function() {
        let time;
        window.onload = resetTimer;
        document.onmousemove = resetTimer;
        document.onkeypress = resetTimer;
		resetTimer();
        function logout() {
$('#incative').modal('show');      
        }
        function resetTimer() {
          clearTimeout(time);
          time = setTimeout(logout, 2700000)
        }
      };

      window.onload = function() {
        inactivityTime();
}
 function inactivetime () {
	$('#incative').modal('hide');
	window.location="logout";
}


function godash(){
	$('#incative').modal('hide');
	window.location="dash";
	
}



function checkIfIdle() {
	idleTime += 1000
	//console.log(idleTime)
	if (idleTime >= 300000) {
		//alert("Inactive for 5 seconds")
		//swal('OOPS!', 'Session is idle For more than 5 minutes.Do you want to continue?', 'error');

		Swal.fire({
			title: 'OOPS!',
			text: "Session is idle For more than 5 minutes.Do you want to continue?",
			icon: 'error',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes!',
			allowOutsideClick: false
		}).then((result) => {
			if (result.isConfirmed) {

			}
		});
		clearInterval(idleInterval)
		//currentTime();
	}
}

//var idleInterval = setInterval(checkIfIdle, 1000);

$(document).ready(function() {
	if(location.href!=localStorage.getItem("currentUrl")){
		localStorage.setItem("prevUrl", localStorage.getItem("currentUrl"));
	}
	localStorage.setItem("currentUrl", location.href);
window.history.pushState({}, "", "Home");
	currentTime();
	//currentTime1();
	sessionTime();
});

/*$(document).bind("contextmenu", function(e) {
	return false;
});*/


/*$(document).keydown(function(event) {
	console.log(event.keyCode);
	if (event.keyCode == 123) {
		return false;
	} else if (event.keyCode == 116) {
		return false;
	}
	else if (event.ctrlKey || event.shiftKey || (event.ctrlKey && event.shiftKey)) {
		return false;
	}
	else if ((event.ctrlKey && event.shiftKey && event.keyCode == 73) || (event.ctrlKey && event.shiftKey && event.keyCode == 74) || (event.ctrlKey && event.shiftKey && event.keyCode == 82)) {
		return false;
	}
});*/

/* function preventBack() {
            window.history.forward(); 
        }
          
        setTimeout("preventBack()", 0);
          
        window.onunload = function () { null };*/

$(document).ready(function() {
	if ($('#role').val() == 'PNA' || $('#role').val() == 'NAL' || $('#role').val() == 'NRP' )
	   $('#pna').show();
    else
       $('#oth').show();            
});



var currentDate = new Date();
					 var day = currentDate.getDate();
					 var month = currentDate.getMonth() + 1;
					 var year = currentDate.getFullYear();
					 var hour=currentDate.getHours();
					 var min=currentDate.getMinutes();
					 var second=currentDate.getSeconds();
					 var datetime = day + "/" + month + "/" + year + " " + hour + ":" + min +":" + second;
					 var empid = $("#empId").val();
					 var sitecode = $("#sitecode").val();
				     console.log(sitecode);


//document.addEventListener("contextmenu", function (e) {
 //      e.preventDefault();
 //   }, false);

function logout() {
	$('#logoutModal').modal('show');
}

/*function logconfirm() {
  
  localStorage.setItem("loggedOut", "true");
  localStorage.setItem("columns", JSON.stringify([1]));

  function preventBack() {
    if (localStorage.getItem("loggedOut") === "true") {
      window.history.replaceState(null, "", "logout");
    } else {
      window.history.forward(1);
    }
  }
  setTimeout(preventBack, 0);


  window.onunload = null;

  window.location = "logout";
}*/


/*function logconfirm() {
  
  /*localStorage.setItem("loggedOut", "true");
  localStorage.setItem("columns", JSON.stringify([1]));

  function preventBack() {
    if (localStorage.getItem("loggedOut") === "true") {
      window.history.replaceState(null, "", "logout");
    } else {
      window.history.forward(1);
    }
  }
  setTimeout(preventBack, 0);


  /*window.onunload = null;

  window.location = "logout";
   window.location.href = "feedback1";

}*/


    function logconfirm() {
  localStorage.setItem("loggedOut", "true");
  localStorage.setItem("columns", JSON.stringify([1]));



  function preventBack() {
    if (localStorage.getItem("loggedOut") === "true") {
      window.history.replaceState(null, "", "logout");
    } else {
      window.history.forward(1);
    }
     localStorage.clear();
  }
  setTimeout(preventBack, 0);

  window.onunload = null;

 
   window.location = "logout";

  
    var empid1 = $("#empId").val(); 
    window.location.href = "feedback1?offid1=" + empid1; 
}





