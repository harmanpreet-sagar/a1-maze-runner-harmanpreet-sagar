package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        
        try {
            Configuration config = new Configuration(args);
            PathFinder pathFinder = new PathFinder();

            char[][] parseMaze = config.parsedMaze();

            int[] entrance = pathFinder.entrance(parseMaze);
            System.out.println("Entrance [row, column]: " + Arrays.toString(entrance));

            int[] exit = pathFinder.exit(parseMaze);
            System.out.println("Exit [row, column]: " + Arrays.toString(exit));

            logger.info("**** Computing path");

            // Print the canonical path
            try {
                String path = pathFinder.canonicalPath(parseMaze);
                System.out.println("The canonical path is: " + path);
            } catch (Exception e) {
                logger.info("PATH NOT COMPUTED");
            }
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("/!\\ An error has occured /!\\");
        }

        logger.info("** End of MazeRunner");
    }
}
