<%-- 
    Document   : index
    Created on : 12-Apr-2020, 10:13:55
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>User Registration</title>
        <link rel="shortcut icon" href="assets/images/faces/faceslogo.PNG" style="height: 20px;padding: 0px; margin: 0px;"/>
	<!-- Global stylesheets -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <link rel="stylesheet" href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  
<link href="css/custom.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="jGrowl/jquery.jgrowl.css" type="text/css"/>
  <!-- jQuery -->
<script type="text/javascript" src="js/core/libraries/jquery.min.js"></script>
<script type="text/javascript" src="js/core/libraries/bootstrap.min.js"></script>
<script src="dist/js/adminlte.min.js"></script>
<script type="text/javascript" src="jGrowl/jquery.jgrowl.js"></script>

<style>
 .login-box {
      width: 60%;
   }

   @media screen and (min-width: 700px) and (max-width: 1500px) {
      .login-box {
         width: 75%;
         overflow: hidden;
         margin: 0 -150px 0 -150px;
      }
   }  
   
   
      @media screen and (max-width: 700px) {
      .login-box {
         width: 95%;
         overflow: hidden;
         margin: 0 -150px 0 -150px;
      }
   }
    
    
    </style>
</head>

<body class="hold-transition login-page">
<div class="login-box" style="margin-top: 5%;">
    <div class="login-logo">
     <div class="title">Daily Data Tracking App</div>
  
</div>
  <!-- /.login-logo -->
  <div class="card" style="width: 100%;margin-top: 5%;">
    <div class="card-body login-card-body" >

					<!-- Advanced login -->
                                        <form action="#" style="margin-top: 0%;">
                                            
                                            <div class="panel panel-body login-form" >
							<div class="text-center">
                                                            <div class="title">New User Registration</div>
                                                         </div>
                                                                <br>
                                                                <div>First Name<b style="color:red;"> *</b></div>
							<div class="form-group has-feedback has-feedback-left">
                                                            <input type="text" class="form-control" minlength="2" maxlength="40" id="fname" name="fname" placeholder="First Name"  required="false">
							</div>
                                                                
                                                        <div>Middle Name<b style="color:red;"></b></div>
							<div class="form-group has-feedback has-feedback-left">
                                                            <input type="text" class="form-control" id="mname" name="mname" placeholder="Middle Name">
							</div>
                                                        
                                                         <div>Sur/Last Name<b style="color:red;"> *</b></div>
							<div class="form-group has-feedback has-feedback-left">
                                                            <input type="text" class="form-control" id="lname" name="lname" minlength="2" maxlength="40" placeholder="Last/Sur Name"  required="false">
							</div>
                                                         
                                                          <div>Email Address<b style="color:red;"> *</b></div>
							<div class="form-group has-feedback has-feedback-left">
                                                            <input type="email" class="form-control" minlength="17" maxlength="50" id="email" name="email" placeholder="Email Address"  required="false">
							</div>
                                                
                                                           <div>Phone Number<b style="color:red;"> *</b></div>
							<div class="form-group has-feedback has-feedback-left"> 
                                                          <input type="text" class="form-control"minlength="10" maxlength="10" id="phone" name="phone" maxlength="10" placeholder="Phone Number" onkeypress='return numbers(event)' required="false">
                                                         </div>

                                                            <div>Password<b style="color:red;"> *</b></div>
							<div class="form-group has-feedback has-feedback-left">
                                                            <input type="password" class="form-control" minlength="3" maxlength="20" oninput="checkPasswords()" id="pass1" name="pass1" placeholder="Password"  required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 5 or more characters">
							</div>
                                                
                                                             <div>Repeat Password<b style="color:red;"> *</b></div>
							<div class="form-group has-feedback has-feedback-left">
                                                            <input type="password" class="form-control" id="pass2" minlength="3" oninput="checkPasswords()" maxlength="20" name="pass2" placeholder="Repeat Password" required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 5 or more characters">
							</div>
                                                             
                                                           <div class="form-group" style="width: 100%;">
                                                            <button type="submit" id="submit" class="btn btn-primary btn-block">Register User </button>
                                                            </div>
                                           </div>
					</form>
                                         </div>
                                                     <div class="form-group login-options">
                                                            
                                                            <b>Already have an Account?</b><span> <a href="index.jsp"><b>Login Here</b></a></span>
                                                          <br style="margin-top:2px;">
                                                        
							</div>

      </div>
				<!-- /content area -->

			</div>
			<!-- /main content -->
					<!-- /advanced login -->

					<!-- Footer -->
					<div class="footer text-muted text-center">
						 <%@include file="menu/footer.jsp"%>
					</div>
		

        
         <%if(session.getAttribute("message")!=null){
                                        String mess =session.getAttribute("message").toString();
                                        %>
                                        <script type="text/javascript">
                                           $.jGrowl('<%=mess%>', {
                                                position: 'center',
                                                header: 'Account Creation Error',
                                                theme: 'bg-danger'
                                                
                                            });  
                                            </script>
                                            <% session.removeAttribute("message");}%>
</body>
<script>
    $(document).ready(function(){ 

$("#submit").click(function(){
  var fname,mname,lname,phone,email,pass1,pass2;
  fname = $("#fname").val();
  mname = $("#mname").val();
  lname = $("#lname").val();
  phone = $("#phone").val();
  email = $("#email").val();
  pass1 = $("#pass1").val();
  pass2 = $("#pass2").val();
  
  var errors=0,error_message="Correct below errors:<br>";
  
  if(fname.length<3){errors++; error_message+="Enter correct first name <br>";}
  if(lname.length<3){errors++; error_message+="Enter correct last name <br>";}
  if(phone.length!==10){errors++; error_message+="Enter correct phone number <br>";}
  if(pass1!==pass2){errors++; error_message+="Passwords do not Match. <br>";}
 
 if(errors>0){
  $.jGrowl(error_message, {
            position: 'center',
            header: 'Account Creation Error',
            theme: 'bg-danger'
      });     
 }
 
 else{
      var form_data = {"fname":fname,"mname":mname,"lname":lname,"phone":phone,"email":email,"pass1":pass1,"pass2":pass2};
     
            save_data(form_data);
 }
 
 
});
    });
    
    
     function save_data(data){
        var theme="";
        var url = "register_user";
    $.post(url,data , function(output) {
                     var code = JSON.parse(output).code;
                     var message = JSON.parse(output).message;
                     var header="";
                     if(code===1){
                         theme = "bg-success";
                         header="<b>Success</b>";
//                                       load_data(data.identifier); 
                     }
                     else{
                        theme = "bg-danger"; 
                        header="<b>Error</b>";
                     }

                     $.jGrowl('close');

                   $.jGrowl(message, {
                         position: 'top-center',
                         header: header,
                         theme: theme
                    }); 
                  });
                   } 
    
    
    </script>



   <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>
 <script type="text/javascript">
    
            function checkPasswords() {
                var password = document.getElementById('pass1');
                var conf_password = document.getElementById('pass2');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }
                
          
        
            }
    
    </script>
</html>
