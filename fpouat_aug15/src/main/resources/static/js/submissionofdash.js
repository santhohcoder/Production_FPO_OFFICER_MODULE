$('#fromdate').datepicker('setDate', fromdate);
$("#todate").datepicker('option', 'minDate', fromdate);
$('#todate').datepicker('setDate', todate);


/*$("#fromdate").datepicker({
    dateFormat: "dd/mm/yy",
    onSelect: function(date) {
$("#todate").datepicker('option', 'minDate', date);
	ajay();
    }
});

$("#todate").datepicker({
    dateFormat: "dd/mm/yy",
});*/


var currentDate=new Date();
  $("#fromdate").datepicker({
    dateFormat: "dd/mm/yy",
    maxDate: currentDate,
    onSelect: function(date) {
  $("#todate").datepicker('option', 'minDate', date);
	ajay();
    }
 });
  
 $("#todate").datepicker({
    dateFormat: "dd/mm/yy",
    maxDate: currentDate,
    onSelect: function(date) {
 $("#fromdate").datepicker('option', 'maxDate', date);

  }
 });

 

		
var d = new Date();
var month = d.getMonth();
var yr = d.getFullYear();
$('#fromdate').datepicker('setDate', new Date(yr,month,1))
$('#todate').datepicker('setDate', 'now')

var selectedSite  = "";
	$("select.selsite").change(function() {
		showLoader();
	
		 selectedSite = $(this).children("option:selected").val();
	ajay();
		
$( ".change" ).change(function() {
	ajay();
});
hideLoader();
		
});

function ajay(){
	var frmdate = $("#fromdate").val();
	var todate =$("#todate").val();
	
	
		$.ajax({
			url: 'getarticleprcsdata?selsite=' + selectedSite + "&frmdt=" + frmdate + "&todt=" + todate,
			success: function(articledata) {
				/*var markup = "<tr><td align= 'center' class= 'font-weight-medium'>" + articledata.CUSCount + "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.Av + "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.duty + "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.stscunt+ "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.assAv+ "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.assDuty + "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.assAmtchrg + "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" +articledata.assdutyfg+ "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.ooccunt + "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.oocAv + "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.oocDuty + "</td>" + 
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.oocAmtchrg + "</td>"+
				"<td align= 'center' class= 'font-weight-medium'>" + articledata.oocdutyfg + "</td></tr>"
				
						$("#appendprcsdata").append(markup);*/
						$('#activeusersite').replaceWith(articledata);
				},

    error: function(xhr, textStatus, errorThrown) {
        if (xhr.status === 400) {
             window.location = "error"; // Redirect to the error page
        } else {
            // Handle other error cases
            console.log(xhr, textStatus, errorThrown);
        }
    },
				
				});
				
}
$(document).ready(function() {
	showLoader();
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




