/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.mysql.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Geofrey Nyabuto
 */
public final class dbConn {

  public ResultSet rs0,rs, rs1, rs2, rs3;
  public Statement st0,st, st1, st2, st3;
  public  PreparedStatement pst0, pst,pst1,pst2,pst3;
  public  CallableStatement csmt0, csmt,csmt1,csmt2,csmt3;
public  Connection conn = null;
    public dbConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
              conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/ddta","root", "admin"); // local setup


                            st0 = conn.createStatement();
                            st = conn.createStatement();
                            st1 = conn.createStatement();
                            st2 = conn.createStatement();
                            st3 = conn.createStatement();

          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR WHILE CONNECTING TO DATABASE. CHECK YOUR CONNECTION CREDENTIALS SETTINGS in dbConn.java");
            //error in dbase configuration 
            //call the jsp page that does configuration

        } catch (InstantiationException ex) {
            Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR WHILE CONNECTING TO DATABASE. CHECK YOUR CONNECTION CREDENTIALS SETTINGS in dbConn.java");
            //error in dbase configuration
            //call the jsp page that does configuration
      } catch (IllegalAccessException ex) {
          Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("ERROR WHILE CONNECTING TO DATABASE. CHECK YOUR CONNECTION CREDENTIALS SETTINGS in dbConn.java");
          //error in dbase configuration
          //call the jsp page that does configuration
      } catch (SQLException ex) {
          Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("ERROR WHILE CONNECTING TO DATABASE. CHECK YOUR CONNECTION CREDENTIALS SETTINGS in dbConn.java");
          //error in dbase configuration
          //call the jsp page that does configuration
      }
    }
}
