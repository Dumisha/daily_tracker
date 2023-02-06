/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import DDTA.Manage;
import Database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Geofrey Nyabuto
 */
public class raw_data extends HttpServlet {
    HttpSession session;
    String start_date,end_date,user_id,indicator_ids,indicator_name,query,facility_ids,user_level;
    int row;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        session = request.getSession();
        dbConn conn = new dbConn();
        Manage mg = new Manage();
        
      start_date = request.getParameter("start_date");
      end_date = request.getParameter("end_date");
      
       if(session.getAttribute("user_id")!=null){
         
           String reports="";
           if(session.getAttribute("reports")!=null ){
               reports=session.getAttribute("reports").toString();
           }
            
           if(reports.equals("1")){
             
           
      String[] indicators = request.getParameterValues("indicator");
     
      user_id=user_level="0";
      
      
     if(session.getAttribute("user_id")!=null){
           user_id=session.getAttribute("user_id").toString(); 
       }
     
     if(session.getAttribute("user_level_id")!=null){
           user_level=session.getAttribute("user_level_id").toString(); 
       }
    
       ArrayList<String>  facilities = mg.get_locations(user_id,user_level,conn);

      
      indicator_ids="";
      for(String indicator : indicators){
         indicator_ids+=indicator+","; 
      }
     // remove last comma 
      if(indicators.length>0){
      indicator_ids = mg.removeLast(indicator_ids,1);
      }
      
      else{
        indicator_ids="0";  
      }
      
      facility_ids="";
      for(String facility : facilities){
         facility_ids+=facility+","; 
      }
     // remove last comma 
      if(facilities.size()>0){
      facility_ids = mg.removeLast(facility_ids,1);
      }
      
      else{
        facility_ids="0";  
      }
      
      
      
     
      XSSFWorkbook wb = new XSSFWorkbook();
      
      XSSFFont font=wb.createFont();
    font.setFontHeightInPoints((short)18);
    font.setFontName("Cambria");
    font.setColor((short)0000);
    
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HorizontalAlignment.CENTER);
    
    XSSFCellStyle styleHeader = wb.createCellStyle();
    styleHeader.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
    styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styleHeader.setBorderTop(BorderStyle.THIN);
    styleHeader.setBorderBottom(BorderStyle.THIN);
    styleHeader.setBorderLeft(BorderStyle.THIN);
    styleHeader.setBorderRight(BorderStyle.THIN);
    styleHeader.setAlignment(HorizontalAlignment.CENTER);
    
    XSSFFont fontHeader = wb.createFont();
    fontHeader.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    fontHeader.setBold(true);
    fontHeader.setFamily(FontFamily.MODERN);
    fontHeader.setFontName("Cambria");
    fontHeader.setFontHeight(15);
    styleHeader.setFont(fontHeader);
//    styleHeader.setWrapText(true);
    
    XSSFCellStyle stylex = wb.createCellStyle();
    stylex.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_CORNFLOWER_BLUE.getIndex());
    stylex.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    stylex.setBorderTop(BorderStyle.THIN);
    stylex.setBorderBottom(BorderStyle.THIN);
    stylex.setBorderLeft(BorderStyle.THIN);
    stylex.setBorderRight(BorderStyle.THIN);
    stylex.setAlignment(HorizontalAlignment.LEFT);
    
    XSSFFont fontx = wb.createFont();
    fontx.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    fontx.setBold(true);
    fontx.setFamily(FontFamily.MODERN);
    fontx.setFontName("Cambria");
    stylex.setFont(fontx);
//    stylex.setWrapText(true);
    
    XSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(BorderStyle.THIN);
    stborder.setBorderBottom(BorderStyle.THIN);
    stborder.setBorderLeft(BorderStyle.THIN);
    stborder.setBorderRight(BorderStyle.THIN);
    stborder.setAlignment(HorizontalAlignment.LEFT);
    
    XSSFFont font_cell=wb.createFont();
    font_cell.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    font_cell.setFamily(FontFamily.MODERN);
    font_cell.setFontName("Cambria");
    stborder.setFont(font_cell);
//    stborder.setWrapText(true);
    
      String get_queries = "SELECT i.name,r.query,r.report_name FROM raw_data_reports r INNER JOIN indicators i ON r.indicator_id=i.id and r.status=1 and r.indicator_id in("+indicator_ids+")";
      conn.rs = conn.st.executeQuery(get_queries);
      while(conn.rs.next()){
          indicator_name = conn.rs.getString(3);
          query = conn.rs.getString(2).replace("start_date", "DATE('"+start_date+"')").replace("end_date", "DATE('"+end_date+"')").replace("facility_ids", facility_ids);

          System.out.println("query is : "+query);
         
//          create sheet
          XSSFSheet sheet= wb.createSheet(indicator_name);
         // run query to get_data
      conn.rs1 = conn.st1.executeQuery(query);
          System.out.println("Query : "+query);
      
         ResultSetMetaData metaData = conn.rs1.getMetaData();
            int col_count = metaData.getColumnCount(); //number of column
            String  value;
          row=0;
          XSSFRow RowHeader = sheet.createRow(row);
          
          for(int i=0;i<col_count;i++){
           value = metaData.getColumnLabel(i+1);
          XSSFCell cell = RowHeader.createCell(i);
          cell.setCellValue(value);
           cell.setCellStyle(stylex);   
          sheet.autoSizeColumn(i);
          }        
          
          row++;
      while(conn.rs1.next()){ // loop through and display data
                      
             XSSFRow RowData = sheet.createRow(row);
        for(int i=0;i<col_count; i++){ // read and output data
System.out.println("Data is :"+conn.rs1.getString(i+1));
          value = conn.rs1.getString(i+1);
          XSSFCell cell = RowData.createCell(i);
          if(mg.isNumeric(value)){cell.setCellValue(Integer.parseInt(value));}
          else{cell.setCellValue(value);}
            cell.setCellStyle(stborder);  
            sheet.autoSizeColumn(i);
          }
        
         row++; 
      }
      sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, col_count));
      }
      
      if( conn.conn!=null){conn.conn.close();}
      
       String filename="Daily_data_tracking_report_generated_on_"+mg.get_timestamp_string()+".xlsx";
      
     ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
    wb.write(outByteStream);
    byte [] outArray = outByteStream.toByteArray();
    response.setContentType("application/ms-excel");
    response.setContentLength(outArray.length);
    response.setHeader("Expires:", "0"); // eliminates browser caching
    response.setHeader("Set-Cookie:", "fileDownload=true; path=/"); // set cookie header
    response.setHeader("Content-Disposition", "attachment; filename="+filename);
    OutputStream outStream = response.getOutputStream();
    outStream.write(outArray);
    outStream.flush();
       }
        else{
          session.setAttribute("message", "<b color=\"red\">Error: User not allowed to use this module.</b>");
          response.sendRedirect("summaries.jsp");       
           }    
       }
       else{
           session.setAttribute("message", "Unknow user. Login to try again");
           response.sendRedirect("raw_data.jsp");
       }
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
            Logger.getLogger(raw_data.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(raw_data.class.getName()).log(Level.SEVERE, null, ex);
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
