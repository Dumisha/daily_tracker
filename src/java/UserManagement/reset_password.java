/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManagement;

import Database.dbConn;
import Security.PasswordUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author Geofrey Nyabuto
 */
public class reset_password extends HttpServlet {
    HttpSession session;
    int code;
    String password,message;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          session = request.getSession();
          
            dbConn conn = new dbConn();
            
            
            String id = request.getParameter("id");
            String pass1 = request.getParameter("pass1");
            String pass2 = request.getParameter("pass2");

            PasswordUtils utils = new PasswordUtils();
            
                  if(session.getAttribute("admin")!=null){
         
        if(session.getAttribute("admin").toString().equals("1")) { // user has rights to change permissions
           
            code=0;
            message="";
            if(pass1.equals(pass2)){
               if(1==1){ // password meets standards
                   // update database
                   System.out.println("pass1 : "+pass1);
                String salt = PasswordUtils.getSalt(30);
                password = PasswordUtils.generateSecurePassword(pass1, salt);
                   String updator = "UPDATE users SET password=?,salt=? WHERE id=?";
                   conn.pst = conn.conn.prepareStatement(updator);
                   conn.pst.setString(1, password);
                   conn.pst.setString(2, salt);
                   conn.pst.setString(3, id);
                   
                 int num =  conn.pst.executeUpdate();
                 if(num>0){
                     code=1;
                     message="User Password updated successfully";
                 }
                 else{
                     code=0;
                     message="Unable to update password. User doesnt exist in the system.";
                 }
                   System.out.println(" numbers are : "+num);  
               } 
               else{
                code=0;
                message = "No Error";   
               }
            }
            else{
                code=0;
                message = "Passwords do not match";
            }
        
        }
             else{
         code=0;
         message="Error: Action not allowed.";     
        }
           
       }  else{
         code=0;
         message="Unknown user access.";  
       } 
         
            JSONObject obj = new JSONObject();
            obj.put("code", code);
            obj.put("message", message);
            
            
           out.println(obj);
            
            
        } finally {
            out.close();
        }
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
            Logger.getLogger(reset_password.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(reset_password.class.getName()).log(Level.SEVERE, null, ex);
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
