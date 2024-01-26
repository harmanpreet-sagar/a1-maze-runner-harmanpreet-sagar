package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        
        try {
            Configuration.config(args);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("/!\\ An error has occurred /!\\");
        }

        logger.info("** End of MazeRunner");
    }
}
