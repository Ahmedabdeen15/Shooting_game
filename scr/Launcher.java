package scr;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("first game in java");
        Game game = new Game("first game in java", 640, 400);
        game.start();
    }
}