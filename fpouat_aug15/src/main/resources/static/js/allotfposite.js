/*function shownotmap(){
$('#tblewithoutcusite').hide('slow');
$('#tblewithoutcusitenotmapped').show('slow');
$('#notmap').show();
$('#map').hide();
}*/

function shownotmap(){
$('#tblewithoutcusite').hide('slow');
//for 2nd table
$('#tblewithoutcusitenotmapped').show('slow');
$('#notmap').hide();
$('#map').hide();
$('#tablesinglerecrd').hide();
$('#divwithmapping').hide();

}

  
function showmap(){
$('#tblewithoutcusite').show('slow');
$('#tblewithoutcusitenotmapped').hide('slow');
$('#map').show();
$('#notmap').hide();
}

$(document).ready(function() {
	$('#map').show();
	$('#notmap').hide();
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