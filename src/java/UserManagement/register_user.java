/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManagement;

import Database.dbConn;
import Security.PasswordUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Geofrey Nyabuto
 */
public class register_user extends HttpServlet {
String first_name,middle_name,sur_name,email,phone,salt,password,pass1,pass2,message;
int code;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConn conn = new dbConn();
        code=0;
        first_name = request.getParameter("fname");
        middle_name = request.getParameter("mname");
        sur_name = request.getParameter("lname");
        email = request.getParameter("email");
        phone = request.getParameter("phone");
        pass1 = request.getParameter("pass1");
        pass2 = request.getParameter("pass2");
        
        
        if(pass1.equals(pass2)){
          
            //
            if(!user_exist(phone,email,conn)){ // user does not exist
              // add as new user 
                
                salt = PasswordUtils.getSalt(30);
                password = PasswordUtils.generateSecurePassword(pass1, salt);
        
        
              String add_user = "INSERT INTO users(first_name,middle_name,sur_name,phone,email,salt,password) VALUES(?,?,?,?,?,?,?)";
              conn.pst = conn.conn.prepareStatement(add_user);
              conn.pst.setString(1, first_name);
              conn.pst.setString(2, middle_name);
              conn.pst.setString(3, sur_name);
              conn.pst.setString(4, phone);
              conn.pst.setString(5, email);
              conn.pst.setString(6, salt);
              conn.pst.setString(7, password);
              
            int cases = conn.pst.executeUpdate();
             
                System.out.println("number of records added :"+cases);
                
                code=1;
                message="User registered successfully.";
            }
            else{
                code=0;
                message="User with similar phone number and/or email already exist in the system";
            }
            
            
        }
        else{
            code=0;
            message="Password do not match";
        }
        
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("message", message);
        
        System.out.println("obj :"+obj);
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
        Logger.getLogger(register_user.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(register_user.class.getName()).log(Level.SEVERE, null, ex);
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

    
    private boolean user_exist(String phone, String email,dbConn conn) throws SQLException{        
        String query = "SELECT id FROM users where (phone=? OR email=?) AND (email is not null or email!='')";
        conn.pst = conn.conn.prepareStatement(query);
        conn.pst.setString(1, phone);
        conn.pst.setString(2, email);
        conn.rs = conn.pst.executeQuery();
        return conn.rs.next();
    }
}
