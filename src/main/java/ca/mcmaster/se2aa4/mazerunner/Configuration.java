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

    private char[][] maze;

    public Configuration(String[] args) throws Exception {
        Options options = new Options();
        CommandLineParser commandLineParser = new DefaultParser();

        options.addOption("i", true, "FileName with option reacting to -i");
        CommandLine cmd = commandLineParser.parse(options, args);

        // Sets default filePath to that of the straight.maz.txt
        String filePath = cmd.getOptionValue("i", "examples/straight.maz.txt");
        // String filePath = "examples/straight.maz.txt";  // Only used for the mvp

        logger.info("**** Reading the maze from file " + filePath);

        // Parses the maze and converts it into a 2D array
        MazeCreator mazeCreator = new MazeCreator(filePath);
        maze = mazeCreator.createMaze(filePath);

        // Prints out the maze.
        for (char[] rows : maze) {
            System.out.println(Arrays.toString(rows));
        }
    }
    
    public char[][] parsedMaze() {
        return maze;
    }
}
