$(document).ready(function() {
    $("#processtable").dataTable({
        "processing": true,
        "paging": false,
		"ordering" :false,
			"dom": 'Bfrtip',
			buttons: [
            'excel', 'pdf', 'print'
        ]
    });

	//santhosh kanna

$("#openingbalancetable").dataTable({
		"processing": true,
		"paging": true
	});
	
	$("#ReceiptForMonthtable").dataTable({
		"processing": true,
		"paging": true
	});
	
	$('#openingbalancetable').DataTable().on('order.dt search.dt', function() {
        $('#openingbalancetable').DataTable().column(0, {
            search: 'applied',
            order: 'applied'
        }).nodes().each(function(cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();
	
	$('#ReceiptForMonthtable').DataTable().on('order.dt search.dt', function() {
        $('#ReceiptForMonthtable').DataTable().column(0, {
            search: 'applied',
            order: 'applied'
        }).nodes().each(function(cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();


    /*$('#processtable').DataTable().on('order.dt search.dt', function() {
        $('#processtable').DataTable().column(0, {
            search: 'applied',
            order: 'applied'
        }).nodes().each(function(cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();*/



    $("#selectmonth").val($("#month").val());
$("#selectyear").val($("#year").val());
    $("#fromdate").datepicker({
        dateFormat: "dd/mm/yy",
        onSelect: function(date) {
            $("#todate").datepicker('option', 'minDate', date);
            var zone = $("#getreportlistform").serialize();
            att = $("#getreportlistform").attr("action");
            $.post(att, zone,
                function(data, status) {
                    console.log('queryQueueshow inside success');

                    $("#queryQueueshow").replaceWith(data);


                });

        }
    });
    $("#todate").datepicker({
        dateFormat: "dd/mm/yy",
    });
    $('#fromdate').datepicker('setDate', fromdate);
    $("#todate").datepicker('option', 'minDate', fromdate);
    $('#todate').datepicker('setDate', todate);



$(document).on('show.bs.modal', '.modal', function (event) {
            var zIndex = 1040 + (10 * $('.modal:visible').length);
            $(this).css('z-index', zIndex);
            setTimeout(function() {
                $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
            }, 0);
        });

});



    var urlLink = 'importedparcel';

$("#button-list a").click(function() {
    $("#button-list a").removeClass("btn-success");
    $("#button-list a").addClass("btn-outline-success");
    $(this).removeClass("btn-outline-success");
    $(this).addClass('btn-success');
    $(this).addClass('btn-success');
    processname = $(this).attr('name');

    if (processname == 'importedparcel') {
        urlLink = 'importedparcel';
    } else if (processname == 'detainedparcel') {
        urlLink = 'detainedparcel';
    } else if (processname == 'boereport') {
        urlLink = 'boereport';
    } else if (processname == 'seizurereport') {
        urlLink = 'seizurereport';
    } else if (processname == 'customdutyreport') {
        urlLink = 'customdutyreport';
    }

    processclick(urlLink);
});


$(".monthchange").on("change", function() {

    var zone = $("#getreportmonthlistform").serialize();
    att = urlLink;
    $.post(att, zone,
        function(data, status) {
            console.log('month change inside success');

            $("#tablediv").replaceWith(data);


        });


});

$("#selectyear").on("change", function() {
        
        var oMyForm = new FormData();
        oMyForm.append("year", $('#selectyear').val());
        oMyForm.append("month", $('#selectmonth').val());
		

		//showLoader();
        jQuery.ajax({
            url: "yearchange",
            global: false,
            data: oMyForm,
            dataType: 'text',
            processData: false,
            contentType: false,
            type: 'POST',

            success: function(data) {
                var marker = JSON.stringify(data);
	            var json = jQuery.parseJSON(marker);
				json=JSON.parse(json);

	        	  $('#selectmonth').empty();
                  var selectdate = document.getElementById('selectmonth');
            	   for(i =0 ; i < json.list.length ; i++){
            		  var opt = document.createElement('option');
              	    opt.value = json.list[i].monthno;
              	    opt.innerHTML = json.list[i].monthname;
              	    selectdate.appendChild(opt);  
                 
                  } 
				
				
	processclick(urlLink);
					
            }
        });
    });

function processclick(urlLink) {

    var zone = $("#getreportmonthlistform").serialize();
    att = urlLink;
    $.post(att, zone,
        function(data, status) {
            console.log('Tab change inside success');

            $("#tablediv").replaceWith(data);


        });


}


function itemdetails(obj) {
	
	       		 var oMyForm = new FormData();
        		 oMyForm.append("itemid",$(obj).attr('data-itemid'));
        		 
        		 
        		jQuery.ajax({
        			url : "itemdetails",
        			global : false,
        			data:oMyForm,
        			dataType : 'text',
        			processData : false,
        			contentType : false,
        			type : 'POST',
        			
        			success : function(response) {
        				console.log('inside success');
        				
        				   $("#itemdet").replaceWith(response);

        				   $('#itemdet').modal("show");
        				    
        			}
        		});
        		
        

    /*var zone = $("#getreportmonthlistform").serialize();
    att = '/itemdetails';
    $.post(att, zone,
        function(data, status) {
            console.log('itemdet inside success');

            

        });*/


}


/*function pdfdownload(obj) {
    var downloadurlLink = '/importedparceldownload';
    var zone = $("#getreportmonthlistform").serialize();
    att = downloadurlLink;
    $.post(att, zone,
        function(data, status) {
            console.log('download inside success');
            $("#staticreportdownloadanchortag").attr("href","downloadPdfFile?filename=" + response);

            document.getElementById("staticreportdownloadanchortag").click();

        });


}
*/
/*$(document).on("click","#ExcelExport",function (){
  			  
  			  this.blur();
      		}); 


$("#ExcelExport").on('click',function(){
  	    	  $(".buttons-excel").trigger('click'); 
			  //$(".buttons-print").trigger('click'); 
  	    	this.blur();
  	      }); 	*/


$("#ExcelExport").on('click',function(){
  	    	  $(".buttons-excel").trigger('click'); 
			  //$(".buttons-print").trigger('click'); 
  	    	this.blur();
  	      });

$("#Print").on('click',function(){
			$(".buttons-print").trigger('click'); 
  	    	this.blur();
  	      });
function pdfdownload(obj) {

    var oMyForm = new FormData();

    oMyForm.append("monthnum", $('#selectmonth').val());
    oMyForm.append("year", $('#selectyear').val());


    jQuery.ajax({
        url: "importedparceldownload",
        global: false,
        data: oMyForm,
        dataType: 'text',
        processData: false,
        contentType: false,
        type: 'POST',

        success: function(response) {
            console.log('inside success');

            $("#staticreportdownloadanchortag").attr("href","downloadPdfFile?filename=" + response);

            document.getElementById("staticreportdownloadanchortag").click();

        }
    });

}



function popup(url) {


    var oMyForm = new FormData();
    oMyForm.append("month", $('#selectmonth').val());
    oMyForm.append("year", $('#selectyear').val());

    oMyForm.append("tab", url);


    jQuery.ajax({
        url: 'importpopup',
        global: false,
        data: oMyForm,
        dataType: 'text',
        processData: false,
        contentType: false,
        type: 'POST',

        success: function(data) {
            console.log('popup replaced inside success');

            $("#myModal").replaceWith(data);
            $("#myModal").modal('show');

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




