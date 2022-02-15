/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLoaders;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_edit_data extends HttpServlet {
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        dbConn conn = new dbConn();
        
        String entry_key = request.getParameter("entry_key");
        
        JSONArray jarray = new JSONArray();
        JSONObject obj_final = new JSONObject();
        String date="";
        
            String query = "SELECT o.id,o.question_id,o.numeric_value,ifnull(o.text_value,\"\") as text_value,q.input_type_id,q.answer_data_type_id,o.date " +
          "FROM questions q " +
          "INNER JOIN " +
          "observations o ON o.question_id=q.id AND o.entry_key=? ORDER BY ordering_num";
                     
    conn.pst = conn.conn.prepareStatement(query);
    conn.pst.setString(1, entry_key);   
       
        System.out.println(conn.pst);
    conn.rs = conn.pst.executeQuery();
    
    while(conn.rs.next()){
        JSONObject ob = new JSONObject();
        
        ob.put("id", conn.rs.getInt(1));
        ob.put("question_id", conn.rs.getInt(2));
        ob.put("numeric_value", conn.rs.getInt(3));
        ob.put("text_value", conn.rs.getString(4));
        ob.put("input_type", conn.rs.getInt(5));
        ob.put("answer_data_type", conn.rs.getInt(6));
        date = conn.rs.getString(7);
      
        jarray.add(ob);
          
    }
        
     obj_final.put("obs", jarray);
     obj_final.put("date", date);

        System.out.println("questions : "+obj_final);
     out.print(obj_final);    
        
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
            Logger.getLogger(load_edit_data.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_edit_data.class.getName()).log(Level.SEVERE, null, ex);
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

    
        public JSONArray get_answers(dbConn conn) throws SQLException{
        JSONArray jarray = new JSONArray();
        
        String get_answers = "SELECT vl.id,vl.name,qvl.question_id FROM question_value_labels qvl INNER JOIN value_labels vl ON qvl.value_label_id=vl.id and qvl.status=?";
        conn.pst = conn.conn.prepareStatement(get_answers);
        conn.pst.setInt(1, 1);
        
        conn.rs = conn.pst.executeQuery();
        while(conn.rs.next()){
            JSONObject ob = new JSONObject();
            ob.put("id", conn.rs.getInt(1));
            ob.put("name", conn.rs.getString(2));
            ob.put("question_id", conn.rs.getInt(3));
            
            jarray.add(ob);
        }
        return jarray;
    }
        
}
