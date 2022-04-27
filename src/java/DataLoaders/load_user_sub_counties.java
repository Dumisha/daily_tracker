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
public class load_user_sub_counties extends HttpServlet {
    HttpSession session;
    String user_id,user_level;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        session = request.getSession();
        dbConn conn = new dbConn();
        
        String county_ids = request.getParameter("county_ids");
        
        
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
        
        
        
        JSONArray jarray = new JSONArray();
             if(session.getAttribute("user_id")!=null){
        String query;
        
        
        if(!county_ids.equals("")){
        if(user_level.equals("1")){
         query="SELECT distinct(sc.id) as id,sc.name FROM sub_counties sc \n" +
                "inner join facilities f on sc.id=f.sub_county_id  and sc.county_id in("+county_ids+") \n" +
                "inner join user_facilities uf on f.id=uf.facility_id and uf.user_id='"+user_id+"' ";   
        }
        else if(user_level.equals("2")){
         query="SELECT distinct(sc.id) as id,sc.name FROM sub_counties sc \n" +
               "inner join user_sub_counties usc on sc.id=usc.sub_county_id and usc.user_id='"+user_id+"' and sc.county_id in("+county_ids+")";   
        }
        else if(user_level.equals("3")){
        query="SELECT distinct(sc.id) as id,sc.name FROM sub_counties sc \n" +
"inner join user_counties uc on sc.county_id=uc.county_id and uc.user_id='"+user_id+"' and sc.county_id in("+county_ids+") ";   
        }
        else{
     query="SELECT distinct(sc.id) as id,sc.name FROM sub_counties sc where sc.county_id in("+county_ids+") " ;   
        }
        } 
        
        else{
        if(user_level.equals("1")){
         query="SELECT distinct(sc.id) as id,sc.name FROM sub_counties sc \n" +
                "inner join facilities f on sc.id=f.sub_county_id \n" +
                "inner join user_facilities uf on f.id=uf.facility_id and uf.user_id='"+user_id+"' ";   
        }
        else if(user_level.equals("2")){
         query="SELECT distinct(sc.id) as id,sc.name FROM sub_counties sc \n" +
               "inner join user_sub_counties usc on sc.id=usc.sub_county_id and usc.user_id='"+user_id+"'";   
        }
        else if(user_level.equals("3")){
        query="SELECT distinct(sc.id) as id,sc.name FROM sub_counties sc \n" +
"inner join user_counties uc on sc.county_id=uc.county_id and uc.user_id='"+user_id+"'";   
        }
        else{
     query="SELECT distinct(sc.id) as id,sc.name FROM sub_counties sc  " ;   
        }   
        }
        
//         System.out.println("query is : "+query);
         
        conn.rs = conn.st.executeQuery(query);
        while(conn.rs.next()){
            JSONObject obj = new JSONObject();
            obj.put("id", conn.rs.getInt(1));
            obj.put("name", conn.rs.getString(2));
            
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
            Logger.getLogger(load_user_sub_counties.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_user_sub_counties.class.getName()).log(Level.SEVERE, null, ex);
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
