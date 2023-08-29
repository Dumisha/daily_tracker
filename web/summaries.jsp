<%-- 
    Document   : summaries
    Created on : 22-Feb-2022, 17:39:52
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Summaries</title>
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
            <%if(session.getAttribute("reports")!=null){
      if(session.getAttribute("reports").toString().equals("1")){%>         
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
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">Summaries Generation Module</h3>
              </div>
                         <div class="card-body">        
                             <form action="summaries">

                    <div class="flex-container" id="indicator_label">
                     <div class="flex-child">
                    Indicators:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                         <select class="form-control select2" id="indicator" data-placeholder="Select Indicators" name="indicator"  required="true" multiple="true"> </select>
                    </div>
                    </div> 

                    <div class="flex-container" id="start_label">
                     <div class="flex-child">
                    Start Date: 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="text" class="form-control datepicker" id="start_date" name="start_date"  required="true" placeholder="Start Date" autocomplete="off">
                    </div>
                    </div>                     
                    <div class="flex-container" id="end_label">
                     <div class="flex-child">
                    End Date:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="text" class="form-control datepicker" id="end_date" name="end_date"  required="true" placeholder="End Date" autocomplete="off">
                    </div>
                    </div>                     
                       
                              <div class="flex-container" id="indicator_label">
                       <button type="submit" id="submit" class="btn btn-primary btn-block">Generate Summaries</button>      
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
       
var indicators = load_indicators();

var indicator_label="";

for(var i=0;i<indicators.length;i++){
    if(indicators[i].is_active===1 && indicators[i].has_summary_report===1){
  indicator_label+="<option value=\""+indicators[i].id+"\">"+indicators[i].name+"</option>"  ;
    }
}

$("#indicator").html(indicator_label);

}); 
    </script>
<script>

    </script>
    
  <script>  
   function load_user_levels(){ 
       var user_levels=[];
    $.ajax({
        url:'load_user_levels',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         user_levels = info.data;
  }
        
   }) ;
   return user_levels;
   }
   
      function load_status(){ 
       var status=[];
    $.ajax({
        url:'load_status',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         status = info.data;
  }
        
   }) ;
   return status;
   }
   
      function load_counties(){ 
       var data=[];
    $.ajax({
        url:'load_counties',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info.data;
  }
        
   }) ;
   return data;
   }
   
   
      function load_sub_counties(){ 
       var data=[];
    $.ajax({
        url:'load_sub_counties',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info.data;
  }
        
   }) ;
   return data;
   }
   
   
      function load_facilities(){ 
       var data=[];
    $.ajax({
        url:'load_facilities',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info.data;
  }
        
   }) ;
   return data;
   }
   
   function load_program_areas(){
           var data=[];
    $.ajax({
        url:'load_sections',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info.data;
  }
        
   }) ;
   return data;   
   }
   function load_indicators(){
           var data=[];
    $.ajax({
        url:'load_indicators',
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
    
    
    <script>
        $("#county").change(function(){
         var county_id = ","+$("#county").val()+",";
        var sub_counties = load_sub_counties();
        var sub_counties_label = "";
         
        for(var i=0;i<sub_counties.length;i++){
            if(county_id.includes(","+sub_counties[i].county_id+",")){
             sub_counties_label+="<option value=\""+sub_counties[i].id+"\">"+sub_counties[i].name+"</option>";
      }
  }
      
      $("#sub_county").html(sub_counties_label);
        
        });
        
     // change sub county   
        
        $("#sub_county").change(function(){
         var sub_county_id = ","+$("#sub_county").val()+",";
        var facilities = load_facilities();
        var facilities_label = "";
         
        for(var i=0;i<facilities.length;i++){
            if(sub_county_id.includes(","+facilities[i].sub_county_id+",")){
             facilities_label+="<option value=\""+facilities[i].id+"\">"+facilities[i].name+"</option>";
      }
  }
      
      $("#facility").html(facilities_label);
        
        });
        </script> 
 <script>
  $(function () {
    $('.select2').select2();
  });
  
  </script>
</html>
