package scr;

import java.awt.Graphics;
import java.util.LinkedList;

public class BulletController {

    private LinkedList<Bullet> bulletlist = new LinkedList<Bullet>();
    private Bullet tempbullet;
    private Ball balls[];
    private int score = 0, limit_of_bullets = 10;

    BulletController(Ball balls[]) {
        this.balls = balls;
    }

    public boolean tick() {
        boolean temp = false;
        for (int i = 0; i < bulletlist.size(); i++) {
            tempbullet = bulletlist.get(i);
            temp = tempbullet.tick();

            // check if bullet hit a balls
            for (Ball ball : balls) {
                if (ball.getBounds().intersects(tempbullet.getBounds())) {
                    limit_of_bullets++;
                    score += ball.restartball();

                }
            }
        }
        return temp;
    }

    public void render(Graphics g) {
        for (int i = 0; i < bulletlist.size(); i++) {
            tempbullet = bulletlist.get(i);

            tempbullet.render(g);
        }
    }

    void addBullet(Bullet block) {
        bulletlist.add(block);
        limit_of_bullets--;
    }

    void removeBullet(Bullet block) {
        bulletlist.remove(block);
    }

    int getScore() {
        return score;
    }

    public int getBullet_limit() {
        return limit_of_bullets;
    }

    void setScore(int score) {
        this.score = score;
    }

    void setbullet_limit(int limit_of_bullets) {
        this.limit_of_bullets = limit_of_bullets;
    }

    void killAll() {
        for (int i = 0; i < bulletlist.size(); i++) {
            tempbullet = bulletlist.get(i);
            tempbullet.tick();
        }
    }
}