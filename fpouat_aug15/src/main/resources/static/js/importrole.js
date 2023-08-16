var role=$('#role').val();
if (role == "PAO")
  {
     $(".ASS").show();  
     $("#PAOdisp").show();  
     $("#PAOqrydisp").show();  
     $("#PACdisp").hide();  
     $("#PINdisp").hide();  
     $("#PSUdisp").hide();  
     $(".ACL").hide();  
     $(".ASSrole").hide(); 
     $(".ACrole").hide(); 
     $(".ACroles").hide(); 
     $(".ASSroles").show();
     $(".SUProles").hide(); 
 //    $(".SUProle").show();
     $(".SUProle").hide();
     $(".SUPques").hide(); 
 //    $(".SUPque").show();
     $(".SUPque").show();
     $(".EXMroles").hide(); 
 //    $(".EXMrole").show(); 
     $(".EXMrole").hide();
     $(".DETroles").hide(); 
//     $(".DETrole").show(); 
     $(".DETrole").hide(); 
     $(".EXMcou").hide(); 
     $(".ASScou").show();
     $(".SUPcou").hide(); 
     $(".DETcou").hide(); 
//    $(".DETcoun").show(); 
     $(".DETcoun").hide(); 
     $(".ASScoun").hide(); 
//     $(".SUPcoun").show(); 
     $(".SUPcoun").hide(); 
     $(".ACcou").hide(); 
     $(".ACCOUn").hide(); 
     $(".EXM").attr("disabled", true);  
     $(".OOC").attr("disabled", true);  
     $(".DET").attr("disabled", true);  
  }
  else if (role == "PAC")
  {
	 $(".ACL").show(); 
     $("#PAOdisp").hide(); 
     $("#PAOqrydisp").hide();
     $("#PACdisp").show();   
     $("#PINdisp").hide();  
     $("#PSUdisp").hide();  
     $(".ASS").hide(); 
     $('.ASScou').hide();
     $(".ACcou").show(); 
     $(".ASSCOUn").hide(); 
     $(".ACrole").show();
     $(".ASSrole").hide(); 
     $(".ASSroles").hide();
     $(".ACroles").hide();
     $(".SUProles").hide(); 
 //    $(".SUProle").show();
     $(".SUProle").hide();
     $(".SUPques").hide(); 
 //    $(".SUPque").show();
     $(".SUPque").hide();
     $(".EXMroles").hide(); 
 //    $(".EXMrole").show(); 
      $(".EXMrole").hide(); 
     $(".DETroles").hide(); 
     $(".DETrole").show(); 
     $(".EXMcou").hide(); 
     $(".SUPcou").hide(); 
     $(".DETcou").hide(); 
  //   $(".DETcoun").show(); 
     $(".DETcoun").hide(); 
     $(".ACCOUn").hide(); 
 //  $(".SUPcoun").show(); 
     $(".SUPcoun").hide(); 
     $(".EXM").attr("disabled", true);  
     $(".OOC").attr("disabled", true);  
     $(".DET").attr("disabled", true);  
  }
  else if (role == "PSU")
    {
	 // $("#PAOdisp").show();  
     $("#PAOdisp").hide(); 
     $("#PAOqrydisp").hide();
     $("#PACdisp").hide();  
     $("#PINdisp").hide();  
     $("#PSUdisp").show(); 
     $(".ASS").attr("disabled", true);  
      $(".ACL").hide();  
     $(".SUProle").hide(); 
     $(".SUProles").show();
     $(".ACrole").hide(); 
     $(".ACroles").hide(); 
     $(".SUPque").hide(); 
     $(".SUPques").show();
     $(".EXMroles").hide();
 //    $(".EXMrole").show();  
     $(".EXMrole").hide();  
     $(".ASSroles").hide();
 //    $(".ASSrole").show();
     $(".ASSrole").hide();
     $(".DETroles").hide(); 
     $(".DETrole").show(); 
     $(".EXMcou").hide(); 
//     $(".ASScoun").show(); 
     $(".ASScoun").hide(); 
     $(".SUPcou").show(); 
     $(".SUPcoun").hide(); 
     $(".DETcou").hide();
//     $(".DETcoun").show();
     $(".DETcoun").hide(); 
//   $(".EXMcoun").show(); 
     $(".EXMcoun").hide(); 
     $(".ACcou").hide(); 
     $(".ACCOUn").hide(); 
     $(".ASScou").hide(); 
     $(".EXM").attr("disabled", true);  
     $(".OOC").attr("disabled", false);  
     $(".DET").attr("disabled", true);  
  }
  else if (role == "PIN")
    {
	//  $("#PAOdisp").show();  
	 $("#PAOdisp").hide(); 
     $("#PAOqrydisp").hide();
     $("#PACdisp").hide();  
     $("#PINdisp").show();  
     $("#PSUdisp").hide(); 
     $(".ASS").attr("disabled", true);  
     $(".EXM").attr("disabled", false);
      $(".ACL").hide();  
     $(".EXMrole").hide(); 
     $(".ACrole").hide(); 
     $(".ACroles").hide(); 
     $(".EXMroles").show();  
     $(".SUPques").hide(); 
   //  $(".SUPque").show();
     $(".SUPque").hide();
     $(".SUProles").hide(); 
   //  $(".SUProle").show(); 
     $(".SUProle").hide(); 
     $(".ASSroles").hide();
//   $(".ASSrole").show();
     $(".ASSrole").hide();
     $(".DETroles").hide(); 
//   $(".DETrole").show(); 
     $(".DETrole").hide(); 
     $(".EXMcou").show(); 
     $(".ASScou").hide(); 
     $(".SUPcou").hide(); 
     $(".DETcou").hide();
 //    $(".DETcoun").show(); 
 //    $(".ASScoun").show(); 
 //    $(".SUPcoun").show(); 
     $(".DETcoun").hide(); 
     $(".ASScoun").hide(); 
     $(".SUPcoun").hide(); 
     $(".EXMcoun").hide();
     $(".ACcou").hide(); 
     $(".ACCOUn").hide(); 
     $(".OOC").attr("disabled", true);  
     $(".DET").attr("disabled", true);  
  }
  else
  {
    window.location.href = "dash" ;
  }
  
  function chk(e)
  {
    if ( e == 1 || e == 8 )
      window.location.href = "import_list";
    else if ( e == 2)
      window.location.href = "import_exm_search";
    else if ( e == 3)
      window.location.href = "import_quick_ooc";
    else if ( e == 4)
      window.location.href = "import_det_search";
    else if ( e == 5)
      window.location.href = "import_det_ooc";
    else if ( e == 6)
      window.location.href = "import_list";
   
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