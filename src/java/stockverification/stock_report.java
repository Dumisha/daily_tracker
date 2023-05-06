/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package stockverification;

import DDTA.Manage;
import Database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
 * @author mwamb
 */
public class stock_report extends HttpServlet {
    HttpSession session;
    String start_date,end_date,user_id;
    int row;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        session = request.getSession();
        dbConn conn = new dbConn();
        
         Manage mg = new Manage();
        
//      start_date = "2021-01-01";
//      end_date = "2023-07-08";
      start_date = request.getParameter("start_date");
      end_date = request.getParameter("end_date");
      
       if(session.getAttribute("user_id")!=null){
            user_id=session.getAttribute("user_id").toString(); 
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
     stborder.setWrapText(true);        
            
         
     
        //          create sheet
          XSSFSheet sheet= wb.createSheet(start_date+" to "+end_date+" Commodities");
              row=0;
              
    if(1==1)  {        
    XSSFRow Header = sheet.createRow(row);   
    XSSFCell cell = Header.createCell(1);  
          cell.setCellValue("Commodity Tracking Report");
           cell.setCellStyle(styleHeader);
    }
    if(1==1)  {  
        row++;
    XSSFRow Header = sheet.createRow(row);   
    XSSFCell cell = Header.createCell(1); 
    XSSFCell cell2 = Header.createCell(2); 
          cell.setCellValue("Start Date : ");
          cell2.setCellValue(start_date);
           cell.setCellStyle(styleHeader);
           cell2.setCellStyle(stborder);
    }
   if(1==1){
       row++;
    XSSFRow Header = sheet.createRow(row);   
    XSSFCell cell = Header.createCell(1); 
    XSSFCell cell2 = Header.createCell(2); 
          cell.setCellValue("End Date : ");
          cell2.setCellValue(end_date);
           cell.setCellStyle(styleHeader);
           cell2.setCellStyle(stborder);
   }        
              
              
              row++;
 XSSFRow Header = sheet.createRow(row);
 Header.setHeightInPoints(5);
              row++;
 XSSFRow HeaderRow = sheet.createRow(row);
 HeaderRow.setHeightInPoints(25);
           
        String query = " SELECT\n" +
            " concat(c.name,\" County\") as County,\n" +
            " concat(scn.name,\" Sub County\") as \"Sub County Name\",\n" +
            " f.name as \"Facility Name\", \n" +
            " f.mfl_code as \"MFL Code\", \n" +
            " verification_date as \"Verification Date\",\n" +
            " delivery_note_number as \"Delivery Note Number\",\n" +
            " document_date as \"Document Date\",\n" +
            "concat(sc.commodity,\" ,\",sc.packs) as \"Commodity Description\", \n" +
            "sc.packs as \"Pack Size\",\n" +
            "batch_number as \"Batch Number\",\n" +
            "delivery_note_quantity as \"Delivery Note Quantity\", \n" +
            "quantity_received as \"Quantity Received\",\n" +
            "expiry_date as \"Expiry Date\",\n"+
            "CONCAT_WS(\":\",sd.time,sd.minute) as \"Time Delivered\", " +
            "date_received as \"Date Received\",\n" +
            "received_by as \"Received By\",\n" +
            "contacts as Contacts,\n" +
            "p.name as Pipeline,\n" +
            "comments as Comments,\n" +
            "concat_ws(\" \",first_name,middle_name,sur_name)  as \"Entered By\",\n" +
            "sd.timestamp as \"Date Created\" \n" +
            "FROM  stock_data sd \n" +
            "INNER JOIN stock_commodities sc ON sd.commodity_id=sc.id AND date_received BETWEEN DATE(?) AND DATE(?) \n" +
            "INNER JOIN stock_pipeline p ON sd.pipeline_id=p.id \n" +
            "INNER JOIN facilities f ON sd.facility_id=f.id \n" +
            "INNER JOIN sub_counties scn ON f.sub_county_id=scn.id \n" +
            "INNER JOIN counties c ON scn.county_id=c.id \n" +
            "LEFT OUTER JOIN users u ON sd.user_id=u.id ORDER by sd.timestamp DESC";
      conn.pst = conn.conn.prepareStatement(query);
      conn.pst.setString(1, start_date);
      conn.pst.setString(2, end_date);
      
      conn.rs = conn.pst.executeQuery();
        System.out.println("query: "+conn.rs);
      
      
      
       ResultSetMetaData metaData = conn.rs.getMetaData();
       int col_count = metaData.getColumnCount(); //number of column
       String  value;
       for(int i=0;i<col_count;i++){
         value = metaData.getColumnLabel(i+1);    
          XSSFCell cell = HeaderRow.createCell(i); 
          cell.setCellValue(value);
           cell.setCellStyle(stylex);  
            switch (i) {
                case 7:
                    sheet.setColumnWidth(i,12000);
                    break;
                case 2:
                    sheet.setColumnWidth(i,8000);
                    break;
                default:
                    sheet.setColumnWidth(i,6000);
                    break;
            }
       }
      while(conn.rs.next()){
          row++;
          XSSFRow Row = sheet.createRow(row); 
          
         for(int i=0;i<col_count;i++){
         value = conn.rs.getString(i+1);    
          XSSFCell cell = Row.createCell(i); 
          cell.setCellValue(value);
           cell.setCellStyle(stborder);   
       } 
          
      }
       
      sheet.addMergedRegion(new CellRangeAddress(0,0,1,2));
      sheet.setAutoFilter(new CellRangeAddress(4, 4, 0, (col_count-1)));
        sheet.createFreezePane(4, 5);

     if( conn.conn!=null){conn.conn.close();}
      
       String filename="Stock_verification_report_generated_on_"+mg.get_timestamp_string()+".xlsx";
      
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
            Logger.getLogger(stock_report.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(stock_report.class.getName()).log(Level.SEVERE, null, ex);
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
