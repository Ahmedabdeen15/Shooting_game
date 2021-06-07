package scr;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball implements Runnable {
    private double xVelocity, yVelocity, x, y;
    private Random rand = new Random();
    private int weight;

    public Ball() {
        xVelocity = (double) rand.nextInt(3) + 1;
        yVelocity = (double) rand.nextInt(3) + 1;
        x = (double) rand.nextInt(200) + 20;
        y = (double) rand.nextInt(300) + 20;
    }

    public void run() {
        move();
    }

    public void draw(int weight, Graphics g) {
        this.weight = weight;
        setWeight(weight, g);
        g.fillOval((int) x - 10, (int) y - 10, 20, 20);
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
        if (y < 10)
            yVelocity = -yVelocity;
        if (y > 370)
            yVelocity = -yVelocity;
        if (x < 10)
            xVelocity = -xVelocity;
        if (x > 630)
            xVelocity = -xVelocity;
    }

    public void setWeight(int weight, Graphics g) {
        switch (weight) {
            case 0:
            case 1: {
                weight = 1;
                g.setColor(Color.red);
                break;
            }
            case 2: {
                g.setColor(Color.yellow);
                break;
            }
            case 3: {
                g.setColor(Color.blue);
                break;
            }
            default:
                System.out.println("Error");
                break;
        }
        this.weight = weight;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x - 10, (int) y - 10, 20, 20);
    }

    public int restartball() {
        xVelocity = (double) rand.nextInt(3) + 1;
        yVelocity = (double) rand.nextInt(3) + 1;
        x = (double) rand.nextInt(200) + 20;
        y = (double) rand.nextInt(300) + 20;
        return weight;
    }
}
