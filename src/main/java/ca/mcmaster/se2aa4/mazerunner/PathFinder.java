package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;


public class PathFinder {

    public PathFinder() {}

    // Method to move forward
    private static String forward() {
        return "F";
    }

    // Method to turn right
    private static String right() {
        return "R";
    }

    // Method to turn left
    private static String left() {
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
    public String canonicalPath(char[][] maze) {
        int[] startLocation = entrance(maze);
        int[] stopLocation = exit(maze);
        int[] currentLocation = startLocation;

        String newOrientation = "RIGHT";

        StringBuilder sb = new StringBuilder();

        while (!Arrays.equals(currentLocation, stopLocation)) {
            int[] checkLoc = CheckLocation(newOrientation, currentLocation);

            // If there is a wall, the object turns left, else it moves forward
            // and turns right.
            if (maze[checkLoc[0]][checkLoc[1]] == '#') {
                newOrientation = nextOrientation(newOrientation, "LEFT");
                sb.append(left());
            } else {
                currentLocation = checkLoc;
                sb.append(forward());
                newOrientation = nextOrientation(newOrientation, "RIGHT");
                sb.append(right());
            }

        }
        sb.append(left());

        String path = sb.toString();
        
        // Since RL just puts the orientation exactly how it was, we can remove
        // it from the path.
        path = path.replaceAll("RL", "");
        return path;
    }
    
    // Method to determine the factorized path
    public static String factorizedPath() {
        return "";
    }

    // Method to determine the location to be checked for wall.
    private int[] CheckLocation(String orientation, int[] currentLocation) {
        int[] frontLocation = Arrays.copyOf(currentLocation, currentLocation.length);

        if (orientation.equals("RIGHT")) {
            frontLocation[1] += 1;
        } else if (orientation.equals("LEFT")) {
            frontLocation[1] -= 1;
        } else if (orientation.equals("UP")) {
            frontLocation[0] -= 1;
        } else {
            frontLocation[0] += 1;
        }

        return frontLocation;
    }

    // Method to determine the next orientation of the object traversing the maze.
    private String nextOrientation(String currentOrientation, String turnDirection) {
        String nextOrientation = currentOrientation;

        switch (currentOrientation) {
            case "LEFT":
                if (turnDirection.equals("LEFT")) {
                    nextOrientation = "DOWN";
                } else if (turnDirection.equals("RIGHT")) {
                    nextOrientation = "UP";
                }
                break;
            case "RIGHT":
                if (turnDirection.equals("LEFT")) {
                    nextOrientation = "UP";
                } else if (turnDirection.equals("RIGHT")) {
                    nextOrientation = "DOWN";
                }
                break;
            case "UP":
                if (turnDirection.equals("LEFT")) {
                    nextOrientation = "LEFT";
                } else if (turnDirection.equals("RIGHT")) {
                    nextOrientation = "RIGHT";
                }
                break;
            case "DOWN":
                if (turnDirection.equals("LEFT")) {
                    nextOrientation = "RIGHT";
                } else if (turnDirection.equals("RIGHT")) {
                    nextOrientation = "LEFT";
                }
                break;
            default:
                nextOrientation = currentOrientation;
                break;
        }

        return nextOrientation;
    }
}
