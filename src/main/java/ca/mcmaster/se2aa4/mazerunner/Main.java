package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        
        try {
            Configuration config = new Configuration(args);
            PathFinder pathFinder = new PathFinder();

            int[] entrance = pathFinder.entrance(config.parsedMaze());
            System.out.println("Entrance [row, column]: " + Arrays.toString(entrance));

            int[] exit = pathFinder.exit(config.parsedMaze());
            System.out.println("Exit [row, column]: " + Arrays.toString(exit));

        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");

        // Walking Skeleton
        logger.info("Walking Skeleton");
        
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
