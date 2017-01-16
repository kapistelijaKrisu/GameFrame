
package gameframe;

import java.awt.Graphics;

public abstract class State {
    private final MainControl game;
    
    public State(MainControl game) {
        this.game = game;
    }
    
    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics g);
}
