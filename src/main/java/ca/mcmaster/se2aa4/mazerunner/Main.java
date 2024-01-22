package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");

        // Walking Skeleton
        logger.info("Walking Skeleton");
        
        PathFinder pathFinder = new PathFinder();

        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
