/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs.thongtin;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author MrCuong
 */
public class CircleLoading extends JPanel {
    int processValue;
    public  void UpdateProcess(int processValue)
    {
        this.processValue = processValue;
    }
    @Override
    public void paint(Graphics gp)
    {
        super.paint(gp);
        Graphics2D g2 =(Graphics2D) gp;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// render để khỏi bể graphics
        g2.translate(this.getWidth()/2, this.getHeight()/2); 
        g2.rotate(Math.toRadians(270));
        
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        Ellipse2D circle = new Ellipse2D.Float(0,0,120,120);
        
        //set vi trí xuất hiện của hinh tròn trên frame
        arc.setFrameFromCenter(new Point(0,0), new Point(120,120));
        circle.setFrameFromCenter(new Point(0,0), new Point(90,90));
        
        arc.setAngleStart(1);
        arc.setAngleExtent(-processValue*3.6);//360/100=3.6
        
        //vẽ, set màu cho hinh tròn màu đỏ bên ngoài
        g2.setColor(Color.RED);
        g2.draw(arc);
        g2.fill(arc);
        
        //vẽ, set mà cho hinh tròn màu trắng bên trong
        g2.setColor(Color.white);
        g2.draw(circle);
        g2.fill(circle);
        
        //set màu chữ font chữa, chiều của chữ(mặc định là đứng)
        g2.setColor(Color.red);
        g2.rotate(Math.toRadians(90));
        g2.setFont(new Font("MV Boli",Font.PLAIN, 50));
        
        //set vi tri chũ % bỏ cũng được
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(processValue+"%", gp);
        int x =(0-(int)r.getWidth())/2;
        int y =(0-(int)r.getHeight())/2+fm.getAscent();
        g2.drawString(processValue+"%", x, y);
    }
}
