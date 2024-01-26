package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

public class MazeCreator {

    // Sub-routine to create the 2D array from the parsed maze
    public char[][] createMaze(String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        // Obtains the width and height of the maze
        int width = getWidth(filePath);
        int height = getHeight(filePath);

        char[][] maze = new char[height][width];

        // Inserts the walls and empty spaces of the maze to a 2D Array
        for (int row = 0; (line = reader.readLine()) != null; row++) {
            for (int column = 0; column < line.length(); column++) {
                if (line.charAt(column) == '#') {
                    maze[row][column] = '#';
                } else if (line.charAt(column) == ' ' || line.charAt(column) == '\0') {
                    maze[row][column] = ' ';
                }
            }
        }

        reader.close();

        return maze;
    }

    public MazeCreator(String filePath) throws Exception {
    }
    
    // Sub-routine to obtain the width of the maze being parsed.
    private int getWidth(String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int width = 0;

        // Sets the length of the line to the width if the line is not null.
        if ((reader.readLine()) != null) {
            width = reader.readLine().length();
        }

        reader.close();

        return width;
    }
    
    // Sub-routine to obtain the height of the maze being parsed.
    private int getHeight(String filePath) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int height = 0;

        // Iterates through all the lines to obtain the number of rows in the maze.
        while ((reader.readLine()) != null) {
            height++;
        }

        reader.close();

        return height;
    }


}
