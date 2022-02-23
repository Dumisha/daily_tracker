<%-- 
    Document   : manage_pending_users
    Created on : 19-Jan-2022, 15:36:30
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Manage Users</title>
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
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">User management module</h3>
              </div>
                         <div class="card-body">        
                             <table id="data_table" class="table table-bordered table-striped" style="width:100%; size: 12px;">
                           <thead>
                               <tr>
                                  <th>No.</th> 
                                  <th>User level</th> 
                                  <th>Region</th> 
                                  <th>Full Name</th> 
                                  <th>Phone</th> 
                                  <th>Email</th> 
                                  <th>Approved</th> 
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
              <h4 class="modal-title">Manage User Information</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body" autocomplete="off">

                
                <div class="flex-container">
                     <div class="flex-child">
                    Full Name:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="fullname">
                    </div>
                    </div>
                
                    <div class="flex-container" id="user_label">
                     <div class="flex-child">
                    User Level:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                       <select class="form-control select2" id="user_level" data-placeholder="Select user level" name="user_level"  required="true"> </select>
                    </div>
                    </div>
                    <div class="flex-container" id="county_label">
                     <div class="flex-child">
                    Counties:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                      <select class="form-control select2" id="county" name="county" data-placeholder="Select county" required="true" multiple="true"> </select>
                    </div>
                    </div>
                    <div class="flex-container" id="sub_county_label">
                     <div class="flex-child">
                    Sub Counties:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <select class="form-control select2" id="sub_county" name="sub_county" data-placeholder="Select sub counties"  required="true" multiple="true"> </select>
                    </div>
                    </div>
                    <div class="flex-container" id="facility_label">
                     <div class="flex-child">
                    Facilities:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                         <select class="form-control select2" id="facility" data-placeholder="Select facilities" name="facility"  required="true" multiple="true"> </select>
                    </div>
                    </div>
                    
                    <div class="flex-container">
                     <div class="flex-child">
                    Approved:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <select class="form-control select2" id="approved" name="approved" data-placeholder="Select if approved" required="true"> </select>
                    </div>
                    </div>
                    <div class="flex-container">
                     <div class="flex-child">
                    Active:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                       <select class="form-control select2" id="status" name="status" data-placeholder="Select if active" required="true"> </select>
                    </div>
                    </div>
                
                
                    
                <div id="basic_info">
                    <div class="form-group has-feedback has-feedback-left">
                        <input type="text" class="form-control" minlength="2" maxlength="40" id="fname" name="fname" placeholder="First Name"  required="false">
                    </div>


                    <div class="form-group has-feedback has-feedback-left">
                        <input type="text" class="form-control" id="mname" name="mname" placeholder="Middle Name">
                    </div>


                    <div class="form-group has-feedback has-feedback-left">
                        <input type="text" class="form-control" id="lname" name="lname" minlength="2" maxlength="40" placeholder="Last/Sur Name"  required="false">
                    </div>


                    <div class="form-group has-feedback has-feedback-left">
                        <input type="email" class="form-control" minlength="17" maxlength="50" id="email" name="email" placeholder="Email Address"  required="false">
                    </div>


                    <div class="form-group has-feedback has-feedback-left"> 
                      <input type="text" class="form-control"minlength="10" maxlength="10" id="phone" name="phone" maxlength="10" placeholder="Phone Number" onkeypress='return numbers(event)' required="false">
                     </div>
                    </div>
                
                
                <input type="hidden" id="user" name="user">
            </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" id="update">Update Info</button>
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
          var user_levels = load_user_levels();
          var status = load_status();
       $.ajax({
        url:'load_users',
        type:"get",
        dataType:"json",
        success:function(info){
       var dataSet=[];
         var data = info.users;
         var pos,id,fullname, phone,email, user_level_id,approved,is_active,user_level_label,approved_label,status_label,coverage;
    for (var i=0; i<data.length;i++){
        user_level_label="";
        var minSet = [];
                pos=(i+1);
                id = data[i].id;
                fullname = data[i].fullname;
                phone = data[i].phone;
                email = data[i].email;
                user_level_id = data[i].user_level_id;
                approved = data[i].approved;
                is_active = data[i].is_active;
                coverage = data[i].coverage;
//alert(coverage);
//                coverage = "";
            
            // get user level name
            for(var j=0;j<user_levels.length;j++){
                if(user_level_id===user_levels[j].id){
                user_level_label= user_levels[j].name;   
                }
            }
            
               // get approved name
            for(var j=0;j<status.length;j++){
                if(approved===status[j].id){
                approved_label= status[j].name;   
                }
                
                if(is_active===status[j].id){
                status_label= status[j].name;   
                }
            }


var action='<ul class="icons-list"><li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">'+
		'<i class="icon-menu9"></i></a><ul class="dropdown-menu dropdown-menu-right">\n\
                <li data-toggle="modal" data-target="#modal-default"><a onclick="manage_user('+id+');"><i class="fas fa-user-cog"></i>Manage</a></li>\n\
                <li><a onclick="delete_user('+id+');"><i class="fas fa-user-alt-slash"></i>Delete</a></li>';

      
        action+='</ul>'+
		'</li>'+
		'</ul>';



                
       minSet.push(pos);  
       minSet.push(user_level_label);  
       minSet.push(coverage);  
       minSet.push(fullname);  
       minSet.push(phone);  
       minSet.push(email);  
       minSet.push(approved_label);
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
    
    
    
      function manage_user(pos){
          $("#basic_info").hide();
          
  var form_data = {"id":pos};
                var theme="";
                var url = "load_user_info";
                   $.post(url,form_data , function(output) {
                                    var code = JSON.parse(output).code;
                                    var message = JSON.parse(output).message;
                                    var header="";
                                    if(code===1){
                                        theme = "bg-success";
                                        header="<b>Success</b>";
                                       edit_user(output); 
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
       function edit_user(user){
           var user_info = JSON.parse(user);
          var user_levels = load_user_levels();
          var status = load_status();
          
          var counties = load_counties();
          var sub_counties = load_sub_counties();
          var facilities = load_facilities();
         
         var levels = "<option value=\"\">Choose User Level</option>";
         var approved_label = "<option value=\"\">Choose User Approved</option>";
         var status_label = "<option value=\"\">Choose User Active?</option>";
         
         var counties_label = "<option value=\"\">Choose County</option>";
         var sub_counties_label = "<option value=\"\">Choose Sub County</option>";
         var facilities_label = "<option value=\"\">Choose Facility</option>";
         
        for(var i=0;i<user_levels.length;i++){
//            alert(user_info.level);
        if(user_info.level===user_levels[i].id){
            levels+="<option value=\""+user_levels[i].id+"\" selected>"+user_levels[i].name+"</option>";
        }
        else{
           levels+="<option value=\""+user_levels[i].id+"\">"+user_levels[i].name+"</option>";  
        }
        }
        
        
        for(var i=0;i<status.length;i++){
//            alert(user_info.level);
        if(user_info.approved===status[i].id){
            approved_label+="<option value=\""+status[i].id+"\" selected>"+status[i].name+"</option>";
        }
        else{
            approved_label+="<option value=\""+status[i].id+"\" selected>"+status[i].name+"</option>";
        }
        
        if(user_info.status!==status[i].id){
            status_label+="<option value=\""+status[i].id+"\" selected>"+status[i].name+"</option>";
        }
       else{
            status_label+="<option value=\""+status[i].id+"\" selected>"+status[i].name+"</option>";
        }
        }
        
        // load counties
                for(var i=0;i<counties.length;i++){
                 counties_label+="<option value=\""+counties[i].id+"\">"+counties[i].name+"</option>";
                 }
        
        // load sub counties
                for(var i=0;i<sub_counties.length;i++){
                 sub_counties_label+="<option value=\""+sub_counties[i].id+"\">"+sub_counties[i].name+"</option>";
                 }
        
        // load facilities
                for(var i=0;i<facilities.length;i++){
                 facilities_label+="<option value=\""+facilities[i].id+"\">"+facilities[i].name+"</option>";
                 }
        $("#user_level").html(levels);
        $("#approved").html(approved_label);
        $("#status").html(status_label);
        
        $('#county').val(null);
        $('#sub_county').val(null);
        $('#facility').val(null);
        $("#county").html(counties_label);
        $("#sub_county").html(sub_counties_label);
        $("#facility").html(facilities_label);
        
        $("#fullname").html("<b>"+user_info.first_name+" "+user_info.middle_name+" "+user_info.sur_name+"</b>");
        
        $("#fname").val(user_info.first_name);
        $("#mname").val(user_info.middle_name);
        $("#lname").val(user_info.sur_name);
        $("#email").val(user_info.email);
        $("#phone").val(user_info.phone);
        
        $("#user").val(user_info.user);
        
      
          
       } 
        
        
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
      $('#sub_county').val(null);
      $("#sub_county").html(sub_counties_label);
       $('#sub_county').select2(); 
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
      
      $('#facility').val(null);
      $("#facility").html(facilities_label);
       $('#facility').select2();  
        });
        </script>
   <script>
    $("#update").click(function(){
       var error_message="",errors=0;
       var user,user_level,approved,status,county,sub_county,facility,fname,mname,lname,email,phone;
       
       
       user=$("#user").val();
       user_level = $("#user_level").val();
       approved =  $("#approved").val();
       status =  $("#status").val();
        
       county = ""+$("#county").val();
       sub_county = ""+$("#sub_county").val();
       facility = ""+$("#facility").val();
        
        
       fname = $("#fname").val();
       mname = $("#mname").val();
       lname = $("#lname").val();
       email = $("#email").val();
       phone = $("#phone").val();
       
       
       
       if(user_level!=="" && approved!=="" && status!==""){
       if(user_level==="1" && facility===""){ // choose facility for the counsellor
        errors++;
        error_message+=" Choose Facility where this counsellor works";
       }
            
            
       }
       else{
           errors++;
           error_message+="Enter all the Required Information";
       }
       
      if(errors===0){
       var form_data = {"user":user,"user_level":user_level,"approved":approved,"status":status,"county":county,"sub_county":sub_county,"facility":facility,"fname":fname,"mname":mname,"lname":lname,"email":email,"phone":phone};
      console.log(form_data);
            save_data(form_data);
          
      } 
      
      else{
          
           $.jGrowl(error_message, {
                         position: 'top-center',
                         header: 'Error occured while saving ...',
                         theme: 'bg-danger'
                    }); 
      }
       
       
    });  
       
       
       
            function save_data(data){
        var theme="";
        var url = "update_user";
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
        $(document).ready(function(){ 
            
          $("#facility_label").hide();   
          $("#sub_county_label").hide();   
          $("#county_label").hide(); 
          
           // make not required
          $("#county").removeAttr('required');
          $("#sub_county").removeAttr('required');
          $("#facility").removeAttr('required');
          
          $("#county").change(function(){
            var user_level = $("#user_level").val();  
            if(parseInt(user_level)===1 || parseInt(user_level)===2){
          $("#sub_county_label").show();   
           $("#sub_county").attr('required', 'required');
            }
            else{
           $("#sub_county_label").hide();   
           $("#sub_county").aremoveAttr('required'); 
            }
            
          });
          
          $("#sub_county").change(function(){
            var user_level = $("#user_level").val();
            if(parseInt(user_level)===1){
          $("#facility_label").show();   
           $("#facility").attr('required', 'required');
            }
            else{
           $("#facility_label").hide();   
           $("#facility").aremoveAttr('required'); 
            }
          });
          
          
          
          
        $("#user_level").change(function(){
         var user_level = $("#user_level").val();
         
         if(parseInt(user_level)===1){ // for facility users
             
          $("#facility_label").hide();   
          $("#sub_county_label").hide();   
          $("#county_label").show(); 
          
           // make required
          $("#facility").attr('required', 'required');
          $("#county").removeAttr('required');
          $("#sub_county").removeAttr('required');
         }
         else if(parseInt(user_level)===2){ // for sub county users
         
            
          $("#facility_label").hide();   
          $("#sub_county_label").show();   
          $("#county_label").show(); 
          
           // make required
          $("#sub_county").attr('required', 'required');
          $("#county").removeAttr('required');
          $("#facility").removeAttr('required');
         }
         else if(parseInt(user_level)===3){ // for county users
          $("#facility_label").hide();   
          $("#sub_county_label").hide();   
          $("#county_label").show(); 
          
           // make required
          $("#county").attr('required', 'required');
          $("#sub_county").removeAttr('required');
          $("#facility").removeAttr('required');
         }
         else if(parseInt(user_level)===4){ // for program users
          $("#facility_label").hide();   
          $("#sub_county_label").hide();   
          $("#county_label").hide(); 
          
           // make required
          $("#county").removeAttr('required');
          $("#sub_county").removeAttr('required');
          $("#facility").removeAttr('required');
         }
         else{ // any other
         $("#facility_label").hide();   
          $("#sub_county_label").hide();   
          $("#county_label").hide(); 
          
           // make required
          $("#county").removeAttr('required');
          $("#sub_county").removeAttr('required');
          $("#facility").removeAttr('required');    
         }
        
        
        });   
        });   
           
           </script>
           
            <script>
  $(function () {
    $('.select2').select2();
  });
  
  </script>
</html>
