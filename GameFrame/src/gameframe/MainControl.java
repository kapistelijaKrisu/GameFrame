package gameframe;

import UI.Input;
import UI.Window;
import java.awt.Graphics;
import java.util.ArrayList;
import utils.FPSLimiter;

public abstract class MainControl implements Runnable {

    private final FPSLimiter limiter;
    protected final Window window;
    private final String name;
    private boolean running;
    private final boolean loopable;

    private final ArrayList<State> states;
    private int currentState;

    //img assets
    //sound assets
    //object
    //map
    //fix input
    //make resize to adjust scale
    public MainControl(Window window, String name, float fps, boolean loopingGame) {
        this.states = new ArrayList<>();
        limiter = new FPSLimiter(fps);
        currentState = 0;
        running = false;
        this.name = name;
        this.loopable = loopingGame;
        this.window = window;
    }

    protected abstract boolean initGame();

    public abstract void save();

    @Override
    public void run() {
        if (running) {
            return;
        }
        if (launch()) {

            if (loopable) {
                loop();
            } else {
                drawLoop();
            }
        }
    }

    private void loop() {
        while (running) {
            limiter.begin();
            updateCurrentState();
            drawGame();
            Input.update();
            limiter.end();
        }
    }

    private void drawLoop() {
        while (running) {
            limiter.begin();
            drawGame();
            limiter.end();
        }
    }

    private boolean launch() {
        if (window == null) {
            messages.Error.printError(-10, "window is null");
            return false;
        }
        window.setGameToDraw(this);
        if (!initGame()) {
            messages.Error.printError(-10, "init failed");
            return false;
        }
        if (states.size() == 0) {
            messages.Error.printError(-10, "no states in game");
            return false;
        }
        running = true;
        return true;
    }

    public void exit() {
        running = false;
        // exit?
    }

    private synchronized void updateCurrentState() {
        states.get(currentState).update();
    }

    private synchronized void drawGame() {
        if (window != null) {
            window.repaint();
        } else {
            messages.Error.printError(-10, "no window");
        }
    }

    public void draw(Graphics g) {
        states.get(currentState).draw(g);
    }

    public void setScale(float scale) {
        if (window == null) {
            messages.Error.printError(-10, "no window");
            return;
        }
        window.setScale(scale);
        Input.setScale(1 / scale);
    }

    public void resizeWindow(int width, int height) {
        if (window == null) {
            messages.Error.printError(-10, "no window");
            return;
        }
        window.resize(width, height);

    }

    protected void addState(State state) {
        states.add(state);
    }

    //setters & getters
    public String getName() {
        return name;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getCurrentState() {
        return currentState;
    }
}
