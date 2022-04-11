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
public class summaries extends HttpServlet {
    HttpSession session;
    String start_date,end_date,user_id,indicator_ids,indicator_name,query,facility_ids,user_level;
    int row,indicator_counter,total_cols;
    int data_point;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        dbConn conn = new dbConn();
      session = request.getSession();
       Manage mg = new Manage();
       
      start_date = request.getParameter("start_date");
      end_date = request.getParameter("end_date");
      
      String[] indicators = request.getParameterValues("indicator"); 
        
        user_id=user_level="0";
      indicator_counter =total_cols=data_point= 0;
      
     if(session.getAttribute("user_id")!=null){
           user_id=session.getAttribute("user_id").toString(); 
       }
     
     if(session.getAttribute("user_level_id")!=null){
           user_level=session.getAttribute("user_level_id").toString(); 
       }
      
        System.out.println("user id : "+user_id+" user_level :"+user_level+" start date :"+start_date+" end date : "+end_date);
      
      
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
    styleHeader.setFillForegroundColor(HSSFColor.HSSFColorPredefined.CORNFLOWER_BLUE.getIndex());
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
    styleHeader.setWrapText(true);
    
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
    stylex.setWrapText(true);
    
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
    stborder.setWrapText(true);
    
    
    //          create sheet
          XSSFSheet sheet= wb.createSheet(start_date+" to "+end_date+" Summaries");
              row=0;
 XSSFRow RowIndicator = sheet.createRow(row);
 RowIndicator.setHeightInPoints(25);
          
            row++;
              XSSFRow RowHeader = sheet.createRow(row);
//              RowHeader.setHeightInPoints(20);
    
          String get_queries = "SELECT i.name,s.query,s.id FROM summaries_reports s INNER JOIN indicators i ON s.indicator_id=i.id and s.status=1 and s.indicator_id in("+indicator_ids+")";
        System.out.println("all query is :"+get_queries);
      conn.rs = conn.st.executeQuery(get_queries);
      while(conn.rs.next()){
//          column_data_counter=0;
          indicator_name = conn.rs.getString(1);
          query = conn.rs.getString(2).replace("start_date", "DATE('"+start_date+"')").replace("end_date", "DATE('"+end_date+"')").replace("facility_ids", facility_ids);
         
          
//          System.out.println("query : "+query);
         // run query to get_data
      conn.rs1 = conn.st1.executeQuery(query);
      
         ResultSetMetaData metaData = conn.rs1.getMetaData();
            int col_count = metaData.getColumnCount(); //number of column
            row=1;
            String  value;
            int cells = col_count;
          if(indicator_counter>0){ cells = col_count-4;}
          
          for(int i=0;i<cells;i++){
             if(indicator_counter>0){ data_point = i+5;}
             else{data_point=i+1;}
              value = metaData.getColumnLabel(data_point);    
          
           
          XSSFCell cellIndic = RowIndicator.createCell(total_cols+i);
          
          XSSFCell cell = RowHeader.createCell(total_cols+i);
         
          cell.setCellValue(value);
           cell.setCellStyle(stylex);   
//          sheet.autoSizeColumn(i);
          
          cellIndic.setCellStyle(styleHeader);
          
          sheet.setColumnWidth(total_cols+i,4000);
          }
         
          // add the indicator name
          int headerpos;
          if(indicator_counter>0){headerpos = total_cols;}
          else{
              
              headerpos = 4;
              XSSFCell cellIndic = RowIndicator.getCell(0);
          cellIndic.setCellValue("Indicator Names : ");
          
          sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
          }
          
           XSSFCell cellIndic = RowIndicator.getCell(headerpos);
          cellIndic.setCellValue(indicator_name);
          
          int end_merge = (headerpos+col_count-5);
          System.out.println("col count "+col_count+" header pos : "+headerpos+" end of merging : "+end_merge);
          if((end_merge-headerpos)>0){
          sheet.addMergedRegion(new CellRangeAddress(0,0,headerpos,end_merge));
          }
         
          row++;
      while(conn.rs1.next()){ // loop through and display data
                      
             XSSFRow RowData;
        
        if(indicator_counter>0){RowData  = sheet.getRow(row);}     
        else{     RowData  = sheet.createRow(row);}  // for first dataset
       
        
        for(int i=0;i<cells; i++){ // read and output data
            if(indicator_counter>0){ data_point = i+5;}
            else{data_point=i+1;}
           
            value = conn.rs1.getString(data_point);    
          
          XSSFCell cell = RowData.createCell(total_cols+i);
          if(mg.isNumeric(value)){cell.setCellValue(Integer.parseInt(value));}
          else{cell.setCellValue(value);}
            cell.setCellStyle(stborder);  
//            sheet.autoSizeColumn(i);
        }
        
         row++; 
      }
      total_cols+= cells;

          // 
  indicator_counter++;
      }

sheet.setAutoFilter(new CellRangeAddress(1, 1, 0, total_cols));
sheet.createFreezePane(4, 2);
        
       String filename="Summaries_report_generated_on_"+mg.get_timestamp_string()+".xlsx";
      
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
            Logger.getLogger(summaries.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(summaries.class.getName()).log(Level.SEVERE, null, ex);
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
