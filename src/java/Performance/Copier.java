/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Performance;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mwamb
 */
public class Copier {
      public void copy_template(String sourcepath,String destpath ){
        try {
              Path FROM = Paths.get(sourcepath);
              Path TO = Paths.get(destpath);
              //overwrite existing file, if exists
              CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
              }; 
              Files.copy(FROM, TO, options);
        }
        //=======================================================================
        catch (IOException ex) {
            Logger.getLogger(Copier.class.getName()).log(Level.SEVERE, null, ex);
        }
 
}
  
  public void transfer_template(String src1,String desteepath ){
        try {
              Path FROM = Paths.get(src1);
              Path TO = Paths.get(desteepath);
              //overwrite existing file, if exists
              CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
              }; 
              Files.copy(FROM, TO, options);
       
        }
        //=======================================================================
        catch (IOException ex) {
            Logger.getLogger(Copier.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
  
}
