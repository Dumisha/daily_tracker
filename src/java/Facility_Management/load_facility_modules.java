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
import org.json.simple.JSONObject;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_facility_modules extends HttpServlet {
    HttpSession  session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        session = request.getSession();
        dbConn conn = new dbConn();
        
        JSONObject obj = new JSONObject();
        
        String facility_id=request.getParameter("f_id");
//        String facility_id="1";
        
         if(session.getAttribute("user_id")!=null){
        String query = "SELECT id,facility_id,gend_gbv,prep,kp,hts_self,hts_tst,hts_pos,index_testing,hts_recency,tx_new,ca_screening,pmtct,eid,retention,vl,tb,tpt,stf,status,timestamp FROM reports_tracker where facility_id=?";
        conn.pst = conn.conn.prepareStatement(query);
        conn.pst.setString(1, facility_id);
        conn.rs = conn.pst.executeQuery();
        if(conn.rs.next()){
         obj.put("id", conn.rs.getInt(1));
         obj.put("facility_id", conn.rs.getInt(2));
         obj.put("gend_gbv", conn.rs.getInt(3));
         obj.put("prep", conn.rs.getInt(4));
         obj.put("kp", conn.rs.getInt(5));
         obj.put("hts_self", conn.rs.getInt(6));
         obj.put("hts_tst", conn.rs.getInt(7));
         obj.put("hts_pos", conn.rs.getInt(8));
         obj.put("index_testing", conn.rs.getInt(9));
         obj.put("hts_recency", conn.rs.getInt(10));
         obj.put("tx_new", conn.rs.getInt(11));
         obj.put("ca_screening", conn.rs.getInt(12));
         obj.put("pmtct", conn.rs.getInt(13));
         obj.put("eid", conn.rs.getInt(14));
         obj.put("retention", conn.rs.getInt(15));
         obj.put("vl", conn.rs.getInt(16));
         obj.put("tb", conn.rs.getInt(17));
         obj.put("tpt", conn.rs.getInt(18));
         obj.put("stf", conn.rs.getInt(19));
         obj.put("status", conn.rs.getInt(20));
         obj.put("timestamp", conn.rs.getString(21));
         
        }
         }
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
            Logger.getLogger(load_facility_modules.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_facility_modules.class.getName()).log(Level.SEVERE, null, ex);
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
