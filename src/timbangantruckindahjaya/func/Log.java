/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package timbangantruckindahjaya.func;

import timbangantruckindahjaya.Session;
import timbangantruckindahjaya.db;
/**
 *
 * @author taufiq
 */
public class Log extends db
{
    public void InsertLog(String type, String memo)
    {
        String sql = "insert into log set kode_log='"+type+"', keterangan_log='"+memo+"', created_by='"+Session.User.getUserId()+"', create_date=now()";
        int querySave = this.querySave(sql);
    }
}
