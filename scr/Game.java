package scr;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class Game implements Runnable {

    private Display display;
    private Thread thread;

    private Image testImage;
    private boolean running = false;
    private int width, height;
    private String title;
    private BufferStrategy BFS;
    private Graphics graphics;

    Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

    }

    public void initialize() {
        display = new Display(title, width, height);
        testImage = Imageloader.loadImage("2020100813003225511.png");
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
        // graphics.clearRect(0, 0, width, height);
        // Draw
        graphics.setColor(Color.red);
        graphics.fillRect(10, 50, 50, 70);

        BFS.show();
        graphics.dispose();
    }

    public void run() {
        initialize();
        while (running) {
            tick();
            render();
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
}