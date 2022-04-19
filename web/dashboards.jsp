<%-- 
    Document   : dashboards
    Created on : 23-Feb-2022, 09:25:48
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Dashboards</title>
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

.parent {
    display: flex;
    clear:both;
}
.child1 {
    float:left;
    flex: 1;
}
.child2 {
    float:right;
    text-align: right;
}


   .flex-container {
    display: flex;
    margin-top: 10px;
    /*border: 1px dotted gray;*/
}

.flex-child {
    flex: 1;
    border: 2px;
    margin: 1px 5px 1px 5px;
}  

.flex-child:first-child {
    margin-right: 2px;
} 
.flex-child:last-child {
    margin-right: 2px;
} 

    </style>   
    
<script src="highcharts/code/highcharts.js"></script>
<script src="highcharts/code/modules/drilldown.js"></script>
<script src="highcharts/code/modules/exporting.js"></script>
<script src="highcharts/code/modules/export-data.js"></script>
<script src="highcharts/code/modules/accessibility.js"></script> 


    
    
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <%@include file="menu/top.jsp"%>
    <%@include file="menu/menu.jsp"%>
         
         <%if(session.getAttribute("dashboard")!=null){
      if(session.getAttribute("dashboard").toString().equals("1")){%>       
      
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
                  <h3 class="card-title" style="text-align: center;font-weight: 900;">Dashboards</h3>
              </div>
                <div class="card-header flex-container" style="background: #edf7f9;">
                  <div class="flex-child">
                      <select class="form-control select2" id="county" name="county" data-placeholder="Select counties" required="true" multiple="true"> </select>
                  </div>
                  <div class="flex-child">
                      <select class="form-control select2" id="sub_county" name="sub_county" data-placeholder="Select sub counties"  required="true" multiple="true"> </select>
                  </div>
                  <div class="flex-child">
                      <select class="form-control select2" id="facility" data-placeholder="Select facilities" name="facility"  required="true" multiple="true"> </select>
                  </div>
                      <div class="flex-child">
                          <input type="text" class="form-control datepicker" id="start_date" name="start_date"  required="true" placeholder="Start Date" autocomplete="off">
                      </div>
                      <div class="flex-child">
                      <input type="text" class="form-control datepicker" id="end_date" name="end_date"  required="true" placeholder="End Date" autocomplete="off">
                    </div>
                      <div class="flex-child">
                          <button type="submit" id="submit" class="btn btn-info btn-block">Filter Data</button> 
                      </div>  
                  
              </div>
                         <div class="card-body">        
                     
                   <!-- Small boxes (Stat box) -->
        <div class="row">
 
             <div class="col-lg-4 col-6">
            <!-- small box -->
            <div id="label_tested" class="small-box bg-info">
              <div class="inner">
                 <div class="parent">
                      <div class="child1"><h3><b id="tested"></b></h3></div>
                      <div class="child2"> </div>
                  </div>
                <p>Total Tested</p>
              </div>
            </div>
          </div>
            
            
            
          <div class="col-lg-4 col-6">
            <!-- small box -->
            <div id="label_positive" class="small-box bg-warning">
              <div class="inner">
                  <div class="parent">
                      <div class="child1"><h3><b id="positive"></b></h3></div>
                      <div class="child2"><h3 style="text-align: right;"><b id="positivity"></b><sup style="font-size: 20px">%</sup></h3> </div>
                  </div>
                <p>Total Tested Positive</p>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-6">
            <!-- small box -->
            <div id="label_linked" class="small-box bg-success">
              <div class="inner">
                 <div class="parent">
                      <div class="child1"><h3><b id="linked"></b></h3></div>
                      <div class="child2"><h3 style="text-align: right;"><b id="linkage"></b><sup style="font-size: 20px">%</sup></h3> </div>
                  </div> 
                <p>Total Linked to ART</p>
              </div>
            </div>
          </div>
          <!-- ./col -->
        </div>                            
                             
           
                   
                   <div class="row"> 
                  
                   <div class="col-lg-3 col-3">
                 <figure class="highcharts-figure">
                <div id="pns"></div>
            </figure>      
                   </div>
                       
                        <div class="col-lg-6 col-6">
                 <figure class="highcharts-figure">
                <div id="gain_losses"></div>
            </figure>      
                   </div>
                       
                   <div class="col-lg-3 col-3">
                 <figure class="highcharts-figure">
                <div id="tx_new"></div>
            </figure>      
                   </div>
                       
                   </div>
                   
                   
                   
                   <div class="row">
                       
                   </div>
                             
                             
                             
                         </div>    
                   
                </div>
          </div>
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
               function load_locations(){
                $.ajax({
                    url:'load_logged_user_location',
                    type:"get",
                    dataType:"json",
                    success:function(data){
                       
                     populate_counties(data.counties);
                     populate_sub_counties(data.sub_counties);
                     populate_facilities(data.facilities);
                    }  
                });     
               } 
                
                function populate_counties(data){
                  var output="<option value=\"\"></option>";
                    for(var i=0;i<data.length;i++){
                     output+="<option value=\""+data[i].id+"\">"+data[i].name+"</option>";   
                    }
                    $("#county").html(output);
                    $("#county").select2();
                }
                function populate_sub_counties(data){
                     var output="<option value=\"\"></option>";
                    for(var i=0;i<data.length;i++){
                     output+="<option value=\""+data[i].id+"\">"+data[i].name+"</option>";   
                    }
                    $("#sub_county").html(output);
                    $("#sub_county").select2();
                }
                function populate_facilities(data){
                     var output="<option value=\"\"></option>";
                    for(var i=0;i<data.length;i++){
                     output+="<option value=\""+data[i].id+"\">"+data[i].name+"</option>";   
                    }
                    $("#facility").html(output);
                    $("#facility").select2();
                }
                
                
                </script>
            
            
            
            
          <script>
            $(document).ready(function(){
        load_locations();
       $('#county').select2(); 
       $('#sub_county').select2(); 
       $('#facility').select2(); 
       
       
       $("#submit").click(function(){
         load_dashboard();  
       });
       
       
       
       $('#county').change(function(){
        // load subcounties
        var counties = ""+$("#county").val();
        $("#sub_county").html("");
        $("#facility").html("");
        load_sub_counties(counties);
       }); 
       $('#sub_county').change(function(){
        // load facilities
        var sub_counties = ""+$("#sub_county").val();
        $("#facility").html("");
        load_facilities(sub_counties);
       });
      
            }) ;
            
            
            function load_sub_counties(counties){
                $.ajax({
                    url:'load_user_sub_counties?county_ids='+counties,
                    type:"get",
                    dataType:"json",
                    success:function(data){
                    var output="<option value=\"\"></option>";
                    for(var i=0;i<data.length;i++){
                      output+="<option value=\""+data[i].id+"\">"+data[i].name+"</option>";  
                    }
                    $("#sub_county").html(output);
                    $('#sub_county').select2(); 
                    }  
                });
            }
            function load_facilities(sub_counties){
                $.ajax({
                    url:'load_user_facilities?sub_county_ids='+sub_counties,
                    type:"get",
                    dataType:"json",
                    success:function(data){
                    var output="<option value=\"\"></option>";
                    
                    for(var i=0;i<data.length;i++){
                      output+="<option value=\""+data[i].id+"\">"+data[i].name+"</option>";  
                    }
          $("#facility").html(output);
          $('#facility').select2(); 
                    }  
                });
            }
              </script>
            
    <script>  
   $(document).ready(function() {  
    default_dates();   
    load_dashboard();   
        
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
}); 
    </script>
    
    
   <script> 
    function default_dates(){
        var date = new Date();
        var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
        
        // first day 
        var day,month,year,formatted_date;
        
        // first date
        day = String(firstDay.getDate()).padStart(2, '0');
        month = String(firstDay.getMonth() + 1).padStart(2, '0'); 
        year = firstDay.getFullYear();
        
        formatted_date = year+"-"+month+"-"+day;
        $("#start_date").val(formatted_date);
        
        
        // today date
        day = String(date.getDate()).padStart(2, '0');
        month = String(date.getMonth() + 1).padStart(2, '0'); 
        year = date.getFullYear();
        
        formatted_date = year+"-"+month+"-"+day;
   
        $("#end_date").val(formatted_date);
    }
    
    </script>
    <script>
function load_dashboard(){ 
    var counties = ""+$("#county").val();
    var sub_counties = ""+$("#sub_county").val();
    var facilities = ""+$("#facility").val();
    var start_date = ""+$("#start_date").val();
    var end_date = ""+$("#end_date").val();
    
    var url="dashboards?counties="+counties+"&sub_counties="+sub_counties+"&facilities="+facilities+"&start_date="+start_date+"&end_date="+end_date;
    
  $.ajax({
        url:url,
        type:"get",
        dataType:"json",
        success:function(data){
        if(data.code>0){
        var hts = data.hts;
        manage_hts(hts.tested,hts.positive,hts.linked);
        net_gain_losses(data.gain_losses);
        pns(data.pns);
        tx_new(data.tx_new);
            }
            else{
            var theme="bg-danger";
            var header="<b>Error</b>";
            $.jGrowl(data.message, {
                header: header,
                theme: theme
                }); 
                    
            }
        }  
});
}

function manage_hts(tested,positive,linked){
         $("#tested").html(tested);
         $("#positive").html(positive);
         $("#linked").html(linked);
         
         var positivity = 0;
         var linkage=0;
         
        if(tested>0){positivity = ((positive*100)/tested).toFixed(1);}
        if(positive>0){linkage = ((linked*100)/positive).toFixed(1);}
         
         
         $("#positivity").html(positivity);
         $("#linkage").html(linkage);
         
      
        if(positivity<3){ // Danger not doing well
          $("#label_positive").removeClass();  
          $("#label_positive").addClass("small-box btn-danger"); 
        }
       else if(positivity>=3 && positivity<5){ // warning 
          $("#label_positive").removeClass();  
          $("#label_positive").addClass("small-box btn-warning"); 
        }
       else if(positivity>=5 ){ // green doing well
         $("#label_positive").removeClass();  
         $("#label_positive").addClass("small-box btn-success");    
        }
        else{
         $("#label_positive").removeClass();  
         $("#label_positive").addClass("small-box btn-info");   
        }
         
      
        if(linkage<80){ // Danger not doing well
          $("#label_linked").removeClass();  
          $("#label_linked").addClass("small-box btn-danger"); 
        }
       else if(linkage>=80 && linkage<90){ // warning 
          $("#label_linked").removeClass();  
          $("#label_linked").addClass("small-box btn-warning"); 
        }
       else if(linkage>=90 ){ // green doing well
         $("#label_linked").removeClass();  
         $("#label_linked").addClass("small-box btn-success");    
        }
        else{
         $("#label_linked").removeClass();  
         $("#label_linked").addClass("small-box btn-info");   
        }   
}


function net_gain_losses(data){
    var net_gain = (data.tx_new) + (data.transfer_in) + (data.iit_returned) + (data.transferred_out*-1) +  (data.died*-1) + (data.stopped*-1) + (data.iits*-1);
    var label = "";
    if(net_gain>0){  label="Net Gain (Inflows>Outflows)";}
    else if(net_gain<0){  label="Net Loss (Inflows<Outflows)";}
    else{  label="No Loss/Gain";}
    
    var all_data = "[{\"name\":\"TX_New\",\"data\":["+data.tx_new+"]},{\"name\":\"Transfer-In\",\"data\":["+data.transfer_in+"]},{\"name\":\"IIT Returned\",\"data\":["+data.iit_returned+"]},{\"name\":\"Transferred-Out\",\"data\":["+(data.transferred_out*-1)+"]},{\"name\":\"Died\",\"data\":["+(data.died*-1)+"]},{\"name\":\"Stopped\",\"data\":["+(data.stopped*-1)+"]},{\"name\":\"IITs\",\"data\":["+(data.iits*-1)+"]},{\"name\": \""+label+"\",\"data\":["+net_gain+"]}]";      
    var color_gain_loss = "";
        if(net_gain>0){
            color_gain_loss = "#155a10";
        }
        else{
            color_gain_loss = "#ff0808";
        }
var json_data = JSON.parse(all_data);

console.log(json_data);
 
 Highcharts.chart('gain_losses', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Net Gain/Losses'
    },
    xAxis: {
        categories: ['Net Gain/Losses']
    },
    yAxis: {
        title: {
            text: 'Number of Patients'
        }
    },
     plotOptions: {
        series: {
            dataLabels: {
                enabled: true
            }
        }
    },
    credits: {
        enabled: false
    },
    series: json_data,
   dataLabels: {
                enabled: true,
                formatter: function() {
                    if (this.y === 0) {
                        return null;
                    }
                    return this.y;
                },
                style: {
                    color: 'white'
                }
            } ,
    colors: [
        '#47cbee',
        '#659efa',
        '#bafa11',
        '#f90feb',
        '#5d0810',
        '#a22afa',
        '#ffbd00',
        color_gain_loss
    ]
});

}

function pns(data){

    var all_data = "[{\"name\":\"Tested\",\"data\":["+data.tested+"]},{\"name\":\"Positive\",\"data\":["+data.positive+"]},{\"name\":\"Linked\",\"data\":["+data.linked+"]}]";      
console.log(all_data);
var json_data = JSON.parse(all_data);


 Highcharts.chart('pns', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Index Testing'
    },
    xAxis: {
        categories: ['Index Testing']
    },
        yAxis: {
        title: {
            text: 'Number of Patients'
        }
    },
     plotOptions: {
        series: {
            dataLabels: {
                enabled: true
            }
        }
    },
    credits: {
        enabled: false
    },
    series: json_data,
   dataLabels: {
                enabled: true,
                formatter: function() {
                    if (this.y === 0) {
                        return null;
                    }
                    return this.y;
                },
                style: {
                    color: 'white'
                }
            }
});

}


function tx_new(tx_new){
  Highcharts.chart('tx_new', {
    chart: {
        type: 'pie'
    },
    title: {
        text: 'New on Treatment'
    },
    subtitle: {
        text: ''
    },

    accessibility: {
        announceNewData: {
            enabled: true
        },
        point: {
            valueSuffix: ''
        }
    },

    plotOptions: {
        series: {
            dataLabels: {
                enabled: true,
                format: '{point.name}: {point.y}'
            }
        }
    },

    tooltip: {
        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
    },

    series: [
        {
            name: "New on Treatment",
            colorByPoint: true,
            data: [
                {
                    name: "Male",
                    y: tx_new.male_total,
                    drilldown: "Male"
                },
                {
                    name: "Female",
                    y: tx_new.female_total,
                    drilldown: "Female"
                }
            ]
        }
    ],

    drilldown: {
        series: [
            {
                name: "Male",
                id: "Male",
                data: [
                    [
                        "<10 Years",
                        tx_new.male_less_10
                    ],
                    [
                        "10-14 Years",
                        tx_new.male_10_14
                    ],
                    [
                        "15-19 Years",
                        tx_new.male_15_19
                    ],
                    [
                        "20-24 Years",
                        tx_new.male_20_24
                    ],
                    [
                        "25-49 Years",
                        tx_new.male_25_49
                    ],
                    [
                        "50+ Years",
                        tx_new.male_above_50
                    ]
                ]
            },
            {
                name: "Female",
                id: "Female",
                data: [
                    [
                        "<10 Years",
                        tx_new.female_less_10
                    ],
                    [
                        "10-14 Years",
                        tx_new.female_10_14
                    ],
                    [
                        "15-19 Years",
                        tx_new.female_15_19
                    ],
                    [
                        "20-24 Years",
                        tx_new.female_20_24
                    ],
                    [
                        "25-49 Years",
                        tx_new.female_25_49
                    ],
                    [
                        "50+ Years",
                        tx_new.female_above_50
                    ]
                ]
            }
        ]
    }
});  
}
    </script>
    
  
</html>
