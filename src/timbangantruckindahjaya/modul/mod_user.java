/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timbangantruckindahjaya.modul;

import timbangantruckindahjaya.db;
import timbangantruckindahjaya.Session;

/**
 *
 * @author taufiq
 */
public class mod_user  extends db
{    
    
    public String UserName(String UserId)
    {
        if(!UserId.equals(""))
        {
            String sql = "select nama from user where user_id='"+UserId+"'";
            String[] res = this.res2(sql);
            if(res.length>0)
                return res[0];
        }
        
        return "";
    }
    
    public boolean CheckPassword(String pass)
    {
        if(!pass.trim().equals(""))
        {
            String sql = "select count(user_id) cnt "
                        +"from user "
                        +"where password=md5('"+pass+"') "
                        + "and user_id='"+Session.User.getUserId()+"'";
            System.out.println(sql);
            Object[] data = this.res2(sql);
            
            if(Integer.valueOf(String.valueOf(data[0]))>0)
                return true;
        }
        
         return false;
    }
    
    public boolean updatePassword(String password)
    {
        if(!password.equals(""))
        {
            String sql = "update user set "+
                        "password=md5('"+password+"')," +
                        "modified_by="+Session.User.getUserId()+"," +
                        "modify_date=now() "+
                        "where user_id=" +Session.User.getUserId();
            boolean ret = this.queryUpdate(sql);
            
            return ret;
        }
        
        
        
        return false;
    }
    
}
