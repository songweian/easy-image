package org.opengear.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PES {
    public static void main(String[] args) {
        compressImage("path_to_your_image", 0.5f);
    }

    public static void compressImage(String imagePath, float quality) {
        try {
            // 读取图像
            BufferedImage img = ImageIO.read(new File(imagePath));

            // 获取一个ImageWriter用于jpg格式
            ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();

            // 配置压缩参数
            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            jpgWriteParam.setCompressionQuality(quality);

            // 写入压缩的图片数据
            try (ImageOutputStream outputStream = ImageIO.createImageOutputStream(new File("compressed.jpg"))) {
                jpgWriter.setOutput(outputStream);
                jpgWriter.write(null, new javax.imageio.IIOImage(img, null, null), jpgWriteParam);
            }

            // 清理
            jpgWriter.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
