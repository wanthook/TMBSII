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
public class mod_timbangan  extends db
{    
    
    public mod_timbangan(){}
    public Object[][] dataTable()
    {
               
        String sql = "select *,  jenis_barang.nama_barang " +
                     "from timbangan left join jenis_barang on timbangan.id_barang=jenis_barang.id_barang " +
                     "where timbangan.hapus = '1' " +
                     "and timbangan.tanggal_masuk = curdate()" +
                     "order by timbangan_id desc";
//        System.out.println(sql);
        Object data[][] = this.res(sql);
        return data;
        
    }
    
    public Object[][] dataTable(String nopol, String DS, String DE)
    {
               
        String add = "";
        
        if(!nopol.isEmpty())
        {
            add+=" and timbangan.no_pol like '%"+nopol+"%' ";
        }
        if(!DS.isEmpty() && !DE.isEmpty())
        {
            add+=" and timbangan.tanggal_masuk between '"+DS+"' and '"+DE+"' ";
        }
        
        String sql = "select *,  jenis_barang.nama_barang " +
                     "from timbangan left join jenis_barang on timbangan.id_barang=jenis_barang.id_barang " +
                     "where timbangan.hapus = '1' " +add+
                     "order by timbangan_id desc";
//        System.out.println(sql);
        Object data[][] = this.res(sql);
        
        return data;
        
    }
    
    public Object[][] dataTable(String nopol, String id_barang, String Print, String DS, String DE)
    {
               
        String add = "";
        
        if(!nopol.isEmpty())
        {
            add+=" and timbangan.no_pol like '%"+nopol+"%' ";
        }
        
        if(!id_barang.isEmpty())
        {
            add+=" and timbangan.id_barang = '"+id_barang+"' ";
        }
        
        if(!Print.isEmpty())
        {
            add+=" and timbangan.print = '"+Print+"' ";
        }
        
        if(!DS.isEmpty() && !DE.isEmpty())
        {
            add+=" and timbangan.tanggal_masuk between '"+DS+"' and '"+DE+"' ";
        }
        
        String sql = "select *,  jenis_barang.nama_barang " +
                     "from timbangan left join jenis_barang on timbangan.id_barang=jenis_barang.id_barang " +
                     "where timbangan.hapus = '1' " +add+
                     "order by timbangan_id desc";
        System.out.println(sql);
        Object data[][] = this.res(sql);
        
        return data;
        
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Object[] dataTable(int id)
    {
               
        String sql = "select * " +
                     "from timbangan " +
                     "where hapus = '1' " +
                     "and timbangan_id='"+id+"'" +
                     "order by timbangan_id desc";
        
        Object data[] = this.res2(sql);
        
        return data;
        
    }
    
    public int getTicket()
    {
        String sql = "select max(timbangan_id) as tck "
                + "from timbangan ";
        
        Object[] data = this.res2(sql);
//        System.out.println(data[0]);
        if(data[0]==null)
            return 0;
        else
            return Integer.parseInt(data[0].toString());
    }
    
    public int getTicketBarang(Object IdBarang)
    {
        String sql = "select count(timbangan_id) as tck "
                + "from timbangan "
                + "where id_barang='"+IdBarang.toString()+"'";
//        System.out.println(sql);
        Object[] data = this.res2(sql);
//        System.out.println(data[0]);
        if(data[0]==null)
            return 0;
        else
            return Integer.parseInt(data[0].toString());
    }
    
    public int getCountTempTicket(Object tiket)
    {
        String sql = "select count(tiket_number) as tck "
                + "from tiket "
                + "where tiket_number='"+tiket.toString()+"' and hapus='1'";
        
         Object[] data = this.res2(sql);
//        System.out.println(data[0]);
        if(data[0]==null)
            return 0;
        else
            return Integer.parseInt(data[0].toString());
    }
    
    public int saveTempTiket(Object tiket)
    {
        if(!tiket.toString().isEmpty())
        {
            String sql = "INSERT INTO tiket" +
                    "(" +
                        "tiket_number," +
                        "created_by," +
                        "create_date," +
                        "modified_by," +
                        "modify_date" +
                 ") VALUES"+
                 "("+
                        "'"+tiket.toString()+"'," +
                        Session.User.getUserId()+"," +
                        "now()," +
                        Session.User.getUserId()+"," +
                        "now())" ;
            
            int ret = this.querySave(sql);
            
            return ret;
        }
        
        return 0;
    }
    
    public boolean removeTempTiket(Object tiket)
    {
        if(!tiket.toString().isEmpty())
        {
            String sql = "update tiket set "+
                        "hapus='0'," +
                        "modified_by="+Session.User.getUserId()+"," +
                        "modify_date=now() "+
                        "where tiket_number='" +tiket.toString()+"'";
            boolean ret = this.queryUpdate(sql);
            
            return ret;
        }
        
        
        
        return false;
    }
    
    public boolean removeTempTiket(Object tiket, String flag)
    {
        if(!tiket.toString().isEmpty())
        {
            String sql = "update tiket set "+
                        "hapus='"+flag+"'," +
                        "modified_by="+Session.User.getUserId()+"," +
                        "modify_date=now() "+
                        "where tiket_number='" +tiket.toString()+"'";
            boolean ret = this.queryUpdate(sql);
            
            return ret;
        }
        
        
        
        return false;
    }
    
    public Object[][] NamaBarang()
    {
        String sql = "select * from jenis_barang where hapus = '1'";
        
        Object[][] ret = this.res(sql);
        
        return ret;
    }
    
    public int saveData(java.util.HashMap data)
    {
        if(data.isEmpty()==false)
        {
            String sql = "INSERT INTO timbangan" +
                    "(" +
                        "tanggal_masuk," +
                        "tiket," +
                        "no_pol," +
                        "relasi," +
                        "id_barang," +
                        "nama_supir," +
                        "berat_gross," +
                        "jam_masuk," +
                        "catatan," +
                        "po," +
                        "sj," +
                        "created_by," +
                        "create_date," +
                        "modified_by," +
                        "modify_date," +
                        "timbang_in"+
                 ") VALUES"+
                 "("+
                        "'"+data.get("txtTanggal").toString()+"'," +
                        "'"+data.get("txtTiket").toString()+"'," +
                        "'"+data.get("txtNoPol").toString()+"'," +
                        "'"+data.get("txtRelasi").toString()+"'," +
                        "'"+data.get("cmbNamaBarang").toString()+"'," +
                        "'"+data.get("txtSupir").toString()+"'," +
                        "'"+data.get("txtGross").toString()+"'," +
                        "'"+data.get("txtJamMasuk").toString()+"'," +
                        "'"+data.get("txtKeterangan").toString()+"'," +
                        "'"+data.get("txtPo").toString()+"'," +
                        "'"+data.get("txtSj").toString()+"'," +
                        Session.User.getUserId()+"," +
                        "now()," +
                        Session.User.getUserId()+"," +
                        "now(),"+
                        "'"+data.get("timbangan_in").toString()+"')";
            
            int ret = this.querySave(sql);
            
            return ret;
        }
        
        
        
        return 0;
    }
    
    public boolean updateData(java.util.HashMap data)
    {
        if(data.isEmpty()==false)
        {
            
            String sql = "update timbangan set "+
                        "tanggal_keluar='"+data.get("txtTanggalKeluar").toString()+"'," +
                        "tiket='"+data.get("txtTiket").toString()+"'," +
                        "relasi='"+data.get("txtRelasi").toString()+"'," +
                        "nama_supir='"+data.get("txtSupir").toString()+"'," +
                        "id_barang='"+data.get("cmbNamaBarang").toString()+"'," +
                        "berat_gross='"+data.get("txtGross").toString()+"'," +
                        "berat_tara='"+data.get("txtTara").toString()+"'," +
                        "berat_netto='"+data.get("txtNetto").toString()+"'," +
                        "jam_keluar='"+data.get("txtJamKeluar").toString()+"'," +
                        "catatan='"+data.get("txtKeterangan").toString()+"'," +
                        "sj='"+data.get("txtSj").toString()+"'," +
                        "po='"+data.get("txtPo").toString()+"'," +
                        "type='"+data.get("InOut").toString()+"'," +
                        "print='"+data.get("print").toString()+"'," +
                        "timbang_out='"+data.get("timbangan_out").toString()+"'," +
                        "modified_by="+Session.User.getUserId()+"," +
                        "modify_date=now() "+
                        "where timbangan_id=" +data.get("id").toString();
            boolean ret = this.queryUpdate(sql);
            
            return ret;
        }
        
        
        
        return false;
    }
    
    public boolean updateBatal(int id, String Note)
    {
        if(!Note.equals(""))
        {
            String sql = "update timbangan set "+
                        "alasan='"+Note+"'," +
                        "hapus='2'," +
                        "modified_by="+Session.User.getUserId()+"," +
                        "modify_date=now() "+
                        "where timbangan_id=" +String.valueOf(id);
            boolean ret = this.queryUpdate(sql);
            
            return ret;
        }
        return false;
    }
    
}
