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
import org.json.simple.JSONObject;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_user_modules extends HttpServlet {
    HttpSession session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        dbConn conn = new dbConn();
        
        String id;
        id= request.getParameter("id");
//        id="1";
        
        JSONObject obj = new JSONObject();
        obj.clear();
        
        String query = "SELECT u.id as user_id,IFNULL(settings,0) AS settings,IFNULL(admin,0) AS admin,IFNULL(users,0) as users,IFNULL(reports,0) as reports,\n" +
                        "IFNULL(ppmt,0) as ppmt,IFNULL(stf,0) as stf,IFNULL(hts,0) as hts,IFNULL(prevention,0) as prevention,IFNULL(treatment,0) as treatment,\n" +
                        "IFNULL(vl,0) as vl,IFNULL(tb,0) as tb,IFNULL(user_profile,0) as user_profile,IFNULL(updated_by,0) as updated_by,IFNULL(updated_at,\"\") as updated_at  \n" +
                        "from users u LEFT OUTER JOIN module_management mg on u.id=mg.user_id where u.id=?";
        conn.pst = conn.conn.prepareStatement(query);
        conn.pst.setString(1, id);
        conn.rs = conn.pst.executeQuery();
        
        System.out.println("pst : "+conn.pst);
        
        
        if(conn.rs.next()){
         obj.put("user_id", conn.rs.getInt(1));
         obj.put("settings", conn.rs.getInt(2));
         obj.put("admin", conn.rs.getInt(3));
         obj.put("users", conn.rs.getInt(4));
         obj.put("reports", conn.rs.getInt(5));
         obj.put("ppmt", conn.rs.getInt(6));
         obj.put("stf", conn.rs.getInt(7));
         obj.put("hts", conn.rs.getInt(8));
         obj.put("prevention", conn.rs.getInt(9));
         obj.put("treatment", conn.rs.getInt(10));
         obj.put("vl", conn.rs.getInt(11));
         obj.put("tb", conn.rs.getInt(12));
         obj.put("user_profile", conn.rs.getInt(13));
         obj.put("updated_by", conn.rs.getInt(14));
         obj.put("updated_at", conn.rs.getInt(15));
         obj.put("code", 1);
         obj.put("message", "User access information loaded successfully");
        }
        else{
         obj.put("code", 0);
         obj.put("message", "User does not exist");    
        }
        
        System.out.println("obj :"+obj);
        
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
            Logger.getLogger(load_user_modules.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_user_modules.class.getName()).log(Level.SEVERE, null, ex);
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
