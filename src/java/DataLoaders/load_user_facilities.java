/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLoaders;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_user_facilities extends HttpServlet {
    HttpSession session;
    String user_id,message,facility_id,user_level;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        dbConn conn = new dbConn();
        JSONArray jarray = new JSONArray();
        
        facility_id = "0";
        
        if(session.getAttribute("user_id")!=null){
            user_id = session.getAttribute("user_id").toString();
        }else{
           
        }
        if(session.getAttribute("facility_id")!=null){
            facility_id = session.getAttribute("facility_id").toString();
        }
        else{
           
        }
        
         if(session.getAttribute("user_level_id")!=null){
           user_level=session.getAttribute("user_level_id").toString(); 
       }
        else{
            user_level="";
        }
         
         
             if(session.getAttribute("user_id")!=null){
        String sub_counties=request.getParameter("sub_county_ids");
        if(sub_counties!=null){
            if(sub_counties.equals("")){sub_counties=null;}
        }
          User_Locations ul = new User_Locations();
        
               String selected_facilities = ul.load_user_selected_facilities("","","",user_level,user_id,conn);
               
        String query = "SELECT id,name FROM facilities f WHERE f.id in("+selected_facilities+")";       
   
        conn.rs = conn.st.executeQuery(query);
        while(conn.rs.next()){
            JSONObject obj = new JSONObject();
            obj.put("id", conn.rs.getInt(1));
            obj.put("name", conn.rs.getString(2));
            if(conn.rs.getString(1).equals(facility_id)){
                obj.put("pre_selected", 1);
            }
            else{
              obj.put("pre_selected", 0);   
            }
            jarray.add(obj);
        }
       }
             out.println(jarray);   
        if( conn.conn!=null){conn.conn.close();}
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
            Logger.getLogger(load_user_facilities.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_user_facilities.class.getName()).log(Level.SEVERE, null, ex);
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
