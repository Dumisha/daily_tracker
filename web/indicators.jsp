<%-- 
    Document   : indicators
    Created on : 03-Feb-2022, 09:53:52
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Manage Indicators</title>
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
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">Indicator Management Module</h3>
              </div>
                         <div class="card-body"> 
                             <div style="margin-bottom: 5px;">
                                 <button data-toggle="modal" data-target="#modal-default" class="btn btn-success" id="add_new"> Add New Indicator </button>
                             </div>
                             <table id="data_table" class="table table-bordered table-striped" style="width:100%; size: 12px;">
                           <thead>
                               <tr>
                                  <th>No.</th> 
                                  <th>Program Area</th> 
                                  <th>Name</th> 
                                  <th>Description</th> 
                                  <th>Indicator Type</th> 
                                  <th>Frequency</th> 
                                  <th>Is Active</th> 
                                  <th>Manage</th> 
                               </tr>
                            </thead>
                            <tbody>
                              
                            </tbody> 
                        </table>
                         </div>  
                
                <!---- modals ---->
                
                
                <div class="modal fade" id="modal-default">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title"><b id="title">Update Indicator Details</b></h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">

                <div class="flex-container">
                <div class="flex-child">
                    Program Area:
                    </div>
                
                    <div class="form-group has-feedback has-feedback-left flex-child">
                    <select class="form-control select2bs4" id="section" name="section"  required="true"> </select>
                    </div>
                    </div>
                <div class="flex-container">    
                <div class="flex-child">
                    Indicator:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control" minlength="2" maxlength="40" id="indicator_name" name="indicator_name" placeholder="Indicator Name"  required="false">
                    </div>
                    </div>

            <div class="flex-container">
                <div class="flex-child">
                    Description:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <textarea type="text" class="form-control" id="description" name="description" placeholder="Description"></textarea>
                    </div>
                    </div>
    <div class="flex-container">
        <div class="flex-child">
                    Indicator Type:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="county_level">
                    <select class="form-control select2bs4" id="indicator_type" name="indicator_type"  required="true"> </select>
                    </div>
                    </div>
                <div class="flex-container">
                    <div  class="flex-child">
                    Frequency:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="sub_county_level">
                    <select class="form-control select2bs4" id="frequency" name="frequency"  required="true"> </select>
                    </div>
                    </div>
                <div class="flex-container">
                    <div class="flex-child">
                    Status:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="facility_level">
                    <select class="form-control select2bs4" id="status" name="status"  required="true"> </select>
                    </div>
                    </div>
                
                <input type="hidden" id="indicator_id" name="indicator_id">
            </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary" id="update">Update</button>
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
                
                
                
                
                
                
                
                
                <!------- end of modals ------->
                
                
                   
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
                                           
                                        <%if(session.getAttribute("adduser")!=null){
                                        String mess =session.getAttribute("adduser").toString();
                                        %>
                                        <script type="text/javascript">
                                           $.jGrowl('<%=mess%>', {
                                                position: 'center',
                                                header: 'Register Information.',
                                                theme: 'bg-info'
                                            });  
                                            </script>
                                            <% session.removeAttribute("adduser");}%>
</body>

   
            <script rel="stylesheet" href="plugins/select2/js/select2.full.min.js"></script>

    <script>
   $(document).ready(function() {
//         $('.select2bs4').select2({  theme: 'bootstrap4' });
        load_data();
}); 
    </script>
<script>
      function load_data(){ 
          var sections = load_sections();
          var indicator_types = load_indicator_type();
          var frequencies = load_frequency();
          var status = load_status();
          
       $.ajax({
        url:'load_indicators',
        type:"get",
        dataType:"json",
        success:function(info){
       var dataSet=[];
         var data = info;
         var pos,section_id,id,name, description,indicator_type,frequency,is_active,section_label,indicator_type_label,frequency_label,status_label;
    for (var i=0; i<data.length;i++){
        var minSet = [];
                pos=(i+1);
                id = data[i].id;
                section_id = data[i].section_id;
                name = data[i].name;
                description = data[i].description;
                indicator_type = data[i].indicator_type;
                frequency = data[i].frequency;
                is_active = data[i].is_active;

            
            // get section name
            for(var j=0;j<sections.length;j++){
                if(section_id===sections[j].section_id){
                section_label= sections[j].name;   
                }
            }
            
               // get indicator types name
            for(var j=0;j<indicator_types.length;j++){
                if(indicator_type===indicator_types[j].id){
                indicator_type_label= indicator_types[j].name;   
                }
            }
               // get approved name
            for(var j=0;j<frequencies.length;j++){
                if(frequency===frequencies[j].id){
                frequency_label= frequencies[j].name;   
                }
            }
                
                
                // for status
                
                for(var j=0;j<status.length;j++){ 
                if(is_active===status[j].id){
                status_label= status[j].name;   
                }
            }
              
var action='<ul class="icons-list"><li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">'+
		'<i class="icon-menu9"></i></a><ul class="dropdown-menu dropdown-menu-right">'+
                '<li data-toggle="modal" data-target="#modal-default"><a onclick="manage_indic(\''+id+'\',\''+section_id+'\',\''+name+'\',\''+description+'\',\''+indicator_type+'\',\''+frequency+'\',\''+is_active+'\');"><i class="fas fa-user-cog"></i>Manage</a></li>'+
                '<li><a onclick="delete_indic('+id+');"><i class="fas fa-user-alt-slash"></i>Delete</a></li>';               

      
        action+='</ul>'+
		'</li>'+
		'</ul>';

                
       minSet.push(pos);  
       minSet.push(section_label);  
       minSet.push(name);  
       minSet.push(description);  
       minSet.push(indicator_type_label);  
       minSet.push(frequency_label);  
       minSet.push(status_label);
       minSet.push(action);
       
        dataSet.push(minSet);
        }
       
       console.log(dataSet);
       
        var table = $('#data_table').DataTable();
        table.destroy();
        
        
        table = $('#data_table').dataTable({
            data: dataSet,
             dom: 'Bfltip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ],
            responsive: true,
            className:'',
            columnDefs: []
            
        });
        
        table.on( 'responsive-resize', function ( e, datatable, columns ) {
    var count = columns.reduce( function (a,b) {
        return b === false ? a+1 : a;
    }, 0 );
 
    console.log( count +' column(s) are hidden' );
} ); 
 
        }
  });   
    } 
    
    
           function manage_indic(id,section_id,name,description,indicator_type,frequency,is_active){
//               alert("called "+name+" => "+description);
//           var data = JSON.parse(indicator); 
           $("#title").html("Update "+name+" Details");
             $("#update").html("Update Indicator");
             
          var sections = load_sections();
          var indicator_types = load_indicator_type();
          var frequencies = load_frequency();
          var status = load_status();
          
         var section_label = "<option value=\"\">Choose Program Area</option>";
         var indicator_type_label = "<option value=\"\">Choose Indicator Type</option>";
         var frequency_label = "<option value=\"\">Choose Reporting Frequency</option>";
         var status_label = "<option value=\"\">Choose Indicator Active?</option>";
         
         // for sections
        for(var i=0;i<sections.length;i++){
        if(parseInt(section_id)===sections[i].section_id){
            section_label+="<option value=\""+sections[i].section_id+"\" selected>"+sections[i].name+"</option>";
        }
        else{
           section_label+="<option value=\""+sections[i].section_id+"\">"+sections[i].name+"</option>";  
        }
        }
        
        // for indicator types
        for(var i=0;i<indicator_types.length;i++){
        if(parseInt(indicator_type)===indicator_types[i].id){
            indicator_type_label+="<option value=\""+indicator_types[i].id+"\" selected>"+indicator_types[i].name+"</option>";
        }
        else{
            indicator_type_label+="<option value=\""+indicator_types[i].id+"\" >"+indicator_types[i].name+"</option>";
        }
        }
        
        // for frequency
        for(var i=0;i<frequencies.length;i++){
        if(parseInt(frequency)===frequencies[i].id){
            frequency_label+="<option value=\""+frequencies[i].id+"\" selected>"+frequencies[i].name+"</option>";
        }
        else{
            frequency_label+="<option value=\""+frequencies[i].id+"\" >"+frequencies[i].name+"</option>";
        }
        }
        
        // for status
        for(var i=0;i<status.length;i++){
        if(parseInt(is_active)===status[i].id){
            status_label+="<option value=\""+status[i].id+"\" selected>"+status[i].name+"</option>";
        }
        else{
            status_label+="<option value=\""+status[i].id+"\" >"+status[i].name+"</option>";
        }
        }
        
                   
           $("#indicator_id").val(id);
           $("#indicator_name").val(name);
           $("#description").val(description); 
        
        $("#section").html(section_label);
        $("#indicator_type").html(indicator_type_label);
        $("#frequency").html(frequency_label);
        $("#status").html(status_label);
         
       } 
  
  $("#update").click(function(){
  
  });
  
  
  
  
    </script>
    
    <script>
        $(document).ready(function(){
         $("#add_new").click(function(){ 
             $("#title").html("Add New Indicator");
             $("#update").html("Add Indicator");
             
               clear_entries(); 
               load_new_form();
               
         });
        
        });
        </script>
  <script>  
     function clear_entries(){
        $("#indicator_id").val("");
        $("#indicator_name").val("");
        $("#description").val(""); 
        $("#section").html("");
        $("#indicator_type").html("");
        $("#frequency").html("");
        $("#status").html("");  
     }     
     
    function load_new_form(){
                     
          var sections = load_sections();
          var indicator_types = load_indicator_type();
          var frequencies = load_frequency();
          var status = load_status(); 
          
          
          var section_label = "<option value=\"\">Choose Program Area</option>";
         var indicator_type_label = "<option value=\"\">Choose Indicator Type</option>";
         var frequency_label = "<option value=\"\">Choose Reporting Frequency</option>";
         var status_label = "<option value=\"\">Choose Indicator Active?</option>";
         
         // for sections
        for(var i=0;i<sections.length;i++){
           section_label+="<option value=\""+sections[i].section_id+"\">"+sections[i].name+"</option>";  
        }
        
        // for indicator types
        for(var i=0;i<indicator_types.length;i++){
            indicator_type_label+="<option value=\""+indicator_types[i].id+"\" >"+indicator_types[i].name+"</option>";
        }
        
        // for frequency
        for(var i=0;i<status.length;i++){
            frequency_label+="<option value=\""+frequencies[i].id+"\" >"+frequencies[i].name+"</option>";
        }
        
        // for status
        for(var i=0;i<status.length;i++){
        status_label+="<option value=\""+status[i].id+"\" >"+status[i].name+"</option>";
        }
        
        
        $("#section").html(section_label);
        $("#indicator_type").html(indicator_type_label);
        $("#frequency").html(frequency_label);
        $("#status").html(status_label);
          
    }
    
   function load_sections(){ 
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
          
   function load_frequency(){ 
       var data=[];
    $.ajax({
        url:'load_frequency',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info;
  }
        
   }) ;
   return data;
   }
   
     function load_indicator_type(){ 
       var data=[];
    $.ajax({
        url:'load_indicator_type',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info;
  }
        
   }) ;
   return data;
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
    </script>
    
    
    <script>
        $(document).ready(function(){
$("#update").click(function(){
//    alert("clicked");
   var id = $("#indicator_id").val();
   var name =  $("#indicator_name").val();
   var description =   $("#description").val(); 
   var section_id =  $("#section").val();
   var indicator_type =   $("#indicator_type").val();
   var frequency =   $("#frequency").val();
   var status =  $("#status").val();
//  alert("reached here");
        var errors = "", error_count=0;
    if(name.length<2){
        error_count++;
        errors+=""+error_count+". Enter indicator name <br>";
    }  
    if(section_id.length===0){
        error_count++;
        errors+=""+error_count+". Select program area <br>";
    }  
    if(indicator_type.length===0){
        error_count++;
        errors+=""+error_count+". Select indicator type <br>";
    }  
    if(frequency.length===0){
        error_count++;
        errors+=""+error_count+". Select indicator reporting frequency <br>";
    }  
    if(status.length===0){
        error_count++;
        errors+=""+error_count+". Choose indicator status <br>";
    } 
    
//    alert(error_count);
    if(error_count<0){
        //exit
         $.jGrowl(errors, {
                         position: 'top-center',
                         header: '<b>Error</b>',
                         theme: 'bg-danger'
                    });
                     alert(error_count+" "+errors);
    }
    else{
        var form_data = {"id":id,"name":name,"section_id":section_id,"description":description,"indicator_type":indicator_type,"frequency":frequency,"status":status};
        save_indicator(form_data);
    }
    
});
        });

function save_indicator(data){
     var theme="";
        var url = "save_indicator";
    $.post(url,data,function(output) {
                     var code = JSON.parse(output).code;
                     var message = JSON.parse(output).message;
                     var header="";
                     if(code===1){
                         theme = "bg-success";
                         header="<b>Success</b>";
                         load_data(); 
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

       </script> 
</html>
