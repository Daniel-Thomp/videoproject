package com.daniel;

import java.util.List;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BinaryVideoText implements VideoProcess {

    List<Character> text = new ArrayList<>();

    public BinaryVideoText(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        int c;
        while ((c = fr.read()) != -1) {
            char ch = (char) c;
            if (!Character.isWhitespace(ch)) {
                text.add(ch);
            }

        }
        fr.close();
    }

    public String processFrame(BufferedImage image) {
        int charNumber = 0;
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
                    output += " ";
                } else {
                    output += text.get(charNumber);
                }
                charNumber++;
            }
            output += "\n";

        }
        return output;
    }

}
