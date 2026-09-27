package com.daniel;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Scanner;
import org.bytedeco.ffmpeg.global.avutil;

public class Main {
    static Scanner scanner;
    static VideoProcess process;

    public static void main(String[] args) throws IOException, AWTException {
        avutil.av_log_set_level(avutil.AV_LOG_QUIET);
        System.out.print("\u001B[0m");
        System.out.print("\033[H\033[2J");

        scanner = new Scanner(System.in);
        chooseProcess();
        chooseSource();
    }

    public static void chooseProcess() throws IOException, AWTException {
        System.out.println(
                "What process would you like to use? Enter 1,2 or 3 to select between: Binary Video, Binary Video (Text) or Colour");
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Please enter the character for white");
                String white = scanner.next();
                System.out.println("Please enter the character for black");
                String black = scanner.next();
                process = new BinaryVideo(white, black);
                break;
            case 2:
                System.out.println("Please enter the name of your text file");
                process = new BinaryVideoText("src/main/resources/" + scanner.next());
                break;
            case 3:
                process = new ColourVideo();
                break;
            default:
                System.out.println("Invalid input! Please enter a number between 1 and 3");
                chooseProcess();
                break;
        }
    }

    public static void chooseSource() throws AWTException {
        System.out.println(
                "What source would you like to use? Enter 1,2 or 3 to select between: Camera, mp4 file or Live screen");
        switch (scanner.nextInt()) {
            case 1:
                Utils.camera(process);
                break;
            case 2:
                System.out.println("Please enter the name of your video file");
                Utils.video("src/main/resources/" + scanner.next(), process);
                break;
            case 3:
                Utils.screen(process);
                break;
            default:
                System.out.println("Invalid input! Please enter a number between 1 and 3");
                chooseSource();
                break;
        }
    }

}
