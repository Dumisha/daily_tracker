/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DDTA;

import Database.dbConn;
import com.mysql.jdbc.CallableStatement;
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
import java.sql.Timestamp;
/**
 *
 * @author Geofrey Nyabuto
 */
public class save_data extends HttpServlet {
    HttpSession session;
   String user_id,facility_id,message; 
   boolean has_aggregate,record_exist;
   int code;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConn conn = new dbConn();
        session = request.getSession();
        JSONObject fn_ob = new JSONObject();
        
        
        String indicator_id,data_value,date,entry_status;
        has_aggregate = record_exist = false;
        message = "";
        code=0;
                   int num = 0;
         String status = "";          
        
      String entry_key = get_entry_key();
        
       indicator_id = request.getParameter("indicator");
       facility_id = request.getParameter("facility_id");
       date = request.getParameter("date");
       entry_status = request.getParameter("entry_status");

        System.out.println(" date is : "+date+" entry status "+entry_status);
       
       if(session.getAttribute("user_id")!=null){
           user_id=session.getAttribute("user_id").toString(); 
       }
       
       session.setAttribute("facility_id", facility_id);
       
       // Get Indicator Properties
     JSONObject indicator_obj =  get_indicator(conn,indicator_id);
       if((Integer)indicator_obj.get("indicator_type")==2){ // aggregate Indicators
         // check data existence
           System.out.println("has aggregate");
           
           has_aggregate = check_existence(conn,indicator_id,date,facility_id);
           
       if(has_aggregate){
           code=0;
           message="Data for this element has already been entered";
       }
       
       // for pmtct
       if(indicator_id.equals("10")){
       has_aggregate = is_pmtct_unique(conn,date,facility_id,"55",request.getParameter("name_55"));
       
       if(has_aggregate){
           code=0;
           message="PMTCT data for this service delivery point has already been entered"; 
       }
       
       }
       
       
       
       
       }
       
       else{
         // check for record existence
           System.out.println("patient level data ");
           record_exist = check_uniqueness(conn,indicator_id,request);
           
       if(record_exist){
           code=0;
           message="This record has already been saved. Please review entries";
       }  
       }
       
        System.out.println(" has aggregate : "+has_aggregate+" is patient level :"+record_exist);
       
       if(entry_status.equals("")){
           status="1";
       if(!has_aggregate && !record_exist){
        JSONArray question_array = get_questions(conn,indicator_id);
        fn_ob.put("questions", question_array);
        
        String query_values="";
        for(Object object:question_array){
         JSONObject obj = (JSONObject) object; 
         
         query_values+="(";
         int question_id = (Integer) obj.get("id");
         
         int answer_data_type = (Integer) obj.get("answer_data_type");
         data_value = null;
         
            System.out.println("answer data type : "+answer_data_type);
         if(request.getParameter("name_"+question_id)!=null){
         data_value =  request.getParameter("name_"+question_id);
         if(answer_data_type==2 && data_value.equals("")){data_value=null;}
         }
         
            System.out.println("question id : "+obj.get("name").toString()+" value : "+data_value);
         
         query_values+=indicator_id+",";
         query_values+=question_id+",";
         if(data_value==null){
          query_values+="null,null,";  
         }
         else if(answer_data_type==2){ 
         query_values+=data_value+",null,";
         }
         else{
         query_values+="null,'"+data_value+"',";
         }
         
         query_values+="'"+date+"',";
         query_values+=""+facility_id+",";
         query_values+=user_id+",";
         query_values+="'"+entry_key+"'";
         
//         ob.put("question", question);
         query_values+="),";
  num++;
        }
        
        query_values = removeLast(query_values, 1); // remove trailling ,
        
       String query="INSERT INTO observations (indicator_id,question_id,numeric_value,text_value,date,facility_id,user_id,entry_key) VALUES "+query_values;
       
        System.out.println(" inserter : "+query);
        conn.st.executeUpdate(query);
        
        System.out.println(" array inserter :"+query_values);
        
        
        // code output
       code=1;
       message="Indicator Data with "+num+" variables saved successfully";
        
       if(num>0){
           update_queue(conn,entry_key,"1",indicator_id);
       }
       }
    }
       
       else if(entry_status.equals("1")){ // save edits 
           String timestamp = get_timestamp();
          String numeric_value,text_value; 
          status = "2";
          entry_key = request.getParameter("entry_key");
          
        JSONArray question_array = get_questions(conn,indicator_id);
        fn_ob.put("questions", question_array);
        
        for(Object object:question_array){
         JSONObject obj = (JSONObject) object; 
         
         int question_id = (Integer) obj.get("id");
         int answer_data_type = (Integer) obj.get("answer_data_type");
         
         if(request.getParameter("name_"+question_id)!=null){
         data_value =  request.getParameter("name_"+question_id);
         if(answer_data_type==2){ // numeric
            text_value=null;
            if(data_value.equals("")){numeric_value=null;}
            else{numeric_value = data_value;}
         }
         else{
             numeric_value=null;
             if(data_value.equals("")){text_value=null;}
            else{text_value = data_value;}
         }
      String obs_id = request.getParameter("o_id_"+question_id);
     System.out.println("obs id is "+obs_id+" question id : "+obj.get("name").toString()+" numeric value : "+numeric_value+" text value : "+text_value); 
        
     
     String updator = "UPDATE observations SET numeric_value=?,text_value=?,updated_at=?,updated_by=? WHERE id=?";
     conn.pst = conn.conn.prepareStatement(updator);
     conn.pst.setString(1, numeric_value);
     conn.pst.setString(2, text_value);
     conn.pst.setString(3, timestamp);
     conn.pst.setString(4, user_id);
     conn.pst.setString(5, obs_id);
     
             System.out.println(conn.pst);
   num+= conn.pst.executeUpdate(); 
     
     
     // update logs
     
     
     // end of logs
         }
  
        }
        // code output
       code=1;
       message=num+" variables updated successfully at "+timestamp;   
       
       if(num>0){
           update_queue(conn,entry_key,"2",indicator_id);
       }
       }

       fn_ob.put("code", code);
       fn_ob.put("message", message);

       
       // process queue
       process_queue(conn,status);
       
       //
        System.out.println("final obj :"+fn_ob);
       out.println(fn_ob);
        
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
            Logger.getLogger(save_data.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_data.class.getName()).log(Level.SEVERE, null, ex);
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

    
    private JSONArray get_questions(dbConn conn, String indicator_id) throws SQLException{
       JSONArray jarray = new JSONArray();
       
       String get_questions = "SELECT id,question,input_type_id,answer_data_type_id FROM questions WHERE indicator_id=? and status=?";
       conn.pst = conn.conn.prepareStatement(get_questions);
       conn.pst.setString(1, indicator_id);
       conn.pst.setInt(2, 1);
       
       conn.rs = conn.pst.executeQuery();
       while(conn.rs.next()){
           JSONObject obj = new JSONObject();
           obj.put("id", conn.rs.getInt(1));
           obj.put("name", conn.rs.getString(2));
           obj.put("input_type", conn.rs.getInt(3));
           obj.put("answer_data_type", conn.rs.getInt(4));
        
           jarray.add(obj);
       }

        
    return jarray;    
    }
    
    private JSONObject get_indicator(dbConn conn, String indicator_id) throws SQLException{
        JSONObject obj = new JSONObject();
        String get_info = "SELECT id,name,indicator_type,frequency FROM indicators WHERE id=?";
        conn.pst = conn.conn.prepareStatement(get_info);
        conn.pst.setString(1, indicator_id);
        conn.rs = conn.pst.executeQuery();
        if(conn.rs.next()){
         obj.put("id", conn.rs.getInt(1));
         obj.put("name", conn.rs.getString(2));
         obj.put("indicator_type", conn.rs.getInt(3));
         obj.put("frequency", conn.rs.getInt(4));
        }
        
        return obj;
    }
    
    private boolean check_existence(dbConn conn,String indicator_id,String date,String facility_id) throws SQLException{ // check if data has been captured for this day for aggregate indicators
      String checker = "SELECT q.id FROM questions q INNER JOIN observations o ON q.id=o.question_id and q.indicator_id=? and o.date=? and facility_id=?";
      conn.pst = conn.conn.prepareStatement(checker);
      conn.pst.setString(1, indicator_id);
      conn.pst.setString(2, date);
      conn.pst.setString(3, facility_id);
      conn.rs = conn.pst.executeQuery();
     
      return conn.rs.next();
    }
    
    private boolean check_uniqueness(dbConn conn,String indicator_id,HttpServletRequest request) throws SQLException{
     String values_to_be_checked = "", query="";
        String get_unique_questions = "SELECT id,question,answer_data_type_id FROM questions WHERE indicator_id=? AND value_unique=?";
        conn.pst = conn.conn.prepareStatement(get_unique_questions);
        conn.pst.setString(1, indicator_id);
        conn.pst.setInt(2, 1);
        System.out.println(conn.pst);
        conn.rs = conn.pst.executeQuery();
        
        while(conn.rs.next()){
            String value = request.getParameter("name_"+conn.rs.getInt(1));
         System.out.println("Unique ids here are ? "+conn.rs.getString(2)+" with id "+conn.rs.getString(1)+" and value :"+value); 
         
         if(conn.rs.getInt(3)==2){
             if(!value.equals("")){
         values_to_be_checked += " (numeric_value="+value+") ";}
                 }
         else{
             if(!value.equals("")){
          values_to_be_checked += " (text_value='"+value+"') ";  }  
         }
         
          values_to_be_checked += " || ";
        }
       
         values_to_be_checked = values_to_be_checked.trim();
        System.out.println("to be checked ---:"+ values_to_be_checked+":--");
        if( values_to_be_checked.length()>0){
            values_to_be_checked = removeLast(values_to_be_checked, 2); 
            
            query = "SELECT id FROM observations where "+values_to_be_checked;
            
        }
        
        System.out.println("-----:"+query+":-----");
        
        System.out.println("query :"+query+":");
        if(values_to_be_checked.length()>0){
        conn.rs = conn.st.executeQuery(query);
        return conn.rs.next();}
        else{
            return false;
        }
    }
    
    private boolean is_pmtct_unique(dbConn conn,String date,String facility_id,String question_id,String value) throws SQLException{
        
        String checker = "SELECT id FROM observations WHERE facility_id=? AND date=? AND question_id=? AND numeric_value=?";
        conn.pst = conn.conn.prepareStatement(checker);
        conn.pst.setString(1, facility_id);
        conn.pst.setString(2, date);
        conn.pst.setString(3, question_id);
        conn.pst.setString(4, value);
        
        System.out.println("pmtct : "+conn.pst);
        conn.rs = conn.pst.executeQuery();
        
        return conn.rs.next();
    }
    
       public String removeLast(String str, int num) { 
        
    if (str != null && str.length() >= num) {
        str = str.substring(0, str.length() - num);
    }
    return str;
    }
       
       
       private String get_entry_key(){
           Timestamp timestamp = new Timestamp(System.currentTimeMillis());
           String key = timestamp.toString().replaceAll("[- :.]", "") ;
           
           System.out.println(key);
           return key;
       }
       
       private String get_timestamp(){
           return new Timestamp(System.currentTimeMillis()).toString();
       }
       
       
       public void update_queue(dbConn conn,String entry_key,String status,String indicator_id) throws SQLException{ // 1 for new entry, 2 for update
        String adder = "INSERT INTO queue (entry_key,status,indicator_id) VALUES(?,?,?)";
        conn.pst = conn.conn.prepareStatement(adder);
        conn.pst.setString(1, entry_key);
        conn.pst.setString(2, status);
        conn.pst.setString(3, indicator_id);
        
        conn.pst.executeUpdate();
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
       
}
