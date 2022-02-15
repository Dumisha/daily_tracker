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
public class save_indicator extends HttpServlet {
    HttpSession session;
    String message;
    int code;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        dbConn conn = new dbConn();
        JSONObject obj = new JSONObject();
        
        message = "";
        code=0;
        
        // receive values
        String indicator_id = request.getParameter("id");
        String section_id = request.getParameter("section_id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String indicator_type = request.getParameter("indicator_type");
        String frequency = request.getParameter("frequency");
        String status = request.getParameter("status");
        
        if(description.equals("")){description=null;}
        
        // check variables to ensure values are passed
        
        //end
        
        if(indicator_id.equals("")){ // add new indicator
          String checker="SELECT id FROM indicators where name=?";
          conn.pst = conn.conn.prepareStatement(checker);
          conn.pst.setString(1, name);
          conn.rs = conn.pst.executeQuery();
          if(conn.rs.next()){
              code=0;
              message = "Indicator with this name already exist in the system";
          }
          else{
              String inserter = "INSERT INTO indicators (name,description,section_id,indicator_type,frequency,status) VALUES (?,?,?,?,?,?)";
              conn.pst = conn.conn.prepareStatement(inserter);
              conn.pst.setString(1, name);
              conn.pst.setString(2, description);
              conn.pst.setString(3, section_id);
              conn.pst.setString(4, indicator_type);
              conn.pst.setString(5, frequency);
              conn.pst.setString(6, status);
              
             int num = conn.pst.executeUpdate();
              if(num>0){
             code=1;
              message = "Indicator added successfully";
              }
              else{
                  code=0;
                  message="Error: Indicator information was not added";
              }
          }
            
        }
        else{ // update existing
           String updator = "UPDATE indicators SET name=?,description=?,section_id=?,indicator_type=?,frequency=?,status=? WHERE id=?";
           conn.pst = conn.conn.prepareStatement(updator);
           conn.pst.setString(1, name);
              conn.pst.setString(2, description);
              conn.pst.setString(3, section_id);
              conn.pst.setString(4, indicator_type);
              conn.pst.setString(5, frequency);
              conn.pst.setString(6, status);
              conn.pst.setString(7, indicator_id);
           int num = conn.pst.executeUpdate();
           if(num>0){
             code=1;
              message = "Indicator updated successfully";
              }
              else{
                  code=0;
                  message="Error: Indicator information was not updated";
              }   
              
            
        }
        
       obj.put("code", code);
       obj.put("message", message);
        
       
        System.out.println(obj);
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
            Logger.getLogger(save_indicator.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_indicator.class.getName()).log(Level.SEVERE, null, ex);
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
