/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DDTA;

import Database.dbConn;
import com.mysql.jdbc.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Geofrey Nyabuto
 */
public class Manage {
    
    
  public String removeLast(String str, int num) {  
    if (str != null && str.length() >= num) {
        str = str.substring(0, str.length() - num);
    }
    return str;
    }
           
    public boolean isNumeric(String s) {
     boolean isint=true;
     try{
         Integer.parseInt(s);
     }
     catch(NumberFormatException e){
         isint=false;
     }
     
//     s != null && s.matches("[-+]?\\d*\\.?\\d+");
        return   isint;
}
    
       public String get_timestamp(){
           return new Timestamp(System.currentTimeMillis()).toString();
       }
    
    public String get_timestamp_string(){
           return new Timestamp(System.currentTimeMillis()).toString().replace(" ", "--").replace("-", "_").replace(":", "_");
       }
    
    
    public ArrayList<String> get_locations(String user_id,String user_level,dbConn conn) throws SQLException{
        ArrayList<String> list = new ArrayList();
        list.clear();
        String q = "";
        if(user_level.equals("1")){ // facility user
           q = "SELECT f.id FROM facilities f INNER JOIN user_facilities uf ON f.id=uf.facility_id WHERE uf.user_id='"+user_id+"'";
        }
        
        else if(user_level.equals("2")){ // sub county user
         q = "SELECT f.id FROM facilities f INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                    "INNER JOIN user_sub_counties usc ON sc.id=usc.sub_county_id WHERE usc.user_id='"+user_id+"'";   
        }
        
        else if(user_level.equals("3")){ // county user
            q = "SELECT f.id FROM facilities f INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                    "INNER JOIN counties c ON sc.county_id=c.id INNER JOIN user_counties uc ON c.id=uc.county_id WHERE uc.user_id='"+user_id+"'";
        }
        
        else if(user_level.equals("4")){ // program user
          q = "SELECT f.id FROM facilities f";  
        } 
        
        else{
            q="SELECT 0 as f.id";
        }
        
        System.out.println("q is : "+q);
       conn.rs = conn.st.executeQuery(q);
        while(conn.rs.next()){
         list.add(conn.rs.getString(1));
        }
        
        return list;
    }
    
    
    public boolean check_sp_processor_indicator(dbConn conn,String indicator_id) throws SQLException{
        String checker = "SELECT id FROM sp_processor WHERE indicator_id=?";
        conn.pst = conn.conn.prepareStatement(checker);
        conn.pst.setString(1, indicator_id);
        conn.rs = conn.pst.executeQuery();
        return conn.rs!=null;
    }
    
    
           public void process_queue(dbConn conn,String status) throws SQLException{ // 1 for new entry, 2 for update
           String updator;
           String get_pending = " SELECT entry_key,sp_name,q.indicator_id FROM queue q INNER JOIN sp_processor spp ON q.indicator_id=spp.indicator_id group by entry_key";
           conn.rs = conn.st.executeQuery(get_pending);
           while(conn.rs.next()){
             // call sp to populate ETL 
             updator = ("{CALL "+conn.rs.getString(2)+"}").replace("?", "'"+conn.rs.getString(1)+"'");
              //execute call
              
               CallableStatement cs = (CallableStatement) conn.conn.prepareCall(updator);
                 int executed = cs.executeUpdate();
                  
                 System.out.println("called to etl"+updator);
               // remove record 
               if(executed>0){
               String deleter = "DELETE FROM queue WHERE entry_key=?";
               conn.pst1 = conn.conn.prepareStatement(deleter);
               conn.pst1.setString(1, conn.rs.getString(1));
               
                conn.pst1.executeUpdate();
               }
               if(conn.rs.getInt(3)==6 || conn.rs.getInt(3)==8){ // FOR HTS POS or te new
                populate_tx_new(conn,conn.rs.getString(1));
                   System.out.println(" it is a tx new --------------------------------");
               }
           }
        }
           
                  public void populate_tx_new(dbConn conn,String entry_key) throws SQLException{
           CallableStatement cs = (CallableStatement) conn.conn.prepareCall("{CALL sp_update_etl_tx_new('"+entry_key+"')}");
               cs.executeUpdate();
       }
                  
                        
       public String get_entry_key(String facility_id, String user_id){
           Timestamp timestamp = new Timestamp(System.currentTimeMillis());
           String key = timestamp.toString().replaceAll("[- :.]", "")+"-"+facility_id+"-"+user_id ;
           
           System.out.println("key is "+key);
           return key;
       }
       
       
       public void update_queue(dbConn conn,String entry_key,String status,String indicator_id) throws SQLException{ // 1 for new entry, 2 for update
        String adder = "INSERT INTO queue (entry_key,status,indicator_id) VALUES(?,?,?)";
        conn.pst = conn.conn.prepareStatement(adder);
        conn.pst.setString(1, entry_key);
        conn.pst.setString(2, status);
        conn.pst.setString(3, indicator_id);
        
        conn.pst.executeUpdate();
       }

    public String check_OS(){
        return System.getProperty("os.name");
    }
}
