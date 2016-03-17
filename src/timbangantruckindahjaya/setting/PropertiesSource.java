/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbangantruckindahjaya.setting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

/**
 *
 * @author wanthook
 */
public class PropertiesSource 
{
    public void SaveProperties(
                                String host, 
                                String database, 
                                String user, 
                                String pass, 
                                String port,
                                String baudrate,
                                String databits,
                                String stopbits,
                                String parity,
                                String noTimbangan
                            )
    {
        Properties prop = new Properties();
//	OutputStream output = null;
        SecuritySource ss = new SecuritySource();
        
        try 
        {
 
//            output = new FileOutputStream("config.properties");
            
            // get the property value and print it out
            prop.setProperty("host", host);
            prop.setProperty("username", user);
            prop.setProperty("password", pass);
            prop.setProperty("db", database);            
            prop.setProperty("port", port);
            prop.setProperty("baudrate", baudrate);
            prop.setProperty("databits", databits);
            prop.setProperty("stopbits", stopbits);
            prop.setProperty("parity", parity);
            prop.setProperty("timbangan_no", noTimbangan);
//            System.out.println(getPropertyAsString(prop));
            
            ss.setPassword("jHJ898idJKHUd8Li003jjfJKJ834HHYUIiiuq");
            
            String enc = ss.Encript(getPropertyAsString(prop));
            this.WriteFile(enc, "setting.properties");
//            prop.store(output, null);
 
	} 
        catch (IOException ex) 
        {
            ex.printStackTrace();
	} 
//        finally 
//        {
//            if (output != null) 
//            {
//                try 
//                {
//                        output.close();
//                } 
//                catch (IOException e) 
//                {
//                        e.printStackTrace();
//                }
//            }
//	}
    }
    
    public Properties OpenProperties()
    {
        Properties prop = null;
//	OutputStream output = null;
        SecuritySource ss = new SecuritySource();
        
        String[] ret = new String[5];
        
        try 
        {
            String enc = this.OpenFile("setting.properties");
//            System.out.println(enc);
            if(!enc.isEmpty())
            {
                ss.setPassword("jHJ898idJKHUd8Li003jjfJKJ834HHYUIiiuq");
                
                String dec = ss.Decript(enc);
                
                prop = this.parsePropertiesString(dec);
                
                
            }
 
	} 
        catch (IOException ex) 
        {
            ex.printStackTrace();
	} 
        
        return prop;
    }
    
    public static String getPropertyAsString(Properties prop) 
    {    
        StringWriter writer = new StringWriter();
        prop.list(new PrintWriter(writer));
        return writer.getBuffer().toString();
    }
    
    public Properties parsePropertiesString(String s) throws IOException 
    {
        // grr at load() returning void rather than the Properties object
        // so this takes 3 lines instead of "return new Properties().load(...);"
        final Properties p = new Properties();
        p.load(new StringReader(s));
        return p;
    }
    
    public void WriteFile(String write, String file) throws IOException 
    {
        FileWriter fw = new FileWriter(file);
        
        fw.write(write);
        
        fw.close();
    }
    
    public String OpenFile(String file) throws IOException 
    {
        BufferedReader br = null;
        
        String line = null;
        
        br = new BufferedReader(new FileReader(file));
        line = br.readLine();
//        while((line = br.readLine())!=null)
//        {
//            
//        }
        br.close();
        return line;
    }
}
