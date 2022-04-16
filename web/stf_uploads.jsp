<%-- 
    Document   : stf_uploads
    Created on : 24-Mar-2022, 16:21:58
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Upload STFs Data</title>
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
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">STF Data Upload Module</h3>
              </div>
                         <div class="card-body">        
                             <form action="stf_upload" method="post"  enctype="multipart/form-data">

                                 <div class="flex-container">
                                     <div class="flex-child">
                                         <table class="table" style="max-width: 95%;">
                                             <tr>
                                           
                                                 <th><b>(1) <br><br> System ID</b></th>
                                                    <th><b>(2) <br><br> Batch</b></th>
                                                    <th><b>(3) <br><br> Patient CCC No</b></th>
                                                    <th><b>(4) <br><br> Lab Tested In</b></th>
                                                    <th><b>(5) <br><br> County</b></th>
                                                    <th><b>(6) <br><br> Sub-County</b></th>
                                                    <th><b>(7) <br><br> Partner</b></th>
                                                    <th><b>(8) <br><br> Facilty</b></th>
                                                    <th><b>(9) <br><br> Facility Code</b></th>
                                                    <th><b>(10) <br><br> Gender</b></th>
                                                    <th><b>(11) <br><br> DOB</b></th>
                                                    <th><b>(12) <br><br> Age</b></th>
                                                    <th><b>(13) <br><br> Sample Type</b></th>
                                                    <th><b>(14) <br><br> Date Collected</b></th>
                                                    <th><b>(15) <br><br> Justification</b></th>
                                                    <th><b>(16) <br><br> Date Received</b></th>
                                                    <th><b>(17) <br><br> Date Tested</b></th>
                                                    <th><b>(18) <br><br> Date Dispatched</b></th>
                                                    <th><b>(19) <br><br> ART Initiation Date</b></th>
                                                    <th><b>(20) <br><br> Received Status</b></th>
                                                    <th><b>(21) <br><br> Regimen</b></th>
                                                    <th><b>(22) <br><br> Regimen Line</b></th>
                                                    <th><b>(23) <br><br> PMTCT</b></th>
                                                    <th><b>(24) <br><br> Result</b></th>



                                             </tr>  
                                             
                                         </table>     
                                         
                                     </div>    
                                 </div>
                                 
                    <div class="flex-container">
                     <div class="flex-child">
                   Browse File (.xlsx) format: 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="file" class="form-control datepicker" id="excel" name="excel" required="true" placeholder="Upload Data (.xlsx) format only"
                         accept= "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"      
                          >
                    </div>
                    </div>                     
                       
                              <div class="flex-container" id="indicator_label">
                       <button type="submit" id="submit" class="btn btn-primary btn-block">Upload</button>      
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
                              
					<!-- Footer -->
                                        <div class="footer text-muted">
                                          <%@include file="menu/footer.jsp"%>
					</div>
					<!-- /footer -->

				</div>
                                        
                                        
        <%if(session.getAttribute("message")!=null){
                                        String mess =session.getAttribute("message").toString();
                                        %>
                                        <script type="text/javascript">
                                           $.jGrowl('<b><%=mess%></b>', {
                                                position: 'center',
                                                header: 'Excel Data Upload Status',
                                                theme: 'bg-info'
                                                
                                            });  
                                            </script>
                                            <% session.removeAttribute("message");}%>                                 
                                 
</body>

            <script rel="stylesheet" href="plugins/select2/js/select2.full.min.js"></script>

    <script>
   $(document).ready(function() { 
$("#start_date").datepicker({
      changeYear: true,
      changeMonth: true,
      dateFormat: 'yy-mm-dd',
      maxDate: 0
  });
  
$("#end_date").datepicker({
      changeYear: true,
      changeMonth: true,
      dateFormat: 'yy-mm-dd',
//      minDate: -10y,
      maxDate: 0
  });
      
}); 
    </script>
<script>

    </script>
    
  <script>  
 
    </script>
        
        <script>
  $(function () {
    $('.select2').select2();
  });
  
  </script>
</html>
