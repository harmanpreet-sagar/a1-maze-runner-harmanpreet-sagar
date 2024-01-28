package ca.mcmaster.se2aa4.mazerunner;

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

        logger.trace("**** Reading the maze from file " + filePath);

        // Parses the maze and converts it into a 2D array
        maze = MazeCreator.getCreatedMaze(filePath);

        // Executes the appropriate instructions based on whether the user has
        // entered the -p flag
        if (cmd.hasOption("p")) {
            String pathCheck = cmd.getOptionValue("p");
            VerifyPath verifyPath = new VerifyPath(maze, pathCheck);
            logger.trace("Path Verification Requested");

            try {
                logger.trace("Path verification successful");
                System.out.println(verifyPath.pathVerificationResult());
            } catch (Exception e) {
                logger.trace("/!\\ An error has occurred /!\\");
                System.out.println("incorrect path");
            }
        } else {
            PathFinder pathFinder = new PathFinder(maze);

            logger.trace("**** Computing path");

            try {
                logger.trace("PATH COMPUTED");
                // Display the path to reach the end.
                System.out.println(pathFinder.getFactorizedPath());
                
            } catch (Exception e) {
                logger.info("PATH NOT COMPUTED");
            }
        }
    }
}
