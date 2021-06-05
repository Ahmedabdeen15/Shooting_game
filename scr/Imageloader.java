package scr;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imageloader {
    public static BufferedImage loadImage(String path) {
        try {
            System.out.println(path + "\n" + Imageloader.class.getResource(path));
            return ImageIO.read(Imageloader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
