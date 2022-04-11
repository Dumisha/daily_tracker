<%-- 
    Document   : ppmt
    Created on : 10-Mar-2022, 10:05:31
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>PPMT Tables</title>
   <link rel="shortcut icon" href="faces/faceslogo.PNG" style="height: 20px;padding: 0px; margin: 0px;"/>
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
table,tr, th, td {
  /*border: 1px solid black;*/
}
table {
  width: 100%;
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


            <form id="form">
            <div class="card">
              <div class="card-header">
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">PPMT (Project Progress Monitoring Tables)</h3>
              </div>
                         <div class="card-body"> 
                           
                <div class="flex-container" id="label_indicator">    
                <div class="flex-child">
                   Choose Indicator <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                      <select class="form-control select2" id="indicator" name="indicator"  required="true"> </select>
                    </div>
                    </div>                            
                                              
                <div class="flex-container" id="label_activity">    
                <div class="flex-child">
                   Choose Activity <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                      <select class="form-control select2" id="activity" name="activity"  required="true"> </select>
                    </div>
                    </div>                        
                    
                 
                                                             
                                              
            <div class="flex-container" id="label_activity_other">
                <div class="flex-child">
                  Activity Other <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <textarea type="text" class="form-control" autocomplete="off" id="activity_other" name="activity_other"  required="true"></textarea>
                    </div>
                    </div>
                                              
                                              
               <div class="flex-container" id="label_indicator">    
                <div class="flex-child">
                   Choose Region <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                      <select class="form-control select2" id="region" name="region"  required="true"> 
                      <option value=""></option>
                      <option value="1">County</option>
                      <option value="2">Sub County</option>
                      <option value="3">Health Facility</option>
                      
                      </select>
                    </div>
                    </div>
               
                <div class="flex-container" id="label_county">    
                <div class="flex-child">
                   Choose County <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <select class="form-control select2" id="county" name="county" required="true"> </select>
                    </div>
                    </div>
                <div class="flex-container" id="label_sub_county">    
                <div class="flex-child">
                   Choose Sub County <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                      <select class="form-control select2" id="sub_county" name="sub_county"  required="true"> </select>
                    </div>
                    </div>
                <div class="flex-container" id="label_facility">    
                <div class="flex-child">
                   Choose Facility <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                      <select class="form-control select2" id="facility" name="facility" required="true"> </select>
                    </div>
                    </div>
                                              
            <div class="flex-container" id="label_activity_date">
                <div class="flex-child">
                  Activity Date <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control datepicker" autocomplete="off" id="date" name="date"  required="true">
                    </div>
                    </div>
                                              
                                              
                <div class="flex-container" id="label_gender_grouped">
        <div class="flex-child">
                   Total Achieved by Gender <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="">
                   <table>
                       <tr>
                       <th> Male</th>
                       <th> Female</th>
                       </tr>
                       
                       <tr>
                       <th> 
                           <input type="number" min="0" class="form-control" value="" id="male" name="male">
                       </th>
                       <th> 
                         <input type="number" min="0" class="form-control" value="" id="female" name="female">
                      </th>
                       </tr>
                       
                       </table>
                    </div>
                    </div>
                                              
                <div class="flex-container" id="label_total">
        <div class="flex-child">
                   Total Achieved <font color="red">*</font> : 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="">
                     <input type="number" class="form-control" min="1" value="" id="total" name="total" required="true">
                    </div>
                    </div>
               
        
                                              <div class="flex-container"  id="">
              <button type="submit" id="submit" class="btn btn-primary btn-block">Save</button>
            </div>
            
                             
                         </div>  
                
                <!---- modals ---->
                </div>

</form>
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
</body>
  <script rel="stylesheet" href="plugins/select2/js/select2.full.min.js"></script>

  
  <script>
     $(document).ready(function(){
      $("form").submit(function(e){
        e.preventDefault();

        var form_data = {};
        $.each($('#form').serializeArray(), function(_, kv) {
          form_data[kv.name] = kv.value;
        });
   
    save_data(form_data);
    
    }); 
      
     }); 
     
     
        function save_data(data){
        var theme="";
        var url = "save_ppmt";
         $.post(url,data , function(output) {
                        output = JSON.parse(output);
                     var code = output.code;
                     var message = output.message;
                     var header="";
                     if(code===1){
                         theme = "bg-success";
                         header="<b>Success</b>";
                          // clear_entries(questions);
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
  
  
  
  
    <script>
   $(document).ready(function() {
    $("#label_county").hide();
    $("#label_sub_county").hide();
    $("#label_facility").hide();
//    $("#label_gender_grouped").hide();
//    $("#label_total").hide();
    $("#label_activity_other").hide();
    
    $('.select2').select2();
    
      $("#date").datepicker({
      changeYear: true,
      changeMonth: true,
      dateFormat: 'yy-mm-dd',
      minDate: -100,
      maxDate: 0
      });
      
      load_indicator();
      load_counties();
      load_sub_counties();
      load_facilities();
      
      
      $("#indicator").change(function(){
      var indic_id =  $("#indicator").val();
        load_activities(indic_id);
      });
      
      
      $("#activity").change(function(){ // show or hide activity other
          var activity = $("#activity").val();
          if(parseInt(activity)===0){ // 
             $("#label_activity_other").show(); 
             $("#activity_other").attr('required', 'required');
          }
          else{
            $("#label_activity_other").hide();  
            $("#activity_other").removeAttr('required');
          }
      });
      
      
      
      $("#region").change(function(){ // show or hide regions
          var region = $("#region").val();
          if(parseInt(region)===1){ // 
            $("#label_facility").hide();  
            $("#label_sub_county").hide();  
            $("#label_county").show();  
            $("#facility").removeAttr('required');
            $("#sub_county").removeAttr('required');
             $("#county").attr('required', 'required');
          }
          else if(parseInt(region)===2){ // 
            $("#label_facility").hide();  
            $("#label_sub_county").show();  
            $("#label_county").hide();  
            $("#facility").removeAttr('required');
            $("#county").removeAttr('required');
             $("#sub_county").attr('required', 'required');
          }
          else if(parseInt(region)===3){ // 
             $("#label_facility").show();  
            $("#label_sub_county").hide();  
            $("#label_county").hide();  
            $("#sub_county").removeAttr('required');
            $("#county").removeAttr('required');
             $("#facility").attr('required', 'required');
          }
          else{
            $("#label_facility").hide();  
            $("#label_sub_county").hide();  
            $("#label_county").hide();  
            $("#facility").removeAttr('required');
            $("#sub_county").removeAttr('required');
            $("#county").removeAttr('required');
          }
      });
      
      
     $("#female").keyup(function(){
       var female = $("#female").val();  
       var male = $("#male").val(); 
       
       if(male===""){male=0;}
       if(female===""){female=0;}
       var total = parseInt(female)+parseInt(male);
       
       $("#total").val(total);
       
     }); 
     $("#male").keyup(function(){
       var female = $("#female").val();  
       var male = $("#male").val(); 
       
        if(male===""){male=0;}
        if(female===""){female=0;}
       var total = parseInt(female)+parseInt(male);
       
       $("#total").val(total);
       
     }); 
     
     
     $("#total").keypress(function(){
       $("#female").val("");  
       $("#male").val("");  
     });
      
});
    </script>

    
    
    <script> 
     function load_indicator(){
                 var output = "<option value=\"\"></option>";
        
        $.ajax({
        url:'load_ppmt_indicators',
        type:"get",
        dataType:"json",
        success:function(data){
           var id,name,number,status;
           for(var i=0;i<data.length;i++){
               id = data[i].id;
               name = data[i].name;
               number = data[i].number;
               status = data[i].status;
                 
               
               output+="<option value=\""+id+"\">"+name+"</option>";
           }
           
           $("#indicator").html(output);
         }
  });
     }   
     function load_activities(indic_id){
        var output = "<option value=\"\"></option>";
//                alert("called");
        $.ajax({
        url:'load_ppmt_activities',
        type:"get",
        dataType:"json",
        async: false,
        success:function(data){
           var id,name,indicator_id,status;
           for(var i=0;i<data.length;i++){
               id = data[i].id;
               name = data[i].name;
               indicator_id = data[i].indicator_id;
               status = data[i].status;
                 
               if(parseInt(indicator_id)===parseInt(indic_id)){
               output+="<option value=\""+id+"\">"+name+"</option>";
           }
           }
           
           output+="<option value=\"0\">Other activity</option>";
           
           $("#activity").html(output);
         }
  });
     } 
     
     
          function load_counties(){
                 var output = "<option value=\"\"></option>";
        $.ajax({
        url:'load_counties',
        type:"get",
        dataType:"json",
        async: false,
        success:function(data){
            data=data.data;
           var id,name;
           for(var i=0;i<data.length;i++){
               id = data[i].id;
               name = data[i].name;
               
               output+="<option value=\""+id+"\">"+name+"</option>";
           }
           $("#county").html(output);
         }
  });
     } 
     
     
     
     
          function load_sub_counties(){
                 var output = "<option value=\"\"></option>";
        
        $.ajax({
        url:'load_sub_counties',
        type:"get",
        dataType:"json",
        async: false,
        success:function(data){
             data=data.data;
           var id,name;
           for(var i=0;i<data.length;i++){
               id = data[i].id;
               name = data[i].name;
                 
               
               output+="<option value=\""+id+"\">"+name+"</option>";
           }
           
           $("#sub_county").html(output);
         }
  });
     } 
     
     
     
     
          function load_facilities(){
                 var output = "<option value=\"\"></option>";
        
        $.ajax({
        url:'load_facilities',
        type:"get",
        dataType:"json",
        async: false,
        success:function(data){
            data=data.data;
           var id,name,mfl_code;
           for(var i=0;i<data.length;i++){
               id = data[i].id;
               name = data[i].name;
               mfl_code = data[i].mfl_code;
               
               output+="<option value=\""+id+"\">"+name+" ["+mfl_code+"]</option>";
           }
           
           $("#facility").html(output);
         }
  });
     } 
     
     
        </script>
    
    
    
</html>