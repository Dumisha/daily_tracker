<%-- 
    Document   : performance_report
    Created on : 6 Mar 2024, 10:15:43
    Author     : mwamb
--%>
<%-- 
    Document   : uploading_performance_data
    Created on : 5 Mar 2024, 12:30:00
    Author     : mwamb
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Performance Analysis Data</title>
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
    margin-right: 16px;
} 

    </style>   
    
    <style type="text/css">
    div.jGrowl-notification {
    font-size: 16px;
    }
    </style>

</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <%@include file="menu/top.jsp"%>
    <%@include file="menu/menu.jsp"%>
              <%if(session.getAttribute("settings")!=null){
      if(session.getAttribute("settings").toString().equals("1")){%>          
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
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">Program performance Report Generation Module</h3>
              </div>
                         <div class="card-body">        
                             <form action="GenerateReport" method="get" >

                                 <div class="flex-container">
                                     <div class="flex-child">
                                         <table class="table" style="max-width: 95%;">
                                             <tr>
                                           

                                             </tr>  
                                             
                                         </table>     
                                         
                                     </div>    
                                 </div>
                                 
                                   <div class="flex-container" id="county_label">
                     <div class="flex-child">
                    Start Period:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                      <select class="form-control select2" id="start_period" name="start_period" data-placeholder="Select dataset" required="true" autocomplete="off"> 
                          <option value=""> Choose start Period</option>
                          <option value=""></option>
                         
                      </select>
                    </div>
                    </div>
                                 
                                 
                     <div class="flex-container" id="county_label">
                     <div class="flex-child">
                    End Period: 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                      <select class="form-control select2" id="end_period" name="end_period" data-placeholder="Select dataset" required="true" autocomplete="off"> 
                         <option value=""> Choose end Period</option>
                          <option value=""></option>
                      </select>
                    </div>
                    </div>
                              
                              <div class="flex-container" id="indicator_label">
                       <button type="submit" id="submit" class="btn btn-primary btn-block">Generate</button>      
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
        <%if(session.getAttribute("report")!=null){
                                        String mess =session.getAttribute("report").toString();
                                        %>
                                        <script type="text/javascript">
                                           $.jGrowl('<b><%=mess%></b>', {
                                                position: 'center',
                                                header: '<b>Excel Data Upload Status</b>',
                                                theme: 'bg-success',
                                                life:15000,
                                                easing: "swing",
                                                beforeOpen: function(e,m,o){
                                                    $(e).width( "450px" );
                                                }
                                                });  
                                            </script>
                                            <% session.removeAttribute("report");}%>                                 
    
                                            
  
 <script>
   $(document).ready(function() { 
   var periods = load_periods();

var period_label="<option value=\"\">Choose Period</option>";

for(var i=0;i<periods.length;i++){
  period_label+="<option value=\""+periods[i].id+"\">"+periods[i].name+"</option>"  ;
    
}

$("#start_period").html(period_label);    
$("#end_period").html(period_label);    
       
   });
    </script>
    
    <script>
    
       function load_periods(){
           var data=[];
    $.ajax({
        url:'load_periods',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info;
  }
        
   }) ;
   return data;   
   }
    </script>
</body>

</html>
