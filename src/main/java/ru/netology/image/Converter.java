package ru.netology.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;


public class Converter implements TextGraphicsConverter {
    TextColorSchema schema = new Schema();
    private double maxRatio;
    private int width;
    private int height;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        int newWidth;
        int newHeight;

        BufferedImage img = ImageIO.read(new URL(url));

        double ratioWH = (double) img.getWidth() / img.getHeight();
        double ratioHW = (double) img.getHeight() / img.getWidth();

        if (ratioWH > getMaxRatio()) {
            throw new BadImageSizeException(ratioWH, maxRatio);
        }
        if (ratioHW > getMaxRatio()) {
            throw new BadImageSizeException(ratioHW, maxRatio);
        }

        if (img.getHeight() > getMaxHeight() || img.getWidth() > getMaxWidth()) {
            double maxRatioImg = Math.max((img.getHeight() / getMaxHeight()), (img.getWidth() / getMaxWidth()));
            if (img.getHeight() > img.getWidth()) {
                newHeight = getMaxHeight();
                newWidth = (int) (img.getWidth() / maxRatioImg);
            } else {
                newWidth = getMaxWidth();
                newHeight = (int) (img.getHeight() / maxRatioImg);
            }
        } else {
            newHeight = img.getHeight();
            newWidth = img.getWidth();

        }
        System.out.println("ширина + высота " + newWidth +" "+ newHeight);

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);


        WritableRaster bwRaster = bwImg.getRaster();

       return stringBilder(charConvert(newHeight,newWidth, bwRaster));
    }
    public char[][] charConvert(int newHeight, int newWidth, WritableRaster bwRaster) {

        char[][] ch = new char[newHeight][newWidth];
        for (int w = 0; w < newWidth; w++) {
            for (int h = 0; h < newHeight; h++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                ch[h][w] = c;
            }
        }
        return ch;
    }
    public String stringBilder (char[][] ch){
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] ch1 : ch) {
            stringBuilder.append('\n');
            for (char ch2 : ch1) {
                stringBuilder.append(ch2).append(ch2);
            }
        }
        String string = stringBuilder.toString();
        return string;
    }


    @Override
    public void setMaxWidth(int width) {
        this.width = width;


    }

    public int getMaxWidth() {
        return width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;


    }

    public int getMaxHeight() {
        return height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;


    }

    public double getMaxRatio() {
        return maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;

    }
}
