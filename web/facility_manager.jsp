<%-- 
    Document   : facility_manager
    Created on : 25-Apr-2022, 12:12:31
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Manage Health Facilities</title>
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
       
              <script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="plugins/select2/js/select2.full.min.js"></script>
             <!-- Select2 -->
             <link rel="stylesheet" href="/plugins/select2/css/select2.min.css">
             <link rel="stylesheet" href="/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
         
            
      <style>
        input[type=checkbox] {
        transform: scale(0.5);
        }
       
   .flex-container {
    display: flex;
    margin-top: -8px;
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
    
         <%if(session.getAttribute("admin")!=null){
      if(session.getAttribute("admin").toString().equals("1")){%>        
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
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">Facility Reportable Sections/Indicators</h3>
              </div>
                         <div class="card-body">        
                             <table id="data_table" class="table table-bordered table-striped" style="width:100%; size: 12px;">
                           <thead>
                               <tr>
                                  <th>No.</th> 
                                  <th>County</th> 
                                  <th>Sub County</th> 
                                  <th>Health Facility</th> 
                                  <th>MFL Code</th> 
                                  <th>Prevention</th> 
                                  <th>HTS</th> 
                                  <th>Treatment</th> 
                                  <th>VL</th> 
                                  <th>TB</th> 
                                  <th>Manage</th> 
                               </tr>
                            </thead>
                            <tbody>
                              
                            </tbody> 
                        </table>
                         </div>  
                
                <!---- modals ---->
               
       <div class="modal fade" id="modal-access">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title"><b>Manage Access Permissions</b></h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body" autocomplete="off" id="user_access">
              <form id="facility_settings">  
             
                  <div class="flex-container">
                     <div class="flex-child">
                   Gend GBV:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="checkbox" class="form-control" id="gend_gbv" name="gend_gbv"  required="true">
                    </div>
                    </div>
                
                    <div class="flex-container">
                     <div class="flex-child">
                    PrEP:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="checkbox" class="form-control" id="prep" name="prep"  required="true">
                    </div>
                    </div>
                
                    <div class="flex-container">
                     <div class="flex-child">
                    Key Population:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="checkbox" class="form-control" id="kp" name="kp"  required="true">
                    </div>
                    </div>
                    
                    <div class="flex-container">
                     <div class="flex-child">
                    HTS SELF:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="checkbox" class="form-control" id="hts_self" name="hts_self"  required="true">
                    </div>
                    </div>
                    <div class="flex-container">
                     <div class="flex-child">
                    HTS TST:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                       <input type="checkbox" class="form-control" id="hts_tst" name="hts_tst"  required="true">
                    </div>
                    </div>
                
                    <div class="flex-container">
                     <div class="flex-child">
                    HTS Pos:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="checkbox" class="form-control" id="hts_pos" name="hts_pos"  required="true">
                    </div>
                    </div>
                    <div class="flex-container">
                     <div class="flex-child">
                    Index Testing:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                       <input type="checkbox" class="form-control" id="index_testing" name="index_testing"  required="true">
                    </div>
                    </div>
                   <hr>
                    
                   
                    <div class="flex-container">
                     <div class="flex-child">
                    HTS Recency:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="checkbox" class="form-control" id="hts_recency" name="hts_recency"  required="true">
                    </div>
                    </div>
                   
                    <div class="flex-container">
                     <div class="flex-child">
                    TX New:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="checkbox" class="form-control" id="tx_new" name="tx_new"  required="true">
                    </div>
                    </div>
                   
                     <div class="flex-container">
                     <div class="flex-child">
                    Cancer Screening:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="checkbox" class="form-control" id="ca_screening" name="ca_screening"  required="true">
                    </div>
                    </div>
                   
                <div class="flex-container">
                     <div class="flex-child">
                    PMTCT: 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="checkbox" class="form-control" id="pmtct" name="pmtct"  required="true">
                    </div>
                    </div>
                
                
                    <div class="flex-container">
                     <div class="flex-child">
                    EID:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="checkbox" class="form-control" id="eid" name="eid"  required="true">
                    </div>
                    </div>
                
                
                     <div class="flex-container">
                     <div class="flex-child">
                   Retention:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="checkbox" class="form-control" id="retention" name="retention"  required="true">
                    </div>
                    </div>
                
                
                     <div class="flex-container">
                     <div class="flex-child">
                    Viral Suppression:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="checkbox" class="form-control" id="vl" name="vl"  required="true">
                    </div>
                    </div>
                
                     <div class="flex-container">
                     <div class="flex-child">
                    TB:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="checkbox" class="form-control" id="tb" name="tb"  required="true">
                    </div>
                    </div>
                
                     <div class="flex-container">
                     <div class="flex-child">
                    TPT:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="checkbox" class="form-control" id="tpt" name="tpt"  required="true">
                    </div>
                    </div>
                
                     <div class="flex-container">
                     <div class="flex-child">
                    STF:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="user_level">
                        <input type="checkbox" class="form-control" id="stf" name="stf"  required="true">
                    </div>
                    </div>
                
                <input type="hidden" id="id" name="id">
                <input type="hidden" id="f_id" name="f_id">
                <p id="timestamp"> Test </p>
              </form>
            </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" id="update_access">Update</button>
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
                                           
                                        <%if(session.getAttribute("new_facility")!=null){
                                        String mess =session.getAttribute("new_facility").toString();
                                        %>
                                        <script type="text/javascript">
                                           $.jGrowl('<%=mess%>', {
                                                position: 'center',
                                                header: 'Register Information.',
                                                theme: 'bg-info'
                                            });  
                                            </script>
                                            <% session.removeAttribute("new_facility");}%>
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
        url:'load_all_facilities_module',
        type:"get",
        dataType:"json",
        success:function(data){
       var dataSet=[];
         var pos,id,health_facility,mfl_code,sub_county,county,prevention,hts,treatment,vl,tb,label_prevention,label_hts,label_treatment,label_vl,label_tb;
    for (var i=0; i<data.length;i++){
        var minSet = [];
                pos=(i+1);
                id = data[i].id;
                county = data[i].county;
                sub_county = data[i].sub_county;
                health_facility = data[i].health_facility;
                mfl_code = data[i].mflcode;
                prevention = data[i].prevention;
                hts = data[i].hts;
                treatment = data[i].treatment;
                vl = data[i].vl;
                tb = data[i].tb;
               

var action='<ul class="icons-list"><li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">'+
		'<i class="icon-menu9"></i></a><ul class="dropdown-menu dropdown-menu-right">\n';
       <%if(session.getAttribute("admin")!=null){
      if(session.getAttribute("admin").toString().equals("1")){%>  
      action+='<li data-toggle="modal" data-target="#modal-access"><a onclick="manage_facility('+id+');"><i class="fas fa-user-cog"></i>Manage</a></li>\n';
             <%}}%>

      
        action+='</ul>'+
		'</li>'+
		'</ul>';

                if(prevention===1){
                 label_prevention="<img src=\"dumisha/tick.png\" width=\"20px;\" alt=\"Yes\">";   
                  }
                  else{
                 label_prevention="<img src=\"dumisha/cross.png\" alt=\"No\">";      
                    }

                if(hts===1){
                 label_hts="<img src=\"dumisha/tick.png\" width=\"20px\" alt=\"Yes\">";   
                  }
                  else{
                 label_hts="<img src=\"dumisha/cross.png\"  alt=\"No\">";      
                    }

                if(treatment===1){
                 label_treatment="<img src=\"dumisha/tick.png\" width=\"20px\" alt=\"Yes\">";   
                  }
                  else{
                 label_treatment="<img src=\"dumisha/cross.png\" alt=\"No\">";      
                    }

                if(vl===1){
                 label_vl="<img src=\"dumisha/tick.png\" width=\"20px\" alt=\"Yes\">";   
                  }
                  else{
                 label_vl="<img src=\"dumisha/cross.png\" alt=\"No\">";      
                    }

                if(tb===1){
                 label_tb="<img src=\"dumisha/tick.png\" width=\"20px\" alt=\"Yes\">";   
                  }
                  else{
                 label_tb="<img src=\"dumisha/cross.png\" alt=\"No\">";      
                    }




                
       minSet.push(pos);  
       minSet.push(county);  
       minSet.push(sub_county);  
       minSet.push(health_facility);  
       minSet.push(mfl_code);  
       minSet.push(label_prevention);  
       minSet.push(label_hts);
       minSet.push(label_treatment);
       minSet.push(label_vl);
       minSet.push(label_tb);  
        minSet.push(action);
       
        dataSet.push(minSet);
        }
       
   
       
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
    
    
    
      function manage_facility(pos){
          
  var form_data = {"f_id":pos};
                var theme="";
                var url = "load_facility_modules";
                   $.post(url,form_data , function(output) {
                         edit_facility_module(output); 
                                 });
  }
  
    </script>
    
    <script>
     function edit_facility_module(modules){ 
           modules = JSON.parse(modules);

         if(modules.gend_gbv>0){$("#gend_gbv").attr('checked','checked');} else{$("#gend_gbv").removeAttr('checked');}
         if(modules.prep>0){$("#prep").attr('checked','checked');} else{$("#prep").removeAttr('checked');}
         if(modules.kp>0){$("#kp").attr('checked','checked');} else{$("#kp").removeAttr('checked');}
         if(modules.hts_self>0){$("#hts_self").attr('checked','checked');} else{$("#hts_self").removeAttr('checked');}
         if(modules.hts_tst>0){$("#hts_tst").attr('checked','checked');} else{$("#hts_tst").removeAttr('checked');}
         if(modules.hts_pos>0){$("#hts_pos").attr('checked','checked');} else{$("#hts_pos").removeAttr('checked');}
         if(modules.index_testing>0){$("#index_testing").attr('checked','checked');} else{$("#index_testing").removeAttr('checked');}
         if(modules.hts_recency>0){$("#hts_recency").attr('checked','checked');} else{$("#hts_recency").removeAttr('checked');}
         if(modules.tx_new>0){$("#tx_new").attr('checked','checked');} else{$("#tx_new").removeAttr('checked');}
         if(modules.ca_screening>0){$("#ca_screening").attr('checked','checked');} else{$("#ca_screening").removeAttr('checked');}
         if(modules.pmtct>0){$("#pmtct").attr('checked','checked');} else{$("#pmtct").removeAttr('checked');}
         if(modules.eid>0){$("#eid").attr('checked','checked');} else{$("#eid").removeAttr('checked');}
         if(modules.retention>0){$("#retention").attr('checked','checked');} else{$("#retention").removeAttr('checked');}
         if(modules.vl>0){$("#vl").attr('checked','checked');} else{$("#vl").removeAttr('checked');}
         if(modules.tb>0){$("#tb").attr('checked','checked');} else{$("#tb").removeAttr('checked');}
         if(modules.tpt>0){$("#tpt").attr('checked','checked');} else{$("#tpt").removeAttr('checked');}
         if(modules.stf>0){$("#stf").attr('checked','checked');} else{$("#stf").removeAttr('checked');}
         
         $("#timestamp").html("<b>Last Updated</b>: <i style=\"color:red\">"+modules.timestamp+"</i>");
         $("#id").val(modules.id);
         $("#f_id").val(modules.facility_id);
       } 
        </script>

    
    
    <script>
    
        </script>
   <script>

       function save_data(data,url){
        var theme="";
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
                   
                   
                   $("#update_access").click(function(){
                       var data = ""+$("#facility_settings").serialize().replace('on', '1');;
                     
//                        alert("called "+data);
                      save_data(data,"update_facility_module_access");

                   });
       </script> 
       
       <script>
        $(document).ready(function(){ 
         
        });   
           
           </script>
           
            <script>
  $(function () {
    $('.select2').select2();
  });
  
  </script>
</html>
