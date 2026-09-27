package com.daniel;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import org.bytedeco.javacv.Frame;

public class Utils {
    public static void video(String videoPath, VideoProcess process) {

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath)) {
            grabber.start();
            double framerate = grabber.getFrameRate();
            int totalFrames = grabber.getLengthInFrames();
            String[] convertedFrames = new String[totalFrames];

            Java2DFrameConverter converter = new Java2DFrameConverter();

            Frame frame;
            int frameNumber = 0;

            while ((frame = grabber.grabImage()) != null) {
                System.out.print("\r Processing video, Frame: " + frameNumber + " of " + totalFrames);
                BufferedImage image = converter.convert(frame);

                convertedFrames[frameNumber] = process.processFrame(
                        scaleDown(image, 160, 40));

                frameNumber++;
            }

            grabber.stop();
            converter.close();

            playBack(convertedFrames, framerate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage scaleDown(BufferedImage original, int targetWidth, int targetHeight) {
        BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();

        return scaledImage;
    }

    public static void playBack(String[] frames, double framerate) throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 0; i < frames.length; i++) {
            System.out.print("\033[H");
            System.out.print(frames[i]);
            Thread.sleep((int) ((1 / framerate) * 1000));
        }

    }

    public static void camera(VideoProcess process) {
        Webcam webcam = Webcam.getDefault();
        webcam.open();

        while (true) {
            System.out.print("\033[H");
            System.out.print(process.processFrame(scaleDown(webcam.getImage(), 160, 40)));
        }
    }

    public static void screen(VideoProcess process) throws AWTException {
        Robot robot = new Robot();
        Rectangle window = new Rectangle(0, 0, 1536, 860);

        while (true) {
            System.out.print("\033[H");
            System.out.print(process.processFrame(scaleDown(robot.createScreenCapture(window),
                    160, 40)));
        }
    }
}
