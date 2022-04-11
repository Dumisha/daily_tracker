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
        
        String sub_counties=request.getParameter("sub_county_ids");
        if(sub_counties!=null){
            if(sub_counties.equals("")){sub_counties=null;}
        }
        System.out.println("sub counties are : "+sub_counties);
        
//        user_id = "1";
        
        String query;
        if(sub_counties==null){
         query = "SELECT f.id,f.name FROM facilities f INNER JOIN user_facilities uf ON f.id=uf.facility_id WHERE uf.user_id='"+user_id+"'";   
        }
        
        else{
        if(user_level.equals("1")){ // facility access 
         query="SELECT distinct(f.id) as id,f.name FROM facilities f  \n" +
                "INNER JOIN user_facilities uf ON f.id=uf.facility_id and uf.user_id='"+user_id+"' and f.sub_county_id in("+sub_counties+")  ";   
        }
        else if(user_level.equals("2")){ // sub county access 
         query="SELECT distinct(f.id) as id,f.name FROM facilities f  \n" +
                "INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                "INNER JOIN user_sub_counties usc on sc.id=usc.sub_county_id and usc.user_id='"+user_id+"' and usc.sub_county_id in("+sub_counties+") ";   
        }
        else if(user_level.equals("3")){ // county access
        query="SELECT distinct(f.id) as id,f.name FROM facilities f  \n" +
                "INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                "INNER JOIN counties c ON sc.county_id=c.id \n" +
                "INNER JOIN user_counties uc on c.id=uc.county_id and uc.user_id='"+user_id+"' and sc.id in("+sub_counties+") ";   
        }
        else{
     query="SELECT distinct(f.id) as id,f.name FROM facilities f  \n" +
            "INNER JOIN sub_counties sc ON f.sub_county_id=sc.id  and sc.id in("+sub_counties+") " ;   
        }
                
        }        
   
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

        System.out.println(" array of facilities for user : "+jarray);
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
