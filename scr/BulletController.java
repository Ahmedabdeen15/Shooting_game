package scr;

import java.awt.Graphics;
import java.util.LinkedList;

public class BulletController {

    private LinkedList<Bullet> bulletlist = new LinkedList<Bullet>();
    private Bullet tempbullet;
    private Ball balls[];

    BulletController(Ball balls[]) {
        this.balls = balls;
    }

    public void tick() {
        for (int i = 0; i < bulletlist.size(); i++) {
            tempbullet = bulletlist.get(i);

            tempbullet.tick();
            // check if bullet hit a balls

        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < bulletlist.size(); i++) {
            tempbullet = bulletlist.get(i);

            tempbullet.render(g);
        }
    }

    void addBullet(Bullet block) {
        bulletlist.add(block);
    }

    void removeBullet(Bullet block) {
        bulletlist.remove(block);
    }
}