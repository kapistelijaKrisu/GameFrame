package UI;

import gameframe.MainControl;
import messages.Error;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Window extends JPanel {

    private static final long serialVersionUID = 1L;

    private JFrame frame;
    private MainControl game;
    private final int width, height;
    private float scale;

    public Window(int width, int height, float scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    private void initFrame() {

        if (game == null) {
            Error.printError(-1, "window doesnt have a game");
            return;
        }

        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);

        frame = new JFrame(game.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Input input = new Input();
        frame.add(this);
        

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        frame.addKeyListener(input);
        
        frame.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (game == null) {
            Error.printError(-1, "window doesnt have a game");
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(scale / 1, scale / 1);
        game.draw(g);
    }

    @Override
    public void resize(int width, int height) {  
        if (frame == null) {
            Error.printError(-2, "window has not been inited");
            return;
        }
        
        frame.setVisible(false);
        frame.setResizable(true);

        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public void setGameToDraw(MainControl game) {
        this.game = game;
        initFrame();
    }

    public boolean isReadyToDraw() {
        return game != null;
    }

    public void setScale(float scale) {
        this.scale = scale;      
    }
    
    

}
