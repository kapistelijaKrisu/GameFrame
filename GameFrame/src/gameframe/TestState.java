package gameframe;

import UI.Input;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class TestState extends State {

    private int x = 0;

    public TestState(MainControl game) {
        super(game);
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        x++;

        if (Input.mouseIsHeld()) {
            System.out.println("asd");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(0, 0, 55, 55);
        g.fillRect(Input.getCoords().x, Input.getCoords().y, 11, 11);
        System.out.println(Input.getCoords().x + " " + Input.getCoords().y);
    }

}
