<%-- 
    Document   : user_profile
    Created on : 23-Feb-2022, 12:44:33
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Raw Data</title>
   <link rel="shortcut icon" href="dumisha/dumisha.png" style="height: 20px;padding: 0px; margin: 0px;"/>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <link rel="stylesheet" href="plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
  <link rel="stylesheet" href="dist/css/adminlte.min.css">  
  <link rel="stylesheet" href="jGrowl/jquery.jgrowl.css" type="text/css"/>
    <!-- Select2 -->
  <link rel="stylesheet" href="plugins/select2/css/select2.min.css">
  <link rel="stylesheet" href="plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
  <link rel="stylesheet" href="css/core.css">
                
<script src="plugins/jquery/jquery.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="dist/js/adminlte.min.js"></script>
<script type="text/javascript" src="jGrowl/jquery.jgrowl.js"></script>

        <link href="dataTables/datatables.css" rel="stylesheet">
        <link href="dataTables/Buttons-1.5.1/css/buttons.dataTables.min.css" rel="stylesheet">
        <link href="dataTables/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
        
        
        <script src="dataTables/datatables.min.js"></script>
        <script src="dataTables/pdfmake-0.1.32/pdfmake.min.js"></script>
        <script src="dataTables/Buttons-1.5.1/js/dataTables.buttons.min.js"></script>
        <script src="dataTables/Buttons-1.5.1/js/buttons.flash.min.js"></script>
        <script src="dataTables/pdfmake-0.1.32/vfs_fonts.js"></script>
        <script src="dataTables/JSZip-2.5.0/jszip.min.js"></script>
        <script src="dataTables/Buttons-1.5.1/js/buttons.html5.min.js"></script>
        <script src="dataTables/Buttons-1.5.1/js/buttons.print.min.js"></script>
            <link href="tables/styles.css" rel="stylesheet"> 
            
                   
            <script src="ui/jquery-ui.js"></script>
            <link href="ui/jquery-ui.css" rel="stylesheet">
            <link href="ui/jquery-ui.theme.css" rel="stylesheet">
            
            <script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="plugins/select2/js/select2.full.min.js"></script>
             <!-- Select2 -->
             <link rel="stylesheet" href="/plugins/select2/css/select2.min.css">
             <link rel="stylesheet" href="/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
         
      <style>
   .flex-container {
    display: flex;
    margin-top: 10px;
    /*border: 1px dotted gray;*/
}

.flex-child {
    flex: 1;
    border: 2px;
}  

.flex-child:first-child {
    margin-right: 20px;
} 

    </style>     
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <%@include file="menu/top.jsp"%>
    <%@include file="menu/menu.jsp"%>
            <%if(session.getAttribute("user_profile")!=null){
      if(session.getAttribute("user_profile").toString().equals("1")){%>         
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
		 <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
           
<br>
            <div class="card">
              <div class="card-header">
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">Raw Data Generation Module</h3>
              </div>
                         <div class="card-body">        
                             <form action="update_profile">

                    <div class="flex-container" id="start_label">
                     <div class="flex-child">
                    Phone: 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="text" class="form-control" id="phone" name="phone" value="<%if(session.getAttribute("phone")!=null){out.println(session.getAttribute("phone"));}%>"  required="true" placeholder="Phone" autocomplete="off">
                    </div>
                    </div>                     
                    <div class="flex-container" id="end_label">
                     <div class="flex-child">
                   Email:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="text" class="form-control" id="email" name="email" value="<%if(session.getAttribute("email")!=null){out.println(session.getAttribute("email"));}%>"  required="true" placeholder="Email Address" autocomplete="off">
                    </div>
                    </div>                     
                    <div class="flex-container" id="end_label">
                     <div class="flex-child">
                   Password:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="text" class="form-control" id="pass1" name="pass1" oninput="checkPasswords()"  required="true" placeholder="Password" autocomplete="off">
                    </div>
                    </div>                     
                    <div class="flex-container" id="end_label">
                     <div class="flex-child">
                   Repeat-password:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="text" class="form-control" id="pass2" name="pass2" oninput="checkPasswords()"  required="true" placeholder="Repeat password" autocomplete="off">
                    </div>
                    </div>                     
                       
                              <div class="flex-container" id="indicator_label">
                       <button type="submit" id="submit" class="btn btn-primary btn-block">Update Profile</button>      
                         </div> 
          
</form>
                         </div>    
                   
                </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
    </div>
       
  
             <%}
      else{%>
     
      <div style="color: red; font-weight: bolder; font-size: 17px; text-align: center; margin-top: 10%;  margin-bottom: 10%;">
      User not allowed to access this module    
          </div>
      
     <% }} else {%>
  
      <div style="color: red; font-weight: bolder; font-size: 17px; text-align: center; margin-top: 10%;  margin-bottom: 10%;">
      Unknown User trying to access Module. Login and try again  
          </div>
     
     <%}%>  
     
     
					<!-- Footer -->
                                        <div class="footer text-muted">
                                          <%@include file="menu/footer.jsp"%>
					</div>
					<!-- /footer -->

				</div>
                                    
</body>

        <%if(session.getAttribute("update_profile")!=null){
        String mess =session.getAttribute("update_profile").toString();
        %>
        <script type="text/javascript">
           $.jGrowl('<%=mess%>', {
                position: 'center',
                header: 'Account Update',
                theme: 'bg-info'

            });  
            </script>
            <% session.removeAttribute("update_profile");}%>


            <script rel="stylesheet" href="plugins/select2/js/select2.full.min.js"></script>
            
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
