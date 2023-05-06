<%-- 
    Document   : data_entry
    Created on : 28-Jan-2022, 21:39:22
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%
String indicator_id = request.getParameter("indicator");
String sec = request.getParameter("sec");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Data Entry</title>
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
      
         <%if(session.getAttribute("hts")!=null && session.getAttribute("prevention")!=null && session.getAttribute("treatment")!=null && session.getAttribute("vl")!=null && session.getAttribute("tb")!=null){
      String hts,prevention,treatment,vl,tb;
       hts = session.getAttribute("hts").toString();
       prevention = session.getAttribute("prevention").toString();
       treatment = session.getAttribute("treatment").toString();
       vl = session.getAttribute("vl").toString();
       tb = session.getAttribute("tb").toString();
       
if((sec.equals("1") && prevention.equals("1")) || (sec.equals("2") && hts.equals("1")) || (sec.equals("3") && treatment.equals("1")) || (sec.equals("4") && vl.equals("1")) || (sec.equals("5") && tb.equals("1"))){%>  
      
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
                  <h3 class="card-title" id="indicator_name" style="text-align: center;font-weight: 900;">Indicator Management Module</h3>
                 <br>
                 <h6 id="description" style="color:red; font-style: italic; font-size: 16px;"></h6>
              </div>
                         <div class="card-body"> 
                             
                <form class="form" action="#" id="form" style="margin-top: 0%;">
                        <div id="questions">
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
                
</body>
 <script rel="stylesheet" href="plugins/select2/js/select2.full.min.js"></script>
    <script>
   $(document).ready(function() {
        load_data();
}); 
    </script>
<script>
      function load_data(){ 
          var output="";
          var date_elems = [];
          
          var indicator_id = <%out.println(indicator_id);%>
          var question_id,label,input_type,answer_data_type,required,required_label;
          var label_text_align = "right", value_length="";
       $.ajax({
        url:'load_questions?indicator_id='+indicator_id,
        type:"get",
        dataType:"json",
        success:function(response){ 
        
        // for facilities
        $("#indicator_name").html("Indicator Name : "+response.indicator_name);   
        
       
        $("#description").html(response.description);
        
        var questions = response.questions;
        
        
        
        output+="<div class=\"flex-container\" id=\"facility_id_label\"><div class=\"flex-child\">Health Facility <b style=\"color:red;\">*</b>: </div><div class=\"flex-child\"><select name=\"facility_id\" id=\"facility_id\" class=\"form-control\" required ></select></div></div>";  
        //end of facilities
      
    var passed_validation=0;    
   var date_label="";
   if(indicator_id===6){date_label = "Date Tested";}
   else if(indicator_id===8){date_label = "Date Started ART";}
   else{date_label="Date";}
   
       output+="<input type=\"hidden\" name=\"indicator\" value=\""+indicator_id+"\" id=\"indicator\" class=\"form-control\">";   
       output+="<input type=\"hidden\" name=\"entry_status\" value=\"\" id=\"entry_status\" class=\"form-control\">";   
       output+="<input type=\"hidden\" name=\"entry_key\" value=\"\" id=\"entry_key\" class=\"form-control\">";   
       output+="<div class=\"flex-container\" id=\"label_date\"><div class=\"flex-child\">"+date_label+" <b style=\"color:red;\">*</b>: </div><div class=\"flex-child\"><input type=\"text\" name=\"date\" value=\"\" required autocomplete=\"off\" id=\"date\" class=\"form-control datepicker\" placeholder=\"\" "+required+" ></div></div>";   
    output+="<div id=\"prev_data\"></div>";
    output+="<div id=\"entry_elements\">";
    
    for(var i=0;i<questions.length;i++){
         question_id = questions[i].id;
//         indicator_id = questions[i].indicator_id;
         label = questions[i].label;
         input_type = questions[i].input_type;
         answer_data_type =  questions[i].answer_data_type;
         
         if(question_id===32 || question_id===44){
            value_length = "min=\"1000000000\" max=\"9999999999\" ";
            }
            
            else{
           value_length = "min=\"0\"";
            }
         
         if(questions[i].required===1){
            required_label =" <b style=\"color:red\"> *</b>";
            required="required=\"true\"";
            }
            else{
          required_label ="";  
          required="";
            }
         
         switch(input_type){
            case 1:  // text
                if(answer_data_type===2){
//                    if(){}
                 output+="<div class=\"flex-container\" id=\"label_"+question_id+"\" ><div class=\"flex-child\" id=\"title_"+question_id+"\"> "+label+" "+required_label+": </div><div class=\"flex-child\"><input type=\"number\" "+value_length+" name=\"name_"+question_id+"\" value=\"\" autocomplete=\"off\" id=\"id_"+question_id+"\" class=\"form-control\" placeholder=\""+label+"\" "+required+"></div></div>";  
                
                    }
                else{
                 output+="<div class=\"flex-container\" id=\"label_"+question_id+"\"><div class=\"flex-child\" id=\"title_"+question_id+"\">"+label+" "+required_label+": </div><div class=\"flex-child\"><input type=\"text\"  "+value_length+"  name=\"name_"+question_id+"\" value=\"\" autocomplete=\"off\" id=\"id_"+question_id+"\" class=\"form-control\" placeholder=\""+label+"\" "+required+"></div></div>";  
               }
                 break;
            case 2: 
               output+="<div class=\"flex-container\" id=\"label_"+question_id+"\"><div class=\"flex-child\" id=\"title_"+question_id+"\">"+label+" "+required_label+": </div><div class=\"flex-child\"><input type=\"text\" name=\"name_"+question_id+"\" value=\"\" autocomplete=\"off\" id=\"id_"+question_id+"\" class=\"form-control\" placeholder=\""+label+"\" "+required+" ></div></div>";      
                date_elems.push(question_id);
                break;
             
            case 3:  
               output+="<div class=\"flex-container\" id=\"label_"+question_id+"\"><div class=\"flex-child\" id=\"title_"+question_id+"\">"+label+" "+required_label+": </div><div class=\"flex-child\"><select name=\"name_"+question_id+"\" value=\"\" autocomplete=\"off\" id=\"id_"+question_id+"\" class=\"form-control select2\" "+required+">";  
               output+=get_answers(questions[i].answers);  
               output+="</select></div></div>";  
                    
                break;
                    
            case 4:
              output+="<div class=\"flex-container\" id=\"label_"+question_id+"\"><div class=\"flex-child\" id=\"title_"+question_id+"\">"+label+" "+required_label+": </div><div class=\"flex-child\"><select name=\"name_"+question_id+"\" value=\"\" id=\"id_"+question_id+"\" class=\"form-control select2\" multiple "+required+">";  
               output+=get_answers(questions[i].answers);  
               output+="</select></div></div>";    
                
               break;
                
            case 5: 
              output+="<div class=\"flex-container\" id=\"label_"+question_id+"\"><div class=\"flex-child\" id=\"title_"+question_id+"\">"+label+" "+required_label+": </div><div class=\"flex-child\"><textarea name=\"name_"+question_id+"\" value=\"\" id=\"id_"+question_id+"\" class=\"form-control\" placeholder=\""+label+"\" "+required+"></textarea></div></div>";      
                
               break;   
                 
            default:
         }
         
         output+="<input type=\"hidden\" name=\"o_id_"+question_id+"\" value=\"\" id=\"o_id_"+question_id+"\" class=\"form-control\">";
   }
    
   output+="<br>\n\
            <div style=\"margin-top: 1px; color: orange; font-weight: 400;\" id=\"flags\"></div><br>\n\
            <div class=\"form-group\" style=\"width: 100%;\">"+
            "<button type=\"submit\" id=\"submit\" class=\"btn btn-primary btn-block\">Save</button>"+
            "</div>";
    output+="</div>";
//     alert(output);
    $("#questions").html(output);
    
    
    $('.select2').select2();
    
    // load facilities
     var facilities = load_facilities();
           var facility_data="<option value=\"\"></option>";
      
           for(var i=0;i<facilities.length;i++){
               if(facilities[i].pre_selected===1){
              facility_data+="<option value=\""+facilities[i].id+"\" selected>"+facilities[i].name+"</option>";     
            }
            else{
          facility_data+="<option value=\""+facilities[i].id+"\">"+facilities[i].name+"</option>";
           }
       }
      $("#facility_id").html(facility_data);
      $("#facility_id").select2();
      
    // end of loading facilities
      
      
      // disable ANC
      if(indicator_id===10){
         $("#label_56").hide(); 
           $("#id_56").removeAttr('required'); 
        } 
        
        $("#id_55").change(function(){
       check_anc();     
    });
        
        // when dates change
    $("#id_107").change(function(){
     hide_non_linkage(); 
    });
        // when dates change
    $("#id_106").change(function(){
     hide_linked_other(); 
    });
        // when dates change
    $("#date").change(function(){
     check_entries(); 
    });
    
    $("#facility_id").change(function(){
     check_entries(); 
    });
   
    $("#facility_id").change(function(){
   var facility_id = $("#facility_id").val();
    $.ajax({
        url:'facility_set_session?facility_id='+facility_id,
        type:"get",
        dataType:"json",
        success:function(){ 
        }
        });
});    
        
    // end when dates and facility change to check if data already exist
    
    autohide();
    
    $("#id_30").change(function(){ 
//        alert("called");
         hide_linkage();
        });
    
            $("#id_35").change(function(){
         hide_hts_tb();
        });
    
    
   var days = (load_dates_unlock()*-1); 
    
  $("#date").datepicker({
      changeYear: true,
      changeMonth: true,
      dateFormat: 'yy-mm-dd',
      minDate: days,
      maxDate: 0
  }); 

//alert(date_elems.length);

// loop through date elements and add year to elemented with date
for(var i=0;i<date_elems.length;i++){
// alert(date_elems[i]);
 $("#id_"+date_elems[i]+"").datepicker({
      changeYear: true,
      changeMonth: true,
      dateFormat: 'yy-mm-dd',
      yearRange: "-120:+0",
      minDate: '-120y',
      maxDate: 0
  });        
        
}



if(indicator_id===6){
//    alert("here");
       // load facilities
     var facilities = load_all_facilities();
           var facility_data="<option value=\"\"></option>";
      
           for(var i=0;i<facilities.length;i++){
               if(facilities[i].pre_selected===1){
              facility_data+="<option value=\""+facilities[i].id+"\" selected>"+facilities[i].name+"</option>";     
            }
            else{
          facility_data+="<option value=\""+facilities[i].id+"\">"+facilities[i].name+"</option>";
           }
       }
//       alert(facilities.length);
      $("#id_113").html(facility_data);
      $("#id_113").select2();
      
    }

// validate PMTCT Flags       
       
       $("#id_49").keyup(function(){
      validate_ca_screening_flags();
        });
       $("#id_50").keyup(function(){
      validate_ca_screening_flags();
        });
        
        
       $("#id_60").keyup(function(){
      validate_pmtct_flags();
        });
        
       $("#id_61").keyup(function(){
      validate_pmtct_flags();
        });
       $("#id_59").keyup(function(){
      validate_pmtct_flags();
        });
       $("#id_62").keyup(function(){
      validate_pmtct_flags();
        });
        
        
        if(indicator_id===12){
     $("#label_75").hide();
     $("#id_75").removeAttr('required');       
    }
        
        
        // for missed appointment 
        $("#id_74").keyup(function(){
       var missed = "0"+$("#id_74").val(); 
       if(parseInt(missed)>0){
           $("#id_75").attr('required', 'required');
           $("#label_75").show();
        }
        
        else{
         $("#id_75").val("");   
         $("#label_75").hide();
         $("#id_75").removeAttr('required'); 
        }
    });

    }
  }); 
  
    } 



function get_answers(answer_labels){
    var options="<option value=\"\">Choose Option</option>";
   
        for(var j=0;j<answer_labels.length;j++){
          options+="<option value=\""+answer_labels[j].id+"\">"+answer_labels[j].name+"</option>";  
        }
        
        return options;
}

   function load_facilities(){ 
       var data=[];
    $.ajax({
        url:'load_user_facilities',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
         data = info;
  }   
   }) ;
   return data;
   }
   function load_all_facilities(){ 
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
     $(document).ready(function(){

$("form").submit(function(e){
        e.preventDefault();

        var form_data = {};
        $.each($('#form').serializeArray(), function(_, kv) {
          form_data[kv.name] = kv.value;
        });
        
        var indicator_id = parseInt(form_data.indicator);
        
        var errors = 0;
 if(indicator_id===1){ errors+= validate_gend_gbv();}
 if(indicator_id===5){ errors+= validate_hts_tst(); }
  if(indicator_id===7){ errors+= validate_pns();}
  if(indicator_id===21){ errors+= validate_recency();}
//  if(indicator_id===9){ errors+= validate_ca_screening();} 
  if(indicator_id===10){ errors+= validate_pmtct();}
  if(indicator_id===12){ errors+= validate_retention();}
  if(indicator_id===13){ errors+= validate_suppression();}
  if(indicator_id===23) {errors+= validate_opd();}

       // 0725705194 brian safaricon home fibre
        
        if(errors===0){
         save_data(form_data); 
         }
    });
    
     });   
    
    
         function save_data(data){
        var theme="";
        var url = "save_data";
    $.post(url,data , function(output) {
                        output = JSON.parse(output);
                     var code = output.code;
                     var message = output.message;
                     var questions = output.questions;
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

});

//    $(document).ready(function(){
//       $.jGrowl('errors', {
//                position: 'top-center',
//                header: '<b>Error</b>',
//                theme: 'bg-danger'
//            });  
//    }) ;                           
    </script>
  
  <script>
                              
        function load_all_questions(indicator_id){
            var data=[];
        $.ajax({
        url:'load_all_questions',
        type:"get",
        dataType:"json",
        async: false,
        success:function(response){ 
         for(var i=0;i<response.length;i++){
             if(response[i].indicator_id===parseInt(indicator_id)){
                 var quiz = {};
                 quiz['id'] = response[i].id;
                 quiz['name'] = response[i].question;
                 data.push(quiz);
             }
         }  
        } 
        });
        
        return data;
        }                      
                              
                              
      function clear_entries(questions){
          for(var i=0;i<questions.length;i++){
              var question = questions[i];
              var id = question.id;
              $("#id_"+id).val('');
              $("#o_id_"+id).val('');
              
          }
          
//           $("date").val('');
      }   
      
      
      function autohide(){
        $("#label_31").hide();
        $("#label_32").hide();
        $("#label_33").hide(); 
        $("#label_36").hide();
        $("#label_106").hide();
        $("#label_107").hide();
        $("#label_111").hide();
        $("#label_112").hide();
        $("#label_113").hide();
        $("#label_118").hide();
      }
     
     
     
   function hide_linkage(){
       
    var linked = $("#id_30").val();

         if(linked==="1"){ // patient linked show fields
          $("#label_31").show();
          $("#label_32").show();
          $("#label_33").show();
          $("#label_106").show();
          $("#label_107").hide();
          $("#label_111").hide();
          $("#label_113").hide();
          
          // make entries required
          $("#id_31").attr('required', 'required');
          $("#id_32").attr('required', 'required');
          $("#id_33").attr('required', 'required');
          $("#id_106").attr('required', 'required');
          $("#id_107").removeAttr('required');
          $("#id_111").removeAttr('required');
          $("#id_112").removeAttr('required');
          $("#id_113").removeAttr('required');
          
          $("#id_107").val('');
          $("#id_111").val('');  
          $("#id_112").val('');  
            
         }
         else{ // hide linkage fields
          $("#label_31").hide();
          $("#label_32").hide();
          $("#label_33").hide(); 
          $("#label_106").hide();
          $("#label_112").hide();
          $("#label_113").hide();
          
          $("#id_31").val('');
          $("#id_32").val('');
          $("#id_33").val('');  
          $("#id_106").val('');  
          $("#id_112").val('');  
          
          
                   // remove required
          $("#id_31").removeAttr('required');
          $("#id_32").removeAttr('required');
          $("#id_33").removeAttr('required');
          $("#id_106").removeAttr('required');
          $("#id_112").removeAttr('required');
          $("#id_113").removeAttr('required');
          
          $("#label_107").show();
          $("#label_111").hide();
          
          $("#id_107").attr('required', 'required');
          $("#id_111").removeAttr('required');
         }   
   }
   
   
   function hide_non_linkage(){
     var non_linkage = $("#id_107").val(); 
     if(parseInt(non_linkage)===20){
         $("#label_111").show();
         $("#id_111").attr('required', 'required');
     }
     else{
      $("#label_111").hide();
      $("#label_111").val('');
      $("#id_111").removeAttr('required');   
     }
   }
   function hide_linked_other(){
     var non_linkage = $("#id_106").val(); 
     if(parseInt(non_linkage)===22){
         $("#label_112").show();
         $("#label_113").hide();
         $("#id_112").attr('required', 'required');
         $("#id_113").removeAttr('required'); 
         $("#label_113").val('');
     }
     else{
      $("#label_112").hide();
      $("#label_113").show();
      $("#label_112").val('');
      $("#id_112").removeAttr('required');   
       $("#id_113").attr('required', 'required');
     }
   }
    
    
   function hide_hts_tb(){
       var screened = $("#id_35").val();
       if(screened==="1"){ // patient screened
         $("#label_36").show(); 
         $("#id_36").attr('required', 'required');
       }
       else{
         $("#label_36").hide();  
         $("#id_36").val(''); 
         $("#id_36").removeAttr('required');
       }
   }
   
   
   function check_anc(){
     var pmtct_sdp = $("#id_55").val();
       if(pmtct_sdp==="9"){ // patient screened
         $("#label_56").show(); 
         $("#id_56").attr('required', 'required');
         
         $("#label_118").show(); 
         $("#id_118").attr('required', 'required');
         
         $("#title_57").html("Initial Test (ANC1) <b style=\"color:red\"> *</b>");
       }
       else{
         $("#label_56").hide();  
         $("#id_56").val(''); 
         $("#id_56").removeAttr('required');
         
         $("#label_118").hide();  
         $("#id_118").val(''); 
         $("#id_118").removeAttr('required');
         
         $("#title_57").html("Number tested (Initial test) <b style=\"color:red\"> *</b>");
       }   
   }
  </script> 
 
 <script>
  function validate_gend_gbv(){
      var sexual_cases = "0"+$("#id_1").val();
      var pep_cases = "0"+$("#id_4").val();
      
        var errors=0;

      if(parseInt(pep_cases)>parseInt(sexual_cases)){
          errors++;
//          
          $.jGrowl('Number of Pep Cases cannot be more than sexual cases reported', {
                header: '<b>Error</b>',
                theme: 'bg-danger'
            }); 
        }
        
      return errors;
    }       
     
     
     function validate_opd(){
                var opd_workload = "0"+$("#id_108").val();
                var screened = "0"+$("#id_20").val();
                var eligible = "0"+$("#id_21").val();
                var tested = "0"+$("#id_131").val();
               
              var error_message = "";
                var errors=0;
              
               if(parseInt(screened)>parseInt(opd_workload)){
           errors++;
           error_message+= errors+". Number Screened cannot be more than OPD Workload<br>";
       }
       if(parseInt(eligible)>parseInt(screened)){
           errors++;
           error_message+= errors+". Number Eligible cannot be more than number screened<br>";
       }
        if(parseInt(tested)>parseInt(eligible)){
           errors++;
           error_message+= errors+". Number tested cannot be more than number eligible<br>";
       }
       
               
        if(errors>0){
           $.jGrowl(error_message, {
                header: '<b>Error</b>',
                theme: 'bg-danger'
            }); 
        }
     
        return errors;
     }
    
   function validate_hts_tst(){

       var hts_tst = "0"+$("#id_22").val();
       var tst_male = "0"+$("#id_23").val();
       var tst_female = "0"+$("#id_109").val();
       var tst_female_24 = "0"+$("#id_24").val();
       var prep_eligible = "0"+$("#id_6").val();
       var prep_new = "0"+$("#id_7").val();
       var tst_male_24 = "0"+$("#id_110").val();
       
       
      var error_message = "";
      var errors=0;

       if((parseInt(tst_male)+parseInt(tst_female))!==parseInt(hts_tst)){
           errors++;
           error_message+= errors+". Number tested female and male cannot be more than total number tested <br>";
       }
       if(parseInt(tst_female_24)>parseInt(tst_female)){
           errors++;
           error_message+= errors+". Number of female 15-25 tested cannot be more than total females tested<br>";
       }
       if(parseInt(tst_male_24)>parseInt(tst_male)){
           errors++;
           error_message+= errors+". Number of male 15-25 tested cannot be more than total males tested<br>";
       }
       if(parseInt(prep_new)>parseInt(prep_eligible)){
           errors++;
            error_message+= errors+". Number PrEP new cannot be more than PrEP eligible<br>";
       }
    
        
        if(errors>0){
           $.jGrowl(error_message, {
                header: '<b>Error</b>',
                theme: 'bg-danger'
            }); 
        }
     
        return errors;
   }
    
     
    
   function validate_pns(){
       var tested = "0"+$("#id_40").val();
       var positive = "0"+$("#id_41").val();
       var linked = "0"+$("#id_42").val();
       
       
       
      var error_message = "";
      var errors=0;
      
       if(parseInt(positive)>parseInt(tested)){
           errors++;
           error_message+= errors+". Number positive cannot be more than tested<br>";
       }
       if(parseInt(linked)>parseInt(positive)){
           errors++;
           error_message+= errors+". Number linked cannot be more than number positive<br>";
       }
       
        if(errors>0){
           $.jGrowl(error_message, {
                header: '<b>Error</b>',
                theme: 'bg-danger'
            });  
        }
     
        return errors;
   }
    
    
   function validate_recency(){
       var pos = "0"+$("#id_119").val();
       var eligible = "0"+$("#id_120").val();
       var offered = "0"+$("#id_121").val();
       var enrolled = "0"+$("#id_122").val();
       
       
       
      var error_message = "";
      var errors=0;
      
       if(parseInt(eligible)>parseInt(pos)){
           errors++;
           error_message+= errors+". Number eligible for Recency testing cannot be more than number identified positive 15+ years<br>";
       }
       if(parseInt(offered)>parseInt(eligible)){
           errors++;
           error_message+= errors+". Number Offered Recency testing cannot be more than number eligible for Recency Testing<br>";
       }
       if(parseInt(enrolled)>parseInt(offered)){
           errors++;
           error_message+= errors+". Number enrolled in recency testing cannot be more than number offered Recency Testing<br>";
       }
       
        if(errors>0){
           $.jGrowl(error_message, {
                header: '<b>Error</b>',
                theme: 'bg-danger'
            });  
        }
     
        return errors;
   }
    
    
    
   function validate_ca_screening_flags(){
       var visits = "0"+$("#id_49").val();
       var screened = "0"+$("#id_50").val();
       
       
       
      var error_message = "";
      var errors=0;
      
       if(parseInt(screened)>parseInt(visits)){
           errors++;
           error_message+= errors+". Number screened should'nt be more than tested<br>";
       }
       
       
//        if(errors>0){
//           $.jGrowl(error_message, {
//                header: '<b>Error</b>',
//                theme: 'bg-danger'
//            });  
//        }
     $("#flags").html("<div style=\"font-weight:bold;\">Warnings:</div>"+error_message);
//        return errors;
   }
    
    
   function validate_pmtct(){
       var tested = "0"+$("#id_57").val();
       var neg = "0"+$("#id_58").val();
       var pos = "0"+$("#id_60").val();
       
       var sdp = parseInt($("#id_55").val());
       
       
       
      var error_message = "";
      var errors=0;
      
      if(sdp!==9){
       if((parseInt(neg)+parseInt(pos))!==parseInt(tested)){
           errors++;
           error_message+= errors+". Sum of Negative and Positive must be equal to tested<br>";
       }
   }
   else{
       var postanc1="0"+$("#id_118").val();
       
     if((parseInt(neg)+parseInt(pos))!==(parseInt(tested)+parseInt(postanc1))){
           errors++;
           error_message+= errors+". Sum of Negative and Positive must be equal to Initial tests (ANC1 & PostANC1 )<br>";
       }  
   }
      
       // flags
        if(errors>0){
           $.jGrowl(error_message, {
                header: '<b>Error</b>',
                theme: 'bg-danger'
            }); 
        }
        return errors;
   }
    
    
    
    function validate_pmtct_flags(){
       var kp = "0"+$("#id_59").val();
       var on_haart = "0"+$("#id_62").val();
       
       var np = "0"+$("#id_60").val();
       var new_haart = "0"+$("#id_61").val();
     
        
        var flags="",errors=0;
      
         // flags
       if(parseInt(np)!==parseInt(new_haart)){
           errors++;
           flags+= errors+". Number New Positive should be equal to newly started on HAART <br>";
       }
       if(parseInt(kp)!==parseInt(on_haart)){
           errors++;
           flags+= errors+". Number Known Positive should be equal to already on HAART <br>";
       }
       
           $("#flags").html("<div style=\"font-weight:bold;\">Warnings:</div>"+flags);
       
       
    }
 
    
   function validate_retention(){
       var booked = "0"+$("#id_71").val();
       var came_earlier = "0"+$("#id_72").val();
       var honored = "0"+$("#id_73").val();       
       var missed = "0"+$("#id_74").val();
       
       
      var error_message = "";
      var errors=0;
      
       if((parseInt(came_earlier)+parseInt(honored)+parseInt(missed))!==parseInt(booked)){
           errors++;
           error_message+= errors+". Sum of patients who came earlier, honored and missed appointment must be equal to number booked<br>";
       }
      
       // flags
        if(errors>0){
           $.jGrowl(error_message, {
                header: '<b>Error</b>',
                theme: 'bg-danger'
            });
 
        }
     
        return errors;
   }
   function validate_suppression(){
       var eligible = "0"+$("#id_81").val();
       var done = "0"+$("#id_82").val();
       
       var results = "0"+$("#id_83").val();       
       var supressed = "0"+$("#id_84").val();
       var not_suppressed = "0"+$("#id_85").val();
      
      var error_message = "";
      var errors=0;
      
       if(parseInt(done)>parseInt(eligible)){
           errors++;
           error_message+= errors+". Number of patients who received VL test should not be more than those who were eligible<br>";
       }
      
       if((parseInt(not_suppressed)+parseInt(supressed))!==parseInt(results)){
           errors++;
           error_message+= errors+". Sum of suppressed and not suppressed must be equal to number received results<br>";
       }
      
       // flags
        if(errors>0){
           $.jGrowl(error_message, {
                header: '<b>Error</b>',
                theme: 'bg-danger'
            });
        }
     
        return errors;
   }
 </script>
     
  <script>
     function check_entries(){

  var date = $("#date").val();
  var facility_id = $("#facility_id").val();
  var indicator_id = $("#indicator").val();   
  
  if(date!=="" && parseInt(indicator_id)===6){ // validate date linked 
        $( "#id_31" ).datepicker( "option", "minDate", new Date(date) );
  }
  
  if(date!=="" && facility_id!=="" && indicator_id!==""){
var form_data={"date":date,"facility_id":facility_id,"indicator_id":indicator_id};
check_previous_entries(form_data,1);
  }
  else{
   $("#prev_data").html("");    
   $("#prev_data").hide();    
  }
     }
     
     
    function load_edit_data(entry_key,indicator_id){
          var id,question_id,answer_data_type;

       $.ajax({
        url:'load_edit_data?entry_key='+entry_key,
        type:"get",
        dataType:"json",
        success:function(response){         
        var observations = response.obs;
        var date = response.date;
             
     var value = "";
     if(observations.length===0){ // clear 
      var quizes =  load_all_questions(indicator_id);
        clear_entries(quizes);
        $("#submit").html("Save");
        $("#submit").addClass("btn-primary");
        $("#submit").removeClass("btn-warning");
        
        $("#entry_status").val("");
        $("#entry_key").val("");
        }
        
        else{
             $("#submit").html("Update");
             $("#submit").addClass("btn-warning");
             $("#submit").removeClass("btn-primary");
             $("#entry_status").val("1"); 
             $("#entry_key").val(entry_key);
        }
    for(var i=0;i<observations.length;i++){
         id = observations[i].id;
         question_id = observations[i].question_id;
         answer_data_type =  observations[i].answer_data_type;
         
         
         if(answer_data_type===2){
             value = observations[i].numeric_value;
            }
            else{
             value = observations[i].text_value;   
            }
         
           $("#id_"+question_id).val(value);
           $("#o_id_"+question_id).val(id);
           
   }

   if(parseInt(indicator_id)===6){ // for hts pos  
  $("#date").val(date);
     hide_linkage();
     hide_hts_tb();
//  $("#date").prop('disabled', true);
        }
   
   else{ //enable date
//       $("#date").prop('disabled', false);
   }
    }
  });  
  
   $("#entry_elements").show();
   $("#prev_data").hide();  
    }
//    
   function check_previous_entries(data,check_type){
    var   url ="";
    if(check_type===1){ // normal check
      url = 'load_view_edit?indicator_id='+data.indicator_id+'&&date='+data.date+'&&facility_id='+data.facility_id;  
    }
    
    else if(check_type===2){ // unlinked clients
     url = 'load_unlinked_clients?facility_id='+data.facility_id;    
    }
    
    $("#entry_status").val(""); // reset to new
       var output="";
       $.ajax({
        url:url,
        type:"get",
        dataType:"json",
           async: false,
        success:function(response){         
        var observations = response;
        
        
     var entry_key,question,date,multiple_entries;   

        output+="<br><table class=\"table\"><tr><th>Date</th><th>Question & Answers</th><th id=\"\"></th></tr>";
    for(var i=0;i<observations.length;i++){
         entry_key = observations[i].entry_key;
         question = observations[i].q_a;
         date =  observations[i].date;
         multiple_entries =  observations[i].multiple_entries;
         output+="<tr><td>"+date+"</td><td>"+question+"</td><td><div onclick=\"load_edit_data('"+entry_key+"','"+data.indicator_id+"')\" class=\"btn btn-primary\">Edit</div></td></tr>";
   }
   
   output+="</table><br>";
 
     if(observations.length===0){ // clear 
      var quizes =  load_all_questions(data.indicator_id);
        clear_entries(quizes);
        $("#submit").html("Save");
        $("#submit").addClass("btn-primary");
        $("#submit").removeClass("btn-warning");
        output="";
        
        $("#entry_elements").show();
           hide_linkage();
           hide_hts_tb();
        }
        else{
          $("#prev_data").show();  
            
             if(multiple_entries===1){
              $("#entry_elements").show();   
             }
             else{
               $("#entry_elements").hide();     
             }
        }
    }
  });
  $("#prev_data").html(output);
   } 
  
         function check_unlinked(){

  var facility_id = $("#facility_id").val(); 
  var indicator_id = $("#indicator").val(); 
  
  if(facility_id!==""){
var form_data={"facility_id":facility_id,"indicator_id":indicator_id};
check_previous_entries(form_data,2);
  }
  else{
      $("#prev_data").hide();  
   $.jGrowl('Choose health facility to view and edit unlinked HTS Positive clients', {
                header: '<b>Error</b>',
                theme: 'bg-danger'
            });   
  }
     } 
     
        function load_dates_unlock(){ 
       var days=0;
    $.ajax({
        url:'load_dates_unlock',
        type:"get",
        dataType:"json",
         async: false,
        success:function(info){
        var data = info;
         days=data['days'];
  }   
   }) ;
   return days;
   }
 </script>     
</html>
