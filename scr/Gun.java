package scr;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Gun implements Runnable {
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

    public double getX() {
        return x;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int) x - 30, y, 60, 20);
        // testImage = Imageloader.loadImage("2020100813003225511.png");

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
        if (x <= 30) {
            x = 30;
        } else if (x >= 610) {
            x = 610;
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

}
