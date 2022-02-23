/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DDTA;

import Database.dbConn;
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
                    "INNER JOIN counties c ON sc.county_id=c.id INNER JOIN user_counties uc ON c.id=uc.county_id WHERE c.user_id='"+user_id+"'";
        }
        
        else if(user_level.equals("4")){ // program user
          q = "SELECT f.id FROM facilities";  
        } 
        
        else{
            q="SELECT 0 as id";
        }
        
       conn.rs = conn.st.executeQuery(q);
        while(conn.rs.next()){
         list.add(conn.rs.getString(1));
        }
        return list;
    }
}
