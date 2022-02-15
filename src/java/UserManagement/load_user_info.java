/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManagement;

import Database.dbConn;
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
 * @author Geofrey Nyabuto
 */
public class load_user_info extends HttpServlet {
    HttpSession session;
    int user_level_id;
    String user_coverage;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConn conn = new dbConn();
        session = request.getSession();
        
        String id = request.getParameter("id");
        
        JSONObject obj = new JSONObject();
        
        
        //check user existence 
        
        String get_user = "SELECT id,user_level_id,first_name,IFNULL(middle_name,\"\") middle_name,sur_name,phone,email,approved,is_active FROM users WHERE id=?";
        conn.pst = conn.conn.prepareStatement(get_user);
        conn.pst.setString(1, id);
        conn.rs = conn.pst.executeQuery();
        if(conn.rs.next()){
          obj.put("code", 1);
          obj.put("message", "User Loaded");
          obj.put("user", conn.rs.getInt(1));
          obj.put("level",  conn.rs.getInt(2));
          obj.put("first_name", conn.rs.getString(3));
          obj.put("middle_name", conn.rs.getString(4));
          obj.put("sur_name", conn.rs.getString(5));
          obj.put("phone", conn.rs.getString(6));
          obj.put("email", conn.rs.getString(7));
          obj.put("approved", conn.rs.getString(8));
          obj.put("status", conn.rs.getString(9));
        }
        
        else{
        obj.put("code", 0);
          obj.put("message", "User does not exist in the system. contact support");    
        }
        
    
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
            Logger.getLogger(load_user_info.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_user_info.class.getName()).log(Level.SEVERE, null, ex);
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
