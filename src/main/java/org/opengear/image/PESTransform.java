package org.opengear.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class PESTransform {
    public static void main(String[] args) {

    }

    public static void rotateImage(String imagePath, double angle) {
        try {
            // 读取图像
            BufferedImage img = ImageIO.read(new File(imagePath));

            // 创建一个新的空白图像，用于保存旋转后的图像
            BufferedImage rotatedImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // 创建一个Graphics2D对象，并设置其AffineTransform属性，以实现旋转
            Graphics2D g2d = rotatedImg.createGraphics();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), img.getWidth() / 2.0, img.getHeight() / 2.0);
            g2d.setTransform(at);

            // 将原始图像画到旋转后的图像上
            g2d.drawImage(img, 0, 0, null);
            g2d.dispose();

            // 保存旋转后的图像
            ImageIO.write(rotatedImg, "png", new File("rotated.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
