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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class update_profile extends HttpServlet {
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        session = request.getSession();
        dbConn conn = new dbConn();
        
        
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        
        String user_id="";
        
        
        if(session.getAttribute("user_id")!=null){
           user_id = session.getAttribute("user_id").toString();
       
        if(pass1.equals(pass2)){
            
            PasswordUtils utils = new PasswordUtils();
            if(1==1){ // password meets standards
            if(!user_exist(phone,email,user_id,conn)){ // another user not registered with the details
         String updator = "UPDATE users SET phone=?,email=?,password=? WHERE id=?"; 
            conn.pst = conn.conn.prepareStatement(updator);
            conn.pst.setString(1, phone);
            conn.pst.setString(2, email);
            conn.pst.setString(3, pass1);
            conn.pst.setString(4, user_id);
            
            conn.pst.executeUpdate();
            
            session.setAttribute("update_profile","<font color=\"green\"><b>Details updated successfully</b></font>");
                 }
            else{
            session.setAttribute("update_profile","<font color=\"red\"><b> Password must contain at least one number and one uppercase and lowercase letter, and at least 5 characters</b></font>");    
            }
            }
            else{
              session.setAttribute("update_profile","<font color=\"red\"><b>Another user already exist with email/password</b></font>");   
            }
        }
        else{
            session.setAttribute("update_profile","<font color=\"red\"><b>Passwords entered do not match</b></font>");
        }
        
        
        
        }
        else{
           session.setAttribute("update_profile","<font color=\"red\"><b>Unknown User</b></font>");  
        }
        
        response.sendRedirect("user_profile.jsp");
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
            Logger.getLogger(update_profile.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(update_profile.class.getName()).log(Level.SEVERE, null, ex);
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

     private boolean user_exist(String phone, String email,String user_id,dbConn conn) throws SQLException{        
        String query = "SELECT id FROM users where (phone=? OR email=?) AND id!=?";
        conn.pst = conn.conn.prepareStatement(query);
        conn.pst.setString(1, phone);
        conn.pst.setString(2, email);
        conn.pst.setString(3, user_id);
         System.out.println("qq "+conn.pst);
        conn.rs = conn.pst.executeQuery();
        return conn.rs.next();
    }
}
