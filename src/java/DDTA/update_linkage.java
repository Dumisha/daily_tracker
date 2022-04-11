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
public class update_linkage extends HttpServlet {
    HttpSession session;
    int count;
    String numeric_value,text_value,user_id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        dbConn conn = new dbConn();
        Manage mg = new Manage();
        user_id = "";
        count = 0;
        
        
        String entry_key = request.getParameter("entry_key");
        String keys = request.getParameter("keys").replace("entry_key", "");
        
        if(session.getAttribute("user_id")!=null){
           user_id=session.getAttribute("user_id").toString(); 
       }
        System.out.println("entry key is : "+entry_key+" and keys are :"+keys);
        
        String [] arr_keys = keys.split(",");
        String timestamp = mg.get_timestamp();
        
        for(String key:arr_keys){
           if(key.length()>0){
            String variable_id = key.split("_")[1];
            String value  = request.getParameter(key);
            System.out.println("indicator id is "+variable_id+" value is : "+value); 
            if(mg.isNumeric(value)){
                numeric_value = value;
                text_value = null;
            }
            
            else{
             numeric_value = null;
                text_value = value;    
            }
               
            String updator = "UPDATE observations SET numeric_value=?, text_value=?,updated_at=?,updated_by=? WHERE entry_key=? AND question_id=?" ;  
              conn.pst = conn.conn.prepareStatement(updator);
              conn.pst.setString(1, numeric_value);
              conn.pst.setString(2, text_value);
              conn.pst.setString(3, timestamp);
              conn.pst.setString(4, user_id);
              conn.pst.setString(5, entry_key);
              conn.pst.setString(6, variable_id);
              conn.pst.executeUpdate();
            count++;
                    }
        }
     
        if(count>0){
            mg.update_queue(conn,entry_key,"2","6");
        }
        
        
        
        JSONObject ob = new JSONObject();
        
       ob.put("code", 1);
       ob.put("message", count+" linkage variables updated successfully");

       // process queue
       
       mg.process_queue(conn,"2");
       
       //
        System.out.println("final obj :"+ob);
       out.println(ob);
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
            Logger.getLogger(update_linkage.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(update_linkage.class.getName()).log(Level.SEVERE, null, ex);
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
