package scr;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.event.*;
import java.util.Random;

public class Game implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private Display display;
    private Thread thread, gunThread;
    private Gun gun;
    private Random rand = new Random();
    private boolean running = false;
    private int width, height, randomarr[], score = 0;
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
            t[i] = new Thread(balls[i]);
            t[i].start();
        }
        gun = new Gun();
        gunThread = new Thread(gun);
        gunThread.start();
        bullet = new BulletController(balls);

    }

    boolean bullet_finished = false;

    public void tick() {
        if (bullet.getBullet_limit() > 0 || !bullet_finished) {
            for (int i = 0; i < 8; i++) {
                balls[i].move();
            }
            bullet_finished = bullet.tick();
            gun.move();
            score = bullet.getScore();
        } else {
            bullet.killAll();
        }

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
        graphics.setColor(Color.pink);
        graphics.fillOval(mouseX, mouseY, 10, 10);
        if (bullet.getBullet_limit() > 0 || !bullet_finished) {
            for (int i = 0; i < 8; i++) {
                balls[i].draw(randomarr[i], graphics);
            }
            gun.draw(graphics);
            bullet.render(graphics);
            graphics.setColor(Color.black);
            graphics.drawString("Score= " + Integer.toString(score), 50, 30);
            graphics.drawString("Bullet left= " + Integer.toString(bullet.getBullet_limit()), 550, 30);
        } else {

            graphics.setColor(Color.red);
            graphics.drawString("GAME OVER!", 280, 190);

            graphics.setColor(Color.black);
            graphics.fillRect(280, 220, 80, 20);

            graphics.setColor(Color.white);
            graphics.drawString("Retry", 305, 235);
        }
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
            if (bullet.getBullet_limit() > 0) {
                bullet.addBullet(new Bullet(gun.getX(), gun.getY()));
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    int mouseX, mouseY;

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        graphics.setColor(Color.pink);
        graphics.fillOval(mouseX, mouseY, 10, 10);
        // graphics.setColor(Color.black);
        // graphics.drawString(Integer.toString(e.getX()) + "," +
        // Integer.toString(e.getY()), 50, 30);
        // System.out.println(e.getY());
        // System.out.println(e.getX());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (bullet.getBullet_limit() > 0) {
            bullet.addBullet(new Bullet(gun.getX(), gun.getY()));
        } else {
            if (mouseX > 280 && mouseX < 360 && mouseY > 220 && mouseY < 240) {
                bullet.setScore(0);
                bullet.setbullet_limit(10);
            }
        }
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