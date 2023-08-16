
$(document).ready(function() {
	$("#deliverytable").dataTable({
		"processing": true,
		"paging": true
	});
	
	
            $('#deliverytable').DataTable().on( 'order.dt search.dt', function () {
                $('#deliverytable').DataTable().column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                         cell.innerHTML = i+1;
                     } );
                 }).draw();





var urlLink = 'deliveryarticledatechange';

$("#button-list a").click(function() {
    $("#button-list a").removeClass("btn-success");
    $("#button-list a").addClass("btn-outline-success");
    $(this).removeClass("btn-outline-success");
    $(this).addClass('btn-success');
    $(this).addClass('btn-success');
    processname = $(this).attr('name');

    if (processname == 'Acknowledgement') {
        urlLink = 'deliveryarticledatechange';
    } else if (processname == 'pending') {
        urlLink = 'deliveryarticlepending';
    } 

    processclick(urlLink);
});



function processclick(urlLink) {

    var zone = $("#getreportlistform").serialize();
    att = urlLink;
    $.post(att, zone,
        function(data, status) {
	
           console.log('Delivery Article inside success');
        				
        				    $("#maindiv").replaceWith(data);


        });


}

$("#fromdate").datepicker({
  dateFormat: "dd/mm/yy",
onSelect: function(date) {
    $("#todate").datepicker('option', 'minDate', date);
var zone = $("#getreportlistform").serialize();
 	att=urlLink ;  
	$.post(att, zone,
 	        function(data,status){
        				console.log('Delivery Article inside success');
        				
        				    $("#maindiv").replaceWith(data);
        				    
        			
        		});

  }
});
$(".change").on("change",function(){
var zone = $("#getreportlistform").serialize();
 	att=urlLink ;  
	$.post(att, zone,
 	        function(data,status){
        				console.log('Delivery Article inside success');
        				
        				    $("#maindiv").replaceWith(data);
        				    
        			
        		});
	});
$("#todate").datepicker({
	dateFormat: "dd/mm/yy",
});
$('#fromdate').datepicker('setDate',fromdate );
 $("#todate").datepicker('option', 'minDate', fromdate);
$('#todate').datepicker('setDate',todate );
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
