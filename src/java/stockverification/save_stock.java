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
import org.json.simple.JSONObject;

/**
 *
 * @author mwamb
 */
public class save_stock extends HttpServlet {
    HttpSession session;
    String stock_id,facility,verification_date,delivery_note_number,document_date,commodity,batch_number,delivery_note_quantity,quantity_received,expiry_date,date_received,received_by,contacts,pipeline,comments;
    String message,time,minute,access_code;
    int code;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        dbConn conn = new dbConn();
        session = request.getSession();
        
        
        message="";
        code=0;
        String user_id="";
        if(session.getAttribute("user_id")!=null){
       user_id = session.getAttribute("user_id").toString();
            }
        else{
           user_id="0";
        }
        access_code = request.getParameter("code");
        stock_id = request.getParameter("stock_id");
        facility = request.getParameter("facility");
        verification_date = request.getParameter("verification_date");
        delivery_note_number = request.getParameter("delivery_note_number");
        document_date = request.getParameter("document_date");
        commodity = request.getParameter("commodity");
        batch_number = request.getParameter("batch_number");
        delivery_note_quantity = request.getParameter("delivery_note_quantity");
        quantity_received = request.getParameter("quantity_received");
        expiry_date = request.getParameter("expiry_date");
        date_received = request.getParameter("date_received");
        received_by = request.getParameter("received_by");
        contacts = request.getParameter("contacts");
        pipeline = request.getParameter("pipeline");
        comments = request.getParameter("comments");
        time = request.getParameter("time");
        minute = request.getParameter("minute");
        
        
        
        String check_code = "SELECT id FROM stock_code where code=? and is_active=?";
        conn.pst = conn.conn.prepareStatement(check_code);
        conn.pst.setString(1, access_code);
        conn.pst.setInt(2, 1);
        conn.rs = conn.pst.executeQuery();
        if(conn.rs.next()){
        
        if(stock_id.equals("0")){
        // checker
        
        String get_stock = "SELECT id FROM stock_data where delivery_note_number=? AND commodity_id=? AND batch_number=?";
        conn.pst = conn.conn.prepareStatement(get_stock);
        conn.pst.setString(1, delivery_note_number);
        conn.pst.setString(2, commodity);
        conn.pst.setString(3, batch_number);
        
        conn.rs = conn.pst.executeQuery();
        
        if(conn.rs.next()){
            message = "Similar quantity already captured";
            code=0;
        }
        else{
            // INSERT stock 
            String inserter = "INSERT INTO stock_data (facility_id,verification_date,delivery_note_number,document_date,commodity_id,batch_number,delivery_note_quantity,"
                    + "quantity_received,expiry_date,date_received,received_by,contacts,pipeline_id,comments,user_id,time,minute) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn.pst = conn.conn.prepareStatement(inserter);
            conn.pst.setString(1, facility);
            conn.pst.setString(2, verification_date);
            conn.pst.setString(3, delivery_note_number);
            conn.pst.setString(4, document_date);
            conn.pst.setString(5, commodity);
            conn.pst.setString(6, batch_number);
            conn.pst.setString(7, delivery_note_quantity);
            
            conn.pst.setString(8, quantity_received);
            conn.pst.setString(9, expiry_date);
            conn.pst.setString(10, date_received);
            conn.pst.setString(11, received_by);
            conn.pst.setString(12, contacts);
            conn.pst.setString(13, pipeline);
            conn.pst.setString(14, comments);
            conn.pst.setString(15, user_id);
            conn.pst.setString(16, time);
            conn.pst.setString(17, minute);
            
            int recs = conn.pst.executeUpdate();
            
            if(recs>0){
                message = "Stock data saved successfully";
                code=1;
            }
            else{
                message="Failed to save stock data";
                code=0;
            }
        }
        
    }
        
        else{
            // update stock quantities
            
              String updator = "UPDATE stock_data SET facility_id=?,verification_date=?,delivery_note_number=?,document_date=?,commodity_id=?,batch_number=?,delivery_note_quantity=?,"
                    + "quantity_received=?,expiry_date=?,date_received=?,received_by=?,contacts=?,pipeline_id=?,comments=?,user_id=?,time=?,minute=? WHERE id=?";
            conn.pst = conn.conn.prepareStatement(updator);
            conn.pst.setString(1, facility);
            conn.pst.setString(2, verification_date);
            conn.pst.setString(3, delivery_note_number);
            conn.pst.setString(4, document_date);
            conn.pst.setString(5, commodity);
            conn.pst.setString(6, batch_number);
            conn.pst.setString(7, delivery_note_quantity);
            
            conn.pst.setString(8, quantity_received);
            conn.pst.setString(9, expiry_date);
            conn.pst.setString(10, date_received);
            conn.pst.setString(11, received_by);
            conn.pst.setString(12, contacts);
            conn.pst.setString(13, pipeline);
            conn.pst.setString(14, comments);
            conn.pst.setString(15, user_id);
            conn.pst.setString(16, time);
            conn.pst.setString(17, minute);
            conn.pst.setString(18, stock_id);
            
            int recs = conn.pst.executeUpdate();
            
            if(recs>0){
                message = "Stock data updated successfully";
                code=1;
            }
            else{
                message="Failed to update stock data";
                code=0;
            }
        }
        }
        else{
           message="Wrong access code given. Provide the correct code";
           code=0;  
        }
        
        obj.put("code", code);
        obj.put("message", message);
        
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
            Logger.getLogger(save_stock.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_stock.class.getName()).log(Level.SEVERE, null, ex);
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
