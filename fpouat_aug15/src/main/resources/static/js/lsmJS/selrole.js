function chrole(e){
 var developerData = {};
	developerData['roleName'] = $('#selrole').val();
	var resObj = $.ajax({
		url: 'selrolenxt',
		data: JSON.stringify(developerData),
		dataType: "json",
		contentType: "application/json",
		type: "post",
		success: function(developerDatas) {
				window.location.href = "dash";
		},
		fail: function(rs, e) {
			alert("Error in Login");
		}
	});
	window.location.href = "dash";
}


$('.roletype').attr('onclick','ShowHideDiv(this)');
$('.roletype1').attr('onclick','ShowHideDiv(this)');
$('.roletype').trigger('click');

$(document).ready(function() {
$('.admin').attr('title','NO Admin. Roles Have Been Allotted to this SSOID');
$('.trans').attr('title','NO Transaction. Roles Have Been Allotted to this SSOID');
$('.misc').attr('title','NO MISC. Roles Have Been Allotted to this SSOID');
   $(".nav li.disabled").click(function() {
     return false;
   });

});

function ShowHideDiv(e) {
	var name = $(e).attr('name');
	
	if(name == 'admin'){
		$('#adminrole').css('display','block');
		$('#transactionrole').css('display','none');
		$('#miscelaneousrole').css('display','none');
		
	}else if(name == 'transaction'){
		$('#adminrole').css('display','none');
		$('#transactionrole').css('display','block');
		$('#miscelaneousrole').css('display','none');
		
	}else if(name == 'miscellaneous'){
		$('#adminrole').css('display','none');
		$('#transactionrole').css('display','none');
		$('#miscelaneousrole').css('display','block');
		
	}
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



/*$(document).ready(function() {
var admcou = $('#dispcoua').val();
for (var j = 0; j < admcou; j++)   
{ 
var opt =$('#admrole').find('tr').eq(j).find("input#admopt").val(); 
var role =$('#admrole').find('tr').eq(j).find("input#rolex").val(); 
 var txta =$('#admrole').find('tr').eq(j).find("input#atxta").val();
 var txtb =$('#admrole').find('tr').eq(j).find("input#atxtb").val();
var hrf="";
 if (opt==1)
  {  $('#admrole').find('tr').eq(j).find('td').eq(3).css('background-color','#8dd6ec');
     $('#admrole').find('tr').eq(j).find('td').eq(3).css('font-weight','bolder');
     $('#admrole').find('tr').eq(j).find('td').eq(3).css('font-size','20px');
     $('#admrole').find('tr').eq(j).css('height','50px');
      $('#admrole').find('tr').eq(j).find('td').eq(1).css('width','40px');
      $('#admrole').find('tr').eq(j).find('td').eq(1).css('height','40px');
      $('#admrole').find('tr').eq(j).find('td').eq(1).css('border', '6px solid darkblue');
       hrf='<a href="dash?role=' + role + '" style="text-decoration: none;">';}
 else
  {  $('#admrole').find('tr').eq(j).css('background-color','transparent'); 
    $('#admrole').find('tr').eq(j).css('font-weight','normal');}
var aimg = $('#admrole').find('tr').eq(j).find("input#admimg").val(); 
 var html1= '<a href="dash?role=' + role + '"><img src=' + aimg + '  width="40px" height="40px" >';
admdisp[j].innerHTML =html1;
if (opt==1)
      var txthtml=hrf + '<font color="darkblue">&nbsp;&nbsp;&nbsp;'+  txtb + ' - ' + txta + '</font>';
else
      var txthtml=hrf + '<font color="c3c3c3">'+ '  '+ txtb + ' - ' + txta + '</font>';
admtxt[j].innerHTML='  '+txthtml;
}
});

$(document).ready(function() {
var trancou = $('#dispcoub').val();
 for (var i = 0; i < trancou; i++)   
 {
 var opt =$('#disproleb').find('tr').eq(i).find("input#tranopt").val(); 
 var role =$('#disproleb').find('tr').eq(i).find("input#roley").val(); 
 var txta =$('#disproleb').find('tr').eq(i).find("input#txta").val();
 var txtb =$('#disproleb').find('tr').eq(i).find("input#txtb").val();
 var hrf="";
 if (opt==1)
   {  $('#disproleb').find('tr').eq(i).find('td').eq(3).css('background-color','#8dd6ec'); 
     $('#disproleb').find('tr').eq(i).find('td').eq(3).css('font-weight','bolder');
     $('#disproleb').find('tr').eq(i).find('td').eq(3).css('font-size','20px');
      $('#disproleb').find('tr').eq(i).find('td').eq(3).css('color','white');
      $('#disproleb').find('tr').eq(i).css('height','50px');
      $('#disproleb').find('tr').eq(i).find('td').eq(1).css('width','40px');
      $('#disproleb').find('tr').eq(i).find('td').eq(1).css('height','40px');
      $('#disproleb').find('tr').eq(i).find('td').eq(1).css('border', '6px solid darkblue');
      hrf='<a href="dash?role=' + role + '" style="text-decoration: none;">';}
 else
   {  $('#disproleb').find('tr').eq(i).css('background-color','transparent'); 
     $('#disproleb').find('tr').eq(i).css('font-weight','normal');
      }
 var timg =$('#disproleb').find('tr').eq(i).find("input#tranimg").val(); 
 var html2= hrf+'<img src=' + timg + '  width="40px" height="40px" >';
transdisp[i].innerHTML = html2;
if (opt==1)
    var txthtml=hrf + '<font color="darkblue">&nbsp;&nbsp;&nbsp;'+ txtb + ' - ' + txta + '</font>';
else
    var txthtml=hrf + '<font color="c3c3c3">'+ txtb + ' - ' + txta + '</font>';
trantxt[i].innerHTML='  '+txthtml;
}
});

$(document).ready(function() {
var misccou= $('#dispcouc').val();
 for (var k = 0; k < misccou; k++)   
{
var opt =$('#miscrole').find('tr').eq(k).find("input#miscopt").val(); 
var role =$('#miscrole').find('tr').eq(k).find("input#rolez").val(); 
var txta =$('#miscrole').find('tr').eq(k).find("input#mtxta").val();
var txtb =$('#miscrole').find('tr').eq(k).find("input#mtxtb").val();
 var hrf="";
 if (opt==1)
 {  
     $('#miscrole').find('tr').eq(k).find('td').eq(3).css('background-color','#8dd6ec'); 
     $('#miscrole').find('tr').eq(k).find('td').eq(3).css('font-weight','bolder');
     $('#miscrole').find('tr').eq(k).find('td').eq(3).css('font-size','20px');
      $('#miscrole').find('tr').eq(k).find('td').eq(3).css('color','white');
       $('#miscrole').find('tr').eq(k).css('height','50px');
      $('#miscrole').find('tr').eq(k).find('td').eq(1).css('width','40px');
      $('#miscrole').find('tr').eq(k).find('td').eq(1).css('height','40px');
      $('#miscrole').find('tr').eq(k).find('td').eq(1).css('border', '6px solid darkblue');
      hrf='<a href="dash?role=' + role + '" style="text-decoration: none;">';}
 else
   {  $('#miscrole').find('tr').eq(k).css('background-color','transparent'); 
     $('#miscrole').find('tr').eq(k).css('font-weight','normal');}
 var mimg = $('#miscrole').find('tr').eq(k).find("input#miscimg").val(); 
 var html3= hrf+'<a href="dash?role=' + role + '"><img src=' + mimg + '  width="40px" height="40px" >';
imgdispc[k].innerHTML = html3;
if (opt==1)
     var txthtml=hrf + '<font color="darkblue">&nbsp;&nbsp;&nbsp;'+  txtb + ' - ' + txta + '</font>';
else
      var txthtml=hrf + '<font color="c3c3c3">'+ txtb + ' - ' + txta + '</font>';
misctxt[k].innerHTML='  '+txthtml;
}
});*/


	
	function goBack() {
			window.location.replace(localStorage.getItem("prevUrl") != 'null' ? localStorage.getItem("prevUrl") : document.referrer);
		}
