/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facility_Management;

import DDTA.Manage;
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
public class update_facility_module_access extends HttpServlet {
    HttpSession session;
   String id,facility_id,gend_gbv,prep,kp,hts_self,hts_tst,hts_pos,index_testing,hts_recency,tx_new,ca_screening,pmtct,eid,retention,vl,tb,tpt,stf,status; 
   int code;
   String message,user_id;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        session = request.getSession();
        dbConn conn = new dbConn();
        
        Manage mg = new Manage();
        
        id=gend_gbv=prep=kp=hts_self=hts_tst=hts_pos=index_testing=hts_recency=tx_new=ca_screening=pmtct=eid=retention=vl=tb=tpt=stf="0";
        message="";
        code=0;
        
        user_id=null;
        
        if(session.getAttribute("user_id")!=null){
           user_id=session.getAttribute("user_id").toString(); 
       }
        
        
       if(request.getParameter("id")!=null){id = request.getParameter("id");}
       if(request.getParameter("gend_gbv")!=null){gend_gbv = "1";}
       if(request.getParameter("prep")!=null){prep = "1";}
       if(request.getParameter("kp")!=null){kp = "1";}
       if(request.getParameter("hts_self")!=null){hts_self = "1";}
       if(request.getParameter("hts_tst")!=null){hts_tst = "1";}
       if(request.getParameter("hts_pos")!=null){hts_pos = "1";}
       if(request.getParameter("index_testing")!=null){index_testing = "1";}
       if(request.getParameter("hts_recency")!=null){hts_recency = "1";}
       if(request.getParameter("tx_new")!=null){tx_new = "1";}
       if(request.getParameter("ca_screening")!=null){ca_screening = "1";}
       if(request.getParameter("pmtct")!=null){pmtct = "1";}
       if(request.getParameter("eid")!=null){eid = "1";}
       if(request.getParameter("retention")!=null){retention = "1";}
       if(request.getParameter("vl")!=null){vl = "1";}
       if(request.getParameter("tb")!=null){tb = "1";}
       if(request.getParameter("tpt")!=null){tpt = "1";}
       if(request.getParameter("stf")!=null){stf = "1";}
       facility_id=request.getParameter("f_id");
       
       if(user_id!=null){ // user exist in the session
           
       if(session.getAttribute("admin")!=null){
         
        if(session.getAttribute("admin").toString().equals("1")) { // user has rights to change permissions
      String timestamp = mg.get_timestamp();
      
         String updator = "REPLACE INTO reports_tracker SET  id=?,gend_gbv=?,prep=?,kp=?,hts_self=?,hts_tst=?,hts_pos=?,index_testing=?,hts_recency=?,tx_new=?,ca_screening=?,pmtct=?,eid=?,retention=?,vl=?,tb=?,tpt=?,stf=?,timestamp=?,facility_id=?";

         conn.pst = conn.conn.prepareStatement(updator);
         conn.pst.setString(1, id);
         conn.pst.setString(2, gend_gbv);
         conn.pst.setString(3, prep);
         conn.pst.setString(4, kp);
         conn.pst.setString(5, hts_self);
         conn.pst.setString(6, hts_tst);
         conn.pst.setString(7, hts_pos);
         conn.pst.setString(8, index_testing);
         conn.pst.setString(9, hts_recency);
         conn.pst.setString(10, tx_new);
         conn.pst.setString(11, ca_screening);
         conn.pst.setString(12, pmtct);
         conn.pst.setString(13, eid);
         conn.pst.setString(14, retention);
         conn.pst.setString(15, vl);
         conn.pst.setString(16, tb);
         conn.pst.setString(17, tpt);
         conn.pst.setString(18, stf);
         conn.pst.setString(19, timestamp);
         conn.pst.setString(20, facility_id);
         
        int num =  conn.pst.executeUpdate()-1;
        
        if(num>0){
        code=1;
           message="update was successfully";      
        }
        else{
        code=0;
        message="Error: No records were updated.";      
        }
            
       } 
        else{
         code=0;
         message="Error: Action not allowed.";     
        }
           
       }  else{
         code=0;
         message="Unknown user access.";  
       }  
           
           
           
       }else{
           code=0;
           message="Unknown user. Login to try again";
       }
       
        
        
        if( conn.conn!=null){conn.conn.close();}
        
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
            Logger.getLogger(update_facility_module_access.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(update_facility_module_access.class.getName()).log(Level.SEVERE, null, ex);
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
