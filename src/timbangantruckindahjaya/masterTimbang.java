/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timbangantruckindahjaya;

import java.util.Date;
import timbangantruckindahjaya.modul.mod_timbangan;
import timbangantruckindahjaya.modul.mod_user;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author taufiq
 */
public class masterTimbang extends javax.swing.JFrame {

    /**
     * Creates new form masterTimbang
     */    
    Object[][] mod, NamaBarang;
    DefaultTableModel tableModel;
    private formTimbang fT;
    private formTimbang2 fT2;
    private rubahPassword rP;
    
    private String DS, DE, nopol, print, barang;
    
    private static final String[] headTable = {
                        "Tanggal Masuk",
                        "Tanggal Keluar", 
                        "Jam Masuk", 
                        "Jam Keluar",
                        "Tiket", 
                        "Truk", 
                        "Relasi", 
                        "Nama Barang", 
                        "Berat Gross",
                        "Berat Tara", 
                        "Timbang IN",
                        "Timbang OUT",
                        "Print",
                        "Last Update"
                    };
    
    private final mod_timbangan modTimbangan = new mod_timbangan();
    
    public masterTimbang(String id) {
        this.setId(id);
        initComponents();
        
        setNamaBarang();
        
        this.refresh();
        
        this.setLoginLabel();
        this.setLocationRelativeTo(null);
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }
    
    public void set_ds(String p)
    {
        this.DS = p;
    }
    
    public void set_de(String p)
    {
        this.DE = p;
    }
    
    public void set_nopol(String p)
    {
        this.nopol = p;
    }
    
    public void set_print(String p)
    {
        this.print = p;
    }
    
    public void set_barang(String p)
    {
        this.barang = p;
    }
    
    public String get_ds()
    {
        return this.DS;
    }
    
    public String get_de()
    {
        return this.DE;
    }
    
    public String get_nopol()
    {
        return this.nopol;
    }
    
    public String get_print()
    {
        return this.print;
    }
    
    public String get_barang()
    {
        return this.barang;
    }
    
    private Object[][] data()
    {
        Object[][] ret = null;
        
        this.set_nopol(txtNoPol.getText());
        this.set_print(this.cmbStatusPrint.getSelectedItem().toString());
        this.set_barang(this.get_cmb_nama_barang().toString());
        this.set_ds(((javax.swing.JTextField)this.txtDS.getDateEditor().getUiComponent()).getText());
        this.set_de(((javax.swing.JTextField)this.txtDA.getDateEditor().getUiComponent()).getText());
        System.out.println(this.get_ds());
        mod = modTimbangan.dataTable(this.get_nopol(), this.get_barang(), (this.get_print().equals("SUDAH")?"1":(this.get_print().equals("BELUM")?"0":"")), this.get_ds(), this.get_de());
        
        if(mod.length>0)
        {
            ret = new Object[mod.length][mod[0].length];
            
            for(int i=0 ; i<mod.length ; i++)
            {
                ret[i][0] = mod[i][0]; //Tanggal Masuk
                ret[i][1] = mod[i][1]; //Tanggal Keluar
                ret[i][2] = mod[i][12]; //jam masuk
                ret[i][3] = mod[i][13]; //jam keluar
                ret[i][4] = mod[i][2]; //tiket
                ret[i][5] = mod[i][3]; //truk
                ret[i][6] = mod[i][4]; //relasi
                ret[i][7] = mod[i][28]; //nama barang
                ret[i][8] = mod[i][7]; //berat gross
                ret[i][9] = mod[i][8]; //berat tara
                ret[i][10] = mod[i][22]; //timbang in
                ret[i][11] = mod[i][23]; //timbang out
                ret[i][12] = (mod[i][16].equals("0")?"Belum":"Sudah"); //print
                ret[i][13] = mod[i][27]; //lastupdate
            }
        }
        return ret;
    }
    
   
    private void set_table_model(Object[][] data)
    {
        this.jTableData.setModel(new DefaultTableModel(
                    data,
                    headTable
                ) {
                    boolean[] canEdit = new boolean [] 
                    {
                        false, 
                        false, 
                        false, 
                        false, 
                        false, 
                        false, 
                        false, 
                        false, 
                        false, 
                        false, 
                        false,
                        false, 
                        false,
                        false
                    };

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) 
                    {
                        return canEdit [columnIndex];
                    }
                });
    }
    
    public void refresh()
    {
        Object[][] objData = this.data();
        this.set_table_model(objData);
    }
    
    private void setNamaBarang()
    {
        NamaBarang = modTimbangan.NamaBarang();
        cmbNamaBarang.addItem("ALL");
        for (Object[] NamaBarang1 : NamaBarang) 
        {
            cmbNamaBarang.addItem(NamaBarang1[0]);
        }
        
        AutoCompleteDecorator.decorate(cmbNamaBarang);
    }
    
    private Object get_cmb_nama_barang()
    {
        int idx = this.cmbNamaBarang.getSelectedIndex()-1;
        
        if(idx<0)
        {
            return "";
        }
        else
        {
            return NamaBarang[idx][1];
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableData = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNoPol = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDS = new com.toedter.calendar.JDateChooser();
        txtDA = new com.toedter.calendar.JDateChooser();
        cmbStatusPrint = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbNamaBarang = new javax.swing.JComboBox();
        jToolBar1 = new javax.swing.JToolBar();
        lblLogin = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        cmdTimbang = new javax.swing.JButton();
        cmdTimbang2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        cmdRefresh = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Master Timbangan - PT. Sipinmill Indah Industry");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("List Timbangan"));

        jTableData.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTableData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableData);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("No. Polisi");

        txtNoPol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoPolActionPerformed(evt);
            }
        });
        txtNoPol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNoPolKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoPolKeyReleased(evt);
            }
        });

        jLabel3.setText("Tanggal Awal");

        jLabel4.setText("Tanggal Akhir");

        txtDS.setDate(new Date());
        txtDS.setDateFormatString("yyyy-MM-dd");

        txtDA.setDate(new Date());
        txtDA.setDateFormatString("yyyy-MM-dd");

        cmbStatusPrint.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BELUM", "SUDAH", "ALL" }));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Status Print");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Jenis Barang");

        cmbNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamaBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoPol, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStatusPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDS, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDA, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtDS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNoPol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbStatusPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar1.setFloatable(false);

        lblLogin.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        lblLogin.setText("jLabel5");
        jToolBar1.add(lblLogin);

        jToolBar2.setFloatable(false);

        cmdTimbang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timbangantruckindahjaya/images/scale24.png"))); // NOI18N
        cmdTimbang.setMnemonic('T');
        cmdTimbang.setText("Timbang");
        cmdTimbang.setToolTipText("Tambah Timbangan");
        cmdTimbang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdTimbang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cmdTimbang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdTimbang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTimbangActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdTimbang);

        cmdTimbang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timbangantruckindahjaya/images/scale24.png"))); // NOI18N
        cmdTimbang2.setMnemonic('T');
        cmdTimbang2.setText("Timbang 2");
        cmdTimbang2.setToolTipText("Tambah Timbangan");
        cmdTimbang2.setFocusable(false);
        cmdTimbang2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdTimbang2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cmdTimbang2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdTimbang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTimbang2ActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdTimbang2);
        jToolBar2.add(jSeparator1);

        cmdRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timbangantruckindahjaya/images/refresh.png"))); // NOI18N
        cmdRefresh.setMnemonic('R');
        cmdRefresh.setText("Refresh");
        cmdRefresh.setToolTipText("Perbaharui List Timbangan");
        cmdRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdRefresh.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cmdRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdRefresh);
        jToolBar2.add(jSeparator3);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timbangantruckindahjaya/images/gembok.png"))); // NOI18N
        jButton2.setText("Rubah Password");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton2);
        jToolBar2.add(jSeparator2);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timbangantruckindahjaya/images/app_sii.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(439, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE)
        );

        jToolBar2.add(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void setLoginLabel()
    {
        this.lblLogin.setText("Selamat datang "+new mod_user().UserName(String.valueOf(Session.User.getUserId())));
    }
    
    private void setId(String id)
    {
        this.loginId = id;
    }
    
    
    
    private void cmdTimbangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTimbangActionPerformed
        // TODO add your handling code here:
        
        if(fT==null)
        {
            fT = new formTimbang(0);
            fT.setVisible(true);
        }
        else if(!fT.isVisible())
        {
            fT = new formTimbang(0);
            fT.setVisible(true);
        }
        
    }//GEN-LAST:event_cmdTimbangActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new timbangantruckindahjaya.func.Log().InsertLog("LOGOUT", "Aplikasi berhasil ditutup");
    }//GEN-LAST:event_formWindowClosing

    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        // TODO add your handling code here:
        this.refresh();
    }//GEN-LAST:event_cmdRefreshActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(rP==null)
        {
            rP = new rubahPassword();
            rP.setVisible(true);
        }
        else if(!rP.isVisible())
        {
            rP = new rubahPassword();
            rP.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTableDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDataMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2)
        {
            int row = jTableData.getSelectedRow();

            int id = Integer.parseInt(mod[row][16].toString());
            new formTimbang(id).setVisible(true);
        }
    }//GEN-LAST:event_jTableDataMouseClicked

    private void txtNoPolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoPolKeyReleased
        // TODO add your handling code here:
        this.txtNoPol.setText(this.txtNoPol.getText().toUpperCase());
    }//GEN-LAST:event_txtNoPolKeyReleased

    private void txtNoPolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoPolKeyPressed
        // TODO add your handling code here:
//        System.out.println(evt.getKeyCode());
        if(evt.getKeyCode()==10)
        {
            this.refresh();
        }
    }//GEN-LAST:event_txtNoPolKeyPressed

    private void txtNoPolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoPolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoPolActionPerformed

    private void cmbNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNamaBarangActionPerformed

    private void cmdTimbang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTimbang2ActionPerformed
        // TODO add your handling code here:
        if(fT2==null)
        {
            fT2 = new formTimbang2(0);
            fT2.setVisible(true);
        }
        else if(!fT2.isVisible())
        {
            fT2 = new formTimbang2(0);
            fT2.setVisible(true);
        }
    }//GEN-LAST:event_cmdTimbang2ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbNamaBarang;
    private javax.swing.JComboBox cmbStatusPrint;
    private javax.swing.JButton cmdRefresh;
    private javax.swing.JButton cmdTimbang;
    private javax.swing.JButton cmdTimbang2;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    public javax.swing.JTable jTableData;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblLogin;
    private com.toedter.calendar.JDateChooser txtDA;
    private com.toedter.calendar.JDateChooser txtDS;
    private javax.swing.JTextField txtNoPol;
    // End of variables declaration//GEN-END:variables
    private String loginId;
}
