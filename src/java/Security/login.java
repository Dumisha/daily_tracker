/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

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

/**
 *
 * @author Geofrey Nyabuto
 */
public class login extends HttpServlet {
String username,password;
String db_pass,db_salt;
String fullname,phone,email,login_message;
int login_code,user_level_id,is_active,user_id,approved;
String NextPage="";       
            HttpSession session;   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
           dbConn conn = new dbConn();
           
           session = request.getSession(); 
           
           
             password = "myPassword123";
             
             password = request.getParameter("password");
             username = request.getParameter("username");
             
             NextPage="index.jsp";
             
             fullname=phone=email=login_message="";
             login_code=user_level_id=is_active=approved=0;
             
             String GetUserSecurityDetails = "SELECT password,salt, concat_ws(\" \",first_name,middle_name,sur_name) as fullname,phone,email,is_active,user_level_id,id,approved FROM users where (phone=? || email=?) AND (email IS NOT NULL OR email!='')";
             conn.pst = conn.conn.prepareStatement(GetUserSecurityDetails);
             conn.pst.setString(1, username);
             conn.pst.setString(2, username);
             
             conn.rs = conn.pst.executeQuery();
             
             if(conn.rs.next()){
                 db_pass = conn.rs.getString(1);
                 db_salt = conn.rs.getString(2);     
//                 
//        // Encrypted and Base64 encoded password read from database
//        String securePassword = "HhaNvzTsVYwS/x/zbYXlLOE3ETMXQgllqrDaJY9PD/U=";
//        
//        // Salt value stored in database 
//        String salt = "EqdmPh53c9x33EygXpTpcoJvc4VXLK";
        
        boolean passwordMatch = PasswordUtils.verifyUserPassword(password,db_pass, db_salt);
        
        if(passwordMatch) 
        {
            System.out.println("Provided user password " + password + " is correct.");
            
          // get and return user information
                    is_active = conn.rs.getInt(7);
                    approved = conn.rs.getInt(9);
            
            if(is_active==1 && approved==1){
                    fullname = conn.rs.getString(3);
                    phone = conn.rs.getString(4);
                    email = conn.rs.getString(5);
                    user_level_id = conn.rs.getInt(6);
                    user_id = conn.rs.getInt(8);
                 
                  session.setAttribute("fullname", fullname);
                  session.setAttribute("phone", phone);
                  session.setAttribute("email", email);
                  session.setAttribute("user_level_id", user_level_id);
                  session.setAttribute("user_id", user_id);
                    
                    System.out.println("user id login : "+user_id);
                    
                login_code=1;
                login_message="User Logged in Successfully";
                NextPage="home.jsp"; 
            }
            
            else if(is_active==1 && approved==0){
               login_code=0;
                login_message="User is pending approval from management";
                NextPage="home.jsp";    
            }
            
            else{
              login_code=0;
                login_message="User Account de-activated. Please contact support";  
            }
             
        } else {
          login_code=0;
          login_message="Wrong Password Provided. Try Again";    
            
            System.out.println("Provided password is incorrect");
        } 
                 
                 
             }
             
             else{
                 // unknown user
                 
                 login_code=0;
                login_message="Unknown Phone or email provided";  
             }     
      
             // setsession
             session.setAttribute("code", login_code);
             session.setAttribute("login", login_message);
            
             response.sendRedirect(NextPage);
             

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
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
