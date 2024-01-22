package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {

    private static final Logger logger = LogManager.getLogger();



    public Configuration(String[] args) throws Exception{
        Options options = new Options();
        CommandLineParser commandLineParser = new DefaultParser();

        options.addOption("i", true, "FileName with option reacting to -i");
        
        CommandLine cmd = commandLineParser.parse(options, args);
        String filePath = cmd.getOptionValue("i");
        
        logger.info("**** Reading the maze from file " + filePath);

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') {
                    System.out.print("WALL ");
                } else if (line.charAt(i) == ' ' || line.charAt(i) == '\0') {
                    System.out.print("PASS ");
                }
            }
            System.out.print(System.lineSeparator());
        }
    }
}
