/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Performance;

/**
 *
 * @author mwamb
 */
public class OSValidator {
   
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	 public OSValidator() {
		
		System.out.println(OS);
	}

	public boolean isWindows() {

		return (OS.contains("win"));

	                                  }

	public boolean isMac()     {

		return (OS.contains("mac"));

	                                  }

	public boolean isUnix()    {

		return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
		
	                                  }

	public boolean isSolaris() {

		return (OS.contains("sunos"));

	                                  }
   
}
