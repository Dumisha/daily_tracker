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
public class save_question extends HttpServlet {
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
        
        code = 0;
        message="";
        
        System.out.println("called here");
        // receive parameters
        
             String   id = request.getParameter("id");
             String   question = request.getParameter("question");
             String   indicator_id = request.getParameter("indicator_id");
             String   input_type_id = request.getParameter("input_type");
             String   answer_data_type_id = request.getParameter("answer_data_type");
             String   required = request.getParameter("required");
             String   value_unique = request.getParameter("value_unique");
             String   status = request.getParameter("status");
                
            System.out.println("id : "+id+" indicator id : "+indicator_id);
             
             if(id.equals("")){ // check if it is a new entry
             String checker = "SELECT id FROM questions WHERE question=? and indicator_id=?";
             conn.pst = conn.conn.prepareStatement(checker);
             conn.pst.setString(1, question);
             conn.pst.setString(2, indicator_id);
             conn.rs = conn.pst.executeQuery();
             if(conn.rs.next()){
                 code = 0;
                 message = "Error: This indicator has a similar question";
             }
             else{ // add the new indicator
              String adder = "INSERT INTO questions (question,indicator_id,input_type_id,answer_data_type_id,required,value_unique,status) VALUES(?,?,?,?,?,?,?)";
              conn.pst = conn.conn.prepareStatement(adder);
              conn.pst.setString(1, question);
              conn.pst.setString(2, indicator_id);
              conn.pst.setString(3, input_type_id);
              conn.pst.setString(4, answer_data_type_id);
              conn.pst.setString(5, required);
              conn.pst.setString(6, value_unique);
              conn.pst.setString(7, status);
              
             int num = conn.pst.executeUpdate();
             
             if(num>0){
                 code=1;
                 message="New question added successfully";
             }
             else{
                 code=0;
                 message="Errors occured while saving the new question";
             }
                 
             }
                
             }
             else{ // update indicator information
               String updator = "UPDATE questions SET question=?,indicator_id=?,input_type_id=?,answer_data_type_id=?,required=?,value_unique=?,status=? WHERE id=?";
              conn.pst = conn.conn.prepareStatement(updator);
              conn.pst.setString(1, question);
              conn.pst.setString(2, indicator_id);
              conn.pst.setString(3, input_type_id);
              conn.pst.setString(4, answer_data_type_id);
              conn.pst.setString(5, required);
              conn.pst.setString(6, value_unique);
              conn.pst.setString(7, status);
              conn.pst.setString(8, id);
              
             int num = conn.pst.executeUpdate();
             
             if(num>0){
                 code=1;
                 message="Question details updated successfully";
             }
             else{
                 code=0;
                 message="Errors occured while updating question details";
             } 
                 
             }
        obj.put("code", code);
        obj.put("message", message);
        
        if( conn.conn!=null){conn.conn.close();}
        out.println(obj);
        System.out.println("response : "+obj);
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
            Logger.getLogger(save_question.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_question.class.getName()).log(Level.SEVERE, null, ex);
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
