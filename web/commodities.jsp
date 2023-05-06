<%-- 
    Document   : commodities
    Created on : 7 Mar 2023, 11:24:04
    Author     : mwamb
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Manage Commodities</title>
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
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">Commodities Management Module</h3>
              </div>
                         <div class="card-body"> 
                             <div style="margin-bottom: 5px;">
                                 <button data-toggle="modal" data-target="#modal-default" class="btn btn-success" id="add_new"> Add New Commodity </button>
                             </div>
                             <table id="data_table" class="table table-bordered table-striped" style="width:100%; size: 12px;">
                           <thead>
                               <tr>
                                  <th>No.</th> 
                                  <th>Health Facility</th> 
                                  <th>Delivery Note Number</th> 
                                  <th>Commodity</th> 
                                  <th>Batch no</th>
                                  <th>Quantity Received</th>
                                  <th>Entered By</th> 
                                  <th>Verification date</th> 
                                  <th>Manage</th> 
                               </tr>
                            </thead>
                            <tbody>
                              
                            </tbody> 
                        </table>
                         </div>  
                
                <!---- modals ---->
                
                
                <div class="modal fade" id="modal-default" style="width:100%;">
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
                    Access Code <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control" minlength="6" maxlength="6" id="code" name="code" placeholder="Access Code"  required="false">
                    </div>
                    </div>
                
                <div class="flex-container">
                <div class="flex-child">
                    Health Facility <font color="red"><b>*</b></font>:
                    </div>
                
                    <div class="form-group has-feedback has-feedback-left flex-child">
                    <select class="form-control select2" id="facility" name="facility"  required="true"> </select>
                    </div>
                    </div>
                
                <div class="flex-container">    
                <div class="flex-child">
                    Delivery Note Number <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control" minlength="2" maxlength="50" id="delivery_note_number" name="delivery_note_number" placeholder="Delivery Note Number"  required="false">
                    </div>
                    </div>

                <div class="flex-container">    
                <div class="flex-child">
                    Document Date <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control datepicker" minlength="10" maxlength="10" id="document_date" name="document_date" placeholder="Document Date"  required="true">
                    </div>
                    </div>
                
                    <div class="flex-container">
        <div class="flex-child">
                    Commodity <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="county_level">
                    <select class="form-control select2" id="commodity" name="commodity"  required="true"> </select>
                    </div>
                    </div>
                
                
                <div class="flex-container">    
                <div class="flex-child">
                    Batch Number <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control" minlength="1" maxlength="50" id="batch_number" name="batch_number" placeholder="Batch Number"  required="false">
                    </div>
                    </div>
                
                
                <div class="flex-container">    
                <div class="flex-child">
                    Delivery Note Quantity <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="number" class="form-control" minlength="1" maxlength="10" id="delivery_note_quantity" name="delivery_note_quantity" placeholder="Delivery Note Quantity"  required="true">
                    </div>
                    </div> 
                
                
              <div class="flex-container">    
                <div class="flex-child">
                    Expiry Date <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control datepicker" minlength="10" maxlength="10" id="expiry_date" name="expiry_date" placeholder="Expiry Date"  required="true">
                    </div>
                    </div>
                
                          
                <div class="flex-container">
                    <div  class="flex-child">
                    Pipeline <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="sub_county_level">
                    <select class="form-control select2" id="pipeline" name="pipeline"  required="true"> </select>
                    </div>
                    </div>
            
                
                              
                <div class="flex-container">
                    <div  class="flex-child">
                    Time Delivered <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" style="" id="sub_county_level">
                      <table> 
                      <tr> 
                      <td> <select class="form-control select2" style="display: block;" id="time" name="time"  required="true"> </select> </td>
                       <td> <select class="form-control select2" style="display: block;" id="minute" name="minute"  required="true"> </select></td>
                      </tr>    
                         </table> 
                     
                    </div>
                    </div>
            
                
                
                <div class="flex-container">    
                <div class="flex-child">
                    Quantity Received <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="number" class="form-control" minlength="1" maxlength="10" id="quantity_received" name="quantity_received" placeholder="Quantity Received"  required="true">
                    </div>
                    </div> 
                
                  <div class="flex-container">    
                <div class="flex-child">
                    Date Received <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control datepicker" minlength="10" maxlength="10" id="date_received" name="date_received" placeholder="Date Received"  required="true">
                    </div>
                    </div>
                
                <div class="flex-container">    
                <div class="flex-child">
                     Received By <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control" minlength="1" maxlength="100" id="received_by" name="received_by" placeholder="Received By"  required="true">
                    </div>
                    </div> 
                
                
                <div class="flex-container">    
                <div class="flex-child">
                    Contacts <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="number" class="form-control" minlength="1" maxlength="100" id="contacts" name="contacts" placeholder="eg. 0720......"  required="true">
                    </div>
                    </div> 
                
               <div class="flex-container">    
                <div class="flex-child">
                    Verification Date <font color="red"><b>*</b></font>:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <input type="text" class="form-control" minlength="10" maxlength="10" id="verification_date" name="verification_date" placeholder="Verification Date"  required="true">
                    </div>
                    </div>
                
            <div class="flex-container">
                <div class="flex-child">
                    Comments:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <textarea type="text" class="form-control" id="comments" name="comments" placeholder="Comments"></textarea>
                    </div>
                    </div>
                
                <input type="hidden" id="stock_id" name="stock_id" value="0">
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
					<!-- Footer -->
                                        <div class="footer text-muted">
                                          <%@include file="menu/footer.jsp"%>
					</div>
					<!-- /footer -->

				</div>
                                           
</body>

   
            <script rel="stylesheet" href="plugins/select2/js/select2.full.min.js"></script>
    <script>
   $(document).ready(function() { 
$("#verification_date").datepicker({
      changeYear: true,
      changeMonth: true,
      dateFormat: 'yy-mm-dd',
      maxDate: 0
  });
$("#document_date").datepicker({
      changeYear: true,
      changeMonth: true,
      dateFormat: 'yy-mm-dd',
      maxDate: 0
  });
$("#expiry_date").datepicker({
      changeYear: true,
      changeMonth: true,
      dateFormat: 'yy-mm-dd',
      minDate: 0
  });
$("#date_received").datepicker({
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
    if(indicators[i].is_active===1){
  indicator_label+="<option value=\""+indicators[i].id+"\">"+indicators[i].name+"</option>"  ;
    }
}

$("#indicator").html(indicator_label);

}); 
    </script>
    
    <script>
   $(document).ready(function() {
        load_data();
}); 
    </script>
<script>
      function load_data(){
       $.ajax({
        url:'load_stock_data',
        type:"get",
        dataType:"json",
        success:function(info){
       var dataSet=[];
         var data = info;
         var pos,id,facility_name,delivery_note_number,commodity,batch_number,quantity_received,entered_by,verification_date;
         var commodity_id,facility_id,document_date,delivery_note_quantity,expiry_date,date_received,received_by,contacts,pipeline_id,comments;
         var time,minute;
         
        for (var i=0; i<data.length;i++){
        var minSet = [];
                pos=(i+1);
                id = data[i].id;
                facility_name = data[i].facility_name;
                delivery_note_number = data[i].delivery_note_number;
                commodity = data[i].commodity;
                batch_number = data[i].batch_number;
                quantity_received = data[i].quantity_received;
                entered_by = data[i].entered_by;
                verification_date = data[i].verification_date;
                
                commodity_id = data[i].commodity_id;
                facility_id = data[i].facility_id;
                document_date = data[i].document_date;
                delivery_note_quantity = data[i].delivery_note_quantity;
                expiry_date = data[i].expiry_date;
                date_received = data[i].date_received;
                received_by = data[i].received_by;
                contacts = data[i].contacts;
                pipeline_id = data[i].pipeline_id;
                comments = data[i].comments;
                time = data[i].time;
                minute = data[i].minute;
   
                // for status
              
var action='<ul class="icons-list"><li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">'+
		'<i class="icon-menu9"></i></a><ul class="dropdown-menu dropdown-menu-right">'+
                '<li data-toggle="modal" data-target="#modal-default"><a onclick="edit_stock(\''+id+'\',\''+delivery_note_number+'\',\''+batch_number+'\',\''+quantity_received+'\',\''+verification_date+'\',\''+commodity_id+'\',\''+facility_id+'\',\''+document_date+'\',\''+delivery_note_quantity+'\',\''+expiry_date+'\',\''+date_received+'\',\''+received_by+'\',\''+contacts+'\',\''+pipeline_id+'\',\''+comments+'\',\''+time+'\',\''+minute+'\');"><i class="fas fa-user-cog"></i>Edit</a></li>';
               
        action+='</ul>'+
		'</li>'+
		'</ul>';
  
       minSet.push(pos);  
       minSet.push(facility_name);  
       minSet.push(delivery_note_number);  
       minSet.push(commodity);  
       minSet.push(batch_number);  
       minSet.push(quantity_received);  
       minSet.push(entered_by);
       minSet.push(verification_date);
       minSet.push(action);
       
        dataSet.push(minSet);
        }
       
//       console.log(dataSet);
       
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
 
//    console.log( count +' column(s) are hidden' );
} ); 
 
        }
  });   
    } 
    
    
function edit_stock(id,delivery_note_number,batch_number,quantity_received,verification_date,commodity_id,facility_id,document_date,delivery_note_quantity,expiry_date,date_received,received_by,contacts,pipeline_id,comments,time,minute){
    
    $("#title").html("Update Commodity");
    $("#update").html("Update Commodity");
    clear_entries();         
    load_new_form(commodity_id,facility_id,pipeline_id,time,minute);

        $("#stock_id").val(id);
        $("#verification_date").val(verification_date); 
        $("#delivery_note_number").val(delivery_note_number);
        $("#document_date").val(document_date);
        $("#batch_number").val(batch_number);
        $("#delivery_note_quantity").val(delivery_note_quantity);
        $("#quantity_received").val(quantity_received);
        $("#expiry_date").val(expiry_date);
        $("#date_received").val(date_received);
        $("#received_by").val(received_by);
        $("#contacts").val(contacts);
        $("#comments").val(comments);
       } 

  
    </script>
    
    <script>
        $(document).ready(function(){
         $("#add_new").click(function(){ 
             $("#title").html("Add New Commodity");
             $("#update").html("Save Commodity");
             
               clear_entries(); 
               load_new_form(0,0,0,90,90);
               
         });
        
        });
        </script>
  <script>  
     function clear_entries(){
        $("#facility").html("");
        $("#commodity").html("");
        $("#pipeline").html(""); 
        $("#time").html(""); 
        $("#minute").html(""); 
        
        $("#stock_id").val(0);
        $("#verification_date").val(""); 
        $("#delivery_note_number").val("");
        $("#document_date").val("");
        $("#batch_number").val("");
        $("#delivery_note_quantity").val("");
        $("#quantity_received").val("");
        $("#expiry_date").val("");
        $("#date_received").val("");
        $("#received_by").val("");
        $("#contacts").val("");
        $("#comments").val("");
        
     }     
     
    function load_new_form(commodity_id,facility_id,pipeline_id,time_id,minute_id){
                     
          var facilities = load_facilities();
          var commodities = load_commodities();
          var pipeline = load_pipeline();
          var time = load_time();
          var minutes = load_minutes();
          
          
          var facility_label = "<option value=\"\">Choose Facility</option>";
         var commodities_label = "<option value=\"\">Choose Commodity</option>";
         var pipeline_label = "<option value=\"\">Choose Pipeline</option>";
         var time_label = "<option value=\"\"> --- </option>";
         var minutes_label = "<option value=\"\"> --- </option>";
         
         // for sections
        for(var i=0;i<facilities.length;i++){
            if(parseInt(facility_id)===parseInt(facilities[i].id)){
           facility_label+="<option value=\""+facilities[i].id+"\" selected>"+facilities[i].name+"-"+facilities[i].mfl_code+"</option>";
            }
            else{
           facility_label+="<option value=\""+facilities[i].id+"\">"+facilities[i].name+"-"+facilities[i].mfl_code+"</option>";     
            }
        }
        
        // for indicator types
        for(var i=0;i<commodities.length;i++){
            if(parseInt(commodity_id)===parseInt(commodities[i].id)){
            commodities_label+="<option value=\""+commodities[i].id+"\" selected>"+commodities[i].commodity+","+commodities[i].packs+"</option>";
        }
        else{
         commodities_label+="<option value=\""+commodities[i].id+"\" >"+commodities[i].commodity+","+commodities[i].packs+"</option>";   
        }
        }
        
        // for frequency
        for(var i=0;i<pipeline.length;i++){
             if(parseInt(pipeline_id)===parseInt(pipeline[i].id)){
            pipeline_label+="<option value=\""+pipeline[i].id+"\" selected>"+pipeline[i].name+"</option>";
             }
             else{
             pipeline_label+="<option value=\""+pipeline[i].id+"\" >"+pipeline[i].name+"</option>";    
             }
        }
       
        // for time
        for(var i=0;i<time.length;i++){
             if(parseInt(time_id)===parseInt(time[i].nums)){
            time_label+="<option value=\""+time[i].nums+"\" selected>"+time[i].nums+"</option>";
             }
             else{
             time_label+="<option value=\""+time[i].nums+"\" >"+time[i].nums+"</option>";    
             }
        }
       
        // for minutes
        for(var i=0;i<minutes.length;i++){
//            console.log("mins selected : "+minute_id+" now minutes : "+minutes[i].nums);
             if(parseInt(minute_id)===parseInt(minutes[i].nums)){
            minutes_label+="<option value=\""+minutes[i].nums+"\" selected>"+minutes[i].nums+"</option>";
             }
             else{
             minutes_label+="<option value=\""+minutes[i].nums+"\" >"+minutes[i].nums+"</option>";    
             }
        }
       
        $("#facility").html(facility_label);
        $("#commodity").html(commodities_label);
        $("#pipeline").html(pipeline_label);
        $("#time").html(time_label);
        $("#minute").html(minutes_label);
       
       $('.select2').select2();
          
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
          
   function load_pipeline(){ 
       var data=[];
    $.ajax({
        url:'load_pipeline',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info;
  }
        
   }) ;
   return data;
   }
   
     function load_commodities(){ 
       var data=[];
    $.ajax({
        url:'load_commodities',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info;
  }
        
   }) ;
   return data;
   }
   
      function load_time(){ 
       var data=[];
    $.ajax({
        url:'load_time',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info;
  }
        
   }) ;
   return data;
   }
   
      function load_minutes(){ 
       var data=[];
    $.ajax({
        url:'load_minutes',
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
        $(document).ready(function(){
$("#update").click(function(){
               
   var stock_id,facility,verification_date,delivery_note_number,document_date,commodity,batch_number,delivery_note_quantity,quantity_received,expiry_date,date_received,received_by,contacts,pipeline,comments;
   var time,minute;
    stock_id = $("#stock_id").val();
    facility =  $("#facility").val();
    verification_date =   $("#verification_date").val(); 
    delivery_note_number =  $("#delivery_note_number").val();
    document_date =   $("#document_date").val();
    commodity =   $("#commodity").val();
    batch_number =   $("#batch_number").val();
    delivery_note_quantity =  $("#delivery_note_quantity").val();
    quantity_received =  $("#quantity_received").val();
    expiry_date =  $("#expiry_date").val();
    date_received =  $("#date_received").val();
    received_by =  $("#received_by").val();
    contacts =  $("#contacts").val();
    pipeline =  $("#pipeline").val();
    comments =  $("#comments").val();
    time =  $("#time").val();
    minute =  $("#minute").val();
   var code =  $("#code").val();
    
    
    
//  alert("reached here");
        var errors = "", error_count=0;
    if(code.length!==6){
        error_count++;
        errors+=""+error_count+". Wrong access code provided <br>";
    }  
    if(facility.length===0){
        error_count++;
        errors+=""+error_count+". Choose Health Facility <br>";
    }  
    if(verification_date.length<10 || verification_date.length>10){
        error_count++;
        errors+=""+error_count+". Enter correct verification date <br>";
    }  
    if(delivery_note_number.length===0){
        error_count++;
        errors+=""+error_count+". Enter delivery note number. <br>";
    } 
        if(document_date.length<10 || document_date.length>10){
        error_count++;
        errors+=""+error_count+". Enter correct document date. <br>";
    }
    if(commodity.length===0){
        error_count++;
        errors+=""+error_count+". Choose commodity description. <br>";
    }  
    if(delivery_note_quantity.length===0){
        error_count++;
        errors+=""+error_count+". Enter delivery note quantity. <br>";
    } 
    if(batch_number.length===0){
        error_count++;
        errors+=""+error_count+". Enter batch number. <br>";
    } 
    if(quantity_received.length===0){
        error_count++;
        errors+=""+error_count+". Enter quantity received. <br>";
    } 
    if(expiry_date.length===0){
        error_count++;
        errors+=""+error_count+". Enter commodity expiry date. <br>";
    } 
    if(date_received.length===0){
        error_count++;
        errors+=""+error_count+". Enter date quantity received. <br>";
    } 
    if(received_by.length===0){
        error_count++;
        errors+=""+error_count+". Enter name of officer who received the commodities. <br>";
    } 
    if(contacts.length===0){
        error_count++;
        errors+=""+error_count+". Enter contact information of receiver. <br>";
    } 
    if(pipeline.length===0){
        error_count++;
        errors+=""+error_count+". Choose Pipeline <br>";
    } 
    if(time.length===0){
        error_count++;
        errors+=""+error_count+". Choose time <br>";
    } 
    if(minute.length===0){
        error_count++;
        errors+=""+error_count+". Choose minutes <br>";
    } 
    
//    alert(error_count);
    if(error_count>0){
        //exit
    show_errors(errors,error_count);
    }
    else{
    
        var form_data = {"code":code,"stock_id":stock_id,"facility":facility,"verification_date":verification_date,"delivery_note_number":delivery_note_number,"document_date":document_date,"commodity":commodity,"batch_number":batch_number,"delivery_note_quantity":delivery_note_quantity,"quantity_received":quantity_received,"expiry_date":expiry_date,"date_received":date_received,"received_by":received_by,"contacts":contacts,"pipeline":pipeline,"comments":comments,"time":time,"minute":minute};
        console.log(form_data);
        save_stock(form_data);
    }
    
});
        });

function save_stock(data){
     var theme="";
        var url = "save_stock";
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
 <script>
  $(function () {
    $('.select2').select2();
  });
  
  </script>
 <script>
  $(function () {

  });
  
  function show_errors(error,num_errors){
          $.jGrowl('close');
    $.jGrowl(error, {
                position: 'top-center',
                life:(num_errors*1000),
                header: '<b>Error</b>',
                theme: 'bg-danger'
           });   
  }
  </script>
</html>