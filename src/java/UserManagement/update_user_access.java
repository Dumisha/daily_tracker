/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManagement;

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
public class update_user_access extends HttpServlet {
    HttpSession session;
   String id,user_id,settings,users,admin,reports,ppmt,stf,hts,prevention,treatment,vl,tb,user_profile; 
   int code;
   String message;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        session = request.getSession();
        dbConn conn = new dbConn();
        
        Manage mg = new Manage();
        
        id=user_id=settings=users=admin=reports=ppmt=stf=hts=prevention=treatment=vl=tb=user_profile="0";
        message="";
        code=0;
        
        user_id=null;
        
        if(session.getAttribute("user_id")!=null){
           user_id=session.getAttribute("user_id").toString(); 
       }
        
        
       if(request.getParameter("user_access_id")!=null){id = request.getParameter("user_access_id");}
       if(request.getParameter("settings")!=null){settings = "1";}
       if(request.getParameter("users")!=null){users = "1";}
       if(request.getParameter("admin")!=null){admin = "1";}
       if(request.getParameter("reports")!=null){reports = "1";}
       if(request.getParameter("ppmt")!=null){ppmt = "1";}
       if(request.getParameter("stf")!=null){stf = "1";}
       if(request.getParameter("hts")!=null){hts = "1";}
       if(request.getParameter("prevention")!=null){prevention = "1";}
       if(request.getParameter("treatment")!=null){treatment = "1";}
       if(request.getParameter("vl")!=null){vl = "1";}
       if(request.getParameter("tb")!=null){tb = "1";}
       if(request.getParameter("user_profile")!=null){user_profile = "1";}
       
       if(user_id!=null){ // user exist in the session
           
       if(session.getAttribute("admin")!=null){
         
        if(session.getAttribute("admin").toString().equals("1")) { // user has rights to change permissions
      String timestamp = mg.get_timestamp();
      
         String updator = "REPLACE INTO module_management SET settings=?,users=?,admin=?,reports=?,ppmt=?,stf=?,hts=?,prevention=?,treatment=?,vl=?,"
                 + "tb=?,user_profile=?,updated_by=?,updated_at=?, user_id=?";
         conn.pst = conn.conn.prepareStatement(updator);
         conn.pst.setString(1, settings);
         conn.pst.setString(2, users);
         conn.pst.setString(3, admin);
         conn.pst.setString(4, reports);
         conn.pst.setString(5, ppmt);
         conn.pst.setString(6, stf);
         conn.pst.setString(7, hts);
         conn.pst.setString(8, prevention);
         conn.pst.setString(9, treatment);
         conn.pst.setString(10, vl);
         conn.pst.setString(11, tb);
         conn.pst.setString(12, user_profile);
         conn.pst.setString(13, user_id);
         conn.pst.setString(14, timestamp);
         conn.pst.setString(15, id);
         
            System.out.println("update access : "+conn.pst);
        int num =  conn.pst.executeUpdate();
        
        if(num>0){
        code=1;
           message=num+" Records updated successfully";      
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
            Logger.getLogger(update_user_access.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(update_user_access.class.getName()).log(Level.SEVERE, null, ex);
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
