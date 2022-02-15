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
public class load_view_edit extends HttpServlet {
    HttpSession session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        dbConn conn = new dbConn();
        JSONArray jarray = new JSONArray();
        
        
        String facility_id = request.getParameter("facility_id");
        String date = request.getParameter("date");
        String indicator_id = request.getParameter("indicator_id"); 
        
       String query = "SELECT \n" +
"entry_key," +
"GROUP_CONCAT(\" \"," +
"CONCAT(q.question,\": \",coalesce(" +
"IF(q.input_type_id in(3,4),vl.name,numeric_value),text_value" +
"))) as q_a," +
"o.date AS Encounter_Date, "
+ "i.multiple_entries " +
" FROM observations o " +
" INNER JOIN questions q ON o.question_id=q.id and o.facility_id=? and o.date=? and q.indicator_id=?"
 + " INNER JOIN indicators i ON q.indicator_id=i.id " +
" LEFT OUTER JOIN value_labels vl ON o.numeric_value=vl.id" +
" group by entry_key ";
       conn.pst = conn.conn.prepareStatement(query);
       conn.pst.setString(1, facility_id);
       conn.pst.setString(2, date);
       conn.pst.setString(3, indicator_id);
       
       conn.rs = conn.pst.executeQuery();
       while(conn.rs.next()){
           JSONObject obj = new JSONObject();
           obj.put("entry_key", conn.rs.getString(1));
           obj.put("q_a", conn.rs.getString(2));
           obj.put("date", conn.rs.getString(3));
           obj.put("multiple_entries", conn.rs.getInt(4));
           
           jarray.add(obj);
       }
       
        System.out.println("Entries: "+jarray);
       out.println(jarray);
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
            Logger.getLogger(load_view_edit.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_view_edit.class.getName()).log(Level.SEVERE, null, ex);
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
