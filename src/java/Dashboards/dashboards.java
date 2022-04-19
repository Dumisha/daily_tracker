/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboards;

import Database.dbConn;
import UserManagement.User_Locations;
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
public class dashboards extends HttpServlet {
    HttpSession session;
    String user_id,user_level;
  String start_date,end_date,facilities,sub_counties,counties; 
  int tested,positive,linked;
  int tx_new,ltfu_returned,transfer_in,transferred_out,died,stopped,iit_today;
  int pns_tested,pns_positive,pns_linked;
  int male_total,male_less_10,male_10_14,male_15_19,male_20_24,male_25_49,male_above_50,
female_total,female_less_10,female_10_14,female_15_19,female_20_24,female_25_49,female_above_50 ;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        session = request.getSession();
        dbConn conn = new dbConn();
        JSONObject obj = new JSONObject();
        
       start_date = request.getParameter("start_date");
       end_date = request.getParameter("end_date");
       
       counties = request.getParameter("counties");
       sub_counties = request.getParameter("sub_counties");
       facilities = request.getParameter("facilities");
       
       
          if(session.getAttribute("user_id")!=null){
            user_id = session.getAttribute("user_id").toString();
        }else{
        user_id="";   
        }
        
          
        if(session.getAttribute("user_level_id")!=null){
           user_level=session.getAttribute("user_level_id").toString(); 
       }
        else{
            user_level="";
        }
       if(session.getAttribute("user_id")!=null){
           
            String dashboard="";
           if(session.getAttribute("dashboard")!=null ){
               dashboard=session.getAttribute("dashboard").toString();
           }
            
           if(dashboard.equals("1")){
            
           
        User_Locations ul = new User_Locations();

               String selected_facilities = ul.load_user_selected_facilities(counties,sub_counties,facilities,user_level,user_id,conn);
       
       // tested, positive, linked 
       
       tested=positive=linked=0;
       tx_new=ltfu_returned=transfer_in=transferred_out=died=stopped=iit_today=0;
       pns_tested=pns_positive=pns_linked=0;
       
       
      male_total=male_less_10=male_10_14=male_15_19=male_20_24=male_25_49=male_above_50=
female_total=female_less_10=female_10_14=female_15_19=female_20_24=female_25_49=female_above_50=0;
        
       
       String get_hts = "SELECT   \n" +
                    "SUM(tested) AS tested,SUM(positive) AS positive,SUM(linked) AS linked \n" +
                    "FROM\n" +
                    "((SELECT 1 as num,0 as tested, \n" +
                    "COUNT(DISTINCT pos.entry_key) as positive,SUM(if(pos.linked=1,1,0)) AS linked \n" +
                    "FROM etl_hts_pos pos where pos.encounter_date BETWEEN DATE('"+start_date+"') AND DATE('"+end_date+"') and pos.facility_id in("+selected_facilities+") ) \n" +
                    "UNION ALL \n" +
                    "(SELECT 1 as num,SUM(tst.tested) as tested, 0 as positive,0 AS linked \n" +
                    "FROM etl_hts_tst tst where tst.encounter_date BETWEEN DATE('"+start_date+"') AND DATE('"+end_date+"') and tst.facility_id in("+selected_facilities+") )) AS hts GROUP BY num \n" +
                    "\n";
   conn.rs = conn.st.executeQuery(get_hts);
   if(conn.rs.next()){
    tested = conn.rs.getInt(1);
    positive = conn.rs.getInt(2);
    linked = conn.rs.getInt(3);  
   }
   
   
   // GET INFLOWS and outflows
   String inflow_outflow = "SELECT \n" +
                                "0 AS num,\n" +
                                "tx_new.tx_new,\n" +
                                "IFNULL(SUM(r.ltfu_returned),0) AS ltfu_returned,\n" +
                                "IFNULL(SUM(r.transfer_in),0) AS transfer_in,\n" +
                                "IFNULL(SUM(r.transferred_out),0) AS transferred_out,\n" +
                                "IFNULL(SUM(r.died),0) AS died,\n" +
                                "IFNULL(SUM(r.stopped),0) AS stopped,\n" +
                                "IFNULL(SUM(r.iit_today),0) AS iit_today\n" +
                                "FROM etl_retention r \n" +
                                "INNER JOIN(\n" +
                                "SELECT 0 as num, COUNT(tx.entry_key) as tx_new\n" +
                                " FROM etl_tx_new tx where tx.encounter_date BETWEEN DATE('"+start_date+"') AND DATE('"+end_date+"') and tx.facility_id in("+selected_facilities+") \n" +
                                ") as tx_new on num=tx_new.num "
                                + " where r.encounter_date BETWEEN DATE('"+start_date+"') AND DATE('"+end_date+"')  and r.facility_id in("+selected_facilities+") ";
   
   conn.rs = conn.st.executeQuery(inflow_outflow);
   if(conn.rs.next()){
    tx_new = conn.rs.getInt(2);
    ltfu_returned = conn.rs.getInt(3);
    transfer_in = conn.rs.getInt(4);
    transferred_out = conn.rs.getInt(5);
    died = conn.rs.getInt(6);
    stopped = conn.rs.getInt(7);
    iit_today = conn.rs.getInt(8);  
   }
   
   // pns testing 
   String get_pns="SELECT \n" +
                "IFNULL(SUM(pns.tested),0) AS tested,\n" +
                "IFNULL(SUM(pns.positive),0) AS positive,\n" +
                "IFNULL(SUM(pns.linked),0) AS linked\n" +
                " FROM ddta.etl_hts_pns as pns where pns.encounter_date BETWEEN DATE('"+start_date+"') AND DATE('"+end_date+"')  and pns.facility_id in("+selected_facilities+")";
   
   conn.rs = conn.st.executeQuery(get_pns);
   if(conn.rs.next()){
       pns_tested = conn.rs.getInt(1);
       pns_positive = conn.rs.getInt(2);
       pns_linked = conn.rs.getInt(3);
   }
   
   
   // tx_new analysis
   
   String get_tx_new = "SELECT \n" +
                "SUM(CASE WHEN (gender=5 ) THEN 1 ELSE 0 END) as male_total,\n" +
                "SUM(CASE WHEN (gender=5 and (TIMESTAMPDIFF(YEAR, dob, encounter_date) <10)) THEN 1 ELSE 0 END) as male_less_10,\n" +
                "SUM(CASE WHEN (gender=5 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)  BETWEEN 10 AND 14.9999)) THEN 1 ELSE 0 END) as male_10_14,\n" +
                "SUM(CASE WHEN (gender=5 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)  BETWEEN 15 AND 19.9999)) THEN 1 ELSE 0 END) as male_15_19,\n" +
                "SUM(CASE WHEN (gender=5 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)  BETWEEN 20 AND 24.9999)) THEN 1 ELSE 0 END) as male_20_24,\n" +
                "SUM(CASE WHEN (gender=5 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)  BETWEEN 25 AND 49.9999)) THEN 1 ELSE 0 END) as male_25_49,\n" +
                "SUM(CASE WHEN (gender=5 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)>=50)) THEN 1 ELSE 0 END) as male_above_50,\n" +
                "\n" +
                "SUM(CASE WHEN (gender=6) THEN 1 ELSE 0 END) as female_total,\n" +
                "SUM(CASE WHEN (gender=6 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)<10)) THEN 1 ELSE 0 END) as female_less_10,\n" +
                "SUM(CASE WHEN (gender=6 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)  BETWEEN 10 AND 14.9999)) THEN 1 ELSE 0 END) as female_10_14,\n" +
                "SUM(CASE WHEN (gender=6 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)  BETWEEN 15 AND 19.9999)) THEN 1 ELSE 0 END) as female_15_19,\n" +
                "SUM(CASE WHEN (gender=6 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)  BETWEEN 20 AND 24.9999)) THEN 1 ELSE 0 END) as female_20_24,\n" +
                "SUM(CASE WHEN (gender=6 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)  BETWEEN 25 AND 49.9999)) THEN 1 ELSE 0 END) as female_25_49,\n" +
                "SUM(CASE WHEN (gender=6 and (TIMESTAMPDIFF(YEAR, dob, encounter_date)>=50)) THEN 1 ELSE 0 END) as female_above_50 \n" +
                "\n" +
                "FROM \n" +
                "etl_tx_new tx_new where tx_new.encounter_date BETWEEN DATE('"+start_date+"') AND DATE('"+end_date+"')  and tx_new.facility_id in("+selected_facilities+")";
   conn.rs = conn.st.executeQuery(get_tx_new);
   if(conn.rs.next()){
              male_total= conn.rs.getInt(1);
              male_less_10= conn.rs.getInt(2);
              male_10_14= conn.rs.getInt(3);
              male_15_19= conn.rs.getInt(4);
              male_20_24= conn.rs.getInt(5);
              male_25_49= conn.rs.getInt(6);
              male_above_50= conn.rs.getInt(7);
              female_total= conn.rs.getInt(8);
              female_less_10= conn.rs.getInt(9);
              female_10_14= conn.rs.getInt(10);
              female_15_19= conn.rs.getInt(11);
              female_20_24= conn.rs.getInt(12);
              female_25_49= conn.rs.getInt(13);
              female_above_50 = conn.rs.getInt(14);
   }
   
   
   
   
   obj.clear();
   
   
   JSONObject ob_tst = new JSONObject();
   ob_tst.put("tested", tested);
   ob_tst.put("positive", positive);
   ob_tst.put("linked", linked);
     
   
   
   JSONObject gain_losses = new JSONObject();
   gain_losses.put("tx_new", tx_new);
   gain_losses.put("iit_returned", ltfu_returned);
   gain_losses.put("transfer_in", transfer_in);
   gain_losses.put("transferred_out", transferred_out);
   gain_losses.put("died", died);
   gain_losses.put("stopped", stopped);
   gain_losses.put("iits", iit_today);
   
   JSONObject obj_pns = new JSONObject();
   obj_pns.put("tested", pns_tested);
   obj_pns.put("positive", pns_positive);
   obj_pns.put("linked", pns_linked);
   

   JSONObject obj_tx_new = new JSONObject();
   obj_tx_new.put("male_total", male_total);
   obj_tx_new.put("male_less_10", male_less_10);
   obj_tx_new.put("male_10_14", male_10_14);
   obj_tx_new.put("male_15_19", male_15_19);
   obj_tx_new.put("male_20_24", male_20_24);
   obj_tx_new.put("male_25_49", male_25_49);
   obj_tx_new.put("male_above_50", male_above_50);
   obj_tx_new.put("female_total", female_total);
   obj_tx_new.put("female_less_10", female_less_10);
   obj_tx_new.put("female_10_14", female_10_14);
   obj_tx_new.put("female_15_19", female_15_19);
   obj_tx_new.put("female_20_24", female_20_24);
   obj_tx_new.put("female_25_49", female_25_49);
   obj_tx_new.put("female_above_50", female_above_50);
   
   
   
   obj.put("hts", ob_tst);
   obj.put("gain_losses", gain_losses);
   obj.put("pns", obj_pns);
   obj.put("tx_new", obj_tx_new);
   obj.put("code",1);
   obj.put("message", "Data Loaded successfully");
   
       }
        else{ // not allowed to use dashboards
             obj.put("code",0);
             obj.put("message", "User not allowed to access this module"); 
           }    
       }
       else{ // unknown user
          obj.put("code",0);
          obj.put("message", "Unknown user. Login to try again");  
           
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
            Logger.getLogger(dashboards.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(dashboards.class.getName()).log(Level.SEVERE, null, ex);
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
