/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Performance;

import DDTA.Manage;
import Database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author mwamb
 */
@MultipartConfig
public class upload_dataset1 extends HttpServlet {
    HttpSession session;
    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "uploads";
    String all_columns="",all_cell_values="",all_row_values="";
    String dataset_type="";
    String user_id,user_level;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        
        Manage mng = new Manage();
       session = request.getSession();
       dbConn conn = new dbConn();
       String table_name="";
       int row_execution=0;
       
       dataset_type = request.getParameter("dataset_type");
       
       if(dataset_type.equals("all_indicators")){
           table_name = "performance";
           row_execution=500;
       }
       
       else if(dataset_type.equals("eid_dataset")){
           table_name="performance_eid";
           row_execution=3000;
       }
       else if(dataset_type.equals("dsd_indicator")){
           table_name="performance_dsd";
           row_execution=300;
       }
       else if(dataset_type.equals("opd_testing")){
           table_name="performance_opd";
           row_execution=3000;
       }
       else if(dataset_type.equals("cecap_indicators")){
           table_name="performance_cecap";
           row_execution=2000;
       }
       else if(dataset_type.equals("prep_indicators")){
           table_name="performance_prep";
           row_execution=2000;
       }
       
       else{
           
       }
       
        String full_path = "";
        String fileName = "";
        String partName = "";
        
        int num_rows=0;
        int position = 0;
        String column = "";
        String cell_value = "";
        String mfl_code="",month_code="",sex="",prep_typology_id="",age_group_id="",id="";
        
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

            
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        for (Part part : request.getParts()) {
          System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());

          partName=getFileName(part,mng.get_timestamp_string());

            if (!partName.equals("")) {

                fileName = partName;
                part.write(uploadFilePath + File.separator + fileName);
                System.out.println("file name is : "+fileName);
                if (fileExtension(part)) {
                    System.out.println("file name : "+fileName);  
      
                    full_path = fileSaveDir.getAbsolutePath() + "/" + fileName; //end of checking if excel file is valid
                    System.out.println("the saved file directory is  :  " + full_path);
                    session.setAttribute("form1a", "<b>Uploading F1a File</b>");
                    session.setAttribute("form1a_count", 20);

                    FileInputStream fileInputStream = new FileInputStream(full_path);
                    BufferedInputStream bfs = new BufferedInputStream(fileInputStream);
                    Workbook workbook = new XSSFWorkbook(bfs);

                    int totalsheets = workbook.getNumberOfSheets();
                    
                    HashMap map_ages = new HashMap();
                    String get_age_groups="SELECT age_groups,id FROM performance_age_groups";
                    conn.rs = conn.st.executeQuery(get_age_groups);
                    while(conn.rs.next()){
                      map_ages.put(conn.rs.getString(1), conn.rs.getInt(2));
                    }
                    System.out.println("map of ages is : "+map_ages.get("<1 (Less than 1 year old) (specific)"));
                    
                    // map of prep typologies
                     HashMap map_prep_typologies = new HashMap();
                    String get_prep_typologies="SELECT template_name,id FROM performance_prep_typologies";
                    conn.rs = conn.st.executeQuery(get_prep_typologies);
                    while(conn.rs.next()){
                      map_prep_typologies.put(conn.rs.getString(1), conn.rs.getInt(2));
                    }
                    

                    for (int sheetno = 0; sheetno < totalsheets; sheetno++) {

                        Sheet worksheet = workbook.getSheetAt(0);

                        System.out.println(sheetno + " (" + workbook.getSheetName(sheetno) + ") out of " + totalsheets + " sheets");
                        num_rows = worksheet.getLastRowNum();
//                        int num_rows = 1032;
                        
                        System.out.println("number of rows is : "+num_rows);
                      
                        ArrayList<HashMap> list = new ArrayList();
                        list.clear();
                        
                        String get_columns="SELECT column_position,column_label FROM performance_column_mapping WHERE is_active=1 AND column_position is not null AND table_name='"+table_name+"'";
                        conn.rs = conn.st.executeQuery(get_columns);
                        while(conn.rs.next()){
                         HashMap map = new HashMap();
                          map.put("position",conn.rs.getInt(1));
                          map.put("column",conn.rs.getString(2));
                          list.add(map);
                        }
                        int total_columns = list.size();
                        
                        // loop through the excel and read the data
                        for(int i=1;i<=num_rows;i++){
                            
                        Row row_data = worksheet.getRow(i);
                        
                        all_columns="REPLACE INTO "+table_name+" (id";
                         mfl_code=month_code=sex=age_group_id=prep_typology_id="";// reset values
                        for(int j=0;j<total_columns;j++){
                            // loop through the columns
//                            System.out.println("list : "+list.get(j));
                            position = Integer.parseInt(list.get(j).get("position").toString());
                            column = list.get(j).get("column").toString();
//                            System.out.println("column position is : "+list.get(j).get("position").toString()+" and label is :"+list.get(j).get("column").toString());
                            
//                            System.out.println(i+"position : "+position+" column : "+column);
                          Cell cell = row_data.getCell(position);   
                          if(null==cell.getCellType()){
                              cell_value="Unknown";
                          }
                          else switch (cell.getCellType()) {
                                case NUMERIC:
                                    cell_value = (cell.getNumericCellValue()+"").replace(".0", "");
                                    break;
                                case STRING:
                                    cell_value = cell.getStringCellValue().replace(".0", "");
                                    break;
                                default:
                                    cell_value="0";
                                    break;
                            }
                            
                         if(column.equals("mflcode") || column.equals("dsd_mfl_code") || column.equals("pmtct_mfl_code")|| column.equals("opd_mfl_code")|| column.equals("cecap_mfl_code") || column.equals("prep_mfl_code") )
                         {cell_value = cell_value.replace(".0", ""); mfl_code=cell_value;}
                         else if(column.equals("month") || column.equals("dsd_month") || column.equals("pmtct_month") || column.equals("opd_month") || column.equals("cecap_month") || column.equals("prep_month")  )
                         {cell_value = cell_value.replace(".0", ""); month_code=cell_value;}
                         else if(column.equals("sex") || column.equals("dsd_gender") || column.equals("prep_gender") )
                         {sex=cell_value;}
                         else if(column.equals("age_group") || column.equals("dsd_age_group") || column.equals("pmtct_ages") || column.equals("cecap_age") || column.equals("prep_age_group"))
                         {
//                            System.out.println("cell value : "+cell_value);
                             age_group_id=map_ages.get(cell_value).toString();
                           
                         }
                         
                         else if(column.equals("prep_typology")){
                         prep_typology_id = map_prep_typologies.get(cell_value).toString();    
                         }
                         else{}
                         generate_columns(column);
                         generate_values(cell_value);
                         id=(mfl_code+"-"+month_code+"-"+sex+"-"+age_group_id)+"-"+prep_typology_id.replace("--", "-");
                        }
        
                        all_columns+=") ";
                        all_cell_values="('"+id+"'"+all_cell_values+")";
                       
                        all_row_values=all_row_values+" "+all_cell_values+",";
                        
                        if(i%row_execution==0 || i==num_rows){ // at the 1000th row, then insert
                         all_row_values=" VALUES "+ removeLast(all_row_values,1)+";";
                         String query = all_columns+" "+all_row_values;
                         conn.st.executeUpdate(query);
                         System.out.println("at position : "+i); 
                         all_row_values="";
                        }
                        all_cell_values=all_columns="";
                        }
              }

                //end of worksheets loop

              session.setAttribute("upload_status", "<b>Data uploading and saving completed. "+num_rows+" records uploaded.</b>");
                }
                else{
                session.setAttribute("upload_status", "<b>Upload module only expects .xlsx files. kindly convert to the required format and try re-uploading</b>");
                 System.out.println("file name not ending with xlsx, exit");    
                }
             }
          }
        
        
        response.sendRedirect("uploading_performance_data.jsp");
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
            Logger.getLogger(upload_dataset1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(upload_dataset1.class.getName()).log(Level.SEVERE, null, ex);
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

        private String getFileName(Part part, String timestamp) {
        String file_name = "";
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");

        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length() - 1)+"_"+timestamp;
                file_name = file_name.replace(".xlsx", "").replace(".", "_");
                file_name = file_name+".xlsx";
                System.out.println("content-disposition final : " + file_name);
                 break;
            }
            
        }
        
        return file_name;
    }
        private boolean fileExtension(Part part) {
        String file_name = "";
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");

        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length() - 1);
                break;
            }
            
        }
        
        return file_name.endsWith(".xlsx");
    }
        
      public Workbook ReadExcel(String excelpath) throws IOException, InvalidFormatException {
        Workbook wb = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelpath));
            wb = WorkbookFactory.create(inputStream);
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(upload_dataset1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return wb;
    }

    public static String removeLast(String str, int num) {
        return str.substring(0, str.length() - num);
    }
    
    public void generate_columns(String column){
     all_columns+=",`"+column+"`";  
    }
    
    public void generate_values(String cell_value){
     all_cell_values+=",'"+cell_value+"'"; 
    }
    
        
}
