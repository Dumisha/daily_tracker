/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package stockverification;

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
 * @author mwamb
 */
public class load_stock_data extends HttpServlet {
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        session = request.getSession();
        dbConn conn = new dbConn();
        
        JSONArray jarray = new JSONArray();
        
//        if(session.getAttribute("user_id")!=null){
//            String user_id=session.getAttribute("user_id").toString();
         String get_commodities = " SELECT \n" +
                        " sd.id as id,\n" +
                        " f.name as facility_name,\n" +
                        " delivery_note_number,\n" +
                        " concat(sc.commodity,\" ,\",sc.packs) as commodity,\n" +
                        " batch_number,\n" +
                        " quantity_received,\n" +
                        " concat_ws(\" \",first_name,middle_name,sur_name)  as entered_by,\n" +
                        " verification_date,commodity_id,facility_id,document_date,"+
                        "delivery_note_quantity,expiry_date,date_received,received_by,"+
                        "contacts,pipeline_id,comments,time,minute\n" +
                        " FROM  stock_data sd \n" +
                        " INNER JOIN stock_commodities sc ON sd.commodity_id=sc.id \n" +
                        " INNER JOIN facilities f ON sd.facility_id=f.id \n" +
                        " LEFT OUTER JOIN users u ON sd.user_id=u.id ORDER by sd.timestamp DESC ";
         
//        System.out.println("Query :"+get_commodities);
                 
        conn.rs = conn.st.executeQuery(get_commodities);
        while(conn.rs.next()){
            JSONObject obj = new JSONObject();
            obj.put("id", conn.rs.getInt(1));
            obj.put("facility_name", conn.rs.getString(2));
            obj.put("delivery_note_number", conn.rs.getString(3));
            obj.put("commodity", conn.rs.getString(4));
            obj.put("batch_number", conn.rs.getString(5));
            obj.put("quantity_received", conn.rs.getInt(6));
            obj.put("entered_by", conn.rs.getString(7));
            obj.put("verification_date", conn.rs.getString(8));
            obj.put("commodity_id", conn.rs.getString(9));
            obj.put("facility_id", conn.rs.getString(10));
            obj.put("document_date", conn.rs.getString(11));
            obj.put("delivery_note_quantity", conn.rs.getString(12));
            obj.put("expiry_date", conn.rs.getString(13));
            obj.put("date_received", conn.rs.getString(14));
            obj.put("received_by", conn.rs.getString(15));
            obj.put("contacts", conn.rs.getString(16));
            obj.put("pipeline_id", conn.rs.getString(17));
            obj.put("comments", conn.rs.getString(18));
            obj.put("time", conn.rs.getString(19));
            obj.put("minute", conn.rs.getString(20));
           
            
            jarray.add(obj);
        }
//        }
        
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
            Logger.getLogger(load_stock_data.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_stock_data.class.getName()).log(Level.SEVERE, null, ex);
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
