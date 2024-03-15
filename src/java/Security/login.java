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
int settings,users,reports,ppmt,stf,hts,prevention,treatment,vl,tb,user_profile,admin,dashboard,mne;
String NextPage="";       
            HttpSession session;   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        
           dbConn conn = new dbConn();
           
           session = request.getSession(); 
           
           
//             password = "myPassword123";
             
             password = request.getParameter("password");
             username = request.getParameter("username");
             
             NextPage="index.jsp";
             
             fullname=phone=email=login_message="";
             login_code=user_level_id=is_active=approved=0;
             
             String GetUserSecurityDetails = "SELECT password,salt, concat_ws(\" \",first_name,middle_name,sur_name) as fullname,\n" +
                    "phone,email,is_active,user_level_id,u.id,approved,\n" +
                    "IFNULL(mg.settings,0) AS settings,IFNULL(mg.users,0) AS users,IFNULL(mg.reports,0) AS reports,IFNULL(mg.ppmt,0) AS ppmt,IFNULL(mg.stf,0) AS stf,\n" +
                    "IFNULL(mg.hts,0) AS hts,IFNULL(mg.prevention,0) AS prevention,IFNULL(mg.treatment,0) AS treatment,"
                    + "IFNULL(mg.vl,0) AS vl,IFNULL(mg.tb,0) AS tb,IFNULL(mg.user_profile,0) AS user_profile,IFNULL(mg.admin,0) AS admin,IFNULL(mg.dashboard,0) AS dashboard,IFNULL(mg.mne,0) AS mne  \n" +
                    "FROM users u\n" +
                    "LEFT OUTER JOIN module_management mg on u.id=mg.user_id "
                                         + " where (phone=? || email=?) AND (email IS NOT NULL OR email!='')";
             
             conn.pst = conn.conn.prepareStatement(GetUserSecurityDetails);
             conn.pst.setString(1, username);
             conn.pst.setString(2, username);
             
             System.out.println("query login "+conn.pst);
             conn.rs = conn.pst.executeQuery();
             
             if(conn.rs.next()){
                 db_pass = conn.rs.getString(1);
                 db_salt = conn.rs.getString(2);     

        boolean passwordMatch = PasswordUtils.verifyUserPassword(password,db_pass, db_salt);
        
        if(passwordMatch) 
        {

   
          // get and return user information
                    is_active = conn.rs.getInt(6);
                    approved = conn.rs.getInt(9);
                    
                    settings = conn.rs.getInt(10);
                    users = conn.rs.getInt(11);
                    reports = conn.rs.getInt(12);
                    ppmt = conn.rs.getInt(13);
                    stf = conn.rs.getInt(14);
                    hts = conn.rs.getInt(15);
                    prevention = conn.rs.getInt(16);
                    treatment = conn.rs.getInt(17);
                    vl = conn.rs.getInt(18);
                    tb = conn.rs.getInt(19);
                    user_profile = conn.rs.getInt(20);
                    admin = conn.rs.getInt(21);
                    dashboard = conn.rs.getInt(22);
                    mne = conn.rs.getInt(23);
                    
                    
            if(is_active==1 && approved==1){
                    fullname = conn.rs.getString(3);
                    phone = conn.rs.getString(4);
                    email = conn.rs.getString(5);
                    user_level_id = conn.rs.getInt(7);
                    user_id = conn.rs.getInt(8);
                 
                  session.setAttribute("fullname", fullname);
                  session.setAttribute("phone", phone);
                  session.setAttribute("email", email);
                  session.setAttribute("user_level_id", user_level_id);
                  session.setAttribute("user_id", user_id);
                  
                  // for user management
                  session.setAttribute("settings", settings);
                  session.setAttribute("users", users);
                  session.setAttribute("admin", admin);
                  session.setAttribute("reports", reports);
                  session.setAttribute("ppmt", ppmt);
                  session.setAttribute("stf", stf);
                  session.setAttribute("hts", hts);
                  session.setAttribute("prevention", prevention);
                  session.setAttribute("treatment", treatment);
                  session.setAttribute("vl", vl);
                  session.setAttribute("tb", tb);
                  session.setAttribute("mne", mne);
                  session.setAttribute("user_profile", user_profile);
                  session.setAttribute("dashboard", dashboard);
                  session.setAttribute("custom", 1);
                    
                login_code=1;
                login_message="User Logged in Successfully";
                NextPage="dashboards.jsp"; 
            }
            
            else if(is_active==1 && approved==0){
               login_code=0;
                login_message="User is pending approval from management";
                NextPage="dashboards.jsp";    
            }
            
            else{
              login_code=0;
                login_message="User Account de-activated. Please contact support";  
            }
             
        } else {
          login_code=0;
          login_message="Wrong Password Provided. Try Again";    
            
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
            
             
             
             if( conn.conn!=null){conn.conn.close();}
             
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
