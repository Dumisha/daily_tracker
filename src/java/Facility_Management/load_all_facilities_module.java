/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facility_Management;

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
public class load_all_facilities_module extends HttpServlet {
    HttpSession session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        dbConn conn = new dbConn();
        session = request.getSession();
        JSONArray jarray = new JSONArray();
        
        
         if(session.getAttribute("user_id")!=null){
        String query="SELECT \n" +
                "f.id as id,\n"+
                "c.name as County,\n" +
                "sc.name as Sub_County,\n" +
                "f.name as Health_Facility,\n" +
                "f.mfl_code as MFL_Code,\n" +
                "IF(SUM(t.gend_gbv+t.prep+t.kp+t.hts_self)>0,1,0) AS prevention,\n" +
                "IF(SUM(t.hts_tst+t.hts_pos+t.index_testing+t.hts_recency)>0,1,0) AS hts,\n" +
                "IF(SUM(t.tx_new+t.ca_screening+t.pmtct+t.eid+t.retention)>0,1,0) AS treatment,\n" +
                "IF(SUM(t.vl+t.stf)>0,1,0) AS vl,\n" +
                "IF(SUM(t.tb+t.tpt)>0,1,0) AS tb \n" +
                "FROM facilities f \n" +
                "INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                "INNER JOIN reports_tracker t on f.id=t.facility_id \n" +
                "INNER JOIN counties c ON sc.county_id=c.id \n" +
                "GROUP BY f.id \n" +
                "ORDER BY County ASC,Sub_County ASC, Health_Facility ASC";
        
        conn.rs = conn.st.executeQuery(query);
        while(conn.rs.next()){
            JSONObject obj = new JSONObject();
            obj.put("id", conn.rs.getInt("id"));   
            obj.put("county", conn.rs.getString("County"));   
            obj.put("sub_county", conn.rs.getString("Sub_County"));   
            obj.put("health_facility", conn.rs.getString("Health_Facility"));   
            obj.put("mflcode", conn.rs.getInt("MFL_Code"));   
            obj.put("prevention", conn.rs.getInt("prevention"));   
            obj.put("hts", conn.rs.getInt("hts"));   
            obj.put("treatment", conn.rs.getInt("treatment"));   
            obj.put("vl", conn.rs.getInt("vl"));   
            obj.put("tb", conn.rs.getInt("tb"));   
            
            jarray.add(obj);
        
        }
         }
        
        if( conn.conn!=null){conn.conn.close();}
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
            Logger.getLogger(load_all_facilities_module.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_all_facilities_module.class.getName()).log(Level.SEVERE, null, ex);
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
