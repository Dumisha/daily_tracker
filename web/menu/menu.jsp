<%-- 
    Document   : men
    Created on : 09-Jul-2020, 17:59:18
    Author     : Geofrey Nyabuto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <aside class="main-sidebar sidebar-dark-primary elevation-4">

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="info">
            <a href="#" class="d-block" style="font-size: 200%;">Data Tracker</a>
         
          <b style="color:white;">
                 <%if(session.getAttribute("fullname")!=null){
                String name=session.getAttribute("fullname").toString();
                out.println(name); 
                 %> 
          <%}%>
          </b>
          <br>
           <b style="color:yellow;">
             <%if(session.getAttribute("level")!=null){
                String level=session.getAttribute("level").toString();
                out.println(level); 
            %> 
          <%}%>
          </b>
          
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">

            
          <li class="nav-item has-treeview" id="indicators"></li>
           
     <%if(session.getAttribute("hts")!=null){
      if(session.getAttribute("hts").toString().equals("1")){%> 
          <li class="nav-item has-treeview">
            <a href="unlinked.jsp" class="nav-link">
              <i class="nav-icon fa fa-clone"  aria-hidden="true"></i>
              <p>
                Un-linked clients
              </p>
            </a>
           
          </li>
         <%}}%> 
         
      
         
         
         
                   <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-ambulance"></i>
              <p>
                Commodity Mgt
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">2</span>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="commodities.jsp" class="nav-link">
                  <i class="fa fa-plus-square nav-icon"></i>
                  <p>Data Entry</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="commodity_report.jsp" class="nav-link">
                  <i class="fas fa-file-excel nav-icon"></i>
                  <p>Report</p>
                </a>
              </li>
              
            </ul>
          </li>
                
         
         
         
         
         
    <%if(session.getAttribute("stf")!=null && session.getAttribute("reports")!=null ){
      if(session.getAttribute("stf").toString().equals("1") || session.getAttribute("reports").toString().equals("1")){%>   
           <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-user-md"></i>
              <p>
                STF
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">3</span>
              </p>
            </a>
            <ul class="nav nav-treeview">
                
                
     <%if(session.getAttribute("admin")!=null){
      if(session.getAttribute("admin").toString().equals("1")){%>   
              <li class="nav-item">
                <a href="stf_uploads.jsp" class="nav-link">
                  <i class="fas fa-file-excel nav-icon"></i>
                  <p>Upload Template</p>
                </a>
              </li>
               <%}}%>
               
              <%if(session.getAttribute("stf")!=null){
      if(session.getAttribute("stf").toString().equals("1")){%>   
              <li class="nav-item">
                <a href="stf_data_entry.jsp" class="nav-link">
                  <i class="fas fa-file-excel nav-icon"></i>
                  <p>Data Entry</p>
                </a>
              </li>
               <%}}%>
              
              <li class="nav-item">
                <a href="stf_reports.jsp" class="nav-link">
                  <i class="fa fa-user-md nav-icon"></i>
                  <p>Report</p>
                </a>
              </li>
        
            </ul>
          </li> 
         <%}}%> 
          
      <%if(session.getAttribute("ppmt")!=null && session.getAttribute("reports")!=null ){
      if(session.getAttribute("ppmt").toString().equals("1") || session.getAttribute("reports").toString().equals("1")){%>   
           <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tasks"></i>
              <p>
                PPMT
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">2</span>
              </p>
            </a>
            <ul class="nav nav-treeview">
                
      <%if(session.getAttribute("ppmt")!=null){
      if(session.getAttribute("ppmt").toString().equals("1")){%>    
              <li class="nav-item">
                <a href="ppmt.jsp" class="nav-link">
                  <i class="fas fa-tasks nav-icon"></i>
                  <p>Data Entry</p>
                </a>
              </li>
            <%}}%>  
              
              <li class="nav-item">
                <a href="ppmt_report.jsp" class="nav-link">
                  <i class="fa fa-file-excel nav-icon"></i>
                  <p>Report (.xlsx)</p>
                </a>
              </li>
             
            </ul>
          </li> 
          <%}}%>
          <!--<li style="text-align: center; color: white;"><br></li>-->
      
       
          
     <%if(session.getAttribute("dashboard")!=null && session.getAttribute("reports")!=null ){
      if(session.getAttribute("dashboard").toString().equals("1") || session.getAttribute("reports").toString().equals("1")){%>  
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-signal"></i>
              <p>
                Reports
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">4</span>
              </p>
            </a>
            <ul class="nav nav-treeview">
                
      <%if(session.getAttribute("reports")!=null){
      if(session.getAttribute("reports").toString().equals("1")){%>     
              <li class="nav-item">
                <a href="raw_data.jsp" class="nav-link">
                  <i class="fas fa-file-excel nav-icon"></i>
                  <p>Raw Data (.xlsx)</p>
                </a>
              </li>
               <%}}%>
      <%if(session.getAttribute("reports")!=null){
      if(session.getAttribute("reports").toString().equals("1")){%>  
              <li class="nav-item">
                <a href="summaries.jsp" class="nav-link">
                  <i class="fas fa-file-excel nav-icon"></i>
                  <p>Summaries (.xlsx)</p>
                </a>
              </li>
               <%}}%>
      <%if(session.getAttribute("reports")!=null){
      if(session.getAttribute("reports").toString().equals("1")){%>  
              <li class="nav-item">
                <a href="Indicator_Summary.jsp" class="nav-link">
                  <i class="fas fa-file-excel nav-icon"></i>
                  <p>Indicator Summaries (.xlsx)</p>
                </a>
              </li>
               <%}}%>
              
       <%if(session.getAttribute("dashboard")!=null){
      if(session.getAttribute("dashboard").toString().equals("1")){%>  
              <li class="nav-item">
                <a href="dashboards.jsp" class="nav-link">
                  <i class="fas fa-industry nav-icon"></i>
                  <p>Dashboards</p>
                </a>
              </li>
              <%}}%>
              
            </ul>
          </li>
          <%}}%>
          
     <%if(session.getAttribute("dashboard")!=null && session.getAttribute("reports")!=null ){
      if(session.getAttribute("dashboard").toString().equals("1") || session.getAttribute("reports").toString().equals("1")){%>  
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-signal"></i>
              <p>
                Other Reports
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">1</span>
              </p>
            </a>
            <ul class="nav nav-treeview">
                
      <%if(session.getAttribute("reports")!=null){
      if(session.getAttribute("reports").toString().equals("1")){%>     
              <li class="nav-item">
                <a href="emr_reports.jsp" class="nav-link">
                  <i class="fas fa-file-excel nav-icon"></i>
                  <p>EMR Modules (.xlsx)</p>
                </a>
              </li>
               <%}}%>
     </ul>
          </li>
          <%}}%>
                    <li style="text-align: center; color: white;"><br></li>
      <%if(session.getAttribute("users")!=null && session.getAttribute("admin")!=null ){
      if(session.getAttribute("users").toString().equals("1") || session.getAttribute("admin").toString().equals("1")){%>            
            <li class="nav-item has-treeview">
            <a href="manage_users.jsp" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
                Manage Users
              </p>
            </a>
           
          </li>
          <%}}%>
      <%if(session.getAttribute("admin")!=null ){
      if(session.getAttribute("admin").toString().equals("1")){%>  
            <li class="nav-item has-treeview">
            <a href="facility_manager.jsp" class="nav-link">
              <i class="nav-icon fa fa-h-square"></i>
              <p>
                Manage Facility
              </p>
            </a>
           
          </li>
          <%}}%>
          
     <%if(session.getAttribute("settings")!=null){
      if(session.getAttribute("settings").toString().equals("1")){%>  
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-cogs"></i>
              <p>
                Settings
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">2</span>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="indicators.jsp" class="nav-link">
                  <i class="fas fa-binoculars nav-icon"></i>
                  <p>Indicators</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="variables.jsp" class="nav-link">
                  <i class="fas fa-question nav-icon"></i>
                  <p>Variables</p>
                </a>
              </li>
              
            </ul>
          </li>
       <%}}%>
       
      <%if(session.getAttribute("user_profile")!=null){
      if(session.getAttribute("user_profile").toString().equals("1")){%>
          <li class="nav-item has-treeview">
            <a href="user_profile.jsp" class="nav-link">
              <i class="nav-icon fas fa-user-plus"></i>
              <p>
                User Profile
              </p>
            </a>
         </li>
        <%}}%>
       
          <li class="nav-item has-treeview">
            <a href="logout" class="nav-link">
              <i class="nav-icon fas fa-lock"></i>
              <p>
               Logout
              </p>
            </a>
         </li>
        
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>
    </body>
    
    <script>
    $(document).ready(function(){
        load_menu();
    });  
    
    
    function load_menu(){ 
        
        var output = "";
        
        $.ajax({
        url:'load_sections',
        type:"get",
        dataType:"json",
        success:function(data){
           var sections = data.data; 
           var id,name,indicators;
           var indicator_id,indicator_name,description,indicator_type,frequency;
           for(var i=0;i<sections.length;i++){
               id = sections[i].section_id;
               name = sections[i].name;
               indicators = sections[i].indicators;
               
               output+="<li class=\"nav-item has-treeview\">"+
                        "<a href=\"#\" class=\"nav-link\">"+
                        "<i class=\"nav-icon fas fa-table\"></i>"+
                        " <p>"+
                        ""+name+""+
                        "<i class=\"fas fa-angle-left right\"></i>"+
                        "<span class=\"badge badge-info right\">"+indicators.length+"</span>"+
                        "</p>"+
                        "</a>"+
                        "<ul class=\"nav nav-treeview\">";
                        
               
               // load indicators per section 
               
               for(var j=0;j<indicators.length;j++){
                       indicator_id = indicators[j]. indicator_id;
                       indicator_name = indicators[j]. indicator_name;
                       description = indicators[j]. description;
                       indicator_type = indicators[j]. indicator_type;
                       frequency  = indicators[j]. frequency;   
                   
                  output+= "<li class=\"nav-item\">"+
                        " <a href=\"data_entry.jsp?indicator="+indicator_id+"&&sec="+id+"&&__&&test=1&&pass=3\" class=\"nav-link\">"+
                        " <i class=\"far fa-circle nav-icon\"></i>"+
                        " <p>"+indicator_name+"</p>"+
                        "</a>"+
                        "</li>";
                   
               }
               
               output+="</ul></li>";
           }
           
           $("#indicators").html(output);
           
//        alert("successfully loaded data");
         }
  });   
    }
        </script>
</html>
