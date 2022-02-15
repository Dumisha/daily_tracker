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
import org.json.simple.JSONObject;

/**
 *
 * @author Geofrey Nyabuto
 */
public class update_user extends HttpServlet {
    HttpSession session;
    String id,approved,is_active,user_level,facility_id,county_id,sub_county_id;
    int nums;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConn conn = new dbConn();
        
        session = request.getSession();
        approved=is_active=user_level=facility_id=county_id=sub_county_id="";
        int code=0;
        nums=0;
        
        String message="";
        
                approved = request.getParameter("approved");
                id = request.getParameter("user");
                is_active = request.getParameter("status");
                user_level = request.getParameter("user_level");
                
              String  facilities [] = request.getParameter("facility").split(",");
              String counties[] = request.getParameter("county").split(",");
              String sub_counties [] = request.getParameter("sub_county").split(",");
                
                
//                if(session.getAttribute("user_id")!=null){
//                user_id = session.getAttribute("user_id").toString();
//                }
                
                String update = "UPDATE users SET approved=?,is_active=?, user_level_id=? WHERE id=?";
                conn.pst = conn.conn.prepareStatement(update);
                conn.pst.setString(1, approved);
                conn.pst.setString(2, is_active);
                conn.pst.setString(3, user_level);
                conn.pst.setString(4, id);
                
               int cases = conn.pst.executeUpdate();
               
               if(cases==1){
                   if(user_level.equals("1")){ // Update facility level users details
                       
                   // remove all user association in user facility table
                       String deleter = "DELETE FROM user_facilities WHERE user_id=?";
                       conn.pst = conn.conn.prepareStatement(deleter);
                       conn.pst.setString(1, id);
                       conn.pst.executeUpdate();
                       
                       
                       for(String f_id:facilities){
                        String adder = "INSERT INTO user_facilities (user_id,facility_id) VALUES(?,?)";
                        conn.pst = conn.conn.prepareStatement(adder);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, f_id);
                        nums+=conn.pst.executeUpdate();
                       }
                       
                   
                  if(nums>0){
                      code=1;
                      message= "User successfully associated with "+nums+" facilities successfully";
                  }
                  else{
                      code=0;
                      message="Unable to link user to facilities";
                  }
                       
                   }
                   
                   else if(user_level.equals("2")){ // sub county user
                       
                    // remove all user association in user sub counties table
                       String deleter = "DELETE FROM user_sub_counties WHERE user_id=?";
                       conn.pst = conn.conn.prepareStatement(deleter);
                       conn.pst.setString(1, id);
                       conn.pst.executeUpdate();
                       
                       
                       for(String sc_id:sub_counties){
                        String adder = "INSERT INTO user_sub_counties (user_id,sub_county_id) VALUES(?,?)";
                        conn.pst = conn.conn.prepareStatement(adder);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, sc_id);
                        nums+=conn.pst.executeUpdate();
                       }
                       
                   
                  if(nums>0){
                      code=1;
                      message= "User successfully associated with "+nums+" sub counties successfully";
                  }
                  else{
                      code=0;
                      message="Unable to link user to sub counties";
                  }
                       
                   }
                   
                   else if(user_level.equals("3")){ // county level user
                      
                    // remove all user association in user sub counties table
                       String deleter = "DELETE FROM user_counties WHERE user_id=?";
                       conn.pst = conn.conn.prepareStatement(deleter);
                       conn.pst.setString(1, id);
                       conn.pst.executeUpdate();
                       
                       
                       for(String c_id:counties){
                        String adder = "INSERT INTO user_counties (user_id,county_id) VALUES(?,?)";
                        conn.pst = conn.conn.prepareStatement(adder);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, c_id);
                        nums+=conn.pst.executeUpdate();
                       }
                       
                   
                  if(nums>0){
                      code=1;
                      message= "User successfully associated with "+nums+" counties successfully";
                  }
                  else{
                      code=0;
                      message="Unable to link user to counties";
                  }
                      
                   }
                   
                   else if(user_level.equals("4")){ // program user
                       
                   }
                   
                   else{ // unknown user level
                       
                   }
               }
               else{
                   code=0;
                   message="Failed to update user details.";
               }
        
               JSONObject obj = new JSONObject();
               obj.put("code", code);
               obj.put("message", message);
               
               
             System.out.println("User update response : "+obj);
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
            Logger.getLogger(update_user.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(update_user.class.getName()).log(Level.SEVERE, null, ex);
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
