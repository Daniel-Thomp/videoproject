package com.daniel;

import java.awt.image.BufferedImage;

public class BinaryVideo implements VideoProcess{

    String WHITE;
    String BLACK;
    public BinaryVideo(String white,String black){
        WHITE = white;
        BLACK = black;
    }

    public String processFrame(BufferedImage image) {
        String output = "";
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int grey = (r + g + b) / 3;

                if (grey < 128) {
                    output += BLACK;
                } else {
                    output += WHITE;
                }
            }
            output += "\n";

        }
        return output;
    }

    
}
