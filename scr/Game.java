package scr;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.event.*;
import java.util.Random;

public class Game implements Runnable, KeyListener {

    private Display display;
    private Thread thread;
    private JFrame frame;
    private Gun gun;
    private Random rand = new Random();
    private boolean running = false;
    private int width, height;
    private String title;
    private BufferStrategy BFS;
    private Graphics graphics;
    private Ball balls[] = new Ball[8];
    private Thread t[] = new Thread[8];

    Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void initialize() {
        display = new Display(title, width, height);
        frame = new JFrame();
        display.frame.addKeyListener(this);
        // testImage = Imageloader.loadImage("2020100813003225511.png");
        for (int i = 0; i < 8; i++)
            balls[i] = new Ball();
        gun = new Gun();
    }

    public void tick() {
    }

    public void render() {
        BFS = display.getCanvas().getBufferStrategy();
        if (BFS == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        graphics = BFS.getDrawGraphics();
        graphics.clearRect(0, 0, width, height);
        // Draw
        // graphics.setColor(Color.red);
        // graphics.fillRect(10, 50, 50, 70);
        for (int i = 0; i < 8; i++) {
            balls[i].draw(3, graphics);
            t[i] = new Thread(balls[i]);
            t[i].start();
        }
        gun.draw(graphics);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BFS.show();
        graphics.dispose();
    }

    public void run() {
        initialize();
        while (running) {
            tick();
            render();
            gun.move();
        }
        stop();
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gun.setRight(true);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gun.setLeft(true);
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gun.setRight(false);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gun.setLeft(false);
        }
    }
}