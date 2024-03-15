/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package DDTA;

import Database.dbConn;
import com.mysql.jdbc.CallableStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author mwamb
 */
public class remove_record extends HttpServlet {
String entry_key,message;
int code;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        dbConn conn = new dbConn();
        JSONObject obj = new JSONObject();
        
        if(session.getAttribute("user_id")!=null){
        entry_key = request.getParameter("entry_key");
        
        code=0;
        String query = "DELETE FROM observations WHERE entry_key=?";
        conn.pst = conn.conn.prepareStatement(query);
        conn.pst.setString(1, entry_key);
        int nums = conn.pst.executeUpdate();
            System.out.println("nums : "+nums);
        // remove from summary records
        if(nums>0){ 
         String stored_procedure = ("{CALL delete_record("+entry_key+")}");
              //execute call
               CallableStatement cs = (CallableStatement) conn.conn.prepareCall(stored_procedure);
               cs.executeUpdate();
       code=1;
       message="Records deleted successfully";
          }
        else{
        code=0;
        message="No records were found to be deleted";    
        }
          }
       
          else{
           code=0;
           message="Unknown User. Login and try again";
               
           }
          
         obj.put("code", code);
         obj.put("message", message);
          
     if( conn.conn!=null){conn.conn.close();}
        System.out.println("output  sent : "+code+" message :"+message);
       out.println(obj);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(remove_record.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(remove_record.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
