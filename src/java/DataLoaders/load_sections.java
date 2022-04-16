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
public class load_sections extends HttpServlet {
    HttpSession session;
    String id,name;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        
        dbConn conn = new dbConn();
        
        JSONObject obj = new JSONObject();
        JSONArray jarray = new JSONArray();
                
        String get_sections = "SELECT id,name FROM sections ";
        conn.rs = conn.st.executeQuery(get_sections);
        
        while(conn.rs.next()){
           JSONObject ob = new JSONObject();
           
           ob.put("section_id", conn.rs.getInt(1));
           ob.put("name", conn.rs.getString(2));
           ob.put("indicators", load_indicators(conn, conn.rs.getInt(1)));
           jarray.add(ob);
           
           
        }
        
        obj.put("data", jarray);
        
        System.out.println("obj sections :"+obj);
        
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
            Logger.getLogger(load_sections.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_sections.class.getName()).log(Level.SEVERE, null, ex);
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

    
    private JSONArray load_indicators(dbConn conn, int section_id) throws SQLException{
        JSONArray jarray = new JSONArray();
     
        String get_indicators = "SELECT id,name,description,indicator_type,frequency FROM indicators WHERE status=1 AND section_id='"+section_id+"'"; // active indicators
        conn.rs1 = conn.st1.executeQuery(get_indicators);
        while(conn.rs1.next()){
        JSONObject ob = new JSONObject();
        ob.put("indicator_id", conn.rs1.getInt(1));
        ob.put("indicator_name", conn.rs1.getString(2));
        ob.put("description", conn.rs1.getString(3));
        ob.put("indicator_type", conn.rs1.getInt(4));
        ob.put("frequency", conn.rs1.getInt(5));
        
        jarray.add(ob);
    }
        
      return jarray;  
    }
}
