<%-- 
    Document   : test
    Created on : 03-Feb-2022, 12:41:17
    Author     : Geofrey Nyabuto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script>
            function funtest(va){
                alert("called : "+va);
            }
            </script>
    </head>
    <body  onload="funtest('hello');">
        <h1 id="test" onload="">Hello World!</h1>
    </body>
</html>
