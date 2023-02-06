<%-- 
    Document   : index
    Created on : 14-Jan-2022, 06:50:35
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Daily Data Tracking App</title>
        <link rel="shortcut icon" href="dumisha/dumisha.png" style="height: 20px;padding: 0px; margin: 0px;"/>
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
      width: 40%;
   }

   @media screen and (min-width: 700px) and (max-width: 1500px) {
      .login-box {
         width: 55%;
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
<div class="login-box">
    <div class="login-logo">
        <!--<div class="title" style="color:green;">s</div>-->
  
</div><br>
  <!-- /.login-logo -->
  <div class="card" style="width: 100%;">
      <div class="card-body login-card-body" style="margin-top: 3%; margin-bottom: 1%;" >
        <div style=" font-weight: bold;">Daily Data Tracking System</div><br>
     <form action="login" method="post">
        <div class="input-group mb-3">
          <input type="text" class="form-control" id="username" name="username" maxlength="50" placeholder="Email/Phone Number"  required="true">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-user"></span>
            </div>
          </div>
        </div>
         <br>
        <div class="input-group mb-3">
        <input type="password" class="form-control" id="password" name="password" maxlength="50" placeholder="Password" required="true">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
         
         <div class="form-group" style="width: 100%;">
	<button type="submit" class="btn btn-primary btn-block">Login </button>
	</div>
        
      </form>
       </div>
    <!-- /.login-card-body -->
    
    <div class="form-group login-options" style="font-size: 14px; margin-left: 2%;">
                                                            
                                                            
                                                            <b>New User?</b><span><a href="register.jsp" style=""><b> Register Here</b></a></span>
                                                            <br>
                                                            <br>
                                                            <div> Click
                                                            <span><a href="stock_index.jsp" ><b >HERE</b></a> to log in stock data</span>
                                                            <br style="margin-top:1%;">
                                                            </div>
                                                            <br><!--
                                                            <b>Email not Confirmed?</b><span><a href="conf_email.jsp" style=""><b>Confirm Now</b></a></span>
                                                            <br style="margin-top:3%;">-->
                                                      </div>
    
  </div>
</div>
<!-- /.login-box -->
                                
					<!-- Footer -->
					<div class="footer">
						 <%@include file="menu/footer.jsp"%>
					</div>
					<!-- /footer -->
                                        <%if(session.getAttribute("login")!=null){
                                        String mess =session.getAttribute("login").toString();
                                        %>
                                        <script type="text/javascript">
                                           $.jGrowl('<%=mess%>', {
                                                position: 'center',
                                                header: 'User Login Error.',
                                                theme: 'bg-danger'
                                            });  
                                            </script>
                                            <% session.removeAttribute("login");}%>
                                     
                                            
                                        <%if(session.getAttribute("message")!=null){
                                        String mess =session.getAttribute("message").toString();
                                        %>
                                        <script type="text/javascript">
                                           $.jGrowl('<%=mess%>', {
                                                position: 'center',
                                                header: 'Mail Verification Status',
                                                theme: 'bg-success'
                                                
                                            });  
                                            </script>
                                            <% session.removeAttribute("message");}%>
			
</body>
</html>
