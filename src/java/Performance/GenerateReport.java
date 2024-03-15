/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Performance;

import DDTA.Manage;
import Database.dbConn;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Performance.OSValidator;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
/**
 *
 * @author mwamb
 */
public class GenerateReport extends HttpServlet {
    HttpSession session;
    String FilePath="";
    String user_id,user_level,facility_ids;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InvalidFormatException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        session = request.getSession();
        dbConn conn = new dbConn();
            Copier ct= new Copier();
            Manage mg = new Manage();
            OSValidator os = new OSValidator();
            
        
        String start_period = request.getParameter("start_period");
        String end_period = request.getParameter("end_period");
        
                  if(session.getAttribute("user_id")!=null){
                  user_id=user_level="0";
                  
                    
     if(session.getAttribute("user_id")!=null){
           user_id=session.getAttribute("user_id").toString(); 
       }
     
     if(session.getAttribute("user_level_id")!=null){
           user_level=session.getAttribute("user_level_id").toString(); 
       }
      }
              ArrayList<String>  facilities = mg.get_locations(user_id,user_level,conn);
              
             facility_ids="";
      for(String facility : facilities){
         facility_ids+=facility+","; 
      }
     // remove last comma 
      if(!facilities.isEmpty()){
      facility_ids = mg.removeLast(facility_ids,1);
      }
      
      else{
        facility_ids="0";  
      }
      
        
        String allpath = getServletContext().getRealPath("/Performance_Analysis_Template.xlsx");

        XSSFWorkbook wb1;

        String pathtodelete=null;

        Date da= new Date();
        String dat2 = da.toString().replace(" ", "_");

        dat2 = dat2.replace(":", "_");

        String mydrive = allpath.substring(0, 1);

        String np=mydrive+":\\Dumisha\\DDTA\\Template\\";

        String filepath="Report_Temp_"+dat2+".xlsx";


        if(os.isUnix()){
            np="/Dumisha/DDTA/Template/";
            FilePath="/Dumisha/DDTA/Template/";
        }

        if(os.isUnix()){
        FilePath=np+"createdExcel/";
//        in ubuntu 20 as part of the set-up,
//        use this guide here link: https://stackoverflow.com/questions/56827735/how-to-allow-tomcat-war-app-to-write-in-folder

//setup: /lib/systemd/system/tomcat9.service then 

//    [Service]
//
//    ReadWritePaths=/path/to/the/directory/
//
// // The service has to be restarted afterward with:
//
//  systemctl daemon-reload
//  systemctl restart tomcat9

          
        }
        else{
        FilePath=np+"createdExcel\\";    
        }

         new File(np).mkdirs();
          new File(FilePath).mkdirs(); // make route to excel files

         np+=filepath;

        //check if file exists
        String sourcepath = getServletContext().getRealPath("/Performance_Analysis_Template.xlsx");
        System.out.println("file path is :::::::::::::::::::::: "+np);
        File f = new File(np);
        //np+=filepath;
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        if (!f.exists()){
            f.createNewFile();
                System.out.println("File does not exist. Copying file..");
                ct.transfer_template(sourcepath,np);
        }
        else
            //copy the file alone  
        {
        //copy the agebased file only
        ct.copy_template(sourcepath,np);

        }
        File allpathfile= new File(np);

        OPCPackage pkg = OPCPackage.open(allpathfile);



        //wb = new XSSFWorkbook( OPCPackage.open(allpath) );
        wb1 = new XSSFWorkbook(pkg);

        XSSFWorkbook wb = wb1;

                     //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
        XSSFSheet sheet=null;

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
        styleHeader.setWrapText(true);

        XSSFCellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
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

       int row=0;  

            String get_queries = "SELECT name,query,last_column FROM performance_queries WHERE status=1 ORDER BY execution_order ASC";
            conn.rs = conn.st.executeQuery(get_queries);
            while(conn.rs.next()){
                row=0; 
                String name = conn.rs.getString(1);
                String query = conn.rs.getString(2);
                String last_column = conn.rs.getString(3);
                sheet= wb.getSheet(name);
                
                
                // replace periods if available
                query= query.replace("start_period_holder", start_period); // start period
                query= query.replace("end_period_holder", end_period);
                query= query.replace("facility_ids", facility_ids); // extract specific data for specific facilities

                System.out.println("query : "+name);
                conn.rs1 = conn.st1.executeQuery(query);
                ResultSetMetaData metaData = conn.rs1.getMetaData();
                int col_count = metaData.getColumnCount(); //number of column
                String  value = "";

                while(conn.rs1.next()){
                        System.out.println("called at pos :"+row);
                        row++;
                        XSSFRow RowData;
                        if(row==1){
                         RowData = sheet.getRow(row);    
                        }
                        else{
                      RowData = sheet.createRow(row);
                        }
                      for(int i=0;i<col_count; i++){ // read and output data
                        value = conn.rs1.getString(i+1);
                        XSSFCell cell = RowData.createCell(i);
                        if(mg.isNumeric(value)){cell.setCellValue(Integer.parseInt(value));}
                        else{cell.setCellValue(value);}
                        cell.setCellStyle(stborder);
                        }  
                }
      if(1==1){
    ((XSSFSheet)sheet).getCTWorksheet().getDimension().setRef("A1:"+last_column+"" + (sheet.getLastRowNum() + 1));

    CTTable ctTable = ((XSSFSheet)sheet).getTables().get(0).getCTTable();

    ctTable.setRef("A1:"+last_column+""+ (sheet.getLastRowNum() + 1)); // adjust reference as needed

            }       
                
            }
            
            
            // LOG AND UPDATE CONFIG in excel
            
            String get_configs = "SELECT config_name,query,sheet_name,row_no,title_position,value_position FROM performance_configs WHERE status=1";
            conn.rs = conn.st.executeQuery(get_configs);
            while (conn.rs.next()) {
            String config_name = conn.rs.getString(1);
            String  query = conn.rs.getString(2);
            String  sheet_name = conn.rs.getString(3);
            String  row_no = conn.rs.getString(4);
            String  title_position = conn.rs.getString(5);
            String  value_position = conn.rs.getString(6);
            String value = "";
            
             conn.rs1 = conn.st1.executeQuery(query);
             if(conn.rs1.next()){
               value = conn.rs1.getString(1);
             }
             
             
             // Out put to the sheet 
            sheet= wb.getSheet(sheet_name); 
            XSSFRow RowConfigs = sheet.getRow(Integer.parseInt(row_no));
            XSSFCell cellTitle = RowConfigs.getCell(Integer.parseInt(title_position));
            XSSFCell cellValue = RowConfigs.getCell(Integer.parseInt(value_position));
            
            // set values 
            if(mg.isNumeric(config_name)){
                cellTitle.setCellValue(Integer.parseInt(config_name));
            }
            else{
               cellTitle.setCellValue(config_name); 
            }
            
            
            if(mg.isNumeric(value)){
                cellValue.setCellValue(Integer.parseInt(value));
            }
            else{
               cellValue.setCellValue(value); 
            }
            
        }
            
      
           if( conn.conn!=null){conn.conn.close();}
    
    ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
    wb.write(outByteStream);
    byte [] outArray = outByteStream.toByteArray();
    response.setContentType("application/ms-excel");
    response.setContentLength(outArray.length);
    response.setHeader("Expires:", "0"); // eliminates browser caching
    response.setHeader("Set-Cookie:", "fileDownload=true; path=/"); // set cookie header
    response.setHeader("Content-Disposition", "attachment; filename=USAID_Dumisha_Afya_Performance_Analysis_Report_Generated_On_"+mg.get_timestamp_string()+".xlsx");
    OutputStream outStream = response.getOutputStream();
    outStream.write(outArray);
     try{
        outStream.flush();
       }
       catch(IOException e){
           System.out.println(" Failed to flush output..... performance analysis bookings: "+e);
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
        } catch (InvalidFormatException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (InvalidFormatException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
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
