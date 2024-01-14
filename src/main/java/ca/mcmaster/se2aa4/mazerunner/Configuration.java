package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Configuration {

    public Configuration(String[] args) {}

    public long seed() {
        return System.currentTimeMillis();
    }

    public int width() {
        return 50;
    }

    public BufferedWriter outputFile() {
        return new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public int height() {
        return 50;
    }
}
