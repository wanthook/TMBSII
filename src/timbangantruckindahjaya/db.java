/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timbangantruckindahjaya;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.Properties;

import java.io.InputStream;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import timbangantruckindahjaya.setting.PropertiesSource;

/**
 *
 * @author taufiq
 */
public class db 
{
    public String host, username, password, db;
    public Connection conn;
    public Statement state;
    public ResultSet rs;
    public ResultSetMetaData rsmd;
    
    public db()
    {
//        this.setConnVar();
    }
    
    public Connection open()
    {
        setConnVar();        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://"+host+"/"+db,username,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void setConnVar()
    {
        PropertiesSource ps = new PropertiesSource();
        Properties prop = ps.OpenProperties();
        
        if(prop!=null)
        {
            host = prop.getProperty("host");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            db = prop.getProperty("db");
        }
        else
        {
            host = "";
            username = "";
            password = "";
        }
//        try
//        {
////            InputStream in = getClass().getResourceAsStream("setting.properties");
////            prop.load(in);
////            in.close();
//            
//            
//        }
//        catch(IOException e)
//        {
//            host = "";
//            username = "";
//            password = "";
//            e.printStackTrace();
//        }
    }
    
   public String[][] res(String query)
    {
        String sRes[][]=null; //string Data Two Dimension
        conn = null;
        state = null;
        rs = null;
        rsmd = null;
        
        
        
        try{
                conn=this.open();
                state=conn.createStatement();
                rs=state.executeQuery(query);
                rsmd=rs.getMetaData();
                rs.last();
                sRes=new String[rs.getRow()][rsmd.getColumnCount()];
                rs.first();
                for(int i=0;i<sRes.length;i++){
                        for(int j=0;j<sRes[i].length;j++){
                                sRes[i][j]=rs.getString(j+1);
                        }
                        rs.next();//recordset berikutnya
                }
                rs.close();state.close();conn.close();
        }
        catch(SQLException e){
                e.printStackTrace();
        }
        return sRes;
    }

    public String[] res2(String query)
    {
        String sRes[]=null; //string Data Two Dimension
        conn = null;
        state = null;
        rs = null;
        rsmd = null;  
        
        try{
                conn=this.open();
                state=conn.createStatement();
                rs=state.executeQuery(query);
                rsmd=rs.getMetaData();
                sRes=new String[rsmd.getColumnCount()];
                rs.first();
                for(int i=0;i<sRes.length;i++)
                {
                    sRes[i]=rs.getString(i+1);
                }
                rs.close();state.close();conn.close();
        }
        catch(SQLException e){
                e.printStackTrace();
        }
        return sRes;
    }
    
    public int querySave(String sql)
    {
        conn = null;
        rs = null;
              
        try
        {
            conn=this.open();
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                int gInt = rs.getInt(1);
                return gInt;
            }
        }
        catch(SQLException e)
        {
            
        }
        
        return 0;
    }
    
    public boolean queryUpdate(String sql)
    {
        conn = null;
        rs = null;
                
        try
        {
            conn=this.open();
            PreparedStatement ps = conn.prepareStatement(sql);
            int exe = ps.executeUpdate();
            if(exe==1)
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            
        }
        
        return false;
    }
}
