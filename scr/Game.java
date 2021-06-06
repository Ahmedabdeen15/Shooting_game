package scr;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.Random;

public class Game implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private Display display;
    private Thread thread, gunThread;
    private Gun gun;
    private Random rand = new Random();
    private boolean running = false;
    private int width, height, randomarr[];
    private String title;
    private BufferStrategy BFS;
    private Graphics graphics;
    private Ball balls[] = new Ball[8];
    private Thread t[] = new Thread[8];
    private BulletController bullet;

    Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

    }

    public void initialize() {
        display = new Display(title, width, height);

        display.getFrame().addMouseListener(this);
        display.getFrame().addMouseMotionListener(this);
        display.getFrame().addKeyListener(this);
        display.getCanvas().addKeyListener(this);
        display.getCanvas().addMouseListener(this);
        display.getCanvas().addMouseMotionListener(this);

        randomarr = new int[8];
        for (int i = 0; i < 8; i++) {
            balls[i] = new Ball();
            randomarr[i] = rand.nextInt(4);
        }
        gun = new Gun();
        gunThread = new Thread(gun);
        gunThread.start();
        bullet = new BulletController(balls);

    }

    public void tick() {
        bullet.tick();
        gun.move();
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
        for (int i = 0; i < 8; i++) {
            balls[i].draw(randomarr[i], graphics);
            t[i] = new Thread(balls[i]);
            t[i].start();
        }
        gun.draw(graphics);
        graphics.setColor(Color.pink);
        graphics.fillRect(mouseY, mouseX, 10, 10);
        bullet.render(graphics);
        try {
            Thread.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BFS.show();
        graphics.dispose();
    }

    public void run() {
        initialize();
        while (running) {
            render();
            tick();
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
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullet.addBullet(new Bullet(gun.getX(), gun.getY()));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    int mouseX, mouseY;

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getY();
        mouseY = e.getX();
        graphics.setColor(Color.pink);
        graphics.fillRect(mouseY, mouseX, 10, 10);
        // System.out.println(e.getY());
        // System.out.println(e.getX());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        bullet.addBullet(new Bullet(gun.getX(), gun.getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}