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
public class ppmt extends HttpServlet {
    HttpSession session;
    String start_date,end_date;
    String prev_County,prev_Indicator;
    Manage mg = new Manage();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        
        session = request.getSession();
        dbConn conn = new dbConn();
        
//        ArrayList<String> counties = get_supported_counties(conn);
        ArrayList<String> ct;

        
        String indicator,county,activity,female,male,total,indicator_number,activity_date,excel_county;
        int row=0,cell_merge=0;
        

        start_date = request.getParameter("start_date");
        end_date = request.getParameter("end_date");
        
//        start_date = "2022-01-01";
//        end_date = "2022-03-30";
        
        
        
        XSSFWorkbook wb = new XSSFWorkbook();
      
      XSSFFont font=wb.createFont();
    font.setFontHeightInPoints((short)15);
    font.setFontName("Cambria");
    font.setColor((short)0000);
    
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HorizontalAlignment.CENTER);
    
    XSSFCellStyle styleMainHeader = wb.createCellStyle();
    styleMainHeader.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex());
    styleMainHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styleMainHeader.setBorderTop(BorderStyle.THIN);
    styleMainHeader.setBorderBottom(BorderStyle.THIN);
    styleMainHeader.setBorderLeft(BorderStyle.THIN);
    styleMainHeader.setBorderRight(BorderStyle.THIN);
    styleMainHeader.setAlignment(HorizontalAlignment.CENTER);
    
    XSSFFont fontMainHeader = wb.createFont();
    fontMainHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
    fontMainHeader.setBold(true);
    fontMainHeader.setFamily(FontFamily.MODERN);
    fontMainHeader.setFontName("Cambria");
    fontMainHeader.setFontHeight(14);
    styleMainHeader.setFont(fontMainHeader);
    styleMainHeader.setWrapText(true);
    
    
    
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
    fontHeader.setFontHeight(13);
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
    fontx.setFontHeight(12);
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
          XSSFSheet sheet= wb.createSheet("PPMT Tables");
        
                row++;
              XSSFRow RowHeader = sheet.createRow(row);     
        RowHeader.setHeightInPoints(30);
                XSSFCell cell_title = RowHeader.createCell(0);
                cell_title.setCellValue("PPMT Tables for USAID Dumisha Afya From: ["+start_date+" to "+end_date+"]");
                cell_title.setCellStyle(styleMainHeader);
                sheet.addMergedRegion(new CellRangeAddress(1,1,0,5));
        
       
                sheet.setColumnWidth(0,6000);
                sheet.setColumnWidth(1,20000);
                sheet.setColumnWidth(2,4000);
                sheet.setColumnWidth(3,4000);
                sheet.setColumnWidth(4,4000);
                sheet.setColumnWidth(5,4000);
        
        String get_data = "SELECT pi.name as Indicator_Name,pi.number AS Indicator_Number,ifnull(c.name,\"\") as County, coalesce(pa.name,p.activity_other) as Activity,\n" +
                            "sum(p.female) as female, \n" +
                            "sum(p.male) as male, \n" +
                            "sum(p.total) as total, activity_date\n" +
                            "\n" +
                            " FROM \n" +
                            " ppmt_indicators pi \n" +
                            " LEFT OUTER JOIN ppmt p ON pi.id=p.indicator_id  AND p.activity_date BETWEEN DATE(?) AND DATE(?)\n\n" +
                            " LEFT OUTER  JOIN ppmt_activities pa ON p.activity_id=pa.id \n" +
                            " LEFT OUTER JOIN facilities f  ON f.id=p.facility_id \n" +
                            " LEFT OUTER  JOIN sub_counties sc ON sc.id=f.sub_county_id      \n" +
                            " LEFT OUTER   JOIN counties c  ON c.id=sc.county_id \n" +
                            " group by pi.id,c.id,pa.id,activity_date \n" +
                            " ORDER BY Indicator_Name ASC ,County ASC, Activity ASC,activity_date DESC";
        
        conn.pst = conn.conn.prepareStatement(get_data);
        conn.pst.setString(1, start_date);
        conn.pst.setString(2, end_date);
        
        System.out.println("query is : "+conn.pst);
        conn.rs = conn.pst.executeQuery();
        
        prev_County=prev_Indicator="";
        row=1;
        
        ct = get_supported_counties(conn);
        System.out.println("------------------------------------------------------------------------------"+get_supported_counties(conn).size());
        
        while(conn.rs.next()){
            indicator = conn.rs.getString(1);
            indicator_number = conn.rs.getString(2);
            county = conn.rs.getString(3);
            activity = conn.rs.getString(4);
            female = conn.rs.getString(5);
            male = conn.rs.getString(6);
            total = conn.rs.getString(7);
            activity_date = conn.rs.getString(8);
            row++;             
           excel_county = county;
//            System.out.println(" prev county : "+prev_County+" current county : "+county);
//          if(prev_County.equals(county)){ excel_county=""; cell_merge++;}
            
          if(!prev_Indicator.equals(indicator))  { // new indicator, start
           
          if(prev_Indicator.equals("")){
              row++;
            sheet = indicator_titles(sheet,styleHeader,row,0,indicator);
            row++; 
            activity_title(sheet,stylex,row,false);
            row++;
            activity_data(sheet,stborder,row,excel_county,activity,female,male,total,activity_date);
            ct.remove(county);
            
          }
          else if(!prev_Indicator.equals("")){
          if(ct.isEmpty()){
             row++;
            sheet = indicator_titles(sheet,styleHeader,row,0,indicator);
            row++; 
            activity_title(sheet,stylex,row,false);
            row++; 
            activity_data(sheet,stborder,row,excel_county,activity,female,male,total,activity_date);      
          }
          else{
              for(String c:ct){
                 activity_data(sheet,stborder,row,c,"No Activity","","","0","");
                 row++;
                }
             
              
            row+=1;
            sheet = indicator_titles(sheet,styleHeader,row,0,indicator);
            row++; 
            activity_title(sheet,stylex,row,false);
            row++; 
            activity_data(sheet,stborder,row,excel_county,activity,female,male,total,activity_date);
           
          }
           
          }
         
          ct=get_supported_counties(conn);
          ct.remove(county);
          }
          
          
          
        else if(prev_Indicator.equals(indicator)){ // same indicator 
//          row++;
          activity_data(sheet,stborder,row,excel_county,activity,female,male,total,activity_date); 
          
          ct.remove(county); 
           }
          
          else{
              System.out.println("Nothing expected here");
          }   
         
          
//               if(!(prev_County.equals(county) || prev_Indicator.equals(indicator)) && cell_merge>0){
//                   System.out.println(" current row :"+row+" cells to merge : "+cell_merge);
//                   try{
//                sheet.addMergedRegion(new CellRangeAddress((row-cell_merge-1),(row-1),0,0));   
//                   }
//                   catch(Exception e){
//                       System.out.println("error "+e);
//                   }
//                   cell_merge=0;
//                }
                      
         prev_Indicator = indicator;
          prev_County=county;
          
//                System.out.println("Indicator : "+indicator+"["+indicator_number+"] County : "+county+" activity : "+activity+" total : "+total);  
                
                
                // merge counties
        }
     System.out.println("------------------------------------------------------------------------------");   
      
     
     if(!ct.isEmpty()){
         row++;
       for(String c:ct){
                 activity_data(sheet,stborder,row,c,"No Activity","","","0","");
                 row++;
      }    
     }
      
     
     
     if( conn.conn!=null){conn.conn.close();}
     
     
    String filename="PPMT_report_generated_on_"+mg.get_timestamp_string()+".xlsx";
      
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
            Logger.getLogger(ppmt.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ppmt.class.getName()).log(Level.SEVERE, null, ex);
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

    
    
 public XSSFSheet indicator_titles(XSSFSheet sheet,XSSFCellStyle style,int row_pos,int cell_pos,String title)  {
    XSSFRow RowData  = sheet.createRow(row_pos);
     XSSFCell cell = RowData.createCell(cell_pos);
     cell.setCellValue(title);
     cell.setCellStyle(style);
     
     for(int j=1;j<=5;j++){
     RowData.createCell(j).setCellStyle(style);
     }
     
     sheet.addMergedRegion(new CellRangeAddress(row_pos,row_pos,0,5));
     RowData.setHeightInPoints(60);
     return sheet;
 } 
 public XSSFSheet activity_data(XSSFSheet Sheet,XSSFCellStyle style,int row_pos, String County,String activity,String female, String male, String total,String activity_date){
    
     XSSFRow Row  = Sheet.createRow(row_pos);
     
     XSSFCell cellCounty = Row.createCell(0);
      XSSFCell cellActivity = Row.createCell(1);
      XSSFCell cellDate = Row.createCell(2);
      XSSFCell cellFemale = Row.createCell(3);
      XSSFCell cellMale = Row.createCell(4);
      XSSFCell cellTotal = Row.createCell(5);
         
      
      cellCounty.setCellValue(County);
      cellActivity.setCellValue(activity);
      cellDate.setCellValue(activity_date);
      
      if(mg.isNumeric(female)){cellFemale.setCellValue(Integer.parseInt(female));}
      else{cellFemale.setCellValue(female);}
      
      if(mg.isNumeric(male)){cellMale.setCellValue(Integer.parseInt(male));}
      else{cellMale.setCellValue(male);}
      
      if(mg.isNumeric(total)){cellTotal.setCellValue(Integer.parseInt(total));}
      else{cellTotal.setCellValue(total);}
      
      cellCounty.setCellStyle(style);
      cellActivity.setCellStyle(style);
      cellDate.setCellStyle(style);
      cellFemale.setCellStyle(style);
      cellMale.setCellStyle(style);
      cellTotal.setCellStyle(style);
              
    return Sheet; 
 }
 public XSSFSheet activity_title(XSSFSheet Sheet,XSSFCellStyle style,int row_pos,boolean has_gender){
    
     XSSFRow Row  = Sheet.createRow(row_pos);
     
     XSSFCell cellCounty = Row.createCell(0);
      XSSFCell cellActivity = Row.createCell(1);
      XSSFCell cellDate = Row.createCell(2);
      XSSFCell cellFemale = Row.createCell(3);
      XSSFCell cellMale = Row.createCell(4);
      XSSFCell cellTotal = Row.createCell(5);
         
      
      cellCounty.setCellValue("County");
      cellActivity.setCellValue("Activity");
      cellDate.setCellValue("Date");
      cellFemale.setCellValue("Female");
      cellMale.setCellValue("Male");
      cellTotal.setCellValue("Total");
      
      cellCounty.setCellStyle(style);
      cellActivity.setCellStyle(style);
      cellDate.setCellStyle(style);
      cellFemale.setCellStyle(style);
      cellMale.setCellStyle(style);
      cellTotal.setCellStyle(style);
              
    return Sheet; 
 }
 
 
 public ArrayList<String> get_supported_counties(dbConn conn) throws SQLException{
             ArrayList<String> counties = new ArrayList<String>();
                               counties.clear();
 
             String query = "SELECT name FROM counties order by name";
             conn.rs3 = conn.st3.executeQuery(query);
             while(conn.rs3.next()){
                counties.add(conn.rs3.getString(1));  
             }

        return counties;
 }
 
}
