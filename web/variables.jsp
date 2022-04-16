<%-- 
    Document   : questions
    Created on : 04-Feb-2022, 08:39:11
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Manage Variables</title>
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
                                 <button data-toggle="modal" data-target="#modal-default" class="btn btn-success" id="add_new"> Add New Variable </button>
                             </div>
                             <table id="data_table" class="table table-bordered table-striped" style="width:100%; size: 12px;">
                           <thead>
                               <tr>
                                  <th>No.</th> 
                                  <th>Indicator</th> 
                                  <th>Variable</th> 
                                  <th>Input Type</th> 
                                  <th>Answer Data Type</th> 
                                  <th>Required</th> 
                                  <th>Unique</th> 
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
              <h4 class="modal-title"><b id="title">Update Variable Details</b></h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">

                <div class="flex-container">
                <div class="flex-child">
                    Indicator:
                    </div>
                
                    <div class="form-group has-feedback has-feedback-left flex-child">
                    <select class="form-control select2bs4" id="indicator_id" name="indicator_id"  required="true"> </select>
                    </div>
                    </div>
                <div class="flex-container">    
                <div class="flex-child">
                   Variable: 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <textarea type="text" class="form-control" minlength="2" id="question" name="question" placeholder="Variable"  required="false"></textarea>
                    </div>
                    </div>

            <div class="flex-container">
                <div class="flex-child">
                  Input Type:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child">
                        <select class="form-control select2bs4" id="input_type" name="input_type"  required="true"> </select>
                    </div>
                    </div>
    <div class="flex-container">
        <div class="flex-child">
                    Answer Data Type:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="">
                    <select class="form-control select2bs4" id="answer_data_type" name="answer_data_type"  required="true"> </select>
                    </div>
                    </div>
                <div class="flex-container">
                    <div  class="flex-child">
                    Variable is mandatory: 
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="sub_county_level">
                    <select class="form-control select2bs4" id="question_mandatory" name="question_mandatory"  required="true"> </select>
                    </div>
                    </div>
                <div class="flex-container">
                    <div  class="flex-child">
                    Answer is unique:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="sub_county_level">
                    <select class="form-control select2bs4" id="answer_unique" name="answer_unique"  required="true"> </select>
                    </div>
                    </div>
                <div class="flex-container">
                    <div class="flex-child">
                    Variable is active?:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="facility_level">
                    <select class="form-control select2bs4" id="status" name="status"  required="true"> </select>
                    </div>
                    </div>
                
                <input type="hidden" id="question_id" name="question_id">
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
 
                
                
                   
                
                
                <div class="modal fade" id="modal-value_labels">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title"><b id="title">Update Possible Answers</b></h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">

                <div class="flex-container">
                <div class="flex-child">
                    Variable:
                    </div>
                
                    <div class="form-group has-feedback has-feedback-left flex-child">
                    <p id="question_vl"></p>
                    </div>
                    </div>
               
    <div class="flex-container">
        <div class="flex-child">
                    Possible Answers:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="county_level">
                        <select class="form-control select2bs4" id="value_labels" name="value_labels"  required="true" multiple="true"> </select>
                    </div>
                    </div>
    <div class="flex-container">
                   Not finding your value in drop down? <b id="add_value_label" data-toggle="modal" data-target="#modal_new_value_labels" > &nbsp;Click here &nbsp;</b> to add it
                    </div>
              
                
                <input type="hidden" id="selected_question_id" name="selected_question_id">
            </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary" id="update_answers">Update</button>
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>        
                
                
                <div class="modal fade" id="modal_new_value_labels">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title"><b id="title">Add New drop down value</b></h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
               
    <div class="flex-container">
        <div class="flex-child">
                    Drop down value name:
                    </div>
                    <div class="form-group has-feedback has-feedback-left flex-child" id="county_level">
                        <input class="form-control" id="label_name" name="label_name"  required="true"> </select>
                    </div>
                    </div>
              
            </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary" id="new_value_label">Update</button>
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
          var indicators = load_indicators();
          var input_types = load_input_types();
          var answer_data_types = load_answer_data_types();
          var status = load_status();
          
       $.ajax({
        url:'load_all_questions',
        type:"get",
        dataType:"json",
        success:function(info){
       var dataSet=[];
         var data = info;
         var pos,indicator_id,id,question,input_type,answer_data_type,is_required,is_unique,is_active,indicator_label,input_type_label,answer_data_type_label,required_label,unique_label,status_label;
    for (var i=0; i<data.length;i++){
        var minSet = [];
                pos=(i+1);
                id = data[i].id;
                indicator_id = data[i].indicator_id;
                question = data[i].question;
                input_type = data[i].input_type;
                answer_data_type = data[i].answer_data_type;
                is_required = data[i].required;
                is_unique = data[i].unique;
                is_active = data[i].status;

            
            // get indicator name
            for(var j=0;j<indicators.length;j++){
                if(indicator_id===indicators[j].id){
                indicator_label= indicators[j].name;   
                }
            }
            
               // get input types name
            for(var j=0;j<input_types.length;j++){
                if(parseInt(input_type)===input_types[j].id){
                input_type_label= input_types[j].name;   
                }
            }
               // get answer data types
            for(var j=0;j<answer_data_types.length;j++){
                if(answer_data_type===answer_data_types[j].id){
                answer_data_type_label= answer_data_types[j].name;   
                }
            }
                
                
                // for is required
                
                for(var j=0;j<status.length;j++){ 
                if(is_required===status[j].id){
                required_label= status[j].name;   
                }
            }
                // for unique 
                
                for(var j=0;j<status.length;j++){ 
                if(is_unique===status[j].id){
                unique_label= status[j].name;   
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
                '<li data-toggle="modal" data-target="#modal-default"><a onclick="manage_quiz(\''+id+'\',\''+indicator_id+'\',\''+question+'\',\''+input_type+'\',\''+answer_data_type+'\',\''+is_required+'\',\''+is_unique+'\',\''+is_active+'\');"><i class="fas fa-user-cog"></i>Edit</a></li>';
                               
if(input_type===3 || input_type===4){
   action+='<li data-toggle="modal" data-target="#modal-value_labels" ><a onclick="manage_answers(\''+id+'\',\''+question+'\');"><i class="fas fa-user-alt-slash"></i>Manage Answers</a></li>';
}
   

action+='<li><a onclick="delete_quiz('+id+');"><i class="fas fa-user-alt-slash"></i>Delete</a></li>';;

        action+='</ul>'+
		'</li>'+
		'</ul>';

                
       minSet.push(pos);  
       minSet.push(indicator_label);  
       minSet.push(question);  
       minSet.push(input_type_label);  
       minSet.push(answer_data_type_label);  
       minSet.push(required_label);  
       minSet.push(unique_label);  
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
    
    
           function manage_quiz(id,indicator_id,question,input_type,answer_data_type,is_required,is_unique,is_active){

           $("#title").html("Update "+question+" Details");
             $("#update").html("Update Variable");
             
          var indicators = load_indicators();
          var input_types = load_input_types();
          var answer_data_types = load_answer_data_types();
          var status = load_status();
          
//          console.log(indicators);
          
         var indicator_label = "<option value=\"\">Choose Indicator</option>";
         var input_type_label = "<option value=\"\">Choose Input Type</option>";
         var answer_data_type_label = "<option value=\"\">Choose Answer Data Type</option>";
         var required_label = "<option value=\"\">Choose If Required?</option>";
         var unique_label = "<option value=\"\">Choose If answer Unique?</option>";
         var status_label = "<option value=\"\">Choose If Variable Active?</option>";
         
         // for indicators
        for(var i=0;i<indicators.length;i++){
        if(parseInt(indicator_id)===indicators[i].id){
            indicator_label+="<option value=\""+indicators[i].id+"\" selected>"+indicators[i].name+"</option>";
        }
        else{
           indicator_label+="<option value=\""+indicators[i].id+"\">"+indicators[i].name+"</option>";  
        }
        }
        
        // for input types
        for(var i=0;i<input_types.length;i++){
        if(parseInt(input_type)===input_types[i].id){
            input_type_label+="<option value=\""+input_types[i].id+"\" selected>"+input_types[i].name+"</option>";
        }
        else{
            input_type_label+="<option value=\""+input_types[i].id+"\" >"+input_types[i].name+"</option>";
        }
        }
        
        // for answer data types
        for(var i=0;i<answer_data_types.length;i++){
        if(parseInt(answer_data_type)===answer_data_types[i].id){
            answer_data_type_label+="<option value=\""+answer_data_types[i].id+"\" selected>"+answer_data_types[i].name+"</option>";
        }
        else{
            answer_data_type_label+="<option value=\""+answer_data_types[i].id+"\" >"+answer_data_types[i].name+"</option>";
        }
        }
        
        // for frequency
        for(var i=0;i<status.length;i++){
        if(parseInt(is_required)===status[i].id){
            required_label+="<option value=\""+status[i].id+"\" selected>"+status[i].name+"</option>";
        }
        else{
            required_label+="<option value=\""+status[i].id+"\" >"+status[i].name+"</option>";
        }
        }
        
        // for frequency
        for(var i=0;i<status.length;i++){
        if(parseInt(is_unique)===status[i].id){
            unique_label+="<option value=\""+status[i].id+"\" selected>"+status[i].name+"</option>";
        }
        else{
            unique_label+="<option value=\""+status[i].id+"\" >"+status[i].name+"</option>";
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
        
                   
        $("#question_id").val(id);
        $("#question").val(question);
        
        $("#indicator_id").html(indicator_label);
        $("#input_type").html(input_type_label);
        $("#answer_data_type").html(answer_data_type_label);
        $("#question_mandatory").html(required_label);
        $("#answer_unique").html(unique_label);
        $("#status").html(status_label);
         
       } 
  
  $("#update").click(function(){
  
  });
  
  
  
  
    </script>
    
    <script>
        $(document).ready(function(){
         $("#add_new").click(function(){ 
             $("#title").html("Add New Variable");
             $("#update").html("Add Variable");
             
               clear_entries(); 
               load_new_form();
               
         });
        
        });
        </script>
  <script>  
     function clear_entries(){
         
        $("#indicator_id").val("");
        $("#question").val("");
//        $("#section").html("");
        $("#input_type").html("");
        $("#answer_data_type").html("");
        $("#question_required").html("");
        $("#answer_unique").html("");
        $("#status").html("");
         
     }     
     
    function load_new_form(){
                     
          var indicators = load_indicators();
          var input_types = load_input_types();
          var answer_data_types = load_answer_data_types();
          var status = load_status();
          
         var indicator_label = "<option value=\"\">Choose Indicator</option>";
         var input_type_label = "<option value=\"\">Choose Input Type</option>";
         var answer_data_type_label = "<option value=\"\">Choose Answer Data Type</option>";
         var required_label = "<option value=\"\">Choose If Required?</option>";
         var unique_label = "<option value=\"\">Choose If answer Unique?</option>";
         var status_label = "<option value=\"\">Choose If Variable Active?</option>";
         
         // for indicators
        for(var i=0;i<indicators.length;i++){
           indicator_label+="<option value=\""+indicators[i].id+"\">"+indicators[i].name+"</option>";  
        }
        
        // for input types
        for(var i=0;i<input_types.length;i++){
            input_type_label+="<option value=\""+input_types[i].id+"\" >"+input_types[i].name+"</option>";
        }
        
        // for answer data types
        for(var i=0;i<answer_data_types.length;i++){
            answer_data_type_label+="<option value=\""+answer_data_types[i].id+"\" >"+answer_data_types[i].name+"</option>";
        }
        
        // for frequency
        for(var i=0;i<status.length;i++){
            required_label+="<option value=\""+status[i].id+"\" >"+status[i].name+"</option>";
        }
        
        // for frequency
        for(var i=0;i<status.length;i++){
            unique_label+="<option value=\""+status[i].id+"\" >"+status[i].name+"</option>";
        }
        
        // for status
        for(var i=0;i<status.length;i++){
          status_label+="<option value=\""+status[i].id+"\" >"+status[i].name+"</option>";
        }
        
                   
        $("#question_id").val("");
        $("#question").val("");
        
        $("#indicator_id").html(indicator_label);
        $("#input_type").html(input_type_label);
        $("#answer_data_type").html(answer_data_type_label);
        $("#question_mandatory").html(required_label);
        $("#answer_unique").html(unique_label);
        $("#status").html(status_label);
          
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
          
   function load_input_types(){ 
       var data=[];
    $.ajax({
        url:'load_input_types',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info;
  }
        
   }) ;
   return data;
   }
   
     function load_answer_data_types(){ 
       var data=[];
    $.ajax({
        url:'load_answer_data_types',
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
//alert("called");
        
   var id = $("#question_id").val();
   var indicator_id = $("#indicator_id").val();
   var question =  $("#question").val();
   var input_type =   $("#input_type").val(); 
   var answer_data_type =  $("#answer_data_type").val();
   var question_mandatory =   $("#question_mandatory").val();
   var answer_unique =   $("#answer_unique").val();
   var status =  $("#status").val();
//  alert("reached here");
        var errors = "", error_count=0;
    if(question.length<2){
        error_count++;
        errors+=""+error_count+". Enter a valid variable name <br>";
    }  
    if(indicator_id.length===0){
        error_count++;
        errors+=""+error_count+". Select indicator <br>";
    }  
    if(input_type.length===0){
        error_count++;
        errors+=""+error_count+". Select input type <br>";
    }  
    if(answer_data_type.length===0){
        error_count++;
        errors+=""+error_count+". Select answer data type <br>";
    }  
    if(question_mandatory.length===0){
        error_count++;
        errors+=""+error_count+". Select if the variable is mandatory <br>";
    }  
    if(answer_unique.length===0){
        error_count++;
        errors+=""+error_count+". Select value is unique <br>";
    }  
    if(status.length===0){
        error_count++;
        errors+=""+error_count+". Choose variable status <br>";
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
        var form_data = {"id":id,"indicator_id":indicator_id,"question":question,"input_type":input_type,"answer_data_type":answer_data_type,"required":question_mandatory,"value_unique":answer_unique,"status":status};
        save_question(form_data);
    }
    
});
        });

function save_question(data){
     var theme="";
        var url = "save_question";
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
        
       $("#input_type").change(function(){
         var input_type = $("#input_type").val();
         var options="";
         
         if(input_type==="2"){ // date
           options="_3_";  
         } 
         else if(input_type==="3" || input_type==="4"){ // selected
          options="_2_";     
         }
         
         else if(input_type==="5"){ // textarea
          options="_1_";
         }
         
         else if(input_type==="1"){ // input text or number
           options="_1_2_";   
         }
        
        load_data_type(options);
        
        
       });
        
        
        
      });      
            
            
          function load_data_type(options) {
//              var answer_data_type_label = "<option value=\"\">Choose Answer Data Type</option>";
              var answer_data_type_label = "";
             var answer_data_types = load_answer_data_types();
             
        // for answer data types
        for(var i=0;i<answer_data_types.length;i++){
          if(options.includes("_"+answer_data_types[i].id+"_")){
            answer_data_type_label+="<option value=\""+answer_data_types[i].id+"\" >"+answer_data_types[i].name+"</option>";
        }
        }
        
         $("#answer_data_type").html(answer_data_type_label);
        
          } 
            
            </script>
        
        
        
   <script>
function manage_answers(id,question){ 
    $("#question_vl").html(question);
    var value_labels = load_value_labels();
    var selected_answers="_";
    
//    var answer_label = "<option value=\"\">Choose one</option>";
    var answer_label = "";
     $.ajax({
        url:'load_selected_possible_answers?id='+id,
        type:"get",
        dataType:"json",
         async: false,
        success:function(data){
        
        for(var i=0;i<data.length;i++){
         selected_answers+= ""+data[i].value_label_id+"_";   
        }
        
        for(var i=0;i<value_labels.length;i++){
         if(selected_answers.includes("_"+value_labels[i].id+"_")){
         answer_label+="<option value=\""+value_labels[i].id+"\" selected>"+value_labels[i].name+"</option>";    
         } 
         else{
             answer_label+="<option value=\""+value_labels[i].id+"\" >"+value_labels[i].name+"</option>";
         }
        }
        
        
        $("#selected_question_id").val(id);
        $("#value_labels").html(answer_label);
  }
        
   }) ;
}


function load_value_labels(){
     var data=[];
    $.ajax({
        url:'load_possible_answers',
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
        $("#new_value_label").click(function(){
        var label_name = $("#label_name").val();
        var data = {"label_name":label_name};
        
             var theme="";
        var url = "save_value_label";
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
        
        
        });    
        });   
           </script> 
           
           <script>
              $("#update_answers").click(function(){
 
                var question_id = $("#selected_question_id").val();
                var ans = ""+$("#value_labels").val();
        var data = {"question_id":question_id,"labels":ans};
        
             var theme="";
        var url = "save_question_value_labels";
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
        
        
        
        
        
              }); 
               
               </script>
</html>
