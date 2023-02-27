
//*******************************************************************
//
//   File: NBody.java
//
//   Author: CS112      Email:  i
//
//   Class: NBody 
// 
//   Time spent on this problem: 
//   --------------------
//   
//      This program 
//
//*******************************************************************
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.IOException;

public class NBody {

    // set up panel
    static final int SIZE = 500;
    static DrawingPanel panel = new DrawingPanel(SIZE, SIZE);
    static Graphics2D g = panel.getGraphics();

    // enable double buffering
    static BufferedImage offscreen = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
    static Graphics2D osg = offscreen.createGraphics();

    // slider position
    public static final int slideStart = 100;
    public static final int slideEnd = 400;

    public static int slideX = 250;
    public static final int slideY = 450;
    public static final int circleRad = 10;

    // animation pause (in miliseconds)
    public static final int DELAY = 100;

    // music (2001 theme)
    public static final String MUSIC = "2001theme.wav";

    // background image
    public static final String BACKGROUND = "starfield.jpg";
    public static BufferedImage bgImage;

    // gravitational constant (N m^2 / kg^2)
    public static final double G = 6.67e-11;

    // parameters from command line
    public static double simDuration; // simulate from time 0 to simDuration (s)
    public static double baseTimeStep; // time quantum given by user input
    public static double timeStep; // time quantum used in simulation. can be updated by speed slider

    // parameters from .txt file
    public static int numBodies; // number of bodies (N)
    public static double universeRadius; // radius of universe (R)

    public static double[] rx; // x position (m)
    public static double[] ry; // y position (m)
    public static double[] vx; // x velocity (m/s)
    public static double[] vy; // y velocity (m/s)
    public static double[] mass; // mass (kg)
    public static String[] imageNames; // image file names
    public static BufferedImage[] images; // image objects

    public static void main(String[] args) {

        // check for number of arguments, give usage string
        if (args.length < 3) {
            printUsage();
            System.exit(1);
        }

        simDuration = Double.parseDouble(args[0]);

        baseTimeStep = Double.parseDouble(args[1]);
        timeStep = baseTimeStep;

        String universeFileName = args[2];

        // load BACKGROUND image
        loadBG();

        // load planets from file specified in the command line
        loadPlanets(universeFileName);

        // play audio file
        StdAudio.play(MUSIC);

        // Set up mouse listener for slider
        panel.onDrag((x, y) -> dragUpdate(x, y));

        // Run simulation
        runSimulation();

        // print final state of universe to standard output
        System.out.printf("%d\n", numBodies);
        System.out.printf("%.2e\n", universeRadius);
        for (int i = 0; i < numBodies; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    rx[i], ry[i], vx[i], vy[i], mass[i], imageNames[i]);
        }
    }

    public static void printUsage() {
        System.out.println("Usage:");
        System.out.println("java NBody <duration> <time step> <universe file>");
    }

    // load BACKGROUND image into the variable bgImage
    public static void loadBG() {
        // TO DO
    }

    // Read the planet file, create the parallel arrays, and load
    // their values from the file.
    public static void loadPlanets(String planetFileName) throws FileNotFoundException {
        // TO DO
        File planets = new File("3body.txt");
        Scanner planetReader = new Scanner(planets);
        

    }

    public static void runSimulation() {
        // run numerical simulation from 0 to simDuration
        // speed may vary if the slider updates timeStep
        for (double t = 0.0; t < simDuration; t += timeStep) {

            // the x- and y-components of force
            double[] fx = new double[numBodies];
            double[] fy = new double[numBodies];

            // calculate forces on each object
            // loop to set up fx and fy

            // draw background

            // draw each planet

            // draw slider (when you are ready)

            // copy from offscreen buffer to panel
            g.drawImage(offscreen, 0, 0, null);

            // pause
            panel.sleep(DELAY);
        }
    }

    // general scaling method
    public static double scale(double oldValue, double oldMin, double oldMax, double newMin, double newMax) {
        // TO DO (see formula in assignment)
    }

    // helper function since we have multiple places where you will scale the
    // universe to panel size
    public static int scaleToPanel(double oldValue) {
        return (int) (scale(oldValue, -universeRadius, universeRadius, 0, SIZE));
    }

    // handle mouse events
    public static void dragUpdate(int x, int y) {
        // TO DO

        // was the slider cicle grabbed?

        // if so, set slider position

        // and update time step size based on slider position

    }

    // draw the slider for this frame
    public static void drawSlider() {

        osg.setColor(Color.GRAY);

        // dashed slider line
        osg.drawLine(slideStart, slideY, slideEnd, slideY);
        for (int dashX = slideStart; dashX <= slideEnd; dashX += (slideEnd - slideStart) / 12) {
            osg.drawLine(dashX, slideY - 5, dashX, slideY + 5);
        }

        for (int dashX = slideStart; dashX <= slideEnd; dashX += (slideEnd - slideStart) / 4) {
            osg.drawLine(dashX, slideY - 10, dashX, slideY + 10);
        }

        // outlined slider "button"
        osg.fillOval(slideX - circleRad, slideY - circleRad, 2 * circleRad, 2 * circleRad);
        osg.setColor(Color.WHITE);
        osg.drawOval(slideX - circleRad, slideY - circleRad, 2 * circleRad, 2 * circleRad);
    }

}
