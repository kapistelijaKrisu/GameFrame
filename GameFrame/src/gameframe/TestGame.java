package gameframe;

import UI.Window;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TestGame extends MainControl {
    
    public static void main(String[] args) {
    /*    try {
      int width = 200, height = 200;

      // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
      // into integer pixels
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

      Graphics2D ig2 = bi.createGraphics();


      Font font = new Font("TimesRoman", Font.BOLD, 20);
      ig2.setFont(font);
      String message = "www.java2s.com!";
      FontMetrics fontMetrics = ig2.getFontMetrics();
      int stringWidth = fontMetrics.stringWidth(message);
      int stringHeight = fontMetrics.getAscent();
      ig2.setPaint(Color.black);
      ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

      File f = new File("C:\\Users\\Blackstorm\\Desktop\\whut.png");
      f.createNewFile();
      ImageIO.write(bi, "PNG", f);

      
    } catch (IOException ie) {
            System.out.println(ie);
      ie.printStackTrace();
    }*/
        TestGame g = new TestGame("asd");
        g.run();

    }

    public TestGame(String name) {
        super(new Window(500, 500, 1.0f), name, 1, true);
        
    }

    @Override
    public boolean initGame() {
        resizeWindow(800, 800);
        setScale(2);
        addState(new TestState(this));
        setCurrentState(0);
        return true;
     }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
}
