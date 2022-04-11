/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STF;

import DDTA.Manage;
import Database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Geofrey Nyabuto
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 20 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)

public class stf_upload extends HttpServlet {
    HttpSession session;
    
    String full_path = "";
    String fileName = "";

    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "UPLOADS\\";

    
 String id,system_id,batch_no,ccc_no,lab,mfl_code,gender,dob,age,sample_type,date_collected,justification,date_received,date_tested,date_dispatched,art_start_date,received_status,regimen,regimen_line,pmtct,results,user_id;   
    int added,skipped;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        response.setContentType("text/html;charset=UTF-8");
        session = request.getSession();

        dbConn conn = new dbConn();
        Manage mg = new Manage();
        System.out.println(" os is : "+mg.check_OS());
        
        
        
        added=skipped=0;
       
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        for (Part part : request.getParts()) {
           if (!getFileName(part).equals("")) {
                
                fileName = getFileName(part);
                
                if (!fileName.endsWith(".xlsx")) { // unsupported file format
                }
                
            }
             if (!fileName.endsWith(".xlsx")) { // unsupported file format
            } 
           else {
             try {
            
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
            
             part.write(uploadFilePath + File.separator + fileName); 
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
            
                    if(mg.check_OS().contains("Windows")){
                    full_path = fileSaveDir.getAbsolutePath() + "\\" + fileName; //end of checking if excel file is valid 
                    }
                    else if(mg.check_OS().contains("nux")){
                    full_path = fileSaveDir.getAbsolutePath() + "\\" + fileName; //end of checking if excel file is valid 
                    //   
                    }
                    
                    System.out.println("the saved file directory is  :  " + full_path);
                    
                    XSSFWorkbook workbook = (XSSFWorkbook) ReadExcel(full_path);
                        
                        int totalsheets = workbook.getNumberOfSheets();
                        
                        System.out.println("number of sheets : "+totalsheets);
             
                        for(int i=0;i<totalsheets;i++){ // loop through the sheets and read data
                        XSSFSheet sheet = workbook.getSheetAt(i);
                       
                        int rows = sheet.getPhysicalNumberOfRows();
                        
                            if(rows>1){ // avoid reading headers... 
                               int cols = sheet.getRow(0).getPhysicalNumberOfCells();
                               System.out.println(sheet.getSheetName()+"---- rows are : "+rows+" and "+cols+" columns");
                        
                          for(int j=1;j<rows;j++){
                            system_id=batch_no=ccc_no=lab=mfl_code=gender=dob=age=sample_type=date_collected=justification=
                            date_received=date_tested=date_dispatched=art_start_date=received_status=regimen=regimen_line=pmtct=results="";   
    
                         XSSFRow Row = sheet.getRow(j);
                         
                         // read the excel cells
                        XSSFCell   cellSystem_id = Row.getCell(0);
                        XSSFCell   cellBatch_no = Row.getCell(1);
                        XSSFCell   cellCcc_no = Row.getCell(2);
                        XSSFCell   cellLab = Row.getCell(3);
                        XSSFCell   cellMfl_code = Row.getCell(8);
                        XSSFCell   cellGender = Row.getCell(9);
                        XSSFCell   cellDob = Row.getCell(10);
                        XSSFCell   cellAge = Row.getCell(11);
                        XSSFCell   cellSample_type = Row.getCell(12);
                        XSSFCell   cellDate_collected = Row.getCell(13);
                        XSSFCell   cellJustification = Row.getCell(14);
                        XSSFCell   cellDate_received = Row.getCell(15);
                        XSSFCell   cellDate_tested = Row.getCell(16);
                        XSSFCell   cellDate_dispatched = Row.getCell(17);
                        XSSFCell   cellArt_start_date = Row.getCell(18);
                        XSSFCell   cellReceived_status = Row.getCell(19);
                        XSSFCell   cellRegimen = Row.getCell(20);
                        XSSFCell   cellRegimen_line = Row.getCell(21);
                        XSSFCell   cellPmtct = Row.getCell(22);
                        XSSFCell   cellResults = Row.getCell(23);
                        
                        
                        //cast values in the excel to string
                        if(cellSystem_id!=null){cellSystem_id .setCellType(CellType.STRING);}
                        if( cellBatch_no!=null){ cellBatch_no .setCellType(CellType.STRING);}
                        if( cellCcc_no!=null){ cellCcc_no .setCellType(CellType.STRING);}
                        if( cellLab!=null){ cellLab .setCellType(CellType.STRING);}
                        if( cellMfl_code!=null){ cellMfl_code .setCellType(CellType.STRING);}
                        if( cellGender!=null){ cellGender .setCellType(CellType.STRING);}
//                        if( cellDob!=null){ cellDob .setCellType(CellType.STRING);}
                        if( cellAge!=null){ cellAge .setCellType(CellType.STRING);}
                        if( cellSample_type!=null){ cellSample_type .setCellType(CellType.STRING);}
//                        if( cellDate_collected!=null){ cellDate_collected .setCellType(CellType.STRING);}
                        if( cellJustification!=null){ cellJustification .setCellType(CellType.STRING);}
//                        if( cellDate_received!=null){ cellDate_received .setCellType(CellType.STRING);}
//                        if( cellDate_tested!=null){ cellDate_tested .setCellType(CellType.STRING);}
//                        if( cellDate_dispatched!=null){ cellDate_dispatched .setCellType(CellType.STRING);}
//                        if( cellArt_start_date!=null){ cellArt_start_date .setCellType(CellType.STRING);}
                        if( cellReceived_status!=null){ cellReceived_status .setCellType(CellType.STRING);}
                        if( cellRegimen!=null){ cellRegimen .setCellType(CellType.STRING);}
                        if( cellRegimen_line!=null){ cellRegimen_line .setCellType(CellType.STRING);}
                        if( cellPmtct!=null){ cellPmtct .setCellType(CellType.STRING);}
                        if( cellResults!=null){ cellResults .setCellType(CellType.STRING);}



                         // read all values          
                                   
                        if(cellSystem_id!=null){system_id = cellSystem_id .getStringCellValue();}
                        if( cellBatch_no!=null){batch_no = cellBatch_no .getStringCellValue();}
                        if( cellCcc_no!=null){ccc_no = cellCcc_no .getStringCellValue();}
                        if( cellLab!=null){lab = cellLab .getStringCellValue();}
                        if( cellMfl_code!=null){mfl_code = cellMfl_code .getStringCellValue();}
                        if( cellGender!=null){gender = cellGender .getStringCellValue();}
                        if( cellDob!=null){dob = ""+formatter.format(cellDob .getDateCellValue());}
                        if( cellAge!=null){age = cellAge .getStringCellValue();}
                        if( cellSample_type!=null){sample_type = cellSample_type .getStringCellValue();}
                        if( cellDate_collected!=null){date_collected = ""+formatter.format(cellDate_collected .getDateCellValue());}
                        if( cellJustification!=null){justification = cellJustification .getStringCellValue();}
                        if( cellDate_received!=null){date_received = ""+formatter.format(cellDate_received .getDateCellValue());}
                        if( cellDate_tested!=null){date_tested = ""+formatter.format(cellDate_tested .getDateCellValue());}
                        if( cellDate_dispatched!=null){date_dispatched = ""+formatter.format(cellDate_dispatched.getDateCellValue());}
                        if( cellArt_start_date!=null){art_start_date = ""+formatter.format(cellArt_start_date .getDateCellValue());}
                        if( cellReceived_status!=null){received_status = cellReceived_status .getStringCellValue();}
                        if( cellRegimen!=null){regimen = cellRegimen .getStringCellValue();}
                        if( cellRegimen_line!=null){regimen_line = cellRegimen_line .getStringCellValue();}
                        if( cellPmtct!=null){pmtct = cellPmtct .getStringCellValue();}
                        if( cellResults!=null){results  = cellResults .getStringCellValue();}

                        
                        id = ccc_no+""+date_tested.replace("-", "");

  
                              System.out.println("ccc number : "+ccc_no+" result : "+results+" date dispatched "+date_dispatched);      
                         
                              
                      String checker = "SELECT id FROM stf WHERE ccc_no=? && date_tested=?" ;  // check if sample already logged in our EMR
                              conn.pst = conn.conn.prepareStatement(checker);
                              conn.pst.setString(1, ccc_no);
                              conn.pst.setString(2, date_tested);
                              conn.rs = conn.pst.executeQuery();
                              if(conn.rs.next()){
//                              record already exist
                                  skipped++;
                              }
                             
                              else{
                                  String inserter = "INSERT INTO stf (id,system_id,batch_no,ccc_no,lab,mfl_code,gender,dob,age,sample_type,date_collected,justification,date_received,date_tested,date_dispatched,art_start_date,received_status,regimen,regimen_line,pmtct,results,user_id)"
                                          + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                                  conn.pst = conn.conn.prepareStatement(inserter);
            
                                  conn.pst.setString(1, id);
                                  conn.pst.setString(2, system_id);
                                  conn.pst.setString(3, batch_no);
                                  conn.pst.setString(4, ccc_no);
                                  conn.pst.setString(5, lab);
                                  conn.pst.setString(6, mfl_code);
                                  conn.pst.setString(7, gender);
                                  conn.pst.setString(8, dob);
                                  conn.pst.setString(9, age);
                                  conn.pst.setString(10, sample_type);
                                  conn.pst.setString(11, date_collected);
                                  conn.pst.setString(12, justification);
                                  conn.pst.setString(13, date_received);
                                  conn.pst.setString(14, date_tested);
                                  conn.pst.setString(15, date_dispatched);
                                  conn.pst.setString(16, art_start_date);
                                  conn.pst.setString(17, received_status);
                                  conn.pst.setString(18, regimen);
                                  conn.pst.setString(19, regimen_line);
                                  conn.pst.setString(20, pmtct);
                                  conn.pst.setString(21, results);
                                  conn.pst.setString(22, user_id);
                                  
                                  conn.pst.executeUpdate();
                                  added++;
                              }
                              
                              
                              
                              
                              
                          }
                            
                            
                            
                            
                            }
                            
                            
                            
                            
                        }
             
             
             
             
             }
              
                catch(IOException e){
                    System.out.println("Missing file error");
                } catch (InvalidFormatException e) {
                    System.out.println("Missing file error");
               }
             
             }        
        }
        
       
        String message="Summary : <br>"
                + " "+added+" Records added successfully. <br>"
                + ""+skipped+" Records skippped. They already exist in the system.";
        
        session.setAttribute("message", message);
        
      response.sendRedirect("stf_uploads.jsp");
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
            Logger.getLogger(stf_upload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(stf_upload.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(stf_upload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(stf_upload.class.getName()).log(Level.SEVERE, null, ex);
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

    private String getFileName(Part part) {
        String file_name = "";
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");

        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length() - 1);
                System.out.println("content-disposition final : " + file_name);
                
                break;
            }

        }
        
        return file_name;
    }

    public Workbook ReadExcel(String excelpath) throws IOException, InvalidFormatException {
        Workbook wb = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelpath));
            wb = WorkbookFactory.create(inputStream);
            int numberOfSheet = wb.getNumberOfSheets();

            for (int i = 0; i < numberOfSheet; i++) {
                Sheet sheet = wb.getSheetAt(i);
                //.... Customize your code here
                // To get sheet name, try -> sheet.getSheetName()
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(stf_upload.class.getName()).log(Level.SEVERE, null, ex);
        }

        return wb;
    }

    public static String removeLast(String str, int num) {
        return str.substring(0, str.length() - num);
    }

}
