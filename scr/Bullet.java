package scr;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
    private double x, y;

    public Bullet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {
        // y=mx+b
        /*
         * double tempx = this.x, tempy = this.y, slope, b; yBullet = this.y; slope = y
         * - this.y / (x + (int) this.x); b = tempy - tempx * slope; do {/*
         * 
         * tempy = tempx * slope + b;
         * 
         * yBullet -= 1;
         * 
         * } while (yBullet >= x);
         * 
         * // System.out.println("pew pew" + x + " , " + y);
         */
        y -= 5;

    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int) x, (int) y, 5, 10);

    }

}
