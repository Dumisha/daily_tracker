/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppmt;

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
public class save_ppmt extends HttpServlet {
    HttpSession session;
    String date,activity,activity_other,indicator,county,sub_county,facility,female,male,total,region,user_id;
    int code;
    String message;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        session = request.getSession();
        dbConn conn = new dbConn();
        
        message=""; code=0;
        
        
        user_id=null;
        if(session.getAttribute("user_id")!=null){
           user_id=session.getAttribute("user_id").toString(); 
       }
           if(session.getAttribute("user_id")!=null){
               String ppmt="";
           if(session.getAttribute("ppmt")!=null ){
               ppmt=session.getAttribute("ppmt").toString();
           }
            
           if(ppmt.equals("1")){
               
           
        date = request.getParameter("date");
        activity = request.getParameter("activity");
        activity_other = request.getParameter("activity_other");
        indicator = request.getParameter("indicator");
        county = request.getParameter("county");
        sub_county = request.getParameter("sub_county");
        facility = request.getParameter("facility");
        female = request.getParameter("female");
        male = request.getParameter("male");
        total = request.getParameter("total");
        region = request.getParameter("region");
        
        
        
        if(female.equals("")) {female=null;}
        if(male.equals("")) {male=null;}
        
        if(region.equals("1")){
        sub_county=null;
        facility = null;
        }
        else if(region.equals("2")){
        county=null;
        facility = null;
        }
        else if(region.equals("3")){
        sub_county=null;
        county = null;
        }
        
//        Data checker
        
        String checker = "SELECT id from ppmt WHERE activity_date=? && activity_id=? && activity_other=? and region_id=? && county_id=? && sub_county_id=? && facility_id=?";
        conn.pst = conn.conn.prepareStatement(checker);
        conn.pst.setString(1, date);
        conn.pst.setString(2, activity);
        conn.pst.setString(3, activity_other);
        conn.pst.setString(4, region);
        conn.pst.setString(5, county);
        conn.pst.setString(6, sub_county);
        conn.pst.setString(7, facility);
        
        
        conn.rs = conn.pst.executeQuery();
        
        
        if(!conn.rs.next()){ // add a new training
         String inserter = "INSERT INTO ppmt (activity_date,indicator_id,activity_id,activity_other,region_id,county_id,sub_county_id,facility_id,female,male,total,user_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
         conn.pst = conn.conn.prepareStatement(inserter);
         conn.pst.setString(1, date);
         conn.pst.setString(2, indicator);
         conn.pst.setString(3, activity);
         conn.pst.setString(4, activity_other);
         conn.pst.setString(5, region);
         conn.pst.setString(6, county);
         conn.pst.setString(7, sub_county);
         conn.pst.setString(8, facility);
         conn.pst.setString(9, female);
         conn.pst.setString(10, male);
         conn.pst.setString(11, total);
         conn.pst.setString(12, user_id);
         
         conn.pst.executeUpdate();
         
         
         
         code=1;
         message = "Activity data saved successfully";
        }
        else{
            code=0;
            message="Activity already exist in the selected region on the stated date";
        }
           }
           
           else{ // do not have enough permissions
           code=0;
           message = "You do not have enough permissions to execute save ppmt data";
               
           }
           
           } 
           else{
               code=0;
               message="Unknown User. Login and try again.";
           }
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("message", message);
        
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
            Logger.getLogger(save_ppmt.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_ppmt.class.getName()).log(Level.SEVERE, null, ex);
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
