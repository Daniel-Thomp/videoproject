package com.daniel;

import java.awt.image.BufferedImage;

public class ColourVideo implements VideoProcess {
    String character = " ";
    String escapeCode = "\u001B[48;2;";
    int previousRGB;

    public String processFrame(BufferedImage image) {
        String output = "";
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                if (previousRGB == rgb) {
                    output += character;
                } else {
                    previousRGB = rgb;
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;

                    output += escapeCode + r + ";" + g + ";" + b + "m" + character;
                }
            }
            output += "\n";

        }
        return output;
    }
}
