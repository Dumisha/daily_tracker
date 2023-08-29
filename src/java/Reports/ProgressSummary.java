/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Reports;

import DDTA.Manage;
import Database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
public class ProgressSummary extends HttpServlet {
    HttpSession session;
    String start_date,end_date,user_id,indicator_ids,indicator_name,query,facility_ids,user_level;
    int row;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
              session = request.getSession();
        dbConn conn = new dbConn();
        Manage mg = new Manage();
        
        start_date = request.getParameter("start_date");
        end_date = request.getParameter("end_date");
        
        
        System.out.println("Start Date :"+start_date+" end date: "+end_date);
        
        
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
    
    
query=
      "SELECT  \n" +
        "prep_new, \n" +
        "\"\" as Prp_ct, \n" +
        "\"\" as kp_prev, \n" +
        "\"\" as pp_prev, \n" +
        "\"\" as tb_prev_d, \n" +
        "\"\" as tb_prev_n, \n" +
        "ifnull(gbv_cases,\"\") as gbv_cases, \n" +
        "hts_tst, \n" +
        "\"\" as hts_recent, \n" +
        "self.kits_issued as hts_self, \n" +
        "pos.pos as hts_pos, \n" +
        "pmtct.pmtct_stat_den, \n" +
        "pmtct.pmtct_stat_num, \n" +
        "pmtct.pmtct_stat_num_pos, \n" +
        "pmtct.pmtct_stat_art, \n" +
        "pmtct_eid_0_2, \n" +
        "pmtct_eid_2_12, \n" +
        "CXCA_SCRN, \n" +
        "tx_new, \n" +
        "0 as tx_curr, \n" +
        "vl.txpvls_d as tx_pvls_d, \n" +
        "vl.txpvls_n as txpvls_n, \n" +
        "tb_stat_d, \n" +
        "tb_stat_n, \n" +
        "tb_stat_n_pos, \n" +
        "tb_art, \n" +
        "\"\" as tx_tb  \n" +
        "FROM (SELECT  \n" +
        "1 as id, \n" +
        "sum(sexual_cases+emotional_cases+physical_cases) as gbv_cases  \n" +
        "from etl_gend_gbv gbv  \n" +
        "where gbv.encounter_date BETWEEN date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        ") as gbv  \n" +
        "inner join \n" +
        "( \n" +
        "SELECT  \n" +
        "1 as id, \n" +
        " sum(prep_refill+restarting_prep) as prep_ct \n" +
        " FROM etl_prep prep  \n" +
        " WHERE  \n" +
        "prep.encounter_date BETWEEN date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        " ) as prep ON gbv.id=prep.id  \n" +
        "  \n" +
        " inner join \n" +
        "( \n" +
        "SELECT  \n" +
        "1 as id, \n" +
        " sum(tested) as hts_tst, \n" +
        "sum(hts.prep_new) as prep_new \n" +
        " FROM etl_hts_tst hts  \n" +
        " WHERE  \n" +
        "hts.encounter_date BETWEEN date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        " ) as hts ON gbv.id=hts.id  \n" +
        " inner join ( \n" +
        "select 1 as id, \n" +
        "count(*) as pos \n" +
        "from etl_hts_pos  \n" +
        "where encounter_date between date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        ") as pos on gbv.id=pos.id \n" +
        " \n" +
        " inner join ( \n" +
        " select 1 as id, \n" +
        "sum(tpt_new_on_art+tpt_current_on_art) as tp_prev  \n" +
        "from etl_tpt tpt \n" +
        "where tpt.encounter_date BETWEEN date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        " ) as tpt on gbv.id=tpt.id  \n" +
        " inner join( \n" +
        "  select 1 as id, \n" +
        "sum(results_received) as txpvls_d, \n" +
        "sum(suppressed) as txpvls_n   \n" +
        "from etl_vl_suppression vl \n" +
        "where encounter_date BETWEEN date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        " ) as vl on gbv.id=vl.id  \n" +
        " inner join  \n" +
        " ( \n" +
        " SELECT 1 as id, \n" +
        " sum(anc1_new_anc_visits) as pmtct_stat_den, \n" +
        " sum(anc1_initial_tests+anc1_known_positive) as pmtct_stat_num, \n" +
        " sum(anc1_new_positive+anc1_known_positive) as pmtct_stat_num_pos, \n" +
        " sum(anc1_started_haart+anc1_on_haart) as pmtct_stat_art \n" +
        " from etl_pmtct pmtct  \n" +
        " where pmtct.encounter_date BETWEEN date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        " ) pmtct on gbv.id=pmtct.id \n" +
        "inner join  \n" +
        "( \n" +
        " select 1 as id, \n" +
        "sum(initial_test_0_2m) as pmtct_eid_0_2, \n" +
        "sum(initial_test_2_12m) as pmtct_eid_2_12  \n" +
        "from etl_pmtct_eid  \n" +
        "where encounter_date BETWEEN date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        " ) as pmtct_eid on gbv.id=pmtct_eid.id  \n" +
        " inner join ( \n" +
        "  select 1 as id, \n" +
        "sum(kits_issued) as kits_issued  \n" +
        "from etl_hts_self sf \n" +
        "where encounter_date BETWEEN date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        " ) as self on gbv.id=self.id  \n" +
        "  \n" +
        " inner join \n" +
        " ( \n" +
        " select 1 as id, \n" +
        " sum(screened) as CXCA_SCRN \n" +
        " from etl_ca_screening CXCA_SCRN \n" +
        " where CXCA_SCRN.encounter_date between date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        ") as CXCA_SCRN on gbv.id=CXCA_SCRN.id \n" +
        "inner join  \n" +
        "( \n" +
        "select 1 as id, \n" +
        "count(*) as tx_new \n" +
        "from etl_tx_new tx_new \n" +
        "where tx_new.encounter_date between date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        ") as tx_new on gbv.id=tx_new.id \n" +
        " \n" +
        "inner join  \n" +
        "( \n" +
        "select 1 as id, \n" +
        "sum(new_relapse_tb_cases) as tb_stat_d, \n" +
        "sum(tb_hiv_pos+tb_hiv_neg+tb_hiv_kp) as tb_stat_n, \n" +
        "sum(tb_hiv_pos+tb_hiv_kp) as tb_stat_n_pos, \n" +
        "sum(tb_art) as tb_art \n" +
        "from etl_tb tb_stat \n" +
        "where encounter_date between date (\""+start_date+"\") and date(\""+end_date+"\") \n" +
        ") as tb_stat on gbv.id=tb_stat.id";
    
    XSSFSheet sheet= wb.createSheet(""+start_date+" to "+end_date+" Report");
         // run query to get_data
         
         System.out.println("Query is : "+query);
      conn.rs1 = conn.st1.executeQuery(query);
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
              
              
               if( conn.conn!=null){conn.conn.close();}
      
       String filename="Daily_data_tracking_Indicator_Summary_on_"+mg.get_timestamp_string()+".xlsx";
      
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
            Logger.getLogger(ProgressSummary.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProgressSummary.class.getName()).log(Level.SEVERE, null, ex);
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
