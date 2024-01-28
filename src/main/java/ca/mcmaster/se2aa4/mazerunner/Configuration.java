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

        // Add the i and p options
        options.addOption("i", true, "FileName with option reacting to -i");
        options.addOption("p", true, "Path with option reacting to -p");

        CommandLine cmd = commandLineParser.parse(options, args);

        // Sets default filePath to that of the straight.maz.txt
        String filePath = cmd.getOptionValue("i", "examples/straight.maz.txt");

        logger.info("**** Reading the maze from file " + filePath);

        // Parses the maze and converts it into a 2D array
        maze = MazeCreator.createMaze(filePath);

        // Prints out the maze.
        for (char[] rows : maze) {
            System.out.println(Arrays.toString(rows));
        }

        // Executes the appropriate instructions based on whether the user has
        // entered the -p flag
        if (cmd.hasOption("p")) {
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
            PathFinder pathFinder = new PathFinder(maze);

            logger.info("**** Computing path");

            try {
                // Display the path to reach the end.
                String factorPath = pathFinder.factorizedPath();
                System.out.println("The path is:" + factorPath);
                
            } catch (Exception e) {
                logger.info("PATH NOT COMPUTED");
            }
        }
    }
}
