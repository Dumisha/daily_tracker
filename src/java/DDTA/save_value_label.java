/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DDTA;

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
public class save_value_label extends HttpServlet {
    HttpSession session;
    String message;
    int code;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConn conn = new dbConn();
        session = request.getSession();
        JSONObject obj = new JSONObject();
        message="";
        code=0;
        
        String label_name = request.getParameter("label_name");
        
//        System.out.println("label name : "+label_name);
        
        String checker = "select id FROM value_labels WHERE name=?";
        conn.pst = conn.conn.prepareStatement(checker);
        conn.pst.setString(1, label_name);
        conn.rs = conn.pst.executeQuery();
        if(conn.rs.next()){
         code=0;
         message = "Similar value exist in the database";
        }
        else{
          String inserter = "INSERT INTO value_labels (name) VALUES(?)";
          conn.pst = conn.conn.prepareStatement(inserter);
          conn.pst.setString(1, label_name);
          int num = conn.pst.executeUpdate();
          if(num>0){
              code=1;
              message = "Value added successfully";
          }
          else{
              code=0;
              message="Error occured while saving the value";
          }
        }
        
      obj.put("code", code);
      obj.put("message", message);
        
      if( conn.conn!=null){conn.conn.close();}  
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
            Logger.getLogger(save_value_label.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_value_label.class.getName()).log(Level.SEVERE, null, ex);
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
