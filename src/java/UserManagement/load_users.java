/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManagement;

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
public class load_users extends HttpServlet {
    HttpSession session;
    String user_coverage;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConn conn = new dbConn();
        JSONObject obj = new JSONObject();
        JSONArray jarray = new JSONArray();
        
        
        String get_pending_users = "SELECT id, concat_ws(\" \",first_name,middle_name,sur_name) as fullname, phone,email, user_level_id,approved,is_active from users";
        conn.rs = conn.st.executeQuery(get_pending_users);
        while(conn.rs.next()){
            user_coverage="";
            JSONObject ob = new JSONObject();
            
            ob.put("id", conn.rs.getInt(1));
            ob.put("fullname", conn.rs.getString(2));
            ob.put("phone", conn.rs.getString(3));
            ob.put("email", conn.rs.getString(4));
            ob.put("user_level_id", conn.rs.getInt(5));
            ob.put("approved", conn.rs.getInt(6));
            ob.put("is_active", conn.rs.getInt(7));

              user_coverage = getUserLocation(conn.rs.getInt(1),conn,conn.rs.getInt(5)); 
            
            
            
            
            
            ob.put("coverage", user_coverage);
           jarray.add(ob);
            
        }
        
        obj.put("users", jarray);
        
        
        System.out.println(obj);
        
        
        
        
        if( conn.conn!=null){conn.conn.close();}
        out.print(obj);
        
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
            Logger.getLogger(load_users.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_users.class.getName()).log(Level.SEVERE, null, ex);
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

    
    private String getUserLocation(int user_id,dbConn conn,int user_level) throws SQLException{
        String locations="";
        System.out.println("user id is : "+user_id+" and level is : "+user_level);
        int num = 1;
        switch(user_level){
            
            case 1:
                String getLocations="SELECT f.name,f.mfl_code FROM user_facilities uf "
                        + "INNER JOIN facilities f ON uf.facility_id=f.id WHERE uf.user_id=?";

                conn.pst1 = conn.conn.prepareStatement(getLocations);
                conn.pst1.setInt(1, user_id);
                conn.rs1 = conn.pst1.executeQuery();

                while(conn.rs1.next()){
                    locations+= num+". "+conn.rs1.getString(1)+" <br>";
                    num++;
                }

                break;
    
        
        case 2: // sub county users
            
                getLocations="SELECT sc.name FROM user_sub_counties usc "
                       + "INNER JOIN sub_counties sc ON usc.sub_county_id=sc.id WHERE usc.user_id=?";

               conn.pst1 = conn.conn.prepareStatement(getLocations);
               conn.pst1.setInt(1, user_id);
               conn.rs1 = conn.pst1.executeQuery();

               while(conn.rs1.next()){
                  locations+= num+". "+conn.rs1.getString(1)+" <br>";
                    num++;
               }   
               break;
        
        case 3: 
              
                getLocations="SELECT c.name FROM user_counties uc "
                       + "INNER JOIN counties c ON uc.county_id=c.id WHERE uc.user_id=?";

               conn.pst1 = conn.conn.prepareStatement(getLocations);
               conn.pst1.setInt(1, user_id);
               conn.rs1 = conn.pst1.executeQuery();

               while(conn.rs1.next()){
                  locations+= num+". "+conn.rs1.getString(1)+" <br>";
                    num++;
               }  

               break;
        
        case 4:
            locations = "Program";
            break;
         
        default: // no defined location
            locations = "Not Assigned"; 
        
}
        
        return locations;
    
}
}