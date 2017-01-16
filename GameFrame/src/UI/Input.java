package UI;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayDeque;

public class Input implements MouseListener, MouseMotionListener, KeyListener {

    //fix key is down

    private static final int mouseHeld = 0, mouseClicked = 1, mouseReleased = 2;

    private static float scale = 1;
    private static Point coords = new Point(0, 0);

    private static boolean mousePressed = false;
    private static int mouseStatus = mouseReleased;

    private static final int SUM_OF_KEYS = 524;
    private static final boolean[] keys = new boolean[SUM_OF_KEYS];
    private static final boolean[] keysBeenDown = new boolean[SUM_OF_KEYS];
    private static ArrayDeque<KeyEvent> toRelease = new ArrayDeque<>();

    public static void update() {
        while (!toRelease.isEmpty()) {
            keys[toRelease.poll().getKeyCode()] = false;
        }
        for (int i = 0; i < keys.length; i++) {
            keysBeenDown[i] = keys[i];
        }
        if (!mousePressed) {
            mouseStatus = mouseReleased;
        } else if (mousePressed && mouseStatus == mouseClicked) {
            mouseStatus = mouseHeld;
        }
    }

    public static void setScale(float scale) {
        Input.scale = scale;
    }

    public static boolean mouseIsClicked() {
        return mouseStatus == mouseClicked;
    }

    public static boolean mouseIsHeld() {
        return mouseStatus == mouseHeld;
    }

    public static Point getCoords() {
        return coords.getLocation();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        mousePressed = true;
        if (mouseStatus == mouseReleased) {
            mouseStatus = mouseClicked;
        }
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
        e.consume();
    }

    public static boolean keyIsClicked(int key) {
        return (keys[key] && !keysBeenDown[key]);
    }

    public static boolean keyIsDown(int key) {
        return (keys[key]);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        coords = e.getPoint();
        coords.setLocation(scale * e.getX(), scale * e.getY());
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        coords = e.getPoint();
        coords.setLocation(scale * e.getX(), scale * e.getY());
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toRelease.add(e);
        e.consume();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

}
