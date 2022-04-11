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
public class load_unlinked extends HttpServlet {
HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConn conn = new dbConn();
        JSONArray jarray = new JSONArray();
        
        
        String load_unlinked = "SELECT \n" +
                                "entry_key,\n" +
                                "c.name as county,\n" +
                                "sc.name as sub_county,\n" +
                                "f.name as facility,\n" +
                                "f.mfl_code as mflcode,\n" +
                                "v.name as gender,\n" +
                                "dob,\n" +
                                "IFNULL(vs.name,\"\") as sdp,\n" +
                                "IFNULL(coalesce(vl.name,non_linkage_reason_other),\"\") as non_linkage,\n" +
                                "IFNULL(vr.name,\"\") as enrolled_recency,\n" +
                                "IFNULL(vst.name,\"\") as screened_tb,\n" +
                                "IFNULL(vts.name,\"\") as has_tb_signs,\n" +
                                "e.encounter_date as date_tested_pos\n" +
                                "FROM etl_hts_pos e\n" +
                                "INNER JOIN facilities f ON e.facility_id=f.id and e.linked=2\n" +
                                "INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                                "INNER JOIN counties c ON sc.county_id=c.id \n" +
                                "LEFT OUTER JOIN value_labels v ON e.gender=v.id \n" +
                                "LEFT OUTER JOIN value_labels vs ON e.sdp_id=vs.id \n" +
                                "LEFT OUTER JOIN value_labels vl ON e.non_linkage_reason=vl.id \n" +
                                "\n" +
                                "LEFT OUTER JOIN value_labels vr ON e.enrolled_recency=vr.id \n" +
                                "LEFT OUTER JOIN value_labels vst ON e.screened_tb=vst.id \n" +
                                "LEFT OUTER JOIN value_labels vts ON e.has_tb_signs=vts.id \n" +
                                "\n" +
                                "ORDER BY county,sub_county,facility ASC, date_tested_pos DESC ";
       conn.rs = conn.st.executeQuery(load_unlinked);
       while(conn.rs.next()){
           JSONObject obj = new JSONObject();
           obj.put("entry_key", conn.rs.getString(1));
           obj.put("county", conn.rs.getString(2));
           obj.put("sub_county", conn.rs.getString(3));
           obj.put("facility", conn.rs.getString(4));
           obj.put("mflcode", conn.rs.getString(5));
           obj.put("gender", conn.rs.getString(6));
           obj.put("dob", conn.rs.getString(7));
           obj.put("sdp", conn.rs.getString(8));
           obj.put("non_linkage", conn.rs.getString(9));
           obj.put("enrolled_recency", conn.rs.getString(10));
           obj.put("screened_tb", conn.rs.getString(11));
           obj.put("has_tb_signs", conn.rs.getString(12));
           obj.put("date_tested_pos", conn.rs.getString(13));
           
        jarray.add(obj);
       }
        
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
        Logger.getLogger(load_unlinked.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(load_unlinked.class.getName()).log(Level.SEVERE, null, ex);
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
