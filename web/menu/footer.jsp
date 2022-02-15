<%-- 
    Document   : footer
    Created on : 28-Jan-2022, 21:41:06
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     </head>
    <body>
         <%
                                                Calendar cal = Calendar.getInstance();  
                                                int year= cal.get(Calendar.YEAR);    
                                            
                                            %>
                                            
       <p align="center"> &copy <a href="#" title="Version 0.1">USAID Dumisha Afya </a> Daily Data Tracking System <%=year%></p>
    </body>
</html>
