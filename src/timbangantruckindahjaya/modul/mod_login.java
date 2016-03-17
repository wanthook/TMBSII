/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timbangantruckindahjaya.modul;

import timbangantruckindahjaya.db;

/**
 *
 * @author taufiq
 */

public class mod_login extends db
{
    
    public mod_login()
    {
        
    }
    
    public int is_user(String username, String password)
    {
        String sql = "select * "+
                     "from user "+
                     "where username='"+username+"' "
                    + "  and password=md5('"+password+"') "
                + "and hapus='1' "
                + "and type in ('ADMIN','OPR','IT')";
        
        String[][] data = this.res(sql);
        
        if(data.length>0)
            return Integer.valueOf(data[0][2]);
        
        return 0;
    }
   
}
