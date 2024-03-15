/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLoaders;

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
 * @author Geofrey Nyabuto
 */
public class load_questions extends HttpServlet {
    HttpSession session;
   String indicator_id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       session = request.getSession();
       JSONArray jarray = new JSONArray();
       JSONObject obj_final = new JSONObject();
       
       
       
       indicator_id = request.getParameter("indicator_id");
        dbConn conn = new dbConn();
        
             if(session.getAttribute("user_id")!=null){
        String get_name = "SELECT name,indicator_type,frequency,description,newly_added FROM indicators WHERE id=?";
        conn.pst = conn.conn.prepareStatement(get_name);
        conn.pst.setString(1, indicator_id);
        conn.rs = conn.pst.executeQuery();
        if(conn.rs.next()){
            obj_final.put("indicator_name", conn.rs.getString(1));
            obj_final.put("indicator_type", conn.rs.getString(2));
            obj_final.put("frequency", conn.rs.getString(3));
            obj_final.put("description", conn.rs.getString(4));
            obj_final.put("newly_added", conn.rs.getString(5));
        }
        
        
        JSONArray array_answers = get_answers(conn);
        
        
     String get_questions = "SELECT id,question,indicator_id,input_type_id,answer_data_type_id,required,editable  FROM questions WHERE indicator_id=? and status=? ORDER BY ordering_num";
     conn.pst = conn.conn.prepareStatement(get_questions);
     conn.pst.setString(1, indicator_id);
     conn.pst.setInt(2, 1);
     
     conn.rs = conn.pst.executeQuery();
     while(conn.rs.next()){
         JSONObject ob = new JSONObject();
         
         ob.put("id", conn.rs.getInt(1));
         ob.put("label", conn.rs.getString(2));
         ob.put("indicator_id", conn.rs.getInt(3));
         ob.put("input_type", conn.rs.getInt(4));
         ob.put("answer_data_type", conn.rs.getInt(5));
         ob.put("required", conn.rs.getInt(6));
         ob.put("editable", conn.rs.getInt(7));
         
          // get possible answers
         if(conn.rs.getString("input_type_id").equals("3") || conn.rs.getString("input_type_id").equals("4")){ // for coded answers 
             JSONArray q_answers = new JSONArray();
             // compare and add questions
             for (Object array_answer : array_answers) {
                 JSONObject obj = (JSONObject) array_answer;
                 if( (Integer)obj.get("question_id") ==conn.rs.getInt(1)){
                     JSONObject q_ob = new JSONObject();
                     q_ob.put("id", (Integer) obj.get("id"));
                     q_ob.put("name", obj.get("name").toString());
                     q_answers.add(q_ob);
                 }
             }
             ob.put("answers",q_answers);
         }
         
        
      jarray.add(ob);
     }
    }
             
     obj_final.put("questions", jarray);
     
            out.print(obj_final); 
        if( conn.conn!=null){conn.conn.close();}
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
            Logger.getLogger(load_questions.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_questions.class.getName()).log(Level.SEVERE, null, ex);
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

    
    
    // load possible answers for drop downs
    
    public JSONArray get_answers(dbConn conn) throws SQLException{
        JSONArray jarray = new JSONArray();
        
        String get_answers = "SELECT vl.id,vl.name,qvl.question_id FROM question_value_labels qvl INNER JOIN value_labels vl ON qvl.value_label_id=vl.id and qvl.status=?";
//        String get_answers = "SELECT vl.id,vl.name FROM question_value_labels qvl INNER JOIN value_labels vl ON qvl.value_label_id=vl.id and qvl.question_id=? and qvl.status=?";
        conn.pst = conn.conn.prepareStatement(get_answers);
//        conn.pst1.setInt(1, question_id);
        conn.pst.setInt(1, 1);
        
        conn.rs = conn.pst.executeQuery();
        while(conn.rs.next()){
            JSONObject ob = new JSONObject();
            ob.put("id", conn.rs.getInt(1));
            ob.put("name", conn.rs.getString(2));
            ob.put("question_id", conn.rs.getInt(3));
            
            jarray.add(ob);
        }
        return jarray;
    }
    
}
