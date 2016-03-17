/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbangantruckindahjaya.setting;

import java.awt.Graphics;
import java.awt.Image;
//import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
//import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author wanthook
 */
public class BackgroundPanel extends JPanel implements ImageObserver
{

    private Image img;
    
    public BackgroundPanel(String path, String protocol)
    {
        try 
        {
            img = ImageIO.read(new URL(getClass().getResource(path),protocol));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
            super.paintComponent(g);
            g.drawImage(img,0,0,getWidth(),getHeight(),this);
    }
    
}
