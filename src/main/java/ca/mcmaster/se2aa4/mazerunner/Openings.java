package ca.mcmaster.se2aa4.mazerunner;

public class Openings {
    // Method to determine the location of the entrance on the west border
    private static int[] westEntrance(char[][] maze) {
        int column = 0;
        int row = 0;

        while (row < maze.length && maze[row][column] == '#') {
            row++;
        }

        int[] entranceCoords = { row, column };

        return entranceCoords;
    }

    // Method to determine the location of the exit on the east border
    private static int[] eastExit(char[][] maze) {
        int column = maze[0].length - 1;
        int row = 0;

        while (row < maze.length && maze[row][column] == '#') {
            row++;
        }

        int[] exitCoords = { row, column };

        return exitCoords;
    }
    
    // Method to return the west entrance
    public static int[] getWestEntrance(char[][] maze) {
        return westEntrance(maze);
    }

    // Method to return the east entrance
    public static int[] getEastEntrance(char[][] maze) {
        return eastExit(maze);
    }

    // Method to return the west exit
    public static int[] getWestExit(char[][] maze) {
        return westEntrance(maze);
    }

    // Method to return the east exit
    public static int[] getEastExit(char[][] maze) {
        return eastExit(maze);
    }
}
