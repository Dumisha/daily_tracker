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
public class save_question_value_labels extends HttpServlet {
    HttpSession session;
    String question_id,value_label_ids,message;
    int code;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConn conn = new dbConn();
        session = request.getSession();
        JSONObject obj = new JSONObject();
        
        code=0;
        message="";
        
        question_id = request.getParameter("question_id");
        value_label_ids = request.getParameter("labels"); 
       
        // loop through and save
        
        String remove_all = "DELETE FROM question_value_labels WHERE question_id=?";
        conn.pst = conn.conn.prepareStatement(remove_all);
        conn.pst.setString(1, question_id);
        conn.pst.executeUpdate();
       
        // loop through saving the values
        String [] values = value_label_ids.split(",");
        for(String label:values){
            
            String inserter = "INSERT INTO question_value_labels (question_id,value_label_id,status) VALUES(?,?,?)";
            conn.pst = conn.conn.prepareStatement(inserter);
            conn.pst.setString(1, question_id);
            conn.pst.setString(2, label);
            conn.pst.setInt(3, 1);
            
            conn.pst.executeUpdate();
        }
        obj.put("code", 1);
        obj.put("message", "Update successful");
        
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
            Logger.getLogger(save_question_value_labels.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_question_value_labels.class.getName()).log(Level.SEVERE, null, ex);
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
