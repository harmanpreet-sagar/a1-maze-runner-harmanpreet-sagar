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
    private String canonicalPath(char[][] maze) {
        int[] startLocation;
        int[] stopLocation;
        String orientation;
        
        // Default direction for maze path finding algorithm is West to East
        boolean westEast = true;
        
        if (westEast) {
            startLocation = entrance(maze);
            stopLocation = exit(maze);
            orientation = "EAST";
        } else {
            startLocation = exit(maze);
            stopLocation = entrance(maze);
            orientation = "WEST";
        }
        
        int[] currentLocation = startLocation;

        System.out.println("Entrance [row, column]: " + Arrays.toString(startLocation));
        System.out.println("Exit [row, column]: " + Arrays.toString(stopLocation));

        StringBuilder sb = new StringBuilder();

        while (!Arrays.equals(currentLocation, stopLocation)) {
            int[] checkLoc = CheckLocation(orientation, currentLocation);

            // If there is a wall, the object turns left, else it moves forward
            // and turns right.
            if (maze[checkLoc[0]][checkLoc[1]] == '#') {
                orientation = nextOrientation(orientation, "LEFT");
                sb.append(left());
            } else {
                currentLocation = checkLoc;
                sb.append(forward());
                orientation = nextOrientation(orientation, "RIGHT");
                sb.append(right());
            }

        }
        // Added this as at the end it usually ends at "FR" instead of "FRL",
        // which then can easily be modified to remove the "RL".
        sb.append(left());

        String path = sb.toString();
        
        // Since RL just puts the orientation exactly how it was, we can remove
        // it from the path.
        path = path.replaceAll("RL", "");
        return path;
    }
    
    // Method to determine the factorized path
    public String factorizedPath(char[][] maze) {
        StringBuilder sb = new StringBuilder(" ");
        String canonPath = canonicalPath(maze);
        char currentAlpha = 'F';
        int counter = 0;
        
        // Algorithm to convert the canonical path to a factorized path
        for (int i = 0; i < canonPath.length(); i++) {
            if (canonPath.charAt(i) == currentAlpha) {
                counter++;
            } else {
                sb.append(counter);
                sb.append(currentAlpha);
                sb.append(" ");
                counter = 1;
                currentAlpha = canonPath.charAt(i);
            }
        }

        // Appends the last character
        sb.append(counter);
        sb.append(canonPath.charAt(canonPath.length() - 1));

        String factorPath = sb.toString();

        factorPath = factorPath.replaceAll(" 1F", " F");
        factorPath = factorPath.replaceAll(" 1R", " R");
        factorPath = factorPath.replaceAll(" 1L", " L");
        
        return factorPath;
    }

    // Method to determine the location to be checked for wall.
    private int[] CheckLocation(String orientation, int[] currentLocation) {
        int[] frontLocation = Arrays.copyOf(currentLocation, currentLocation.length);

        if (orientation.equals("EAST")) {
            frontLocation[1] += 1;
        } else if (orientation.equals("WEST")) {
            frontLocation[1] -= 1;
        } else if (orientation.equals("NORTH")) {
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
            case "WEST":
                if (turnDirection.equals("LEFT")) {
                    nextOrientation = "SOUTH";
                } else if (turnDirection.equals("RIGHT")) {
                    nextOrientation = "NORTH";
                }
                break;
            case "EAST":
                if (turnDirection.equals("LEFT")) {
                    nextOrientation = "NORTH";
                } else if (turnDirection.equals("RIGHT")) {
                    nextOrientation = "SOUTH";
                }
                break;
            case "NORTH":
                if (turnDirection.equals("LEFT")) {
                    nextOrientation = "WEST";
                } else if (turnDirection.equals("RIGHT")) {
                    nextOrientation = "EAST";
                }
                break;
            case "SOUTH":
                if (turnDirection.equals("LEFT")) {
                    nextOrientation = "EAST";
                } else if (turnDirection.equals("RIGHT")) {
                    nextOrientation = "WEST";
                }
                break;
            default:
                nextOrientation = currentOrientation;
                break;
        }

        return nextOrientation;
    }
}
