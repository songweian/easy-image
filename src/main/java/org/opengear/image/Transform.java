package org.opengear.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class Transform {
    public static void processImage(String imagePath, double redPercent, double greenPercent, double bluePercent, double grayPercent, double angle) {
        try {
            // 读取图像
            BufferedImage img = ImageIO.read(new File(imagePath));

            // 调整RGB值
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    Color c = new Color(img.getRGB(i, j));
                    int red = (int) (c.getRed() * redPercent);
                    int green = (int) (c.getGreen() * greenPercent);
                    int blue = (int) (c.getBlue() * bluePercent);
                    Color newColor = new Color(red, green, blue);
                    img.setRGB(i, j, newColor.getRGB());
                }
            }

            // 转换为灰度图像
            BufferedImage grayImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    Color c = new Color(img.getRGB(i, j));
                    int red = c.getRed();
                    int green = c.getGreen();
                    int blue = c.getBlue();
                    int gray = (int) ((red + green + blue) / 3.0 * grayPercent);
                    Color newColor = new Color(gray, gray, gray);
                    grayImg.setRGB(i, j, newColor.getRGB());
                }
            }

            // 旋转图像
            BufferedImage rotatedImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = rotatedImg.createGraphics();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), img.getWidth() / 2.0, img.getHeight() / 2.0);
            g2d.setTransform(at);
            g2d.drawImage(grayImg, 0, 0, null);
            g2d.dispose();

            // 保存图像
            ImageIO.write(img, "jpg", new File("adjusted.jpg"));
            ImageIO.write(grayImg, "jpg", new File("gray.jpg"));
            ImageIO.write(rotatedImg, "jpg", new File("rotated.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
