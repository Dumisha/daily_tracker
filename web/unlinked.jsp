<%-- 
    Document   : unlinked
    Created on : 01-Mar-2022, 11:27:00
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Un-linked clients</title>
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
            <div class="card">
              <div class="card-header">
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">Unlinked clients Module</h3>
              </div>
                         <div class="card-body"> 
                            
                             <table id="data_table" class="table table-bordered table-striped" style="width:100%; size: 12px;">
                           <thead>
                               <tr>
                                  <th>#</th> 
                                  <th>County</th> 
                                  <th>Sub County</th> 
                                  <th>Health_Facility</th> 
                                  <th>Gender</th> 
                                  <th>Date of Birth</th> 
                                  <th>SDP</th> 
                                  <th>Non-Linkage Reason</th> 
                                  <th>Recency</th> 
                                  <th>Screened TB</th> 
                                  <th>Has TB Signs</th> 
                                  <th>Date Tested</th> 
                                  <th>Action</th> 
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
              <form id="form"> 
            <div class="modal-header">
              <h4 class="modal-title"><b id="title">Update Linkage</b></h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
               
                 <div class="flex-container" id="label_106">
                <div class="flex-child">
                    Where Patient Linked :
                    </div>
                
                    <div class="form-group has-feedback has-feedback-left flex-child">
                    <select class="form-control select2bs4" id="id_106" name="id_106"  required="true"> 
                    <option value="21">Dumisha Supported Site</option>
                    <option value="22">Other health facilities</option>
                    </select>
                    </div>
                    </div>
                <div class="flex-container" id="label_113">    
                <div class="flex-child">
                   Facility Linked: 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                      <select class="form-control select2bs4" id="id_113" name="id_113"  required="true"> </select>
                    </div>
                    </div>

            <div class="flex-container" id="label_112">
                <div class="flex-child">
                  Facility Linked Other:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control" id="id_112" name="id_112"  required="true">
                    </div>
                    </div>
                
            <div class="flex-container" id="label_31">
                <div class="flex-child">
                  Date Linked:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control datepicker" autocomplete="off" id="id_31" name="id_31"  required="true">
                    </div>
                    </div>
            <div class="flex-container" id="label_31">
                <div class="flex-child">
                  UPN:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="number" min="1000000000" max="9999999999" maxlength="10" class="form-control" autocomplete="off" id="id_32" name="id_32"  required="true">
                    </div>
                    </div>
                <div class="flex-container" id="label_33">
        <div class="flex-child">
                    Linked (Community, Facility):
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="">
                    <select class="form-control select2bs4" id="id_33" name="id_33"  required="true">
                    <option value="3">Community</option>
                    <option value="4">Facility</option>
                     </select>
                    </div>
                    </div>
               
            </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary" id="update">Update</button>
            </div>
                  
                  <input type="hidden" id="id_30" name="id_30" value="1"> <!-- client has been linked --->
                  <input type="hidden" id="entry_key" name="entry_key" value="">
               </form>   
    
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
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
        load_data();
});
    </script>
<script>
      function load_data(){ 
          
       $.ajax({
        url:'load_unlinked',
        type:"get",
        dataType:"json",
        success:function(info){
       var dataSet=[];
         var data = info;
    for (var i=0; i<data.length;i++){
        var minSet = [];
       var pos=(i+1);
  
var action='<ul class="icons-list"><li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">'+
		'<i class="icon-menu9"></i></a><ul class="dropdown-menu dropdown-menu-right">'+
                '<li data-toggle="modal" data-target="#modal-default"><a onclick="manage(\''+data[i].entry_key+'\',\''+data[i].date_tested_pos+'\');"><i class="fas fa-user-cog"></i>Edit</a></li>';
   
        action+='</ul>'+
		'</li>'+
		'</ul>';
 
       minSet.push(pos);  
       minSet.push(data[i].county);  
       minSet.push(data[i].sub_county);  
       minSet.push(data[i].facility);  
       minSet.push(data[i].gender);  
       minSet.push(data[i].dob);  
       minSet.push(data[i].sdp);  
       minSet.push(data[i].non_linkage);
       minSet.push(data[i].enrolled_recency);  
       minSet.push(data[i].screened_tb);  
       minSet.push(data[i].has_tb_signs);  
       minSet.push(data[i].date_tested_pos);  
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
    
    
           function manage(id,date_tested){
        load_all_facilities();
        $( "#id_31" ).datepicker( "option", "minDate", new Date(date_tested) );
        $("#entry_key").val(id);
        
        
        $("#label_112").hide();
        $("#label_113").show();
        
         $("#id_112").removeAttr('required');
         $("#id_113").attr('required', 'required');
                }
        
  </script>
               
               
               <script> 
                $(document).ready(function(){
                 $('.select2').select2();   
                
                 $("#id_31").datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat: 'yy-mm-dd',
                    yearRange: "-120:+0",
                    minDate: '-120y',
                    maxDate: 0
                      });
                  });   
                   
                
    
    $("#id_106").change(function(){
      var value =  parseInt($("#id_106").val());
      if(value===21){
       $("#label_112").hide();
        $("#label_113").show();
        
         $("#id_112").removeAttr('required');
         $("#id_113").attr('required', 'required');   
      }
      
      else{
       $("#label_112").show();
        $("#label_113").hide();
        
         $("#id_113").removeAttr('required');
         $("#id_112").attr('required', 'required');   
          
          
      }
      
    });
                   </script>
                   
                   
                   <script>
                       
 function load_all_facilities(){ 
        var facility_data="<option value=\"\"></option>";
            $.ajax({
                url:'load_facilities',
                type:"get",
                dataType:"json",
                 async: false,
                success:function(facilities){
                    facilities = facilities.data;
                        for(var i=0;i<facilities.length;i++){
                      if(facilities[i].pre_selected===1){
                     facility_data+="<option value=\""+facilities[i].id+"\" selected>"+facilities[i].name+"</option>";     
                   }
                   else{
                 facility_data+="<option value=\""+facilities[i].id+"\">"+facilities[i].name+"</option>";
                  }
              }
              
              
                     }   
           }) ;
          $("#id_113").html(facility_data);
          $("#id_113").select2();
           } 
                       
                       
                       </script>
                       
                       
                       <script>
                        $(document).ready(function(){
                       $("#update").click(function(e){
                            e.preventDefault();
                            var form_data = {},keys=[];
                            $.each($('#form').serializeArray(), function(_, kv) {
                              form_data[kv.name] = kv.value;
                              keys.push(kv.name);
                            });
                            
                            form_data["keys"] = keys.toString();
                            
                            var upn = form_data.id_32;
                            var date_linked = form_data.id_31;
                            var site_type = parseInt(form_data.id_106);
                            var dumisha_site = form_data.id_113;
                            var other_sites = form_data.id_112;
                          
                                    var errors = 0;
                                    var error_message = "";
                            
                            if(site_type===21 && dumisha_site.length===0){
                                errors++;
                                error_message +=errors+". Select facility linked. <br>";
                            }
                            if(site_type===22 && other_sites.length===0){
                                errors++;
                                error_message +=errors+". Enter name of other facility linked. <br>";
                            }
                            
                            if(date_linked.length===0){
                                errors++;
                                error_message +=errors+". Enter date linked. <br>";
                            }
                            
                             if(upn.length!==10){
                                errors++;
                                error_message +=errors+". Enter correct 10 digit UPN number. <br>";
                            }
                            
                            
                            if(errors===0){
                             update_linkage(form_data); 
                             }
                             else{
                                 
                                 $.jGrowl(error_message, {
                                header: '<b>Error</b>',
                                theme: 'bg-danger'
                                    }); 
                             }
       
        
                       }); 
                       
                       
                       
                       
                        });  
                        
                        
             function update_linkage(data){
                 var theme="";
            var url = "update_linkage";
            $.post(url,data , function(output) {
                        output = JSON.parse(output);
                     var code = output.code;
                     var message = output.message;
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
</html>
