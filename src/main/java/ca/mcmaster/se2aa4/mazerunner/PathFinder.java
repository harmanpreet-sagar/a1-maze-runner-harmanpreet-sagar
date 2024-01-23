package ca.mcmaster.se2aa4.mazerunner;

public class PathFinder {

    public PathFinder() {}

    // Method to move forward
    public static String forward() {
        return "F";
    }

    // Method to turn right
    public static String right() {
        return "R";
    }

    // Method to turn left
    public static String left() {
        return "L";
    }

    // Method to determine the location of the entrance
    public int[] entrance(char[][] maze) {
        int column = 0;
        int row = 0;

        while (row < maze.length && maze[row][column] == '#') {
            row++;
        }

        int[] entranceCoords = { row, column };

        return entranceCoords;
    }

    // Method to determine the location of the exit
    public int[] exit(char[][] maze) {
        int column = maze[0].length - 1;
        int row = 0;

        while (row < maze.length && maze[row][column] == '#') {
            row++;
        }
        
        int[] exitCoords = { row, column };

        return exitCoords;
    }

    // Method to determine the canonical path
    public String canonicalPath(char[][] maze, int[] entrance, int[] exit) {
        return "FFFFF";
    }
    
    // Method to determine the factorized path
    public static String factorizedPath() {
        return "";
    }

}
