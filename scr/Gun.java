package scr;

import java.awt.Graphics;
import java.awt.Color;

public class Gun {
    private double xVelocity, x, GRAVITY = 0.94;
    private boolean left, right;
    private int y;
    // private Image testImage;

    public Gun() {
        xVelocity = 0;
        x = 300;
        y = 380;
        left = false;
        right = false;
    }

    public void run() {
        move();
    }

    public double getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, y, 60, 20);
    }

    public void move() {
        // System.out.println(xVelocity);
        if (left) {
            xVelocity -= 2;
        } else if (right) {
            xVelocity += 2;
        } else if (!right && !left) {
            xVelocity *= GRAVITY;
        }
        if (xVelocity >= 5) {
            xVelocity = 5;
        } else if (xVelocity <= -5) {
            xVelocity = -5;
        }

        x += xVelocity;
        if (x <= 0) {
            x = 0;
        } else if (x >= 580) {
            x = 580;
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
