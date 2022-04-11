/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManagement;

import Database.dbConn;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Geofrey Nyabuto
 */
public class User_Locations {
    dbConn conn = new dbConn();
    
 public JSONObject facility_user(String user_id) throws SQLException{
     JSONArray jarray_c = new JSONArray();
     JSONArray jarray_sc = new JSONArray();
     JSONArray jarray_f = new JSONArray();
     
     String  query = "SELECT c.id as county_id, c.name as county_name, sc.id as sub_county_id, \n" +
                    "sc.name as sub_county_name, f.id as facility_id,f.name as facility_name \n" +
                    "FROM facilities f \n" +
                    "INNER JOIN user_facilities uf ON uf.facility_id=f.id and uf.user_id='"+user_id+"'\n" +
                    "INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                    "INNER JOIN counties c ON sc.county_id=c.id";
     conn.rs = conn.st.executeQuery(query);
     while(conn.rs.next()){
         JSONObject job_c = new JSONObject();
         JSONObject job_sc = new JSONObject();
         JSONObject job_f = new JSONObject();
         
         // counties
         job_c.put("id", conn.rs.getInt(1));
         job_c.put("name", conn.rs.getString(2));
         
        if(!jarray_c.contains(job_c)){jarray_c.add(job_c);}
        
        // sub  counties
         job_sc.put("id", conn.rs.getInt(3));
         job_sc.put("name", conn.rs.getString(4));
         
        if(!jarray_sc.contains(job_sc)){jarray_sc.add(job_sc);}
        
        // facilities
         job_f.put("id", conn.rs.getInt(5));
         job_f.put("name", conn.rs.getString(6));
         
        if(!jarray_f.contains(job_f)){jarray_f.add(job_f);}
         
     }
     
     JSONObject obj = new JSONObject();
     obj.put("counties", jarray_c);
     obj.put("sub_counties", jarray_sc);
     obj.put("facilities", jarray_f);
     
  
     return obj;
 }
 public JSONObject sub_county_user(String user_id) throws SQLException{
     JSONArray jarray_c = new JSONArray();
     JSONArray jarray_sc = new JSONArray();
     JSONArray jarray_f = new JSONArray();
     
     String  query = "SELECT c.id as county_id, c.name as county_name, sc.id as sub_county_id, \n" +
                    "sc.name as sub_county_name, f.id as facility_id,f.name as facility_name \n" +
                    "FROM facilities f \n" +
                    "INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                    "INNER JOIN user_sub_counties usc ON usc.sub_county_id=sc.id and usc.user_id='"+user_id+"'\n" +
                    "INNER JOIN counties c ON sc.county_id=c.id";
     conn.rs = conn.st.executeQuery(query);
     while(conn.rs.next()){
         JSONObject job_c = new JSONObject();
         JSONObject job_sc = new JSONObject();
         JSONObject job_f = new JSONObject();
         
         // counties
         job_c.put("id", conn.rs.getInt(1));
         job_c.put("name", conn.rs.getString(2));
         
        if(!jarray_c.contains(job_c)){jarray_c.add(job_c);}
        
        // sub  counties
         job_sc.put("id", conn.rs.getInt(3));
         job_sc.put("name", conn.rs.getString(4));
         
        if(!jarray_sc.contains(job_sc)){jarray_sc.add(job_sc);}
        
        // facilities
         job_f.put("id", conn.rs.getInt(5));
         job_f.put("name", conn.rs.getString(6));
         
        if(!jarray_f.contains(job_f)){jarray_f.add(job_f);}
         
     }
     
     JSONObject obj = new JSONObject();
     obj.put("counties", jarray_c);
     obj.put("sub_counties", jarray_sc);
     obj.put("facilities", jarray_f);
     
  
     return obj;
 }
 public JSONObject county_user(String user_id) throws SQLException{
     JSONArray jarray_c = new JSONArray();
     JSONArray jarray_sc = new JSONArray();
     JSONArray jarray_f = new JSONArray();
     
     String  query = "SELECT c.id as county_id, c.name as county_name, sc.id as sub_county_id, \n" +
                    "sc.name as sub_county_name, f.id as facility_id,f.name as facility_name \n" +
                    "FROM facilities f \n" +
                    "INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                    "INNER JOIN counties c ON sc.county_id=c.id \n" +
                    "INNER JOIN user_counties uc ON uc.county_id=c.id and uc.user_id='"+user_id+"'";
     conn.rs = conn.st.executeQuery(query);
     while(conn.rs.next()){
         JSONObject job_c = new JSONObject();
         JSONObject job_sc = new JSONObject();
         JSONObject job_f = new JSONObject();
         
         // counties
         job_c.put("id", conn.rs.getInt(1));
         job_c.put("name", conn.rs.getString(2));
         
        if(!jarray_c.contains(job_c)){jarray_c.add(job_c);}
        
        // sub  counties
         job_sc.put("id", conn.rs.getInt(3));
         job_sc.put("name", conn.rs.getString(4));
         
        if(!jarray_sc.contains(job_sc)){jarray_sc.add(job_sc);}
        
        // facilities
         job_f.put("id", conn.rs.getInt(5));
         job_f.put("name", conn.rs.getString(6));
         
        if(!jarray_f.contains(job_f)){jarray_f.add(job_f);}
         
     }
     
     JSONObject obj = new JSONObject();
     obj.put("counties", jarray_c);
     obj.put("sub_counties", jarray_sc);
     obj.put("facilities", jarray_f);
     
  
     return obj;
 }
 public JSONObject program_user(String user_id) throws SQLException{
     JSONArray jarray_c = new JSONArray();
     JSONArray jarray_sc = new JSONArray();
     JSONArray jarray_f = new JSONArray();
     
     String  query = "SELECT c.id as county_id, c.name as county_name, sc.id as sub_county_id, \n" +
                        "sc.name as sub_county_name, f.id as facility_id,f.name as facility_name \n" +
                        "FROM facilities f \n" +
                        "INNER JOIN sub_counties sc ON f.sub_county_id=sc.id \n" +
                        "INNER JOIN counties c ON sc.county_id=c.id ";
     conn.rs = conn.st.executeQuery(query);
     while(conn.rs.next()){
         JSONObject job_c = new JSONObject();
         JSONObject job_sc = new JSONObject();
         JSONObject job_f = new JSONObject();
         
         // counties
         job_c.put("id", conn.rs.getInt(1));
         job_c.put("name", conn.rs.getString(2));
         
        if(!jarray_c.contains(job_c)){jarray_c.add(job_c);}
        
        // sub  counties
         job_sc.put("id", conn.rs.getInt(3));
         job_sc.put("name", conn.rs.getString(4));
         
        if(!jarray_sc.contains(job_sc)){jarray_sc.add(job_sc);}
        
        // facilities
         job_f.put("id", conn.rs.getInt(5));
         job_f.put("name", conn.rs.getString(6));
         
        if(!jarray_f.contains(job_f)){jarray_f.add(job_f);}
         
     }
     
     JSONObject obj = new JSONObject();
     obj.put("counties", jarray_c);
     obj.put("sub_counties", jarray_sc);
     obj.put("facilities", jarray_f);
     
  
     return obj;
 }
 
 
 public JSONObject get_locations(String user_id,String user_level) throws SQLException{
     JSONObject obj;
    if(user_level.equals("1")){ // facility_user
       obj = facility_user(user_id); 
    }
    else if(user_level.equals("2")){ // sub county users
      obj = sub_county_user(user_id);   
    }
    else if(user_level.equals("3")){ // county users
      obj = county_user(user_id);   
    }
    else if(user_level.equals("4")){ // program users
     obj = program_user(user_id);    
    }
    else{
     obj=null;   
    }
    
 return obj;   
 }
    
}
