package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {

    private static final Logger logger = LogManager.getLogger();

    private static char[][] maze;

    public static void config(String[] args) throws Exception {
        Options options = new Options();
        CommandLineParser commandLineParser = new DefaultParser();

        options.addOption("i", true, "FileName with option reacting to -i");
        options.addOption("p", true, "Path with option reacting to -p");

        CommandLine cmd = commandLineParser.parse(options, args);

        // Sets default filePath to that of the straight.maz.txt
        String filePath = cmd.getOptionValue("i", "examples/straight.maz.txt");

        logger.info("**** Reading the maze from file " + filePath);

        // Parses the maze and converts it into a 2D array
        MazeCreator mazeCreator = new MazeCreator(filePath);
        maze = mazeCreator.createMaze(filePath);

        // Prints out the maze.
        for (char[] rows : maze) {
            System.out.println(Arrays.toString(rows));
        }

        boolean pathVerificationRequest = false;

        for (String argument : args) {
            if (argument.equals("-p")) {
                pathVerificationRequest = true;
                break;
            }
        }

        if (pathVerificationRequest) {
            String pathCheck = cmd.getOptionValue("p");
            VerifyPath verifyPath = new VerifyPath(maze, pathCheck);
            logger.info("Path Verification Requested");
            System.out.println("Path to be verified: " + pathCheck);

            try {
                System.out.println("Verification Result: " + verifyPath.pathVerified());
            } catch (Exception e) {
                System.out.println("Verification Result: FAIL");
                logger.error("PATH GOES OUT OF BOUNDS");
            }
        } else {
            PathFinder pathFinder = new PathFinder();

            int[] entrance = pathFinder.entrance(maze);
            System.out.println("Entrance [row, column]: " + Arrays.toString(entrance));

            int[] exit = pathFinder.exit(maze);
            System.out.println("Exit [row, column]: " + Arrays.toString(exit));

            logger.info("**** Computing path");

            // Print the canonical path
            try {
                String factorPath = pathFinder.factorizedPath(maze);
                System.out.println("The factorized path is:" + factorPath);
                
            } catch (Exception e) {
                logger.info("PATH NOT COMPUTED");
            }
        }
    }
    
    public char[][] parsedMaze() {
        return maze;
    }
}
