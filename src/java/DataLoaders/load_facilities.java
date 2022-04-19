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
public class load_facilities extends HttpServlet {
String facility_id,user_id;
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        dbConn conn = new dbConn();
        JSONObject obj = new JSONObject();
        JSONArray jarray = new JSONArray();
        session = request.getSession();
        
        facility_id = "0";
        user_id="";
        if(session.getAttribute("user_id")!=null){
            user_id = session.getAttribute("user_id").toString();
        }else{
           
        }
        if(session.getAttribute("facility_id")!=null){
            facility_id = session.getAttribute("facility_id").toString();
        }
        else{
           
        }
        
             if(session.getAttribute("user_id")!=null){
        String get_facilities = "SELECT id,sub_county_id,name,mfl_code FROM facilities order by name";
        conn.rs = conn.st.executeQuery(get_facilities);
        
        while(conn.rs.next()){
          JSONObject ob = new JSONObject();
          ob.put("id", conn.rs.getInt(1));
          ob.put("sub_county_id", conn.rs.getString(2));
          ob.put("name", conn.rs.getString(3));
          ob.put("mfl_code", conn.rs.getString(4));
          
          if(conn.rs.getString(1).equals(facility_id)){
                ob.put("pre_selected", 1);
            }
            else{
              ob.put("pre_selected", 0);   
            }
          jarray.add(ob);
        }
             }
             
       obj.put("data", jarray);
        
        
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
            Logger.getLogger(load_facilities.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_facilities.class.getName()).log(Level.SEVERE, null, ex);
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
