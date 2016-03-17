/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timbangantruckindahjaya;

import javax.swing.UIManager;

/**
 *
 * @author taufiq
 */
public class TimbanganTruckIndahJaya {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new loginForm().setVisible(true);
//            new formTimbang(0).setVisible(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
